/**
 * 
 */
package org.httprobot.common.placeholders.operators.html;

import javax.xml.bind.annotation.XmlAttribute;

import org.httprobot.common.Html;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;

/**
 * @author joan
 *
 */
public class Element extends Html {

	/**
	 * 5065898100265476033L
	 */
	private static final long serialVersionUID = 5065898100265476033L;
	
	private String XPath;
	private String nodeName;
	private String tagName;
	private String id;
	
	/**
	 * @param xPath the XPath expression
	 */
	@XmlAttribute
	public void setXPath(String xPath)
	{
		this.XPath = xPath;
	}
	/**
	 * @return the XPath expression
	 */
	public String getXPath() 
	{
		return XPath;
	}
	
	/**
	 * @return the nodeName
	 */
	public String getNodeName() {
		return nodeName;
	}
	/**
	 * @param nodeName the nodeName to set
	 */
	@XmlAttribute
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	/**
	 * @return the tagName
	 */
	public String getTagName() {
		return tagName;
	}
	/**
	 * @param tagName the tagName to set
	 */
	@XmlAttribute
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 
	 */
	public Element() 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.Placeholder#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		super.OnObjectUnmarshalled(sender, e);
		setXPath(((Element)e.getRml()).getXPath());
		setTagName(((Element)e.getRml()).getTagName());
		setNodeName(((Element)e.getRml()).getNodeName());
		setId(((Element)e.getRml()).getId());
	}
}