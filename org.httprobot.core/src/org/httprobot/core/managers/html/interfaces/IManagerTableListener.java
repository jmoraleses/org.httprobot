/**
 * 
 */
package org.httprobot.core.managers.html.interfaces;

import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.operators.html.Table;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.managers.interfaces.IOperatorManagerImpl;

/**
 * {@link Table} message manager listener interface. Inherits {@link IOperatorManagerImpl}.
 * <br>
 * @author joan
 *
 */
public interface IManagerTableListener extends IOperatorManagerImpl
{
	/**
	 * Fired when {@link Table} message manager has started.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerTable_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException;
	/**
	 * Fired when {@link Table} message manager has finished.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerTable_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException;
	/**
	 * Fired when {@link Table} message manager has finished with errors.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerTable_Error(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException;
}