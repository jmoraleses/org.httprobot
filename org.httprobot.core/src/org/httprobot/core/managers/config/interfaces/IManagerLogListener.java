/**
 * 
 */
package org.httprobot.core.managers.config.interfaces;

import org.httprobot.common.config.Log;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.config.LogManager;

/**
 * {@link Log} message manager listener interface.
 * Inherits {@link IManagerImpl}.
 * @author joan
 *
 */
public interface IManagerLogListener extends IManagerImpl
{
	/**
	 * Fired when {@link LogManager} manager has started.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws ManagerException
	 */
	public void OnManagerLog_Started(Object sender, ManagerEventArgs e) throws NotImplementedException, ManagerException;
	/**
	 * Fired when {@link LogManager} manager has finished.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws ManagerException
	 */
	public void OnManagerLog_Finished(Object sender, ManagerEventArgs e) throws NotImplementedException, ManagerException;
	/**
	 * Fired when {@link LogManager} manager has finished with errors.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws ManagerException
	 */
	public void OnManagerLog_Error(Object sender, ManagerEventArgs e) throws NotImplementedException, ManagerException;
}
