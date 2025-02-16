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

package com.sun.tools.ws.processor.modeler.annotation;

import com.sun.codemodel.CodeWriter;
import com.sun.codemodel.JAnnotationArrayMember;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JBlock;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JCommentPart;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JDocComment;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;
import com.sun.codemodel.JVar;
import com.sun.codemodel.writer.ProgressCodeWriter;
import com.sun.tools.jxc.ap.InlineAnnotationReaderImpl;
import com.sun.tools.jxc.model.nav.ApNavigator;
import com.sun.tools.ws.ToolVersion;
import com.sun.tools.ws.processor.generator.GeneratorBase;
import com.sun.tools.ws.processor.generator.GeneratorConstants;
import com.sun.tools.ws.processor.generator.Names;
import com.sun.tools.ws.processor.modeler.ModelerException;
import com.sun.tools.ws.processor.util.DirectoryUtil;
import com.sun.tools.ws.resources.WebserviceapMessages;
import com.sun.tools.ws.util.ClassNameInfo;
import com.sun.tools.ws.wscompile.FilerCodeWriter;
import com.sun.tools.ws.wscompile.WsgenOptions;
import com.sun.tools.ws.wsdl.document.soap.SOAPStyle;
import com.sun.xml.bind.v2.model.annotation.AnnotationReader;
import com.sun.xml.bind.v2.model.nav.Navigator;
import com.sun.xml.ws.model.AbstractWrapperBeanGenerator;
import com.sun.xml.ws.spi.db.BindingHelper;
import com.sun.xml.ws.util.StringUtils;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttachmentRef;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import javax.xml.ws.WebFault;
import javax.xml.ws.WebServiceException;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.sun.codemodel.ClassType.CLASS;
import static com.sun.tools.ws.processor.modeler.annotation.WebServiceConstants.BEAN;
import static com.sun.tools.ws.processor.modeler.annotation.WebServiceConstants.FAULT_INFO;
import static com.sun.tools.ws.processor.modeler.annotation.WebServiceConstants.JAXWS_PACKAGE_PD;
import static com.sun.tools.ws.processor.modeler.annotation.WebServiceConstants.PD_JAXWS_PACKAGE_PD;
import static com.sun.tools.ws.processor.modeler.annotation.WebServiceConstants.RESPONSE;

/**
 * This class generates the request/response and Exception Beans
 * used by the JAX-WS runtime.
 *
 * @author  WS Development Team
 */
public class WebServiceWrapperGenerator extends WebServiceVisitor {
    private Set<String> wrapperNames;
    private Set<String> processedExceptions;
    private JCodeModel cm;
    private final MakeSafeTypeVisitor makeSafeVisitor;

    private static final FieldFactory FIELD_FACTORY = new FieldFactory();

    private final AbstractWrapperBeanGenerator ap_generator =
            new ApWrapperBeanGenerator(InlineAnnotationReaderImpl.theInstance,
                    new ApNavigator(builder.getProcessingEnvironment()), FIELD_FACTORY);

    private final class ApWrapperBeanGenerator extends AbstractWrapperBeanGenerator<TypeMirror, TypeElement, ExecutableElement, MemberInfo> {

        protected ApWrapperBeanGenerator(
                AnnotationReader<TypeMirror, TypeElement, ?, ExecutableElement> annReader,
                Navigator<TypeMirror, TypeElement, ?, ExecutableElement> nav, BeanMemberFactory<TypeMirror, MemberInfo> beanMemberFactory) {
            super(annReader, nav, beanMemberFactory);
        }

        protected TypeMirror getSafeType(TypeMirror type) {
            return WebServiceWrapperGenerator.this.getSafeType(type);
        }

        protected TypeMirror getHolderValueType(TypeMirror paramType) {
            return builder.getHolderValueType(paramType);
        }

        protected boolean isVoidType(TypeMirror type) {
            return type != null && type.getKind().equals(TypeKind.VOID);
        }

    }

    private static final class FieldFactory implements AbstractWrapperBeanGenerator.BeanMemberFactory<TypeMirror, MemberInfo> {

        public MemberInfo createWrapperBeanMember(TypeMirror paramType,
                                                  String paramName, List<Annotation> jaxb) {
            return new MemberInfo(paramType, paramName, jaxb);
        }
    }

    public WebServiceWrapperGenerator(ModelBuilder builder, AnnotationProcessorContext context) {
        super(builder, context);
        makeSafeVisitor = new MakeSafeTypeVisitor(builder.getProcessingEnvironment());
    }

