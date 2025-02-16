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
public final class ModelerMessages {

    private final static LocalizableMessageFactory messageFactory = new LocalizableMessageFactory("com.sun.xml.ws.resources.modeler");
    private final static Localizer localizer = new Localizer();

    public static Localizable localizableNESTED_MODELER_ERROR(Object arg0) {
        return messageFactory.getMessage("nestedModelerError", arg0);
    }

    /**
     * runtime modeler error: {0}
     * 
     */
    public static String NESTED_MODELER_ERROR(Object arg0) {
        return localizer.localize(localizableNESTED_MODELER_ERROR(arg0));
    }

    public static Localizable localizableRUNTIME_MODELER_WSFEATURE_NO_FTRCONSTRUCTOR(Object arg0, Object arg1) {
        return messageFactory.getMessage("runtime.modeler.wsfeature.no.ftrconstructor", arg0, arg1);
    }

    /**
     * Annotation {0} is not recognizable, at least one constructor of {1} should be marked with @FeatureConstructor
     * 
     */
    public static String RUNTIME_MODELER_WSFEATURE_NO_FTRCONSTRUCTOR(Object arg0, Object arg1) {
        return localizer.localize(localizableRUNTIME_MODELER_WSFEATURE_NO_FTRCONSTRUCTOR(arg0, arg1));
    }

    public static Localizable localizableRUNTIME_MODELER_WEBMETHOD_MUST_BE_PUBLIC(Object arg0) {
        return messageFactory.getMessage("runtime.modeler.webmethod.must.be.public", arg0);
    }

    /**
     * @WebMethod is not allowed on a non-public method {0}
     * 
     */
    public static String RUNTIME_MODELER_WEBMETHOD_MUST_BE_PUBLIC(Object arg0) {
        return localizer.localize(localizableRUNTIME_MODELER_WEBMETHOD_MUST_BE_PUBLIC(arg0));
    }

    public static Localizable localizableRUNTIME_MODELER_WRAPPER_NOT_FOUND(Object arg0) {
        return messageFactory.getMessage("runtime.modeler.wrapper.not.found", arg0);
    }

    /**
     * Wrapper class {0} is not found. Have you run annotation processing to generate them?
     * 
     */
    public static String RUNTIME_MODELER_WRAPPER_NOT_FOUND(Object arg0) {
        return localizer.localize(localizableRUNTIME_MODELER_WRAPPER_NOT_FOUND(arg0));
    }

    public static Localizable localizableRUNTIME_MODELER_MTOM_CONFLICT(Object arg0, Object arg1) {
        return messageFactory.getMessage("runtime.modeler.mtom.conflict", arg0, arg1);
    }

    /**
     * Error in  @BindingType: MTOM Configuration in binding identifier {0} conflicts with feature @MTOM {1}
     * 
     */
    public static String RUNTIME_MODELER_MTOM_CONFLICT(Object arg0, Object arg1) {
        return localizer.localize(localizableRUNTIME_MODELER_MTOM_CONFLICT(arg0, arg1));
    }

    public static Localizable localizableRUNTIME_MODELER_FEATURE_CONFLICT(Object arg0, Object arg1) {
        return messageFactory.getMessage("runtime.modeler.feature.conflict", arg0, arg1);
    }

    /**
     * Feature {0} in implementation conflicts with {1} in WSDL configuration 
     * 
     */
    public static String RUNTIME_MODELER_FEATURE_CONFLICT(Object arg0, Object arg1) {
        return localizer.localize(localizableRUNTIME_MODELER_FEATURE_CONFLICT(arg0, arg1));
    }

    public static Localizable localizableRUNTIME_MODELER_INVALID_SOAPBINDING_PARAMETERSTYLE(Object arg0, Object arg1) {
        return messageFactory.getMessage("runtime.modeler.invalid.soapbinding.parameterstyle", arg0, arg1);
    }

