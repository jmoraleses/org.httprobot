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

package net.jxta.impl.protocol;


import net.jxta.document.Advertisement;
import net.jxta.document.AdvertisementFactory;
import net.jxta.document.Attribute;
import net.jxta.document.Element;
import net.jxta.document.MimeMediaType;
import net.jxta.document.StructuredDocument;
import net.jxta.document.XMLElement;
import net.jxta.id.ID;
import net.jxta.protocol.ConfigParams;

import java.util.Enumeration;
import java.util.logging.Logger;


/**
 * Configuration container for any Peer Group.
 */
public class GroupConfig extends ConfigParams implements Cloneable {
    
    /**
     *  Logger
     */
    private static final Logger LOG = Logger.getLogger(GroupConfig.class.getName());
    
    private static final String advType = "jxta:GroupConfig";
    
    /**
     * Instantiator for GroupConfig
     */
    public final static class Instantiator implements AdvertisementFactory.Instantiator {
        
        /**
         * {@inheritDoc}
         */
        public String getAdvertisementType() {
            return advType;
        }
        
        /**
         * {@inheritDoc}
         */
        public Advertisement newInstance() {
            return new GroupConfig();
        }
        
        /**
         * {@inheritDoc}
         */
        public Advertisement newInstance(Element root) {
            if (!XMLElement.class.isInstance(root)) {
                throw new IllegalArgumentException(getClass().getName() + " only supports XLMElement");
            }
            
            return new GroupConfig((XMLElement) root);
        }
    }
    
    /**
     * Use the Instantiator through the factory
     */
    GroupConfig() {}
    
    /**
     * Use the Instantiator through the factory
     *
     * @param doc the element
     */
    GroupConfig(XMLElement doc) {
        String doctype = doc.getName();
        
        String typedoctype = "";
        Attribute itsType = doc.getAttribute("type");
        
        if (null != itsType) {
            typedoctype = itsType.getValue();
        }
        
        if (!doctype.equals(getAdvertisementType()) && !getAdvertisementType().equals(typedoctype)) {
            throw new IllegalArgumentException(
                    "Could not construct : " + getClass().getName() + "from doc containing a " + doc.getName());
        }
        
        Enumeration<XMLElement> elements = doc.getChildren();
        
        while (elements.hasMoreElements()) {

            Element elem = (Element) elements.nextElement();
            
            if (!handleElement(elem)) {


            }

        }
        
    }
    
    /**
     * Make a safe clone of this GroupConfig.
     *
     * @return Object an object of class GroupConfig that is a deep-enough
     *         copy of this one.
     */
    @Override
    public GroupConfig clone() {
        GroupConfig result = (GroupConfig) super.clone();
        
        return result;
    }
    
    /**
     * returns the advertisement type
     *
     * @return string type
     */
    public static String getAdvertisementType() {
        return advType;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getAdvType() {
        return getAdvertisementType();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public StructuredDocument getDocument(MimeMediaType encodeAs) {
        StructuredDocument adv = (StructuredDocument) super.getDocument(encodeAs);
        
        addDocumentElements(adv);
        
        return adv;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getIndexFields() {
        return new String[0];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ID getID() {
        return null;
    }
}
