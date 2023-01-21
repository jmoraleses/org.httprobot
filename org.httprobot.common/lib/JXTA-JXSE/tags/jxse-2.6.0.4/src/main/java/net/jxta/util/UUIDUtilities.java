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

package net.jxta.util;

import java.security.SecureRandom;
import java.util.UUID;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Utility methods to augment the capabilities of the java.util.UUID class
 * as provided in the JDK.
 */
public final class UUIDUtilities {
    
    /**
     * The point at which the Gregorian calendar rules are used, measured in
     * milliseconds from the standard epoch.  Default is October 15, 1582
     * (Gregorian) 00:00:00 UTC or -12219292800000L.
     */
    private static final long GREGORIAN_MILLIS_OFFSET = 12219292800000L;
    
    /**
     *  offset of this computer relative to utc
     */
    private static long utc_offset = 0L;
    
    /**
     *  Time at which we last generated a version 1 UUID in relative
     *  milliseconds from 00:00:00.00, 15 October 1582 UTC.
     */
    private static long lastTimeSequence = 0L;
    
    /**
     *   Count of how many version 1 UUIDs we have generated at this time
     *  sequence value.
     */
    private static long inSequenceCounter = 0L;
    
    /**
     *  pseudo random value to prevent clock collisions on the same computer.
     */
    private static long clock_seq = 0L;
    
    /**
     *  pseudo random value. If available, this should be seeded with the MAC
     *  address of a local network interface.
     */
    private static long node = 0L;
    
    /**
     * Static initializer.
     */
    static {
        SecureRandom randNum = new SecureRandom();
        
        TimeZone timeZone = TimeZone.getDefault();
        Calendar gregorianCalendar = new GregorianCalendar(timeZone);
        
        // FIXME 20031024 bondolo@jxta.org In theory we should be doing this
        // EVERY time we generate a UUID. In practice because of we use a random
        // clock_seq we don't have to.
        utc_offset = gregorianCalendar.get(Calendar.ZONE_OFFSET)
                + gregorianCalendar.get(Calendar.DST_OFFSET);
        
        // Generate a random clock seq
        clock_seq = randNum.nextInt() & 0x03FFL;
        
        // Generate a random node ID since we can't get the MAC Address
        node = (randNum.nextLong() & 0x0000FFFFFFFFFFFFL);
        
        // mask in the multicast bit since we don't know if its unique.
        node |= 0x0000800000000000L;
    }

    /**
     * Prevent construction.
     */
    private UUIDUtilities() {
        // Empty
    }
    
    /**
     *  Generate a new UUID value. The UUID returned is a version 1 IETF
     *  variant UUID.
     *
     *  <p/>The node value used is currently a random value rather than the
     *  normal ethernet MAC address because the MAC address is not directly
     *  accessible from within java.
     *
     *  @return UUID returns a version 1 IETF variant UUID.
     */
    public static UUID newSeqUUID() {
        long mostSig = 0L, leastSig = 0L;
        
        long timeSeq = getTimeSequence();
        
        mostSig |= (timeSeq & 0x0FFFFFFFFL) << 32;
        mostSig |= ((timeSeq >> 32) & 0x0FFFFL) << 16;
        mostSig |= (0x01L) << 12; // version 1;
        mostSig |= ((timeSeq >> 48) & 0x00FFFL);
        
        leastSig |= (0x02L) << 62; // ietf variant
        leastSig |= ((clock_seq >> 8) & 0x03FL) << 56;
        leastSig |= (clock_seq & 0x0FFL) << 48;
        leastSig |= node & 0x0FFFFFFFFFFFFL;
        
        return new UUID(mostSig, leastSig);
    }
    
    /**
     *  Returns a formatted time sequence field containing the elapsed time in
     *  100 nano units since 00:00:00.00, 15 October 1582. Since the normal
     *  clock resolution is coarser than 100 nano than this value, the lower
     *  bits are generated in sequence for each call within the same milli.
     *
     *  @return time sequence value
     */
    private static synchronized long getTimeSequence() {
        // convert to 100 nano units;
        long now = (System.currentTimeMillis() 
                - GREGORIAN_MILLIS_OFFSET + utc_offset) * 10000L;
        
        if (now > lastTimeSequence) {
            lastTimeSequence = now;
            // XXX bondolo@jxta.org It might be better to set this to a random
            // value and just watch for rollover. The reason is that there may
            // be more than one instance running on the same computer which is
            // generating UUIDs, but is not excluded by our synchronization.
            // A random value would reduce collisions.
            inSequenceCounter = 0;
        } else {
            inSequenceCounter++;
            if (inSequenceCounter >= 10000L) {
                // we allow the clock to skew forward rather than wait. It's
                // really unlikely that anyone will be continuously generating
                // more than 10k UUIDs per milli for very long.
                lastTimeSequence += 10000L;
                inSequenceCounter = 0;
            }
        }
        
        return (lastTimeSequence + inSequenceCounter);
    }

}
