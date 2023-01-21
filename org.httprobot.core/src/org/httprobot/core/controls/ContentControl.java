/**
 * 
 */
package org.httprobot.core.controls;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;

import org.httprobot.common.RML;
import org.httprobot.common.Content;
import org.httprobot.common.contents.ContentType;
import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.common.contents.DataView;
import org.httprobot.common.contents.FieldRef;
import org.httprobot.common.definitions.Enums.ControlEventType;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.ContentData;
import org.httprobot.core.controls.contents.interfaces.IControlContentTypeListener;
import org.httprobot.core.controls.contents.interfaces.IControlContentTypeRefListener;
import org.httprobot.core.controls.contents.interfaces.IControlContentTypeRootListener;
import org.httprobot.core.controls.contents.interfaces.IControlDataViewListener;
import org.httprobot.core.controls.contents.interfaces.IControlFieldRefListener;
import org.httprobot.core.controls.interfaces.listeners.IContentControlListener;
import org.httprobot.core.controls.unit.interfaces.IControlActionListener;
import org.httprobot.core.interfaces.IDataMappingImpl;

/**
 * {@link Content} message control class. Inherits {@link Control}.
 * <br>
 * It's {@link IContentControlListener}.
 * <br>
 * @author joan
 *
 */
public abstract class ContentControl
	<TMessage extends Content, IListener extends IControlListener> 
		extends Control	<TMessage, IListener>
		implements IContentControlListener, IDataMappingImpl<ContentData, Object> {
	
	/**
	 * -5973901691900645225L
	 */
	private static final long serialVersionUID = -5973901691900645225L;
	
	/**
	 * {@link ContentType} listeners
	 */
	private Vector<IControlContentTypeListener> contentTypeListeners;
	/**
	 * {@link ContentTypeRef} listeners
	 */
	private Vector<IControlContentTypeRefListener> contentTypeRefListeners;
	/**
	 * {@link ContentTypeRoot} listeners
	 */
	private Vector<IControlContentTypeRootListener> contentTypeRootListeners;
	/**
	 * {@link DataView} listeners
	 */
	private Vector<IControlDataViewListener> dataViewListeners;
	/**
	 * {@link FieldRef} listeners
	 */
	private Vector<IControlFieldRefListener> fieldRefListeners;
	/**
	 * The {@link Content} message attributes
	 */
	Map<ContentData, Object> data;
	
	/**
	 * {@link Content} message control class constructor.
	 */
	public ContentControl() 
	{
		super();
		
		//Initialize content control.
		initContentControl(null, null);
	}
	/**
	 * {@link Content} message control class constructor.
	 * @param parent
	 * @param message
	 */
	public ContentControl(IListener parent, TMessage message) 
	{
		super(parent, message);
		
		//Initialize content control.
		initContentControl(parent, message);
	}
	/**
	 * Adds {@link ContentType} event {@link IControlContentTypeListener}.
	 * @param listener {@link IControlContentTypeListener} the listener
	 */
	public final synchronized void addControlContentTypeListener(IControlContentTypeListener listener)
	{
		if(this.contentTypeListeners == null)
		{
			this.contentTypeListeners = new Vector<IControlContentTypeListener>();
		}
		this.contentTypeListeners.add(listener);
	}
	/**
	 * Adds {@link ContentTypeRef} event {@link IControlContentTypeRefListener}.
	 * @param listener {@link IControlContentTypeRefListener} the listener
	 */
	public final synchronized void addControlContentTypeRefListener(IControlContentTypeRefListener listener)
	{
		if(this.contentTypeRefListeners == null)
		{
			this.contentTypeRefListeners = new Vector<IControlContentTypeRefListener>();
		}
		this.contentTypeRefListeners.add(listener);
	}
	/**
	 * Adds {@link ContentTypeRoot} event {@link IControlContentTypeRootListener}.
	 * @param listener {@link IControlContentTypeRootListener} the listener
	 */
	public final synchronized void addControlContentTypeRootListener(IControlContentTypeRootListener listener)
	{
		if(this.contentTypeRootListeners == null)
		{
			this.contentTypeRootListeners = new Vector<IControlContentTypeRootListener>();
		}
		this.contentTypeRootListeners.add(listener);
	}
	/**
	 * Adds {@link DataView} event {@link IControlDataViewListener}.
	 * @param listener {@link IControlActionListener} the listener
	 */
	public final synchronized void addControlDataViewListener(IControlDataViewListener listener)
	{
		if(this.dataViewListeners == null)
		{
			this.dataViewListeners = new Vector<IControlDataViewListener>();
		}
		this.dataViewListeners.add(listener);
	}
	/**
	 * Adds {@link FieldRef} event {@link IControlFieldRefListener}.
	 * @param listener {@link IControlFieldRefListener} the listener
	 */
	public final synchronized void addControlFieldRefListener(IControlFieldRefListener listener)
	{
		if(this.fieldRefListeners == null)
		{
			this.fieldRefListeners = new Vector<IControlFieldRefListener>();
		}
		this.fieldRefListeners.add(listener);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#clear()
	 */
	@Override
	public void clear() 
	{
		this.data.clear();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey(Object key) 
	{
		return this.data.containsKey(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object value) 
	{
		return this.data.containsValue(value);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#setMessage(org.httprobot.common.RML)
	 */
	@Override
	public void controlMessage(TMessage message) 
	{
		super.controlMessage(message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#ChangeControl(org.httprobot.common.RML)
	 */
	@Override
	public final void ChangeControl(RML message)
	{
		super.ChangeControl(message);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<Entry<ContentData, Object>> entrySet() 
	{
		return this.data.entrySet();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public Object get(Object key) 
	{
		return this.data.get(key);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#InitControl(org.httprobot.common.RML)
	 */
	@Override
	public final void InitControl(RML message) 
	{
		super.InitControl(message);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() 
	{
		return this.data.isEmpty();
	}
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<ContentData> iterator() 
	{
		return this.data.keySet().iterator();
	}
	
	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<ContentData> keySet() 
	{
		return this.data.keySet();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#LoadControl(org.httprobot.common.RML)
	 */
	@Override
	public final void LoadControl(RML message) 
	{
		/*Full override.*/		
		if (message == null 
				&& this.message != null) {
			
			throw new NullPointerException("Control.LoadControl: Control's message hasn't been initialized");
		}
		
		//Update loaded inherited data
		this.put(ContentData.UUID, message.getUuid());
		this.put(ContentData.INHERITED, message.getInherited());
		
		if(!Boolean.class.cast(this.get(ContentData.INHERITED)) ? 
				message.getRuntimeOptions() != null : false) {

			for(RuntimeOptions option : message.getRuntimeOptions())
			{
				this.put(ContentData.RUNTIME_OPTIONS, option);
			}
		}

		//Send event to parent.
		ControlLoadedEvent(new ControlEventArgs(this, ControlEventType.LOAD, message));
		
		if(this.message instanceof DataView)
		{
			ControlDataViewEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		}
		else if(this.message instanceof FieldRef)
		{
			ControlFieldRefEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		}
		else if(this.message instanceof ContentTypeRef)
		{
			ControlContentTypeRefEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		}
		else if(this.message instanceof ContentType)
		{
			ControlContentTypeEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		}
		else if(this.message instanceof ContentTypeRoot)
		{
			ControlContentTypeRootEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Change(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#OnControl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Initialize(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#OnControl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Load(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#OnControl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#OnControl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Render(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#OnControl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeListener#OnControlContentType_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentType_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"ContentControl.OnControlContentType_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeListener#OnControlContentType_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentType_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentControl.OnControlContentType_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeListener#OnControlContentType_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentType_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentControl.OnControlContentType_Loaded not implemented method");		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeListener#OnControlContentType_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentType_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"ContentControl.OnControlContentType_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeListener#OnControlContentType_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentType_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"ContentControl.OnControlContentType_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeListener#OnControlContentType_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentType_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentControl.OnContentType_Write not implemented method");		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRefListener#OnControlContentTypeRef_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentControl.OnControlContentTypeRef_Changed not implemented method");		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRefListener#OnControlContentTypeRef_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentControl.OnControlContentTypeRef_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRefListener#OnControlContentTypeRef_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentControl.OnControlContentTypeRef_Loaded not implemented method");		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRefListener#OnControlContentTypeRef_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentControl.OnControlContentTypeRef_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRefListener#OnControlContentTypeRef_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentControl.OnControlContentTypeRef_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRefListener#OnControlContentTypeRef_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentControl.OnControlContentTypeRef_Write not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRootListener#OnControlContentTypeRoot_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRoot_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentControl.OnControlContentTypes_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRootListener#OnControlContentTypeRoot_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRoot_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentControl.OnControlContentTypes_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRootListener#OnControlContentTypeRoot_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRoot_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"ContentControl.OnControlContentTypes_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRootListener#OnControlContentTypeRoot_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRoot_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentControl.OnControlContentTypes_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRootListener#OnControlContentTypeRoot_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRoot_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"ContentControl.OnControlContentTypes_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRootListener#OnControlContentTypeRoot_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRoot_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"ContentControl.OnControlContentTypes_Write not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlDataViewListener#OnControlDataView_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataView_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentControl.OnControlDataView_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlDataViewListener#OnControlDataView_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataView_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentControl.OnControlDataView_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlDataViewListener#OnControlDataView_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataView_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentControl.OnControlDataView_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlDataViewListener#OnControlDataView_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataView_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentControl.OnControlDataView_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlDataViewListener#OnControlDataView_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataView_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentControl.OnControlDataView_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlDataViewListener#OnControlDataView_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataView_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentControl.OnControlDataView_Write not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlFieldRefListener#OnControlFieldRef_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentControl.OnControlFieldRef_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlFieldRefListener#OnControlFieldRef_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentControl.OnControlFieldRef_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlFieldRefListener#OnControlFieldRef_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"ContentControl.OnControlFieldRefLoaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlFieldRefListener#OnControlFieldRef_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"ContentControl.OnControlFieldRef_Read not implemented method");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlFieldRefListener#OnControlFieldRef_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentControl.OnControlFieldRef_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlFieldRefListener#OnControlFieldRef_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentControl.OnControlFieldRef_Write not implemented method");		
	}
	
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Object put(ContentData key, Object value) 
	{
		switch (key) 
		{
		case UUID:
			this.message.setUuid(UUID.class.cast(value));
			break;

		case INHERITED:
			if (value != null)
			{
				this.message.setInherited(Boolean.class.cast(value));
			} 
			else 
			{
				value = new Boolean(true);
				
				// Set default value.
				this.message.setInherited(Boolean.class.cast(value));
			}
			break;

		case RUNTIME_OPTIONS:
			this.message.addRuntimeOption(RuntimeOptions.class.cast(value));
			break;
			
		case FIELD_REF:
			//Check input value is a Document message.
			if(value.equals(this.message)) 
			{
				RenderControl(this.message);
				ControlFieldRefEvent(new ControlEventArgs(this, ControlEventType.RENDER, FieldRef.class.cast(value)));	
			}
			break;
			
		case CONTENT_TYPE:
			//Check input value is a Document message.
			if(value.equals(this.message)) 
			{
				RenderControl(this.message);
				ControlContentTypeEvent(new ControlEventArgs(this, ControlEventType.RENDER, ContentType.class.cast(value)));	
			}
			break;
	
		case CONTENT_TYPE_REF:
			//Check input value is a Document message.
			if(value.equals(this.message)) 
			{
				RenderControl(this.message);
				ControlContentTypeRefEvent(new ControlEventArgs(this, ControlEventType.RENDER, ContentTypeRef.class.cast(value)));	
			}
			break;

		case CONTENT_TYPE_ROOT:
			//Check input value is a Document message.
			if(value.equals(this.message)) 
			{
				RenderControl(this.message);
				ControlContentTypeRootEvent(new ControlEventArgs(this, ControlEventType.RENDER, ContentTypeRoot.class.cast(value)));	
			}
			break;

		case DATA_VIEW:
			//Check input value is a Document message.
			if(value.equals(this.message)) 
			{
				RenderControl(this.message);
				ControlDataViewEvent(new ControlEventArgs(this, ControlEventType.RENDER, DataView.class.cast(value)));	
			}			
			break;
		default:
			break;
		}
		
		//Update ContentData
		return this.data.put(key, value);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends ContentData, ? extends Object> m) {
		
		for(ContentData attribute : m.keySet())
		{
			if(this.put(attribute, m.get(attribute)) == null)
			{
				//Error
			}
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public Object remove(Object key) 
	{
		return this.data.remove(key);
	}
	/**
	 * Removes {@link ContentType} event {@link IControlContentTypeListener}.
	 * @param listener {@link IControlContentTypeListener} the listener
	 */
	public final synchronized void removeControlContentTypeListener(IControlContentTypeListener listener)
	{
		if(this.contentTypeListeners == null)
		{
			this.contentTypeListeners = new Vector<IControlContentTypeListener>();
		}
		this.contentTypeListeners.remove(listener);
	}
	/**
	 * Removes {@link ContentTypeRef} event {@link IControlContentTypeRefListener}.
	 * @param listener {@link IControlContentTypeListener} the listener
	 */
	public final synchronized void removeControlContentTypeRefListener(IControlContentTypeRefListener listener)
	{
		if(this.contentTypeRefListeners == null)
		{
			this.contentTypeRefListeners = new Vector<IControlContentTypeRefListener>();
		}
		this.contentTypeRefListeners.remove(listener);
	}
	/**
	 * Removes {@link ContentTypeRoot} event {@link IControlContentTypeRootListener}.
	 * @param listener {@link IControlContentTypeRootListener} the listener
	 */
	public final synchronized void removeControlContentTypeRootListener(IControlContentTypeRootListener listener)
	{
		if(this.contentTypeRootListeners == null)
		{
			this.contentTypeRootListeners = new Vector<IControlContentTypeRootListener>();
		}
		this.contentTypeRootListeners.remove(listener);
	}
	/**
	 * Removes {@link DataView} event {@link IControlDataViewListener}.
	 * @param listener {@link IControlActionListener} the listener
	 */
	public final synchronized void removeControlDataViewListener(IControlDataViewListener listener)
	{
		if(this.dataViewListeners == null)
		{
			this.dataViewListeners = new Vector<IControlDataViewListener>();
		}
		this.dataViewListeners.remove(listener);
	}
	/**
	 * Removes {@link FieldRef} event {@link IControlFieldRefListener}.
	 * @param listener {@link IControlFieldRefListener} the listener
	 */
	public final synchronized void removeControlFieldRefListener(IControlFieldRefListener listener)
	{
		if(this.fieldRefListeners == null)
		{
			this.fieldRefListeners = new Vector<IControlFieldRefListener>();
		}
		this.fieldRefListeners.remove(listener);
	}

	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#RenderControl()
	 */
	@Override
	public final void RenderControl(RML message) 
	{
		super.RenderControl(message);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() 
	{
		return this.data.size();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<Object> values() 
	{
		return this.data.values();
	}
	/**
	 * Initializes RmlContent control.
	 * @param parent the parent
	 * @param message the message
	 */
	private final void initContentControl(IControlListener parent, RML message)
	{
		//Initialize content control's data
		this.data = new HashMap<ContentData, Object>();
		
		//Initialize Initialization event's arguments.
		ControlEventArgs e = new ControlEventArgs(this, ControlEventType.INIT, message);

		//Check by instance to add control listeners efficiently.
		if(message instanceof DataView)
		{
			addControlDataViewListener(this);

			//Check if parent is null and match with parent listener's type.
			if(parent != null ?
					parent instanceof IControlDataViewListener : false) {
				
				addControlDataViewListener(IControlDataViewListener.class.cast(parent));
			}
			ControlDataViewEvent(e);
		}
		else if(message instanceof FieldRef)
		{
			addControlFieldRefListener(this);

			//Check if parent is null and match with parent listener's type.
			if(parent != null ?
					parent instanceof IControlFieldRefListener : false) {
				
				addControlFieldRefListener(IControlFieldRefListener.class.cast(parent));
			}
			ControlFieldRefEvent(e);
		}
		else if(message instanceof ContentTypeRef)
		{
			addControlContentTypeRefListener(this);

			//Check if parent is null and match with parent listener's type.
			if(parent != null ?
					parent instanceof IControlContentTypeRefListener : false) {
				
				addControlContentTypeRefListener(IControlContentTypeRefListener.class.cast(parent));
			}
			ControlContentTypeRefEvent(e);
		}
		else if(message instanceof ContentType)
		{
			addControlContentTypeListener(this);

			//Check if parent is null and match with parent listener's type.
			if(parent != null ?
					parent instanceof IControlContentTypeListener : false) {
				
				addControlContentTypeListener(IControlContentTypeListener.class.cast(parent));
			}
			ControlContentTypeEvent(e);
		}
		else if(message instanceof ContentTypeRoot)
		{
			addControlContentTypeRootListener(this);

			//Check if parent is null and match with parent listener's type.
			if(parent != null ?
					parent instanceof IControlContentTypeRootListener : false) {
				
				addControlContentTypeRootListener(IControlContentTypeRootListener.class.cast(parent));
			}
			ControlContentTypeRootEvent(e);
		}			
	}
	/**
	 * Fires {@link ContentType} event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlContentTypeEvent(ControlEventArgs e) 
	{
		for (IControlContentTypeListener listener : contentTypeListeners) 
		{
			try
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlContentType_Init(this, e);
						break;
					case READ:
						listener.OnControlContentType_Read(this, e);
						break;
					case LOAD:
						listener.OnControlContentType_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlContentType_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlContentType_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlContentType_Write(this, e);
						break;	
					default:
						break;
				}
			}
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			} 
			catch (InconsistenMessageException e2) 
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Fires {@link ContentType} event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlContentTypeRefEvent(ControlEventArgs e) 
	{
		for (IControlContentTypeRefListener listener : contentTypeRefListeners) 
		{
			try
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlContentTypeRef_Init(this, e);
						break;
					case READ:
						listener.OnControlContentTypeRef_Read(this, e);
						break;
					case LOAD:
						listener.OnControlContentTypeRef_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlContentTypeRef_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlContentTypeRef_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlContentTypeRef_Write(this, e);
						break;	
					default:
						break;
				}
			}
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			} 
			catch (InconsistenMessageException e2) 
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Fires {@link ContentType} event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlContentTypeRootEvent(ControlEventArgs e) 
	{
		for (IControlContentTypeRootListener listener : contentTypeRootListeners) 
		{
			try
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlContentTypeRoot_Init(this, e);
						break;
					case READ:
						listener.OnControlContentTypeRoot_Read(this, e);
						break;
					case LOAD:
						listener.OnControlContentTypeRoot_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlContentTypeRoot_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlContentTypeRoot_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlContentTypeRoot_Write(this, e);
						break;	
					default:
						break;
				}
			}
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			} 
			catch (InconsistenMessageException e2) 
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Fires {@link DataView} event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlDataViewEvent(ControlEventArgs e) 
	{
		for (IControlDataViewListener listener : dataViewListeners) 
		{
			try
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlDataView_Init(this, e);
						break;
					case READ:
						listener.OnControlDataView_Read(this, e);
						break;
					case LOAD:
						listener.OnControlDataView_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlDataView_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlDataView_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlDataView_Write(this, e);
						break;	
					default:
						break;
				}
			}
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			} 
			catch (InconsistenMessageException e2) 
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Fires {@link FieldRef} event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlFieldRefEvent(ControlEventArgs e) 
	{
		for (IControlFieldRefListener listener : fieldRefListeners) 
		{
			try
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlFieldRef_Init(this, e);
						break;
					case READ:
						listener.OnControlFieldRef_Read(this, e);
						break;
					case LOAD:
						listener.OnControlFieldRef_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlFieldRef_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlFieldRef_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlFieldRef_Write(this, e);
						break;	
					default:
						break;
				}
			}
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			} 
			catch (InconsistenMessageException e2) 
			{
				e2.printStackTrace();
			}
		}
	}
	
}
