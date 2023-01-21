/**
 * 
 */
package org.httprobot.common.io;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.httprobot.common.definitions.Enums.WebBrowserVersion;

import com.gargoylesoftware.htmlunit.BrowserVersion;

/**
 * @author joan
 *
 */
public class BrowserVersionAdapter extends XmlAdapter<WebBrowserVersion, BrowserVersion> {

	/**
	 * 
	 */
	public BrowserVersionAdapter() {
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.Object)
	 */
	@Override
	public BrowserVersion unmarshal(WebBrowserVersion v) throws Exception 
	{
		switch (v) 
		{
			case FIREFOX_17:
				return BrowserVersion.FIREFOX_17;
	
			default:
				return BrowserVersion.getDefault();
		}
	}
	/* (non-Javadoc)
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
	 */
	@Override
	public WebBrowserVersion marshal(BrowserVersion v) throws Exception 
	{		 	
		if(v == BrowserVersion.FIREFOX_17)
		{
			return WebBrowserVersion.FIREFOX_17;
		}
		else
		{
			return WebBrowserVersion.DEFAULT;
		}
	}
}
