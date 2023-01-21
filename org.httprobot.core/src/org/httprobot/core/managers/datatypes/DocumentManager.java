/**
 * 
 */
package org.httprobot.core.managers.datatypes;

import java.util.Collection;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.ContentType;
import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.common.contents.FieldRef;
import org.httprobot.common.datatypes.Document;
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
import org.httprobot.core.controls.datatypes.DocumentControl;
import org.httprobot.core.controls.datatypes.interfaces.IControlDocumentListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IDataMappingImpl;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.DataTypeManager;
import org.httprobot.core.managers.contents.ContentTypeRefManager;
import org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRefListener;
import org.httprobot.core.managers.datatypes.interfaces.IManagerActionListener;
import org.httprobot.core.managers.datatypes.interfaces.IManagerFieldRootListener;
import org.httprobot.core.managers.datatypes.interfaces.IManagerDocumentListener;
import org.httprobot.core.managers.unit.ActionManager;

import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * {@link Document} message manager class. Inherits {@link DataTypeManager}. 
 * <br>
 * It's a {@link IControlDocumentListener}, {@link IManagerDocumentListener}, 
 * {@link IManagerActionListener} and {@link IManagerFieldRootListener}.
 * <br>
 * <h1>Input data</h1>{@link Dictionary} of {@link HtmlPage} with {@link WebRequest} as primary keys. 
 * <br>
 * <h1>Output data</h1>{@link Dictionary} of {@link InputDocument} with {@link HtmlPage} as primary keys.
 * <br>
 * @author joan
 *
 */
