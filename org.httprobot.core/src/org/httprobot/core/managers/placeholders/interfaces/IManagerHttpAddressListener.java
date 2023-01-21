/**
 * 
 */
package org.httprobot.core.managers.placeholders.interfaces;

import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.HttpAddress;
import org.httprobot.core.contents.solr.InputField;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;

/**
 * @author joan
 *
 */
public interface IManagerHttpAddressListener extends IManagerImpl
{
	/**
	 * Fired when {@link HttpAddress} message manager has completed an {@link InputField}.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException custom exception
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerHttpAddress_InputFieldCompleted(Object sender, ManagerEventArgs e) throws ManagerException, NotImplementedException;
	/**
	 * Fired when {@link HttpAddress} message manager has started.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerHttpAddress_Started(Object sender, ManagerEventArgs e) throws ManagerException, NotImplementedException;
	/**
	 * Fired when {@link HttpAddress} message manager has finished.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerHttpAddress_Finished(Object sender, ManagerEventArgs e) throws ManagerException, NotImplementedException;
	/**
	 * Fired when {@link HttpAddress} message manager has finished with errors.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerHttpAddress_Error(Object sender, ManagerEventArgs e) throws ManagerException, NotImplementedException;
}