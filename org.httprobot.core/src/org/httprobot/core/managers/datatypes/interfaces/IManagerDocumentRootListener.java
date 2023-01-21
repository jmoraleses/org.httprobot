/**
 * 
 */
package org.httprobot.core.managers.datatypes.interfaces;

import org.httprobot.common.datatypes.DocumentRoot;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;

/**
 * {@link DocumentRoot} message manager listener interface. 
 * Inherits {@link IManagerImpl}.
 * @author joan
 *
 */
public interface IManagerDocumentRootListener extends IManagerImpl
{
	/**
	 * Called when {@link DocumentRoot} message manager has started.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerDocumentRoot_Started(Object sender, ManagerEventArgs e) throws ManagerException, NotImplementedException;
	/**
	 * Called when {@link DocumentRoot} message manager has finished.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerDocumentRoot_Finished(Object sender, ManagerEventArgs e) throws ManagerException, NotImplementedException;
	/**
	 * Called when {@link DocumentRoot} message manager has finished with errors.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerDocumentRoot_Error(Object sender, ManagerEventArgs e) throws ManagerException, NotImplementedException;
}