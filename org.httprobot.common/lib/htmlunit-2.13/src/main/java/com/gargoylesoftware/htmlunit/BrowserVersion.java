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

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.gargoylesoftware.htmlunit.javascript.configuration.BrowserFeature;
import com.gargoylesoftware.htmlunit.javascript.configuration.WebBrowser;

/**
 * Objects of this class represent one specific version of a given browser. Predefined
 * constants are provided for common browser versions.
 *
 * <p>You can change the constants by something like:
 * <pre id='htmlUnitCode'>
 *      String applicationName = "APPNAME";
 *      String applicationVersion = "APPVERSION";
 *      String userAgent = "USERAGENT";
 *      int browserVersionNumeric = NUMERIC;
 *
 *      BrowserVersion browser = new BrowserVersion(applicationName, applicationVersion, userAgent, browserVersionNumeric) {
 *          public boolean hasFeature(BrowserVersionFeatures property) {
 *
 *              // change features here
 *              return BrowserVersion.BROWSER.hasFeature(property);
 *          }
 *      };
 * </pre>
 * <script>
 *     var pre = document.getElementById('htmlUnitCode');
 *     pre.innerHTML = pre.innerHTML.replace('APPNAME', navigator.appName);
 *     pre.innerHTML = pre.innerHTML.replace('APPVERSION', navigator.appVersion);
 *     pre.innerHTML = pre.innerHTML.replace('USERAGENT', navigator.userAgent);
 *     var isMicrosoft = navigator.appName.indexOf('Microsoft') != -1;
 *     var numeric = isMicrosoft ? 9 : 17;
 *     pre.innerHTML = pre.innerHTML.replace('NUMERIC', numeric);
 *     var browser = isMicrosoft ? "INTERNET_EXPLORER_8" : "FIREFOX_10";
 *     pre.innerHTML = pre.innerHTML.replace('BROWSER', browser);
 * </script>
 * However, note that the constants are not enough to fully customize the browser,
 *   you also need to look into the {@link BrowserVersionFeatures} and the classes inside "javascript" package.
 *
 * @version $Revision: 8590 $
 * @author <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 * @author Daniel Gredler
 * @author Marc Guillemot
 * @author Chris Erskine
 * @author Ahmed Ashour
 * @author Frank Danek
 */
public class BrowserVersion implements Serializable, Cloneable {

    private String applicationCodeName_ = "Mozilla";
    private String applicationMinorVersion_ = "0";
    private String applicationName_;
    private String applicationVersion_;
    private String browserLanguage_ = LANGUAGE_ENGLISH_US;
    private String cpuClass_ = CPU_CLASS_X86;
    private boolean onLine_ = true;
    private String platform_ = PLATFORM_WIN32;
    private String systemLanguage_ = LANGUAGE_ENGLISH_US;
    private String userAgent_;
    private String userLanguage_ = LANGUAGE_ENGLISH_US;
    private float browserVersionNumeric_;
    private final Set<PluginConfiguration> plugins_ = new HashSet<PluginConfiguration>();
    private final Set<BrowserVersionFeatures> features_ = EnumSet.noneOf(BrowserVersionFeatures.class);
    private final String nickname_;
    private String htmlAcceptHeader_;
    private String imgAcceptHeader_;
    private String cssAcceptHeader_;

    /**
     * Application name for the Internet Explorer series of browsers.
     */
    private static final String INTERNET_EXPLORER = "Microsoft Internet Explorer";

    /**
     * Application name the Netscape navigator series of browsers.
     */
    private static final String NETSCAPE = "Netscape";

    /**
     * United States English language identifier.
     */
    private static final String LANGUAGE_ENGLISH_US = "en-us";

    /**
     * The X86 CPU class.
     */
    private static final String CPU_CLASS_X86 = "x86";

    /**
     * The WIN32 platform.
     */
    private static final String PLATFORM_WIN32 = "Win32";

