/**
 * 
 */
package org.httprobot.core.managers.config;

import java.util.Collection;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.config.Configuration;
import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.common.datatypes.DataSource;
import org.httprobot.common.definitions.Enums.ManagerErrorCode;
import org.httprobot.common.definitions.Enums.ManagerEventType;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.core.common.Enums.ConfigData;
import org.httprobot.core.contents.DocumentLibrary;
import org.httprobot.core.contents.TemplateLibrary;
import org.httprobot.core.contents.solr.InputDocument;
import org.httprobot.core.controls.config.ConfigurationControl;
import org.httprobot.core.controls.config.interfaces.IControlConfigurationListener;
import org.httprobot.core.controls.contents.ContentTypeRootControl;
import org.httprobot.core.controls.datatypes.DataSourceControl;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IDataMappingImpl;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.ConfigManager;
import org.httprobot.core.managers.config.interfaces.IManagerConfigurationListener;
import org.httprobot.core.managers.contents.ContentTypeRootManager;
import org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRootListener;
import org.httprobot.core.managers.contents.interfaces.IManagerDataViewListener;
import org.httprobot.core.managers.datatypes.DataSourceManager;
import org.httprobot.core.managers.datatypes.interfaces.IManagerDataSourceListener;

/**
 * {@link Configuration} message manager class. Inherits {@link ConfigManager}.
 * <br>
 * Manages {@link Configuration} message and fire 
 * events for {@link IManagerConfigurationListener} objects.
 * <br>
 * It's {@link IControlConfigurationListener}, {@link IManagerDataSourceListener}, 
 * {@link IManagerDataViewListener} and {@link IManagerContentTypeRootListener}.
 * <br>
 * <h1>Input data: Map[Object, DataSource]</h1> 
 * The {@link DataSource}'s of starting {@link DataSourceManager}'s.
 * <br>
 * <h1>Output data: Map[DataSource, InputDocumentHandler]</h1> 
 * {@link Dictionary} of {@link InputDocument} with {@link DataSource} as primary keys.
 * <br>
 * @author Joan
 *
 */
