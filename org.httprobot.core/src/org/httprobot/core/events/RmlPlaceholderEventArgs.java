package org.httprobot.core.events;

import org.httprobot.common.RML;
import org.httprobot.common.definitions.Enums.PlaceholderEventType;
import org.httprobot.common.definitions.Enums.ControlEventType;
import org.httprobot.common.events.ControlEventArgs;

public class RmlPlaceholderEventArgs extends ControlEventArgs
{
	/**
	 * 7020225105808505521L
	 */
	private static final long serialVersionUID = 7020225105808505521L;
	private PlaceholderEventType eventType;	
	/**
	 * @return the eventType
	 */
	public PlaceholderEventType getEventType() {
		return eventType;
	}
	/**
	 * @param value
	 * @param et
	 */
	public RmlPlaceholderEventArgs(Object value, ControlEventType ret, RML message) 
	{
		super(value, ret, message);
//		this.eventType = eventType;
	}
}