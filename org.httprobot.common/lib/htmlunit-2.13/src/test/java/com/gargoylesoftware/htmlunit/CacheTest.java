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
package com.gargoylesoftware.htmlunit;

import static com.gargoylesoftware.htmlunit.BrowserRunner.Browser.NONE;
import static com.gargoylesoftware.htmlunit.util.StringUtils.formatHttpDate;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.BrowserRunner.Alerts;
import com.gargoylesoftware.htmlunit.BrowserRunner.Browser;
import com.gargoylesoftware.htmlunit.BrowserRunner.Browsers;
import com.gargoylesoftware.htmlunit.BrowserRunner.NotYetImplemented;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

/**
 * Tests for {@link Cache}.
 *
 * @version $Revision: 8637 $
 * @author Marc Guillemot
 * @author Ahmed Ashour
 */
@RunWith(BrowserRunner.class)
public class CacheTest extends SimpleWebTestCase {

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Browsers(NONE)
    public void isDynamicContent() throws Exception {
        final Cache cache = new Cache();
        final Map<String, String> headers = new HashMap<String, String>();
        final WebResponse response = new DummyWebResponse() {
            @Override
            public String getResponseHeaderValue(final String headerName) {
                return headers.get(headerName);
            }
        };

        assertTrue(cache.isDynamicContent(response));

        headers.put("Last-Modified", "Sun, 15 Jul 2007 20:46:27 GMT");
        assertFalse(cache.isDynamicContent(response));

        headers.put("Last-Modified", formatHttpDate(DateUtils.addMinutes(new Date(), -5)));
        assertTrue(cache.isDynamicContent(response));

        headers.put("Expires", formatHttpDate(DateUtils.addMinutes(new Date(), 5)));
        assertTrue(cache.isDynamicContent(response));

        headers.put("Expires", formatHttpDate(DateUtils.addHours(new Date(), 1)));
        assertFalse(cache.isDynamicContent(response));

        headers.remove("Last-Modified");
        assertFalse(cache.isDynamicContent(response));

        headers.put("Expires", "0");
        assertTrue(cache.isDynamicContent(response));

        headers.put("Expires", "-1");
        assertTrue(cache.isDynamicContent(response));
    }

    /**
     *@throws Exception if the test fails
     */
    @Test
    public void usage() throws Exception {
        final String content = "<html><head><title>page 1</title>\n"
            + "<script src='foo1.js'></script>\n"
            + "<script src='foo2.js'></script>\n"
            + "</head><body>\n"
            + "<a href='page2.html'>to page 2</a>\n"
            + "</body></html>";

        final String content2 = "<html><head><title>page 2</title>\n"
            + "<script src='foo2.js'></script>\n"
            + "</head><body>\n"
            + "<a href='page1.html'>to page 1</a>\n"
            + "</body></html>";

        final String script1 = "alert('in foo1');";
        final String script2 = "alert('in foo2');";

        final WebClient webClient = getWebClient();
        final MockWebConnection connection = new MockWebConnection();
        webClient.setWebConnection(connection);

        final URL urlPage1 = new URL(URL_FIRST, "page1.html");
        connection.setResponse(urlPage1, content);
        final URL urlPage2 = new URL(URL_FIRST, "page2.html");
        connection.setResponse(urlPage2, content2);

        final List<NameValuePair> headers = new ArrayList<NameValuePair>();
        headers.add(new NameValuePair("Last-Modified", "Sun, 15 Jul 2007 20:46:27 GMT"));
        connection.setResponse(new URL(URL_FIRST, "foo1.js"), script1, 200, "ok", JAVASCRIPT_MIME_TYPE, headers);
        connection.setResponse(new URL(URL_FIRST, "foo2.js"), script2, 200, "ok", JAVASCRIPT_MIME_TYPE, headers);

        final List<String> collectedAlerts = new ArrayList<String>();
        webClient.setAlertHandler(new CollectingAlertHandler(collectedAlerts));

        final HtmlPage page1 = webClient.getPage(urlPage1);
        final String[] expectedAlerts = {"in foo1", "in foo2"};
        assertEquals(expectedAlerts, collectedAlerts);

        collectedAlerts.clear();
        page1.getAnchors().get(0).click();

        assertEquals(new String[] {"in foo2"}, collectedAlerts);
        assertEquals("no request for scripts should have been performed",
                urlPage2, connection.getLastWebRequest().getUrl());
    }

    /**
     *@throws Exception if the test fails
     */
    @Test
    @NotYetImplemented(Browser.FF)
    public void usageUrlEncoded() throws Exception {
        final String content = "<html>\n"
            + "<head>\n"
            + "  <title>page 1</title>\n"
            + "  <script src='foo1.js'></script>\n"
            + "  <script src='foo2.js?foo[1]=bar/baz'></script>\n"
            + "</head>\n"
            + "<body>\n"
            + "  <a href='page2.html'>to page 2</a>\n"
            + "</body>\n"
            + "</html>";

        final String content2 = "<html>\n2"
            + "<head>\n"
            + "  <title>page 2</title>\n"
            + "  <script src='foo2.js?foo[1]=bar/baz'></script>\n"
            + "</head>\n"
            + "<body>\n"
            + "  <a href='page1.html'>to page 1</a>\n"
            + "</body>\n"
            + "</html>";

        final String script1 = "alert('in foo1');";
        final String script2 = "alert('in foo2');";

        final URL urlPage1 = new URL(URL_FIRST, "page1.html");
        getMockWebConnection().setResponse(urlPage1, content);
        final URL urlPage2 = new URL(URL_FIRST, "page2.html");
        getMockWebConnection().setResponse(urlPage2, content2);

        final List<NameValuePair> headers = new ArrayList<NameValuePair>();
        headers.add(new NameValuePair("Last-Modified", "Sun, 15 Jul 2007 20:46:27 GMT"));
        getMockWebConnection().setResponse(new URL(URL_FIRST, "foo1.js"), script1,
                200, "ok", JAVASCRIPT_MIME_TYPE, headers);
        getMockWebConnection().setDefaultResponse(script2, 200, "ok", JAVASCRIPT_MIME_TYPE, headers);

        final WebClient webClient = getWebClientWithMockWebConnection();

        final List<String> collectedAlerts = new ArrayList<String>();
        webClient.setAlertHandler(new CollectingAlertHandler(collectedAlerts));

        final HtmlPage page1 = webClient.getPage(urlPage1);
        final String[] expectedAlerts = {"in foo1", "in foo2"};
        assertEquals(expectedAlerts, collectedAlerts);

        collectedAlerts.clear();
        page1.getAnchors().get(0).click();

        assertEquals(new String[] {"in foo2"}, collectedAlerts);
        assertEquals("no request for scripts should have been performed",
                urlPage2, getMockWebConnection().getLastWebRequest().getUrl());
    }

