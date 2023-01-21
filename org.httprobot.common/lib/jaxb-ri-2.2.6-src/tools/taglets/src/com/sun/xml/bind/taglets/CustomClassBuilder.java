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

package com.sun.xml.bind.taglets;

import java.util.HashSet;
import java.util.List;
import java.util.Arrays;
import java.lang.reflect.Method;
import java.io.IOException;
import java.io.File;

import com.sun.tools.doclets.internal.toolkit.builders.*;
import com.sun.tools.doclets.internal.toolkit.ClassWriter;
import com.sun.tools.doclets.internal.toolkit.Configuration;
import com.sun.tools.doclets.internal.toolkit.util.Util;
import com.sun.tools.doclets.internal.toolkit.util.DirectoryManager;
import com.sun.tools.doclets.internal.toolkit.util.DocletConstants;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.PackageDoc;

/**
 * Builds the summary for a given class.
 *
 * This code is not part of an API.
 * It is implementation that is subject to change.
 * Do not use it as an API
 *
 * @author Jamie Ho
 * @since 1.5
 */
public class CustomClassBuilder extends AbstractBuilder {

    /**
     * The root element of the class XML is {@value}.
     */
    public static final String ROOT = "ClassDoc";

    /**
     * The class being documented.
     */
    private ClassDoc classDoc;

    /**
     * The doclet specific writer.
     */
    private ClassWriter writer;

    /**
     * Keep track of whether or not this classdoc is an interface.
     */
    private boolean isInterface = false;

    /**
     * Keep track of whether or not this classdoc is an enum.
     */
    private boolean isEnum = false;

    /**
     * Construct a new ClassBuilder.
     *
     * @param configuration the current configuration of the
     *                      doclet.
     */
    private CustomClassBuilder(Configuration configuration) {
        super(configuration);
    }

    /**
     * Construct a new ClassBuilder.
     *
     * @param configuration the current configuration of the doclet.
     * @param classDoc the class being documented.
     * @param writer the doclet specific writer.
     */
    public static CustomClassBuilder getInstance(Configuration configuration,
        ClassDoc classDoc, ClassWriter writer)
    throws Exception {
        CustomClassBuilder builder = new CustomClassBuilder(configuration);
        builder.configuration = configuration;
        builder.classDoc = classDoc;
        builder.writer = writer;
        if (classDoc.isInterface()) {
            builder.isInterface = true;
        } else if (classDoc.isEnum()) {
            builder.isEnum = true;
            Util.setEnumDocumentation(configuration, classDoc);
        }
        if(containingPackagesSeen == null) {
            containingPackagesSeen = new HashSet();
        }
        return builder;
    }

    /**
     * {@inheritDoc}
     */
    public void invokeMethod(String methodName, Class[] paramClasses,
            Object[] params)
    throws Exception {
        if (DEBUG) {
            configuration.root.printError("DEBUG: " + this.getClass().getName()
                + "." + methodName);
        }
        Method method = this.getClass().getMethod(methodName, paramClasses);
        method.invoke(this, params);
    }

    /**
     * {@inheritDoc}
     */
    public void build() throws IOException {
        build(LayoutParser.getInstance(configuration).parseXML(ROOT));
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return ROOT;
    }

     /**
      * Handles the &lt;ClassDoc> tag.
      *
      * @param elements the XML elements that specify how to document a class.
      */
     public void buildClassDoc(List elements) throws Exception {
        build(elements);
        writer.close();
        copyDocFiles();
     }


     /**
      * Copy the doc files for the current ClassDoc if necessary.
      */
     private void copyDocFiles() {
        PackageDoc containingPackage = classDoc.containingPackage();
        if((configuration.packages == null ||
                Arrays.binarySearch(configuration.packages,
                                    containingPackage) < 0) &&
           ! containingPackagesSeen.contains(containingPackage.name())){
            //Only copy doc files dir if the containing package is not
            //documented AND if we have not documented a class from the same
            //package already. Otherwise, we are making duplicate copies.
            Util.copyDocFiles(configuration,
                Util.getPackageSourcePath(configuration,
                    classDoc.containingPackage()) +
                DirectoryManager.getDirectoryPath(classDoc.containingPackage())
                    + File.separator, DocletConstants.DOC_FILES_DIR_NAME, true);
            containingPackagesSeen.add(containingPackage.name());
        }
     }

    /**
     * Build the header of the page.
     */
    public void buildClassHeader() {
        String key;
        if (isInterface) {
            key =  "doclet.Interface";
        } else if (isEnum) {
            key = "doclet.Enum";
        } else {
            key =  "doclet.Class";
        }

        writer.writeHeader(configuration.getText(key) + " " + classDoc.name());
    }

    /**
     * Build the class tree documentation.
     */
    public void buildClassTree() {
//        writer.writeClassTree();
    }

    /**
     * If this is a class, list all interfaces
     * implemented by this class.
     */
    public void buildImplementedInterfacesInfo() {
//        writer.writeImplementedInterfacesInfo();
    }

    /**
     * If this is an interface, list all super interfaces.
     */
    public void buildSuperInterfacesInfo() {
//        writer.writeSuperInterfacesInfo();
    }

    /**
     * List the parameters of this class.
     */
    public void buildTypeParamInfo() {
        writer.writeTypeParamInfo();
    }

    /**
     * List all the classes extend this one.
     */
    public void buildSubClassInfo() {
    }

    /**
     * List all the interfaces that extend this one.
     */
    public void buildSubInterfacesInfo() {
    }

    /**
     * If this is an interface, list all classes that implement this interface.
     */
    public void buildInterfaceUsageInfo () {
    }

    /**
     * If this is an inner class or interface, list the enclosing class or
     * interface.
     */
    public void buildNestedClassInfo () {
    }

    /**
     * If this class is deprecated, print the appropriate information.
     */
    public void buildDeprecationInfo () {
    }

    /**
     * Build the signature of the current class.
     */
    public void buildClassSignature() {
    }

    /**
     * Build the class description.
     */
    public void buildClassDescription() {
       writer.writeClassDescription();
    }

    /**
     * Build the tag information for the current class.
     */
    public void buildClassTagInfo() {
       writer.writeClassTagInfo();
    }

    public void buildMemberSummary(List elements) throws Exception {
    }

    public void buildEnumConstantsDetails(List elements) throws Exception {
    }

    public void buildFieldDetails(List elements) throws Exception {
    }

    public void buildConstructorDetails(List elements) throws Exception {
    }

    public void buildMethodDetails(List elements) throws Exception {
    }

    /**
     * Build the footer of the page.
     */
    public void buildClassFooter() {
        writer.writeFooter();
    }
}
