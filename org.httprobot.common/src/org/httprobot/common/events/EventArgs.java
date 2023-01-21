package org.httprobot.common.events;

import java.util.EventObject;

import org.httprobot.common.definitions.Enums.EventType;

/**
 * @author Joan
 * Event arguments. Inherits {@link EventObject}.
 */
public class EventArgs extends EventObject
{
	/**
	 * 6285386361331870752L
	 */
	private static final long serialVersionUID = 6285386361331870752L;

	private EventType type;	

	/**
	 * Gets the type of the event argument.
	 * @return {@link EventArgs}
	 */
	public final EventType getType() {
		return type;
	}	
	/**
	 * EventArgs class constructor.
	 * @param value the value
	 * @param et {@link EventArgs} the event type
	 */
	public EventArgs(Object value, EventType et)
	{
		super(value);
		this.type = et;
	}
}
