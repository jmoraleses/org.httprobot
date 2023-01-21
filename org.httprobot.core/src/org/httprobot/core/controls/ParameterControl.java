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

import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.Parameter;
import org.httprobot.common.RML;
import org.httprobot.common.definitions.Enums.ControlEventType;
import org.httprobot.common.definitions.Enums.ParameterType;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.params.BannedWord;
import org.httprobot.common.params.Constant;
import org.httprobot.common.params.ServerUrl;
import org.httprobot.common.params.StartUrl;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.ParameterData;
import org.httprobot.core.controls.interfaces.impl.IControlImpl;
import org.httprobot.core.controls.interfaces.listeners.IParameterControlListener;
import org.httprobot.core.controls.parameters.interfaces.IControlBannedWordListener;
import org.httprobot.core.controls.parameters.interfaces.IControlConstantListener;
import org.httprobot.core.controls.parameters.interfaces.IControlServerUrlListener;
import org.httprobot.core.controls.parameters.interfaces.IControlStartUrlListener;
import org.httprobot.core.interfaces.IDataMappingImpl;

/**
 * Parameter message control class. Inherits {@link Control}.
 * <br> 
 * It's {@link IParameterControlListener}.
 * <br> 
 * @author joan
 *
 */
@XmlTransient
public abstract class ParameterControl
	<TMessage extends Parameter, IListener extends IControlListener> 
		extends Control	<TMessage, IListener> 
		implements IParameterControlListener, IDataMappingImpl<ParameterData, Object> {
	
	/**
	 * 3615695472888148015L
	 */
	private static final long serialVersionUID = 3615695472888148015L;
	
	/**
	 * {@link BannedWord} message listeners.
	 */
	private Vector<IControlBannedWordListener> bannedWordListeners = null;
	/**
	 * {@link Constant} message listeners.
	 */
	private Vector<IControlConstantListener> constantListeners = null;	
	/**
	 * {@link ServerUrl} message listeners.
	 */
	private Vector<IControlServerUrlListener> serverUrlListeners = null;
	/**
	 * {@link StartUrl} message listeners.
	 */
	private Vector<IControlStartUrlListener> startUrlListeners = null;
	/**
	 * The {@link Parameter} message control data.
	 */
	Map<ParameterData, Object> data;
	
	/**
	 * {@link Parameter} control default class constructor.
	 */
	public ParameterControl() 
	{
		super();
		
		//Initialize Parameter message control.
		initParameterControl(null, null);
	}	
	/**
	 * {@link Parameter} control class constructor.
	 * @param parent {@link IControlImpl} the parent
	 * @param message {@link RML} the message
	 */
	public ParameterControl(IListener parent, TMessage message) 
	{
		super(parent, message);
		
		//Initialize Parameter message control.
		initParameterControl(parent, message);
	}
	/**
	 * Adds {@link IControlBannedWordListener} to current instance.
	 * @param listener {@link IControlConstantListener} the listener
	 */
	public final synchronized void addControlBannedWordListener(IControlBannedWordListener listener)
	{
		if(this.bannedWordListeners == null)
		{
			this.bannedWordListeners = new Vector<IControlBannedWordListener>();
		}
		this.bannedWordListeners.add(listener);
	}
	/**
	 * Adds {@link IControlConstantListener} to current instance.
	 * @param listener {@link IControlConstantListener} the listener
	 */
	public final synchronized void addControlConstantListener(IControlConstantListener listener)
	{
		if(this.constantListeners == null)
		{
			this.constantListeners = new Vector<IControlConstantListener>();
		}
		this.constantListeners.add(listener);
	}
	/**
	 * Adds {@link IControlServerUrlListener} to current instance.
	 * @param listener {@link IControlServerUrlListener} the listener
	 */
	public final synchronized void addControlServerUrlListener(IControlServerUrlListener listener)
	{
		if(this.serverUrlListeners == null)
		{
			this.serverUrlListeners = new Vector<IControlServerUrlListener>();
		}
		this.serverUrlListeners.add(listener);
	}
	/**
	 * Adds {@link IControlStartUrlListener} to current instance.
	 * @param listener {@link IControlStartUrlListener} the listener
	 */
	public final synchronized void addControlStartUrlListener(IControlStartUrlListener listener)
	{
		if(this.startUrlListeners == null)
		{
			this.startUrlListeners = new Vector<IControlStartUrlListener>();
		}
		this.startUrlListeners.add(listener);
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
	public void ChangeControl(RML message) {
		// TODO Auto-generated method stub
		super.ChangeControl(message);
	}	
	/* (non-Javadoc)
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<java.util.Map.Entry<ParameterData, Object>> entrySet() 
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
	public void InitControl(RML message) {
		// TODO Auto-generated method stub
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
	public Iterator<ParameterData> iterator()
	{
		return this.data.keySet().iterator();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<ParameterData> keySet() 
	{
		return this.data.keySet();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#LoadControl(org.httprobot.common.RML)
	 */
	@Override
	public void LoadControl(RML message) 
	{		
		if (this.message == null)
			throw new NullPointerException("ParameterControl.LoadControl: Control's message hasn't been initialized");
		
		//Insert loaded inherited data
		this.put(ParameterData.UUID, message.getUuid());
		this.put(ParameterData.INHERITED, message.getInherited());
		
		if(!Boolean.class.cast(this.get(ParameterData.INHERITED)) ? 
				message.getRuntimeOptions() != null : false) {

			for(RuntimeOptions option : message.getRuntimeOptions())
			{
				this.put(ParameterData.RUNTIME_OPTIONS, option);
			}
		}

		//Send Load event to current class.
		ControlLoadedEvent(new ControlEventArgs(this, ControlEventType.LOAD, message));
		
		///Send Loaded event to parent listeners.
		if(this.message instanceof ServerUrl)
		{
			ControlServerUrlEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		}
		else if(this.message instanceof StartUrl)
		{
			ControlStartUrlEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		}
		else if(this.message instanceof Constant)
		{
			ControlConstantEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		}
		else if(this.message instanceof BannedWord)
		{
			ControlBannedWordEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
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
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlBannedWordListener#OnControlBannedWord_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlBannedWord_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ParameterControl.OnControlBannedWord_Changed: method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlBannedWordListener#OnControlBannedWord_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlBannedWord_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ParameterControl.OnControlBannedWord_Init: method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlBannedWordListener#OnControlBannedWord_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlBannedWord_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ParameterControl.OnControlBannedWord_Loaded: method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlBannedWordListener#OnControlBannedWord_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlBannedWord_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ParameterControl.OnControlBannedWord_Read: method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlBannedWordListener#OnControlBannedWord_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlBannedWord_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ParameterControl.OnControlBannedWord_Rendered: method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlBannedWordListener#OnControlBannedWord_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlBannedWord_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ParameterControl.OnControlBannedWord_Write: method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlConstantListener#OnControlConstant_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConstant_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ParameterControl.OnControlConstant_Changed: method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlConstantListener#OnControlConstant_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConstant_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ParameterControl.OnControlConstant_Init: method not overridden");	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlConstantListener#OnControlConstant_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConstant_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ParameterControl.OnControlConstant_Loaded: method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlConstantListener#OnControlConstant_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConstant_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ParameterControl.OnControlConstant_Read: method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlConstantListener#OnControlConstant_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConstant_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ParameterControl.OnControlConstant_Rendered: method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlConstantListener#OnControlConstant_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConstant_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ParameterControl.OnControlConstant_Write: method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlServerUrlListener#OnControlServerUrl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServerUrl_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ParameterControl.OnControlServerUrl_Changed: method not overridden");	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlServerUrlListener#OnControlServerUrl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServerUrl_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ParameterControl.OnControlServerUrl_Init: method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlServerUrlListener#OnControlServerUrl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServerUrl_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ParameterControl.OnControlServetUrl_Loaded: method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlServerUrlListener#OnControlServerUrl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServerUrl_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ParameterControl.OnControlServerUrl_Read: method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlServerUrlListener#OnControlServerUrl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServerUrl_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ParameterControl.OnControlServerUrl_Rendered: method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlServerUrlListener#OnControlServerUrl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServerUrl_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"ParameterControl.OnControlServerUrl_Write: method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlStartUrlListener#OnControlStartUrl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlStartUrl_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ParameterControl.OnControlStartUrl_Changed: method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlStartUrlListener#OnControlStartUrl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlStartUrl_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ParameterControl.OnControlStartUrl_Init: method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlStartUrlListener#OnControlStartUrl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlStartUrl_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"ParameterControl.OnControlStartUrl_Loaded: method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlStartUrlListener#OnControlStartUrl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlStartUrl_Read(Object sender, ControlEventArgs e) 
 			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ParameterControl.OnControlStartUrl_Read: method not overridden");	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlStartUrlListener#OnControlStartUrl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlStartUrl_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ParameterControl.OnControlStartUrl_Rendered: method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlStartUrlListener#OnControlStartUrl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlStartUrl_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ParameterControl.OnControlStartUrl_Write: method not overridden");
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Object put(ParameterData key, Object value) 
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
				this.message.getRuntimeOptions().add(RuntimeOptions.class.cast(value));
				break;
				
			case PARAMETER_NAME:
				this.message.setParamName(String.class.cast(value));
				break;
				
			case PARAMETER_TYPE:
				this.message.setParamType(ParameterType.class.cast(value));
				break;
				
			case VALUE:
				this.message.setValue(String.class.cast(value));
				break;
				
			case SERVER_URL:
				if(value.equals(this.message)) 
				{					
					RenderControl(this.message);
					ControlServerUrlEvent(new ControlEventArgs(this, ControlEventType.RENDER, ServerUrl.class.cast(value)));
				}
				break;
				
			case START_URL:
				if(value.equals(this.message))
				{
					RenderControl(this.message);
					ControlStartUrlEvent(new ControlEventArgs(this, ControlEventType.RENDER, StartUrl.class.cast(value)));
				}
				break;

			case CONSTANT:
				if(value.equals(this.message))
				{
					RenderControl(this.message);
					ControlConstantEvent(new ControlEventArgs(this, ControlEventType.RENDER, Constant.class.cast(value)));
				}
				break;

			case BANNED_WORD:
				if(value.equals(this.message))
				{
					RenderControl(this.message);
					ControlBannedWordEvent(new ControlEventArgs(this, ControlEventType.RENDER, BannedWord.class.cast(value)));
				}
				break;
				
			default:
				break;
		}
		
		//Update parameter data
		return this.data.put(key, value);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends ParameterData, ? extends Object> m) {
		
		for(ParameterData attribute : m.keySet())
		{
			if(this.put(attribute, m.get(attribute)) == null)
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
	 * Removes {@link IControlBannedWordListener} to current instance.
	 * @param listener {@link IControlConstantListener} the listener
	 */
	public final synchronized void removeBannedWordListener(IControlBannedWordListener listener)
	{
		this.bannedWordListeners.remove(listener);
	}
	/**
	 * Removes {@link IControlConstantListener} to current instance.
	 * @param listener {@link IControlConstantListener} the listener
	 */
	public final synchronized void removeConstantListener(IControlConstantListener listener)
	{
		this.constantListeners.remove(listener);
	}
	/**
	 * Removes {@link IControlServerUrlListener}
	 * @param listener {@link IControlServerUrlListener} the listener
	 */
	public final synchronized void removeServerUrlListener(IControlServerUrlListener listener)
	{
		this.serverUrlListeners.remove(listener);
	}
	/**
	 * Removes {@link IControlStartUrlListener}
	 * @param listener {@link IControlStartUrlListener} the listener
	 */
	public final synchronized void removeStartUrlListener(IControlStartUrlListener listener)
	{
		this.startUrlListeners.remove(listener);
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
	 * Initializes {@link ParameterControl}
	 * @param parent {@link IControlListener} the parent
	 * @param message {@link RML} the message
	 */
	private final void initParameterControl(IListener parent, TMessage message)
	{
		//Initialize control's data
		this.data = new HashMap<ParameterData, Object>();

		ControlEventArgs e = new ControlEventArgs(this, ControlEventType.INIT, message);
		
		if(message instanceof ServerUrl)
		{
			addControlServerUrlListener(this);

			//Check if parent is null and match with parent listener's type.
			if(parent != null ?
				parent instanceof IControlServerUrlListener : false) {
				
				//Parent is properly listening initialization event
				addControlServerUrlListener(IControlServerUrlListener.class.cast(parent));
			}
			//Send event to parent.
			ControlServerUrlEvent(e);
		}
		else if(message instanceof StartUrl)
		{
			addControlStartUrlListener(this);
			
			//Check if parent is null and match with parent listener's type.
			if(parent != null ?
				parent instanceof IControlStartUrlListener : false) {
				
				//Parent is properly listening initialization event
				addControlStartUrlListener(IControlStartUrlListener.class.cast(parent));
			}
			//Send event to parent.
			ControlStartUrlEvent(e);
		}
		else if(message instanceof Constant)
		{
			addControlConstantListener(this);

			//Check if parent is null and match with parent listener's type.
			if(parent != null ?
				parent instanceof IControlConstantListener : false) {
				
				//Parent is properly listening initialization event
				addControlConstantListener(IControlConstantListener.class.cast(parent));
			}
			ControlConstantEvent(e);
		}
		else if(message instanceof BannedWord)
		{
			addControlBannedWordListener(this);
			
			//Check if parent is null and match with parent listener's type.
			if(parent != null ?
				parent instanceof IControlBannedWordListener : false) {
				
				//Parent is properly listening initialization event
				addControlBannedWordListener(IControlBannedWordListener.class.cast(parent));
			}
			ControlBannedWordEvent(e);
		}
	}
	/**
	 * Fires {@link BannedWord} message control event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected void ControlBannedWordEvent(ControlEventArgs e)
	{
		for(IControlBannedWordListener listener : this.bannedWordListeners)
		{
			try 
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlBannedWord_Init(this, e);
						break;
					case READ:
						listener.OnControlBannedWord_Read(this, e);
						break;
					case LOAD:
						listener.OnControlBannedWord_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlBannedWord_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlBannedWord_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlBannedWord_Write(this, e);
						break;
					default:
						break;
				}				
			} 
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			}
			catch (InconsistenMessageException e1) 
			{				
				e1.printStackTrace();
			}
		}		
	}
	/**
	 * Fires {@link Constant} message control event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected void ControlConstantEvent(ControlEventArgs e)
	{
		for(IControlConstantListener listener : this.constantListeners)
		{
			try 
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlConstant_Init(this, e);
						break;
					case READ:
						listener.OnControlConstant_Read(this, e);
						break;
					case LOAD:
						listener.OnControlConstant_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlConstant_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlConstant_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlConstant_Write(this, e);
						break;
					default:
						break;
				}				
			} 
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			}
			catch (InconsistenMessageException e1) 
			{				
				e1.printStackTrace();
			}
		}		
	}
	/**
	 * Fires {@link ServerUrl} message control event
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected void ControlServerUrlEvent(ControlEventArgs e)
	{
		for(IControlServerUrlListener listener : this.serverUrlListeners)
		{
			try 
			{
				switch (e.getControlEventType())
				{
					case INIT:
						listener.OnControlServerUrl_Init(this, e);
						break;
					case READ:
						listener.OnControlServerUrl_Read(this, e);
						break;
					case LOAD:
						listener.OnControlServerUrl_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlServerUrl_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlServerUrl_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlServerUrl_Write(this, e);
						break;
					default:
						break;
				}				
			} 
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			}
			catch (InconsistenMessageException e1) 
			{				
				e1.printStackTrace();
			}
		}		
	}
	/**
	 * Fires {@link StartUrl} message control event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected void ControlStartUrlEvent(ControlEventArgs e)
	{
		for(IControlStartUrlListener listener : this.startUrlListeners)
		{
			try 
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlStartUrl_Init(this, e);
						break;
					case READ:
						listener.OnControlStartUrl_Read(this, e);
						break;
					case LOAD:
						listener.OnControlStartUrl_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlStartUrl_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlStartUrl_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlStartUrl_Write(this, e);
						break;
					default:
						break;
				}
			}
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			}
			catch (InconsistenMessageException e1) 
			{
				e1.printStackTrace();
			}
		}		
	}	
}
