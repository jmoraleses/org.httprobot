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

package com.sun.xml.ws.wsdl.parser;

import com.sun.xml.ws.api.addressing.AddressingVersion;
import com.sun.xml.ws.api.model.wsdl.*;
import com.sun.xml.ws.api.wsdl.parser.WSDLParserExtension;
import com.sun.xml.ws.api.wsdl.parser.WSDLParserExtensionContext;
import com.sun.xml.ws.model.wsdl.*;
import com.sun.xml.ws.streaming.XMLStreamReaderUtil;
import com.sun.xml.ws.resources.AddressingMessages;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.AddressingFeature;
import java.util.Map;

/**
 * W3C WS-Addressing Runtime WSDL parser extension
 *
 * @author Arun Gupta
 */
public class W3CAddressingWSDLParserExtension extends WSDLParserExtension {
    @Override
    public boolean bindingElements(WSDLBoundPortType binding, XMLStreamReader reader) {
        return addressibleElement(reader, binding);
    }

    @Override
    public boolean portElements(WSDLPort port, XMLStreamReader reader) {
        return addressibleElement(reader, port);
    }

    private boolean addressibleElement(XMLStreamReader reader, WSDLFeaturedObject binding) {
        QName ua = reader.getName();
        if (ua.equals(AddressingVersion.W3C.wsdlExtensionTag)) {
            String required = reader.getAttributeValue(WSDLConstants.NS_WSDL, "required");
            binding.addFeature(new AddressingFeature(true, Boolean.parseBoolean(required)));
            XMLStreamReaderUtil.skipElement(reader);
            return true;        // UsingAddressing is consumed
        }

        return false;
    }

    @Override
    public boolean bindingOperationElements(WSDLBoundOperation operation, XMLStreamReader reader) {
        WSDLBoundOperationImpl impl = (WSDLBoundOperationImpl)operation;

        QName anon = reader.getName();
        if (anon.equals(AddressingVersion.W3C.wsdlAnonymousTag)) {
            try {
                String value = reader.getElementText();
                if (value == null || value.trim().equals("")) {
                    throw new WebServiceException("Null values not permitted in wsaw:Anonymous.");
                    // TODO: throw exception only if wsdl:required=true
                    // TODO: is this the right exception ?
                } else if (value.equals("optional")) {
                    impl.setAnonymous(WSDLBoundOperation.ANONYMOUS.optional);
                } else if (value.equals("required")) {
                    impl.setAnonymous(WSDLBoundOperation.ANONYMOUS.required);
                } else if (value.equals("prohibited")) {
                    impl.setAnonymous(WSDLBoundOperation.ANONYMOUS.prohibited);
                } else {
                    throw new WebServiceException("wsaw:Anonymous value \"" + value + "\" not understood.");
                    // TODO: throw exception only if wsdl:required=true
                    // TODO: is this the right exception ?
                }
            } catch (XMLStreamException e) {
                throw new WebServiceException(e);       // TODO: is this the correct behavior ?
            }

            return true;        // consumed the element
        }

        return false;
    }

    public void portTypeOperationInputAttributes(WSDLInput input, XMLStreamReader reader) {
       String action = ParserUtil.getAttribute(reader, getWsdlActionTag());
       if (action != null) {
            ((WSDLInputImpl)input).setAction(action);
            ((WSDLInputImpl)input).setDefaultAction(false);
        }
    }


    public void portTypeOperationOutputAttributes(WSDLOutput output, XMLStreamReader reader) {
       String action = ParserUtil.getAttribute(reader, getWsdlActionTag());
       if (action != null) {
            ((WSDLOutputImpl)output).setAction(action);
            ((WSDLOutputImpl)output).setDefaultAction(false);
        }
    }


    public void portTypeOperationFaultAttributes(WSDLFault fault, XMLStreamReader reader) {
        String action = ParserUtil.getAttribute(reader, getWsdlActionTag());
        if (action != null) {
            ((WSDLFaultImpl) fault).setAction(action);
            ((WSDLFaultImpl) fault).setDefaultAction(false);
        }
    }

