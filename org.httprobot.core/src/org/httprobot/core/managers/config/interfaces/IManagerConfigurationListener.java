/**
 * 
 */
package org.httprobot.core.managers.config.interfaces;

import java.util.Dictionary;
import java.util.UUID;

import org.httprobot.common.config.Configuration;
import org.httprobot.common.config.DataSourceList;
import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.common.datatypes.DataSource;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;

/**
 * {@link Configuration} message manager listener interface.
 * Inherits {@link IManagerImpl}.
 * @author joan
 *
 */
public interface IManagerConfigurationListener extends IManagerImpl 
{
	/**
	 * @return {@link ContentTypeRoot} the root content type
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public ContentTypeRoot getSystemContentTypeRoot() throws NotImplementedException;
	/**
	 * @return {@link DataSourceList} the data sources list
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public DataSourceList getDataSourceList() throws NotImplementedException;
	/**
	 * @return Dictionary of {@link DataSource} with {@link UUID} as primary key
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public Dictionary<UUID, DataSource> getDataSources() throws NotImplementedException;
	/**
	 * Fired when {@link Configuration} message manager has started.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws ManagerException
	 */
	public void OnManagerConfiguration_Started(Object sender, ManagerEventArgs e) throws NotImplementedException, ManagerException;
	/**
	 * Fired when {@link Configuration} message manager has finished.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws ManagerException
	 */
	public void OnManagerConfiguration_Finished(Object sender, ManagerEventArgs e) throws NotImplementedException, ManagerException;
	/**
	 * Fired when {@link Configuration} message manager has finished with errors.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws ManagerException
	 */
	public void OnManagerConfiguration_Error(Object sender, ManagerEventArgs e) throws NotImplementedException, ManagerException;
}