    /**
     * Firefox 3.6.
     * @deprecated as of 2.12. Use FF17 instead.
     **/
    @Deprecated
    public static final BrowserVersion FIREFOX_3_6 = new BrowserVersion(
        NETSCAPE, "5.0 (Windows; en-US)",
        "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.2.28) Gecko/20120306 Firefox/3.6.28",
        (float) 3.6, "FF3.6", null);

    /**
     * Firefox 10. Warning: experimental!!!.
     * @deprecated as of 2.12. Use FF17 instead.
     */
    @Deprecated
    public static final BrowserVersion FIREFOX_10 = new BrowserVersion(
        NETSCAPE, "5.0 (Windows)",
        "Mozilla/5.0 (Windows NT 6.1; rv:10.0.11) Gecko/20100101 Firefox/10.0.11",
        (float) 10.0, "FF10", null);

    /**
     * Firefox 17 ESR.
     * @since 2.12
     **/
    public static final BrowserVersion FIREFOX_17 = new BrowserVersion(
        NETSCAPE, "5.0 (Windows)",
        "Mozilla/5.0 (Windows NT 6.1; rv:17.0) Gecko/20100101 Firefox/17.0",
        (float) 17.0, "FF17", null);

    /**
     * Internet Explorer 6.
     * @deprecated as of 2.12
     */
    @Deprecated
    public static final BrowserVersion INTERNET_EXPLORER_6 = new BrowserVersion(
        INTERNET_EXPLORER, "4.0 (compatible; MSIE 6.0b; Windows 98)",
        "Mozilla/4.0 (compatible; MSIE 6.0; Windows 98)", 6, "IE6", null);

    /**
     * Internet Explorer 7.
     * @deprecated as of 2.12
     */
    @Deprecated
    public static final BrowserVersion INTERNET_EXPLORER_7 = new BrowserVersion(
        INTERNET_EXPLORER, "4.0 (compatible; MSIE 7.0; Windows NT 5.1)",
        "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)", 7, "IE7", null);

    /** Internet Explorer 8. */
    public static final BrowserVersion INTERNET_EXPLORER_8 = new BrowserVersion(
        INTERNET_EXPLORER, "4.0 (compatible; MSIE 8.0; Windows NT 6.0)",
        "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0)", 8, "IE8", null);

    /** Internet Explorer 9. Work In Progress!!! */
    public static final BrowserVersion INTERNET_EXPLORER_9 = new BrowserVersion(
        INTERNET_EXPLORER, "5.0 (compatible; MSIE 9.0; Windows NT 6.1)",
        "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1)", 9, "IE9", null);

    /** Internet Explorer 10. Work In Progress!!! */
    public static final BrowserVersion INTERNET_EXPLORER_10 = new BrowserVersion(
        INTERNET_EXPLORER, "5.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/6.0)",
        "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/6.0)", 10, "IE10", null);

    /**
     * Chrome 16.
     * @deprecated as of 2.12
     */
    @Deprecated
    public static final BrowserVersion CHROME_16 = new BrowserVersion(
        "Netscape", "5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/535.7"
        + " (KHTML, like Gecko) Chrome/16.0.912.63 Safari/535.7",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/535.7"
        + " (KHTML, like Gecko) Chrome/16.0.912.63 Safari/535.7",
        16, "Chrome16", null);

    /** Latest Chrome. Work In Progress!!! */
    public static final BrowserVersion CHROME = new BrowserVersion(
        "Netscape", "5.0 (Windows NT 6.1) AppleWebKit/537.36"
        + " (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36",
        "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36"
        + " (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36",
        29, "Chrome", null);

    /** The default browser version. */
    private static BrowserVersion DefaultBrowserVersion_ = INTERNET_EXPLORER_8;

