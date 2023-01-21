package org.httprobot.core.managers.datatypes;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.Parameter;
import org.httprobot.common.contents.ContentType;
import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.common.datatypes.DataSource;
import org.httprobot.common.datatypes.DocumentRoot;
import org.httprobot.common.definitions.Enums.CliNamespace;
import org.httprobot.common.definitions.Enums.ManagerErrorCode;
import org.httprobot.common.definitions.Enums.ManagerEventType;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.unit.Action;
import org.httprobot.core.common.Enums.DataTypeData;
import org.httprobot.core.contents.DocumentLibrary;
import org.httprobot.core.contents.solr.InputDocument;
import org.httprobot.core.controls.datatypes.DataSourceControl;
import org.httprobot.core.controls.datatypes.interfaces.IControlDataSourceListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IDataMappingImpl;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.DataTypeManager;
import org.httprobot.core.managers.contents.ContentTypeRefManager;
import org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRefListener;
import org.httprobot.core.managers.contents.interfaces.IManagerDataViewListener;
import org.httprobot.core.managers.datatypes.interfaces.IManagerActionListener;
import org.httprobot.core.managers.datatypes.interfaces.IManagerDataSourceListener;
import org.httprobot.core.managers.datatypes.interfaces.IManagerDocumentRootListener;
import org.httprobot.core.managers.unit.ActionManager;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * {@link DataSource} message manager class. Inherits {@link DataTypeManager}.
 * <br>
 * Is {@link IControlDataSourceListener}, {@link IManagerActionListener}, {@link IManagerDataViewListener},
 * {@link IManagerDocumentRootListener}.
 * <br>
 * <h1>Input data: Dictionary[Object, InputDocument]</h1> 
 * {@link InputDocument} template document for current {@link DataSource} manager.
 * <br>
 * <h1>Output data: Dictionary[InputDocument, Dictionary[UUID, {@link InputDocument}]]</h1> 
 * {@link InputDocument} with it's UUID as primary key of current {@link DataSource}.
 * <br>
 * @author Joan
