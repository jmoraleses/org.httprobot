/**
 * 
 */
package org.httprobot.core.managers.datatypes.interfaces;

import org.httprobot.common.datatypes.Field;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.core.contents.solr.InputField;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;

/**
 * {@link Field} message manager listener interface. Inherits {@link IManagerImpl}.
 * <br>
 * @author joan
 *
 */
public interface IManagerFieldListener extends IManagerImpl
{
	/**
	 * Fired when {@link InputField} manager has been completed.
	 * @param sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException
	 */
	public void OnManagerField_FieldCompleted(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException;
	/**
	 * Fired when Field message manager has started.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden.
	 */
	public void OnManagerField_Started(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException;
	/**
	 * Fired when Field message manager has finished.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden. 
	 */
	public void OnManagerField_Finished(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException;
	/**
	 * Fired when Field message manager has finished with errors.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden.
	 */
	public void OnManagerField_Error(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException;
}