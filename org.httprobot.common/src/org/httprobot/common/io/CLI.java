/**
 * 
 */
package org.httprobot.common.io;

import java.util.EnumSet;
import java.util.UUID;
import java.util.Vector;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.httprobot.common.definitions.Enums.CliNamespace;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.interfaces.IListener;
import org.httprobot.common.io.adapters.RuntimeOptionsAdapter;
import org.w3c.dom.events.Event;

/**
 * Command line class. It's {@link IListener}.
 * <br>
 * @author joan
 *
 */
@XmlTransient
public abstract class CLI implements IListener
{
	/**
	 * 3285958963681886547L
	 */
	private static final long serialVersionUID = 3285958963681886547L;
	/**
	 * Runtime options
	 */
	private EnumSet<RuntimeOptions> runtimeOptions;
	/**
	 * The destination path if this object must be stored
	 */
	private String destinationPath;	
	/**
	 * The Unique ID
	 */
	private UUID uuid;
	/**
	 * CLI Listeners
	 */
	private Vector<IListener> commandInputListeners;
	/**
	 * CLI Listeners
	 */
	private Vector<IListener> commandOutputListeners;
	/* (non-Javadoc)
	 * @see org.w3c.dom.events.EventListener#handleEvent(org.w3c.dom.events.Event)
	 */
	@Override
	public void handleEvent(Event arg0) 
	{
	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getUuid()
	 */
	@Override
	public final UUID getUuid() 
	{
		return this.uuid;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#setUuid(java.util.UUID)
	 */
	@XmlAttribute
	public final void setUuid(UUID uuid)
	{
		if(uuid != null)
		{
			this.uuid = uuid;
		}
		else
		{
			this.uuid = UUID.randomUUID();
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getDebugOptions()
	 */
	@Override
	@XmlJavaTypeAdapter(value = RuntimeOptionsAdapter.class)
	public EnumSet<RuntimeOptions> getRuntimeOptions() 
	{
		return this.runtimeOptions;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#setDebugOptions(java.util.EnumSet)
	 */
	@Override
	public void setRuntimeOptions(EnumSet<RuntimeOptions> options) 
	{
		this.runtimeOptions = options;
	}
	/**
	 * @param option
	 */
	public void addRuntimeOption(RuntimeOptions option)
	{
		if(this.runtimeOptions == null)
		{
			this.runtimeOptions = RuntimeOptions.EMPTY_DEBUG;
		}
		this.runtimeOptions.add(option);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getDestinationPath()
	 */
	@Override
	public String getDestinationPath() 
	{
		return this.destinationPath;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#setDestinationPath(java.lang.String)
	 */
	@Override
	@XmlAttribute
	public void setDestinationPath(String destinationPath) 
	{
		this.destinationPath = destinationPath;
	}
	/**
	 * CommandLine default class constructor.
	 */
	public CLI()
	{
		this.commandInputListeners = new Vector<IListener>();
		this.commandOutputListeners = new Vector<IListener>();
	}
	/**
	 * Adds {@link IListener} Command event listener.
	 * @param listener {@link IListener} the listener
	 */
	public final synchronized void addCommandInputListener(IListener listener)
	{
		if(this.commandInputListeners == null)
		{
			this.commandInputListeners = new Vector<IListener>();
		}
		this.commandInputListeners.add(listener);
	}
	/**
	 * Adds {@link IListener} Command event listener.
	 * @param listener {@link IListener} the listener
	 */
	public final synchronized void addCommandOutputListener(IListener listener)
	{
		if(this.commandOutputListeners == null)
		{
			this.commandOutputListeners = new Vector<IListener>();
		}
		this.commandOutputListeners.add(listener);
	}
	/**
	 * Removes {@link IListener} Command event listener.
	 * @param listener {@link IListener} the listener
	 */
	public final synchronized void removeCommandInputListener(IListener listener)
	{
		commandInputListeners.remove(listener);
	}
	/**
	 * Removes {@link IListener} Command event listener.
	 * @param listener {@link IListener} the listener
	 */
	public final synchronized void removeCommandOutputLi(IListener listener)
	{
		commandOutputListeners.remove(listener);
	}
	/**
	 * Fires Control Input Command event method to parent.
	 * @param e {@link CliEventArgs}  the arguments
	 * @throws InconsistenMessageException 
	 */
	protected final void CliCommandInputEvent(CliEventArgs e) throws InconsistenMessageException 
	{
		for (IListener listener : commandInputListeners) 
		{
			listener.OnCommandInput(this, e);
		}
	}
	/**
	 * Fires Control Input Command event method to parent.
	 * @param e {@link CliEventArgs}  the arguments
	 * @throws InconsistenMessageException 
	 */
	protected final void CliCommandOutputEvent(CliEventArgs e) throws InconsistenMessageException 
	{
		for (IListener listener : commandOutputListeners) 
		{
			listener.OnCommandOutput(this, e);
		}
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getCliNamespace()
	 */
	public abstract CliNamespace getCliNamespace();
}
