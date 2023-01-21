/**
 * 
 */
package org.httprobot.core.managers.datatypes.interfaces;

import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;

/**
 * @author joan
 *
 */
public interface IManagerActionListener extends IManagerImpl
{
	/**
	 * Fired when Action's manager web loader has loaded a page.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 */
	public void OnManagerAction_WebLoaded(Object sender, ManagerEventArgs e) throws ManagerException, NotImplementedException;
	/**
	 * Fired when manager has started requesting.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 */
	public void OnManagerAction_Started(Object sender, ManagerEventArgs e) throws ManagerException, NotImplementedException;
	/**
	 * Fired when manager has finished requesting.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 */
	public void OnManagerAction_Finished(Object sender, ManagerEventArgs e) throws ManagerException, NotImplementedException;
	/**
	 * Fired when manager has finished requesting with errors.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException 
	 */
	public void OnManagerAction_Error(Object sender, ManagerEventArgs e) throws ManagerException, NotImplementedException;
}
