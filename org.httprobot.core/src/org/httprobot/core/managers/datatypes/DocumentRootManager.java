/**
 * 
 */
package org.httprobot.core.managers.datatypes;

import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.ContentType;
import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.common.contents.FieldRef;
import org.httprobot.common.datatypes.Document;
import org.httprobot.common.datatypes.DocumentRoot;
import org.httprobot.common.datatypes.FieldRoot;
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
import org.httprobot.core.contents.FieldLibrary;
import org.httprobot.core.contents.solr.InputDocument;
import org.httprobot.core.controls.datatypes.DocumentRootControl;
import org.httprobot.core.controls.datatypes.interfaces.IControlDocumentRootListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IDataMappingImpl;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.DataTypeManager;
import org.httprobot.core.managers.contents.ContentTypeRefManager;
import org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRefListener;
import org.httprobot.core.managers.datatypes.interfaces.IManagerActionListener;
import org.httprobot.core.managers.datatypes.interfaces.IManagerDocumentListener;
import org.httprobot.core.managers.datatypes.interfaces.IManagerDocumentRootListener;
import org.httprobot.core.managers.datatypes.interfaces.IManagerFieldRootListener;
import org.httprobot.core.managers.unit.ActionManager;

import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * {@link DocumentRoot} message manager class. Inherits {@link DataTypeManager}.
 * <br>
 * It's {@link IControlDocumentRootListener}, {@link IManagerDocumentListener} 
 * and {@link IDataMappingImpl}.
 * <br>
 * <h1>Input data:</h1> Dictionary[Object, Dictionary[WebRequest, HtmlPage]].
 * The {@link HtmlPage}'s result from {@link DataSourceManager}.
 * <br>
 * <h1>Output data:</h1> Dictionary[Dictionary[WebRequest, HtmlPage], Dictionary[HtmlPage, InputDocument]].
 * @author joan
 *
 */
