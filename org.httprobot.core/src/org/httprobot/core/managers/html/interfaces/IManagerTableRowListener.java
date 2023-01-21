/**
 * 
 */
package org.httprobot.core.managers.html.interfaces;

import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.operators.html.TableRow;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.managers.interfaces.IOperatorManagerImpl;

/**
 * {@link TableRow} message manager listener interface. Inherits {@link IOperatorManagerImpl}.
 * <br>
 * @author joan
 *
 */
public interface IManagerTableRowListener extends IOperatorManagerImpl
{
	/**
	 * Fired when {@link TableRow} message manager has started.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerTableRow_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException;
	/**
	 * Fired when {@link TableRow} message manager has finished.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerTableRow_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException;
	/**
	 * Fired when {@link TableRow} message manager has finished with errors.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerTableRow_Error(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException;
}