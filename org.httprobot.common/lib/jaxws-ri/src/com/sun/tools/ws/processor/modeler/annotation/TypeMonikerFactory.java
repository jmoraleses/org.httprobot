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

package com.sun.tools.ws.processor.modeler.annotation;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author dkohlert
 */
public class TypeMonikerFactory {

    public static TypeMoniker getTypeMoniker(TypeMirror typeMirror) {
        if (typeMirror == null)
            throw new NullPointerException();

        if (typeMirror.getKind().isPrimitive())
            return new PrimitiveTypeMoniker((PrimitiveType) typeMirror);
        else if (typeMirror.getKind().equals(TypeKind.ARRAY))
            return new ArrayTypeMoniker((ArrayType) typeMirror);
        else if (typeMirror.getKind().equals(TypeKind.DECLARED))
            return new DeclaredTypeMoniker((DeclaredType) typeMirror);
        return getTypeMoniker(typeMirror.toString());
    }

    public static TypeMoniker getTypeMoniker(String typeName) {
        return new StringMoniker(typeName);
    }

    static class ArrayTypeMoniker implements TypeMoniker {
        private TypeMoniker arrayType;

        public ArrayTypeMoniker(ArrayType type) {
            arrayType = TypeMonikerFactory.getTypeMoniker(type.getComponentType());
        }

        public TypeMirror create(ProcessingEnvironment apEnv) {
            return apEnv.getTypeUtils().getArrayType(arrayType.create(apEnv));
        }
    }
    static class DeclaredTypeMoniker implements TypeMoniker {
        private Name typeDeclName;
        private Collection<TypeMoniker> typeArgs = new ArrayList<TypeMoniker>();

        public DeclaredTypeMoniker(DeclaredType type) {
            typeDeclName = ((TypeElement) type.asElement()).getQualifiedName();
            for (TypeMirror arg : type.getTypeArguments())
                typeArgs.add(TypeMonikerFactory.getTypeMoniker(arg));
        }

        public TypeMirror create(ProcessingEnvironment apEnv) {
            TypeElement typeDecl = apEnv.getElementUtils().getTypeElement(typeDeclName);
            TypeMirror[] tmpArgs = new TypeMirror[typeArgs.size()];
            int idx = 0;
            for (TypeMoniker moniker : typeArgs)
                tmpArgs[idx++] = moniker.create(apEnv);

            return apEnv.getTypeUtils().getDeclaredType(typeDecl, tmpArgs);
        }
    }
    static class PrimitiveTypeMoniker implements TypeMoniker {
        private TypeKind kind;

        public PrimitiveTypeMoniker(PrimitiveType type) {
            kind = type.getKind();
        }

        public TypeMirror create(ProcessingEnvironment apEnv) {
            return apEnv.getTypeUtils().getPrimitiveType(kind);
        }
    }
    static class StringMoniker implements TypeMoniker {
        private String typeName;

        public StringMoniker(String typeName) {
            this.typeName = typeName;
        }

        public TypeMirror create(ProcessingEnvironment apEnv) {
            return apEnv.getTypeUtils().getDeclaredType(apEnv.getElementUtils().getTypeElement(typeName));
        }
    }
}
