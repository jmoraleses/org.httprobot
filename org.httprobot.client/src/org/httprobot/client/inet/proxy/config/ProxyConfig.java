/**
 * 
 */
package org.httprobot.client.inet.proxy.config;

import org.httprobot.client.inet.proxy.dataview.ProxyDataView;
import org.httprobot.common.config.Configuration;
import org.httprobot.core.controls.config.ConfigurationControl;
import org.httprobot.core.controls.config.interfaces.IControlConfigurationListener;

/**
 * Proxy Requester configuration file. Inherits {@link ConfigurationControl}.
 * It gets a list of rows of proxy servers.
 * @author Joan
 *
 */
public final class ProxyConfig extends ConfigurationControl 
{
	/**
	 * 2305234054336591695L
	 */
	private static final long serialVersionUID = 2305234054336591695L;
	
	private ProxyDataView proxyList = null;
	
	/**
	 * ProxyGetter default class constructor.
	 */
	public ProxyConfig() 
	{
	}
	/**
	 * ProxyGetter class constructor.
	 * @param parent {@link IControlConfigurationListener} the parent
	 * @param config {@link Configuration} the configuration file to get the proxy's
	 */
	public ProxyConfig(IControlConfigurationListener parent, Configuration config) {
		super(parent, config);
	}
	/**
	 * Sets the proxies list.
	 * @param proxyList the proxyList to set
	 */
	public void setProxyList(ProxyDataView proxyList) {
		this.proxyList = proxyList;
	}
	/**
	 * Gets the proxies list.
	 * @return the proxyList {@link ProxyDataView} the proxies list
	 */
	public ProxyDataView getProxyList() {
		return proxyList;
	}
}