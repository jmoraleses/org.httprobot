package org.httprobot.common.placeholders.operators;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.Operator;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
/**
 * @author Joan
 * Contains RML message class. Inherits {@link Rml2}.
 */
@XmlRootElement
public class Contains extends Operator
{	
	/**
	 * -5547654309978347384L
	 */
	private static final long serialVersionUID = -5547654309978347384L;
	
	private String Value = null;
	/**
	 * Contains class constructor.
	 */
	public Contains() { }

	/**
	 * Gets the value
	 * @return {@link String} the value
	 */
	public String getValue() {
		return Value;
	}

	/**
	 * Sets the value
	 * @param value {@link String} the value
	 */
	@XmlElement
	public void setValue(String value) {
		this.Value = value;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		super.OnObjectUnmarshalled(sender, e);
		
		setValue(((Contains)e.getRml()).getValue());
	}
}