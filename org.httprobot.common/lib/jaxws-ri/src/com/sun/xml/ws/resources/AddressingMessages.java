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
public final class AddressingMessages {

    private final static LocalizableMessageFactory messageFactory = new LocalizableMessageFactory("com.sun.xml.ws.resources.addressing");
    private final static Localizer localizer = new Localizer();

    public static Localizable localizableNON_ANONYMOUS_RESPONSE_ONEWAY() {
        return messageFactory.getMessage("nonAnonymous.response.oneway");
    }

    /**
     * Ignoring non-anonymous response for one-way message
     * 
     */
    public static String NON_ANONYMOUS_RESPONSE_ONEWAY() {
        return localizer.localize(localizableNON_ANONYMOUS_RESPONSE_ONEWAY());
    }

    public static Localizable localizableNULL_WSA_HEADERS() {
        return messageFactory.getMessage("null.wsa.headers");
    }

    /**
     * No WS-Addressing headers found processing the server inbound request
     * 
     */
    public static String NULL_WSA_HEADERS() {
        return localizer.localize(localizableNULL_WSA_HEADERS());
    }

    public static Localizable localizableUNKNOWN_WSA_HEADER() {
        return messageFactory.getMessage("unknown.wsa.header");
    }

    /**
     * Unknown WS-Addressing header
     * 
     */
    public static String UNKNOWN_WSA_HEADER() {
        return localizer.localize(localizableUNKNOWN_WSA_HEADER());
    }

    public static Localizable localizableNULL_ACTION() {
        return messageFactory.getMessage("null.action");
    }

    /**
     * Populating request Addressing headers and found null Action
     * 
     */
    public static String NULL_ACTION() {
        return localizer.localize(localizableNULL_ACTION());
    }

    public static Localizable localizableINVALID_WSAW_ANONYMOUS(Object arg0) {
        return messageFactory.getMessage("invalid.wsaw.anonymous", arg0);
    }

    /**
     * Invalid value obtained from wsaw:Anonymous: "{0}"
     * 
     */
    public static String INVALID_WSAW_ANONYMOUS(Object arg0) {
        return localizer.localize(localizableINVALID_WSAW_ANONYMOUS(arg0));
    }

    public static Localizable localizableNULL_SOAP_VERSION() {
        return messageFactory.getMessage("null.soap.version");
    }

    /**
     * Unexpected null SOAP version
     * 
     */
    public static String NULL_SOAP_VERSION() {
        return localizer.localize(localizableNULL_SOAP_VERSION());
    }

    public static Localizable localizableWSDL_BOUND_OPERATION_NOT_FOUND(Object arg0) {
        return messageFactory.getMessage("wsdlBoundOperation.notFound", arg0);
    }

    /**
     * Cannot find an operation in wsdl:binding for "{0}"
     * 
     */
    public static String WSDL_BOUND_OPERATION_NOT_FOUND(Object arg0) {
        return localizer.localize(localizableWSDL_BOUND_OPERATION_NOT_FOUND(arg0));
    }

    public static Localizable localizableNON_UNIQUE_OPERATION_SIGNATURE(Object arg0, Object arg1, Object arg2, Object arg3) {
        return messageFactory.getMessage("non.unique.operation.signature", arg0, arg1, arg2, arg3);
    }

    /**
     * Operations in a port should have unique operation signature to successfuly identify a associated wsdl operation for a message. WSDL operation {0} and {1} have the same operation signature, wsa:Action "{2}" and request body block "{3}", Method dispatching may fail at runtime. Use unique wsa:Action for each operation
     * 
     */
    public static String NON_UNIQUE_OPERATION_SIGNATURE(Object arg0, Object arg1, Object arg2, Object arg3) {
        return localizer.localize(localizableNON_UNIQUE_OPERATION_SIGNATURE(arg0, arg1, arg2, arg3));
    }

    public static Localizable localizableNON_ANONYMOUS_RESPONSE() {
        return messageFactory.getMessage("nonAnonymous.response");
    }

    /**
     * Sending 202 and processing non-anonymous response
     * 
     */
    public static String NON_ANONYMOUS_RESPONSE() {
        return localizer.localize(localizableNON_ANONYMOUS_RESPONSE());
    }

