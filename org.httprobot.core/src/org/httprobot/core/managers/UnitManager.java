/**
 * 
 */
package org.httprobot.core.managers;

import java.util.Vector;

import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.tools.CliTools;
import org.httprobot.common.unit.Action;
import org.httprobot.core.controls.interfaces.impl.IUnitControlImpl;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.datatypes.interfaces.IManagerActionListener;
import org.httprobot.core.managers.interfaces.IUnitManagerImpl;
import org.httprobot.common.Unit;

/**
 * {@link Unit} message manager class. Inherits {@link Manager}.
 * <br>
 * @author joan
 *
 * @param <TMessage> The {@link Unit} message type.
 * @param <TControl> The {@link IUnitControlImpl} type.
 * @param <TListener> The {@link IManagerImpl} listener type.
 */
public abstract class UnitManager
	<TMessage extends Unit, TControl extends IUnitControlImpl, TListener extends IManagerImpl>
		extends Manager<TMessage, TControl, TListener>
		implements IUnitManagerImpl {

	/**
	 * -4860553352052186483L
	 */
	private static final long serialVersionUID = -4860553352052186483L;
	/**
	 * The {@link Action} message manager listeners.
	 */
	private Vector<IManagerActionListener> actionListeners;
	
	/**
	 * Adds {@link Action} message manager listener
	 * @param listener
	 */
	public synchronized final void addManagerActionListener(IManagerActionListener listener)
	{
		if(this.actionListeners == null)
		{
			this.actionListeners = new Vector<IManagerActionListener>();
		}
		this.actionListeners.add(listener);
	}
	/**
	 * @param listener
	 */
	public synchronized final void removeManagerActionListener(IManagerActionListener listener)
	{
		this.actionListeners.remove(listener);
	}
	/**
	 * {@link Unit} message manager default class constructor.
	 */
	public UnitManager() 
	{
		super();
		
		initUnitManager();
	}
	/**
	 * {@link Unit} message manager class constructor.
	 * @param parent the parent
	 * @param message the message
	 */
	public UnitManager(TListener parent, TMessage message) 
	{
		super(parent, message);
		
		initUnitManager();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.Manager#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.Manager#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.Manager#start()
	 */
	@Override
	public abstract void start() ;
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.Manager#stop()
	 */
	@Override
	public abstract void stop();
	/**
	 * Initializes {@link Unit} message manager.
	 */
	private final void initUnitManager()
	{
		addManagerActionListener(this);
	}
	/**
	 * Fires {@link Action} message manager event.
	 * @param e {@link ManagerEventArgs} the arguments
	 */
	protected final void ManagerActionEvent(ManagerEventArgs e)
	{
		for (IManagerActionListener listener : this.actionListeners) 
		{
			try 
			{
				switch (e.getEventType()) 
				{
					case STARTED:
						listener.OnManagerAction_Started(this, e);
						break;
					case FINISHED:
						listener.OnManagerAction_Finished(this, e);
						break;
					case ERROR:
						listener.OnManagerAction_Error(this, e);
						break;
					case PAGE_LOADED:
						listener.OnManagerAction_WebLoaded(this, e);
						break;
					default:
						break;
				}
			}
			catch (ManagerException e2)
			{
				e2.printStackTrace();
			}
			catch (NotImplementedException e3) 
			{
				e3.printStackTrace();
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerActionListener#OnManagerAction_WebLoaded(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAction_WebLoaded(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {

		CliTools.ThrowNotImplementedException(this, 
				"UnitManager.OnManagerAction_WebLoaded: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerActionListener#OnManagerAction_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAction_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {

		CliTools.ThrowNotImplementedException(this, 
				"UnitManager.OnManagerAction_Started: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerActionListener#OnManagerAction_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAction_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {

		CliTools.ThrowNotImplementedException(this, 
				"UnitManager.OnManagerAction_Finished: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerActionListener#OnManagerAction_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAction_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {	

		CliTools.ThrowNotImplementedException(this, 
				"UnitManager.OnManagerAction_Error: Method not overridden.");
	}
}