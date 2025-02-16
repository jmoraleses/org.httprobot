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
package com.gargoylesoftware.htmlunit.javascript.host.html;

import static com.gargoylesoftware.htmlunit.BrowserVersionFeatures.CSS_DISPLAY_DEFAULT;
import static com.gargoylesoftware.htmlunit.javascript.configuration.BrowserName.FF;

import com.gargoylesoftware.htmlunit.html.HtmlArea;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.javascript.configuration.JsxClass;
import com.gargoylesoftware.htmlunit.javascript.configuration.JsxGetter;
import com.gargoylesoftware.htmlunit.javascript.configuration.JsxSetter;
import com.gargoylesoftware.htmlunit.javascript.configuration.WebBrowser;

/**
 * The JavaScript object "HTMLAreaElement".
 *
 * @version $Revision: 8391 $
 * @author Ahmed Ashour
 * @author Ronald Brill
*/
@JsxClass(domClasses = HtmlArea.class)
public class HTMLAreaElement extends HTMLElement {

    /**
     * Calls for instance for implicit conversion to string.
     * @see com.gargoylesoftware.htmlunit.javascript.SimpleScriptable#getDefaultValue(java.lang.Class)
     * @param hint the type hint
     * @return the default value
     */
    @Override
    public Object getDefaultValue(final Class<?> hint) {
        final HtmlElement element = getDomNodeOrNull();
        if (element == null) {
            return super.getDefaultValue(null);
        }
        return HTMLAnchorElement.getDefaultValue(element);
    }

    /**
     * Returns the value of the "alt" property.
     * @return the value of the "alt" property
     */
    @JsxGetter
    public String getAlt() {
        final String alt = getDomNodeOrDie().getAttribute("alt");
        return alt;
    }

    /**
     * Returns the value of the "alt" property.
     * @param alt the value
     */
    @JsxSetter
    public void setAlt(final String alt) {
        getDomNodeOrDie().setAttribute("alt", alt);
    }

    /**
     * {@inheritDoc} Overridden to modify browser configurations.
     */
    @Override
    @JsxGetter(@WebBrowser(FF))
    public String getAccessKey() {
        return super.getAccessKey();
    }

    /**
     * {@inheritDoc} Overridden to modify browser configurations.
     */
    @Override
    @JsxSetter(@WebBrowser(FF))
    public void setAccessKey(final String accessKey) {
        super.setAccessKey(accessKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isEndTagForbidden() {
        return true;
    }

    /**
     * <span style="color:red">INTERNAL API - SUBJECT TO CHANGE AT ANY TIME - USE AT YOUR OWN RISK.</span><br/>
     * {@inheritDoc}
    */
    @Override
    public String getDefaultStyleDisplay() {
        if (getBrowserVersion().hasFeature(CSS_DISPLAY_DEFAULT)) {
            return "none";
        }
        return "inline";
    }
}
