package org.httprobot.core.precursor;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.RML;
import org.httprobot.common.config.Configuration;
import org.httprobot.common.config.DataSourceList;
import org.httprobot.common.config.DataSourceRef;
import org.httprobot.common.config.ServiceConnection;
import org.httprobot.common.contents.ContentType;
import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.common.contents.FieldRef;
import org.httprobot.common.datatypes.DataSource;
import org.httprobot.common.definitions.Enums.CliNamespace;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.exceptions.ContentException;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.contents.DocumentLibrary;
import org.httprobot.core.contents.FieldLibrary;
import org.httprobot.core.contents.TemplateLibrary;
import org.httprobot.core.contents.events.ContentEventArgs;
import org.httprobot.core.contents.interfaces.IInputDocumentListener;
import org.httprobot.core.contents.solr.InputDocument;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.interfaces.IPrecursorListener;
import org.httprobot.core.managers.config.ConfigurationManager;
import org.httprobot.core.managers.config.ServiceConnectionManager;
import org.httprobot.core.managers.config.interfaces.IManagerConfigurationListener;
import org.httprobot.core.managers.config.interfaces.IManagerServiceConnectionListener;

/**
 * Precursor class. Inherits {@link RML}. 
 * <br>
 * It's {@link IManagerImpl}, {@link IManagerConfigurationListener}, 
 * {@link IManagerServiceConnectionListener} and {@link IInputDocumentListener}.
 * <br>  
 * @author Joan 
 */
