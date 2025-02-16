/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 1997-2011 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tools.ws.processor.generator;

import com.sun.istack.NotNull;
import com.sun.tools.ws.processor.model.Fault;
import com.sun.tools.ws.processor.model.ModelProperties;
import com.sun.tools.ws.processor.model.Port;
import com.sun.tools.ws.processor.model.java.JavaInterface;
import com.sun.tools.ws.processor.model.java.JavaStructureMember;
import com.sun.tools.ws.processor.modeler.ModelerConstants;
import com.sun.tools.ws.util.ClassNameInfo;
import com.sun.xml.ws.util.StringUtils;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

/**
 * Names provides utility methods used by other wscompile classes
 * for dealing with identifiers.
 *
 * @author WS Development Team
 */
public final class Names {

    private Names() {
    }

    public static String getPortName(Port port) {
        String javaPortName =
            (String) port.getProperty(ModelProperties.PROPERTY_JAVA_PORT_NAME);
        if (javaPortName != null) {
            return javaPortName;
        } else {
            QName portName =
                (QName) port.getProperty(
                    ModelProperties.PROPERTY_WSDL_PORT_NAME);
            if (portName != null) {
                return portName.getLocalPart();
            } else {
                String name = stripQualifier(port.getJavaInterface().getName());
                return ClassNameInfo.replaceInnerClassSym(name);
            }
        }
    }


    public static String stripQualifier(String name) {
        return ClassNameInfo.getName(name);
    }

    public static String getPackageName(String className) {
        String packageName = ClassNameInfo.getQualifier(className);
        return packageName != null ? packageName : "";
    }


    public static String customJavaTypeClassName(JavaInterface intf) {
        return intf.getName();
    }

    public static String customExceptionClassName(Fault fault) {
        return fault.getJavaException().getName();
    }

    public static String getExceptionClassMemberName(){
        return GeneratorConstants.FAULT_CLASS_MEMBER_NAME.getValue();
    }

    public static boolean isJavaReservedWord(String name) {
        return RESERVED_WORDS.get(name) != null;
    }

    /**
     * See if its a java keyword name, if so then mangle the name
     */
    public static @NotNull String getJavaReserverVarialbeName(@NotNull String name){
        return (RESERVED_WORDS.get(name) == null) ? name : RESERVED_WORDS.get(name);
    }

    /* here we check on wether return values datatype is
       boolean. If its boolean, instead of a get method
       its set a is<MethodName> to comply with JavaBeans
       Pattern spec */
    public static String getJavaMemberReadMethod(JavaStructureMember member) {
        String return_value;
        if (member.getType().getRealName().equals(ModelerConstants.BOOLEAN_CLASSNAME.getValue())) {
            return_value = GeneratorConstants.IS.getValue() + StringUtils.capitalize(member.getName());
        } else {
            return_value = GeneratorConstants.GET.getValue() + StringUtils.capitalize(member.getName());
        }
        return (return_value);
    }

    public static String getResponseName(String messageName) {
        return messageName + GeneratorConstants.RESPONSE.getValue();
    }

    private static final Map<String, String> RESERVED_WORDS = new HashMap<String, String>(53);

    static {
        RESERVED_WORDS.put("abstract", "_abstract");
        RESERVED_WORDS.put("assert", "_assert");
        RESERVED_WORDS.put("boolean", "_boolean");
        RESERVED_WORDS.put("break", "_break");
        RESERVED_WORDS.put("byte", "_byte");
        RESERVED_WORDS.put("case", "_case");
        RESERVED_WORDS.put("catch", "_catch");
        RESERVED_WORDS.put("char", "_char");
        RESERVED_WORDS.put("class", "_class");
        RESERVED_WORDS.put("const", "_const");
        RESERVED_WORDS.put("continue", "_continue");
        RESERVED_WORDS.put("default", "_default");
        RESERVED_WORDS.put("do", "_do");
        RESERVED_WORDS.put("double", "_double");
        RESERVED_WORDS.put("else", "_else");
        RESERVED_WORDS.put("extends", "_extends");
        RESERVED_WORDS.put("false", "_false");
        RESERVED_WORDS.put("final", "_final");
        RESERVED_WORDS.put("finally", "_finally");
        RESERVED_WORDS.put("float", "_float");
        RESERVED_WORDS.put("for", "_for");
        RESERVED_WORDS.put("goto", "_goto");
        RESERVED_WORDS.put("if", "_if");
        RESERVED_WORDS.put("implements", "_implements");
        RESERVED_WORDS.put("import", "_import");
        RESERVED_WORDS.put("instanceof", "_instanceof");
        RESERVED_WORDS.put("int", "_int");
        RESERVED_WORDS.put("interface", "_interface");
        RESERVED_WORDS.put("long", "_long");
        RESERVED_WORDS.put("native", "_native");
        RESERVED_WORDS.put("new", "_new");
        RESERVED_WORDS.put("null", "_null");
        RESERVED_WORDS.put("package", "_package");
        RESERVED_WORDS.put("private", "_private");
        RESERVED_WORDS.put("protected", "_protected");
        RESERVED_WORDS.put("public", "_public");
        RESERVED_WORDS.put("return", "_return");
        RESERVED_WORDS.put("short", "_short");
        RESERVED_WORDS.put("static", "_static");
        RESERVED_WORDS.put("strictfp", "_strictfp");
        RESERVED_WORDS.put("super", "_super");
        RESERVED_WORDS.put("switch", "_switch");
        RESERVED_WORDS.put("synchronized", "_synchronized");
        RESERVED_WORDS.put("this", "_this");
        RESERVED_WORDS.put("throw", "_throw");
        RESERVED_WORDS.put("throws", "_throws");
        RESERVED_WORDS.put("transient", "_transient");
        RESERVED_WORDS.put("true", "_true");
        RESERVED_WORDS.put("try", "_try");
        RESERVED_WORDS.put("void", "_void");
        RESERVED_WORDS.put("volatile", "_volatile");
        RESERVED_WORDS.put("while", "_while");
        RESERVED_WORDS.put("enum", "_enum");
    }
}
