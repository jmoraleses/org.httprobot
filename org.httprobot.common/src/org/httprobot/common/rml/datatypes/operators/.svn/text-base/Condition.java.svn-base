package org.httprobot.common.rml.datatypes.operators;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.Rml;
import org.httprobot.common.rml.RmlOperator;

/**
 * Condition RML message class. Inherits {@link Rml}.
 * @author Joan
 *
 */
@XmlRootElement
public class Condition extends RmlOperator
{
	/**
	 * -5544782863001938907L
	 */
	private static final long serialVersionUID = -5544782863001938907L;
	
	private String FieldNameRef;
	private String FieldValue;
	private String ConditionType;	
	/**
	 * Gets the condition type.
	 * @return
	 */
	public String getConditionType() {
		return ConditionType;
	}
	/**
	 * Gets the condition type.
	 * @return
	 */
	public String getFieldValue() {
		return this.FieldValue;
	}	
	/**
	 * @return
	 */
	public String getFieldNameRef() {
		return FieldNameRef;
	}
	/**
	 * @param ConditionType
	 */
	@XmlAttribute
	public void setConditionType(String ConditionType) {
		this.ConditionType = ConditionType;
	}
	/**
	 * @param FieldValue
	 */
	@XmlElement
	public void setFiedValue(String FieldValue) {
		this.FieldValue = FieldValue;
	}
	/**
	 * @param FieldNameRef
	 */
	@XmlAttribute
	public void setFieldNameRef(String FieldNameRef) {
		this.FieldNameRef = FieldNameRef;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
		setConditionType(((Condition)e.getRml()).getConditionType());
		setFiedValue(((Condition)e.getRml()).getFieldValue());
		setFieldNameRef(((Condition)e.getRml()).getFieldNameRef());
	}
}
