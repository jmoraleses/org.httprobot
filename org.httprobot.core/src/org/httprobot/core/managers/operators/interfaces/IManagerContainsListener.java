/**
 * 
 */
package org.httprobot.core.managers.operators.interfaces;

import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;

/**
 * @author joan
 *
 */
public interface IManagerContainsListener extends IManagerImpl
{
	/**
	 * Fired when Contains message manager has started.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the argumentsP
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerContains_Started(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException;
	/**
	 * Fired when Contains message manager has finished.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerContains_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException;
	/**
	 * Fired when Contains message manager has finished with errors.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerContains_Error(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException;
}
