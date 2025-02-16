/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */


package com.sun.xml.ws.resources;

import com.sun.istack.localization.Localizable;
import com.sun.istack.localization.LocalizableMessageFactory;
import com.sun.istack.localization.Localizer;


/**
 * Defines string formatting method for each constant in the resource file
 * 
 */
public final class ManagementMessages {

    private final static LocalizableMessageFactory messageFactory = new LocalizableMessageFactory("com.sun.xml.ws.resources.management");
    private final static Localizer localizer = new Localizer();

    public static Localizable localizableWSM_1008_EXPECTED_INTEGER_DISPOSE_DELAY_VALUE(Object arg0) {
        return messageFactory.getMessage("WSM_1008_EXPECTED_INTEGER_DISPOSE_DELAY_VALUE", arg0);
    }

    /**
     * WSM1008: Expected an integer as value of the endpointDisposeDelay attribute, got this instead: "{0}".
     * 
     */
    public static String WSM_1008_EXPECTED_INTEGER_DISPOSE_DELAY_VALUE(Object arg0) {
        return localizer.localize(localizableWSM_1008_EXPECTED_INTEGER_DISPOSE_DELAY_VALUE(arg0));
    }

    public static Localizable localizableWSM_1004_EXPECTED_XML_TAG(Object arg0, Object arg1) {
        return messageFactory.getMessage("WSM_1004_EXPECTED_XML_TAG", arg0, arg1);
    }

    /**
     * WSM1004: Expected tag <{0}> but instead read <{1}>.
     * 
     */
    public static String WSM_1004_EXPECTED_XML_TAG(Object arg0, Object arg1) {
        return localizer.localize(localizableWSM_1004_EXPECTED_XML_TAG(arg0, arg1));
    }

    public static Localizable localizableWSM_1007_FAILED_MODEL_TRANSLATOR_INSTANTIATION() {
        return messageFactory.getMessage("WSM_1007_FAILED_MODEL_TRANSLATOR_INSTANTIATION");
    }

    /**
     * WSM1007: Failed to create a ModelTranslator instance.
     * 
     */
    public static String WSM_1007_FAILED_MODEL_TRANSLATOR_INSTANTIATION() {
        return localizer.localize(localizableWSM_1007_FAILED_MODEL_TRANSLATOR_INSTANTIATION());
    }

    public static Localizable localizableWSM_1002_EXPECTED_MANAGEMENT_ASSERTION(Object arg0) {
        return messageFactory.getMessage("WSM_1002_EXPECTED_MANAGEMENT_ASSERTION", arg0);
    }

    /**
     * WSM1002: Expected policy assertion {0} in this namespace.
     * 
     */
    public static String WSM_1002_EXPECTED_MANAGEMENT_ASSERTION(Object arg0) {
        return localizer.localize(localizableWSM_1002_EXPECTED_MANAGEMENT_ASSERTION(arg0));
    }

    public static Localizable localizableWSM_1006_CLIENT_MANAGEMENT_ENABLED() {
        return messageFactory.getMessage("WSM_1006_CLIENT_MANAGEMENT_ENABLED");
    }

    /**
     * WSM1006: The management property of the ManagedClient policy assertion is set to on. Clients cannot be managed and this setting will be ignored.
     * 
     */
    public static String WSM_1006_CLIENT_MANAGEMENT_ENABLED() {
        return localizer.localize(localizableWSM_1006_CLIENT_MANAGEMENT_ENABLED());
    }

    public static Localizable localizableWSM_1001_FAILED_ASSERTION(Object arg0) {
        return messageFactory.getMessage("WSM_1001_FAILED_ASSERTION", arg0);
    }

    /**
     * WSM1001: Failed to get policy assertion {0}.
     * 
     */
    public static String WSM_1001_FAILED_ASSERTION(Object arg0) {
        return localizer.localize(localizableWSM_1001_FAILED_ASSERTION(arg0));
    }

    public static Localizable localizableWSM_1005_EXPECTED_COMMUNICATION_CHILD() {
        return messageFactory.getMessage("WSM_1005_EXPECTED_COMMUNICATION_CHILD");
    }

    /**
     * WSM1005: Expected to find a CommunicationServerImplementation tag as child node of CommunicationServerImplementations.
     * 
     */
    public static String WSM_1005_EXPECTED_COMMUNICATION_CHILD() {
        return localizer.localize(localizableWSM_1005_EXPECTED_COMMUNICATION_CHILD());
    }

    public static Localizable localizableWSM_1003_MANAGEMENT_ASSERTION_MISSING_ID(Object arg0) {
        return messageFactory.getMessage("WSM_1003_MANAGEMENT_ASSERTION_MISSING_ID", arg0);
    }

    /**
     * WSM1003: Policy assertion {0} must have id attribute when management is enabled.
     * 
     */
    public static String WSM_1003_MANAGEMENT_ASSERTION_MISSING_ID(Object arg0) {
        return localizer.localize(localizableWSM_1003_MANAGEMENT_ASSERTION_MISSING_ID(arg0));
    }

}
