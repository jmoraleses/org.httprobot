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

package com.sun.tools.ws.wsdl.parser;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import com.sun.istack.SAXParseException2;
import com.sun.tools.ws.resources.WsdlMessages;
import com.sun.tools.ws.wscompile.ErrorReceiver;
import com.sun.tools.ws.wscompile.WsimportOptions;
import com.sun.tools.ws.wsdl.document.jaxws.JAXWSBindingsConstants;
import com.sun.tools.xjc.util.DOMUtils;
import com.sun.xml.bind.v2.util.EditDistance;
import com.sun.xml.ws.util.DOMUtil;
import com.sun.xml.ws.util.JAXWSUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXParseException;

import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * Internalizes external binding declarations.
 *
 * @author Vivek Pandey
 */
public class Internalizer {
    private static final XPathFactory xpf = XPathFactory.newInstance();
    private final XPath xpath = xpf.newXPath();
    private final WsimportOptions options;
    private final DOMForest forest;
    private final ErrorReceiver errorReceiver;


    public Internalizer(DOMForest forest, WsimportOptions options, ErrorReceiver errorReceiver) {
        this.forest = forest;
        this.options = options;
        this.errorReceiver = errorReceiver;
    }

    public void transform() {
        for (Element jaxwsBinding : forest.outerMostBindings) {
            internalize(jaxwsBinding, jaxwsBinding);
        }
        /*
        Map<Element, Node> targetNodes = new HashMap<Element, Node>();
        for (Element jaxwsBinding : forest.outerMostBindings) {
            buildTargetNodeMap(jaxwsBinding, jaxwsBinding, targetNodes);
        }
        for (Element jaxwsBinding : forest.outerMostBindings) {
            move(jaxwsBinding, targetNodes);
        }
        */
    }

    /**
     * Validates attributes of a &lt;JAXWS:bindings> element.
     */
    private void validate(Element bindings) {
        NamedNodeMap atts = bindings.getAttributes();
        for (int i = 0; i < atts.getLength(); i++) {
            Attr a = (Attr) atts.item(i);
            if (a.getNamespaceURI() != null)
                continue;   // all foreign namespace OK.
            if (a.getLocalName().equals("node"))
                continue;
            if (a.getLocalName().equals("wsdlLocation"))
                continue;

            // TODO: flag error for this undefined attribute
        }
    }

