/*
 * Copyright (c) 2001-2007 Sun Microsystems, Inc.  All rights reserved.
 *  
 *  The Sun Project JXTA(TM) Software License
 *  
 *  Redistribution and use in source and binary forms, with or without 
 *  modification, are permitted provided that the following conditions are met:
 *  
 *  1. Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *  
 *  2. Redistributions in binary form must reproduce the above copyright notice, 
 *     this list of conditions and the following disclaimer in the documentation 
 *     and/or other materials provided with the distribution.
 *  
 *  3. The end-user documentation included with the redistribution, if any, must 
 *     include the following acknowledgment: "This product includes software 
 *     developed by Sun Microsystems, Inc. for JXTA(TM) technology." 
 *     Alternately, this acknowledgment may appear in the software itself, if 
 *     and wherever such third-party acknowledgments normally appear.
 *  
 *  4. The names "Sun", "Sun Microsystems, Inc.", "JXTA" and "Project JXTA" must 
 *     not be used to endorse or promote products derived from this software 
 *     without prior written permission. For written permission, please contact 
 *     Project JXTA at http://www.jxta.org.
 *  
 *  5. Products derived from this software may not be called "JXTA", nor may 
 *     "JXTA" appear in their name, without prior written permission of Sun.
 *  
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 *  INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND 
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL SUN 
 *  MICROSYSTEMS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, 
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT 
 *  LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, 
 *  OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
 *  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *  
 *  JXTA is a registered trademark of Sun Microsystems, Inc. in the United 
 *  States and other countries.
 *  
 *  Please see the license information page at :
 *  <http://www.jxta.org/project/www/license.html> for instructions on use of 
 *  the license in source files.
 *  
 *  ====================================================================
 *  
 *  This software consists of voluntary contributions made by many individuals 
 *  on behalf of Project JXTA. For more information on Project JXTA, please see 
 *  http://www.jxta.org.
 *  
 *  This license is based on the BSD license adopted by the Apache Foundation. 
 */
package net.jxta.impl.endpoint.servlethttp;

import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.jxta.endpoint.EndpointAddress;
import net.jxta.endpoint.Message;
import net.jxta.endpoint.MessageElement;
import net.jxta.endpoint.StringMessageElement;
import net.jxta.impl.endpoint.BlockingMessenger;
import net.jxta.impl.endpoint.EndpointServiceImpl;
import net.jxta.impl.util.TimeUtils;
import net.jxta.impl.util.threads.SelfCancellingTask;
import net.jxta.impl.util.threads.TaskManager;
import net.jxta.logging.Logging;
import net.jxta.peergroup.PeerGroupID;

/**
 * Simple messenger that waits for a message to give back to the requesting client
 *
 * <p/>This messenger is not entirely thread-safe. You should not use any
 * of the <code>sendMessage</code> methods from more than one thread.
 *
 */
final class HttpServletMessenger extends BlockingMessenger {
    
    /**
     *  Logger
     */
    private final static transient Logger LOG = Logger.getLogger(HttpServletMessenger.class.getName());
    
    // We need an explicit idle state. outgoingMessage being null is not enough
    // because there is an intermediate state where the http servlet must know
    // that there is nothing new to be sent but the relay side must also know
    // that status has not been collected yet (the messenger is not ready to be
    // reused). We use outgoingMessage == null to know that the message
    // has already been picked-up by the servlet thread, and
    // sendResult != IDLE to know that the result has not yet been collected
    // by the relay sender thread.
    
    private final static int SEND_IDLE = 0;
    private final static int SEND_INPROGRESS = 1;
    private final static int SEND_SUCCESS = 2;
    private final static int SEND_FAIL = 3;
    private final static int SEND_TOOLONG = 4;
    
    private final static long MAX_SENDING_BLOCK = 2 * TimeUtils.AMINUTE;
    private final static long MAX_SENDING_WAIT = 3 * TimeUtils.ASECOND;
    
    private final static EndpointAddress nullEndpointAddr = new EndpointAddress("http", "0.0.0.0:0", null, null);
    
    private final EndpointAddress logicalAddress;
    private final MessageElement srcAddressElement;
    
    /**
     *  The message "queue"
     */
    private Message outgoingMessage = null;
    
    private int sendResult = SEND_IDLE;
    private long sendingSince = 0;

	private ScheduledFuture<?> expirationTaskHandle;
    
    /**
     *  Allows us to schedule the closing of a messenger.
     */
    private static class ScheduledExpiry extends SelfCancellingTask {

        /**
         *  The messenger we will be expiring.
         */
        HttpServletMessenger messenger;
        
        ScheduledExpiry(HttpServletMessenger toExpire) {
            messenger = toExpire;
        }
        
        /**
         *  {@inheritDoc}
         */
        public void cancel() {
            // It is important we clear the messenger because Timer doesn't
            // remove cancelled TimerTasks from it's queue until they fire. This
            // means that the Messenger would not be GCed until the TimerTask
            // fired.
        	// 24.06.09 - This is now properly handled by the scheduledExecutorService
            messenger = null;
            super.cancel();
        }
        
