package org.httprobot.common.rml.datatypes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.Rml;
/**
 * @author Joan
 * Field RML object class. Inherits {@link Rml}.
 */
@XmlRootElement
public class Field extends Rml{
	
	/**
	 * -3294678169860459611L
	 */
	private static final long serialVersionUID = -3294678169860459611L;
	private String FieldName = null;
	private String FieldType = null;
	private Rules Rules = null;

	/**
	 * Field class constructor.
	 */
	public Field()
	{
	}
	/**
	 * Gets the field name
	 * @return {@link String} the name
	 */
	public String getFieldName() {
		return FieldName;
	}
	/**
	 * Gets the FieldType
	 * @return {@link String} the type
	 */
	public String getFieldType() {
		return FieldType;
	}
	/**
	 * Gets the rules
	 * @return {@link Rules} the rules
	 */
	public Rules getRules() {
		return Rules;
	}	
	/**
	 * Sets the field name
	 * @param fieldName {@link String} the field name
	 */
	@XmlElement
	public void setFieldName(String fieldName) {
		this.FieldName = fieldName;
	}
	/**
	 * Sets the field type
	 * @param FieldType {@link String} the field type
	 */
	@XmlElement
	public void setFieldType(String FieldType) {
		this.FieldType = FieldType;
	}	
	/**
	 * Sets the rules
	 * @param Rules {@link Rules} the rules
	 */
	@XmlElement
	public void setRules(Rules Rules) {
		this.Rules = Rules;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
		this.setFieldName(((Field)e.getRml()).getFieldName());
		this.setFieldType(((Field)e.getRml()).getFieldType());
		this.setRules(((Field)e.getRml()).getRules());
	}
	

}