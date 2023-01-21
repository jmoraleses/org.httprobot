/**
 * 
 */
package org.httprobot.core.managers.html.interfaces;

import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.operators.html.Division;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.managers.interfaces.IOperatorManagerImpl;

/**
 * {@link Division} message manager listener interface. Inherits {@link IOperatorManagerImpl}.
 * <br>
 * @author joan
 *
 */
public interface IManagerDivisionListener extends IOperatorManagerImpl
{
	/**
	 * Fired when {@link Division} message manager has started.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerDivision_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException;
	/**
	 * Fired when {@link Division} message manager has finished.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerDivision_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException;
	/**
	 * Fired when {@link Division} message manager has finished with errors.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerDivision_Error(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException;
}