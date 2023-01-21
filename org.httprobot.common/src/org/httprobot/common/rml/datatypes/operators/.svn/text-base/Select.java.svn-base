package org.httprobot.common.rml.datatypes.operators;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.RmlOperator;

/**
 * @author Joan
 * Select RML object class. Inherits {@link Rml2}.
 */
@XmlRootElement
public class Select extends RmlOperator
{
	/**
	 * -4438249628901831227L
	 */
	private static final long serialVersionUID = -4438249628901831227L;
	
	private String tagName;
	private Condition condition;
	private Where where;
	/**
	 * Select class constructor.
	 */
	public Select() { }
	/**
	 * Gets the tag name.
	 * @return {@link String}
	 */
	public String getTagName() {
		return tagName;
	}	
	/**
	 * Sets the tag name.
	 * @param tagName {@link String} the tag name
	 */
	@XmlAttribute
	public void setTagName(String tagName)
	{
		this.tagName = tagName;
	}
	
	/**
	 * @return the condition
	 */
	public Condition getCondition()
	{
		return condition;
	}
	/**
	 * @param condition the condition to set
	 */
	@XmlElement
	public void setCondition(Condition condition) 
	{
		this.condition = condition;
	}
	/**
	 * @return the where
	 */
	public Where getWhere() 
	{
		return where;
	}
	/**
	 * @param where the where to set
	 */
	@XmlElement
	public void setWhere(Where where) 
	{
		this.where = where;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
		setTagName(((Select)e.getRml()).getTagName());
		setCondition(((Select)e.getRml()).getCondition());
		setWhere(((Select)e.getRml()).getWhere());
	}
}