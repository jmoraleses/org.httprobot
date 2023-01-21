/**
 * 
 */
package org.httprobot.core.managers.operators.interfaces;

import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;

/**
 * @author joan
 *
 */
public interface IManagerTryParseListener extends IManagerImpl
{
	/**
	 * Fired when TryParse message manager has started.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerTryParse_Started(Object sender, ManagerEventArgs e) throws ManagerException, NotImplementedException;
	/**
	 * Fired when TryParse message manager has finished.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerTryParse_Finished(Object sender, ManagerEventArgs e) throws ManagerException, NotImplementedException;
	/**
	 * Fired when TryParse message manager has finished with errors.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerTryParse_Error(Object sender, ManagerEventArgs e) throws ManagerException, NotImplementedException;
}
