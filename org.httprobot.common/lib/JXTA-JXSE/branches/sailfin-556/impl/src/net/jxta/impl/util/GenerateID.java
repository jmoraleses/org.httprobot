/*
 * Copyright (c) 2001-2007 Sun Microsystems, Inc. All rights reserved.
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
package net.jxta.impl.util;


import net.jxta.codat.CodatID;
import net.jxta.id.IDFactory;
import net.jxta.peer.PeerID;
import net.jxta.peergroup.PeerGroup;
import net.jxta.peergroup.PeerGroupID;
import net.jxta.pipe.PipeID;
import net.jxta.platform.ModuleClassID;
import net.jxta.platform.ModuleSpecID;


/**
 * @deprecated This would be much better as a JXTA shell command. Will be removed soon.
 *  
 * Generate a new JXTA IDs from the command line.
 * 
 * @author <a href="mailto:burton@relativity.yi.org">Kevin A. Burton</a>
 */
@Deprecated
public class GenerateID {

    /**
     * main()
     *
     */
    public static void main(String[] args) {

        try { 
            
            ModuleClassID classID = IDFactory.newModuleClassID();
            
            ModuleSpecID specID = IDFactory.newModuleSpecID(classID);
            
            PeerGroupID groupID = IDFactory.newPeerGroupID();
            
            PeerID peerID = IDFactory.newPeerID(groupID);
            
            PipeID pipeID = IDFactory.newPipeID(groupID);
            
            CodatID codatID = IDFactory.newCodatID(groupID);
            
            ModuleSpecID groupSpecID = IDFactory.newModuleSpecID(PeerGroup.allPurposePeerGroupSpecID.getBaseClass());

            System.out.println("Module Class ID             -  " + classID.toString());
            System.out.println("Module Spec  ID             -  " + specID.toString());
            System.out.println("Peer Group ID               -  " + groupID.toString());
            System.out.println("Peer ID                     -  " + peerID.toString());
            System.out.println("Pipe ID                     -  " + pipeID.toString());
            System.out.println("Codat ID                    -  " + codatID.toString());
            System.out.println("Peer Group Module Spec ID   -  " + groupSpecID.toString());
            
        } catch (Throwable t) {
            
            t.printStackTrace();
            
        }
        
    }
    
}
