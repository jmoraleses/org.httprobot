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

package com.sun.xml.bind.api;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 * <h1>Canonical XML Support in the JAXB RI</h1>
 *
 * <p>
 * The JAXB RI marshaller or {@link JAXBRIContext} can be configured to marshal
 * canonical XML. There are two ways to do this:
 *
 * <ol>
 *  <li>If you know in advance that you'll need c14n, use
 *      {@link JAXBRIContext#newInstance} to create a new {@link JAXBContext}
 *      with c14n. In this way, every marshaller created from this {@link JAXBRIContext}
 *      will do c14n.
 *  <li>use {@link Marshaller#setProperty(String, Object)} with {@link JAXBRIContext#CANONICALIZATION_SUPPORT}
 *      to enable c14n on a marshaller instance.
 * </ol>
 *
 * <p>
 * The first option runs faster, because the JAXB RI can do a better optimization,
 * but the end result is the same.
 *
 *
 * <h2>Supported C14n Modes</h2>
 * <h3>Inclusive Canonicalization</h3>
 * <p>
 * The generated canonical XML documents will follow
 * <a href="http://www.w3.org/TR/2001/REC-xml-c14n-20010315">the Canonical XML spec</a>
 * provided that you marshal it to UTF-8 (which is the spec requirement) and
 * don't turn on the formatting.
 *
 * <p>
 * When using JAXB to marshal a tree canonically to be a subtree of a bigger document,
 * you also need to use {@link com.sun.xml.bind.marshaller.NamespacePrefixMapper}
 * with the marshaller.
 *
 * In particular, use
 * {@link com.sun.xml.bind.marshaller.NamespacePrefixMapper#getPreDeclaredNamespaceUris2()}
 * to make sure that the marshalling does not produce redundant namespace declarations
 * at the first element it marshals. See below:
 *
 * <pre><xmp>
 * <root xmlns="foo">
 *   <abc>
 *     ... suppose you are marshalling a subtree into here ...
 *   </abc>
 * </root>
 *
 * [[ if you don't use NamespacePrefixMapper ]]
 * <root xmlns="foo">
 *   <abc>
 *     <child xmlns="foo">
 *       <bar/>
 *     </child>
 *   </abc>
 * </root>
 *
 * [[ use NamespacePrefixMapper and return "","foo" to get this ]]
 * <root xmlns="foo">
 *   <abc>
 *     <child>
 *       <bar/>
 *     </child>
 *   </abc>
 * </root>
 * </xmp></pre>
 *
 *
 *
 * <h3>Exclusive Canonicalization</h3>
 * <p>
 * JAXB RI doesn't support exclusive canonicalization (xc14n) in its full generality,
 * but it can be used to performa xc14n if the application is in position to choose
 * the list of <a href="http://www.w3.org/TR/2002/REC-xml-exc-c14n-20020718/#def-InclusiveNamespaces-PrefixList">
 * "inclusive namespaces"</a> (which is the most normal case.)
 *
 * <p>
 * IOW, this support can be used when c14n is for producing a new signature.
 *
 * <p>
 * The exclusive c14n support in JAXB can be done by making "inclusive namespaces"
 * an union of (1) all the namespace URIs statically known to JAXB RI and
 * (2) all in-scope namespace bindings available at ancestors.
 *
 * This effectively reduces xc14n to inclusive c14n.
 *
 * <p>
 * Specifically, the calling application should do the followings:
 *
 * <ol>
 *  <li>List up all the in-scope namespaces declared in ancestor elements,
 *      and add them to the inclusive namespace prefix list.
 *  <li>Do not  
 * </ol>
 *
 * <h2>Unsupported Features</h2>
 * <p>
 * When canonicalizing a subtree, the canonical XML spec requires the xml attributes
 * (such as xml:lang, xml:base) on ancestor elements to be copied over to the root
 * of the canonical subtree.
 * This behavior is not implemented.
 *
 *
 * <h2>Call For Help</h2>
 * <p>
 * The reason this feature is experimental is because the JAXB team feels uncomfortable
 * with the understanding of the c14n specs. Therefore, we'd like to be able to change
 * the way this mechanism works as we go forward, and that's why this feature is an
 * experimental vendor extension. Any feedback on this feature is greatly appreciated.
 *
 *
 * <h2>Internals</h2>
 * <p>
 * The c14n support in {@link JAXBRIContext} causes the marshaller to write
 * "known" attributes in the lexicographical order (except those attributes in the attribute wildcard.)
 *
 * <p>
 * If the object tree to be marshalled doesn't contain any attribute wildcard,
 * the marshalling performs almost as fast as the ordinary marshalling mode.
 * The only additional cost is:
 * <ol>
 *  <li>buffering of attributes
 *  <li>bubble-sorting of namespace declarations
 * </ol>
 *
 * @see JAXBRIContext#CANONICALIZATION_SUPPORT
 *
 * @ArchitectureDocument
 * @author Kohsuke Kawaguchi
 */
public class C14nSupport_ArchitectureDocument {
}