    /** Register plugins for the browser versions. */
    static {
        INTERNET_EXPLORER_6.initDefaultFeatures();
        INTERNET_EXPLORER_7.initDefaultFeatures();
        INTERNET_EXPLORER_8.initDefaultFeatures();
        INTERNET_EXPLORER_9.initDefaultFeatures();
        INTERNET_EXPLORER_10.initDefaultFeatures();

        FIREFOX_3_6.initDefaultFeatures();
        FIREFOX_10.initDefaultFeatures();
        FIREFOX_17.initDefaultFeatures();

        FIREFOX_3_6.setHtmlAcceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        FIREFOX_3_6.setImgAcceptHeader("image/png,image/*;q=0.8,*/*;q=0.5");
        FIREFOX_3_6.setCssAcceptHeader("text/css,*/*;q=0.1");
        FIREFOX_10.setHtmlAcceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        FIREFOX_10.setImgAcceptHeader("image/png,image/*;q=0.8,*/*;q=0.5");
        FIREFOX_10.setCssAcceptHeader("text/css,*/*;q=0.1");
        FIREFOX_17.setHtmlAcceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        FIREFOX_17.setImgAcceptHeader("image/png,image/*;q=0.8,*/*;q=0.5");
        FIREFOX_17.setCssAcceptHeader("text/css,*/*;q=0.1");

        INTERNET_EXPLORER_8.setHtmlAcceptHeader("image/gif, image/jpeg, image/pjpeg, image/pjpeg, */*");

        final PluginConfiguration flash = new PluginConfiguration("Shockwave Flash",
            "Shockwave Flash 9.0 r31", "libflashplayer.so");
        flash.getMimeTypes().add(new PluginConfiguration.MimeType("application/x-shockwave-flash",
            "Shockwave Flash", "swf"));
        FIREFOX_3_6.getPlugins().add(flash);
        FIREFOX_10.getPlugins().add(flash);
        FIREFOX_17.getPlugins().add(flash);

        CHROME_16.initDefaultFeatures();
        CHROME_16.setApplicationCodeName("Mozilla");
        CHROME_16.setPlatform("MacIntel");
        CHROME_16.setCpuClass(null);
        CHROME_16.setBrowserLanguage("undefined");
        CHROME.initDefaultFeatures();
        CHROME.setApplicationCodeName("Mozilla");
        CHROME.setPlatform("MacIntel");
        CHROME.setCpuClass(null);
        CHROME.setBrowserLanguage("undefined");
        // there are other issues with Chrome; a different productSub, etc.
    }

    /**
     * Instantiates one.
     *
     * @param applicationName the name of the application
     * @param applicationVersion the version string of the application
     * @param userAgent the user agent string that will be sent to the server
     * @param browserVersionNumeric the floating number version of the browser
     */
    public BrowserVersion(final String applicationName, final String applicationVersion,
        final String userAgent, final float browserVersionNumeric) {

        this(applicationName, applicationVersion, userAgent,
                browserVersionNumeric, applicationName + browserVersionNumeric, null);
    }

    /**
     * Instantiates one.
     *
     * @param applicationName the name of the application
     * @param applicationVersion the version string of the application
     * @param userAgent the user agent string that will be sent to the server
     * @param browserVersionNumeric the floating number version of the browser
     * @param features the browser features
     */
    public BrowserVersion(final String applicationName, final String applicationVersion,
        final String userAgent, final float browserVersionNumeric,
        final BrowserVersionFeatures[] features) {

        this(applicationName, applicationVersion, userAgent,
                browserVersionNumeric, applicationName + browserVersionNumeric, features);
    }

    /**
     * Creates a new browser version instance.
     *
     * @param applicationName the name of the application
     * @param applicationVersion the version string of the application
     * @param userAgent the user agent string that will be sent to the server
     * @param javaScriptVersion the version of JavaScript
     * @param browserVersionNumeric the floating number version of the browser
     * @param nickname the short name of the browser (like "FF3", "IE6", ...)
     * @param features the browser features
     */
    private BrowserVersion(final String applicationName, final String applicationVersion,
        final String userAgent, final float browserVersionNumeric,
        final String nickname, final BrowserVersionFeatures[] features) {

        applicationName_ = applicationName;
        setApplicationVersion(applicationVersion);
        userAgent_ = userAgent;
        browserVersionNumeric_ = browserVersionNumeric;
        nickname_ = nickname;
        htmlAcceptHeader_ = "*/*";
        imgAcceptHeader_ = "*/*";
        cssAcceptHeader_ = "*/*";

        if (features != null) {
            features_.addAll(Arrays.asList(features));
        }
    }

