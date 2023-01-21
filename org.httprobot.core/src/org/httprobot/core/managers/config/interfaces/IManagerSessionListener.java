/**
 * 
 */
package org.httprobot.core.managers.config.interfaces;

import org.httprobot.common.config.Session;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.config.SessionManager;

/**
 * {@link Session} message manager listener interface.
 * Inherits {@link IManagerImpl}.
 * @author joan
 *
 */
public interface IManagerSessionListener extends IManagerImpl {

	/**
	 * Fired when {@link SessionManager} has started.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws ManagerException
	 */
	public void OnManagerSession_Started(Object sender, ManagerEventArgs e) throws NotImplementedException, ManagerException;
	/**
	 * Fired when {@link SessionManager} has finished.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws ManagerException
	 */
	public void OnManagerSession_Finished(Object sender, ManagerEventArgs e) throws NotImplementedException, ManagerException;
	/**
	 * Fired when {@link SessionManager} has finished with errors.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws ManagerException
	 */
	public void OnManagerSession_Error(Object sender, ManagerEventArgs e) throws NotImplementedException, ManagerException;
}
