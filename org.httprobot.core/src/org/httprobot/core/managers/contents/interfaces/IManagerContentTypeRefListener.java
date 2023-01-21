/**
 * 
 */
package org.httprobot.core.managers.contents.interfaces;

import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;

/**
 * ContentTypeRef message manager listener interface.
 * @author joan
 *
 */
public interface IManagerContentTypeRefListener extends IManagerImpl {
	/**
	 * Fired when ContentTypeRef manager has started.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerContentTypeRef_Started(Object sender, ManagerEventArgs e) throws ManagerException, NotImplementedException;
	/**
	 * Fired when ContentTypeRef manager has finished.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerContentTypeRef_Finished(Object sender, ManagerEventArgs e) throws ManagerException, NotImplementedException;
	/**
	 * Fired when ContentTypeRef manager has finished with errors.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerContentTypeRef_Error(Object sender, ManagerEventArgs e) throws ManagerException, NotImplementedException;

}
