package org.httprobot.core.events;

import org.httprobot.common.definitions.Enums.RmlEventType;
import org.httprobot.common.events.EventArgs;
import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.rml.Rml;

/**
 * ConfigEventArgs Class. Inherits {@link EventArgs}.
 * @author Joan 
 */
public class RmlConfigEventArgs extends RmlEventArgs {

	/**
	 * 1177098248565095357L
	 */
	private static final long serialVersionUID = 1177098248565095357L;
	/**
	 * ConfigEventArgs class constructor.
	 * @param value
	 * @param cet
	 */
	public RmlConfigEventArgs(Object value, Rml message, RmlEventType cet) {
		super(value, cet, message);		
	}
}