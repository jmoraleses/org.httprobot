/**
 * 
 */
package org.httprobot.common.io.adapters;

import java.net.URL;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.httprobot.common.config.ServiceUrl;

/**
 * @author joan
 *
 */
public class ServiceUrlAdapter extends XmlAdapter<ServiceUrl, URL> {

	/**
	 * 
	 */
	public ServiceUrlAdapter() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.Object)
	 */
	@Override
	public URL unmarshal(ServiceUrl v) throws Exception
	{		
		if(v.getProtocol() != null && v.getHost() != null && v.getPort() != null && v.getFile() != null)
		{	
			return new URL(v.getProtocol(), v.getHost(), v.getPort(), v.getFile());
		}
		else if(v.getProtocol() != null && v.getHost() != null && v.getFile() != null)
		{	
			return new URL(v.getProtocol(), v.getHost(), v.getFile());
		}
		else if(v.getUrl() != null)
		{
			return new URL(v.getUrl());
		}
		else
		{
			throw new Exception();
		}		
	}

	/* (non-Javadoc)
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
	 */
	@Override
	public ServiceUrl marshal(URL v) throws Exception {
		ServiceUrl serviceUrl = new ServiceUrl();
		serviceUrl.setUrl(v.toString());
		serviceUrl.setFile(v.getFile());
		serviceUrl.setHost(v.getHost());
		serviceUrl.setPort(v.getPort());
		serviceUrl.setProtocol(v.getProtocol());
		return serviceUrl;
	}
}