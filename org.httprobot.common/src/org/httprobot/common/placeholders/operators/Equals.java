package org.httprobot.common.placeholders.operators;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.Operator;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
/**
 * {@link Equals} message manager class. Inherits {@link Operator}.
 * <br>
 * @author Joan
 * 
 */
@XmlRootElement
public class Equals extends Operator
{	
	/**
	 * 8409401054765182476L
	 */
	private static final long serialVersionUID = 8409401054765182476L;
	
	private String Value = null;
	/**
	 * Equals class constructor.
	 */
	public Equals()
	{
		super();
	}
	/**
	 * Gets the value.
	 * @return {@link String} the value
	 */
	public String getValue() {
		return Value;
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
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		super.OnObjectUnmarshalled(sender, e);
		
		setValue(((Equals)e.getRml()).getValue());
	}
}