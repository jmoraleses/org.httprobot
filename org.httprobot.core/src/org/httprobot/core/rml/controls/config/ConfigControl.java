/**
 * 
 */
package org.httprobot.core.rml.controls.config;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.config.Config;
import org.httprobot.common.rml.datatypes.ServerInfo;
import org.httprobot.common.tools.CommandLineInterface;
import org.httprobot.core.rml.controls.RmlConfigControl;
import org.httprobot.core.rml.controls.datatypes.ServerInfoControl;

/**
 * ConfigControl class. Inherits {@link RmlConfigControl}.
 * @author Joan
 *
 */
@XmlRootElement
public class ConfigControl extends RmlConfigControl
{
	/**
	 * -1690636781639188421L
	 */
	private static final long serialVersionUID = -1690636781639188421L;
	private ArrayList<ServerInfoControl> server_info_control_list;
	private ArrayList<ServerInfo> server_info_list;
	private int serverInfoCount;
	private Config config;	
	/**
	 * ConfigControl default constructor
	 */
	public ConfigControl()
	{
		super();
	}
	/**
	 * ConfigControl constructor with loaded data
	 * @param parent {@link IRmlListener} the parent
	 * @param config {@link Config} the config
	 */
	public ConfigControl(IRmlListener parent, Config config) 
	{
		super(parent, config);
	}
	/**
	 * @return the servers_info
	 */
	public ArrayList<ServerInfoControl> getServerInfoControl() 
	{
		return this.server_info_control_list;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlListener#OnControlCommand(java.lang.Object, org.httprobot.common.events.CommandEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e)
	{
		super.OnCommandInput(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlControl#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e)
	{
		super.OnCommandOutput(sender, e);
		// TODO Child's commands
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlConfigControl#OnControlChanged(java.lang.Object, org.httprobot.core.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlConfigControl#OnControlInit(java.lang.Object, org.httprobot.core.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlInit(Object sender, RmlEventArgs e) throws InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			//Initialize using data
			this.server_info_control_list = new ArrayList<ServerInfoControl>();
			this.server_info_list = new ArrayList<ServerInfo>();
			this.config = new Config();
			
			//Associate message with control.
			this.addCommandOutputListener(this.config);
			
			//Set initializing data
			this.setUuid(e.getMessage().getUuid());
			this.setInherited(e.getMessage().getInherited());
			this.setRuntimeOptions(e.getMessage().getRuntimeOptions());
			
			this.config.setUuid(getUuid());
			this.config.setRuntimeOptions(getRuntimeOptions());
			this.config.setInherited(getInherited());
			
			Config config = Config.class.cast(e.getMessage());
			
			if(config != null)
			{
				if(config.getServerInfo() != null)
				{	
					//Sets the count of servers for post back
					this.serverInfoCount = config.getServerInfo().size();
					
					for(ServerInfo serverInfo : config.getServerInfo())
					{
						//This instance listens for it's CliCommandInput
						ServerInfoControl serverInfoControl = new ServerInfoControl(this, serverInfo);
						
						//Associate child controls to parent.
						serverInfoControl.addServerInfoListener(this);
						this.addCommandOutputListener(serverInfoControl);
						
						//Add control to array
						this.server_info_control_list.add(serverInfoControl);
					}
				}
				else
				{
					CommandLineInterface.ThrowInconsistentMessageException(this, "\nConfigControl.OnControlInit: ServerInfo RML message expected");
				}
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlConfigControl#OnControlLoaded(java.lang.Object, org.httprobot.core.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e) throws InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			Config config = Config.class.cast(e.getMessage());
			
			if(config != null)
			{
				if(config.getServerInfo() != null)
				{	
					//Sets the count of servers for post back
					this.serverInfoCount = config.getServerInfo().size();
					
					for(ServerInfo serverInfo : config.getServerInfo())
					{
						for(ServerInfoControl serverInfoControl : this.server_info_control_list)
						{
							if(serverInfo.getUuid() == serverInfoControl.getUuid())
							{
								//Load controlled data for each corresponding message
								serverInfoControl.setMessage(serverInfo);
								break;
							}
						}
					}
				}
				else
				{
					CommandLineInterface.ThrowInconsistentMessageException(this, "\nConfigControl.OnControlLoaded: ServerInfo RML message expected");
				}
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlConfigControl#OnControlRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		this.setMessage(e.getMessage());
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlConfigControl#OnControlRendered(java.lang.Object, org.httprobot.core.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlRendered(Object sender, RmlEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlConfigControl#OnControlWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlConfigControl#OnServerInfoChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnServerInfoChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}

	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlConfigControl#OnServerInfoLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnServerInfoLoaded(Object sender, RmlEventArgs e)
	{
		try
		{
			this.server_info_list.add(ServerInfo.class.cast(e.getMessage()));
			
			//If received ServerInfo control is the last one		
			if(this.server_info_list.size() == this.serverInfoCount)
			{
				this.config.setServerInfo(this.server_info_list);
			}
			
			CliCommandInputEvent(new CliEventArgs(this, Command.SERVER_INFO_CONTROL, e.getMessage()));
		}
		catch(ClassCastException ex1)
		{
			ex1.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlConfigControl#OnServerInfoRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnServerInfoRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlConfigControl#OnServerInfoRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnServerInfoRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlConfigControl#OnServerInfoWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnServerInfoWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}

}