    private void internalize(Element bindings, Node inheritedTarget) {
        // start by the inherited target
        Node target = inheritedTarget;

        validate(bindings); // validate this node

        // look for @wsdlLocation
        if (isTopLevelBinding(bindings)) {
            String wsdlLocation;
            if (bindings.getAttributeNode("wsdlLocation") != null) {
                wsdlLocation = bindings.getAttribute("wsdlLocation");

                try {
                    // absolutize this URI.
                    // TODO: use the URI class
                    // TODO: honor xml:base
                    wsdlLocation = new URL(new URL(forest.getSystemId(bindings.getOwnerDocument())),
                            wsdlLocation).toExternalForm();
                } catch (MalformedURLException e) {
                    wsdlLocation = JAXWSUtils.absolutize(JAXWSUtils.getFileOrURLName(wsdlLocation));
                }
            } else {
                //the node does not have
                wsdlLocation = forest.getFirstRootDocument();
            }
            target = forest.get(wsdlLocation);

            if (target == null) {
                reportError(bindings, WsdlMessages.INTERNALIZER_INCORRECT_SCHEMA_REFERENCE(wsdlLocation, EditDistance.findNearest(wsdlLocation, forest.listSystemIDs())));
                return; // abort processing this <JAXWS:bindings>
            }
        }

        //if the target node is xs:schema, declare the jaxb version on it as latter on it will be
        //required by the inlined schema bindings

        Element element = DOMUtil.getFirstElementChild(target);
        if (element != null && element.getNamespaceURI().equals(Constants.NS_WSDL) && element.getLocalName().equals("definitions")) {
            //get all schema elements
            Element type = DOMUtils.getFirstChildElement(element, Constants.NS_WSDL, "types");
            if (type != null) {
                for (Element schemaElement : DOMUtils.getChildElements(type, Constants.NS_XSD, "schema")) {
                    if (!schemaElement.hasAttributeNS(Constants.NS_XMLNS, "jaxb")) {
                        schemaElement.setAttributeNS(Constants.NS_XMLNS, "xmlns:jaxb", JAXWSBindingsConstants.NS_JAXB_BINDINGS);
                    }

                    //add jaxb:bindings version info. Lets put it to 1.0, may need to change latter
                    if (!schemaElement.hasAttributeNS(JAXWSBindingsConstants.NS_JAXB_BINDINGS, "version")) {
                        schemaElement.setAttributeNS(JAXWSBindingsConstants.NS_JAXB_BINDINGS, "jaxb:version", JAXWSBindingsConstants.JAXB_BINDING_VERSION);
                    }
                }
            }
        }


        NodeList targetNodes = null;
        boolean hasNode = true;
        boolean isToplevelBinding = isTopLevelBinding(bindings);
        if ((isJAXWSBindings(bindings) || isJAXBBindings(bindings)) && bindings.getAttributeNode("node") != null) {
            targetNodes = evaluateXPathMultiNode(bindings, target, bindings.getAttribute("node"), new NamespaceContextImpl(bindings));
        } else
        if (isJAXWSBindings(bindings) && (bindings.getAttributeNode("node") == null) && !isToplevelBinding) {
            hasNode = false;
        } else
        if (isGlobalBinding(bindings) && !isWSDLDefinition(target) && isTopLevelBinding(bindings.getParentNode())) {
            targetNodes = getWSDLDefintionNode(bindings, target);
        }

        //if target is null or empty it means the xpath evaluation has some problem,
        // just return
        if (targetNodes == null && hasNode && !isToplevelBinding)
            return;


        if (hasNode) {
            if (targetNodes != null) {
                for (int i = 0; i < targetNodes.getLength(); i++) {
                    insertBinding(bindings, targetNodes.item(i));
                    // look for child <JAXWS:bindings> and process them recursively
                    Element[] children = getChildElements(bindings);
                    for (Element child : children)
                        if("bindings".equals(child.getLocalName())) {
                            internalize(child, targetNodes.item(i));
                        }
                }
            }
        }
        if (targetNodes == null) {
            // look for child <JAXWS:bindings> and process them recursively
            Element[] children = getChildElements(bindings);

            for (Element child : children)
                internalize(child, target);
        }
    }

    /**
     * Moves JAXWS customizations under their respective target nodes.
     */
    private void insertBinding(@NotNull Element bindings, @NotNull Node target) {
        if ("bindings".equals(bindings.getLocalName())) {
            Element[] children = DOMUtils.getChildElements(bindings);
            for (Element item : children) {
                if ("bindings".equals(item.getLocalName())) {
                    //done
                } else {
                    moveUnder(item, (Element) target);

                }
            }
        } else {
            moveUnder(bindings, (Element) target);
        }
    }


