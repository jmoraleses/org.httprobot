/**
 * 
 */
package org.httprobot.common.managers.action;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.RML;

/**
 * Action message manager output data row. Inherits {@link RML}.
 * @author joan
 *
 */
@XmlRootElement
public class ActionOutputDataRow extends RML {

	/**
	 * 5048697099382408261L
	 */
	private static final long serialVersionUID = 5048697099382408261L;
	
	private String sourceHttpAddress;
	private String sourceHtmlTitle;
	private ArrayList<ResultHtmlPage> resultHtmlPages;
	
	/**
	 * 
	 */
	public ActionOutputDataRow() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the sourceHttpAddress
	 */
	public String getSourceHttpAddress() {
		return sourceHttpAddress;
	}

	/**
	 * @param sourceHttpAddress the sourceHttpAddress to set
	 */
	@XmlAttribute
	public void setSourceHttpAddress(String sourceHttpAddress) {
		this.sourceHttpAddress = sourceHttpAddress;
	}

	/**
	 * @return the sourceHtmlTitle
	 */
	public String getSourceHtmlTitle() {
		return sourceHtmlTitle;
	}

	/**
	 * @param sourceHtmlTitle the sourceHtmlTitle to set
	 */
	@XmlAttribute
	public void setSourceHtmlTitle(String sourceHtmlTitle) {
		this.sourceHtmlTitle = sourceHtmlTitle;
	}

	/**
	 * @return the resultHtmlPages
	 */
	public ArrayList<ResultHtmlPage> getResultHtmlPages() 
	{
		if(resultHtmlPages != null)
		{
			return resultHtmlPages;
		}
		else
		{
			resultHtmlPages = new ArrayList<ResultHtmlPage>();
			return resultHtmlPages;
		}
	}

	/**
	 * @param resultHtmlPages the resultHtmlPages to set
	 */
	@XmlElement
	public void setResultHtmlPages(ArrayList<ResultHtmlPage> resultHtmlPages) {
		this.resultHtmlPages = resultHtmlPages;
	}

}