    /**
     * Incorrect usage of Annotation {0} on {1}, ParameterStyle can only be WRAPPED with RPC Style Web service.
     * 
     */
    public static String RUNTIME_MODELER_INVALID_SOAPBINDING_PARAMETERSTYLE(Object arg0, Object arg1) {
        return localizer.localize(localizableRUNTIME_MODELER_INVALID_SOAPBINDING_PARAMETERSTYLE(arg0, arg1));
    }

    public static Localizable localizableRUNTIME_MODELER_WSFEATURE_MORETHANONE_FTRCONSTRUCTOR(Object arg0, Object arg1) {
        return messageFactory.getMessage("runtime.modeler.wsfeature.morethanone.ftrconstructor", arg0, arg1);
    }

    /**
     * Annotation {0} is illegal, Only one constructor of {1} can be marked as @FeatureConstructor
     * 
     */
    public static String RUNTIME_MODELER_WSFEATURE_MORETHANONE_FTRCONSTRUCTOR(Object arg0, Object arg1) {
        return localizer.localize(localizableRUNTIME_MODELER_WSFEATURE_MORETHANONE_FTRCONSTRUCTOR(arg0, arg1));
    }

    public static Localizable localizableNOT_A_VALID_BARE_METHOD(Object arg0, Object arg1) {
        return messageFactory.getMessage("not.a.valid.bare.method", arg0, arg1);
    }

    /**
     * SEI {0} has method {1} annotated as BARE but it has more than one parameter bound to body. This is invalid. Please annotate the method with annotation: @SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)
     * 
     */
    public static String NOT_A_VALID_BARE_METHOD(Object arg0, Object arg1) {
        return localizer.localize(localizableNOT_A_VALID_BARE_METHOD(arg0, arg1));
    }

    public static Localizable localizableRUNTIME_MODELER_NO_PACKAGE(Object arg0) {
        return messageFactory.getMessage("runtime.modeler.no.package", arg0);
    }

    /**
     * A @WebService.targetNamespace must be specified on classes with no package.  Class: {0}
     * 
     */
    public static String RUNTIME_MODELER_NO_PACKAGE(Object arg0) {
        return localizer.localize(localizableRUNTIME_MODELER_NO_PACKAGE(arg0));
    }

    public static Localizable localizableRUNTIME_MODELER_NO_WEBSERVICE_ANNOTATION(Object arg0) {
        return messageFactory.getMessage("runtime.modeler.no.webservice.annotation", arg0);
    }

    /**
     * A WebService annotation is not present on class: {0}
     * 
     */
    public static String RUNTIME_MODELER_NO_WEBSERVICE_ANNOTATION(Object arg0) {
        return localizer.localize(localizableRUNTIME_MODELER_NO_WEBSERVICE_ANNOTATION(arg0));
    }

    public static Localizable localizableRUNTIME_MODELER_ADDRESSING_RESPONSES_NOSUCHMETHOD(Object arg0) {
        return messageFactory.getMessage("runtime.modeler.addressing.responses.nosuchmethod", arg0);
    }

    /**
     * JAX-WS 2.1 API is loaded from {0}, But JAX-WS runtime requires JAX-WS 2.2 API. Use the endorsed standards override mechanism to load JAX-WS 2.2 API   
     * 
     */
    public static String RUNTIME_MODELER_ADDRESSING_RESPONSES_NOSUCHMETHOD(Object arg0) {
        return localizer.localize(localizableRUNTIME_MODELER_ADDRESSING_RESPONSES_NOSUCHMETHOD(arg0));
    }

    public static Localizable localizableRUNTIME_MODELER_ONEWAY_OPERATION_NO_OUT_PARAMETERS(Object arg0, Object arg1) {
        return messageFactory.getMessage("runtime.modeler.oneway.operation.no.out.parameters", arg0, arg1);
    }

    /**
     * oneway operation should not have OUT parameters class: {0} method: {1}
     * 
     */
    public static String RUNTIME_MODELER_ONEWAY_OPERATION_NO_OUT_PARAMETERS(Object arg0, Object arg1) {
        return localizer.localize(localizableRUNTIME_MODELER_ONEWAY_OPERATION_NO_OUT_PARAMETERS(arg0, arg1));
    }

