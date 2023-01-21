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
public class ServiceQName extends Config {

	/**
	 * -2016770234345077899L
	 */
	private static final long serialVersionUID = -2016770234345077899L;
	
	private String namespaceURI;
	private String localPart;
	private String prefix;
	/**
	 * @return the namespaceURI
	 */
	public String getNamespaceURI() {
		return namespaceURI;
	}
	/**
	 * @param namespaceURI the namespaceURI to set
	 */
	@XmlAttribute
	public void setNamespaceURI(String namespaceURI) {
		this.namespaceURI = namespaceURI;
	}
	/**
	 * @return the localPart
	 */
	public String getLocalPart() {
		return localPart;
	}
	/**
	 * @param localPart the localPart to set
	 */
	@XmlAttribute
	public void setLocalPart(String localPart) {
		this.localPart = localPart;
	}
	/**
	 * @param prefix the prefix to set
	 */
	@XmlAttribute
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}
}