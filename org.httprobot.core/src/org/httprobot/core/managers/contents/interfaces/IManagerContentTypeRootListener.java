/**
 * 
 */
package org.httprobot.core.managers.contents.interfaces;

import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;

/**
 * {@link ContentTypeRoot} message manager listener interface.
 * Inherits {@link IManagerImpl}.
 * @author joan
 *
 */
public interface IManagerContentTypeRootListener extends IManagerImpl 
{
	/**
	 * Fired when {@link ContentTypeRoot} manager has started.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerContentTypeRoot_Started(Object sender, ManagerEventArgs e) throws ManagerException, NotImplementedException;
	/**
	 * Fired when {@link ContentTypeRoot} manager has finished.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerContentTypeRoot_Finished(Object sender, ManagerEventArgs e) throws ManagerException, NotImplementedException;
	/**
	 * Fired when {@link ContentTypeRoot} manager has finished with errors.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerContentTypeRoot_Error(Object sender, ManagerEventArgs e) throws ManagerException, NotImplementedException;

}
