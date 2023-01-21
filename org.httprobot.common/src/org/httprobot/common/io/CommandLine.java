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
import org.httprobot.common.interfaces.IListener;
import org.w3c.dom.events.Event;

/**
 * CommandLine
 * @author joan
 *
 */
@XmlTransient
public abstract class CommandLine implements IListener
{
	/**
	 * Runtime options
	 */
	EnumSet<RuntimeOptions> runtimeOptions;
	/**
	 * The destination path if this object must be stored
	 */
	String destinationPath;	
	/**
	 * The Unique ID
	 */
	UUID uuid;
	/**
	 * CLI Listeners
	 */
	private Vector<IListener> command_input_listeners;
	/**
	 * CLI Listeners
	 */
	private Vector<IListener> command_output_listeners;
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
	public UUID getUuid() 
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
	public void setDestinationPath(String destinationPath) 
	{
		this.destinationPath = destinationPath;
	}
	/**
	 * CommandLine default class constructor.
	 */
	public CommandLine()
	{
		this.command_input_listeners = new Vector<IListener>();
		this.command_output_listeners = new Vector<IListener>();
	}
	/**
	 * Adds {@link IListener} Command event listener.
	 * @param listener {@link IListener} the listener
	 */
	public final synchronized void addCommandInputListener(IListener listener)
	{
		command_input_listeners.add(listener);
	}
	/**
	 * Adds {@link IListener} Command event listener.
	 * @param listener {@link IListener} the listener
	 */
	public final synchronized void addCommandOutputListener(IListener listener)
	{
		command_output_listeners.add(listener);
	}
	/**
	 * Removes {@link IListener} Command event listener.
	 * @param listener {@link IListener} the listener
	 */
	public final synchronized void removeCommandInputListener(IListener listener)
	{
		command_input_listeners.remove(listener);
	}
	/**
	 * Removes {@link IListener} Command event listener.
	 * @param listener {@link IListener} the listener
	 */
	public final synchronized void removeCommandOutputLi(IListener listener)
	{
		command_output_listeners.remove(listener);
	}
	/**
	 * Fires Control Input Command event method to parent.
	 * @param e {@link CliEventArgs}  the arguments
	 */
	protected final void CliCommandInputEvent(CliEventArgs e) 
	{
		for (IListener listener : command_input_listeners) 
		{
			listener.OnCommandInput(this, e);
		}
	}
	/**
	 * Fires Control Input Command event method to parent.
	 * @param e {@link CliEventArgs}  the arguments
	 */
	protected final void CliCommandOutputEvent(CliEventArgs e) 
	{
		for (IListener listener : command_output_listeners) 
		{
			listener.OnCommandOutput(this, e);
		}
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getCliNamespace()
	 */
	public abstract CliNamespace getCliNamespace();
}
