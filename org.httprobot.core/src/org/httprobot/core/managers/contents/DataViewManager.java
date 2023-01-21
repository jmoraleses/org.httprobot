/**
 * 
 */
package org.httprobot.core.managers.contents;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.DataView;
import org.httprobot.common.definitions.Enums.CliNamespace;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.core.controls.contents.DataViewControl;
import org.httprobot.core.controls.contents.interfaces.IControlDataViewListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.managers.ContentManager;
import org.httprobot.core.managers.Manager;
import org.httprobot.core.managers.contents.interfaces.IManagerDataViewListener;
import org.httprobot.core.managers.datatypes.interfaces.IManagerDataSourceListener;

/**
 * {@link DataView} RML message manager. Inherits {@link Manager}.
 * @author joan
 *
 */
@XmlRootElement
public class DataViewManager 
		extends ContentManager<DataView, DataViewControl, IManagerDataViewListener> 
		implements IControlDataViewListener, IManagerDataSourceListener {

	/**
	 * 7428759831517796917L
	 */
	private static final long serialVersionUID = 7428759831517796917L;

	private static CliNamespace cliNamespace = CliNamespace.DATA;
	
	DataView dataView;
	DataViewControl dataViewControl;
	

	/**
	 * DataView manager default constructor
	 */
	public DataViewManager()
	{
		super();
	}	
	/**
	 * @param parent
	 */
	public DataViewManager(IManagerDataViewListener parent, DataView dataView)
	{
		super(parent, dataView);
		
		//Initialize using data
		this.dataView = dataView;
		this.dataViewControl = new DataViewControl(this, dataView);
		
		//Set inherited data
		this.setUuid(dataView.getUuid());
		this.setRuntimeOptions(parent.getRuntimeOptions());
		this.setDestinationPath(parent.getDestinationPath());
		
		//Associate control to listener
		this.dataViewControl.addControlDataViewListener(this);
		this.addCommandOutputListener(this.dataViewControl);
		this.addCommandOutputListener(this.dataView);		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e) throws InconsistenMessageException 
	{
		switch (e.getCommand()) 
		{
			case CONTROL_DATA_VIEW:
	
				break;
			case CONTROL_FIELD_REF:
	
				break;
	
			default:
				break;
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e) throws InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.Manager#start()
	 */
	@Override
	public void start() 
	{
		//Set controlled data
		this.dataViewControl.controlMessage(this.dataView);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.Manager#stop()
	 */
	@Override
	public void stop() 
	{
	
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
	 * @see org.httprobot.common.interfaces.IRmlListener#getInherited()
	 */
	@Override
	public Boolean getInherited() 
	{
		return this.dataView.getInherited();
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
	 * @see org.httprobot.core.rml.managers.interfaces.IManagerServerInfoListener#OnManagerServerInfo_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDataSource_Started(Object sender, ManagerEventArgs e)
			throws ManagerException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.interfaces.IManagerServerInfoListener#OnManagerServerInfo_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDataSource_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.interfaces.IManagerServerInfoListener#OnManagerServerInfo_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDataSource_Error(Object sender, ManagerEventArgs e)
			throws ManagerException {
		
	}
}