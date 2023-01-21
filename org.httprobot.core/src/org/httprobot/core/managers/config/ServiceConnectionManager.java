/**
 * 
 */
package org.httprobot.core.managers.config;

import java.net.URL;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;

import org.httprobot.common.config.DataSourceList;
import org.httprobot.common.config.ServiceConnection;
import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.common.datatypes.DataSource;
import org.httprobot.common.definitions.Enums.ManagerErrorCode;
import org.httprobot.common.definitions.Enums.ManagerEventType;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.exceptions.ServiceLoaderException;
import org.httprobot.core.common.Enums.ConfigData;
import org.httprobot.core.controls.config.ServiceConnectionControl;
import org.httprobot.core.controls.config.interfaces.IControlServiceConnectionListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.events.ServiceLoaderEventArgs;
import org.httprobot.core.interfaces.IServiceLoaderListener;
import org.httprobot.core.managers.ConfigManager;
import org.httprobot.core.managers.config.interfaces.IManagerServiceConnectionListener;
import org.httprobot.core.net.ServiceLoader;

/**
 * {@link ServiceConnection} message manager class. Inherits {@link ConfigManager}. 
 * Is {@link IControlServiceConnectionListener} and {@link IServiceLoaderListener}.
 * <br>
 * @author joan
 *
 */