    private void initDefaultFeatures() {
        final String expectedBrowserName;
        if (isIE()) {
            expectedBrowserName = "IE";
        }
        else if (isFirefox()) {
            expectedBrowserName = "FF";
        }
        else {
            expectedBrowserName = "CHROME";
        }

        for (final BrowserVersionFeatures features : BrowserVersionFeatures.values()) {
            try {
                final Field field = BrowserVersionFeatures.class.getField(features.name());
                final BrowserFeature browserFeature = field.getAnnotation(BrowserFeature.class);
                if (browserFeature != null) {
                    for (final WebBrowser browser : browserFeature.value()) {
                        if (expectedBrowserName.equals(browser.value().name())
                                && browser.minVersion() <= getBrowserVersionNumeric()
                                && browser.maxVersion() >= getBrowserVersionNumeric()) {
                            features_.add(features);
                        }
                    }
                }
            }
            catch (final NoSuchFieldException e) {
                // should never happen
                throw new IllegalStateException();
            }
        }
    }

    /**
     * Returns the default browser version that is used whenever a specific version isn't specified.
     * Defaults to {@link #INTERNET_EXPLORER_8}.
     * @return the default browser version
     */
    public static BrowserVersion getDefault() {
        return DefaultBrowserVersion_;
    }

    /**
     * Sets the default browser version that is used whenever a specific version isn't specified.
     * @param newBrowserVersion the new default browser version
     */
    public static void setDefault(final BrowserVersion newBrowserVersion) {
        WebAssert.notNull("newBrowserVersion", newBrowserVersion);
        DefaultBrowserVersion_ = newBrowserVersion;
    }

    /**
     * Returns <tt>true</tt> if this <tt>BrowserVersion</tt> instance represents some
     * version of Internet Explorer.
     * @return whether or not this version is a version of IE
     */
    public final boolean isIE() {
        return INTERNET_EXPLORER.equals(getApplicationName());
    }

    /**
     * Returns <tt>true</tt> if this <tt>BrowserVersion</tt> instance represents some
     * version of Google Chrome. Note that Google Chrome does not return 'Chrome'
     * in the application name, we have to look in the nickname.
     * @return whether or not this version is a version of a Chrome browser.
     */
    public final boolean isChrome() {
        return getNickname().startsWith("Chrome");
    }

    /**
     * Returns <tt>true</tt> if this <tt>BrowserVersion</tt> instance represents some
     * version of Firefox like {@link #FIREFOX_3_6} or {@link #FIREFOX_17}.
     * @return whether or not this version is a version of a Firefox browser
     */
    public final boolean isFirefox() {
        return !isChrome() && NETSCAPE.equals(getApplicationName());
    }

    /**
     * Returns the application code name, for example "Mozilla".
     * Default value is "Mozilla" if not explicitly configured.
     * @return the application code name
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms533077.aspx">MSDN documentation</a>
     */
    public String getApplicationCodeName() {
        return applicationCodeName_;
    }

    /**
     * Returns the application minor version, for example "0".
     * Default value is "0" if not explicitly configured.
     * @return the application minor version
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms533078.aspx">MSDN documentation</a>
     */
    public String getApplicationMinorVersion() {
        return applicationMinorVersion_;
    }

    /**
     * Returns the application name, for example "Microsoft Internet Explorer".
     * @return the application name
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms533079.aspx">MSDN documentation</a>
     */
    public String getApplicationName() {
        return applicationName_;
    }