@XmlRootElement
public final class ConfigurationManager 	
		extends ConfigManager<Configuration, ConfigurationControl, IManagerConfigurationListener>
		implements IControlConfigurationListener, IManagerDataSourceListener,
			IManagerContentTypeRootListener, IDataMappingImpl<DataSource, DocumentLibrary> {
	
	/**
	 * -3237564394870877843L
	 */
	private static final long serialVersionUID = -3237564394870877843L;
	
	/**
	 * The {@link ContentTypeRoot} message manager
	 */
	protected ContentTypeRootManager contentTypeRootManager;
	/**
	 * The {@link DataSource} message managers with {@link DataSource} message as primary key.
	 */
	protected Map<DataSource, DataSourceManager> dataSourceManagers;
	/**
	 * The current {@link DataSource} message going on.
	 */
	DataSource currentDataSource;
	/**
	 * The input data.
	 */
	Set<DataSource> inputData;
	/**
	 * The output data.
	 */
	Map<DataSource, DocumentLibrary> outputData;

	/**
	 * {@link Configuration} message manager default class constructor.
	 */
	public ConfigurationManager()
	{
		super();
		
		//Initialize members
		this.dataSourceManagers = new LinkedHashMap<DataSource, DataSourceManager>();
		this.control = new ConfigurationControl(this, null);
		
		this.outputData = new LinkedHashMap<DataSource, DocumentLibrary>();
		this.inputData = new HashSet<DataSource>();
	}
	/**
	 * {@link Configuration} message manager class constructor.
	 * @param parent {@link IManagerConfigurationListener} the parent
	 * @param message {@link Configuration} the message
	 */
	public ConfigurationManager(IManagerConfigurationListener parent, Configuration message) 
	{
		super(parent, message);
		
		//Initialize members
		this.dataSourceManagers = new LinkedHashMap<DataSource, DataSourceManager>();
		this.control = new ConfigurationControl(this, message);
		
		this.outputData = new LinkedHashMap<DataSource, DocumentLibrary>();
		this.inputData = new HashSet<DataSource>();
		
		this.templateLibrary = new TemplateLibrary();
		this.contentTypeRoot = new ContentTypeRoot();
		
		this.parameterConstants = new LinkedHashMap<String, String>();
		this.parameterBannedWords = new LinkedHashMap<String, String>();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#clear()
	 */
	@Override
	public void clear() 
	{
		this.outputData.clear();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey(Object key) 
	{
		return this.inputData.contains(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object value)
	{
		return this.outputData.containsValue(value);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<java.util.Map.Entry<DataSource, DocumentLibrary>> entrySet() 
	{
		return this.outputData.entrySet();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public DocumentLibrary get(Object key) 
	{
		return this.get(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() 
	{
		return this.outputData.isEmpty();
	}
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<DataSource> iterator() {
		return this.outputData.keySet().iterator();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<DataSource> keySet()
	{
		return this.outputData.keySet();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ConfigManager#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e) 
			throws InconsistenMessageException {
		
		switch (e.getCommand()) 
		{
			case CONTROL_CONTENT_TYPE_ROOT:
				try
				{
					//Cast source
					ContentTypeRootControl contentTypeRootControl = ContentTypeRootControl.class.cast(e.getControl());
					
					//Check if it's the current ContentTypeRoot
					if(this.control.get(ConfigData.SYSTEM_CONTENT_TYPE_ROOT) != null ?
							this.control.get(ConfigData.SYSTEM_CONTENT_TYPE_ROOT).equals(contentTypeRootControl) : false) {
					
						//Initialize content type root message manager
						this.contentTypeRootManager = new ContentTypeRootManager(this, contentTypeRootControl.getMessage());
					
						//Associate manager to parent
						this.contentTypeRootManager.addManagerContentTypeRootListener(this);
						this.contentTypeRootManager.addManagerListener(this);
						addCommandOutputListener(this.contentTypeRootManager);
					
						//Store manager
						addChildManager(this.contentTypeRootManager);
					}
				}
				catch(ClassCastException ex)
				{
					ManagerConfigurationEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
				}
				return;
				
			case CONTROL_DATA_SOURCE:
				try
				{
					//Cast source
					DataSourceControl dataSourceControl = DataSourceControl.class.cast(e.getControl());
					
					//Check if DataSource message is the current configuration control message.
					if(this.control.get(ConfigData.DATA_SOURCE) != null ?
							this.control.get(ConfigData.DATA_SOURCE).equals(dataSourceControl) : false) {
						
						//Update input data
						this.inputData.add(dataSourceControl.getMessage());
						
						//Initialize manager
						DataSourceManager dataSourceManager = new DataSourceManager(this, dataSourceControl.getMessage());
						
						//Associate manager to listener
						dataSourceManager.addManagerListener(this);
						dataSourceManager.addManagerDataSourceListener(this);
						addCommandOutputListener(dataSourceManager);
						
						//Store manager
						addChildManager(dataSourceManager);
						this.dataSourceManagers.put(dataSourceControl.getMessage(), dataSourceManager);
					}
				}
				catch(ClassCastException ex)
				{
					ManagerConfigurationEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
				}
				return;

			default:
				return;
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ConfigManager#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlConfigurationListener#OnControlConfiguration_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConfiguration_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlConfigurationListener#OnControlConfiguration_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConfiguration_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlConfigurationListener#OnControlConfiguration_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConfiguration_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(this.hasChildManagers())
		{
			//Start each child manager
			while(this.hasNext())
			{
				IManagerImpl manager = this.next();
				manager.start();
			}
			
			ManagerConfigurationEvent(new ManagerEventArgs(this, ManagerEventType.STARTED));
		}
		else
		{
			ManagerConfigurationEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlConfigurationListener#OnControlConfiguration_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConfiguration_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlConfigurationListener#OnControlConfiguration_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConfiguration_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlConfigurationListener#OnControlConfiguration_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConfiguration_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ConfigManager#OnManagerConfiguration_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerConfiguration_Error(Object sender, ManagerEventArgs e)
			throws NotImplementedException, ManagerException {
		
		//Nothing
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ConfigManager#OnManagerConfiguration_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerConfiguration_Finished(Object sender, ManagerEventArgs e) 
			throws NotImplementedException, ManagerException {
		
		//Nothing
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ConfigManager#OnManagerConfiguration_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerConfiguration_Started(Object sender, ManagerEventArgs e)
			throws NotImplementedException, ManagerException {
		
		if(e.getSource().equals(this))
		{
			//When all managers has been started. 
			//Set iterator ready to iterate again.
			this.reset();	
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRootListener#OnManagerContentTypeRoot_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentTypeRoot_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		ManagerConfigurationEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRootListener#OnManagerContentTypeRoot_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentTypeRoot_Finished(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {
		
		if(e.getSource().equals(this.contentTypeRootManager))
		{
			for(ContentTypeRoot contentTypeRoot : this.contentTypeRootManager)
			{
				if(contentTypeRoot.equals(this.control.get(ConfigData.SYSTEM_CONTENT_TYPE_ROOT)))
				{
					//Assign root content type data
					this.contentTypeRoot.getContentTypeRef().addAll(contentTypeRoot.getContentTypeRef());
					this.contentTypeRoot.getFieldRef().addAll(contentTypeRoot.getFieldRef());
					this.contentTypeRoot.getContentType().addAll(contentTypeRoot.getContentType());					
					
					this.templateLibrary.putAll(this.contentTypeRootManager.get(contentTypeRoot));
					
					//Not more than one content type root expected.
					break;
				}
			}				
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRootListener#OnManagerContentTypeRoot_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentTypeRoot_Started(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {
		
		if(e.getSource().equals(this.contentTypeRootManager) 
				&& this.getParent().getSystemContentTypeRoot() != null) {
			
			//Set template documents as output data to be finally assigned from current class.
			this.contentTypeRootManager.put(this.getParent().getSystemContentTypeRoot(), this.templateLibrary);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerDataSourceListener#OnManagerDataSource_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDataSource_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		ManagerConfigurationEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerDataSourceListener#OnManagerDataSource_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDataSource_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {

		//TODO set output document library
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerDataSourceListener#OnManagerDataSource_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDataSource_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		try
		{
			DataSourceManager dataSourceManager = DataSourceManager.class.cast(sender);
			
			//Get content type reference key to set corresponding document library.
			for(ContentTypeRef contentTypeRef : dataSourceManager)
			{
				dataSourceManager.put(contentTypeRef, this.documentLibrary);
			}
		}
		catch(ClassCastException ex)
		{
			ManagerConfigurationEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public DocumentLibrary put(DataSource key, DocumentLibrary value) 
	{
		if(this.containsKey(key))
		{
			//Set current data source and document library
			this.currentDataSource = key;
			this.documentLibrary = value;
			
			//Start selected data source
			DataSourceManager dataSourceManager = this.dataSourceManagers.get(key);
			
			for(ContentTypeRef contentTypeRef : dataSourceManager)
			{
				//Put document library and it will start to process data
				dataSourceManager.put(contentTypeRef, value);
			}
			
			//Update output data
			value = this.outputData.put(key, value);
			
			//Send event to parent
			ManagerConfigurationEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
		
			//Return full library
			return value;
		}
		
		return null;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends DataSource, ? extends DocumentLibrary> m) 
	{
		for(DataSource dataSource : m.keySet())
		{
			DocumentLibrary documentLibrary =  m.get(dataSource);
			
			//Call put for each data source message.
			if(this.put(dataSource, documentLibrary) == null)
			{
				//Call error event if result is null.
				ManagerConfigurationEvent(new ManagerEventArgs(this, ManagerErrorCode.BAD_CONTENT_TYPE));
			}
		}		
	}
	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public DocumentLibrary remove(Object key) 
	{
		return this.outputData.remove(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() 
	{
		return this.outputData.size();
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
	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<DocumentLibrary> values()
	{
		return this.outputData.values();
	}
}