    public static Localizable localizableVALIDATION_SERVER_NULL_ACTION() {
        return messageFactory.getMessage("validation.server.nullAction");
    }

    /**
     * Validating inbound Addressing headers on server and found null Action
     * 
     */
    public static String VALIDATION_SERVER_NULL_ACTION() {
        return localizer.localize(localizableVALIDATION_SERVER_NULL_ACTION());
    }

    public static Localizable localizableFAULT_TO_CANNOT_PARSE() {
        return messageFactory.getMessage("faultTo.cannot.parse");
    }

    /**
     * FaultTo header cannot be parsed
     * 
     */
    public static String FAULT_TO_CANNOT_PARSE() {
        return localizer.localize(localizableFAULT_TO_CANNOT_PARSE());
    }

    public static Localizable localizableVALIDATION_CLIENT_NULL_ACTION() {
        return messageFactory.getMessage("validation.client.nullAction");
    }

    /**
     * Validating inbound Addressing headers on client and found null Action
     * 
     */
    public static String VALIDATION_CLIENT_NULL_ACTION() {
        return localizer.localize(localizableVALIDATION_CLIENT_NULL_ACTION());
    }

    public static Localizable localizableNULL_MESSAGE() {
        return messageFactory.getMessage("null.message");
    }

    /**
     * Null message found when processing the server inbound request and WS-Addressing is required
     * 
     */
    public static String NULL_MESSAGE() {
        return localizer.localize(localizableNULL_MESSAGE());
    }

    public static Localizable localizableACTION_NOT_SUPPORTED_EXCEPTION(Object arg0) {
        return messageFactory.getMessage("action.not.supported.exception", arg0);
    }

    /**
     * Action: "{0}" not supported
     * 
     */
    public static String ACTION_NOT_SUPPORTED_EXCEPTION(Object arg0) {
        return localizer.localize(localizableACTION_NOT_SUPPORTED_EXCEPTION(arg0));
    }

    public static Localizable localizableNON_ANONYMOUS_RESPONSE_NULL_HEADERS(Object arg0) {
        return messageFactory.getMessage("nonAnonymous.response.nullHeaders", arg0);
    }

    /**
     * No response headers found in non-anonymous response from "{0}"
     * 
     */
    public static String NON_ANONYMOUS_RESPONSE_NULL_HEADERS(Object arg0) {
        return localizer.localize(localizableNON_ANONYMOUS_RESPONSE_NULL_HEADERS(arg0));
    }

    public static Localizable localizableNON_ANONYMOUS_RESPONSE_SENDING(Object arg0) {
        return messageFactory.getMessage("nonAnonymous.response.sending", arg0);
    }

    /**
     * Sending non-anonymous reply to "{0}"
     * 
     */
    public static String NON_ANONYMOUS_RESPONSE_SENDING(Object arg0) {
        return localizer.localize(localizableNON_ANONYMOUS_RESPONSE_SENDING(arg0));
    }

    public static Localizable localizableREPLY_TO_CANNOT_PARSE() {
        return messageFactory.getMessage("replyTo.cannot.parse");
    }

    /**
     * ReplyTo header cannot be parsed
     * 
     */
    public static String REPLY_TO_CANNOT_PARSE() {
        return localizer.localize(localizableREPLY_TO_CANNOT_PARSE());
    }

    public static Localizable localizableINVALID_ADDRESSING_HEADER_EXCEPTION(Object arg0, Object arg1) {
        return messageFactory.getMessage("invalid.addressing.header.exception", arg0, arg1);
    }

    /**
     * Invalid WS-Addressing header: "{0}",Reason: "{1}"
     * 
     */
    public static String INVALID_ADDRESSING_HEADER_EXCEPTION(Object arg0, Object arg1) {
        return localizer.localize(localizableINVALID_ADDRESSING_HEADER_EXCEPTION(arg0, arg1));
    }

    public static Localizable localizableWSAW_ANONYMOUS_PROHIBITED() {
        return messageFactory.getMessage("wsaw.anonymousProhibited");
    }

