package org.httprobot.core.events;

import org.httprobot.common.definitions.Enums.EventType;
import org.httprobot.common.definitions.Enums.ProgramDataEventType;
import org.httprobot.common.events.EventArgs;

/**
 * Program data event arguments class.
 * @author Joan
 *
 */
public class ProgramDataEventArgs extends EventArgs 
{
	private ProgramDataEventType pdet;
	/**
	 * -6131114418432176715L
	 */
	private static final long serialVersionUID = -6131114418432176715L;

	/**
	 * Gets the type of the {@link ProgramDataEventType}.
	 * @return
	 */
	public ProgramDataEventType getPdet() {
		return pdet;
	}
	/**
	 * ProgramDataEventArgs class constructor.
	 * @param value
	 * @param pdet {@link ProgramDataEventType} 
	 */
	public ProgramDataEventArgs(Object value, ProgramDataEventType pdet) 
	{
		super(value, EventType.PROGRAM_DATA);
		this.pdet = pdet;
	}
}