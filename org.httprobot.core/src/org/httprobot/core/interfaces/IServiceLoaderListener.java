/**
 * 
 */
package org.httprobot.core.interfaces;

import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.common.exceptions.ServiceLoaderException;
import org.httprobot.common.interfaces.IListener;
import org.httprobot.core.events.ServiceLoaderEventArgs;
import org.httprobot.core.net.ServiceLoader;

/**
 * {@link ServiceLoader} listener interface.
 * Inherits {@link IListener}.
 * @author joan
 *
 */
public interface IServiceLoaderListener extends IListener 
{
	/**
	 * Fired when a {@link ServiceLoader} error has occurred during a web service method 
	 * @param sender the sender
	 * @param e {@link ServiceLoaderEventArgs} the arguments
	 * @throws ServiceLoaderException
	 */
	public void OnServiceLoader_WebServiceError(Object sender, ServiceLoaderEventArgs e) throws ServiceLoaderException;
	/**
	 * Fired when {@link ServiceLoader} has been initialized.
	 * @param sender the sender
	 * @param e {@link ServiceLoaderEventArgs} the arguments
	 * @throws ServiceLoaderException
	 */
	public void OnServiceLoader_Ready(Object sender, ServiceLoaderEventArgs e) throws ServiceLoaderException;
	/**
	 * Fired when {@link ServiceLoader} has loaded the System {@link ContentTypeRoot} message.
	 * @param sender the sender
	 * @param e {@link ServiceLoaderEventArgs} the arguments
	 * @throws ServiceLoaderException
	 */
	public void OnServiceLoader_SystemContentTypeRoot(Object sender, ServiceLoaderEventArgs e) throws ServiceLoaderException;
	/**
	 * Fired when {@link ServiceLoader} has loaded the {@link DataSourceList} message.
	 * @param sender the sender
	 * @param e {@link ServiceLoaderEventArgs} the arguments
	 * @throws ServiceLoaderException
	 */
	public void OnServiceLoader_DataSourceList(Object sender, ServiceLoaderEventArgs e) throws ServiceLoaderException;
	/**
	 * Fired when {@link ServiceLoader} has loaded the {@link DataSource} message.
	 * @param sender the sender
	 * @param e {@link ServiceLoaderEventArgs} the arguments
	 * @throws ServiceLoaderException
	 */
	public void OnServiceLoader_DataSource(Object sender, ServiceLoaderEventArgs e) throws ServiceLoaderException;
}
