/**
 * 
 */
package org.httprobot.common.io.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.httprobot.common.definitions.Enums.UnitBrowserVersion;

import com.gargoylesoftware.htmlunit.BrowserVersion;

/**
 * @author joan
 *
 */
public class BrowserVersionAdapter 
		extends XmlAdapter<UnitBrowserVersion, BrowserVersion> {

	/**
	 * 
	 */
	public BrowserVersionAdapter() 
	{
		
	}
	/* (non-Javadoc)
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.Object)
	 */
	@Override
	public BrowserVersion unmarshal(UnitBrowserVersion v) throws Exception 
	{
		switch (v) 
		{
			case FIREFOX_17:
				return BrowserVersion.FIREFOX_17;
			case DEFAULT:
				return BrowserVersion.FIREFOX_17;
			default:
				return BrowserVersion.getDefault();
		}
	}
	/* (non-Javadoc)
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
	 */
	@Override
	public UnitBrowserVersion marshal(BrowserVersion v) throws Exception 
	{		 	
		if(v == BrowserVersion.FIREFOX_17)
		{
			return UnitBrowserVersion.FIREFOX_17;
		}
		else
		{
			return UnitBrowserVersion.DEFAULT;
		}
	}
}
