package org.httprobot.core.rml.controls.datatypes;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.datatypes.DataView;
import org.httprobot.common.rml.datatypes.ServerInfo;
import org.httprobot.common.rml.datatypes.Steps;
import org.httprobot.common.tools.CommandLineInterface;
import org.httprobot.core.rml.controls.RmlDataTypeControl;

/**
 * @author Joan
 * ServerInfo Control Class. Inherits RmlDataTypeControl.
 */
@XmlRootElement
public class ServerInfoControl extends RmlDataTypeControl
{
	/**
	 * -7800283969359559523L
	 */
	private static final long serialVersionUID = -7800283969359559523L;
	private String last_update;
	private String server_name;
	private String server_url;
	private String start_url;
	
	ServerInfo server_info;
	StepsControl stepsControl;
	DataViewControl dataViewControl;
	/**
	 * ServerInfoControl default class constructor.
	 */
	public ServerInfoControl()
	{
		super();
	}
	/**
	 * ServerInfoControl class constructor.
	 * @param parent {@link IRmlListener}
	 * @param server_info {@link ServerInfo}
	 */
	public ServerInfoControl(IRmlListener parent, ServerInfo server_info) 
	{
		super(parent, server_info);
	}
	/**
	 * @return {@link String} last_update
	 */
	public String getLast_update() {
		return last_update;
	}
	public ServerInfo getServer_info()
	{
		return this.server_info;
	}
	/**
	 * @return {@link String} server_name<
	 */
	public String getServer_name() {
		return server_name;
	}
	/**
	 * @return {@link String} server_url
	 */
	public String getServer_url() {
		return server_url;
	}
	/**
	 * @return {@link String} start_url
	 */
	public String getStart_url() {
		return start_url;
	}

	/**
	 * @return {@link StepsControl} steps_control
	 */
	public StepsControl getSteps_control() {
		return this.stepsControl;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e) 
	{
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlInit(Object sender, RmlEventArgs e) throws InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			this.server_info = new ServerInfo();
			
			//Associate message with control.
			this.addCommandOutputListener(this.server_info);
			
			//Set initializing data
			this.setUuid(e.getMessage().getUuid());
			this.setInherited(e.getMessage().getInherited());
			this.setRuntimeOptions(e.getMessage().getRuntimeOptions());
			
			this.server_info.setUuid(getUuid());
			this.server_info.setInherited(getInherited());
			this.server_info.setRuntimeOptions(getRuntimeOptions());
			
			try
			{
				ServerInfo server_info = ServerInfo.class.cast(e.getMessage());			

				
				if(server_info.getDataView() != null)
				{
					this.dataViewControl = new DataViewControl(this, server_info.getDataView());
					
					//Associate control to parent
					this.addCommandOutputListener(this.dataViewControl);
					this.dataViewControl.addDataViewListener(this);
				}
				else
				{
					CommandLineInterface.ThrowInconsistentMessageException(this, "ServerInfoControl.OnControlInit: DataView RML message expected");
				}

				if(server_info.getSteps() != null)
				{
					//Instantiate steps control
					this.stepsControl = new StepsControl(this, server_info.getSteps());
					
					//Associate control to parent
					this.addCommandOutputListener(this.stepsControl);
					this.stepsControl.addStepsListener(this);
				}
				else
				{
					CommandLineInterface.ThrowInconsistentMessageException(this, "ServerInfoControl.OnControlInit: Steps RML message expected");		
				}
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "ServerInfoControl.OnControlInit: ServerInfo RML message expected");
			}
		}		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e) 
	{
		if(e.getMessage() != null)
		{
			ServerInfo serverInfo = ServerInfo.class.cast(e.getMessage());
			
			if(serverInfo != null)
			{
				//Set non-controlled data
				this.server_info.setLastUpdate(serverInfo.getLastUpdate());
				this.server_info.setServerName(serverInfo.getServerName());
				this.server_info.setServerUrl(serverInfo.getStartUrl());
				this.server_info.setStartUrl(serverInfo.getStartUrl());
				
				//Load controlled data
				if(serverInfo.getSteps() != null)
				{
					this.stepsControl.setMessage(serverInfo.getSteps());	
				}
				
				if(serverInfo.getDataView() != null)
				{
					this.dataViewControl.setMessage(serverInfo.getDataView());
				}
			}
		}
	}
	
	@Override
	public void OnControlRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlRendered(Object sender, RmlEventArgs e) 
	{

	}
	@Override
	public void OnControlWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnServerInfoChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnServerInfoChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnServerInfoInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnServerInfoInit(Object sender, RmlEventArgs e)
	{
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnServerInfoLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnServerInfoLoaded(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnServerInfoRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnServerInfoRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnServerInfoRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnServerInfoRendered(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException 
	{
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnServerInfoWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnServerInfoWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnStepsChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepsChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnStepsInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepsInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnStepsLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnStepsLoaded(Object sender, RmlEventArgs e) throws InconsistenMessageException
	{
		try
		{
			this.server_info.setSteps(Steps.class.cast(e.getMessage()));
			CliCommandInputEvent(new CliEventArgs(this, Command.STEPS_CONTROL, e.getMessage()));
		}
		catch(ClassCastException ex)
		{
			CommandLineInterface.ThrowInconsistentMessageException(this, "ServerInfoControl.OnStepsLoaded: Steps RML message expected");
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnStepsRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepsRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnStepsRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepsRendered(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnStepsWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepsWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnDataViewChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnDataViewChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnDataViewInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnDataViewInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnDataViewLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnDataViewLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			try
			{
				this.server_info.setDataView(DataView.class.cast(e.getMessage()));
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "ServerInfoControl.OnDataViewLoaded: ServerInfo RML message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnDataViewRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnDataViewRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnDataViewRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnDataViewRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnDataViewWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnDataViewWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	
}
