/**
 * 
 */
package org.httprobot.common.unit;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.Unit;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;

/**
 * @author joan
 *
 */
@XmlRootElement
public class Paginator extends Unit 
{
	/**
	 * 1453000527251479335L
	 */
	private static final long serialVersionUID = 1453000527251479335L;

	private String anchorHrefAttributeValue = null;
	private String anchorValue = null;
	private Integer paginatorIncrement = 1;
	private String paginatorUrlPattern = null;	
	
	/**
	 * @return the anchorHrefAttributeValue
	 */
	public String getAnchorHrefAttributeValue() 
	{
		return anchorHrefAttributeValue;
	}
	/**
	 * @return the anchorValue
	 */
	public String getAnchorValue()
	{
		return anchorValue;
	}
	/**
	 * @return the paginatorIncrement
	 */
	public Integer getPaginatorIncrement() 
	{
		return paginatorIncrement;
	}
	/**
	 * @return the paginatorPattern
	 */
	public String getPaginatorPattern()
	{
		return paginatorUrlPattern;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.RML#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		super.OnObjectUnmarshalled(sender, e);
		this.setAnchorHrefAttributeValue(((Paginator)e.getRml()).getAnchorHrefAttributeValue());
		this.setAnchorValue(((Paginator)e.getRml()).getAnchorValue());
		this.setPaginatorIncrement(((Paginator)e.getRml()).getPaginatorIncrement());
		this.setPaginatorPattern(((Paginator)e.getRml()).getPaginatorPattern());
	}
	/**
	 * @param anchorHrefAttributeValue the anchorHrefAttributeValue to set
	 */
	@XmlElement
	public void setAnchorHrefAttributeValue(String anchorHrefAttributeValue) 
	{
		this.anchorHrefAttributeValue = anchorHrefAttributeValue;
	}
	/**
	 * @param anchorValue the anchorValue to set
	 */
	@XmlElement
	public void setAnchorValue(String anchorValue)
	{
		this.anchorValue = anchorValue;
	}
	/**
	 * @param paginatorIncrement the paginatorIncrement to set
	 */
	@XmlElement
	public void setPaginatorIncrement(Integer paginatorIncrement) 
	{
		this.paginatorIncrement = paginatorIncrement;
	}
	/**
	 * @param paginatorPattern the paginatorPattern to set
	 */
	@XmlElement
	public void setPaginatorPattern(String paginatorPattern) 
	{
		this.paginatorUrlPattern = paginatorPattern;
	}
}