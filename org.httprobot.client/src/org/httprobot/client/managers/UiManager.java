package org.httprobot.client.managers;

import java.util.EnumSet;
import java.util.Vector;

import javax.swing.SwingUtilities;

import org.httprobot.client.common.UiRunnable;
import org.httprobot.common.definitions.Enums.CliNamespace;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.UiEventArgs;
import org.httprobot.common.interfaces.IUiListener;
import org.httprobot.core.events.InetEventArgs;
import org.httprobot.core.interfaces.IInetListener;
import org.httprobot.core.managers.Manager;
import org.httprobot.ui.client.Main;
import org.w3c.dom.events.Event;

public class UiManager extends Manager<IUiListener> implements IUiListener, IInetListener
{
	private static final CliNamespace cliNamespace = CliNamespace.UI;
	static Main application;
	static UiRunnable uiRunnable;
	static UiManager uiManager;
	
	static String appconfig;
	static String serverslist;
	
	static String destinationPath;
	static EnumSet<RuntimeOptions> options;
	/**
	 * ui_changed_listeners Listeners
	 */
	static Vector<IUiListener> manager_changed_listeners = new Vector<IUiListener>();  //  @jve:decl-index=0:
	/**
	 * Adds ui_changed_listeners event listener
	 * @param rmlControl
	 */
	public final synchronized void addUiChangedListener(IUiListener rmlControl)
	{
		manager_changed_listeners.add(rmlControl);
	}	
	/**
	 * Removes ui_changed_listeners event listener
	 * @param rmlControl
	 */
	public final synchronized void removeUiChangedListeners(IUiListener listener)
	{
		manager_changed_listeners.remove(listener);
	}
	/**
	 * Fires event method to parent.
	 * @param UiEventArgs
	 */
	public final void ManagerChangedEvent(UiEventArgs e) 
	{
		for (IUiListener listener : manager_changed_listeners) 
		{
			if(listener != null)
			{
				listener.OnUiChanged(this, e);
			}
		}
	}	
	/**
	 * UiManager default class constructor.
	 * @param parent {@link IUiListener} the parent
	 * @param app_config the Request configuration file to string
	 * @param serv_list the list of ServerInfo to string
	 */
	public UiManager(IUiListener parent, String app_config, String serv_list)
	{
		super(parent);
		addUiChangedListener(parent);
		appconfig = app_config;		
		serverslist = serv_list;
	}
	
	/* (non-Javadoc)
	 * @see org.httprobot.client.managers.Manager#start()
	 */
	@Override
	public void start() 
	{
		 //bacgroundTask.start();
		 uiManager = new UiManager(this.getParent(), appconfig, serverslist);
		 //Thread thread = new Thread(doneAction);
		 //thread.start();
		 SwingUtilities.invokeLater(doneAction);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.client.managers.Manager#stop()
	 */
	@Override
	public void stop() 
	{
		
	}
    /**
     * Called when id done
     */
    static UiRunnable doneAction = new UiRunnable(uiManager)
    {
        public void run()
        {
            application = new Main(appconfig, serverslist);
			application.addUiChangedListeners(uiManager);
			application.getJFrame().setVisible(true);
            System.out.println( Thread.currentThread().getName() + " finish setting text ");
        }
    };
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IUiListener#OnUiChanged(java.lang.Object, org.httprobot.common.events.UiEventArgs)
	 */
	@Override
	public void OnUiChanged(Object sender, UiEventArgs e) 
	{
		System.out.println("\nEntra UiManager.OnUiChanged: " + e.getUiet() + "\n");
		ManagerChangedEvent(new UiEventArgs(this, e.getUiet()));
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e) 
	{
		
	}


	/* (non-Javadoc)
	 * @see org.w3c.dom.events.EventListener#handleEvent(org.w3c.dom.events.Event)
	 */
	@Override
	public void handleEvent(Event arg0) 
	{
		
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
	 * @see org.httprobot.common.interfaces.IListener#setCliOptions(java.util.EnumSet)
	 */
	@Override
	public void setRuntimeOptions(EnumSet<RuntimeOptions> _options) 
	{
		options = _options;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getDestinationPath()
	 */
	@Override
	public String getDestinationPath() 
	{
		return destinationPath;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#setDestinationPath(java.lang.String)
	 */
	@Override
	public void setDestinationPath(String destinationPath)
	{
		
	}

	@Override
	public void OnServerInfoStarted(Object sender, InetEventArgs e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void OnServerInfoStopped(Object sender, InetEventArgs e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void OnServerInfoError(Object sender, InetEventArgs e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnServerInfoFinished(Object sender, InetEventArgs e) {
		// TODO Auto-generated method stub
		
	}
	
//	static void done()
//	{
//		
//	}
//    // run a task in the background
//    static Thread bacgroundTask = new Thread(){
//        public void run(){
//            try { 
//                System.out.println( Thread.currentThread().getName() + " Started background task ");
//                Thread.sleep( 5000 );
//                System.out.println( Thread.currentThread().getName() + " Finished background task");
//                done();
//            } catch ( InterruptedException ie ){}
//        }
//    };
}
