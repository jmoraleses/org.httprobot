package org.httprobot.core.events;

import org.httprobot.common.definitions.Enums.EventType;
import org.httprobot.common.events.EventArgs;

public class HttpEventArgs extends EventArgs {

	/**
	 * -3861839914159775885L
	 */
	private static final long serialVersionUID = -3861839914159775885L;

	public HttpEventArgs(Object value, EventType et) 
	{
		super(value, et);
	}

}
