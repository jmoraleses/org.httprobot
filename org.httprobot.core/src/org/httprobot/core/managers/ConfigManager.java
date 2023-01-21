/**
 * 
 */
package org.httprobot.core.managers;

import java.util.Dictionary;
import java.util.UUID;
import java.util.Vector;

import org.httprobot.common.Config;
import org.httprobot.common.config.Configuration;
import org.httprobot.common.config.DataSourceList;
import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.common.datatypes.DataSource;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.controls.interfaces.impl.IConfigControlImpl;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.config.interfaces.IManagerConfigurationListener;
import org.httprobot.core.managers.config.interfaces.IManagerLogListener;
import org.httprobot.core.managers.config.interfaces.IManagerServiceConnectionListener;
import org.httprobot.core.managers.config.interfaces.IManagerSessionListener;
import org.httprobot.core.managers.interfaces.IConfigManagerImpl;

/**
 * {@link Config} message manager abstract class. Inherits {@link Manager}. 
 * <br>
 * It's a {@link IConfigManagerImpl}.
 * <br>
 * @param <TControl> {@link Config} the managed message type
 * @param <TListener> {@link IManagerImpl} the managed message listener type.
 * 
 * @author joan
 */
public abstract class ConfigManager
	<TMessage extends Config, TControl extends IConfigControlImpl, TListener extends IManagerImpl>
		extends Manager<TMessage, TControl, TListener>
		implements IConfigManagerImpl {
	
	/**
	 * 2296416491885698809L
	 */
	private static final long serialVersionUID = 2296416491885698809L;

	private Vector<IManagerConfigurationListener> configurationListeners;
	private Vector<IManagerLogListener> logListeners;
	private Vector<IManagerServiceConnectionListener> serviceConnectionListeners;
	private Vector<IManagerSessionListener> sessionListeners;
	
	/**
	 * Configuration message manager default class constructor.
	 */
	public ConfigManager() 
	{
		initConfigManager();
	}
	/**
	 * Configuration message manager class constructor.
	 * @param parent the parent
	 * @param message the message
	 */
	public ConfigManager(TListener parent, TMessage message)
	{
		super(parent, message);
		initConfigManager();
	}
	/**
	 * Adds {@link IManagerConfigurationListener} listener to current manager.
	 * @param listener
	 */
	public final synchronized void addManagerConfigurationListener(IManagerConfigurationListener listener)
	{
		if(this.configurationListeners == null)
		{
			this.configurationListeners = new Vector<IManagerConfigurationListener>();
		}
		this.configurationListeners.add(listener);
	}
	/**
	 * Adds {@link IManagerLogListener} listener to current manager.
	 * @param listener
	 */
	public final synchronized void addManagerLogListener(IManagerLogListener listener)
	{
		if(this.logListeners == null)
		{
			this.logListeners = new Vector<IManagerLogListener>();
		}
		this.logListeners.add(listener);
	}
	/**
	 * Adds {@link IManagerServiceConnectionListener} listener to current manager.
	 * @param listener
	 */
	public final synchronized void addManagerServiceConnectionListener(IManagerServiceConnectionListener listener)
	{
		if(this.serviceConnectionListeners == null)
		{
			this.serviceConnectionListeners = new Vector<IManagerServiceConnectionListener>();
		}
		this.serviceConnectionListeners.add(listener);
	}
	/**
	 * Adds {@link IManagerSessionListener} listener to current manager.
	 * @param listener
	 */
	public final synchronized void addManagerSessionListener(IManagerSessionListener listener)
	{
		if(this.sessionListeners == null)
		{
			this.sessionListeners = new Vector<IManagerSessionListener>();
		}
		this.sessionListeners.add(listener);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.config.interfaces.IManagerConfigurationListener#getDataSourceList()
	 */
	@Override
	public DataSourceList getDataSourceList()
			throws NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this,
				"ConfigManager.getDataSourceList: Method not overridden");
		
		return null;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.config.interfaces.IManagerConfigurationListener#getDataSource(java.util.UUID)
	 */
	@Override
	public Dictionary<UUID, DataSource> getDataSources() 
			throws NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigManager.getDataSource: Method not overridden");
		
		return null;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.config.interfaces.IManagerConfigurationListener#getSystemContentTypeRoot()
	 */
	@Override
	public ContentTypeRoot getSystemContentTypeRoot()
			throws NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigManager.getSystemContentTypeRoot: Method not overridden");
		
		return null;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.Manager#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public abstract void OnCommandInput(Object sender, CliEventArgs e) 
			throws InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.Manager#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public abstract void OnCommandOutput(Object sender, CliEventArgs e) 
			throws InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.config.interfaces.IManagerConfigurationListener#OnManagerConfiguration_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerConfiguration_Error(Object sender, ManagerEventArgs e)
			throws NotImplementedException, ManagerException {
	
		CliTools.ThrowNotImplementedException(this, 
				"ConfigManager.OnManagerConfiguration_Error: Not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.config.interfaces.IManagerConfigurationListener#OnManagerConfiguration_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerConfiguration_Finished(Object sender, ManagerEventArgs e)
			throws NotImplementedException, ManagerException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigManager.OnManagerConfiguration_Finished: Not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.config.interfaces.IManagerConfigurationListener#OnManagerConfiguration_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerConfiguration_Started(Object sender, ManagerEventArgs e)
			throws NotImplementedException, ManagerException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigManager.OnManagerConfiguration_Started: Not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.config.interfaces.IManagerLogListener#OnManagerLog_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerLog_Error(Object sender, ManagerEventArgs e)
			throws NotImplementedException, ManagerException {

		CliTools.ThrowNotImplementedException(this, 
				"ConfigManager.OnManagerLog_Error: Not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.config.interfaces.IManagerLogListener#OnManagerLog_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerLog_Finished(Object sender, ManagerEventArgs e)
			throws NotImplementedException, ManagerException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigManager.OnManagerLog_Finished: Not implemented method");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.config.interfaces.IManagerLogListener#OnManagerLog_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerLog_Started(Object sender, ManagerEventArgs e)
			throws NotImplementedException, ManagerException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigManager.OnManagerLog_Started: Not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.config.interfaces.IManagerServiceConnectionListener#OnManagerServiceConnection_DataSource(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerServiceConnection_DataSource(Object sender, ManagerEventArgs e) 
			throws NotImplementedException, ManagerException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigManager.OnManagerServiceConnection_DataSource: Not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.config.interfaces.IManagerServiceConnectionListener#OnManagerServiceConnection_DataSourceList(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerServiceConnection_DataSourceList(Object sender, ManagerEventArgs e) 
			throws NotImplementedException, ManagerException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigManager.OnManagerServiceConnection_DataSourceList: Not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.config.interfaces.IManagerServiceConnectionListener#OnManagerServiceConnection_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerServiceConnection_Error(Object sender, ManagerEventArgs e) 
			throws NotImplementedException, ManagerException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigManager.OnManagerServiceConnection_Error: Not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.config.interfaces.IManagerServiceConnectionListener#OnManagerServiceConnection_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerServiceConnection_Finished(Object sender, ManagerEventArgs e) 
			throws NotImplementedException, ManagerException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigManager.OnManagerServiceConnection_Finished: Not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.config.interfaces.IManagerServiceConnectionListener#OnManagerServiceConnection_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerServiceConnection_Started(Object sender, ManagerEventArgs e) 
			throws NotImplementedException, ManagerException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigManager.OnManagerServiceConnection_Started: Not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.config.interfaces.IManagerServiceConnectionListener#OnManagerServiceConnection_SystemContentTypes(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerServiceConnection_SystemContentTypeRoot(Object sender, ManagerEventArgs e) 
			throws NotImplementedException, ManagerException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigManager.OnManagerServiceConnection_SystemContentTypes: Not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.config.interfaces.IManagerSessionListener#OnManagerSession_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSession_Error(Object sender, ManagerEventArgs e)
			throws NotImplementedException, ManagerException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigManager.OnManagerSession_Error: Not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.config.interfaces.IManagerSessionListener#OnManagerSession_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSession_Finished(Object sender, ManagerEventArgs e)
			throws NotImplementedException, ManagerException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigManager.OnManagerSession_Finished: Not implemented method");
	}
	@Override
	public void OnManagerSession_Started(Object sender, ManagerEventArgs e)
			throws NotImplementedException, ManagerException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigManager.OnManagerSession_Started: Not implemented method");
	}
	/**
	 * Removes {@link IManagerConfigurationListener} listener to current manager.
	 * @param listener
	 */
	public final synchronized void removeManagerConfigListener(IManagerConfigurationListener listener)
	{
		this.configurationListeners.remove(listener);
	}
	/**
	 * Removes {@link IManagerLogListener} listener to current manager.
	 * @param listener
	 */
	public final synchronized void removeManagerLogListener(IManagerLogListener listener)
	{
		this.logListeners.remove(listener);
	}
	/**
	 * Removes {@link IManagerServiceConnectionListener} listener to current manager.
	 * @param listener
	 */
	public final synchronized void removeManagerServiceConnectionListener(IManagerServiceConnectionListener listener)
	{
		this.serviceConnectionListeners.add(listener);
	}
	/**
	 * Removes {@link IManagerSessionListener} listener to current manager.
	 * @param listener
	 */
	public final synchronized void removeManagerSessionListener(IManagerSessionListener listener)
	{
		this.sessionListeners.remove(listener);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.Manager#start()
	 */
	@Override
	public abstract void start();
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.Manager#stop()
	 */
	@Override
	public abstract void stop();
	/**
	 * Initializes configuration message manager.
	 */
	private void initConfigManager()
	{
		addManagerConfigurationListener(this);
		addManagerLogListener(this);
		addManagerServiceConnectionListener(this);
		addManagerSessionListener(this);
	}
	/**
	 * Fires {@link Configuration} message manager event to listeners.
	 * @param e {@link ManagerEventArgs} the arguments
	 */
	protected final void ManagerConfigurationEvent(ManagerEventArgs e)
	{
		try
		{
			for(IManagerConfigurationListener listener : this.configurationListeners)
			{
				switch (e.getEventType()) {
				
				case STARTED:
					listener.OnManagerConfiguration_Started(this, e);
					break;
				case FINISHED:
					listener.OnManagerConfiguration_Finished(this, e);
					break;
				case ERROR:
					listener.OnManagerConfiguration_Error(this, e);
					break;

				default:
					break;
				}	
			}			
		}
		catch (NotImplementedException ex1) {
			ex1.printStackTrace();
		} catch (ManagerException ex2) {
			ex2.printStackTrace();
		} 
	}
	/**
	 * Fires {@link Log} message manager event to listeners.
	 * @param e {@link ManagerEventArgs} the arguments
	 */
	protected final void ManagerLogEvent(ManagerEventArgs e)
	{
		try
		{
			for(IManagerLogListener listener : this.logListeners)
			{
				switch (e.getEventType()) {
				
				case STARTED:
					listener.OnManagerLog_Started(this, e);
					break;
				case FINISHED:
					listener.OnManagerLog_Finished(this, e);
					break;
				case ERROR:
					listener.OnManagerLog_Error(this, e);
					break;

				default:
					break;
				}	
			}			
		}
		catch (NotImplementedException ex1) {
			ex1.printStackTrace();
		} catch (ManagerException ex2) {
			ex2.printStackTrace();
		} 
	}
	/**
	 * Fires {@link ServiceConnection} message manager event to listeners.
	 * @param e {@link ManagerEventArgs} the arguments
	 */
	protected final void ManagerServiceConnectionEvent(ManagerEventArgs e)
	{
		try
		{
			for(IManagerServiceConnectionListener listener : this.serviceConnectionListeners)
			{
				switch (e.getEventType()) {
				
				case STARTED:
					listener.OnManagerServiceConnection_Started(this, e);
					break;
				case FINISHED:
					listener.OnManagerServiceConnection_Finished(this, e);
					break;
				case ERROR:
					listener.OnManagerServiceConnection_Error(this, e);
					break;
				case DATA_SOURCE_LIST_LOADED:
					listener.OnManagerServiceConnection_DataSourceList(this, e);
					break;
				case SYSTEM_CONTENT_TYPE_ROOT_LOADED:
					listener.OnManagerServiceConnection_SystemContentTypeRoot(this, e);
					break;
				case DATA_SOURCE_LOADED:
					listener.OnManagerServiceConnection_DataSource(this, e);
					break;
				default:
					break;
				}	
			}			
		}
		catch (NotImplementedException ex1) {
			ex1.printStackTrace();
		} catch (ManagerException ex2) {
			ex2.printStackTrace();
		} 
	}	
	/**
	 * Fires {@link Session} message manager event to listeners.
	 * @param e {@link ManagerEventArgs} the arguments
	 */
	protected final void ManagerSessionEvent(ManagerEventArgs e)
	{
		try
		{
			for(IManagerSessionListener listener : this.sessionListeners)
			{
				switch (e.getEventType()) {
				
				case STARTED:
					listener.OnManagerSession_Started(this, e);
					break;
				case FINISHED:
					listener.OnManagerSession_Finished(this, e);
					break;
				case ERROR:
					listener.OnManagerSession_Error(this, e);
					break;

				default:
					break;
				}	
			}			
		}
		catch (NotImplementedException ex1) {
			ex1.printStackTrace();
		} catch (ManagerException ex2) {
			ex2.printStackTrace();
		} 
	}
}