    /**
     *@throws Exception if the test fails
     */
    @Test
    public void maxSizeMaintained() throws Exception {
        final String html = "<html><head><title>page 1</title>\n"
            + "<script src='foo1.js' type='text/javascript'/>\n"
            + "<script src='foo2.js' type='text/javascript'/>\n"
            + "</head><body>abc</body></html>";

        final WebClient client = getWebClient();
        client.getCache().setMaxSize(1);

        final MockWebConnection connection = new MockWebConnection();
        client.setWebConnection(connection);

        final URL pageUrl = new URL(URL_FIRST, "page1.html");
        connection.setResponse(pageUrl, html);

        final List<NameValuePair> headers =
            Collections.singletonList(new NameValuePair("Last-Modified", "Sun, 15 Jul 2007 20:46:27 GMT"));
        connection.setResponse(new URL(URL_FIRST, "foo1.js"), ";", 200, "ok", JAVASCRIPT_MIME_TYPE, headers);
        connection.setResponse(new URL(URL_FIRST, "foo2.js"), ";", 200, "ok", JAVASCRIPT_MIME_TYPE, headers);

        client.getPage(pageUrl);
        assertEquals(1, client.getCache().getSize());

        client.getCache().clear();
        assertEquals(0, client.getCache().getSize());
    }

    /**
     * TODO: improve CSS caching to cache a COPY of the object as stylesheet objects can be modified dynamically.
     * @throws Exception if the test fails
     */
    @Test
    public void cssIsCached() throws Exception {
        final String html = "<html><head><title>page 1</title>\n"
            + "<style>.x { color: red; }</style>\n"
            + "<link rel='stylesheet' type='text/css' href='foo.css' />\n"
            + "</head>\n"
            + "<body onload='document.styleSheets.item(0); document.styleSheets.item(1);'>x</body>\n"
            + "</html>";

        final WebClient client = getWebClient();

        final MockWebConnection connection = new MockWebConnection();
        client.setWebConnection(connection);

        final URL pageUrl = new URL(URL_FIRST, "page1.html");
        connection.setResponse(pageUrl, html);

        final List<NameValuePair> headers =
            Collections.singletonList(new NameValuePair("Last-Modified", "Sun, 15 Jul 2007 20:46:27 GMT"));
        connection.setResponse(new URL(URL_FIRST, "foo.css"), "", 200, "OK", JAVASCRIPT_MIME_TYPE, headers);

        client.getPage(pageUrl);
        assertEquals(2, client.getCache().getSize());
    }

    /**
     * Test that content retrieved with XHR is cached when right headers are here.
     * @throws Exception if the test fails
     */
    @Test
    @Alerts({ "hello", "hello" })
    public void xhrContentCached() throws Exception {
        final String html = "<html><head><title>page 1</title>\n"
            + "<script>\n"
            + "  function doTest() {\n"
            + "    var xhr = window.XMLHttpRequest ? new XMLHttpRequest()"
            + "      : new ActiveXObject('Microsoft.XMLHTTP');\n"
            + "    xhr.open('GET', 'foo.txt', false);\n"
            + "    xhr.send('');\n"
            + "    alert(xhr.responseText);\n"
            + "    xhr.send('');\n"
            + "    alert(xhr.responseText);\n"
            + "  }\n"
            + "</script>\n"
            + "</head>\n"
            + "<body onload='doTest()'>x</body>\n"
            + "</html>";

        final MockWebConnection connection = getMockWebConnection();

        final List<NameValuePair> headers =
            Collections.singletonList(new NameValuePair("Last-Modified", "Sun, 15 Jul 2007 20:46:27 GMT"));
        connection.setResponse(new URL(getDefaultUrl(), "foo.txt"), "hello", 200, "OK", "text/plain", headers);

        loadPageWithAlerts(html);

        assertEquals(2, connection.getRequestCount());
    }
}

class DummyWebResponse extends WebResponse {

    DummyWebResponse() {
        super(null, null, 0);
    }

    @Override
    public InputStream getContentAsStream() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public String getContentAsString() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public String getContentAsString(final String encoding) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public String getContentCharset() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public String getContentCharsetOrNull() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public String getContentType() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public long getLoadTime() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public List<NameValuePair> getResponseHeaders() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public String getResponseHeaderValue(final String headerName) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public int getStatusCode() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public String getStatusMessage() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public WebRequest getWebRequest() {
        throw new RuntimeException("not implemented");
    }
}