    /**
     * Returns the application version, for example "4.0 (compatible; MSIE 6.0b; Windows 98)".
     * @return the application version
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms533080.aspx">MSDN documentation</a>
     */
    public String getApplicationVersion() {
        return applicationVersion_;
    }

    /**
     * Returns the browser application language, for example "en-us".
     * Default value is {@link #LANGUAGE_ENGLISH_US} if not explicitly configured.
     * @return the browser application language
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms533542.aspx">MSDN documentation</a>
     */
    public String getBrowserLanguage() {
        return browserLanguage_;
    }

    /**
     * Returns the type of CPU in the machine, for example "x86".
     * Default value is {@link #CPU_CLASS_X86} if not explicitly configured.
     * @return the type of CPU in the machine
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms533697.aspx">MSDN documentation</a>
     */
    public String getCpuClass() {
        return cpuClass_;
    }

    /**
     * Returns <tt>true</tt> if the browser is currently online.
     * Default value is <code>true</code> if not explicitly configured.
     * @return <tt>true</tt> if the browser is currently online
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms534307.aspx">MSDN documentation</a>
     */
    public boolean isOnLine() {
        return onLine_;
    }

    /**
     * Returns the platform on which the application is running, for example "Win32".
     * Default value is {@link #PLATFORM_WIN32} if not explicitly configured.
     * @return the platform on which the application is running
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms534340.aspx">MSDN documentation</a>
     */
    public String getPlatform() {
        return platform_;
    }

    /**
     * Returns the system language, for example "en-us".
     * Default value is {@link #LANGUAGE_ENGLISH_US} if not explicitly configured.
     * @return the system language
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms534653.aspx">MSDN documentation</a>
     */
    public String getSystemLanguage() {
        return systemLanguage_;
    }

    /**
     * Returns the user agent string, for example "Mozilla/4.0 (compatible; MSIE 6.0b; Windows 98)".
     * @return the user agent string
     */
    public String getUserAgent() {
        return userAgent_;
    }

    /**
     * Returns the user language, for example "en-us".
     * Default value is {@link #LANGUAGE_ENGLISH_US} if not explicitly configured.
     * @return the user language
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms534713.aspx">MSDN documentation</a>
     */
    public String getUserLanguage() {
        return userLanguage_;
    }

    /**
     * Returns the value used by the browser for the accept header
     * if requesting a page.
     * @return the accept header string
     */
    public String getHtmlAcceptHeader() {
        return htmlAcceptHeader_;
    }

    /**
     * Returns the value used by the browser for the accept header
     * if requesting an script.
     * @return the accept header string
     */
    public String getScriptAcceptHeader() {
        return "*/*";
    }

    /**
     * Returns the value used by the browser for the accept header
     * if requesting an image.
     * @return the accept header string
     */
    public String getImgAcceptHeader() {
        return imgAcceptHeader_;
    }

    /**
     * Returns the value used by the browser for the accept header
     * if requesting a css declaration.
     * @return the accept header string
     */
    public String getCssAcceptHeader() {
        return cssAcceptHeader_;
    }

    /**
     * @param applicationCodeName the applicationCodeName to set
     */
    public void setApplicationCodeName(final String applicationCodeName) {
        applicationCodeName_ = applicationCodeName;
    }

    /**
     * @param applicationMinorVersion the applicationMinorVersion to set
     */
    public void setApplicationMinorVersion(final String applicationMinorVersion) {
        applicationMinorVersion_ = applicationMinorVersion;
    }

    /**
     * @param applicationName the applicationName to set
     */
    public void setApplicationName(final String applicationName) {
        applicationName_ = applicationName;
    }

    /**
     * @param applicationVersion the applicationVersion to set
     */
    public void setApplicationVersion(final String applicationVersion) {
        applicationVersion_ = applicationVersion;
    }

    /**
     * @param browserLanguage the browserLanguage to set
     */
    public void setBrowserLanguage(final String browserLanguage) {
        browserLanguage_ = browserLanguage;
    }