    public static Localizable localizableUNABLE_TO_CREATE_JAXB_CONTEXT() {
        return messageFactory.getMessage("unable.to.create.JAXBContext");
    }

    /**
     * Unable to create JAXBContext
     * 
     */
    public static String UNABLE_TO_CREATE_JAXB_CONTEXT() {
        return localizer.localize(localizableUNABLE_TO_CREATE_JAXB_CONTEXT());
    }

    public static Localizable localizableRUNTIME_MODELER_NO_OPERATIONS(Object arg0) {
        return messageFactory.getMessage("runtime.modeler.no.operations", arg0);
    }

    /**
     * The web service defined by the class {0} does not contain any valid WebMethods.
     * 
     */
    public static String RUNTIME_MODELER_NO_OPERATIONS(Object arg0) {
        return localizer.localize(localizableRUNTIME_MODELER_NO_OPERATIONS(arg0));
    }

    public static Localizable localizableRUNTIME_MODELER_ONEWAY_OPERATION_NO_CHECKED_EXCEPTIONS(Object arg0, Object arg1, Object arg2) {
        return messageFactory.getMessage("runtime.modeler.oneway.operation.no.checked.exceptions", arg0, arg1, arg2);
    }

    /**
     * Oneway operation should not throw any checked exceptions class: {0} method: {1} throws: {2}
     * 
     */
    public static String RUNTIME_MODELER_ONEWAY_OPERATION_NO_CHECKED_EXCEPTIONS(Object arg0, Object arg1, Object arg2) {
        return localizer.localize(localizableRUNTIME_MODELER_ONEWAY_OPERATION_NO_CHECKED_EXCEPTIONS(arg0, arg1, arg2));
    }

    public static Localizable localizableRUNTIME_MODELER_WEBMETHOD_MUST_BE_NONSTATIC(Object arg0) {
        return messageFactory.getMessage("runtime.modeler.webmethod.must.be.nonstatic", arg0);
    }

    /**
     * @WebMethod is not allowed on a static method {0}
     * 
     */
    public static String RUNTIME_MODELER_WEBMETHOD_MUST_BE_NONSTATIC(Object arg0) {
        return localizer.localize(localizableRUNTIME_MODELER_WEBMETHOD_MUST_BE_NONSTATIC(arg0));
    }

    public static Localizable localizableRUNTIME_MODELER_METHOD_NOT_FOUND(Object arg0, Object arg1) {
        return messageFactory.getMessage("runtime.modeler.method.not.found", arg0, arg1);
    }

    /**
     * method: {0} could not be found on class: {1}
     * 
     */
    public static String RUNTIME_MODELER_METHOD_NOT_FOUND(Object arg0, Object arg1) {
        return localizer.localize(localizableRUNTIME_MODELER_METHOD_NOT_FOUND(arg0, arg1));
    }

    public static Localizable localizableRUNTIME_MODELER_CLASS_NOT_FOUND(Object arg0) {
        return messageFactory.getMessage("runtime.modeler.class.not.found", arg0);
    }

    /**
     * class: {0} could not be found
     * 
     */
    public static String RUNTIME_MODELER_CLASS_NOT_FOUND(Object arg0) {
        return localizer.localize(localizableRUNTIME_MODELER_CLASS_NOT_FOUND(arg0));
    }

    public static Localizable localizableRUNTIME_MODELER_SOAPBINDING_CONFLICT(Object arg0, Object arg1, Object arg2) {
        return messageFactory.getMessage("runtime.modeler.soapbinding.conflict", arg0, arg1, arg2);
    }

    /**
     * SOAPBinding Style {0} for method {1} conflicts with global SOAPBinding Style {2}
     * 
     */
    public static String RUNTIME_MODELER_SOAPBINDING_CONFLICT(Object arg0, Object arg1, Object arg2) {
        return localizer.localize(localizableRUNTIME_MODELER_SOAPBINDING_CONFLICT(arg0, arg1, arg2));
    }

