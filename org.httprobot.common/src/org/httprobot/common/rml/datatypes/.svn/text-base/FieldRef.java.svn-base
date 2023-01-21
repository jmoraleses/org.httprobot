package org.httprobot.common.rml.datatypes;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.definitions.Enums.RmlDataType;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.Rml;

/**
 * FieldRef RML object class. Inherits {@link Rml}.
 * @author Joan
 * 
 */
@XmlRootElement
public class FieldRef extends Rml
{
	/**
	 * 5672797536641842105L
	 */
	private static final long serialVersionUID = 5672797536641842105L;
	
	private String FieldName;
	private RmlDataType FieldType;
	/**
	 * Gets the field name
	 * @return {@link String}the field name
	 */
	public String getFieldName() {
		return FieldName;
	}
	/**
	 * Gets the field type
	 * @return {@link RmlDataType} the type of data
	 */
	public RmlDataType getFieldType() {
		return FieldType;
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
	 * Sets the field type
	 * @param FieldType {@link RmlDataType} the type
	 */
	@XmlAttribute
	public void setFieldType(RmlDataType FieldType) {
		this.FieldType = FieldType;
	}	
	

	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
		this.setFieldName(((FieldRef)e.getRml()).getFieldName());
		this.setFieldType(((FieldRef)e.getRml()).getFieldType());
	}
}