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


package com.sun.tools.ws.resources;

import com.sun.istack.localization.Localizable;
import com.sun.istack.localization.LocalizableMessageFactory;
import com.sun.istack.localization.Localizer;


/**
 * Defines string formatting method for each constant in the resource file
 * 
 */
public final class JavacompilerMessages {

    private final static LocalizableMessageFactory messageFactory = new LocalizableMessageFactory("com.sun.tools.ws.resources.javacompiler");
    private final static Localizer localizer = new Localizer();

    public static Localizable localizableJAVACOMPILER_CLASSPATH_ERROR(Object arg0) {
        return messageFactory.getMessage("javacompiler.classpath.error", arg0);
    }

    /**
     * {0} is not available in the classpath, requires Sun's JDK version 5.0 or latter.
     * 
     */
    public static String JAVACOMPILER_CLASSPATH_ERROR(Object arg0) {
        return localizer.localize(localizableJAVACOMPILER_CLASSPATH_ERROR(arg0));
    }

    public static Localizable localizableJAVACOMPILER_NOSUCHMETHOD_ERROR(Object arg0) {
        return messageFactory.getMessage("javacompiler.nosuchmethod.error", arg0);
    }

    /**
     * There is no such method {0} available, requires Sun's JDK version 5.0 or latter.
     * 
     */
    public static String JAVACOMPILER_NOSUCHMETHOD_ERROR(Object arg0) {
        return localizer.localize(localizableJAVACOMPILER_NOSUCHMETHOD_ERROR(arg0));
    }

    public static Localizable localizableJAVACOMPILER_ERROR(Object arg0) {
        return messageFactory.getMessage("javacompiler.error", arg0);
    }

    /**
     * error : {0}.
     * 
     */
    public static String JAVACOMPILER_ERROR(Object arg0) {
        return localizer.localize(localizableJAVACOMPILER_ERROR(arg0));
    }

}