    public static Localizable localizableRUNTIME_MODELER_CANNOT_GET_SERVICE_NAME_FROM_INTERFACE(Object arg0) {
        return messageFactory.getMessage("runtime.modeler.cannot.get.serviceName.from.interface", arg0);
    }

    /**
     * The serviceName cannot be retrieved from an interface.  class {0}
     * 
     */
    public static String RUNTIME_MODELER_CANNOT_GET_SERVICE_NAME_FROM_INTERFACE(Object arg0) {
        return localizer.localize(localizableRUNTIME_MODELER_CANNOT_GET_SERVICE_NAME_FROM_INTERFACE(arg0));
    }

    public static Localizable localizableRUNTIME_MODELER_ENDPOINT_INTERFACE_NO_WEBSERVICE(Object arg0) {
        return messageFactory.getMessage("runtime.modeler.endpoint.interface.no.webservice", arg0);
    }

    /**
     * The Endpoint Interface: {0} does not have WebService Annotation
     * 
     */
    public static String RUNTIME_MODELER_ENDPOINT_INTERFACE_NO_WEBSERVICE(Object arg0) {
        return localizer.localize(localizableRUNTIME_MODELER_ENDPOINT_INTERFACE_NO_WEBSERVICE(arg0));
    }

    public static Localizable localizableRUNTIMEMODELER_INVALID_SOAPBINDING_ON_METHOD(Object arg0, Object arg1, Object arg2) {
        return messageFactory.getMessage("runtimemodeler.invalid.soapbindingOnMethod", arg0, arg1, arg2);
    }

    /**
     * Invalid annotation: {0} on Method {1} in Class {2}, A method cannot be annotated with @SOAPBinding with Style "RPC"
     * 
     */
    public static String RUNTIMEMODELER_INVALID_SOAPBINDING_ON_METHOD(Object arg0, Object arg1, Object arg2) {
        return localizer.localize(localizableRUNTIMEMODELER_INVALID_SOAPBINDING_ON_METHOD(arg0, arg1, arg2));
    }

    public static Localizable localizableRUNTIME_MODELER_PORTNAME_SERVICENAME_NAMESPACE_MISMATCH(Object arg0, Object arg1) {
        return messageFactory.getMessage("runtime.modeler.portname.servicename.namespace.mismatch", arg0, arg1);
    }

    /**
     * The namespace of the serviceName "{0}" and the namespace of the portName "{1}" must match
     * 
     */
    public static String RUNTIME_MODELER_PORTNAME_SERVICENAME_NAMESPACE_MISMATCH(Object arg0, Object arg1) {
        return localizer.localize(localizableRUNTIME_MODELER_PORTNAME_SERVICENAME_NAMESPACE_MISMATCH(arg0, arg1));
    }

    public static Localizable localizableRUNTIME_MODELER_WSFEATURE_ILLEGAL_FTRCONSTRUCTOR(Object arg0, Object arg1) {
        return messageFactory.getMessage("runtime.modeler.wsfeature.illegal.ftrconstructor", arg0, arg1);
    }

    /**
     * Annotation {0} is illegal, In {1} @FeatureConstructor value doesn't match the constructor parameters
     * 
     */
    public static String RUNTIME_MODELER_WSFEATURE_ILLEGAL_FTRCONSTRUCTOR(Object arg0, Object arg1) {
        return localizer.localize(localizableRUNTIME_MODELER_WSFEATURE_ILLEGAL_FTRCONSTRUCTOR(arg0, arg1));
    }

    public static Localizable localizableRUNTIME_MODELER_WEBMETHOD_MUST_BE_NONSTATICFINAL(Object arg0) {
        return messageFactory.getMessage("runtime.modeler.webmethod.must.be.nonstaticfinal", arg0);
    }

    /**
     * @WebMethod is not allowed on a static or final method {0}   
     * 
     */
    public static String RUNTIME_MODELER_WEBMETHOD_MUST_BE_NONSTATICFINAL(Object arg0) {
        return localizer.localize(localizableRUNTIME_MODELER_WEBMETHOD_MUST_BE_NONSTATICFINAL(arg0));
    }

}
