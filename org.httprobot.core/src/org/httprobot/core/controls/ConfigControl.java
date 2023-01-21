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

import org.httprobot.common.RML;
import org.httprobot.common.Config;
import org.httprobot.common.config.Configuration;
import org.httprobot.common.config.Log;
import org.httprobot.common.config.ServiceConnection;
import org.httprobot.common.config.Session;
import org.httprobot.common.definitions.Enums.ControlEventType;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.placeholders.operators.Delimiters;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.ConfigData;
import org.httprobot.core.controls.config.interfaces.IControlConfigurationListener;
import org.httprobot.core.controls.config.interfaces.IControlLogListener;
import org.httprobot.core.controls.config.interfaces.IControlServiceConnectionListener;
import org.httprobot.core.controls.config.interfaces.IControlSessionListener;
import org.httprobot.core.controls.interfaces.listeners.IConfigControlListener;
import org.httprobot.core.controls.interfaces.listeners.IDataTypeControlListener;
import org.httprobot.core.interfaces.IDataMappingImpl;

/**
 * {@link Config} message control class. Inherits {@link Control}.
 * <br>
 * It's {@link IConfigControlListener}. {@link Iterable} through
 * {@link ConfigData} getting Object values.
 * @author joan
 *
 * @param <TConfigMessage> The {@link Config} message.
 * @param <IListener> The {@link IControlListener} interface type of instantiated control.
 */
