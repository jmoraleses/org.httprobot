/**
 * 
 */
package org.httprobot.core.events;

import org.httprobot.common.definitions.Enums.EventType;
import org.httprobot.common.events.EventArgs;

/**
 * @author joan
 *
 */
public class RequesterEventArgs extends EventArgs {

	/**
	 * 7276472331957670231L
	 */
	private static final long serialVersionUID = 7276472331957670231L;
	
	
	/**
	 * @param value
	 * @param et
	 */
	public RequesterEventArgs(Object value, EventType et) 
	{
		super(value, EventType.REQUESTER);
	}

}