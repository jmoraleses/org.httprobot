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
import org.httprobot.common.definitions.Enums.ManagerErrorCode;
import org.httprobot.common.definitions.Enums.ManagerEventType;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.core.controls.contents.ContentTypeRefControl;
import org.httprobot.core.controls.contents.interfaces.IControlContentTypeRefListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IDataMappingImpl;
import org.httprobot.core.managers.ContentManager;
import org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRefListener;

/**
 * Content type reference message manager class. Inherits {@link ContentManager}.
 * <br>
 * It's {@link IControlContentTypeRefListener} and {@link IDataMappingImpl}.
 * <br>
 * <h1>Input data: </h1> The {@link ContentTypeRoot} the root content type.
 * <br>
 * <h1>Output data:</h1> The {@link ContentType} message referenced to.
 * <br>
 * @author joan
 */
@XmlRootElement
public class ContentTypeRefManager 
	extends ContentManager<ContentTypeRef, ContentTypeRefControl, IManagerContentTypeRefListener> 
	implements IControlContentTypeRefListener, IDataMappingImpl<ContentTypeRef, ContentType> {
	/**
	 * 3716944666894006976L
	 */
	private static final long serialVersionUID = 3716944666894006976L;
	/**
	 * The input data.
	 */
	Set<ContentTypeRef> inputData;
	/**
	 * The output data.
	 */
	Map<ContentTypeRef, ContentType> outputData;
	/**
	 * ContentTypeRef message manager default constructor.
	 */
	public ContentTypeRefManager() 
	{
		super();
	}
	/**
	 * ContentTypeRef message manager constructor.
	 * @param parent {@link IManagerContentTypeRefListener} the parent
	 * @param message {@link ContentTypeRef} the message
	 */
	public ContentTypeRefManager(IManagerContentTypeRefListener parent, ContentTypeRef message) 
	{
		super(parent, message);
		
		//Initialize members
		this.control = new ContentTypeRefControl(this, message);
		
		this.inputData = new HashSet<ContentTypeRef>();
		this.outputData = new LinkedHashMap<ContentTypeRef, ContentType>();
		
		//Add listeners
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
	public boolean containsKey(Object key) {
		return this.outputData.containsKey(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object value) 
	{
		return this.outputData.containsKey(value);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<java.util.Map.Entry<ContentTypeRef, ContentType>> entrySet() 
	{
		return this.outputData.entrySet();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public ContentType get(Object key) {
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
					ContentTypeRef contentTypeRef = ContentTypeRef.class.cast(e.getMessage());
					
					//Update input data
					this.inputData.add(contentTypeRef);					
				}
				catch(ClassCastException ex)
				{
					ManagerContentTypeRefEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
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
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRefListener#OnControlContentTypeRef_Changed(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRefListener#OnControlContentTypeRef_Init(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRefListener#OnControlContentTypeRef_Loaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getMessage() != null)
		{
			try
			{
				ContentTypeRef contentTypeRef = ContentTypeRef.class.cast(e.getMessage());
				
				if(contentTypeRef.getUuid() != null)
				{
					ManagerContentTypeRefEvent(new ManagerEventArgs(this, ManagerEventType.STARTED));
				}
				else
				{
					ManagerContentTypeRefEvent(new ManagerEventArgs(this, ManagerErrorCode.BAD_MESSAGE));
				}
			}
			catch(ClassCastException ex)
			{
				ManagerContentTypeRefEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRefListener#OnControlContentTypeRef_Read(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRefListener#OnControlContentTypeRef_Rendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRefListener#OnControlContentTypeRef_Write(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {		
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
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnManagerContentTypeRef_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentTypeRef_Started(Object sender, ManagerEventArgs e) 
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
	public ContentType put(ContentTypeRef key, ContentType value) {
		
		if(this.inputData.contains(key))
		{
			if(value.getUuid().equals(key.getUuid()))
			{
				//Set output data
				this.outputData.put(key, value);
				
				//Finish event
				ManagerContentTypeRefEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
				
				return value;
			}	
		}
		
		return null;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends ContentTypeRef, ? extends ContentType> m) {
		
		if(this.inputData.containsAll(m.keySet()))
		{
			ContentType contentType;
			
			try {
				contentType = m.get(this.getContentTypeRoot());
				
				for(ContentTypeRef contentTypeRef : m.keySet())
				{
					if(contentType.getUuid().equals(contentTypeRef.getUuid()))
					{
						//Update output data
						this.outputData.put(contentTypeRef, contentType);
						
						//Finish event
						ManagerContentTypeRefEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
						
						return;	
					}			
				}
			} 
			catch (NotImplementedException e) 
			{
				e.printStackTrace();
			}
			catch (ManagerException e) 
			{
				e.printStackTrace();
			}
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public ContentType remove(Object key) 
	{
		return this.outputData.remove(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() {
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
	public void stop() 
	{
	
	}
	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<ContentType> values() 
	{
		return this.outputData.values();
	}
}