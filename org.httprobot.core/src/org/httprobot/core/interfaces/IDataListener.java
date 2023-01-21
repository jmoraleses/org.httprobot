package org.httprobot.core.interfaces;

import org.httprobot.common.interfaces.IListener;
import org.httprobot.core.events.ProgramDataEventArgs;

/**
 * @author Joan
 * Program data listener interface.
 */
public interface IDataListener extends IListener
{
	/**
	 * Fired when data has been changed.
	 * @param sender {@link Object} the sender
	 * @param e {@link ProgramDataEventArgs} the arguments
	 */
	public void OnProgramDataChanged(Object sender, ProgramDataEventArgs e);
}
