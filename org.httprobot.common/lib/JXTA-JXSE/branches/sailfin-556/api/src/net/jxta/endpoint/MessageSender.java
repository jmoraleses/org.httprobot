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

package net.jxta.endpoint;


/**
 *  A MessageSender is a MessageTransport that is able to send messages to
 *  remote peers using some protocol. MessageSenders provide facilities for 
 *  sending point-to-point (unicast) messages.
 *
 *  <p/>MessageSenders additionally describe themselves in terms of their
 *  abilities.
 *  <dl>
 *  <dt>{@link #isConnectionOriented()}</dt><dd>Indicates that the
 *  Message Transport can provide efficient transport of a series of messages to
 *  the same destination</dd>
 *  <dt>{link@ #allowRouting()}</dt><dd>Indicates that the Message Transport 
 *  can be used in the routing of messages to destinations which are not 
 *  directly reachable via this transport.</dd>
 *  </dl>
 *
 *  @see net.jxta.endpoint.MessageTransport
 *  @see net.jxta.endpoint.Message
 *  @see net.jxta.endpoint.Messenger
 *  @see net.jxta.endpoint.EndpointService
 *  @see net.jxta.endpoint.MessageReceiver
 *  @see net.jxta.endpoint.MessagePropagater
 */
public interface MessageSender extends MessageTransport {
    
    /**
     *  Returns the {@link EndpointAddress} which will be used as the source
     *  address for all messages sent by this message sender. This is the
     *  "preferred" address to which replies should be sent. This address is not
     *  necessarily the best or only address by which the peer may be reached.
     *
     *  <p/>The public address may also be for a different message transport.
     *
     *  @return an EndpointAddress containing the public address for this
     *  message receiver.
     */
    public EndpointAddress getPublicAddress();
    
    /**
     *  Returns {@code true} if the Message Transport is connection oriented
     *  (like TCP/IP). Indicates that the Message Transport can provide 
     *  efficient transport of a series of messages to the same destination.
     *
     *  @return {@code true} if the Message Transport is connection oriented.
     */
    public boolean isConnectionOriented();
    
    /**
     *  Returns true if the Message Transport can be used by the EndpointRouter.
     *  Indicates that the Message Transport can be used in the routing of
     *  messages to destinations which are not directly reachable via this 
     *  transport.
     *
     *  <p/>More specifically, this Message Transport will be used to route 
     *  messages who's final destination is <b>not</b> one of the endpoint 
     *  addresses available from {@code getReachableEndpointAddresses}.
     *
     * @return <tt>true</tt> if the protocol can be used by the EndpointRouter
     */
    public boolean allowsRouting();
    
    /**
     *  Return a {@link Messenger} for sending messages to the specified
     *  destination {@link EndpointAddress}.
     *
     @param dest The destination address for which a messenger is requested.
     *  @param hint An optional hint for the transport to use when creating the 
     *  messenger. The format of the hint is specific to each Message Transport
     *  and may be {@code null} if no hint is provided.
     *  @return a Messenger or {@code null} if the destination is not reachable.
     */
    public Messenger getMessenger(EndpointAddress dest, Object hint);
    
    /**
     *  Returns {@code true} if the specified destination address is reachable 
     *  via this Message Transport otherwise returns {@code false}.
     *
     *  @deprecated This operation is often very expensive and usually  
     *  duplicates the work of {@link #getMessenger}. If you want to determine
     *  the reachability of a destination, get a Messenger to the destination.
     *
     *  @param addr Address to ping
     *  @return {@code true} if the specified destination address is reachable
     *  via this Message Transport otherwise returns {@code false}.
     */
    @Deprecated
    public boolean ping(EndpointAddress addr);
}