*/
@XmlRootElement
public class DataSourceManager 
	extends DataTypeManager<DataSource, DataSourceControl, IManagerDataSourceListener> 
	implements IControlDataSourceListener, IManagerActionListener, 
		IManagerContentTypeRefListener, IManagerDocumentRootListener,
		IDataMappingImpl<ContentTypeRef, DocumentLibrary> {
	
	private static final CliNamespace cliNamespace = CliNamespace.INET;
	
	/**
	 * -6674139343565479397L
	 */
	private static final long serialVersionUID = -6674139343565479397L;
	
	/**
	 * The {@link Action} message manager.
	 */
	protected ActionManager actionManager;
	/**
	 * The {@link ContentTypeRef} message manager.
	 */
	protected ContentTypeRefManager contentTypeRefManager;
	/**
	 * The {@link DocumentRoot} message manager.
	 */
	protected DocumentRootManager documentRootManager;
	/**
	 * The current {@link ContentType}.
	 */
	protected ContentType currentContentType;
	/**
	 * The input data.
	 */
	Set<ContentTypeRef> inputData;
	/**
	 * The output data.
	 */
	Map<ContentTypeRef, DocumentLibrary> outputData;
	
	/**
	 * {@link DataSource} message manager default class constructor.
	 */
	public DataSourceManager()
	{
		super();
	}
	/**
	 * {@link DataSource} message manager class constructor.
	 * @param parent {@link IManagerDataSourceListener} the listener
	 */
	public DataSourceManager(IManagerDataSourceListener parent, DataSource dataSource)
	{
		super(parent, dataSource);

		//Initialize control
		this.control = new DataSourceControl(this, dataSource);

		//Associate control to parent
		this.addCommandOutputListener(this.control);
		
		//Initialize data
		this.inputData = new HashSet<ContentTypeRef>();
		this.outputData = new LinkedHashMap<ContentTypeRef, DocumentLibrary>();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#clear()
	 */
	@Override
	public void clear() 
	{
		this.inputData.clear();
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
	public Set<java.util.Map.Entry<ContentTypeRef, DocumentLibrary>> entrySet() 
	{
		return this.outputData.entrySet();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public DocumentLibrary get(Object key) 
	{
		return this.outputData.get(key);
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getCliNamespace()
	 */
	@Override
	public CliNamespace getCliNamespace() 
	{
		return cliNamespace;
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
	public Iterator<ContentTypeRef> iterator() 
	{
		return this.inputData.iterator();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<ContentTypeRef> keySet() 
	{
		return this.inputData;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e) 
			throws InconsistenMessageException {
		
		switch (e.getCommand()) 
		{
			case MESSAGE:
				System.out.println("\n CLI message: \n" + e.getSource().toString() + "\n");
				return;
				
			case ADD_PARAMETER:
				//Parameter message received. Fire manager event.
				try
				{
					Parameter param = Parameter.class.cast(e.getMessage());
					
					switch (param.getParamType())
					{
						case CONSTANT:						
							ManagerEvent(new ManagerEventArgs(param, ManagerEventType.PARAM_ADDED));								
							break;
//						case BANNED_WORD:
//							this.bannedDictionary.put(param.getParamName(), param.getValue());							
//							ManagerEvent(new ManagerEventArgs(param, ManagerEventType.BANNED_ADDED));								
//							break;
						default:
							break;
					}
				}
				catch(ClassCastException ex)
				{
					ManagerDataSourceEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
				}
			break;
			
			case CONTROL_ACTION:
				try
				{
					Action action = Action.class.cast(e.getMessage());
					
					//Check if Action message is a child of current managed message.
					if(this.control.get(DataTypeData.ACTION) != null ? 
							this.control.get(DataTypeData.ACTION).equals(action) : false) {
						
						//Initialize manager
						this.actionManager = new ActionManager(this, action);
						
						//Associate manager to parent
						addManagerListener(this.actionManager);
						this.actionManager.addManagerListener(this);
						this.actionManager.addManagerActionListener(this);
						
						//Store managers
						this.addChildManager(this.actionManager);
					}
				}
				catch (ClassCastException ex)
				{
					ManagerDataSourceEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
				}
				return;
				
			case CONTROL_DOCUMENT_ROOT:
				//Steps message received
				try
				{
					DocumentRoot documentRoot = DocumentRoot.class.cast(e.getMessage());
					
					//Check if it's the current DocumentRoot
					if(this.control.get(DataTypeData.DOCUMENT_ROOT).equals(documentRoot))
					{
						//Initialize document root message manager
						this.documentRootManager = new DocumentRootManager(this, documentRoot);
						
						//Associate manager to parent
						addManagerListener(this.documentRootManager);
						this.documentRootManager.addManagerListener(this);
						this.documentRootManager.addManagerDocumentRootListener(this);
					
						//Store manager
						addChildManager(this.documentRootManager);
					}
				}
				catch(ClassCastException ex)
				{
					ManagerDataSourceEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
				}
				return;

			case CONTROL_CONTENT_TYPE_REF:
				//ContentTypeRef message received
				try
				{
					ContentTypeRef contentTypeRef = ContentTypeRef.class.cast(e.getMessage());
					
					//Update input data
					this.inputData.add(contentTypeRef);
					
					//Check if it's the current ContentTypeRef
					if(this.control.get(DataTypeData.CONTENT_TYPE_REF).equals(contentTypeRef))
					{
						//Initialize content type reference message manager
						this.contentTypeRefManager = new ContentTypeRefManager(this, contentTypeRef);
						
						//Associate message manager to parent
						this.addManagerListener(this.contentTypeRefManager);
						this.contentTypeRefManager.addManagerListener(this);
						this.contentTypeRefManager.addManagerContentTypeRefListener(this);
						
						//Store manager
						this.addChildManager(this.contentTypeRefManager);
					}
				}
				catch(ClassCastException ex)
				{
					ManagerDataSourceEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
				}
				return;

			default:
				return;
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
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDataSourceListener#OnControlDataSource_Changed(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlDataSource_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDataSourceListener#OnControlDataSource_Init(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlDataSource_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDataSourceListener#OnControlDataSource_Loaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlDataSource_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(this.hasChildManagers())
		{
			//Start each child manager
			while(this.hasNext())
			{
				IManagerImpl manager = this.next();
				manager.start();
			}
			
			ManagerDataSourceEvent(new ManagerEventArgs(this, ManagerEventType.STARTED));
		}
		else
		{
			ManagerDataSourceEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDataSourceListener#OnControlDataSource_Read(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlDataSource_Read(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDataSourceListener#OnControlDataSource_Rendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlDataSource_Rendered(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDataSourceListener#OnControlDataSource_Write(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlDataSource_Write(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.RmlDataTypeManager#OnManagerAction_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAction_Error(Object sender, ManagerEventArgs e)
			throws ManagerException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.RmlDataTypeManager#OnManagerAction_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAction_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException {

		if(sender.equals(this.actionManager))
		{
			//No more than one expected.
			for(HtmlPage pageKey : this.actionManager)
			{
				//Get the returned pages by first request.
				Set<HtmlPage> actiontData = this.actionManager.get(pageKey);
				
				//Put loaded data on document root message manager
				this.documentRootManager.put(actiontData, new LinkedHashMap<InputDocument, HtmlPage>());
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.RmlDataTypeManager#OnManagerAction_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAction_Started(Object sender, ManagerEventArgs e) 
			throws ManagerException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.RmlDataTypeManager#OnManagerAction_WebLoaded(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAction_WebLoaded(Object sender, ManagerEventArgs e) 
			throws ManagerException {
		
		if(sender.equals(this.actionManager))
		{
			
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRefListener#OnManagerContentTypeRef_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentTypeRef_Error(Object sender, ManagerEventArgs e) 
			throws ManagerException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRefListener#OnManagerContentTypeRef_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentTypeRef_Finished(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {
		
		if(sender.equals(this.contentTypeRefManager))
		{
			for(ContentTypeRef contentTypeRef : this.contentTypeRefManager)
			{
				//Set current content type
				this.currentContentType = this.contentTypeRefManager.get(contentTypeRef);	
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRefListener#OnManagerContentTypeRef_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentTypeRef_Started(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {
		
		if(sender.equals(this.contentTypeRefManager))
		{
			for(ContentTypeRef contentTypeRef : this.contentTypeRefManager)
			{
				for(ContentType contentType : this.getContentTypeRoot().getContentType())
				{
					this.contentTypeRefManager.put(contentTypeRef, contentType);	
				}
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.RmlDataTypeManager#OnManagerDataSource_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDataSource_Error(Object sender, ManagerEventArgs e)
			throws ManagerException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.RmlDataTypeManager#OnManagerDataSource_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDataSource_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException {
		
		//Nothing
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.RmlDataTypeManager#OnManagerDataSource_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDataSource_Started(Object sender, ManagerEventArgs e)
			throws ManagerException {
		
		if(e.getSource().equals(this))
		{
			//When all managers has been started. 
			//Set iterator ready to iterate again.
			this.reset();	
		}		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.RmlDataTypeManager#OnManagerDocumentRoot_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDocumentRoot_Error(Object sender, ManagerEventArgs e) 
			throws ManagerException {
		
		//Nothing
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.RmlDataTypeManager#OnManagerDocumentRoot_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDocumentRoot_Finished(Object sender, ManagerEventArgs e) 
			throws ManagerException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.RmlDataTypeManager#OnManagerDocumentRoot_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDocumentRoot_Started(Object sender, ManagerEventArgs e) 
			throws ManagerException {
		
		if(sender.equals(this.documentRootManager))
		{
			//Initialize DocumentRoot input data
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public DocumentLibrary put(ContentTypeRef key, DocumentLibrary value)
	{
		if(this.inputData.contains(key))
		{
			//Set current document library
			this.documentLibrary = value;
			
			//Start putting data on action's manager
			this.actionManager.put(null, null);
			
			//Update output data.
			this.outputData.put(key, value);
			
			//Send event to parent
			ManagerDataSourceEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
		}
		return value;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends ContentTypeRef, ? extends DocumentLibrary> m) 
	{
		for(ContentTypeRef contentTypeRef : m.keySet())
		{
			DocumentLibrary documentLibrary = m.get(contentTypeRef);
			
			//Call put method for each content type reference.
			if(this.put(contentTypeRef, documentLibrary) == null)
			{
				ManagerDataSourceEvent(new ManagerEventArgs(this, ManagerErrorCode.BAD_CONTENT_TYPE));
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
		return this.inputData.size();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.RmlDataTypeManager#start()
	 */
	@Override
	public void start() 
	{
		this.control.controlMessage(this.message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.RmlDataTypeManager#stop()
	 */
	@Override
	public void stop() 
	{
		
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