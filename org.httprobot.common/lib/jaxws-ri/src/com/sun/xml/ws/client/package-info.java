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

/**
 *  <h1>JAX-WS 2.0.1 Client Runtime</h1>
 * <P>This document describes the architecture of client side 
 * JAX-WS 2.0.1 runtime. 
 *
 * <h3>JAX-WS 2.0.1 Client Sequence Diagram</h3>
 * <img src='../../../../../jaxws/basic-client.seq.png'>
  * <h3>JAX-WS 2.0.1 Asynchronous Invocation Sequence Diagram</h3>
 * <img src='../../../../../jaxws/client-async.seq.png'>
  * <h3>JAX-WS 2.0.1 Dispatch Invocation Sequence Diagram</h3>
 * <img src='../../../../../jaxws/dispatch.seq.png'>

 * <H3>Message Flow</H3>
 * {@link com.sun.xml.ws.client.WebService} provides client view of a Web service.
 * WebService.getPort returns an instance of {@link com.sun.xml.ws.client.EndpointIFInvocationHandler}
 * with {@link com.sun.pept.ept.ContactInfoList} and {@link com.sun.pept.Delegate} 
 * initialized. A method invocation on the port, obtained from WebService, invokes
 * {@link com.sun.xml.ws.client.EndpointIFInvocationHandler#invoke}. This method 
 * then creates a {@link com.sun.pept.ept.MessageInfo} and populates the data 
 * (parameters specified by the user) and metadata such as RuntimeContext, RequestContext, 
 * Message Exchange Pattern into this MessageInfo. This method then invokes 
 * {@link com.sun.pept.Delegate#send} and returns the response.
 * <P></P>
 * The Delegate.send method iterates through the ContactInfoList and picks up the 
 * correct {@link com.sun.pept.ept.ContactInfo} based upon the binding id of 
 * {@link javax.xml.ws.BindingProvider} and sets it on the MessageInfo. After the 
 * Delegate obtains a specific ContactInfo it uses that ContactInfo to obtain a 
 * protocol-specific {@link com.sun.pept.protocol.MessageDispatcher}. There will be 
 * two types of client-side MessageDispatchers for JAX-WS 2.0.1, 
 * {@link com.sun.xml.ws.protocol.soap.client.SOAPMessageDispatcher} and 
 * {@link com.sun.xml.ws.protocol.xml.client.XMLMessageDispatcher}. The Delegate 
 * then invokes {@link com.sun.pept.protocol.MessageDispatcher#send}. The 
 * MessageDispatcher.send method makes a decision about the synchronous and 
 * asynchronous nature of the message exchange pattern and invokes separate methods
 * accordingly.
 * <p></P>
 * The MessageDispatcher uses ContactInfo to obtain
 * a {@link com.sun.xml.ws.encoding.soap.client.SOAPXMLEncoder} which converts 
 * the MessageInfo to {@link com.sun.xml.ws.encoding.soap.internal.InternalMessage}. 
 * There will be two types of client-side SOAPXMLEncoder for JAX-WS 2.0.1, 
 * SOAPXMEncoder for SOAP 1.1 and {@link com.sun.xml.ws.encoding.soap.client.SOAP12XMLEncoder}
 * for SOAP 1.2. The MessageDispatcher invokes configured handlers and use the 
 * codec to convert the InternalMessage to a {@link javax.xml.soap.SOAPMessage}.
 * The metadata from the MessageInfo is classified into {@link javax.xml.soap.MimeHeaders} 
 * of this SOAPMessage and context information for {@link com.sun.xml.ws.api.server.WSConnection}.
 * The SOAPMessge is then written to the output stream of the WSConnection
 * obtained from MessageInfo.
 *<P></P>
 * The MessageDispatcher.receive method handles the response. The 
 * SOAPMessageDispatcher extracts the SOAPMessage from the input stream of 
 * WSConnection and performs the mustUnderstand processing followed by invocation 
 * of any handlers. The MessageDispatcher uses ContactInfo to obtain a 
 * {@link com.sun.xml.ws.encoding.soap.client.SOAPXMLDecoder} which converts the SOAPMessage 
 * to InternalMessage and then InternalMessage to MessageInfo. There will be two types of 
 * client-side SOAPXMLDecoder for JAX-WS 2.0.1, SOAPXMLDencoder for SOAP 1.1 and 
 * {@link com.sun.xml.ws.encoding.soap.client.SOAP12XMLDecoder} for SOAP 1.2. The 
 * response is returned back to the client code via Delegate.
 *
 * <H3>External Interactions</H3>
 * <H4>SAAJ API</H4>
 * <UL>
 * 	<LI><P>JAX-WS creates SAAJ SOAPMessage from the HttpServletRequest.
 * 	At present, JAX-WS reads all the bytes from the request stream and
 * 	then creates SOAPMessage along with the HTTP headers.</P>
 * </UL>
 * <P>MessageFactory(binding).createMessage(MimeHeaders, InputStream)</P>
 * <UL>
 * 	<LI><P>SOAPMessage parses the content from the stream including MIME
 * 	data</P>
 * 	<LI><P>com.sun.xml.ws.server.SOAPMessageDispatcher::checkHeadersPeekBody()</P>
 * 	<P>SOAPMessage.getSOAPHeader() is used for mustUnderstand processing
 * 	of headers. It further uses
 * 	SOAPHeader.examineMustUnderstandHeaderElements(role)</P>
 * 	<P>SOAPMessage.getSOAPBody().getFistChild() is used for guessing the
 * 	MEP of the request</P>
 * 	<LI><P>com.sun.xml.ws.handler.HandlerChainCaller:insertFaultMessage()</P>
 * 	<P>SOAPMessage.getSOAPPart().getEnvelope() and some other SAAJ calls
 * 	are made to create a fault in the SOAPMessage</P>
 * 	<LI><P>com.sun.xml.ws.handler.LogicalMessageImpl::getPayload()
 * 	interacts with SAAJ to get body from SOAPMessage</P>
 * 	<LI><P>com.sun.xml.ws.encoding.soap.SOAPEncoder.toSOAPMessage(com.sun.xml.ws.encoding.soap.internal.InternalMessage,
 * 	SOAPMessage). There is a scenario where there is SOAPMessage and a
 * 	logical handler sets payload as Source. To write to the stream,
 * 	SOAPMessage.writeTo() is used but before that the body needs to be
 * 	updated with logical handler' Source. Need to verify if this
 * 	scenario is still happening since Handler.close() is changed to take
 * 	MessageContext.</P>
 * 	<LI><P>com.sun.xml.ws.handlerSOAPMessageContextImpl.getHeaders()
 * 	uses SAAJ API to get headers.</P>
 * 	<LI><P>SOAPMessage.writeTo() is used to write response. At present,
 * 	it writes into byte[] and this byte[] is written to
 * 	HttpServletResponse.</P>
 * </UL>
 * <H4>JAXB API</H4>
 * <P>JAX-WS RI uses the JAXB API to marshall/unmarshall user created
 * JAXB objects with user created {@link javax.xml.bind.JAXBContext JAXBContext}. 
 * Handler, Dispatch in JAX-WS API provide ways for the user to specify his/her own
 * JAXBContext. {@link com.sun.xml.ws.encoding.jaxb.JAXBTypeSerializer JAXBTypeSerializer} class uses all these methods.</P>
 * <UL>
 * 	<LI><p>{@link javax.xml.bind.Marshaller#marshal(Object,XMLStreamWriter) Marshaller.marshal(Object,XMLStreamWriter)}</p>
 * 	<LI><P>{@link javax.xml.bind.Marshaller#marshal(Object,Result) Marshaller.marshal(Object, DomResult)}</P>
 * 	<LI><P>{@link javax.xml.bind.Unmarshaller#unmarshal(XMLStreamReader) Object Unmarshaller.unmarshal(XMLStreamReader)}</P>
 * 	<LI><P>{@link javax.xml.bind.Unmarshaller#unmarshal(Source) Object Unmarshaller.unmarshal(Source)}</P>
 * </UL>
 * The following two JAXB classes are implemented by JAX-WS to enable/implement MTOM and XOP
 * <UL>
 *      <LI><P>{@link javax.xml.bind.attachment.AttachmentMarshaller AttachmentMarshaller}</P>
 *      <LI><P>{@link javax.xml.bind.attachment.AttachmentUnmarshaller AttachmentUnmarshaller}</P>
 * </UL>
 * <H4>JAXB Runtime-API (private contract)</H4>
 * <P>JAX-WS RI uses these private API for serialization/deserialization
 * purposes. This private API is used to serialize/deserialize method
 * parameters at the time of JAXBTypeSerializer class uses all
 * these methods.</P>
 * <UL>
 * 	<LI><P>{@link com.sun.xml.bind.api.Bridge#marshal(BridgeContext, Object, XMLStreamWriter) Bridge.marshal(BridgeContext, Object, XMLStreamWriter)}</P>
 * 	<LI><P>{@link com.sun.xml.bind.api.Bridge#marshal(BridgeContext, Object, Node) Bridge.marshal(BridgeContext, Object, Node)}</P>
 * 	<LI><P>{@link com.sun.xml.bind.api.Bridge#unmarshal(BridgeContext, XMLStreamReader) Object Bridge.unmarshal(BridgeContext, XMLStreamReader)}</P>
 * </UL>
 * 
 * @ArchitectureDocument
 **/
package com.sun.xml.ws.client;

import com.sun.xml.bind.api.BridgeContext;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.w3c.dom.Node;
