/**
 * 
 */
package org.httprobot.common.managers.action;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.RML;

/**
 * @author joan
 *
 */
@XmlRootElement
public class ResultHtmlPage extends RML {

	/**
	 * 559995738157964093L
	 */
	private static final long serialVersionUID = 559995738157964093L;
	private String httpAddress = null;
	private String htmlTitle = null;
	private String htmlTextContent = null;
	
	/**
	 * 
	 */
	public ResultHtmlPage() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the httpAddress
	 */
	public String getHttpAddress() {
		return httpAddress;
	}
	/**
	 * @param httpAddress the httpAddress to set
	 */
	@XmlAttribute
	public void setHttpAddress(String httpAddress) {
		this.httpAddress = httpAddress;
	}
	/**
	 * @return the htmlTitle
	 */
	public String getHtmlTitle() {
		return htmlTitle;
	}
	/**
	 * @param htmlTitle the htmlTitle to set
	 */
	@XmlAttribute
	public void setHtmlTitle(String htmlTitle) {
		this.htmlTitle = htmlTitle;
	}
	/**
	 * @return the htmlTextContent
	 */
	public String getHtmlTextContent() {
		return htmlTextContent;
	}
	/**
	 * @param htmlTextContent the htmlTextContent to set
	 */
	@XmlElement
	public void setHtmlTextContent(String htmlTextContent) {
		this.htmlTextContent = htmlTextContent;
	}
}