    protected void processWebService(WebService webService, TypeElement d) {
        cm = new JCodeModel();
        wrapperNames = new HashSet<String>();
        processedExceptions = new HashSet<String>();
    }

    protected void postProcessWebService(WebService webService, TypeElement d) {
        super.postProcessWebService(webService, d);
        doPostProcessWebService(webService, d);
    }

    protected void doPostProcessWebService(WebService webService, TypeElement d) {
        if (cm != null) {
            File sourceDir = builder.getSourceDir();
            assert(sourceDir != null);
            WsgenOptions options = builder.getOptions();
            try {
                CodeWriter cw = new FilerCodeWriter(sourceDir, options);
                if(options.verbose)
                    cw = new ProgressCodeWriter(cw, System.out);
                cm.build(cw);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void processMethod(ExecutableElement method, WebMethod webMethod) {
        builder.log("WrapperGen - method: "+method);
        builder.log("method.getDeclaringType(): " + method.asType());
        if (wrapped && soapStyle.equals(SOAPStyle.DOCUMENT)) {
            generateWrappers(method, webMethod);
        }
        generateExceptionBeans(method);
    }

    private boolean generateExceptionBeans(ExecutableElement method) {
        String beanPackage = packageName + PD_JAXWS_PACKAGE_PD.getValue();
        if (packageName.length() == 0)
            beanPackage = JAXWS_PACKAGE_PD.getValue();
        boolean beanGenerated = false;
        for (TypeMirror thrownType : method.getThrownTypes()) {
            TypeElement typeDecl = (TypeElement) ((DeclaredType) thrownType).asElement();
            if (typeDecl == null) {
                builder.processError(WebserviceapMessages.WEBSERVICEAP_COULD_NOT_FIND_TYPEDECL(
                        thrownType.toString(), context.getRound()));
                return false;
            }
            boolean tmp = generateExceptionBean(typeDecl, beanPackage);
            beanGenerated = beanGenerated || tmp;
        }
        return beanGenerated;
    }

    private boolean duplicateName(String name) {
        for (String str : wrapperNames) {
            if (str.equalsIgnoreCase(name))
        return true;
        }
        wrapperNames.add(name);
    return false;
    }

    private boolean generateWrappers(ExecutableElement method, WebMethod webMethod) {
        boolean isOneway = method.getAnnotation(Oneway.class) != null;
        String beanPackage = packageName + PD_JAXWS_PACKAGE_PD.getValue();
        if (packageName.length() == 0)
            beanPackage = JAXWS_PACKAGE_PD.getValue();
        Name methodName = method.getSimpleName();
        String operationName = builder.getOperationName(methodName);
        operationName = webMethod != null && webMethod.operationName().length() > 0 ?
                webMethod.operationName() : operationName;
        String reqName = operationName;
        String resName = operationName + WebServiceConstants.RESPONSE.getValue();
        String reqNamespace = typeNamespace;
        String resNamespace = typeNamespace;

        String requestClassName = beanPackage + StringUtils.capitalize(method.getSimpleName().toString());
        RequestWrapper reqWrapper = method.getAnnotation(RequestWrapper.class);
        if (reqWrapper != null) {
            if (reqWrapper.className().length() > 0)
                requestClassName = reqWrapper.className();
            if (reqWrapper.localName().length() > 0)
                reqName = reqWrapper.localName();
            if (reqWrapper.targetNamespace().length() > 0)
                reqNamespace = reqWrapper.targetNamespace();
        }
        builder.log("requestWrapper: "+requestClassName);
///// fix for wsgen CR 6442344
        File file = new File(DirectoryUtil.getOutputDirectoryFor(requestClassName, builder.getSourceDir()),
                Names.stripQualifier(requestClassName) + GeneratorConstants.JAVA_SRC_SUFFIX.getValue());
        builder.getOptions().addGeneratedFile(file);
//////////
        boolean canOverwriteRequest = builder.canOverWriteClass(requestClassName);
        if (!canOverwriteRequest) {
            builder.log("Class " + requestClassName + " exists. Not overwriting.");
        }
        if (duplicateName(requestClassName) && canOverwriteRequest) {
            builder.processError(WebserviceapMessages.WEBSERVICEAP_METHOD_REQUEST_WRAPPER_BEAN_NAME_NOT_UNIQUE(
                    typeElement.getQualifiedName(), method.toString()));
        }

        String responseClassName = null;
        boolean canOverwriteResponse = canOverwriteRequest;
        if (!isOneway) {
            responseClassName = beanPackage+StringUtils.capitalize(method.getSimpleName().toString())+RESPONSE.getValue();
            ResponseWrapper resWrapper = method.getAnnotation(ResponseWrapper.class);
            if(resWrapper != null) {
                if (resWrapper.className().length() > 0)
                    responseClassName = resWrapper.className();
                if (resWrapper.localName().length() > 0)
                    resName = resWrapper.localName();
                if (resWrapper.targetNamespace().length() > 0)
                    resNamespace = resWrapper.targetNamespace();
            }
            canOverwriteResponse = builder.canOverWriteClass(responseClassName);
            if (!canOverwriteResponse) {
                builder.log("Class " + responseClassName + " exists. Not overwriting.");
            }
            if (duplicateName(responseClassName) && canOverwriteResponse) {
                builder.processError(WebserviceapMessages.WEBSERVICEAP_METHOD_RESPONSE_WRAPPER_BEAN_NAME_NOT_UNIQUE(
                        typeElement.getQualifiedName(), method.toString()));
            }
            file = new File(DirectoryUtil.getOutputDirectoryFor(responseClassName, builder.getSourceDir()),
                    Names.stripQualifier(responseClassName) + GeneratorConstants.JAVA_SRC_SUFFIX.getValue());
            builder.getOptions().addGeneratedFile(file);
        }
        //ArrayList<MemberInfo> reqMembers = new ArrayList<MemberInfo>();
        //ArrayList<MemberInfo> resMembers = new ArrayList<MemberInfo>();
        WrapperInfo reqWrapperInfo = new WrapperInfo(requestClassName);
        //reqWrapperInfo.setMembers(reqMembers);
        WrapperInfo resWrapperInfo = null;
        if (!isOneway) {
            resWrapperInfo = new WrapperInfo(responseClassName);
            //resWrapperInfo.setMembers(resMembers);
        }
        seiContext.setReqWrapperOperation(method, reqWrapperInfo);
        if (!isOneway)
            seiContext.setResWrapperOperation(method, resWrapperInfo);
        try {
            if (!canOverwriteRequest && !canOverwriteResponse) {
                return false;
            }

            JDefinedClass reqCls = null;
            if (canOverwriteRequest) {
                reqCls = getCMClass(requestClassName, CLASS);
            }

            JDefinedClass resCls = null;
            if (!isOneway && canOverwriteResponse) {
                resCls = getCMClass(responseClassName, CLASS);
            }

            // XMLElement Declarations
            writeXmlElementDeclaration(reqCls, reqName,reqNamespace);
            writeXmlElementDeclaration(resCls, resName, resNamespace);

            List<MemberInfo> reqMembers = ap_generator.collectRequestBeanMembers(method);
            List<MemberInfo> resMembers = ap_generator.collectResponseBeanMembers(method);

            // XmlType
            writeXmlTypeDeclaration(reqCls, reqName, reqNamespace, reqMembers);
            writeXmlTypeDeclaration(resCls, resName, resNamespace, resMembers);

            // class members
            writeMembers(reqCls, reqMembers);
            writeMembers(resCls, resMembers);

        } catch (Exception e) {
            throw new ModelerException("modeler.nestedGeneratorError",e);
        }
        return true;
    }

//    private List<Annotation> collectJAXBAnnotations(Declaration decl) {
//        List<Annotation> jaxbAnnotation = new ArrayList<Annotation>();
//        for(Class jaxbClass : jaxbAnns) {
//            Annotation ann = decl.getAnnotation(jaxbClass);
//            if (ann != null) {
//                jaxbAnnotation.add(ann);
//            }
//        }
//        return jaxbAnnotation;
//    }

    private TypeMirror getSafeType(TypeMirror type) {
        return makeSafeVisitor.visit(type, builder.getProcessingEnvironment().getTypeUtils());
    }

    private JType getType(TypeMirror typeMirror) {
        String type = typeMirror.toString();
        try {
//            System.out.println("typeName: "+typeName);
            return cm.parseType(type);
//            System.out.println("type: "+type);
        } catch (ClassNotFoundException e) {
            return cm.ref(type);
        }
    }

    private void writeMembers(JDefinedClass cls, Collection<MemberInfo> members) {
        if (cls == null)
            return;
        for (MemberInfo memInfo : members) {
            JType type = getType(memInfo.getParamType());
            JFieldVar field = cls.field(JMod.PRIVATE, type, memInfo.getParamName());
            annotateParameterWithJaxbAnnotations(memInfo, field);
        }
        for (MemberInfo memInfo : members) {
            writeMember(cls, memInfo.getParamType(),
                    memInfo.getParamName());
        }
    }

    private void annotateParameterWithJaxbAnnotations(MemberInfo memInfo, JFieldVar field) {
        List<Annotation> jaxbAnnotations = memInfo.getJaxbAnnotations();
        for(Annotation ann : jaxbAnnotations) {
            if (ann instanceof XmlMimeType) {
                JAnnotationUse jaxbAnn = field.annotate(XmlMimeType.class);
                jaxbAnn.param("value", ((XmlMimeType)ann).value());
            } else if (ann instanceof XmlJavaTypeAdapter) {
                JAnnotationUse jaxbAnn = field.annotate(XmlJavaTypeAdapter.class);
                XmlJavaTypeAdapter ja = (XmlJavaTypeAdapter) ann;
                try {
                    ja.value();
                    throw new AssertionError();
                } catch (MirroredTypeException e) {
                    jaxbAnn.param("value",getType(e.getTypeMirror()));
                }
                // XmlJavaTypeAdapter.type() is for package only. No need to copy.
            } else if (ann instanceof XmlAttachmentRef) {
                field.annotate(XmlAttachmentRef.class);
            } else if (ann instanceof XmlList){
                field.annotate(XmlList.class);
            } else if (ann instanceof XmlElement) {
                XmlElement elemAnn = (XmlElement)ann;
                JAnnotationUse jAnn = field.annotate(XmlElement.class);
                jAnn.param("name", elemAnn.name());
                jAnn.param("namespace", elemAnn.namespace());
                if (elemAnn.nillable()) {
                    jAnn.param("nillable", true);
                }
                if (elemAnn.required()) {
                     jAnn.param("required", true);
                }
            } else {
                throw new WebServiceException("SEI Parameter cannot have this JAXB annotation: " + ann);
            }
        }
    }

    protected JDefinedClass getCMClass(String className, com.sun.codemodel.ClassType type) {
        JDefinedClass cls;
        try {
            cls = cm._class(className, type);
        } catch (com.sun.codemodel.JClassAlreadyExistsException e){
            cls = cm._getClass(className);
        }
        return cls;
    }

    private boolean generateExceptionBean(TypeElement thrownDecl, String beanPackage) {
        if (!builder.isServiceException(thrownDecl.asType()))
            return false;

        String exceptionName = ClassNameInfo.getName(thrownDecl.getQualifiedName().toString());
        if (processedExceptions.contains(exceptionName))
            return false;
        processedExceptions.add(exceptionName);
        WebFault webFault = thrownDecl.getAnnotation(WebFault.class);
        String className = beanPackage+ exceptionName + BEAN.getValue();

        Collection<MemberInfo> members = ap_generator.collectExceptionBeanMembers(thrownDecl);
        boolean isWSDLException = isWSDLException(members, thrownDecl);
        String namespace = typeNamespace;
        String name = exceptionName;
        FaultInfo faultInfo;
        if (isWSDLException) {
            TypeMirror beanType =  getFaultInfoMember(members).getParamType();
            faultInfo = new FaultInfo(TypeMonikerFactory.getTypeMoniker(beanType), true);
            namespace = webFault.targetNamespace().length()>0 ?
                               webFault.targetNamespace() : namespace;
            name = webFault.name().length()>0 ?
                          webFault.name() : name;
            faultInfo.setElementName(new QName(namespace, name));
            seiContext.addExceptionBeanEntry(thrownDecl.getQualifiedName(), faultInfo, builder);
            return false;
        }
        if (webFault != null) {
            namespace = webFault.targetNamespace().length()>0 ?
                        webFault.targetNamespace() : namespace;
            name = webFault.name().length()>0 ?
                   webFault.name() : name;
            className = webFault.faultBean().length()>0 ?
                        webFault.faultBean() : className;

        }
        JDefinedClass cls = getCMClass(className, CLASS);
        faultInfo = new FaultInfo(className, false);

        if (duplicateName(className)) {
            builder.processError(WebserviceapMessages.WEBSERVICEAP_METHOD_EXCEPTION_BEAN_NAME_NOT_UNIQUE(
                    typeElement.getQualifiedName(), thrownDecl.getQualifiedName()));
        }

        boolean canOverWriteBean = builder.canOverWriteClass(className);
        if (!canOverWriteBean) {
            builder.log("Class " + className + " exists. Not overwriting.");
            seiContext.addExceptionBeanEntry(thrownDecl.getQualifiedName(), faultInfo, builder);
            return false;
        }
        if (seiContext.getExceptionBeanName(thrownDecl.getQualifiedName()) != null)
            return false;

        //write class comment - JAXWS warning
        JDocComment comment = cls.javadoc();
        for (String doc : GeneratorBase.getJAXWSClassComment(ToolVersion.VERSION.MAJOR_VERSION)) {
            comment.add(doc);
        }

        // XmlElement Declarations
        writeXmlElementDeclaration(cls, name, namespace);

        // XmlType Declaration
        //members = sortMembers(members);
        XmlType xmlType = thrownDecl.getAnnotation(XmlType.class);
        String xmlTypeName = (xmlType != null && !xmlType.name().equals("##default")) ? xmlType.name() : exceptionName;
        String xmlTypeNamespace = (xmlType != null && !xmlType.namespace().equals("##default")) ? xmlType.namespace() : typeNamespace;
        writeXmlTypeDeclaration(cls, xmlTypeName, xmlTypeNamespace, members);

        writeMembers(cls, members);

        seiContext.addExceptionBeanEntry(thrownDecl.getQualifiedName(), faultInfo, builder);
        return true;
    }

    protected boolean isWSDLException(Collection<MemberInfo> members, TypeElement thrownDecl) {
        WebFault webFault = thrownDecl.getAnnotation(WebFault.class);
        return webFault != null && members.size() == 2 && getFaultInfoMember(members) != null;
    }

    /*
     * Returns the corresponding MemberInfo for getFaultInfo()
     * method of an exception. Returns null, if that method is not there.
     */
    private MemberInfo getFaultInfoMember(Collection<MemberInfo> members) {
        for(MemberInfo member : members) {
            if (member.getParamName().equals(FAULT_INFO.getValue())) {
                return member;
            }
        }
        return null;
    }

    private void writeXmlElementDeclaration(JDefinedClass cls, String elementName, String namespaceUri) {

       if (cls == null)
            return;
        JAnnotationUse xmlRootElementAnn = cls.annotate(XmlRootElement.class);
        xmlRootElementAnn.param("name", elementName);
        if (namespaceUri.length() > 0) {
            xmlRootElementAnn.param("namespace", namespaceUri);
        }
        JAnnotationUse xmlAccessorTypeAnn = cls.annotate(cm.ref(XmlAccessorType.class));
        xmlAccessorTypeAnn.param("value", XmlAccessType.FIELD);
    }

    private void writeXmlTypeDeclaration(JDefinedClass cls, String typeName, String namespaceUri,
                                         Collection<MemberInfo> members) {
        if (cls == null)
            return;
        JAnnotationUse xmlTypeAnn = cls.annotate(cm.ref(XmlType.class));
        xmlTypeAnn.param("name", typeName);
        xmlTypeAnn.param("namespace", namespaceUri);
        if (members.size() > 1) {
            JAnnotationArrayMember paramArray = xmlTypeAnn.paramArray("propOrder");
            for (MemberInfo memInfo : members) {
                paramArray.param(memInfo.getParamName());
            }
        }
    }

    private void writeMember(JDefinedClass cls, TypeMirror paramType,
                             String paramName) {

        if (cls == null)
            return;

        String accessorName =BindingHelper.mangleNameToPropertyName(paramName);
        String getterPrefix = paramType.toString().equals("boolean")? "is" : "get";
        JType propType = getType(paramType);
        JMethod m = cls.method(JMod.PUBLIC, propType, getterPrefix+ accessorName);
        JDocComment methodDoc = m.javadoc();
        JCommentPart ret = methodDoc.addReturn();
        ret.add("returns "+propType.name());
        JBlock body = m.body();
        body._return( JExpr._this().ref(paramName) );

        m = cls.method(JMod.PUBLIC, cm.VOID, "set"+accessorName);
        JVar param = m.param(propType, paramName);
        methodDoc = m.javadoc();
        JCommentPart part = methodDoc.addParam(paramName);
        part.add("the value for the "+ paramName+" property");
        body = m.body();
        body.assign( JExpr._this().ref(paramName), param );
    }
}      
    