    /**
     * Determines the target node of the "bindings" element
     * by using the inherited target node, then put
     * the result into the "result" map.
     */
    /* TODO Remove this logic if there are no regressions with new internalization logic
    private void buildTargetNodeMap(Element bindings, Node inheritedTarget, Map<Element, Node> result) {
        // start by the inherited target
        Node target = inheritedTarget;

        validate(bindings); // validate this node

        // look for @wsdlLocation
        if (isTopLevelBinding(bindings)) {
            String wsdlLocation;
            if (bindings.getAttributeNode("wsdlLocation") != null) {
                wsdlLocation = bindings.getAttribute("wsdlLocation");

                try {
                    // absolutize this URI.
                    // TODO: use the URI class
                    // TODO: honor xml:base
                    wsdlLocation = new URL(new URL(forest.getSystemId(bindings.getOwnerDocument())),
                            wsdlLocation).toExternalForm();
                } catch (MalformedURLException e) {
                    wsdlLocation = JAXWSUtils.absolutize(JAXWSUtils.getFileOrURLName(wsdlLocation));
                }
            } else {
                //the node does not have
                wsdlLocation = forest.getFirstRootDocument();
            }
            target = forest.get(wsdlLocation);

            if (target == null) {
                reportError(bindings, WsdlMessages.INTERNALIZER_INCORRECT_SCHEMA_REFERENCE(wsdlLocation, EditDistance.findNearest(wsdlLocation, forest.listSystemIDs())));
                return; // abort processing this <JAXWS:bindings>
            }
        }

        //if the target node is xs:schema, declare the jaxb version on it as latter on it will be
        //required by the inlined schema bindings

        Element element = DOMUtil.getFirstElementChild(target);
        if (element != null && element.getNamespaceURI().equals(Constants.NS_WSDL) && element.getLocalName().equals("definitions")) {
            //get all schema elements
            Element type = DOMUtils.getFirstChildElement(element, Constants.NS_WSDL, "types");
            if (type != null) {
                for (Element schemaElement : DOMUtils.getChildElements(type, Constants.NS_XSD, "schema")) {
                    if (!schemaElement.hasAttributeNS(Constants.NS_XMLNS, "jaxb")) {
                        schemaElement.setAttributeNS(Constants.NS_XMLNS, "xmlns:jaxb", JAXWSBindingsConstants.NS_JAXB_BINDINGS);
                    }

                    //add jaxb:bindings version info. Lets put it to 1.0, may need to change latter
                    if (!schemaElement.hasAttributeNS(JAXWSBindingsConstants.NS_JAXB_BINDINGS, "version")) {
                        schemaElement.setAttributeNS(JAXWSBindingsConstants.NS_JAXB_BINDINGS, "jaxb:version", JAXWSBindingsConstants.JAXB_BINDING_VERSION);
                    }
                }
            }
        }


        boolean hasNode = true;
        if ((isJAXWSBindings(bindings) || isJAXBBindings(bindings)) && bindings.getAttributeNode("node") != null) {
            target = evaluateXPathNode(bindings, target, bindings.getAttribute("node"), new NamespaceContextImpl(bindings));
        } else
        if (isJAXWSBindings(bindings) && (bindings.getAttributeNode("node") == null) && !isTopLevelBinding(bindings)) {
            hasNode = false;
        } else
        if (isGlobalBinding(bindings) && !isWSDLDefinition(target) && isTopLevelBinding(bindings.getParentNode())) {
            target = getWSDLDefintionNode(bindings, target);
        }

        //if target is null it means the xpath evaluation has some problem,
        // just return
        if (target == null)
            return;

        // update the result map
        if (hasNode)
            result.put(bindings, target);

        // look for child <JAXWS:bindings> and process them recursively
        Element[] children = getChildElements(bindings);
        for (Element child : children)
            buildTargetNodeMap(child, target, result);
    }
    */
    private NodeList getWSDLDefintionNode(Node bindings, Node target) {
        return evaluateXPathMultiNode(bindings, target, "wsdl:definitions",
                new NamespaceContext() {
                    public String getNamespaceURI(String prefix) {
                        return "http://schemas.xmlsoap.org/wsdl/";
                    }

                    public String getPrefix(String nsURI) {
                        throw new UnsupportedOperationException();
                    }

                    public Iterator getPrefixes(String namespaceURI) {
                        throw new UnsupportedOperationException();
                    }
                });
    }

    private boolean isWSDLDefinition(Node target) {
        if (target == null)
            return false;
        String localName = target.getLocalName();
        String nsURI = target.getNamespaceURI();
        return fixNull(localName).equals("definitions") && fixNull(nsURI).equals("http://schemas.xmlsoap.org/wsdl/");
    }

    private boolean isTopLevelBinding(Node node) {
        return node.getOwnerDocument().getDocumentElement() == node;
    }

    private boolean isJAXWSBindings(Node bindings) {
        return (bindings.getNamespaceURI().equals(JAXWSBindingsConstants.NS_JAXWS_BINDINGS) && bindings.getLocalName().equals("bindings"));
    }

