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

import org.httprobot.common.datatypes.Field;
import org.httprobot.common.definitions.Enums.ControlEventType;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.RML;
import org.httprobot.common.Unit;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.tools.CliTools;
import org.httprobot.common.unit.Action;
import org.httprobot.common.unit.Paginator;
import org.httprobot.common.unit.WebOptions;
import org.httprobot.core.common.Enums.UnitData;
import org.httprobot.core.controls.interfaces.listeners.IDataTypeControlListener;
import org.httprobot.core.controls.interfaces.listeners.IUnitControlListener;
import org.httprobot.core.controls.unit.interfaces.IControlActionListener;
import org.httprobot.core.controls.unit.interfaces.IControlPaginatorListener;
import org.httprobot.core.controls.unit.interfaces.IControlWebOptionsListener;
import org.httprobot.core.interfaces.IDataMappingImpl;

/**
 * {@link Unit} message control class. Inherits {@link Control}.
 * <br>
 * @author joan
 */
@XmlTransient
public abstract class UnitControl
	<TMessage extends Unit, TListener extends IControlListener>
		extends Control<TMessage, TListener> 
		implements IUnitControlListener, IDataMappingImpl<UnitData, Object> {

	/**
	 * 688385846331452429L
	 */
	private static final long serialVersionUID = 688385846331452429L;

	/**
	 * {@link Action} message control listeners.
	 */
	private Vector<IControlActionListener> actionListeners;
	/**
	 * {@link WebOptions} Listeners.
	 */
	private Vector<IControlWebOptionsListener> webOptionsListeners;
	/**
	 * {@link Paginator} message control listeners.
	 */
	private Vector<IControlPaginatorListener> paginatorListeners;
	/**
	 * The control attributes.
	 */
	Map<UnitData, Object> data;
	
	/**
	 * {@link Unit} message control default class constructor.
	 */
	public UnitControl() 
	{
		super();

		//Initialize Unit message control.
		initUnitControl(null, null);
	}
	/**
	 * {@link Unit} message control class constructor.
	 * @param parent the parent
	 * @param message the message
	 */
	public UnitControl(TListener parent, TMessage message)
	{
		super(parent, message);
		
		//Initialize Unit message control.
		initUnitControl(parent, message);
	}
	/**
	 * Adds {@link Action} event {@link IControlActionListener}.
	 * @param listener {@link IControlActionListener} the listener
	 */
	public final synchronized void addControlActionListener(IControlActionListener listener)
	{
		if(this.actionListeners == null)
		{
			this.actionListeners = new Vector<IControlActionListener>();
		}
		this.actionListeners.add(listener);
	}
	/**
	 * Adds {@link Action} event {@link IControlActionListener}.
	 * @param listener {@link IControlActionListener} the listener
	 */
	public final synchronized void addControlPaginatorListener(IControlPaginatorListener listener)
	{
		if(this.paginatorListeners == null)
		{
			this.paginatorListeners = new Vector<IControlPaginatorListener>();
		}
		this.paginatorListeners.add(listener);
	}
	/**
	 * Adds {@link WebOptions} event {@link IControlWebOptionsListener}.
	 * @param listener {@link IControlWebOptionsListener} the listener
	 */
	public final synchronized void addControlWebOptionsListener(IControlWebOptionsListener listener)
	{
		if(this.webOptionsListeners == null)
		{
			this.webOptionsListeners = new Vector<IControlWebOptionsListener>();
		}
		this.webOptionsListeners.add(listener);
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
	@Override
	public abstract void Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException ;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlActionListener#OnControlAction_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAction_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"UnitControl.OnControlAction_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlActionListener#OnControlAction_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAction_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"UnitControl.OnControlAction_Init not implemented method");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlActionListener#OnControlAction_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAction_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"UnitControl.OnControlAction_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlActionListener#OnControlAction_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAction_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"UnitControl.OnControlAction_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlActionListener#OnControlAction_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAction_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"UnitControl.OnControlAction_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlActionListener#OnControlAction_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAction_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"UnitControl.OnControlAction_Write not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlPaginatorListener#OnControlPaginator_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPaginator_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"UnitControl.OnControlPaginator_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlPaginatorListener#OnControlPaginator_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPaginator_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"UnitControl.OnControlPaginator_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlPaginatorListener#OnControlPaginator_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPaginator_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"UnitControl.OnControlPaginator_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlPaginatorListener#OnControlPaginator_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPaginator_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"UnitControl.OnControlPaginator_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlPaginatorListener#OnControlPaginator_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPaginator_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"UnitControl.OnControlPaginator_Rendered not implemented method");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlPaginatorListener#OnControlPaginator_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPaginator_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"UnitControl.OnControlPaginator_Write not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlWebOptionsListener#OnControlWebOptions_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlWebOptions_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"UnitControl.OnControlWebOptions_Changed not implemented method");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlWebOptionsListener#OnControlWebOptions_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlWebOptions_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"UnitControl.OnControlWebOptions_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlWebOptionsListener#OnControlWebOptions_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlWebOptions_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"UnitControl.OnControlWebOptions_Loaded not implemented method");	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlWebOptionsListener#OnControlWebOptions_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlWebOptions_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"UnitControl.OnControlWebOptions_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlWebOptionsListener#OnControlWebOptions_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlWebOptions_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"UnitControl.OnControlWebOptions_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlWebOptionsListener#OnControlWebOptions_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlWebOptions_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"UnitControl.OnControlWebOptions_Write not implemented method");	
	}
	/**
	 * Removes {@link Action} event {@link IDataTypeControlListener}
	 * @param listener
	 */
	public final synchronized void removeActionListener(IDataTypeControlListener listener)
	{
		this.actionListeners.remove(listener);
	}
	/**
	 * Adds {@link Action} event {@link IControlActionListener}.
	 * @param listener {@link IControlActionListener} the listener
	 */
	public final synchronized void removePaginatorListener(IControlPaginatorListener listener)
	{
		this.paginatorListeners.remove(listener);
	}
	/**
	 * Removes {@link WebOptions} event {@link IControlWebOptionsListener}.
	 * @param listener {@link IControlWebOptionsListener} the listener
	 */
	public final synchronized void removeWebOptionsListener(IControlWebOptionsListener listener)
	{
		this.webOptionsListeners.remove(listener);
	}

	
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#setMessage(org.httprobot.common.DataType)
	 */
	@Override
	public void controlMessage(TMessage message) 
	{
		super.controlMessage(message);
	}
	/**
	 * Initializes {@link Unit} message control.
	 * @param parent the parent
	 * @param message the message
	 */
	private final void initUnitControl(TListener parent, TMessage message)
	{
		this.data = new HashMap<UnitData, Object>();
				
		if(message != null)
		{
			ControlEventArgs e = new ControlEventArgs(this, ControlEventType.INIT, message);
			
			if(message instanceof Action)
			{
				addControlActionListener(this);

				//Check if parent is null and match with parent listener's type.
				if(parent != null ?
						parent instanceof IControlActionListener : false) {

					//Parent is properly listening initialization event
					addControlActionListener(IControlActionListener.class.cast(parent));			
				}
				//Fire event.
				ControlActionEvent(e);
			}
			else if(message instanceof Paginator)
			{
				addControlPaginatorListener(this);

				//Check if parent is null and match with parent listener's type.
				if(parent != null ?
						parent instanceof IControlPaginatorListener : false) {

					//Parent is properly listening initialization event
					addControlPaginatorListener(IControlPaginatorListener.class.cast(parent));			
				}
				//Fire event.
				ControlPaginatorEvent(e);
			}
			else if(message instanceof WebOptions)
			{
				addControlWebOptionsListener(this);

				//Check if parent is null and match with parent listener's type.
				if(parent != null ?
						parent instanceof IControlWebOptionsListener : false) {

					//Parent is properly listening initialization event
					addControlWebOptionsListener(IControlWebOptionsListener.class.cast(parent));			
				}
				//Fire event.
				ControlWebOptionsEvent(e);
			}
		}
	}
	/**
	 * Fires {@link Field} event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlActionEvent(ControlEventArgs e) 
	{
		for (IControlActionListener listener : actionListeners) 
		{
			try
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlAction_Init(this, e);
						break;
					case READ:
						listener.OnControlAction_Read(this, e);
						break;
					case LOAD:
						listener.OnControlAction_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlAction_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlAction_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlAction_Write(this, e);
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
	 * Fires {@link Paginator} event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlPaginatorEvent(ControlEventArgs e) 
	{
		for (IControlPaginatorListener listener : paginatorListeners) 
		{
			try
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlPaginator_Init(this, e);
						break;
					case READ:
						listener.OnControlPaginator_Read(this, e);
						break;
					case LOAD:
						listener.OnControlPaginator_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlPaginator_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlPaginator_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlPaginator_Write(this, e);
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
	protected final void ControlWebOptionsEvent(ControlEventArgs e) 
	{
		for (IControlWebOptionsListener listener : webOptionsListeners) 
		{
			try
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlWebOptions_Init(this, e);
						break;
					case READ:
						listener.OnControlWebOptions_Read(this, e);
						break;
					case LOAD:
						listener.OnControlWebOptions_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlWebOptions_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlWebOptions_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlWebOptions_Write(this, e);
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
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<UnitData> iterator() 
	{
		return this.data.keySet().iterator();
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
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() 
	{
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
		return this.data.get(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Object put(UnitData key, Object value) 
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
				
			case ACTION:
				//Check input value is a Document message.
				if(value.equals(this.message)) 
				{
					RenderControl(this.message);
					ControlActionEvent(new ControlEventArgs(this, ControlEventType.RENDER, this.message));
				}
				break;
				
			case WEB_OPTIONS:
				//Check input value is a WebOptions message.
				if(value.equals(this.message)) 
				{
					RenderControl(this.message);
					ControlWebOptionsEvent(new ControlEventArgs(this, ControlEventType.RENDER, this.message));
				}
				break;
				
			case PAGINATOR:
				//Check input value is a Document message.
				if(value.equals(this.message)) 
				{
					RenderControl(this.message);
					ControlPaginatorEvent(new ControlEventArgs(this, ControlEventType.RENDER, this.message));
				}
				break;
				
			default:
				break;
		}
		
		//Update UnitControl data
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
	public void putAll(Map<? extends UnitData, ? extends Object> m) {
		
		for(UnitData attribute : m.keySet())
		{
			if(this.put(attribute, m.get(attribute)) == null)
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
	public Set<UnitData> keySet() 
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
	public Set<java.util.Map.Entry<UnitData, Object>> entrySet() 
	{
		return this.data.entrySet();
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
	 * @see org.httprobot.core.controls.Control#LoadControl(org.httprobot.common.RML)
	 */
	@Override
	public void LoadControl(RML message) {
		
		if (this.message == null)
			throw new NullPointerException("UnitControl.LoadControl: Control's message hasn't been initialized");
		
		//Insert loaded inherited data
		this.put(UnitData.UUID, message.getUuid());
		this.put(UnitData.INHERITED, message.getInherited());
		
		if(!Boolean.class.cast(this.get(UnitData.INHERITED)) ? 
				message.getRuntimeOptions() != null : false) {

			for(RuntimeOptions option : message.getRuntimeOptions())
			{
				this.put(UnitData.RUNTIME_OPTIONS, option);
			}
		}

		//Send event to parent.
		ControlLoadedEvent(new ControlEventArgs(this, ControlEventType.LOAD, message));

		if(this.message instanceof Action)
		{
			ControlActionEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		}
		else if(this.message instanceof WebOptions)
		{
			ControlWebOptionsEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		}
		else if(this.message instanceof Paginator)
		{
			ControlPaginatorEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#RenderControl()
	 */
	@Override
	public void RenderControl(RML message) 
	{
		super.RenderControl(message);
		
		if(message instanceof Action)
		{
			
		}
		else if(message instanceof WebOptions)
		{
			ControlWebOptionsEvent(new ControlEventArgs(this, ControlEventType.RENDER, message));
		}
		else if(message instanceof Paginator)
		{
			ControlPaginatorEvent(new ControlEventArgs(this, ControlEventType.RENDER, message));
		}
	}
}