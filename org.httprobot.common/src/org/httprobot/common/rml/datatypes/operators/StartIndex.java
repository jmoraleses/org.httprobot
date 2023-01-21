package org.httprobot.common.rml.datatypes.operators;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.RmlOperator;
/**
 * @author Joan
 *
 */
@XmlRootElement
public class StartIndex extends RmlOperator
{

	/**
	 * 4609647232903480519L
	 */
	private static final long serialVersionUID = 4609647232903480519L;
	private String StringValue = null;	
	private Integer IntegerValue = null;
	private Integer offset = null;
	/**
	 * StartIndex class constructor.
	 */
	public StartIndex() { }
	/**
	 * Gets the integer value.
	 * @return {@link Integer} the integer value
	 */
	public Integer getIntegerValue() {
		return IntegerValue;
	}
	/**
	 * Gets the offset
	 * @return {@link Integer} the offset
	 */
	public Integer getOffset() {
		return offset;
	}	
	/**
	 * Gets the string value
	 * @return {@link String} the string value
	 */
	public String getStringValue() {
		return StringValue;
	}
	/**
	 * Sets the integer value.
	 * @param IntegerValue {@link Integer} the integer value
	 */
	@XmlElement
	public void setIntegerValue(Integer IntegerValue) {
		this.IntegerValue = IntegerValue;
	}
	/**
	 * Sets the offset
	 * @param offset {@link Integer} the offset
	 */
	@XmlElement
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	/**
	 * Sets the string value
	 * @param StringValue {@link String} the string value
	 */
	@XmlElement
	public void setStringValue(String StringValue) {
		this.StringValue = StringValue;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
		setStringValue(((StartIndex)e.getRml()).getStringValue());
		setOffset(((StartIndex)e.getRml()).getOffset());
		setIntegerValue(((StartIndex)e.getRml()).getIntegerValue());
	}
}