package org.httprobot.core.managers.datatypes.interfaces;

import org.httprobot.common.datatypes.DataSource;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.datatypes.DataSourceManager;

/**
 * {@link DataSource} message manager listener interface. Inherits {@link IManagerImpl}.
 * @author Joan 
 */
public interface IManagerDataSourceListener extends IManagerImpl
{
	/**
	 * Fired when {@link DataSourceManager} has started.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException when error is detected.
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerDataSource_Started(Object sender, ManagerEventArgs e) throws ManagerException, NotImplementedException;
	/**
	 * Fired when {@link DataSourceManager} has finished.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException when error is detected.
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerDataSource_Finished(Object sender, ManagerEventArgs e) throws ManagerException, NotImplementedException;
	/**
	 * Fired when {@link DataSourceManager} has finished with errors.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException when error is detected.
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerDataSource_Error(Object sender, ManagerEventArgs e) throws ManagerException, NotImplementedException;
}