@XmlRootElement
public final class ServiceConnectionManager 
extends ConfigManager<ServiceConnection, ServiceConnectionControl, IManagerServiceConnectionListener> 
implements IControlServiceConnectionListener, IServiceLoaderListener
{
	/**
	 * 3160292279150071534L
	 */
	private static final long serialVersionUID = 3160292279150071534L;
	/**
	 * The service loader.
	 */
	protected ServiceLoader serviceLoader;
	/**
	 * The system content type root.
	 */
	ContentTypeRoot systemContentTypeRoot;
	/**
	 * The list of available {@link DataSource} elements.
	 */
	DataSourceList dataSourceList;	
	/**
	 * The list of {@link DataSource} elements.
	 */
	Dictionary<UUID, DataSource> dataSources;
	/**
	 * The number of {@link DataSource} elements.
	 */
	Integer dataSourcesCount;
	/**
	 * {@link ServiceConnection} message manager default class constructor.
	 */
	public ServiceConnectionManager()
	{
		super();
	}
	/**
	 * {@link ServiceConnection} message manager class constructor.
	 * @param parent {@link IManagerServiceConnectionListener} the parent
	 * @param message {@link ServiceConnection} the message
	 */
	public ServiceConnectionManager(IManagerServiceConnectionListener parent, ServiceConnection message)
	{
		super(parent, message);
		
		//Initialize members
		this.dataSources = new Hashtable<UUID, DataSource>();
		
		//Initialize control
		this.control = new ServiceConnectionControl(this, message);

		//Associate control to manager.
		addCommandOutputListener(this.control);
	}	
	/**
	 * @return {@link DataSourceList} the available data sources
	 */
	public DataSourceList getDataSourceList()
	{
		if(this.dataSourceList != null)
		{
			return this.dataSourceList;
		}
		else
		{
			this.dataSourceList = this.serviceLoader.getDataSourceList();
			return this.dataSourceList;
		}		
	}
	/**
	 * @return the list of data sources
	 */
	public Dictionary<UUID, DataSource> getDataSources()
	{
		return this.dataSources;
	}
	/**
	 * @return {@link ContentTypeRoot} the system content types
	 */
	public ContentTypeRoot getSystemContentTypeRoot()
	{
		if(this.systemContentTypeRoot != null)
		{
			return this.systemContentTypeRoot;
		}
		else
		{
			this.systemContentTypeRoot = this.serviceLoader.getSystemContentTypeRoot();
			return this.systemContentTypeRoot;
		}
	}
	/**
	 * Loads requested data sources.
	 * @param dataSources the string UUID sources list
	 */
	public void LoadTargetDataSources(ArrayList<String> dataSources)
	{
		this.dataSourcesCount = dataSources.size();
		this.serviceLoader.LoadDataSources(dataSources);
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ConfigManager#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e) 
			throws InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ConfigManager#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e) 
			throws InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlServiceConnectionListener#OnControlServiceConnection_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServiceConnection_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlServiceConnectionListener#OnControlServiceConnection_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServiceConnection_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlServiceConnectionListener#OnControlServiceConnection_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServiceConnection_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.control))
		{
			if(this.control.get(ConfigData.URL) != null ?
					this.control.get(ConfigData.Q_NAME) != null : false) { 
				
				URL url = URL.class.cast(this.control.get(ConfigData.URL));
				QName qname = QName.class.cast(this.control.get(ConfigData.Q_NAME));
				
				//Initialize web service loader
				this.serviceLoader = new ServiceLoader(this, url, qname);
				this.serviceLoader.start();
				
				//Fire STARTED event
				ManagerServiceConnectionEvent(new ManagerEventArgs(this, ManagerEventType.STARTED));
			}
			else
			{
				ManagerServiceConnectionEvent(new ManagerEventArgs(this, ManagerErrorCode.BAD_MESSAGE));
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlServiceConnectionListener#OnControlServiceConnection_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServiceConnection_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlServiceConnectionListener#OnControlServiceConnection_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServiceConnection_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlServiceConnectionListener#OnControlServiceConnection_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServiceConnection_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ConfigManager#OnManagerServiceConnection_DataSource(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerServiceConnection_DataSource(Object sender, ManagerEventArgs e) 
			throws NotImplementedException, ManagerException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ConfigManager#OnManagerServiceConnection_DataSourceList(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerServiceConnection_DataSourceList(Object sender, ManagerEventArgs e) 
			throws NotImplementedException, ManagerException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ConfigManager#OnManagerServiceConnection_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerServiceConnection_Error(Object sender, ManagerEventArgs e)
			throws NotImplementedException, ManagerException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ConfigManager#OnManagerServiceConnection_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerServiceConnection_Finished(Object sender, ManagerEventArgs e) 
			throws NotImplementedException, ManagerException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ConfigManager#OnManagerServiceConnection_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerServiceConnection_Started(Object sender, ManagerEventArgs e) 
			throws NotImplementedException, ManagerException {
		
		if(e.getSource().equals(this))
		{
			//When all managers has been started. 
			//Set iterator ready to iterate again.
			this.reset();
		}
	}
	@Override
	public void OnManagerServiceConnection_SystemContentTypeRoot(Object sender, ManagerEventArgs e) 
			throws NotImplementedException, ManagerException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IServiceLoaderListener#OnServiceLoader_DataSource(java.lang.Object, org.httprobot.core.events.ServiceLoaderEventArgs)
	 */
	@Override
	public void OnServiceLoader_DataSource(Object sender, ServiceLoaderEventArgs e) 
			throws ServiceLoaderException {
		
		if(e.getDataSource() != null)
		{
			DataSource dataSource = e.getDataSource();
			
			//Store data source
			this.dataSources.put(dataSource.getUuid(), dataSource);
			
			//Fire status event
			ManagerServiceConnectionEvent(new ManagerEventArgs(this, dataSource));	
		
			if(this.dataSourcesCount == this.dataSources.size())
			{
				//Fire status FINISHED event
				ManagerServiceConnectionEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));			
			}
		}
		else
		{
			ManagerServiceConnectionEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IServiceLoaderListener#OnServiceLoader_DataSourceList(java.lang.Object, org.httprobot.core.events.ServiceLoaderEventArgs)
	 */
	@Override
	public void OnServiceLoader_DataSourceList(Object sender, ServiceLoaderEventArgs e) 
			throws ServiceLoaderException {
		
		if(e.getDataSourceList() != null)
		{
			//Store loaded data
			this.dataSourceList = e.getDataSourceList();
			
			//Fire status event
			ManagerServiceConnectionEvent(new ManagerEventArgs(this, this.dataSourceList));
		}
		else
		{
			ManagerServiceConnectionEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IServiceLoaderListener#OnServiceLoader_Ready(java.lang.Object, org.httprobot.core.events.ServiceLoaderEventArgs)
	 */
	@Override
	public void OnServiceLoader_Ready(Object sender, ServiceLoaderEventArgs e)
			throws ServiceLoaderException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IServiceLoaderListener#OnServiceLoader_SystemContentTypes(java.lang.Object, org.httprobot.core.events.ServiceLoaderEventArgs)
	 */
	@Override
	public void OnServiceLoader_SystemContentTypeRoot(Object sender, ServiceLoaderEventArgs e) 
			throws ServiceLoaderException {
		
		if(e.getSystemContentTypeRoot() != null)
		{
			//Store loaded data
			this.systemContentTypeRoot = e.getSystemContentTypeRoot();
			ManagerServiceConnectionEvent(new ManagerEventArgs(this, this.systemContentTypeRoot));	
		}
		else
		{
			ManagerServiceConnectionEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IServiceLoaderListener#OnServiceLoader_WebServiceError(java.lang.Object, org.httprobot.core.events.ServiceLoaderEventArgs)
	 */
	@Override
	public void OnServiceLoader_WebServiceError(Object sender, ServiceLoaderEventArgs e) 
			throws ServiceLoaderException {
		ManagerServiceConnectionEvent(new ManagerEventArgs(this, ManagerErrorCode.CONNECTION));
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ConfigManager#start()
	 */
	@Override
	public void start() 
	{
		this.control.controlMessage(this.message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ConfigManager#stop()
	 */
	@Override
	public void stop() {

	}
}