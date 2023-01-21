package org.httprobot.common.events;

import org.httprobot.common.definitions.Enums.EventType;
import org.httprobot.common.definitions.Enums.RmlEventType;
import org.httprobot.common.rml.Rml;

/**
 * @author Joan
 * RML control event arguments class. Inherits {@link EventArgs}.
 */
public class RmlEventArgs extends EventArgs 
{
	/**
	 * 7149902161976610833L
	 */
	private static final long serialVersionUID = 7149902161976610833L;
	Rml message;	
	private RmlEventType ret;
	
	/**
	 * Gets the message.
	 * @return {@link Rml}
	 */
	public Rml getMessage() {
		return message;
	}
	/**
	 * @return ConfigEventType
	 */
	public RmlEventType getRmlEventType() {
		return ret;
	}
	/**
	 * Gets the {@link Class} of {@link Rml2}.
	 * @return {@link Class} the class
	 */
	public Class<? extends Rml> getMessageClass()
	{
		return message.getClass();
	}	
	/**
	 * RmlControlEventArgs class constructor.
	 * @param value sender
	 * @param message {@link Rml2}
	 */
	public RmlEventArgs(Object value, RmlEventType cet, Rml message) 
	{
		super(value, EventType.RML);
		this.ret = cet;
		this.message = message;
	}
}
