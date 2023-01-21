/**
 * 
 */
package org.httprobot.client.inet.proxy.config;

import org.httprobot.client.inet.proxy.dataview.ProxyDataView;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.config.Config;
import org.httprobot.core.rml.controls.config.ConfigControl;

/**
 * Proxy Requester configuration file. Inherits {@link ConfigControl}.
 * It gets a list of rows of proxy servers.
 * @author Joan
 *
 */
public final class ProxyConfig extends ConfigControl 
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
	 * @param parent {@link IRmlListener} the parent
	 * @param config {@link Config} the configuration file to get the proxies
	 */
	public ProxyConfig(IRmlListener parent, Config config) {
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