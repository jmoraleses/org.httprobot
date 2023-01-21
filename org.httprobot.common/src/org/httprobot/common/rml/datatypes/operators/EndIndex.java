package org.httprobot.common.rml.datatypes.operators;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.RmlOperator;

/**
 * @author Joan
 * EndIndex RML object class. Inherits {@link Rml2}.
 */
@XmlRootElement
public class EndIndex extends RmlOperator
{

	/**
	 * 3184342089701762557L
	 */
	private static final long serialVersionUID = 3184342089701762557L;
	
	private String stringValue = null;	
	private Integer integerValue = null;
	private Integer offset = null;
	/**
	 * EndIndex class constructor.
	 */
	public EndIndex(){ }	
	/**
	 * Gets the {@link Integer} value.
	 * @return {@link Integer} the value
	 */
	public Integer getIntegerValue() {
		return integerValue;
	}
	/**
	 * Gets the offset.
	 * @return
	 */
	public Integer getOffset() {
		return offset;
	}
	/**
	 * Gets the string value.
	 * @return {@link String} the value
	 */
	public String getStringValue() {
		return stringValue;
	}
	/**
	 * Sets the integer value.
	 * @param integerValue
	 */
	@XmlElement
	public void setIntegerValue(Integer integerValue) {
		this.integerValue = integerValue;
	}
	/**
	 * Sets the offset value.
	 * @param offset {@link Integer} the value
	 */
	@XmlElement
	public void setOffset(Integer offset) {
		this.offset = offset;
	}	
	/**
	 * Sets the String value.
	 * @param stringValue
	 */
	@XmlElement
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
		setOffset(((EndIndex)e.getRml()).getOffset());
		setStringValue(((EndIndex)e.getRml()).getStringValue());
		setIntegerValue(((EndIndex)e.getRml()).getIntegerValue());
	}
}