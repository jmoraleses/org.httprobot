/**
 * 
 */
package org.httprobot.core.managers.config.interfaces;

import org.httprobot.common.config.DataSourceList;
import org.httprobot.common.config.ServiceConnection;
import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.common.datatypes.DataSource;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.net.ServiceLoader;

/**
 * {@link ServiceConnection} message manager listener interface.
 * Inherits {@link IManagerImpl}.
 * @author joan
 *
 */
public interface IManagerServiceConnectionListener extends IManagerImpl 
{
	/**
	 * Fired when {@link ServiceLoader} member has loaded a {@link DataSource}.
	 * @param sender
	 * @param e
	 * @throws NotImplementedException
	 * @throws ManagerException
	 */
	public void OnManagerServiceConnection_DataSource(Object sender, ManagerEventArgs e) throws NotImplementedException, ManagerException;
	/**
	 * Fired when {@link ServiceLoader} member has loaded a {@link DataSourceList}.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws NotImplementedException
	 * @throws ManagerException
	 */
	public void OnManagerServiceConnection_DataSourceList(Object sender, ManagerEventArgs e) throws NotImplementedException, ManagerException;
	/**
	 * Fired when {@link ServiceLoader} member has loaded the system {@link ContentTypeRoot}.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws ManagerException
	 */
	public void OnManagerServiceConnection_SystemContentTypeRoot(Object sender, ManagerEventArgs e) throws NotImplementedException, ManagerException;
	/**
	 * Fired when {@link ServiceConnection} has started.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws NotImplementedException
	 * @throws ManagerException
	 */
	public void OnManagerServiceConnection_Started(Object sender, ManagerEventArgs e) throws NotImplementedException, ManagerException;
	/**
	 * Fired when {@link ServiceConnection} has finished.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws NotImplementedException
	 * @throws ManagerException
	 */
	public void OnManagerServiceConnection_Finished(Object sender, ManagerEventArgs e) throws NotImplementedException, ManagerException;
	/**
	 * Fired when {@link ServiceConnection} has finished with errors.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws NotImplementedException
	 * @throws ManagerException
	 */
	public void OnManagerServiceConnection_Error(Object sender, ManagerEventArgs e) throws NotImplementedException, ManagerException;
}