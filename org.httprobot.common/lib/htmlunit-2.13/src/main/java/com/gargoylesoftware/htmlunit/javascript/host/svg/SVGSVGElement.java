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
package com.gargoylesoftware.htmlunit.javascript.host.svg;

import static com.gargoylesoftware.htmlunit.javascript.configuration.BrowserName.CHROME;
import static com.gargoylesoftware.htmlunit.javascript.configuration.BrowserName.FF;
import static com.gargoylesoftware.htmlunit.javascript.configuration.BrowserName.IE;

import com.gargoylesoftware.htmlunit.javascript.configuration.JsxClass;
import com.gargoylesoftware.htmlunit.javascript.configuration.JsxFunction;
import com.gargoylesoftware.htmlunit.javascript.configuration.WebBrowser;
import com.gargoylesoftware.htmlunit.svg.SvgSvg;

/**
 * A JavaScript object for SVGSVGElement.
 *
 * @version $Revision: 8603 $
 * @author Ahmed Ashour
 */
@JsxClass(domClasses = SvgSvg.class,
    browsers = { @WebBrowser(value = IE, minVersion = 9), @WebBrowser(FF), @WebBrowser(CHROME) })
public class SVGSVGElement extends SVGElement {

    /**
     * Creates an instance. JavaScript objects must have a default constructor.
     */
    public SVGSVGElement() {
        // nothing
    }

    /**
     * Creates a new {@link SVGMatrix}.
     * @return the new matrix
     */
    @JsxFunction
    public SVGMatrix createSVGMatrix() {
        return new SVGMatrix(getWindow());
    }

    /**
     * Creates a new {@link SVGMatrix}.
     * @return the new matrix
     */
    @JsxFunction
    public SVGMatrix getScreenCTM() {
        return new SVGMatrix(getWindow());
    }

    /**
     * Creates a new {@link SVGRect}.
     * @return the new rect
     */
    @JsxFunction
    public SVGRect createSVGRect() {
        final SVGRect rect = new SVGRect();
        rect.setPrototype(getPrototype(rect.getClass()));
        rect.setParentScope(getParentScope());
        return rect;
    }
}
