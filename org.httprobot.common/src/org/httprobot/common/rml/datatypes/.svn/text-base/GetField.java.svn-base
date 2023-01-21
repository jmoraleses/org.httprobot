package org.httprobot.common.rml.datatypes;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * @author Joan
 * GetField RML object class. Inherits {@link Rml}.
 */
@XmlRootElement
public class GetField extends Rml
{
	/**
	 * -5904846724787985811L
	 */
	private static final long serialVersionUID = -5904846724787985811L;
	
	private String FieldName = null;
	private String Value = null;
	/**
	 * Sets the value
	 * @param Value {@link String} the value
	 */
	@XmlElement
	public void setValue(String Value) {
		this.Value = Value;
	}
	/**
	 * Gets the value
	 * @return Valor {@link String} the value
	 */
	public String getValue() {
		return Value;
	}	
	/**
	 * Gets the field name
	 * @return {@link String} the field name
	 */
	public String getFieldName() {
		return FieldName;
	}
	/**
	 * Sets the field name
	 * @param FieldName {@link String} the field name
	 */
	@XmlElement
	public void setFieldName(String FieldName) {
		this.FieldName = FieldName;
	}
	/**
	 * GetField class constructor.
	 */
	public GetField()
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
		setFieldName(((GetField)e.getRml()).getFieldName());
		setValue(((GetField)e.getRml()).getValue());
	}
}