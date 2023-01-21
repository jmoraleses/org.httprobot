package org.httprobot.core.data.view;

import java.util.EnumSet;
import java.util.UUID;

import org.httprobot.common.definitions.Enums.CliNamespace;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.interfaces.IListener;
import org.httprobot.core.events.ProgramDataEventArgs;
import org.httprobot.core.interfaces.IDataListener;
import org.w3c.dom.events.Event;

/**
 * {@link ViewTable}'s elements
 * @author Joan
 *
 */
public abstract class ViewElement implements IDataListener
{
	private EnumSet<RuntimeOptions> debugOptions;
	private static final CliNamespace cliNamespace = CliNamespace.DATA;
	private String destinationPath;	
	/**
	 * ViewElement default class constructor.
	 * @param listener
	 */
	public ViewElement(IListener listener) 
	{
		this.debugOptions = listener.getRuntimeOptions();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getUuid()
	 */
	@Override
	public UUID getUuid() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#setUuid(java.util.UUID)
	 */
	@Override
	public void setUuid(UUID uuid) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getDebugNamespace()
	 */
	@Override
	public CliNamespace getCliNamespace() 
	{
		return cliNamespace;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getDebugOptions()
	 */
	@Override
	public EnumSet<RuntimeOptions> getRuntimeOptions() 
	{
		return this.debugOptions;
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
	 * @see org.w3c.dom.events.EventListener#handleEvent(org.w3c.dom.events.Event)
	 */
	@Override
	public void handleEvent(Event arg0) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnControlCommand(java.lang.Object, org.httprobot.common.events.CommandEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e)
	{
		
	};
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandRead(java.lang.Object, org.httprobot.common.events.CommandEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IDataListener#OnProgramDataChanged(java.lang.Object, org.httprobot.core.events.ProgramDataEventArgs)
	 */
	@Override
	public void OnProgramDataChanged(Object sender, ProgramDataEventArgs e)
	{

	}	
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#setCliOptions(java.util.EnumSet)
	 */
	@Override
	public void setRuntimeOptions(EnumSet<RuntimeOptions> options) 
	{
		this.debugOptions = options;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#setDestinationPath(java.lang.String)
	 */
	@Override
	public void setDestinationPath(String destinationPath) 
	{
		this.destinationPath = destinationPath;
	}
}
