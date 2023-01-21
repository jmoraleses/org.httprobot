/**
 * 
 */
package org.httprobot.core.events;

import org.httprobot.common.config.DataSourceList;
import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.common.datatypes.DataSource;
import org.httprobot.common.definitions.Enums.EventType;
import org.httprobot.common.definitions.Enums.ServiceLoaderEventType;
import org.httprobot.common.events.EventArgs;
import org.httprobot.core.net.ServiceLoader;

/**
 * {@link ServiceLoader} event arguments class. 
 * Inherits {@link EventArgs}.
 * @author joan
 *
 */
public class ServiceLoaderEventArgs extends EventArgs {

	/**
	 * -785113946795812239L
	 */
	private static final long serialVersionUID = -785113946795812239L;

	ServiceLoaderEventType serviceLoaderEventType;	
	ContentTypeRoot systemContentTypeRoot;
	DataSourceList dataSourceList;
	DataSource dataSource;
	
	/**
	 * @return the target data source
	 */
	public DataSource getDataSource()
	{
		return this.dataSource;
	}
	/**
	 * @return the data source list
	 */
	public DataSourceList getDataSourceList()
	{
		return this.dataSourceList;
	}
	/**
	 * @return the system content types
	 */
	public ContentTypeRoot getSystemContentTypeRoot()
	{
		return this.systemContentTypeRoot;
	}	
	/**
	 * @return the service loader event type
	 */
	public ServiceLoaderEventType getEventType()
	{
		return this.serviceLoaderEventType;
	}	
	/**
	 * Service loader event arguments class constructor.
	 * @param value the source
	 * @param et {@link ServiceLoaderEventType} the event type
	 */
	public ServiceLoaderEventArgs(Object value, ServiceLoaderEventType et) {
		super(value, EventType.INET);
		this.serviceLoaderEventType = et;
	}	
	/**
	 * Service loader event arguments class constructor.
	 * @param value the source
	 * @param contentTypeRoot
	 */
	public ServiceLoaderEventArgs(Object value, ContentTypeRoot contentTypeRoot) {
		super(value, EventType.INET);
		this.systemContentTypeRoot = contentTypeRoot;
		this.serviceLoaderEventType = ServiceLoaderEventType.SYSTEM_CONTENT_TYPES_LOADED;
	}
	/**
	 * Service loader event arguments class constructor.
	 * @param value the source
	 * @param dataSourceList
	 */
	public ServiceLoaderEventArgs(Object value, DataSourceList dataSourceList) {
		super(value, EventType.INET);
		this.dataSourceList = dataSourceList;
		this.serviceLoaderEventType = ServiceLoaderEventType.DATA_SOURCE_LIST_LOADED;
	}
	/**
	 * Service loader event arguments class constructor.
	 * @param value the source
	 * @param dataSource
	 */
	public ServiceLoaderEventArgs(Object value, DataSource dataSource) {
		super(value, EventType.INET);
		this.dataSource = dataSource;
		this.serviceLoaderEventType = ServiceLoaderEventType.DATA_SOURCE_LOADED;
	}
}