@XmlRootElement
public class DocumentManager 
	extends DataTypeManager<Document, DocumentControl, IManagerDocumentListener> 
	implements IControlDocumentListener, IManagerDocumentListener, 
		IManagerActionListener, IManagerFieldRootListener, IManagerContentTypeRefListener,
		IDataMappingImpl<Map<InputDocument, HtmlPage>, Map<InputDocument, HtmlPage>> {
	
	/**
	 * 4735448736231097776L
	 */
	private static final long serialVersionUID = 4735448736231097776L;	
	/**
	 * Manages {@link Action} message.
	 */
	protected ActionManager actionManager;
	/**
	 * Manages {@link ContentTypeRef} message.
	 */
	protected ContentTypeRefManager contentTypeRefManager;
	/**
	 * Manages {@link FieldRoot} message
	 */
	protected FieldRootManager fieldRootManager;
	/**
	 * Current input data being processed.
	 */
	Map<InputDocument, HtmlPage> currentInput;
	/**
	 * Current output data being processed
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
	Set<Map<InputDocument, HtmlPage>> inputData;
	/**
	 * The output data.
	 */
	Map<Map<InputDocument, HtmlPage>, Map<InputDocument, HtmlPage>> outputData;
	/**
	 * {@link Document} message manager default class constructor.
	 */
	public DocumentManager() 
	{  
		super();
	}
	/**
	 * {@link Document} message manager class constructor.
	 * @param parent {@link IManagerDocumentListener} the parent
	 * @param document {@link Document} the step
	 */
	public DocumentManager(IManagerDocumentListener parent, Document document) 
	{
		super(parent, document);

		//Initialize control member.
		this.control = new DocumentControl(this, document);
		
		//Initialize data members.
		this.inputData = new HashSet<Map<InputDocument,HtmlPage>>();
		this.outputData = new LinkedHashMap<Map<InputDocument,HtmlPage>, Map<InputDocument, HtmlPage>>();
		
		//Add listeners.
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
	public Set<java.util.Map.Entry<Map<InputDocument, HtmlPage>, Map<InputDocument, HtmlPage>>> entrySet()
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
		return this.inputData == null ? true : this.inputData.isEmpty();
	}
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Map<InputDocument, HtmlPage>> iterator() 
	{
		return this.inputData.iterator();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<Map<InputDocument, HtmlPage>> keySet()
	{
		return this.inputData;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.io.MarshalObject#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e) 
			throws InconsistenMessageException {
		
		switch (e.getCommand()) 
		{
			case CONTROL_ACTION:
				
				try
				{
					Action action = Action.class.cast(e.getMessage());
					
					if(this.control.get(DataTypeData.ACTION) != null ? 
							this.control.get(DataTypeData.ACTION).equals(action) : false) {
						
						//Instantiate manager of current action
						this.actionManager = new ActionManager(this, action);
						
						//Associate action manager to current manager
						this.addManagerListener(this.actionManager);
						this.actionManager.addManagerListener(this);
						this.actionManager.addManagerActionListener(this);
						
						//Store manager
						addChildManager(this.actionManager);
					}
				}
				catch(ClassCastException ex)
				{
					ManagerDocumentEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
				}
				return;
		
			case CONTROL_CONTENT_TYPE_REF:
				
				try
				{
					ContentTypeRef contentTypeRef = ContentTypeRef.class.cast(e.getMessage());
					
					if(this.control.get(DataTypeData.CONTENT_TYPE_REF) != null ?
							this.control.get(DataTypeData.CONTENT_TYPE_REF).equals( contentTypeRef) : false) {

						//Instantiate manager of current content type reference
						this.contentTypeRefManager = new ContentTypeRefManager(this, contentTypeRef);
						
						//Associate manager to current manager
						this.addManagerListener(this.contentTypeRefManager);
						this.contentTypeRefManager.addManagerListener(this);
						this.contentTypeRefManager.addManagerContentTypeRefListener(this);
						
						//Store manager
						addChildManager(this.contentTypeRefManager);
					}
				}
				catch(ClassCastException ex)
				{
					ManagerDocumentEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
				}
				return;
				
			case CONTROL_DOCUMENT:
				
				try
				{
					//Cast source
					Document document = Document.class.cast(e.getMessage());
					
					//Check only for first level documents
					if(this.control.get(DataTypeData.DOCUMENT) != null ? 
							this.control.get(DataTypeData.DOCUMENT).equals(document) : false) {
						
						//Instantiate document managers
						DocumentManager documentManager = new DocumentManager(this, document);
						documentManager.addManagerListener(this);
						documentManager.addManagerDocumentListener(this);
						
						//Store manager
						this.documentManagers.put(document, documentManager);
						addChildManager(this.actionManager);						
					}
				}
				catch(ClassCastException ex)
				{
					ManagerDocumentEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
				}
				return;

			case CONTROL_FIELDS:
				
				try
				{
					FieldRoot fieldRoot = FieldRoot.class.cast(e.getMessage());
					
					if(this.control.get(DataTypeData.FIELD_ROOT) != null ?
							this.control.get(DataTypeData.FIELD_ROOT).equals(fieldRoot) : false) {
						
						//Instantiate fields document manager
						this.fieldRootManager = new FieldRootManager(this, fieldRoot);
						
						//Associate fields manager to current manager
						this.addManagerListener(this.fieldRootManager);
						this.fieldRootManager.addManagerListener(this);
						this.fieldRootManager.addManagerFieldRootListener(this);
						

						//Store manager
						addChildManager(this.fieldRootManager);
					}
				}
				catch(ClassCastException ex)
				{
					ManagerDocumentEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
				}
				return;
				
			default:
				return;
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.RmlManager#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e) 
			throws InconsistenMessageException {
		
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IRmlStepListener#OnControlStep_Changed(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlDocument_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IRmlStepListener#OnControlStep_Init(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlDocument_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IRmlStepListener#OnControlStep_Loaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlDocument_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(this.hasChildManagers())
		{
			//Start each child manager
			while(this.hasNext())
			{
				IManagerImpl manager = this.next();
				manager.start();
			}
			
			ManagerDocumentEvent(new ManagerEventArgs(this, ManagerEventType.STARTED));
		}
		else
		{
			ManagerDocumentEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IRmlStepListener#OnControlStep_Read(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlDocument_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IRmlStepListener#OnControlStep_Rendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlDocument_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IRmlStepListener#OnControlStep_Write(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlDocument_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {		
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.interfaces.IManagerActionListener#OnManagerAction_RequestError(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAction_Error(Object sender, ManagerEventArgs e) 
			throws ManagerException {
		
		//Send event to parent message manager
		ManagerDocumentEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.interfaces.IManagerActionListener#OnManagerAction_RequestFinished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAction_Finished(Object sender, ManagerEventArgs e) 
			throws ManagerException {
		
		/*
		 * Start managing Document message children.
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
	 * @see org.httprobot.core.managers.interfaces.IManagerActionListener#OnManagerAction_RequestStarted(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAction_Started(Object sender, ManagerEventArgs e) 
			throws ManagerException {
		
		if(sender.equals(this.actionManager))
		{

		}		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.interfaces.IManagerActionListener#OnManagerAction_ResponseReceived(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAction_WebLoaded(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {
		
		if(sender.equals(this.actionManager))
		{
			//Set current results
			this.currentRequest = e.getCurrentRequest();
			this.currentResponse = e.getCurrentResponse();
			
			System.out.println("\n Current web request: " + this.currentRequest.getUrl().toString());

			//Get the content type reference for document
			for(ContentTypeRef contentTypeRef : this.contentTypeRefManager)
			{
				//Get new template document.
				InputDocument templateDocument = this.getTemplateLibrary().get(contentTypeRef);
				
				//Store new document on library. It will be processed on fields message manager child.
				this.getDocumentLibrary().put(this.currentResponse, templateDocument);
				
				//Update current output data
				this.currentOutput.put(templateDocument, this.currentResponse);
				
				//Put new web response to fields message manager.
				this.fieldRootManager.put(templateDocument, e.getCurrentResponse());
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRefListener#OnManagerContentTypeRef_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentTypeRef_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRefListener#OnManagerContentTypeRef_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentTypeRef_Finished(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {
		
		/*
		 * Initialize document library. 
		 * It will break inheritance with DocumentRootManager's document library.
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
	 * @see org.httprobot.core.rml.managers.datatypes.interfaces.IManagerStepListener#OnManagerStep_DocumentCompleted(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDocument_DocumentCompleted(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {
		
		for(Document document : this.documentManagers.keySet())
		{
			DocumentManager documentManager = this.documentManagers.get(document);
			
			if(sender.equals(documentManager))
			{
				//Get Document message manager result document
				InputDocument childDocument = e.getInputDocument();
				
				//Get current root document
				InputDocument currentDocument = this.getDocumentLibrary().get(this.currentResponse);
				
				//Add document manager result to current root document
				currentDocument.addChildDocument(childDocument);
				
				return;
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.RmlDataTypeManager#OnManagerDocument_DocumentInitialized(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
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
		
		//Call error event to parent
		ManagerDocumentEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.interfaces.IManagerStepListener#OnManagerStep_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDocument_Finished(Object sender, ManagerEventArgs e) 
			throws ManagerException {
		
		if(this.documentManagers.containsValue(e.getSource()))
		{
			
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.interfaces.IManagerStepListener#OnManagerStep_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDocument_Started(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {
		
		if(sender.equals(this))
		{
			//When all managers has been started. 
			//Set iterator ready to iterate again.
			this.reset();	
		}		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlDataTypeManager#OnManagerFields_DocumentCompleted(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRoot_DocumentCompleted(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {
		
		if(sender.equals(this.fieldRootManager))
		{
			//Get completed document
			InputDocument inputDocument = e.getInputDocument();
		
			//Update document library
			this.getDocumentLibrary().put(this.currentResponse, inputDocument);
			
			//Fire DOCUMENT_COMPLETED event.
			ManagerDocumentEvent(e);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlDataTypeManager#OnManagerFields_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRoot_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//Call error event
		ManagerDocumentEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlDataTypeManager#OnManagerFields_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRoot_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException 
	{
		if(sender.equals(this.fieldRootManager))
		{
			try
			{
			}
			catch(NullPointerException ex)
			{
				ManagerDocumentEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_POINTER));
			}			
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlDataTypeManager#OnManagerFields_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRoot_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {

		//When Fields message manager has started initialize current manager's InputDocumentHandler.
		if(sender.equals(this.fieldRootManager))
		{

		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Map<InputDocument, HtmlPage> put(Map<InputDocument, HtmlPage> key, Map<InputDocument, HtmlPage> value) 
	{
		if(key != null)
		{
			//Update input data
			this.inputData.add(key);
			
			//Update pointers
			this.currentInput = key;
			this.currentOutput = value;
			
			//Call action manager
			for(InputDocument inputDocument : key.keySet())
			{
				//Get the value obtained from parent
				HtmlPage pageKey = value.get(inputDocument);
				
				//Initialize new action output data
				Set<HtmlPage> actionOutput = new HashSet<HtmlPage>();
				
				//Update Action message manager data.
				this.actionManager.put(pageKey, actionOutput);
			}
			
			return this.outputData.put(key, value);	
		}
		else
		{
			return null;
		}		
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends Map<InputDocument, HtmlPage>, 
			? extends Map<InputDocument, HtmlPage>> m) {
		
		for(Map<InputDocument, HtmlPage> map : m.keySet())
		{
			//Update input data.
			this.inputData.add(map);
			
			//Update pointers.
			this.currentInput = map;
			this.currentOutput = m.get(map);
		
			//Call action manager.
			for(InputDocument inputDocument : this.currentOutput.keySet())
			{
				//Get the value obtained from parent.
				HtmlPage pageKey = this.currentOutput.get(inputDocument);
				
				//Initialize new action output data.
				Set<HtmlPage> actionOutput = new HashSet<HtmlPage>();
				
				//Update Action message manager data.
				this.actionManager.put(pageKey, actionOutput);
			}
			
			//Update output data.
			this.outputData.put(this.currentInput, this.currentOutput);
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public Map<InputDocument, HtmlPage> remove(Object key) 
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
	 * @see org.httprobot.core.managers.Manager#start()
	 */
	@Override
	public void start() 
	{
		//Start controlling Document message
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
		return this.outputData.values();
	}
}