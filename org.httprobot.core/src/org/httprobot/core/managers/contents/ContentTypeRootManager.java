/**
 * 
 */
package org.httprobot.core.managers.contents;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.ContentType;
import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.common.contents.FieldRef;
import org.httprobot.common.definitions.Enums.ManagerErrorCode;
import org.httprobot.common.definitions.Enums.ManagerEventType;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.core.contents.FieldLibrary;
import org.httprobot.core.contents.InputDocumentLibrary;
import org.httprobot.core.contents.TemplateLibrary;
import org.httprobot.core.contents.solr.InputDocument;
import org.httprobot.core.contents.solr.InputField;
import org.httprobot.core.controls.contents.ContentTypeControl;
import org.httprobot.core.controls.contents.ContentTypeRefControl;
import org.httprobot.core.controls.contents.ContentTypeRootControl;
import org.httprobot.core.controls.contents.FieldRefControl;
import org.httprobot.core.controls.contents.interfaces.IControlContentTypeRootListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IDataMappingImpl;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.ContentManager;
import org.httprobot.core.managers.contents.interfaces.IManagerContentTypeListener;
import org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRootListener;
import org.httprobot.core.managers.datatypes.DataSourceManager;

/**
 * {@link ContentTypeRoot} message manager class. Inherits {@link ContentManager}.</br>
 * It's {@link IControlContentTypeRootListener}, {@link IManagerContentTypeListener} 
 * and {@link IDataMappingImpl}.
 * <br><h1>Input data: Map[Object, ContentType]</h1> 
 * The {@link ContentType} for starting {@link DataSourceManager}s.
 * <br><h1>Output data: Map[ContentType, InputDocument]</h1>
 * The template {@link InputDocument}'s of starting {@link DataSourceManager}s.
 * <br>
 * @author Joan
 *
 */
