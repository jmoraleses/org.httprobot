/**
 * 
 */
package org.httprobot.core.controls;

import java.util.Vector;

import org.httprobot.common.Placeholder;
import org.httprobot.common.RML;
import org.httprobot.common.definitions.Enums.ControlEventType;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.placeholders.HtmlUnit;
import org.httprobot.common.placeholders.HttpAddress;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.controls.interfaces.listeners.IPlaceholderControlListener;
import org.httprobot.core.controls.placeholders.interfaces.IControlHtmlUnitListener;
import org.httprobot.core.controls.placeholders.interfaces.IControlHttpAddressListener;
import org.httprobot.core.events.RmlPlaceholderEventArgs;


/**
 * {@link Placeholder} message control class. Inherits {@link Control}.
 * <br>
 * It's {@link IPlaceholderControlListener}.
 * <br>
 * @author Joan
 *
 */
public abstract class PlaceholderControl
	<TMessage extends Placeholder, IListener extends IControlListener> 
		extends Control<TMessage, IListener> 
		implements IPlaceholderControlListener {
	
	/**
	 * 7430074633996268936L
	 */
	private static final long serialVersionUID = 7430074633996268936L;
	
	/**
	 * {@link HtmlUnit} message control listeners.
	 */
	private Vector<IControlHtmlUnitListener> htmlUnitListeners;
	/**
	 * {@link HttpAddress} message control listeners.
	 */
	private Vector<IControlHttpAddressListener> httpAddressListeners;

	/**
	 * {@link Placeholder} message control default class constructor.
	 */
	public PlaceholderControl()
	{
		super();
		
		//Initialize place holder control.
		initPlaceholderControl(null, null);
	}	
	/**
	 * {@link Placeholder} message control class constructor.
	 * @param parent
	 */
	public PlaceholderControl(IListener parent, TMessage message) 
	{
		super(parent, message);

		//Initialize place holder control.
		initPlaceholderControl(parent, message);
	}
	/**
	 * Removes {@link HtmlUnit} message control listener.
	 * @param listener {@link IControlHtmlUnitListener} the listener
	 */
	public final synchronized void addControlHtmlUnitListener(IControlHtmlUnitListener listener)
	{
		if(this.htmlUnitListeners == null)
		{
			this.htmlUnitListeners = new Vector<IControlHtmlUnitListener>();
		}
		this.htmlUnitListeners.add(listener);
	}
	/**
	 * Removes {@link HttpAddress} message control listener.
	 * @param listener {@link IControlHttpAddressListener} the listener
	 */
	public final synchronized void addControlHttpAddressListener(IControlHttpAddressListener listener)
	{
		if(this.httpAddressListeners == null)
		{
			this.httpAddressListeners = new Vector<IControlHttpAddressListener>();
		}
		this.httpAddressListeners.add(listener);
	}
	/**
	 * Initializes the place holder message control
	 * @param parent
	 * @param message
	 */
	private final void initPlaceholderControl(IListener parent, TMessage message)
	{
		ControlEventArgs e = new ControlEventArgs(this, ControlEventType.INIT, message);
		
		if(message instanceof HttpAddress)
		{
			addControlHttpAddressListener(this);

			//Check if parent is null and match with parent listener's type.
			if(parent != null ?
					parent instanceof IControlHttpAddressListener : false) {

				//Parent is properly listening initialization event
				addControlHttpAddressListener(IControlHttpAddressListener.class.cast(parent));	
			}
			ControlHttpAddressEvent(e);
		}
		if(message instanceof HtmlUnit)
		{
			addControlHtmlUnitListener(this);

			//Check if parent is null and match with parent listener's type.
			if(parent != null ?
					parent instanceof IControlHtmlUnitListener : false) {

				//Parent is properly listening initialization event
				addControlHtmlUnitListener(IControlHtmlUnitListener.class.cast(parent));
			}
			ControlHtmlBodyEvent(e);
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
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHtmlUnitListener#OnControlHtmlUnit_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"PlaceholderControl.OnControlHtmlBody_Changed not implemented method");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHtmlUnitListener#OnControlHtmlUnit_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"PlaceholderControl.OnControlHtmlBody_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHtmlUnitListener#OnControlHtmlUnit_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"PlaceholderControl.OnControlHtmlBody_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHtmlUnitListener#OnControlHtmlUnit_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"PlaceholderControl.OnControlHtmlBody_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHtmlUnitListener#OnControlHtmlUnit_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"PlaceholderControl.OnControlHtmlBody_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHtmlUnitListener#OnControlHtmlUnit_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"PlaceholderControl.OnControlHtmlBody_Write not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHttpAddressListener#OnControlHttpAddress_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"PlaceholderControl.OnControlHttpRequest_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHttpAddressListener#OnControlHttpAddress_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"PlaceholderControl.OnControlHttpRequest_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHttpAddressListener#OnControlHttpAddress_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"PlaceholderControl.OnControlHttpRequest_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHttpAddressListener#OnControlHttpAddress_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"PlaceholderControl.OnControlHttpRequest_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHttpAddressListener#OnControlHttpAddress_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {

		CliTools.ThrowNotImplementedException(this, 
				"PlaceholderControl.OnControlHttpRequest_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHttpAddressListener#OnControlHttpAddress_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"PlaceholderControl.OnControlHttpRequest_Write not implemented method");
	}
	/**
	 * Adds {@link HtmlUnit} render listener.
	 * @param listener {@link IPlaceholderControlListener} the listener
	 */
	public final synchronized void removeControlHtmlBodyListener(IPlaceholderControlListener listener)
	{
		this.htmlUnitListeners.remove(listener);
	}
	/**
	 * Removes {@link HttpAddress} render listener.
	 * @param listener {@link IPlaceholderControlListener} the listener
	 */
	public final synchronized void removeControlHttpAddressListener(IPlaceholderControlListener listener)
	{
		this.httpAddressListeners.remove(listener);
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
	 * @see org.httprobot.core.controls.Control#setMessage(org.httprobot.common.RML)
	 */
	@Override
	public void controlMessage(TMessage message) 
	{
		super.controlMessage(message);
	}
	
	/**
	 * Fires {@link PlaceholderControl} event.
	 * @param e {@link RmlPlaceholderEventArgs} the arguments
	 */
	protected final void ControlHtmlBodyEvent(ControlEventArgs e) 
	{
		for (IControlHtmlUnitListener listener : htmlUnitListeners) 
		{
			try 
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlHtmlUnit_Init(this, e);
						break;
					case READ:
						listener.OnControlHtmlUnit_Read(this, e);
						break;
					case LOAD:
						listener.OnControlHtmlUnit_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlHtmlUnit_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlHtmlUnit_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlHtmlUnit_Write(this, e);
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
	 * Fires {@link PlaceholderControl} event.
	 * @param e {@link RmlPlaceholderEventArgs} the arguments
	 */
	protected final void ControlHttpAddressEvent(ControlEventArgs e) 
	{
		for (IControlHttpAddressListener listener : httpAddressListeners) 
		{
			try
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlHttpAddress_Init(this, e);
						break;
					case READ:
						listener.OnControlHttpAddress_Read(this, e);
						break;
					case LOAD:
						listener.OnControlHttpAddress_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlHttpAddress_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlHttpAddress_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlHttpAddress_Write(this, e);
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
	 * @see org.httprobot.core.controls.Control#ChangeControl(org.httprobot.common.RML)
	 */
	@Override
	public void ChangeControl(RML message) {
		// TODO Auto-generated method stub
		super.ChangeControl(message);
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
	 * @see org.httprobot.core.controls.Control#LoadControl(org.httprobot.common.RML)
	 */
	@Override
	public void LoadControl(RML message) 
	{
		super.LoadControl(message);
		
		if(message instanceof HtmlUnit)
		{
			ControlHtmlBodyEvent(new ControlEventArgs(this, ControlEventType.LOAD, message));
		}
		else if(message instanceof HttpAddress)
		{
			ControlHttpAddressEvent(new ControlEventArgs(this, ControlEventType.LOAD, message));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#RenderControl()
	 */
	@Override
	public void RenderControl(RML message) 
	{
		super.RenderControl(message);
	}
}