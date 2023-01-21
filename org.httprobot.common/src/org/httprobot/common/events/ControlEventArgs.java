package org.httprobot.common.events;

import org.httprobot.common.RML;
import org.httprobot.common.definitions.Enums.EventType;
import org.httprobot.common.definitions.Enums.ControlEventType;

/**
 * RML control event arguments class. Inherits {@link EventArgs}.
 * <br>
 * @author Joan
 */
public class ControlEventArgs extends EventArgs 
{
	/**
	 * 7149902161976610833L
	 */
	private static final long serialVersionUID = 7149902161976610833L;
	
	RML message;	
	private ControlEventType controlEventType;
	
	/**
	 * Gets the message.
	 * @return {@link RML}
	 */
	public RML getMessage() {
		return message;
	}
	/**
	 * @return ConfigEventType
	 */
	public ControlEventType getControlEventType() {
		return controlEventType;
	}
	/**
	 * Gets the {@link Class} of {@link Rml2}.
	 * @return {@link Class} the class
	 */
	public Class<? extends RML> getMessageClass()
	{
		return message.getClass();
	}	
	/**
	 * RmlControlEventArgs class constructor.
	 * @param value sender
	 * @param message {@link Rml2}
	 */
	public ControlEventArgs(Object value, ControlEventType cet, RML message) 
	{
		super(value, EventType.RML);
		this.controlEventType = cet;
		this.message = message;
	}
}