@XmlRootElement
public final class ContentTypeRootManager 
	extends ContentManager<ContentTypeRoot, ContentTypeRootControl, IManagerContentTypeRootListener> 
	implements IControlContentTypeRootListener, IManagerContentTypeListener, 
		IDataMappingImpl<ContentTypeRoot, TemplateLibrary> {
	
	/**
	 * -8640595784209316306L
	 */
	private static final long serialVersionUID = -8640595784209316306L;
	
	//Data members
	Map<ContentTypeRef, ContentType> contentTypeIndex;
	
	//Manager members
	Map<ContentType, ContentTypeManager> contentTypeManagerList;
	Map<ContentTypeRef, ContentTypeRefManager> contentTypeRefManagerList;
	
	InputDocumentLibrary<ContentTypeRef, FieldRef> documentLibrary;
	FieldLibrary<FieldRef> fieldLibrary;
	
	Map<FieldRef, FieldRefManager> fieldRefManagerList;
	//Mapping members
	Set<ContentTypeRoot> inputData;
	Map<ContentTypeRoot, TemplateLibrary> outputData;
	
	/**
	 * {@link ContentTypeRoot} message manager default constructor.
	 */
	public ContentTypeRootManager() {
		super();
	}	
	/**
	 * {@link ContentTypeRoot} message manager constructor.
	 * @param parent the parent
	 * @param message {@link ContentTypeRoot} the message
	 */
	public ContentTypeRootManager(IManagerContentTypeRootListener parent, ContentTypeRoot message) 
	{
		super(parent, message);
		
		//Initialize control members
		this.control = new ContentTypeRootControl(this, message);
		
		//Manager members
		this.fieldRefManagerList = new LinkedHashMap<FieldRef, FieldRefManager>();
		this.contentTypeManagerList = new LinkedHashMap<ContentType, ContentTypeManager>();
		
		//Content members
		this.contentTypeIndex = new LinkedHashMap<ContentTypeRef, ContentType>();
		this.fieldLibrary = new FieldLibrary<FieldRef>();
		this.documentLibrary = new InputDocumentLibrary<ContentTypeRef, FieldRef>();
		
		//Data members
		this.inputData = new HashSet<ContentTypeRoot>();
		this.outputData = new LinkedHashMap<ContentTypeRoot, TemplateLibrary>();
		
		//Associate parent's control child
		addCommandOutputListener(this.control);
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
	public Set<java.util.Map.Entry<ContentTypeRoot, TemplateLibrary>> entrySet()
	{
		return this.outputData.entrySet();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public TemplateLibrary get(Object key) 
	{
		return this.outputData.get(key);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#getContentTypeRoot()
	 */
	@Override
	public ContentTypeRoot getContentTypeRoot() 
			throws NotImplementedException, ManagerException {
		
		return this.control.getMessage();
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
	public Iterator<ContentTypeRoot> iterator()
	{
		return this.inputData.iterator();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<ContentTypeRoot> keySet() 
	{
		return this.inputData;
	}/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
		
		//Initialize managers
		switch (e.getCommand()) 
		{
		
		case CONTROL_CONTENT_TYPE_ROOT:
			try 
			{
				ContentTypeRoot contentTypeRoot = ContentTypeRoot.class.cast(e.getMessage());

				// Update input data
				this.inputData.add(contentTypeRoot);
			} 
			catch (ClassCastException ex)
			{
				ManagerContentTypeRootEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}
			return;

		case CONTROL_FIELD_REF:
			try
			{
				// Cast sources
				FieldRef fieldRef = FieldRef.class.cast(e.getMessage());
				FieldRefControl fieldRefControl = FieldRefControl.class.cast(e.getSource());

				// Only initialize child managers
				if (fieldRefControl.getParent() instanceof ContentTypeRootControl) 
				{
					// Instantiate manager
					FieldRefManager fieldRefManager = new FieldRefManager(this, fieldRef);

					// Associate manager to parent
					fieldRefManager.addManagerListener(this);
					fieldRefManager.addManagerFieldRefListener(this);
					this.addCommandOutputListener(fieldRefManager);

					// Store manager
					this.addChildManager(fieldRefManager);
					this.fieldRefManagerList.put(fieldRef, fieldRefManager);
				}
			}
			catch (ClassCastException ex) 
			{
				ManagerContentTypeRootEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}
			return;

		case CONTROL_CONTENT_TYPE_REF:
			try 
			{
				ContentTypeRef contentTypeRef = ContentTypeRef.class.cast(e.getMessage());
				ContentTypeRefControl contentTypeRefControl = ContentTypeRefControl.class.cast(e.getSource());

				if (contentTypeRefControl.getParent() instanceof ContentTypeRootControl)
				{
					// Initialize child content type reference message manager
					ContentTypeRefManager contentTypeRefManager = new ContentTypeRefManager(this, contentTypeRef);

					// Associate child manager to parent
					contentTypeRefManager.addManagerListener(this);
					contentTypeRefManager.addManagerContentTypeRefListener(this);
					addCommandOutputListener(contentTypeRefManager);

					// Store manager
					addChildManager(contentTypeRefManager);
					
					this.contentTypeRefManagerList.put(contentTypeRef, contentTypeRefManager);
				}
			} 
			catch (ClassCastException ex)
			{
				ManagerContentTypeRootEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}
			return;

		case CONTROL_CONTENT_TYPE:

			try {
				// Cast sources
				ContentType contentType = ContentType.class.cast(e.getMessage());
				ContentTypeControl contentTypeControl = ContentTypeControl.class.cast(e.getSource());

				// Only initialize child managers
				if (contentTypeControl.getParent() instanceof ContentTypeRootControl) 
				{
					// Instantiate manager
					ContentTypeManager contentTypeManager = new ContentTypeManager(this, contentType);

					// Associate manager to parent
					contentTypeManager.addManagerListener(this);
					contentTypeManager.addManagerContentTypeListener(this);
					addCommandOutputListener(contentTypeManager);

					// Store manager
					addChildManager(contentTypeManager);
					this.contentTypeManagerList.put(contentType, contentTypeManager);
				}
			} 
			catch (ClassCastException ex) 
			{
				ManagerContentTypeRootEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}
			return;

		default:
			return;
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRootListener#OnControlContentTypeRoot_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRoot_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRootListener#OnControlContentTypeRoot_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRoot_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRootListener#OnControlContentTypeRoot_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRoot_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(this.hasChildManagers())
		{
			//Start each child manager
			while(this.hasNext())
			{
				IManagerImpl manager = this.next();
				manager.start();
			}
			
			ManagerContentTypeRootEvent(new ManagerEventArgs(this, ManagerEventType.STARTED));
		}
		else
		{
			ManagerContentTypeRootEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		}	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRootListener#OnControlContentTypeRoot_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRoot_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRootListener#OnControlContentTypeRoot_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRoot_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRootListener#OnControlContentTypeRoot_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRoot_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnManagerContentType_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentType_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException 
	{
		ManagerContentTypeRootEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnManagerContentType_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentType_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException 
	{
		try
		{
			//Cast sources
			ContentTypeManager contentTypeManager = ContentTypeManager.class.cast(e.getSource());
			
			//Get only child ContentType managers
			if(contentTypeManager.getParent() instanceof ContentTypeRootManager)
			{
				//Iterate through ContentType message manager output data
				for(ContentType contentType : contentTypeManager)
				{
					//Current message manager must be a child.
					if(contentTypeManager.equals(this.contentTypeManagerList.get(contentType)))
					{
						//Get template document
						InputDocument templateDocument = contentTypeManager.get(contentType);
					
						//Look for corresponding content type reference.
						for(ContentTypeRef contentTypeRef : this.contentTypeIndex.keySet())
						{
							if(contentTypeRef.getUuid().equals(contentType.getUuid()))
							{
								//Store template document to library
								this.documentLibrary.put(contentTypeRef, templateDocument);								
							}
						}
						
					}
				}
			}
		}
		catch(ClassCastException ex)
		{
			ManagerContentTypeRootEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnManagerContentType_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentType_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException 
	{
		try
		{
			//Cast sources
			ContentTypeManager contentTypeManager = ContentTypeManager.class.cast(e.getSource());
			
			//Iterate through manager's content types 
			for(ContentType contentType : contentTypeManager)
			{
				//Current message manager must be a child.
				if(contentTypeManager.equals(this.contentTypeManagerList.get(contentType)))
				{
					//Initialize new template document
					InputDocument templateDocument = new InputDocument(contentTypeManager.getControl().getMessage());
					
					//Set new template document to corresponding content type message manager
					contentTypeManager.put(contentType, templateDocument);
				}	
			}
		}
		catch(ClassCastException ex)
		{
			ManagerContentTypeRootEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnManagerContentTypeRef_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentTypeRef_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		ManagerContentTypeRootEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnManagerContentTypeRef_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentTypeRef_Finished(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {
		
		try
		{
			ContentTypeRefManager contentTypeRefManager = ContentTypeRefManager.class.cast(e.getSource());
			
			for(ContentTypeRef contentTypeRef : contentTypeRefManager)
			{
				ContentType contentType = contentTypeRefManager.get(contentTypeRef);
				
				//Update content type reference index
				this.contentTypeIndex.put(contentTypeRef, contentType);
			}
		}
		catch(ClassCastException ex)
		{
			ManagerContentTypeRefEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnManagerContentTypeRef_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentTypeRef_Started(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {
		
		try
		{
			ContentTypeRefManager contentTypeRefManager = ContentTypeRefManager.class.cast(e.getSource());
			
			//Iterate through manager's keys
			for(ContentTypeRef contentTypeRef : contentTypeRefManager)
			{
				for(ContentType contentType : this.contentTypeManagerList.keySet())
				{
					//Insert content types until matches with content type reference
					contentTypeRefManager.put(contentTypeRef, contentType);
				}
			}
		}
		catch(ClassCastException ex)
		{
			ManagerContentTypeRefEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnManagerFieldRef_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRef_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnManagerFieldRef_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRef_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException 
	{
		try
		{
			FieldRefManager fieldRefManager = FieldRefManager.class.cast(e.getSource());
			
			//Check only for FieldRef manager children
			if(fieldRefManager.getParent() instanceof ContentTypeRootManager)
			{
				for(FieldRef fieldRef : fieldRefManager)
				{
					InputField inputField = fieldRefManager.get(fieldRef);
					
					//Store FieldRef manager output data
					this.fieldLibrary.put(fieldRef, inputField);
				}
			}
		}
		catch(ClassCastException ex)
		{
			ManagerContentTypeRootEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		}	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnManagerFieldRef_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRef_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException 
	{
		try
		{
			//Cast sources
			FieldRefManager fieldRefManager = FieldRefManager.class.cast(sender);
			
			for(FieldRef fieldRef : fieldRefManager)
			{
				//Check if manager is a child of current object
				if(fieldRefManager.equals(this.fieldRefManagerList.get(fieldRef)))
				{
					//Initialize new SOLR field
					InputField inputField = new InputField(fieldRef);
					
					//Set matching message as input data
					fieldRefManager.put(fieldRef, inputField);
				}
			}
		}
		catch(ClassCastException ex)
		{
			ManagerContentTypeRootEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		}
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnManagerContentTypeRoot_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentTypeRoot_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnManagerContentTypeRoot_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentTypeRoot_Finished(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnManagerContentTypeRoot_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentTypeRoot_Started(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {
		
		if(e.getSource().equals(this))
		{
			//When all managers has been started. 
			//Set iterator ready to iterate again.
			this.reset();	
		}		
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public TemplateLibrary put(ContentTypeRoot key, TemplateLibrary value) 
	{
		if(this.inputData.contains(key))
		{
			//Add all template documents and fields to template library
			value.putAll(this.documentLibrary);
			value.getFieldsLibrary().putAll(this.fieldLibrary);
			
			//Finish event
			ManagerContentTypeRefEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
			
			return this.outputData.put(key, value);
		}
		return null;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends ContentTypeRoot, ? extends TemplateLibrary> m) 
	{
		if(this.inputData.containsAll(m.keySet()))
		{
			this.outputData.putAll(m);
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public TemplateLibrary remove(Object key) 
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
	 * @see org.httprobot.core.managers.ContentManager#start()
	 */
	@Override
	public void start()
	{
		this.control.controlMessage(this.message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#stop()
	 */
	@Override
	public void stop() 
	{
		
	}
	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<TemplateLibrary> values() 
	{
		return this.outputData.values();
	}
}