@XmlRootElement
public class Precursor 
	extends RML 
	implements IManagerImpl, IManagerConfigurationListener, 
		IManagerServiceConnectionListener, IInputDocumentListener
{
	
	private static CliNamespace cli_namespace = CliNamespace.PRECURSOR;
	/**
	 * -6888744731930123079L
	 */
	private static final long serialVersionUID = -6888744731930123079L;
	/**
	 * Precursor's configuration sheet
	 */
	Configuration configuration;
	/**
	 * Configuration message manager
	 */
	ConfigurationManager configurationManager;
	/**
	 * Precursor listeners
	 */
	Vector<IPrecursorListener> precursorListeners;
	/**
	 * Service connection manager
	 */
	ServiceConnectionManager serviceConnectionManager;
	/**
	 * Precursor default class constructor.
	 */
	public Precursor(IPrecursorListener parent, ServiceConnection serviceConnection)
	{
		//Set inherited data
		this.setUuid(null);
		this.setInherited(false);
		this.setDestinationPath(parent.getDestinationPath());
		this.setRuntimeOptions(parent.getRuntimeOptions());
		
		//Initialize members
		this.configuration = new Configuration();
		this.configuration.setUuid(null);
		this.configuration.setInherited(true);
		this.configuration.setDestinationPath(getDestinationPath());
		this.configuration.setRuntimeOptions(getRuntimeOptions());
		
		this.precursorListeners = new Vector<IPrecursorListener>();
		this.serviceConnectionManager = new ServiceConnectionManager(this, serviceConnection);
	
		//Associate managers to parent
		this.serviceConnectionManager.addManagerServiceConnectionListener(this);
		this.serviceConnectionManager.addManagerListener(this);
		this.addCommandOutputListener(this.serviceConnectionManager);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.interfaces.IManagerListener#getBannedDictionary()
	 */
	@Override
	public Map<String, String> getParameterBannedWords() {
		
		try
		{
			return this.configurationManager.getParameterBannedWords();
		} 
		catch (NotImplementedException e) 
		{
			e.printStackTrace();
		} 
		catch (ManagerException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getCliNamespace()
	 */
	@Override
	public CliNamespace getCliNamespace() 
	{
		return Precursor.cli_namespace;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#getContentTypeRoot()
	 */
	@Override
	public ContentTypeRoot getContentTypeRoot() 
			throws NotImplementedException, ManagerException {
		
		return this.getSystemContentTypeRoot();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.config.interfaces.IManagerConfigurationListener#getDataSourceList()
	 */
	@Override
	public DataSourceList getDataSourceList() 
			throws NotImplementedException 	{
		
		return this.serviceConnectionManager.getDataSourceList();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.config.interfaces.IManagerConfigurationListener#getDataSources()
	 */
	@Override
	public Dictionary<UUID, DataSource> getDataSources() 
			throws NotImplementedException {
		
		return this.serviceConnectionManager.getDataSources();
	}

	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#getDocumentLibrary()
	 */
	@Override
	public DocumentLibrary getDocumentLibrary() 
			throws NotImplementedException, ManagerException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.interfaces.IManagerImpl#getParamDictionary()
	 */
	@Override
	public Map<String, String> getParameterConstants() 
			throws NotImplementedException, ManagerException {
		
		try 
		{
			return this.configurationManager.getParameterConstants();
		} 
		catch (NotImplementedException e) 
		{
			e.printStackTrace();
		} 
		catch (ManagerException e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.config.interfaces.IManagerConfigurationListener#getSystemContentTypeRoot()
	 */
	@Override
	public ContentTypeRoot getSystemContentTypeRoot() 
			throws NotImplementedException 	{
		
		return this.serviceConnectionManager.getSystemContentTypeRoot();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#getInputDocumentHandler()
	 */
	@Override
	public TemplateLibrary getTemplateLibrary() 
			throws NotImplementedException, ManagerException {
		
		return this.configurationManager.getTemplateLibrary();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e) 
			throws InconsistenMessageException 	{
		
		switch (e.getCommand()) 
		{
			case MESSAGE:
				break;

			default:
				break;
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e)
	{
	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.interfaces.IInputDocumentListener#OnInputDocument_DocumentAdded(java.lang.Object, org.httprobot.core.contents.events.ContentEventArgs)
	 */
	@Override
	public void OnInputDocument_DocumentAdded(Object sender, ContentEventArgs e)
			throws NotImplementedException, ContentException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.interfaces.IInputDocumentListener#OnInputDocument_DocumentCompleted(java.lang.Object, org.httprobot.core.contents.events.ContentEventArgs)
	 */
	@Override
	public void OnInputDocument_DocumentCompleted(Object sender, ContentEventArgs e) 
			throws NotImplementedException, ContentException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.interfaces.IInputDocumentListener#OnInputDocument_DocumentRemoved(java.lang.Object, org.httprobot.core.contents.events.ContentEventArgs)
	 */
	@Override
	public void OnInputDocument_DocumentRemoved(Object sender, ContentEventArgs e) 
			throws NotImplementedException, ContentException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.interfaces.IInputDocumentListener#OnInputDocument_FieldAdded(java.lang.Object, org.httprobot.core.contents.events.ContentEventArgs)
	 */
	@Override
	public void OnInputDocument_FieldAdded(Object sender, ContentEventArgs e)
			throws NotImplementedException, ContentException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.interfaces.IInputDocumentListener#OnInputDocument_FieldChanged(java.lang.Object, org.httprobot.core.contents.events.ContentEventArgs)
	 */
	@Override
	public void OnInputDocument_FieldChanged(Object sender, ContentEventArgs e)
			throws NotImplementedException, ContentException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.interfaces.IInputDocumentListener#OnInputDocument_FieldRemoved(java.lang.Object, org.httprobot.core.contents.events.ContentEventArgs)
	 */
	@Override
	public void OnInputDocument_FieldRemoved(Object sender, ContentEventArgs e)
			throws NotImplementedException, ContentException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#OnManager_BannedAdded(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManager_BannedAdded(Object sender, ManagerEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#OnParamAdded(java.lang.Object, org.httprobot.core.events.InetEventArgs)
	 */
	@Override
	public void OnManager_ParamAdded(Object sender, ManagerEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.config.interfaces.IManagerConfigurationListener#OnManagerConfiguration_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerConfiguration_Error(Object sender, ManagerEventArgs e) 
			throws NotImplementedException, ManagerException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.config.interfaces.IManagerConfigurationListener#OnManagerConfiguration_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerConfiguration_Finished(Object sender, ManagerEventArgs e) 
			throws NotImplementedException, ManagerException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.config.interfaces.IManagerConfigurationListener#OnManagerConfiguration_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerConfiguration_Started(Object sender, ManagerEventArgs e)
			throws NotImplementedException, ManagerException {
		
		if(sender.equals(this.configurationManager))
		{
			for(DataSource dataSource : this.configurationManager)
			{
				//Get data source's content type reference
				ContentTypeRef contentTypeRef = dataSource.getContentTypeRef();
				
				//Look for matching content type
				for(ContentType contentType : this.configuration.getContentTypeRoot().getContentType())
				{
					//Match UUID
					if(contentTypeRef.getUuid().equals(contentType.getUuid()))
					{	
						InputDocument templateDocument = this.getTemplateLibrary().get(contentTypeRef);
						FieldLibrary<FieldRef> fieldTemplates = this.getTemplateLibrary().getFieldsLibrary();
						
						//Initialize document library
						DocumentLibrary documentLibrary = new DocumentLibrary(contentTypeRef, templateDocument, fieldTemplates);
						documentLibrary.addInputDocumentListener(this);
						
						//Put data
						this.configurationManager.put(dataSource, documentLibrary);
						
						break;
					}
				}
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.config.interfaces.IManagerServiceConnectionListener#OnManagerServiceConnection_DataSource(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerServiceConnection_DataSource(Object sender, ManagerEventArgs e) 
			throws NotImplementedException, ManagerException {
		
		if(sender.equals(this.serviceConnectionManager))
		{
			//Store data source on configuration sheet
			this.configuration.getDataSource().add(e.getDataSource());
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.config.interfaces.IManagerServiceConnectionListener#OnManagerServiceConnection_DataSourceList(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerServiceConnection_DataSourceList(Object sender, ManagerEventArgs e) 
			throws NotImplementedException, ManagerException {
		
		if(sender.equals(this.serviceConnectionManager))
		{
			//Get dataSources
			ArrayList<String> queryDataSources = new ArrayList<String>();
			
			for(DataSourceRef dataSourceRef : this.serviceConnectionManager.getDataSourceList().getDataSourceRef())	
			{
				String value = dataSourceRef.getServerInfoID().toString();
				
				//TODO Some criteria to select DataSource
				queryDataSources.add(value);
			}
			
			//Call for requested data sources
			this.serviceConnectionManager.LoadTargetDataSources(queryDataSources);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.config.interfaces.IManagerServiceOptionsListener#OnManagerServiceOptions_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerServiceConnection_Error(Object sender, ManagerEventArgs e)
			throws NotImplementedException, ManagerException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.config.interfaces.IManagerServiceOptionsListener#OnManagerServiceOptions_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerServiceConnection_Finished(Object sender, ManagerEventArgs e) 
			throws NotImplementedException, ManagerException {
	
		//Once Service connection has finished start Precursor configuration manager
		if(e.getSource().equals(this.serviceConnectionManager))
		{
			//Initialize Configuration message manager
			this.configurationManager = new ConfigurationManager(this, this.configuration);
			
			//Add listeners
			this.configurationManager.addManagerConfigurationListener(this);
			this.configurationManager.addManagerListener(this);
			this.addCommandOutputListener(this.configurationManager);
			
			//Start Configuration message manager
			this.configurationManager.start();
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.config.interfaces.IManagerServiceOptionsListener#OnManagerServiceOptions_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerServiceConnection_Started(Object sender, ManagerEventArgs e) 
			throws NotImplementedException, ManagerException {
		
		if(sender.equals(this.serviceConnectionManager))
		{
			if(this.serviceConnectionManager.getDataSourceList() == null)
			{
				
			}
			if(this.serviceConnectionManager.getDataSourceList() == null 
					|| this.serviceConnectionManager.getSystemContentTypeRoot() == null)
			{
				CliTools.ThrowManagerException(this, "Precursor.OnManagerServiceConnection_Started: Some bad loaded data");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.config.interfaces.IManagerServiceConnectionListener#OnManagerServiceConnection_OperationLoaded(java.lang.Object, org.httprobot.core.events.ServiceLoaderEventArgs)
	 */
	@Override
	public void OnManagerServiceConnection_SystemContentTypeRoot(Object sender, ManagerEventArgs e) 
			throws NotImplementedException,	 ManagerException {
		
		if(sender.equals(this.serviceConnectionManager))
		{
			this.configuration.setContentTypeRoot(e.getContentTypeRoot());
		}
	}
	/**
	 * Starts sequence process
	 */
	public void start()
	{
		this.serviceConnectionManager.start();	
	}
	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public IManagerImpl next() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#stop()
	 */
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#reset()
	 */
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#addChildManager(org.httprobot.core.interfaces.IManagerImpl)
	 */
	@Override
	public void addChildManager(IManagerImpl manager)
			throws NotImplementedException, ManagerException {
		// TODO Auto-generated method stub
		
	}
}