@XmlTransient
public abstract class ConfigControl
	<TConfigMessage extends Config, IListener extends IControlListener> 
		extends Control<TConfigMessage, IListener> 
		implements IConfigControlListener, IDataMappingImpl<ConfigData, Object> {
	
	/**
	 * -3219652987951425693L
	 */
	private static final long serialVersionUID = -3219652987951425693L;
	
	/**
	 * {@link Configuration} listeners
	 */
	private Vector<IControlConfigurationListener> configurationListeners = null;
	/**
	 * {@link Log} listeners
	 */
	private Vector<IControlLogListener> logListeners = null;
	/**
	 * {@link ServiceConnection} listeners
	 */
	private Vector<IControlServiceConnectionListener> serviceConnectionListeners = null;
	/**
	 * {@link Session} Listeners
	 */
	private Vector<IControlSessionListener> sessionListeners = null;
	/**
	 * The message control data with {@link ConfigData} as primary keys..
	 */
	Map<ConfigData, Object> data;
	
	/**
	 * {@link Config} message control default class constructor.
	 */
	public ConfigControl() 
	{
		super();
		
		initConfigControl(null, null);
	}
	/**
	 * {@link Config} message control class constructor.
	 * @param parent
	 */
	public ConfigControl(IListener parent, TConfigMessage message) 
	{
		super(parent, message);
		
		initConfigControl(message, parent);
	}
	/**
	 * Adds {@link IControlConfigurationListener} event listener.
	 * @param listener {@link IDataTypeControlListener} the listener
	 */
	public final synchronized void addControlConfigurationListener(IControlConfigurationListener listener)
	{
		if(this.configurationListeners == null)
		{
			this.configurationListeners = new Vector<IControlConfigurationListener>();
		}
		this.configurationListeners.add(listener);
	}
	/**
	 * Adds {@link IControlLogListener} event listener.
	 * @param listener {@link IDataTypeControlListener} the listener
	 */
	public final synchronized void addControlLogListener(IControlLogListener listener)
	{
		if(this.logListeners == null)
		{
			this.logListeners = new Vector<IControlLogListener>();
		}
		this.logListeners.add(listener);
	}
	/**
	 * Adds {@link ServiceConnection} event {@link IControlServiceConnectionListener}.
	 * @param listener {@link IControlServiceConnectionListener} the listener
	 */
	public final synchronized void addControlServiceConnectionListener(IControlServiceConnectionListener listener)
	{
		if(this.serviceConnectionListeners == null)
		{
			this.serviceConnectionListeners = new Vector<IControlServiceConnectionListener>();
		}
		this.serviceConnectionListeners.add(listener);
	}
	/**
	 * Adds {@link IControlSessionListener} event listener.
	 * @param listener {@link IControlSessionListener} the listener
	 */
	public final synchronized void addControlSessionListener(IControlSessionListener listener)
	{
		if(this.sessionListeners == null)
		{
			this.sessionListeners = new Vector<IControlSessionListener>();
		}
		this.sessionListeners.add(listener);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#ChangeControl(org.httprobot.common.RML)
	 */
	@Override
	public void ChangeControl(RML message)
	{		
		//Update loaded inherited data
		this.put(ConfigData.UUID, message.getUuid());
		this.put(ConfigData.INHERITED, message.getInherited());
		
		for(RuntimeOptions option : message.getRuntimeOptions())
		{
			this.put(ConfigData.RUNTIME_OPTIONS, option);
		}
		super.ChangeControl(message);		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#InitControl(org.httprobot.common.RML)
	 */
	@Override
	public void InitControl(RML message) 
	{
		super.InitControl(message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#LoadControl(org.httprobot.common.RML)
	 */
	@Override
	public void LoadControl(RML message) 
	{
		/*Full override.*/		
		if (message == null 
				&& this.message != null) {
			
			throw new NullPointerException("Control.LoadControl: Control's message hasn't been initialized");
		}
		
		//Update loaded inherited data
		this.put(ConfigData.UUID, message.getUuid());
		this.put(ConfigData.INHERITED, message.getInherited());
		
		if(!Boolean.class.cast(this.get(ConfigData.INHERITED)) ? 
				message.getRuntimeOptions() != null : false) {

			for(RuntimeOptions option : message.getRuntimeOptions())
			{
				this.put(ConfigData.RUNTIME_OPTIONS, option);
			}
		}

		//Send event to parent wit input message as argument.
		ControlLoadedEvent(new ControlEventArgs(this, ControlEventType.LOAD, message));
		
		//Send specific loaded events with current control's message.
		if(this.message instanceof Configuration)
		{
			ControlConfigurationEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		}
		else if(this.message instanceof Log)
		{
			ControlConfigurationEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		}
		else if(this.message instanceof Session)
		{
			ControlConfigurationEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		}
		else if(this.message instanceof ServiceConnection)
		{
			ControlServiceConnectionEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#RenderControl()
	 */
	@Override
	public void RenderControl(RML message) 
	{
		super.RenderControl(message);
		
		if(message instanceof Configuration)
		{
			
		}
		else if(message instanceof Log)
		{
			ControlConfigurationEvent(new ControlEventArgs(this, ControlEventType.RENDER, message));
		}
		else if(message instanceof ServiceConnection)
		{
			
		}
		else if(message instanceof Session)
		{
			ControlConfigurationEvent(new ControlEventArgs(this, ControlEventType.RENDER, message));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Change(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException ;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#OnControl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Initialize(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException ;	
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#OnControl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Load(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException ;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#OnControl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException ;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#OnControl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Render(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException ;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#OnControl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	public abstract void Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException ;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlConfigurationListener#OnControlConfiguration_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConfiguration_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigControl.OnControlConfiguration_Changed not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlConfigurationListener#OnControlConfiguration_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConfiguration_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigControl.OnControlConfiguration_Init not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlConfigurationListener#OnControlConfiguration_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConfiguration_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigControl.OnControlConfiguration_Loaded not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlConfigurationListener#OnControlConfiguration_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConfiguration_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigControl.OnConfigRead not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlConfigurationListener#OnControlConfiguration_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConfiguration_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigControl.OnControlConfiguration_Rendered not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlConfigurationListener#OnControlConfiguration_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConfiguration_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigControl.OnControlConfiguration_Write not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlLogListener#OnControlLog_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlLog_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigControl.OnControlLog_Changed not implemented");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlLogListener#OnControlLog_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlLog_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigControl.OnControlLog_Init not implemented");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlLogListener#OnControlLog_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlLog_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigControl.OnControlLog_Loaded not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlLogListener#OnControlLog_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlLog_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigControl.OnControlLog_Read not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlLogListener#OnControlLog_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlLog_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		CliTools.ThrowNotImplementedException(this, 
				"ConfigControl.OnControlLog_Rendered not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlLogListener#OnControlLog_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlLog_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigControl.OnControlLog_Write not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlServiceConnectionListener#OnControlServiceConnection_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServiceConnection_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigControl.OnControlServiceOptions_Changed: not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlServiceConnectionListener#OnControlServiceConnection_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServiceConnection_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigControl.OnControlServiceOptions_Init: not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlServiceConnectionListener#OnControlServiceConnection_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServiceConnection_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigControl.OnControlServiceOptions_Loaded: not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlServiceConnectionListener#OnControlServiceConnection_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServiceConnection_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		CliTools.ThrowNotImplementedException(this, 
				"ConfigControl.OnControlServiceOptions_Read: not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlServiceConnectionListener#OnControlServiceConnection_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServiceConnection_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		CliTools.ThrowNotImplementedException(this, 
				"ConfigControl.OnControlServiceOptions_Rendered: not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlServiceConnectionListener#OnControlServiceConnection_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServiceConnection_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		CliTools.ThrowNotImplementedException(this, 
				"ConfigControl.OnControlServiceOptions_Write: not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlSessionListener#OnControlSession_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSession_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigControl.OnControlSession_Changed not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlSessionListener#OnControlSession_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSession_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigControl.OnControlSession_Init not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlSessionListener#OnControlSession_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSession_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigControl.OnControlSession_Loaded not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlSessionListener#OnControlSession_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSession_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigControl.OnControlSession_Read not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlSessionListener#OnControlSession_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSession_Rendered(Object sender, ControlEventArgs e) 
 			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigControl.OnControlSession_Rendered not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlSessionListener#OnControlSession_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSession_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ConfigControl.OnControlSession_Write not implemented");
	}
	/**
	 * Removes {@link Delimiters} event {@link IDataTypeControlListener}.
	 * @param listener {@link IDataTypeControlListener} the listener
	 */
	public final synchronized void removeControlConfigurationListener(IControlConfigurationListener listener)
	{
		configurationListeners.remove(listener);
	}
	/**
	 * Removes {@link Delimiters} event {@link IDataTypeControlListener}.
	 * @param listener {@link IDataTypeControlListener} the listener
	 */
	public final synchronized void removeControlLogListener(IConfigControlListener listener)
	{
		logListeners.remove(listener);
	}
	/**
	 * Removes {@link ServiceConnection} event {@link IControlServiceConnectionListener}.
	 * @param listener {@link IControlServiceConnectionListener} the listener
	 */
	public final synchronized void removeControlServiceConnectionListener(IControlServiceConnectionListener listener)
	{
		serviceConnectionListeners.remove(listener);
	}
	/**
	 * Removes {@link Delimiters} event {@link IDataTypeControlListener}.
	 * @param listener {@link IDataTypeControlListener} the listener
	 */
	public final synchronized void removeControlSessionListener(IConfigControlListener listener)
	{
		sessionListeners.remove(listener);
	}

	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#setMessage(org.httprobot.common.RML)
	 */
	@Override
	public void controlMessage(TConfigMessage message) 
	{
		super.controlMessage(message);
	}
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<ConfigData> iterator() 
	{
		return this.data.keySet().iterator();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() {
		return this.data != null ? this.data.size() : 0;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return this.data != null ? this.data.isEmpty() : true;
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
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public Object get(Object key) 
	{
		return this.data != null ? this.data.get(key) : null;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Object put(ConfigData key, Object value) 
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
			//Add runtime option to current message
			this.message.addRuntimeOption(RuntimeOptions.class.cast(value));
			break;
			
		case CONFIGURATION:
			if(value instanceof Configuration)
			{
				//Call Render event
				ControlConfigurationEvent(new ControlEventArgs(this, ControlEventType.RENDER, Configuration.class.cast(value)));	
			}
			break;
			
		case LOG:
			if(value instanceof Log)
			{
				//Call Log render event
				ControlLogEvent(new ControlEventArgs(this, ControlEventType.RENDER, Log.class.cast(value)));	
			}
			break;
			
		case SERVICE_CONNECTION:
			if(value instanceof ServiceConnection)
			{
				//Call ServiceConnection render event
				ControlServiceConnectionEvent(new ControlEventArgs(this, ControlEventType.RENDER, ServiceConnection.class.cast(value)));
			}
			break;
			
		case SESSION:
			if(value instanceof Session)
			{
				//Call Session render event
				ControlSessionEvent(new ControlEventArgs(this, ControlEventType.RENDER, message));	
			}
			break;
		default:
			break;
		}
		
		//Update ConfigControl data
		return this.data.put(key, value);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public Object remove(Object key) 
	{
		return this.data.remove(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends ConfigData, ? extends Object> m) {
	
		for(ConfigData configAttribute : m.keySet())
		{
			if(this.put(configAttribute, m.get(configAttribute)) == null)
			{
				//TODO report error
			}
		}
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
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<ConfigData> keySet() 
	{
		return this.data.keySet();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<Object> values() 
	{
		return this.data.values();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<Entry<ConfigData, Object>> entrySet() 
	{
		return this.data.entrySet();
	}
	/**
	 * Initializes configuration control.
	 */
	private final void initConfigControl(TConfigMessage message, IListener parent)
	{
		this.data = new HashMap<ConfigData, Object>();
		
		ControlEventArgs e = new ControlEventArgs(this, ControlEventType.INIT, message);
		
		if(message instanceof Configuration)
		{
			addControlConfigurationListener(this);
			
			//Check if parent is null and match with parent listener's type.
			if(parent != null ?
					parent instanceof IControlConfigurationListener : false) {
				
				//Parent is properly listening initialization event
				addControlConfigurationListener(IControlConfigurationListener.class.cast(parent));
			}
			//Send event to parent
			ControlConfigurationEvent(e);
		}
		else if(message instanceof Log)
		{
			addControlLogListener(this);

			//Check if parent is null and match with parent listener's type.
			if(parent != null ?
					parent instanceof IControlLogListener : false) {
				
				//Parent is properly listening initialization event
				addControlLogListener(IControlLogListener.class.cast(parent));
			}
			//Send event to parent
			ControlLogEvent(e);
		}
		else if(message instanceof Session)
		{
			addControlSessionListener(this);

			//Check if parent is null and match with parent listener's type.
			if(parent != null ?
					parent instanceof IControlSessionListener : false) {
				
				//Parent is properly listening initialization event
				addControlSessionListener(IControlSessionListener.class.cast(parent));
			}
			ControlSessionEvent(e);
		}
		else if(message instanceof ServiceConnection)
		{
			addControlServiceConnectionListener(this);

			//Check if parent is null and match with parent listener's type.
			if(parent != null ?
					parent instanceof IControlServiceConnectionListener : false) {
				
				//Parent is properly listening initialization event
				addControlServiceConnectionListener(IControlServiceConnectionListener.class.cast(parent));
			}
			ControlServiceConnectionEvent(e);
		}
	}
	/**
	 * Fires control config event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlConfigurationEvent(ControlEventArgs e)
	{
		for(IControlConfigurationListener config_listener : configurationListeners) 
		{
			try 
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						config_listener.OnControlConfiguration_Init(this, e);						
						break;
					case READ:
						config_listener.OnControlConfiguration_Read(this, e);						
						break;
					case LOAD:				
						config_listener.OnControlConfiguration_Loaded(this, e);
						break;
					case CHANGE:
						config_listener.OnControlConfiguration_Changed(this, e);
						break;
					case RENDER:
						config_listener.OnControlConfiguration_Rendered(this, e);
						break;
					case WRITE:
						config_listener.OnControlConfiguration_Write(this, e);
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
	 * Fires control log event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlLogEvent(ControlEventArgs e)
	{
		for(IControlLogListener listener : logListeners) 
		{
			try 
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlLog_Init(this, e);						
						break;
					case READ:
						listener.OnControlLog_Read(this, e);						
						break;
					case LOAD:				
						listener.OnControlLog_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlLog_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlLog_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlLog_Write(this, e);
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
	 * Fires {@link ServiceConnection} manager event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlServiceConnectionEvent(ControlEventArgs e) 
	{
		for (IControlServiceConnectionListener listener : serviceConnectionListeners) 
		{
			try 
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlServiceConnection_Init(this, e);
						break;
					case READ:
						listener.OnControlServiceConnection_Read(this, e);
						break;
					case LOAD:
						listener.OnControlServiceConnection_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlServiceConnection_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlServiceConnection_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlServiceConnection_Write(this, e);
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
	 * Fires control session event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlSessionEvent(ControlEventArgs e)
	{
		for(IControlSessionListener listener : sessionListeners) 
		{
			try 
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlSession_Init(this, e);						
						break;
					case READ:
						listener.OnControlSession_Read(this, e);						
						break;
					case LOAD:				
						listener.OnControlSession_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlSession_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlSession_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlSession_Write(this, e);
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