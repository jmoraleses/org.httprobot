package org.httprobot.core.events;

import org.httprobot.common.definitions.Enums.EventType;
import org.httprobot.common.definitions.Enums.InetEventType;
import org.httprobot.common.events.EventArgs;

/**
 * Internet manager event arguments class. Inherits EventArgs.
 * @author joan
 *
 */
public class InetEventArgs extends EventArgs 
{
	/**
	 *  -305874349827454967L
	 */
	private static final long serialVersionUID = -305874349827454967L;

	InetEventType eventType;
	
	public InetEventType getEventType()
	{
		return this.eventType;
	}
	
	public InetEventArgs(Object value, InetEventType et) {
		super(value, EventType.INET);
		this.eventType = et;
	}

}
