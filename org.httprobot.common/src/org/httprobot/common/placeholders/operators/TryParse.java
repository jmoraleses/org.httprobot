package org.httprobot.common.placeholders.operators;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.Operator;
import org.httprobot.common.definitions.Enums.FieldType;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
/**
 * @author Joan
 * TryParse RML object class. Inherits {@link Rml2}.
 */
@XmlRootElement
public class TryParse extends Operator
{
	/**
	 * -7421636234308445795L
	 */
	private static final long serialVersionUID = -7421636234308445795L;
	
	private FieldType FieldType = null;
	private String Value = null;
	/**
	 * TryParse class constructor.
	 */
	public TryParse() { }	
	/**
	 * @return the fieldType
	 */
	public FieldType getFieldType() {
		return FieldType;
	}
	/**
	 * Gets the value.
	 * @return {@link String} the value
	 */
	public String getValue() {
		return Value;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		super.OnObjectUnmarshalled(sender, e);
		
		setValue(((TryParse)e.getRml()).getValue());
		setFieldType(((TryParse)e.getRml()).getFieldType());
	}
	/**
	 * @param fieldType the fieldType to set
	 */
	@XmlAttribute
	public void setFieldType(FieldType fieldType) {
		FieldType = fieldType;
	}
	/**
	 * Sets the value.
	 * @param Value {@link String} the value
	 */
	@XmlElement
	public void setValue(String Value) 
	{
		this.Value = Value;
	}
}