/**
 * 
 */
package org.httprobot.common.rml.datatypes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.io.BrowserVersionAdapter;
import org.httprobot.common.io.RuntimeOptionsAdapter;
import org.httprobot.common.rml.Rml;

import com.gargoylesoftware.htmlunit.BrowserVersion;

/**
 * @author joan
 *
 */
public class WebOptions extends Rml
{
	private Boolean ActiveXNativeEnabled = null;
	private Boolean AppletEnabled = null;
	private BrowserVersion browserVersion = null;
	private Boolean CssEnabled = null;
	private Boolean GeoLocationEnabled = null;
	private Boolean javaScriptEnabled = null;
	private Integer periodTime = null;
	private Boolean PopupBlockerEnabled = null;
	private Boolean RedirectEnabled = null;
	private Integer timeout = null;
	private Boolean useProxyEnabled = null;
	/**
	 * @return the activeXNativeEnabled
	 */
	public Boolean getActiveXNativeEnabled() {
		return ActiveXNativeEnabled;
	}
	/**
	 * @return the appletEnabled
	 */
	public Boolean getAppletEnabled()
	{
		return AppletEnabled;
	}
	/**
	 * @return the HtmlUnit browser version
	 */
	@XmlJavaTypeAdapter(value = BrowserVersionAdapter.class)
	public BrowserVersion getBrowserVersion() 
	{
		return browserVersion;
	}
	/**
	 * @return the cssEnabled
	 */
	public Boolean getCssEnabled()
	{
		return CssEnabled;
	}
	/**
	 * @return the geoLocationEnabled
	 */
	public Boolean getGeoLocationEnabled()
	{
		return GeoLocationEnabled;
	}
	/**
	 * @return the javaScriptEnabled
	 */
	public Boolean getJavaScriptEnabled()
	{
		return javaScriptEnabled;
	}
	/**
	 * @return the periodTime
	 */
	public Integer getPeriodTime() 
	{
		return periodTime;
	}
	/**
	 * @return the popupBlockerEnabled
	 */
	public Boolean getPopupBlockerEnabled()
	{
		return PopupBlockerEnabled;
	}
	/**
	 * @return the redirectEnabled
	 */
	public Boolean getRedirectEnabled() 
	{
		return RedirectEnabled;
	}
	/**
	 * @return the timeout
	 */
	public Integer getTimeout() 
	{
		return timeout;
	}
	/**
	 * @param activeXNativeEnabled the activeXNativeEnabled to set
	 */
	@XmlElement
	public void setActiveXNativeEnabled(Boolean activeXNativeEnabled) 
	{
		ActiveXNativeEnabled = activeXNativeEnabled;
	}
	/**
	 * @param appletEnabled the appletEnabled to set
	 */
	@XmlElement
	public void setAppletEnabled(Boolean appletEnabled)
	{
		AppletEnabled = appletEnabled;
	}
	/**
	 * @param browserVersion
	 */
	public void setBrowserVersion(BrowserVersion browserVersion)
	{
		this.browserVersion = browserVersion;
	}
	/**
	 * @param cssEnabled the cssEnabled to set
	 */
	@XmlElement
	public void setCssEnabled(Boolean cssEnabled)
	{
		CssEnabled = cssEnabled;
	}
	/**
	 * @param geoLocationEnabled the geoLocationEnabled to set
	 */
	@XmlElement
	public void setGeoLocationEnabled(Boolean geoLocationEnabled)
	{
		GeoLocationEnabled = geoLocationEnabled;
	}
	/**
	 * @param javaScriptEnabled the javaScriptEnabled to set
	 */
	@XmlElement
	public void setJavaScriptEnabled(Boolean javaScriptEnabled)
	{
		this.javaScriptEnabled = javaScriptEnabled;
	}
	/**
	 * @param periodTime the periodTime to set
	 */
	@XmlElement
	public void setPeriodTime(Integer periodTime)
	{
		this.periodTime = periodTime;
	}
	/**
	 * @param popupBlockerEnabled the popupBlockerEnabled to set
	 */
	@XmlElement
	public void setPopupBlockerEnabled(Boolean popupBlockerEnabled)
	{
		PopupBlockerEnabled = popupBlockerEnabled;
	}
	/**
	 * @param redirectEnabled the redirectEnabled to set
	 */
	@XmlElement
	public void setRedirectEnabled(Boolean redirectEnabled) 
	{
		RedirectEnabled = redirectEnabled;
	}
	/**
	 * @param timeout the timeout to set
	 */
	@XmlElement
	public void setTimeout(Integer timeout)
	{
		this.timeout = timeout;
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
		this.setActiveXNativeEnabled(((WebOptions)e.getRml()).getActiveXNativeEnabled());
		this.setAppletEnabled(((WebOptions)e.getRml()).getAppletEnabled());
		this.setBrowserVersion(((WebOptions)e.getRml()).getBrowserVersion());
		this.setCssEnabled(((WebOptions)e.getRml()).getCssEnabled());
		this.setGeoLocationEnabled(((WebOptions)e.getRml()).getGeoLocationEnabled());
		this.setJavaScriptEnabled(((WebOptions)e.getRml()).getJavaScriptEnabled());
		this.setPeriodTime(((WebOptions)e.getRml()).getPeriodTime());
		this.setPopupBlockerEnabled(((WebOptions)e.getRml()).getPopupBlockerEnabled());
		this.setRedirectEnabled(((WebOptions)e.getRml()).getRedirectEnabled());
		this.setTimeout(((WebOptions)e.getRml()).getTimeout());
	}
	/**
	 * @param useProxyEnabled
	 */
	@XmlElement
	public void setUseProxyEnabled(Boolean useProxyEnabled) 
	{
		this.useProxyEnabled = useProxyEnabled;
	}
	/**
	 * @return
	 */
	public Boolean getUseProxyEnabled() {
		return useProxyEnabled;
	}
}