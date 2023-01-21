package org.httprobot.core.controls;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.RML;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.definitions.Enums.ControlEventType;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.interfaces.IListener;
import org.httprobot.common.io.CLI;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.controls.interfaces.impl.IControlImpl;

/**
 * Message control class. Inherits {@link RML}.
 * <br>
 * Is {@link IControlImpl}.
 * <br>
 * @author Joan  
 */
@XmlTransient
public abstract class Control
	<TMessage extends RML, TControlListener extends IControlListener> 
		extends RML
		implements IControlImpl {
	
	/**
	 * 7259374887765950550L
	 */
	private static final long serialVersionUID = 7259374887765950550L;
	
	/**
	 * The current control performing.
	 */
	private int currentControl;
	/**
	 * The parent listener,
	 */
	private TControlListener parent;
	/**
	 * The children message controls.
	 */
	protected List<IControlImpl> childControls;
	/**
	 * The controlled message.
	 */
	protected TMessage message;
	/**
	 * Render status.
	 */
	private Boolean isRendered = false;
	
	/**
	 * Changed event listeners.
	 */
	private Vector<IControlImpl> changeEventListeners = null;	
	/**
	 * Initialized event listeners.
	 */
	private Vector<IControlImpl> initEventListeners = null;
	/**
	 * Loaded event listeners.
	 */
	private Vector<IControlImpl> loadEventListeners = null;
	/**
	 * Read event listeners.
	 */
	private Vector<IControlImpl> readEventListener = null;
	/**
	 * Rendered event listeners.
	 */
	private Vector<IControlImpl> renderEventListeners = null;
	/**
	 * Write event listeners.
	 */
	private Vector<IControlImpl> writeEventListeners = null;
	
	/**
	 * @return the controlled message.
	 */
	public TMessage getMessage()
	{
		return this.message;
	}
	/**
	 * Message control default class constructor.
	 */
	public Control()
	{
		super();
		
		//Initialize CLI members.
		this.setUuid(null);
		this.setInherited(false);		
		this.setRuntimeOptions(RuntimeOptions.EMPTY_DEBUG);
		
		//Initialize control.
		InitControl(null);
	}
	/**
	 * Message control class constructor.
	 * @param parent {@link IControlListener} the parent
	 */
	public Control(TControlListener parent, TMessage message)
	{
		super();
		
		//Set inherited data
		this.parent = parent;
		
		this.setUuid(message.getUuid());
		this.setInherited(message.getInherited());		
		this.setRuntimeOptions(message.getRuntimeOptions());
		
		//Initialize control
		InitControl(message);
	}
	/**
	 * Adds Input event listeners to parent if not null.
	 */
	public final void AddCommandLineListeners() 
	{
		this.addCommandOutputListener(this.message);
	}
	/**
	 * Adds {@link IControlImpl} listener to current control.
	 * @param listener {@link IControlListener} the listener
	 * @param event_type {@link RmlType} the event type to listen
	 */
	public final synchronized void addControlListener(IControlImpl listener, ControlEventType event_type)
	{
		switch (event_type) 
		{
			case READ:
				this.readEventListener.add(listener);
				break;
			case INIT:
				this.initEventListeners.add(listener);
				break;
			case LOAD:	
				this.loadEventListeners.add(listener);
				break;
			case CHANGE:
				this.changeEventListeners.add(listener);
				break;
			case RENDER:	
				this.renderEventListeners.add(listener);
				break;
			case WRITE:
				this.writeEventListeners.add(listener);
				break;
			default:
				break;
		}
	}
	/**
	 * @throws InconsistenMessageException
	 */
	public void breakInheritance() 
			throws InconsistenMessageException {
		
		if(this.getInherited())
		{
			this.setInherited(false);
			CliCommandOutputEvent(new CliEventArgs(this, Command.SET_UUID));
			ChangeControl(this.message);
		}		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.io.MarshalObject#getDestinationPath()
	 */
	@Override
	public final String getDestinationPath() 
	{
		if(super.getDestinationPath() != null)
		{
			return super.getDestinationPath();
		}
		else
		{
			return parent.getDestinationPath();
		}
	}
	/**
	 * @return the render state
	 */
	public final Boolean getIsRendered() 
	{
		return this.isRendered;
	}
	/**
	 * @return {@link IListener} the parent
	 */
	public final IListener getParent() 
	{
		return parent;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.io.MarshalObject#getCliOptions()
	 */
	@Override
	public EnumSet<RuntimeOptions> getRuntimeOptions() 
	{
		if(super.getRuntimeOptions() != null)
		{
			return super.getRuntimeOptions();
		}
		else
		{
			return parent.getRuntimeOptions();
		}
	}
	/**
	 * @return true if child managers list not null or empty.
	 */
	public boolean hasChildControls()
	{
		boolean isEmpty = (this.childControls == null || this.childControls.isEmpty());
		return !isEmpty;
	}
	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() 
	{
		return currentControl < this.childControls.size();
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public IControlImpl next() {
		
		if(! hasNext())
			throw new NoSuchElementException();
		return this.childControls.get(currentControl++);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.io.MarshalLayer#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e) 
			throws InconsistenMessageException {
		
		super.OnCommandInput(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.RML#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e) 
			throws InconsistenMessageException {
		
		super.OnCommandOutput(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.interfaces.IControlImpl#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Change(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.interfaces.IControlImpl#OnControl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Initialize(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.interfaces.IControlImpl#OnControl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Load(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.interfaces.IControlImpl#OnControl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.interfaces.IControlImpl#OnControl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Render(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.interfaces.IControlImpl#OnControl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException ;
	/* (non-Javadoc)
	 * @see org.httprobot.common.RML#OnObjectMarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public final void OnObjectMarshalled(Object sender, MarshallerEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		super.OnObjectMarshalled(sender, e);
		
		ControlWriteEvent(new ControlEventArgs(this, ControlEventType.WRITE, this.message));
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.RML#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public final void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		super.OnObjectUnmarshalled(sender, e);
		
		//If parent is set addCommandInputListener.
		if(this.parent != null)
		{
			addCommandInputListener(this.parent);
		}
		
		//Assign read data.
		RML rml = e.getRml();
		EnumSet<RuntimeOptions> options = rml.getRuntimeOptions();
		
		this.setUuid(e.getUUID());
		this.setInherited(rml.getInherited());		
		this.setRuntimeOptions(options);		
		
		if (!rml.getInherited())
		{
			if(rml.getRuntimeOptions() != null)
			{
				setRuntimeOptions(rml.getRuntimeOptions());	
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"Control.OnObjectUnmarshalled: CLI options null when Inherited = false");
			}
		}
		
		ControlReadEvent(new ControlEventArgs(this, ControlEventType.READ, rml));
	}
	/* (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		if(!hasNext())
			throw new NoSuchElementException();
		
		if(this.currentControl +1 < this.childControls.size())
		{
			System.arraycopy(this.childControls, this.currentControl + 1, this.childControls, this.currentControl , this.currentControl - 1);
		}
		this.currentControl--;
	}

	/**
	 * @param listener the listener to remove from listeners array
	 * @param event_type the event type
	 */
	public final synchronized void removeControlListener(IControlListener listener, ControlEventType event_type)
	{
		switch (event_type) 
		{
			case INIT:
				this.initEventListeners.remove(listener);
				break;
			case READ:
				this.readEventListener.remove(listener);
				break;			
			case LOAD:	
				this.loadEventListeners.remove(listener);
				break;
			case CHANGE:
				this.changeEventListeners.remove(listener);
				break;
			case RENDER:	
				this.renderEventListeners.remove(listener);
				break;
			case WRITE:
				this.writeEventListeners.remove(listener);
				break;
			default:
				break;
		}
	}
	/**
	 * Sets the render status.
	 * @param value the value
	 */
	public void render(Boolean value) 
	{
		this.isRendered = value;
	}
	/**
	 * Controls input message.
	 * @param message the message
	 */
	public void controlMessage(TMessage message)
	{
		if(this.message != null ?
				message != null : false) {
			
			addCommandOutputListener(this.message);
			LoadControl(message);
		}
		else if(message != null ?
				this.isRendered : false) {
			
			LoadControl(message);
			ChangeControl(message);
		}
		if (message == null) {
			
			this.message = null;
			this.remove();
		}
	}	
	/**
	 * Sets the control ready to be read again.
	 */
	public void reset()
	{
		this.currentControl = 0;
	}
	/**
	 * Changes the control.
	 * @param message the message
	 */
	public void ChangeControl(RML message) 
	{
		//set inherited data.
		this.message.setUuid(message.getUuid());
		this.message.setInherited(message.getInherited());
		this.message.setRuntimeOptions(message.getRuntimeOptions());
		
		ControlEventArgs e = new ControlEventArgs(this, ControlEventType.CHANGE, message);
		ControlChangedEvent(e);
	}
	/**
	 * Initializes RML control. Fires ControlInitEvent.
	 */
	public void InitControl(RML message) 
	{
		this.currentControl = 0;
		this.childControls = new ArrayList<IControlImpl>();
		
		this.readEventListener = new Vector<IControlImpl>();
		this.writeEventListeners = new Vector<IControlImpl>();
		this.initEventListeners = new Vector<IControlImpl>();
		this.loadEventListeners = new Vector<IControlImpl>();
		this.changeEventListeners = new Vector<IControlImpl>();
		this.renderEventListeners = new Vector<IControlImpl>();

		//Adds event listeners
		addControlListener(this, ControlEventType.INIT);
		addControlListener(this, ControlEventType.READ);
		addControlListener(this, ControlEventType.LOAD);
		addControlListener(this, ControlEventType.CHANGE);
		addControlListener(this, ControlEventType.RENDER);
		addControlListener(this, ControlEventType.WRITE);
		
		//Adds parent's inputs command listener from this.
		if(this.parent != null)
		{
			addCommandInputListener(this.parent);
		}
		
		//Initialize work flow
		ControlInitEvent(new ControlEventArgs(this, ControlEventType.INIT, message));
	}
	/**
	 * Loads {@link RML} control.
	 * {@link CLI} message's members will be settled.
	 */
	public void LoadControl(RML message) 
	{
		if (this.message == null)
			throw new NullPointerException("Control.LoadControl: Control's message hasn't been initialized");
		
		//set inherited data.
		this.message.setUuid(message.getUuid());
		this.message.setInherited(message.getInherited());
		
		if(message.getRuntimeOptions() != null)
		{
			this.message.setRuntimeOptions(message.getRuntimeOptions());	
		}
		
		//Send event to parent.
		ControlLoadedEvent(new ControlEventArgs(this, ControlEventType.LOAD, message));
	}
	/**
	 * Renders the control. Fires ControlRenderedEvent.
	 * @param message TODO
	 */
	public void RenderControl(RML message) 
	{
		if(message != null)
		{
			this.render(true);
			ControlRenderEvent(new ControlEventArgs(this, ControlEventType.RENDER, message));	
		}
	}
	/**
	 * Fires  {@link IControlListener} ControlChanged event. event method to listeners.
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws InconsistenMessageException 
	 */
	protected final void ControlChangedEvent(ControlEventArgs e) 
	{
		for (IControlImpl listener : changeEventListeners) 
		{
			try
			{
				listener.Change(this, e);
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
	 * Fires ControlInit event method to listeners.
	 * @param e {@link ControlEventArgs}  the arguments
	 */
	protected final void ControlInitEvent(ControlEventArgs e) 
	{
		for (IControlImpl listener : initEventListeners) 
		{
			try 
			{
				listener.Initialize(this, e);
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
	 * Fires {@link IControlListener} ControlLoaded event.
	 * @param e {@link ControlEventArgs}  the arguments
	 */
	protected final void ControlLoadedEvent(ControlEventArgs e) 
	{
		for (IControlImpl listener : loadEventListeners) 
		{
			try {
				listener.Load(this, e);
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
	 * Control read event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlReadEvent(ControlEventArgs e) 
	{
		for (IControlImpl listener : readEventListener) 
		{
			try 
			{
				listener.Read(this, e);
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
	 * Fires ControlInit event method to listeners.
	 * @param e {@link ControlEventArgs}  the arguments
	 */
	protected final void ControlRenderEvent(ControlEventArgs e)
	{
		for (IControlImpl listener : renderEventListeners) 
		{
			try {
				listener.Render(this, e);
			} catch (NotImplementedException e1) 
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
	 * Control write event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlWriteEvent(ControlEventArgs e) 
	{
		for (IControlImpl listener : writeEventListeners) 
		{
			try 
			{
				listener.Write(this, e);
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
