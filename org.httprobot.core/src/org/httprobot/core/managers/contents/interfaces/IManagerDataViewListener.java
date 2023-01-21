package org.httprobot.core.managers.contents.interfaces;

import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.events.ProgramDataEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;

/**
 * @author Joan
 * Program data listener interface.
 */
public interface IManagerDataViewListener extends IManagerImpl
{
	/**
	 * Fired when DataView message manager has started.
	 * @param sender {@link Object} the sender
	 * @param e {@link ProgramDataEventArgs} the arguments
	 */
	public void OnManagerDataView_Started(Object sender, ManagerEventArgs e) throws ManagerException, NotImplementedException;
	/**
	 * Fired when DataView message manager has finished.
	 * @param sender {@link Object} the sender
	 * @param e {@link ProgramDataEventArgs} the arguments
	 */
	public void OnManagerDataView_Finished(Object sender, ManagerEventArgs e) throws ManagerException, NotImplementedException;
	/**
	 * Fired when DataView message manager has finished with errors.
	 * @param sender {@link Object} the sender
	 * @param e {@link ProgramDataEventArgs} the arguments
	 */
	public void OnManagerDataView_Error(Object sender, ManagerEventArgs e) throws ManagerException, NotImplementedException;
}
