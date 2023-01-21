package org.httprobot.core.managers;

import java.util.ArrayList;
import java.util.UUID;
import java.util.Vector;

import org.httprobot.common.definitions.Enums.CliNamespace;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.io.CommandLine;
import org.httprobot.common.rml.datatypes.Action;
import org.httprobot.common.rml.datatypes.ServerInfo;
import org.httprobot.common.rml.datatypes.Step;
import org.httprobot.common.rml.datatypes.Steps;
import org.httprobot.common.tools.CommandLineInterface;
import org.httprobot.core.events.InetEventArgs;
import org.httprobot.core.events.WebLoaderEventArgs;
import org.httprobot.core.inet.WebLoader;
import org.httprobot.core.interfaces.IInetListener;
import org.httprobot.core.interfaces.IWebLoaderListener;
import org.httprobot.core.rml.controls.datatypes.ServerInfoControl;
import org.httprobot.core.rml.controls.datatypes.StepsControl;
import org.httprobot.core.rml.controls.datatypes.interfaces.IServerInfoListener;
import org.httprobot.core.rml.controls.datatypes.interfaces.IStepsListener;
/**
 * Internet manager class. 
 * Inherits {@link Manager} with {@link IInetListener}.
 * @author Joan
*/
public class InetManager extends Manager<IInetListener> implements IWebLoaderListener, IServerInfoListener, IStepsListener
{
	private static final CliNamespace cliNamespace = CliNamespace.INET;
	/**
	 * InetManager Listeners
	 */
	private Vector<IInetListener> inetListeners;
	private Vector<WebLoader> webLoadersList;
	
	private ServerInfo serverInfo;	
	private ServerInfoControl serverInfoControl;
	private Steps steps;
	private StepsControl stepsControl;
	/**
	 * InetManager class constructor.
	 * @param parent {@link IInetListener} the listener
	 */
	public InetManager(IInetListener parent, ServerInfo serverInfo)
	{
		super(parent);
		
		//Initialize using data
		this.serverInfo = serverInfo;
		this.inetListeners = new Vector<IInetListener>();
		this.webLoadersList = new Vector<WebLoader>();
		this.serverInfoControl = new ServerInfoControl(this, serverInfo);
		
		//Associate control to parent
		this.addCommandOutputListener(this.serverInfoControl);
		this.serverInfoControl.addServerInfoListener(this);
		this.serverInfoControl.addStepsListener(this);
	}
	/**
	 * Adds {@link IInetListener} Internet manager event listener.
	 * @param listener {@link IInetListener} the listener
	 */
	public final synchronized void addInetManagerListener(IInetListener listener)
	{
		inetListeners.add(listener);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getCliNamespace()
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
		return null;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IRmlListener#getUuid()
	 */
	@Override
	public UUID getUuid()
	{
		return null;
	}
	/**
	 * Gets the web tabs.
	 * @return {@link Vector} of {@link WebLoader}
	 */
	public Vector<WebLoader> getWeb_tabs() 
	{
		return this.webLoadersList;
	}
	/**
	 * Gets the count of WebTab.
	 * @return {@link Integer} the web tabs count
	 */
	public Integer getWeb_tabs_count() 
	{
		return this.webLoadersList.size();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e) 
	{
		switch (e.getCmd()) 
		{
			case MESSAGE:
				System.out.println("\n CLI message: \n" + e.getSource().toString() + "\n");
				break;
			case ACTION_CONTROL:
				Action action = Action.class.cast(e.getMessage());
				System.out.println("\nCONNECT TO\n" + action.getHttpAddress());
				
				
				break;
				
			case SERVER_INFO_CONTROL:
				break;
				
			default:
				System.out.println("\n CLI event" + e.getCmd().toString() + ": \n" + e.getMessage().toString());
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
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IServerInfoListener#OnServerInfoChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnServerInfoChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IServerInfoListener#OnServerInfoInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnServerInfoInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IServerInfoListener#OnServerInfoLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnServerInfoLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			try
			{
				ServerInfo serverInfo = ServerInfo.class.cast(e.getMessage());
				
				if(serverInfo.getSteps() != null)
				{
					//Initialize control
					this.steps = serverInfo.getSteps();					
					
				}
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "InetManager.OnServerInfoLoaded: ServerInfo RML message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IServerInfoListener#OnServerInfoRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnServerInfoRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IServerInfoListener#OnServerInfoRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnServerInfoRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IServerInfoListener#OnServerInfoWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnServerInfoWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IWebTabListener#OnWebTabAddressChanged(java.lang.Object, org.httprobot.core.events.WebRequesterEventArgs)
	 */
	@Override
	public void OnWebLoaderAddressChanged(Object sender, WebLoaderEventArgs e)
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IWebTabListener#OnWebTabCompleted(java.lang.Object, org.httprobot.core.events.WebRequesterEventArgs)
	 */
	@Override
	public void OnWebLoaderCompleted(Object sender, WebLoaderEventArgs e) 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IWebTabListener#OnWebTabStarted(java.lang.Object, org.httprobot.core.events.WebRequesterEventArgs)
	 */
	@Override
	public void OnWebLoaderStarted(Object sender, WebLoaderEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IWebTabListener#OnWebTabStopped(java.lang.Object, org.httprobot.core.events.WebRequesterEventArgs)
	 */
	@Override
	public void OnWebLoaderStopped(Object sender, WebLoaderEventArgs e) 
	{
		// TODO Auto-generated method stub
		switch (e.getIet()) 
		{
			case ERROR:
				WebLoader currentWebTab = WebLoader.class.cast(sender);
				currentWebTab.refresh();
				break;
			default:
				break;
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IWebLoaderListener#OnWebLoaderTimedOut(java.lang.Object, org.httprobot.core.events.WebLoaderEventArgs)
	 */
	@Override
	public void OnWebLoaderTimedOut(Object sender, WebLoaderEventArgs e) 
	{
	
	}
	/**
	 * Adds {@link IInetListener} Internet manager event listener.
	 * @param listener {@link IInetListener} the listener
	 */
	public final synchronized void removeInetManagerListener(IInetListener listener)
	{
		inetListeners.remove(listener);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.client.managers.Manager#start()
	 */
	@Override
	public void start() 
	{
		this.serverInfoControl.setMessage(serverInfo);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.client.managers.Manager#stop()
	 */
	@Override
	public void stop() 
	{
		
	}
	/**
	 * Fires {@link IRmlListener} InetManager event.
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void InetManagerEvent(InetEventArgs e)
	{
		for (IInetListener listener : inetListeners) 
		{
			switch (e.getEventType()) 
			{
				case SERVER_INFO_STARTED:
					listener.OnServerInfoStarted(this, e);
					break;
				case SERVER_INFO_STOPPED:
					listener.OnServerInfoStopped(this, e);
					break;
				case SERVER_INFO_ERROR:
					listener.OnServerInfoError(this, e);
					break;
				
				default:
					break;
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IStepsListener#OnStepsInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepsInit(Object sender, RmlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IStepsListener#OnStepsRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepsRead(Object sender, RmlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IStepsListener#OnStepsLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepsLoaded(Object sender, RmlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IStepsListener#OnStepsChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepsChanged(Object sender, RmlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IStepsListener#OnStepsRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepsRendered(Object sender, RmlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IStepsListener#OnStepsWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepsWrite(Object sender, RmlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		// TODO Auto-generated method stub
		
	}
}