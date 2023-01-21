package org.httprobot.core.events;

import org.httprobot.common.RML;
import org.httprobot.common.definitions.Enums.ControlEventType;
import org.httprobot.common.events.EventArgs;
import org.httprobot.common.events.ControlEventArgs;

/**
 * ConfigEventArgs Class. Inherits {@link EventArgs}.
 * @author Joan 
 */
public class RmlConfigEventArgs extends ControlEventArgs {

	/**
	 * 1177098248565095357L
	 */
	private static final long serialVersionUID = 1177098248565095357L;
	/**
	 * ConfigEventArgs class constructor.
	 * @param value
	 * @param cet
	 */
	public RmlConfigEventArgs(Object value, RML message, ControlEventType cet) {
		super(value, cet, message);		
	}
}