    /**
     * Process wsdl:portType operation after the entire WSDL model has been populated.
     * The task list includes: <p>
     * <ul>
     * <li>Patch the value of UsingAddressing in wsdl:port and wsdl:binding</li>
     * <li>Populate actions for the messages that do not have an explicit wsaw:Action</li>
     * <li>Patch the default value of wsaw:Anonymous=optional if none is specified</li>
     * </ul>
     * @param context
     */
    @Override
    public void finished(WSDLParserExtensionContext context) {
        WSDLModel model = context.getWSDLModel();
        for (WSDLService service : model.getServices().values()) {
            for (WSDLPort wp : service.getPorts()) {
                WSDLPortImpl port = (WSDLPortImpl)wp;
                WSDLBoundPortTypeImpl binding = port.getBinding();

                // populate actions for the messages that do not have an explicit wsaw:Action
                populateActions(binding);

                // patch the default value of wsaw:Anonymous=optional if none is specified
                patchAnonymousDefault(binding);
            }
        }
    }

    protected String getNamespaceURI() {
        return AddressingVersion.W3C.wsdlNsUri;
    }

    protected QName getWsdlActionTag() {
       return AddressingVersion.W3C.wsdlActionTag;
    }
    /**
     * Populate all the Actions
     *
     * @param binding soapbinding:operation
     */
    private void populateActions(WSDLBoundPortTypeImpl binding) {
        WSDLPortTypeImpl porttype = binding.getPortType();
        for (WSDLOperationImpl o : porttype.getOperations()) {
            // TODO: this may be performance intensive. Alternatively default action
            // TODO: can be calculated when the operation is actually invoked.
            WSDLBoundOperationImpl wboi = binding.get(o.getName());

            if (wboi == null) {
                //If this operation is unbound set the action to default
                o.getInput().setAction(defaultInputAction(o));
                continue;
            }
                String soapAction = wboi.getSOAPAction();
            if (o.getInput().getAction() == null || o.getInput().getAction().equals("")) {
                // explicit wsaw:Action is not specified

                if (soapAction != null && !soapAction.equals("")) {
                    // if soapAction is non-empty, use that
                    o.getInput().setAction(soapAction);
                } else {
                    // otherwise generate default Action
                    o.getInput().setAction(defaultInputAction(o));
                }
            }

            // skip output and fault processing for one-way methods
            if (o.getOutput() == null)
                continue;

            if (o.getOutput().getAction() == null || o.getOutput().getAction().equals("")) {
                o.getOutput().setAction(defaultOutputAction(o));
            }

            if (o.getFaults() == null || !o.getFaults().iterator().hasNext())
                continue;

            for (WSDLFault f : o.getFaults()) {
                if (f.getAction() == null || f.getAction().equals("")) {
                    ((WSDLFaultImpl)f).setAction(defaultFaultAction(f.getName(), o));
                }

            }
        }
    }

    /**
     * Patch the default value of wsaw:Anonymous=optional if none is specified
     *
     * @param binding WSDLBoundPortTypeImpl
     */
    protected void patchAnonymousDefault(WSDLBoundPortTypeImpl binding) {
        for (WSDLBoundOperationImpl wbo : binding.getBindingOperations()) {
            if (wbo.getAnonymous() == null)
                wbo.setAnonymous(WSDLBoundOperation.ANONYMOUS.optional);
        }
    }

    private String defaultInputAction(WSDLOperation o) {
        return buildAction(o.getInput().getName(), o, false);
    }

    private String defaultOutputAction(WSDLOperation o) {
        return buildAction(o.getOutput().getName(), o, false);
    }

    private String defaultFaultAction(String name, WSDLOperation o) {
        return buildAction(name, o, true);
    }

    protected static final String buildAction(String name, WSDLOperation o, boolean isFault) {
        String tns = o.getName().getNamespaceURI();

        String delim = SLASH_DELIMITER;

        // TODO: is this the correct way to find the separator ?
        if (!tns.startsWith("http"))
            delim = COLON_DELIMITER;

        if (tns.endsWith(delim))
            tns = tns.substring(0, tns.length()-1);

        if (o.getPortTypeName() == null)
            throw new WebServiceException("\"" + o.getName() + "\" operation's owning portType name is null.");

        return tns +
            delim +
            o.getPortTypeName().getLocalPart() +
            delim +
            (isFault ? o.getName().getLocalPart() + delim + "Fault" + delim : "") +
            name;
    }

    protected static final String COLON_DELIMITER = ":";
    protected static final String SLASH_DELIMITER = "/";
}
