package org.httprobot.common.rml.datatypes.operators;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.*;
/**
 * @author Joan
 * TryParse RML object class. Inherits {@link Rml2}.
 */
@XmlRootElement
public class TryParse extends RmlOperator
{
	/**
	 * -7421636234308445795L
	 */
	private static final long serialVersionUID = -7421636234308445795L;
	
	private String Value = null;
	private GetField GetField = null;
	/**
	 * TryParse class constructor.
	 */
	public TryParse() { }	
	/**
	 * Gets the get field.
	 * @return {@link GetField} the get field
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
	 * Sets the get field.
	 * @param GetField
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
		
	}
}