    /**
     * Operation has "prohibited" value for wsaw:anonymous in the WSDL, Addressing must be disabled and SOAP message need to be hand-crafted
     * 
     */
    public static String WSAW_ANONYMOUS_PROHIBITED() {
        return localizer.localize(localizableWSAW_ANONYMOUS_PROHIBITED());
    }

    public static Localizable localizableNULL_WSDL_PORT() {
        return messageFactory.getMessage("null.wsdlPort");
    }

    /**
     * Populating request Addressing headers and found null WSDLPort
     * 
     */
    public static String NULL_WSDL_PORT() {
        return localizer.localize(localizableNULL_WSDL_PORT());
    }

    public static Localizable localizableADDRESSING_SHOULD_BE_ENABLED() {
        return messageFactory.getMessage("addressing.should.be.enabled.");
    }

    /**
     * Addressing is not enabled
     * 
     */
    public static String ADDRESSING_SHOULD_BE_ENABLED() {
        return localizer.localize(localizableADDRESSING_SHOULD_BE_ENABLED());
    }

    public static Localizable localizableNULL_ADDRESSING_VERSION() {
        return messageFactory.getMessage("null.addressing.version");
    }

    /**
     * Unexpected null Addressing version
     * 
     */
    public static String NULL_ADDRESSING_VERSION() {
        return localizer.localize(localizableNULL_ADDRESSING_VERSION());
    }

    public static Localizable localizableMISSING_HEADER_EXCEPTION(Object arg0) {
        return messageFactory.getMessage("missing.header.exception", arg0);
    }

    /**
     * Missing WS-Addressing header: "{0}"
     * 
     */
    public static String MISSING_HEADER_EXCEPTION(Object arg0) {
        return localizer.localize(localizableMISSING_HEADER_EXCEPTION(arg0));
    }

    public static Localizable localizableNULL_PACKET() {
        return messageFactory.getMessage("null.packet");
    }

    /**
     * Populating request Addressing headers and found null Packet
     * 
     */
    public static String NULL_PACKET() {
        return localizer.localize(localizableNULL_PACKET());
    }

    public static Localizable localizableWRONG_ADDRESSING_VERSION(Object arg0, Object arg1) {
        return messageFactory.getMessage("wrong.addressing.version", arg0, arg1);
    }

    /**
     * Expected "{0}" version of WS-Addressing but found "{1}"
     * 
     */
    public static String WRONG_ADDRESSING_VERSION(Object arg0, Object arg1) {
        return localizer.localize(localizableWRONG_ADDRESSING_VERSION(arg0, arg1));
    }

    public static Localizable localizableADDRESSING_NOT_ENABLED(Object arg0) {
        return messageFactory.getMessage("addressing.notEnabled", arg0);
    }

    /**
     * Addressing is not enabled, {0} should not be included in the pipeline"
     * 
     */
    public static String ADDRESSING_NOT_ENABLED(Object arg0) {
        return localizer.localize(localizableADDRESSING_NOT_ENABLED(arg0));
    }

    public static Localizable localizableNON_ANONYMOUS_UNKNOWN_PROTOCOL(Object arg0) {
        return messageFactory.getMessage("nonAnonymous.unknown.protocol", arg0);
    }

    /**
     * Unknown protocol: "{0}"
     * 
     */
    public static String NON_ANONYMOUS_UNKNOWN_PROTOCOL(Object arg0) {
        return localizer.localize(localizableNON_ANONYMOUS_UNKNOWN_PROTOCOL(arg0));
    }

    public static Localizable localizableNULL_HEADERS() {
        return messageFactory.getMessage("null.headers");
    }

    /**
     * No headers found when processing the server inbound request and WS-Addressing is required
     * 
     */
    public static String NULL_HEADERS() {
        return localizer.localize(localizableNULL_HEADERS());
    }

    public static Localizable localizableNULL_BINDING() {
        return messageFactory.getMessage("null.binding");
    }

    /**
     * Populating request Addressing headers and found null Binding
     * 
     */
    public static String NULL_BINDING() {
        return localizer.localize(localizableNULL_BINDING());
    }

}
