package org.httprobot.core.controls;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;

import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.RML;
import org.httprobot.common.DataType;
import org.httprobot.common.datatypes.DataSource;
import org.httprobot.common.datatypes.Document;
import org.httprobot.common.datatypes.Field;
import org.httprobot.common.datatypes.FieldRoot;
import org.httprobot.common.datatypes.DocumentRoot;
import org.httprobot.common.definitions.Enums.ControlEventType;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.DataTypeData;
import org.httprobot.core.controls.datatypes.interfaces.IControlDataSourceListener;
import org.httprobot.core.controls.datatypes.interfaces.IControlFieldListener;
import org.httprobot.core.controls.datatypes.interfaces.IControlFieldRootListener;
import org.httprobot.core.controls.datatypes.interfaces.IControlDocumentListener;
import org.httprobot.core.controls.datatypes.interfaces.IControlDocumentRootListener;
import org.httprobot.core.controls.interfaces.listeners.IDataTypeControlListener;
import org.httprobot.core.interfaces.IDataMappingImpl;

/**
 * {@link DataType} message control class. Inherits {@link Control}. 
 * <br>
 * It's {@link IDataTypeControlListener}.
 * <br>
 * @author Joan
 *  
 */
@XmlTransient
public abstract class DataTypeControl
	<TMessage extends DataType, IListener extends IControlListener> 
		extends Control<TMessage, IListener> 
		implements IDataTypeControlListener, IDataMappingImpl<DataTypeData, Object> {
	
	/**
	 * -5117685502901358057L
	 */
	private static final long serialVersionUID = -5117685502901358057L;
	
	/**
	 * {@link DataSource} message control listeners
	 */
	private Vector<IControlDataSourceListener> dataSourceListeners;
	/**
	 * {@link Document} message control listeners
	 */
	private Vector<IControlDocumentListener> documentListeners;
	/**
	 * {@link DocumentRoot} message control listeners
	 */
	private Vector<IControlDocumentRootListener> documentRootListeners;
	/**
	 * {@link Field} message control listeners
	 */
	private Vector<IControlFieldListener> fieldListeners;
	/**
	 * {@link FieldRoot} message control listeners
	 */
	private Vector<IControlFieldRootListener> fieldRootListeners;
	/**
	 * The message control data with {@link DataTypeData} as primary keys. 
	 */
	Map<DataTypeData, Object> data;
	
	/**
	 * {@link DataType} message control default class constructor.
	 * Initializes and add event listeners.
	 */
	public DataTypeControl()
	{
		super();
		
		//Initialize using data
		initDataTypeControl(null, null);
	}
	/**
	 * {@link DataType} message control class constructor. 
	 * Initializes and add event listeners.
	 * @param parent {@link IControlListener} the parent listener
	 * @param message {@link RML} the message
	 */
	public DataTypeControl(IListener parent, TMessage message)
	{
		super(parent, message);
		
		//Initialize using data
		initDataTypeControl(parent, message);
	}
	/**
	 * Adds {@link DataSource} event {@link IDataTypeControlListener}.
	 * @param listener {@link IDataTypeControlListener} the listener
	 */
	public final synchronized void addControlDataSourceListener(IControlDataSourceListener listener)
	{
		if(this.dataSourceListeners == null)
		{
			this.dataSourceListeners = new Vector<IControlDataSourceListener>();
		}
		this.dataSourceListeners.add(listener);
	}
	/**
	 * Adds {@link Document} event {@link IDataTypeControlListener}.
	 * @param listener {@link IDataTypeControlListener} the listener
	 */
	public final synchronized void addControlDocumentListener(IControlDocumentListener listener)
	{
		if(this.documentListeners == null)
		{
			this.documentListeners = new Vector<IControlDocumentListener>();
		}
		this.documentListeners.add(listener);
	}
	/**
	 * Adds {@link DocumentRoot} event {@link IDataTypeControlListener}.
	 * @param listener {@link IDataTypeControlListener} the listener
	 */
	public final synchronized void addControlDocumentRootListener(IControlDocumentRootListener listener)
	{
		if(this.documentRootListeners == null)
		{
			this.documentRootListeners = new Vector<IControlDocumentRootListener>();
		}
		this.documentRootListeners.add(listener);
	}
	/**
	 * Adds {@link Field} event {@link IControlFieldListener}.
	 * @param listener {@link IControlFieldListener} the listener
	 */
	public final synchronized void addControlFieldListener(IControlFieldListener listener)
	{
		if(this.fieldListeners == null)
		{
			this.fieldListeners = new Vector<IControlFieldListener>();
		}
		this.fieldListeners.add(listener);
	}
	/**
	 * Adds {@link FieldRoot} event {@link IControlFieldRootListener}.
	 * @param listener {@link IControlFieldRootListener} the listener
	 */
	public final synchronized void addControlFieldRootListener(IControlFieldRootListener listener)
	{
		if(this.fieldRootListeners == null)
		{
			this.fieldRootListeners = new Vector<IControlFieldRootListener>();	
		}		
		this.fieldRootListeners.add(listener);
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
		return this.data != null ? this.data.containsKey(key) : false;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object value) 
	{
		return this.data != null ? this.data.containsValue(value) : false;
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
	public Set<Entry<DataTypeData, Object>> entrySet() 
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
		return this.data != null ? this.data.isEmpty() : true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<DataTypeData> iterator() {
		return this.data.keySet().iterator();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<DataTypeData> keySet() 
	{
		return this.data.keySet();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#LoadControl(org.httprobot.common.RML)
	 */
	@Override
	public final void LoadControl(RML message) 
	{
		if (this.message == null)
			throw new NullPointerException("Control.LoadControl: Control's message hasn't been initialized");
		
		//Insert loaded inherited data
		this.put(DataTypeData.UUID, message.getUuid());
		this.put(DataTypeData.INHERITED, message.getInherited());
		
		if(!Boolean.class.cast(this.get(DataTypeData.INHERITED)) ? 
				message.getRuntimeOptions() != null : false) {

			for(RuntimeOptions option : message.getRuntimeOptions())
			{
				this.put(DataTypeData.RUNTIME_OPTIONS, option);
			}
		}
		
		//Set abstract DataType's message members.
		if(message instanceof DataType)
		{
			this.put(DataTypeData.NTP, DataType.class.cast(message).getNTP());
		}
		
		//Send event to parent wit input message as argument.
		ControlLoadedEvent(new ControlEventArgs(this, ControlEventType.LOAD, message));
		
		//Send specific loaded events with current control's message.
		if(this.message instanceof Field)
		{
			ControlFieldEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		}
		else if(this.message instanceof FieldRoot)
		{
			ControlFieldRootEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		}
		else if(this.message instanceof DataSource)
		{
			ControlDataSourceEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		}
		else if(this.message instanceof Document)
		{
			ControlDocumentEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		}
		else if(this.message instanceof DocumentRoot)
		{
			ControlDocumentRootEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
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
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDataSourceListener#OnControlDataSource_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataSource_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlDataSource_Changed not implemented method");
	}		
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDataSourceListener#OnControlDataSource_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataSource_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlDataSource_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDataSourceListener#OnControlDataSource_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataSource_Loaded(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlDataSource_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDataSourceListener#OnControlDataSource_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataSource_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlDataSource_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDataSourceListener#OnControlDataSource_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataSource_Rendered(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlDataSource_Rendered not implemented method");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDataSourceListener#OnControlDataSource_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataSource_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlDataSource_Write not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDocumentListener#OnControlDocument_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocument_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlDocument_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDocumentListener#OnControlDocument_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocument_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlDocument_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDocumentListener#OnControlDocument_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocument_Loaded(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlDocument_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDocumentListener#OnControlDocument_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocument_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlDocument_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDocumentListener#OnControlDocument_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocument_Rendered(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlDocument_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDocumentListener#OnControlDocument_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocument_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlDocument_Write not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDocumentRootListener#OnControlDocumentRoot_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocumentRoot_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlDocumentRoot_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDocumentRootListener#OnControlDocumentRoot_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocumentRoot_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlDocumentRoot_Init not implemented method");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDocumentRootListener#OnControlDocumentRoot_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocumentRoot_Loaded(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlDocumentRoot_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDocumentRootListener#OnControlDocumentRoot_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocumentRoot_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlDocumentRoot_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDocumentRootListener#OnControlDocumentRoot_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocumentRoot_Rendered(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlDocumentRoot_Rendered not implemented method");		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDocumentRootListener#OnControlDocumentRoot_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocumentRoot_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlDocumentRoot_Write not implemented method");	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlFieldListener#OnControlField_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlField_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlField_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlFieldListener#OnControlField_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlField_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlField_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlFieldListener#OnControlField_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlField_Loaded(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlField_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlFieldListener#OnControlField_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlField_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlField_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlFieldListener#OnControlField_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlField_Rendered(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlField_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlFieldListener#OnControlField_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlField_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlField_Write not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlFieldsListener#OnControlFields_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRoot_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlFields_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlFieldsListener#OnControlFields_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRoot_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlFields_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlFieldsListener#OnControlFields_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRoot_Loaded(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlFields_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlFieldsListener#OnControlFields_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRoot_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlFields_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlFieldsListener#OnControlFields_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRoot_Rendered(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlFields_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlFieldsListener#OnControlFields_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRoot_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeControl.OnControlFields_Write not implemented method");
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Object put(DataTypeData key, Object value) 
	{
		switch (key) 
		{
		case UUID:
			this.message.setUuid(UUID.class.cast(value));
			break;

		case INHERITED:
			this.message.setInherited(Boolean.class.cast(value));
			break;

		case RUNTIME_OPTIONS:
			this.message.addRuntimeOption(RuntimeOptions.class.cast(value));
			break;

		case NTP:
			this.message.setNTP(Date.class.cast(value));
			break;
			
		case DATA_SOURCE:
			//Check input value is a DataSource message.
			if(value.equals(this.message)) 
			{
				RenderControl(this.message);
				ControlDataSourceEvent(new ControlEventArgs(this, ControlEventType.RENDER, this.message));
			}
			break;
			
		case DOCUMENT_ROOT:
			//Check input value is the DocumentRoot message.
			if(value.equals(this.message)) 
			{
				RenderControl(this.message);
				ControlDocumentRootEvent(new ControlEventArgs(this, ControlEventType.RENDER, this.message));
			}
			break;
			
		case DOCUMENT:
			//Check input value is a Document message.
			if(value.equals(this.message)) 
			{
				RenderControl(this.message);
				ControlDocumentEvent(new ControlEventArgs(this, ControlEventType.RENDER, this.message));
			}
			break;
			
		case FIELD_ROOT:
			//Check input value is a FieldRoot message.
			if(value.equals(this.message)) 
			{	
				RenderControl(this.message);
				ControlFieldRootEvent(new ControlEventArgs(this, ControlEventType.RENDER, this.message));
			}
			break;
			
		case FIELD:
			//Check input value is a Field message.
			if(value.equals(this.message)) 
			{
				RenderControl(this.message);
				ControlFieldEvent(new ControlEventArgs(this, ControlEventType.RENDER, this.message));
			}
			break;
			
		default:
			break;
		}
		
		//Update DataType data.	
		return this.data.put(key, value);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends DataTypeData, ? extends Object> m) {
		
		for(DataTypeData dataKey : m.keySet())
		{
			if(this.put(dataKey, m.get(dataKey)) == null)
			{
				//TODO report error
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
	 * Removes {@link Document} event {@link IDataTypeControlListener}.
	 * @param listener {@link IDataTypeControlListener} the listener
	 */
	public final synchronized void removeDocumentListener(IDataTypeControlListener listener)
	{
		documentListeners.remove(listener);
	}
	/**
	 * Removes {@link DocumentRoot} event {@link IDataTypeControlListener}.
	 * @param listener {@link IDataTypeControlListener} the listener
	 */
	public final synchronized void removeDocumentRootListener(IDataTypeControlListener listener)
	{
		documentRootListeners.remove(listener);
	}
	/**
	 * Removes {@link Field} event {@link IDataTypeControlListener}.
	 * @param listener {@link IDataTypeControlListener} the listener
	 */
	public final synchronized void removeFieldListener(IDataTypeControlListener listener)
	{
		fieldListeners.remove(listener);
	}
	/**
	 * Removes {@link FieldRoot} event {@link IDataTypeControlListener}.
	 * @param listener {@link IDataTypeControlListener} the listener
	 */
	public final synchronized void removeFieldsListener(IDataTypeControlListener listener)
	{
		fieldRootListeners.remove(listener);
	}
	/**
	 * Removes {@link DataSource} event {@link IDataTypeControlListener}.
	 * @param listener {@link IDataTypeControlListener} the listener
	 */
	public final synchronized void removeServerInfoListener(IDataTypeControlListener listener)
	{
		dataSourceListeners.remove(listener);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#setIsRendered(java.lang.Boolean)
	 */
	@Override
	public void render(Boolean value) 
	{
		super.render(value);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#RenderControl()
	 */
	@Override
	public void RenderControl(RML message) 
	{
		super.RenderControl(message);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() 
	{
		return this.data != null ? this.data.size() : 0;
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
	 * Initializes {@link DataType} message control.
	 * @param parent {@link IControlListener} the parent
	 * @param message {@link RML} the message
	 */
	private final void initDataTypeControl(IListener parent, TMessage message) 
	{
		//Initialize control's data.
		data = new HashMap<DataTypeData, Object>();
		
		//Initialize event's arguments.
		ControlEventArgs e = new ControlEventArgs(this, ControlEventType.INIT, message);
		
		if(message instanceof Field)
		{
			addControlFieldListener(this);

			//Check if parent is null and match with parent listener's type.			
			if(parent != null ?
					parent instanceof IControlFieldListener : false) {

				//Parent is properly listening initialization event
				addControlFieldListener(this);
			}
			//Send event to parent
			ControlFieldEvent(e);
		}
		else if(message instanceof FieldRoot)
		{
			addControlFieldRootListener(this);

			//Check if parent is null and match with parent listener's type.			
			if(parent != null ?
					parent instanceof IControlFieldRootListener : false) {

				//Parent is properly listening initialization event
				addControlFieldRootListener(IControlFieldRootListener.class.cast(parent));
			}
			//Send event to parent
			ControlFieldRootEvent(e);
		}
		else if(message instanceof DataSource)
		{
			addControlDataSourceListener(this);

			//Check if parent is null and match with parent listener's type.			
			if(parent != null ?
					parent instanceof IControlDataSourceListener : false) {

				//Parent is properly listening initialization event
				addControlDataSourceListener(IControlDataSourceListener.class.cast(parent));
			}
			ControlDataSourceEvent(e);
		}
		else if(message instanceof Document)
		{
			addControlDocumentListener(this);

			//Check if parent is null and match with parent listener's type.			
			if(parent != null ?
					parent instanceof IControlDocumentListener : false) {

				//Parent is properly listening initialization event
				addControlDocumentListener(IControlDocumentListener.class.cast(parent));
			}
			ControlDocumentEvent(e);
		}
		else if(message instanceof DocumentRoot)
		{
			addControlDocumentRootListener(this);
			
			//Check if parent is null and match with parent listener's type.			
			if(parent != null ?
					parent instanceof IControlDocumentRootListener : false) {

				//Parent is properly listening initialization event
				addControlDocumentRootListener(IControlDocumentRootListener.class.cast(parent));
			}
			ControlDocumentRootEvent(e);
		}
	}
	/**
	 * Fires {@link DataSource} event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlDataSourceEvent(ControlEventArgs e) 
	{
		for (IControlDataSourceListener listener : dataSourceListeners) 
		{
			try 
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlDataSource_Init(this, e);
						break;
					case READ:
						listener.OnControlDataSource_Read(this, e);
						break;
					case LOAD:
						listener.OnControlDataSource_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlDataSource_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlDataSource_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlDataSource_Write(this, e);
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
	 * Fires {@link Document} event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlDocumentEvent(ControlEventArgs e) 
	{
		for (IControlDocumentListener listener : documentListeners) 
		{
			try 
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlDocument_Init(this, e);
						break;
					case READ:
						listener.OnControlDocument_Read(this, e);
						break;
					case LOAD:
						listener.OnControlDocument_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlDocument_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlDocument_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlDocument_Write(this, e);
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
	 * Fires {@link DocumentRoot} event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlDocumentRootEvent(ControlEventArgs e) 
	{
		for (IControlDocumentRootListener listener : documentRootListeners) 
		{
			try 
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlDocumentRoot_Init(this, e);
						break;
					case READ:
						listener.OnControlDocumentRoot_Read(this, e);
						break;
					case LOAD:
						listener.OnControlDocumentRoot_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlDocumentRoot_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlDocumentRoot_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlDocumentRoot_Write(this, e);
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
	 * Fires {@link Field} event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlFieldEvent(ControlEventArgs e) 
	{
		for (IControlFieldListener listener : fieldListeners) 
		{
			try
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlField_Init(this, e);
						break;
					case READ:
						listener.OnControlField_Read(this, e);
						break;
					case LOAD:
						listener.OnControlField_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlField_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlField_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlField_Write(this, e);
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
	 * Fires {@link FieldRoot} event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlFieldRootEvent(ControlEventArgs e) 
	{
		for (IControlFieldRootListener listener : fieldRootListeners) 
		{
			try
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlFieldRoot_Init(this, e);
						break;
					case READ:
						listener.OnControlFieldRoot_Read(this, e);
						break;
					case LOAD:
						listener.OnControlFieldRoot_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlFieldRoot_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlFieldRoot_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlFieldRoot_Write(this, e);
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
