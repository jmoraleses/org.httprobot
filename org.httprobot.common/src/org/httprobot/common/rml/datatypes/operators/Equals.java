package org.httprobot.common.rml.datatypes.operators;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.RmlOperator;
/**
 * Equals RML object class. Inherits {@link Rml}.
 * @author Joan
 * 
 */
@XmlRootElement
public class Equals extends RmlOperator
{	
	/**
	 * 8409401054765182476L
	 */
	private static final long serialVersionUID = 8409401054765182476L;
	
	private String Value = null;
	private GetField GetField = null;
	/**
	 * Equals class constructor.
	 */
	public Equals(){ }
	/**
	 * Gets the GetField value.
	 * @return {@link GetField}
	 */
	public GetField getGetField() {
		return GetField;
	}
	/**
	 * Gets the value.
	 * @return {@link String} the value
	 */
	public String getValue() {
		return Value;
	}
	/**
	 * Sets the GetField value.
	 * @param GetField {@link GetField}
	 */
	public void setGetField(GetField GetField) {
		this.GetField = GetField;
	}
	/**
	 * Sets the value.
	 * @param Value {@link String} the value
	 */
	@XmlElement
	public void setValue(String Value) {
		this.Value = Value;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
		setValue(((Equals)e.getRml()).getValue());
		setGetField(((Equals)e.getRml()).getGetField());
	}
}