    private boolean isJAXBBindings(Node bindings) {
        return (bindings.getNamespaceURI().equals(JAXWSBindingsConstants.NS_JAXB_BINDINGS) && bindings.getLocalName().equals("bindings"));
    }

    private boolean isGlobalBinding(Node bindings) {
        if (bindings.getNamespaceURI() == null) {
            errorReceiver.warning(forest.locatorTable.getStartLocation((Element) bindings), WsdlMessages.INVALID_CUSTOMIZATION_NAMESPACE(bindings.getLocalName()));
            return false;
        }
        return (bindings.getNamespaceURI().equals(JAXWSBindingsConstants.NS_JAXWS_BINDINGS) &&
                (bindings.getLocalName().equals("package") ||
                        bindings.getLocalName().equals("enableAsyncMapping") ||
                        bindings.getLocalName().equals("enableAdditionalSOAPHeaderMapping") ||
                        bindings.getLocalName().equals("enableWrapperStyle") ||
                        bindings.getLocalName().equals("enableMIMEContent")));
    }

    private static Element[] getChildElements(Element parent) {
        ArrayList<Element> a = new ArrayList<Element>();
        NodeList children = parent.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node item = children.item(i);
            if (!(item instanceof Element)) continue;

            if (JAXWSBindingsConstants.NS_JAXWS_BINDINGS.equals(item.getNamespaceURI()) ||
                    JAXWSBindingsConstants.NS_JAXB_BINDINGS.equals(item.getNamespaceURI()))
                a.add((Element) item);
        }
        return a.toArray(new Element[a.size()]);
    }

    private Node evaluateXPathNode(Node bindings, Node target, String expression, NamespaceContext namespaceContext) {
        NodeList nlst;
        try {
            xpath.setNamespaceContext(namespaceContext);
            nlst = (NodeList) xpath.evaluate(expression, target, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            reportError((Element) bindings, WsdlMessages.INTERNALIZER_X_PATH_EVALUATION_ERROR(e.getMessage()), e);
            return null; // abort processing this <jaxb:bindings>
        }

        if (nlst.getLength() == 0) {
            reportError((Element) bindings, WsdlMessages.INTERNALIZER_X_PATH_EVALUATES_TO_NO_TARGET(expression));
            return null; // abort
        }

        if (nlst.getLength() != 1) {
            reportError((Element) bindings, WsdlMessages.INTERNALIZER_X_PATH_EVAULATES_TO_TOO_MANY_TARGETS(expression, nlst.getLength()));
            return null; // abort
        }

        Node rnode = nlst.item(0);
        if (!(rnode instanceof Element)) {
            reportError((Element) bindings, WsdlMessages.INTERNALIZER_X_PATH_EVALUATES_TO_NON_ELEMENT(expression));
            return null; // abort
        }
        return rnode;
    }

    private NodeList evaluateXPathMultiNode(Node bindings, Node target, String expression, NamespaceContext namespaceContext) {
        NodeList nlst;
        try {
            xpath.setNamespaceContext(namespaceContext);
            nlst = (NodeList) xpath.evaluate(expression, target, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            reportError((Element) bindings, WsdlMessages.INTERNALIZER_X_PATH_EVALUATION_ERROR(e.getMessage()), e);
            return null; // abort processing this <jaxb:bindings>
        }

        if (nlst.getLength() == 0) {
            reportError((Element) bindings, WsdlMessages.INTERNALIZER_X_PATH_EVALUATES_TO_NO_TARGET(expression));
            return null; // abort
        }

        return nlst;
    }

    /**
     * Moves JAXWS customizations under their respective target nodes.
     */
    private void move(Element bindings, Map<Element, Node> targetNodes) {
        Node target = targetNodes.get(bindings);
        if (target == null)
            // this must be the result of an error on the external binding.
            // recover from the error by ignoring this node
            return;

        Element[] children = DOMUtils.getChildElements(bindings);

        for (Element item : children) {
            if ("bindings".equals(item.getLocalName())) {
                // process child <jaxws:bindings> recursively
                move(item, targetNodes);
            } else if (isGlobalBinding(item)) {
                target = targetNodes.get(item);
                moveUnder(item, (Element) target);
            } else {
                if (!(target instanceof Element)) {
                    return; // abort
                }
                // move this node under the target
                moveUnder(item, (Element) target);
            }
        }
    }

    private boolean isJAXBBindingElement(Element e) {
        return fixNull(e.getNamespaceURI()).equals(JAXWSBindingsConstants.NS_JAXB_BINDINGS);
    }

    private boolean isJAXWSBindingElement(Element e) {
        return fixNull(e.getNamespaceURI()).equals(JAXWSBindingsConstants.NS_JAXWS_BINDINGS);
    }

    /**
     * Moves the "decl" node under the "target" node.
     *
     * @param decl   A JAXWS customization element (e.g., &lt;JAXWS:class>)
     * @param target XML wsdl element under which the declaration should move.
     *               For example, &lt;xs:element>
     */
    private void moveUnder(Element decl, Element target) {

        //if there is @node on decl and has a child element jaxb:bindings, move it under the target
        //Element jaxb = getJAXBBindingElement(decl);
        if (isJAXBBindingElement(decl)) {
            //add jaxb namespace declaration
            if (!target.hasAttributeNS(Constants.NS_XMLNS, "jaxb")) {
                target.setAttributeNS(Constants.NS_XMLNS, "xmlns:jaxb", JAXWSBindingsConstants.NS_JAXB_BINDINGS);
            }

            //add jaxb:bindings version info. Lets put it to 1.0, may need to change latter
            if (!target.hasAttributeNS(JAXWSBindingsConstants.NS_JAXB_BINDINGS, "version")) {
                target.setAttributeNS(JAXWSBindingsConstants.NS_JAXB_BINDINGS, "jaxb:version", JAXWSBindingsConstants.JAXB_BINDING_VERSION);
            }

            // HACK: allow XJC extension all the time. This allows people to specify
            // the <xjc:someExtension> in the external bindings. Otherwise users lack the ability
            // to specify jaxb:extensionBindingPrefixes, so it won't work.
            //
            // the current workaround is still problematic in the sense that
            // it can't support user-defined extensions. This needs more careful thought.

            //JAXB doesn't allow writing jaxb:extensionbindingPrefix anywhere other than root element so lets write only on <xs:schema>
            if (target.getLocalName().equals("schema") && target.getNamespaceURI().equals(Constants.NS_XSD) && !target.hasAttributeNS(JAXWSBindingsConstants.NS_JAXB_BINDINGS, "extensionBindingPrefixes")) {
                target.setAttributeNS(JAXWSBindingsConstants.NS_JAXB_BINDINGS, "jaxb:extensionBindingPrefixes", "xjc");
                target.setAttributeNS(Constants.NS_XMLNS, "xmlns:xjc", JAXWSBindingsConstants.NS_XJC_BINDINGS);
            }

            //insert xs:annotation/xs:appinfo where in jaxb:binding will be put
            target = refineSchemaTarget(target);
            copyInscopeNSAttributes(decl);
        } else if (isJAXWSBindingElement(decl)) {
            //add jaxb namespace declaration
            if (!target.hasAttributeNS(Constants.NS_XMLNS, "JAXWS")) {
                target.setAttributeNS(Constants.NS_XMLNS, "xmlns:JAXWS", JAXWSBindingsConstants.NS_JAXWS_BINDINGS);
            }

            //insert xs:annotation/xs:appinfo where in jaxb:binding will be put
            target = refineWSDLTarget(target);
            copyInscopeNSAttributes(decl);
        } else {
            return;
        }

        // finally move the declaration to the target node.
        if (target.getOwnerDocument() != decl.getOwnerDocument()) {
            // if they belong to different DOM documents, we need to clone them
            decl = (Element) target.getOwnerDocument().importNode(decl, true);

        }

        target.appendChild(decl);
    }

    /**
     * Copy in-scope namespace declarations of the decl node
     * to the decl node itself so that this move won't change
     * the in-scope namespace bindings.
     */
    private void copyInscopeNSAttributes(Element e) {
        Element p = e;
        Set<String> inscopes = new HashSet<String>();
        while (true) {
            NamedNodeMap atts = p.getAttributes();
            for (int i = 0; i < atts.getLength(); i++) {
                Attr a = (Attr) atts.item(i);
                if (Constants.NS_XMLNS.equals(a.getNamespaceURI())) {
                    String prefix;
                    if (a.getName().indexOf(':') == -1) prefix = "";
                    else prefix = a.getLocalName();

                    if (inscopes.add(prefix) && p != e) {
                        // if this is the first time we see this namespace bindings,
                        // copy the declaration.
                        // if p==decl, there's no need to. Note that
                        // we want to add prefix to inscopes even if p==Decl

                        e.setAttributeNodeNS((Attr) a.cloneNode(true));
                    }
                }
            }

            if (p.getParentNode() instanceof Document)
                break;

            p = (Element) p.getParentNode();
        }

        if (!inscopes.contains("")) {
            // if the default namespace was undeclared in the context of decl,
            // it must be explicitly set to "" since the new environment might
            // have a different default namespace URI.
            e.setAttributeNS(Constants.NS_XMLNS, "xmlns", "");
        }
    }

    public Element refineSchemaTarget(Element target) {
        // look for existing xs:annotation
        Element annotation = DOMUtils.getFirstChildElement(target, Constants.NS_XSD, "annotation");
        if (annotation == null)
            // none exists. need to make one
            annotation = insertXMLSchemaElement(target, "annotation");

        // then look for appinfo
        Element appinfo = DOMUtils.getFirstChildElement(annotation, Constants.NS_XSD, "appinfo");
        if (appinfo == null)
            // none exists. need to make one
            appinfo = insertXMLSchemaElement(annotation, "appinfo");

        return appinfo;
    }

    public Element refineWSDLTarget(Element target) {
        // look for existing xs:annotation
        Element JAXWSBindings = DOMUtils.getFirstChildElement(target, JAXWSBindingsConstants.NS_JAXWS_BINDINGS, "bindings");
        if (JAXWSBindings == null)
            // none exists. need to make one
            JAXWSBindings = insertJAXWSBindingsElement(target, "bindings");
        return JAXWSBindings;
    }

    /**
     * Creates a new XML Schema element of the given local name
     * and insert it as the first child of the given parent node.
     *
     * @return Newly create element.
     */
    private Element insertXMLSchemaElement(Element parent, String localName) {
        // use the same prefix as the parent node to avoid modifying
        // the namespace binding.
        String qname = parent.getTagName();
        int idx = qname.indexOf(':');
        if (idx == -1) qname = localName;
        else qname = qname.substring(0, idx + 1) + localName;

        Element child = parent.getOwnerDocument().createElementNS(Constants.NS_XSD, qname);

        NodeList children = parent.getChildNodes();

        if (children.getLength() == 0)
            parent.appendChild(child);
        else
            parent.insertBefore(child, children.item(0));

        return child;
    }

    private Element insertJAXWSBindingsElement(Element parent, String localName) {
        String qname = "JAXWS:" + localName;

        Element child = parent.getOwnerDocument().createElementNS(JAXWSBindingsConstants.NS_JAXWS_BINDINGS, qname);

        NodeList children = parent.getChildNodes();

        if (children.getLength() == 0)
            parent.appendChild(child);
        else
            parent.insertBefore(child, children.item(0));

        return child;
    }

    private static
    @NotNull
    String fixNull(@Nullable String s) {
        if (s == null) return "";
        else return s;
    }


    private void reportError(Element errorSource, String formattedMsg) {
        reportError(errorSource, formattedMsg, null);
    }

    private void reportError(Element errorSource,
                             String formattedMsg, Exception nestedException) {

        SAXParseException e = new SAXParseException2(formattedMsg,
                forest.locatorTable.getStartLocation(errorSource),
                nestedException);
        errorReceiver.error(e);
    }


}