        /**
         *  {@inheritDoc}
         */
        @Override
        public void execute() {

            try {

                HttpServletMessenger temp = messenger;

                while( ( null != temp ) &&  ( null != temp.outgoingMessage || temp.sendResult == SEND_INPROGRESS  ) ){
                	
                    Logging.logCheckedFine(LOG, "Waiting for outgoingMessage to clear before we close...", temp);

                    // Wait a while
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException ignore) {
                    }
                    
                    if( temp.isClosed() || null == this.messenger ){
                        Logging.logCheckedFine(LOG, "Messenger closed while waiting for send to complete.  Operation cancelled: ", temp);
                    	return;
                    }
                    
                }
                
                messenger = null;
                
                if (null != temp) temp.close();
                
            } catch (Throwable all) {

                Logging.logCheckedSevere(LOG, "Uncaught Throwable in timer task :", Thread.currentThread().getName(), "\n", all);
                
            } finally {

                Logging.logCheckedFine(LOG, "Messenger self destruct complete.");
                
            }
        }
    }
    
    /**
     *  Standard constructor.
     *
     * @param peerGroupID the peer group id
     * @param srcAddress  source address
     * @param logicalAddress logical address
     * @param validFor validity in milliseconds
     */
    HttpServletMessenger(PeerGroupID peerGroupID, TaskManager taskManager, EndpointAddress srcAddress, EndpointAddress logicalAddress, long validFor) {
        
        // We do not use self destruction.
        super(peerGroupID, nullEndpointAddr, taskManager, false);
        
        this.logicalAddress = logicalAddress;
        
        this.srcAddressElement = new StringMessageElement(EndpointServiceImpl.MESSAGE_SOURCE_NAME, srcAddress.toString(), null);
        
        if ((0 != validFor) && (validFor < Long.MAX_VALUE)) {
            ScheduledExecutorService scheduledExecutorService = taskManager.getScheduledExecutorService();
            expirationTaskHandle = scheduledExecutorService.schedule(new ScheduledExpiry(this), validFor, TimeUnit.MILLISECONDS);
        }
        
        Logging.logCheckedFine(LOG, "HttpServletMessenger\n\t", this);
        
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void closeImpl() {

        Logging.logCheckedFine(LOG, "close\n\t", this);
        
        ScheduledFuture<?> cancelExpire = expirationTaskHandle;

        expirationTaskHandle = null;
        if (null != cancelExpire) {
            cancelExpire.cancel(false);
        }
        
        super.close();
        
        notifyAll();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public EndpointAddress getLogicalDestinationImpl() {
        return logicalAddress;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isIdleImpl() {
        // We do not use self destruction.
        return false;
    }
    
    /**
     * Send messages. Messages are queued and processed by a thread
     * running HttpClientConnection.
     */
    @Override
    public synchronized void sendMessageBImpl(Message message, String service, String serviceParam) throws IOException {
        
        Logging.logCheckedFine(LOG, "Send ", message, " to ", dstAddress, "\n\t", this);
        
        if (isClosed()) {

            IOException failure = new IOException("Messenger was closed, it cannot be used to send messages.");
            Logging.logCheckedFine(LOG, failure);
            
            throw failure;
        }
        
        // Set the message with the appropriate src and dest address
        message.replaceMessageElement(EndpointServiceImpl.MESSAGE_SOURCE_NS, srcAddressElement);
        
        EndpointAddress destAddressToUse = getDestAddressToUse(service, serviceParam);
        
        MessageElement dstAddressElement = new StringMessageElement(EndpointServiceImpl.MESSAGE_DESTINATION_NAME,
                destAddressToUse.toString(), null);
        
        message.replaceMessageElement(EndpointServiceImpl.MESSAGE_DESTINATION_NS, dstAddressElement);
        
        // doSend returns false only when this messenger is closed.
        if (!doSend(message)) {

            // send message failed
            IOException failure = new IOException("Messenger was closed, it cannot be used to send messages.");
            Logging.logCheckedFine(LOG, "sendMessage failed (messenger closed).\n\t", this , "\n", failure);
            throw failure;

        }
        
        Logging.logCheckedFine(LOG, "sendMessage successful for ", message, "\n\t", this);

    }
    
    // Must be called from synchronized context only.
    private boolean doSend(Message message) {
        
        // No need to wait for the messenger to be free. Transport
        // messengers have no obligation to behave nicely if they're
        // used by mltiple threads. If a thread comes here while
        // we're already busy sending, then that's a congestion. Just
        // drop the new message (pretend it went out).
        // This is not even so nasty, because jetty has a sizeable
        // output buffer. As long as that buffer is not full, sending
        // is instantaneou. If sending starts blocking, then we can honestly
        // drop messages.
        
        if (isClosed()) {
            return false;
        }
        
        long now = TimeUtils.timeNow();
        
        if (sendResult != SEND_IDLE) {
            // check if that connection is a lemon
            if ((sendResult == SEND_TOOLONG) && (now > (sendingSince + MAX_SENDING_BLOCK))) {
                close();
            }
            return true;
        }
        
        // put the message on the outgoing "queue" of size 1
        outgoingMessage = message;
        sendResult = SEND_INPROGRESS;
        sendingSince = now;
        
        Logging.logCheckedFine(LOG, "Queued ", message);
        
        // notify the servlet if it was waiting for a message
        notifyAll();
        
        // wait for the result of the send Since there is ample
        // buffering underneath, we're not supposed to wait for long;
        // unless we outdo the connection, in which case, we might as
        // well start dropping. No point in blocking.
        // FIXME: jice@jxta.org - this is a rudimentary fix. We need
        // something like watchedOutputStream instead. Being forced
        // to do a two-way handshake with a servlet thread is pretty
        // ridiculous too. We need a simpler http transport.
        
        long absoluteTimeOut = TimeUtils.toAbsoluteTimeMillis(MAX_SENDING_WAIT);
        
        while (!isClosed()) {
            if (sendResult != SEND_INPROGRESS) {
                break;
            }
            
            long waitfor = TimeUtils.toRelativeTimeMillis(absoluteTimeOut);
            
            if (waitfor <= 0) {
                break;
            }
            
            try {
                wait(waitfor);
            } catch (InterruptedException e) {
                Thread.interrupted();
                Logging.logCheckedFine(LOG, "InterruptedException timeout = ", MAX_SENDING_WAIT, "\n\t", this, "\n", e);
                break;
            }
        }
        
        Logging.logCheckedFine(LOG, "Got result\n\t", this);
        
        if (isClosed() && (SEND_INPROGRESS == sendResult)) {
            return false;
        }
        
        // If the operation completed just confirm that we're done
        // reading the result too. Else mark that we don't care.
        // When it completes the result will be discarded. By default,
        // the we return ok. If the operation did not complete fast
        // enough, that's what we return.
        
        boolean result = (sendResult != SEND_FAIL);
        
        if (sendResult == SEND_INPROGRESS) {
            sendResult = SEND_TOOLONG;
            outgoingMessage = null;
        } else {
            sendResult = SEND_IDLE;
        }
        
        // Let another contending thread use this messenger.
        notifyAll();
        
        return result;
    }
    
    /**
     *  Retrieve a message from the "queue" of messages for the servlet.
     *
     *  @param timeout Number of milliseconds to wait for a message. Per Java
     *  convention 0 (zero) means wait forever.
     *  @return the message or <code>null</code> if no message was available
     *  before the timeout was reached.
     *  @throws InterruptedException If the thread is interrupted while waiting.
     */
    protected synchronized Message waitForMessage(long timeout) throws InterruptedException {

        Logging.logCheckedFine(LOG, "Waiting (", (0 == timeout ? "forever" : Long.toString(timeout)), ") for message\n\t", this);
        
        if (0 == timeout) {
            // it's forever
            timeout = Long.MAX_VALUE;
        }
        
        long absoluteTimeOut = TimeUtils.toAbsoluteTimeMillis(timeout);
        
        while (!isClosed() && (null == outgoingMessage)) {
            long waitfor = TimeUtils.toRelativeTimeMillis(absoluteTimeOut);
            
            if (waitfor <= 0) {
                break;
            }
            
            wait(waitfor);
        }
        
        // get the message
        Message result = outgoingMessage;
        
        outgoingMessage = null; // Msg can only be picked-up once.
        
        if (!isClosed() && (result == null)) {
            // ABSURD, but seems to happen: the message sent was NULL
            // and the sender thread is waiting for the completion event.
            // There would be no such thing, but we can make sure it stops
            // waiting and still leave the messenger in a sane state if there
            // was no such absurdity going on.
            
            sendResult = SEND_IDLE;
            notifyAll();
        }
        
        Logging.logCheckedFine(LOG, "Returning ", result, "\n\t", this);
        
        return result;
    }
    
    protected synchronized void messageSent(boolean wasSuccessful) {

        Logging.logCheckedFine(LOG, "messageSent(", wasSuccessful, ")\n\t", this);
        
        if (SEND_TOOLONG == sendResult) {
            // No-one cares for the result any more. Let the next send go.
            sendResult = SEND_IDLE;
        } else {
            sendResult = wasSuccessful ? SEND_SUCCESS : SEND_FAIL;
        }
        
        notifyAll();
    }
    
    /**
     * {@inheritDoc}
     *
     *  <p/>An implementation for debugging. Do not depend on the format.
     */
    @Override
    public String toString() {
        return "[" + super.toString() + "] isClosed=" + isClosed() + " sendResult=" + sendResult + " outmsg=" + outgoingMessage;
    }
}
