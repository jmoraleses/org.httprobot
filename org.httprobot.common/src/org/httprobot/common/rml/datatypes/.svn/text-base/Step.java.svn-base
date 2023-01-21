package org.httprobot.common.rml.datatypes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.Rml;

/**
 * Step RML message class. Inherits {@link Rml}.
 * @author Joan
 *
 */
@XmlRootElement
public class Step extends Rml 
{
	/**
	 * -8153155320824643209L
	 */
	private static final long serialVersionUID = -8153155320824643209L;
	private Action Action = null;
	private Fields Fields = null;
	private Step Step = null;
	/**
	 * Step class constructor.
	 */
	public Step() { }
	/**
	 * Get the action
	 * @return {@link String} the action
	 */
	public Action getAction() {
		return Action;
	}
	/**
	 * Gets the fields.
	 * @return {@link Fields} the fields
	 */
	public Fields getFields() {
		return Fields;
	}
	/**
	 * Gets the next step.
	 * @return {@link Step} the step
	 */
	public Step getStep() 
	{
		return Step;
	}
	/**
	 * Sets the action
	 * @param Action {@link Action} the action
	 */
	@XmlElement
	public void setAction(Action Action) {
		this.Action = Action;
	}
	/**
	 * Sets the fields
	 * @param Fields {@link Fields} the fields
	 */
	@XmlElement
	public void setFields(Fields Fields) {
		this.Fields = Fields;
	}
	/**
	 * @param Step
	 */
	@XmlElement
	public void setStep(Step Step) {
		this.Step = Step;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
}