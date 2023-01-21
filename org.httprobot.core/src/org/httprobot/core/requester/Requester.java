package org.httprobot.core.requester;

import java.util.Vector;

import org.httprobot.common.definitions.Enums.CliNamespace;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.io.CommandLine;
import org.httprobot.common.rml.config.Config;
import org.httprobot.common.rml.datatypes.ServerInfo;
import org.httprobot.common.tools.CommandLineInterface;
import org.httprobot.core.events.InetEventArgs;
import org.httprobot.core.events.ProgramDataEventArgs;
import org.httprobot.core.interfaces.IDataListener;
import org.httprobot.core.interfaces.IInetListener;
import org.httprobot.core.interfaces.IRequesterListener;
import org.httprobot.core.interfaces.IWebLoaderListener;
import org.httprobot.core.managers.InetManager;
import org.httprobot.core.rml.controls.config.ConfigControl;
import org.httprobot.core.rml.controls.config.interfaces.IConfigListener;

/**
 * Web requester class. Is {@link IWebLoaderListener}. 
 * @author Joan 
 */
public class Requester extends CommandLine 
	implements IConfigListener, IInetListener, IDataListener
{
	private static CliNamespace cli_namespace = CliNamespace.REQUESTER;

	Vector<Config> configurationList;	
	Vector<ConfigControl> configurationControlList;
	Vector<InetManager> internetManagerList;
	Vector<IRequesterListener> requesterListeners;
	
	private String destinationPath;
	private Boolean isInherited;
	/**
	 * WebRequester default class constructor. Full tabs.
	 */
	public Requester(IRequesterListener parent)
	{
		this.setUuid(null);
		setDestinationPath(parent.getDestinationPath());
		setRuntimeOptions(parent.getRuntimeOptions());
		
		this.requesterListeners = new Vector<IRequesterListener>();
		this.configurationControlList = new Vector<ConfigControl>();
		this.configurationList = new Vector<Config>();
		this.internetManagerList = new Vector<InetManager>();
//		this.setUuid(configuration.getUuid());
//		setDestinationPath(configuration.getDestinationPath());
//		setRuntimeOptions(configuration.getCliOptions());
//		
////		this.configuration = configuration;
//		
//		this.configurationControl = new ConfigControl(this, configuration);
//		this.configurationControl.addConfigListener(this);
//		this.internetManager = new InetManager(this);
//		
		
		/*
		 * 1. Initialize Data objects
		 * 2. 
		 * */
	}
	
	public void addConfiguration(Config configuration)
	{
		//Add configuration sheet to vector.
		this.configurationList.add(configuration);
		
		//Associate message to control.
		this.addCommandOutputListener(configuration);
		
		//Associate child control to parent.
		ConfigControl configControl = new ConfigControl(this, configuration);
		configControl.addConfigListener(this);
		this.addCommandOutputListener(configControl);		
		
		//Add configuration sheet control to vector.
		this.configurationControlList.add(configControl);
		
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getCliNamespace()
	 */
	@Override
	public CliNamespace getCliNamespace() 
	{
		return Requester.cli_namespace;
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
	 * @see org.httprobot.common.interfaces.IRmlListener#getInherited()
	 */
	@Override
	public final Boolean getInherited() 
	{
		return this.isInherited;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e) 
	{
		switch (e.getCmd())
		{
			case CONFIG_CONTROL:
				
				break;
			case SERVER_INFO_CONTROL:
								
				break;
			default:
				System.out.println("\nCommand " + e.getCmd().toString());
				break;
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e)
	{
				
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.config.interfaces.IConfigControl#OnConfigChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnConfigChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.config.interfaces.IConfigControl#OnConfigInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnConfigInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.config.interfaces.IConfigControl#OnConfigLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnConfigLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		if(e.getMessage() != null)
		{
			try
			{
				Config config = Config.class.cast(e.getMessage());
				
//				For each ServerInfo received instantiate InetManager and store it.
				for(ServerInfo serverInfo : config.getServerInfo())
				{
					InetManager inetMgr = new InetManager(this, serverInfo);
					this.internetManagerList.add(inetMgr);
					inetMgr.start();
				}
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "Requester.OnConfigLoaded: Config RML message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.config.interfaces.IConfigControl#OnConfigRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnConfigRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.config.interfaces.IConfigControl#OnConfigRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnConfigRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.config.interfaces.IConfigControl#OnConfigWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnConfigWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IRmlListener#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IRmlListener#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IRmlListener#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IRmlListener#OnControlRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IRmlListener#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IRmlListener#OnControlWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
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
	 * @see org.httprobot.common.interfaces.IListener#setDestinationPath(java.lang.String)
	 */
	@Override
	public void setDestinationPath(String destinationPath) 
	{
		this.destinationPath = destinationPath;
	}
	/**
	 * Starts sequence process
	 */
	public void start()
	{
//		for(Config configuration : this.configurationList)
//		{
//			this.configurationControlList.setMessage(configuration);	
//		}
		
		for(ConfigControl configControl : this.configurationControlList)
		{
			for(Config config : this.configurationList)
			{
				if(configControl.getUuid() == config.getUuid())
				{
					configControl.setMessage(config);
				}
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IInetListener#OnServerInfoStarted(java.lang.Object, org.httprobot.core.events.InetEventArgs)
	 */
	@Override
	public void OnServerInfoStarted(Object sender, InetEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IInetListener#OnServerInfoStopped(java.lang.Object, org.httprobot.core.events.InetEventArgs)
	 */
	@Override
	public void OnServerInfoStopped(Object sender, InetEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IInetListener#OnServerInfoError(java.lang.Object, org.httprobot.core.events.InetEventArgs)
	 */
	@Override
	public void OnServerInfoError(Object sender, InetEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IInetListener#OnServerInfoFinished(java.lang.Object, org.httprobot.core.events.InetEventArgs)
	 */
	@Override
	public void OnServerInfoFinished(Object sender, InetEventArgs e)
	{
		
	}
}