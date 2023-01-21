/**
 * 
 */
package org.httprobot.common.managers.action;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.RML;

/**
 * Action message manager input data row. Inherits {@link RML}
 * @author joan
 *
 */
@XmlRootElement
public class ActionInputDataRow extends RML {

	/**
	 * 3594398921833631414L
	 */
	private static final long serialVersionUID = 3594398921833631414L;

	private String httpAddress = null;
	private String htmlTitle = null;
	private String htmlTextContent = null;
	/**
	 * 
	 */
	public ActionInputDataRow() {
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
	 * @param htmlTextContent the htmlTextContent to set
	 */
	@XmlElement
	public void setHtmlTextContent(String htmlTextContent) {
		this.htmlTextContent = htmlTextContent;
	}

	/**
	 * @return the htmlTextContent
	 */
	public String getHtmlTextContent() {
		return htmlTextContent;
	}
}
