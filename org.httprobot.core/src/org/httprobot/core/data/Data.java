package org.httprobot.core.data;

import java.util.EnumSet;
import java.util.Vector;

import org.httprobot.common.definitions.Enums.CliNamespace;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.definitions.Enums.ProgramDataEventType;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.io.CommandLine;
import org.httprobot.core.data.view.ViewTable;
import org.httprobot.core.events.ProgramDataEventArgs;
import org.httprobot.core.events.RequesterEventArgs;
import org.httprobot.core.interfaces.IDataListener;
import org.httprobot.core.interfaces.IRequesterListener;

/**
 * Program data class. Contains all information used by the application,
 * @author Joan
 */
public class Data extends CommandLine implements IRequesterListener
{
	private static final CliNamespace cliNamespace = CliNamespace.DATA;
	
	private Vector<ViewTable> viewTablesList;
	private Vector<IDataListener> dataListeners = null;
	/**
	 * Adds TryParse event listener
	 * @param listener {@link IDataListener}
	 */
	public final synchronized void addProgramDataChangedListener(IDataListener listener)
	{
		dataListeners.add(listener);
	}
	/**
	 * Adds TryParse event listener
	 * @param listener {@link IDataListener}
	 */
	public final synchronized void removeProgramDataChangedListener(IDataListener listener)
	{
		dataListeners.remove(listener);
	}
	/**
	 * Fires event method to parent.
	 * @param message RML Object
	 */
	protected final void ProgramDataChangedEvent(ProgramDataEventArgs e) 
	{
		for (IDataListener listener : dataListeners) 
		{
			listener.OnProgramDataChanged(this, e);
		}
	}
	/**
	 * Gets the collected data by the application.
	 * @return {@link Vector} of {@link ViewTable}
	 */
	public Vector<ViewTable> getCaptures_tables() 
	{
		return viewTablesList;
	}
	/**
	 * Sets the captures tables.
	 * @param captures_tables {@link Vector} of {@link ViewTable}
	 */
	public void setCaptures_tables(Vector<ViewTable> captures_tables) 
	{
		this.viewTablesList = captures_tables;
		ProgramDataChangedEvent(new ProgramDataEventArgs(this, ProgramDataEventType.CAPTURE_TABLE));
	}
	/**
	 * ProgramData class constructor.
	 * @param parent {@link IDataListener}
	 */
	public Data(IDataListener parent)
	{
		this.viewTablesList = new Vector<ViewTable>();
		this.dataListeners = new Vector<IDataListener>();
		this.setDestinationPath(parent.getDestinationPath());		
		addProgramDataChangedListener(parent);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e) 
	{
		
	}
	/**
	 * Set application's runtime options
	 * @param runtimeOptions
	 */
	@Override	 
	public void setRuntimeOptions(EnumSet<RuntimeOptions> runtimeOptions) 
	{
		super.setRuntimeOptions(runtimeOptions);
		ProgramDataChangedEvent(new ProgramDataEventArgs(runtimeOptions, ProgramDataEventType.DEBUG_OPTIONS));
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.io.CommandLine#getCliNamespace()
	 */
	@Override
	public CliNamespace getCliNamespace() 
	{
		return cliNamespace;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRequesterListener#OnDataRowCaptured(java.lang.Object, org.httprobot.core.events.RequesterEventArgs)
	 */
	@Override
	public void OnDataRowCaptured(Object sender, RequesterEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e) 
	{
		
	}
}
