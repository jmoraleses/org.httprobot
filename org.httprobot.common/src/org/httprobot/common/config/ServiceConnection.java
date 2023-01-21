/**
 * 
 */
package org.httprobot.common.config;

import java.net.URL;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

import org.httprobot.common.Config;
import org.httprobot.common.io.adapters.ServiceQNameAdapter;
import org.httprobot.common.io.adapters.ServiceUrlAdapter;

/**
 * @author joan
 *
 */
@XmlRootElement
public class ServiceConnection extends Config {

	/**
	 * 7580049031180635495L
	 */
	private static final long serialVersionUID = 7580049031180635495L;

	private QName qName;
	private URL url;
	/**
	 * 
	 */
	public ServiceConnection() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the qName
	 */
	@XmlJavaTypeAdapter(value = ServiceQNameAdapter.class)
	public QName getQName() {
		return qName;
	}
	/**
	 * @param qName the qName to set
	 */
	public void setQName(QName qName) {
		this.qName = qName;
	}
	/**
	 * @return the url
	 */
	@XmlJavaTypeAdapter(value = ServiceUrlAdapter.class)
	public URL getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(URL url) {
		this.url = url;
	}

	
}