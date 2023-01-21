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
public interface IManagerEqualsListener extends IManagerImpl
{
	/**
	 * Fired when Equals message manager has started.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerEquals_Started(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException;
	/**
	 * Fired when Equals message manager has finished.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerEquals_Finished(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException;
	/**
	 * Fired when Equals message manager has finished with errors.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerEquals_Error(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException;
}