@XmlRootElement
public class DocumentRootManager 
	extends DataTypeManager<DocumentRoot, DocumentRootControl, IManagerDocumentRootListener>
	implements IControlDocumentRootListener, IManagerDocumentListener,
		IManagerContentTypeRefListener, IManagerFieldRootListener, IManagerActionListener,
		IDataMappingImpl<Set<HtmlPage>, Map<InputDocument, HtmlPage> > {
	
	/**
	 * -1566602668281667366L
	 */
	private static final long serialVersionUID = -1566602668281667366L;
	/**
	 * The {@link Action} message manager.
	 */
	protected ActionManager actionManager;
	/**
	 * The {@link ContentTypeRef} message manager.
	 */
	protected ContentTypeRefManager contentTypeRefManager;
	/**
	 * The {@link FieldRoot} message manager
	 */
	protected FieldRootManager fieldsManager;
	/**
	 * Current input data being processed.
	 */
	Set<HtmlPage> currentInput;
	/**
	 * Current output data being processed.
	 */
	Map<InputDocument, HtmlPage> currentOutput;
	/**
	 * Current request output data result.
	 */
	WebRequest currentRequest;
	/**
	 * Current response output data result.
	 */
	HtmlPage currentResponse;
	/**
	 * The {@link DocumentManager} with it's {@link Document}.
	 */
	Map<Document, DocumentManager> documentManagers;
	/**
	 * The input data.
	 */
	Set<Set<HtmlPage>> inputData;
	/**
	 * The output data.
	 */
	Map<Set<HtmlPage>, Map<InputDocument, HtmlPage>> outputData;
	
	/**
	 * {@link DocumentRoot} message manager default class constructor.
	 */
	public DocumentRootManager()
	{
		super();
	}
	/**
	 * {@link DocumentRoot} message manager class constructor
	 * @param parent {@link IManagerDocumentRootListener} the parent
	 * @param documentRoot {@link DocumentRoot} the message
	 */
	public DocumentRootManager(IManagerDocumentRootListener parent, DocumentRoot documentRoot) 
	{
		super(parent, documentRoot);
		
		//Initialize members
		this.documentManagers = new Hashtable<Document, DocumentManager>();
		this.control = new DocumentRootControl(this, documentRoot);

		this.inputData = new HashSet<Set<HtmlPage>>();
		this.outputData = new LinkedHashMap<Set<HtmlPage>, Map<InputDocument,HtmlPage>>();
	
		//Add listeners
		addCommandOutputListener(this.control);
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
	public Set<java.util.Map.Entry<Set<HtmlPage>, Map<InputDocument, HtmlPage>>> entrySet() 
	{
		return this.outputData.entrySet();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public Map<InputDocument, HtmlPage> get(Object key) 
	{
		return this.outputData.get(key);
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
	public Iterator<Set< HtmlPage>> iterator() 
	{
		return this.inputData.iterator();
	}	
	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<Set<HtmlPage>> keySet() 
	{
		return this.inputData;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e) 
			throws InconsistenMessageException {
		
		switch (e.getCommand()) 
		{
			case CONTROL_CONTENT_TYPE_REF:
				
				try
				{
					ContentTypeRef contentTypeRef = ContentTypeRef.class.cast(e.getMessage());
					
					//Check only for first level documents
					if(this.control.get(DataTypeData.CONTENT_TYPE_REF) != null ?
							this.control.get(DataTypeData.CONTENT_TYPE_REF).equals(contentTypeRef) : false) {
						
						//Initialize content type reference message manager
						this.contentTypeRefManager = new ContentTypeRefManager(this, contentTypeRef);
						
						//Associate manager to parent
						addCommandOutputListener(this.contentTypeRefManager);
						this.contentTypeRefManager.addManagerListener(this);
						this.contentTypeRefManager.addManagerContentTypeRefListener(this);
					
						//Store manager
						addChildManager(this.contentTypeRefManager);
					}
				}
				catch(ClassCastException ex)
				{
					ManagerDocumentRootEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
				}
				return;
				
			case CONTROL_ACTION:
				
				try
				{
					Action action = Action.class.cast(e.getMessage());
					
					if(this.control.get(DataTypeData.ACTION) != null ?
							this.control.get(DataTypeData.ACTION).equals(action) : false) {
						
						//Initialize Action message manager
						this.actionManager = new ActionManager(this, action);
						
						//Associate manager to parent
						addCommandOutputListener(this.actionManager);
						this.actionManager.addManagerListener(this);
						this.actionManager.addManagerActionListener(this);
					
						//Store manager
						addChildManager(this.actionManager);
					}
				}
				catch(ClassCastException ex)
				{
					ManagerDocumentRootEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
				}
				return;
				
			case CONTROL_FIELDS:
				
				try
				{
					FieldRoot fieldRoot = FieldRoot.class.cast(e.getMessage());
					
					if(this.control.get(DataTypeData.FIELD_ROOT) != null ? 
							this.control.get(DataTypeData.FIELD_ROOT).equals(fieldRoot) : false) {
						
						//Initialize Fields message manager
						this.fieldsManager = new FieldRootManager(this, fieldRoot);
						
						//Associate manager to parent
						addCommandOutputListener(this.fieldsManager);
						this.fieldsManager.addManagerListener(this);
						this.fieldsManager.addManagerFieldRootListener(this);
					
						//Store manager
						addChildManager(this.fieldsManager);
					}
				}
				catch(ClassCastException ex)
				{
					ManagerDocumentRootEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
				}
				return;
				
			case CONTROL_DOCUMENT:
				
				try
				{
					//Cast sources
					Document document = Document.class.cast(e.getMessage());
					
					//Check only for first level documents
					if(this.control.get(DataTypeData.DOCUMENT) != null ? 
							this.control.get(DataTypeData.DOCUMENT).equals(document) : false) {
						
						//Instantiate document managers
						DocumentManager documentManager = new DocumentManager(this, document);
						
						//Associate manager to parent
						addCommandOutputListener(documentManager);
						documentManager.addManagerListener(this);
						documentManager.addManagerDocumentListener(this);
						
						//Store manager
						this.documentManagers.put(document, documentManager);
						addChildManager(documentManager);
					}
				}
				catch(ClassCastException ex)
				{
					ManagerDocumentRootEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
				}
				return;
	
			default:
				return;
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e) 
			throws InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDocumentRootListener#OnControlDocumentRoot_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocumentRoot_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDocumentRootListener#OnControlDocumentRoot_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocumentRoot_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {		
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDocumentRootListener#OnControlDocumentRoot_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocumentRoot_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(this.hasChildManagers())
		{
			//Start each child manager
			while(this.hasNext())
			{
				IManagerImpl manager = this.next();
				manager.start();
			}
			
			ManagerDocumentRootEvent(new ManagerEventArgs(this, ManagerEventType.STARTED));
		}
		else
		{
			ManagerDocumentRootEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDocumentRootListener#OnControlDocumentRoot_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocumentRoot_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDocumentRootListener#OnControlDocumentRoot_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocumentRoot_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDocumentRootListener#OnControlDocumentRoot_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocumentRoot_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnManagerAction_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAction_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//Send error event to parent
		ManagerDocumentRootEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnManagerAction_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAction_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		/*
		 * Start managing Document messages.
		 * Initializes new Document message manager output data.
		 * */
		
		if(sender.equals(this.actionManager))
		{
			for(Document document : this.documentManagers.keySet())
			{
				//Get the manager
				DocumentManager documentManager = this.documentManagers.get(document);
				
				//Initialize new Document manager output data
				Map<InputDocument, HtmlPage> documentOutputData = new LinkedHashMap<InputDocument, HtmlPage>();
				
				//Put data to document managers. Current output has been updated on WebLoaded event.
				documentManager.put(this.currentOutput, documentOutputData);
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnManagerAction_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAction_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		if(sender.equals(this.actionManager))
		{
			
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnManagerAction_WebLoaded(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAction_WebLoaded(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		/*
		 * For each new page received initialize the root template document.
		 * Assign it with current response inside current output data.
		 * Then add data to Fields message manager.
		 * */
		
		if(sender.equals(this.actionManager))
		{
			//Set current results
			this.currentRequest = e.getCurrentRequest();
			this.currentResponse = e.getCurrentResponse();

			//Get the content type reference for root document
			for(ContentTypeRef contentTypeRef : this.contentTypeRefManager)
			{
				//Get new root template document.
				InputDocument rootTemplateDocument = this.getTemplateLibrary().get(contentTypeRef);

				//Update current output data.
				this.currentOutput.put(rootTemplateDocument, this.currentResponse);
				
				//Store new document with current HtmlPage as primary key.
				//It will be processed on fields message manager child.
				this.getDocumentLibrary().put(this.currentResponse, rootTemplateDocument);
				
				//Put data to fields manager the get data
				this.fieldsManager.put(rootTemplateDocument, e.getCurrentResponse());
			}

		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRefListener#OnManagerContentTypeRef_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentTypeRef_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//Send error event to parent
		ManagerDocumentRootEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRefListener#OnManagerContentTypeRef_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentTypeRef_Finished(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {
		
		/*
		 * Initialize document library. 
		 * It will break inheritance with DataSourceManager's document library.
		 * */
		
		if(sender.equals(this.contentTypeRefManager))
		{
			for(ContentTypeRef contentTypeRef : this.contentTypeRefManager)
			{
				//Get the template document
				InputDocument templateDocument = this.getTemplateLibrary().get(contentTypeRef);
				
				//Get the template fields
				FieldLibrary<FieldRef> templateFields = this.getTemplateLibrary().getFieldsLibrary();
				
				//Initialize new document library for DocumentRootManager. 
				this.documentLibrary = new DocumentLibrary(contentTypeRef, templateDocument, templateFields);
				
				//Only one expected
				break;
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
			//Give to content type reference message manager all content types until found.
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
	 * @see org.httprobot.core.managers.DataTypeManager#OnManagerDocument_DocumentCompleted(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDocument_DocumentCompleted(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {	
		
		//Check if event comes from a child document message manager.
		if(this.documentManagers.containsValue(e.getSource()))
		{
			//Get Document message manager result document.
			InputDocument childDocument = e.getInputDocument();
			
			//Get current root document.
			InputDocument currentDocument = this.getDocumentLibrary().get(this.currentResponse);
			
			//Add document manager result to current root document.
			currentDocument.addChildDocument(childDocument);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnManagerDocument_DocumentInitialized(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDocument_DocumentInitialized(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.interfaces.IManagerStepListener#OnManagerStep_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDocument_Error(Object sender, ManagerEventArgs e) 
			throws ManagerException {

		//Send event to parent
		ManagerDocumentRootEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.interfaces.IManagerStepListener#OnManagerStep_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDocument_Finished(Object sender, ManagerEventArgs e) 
			throws ManagerException {
		
//		ManagerStepsEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.interfaces.IManagerStepListener#OnManagerStep_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDocument_Started(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnManagerDocumentRoot_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDocumentRoot_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//Nothing
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnManagerDocumentRoot_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDocumentRoot_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//Nothing
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnManagerDocumentRoot_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDocumentRoot_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//When all managers has been started. 
		//Set iterator ready to iterate again.
		this.reset();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnManagerFields_DocumentCompleted(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRoot_DocumentCompleted(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {
		
		if(sender.equals(this.fieldsManager))
		{
			//Iterate through HtmlPage keys
			for(InputDocument inputDocument  : this.fieldsManager)
			{
				//Get Fields message manager output document
				HtmlPage pageKey = this.fieldsManager.get(inputDocument);
				
				//Update document on current document library.
				this.getDocumentLibrary().get(pageKey).addInputDocument(inputDocument);
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnManagerFields_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRoot_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//Send error event to parent
		ManagerDocumentRootEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnManagerFields_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRoot_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		if(sender.equals(this.fieldsManager))
		{
			
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnManagerFields_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRoot_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	
		if(sender.equals(this.fieldsManager))
		{
			
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Map<InputDocument, HtmlPage> put(Set<HtmlPage> key,	Map<InputDocument, HtmlPage> value) {
		
		//Set inherited data
		this.inputData.add(key);
		
		//Set pointers
		this.currentInput = key;
		this.currentOutput = value;
		
		//Iterate through current response pages.
		for(HtmlPage htmlPage : key)
		{
			//Initialize Action message manager output data
			Set<HtmlPage> actionOutput = new HashSet<HtmlPage>();
			
			//Set page key to current action manager with new HashSet as output value.
			this.actionManager.put(htmlPage, actionOutput);
		}
		
		value = this.outputData.put(key, value);
		
		//Send event to parent
		ManagerDocumentRootEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
		
		return value;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends Set<HtmlPage>, ? extends Map<InputDocument, HtmlPage>> m) {
		
		for(Set<HtmlPage> pagesKey : m.keySet())
		{
			Map<InputDocument, HtmlPage> map = m.get(pagesKey);
			
			//Call put method for each map.
			if(this.put(pagesKey, map) == null)
			{
				ManagerDocumentRootEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
			}
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public Map<InputDocument, HtmlPage> remove(Object key) 
	{
		this.inputData.remove(key);
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
	 * @see org.httprobot.core.managers.Manager#start()
	 */
	@Override
	public void start() 
	{
		this.control.controlMessage(this.message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.Manager#stop()
	 */
	@Override
	public void stop() 
	{
	
	}
	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<Map<InputDocument, HtmlPage>> values() 
	{
		return null;
	}	
}
