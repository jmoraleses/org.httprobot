/**
 * 
 */
package org.httprobot.common.config;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.Config;

/**
 * @author joan
 *
 */
@XmlRootElement
public class ServiceUrl extends Config 
{
	/**
	 * 518485001564598070L
	 */
	private static final long serialVersionUID = 518485001564598070L;

	private String url;
	private String protocol;
	private String host;
	private Integer port;
	private String file;
	
	/**
	 * @param url the URL to set
	 */
	@XmlAttribute
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @return the protocol
	 */
	public String getProtocol() {
		return protocol;
	}
	/**
	 * @param protocol the protocol to set
	 */
	@XmlAttribute
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}
	/**
	 * @param host the host to set
	 */
	@XmlAttribute
	public void setHost(String host) {
		this.host = host;
	}
	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}
	/**
	 * @param port the port to set
	 */
	@XmlAttribute
	public void setPort(Integer port) {
		this.port = port;
	}
	/**
	 * @return the file
	 */
	public String getFile() {
		return file;
	}
	/**
	 * @param file the file to set
	 */
	@XmlAttribute
	public void setFile(String file) {
		this.file = file;
	}
}