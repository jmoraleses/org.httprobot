/*
 * Copyright (c) 2002-2013 Gargoyle Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gargoylesoftware.htmlunit.javascript.host;

import static com.gargoylesoftware.htmlunit.javascript.configuration.BrowserName.FF;

import org.apache.xml.utils.PrefixResolver;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.javascript.SimpleScriptable;
import com.gargoylesoftware.htmlunit.javascript.configuration.JsxClass;
import com.gargoylesoftware.htmlunit.javascript.configuration.JsxFunction;
import com.gargoylesoftware.htmlunit.javascript.configuration.WebBrowser;
import com.gargoylesoftware.htmlunit.xml.XmlUtil;

/**
 * A JavaScript object for XPathNSResolver.
 *
 * @version $Revision: 8397 $
 * @author Ahmed Ashour
 * @author Chuck Dumont
 */
@JsxClass(browsers = @WebBrowser(FF))
public class XPathNSResolver extends SimpleScriptable implements PrefixResolver {

    private Object element_;

    /**
     * Sets the element to start lookup from.
     * @param element {@link com.gargoylesoftware.htmlunit.javascript.host.html.HTMLElement}
     * or {@link Element} to start lookup from
     */
    public void setElement(final Node element) {
        element_ = element;
    }

    /**
     * Look up the namespace URI associated to the given namespace prefix.
     * @param prefix the prefix to look for
     * @return the associated namespace URI or null if none is found
     */
    @JsxFunction
    public String lookupNamespaceURI(final String prefix) {
        return XmlUtil.lookupNamespaceURI((DomElement) ((SimpleScriptable) element_).getDomNodeOrDie(), prefix);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBaseIdentifier() {
        return XmlUtil.lookupNamespaceURI((DomElement) ((SimpleScriptable) element_).getDomNodeOrDie(), "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNamespaceForPrefix(final String prefix) {
        return lookupNamespaceURI(prefix);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNamespaceForPrefix(final String prefix, final org.w3c.dom.Node context) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean handlesNullPrefixes() {
        return false;
    }
}
