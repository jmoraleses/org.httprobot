package org.httprobot.core.data;

import java.util.EnumSet;
import java.util.Vector;

import org.httprobot.common.RML;
import org.httprobot.common.definitions.Enums.CliNamespace;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.definitions.Enums.ProgramDataEventType;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.io.CLI;
import org.httprobot.core.controls.contents.interfaces.IControlDataViewListener;
import org.httprobot.core.data.view.ViewTable;
import org.httprobot.core.events.ProgramDataEventArgs;
import org.httprobot.core.events.RequesterEventArgs;
import org.httprobot.core.interfaces.IPrecursorListener;
import org.httprobot.core.managers.contents.interfaces.IManagerDataViewListener;

/**
 * Program data class. Contains all information used by the application,
 * @author Joan
 */
public class Data extends CLI implements IPrecursorListener, IControlDataViewListener
{
	/**
	 * 7146395264397929068L
	 */
	private static final long serialVersionUID = 7146395264397929068L;

	private static final CliNamespace cliNamespace = CliNamespace.DATA;
	
	private Vector<ViewTable> viewTablesList;
	private Vector<IManagerDataViewListener> dataViewListeners = null;
	/**
	 * Adds TryParse event listener
	 * @param listener {@link IManagerDataViewListener}
	 */
	public final synchronized void addProgramDataChangedListener(IManagerDataViewListener listener)
	{
		dataViewListeners.add(listener);
	}
	/**
	 * Adds TryParse event listener
	 * @param listener {@link IManagerDataViewListener}
	 */
	public final synchronized void removeProgramDataChangedListener(IManagerDataViewListener listener)
	{
		dataViewListeners.remove(listener);
	}
	/**
	 * Fires event method to parent.
	 * @param message RML Object
	 */
	protected final void DataChangedEvent(ProgramDataEventArgs e) 
	{

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
		DataChangedEvent(new ProgramDataEventArgs(this, ProgramDataEventType.CAPTURE_TABLE));
	}
	/**
	 * ProgramData class constructor.
	 * @param parent {@link IManagerDataViewListener}
	 */
	public Data(IManagerDataViewListener parent)
	{
		this.viewTablesList = new Vector<ViewTable>();
		this.dataViewListeners = new Vector<IManagerDataViewListener>();
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
		DataChangedEvent(new ProgramDataEventArgs(runtimeOptions, ProgramDataEventType.DEBUG_OPTIONS));
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
	public void OnDocumentCompleted(Object sender, RequesterEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IRmlListener#getInherited()
	 */
	@Override
	public Boolean getInherited() 
	{
		return null;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IDataViewListener#OnDataViewInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlDataView_Init(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IDataViewListener#OnDataViewRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlDataView_Read(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IDataViewListener#OnDataViewLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlDataView_Loaded(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IDataViewListener#OnDataViewChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlDataView_Changed(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IDataViewListener#OnDataViewRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlDataView_Rendered(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IDataViewListener#OnDataViewWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlDataView_Write(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IControlListener#getMessage()
	 */
	@Override
	public RML getMessage() {
		// TODO Auto-generated method stub
		return null;
	}


}
