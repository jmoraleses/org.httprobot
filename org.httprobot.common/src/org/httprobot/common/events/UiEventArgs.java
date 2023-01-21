package org.httprobot.common.events;

import org.httprobot.common.definitions.Enums.EventType;
import org.httprobot.common.definitions.Enums.UiEventType;

/**
 * @author Joan
 * UiEventArgs class. Inherits EventArgs.
 */
public class UiEventArgs extends EventArgs {

	/**
	 * -8789663743900156744L
	 */
	private static final long serialVersionUID = -8789663743900156744L;

	private UiEventType uiet;	
	/**
	 * Gets the type
	 * @return {@link UiEventType}
	 */
	public UiEventType getUiet() {
		return uiet;
	}
	/**
	 * UiEventArgs class constructor.
	 * @param value the value
	 * @param uiet {@link UiEventType}
	 */
	public UiEventArgs(Object value, UiEventType uiet) 
	{
		super(value, EventType.UI);
		this.uiet = uiet;
	}
}