    /**
     * @param cpuClass the cpuClass to set
     */
    public void setCpuClass(final String cpuClass) {
        cpuClass_ = cpuClass;
    }

    /**
     * @param onLine the onLine to set
     */
    public void setOnLine(final boolean onLine) {
        onLine_ = onLine;
    }

    /**
     * @param platform the platform to set
     */
    public void setPlatform(final String platform) {
        platform_ = platform;
    }

    /**
     * @param systemLanguage the systemLanguage to set
     */
    public void setSystemLanguage(final String systemLanguage) {
        systemLanguage_ = systemLanguage;
    }

    /**
     * @param userAgent the userAgent to set
     */
    public void setUserAgent(final String userAgent) {
        userAgent_ = userAgent;
    }

    /**
     * @param userLanguage the userLanguage to set
     */
    public void setUserLanguage(final String userLanguage) {
        userLanguage_ = userLanguage;
    }

    /**
     * @param browserVersion the browserVersion to set
     */
    public void setBrowserVersion(final float browserVersion) {
        browserVersionNumeric_ = browserVersion;
    }

    /**
     * @param htmlAcceptHeader the accept header to be used when retrieving pages
     */
    public void setHtmlAcceptHeader(final String htmlAcceptHeader) {
        htmlAcceptHeader_ = htmlAcceptHeader;
    }

    /**
     * @param imgAcceptHeader the accept header to be used when retrieving images
     */
    public void setImgAcceptHeader(final String imgAcceptHeader) {
        imgAcceptHeader_ = imgAcceptHeader;
    }

    /**
     * @param cssAcceptHeader the accept header to be used when retrieving pages
     */
    public void setCssAcceptHeader(final String cssAcceptHeader) {
        cssAcceptHeader_ = cssAcceptHeader;
    }

    /**
     * @return the browserVersionNumeric
     */
    public float getBrowserVersionNumeric() {
        return browserVersionNumeric_;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     * Returns the available plugins. This makes only sense for Firefox as only this
     * browser makes this kind of information available via JavaScript.
     * @return the available plugins
     */
    public Set<PluginConfiguration> getPlugins() {
        return plugins_;
    }

    /**
     * Indicates if this instance has the given feature. Used for HtmlUnit internal processing.
     * @param property the property name
     * @return <code>false</code> if this browser doesn't have this feature
     */
    public boolean hasFeature(final BrowserVersionFeatures property) {
        return features_.contains(property);
    }

    /**
     * Returns the short name of the browser like "FF3", "IE7", ...
     * This is used in different tests to reference the browser to which it applies.
     * @return the short name (if any)
     */
    public String getNickname() {
        return nickname_;
    }

    @Override
    public String toString() {
        return nickname_;
    }

    /**
     * Creates and return a copy of this object. Current instance and cloned
     * object can be modified independently.
     * @return a clone of this instance.
     */
    @Override
    public BrowserVersion clone() {
        final BrowserVersion clone = new BrowserVersion(getApplicationName(), getApplicationVersion(),
                getUserAgent(), getBrowserVersionNumeric(), getNickname(), null);

        clone.setApplicationCodeName(getApplicationCodeName());
        clone.setApplicationMinorVersion(getApplicationMinorVersion());
        clone.setBrowserLanguage(getBrowserLanguage());
        clone.setCpuClass(getCpuClass());
        clone.setOnLine(isOnLine());
        clone.setPlatform(getPlatform());
        clone.setSystemLanguage(getSystemLanguage());
        clone.setUserLanguage(getUserLanguage());

        clone.htmlAcceptHeader_ = getHtmlAcceptHeader();
        clone.imgAcceptHeader_ = getImgAcceptHeader();
        clone.cssAcceptHeader_ = getCssAcceptHeader();

        for (final PluginConfiguration pluginConf : getPlugins()) {
            clone.getPlugins().add(pluginConf.clone());
        }

        clone.features_.addAll(features_);

        return clone;
    }
}
