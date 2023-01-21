/**
 * 
 */
package org.httprobot.core.managers.contents;

import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.ContentType;
import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.common.contents.FieldRef;
import org.httprobot.common.definitions.Enums.ManagerErrorCode;
import org.httprobot.common.definitions.Enums.ManagerEventType;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.ContentData;
import org.httprobot.core.contents.solr.InputDocument;
import org.httprobot.core.contents.solr.InputField;
import org.httprobot.core.controls.contents.ContentTypeControl;
import org.httprobot.core.controls.contents.interfaces.IControlContentTypeListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IDataMappingImpl;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.ContentManager;
import org.httprobot.core.managers.contents.interfaces.IManagerContentTypeListener;

/**
 * Content type message manager class. Inherits {@link ContentManager}.
 * <br>
 * It's {@link IControlContentTypeListener} and {@link IDataMappingImpl}.
 * <br>
 * <h1>Input data:</h1>
 * Dictionary of {@link ContentType}
 * <br>
 * <h1>Output data:</h1>
 * Dictionary of {@link InputDocument} with {@link ContentType} as primary keys
 * 
 * @author joan
 *
 */
@XmlRootElement
public class ContentTypeManager 
		extends ContentManager<ContentType, ContentTypeControl, IManagerContentTypeListener> 
		implements IControlContentTypeListener, IDataMappingImpl<ContentType, InputDocument> {
	
	/**
	 * -2048461264139937336L
	 */
	private static final long serialVersionUID = -2048461264139937336L;
	/**
	 * The number of {@link ContentTypeRef} messages.
	 */
	private Integer contentTypeRefCount;
	/**
	 * {@link ContentType} message manager list.
	 * For each ContentTypeRef must be a ContentTypeManager.
	 */
	Map<ContentTypeRef, ContentTypeManager> contentTypeManagerList;
	/**
	 * {@link ContentTypeRef} message manager list.
	 */
	Map<ContentTypeRef, ContentTypeRefManager> contentTypeRefManagerList;
	/**
	 * {@link FieldRef} message manager list.
	 */
	Map<FieldRef, FieldRefManager> fieldRefManagerList;
	/**
	 * FieldRef message manager output fields.
	 */
	Map<FieldRef, InputField> fieldRefManagerOutputData;	
	/**
	 * Current message manager input data.
	 */
	Set<ContentType> inputData;
	/**
	 * Current message manager output data.
	 */
	Map<ContentType, InputDocument> outputData;
	/**
	 * The generated template of the input document 
	 */
	InputDocument templateDocument;
	/**
	 * {@link ContentType} message manager default constructor.
	 */
	public ContentTypeManager()
	{
		super();
	}
	/**
	 * {@link ContentType} message manager constructor.
	 * @param parent {@link IManagerContentTypeListener} the parent
	 * @param message {@link ContentType} the content type
	 */
	public ContentTypeManager(IManagerContentTypeListener parent, ContentType message) 
	{
		super(parent, message);
		
		//Initialize members.
		this.contentTypeRefCount = 0;
		this.control = new ContentTypeControl(this, message);
		
		this.fieldRefManagerList = new Hashtable<FieldRef, FieldRefManager>();
		this.contentTypeRefManagerList = new Hashtable<ContentTypeRef, ContentTypeRefManager>();
		this.contentTypeManagerList = new Hashtable<ContentTypeRef, ContentTypeManager>();
		
		this.inputData = new HashSet<ContentType>();
		this.outputData = new Hashtable<ContentType, InputDocument>();

		//Initialize result template document
		this.templateDocument = new InputDocument(message);
		
		//Set inherited data.
		this.inputData.add(message);
		
		//Associate parent's control child.
		this.addCommandOutputListener(this.control);
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
	public Set<java.util.Map.Entry<ContentType, InputDocument>> entrySet() 
	{
		return this.outputData.entrySet();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public InputDocument get(Object key) 
	{
		return this.outputData.get(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return this.outputData.isEmpty();
	}
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<ContentType> iterator() {
		return this.inputData.iterator();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<ContentType> keySet() 
	{
		return this.outputData.keySet();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
		
		switch (e.getCommand()) 
		{
		case CONTROL_CONTENT_TYPE_REF:
			try
			{
				// Cast sources
				ContentTypeRef contentTypeRef = ContentTypeRef.class.cast(e
						.getMessage());

				// Initialize manager
				ContentTypeRefManager contentTypeRefManager = new ContentTypeRefManager(this, contentTypeRef);
				contentTypeRefManager.addManagerListener(this);
				contentTypeRefManager.addManagerContentTypeRefListener(this);

				// Store manager
				this.addChildManager(contentTypeRefManager);
				this.contentTypeRefManagerList.put(contentTypeRef,
						contentTypeRefManager);
			}
			catch (ClassCastException ex) 
			{
				ManagerContentTypeEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}
			return;

		case CONTROL_FIELD_REF:
			try 
			{
				// Cast sources
				FieldRef fieldRef = FieldRef.class.cast(e.getMessage());

				// Initialize manager
				FieldRefManager fieldRefManager = new FieldRefManager(this, fieldRef);
				fieldRefManager.addManagerListener(this);
				fieldRefManager.addManagerFieldRefListener(this);

				// Store manager
				this.addChildManager(fieldRefManager);
				this.fieldRefManagerList.put(fieldRef, fieldRefManager);
			}
			catch (ClassCastException ex) 
			{
				ManagerContentTypeEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}
			return;

		case CONTROL_CONTENT_TYPE:
			try
			{
				// Cast sources
				ContentType contentType = ContentType.class	.cast(e.getMessage());

				// Update input data
				this.inputData.add(contentType);
			}
			catch (ClassCastException ex) 
			{
				ManagerContentTypeEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
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
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeListener#OnControlContentType_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentType_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeListener#OnControlContentType_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentType_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeListener#OnControlContentType_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentType_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(this.hasChildManagers())
		{
			//Start each child manager
			while(this.hasNext())
			{
				IManagerImpl manager = this.next();
				manager.start();
			}
			
			ManagerContentTypeEvent(new ManagerEventArgs(this, ManagerEventType.STARTED));
		}
		else
		{
			ManagerContentTypeEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeListener#OnControlContentType_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentType_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeListener#OnControlContentType_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentType_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeListener#OnControlContentType_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentType_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnManagerContentType_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentType_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnManagerContentType_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentType_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		try
		{
			//Cast sources
			ContentTypeManager contentTypeManager = ContentTypeManager.class.cast(sender);
			Iterator<ContentTypeRef> contentTypeRefKeys = this.contentTypeManagerList.keySet().iterator();
			
			while(contentTypeRefKeys.hasNext())
			{
				ContentTypeRef contentTypeRefKey = contentTypeRefKeys.next();
				
				//Get only current ContentTypeManager result data
				if(contentTypeManager.equals(this.contentTypeManagerList.get(contentTypeRefKey)))
				{
					//Get output data
					Iterator<ContentType> outputDataKeys = contentTypeManager.keySet().iterator();
					
					while(outputDataKeys.hasNext())
					{
						ContentType contentType = outputDataKeys.next();
						
						//Add loaded documents for each ContentTypeManager
						this.templateDocument.addChildDocument(contentTypeManager.get(contentType));
					}
					
					//Manager has been found
					return;
				}
			}
		}
		catch(ClassCastException ex)
		{
			CliTools.ThrowManagerException(this,
					"ContentTypeManager.OnManagerContentType_Finished: Unexpected source type");
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnManagerContentType_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentType_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		try
		{
			//Cast source
			ContentTypeManager contentTypeManager = ContentTypeManager.class.cast(sender);
			
			//Initialize using data
			Iterator<ContentTypeRef> contentTypeRefKeys = this.contentTypeManagerList.keySet().iterator();
			
			//Each content type message manager is mapped with it's content type reference.
			while(contentTypeRefKeys.hasNext())
			{
				//Get the content type reference (the key)
				ContentTypeRef contentTypeRefKey = contentTypeRefKeys.next();
				
				//Current content type manager must be a child of current class.
				if(contentTypeManager.equals(this.contentTypeManagerList.get(contentTypeRefKey)))
				{					
					ContentTypeRefManager contentTypeRefManager = this.contentTypeRefManagerList.get(contentTypeRefKey);

					for(ContentTypeRef contentTypeRef : contentTypeRefManager)
					{
						//Get corresponding content type message
						ContentType contentType = contentTypeRefManager.get(contentTypeRef);

						//Initialize new template document
						InputDocument templateDocument = new InputDocument(contentType);
						
						//Set new template document to corresponding content type message manager
						contentTypeManager.put(contentType, templateDocument);
						
						//Manager has been updated
						break;
					}
					//Manager has been found
					break;
				}
			}
		}
		catch(ClassCastException ex)
		{
			ManagerContentTypeEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnManagerContentTypeRef_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentTypeRef_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnManagerContentTypeRef_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentTypeRef_Finished(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {
		
		try
		{
			//Cast sources
			ContentTypeRefManager contentTypeRefManager = ContentTypeRefManager.class.cast(e.getSource());
			
			//Get ContentTypeRef message manager output data
			Iterator<ContentTypeRef> contentTypeRefManagerKeys = this.contentTypeRefManagerList.keySet().iterator();
			
			while(contentTypeRefManagerKeys.hasNext())
			{
				ContentTypeRef contentTypeRefKey = contentTypeRefManagerKeys.next();
				
				if(contentTypeRefManager.equals(this.contentTypeRefManagerList.get(contentTypeRefKey)))
				{
					for(ContentTypeRef contentTypeRef : contentTypeRefManager)
					{
						//Get ContentTypeRefManager result
						ContentType contentType = contentTypeRefManager.get(contentTypeRef);
						
						//Initialize manager
						ContentTypeManager contentTypeManager = new ContentTypeManager(this, contentType);
						
						//Associate child manager to parent
						contentTypeManager.addManagerListener(this);
						contentTypeManager.addManagerContentTypeListener(this);
						this.addCommandOutputListener(contentTypeManager);
						
						//Store manager
						this.contentTypeManagerList.put(contentTypeRefKey, contentTypeManager);
						
						//Start manager
						contentTypeManager.start();
					}
					
					this.contentTypeRefCount++;
					
					if(this.contentTypeRefCount == this.contentTypeRefManagerList.size() - 1)
					{
						//When all ContentTypeRef instances have been finished 
						//start managing ContentTypeManager list
						Iterator<ContentTypeRef> contentTypeRefKeys = this.contentTypeManagerList.keySet().iterator();
						
						while(contentTypeRefKeys.hasNext())
						{
							ContentTypeRef contentTypeRef = contentTypeRefKeys.next();
							ContentTypeManager contentTypeManager = this.contentTypeManagerList.get(contentTypeRef);
							
							contentTypeManager.start();
						}
					}
					
					//Manager has been found
					break;
				}
			}
		}
		catch(ClassCastException ex)
		{
			CliTools.ThrowManagerException(this, 
					"ContentTypeManager.OnManagerContentTypeRef_Finished: Unexpected source type");
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
			//Cast sources
			ContentTypeRefManager contentTypeRefManager = ContentTypeRefManager.class.cast(e.getSource());
			
			//Initialize variables
			if(this.contentTypeRefManagerList.containsValue(contentTypeRefManager))
			{
				for(ContentType contentType : this.getContentTypeRoot().getContentType())
				{
					//Iterate through ContentTypeRef manager list
					for(ContentTypeRef contentTypeRef : contentTypeRefManager)
					{
						/*Call put method until corresponding content type is found 
						(content type reference message manager FINISHED event).*/
						contentTypeRefManager.put(contentTypeRef, contentType);
					}
				}	
			}
			else
			{
				ManagerContentTypeEvent(new ManagerEventArgs(this, ManagerErrorCode.STACK_CONFLICT));
			}			
		}
		catch(ClassCastException ex)
		{
			ManagerContentTypeEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnManagerFieldRef_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRef_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnManagerFieldRef_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRef_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		try
		{
			//Cast sources
			FieldRefManager fieldRefManager = FieldRefManager.class.cast(e.getSource());
			
			for(FieldRef fieldRef : fieldRefManager)
			{
				InputField inputField = fieldRefManager.get(fieldRef);

				//Add field to template document
				this.templateDocument.put(fieldRef, inputField);
				
				return;
			}
		}
		catch(ClassCastException ex)
		{
			ManagerContentTypeEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnManagerFieldRef_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRef_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		try
		{
			//Cast sources
			FieldRefManager fieldRefManager = FieldRefManager.class.cast(e.getSource());
			
			for(FieldRef fieldRef : fieldRefManager)
			{
				if(fieldRefManager.equals(this.fieldRefManagerList.get(fieldRef)))
				{
					//Initialize new SOLR field
					InputField inputField = new InputField(fieldRef);
					
					//Set matching message as input data
					fieldRefManager.put(fieldRef, inputField);
					
					return;
				}
			}
			
		}
		catch(ClassCastException ex)
		{
			ManagerContentTypeEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public InputDocument put(ContentType key, InputDocument value)
	{		
		//Check if content type and input document are correct.
		if(this.control.get(ContentData.UUID).equals(key.getUuid())
				&& this.control.get(ContentData.UUID).equals(value.getContentType().getUuid())) {
			
			value = this.templateDocument.deepInputCopy();			
			
			//Fire event to parent.
			ManagerContentTypeEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
			
			return value;
		}
		else
		{
			ManagerContentTypeEvent(new ManagerEventArgs(this, ManagerErrorCode.BAD_CONTENT_TYPE));
			return null;
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends ContentType, ? extends InputDocument> m) {
		
		for(ContentType contentType : m.keySet())
		{
			if(this.put(contentType, m.get(contentType)) == null)
			{
				ManagerContentTypeEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
			}
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public InputDocument remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.outputData.size();
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
	public void stop() {
	}
	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<InputDocument> values() {
		// TODO Auto-generated method stub
		return null;
	}
}