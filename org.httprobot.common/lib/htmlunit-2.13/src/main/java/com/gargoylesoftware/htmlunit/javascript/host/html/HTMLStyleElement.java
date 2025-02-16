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

import static com.gargoylesoftware.htmlunit.javascript.configuration.BrowserName.FF;
import static com.gargoylesoftware.htmlunit.javascript.configuration.BrowserName.IE;

import java.io.StringReader;

import org.w3c.css.sac.InputSource;

import com.gargoylesoftware.htmlunit.Cache;
import com.gargoylesoftware.htmlunit.html.HtmlStyle;
import com.gargoylesoftware.htmlunit.javascript.configuration.JsxClass;
import com.gargoylesoftware.htmlunit.javascript.configuration.JsxGetter;
import com.gargoylesoftware.htmlunit.javascript.configuration.JsxSetter;
import com.gargoylesoftware.htmlunit.javascript.configuration.WebBrowser;
import com.gargoylesoftware.htmlunit.javascript.host.css.CSSStyleSheet;

/**
 * The JavaScript object "HTMLStyleElement".
 *
 * @version $Revision: 8500 $
 * @author Ahmed Ashour
 * @author Marc Guillemot
 * @author Ronald Brill
 */
@JsxClass(domClasses = HtmlStyle.class)
public class HTMLStyleElement extends HTMLElement {

    private CSSStyleSheet sheet_;

    /**
     * Gets the associated sheet.
     * @see <a href="http://www.xulplanet.com/references/objref/HTMLStyleElement.html">Mozilla doc</a>
     * @return the sheet
     */
    @JsxGetter(@WebBrowser(FF))
    public CSSStyleSheet getSheet() {
        if (sheet_ != null) {
            return sheet_;
        }

        final HtmlStyle style = (HtmlStyle) getDomNodeOrDie();
        final String css = style.getTextContent();

        final Cache cache = getWindow().getWebWindow().getWebClient().getCache();
        final org.w3c.dom.css.CSSStyleSheet cached = cache.getCachedStyleSheet(css);
        final String uri = getDomNodeOrDie().getPage().getWebResponse().getWebRequest()
        .getUrl().toExternalForm();
        if (cached != null) {
            sheet_ = new CSSStyleSheet(this, cached, uri);
        }
        else {
            final InputSource source = new InputSource(new StringReader(css));
            sheet_ = new CSSStyleSheet(this, source, uri);
            cache.cache(css, sheet_.getWrappedSheet());
        }

        return sheet_;
    }

    /**
     * Gets the associated sheet (IE).
     * @return the sheet
     */
    @JsxGetter(@WebBrowser(IE))
    public CSSStyleSheet getStyleSheet() {
        return getSheet();
    }

    /**
     * Returns the type of this style.
     * @return the type
     */
    @JsxGetter()
    public String getType() {
        final HtmlStyle style = (HtmlStyle) getDomNodeOrDie();
        return style.getTypeAttribute();
    }

    /**
     * Sets the type of this style.
     * @param type the new type
     */
    @JsxSetter()
    public void setType(final String type) {
        final HtmlStyle style = (HtmlStyle) getDomNodeOrDie();
        style.setTypeAttribute(type);
    }

    /**
     * Returns the media of this style.
     * @return the media
     */
    @JsxGetter()
    public String getMedia() {
        final HtmlStyle style = (HtmlStyle) getDomNodeOrDie();
        return style.getAttribute("media");
    }
}
