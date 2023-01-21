/**
 * 
 */
package org.httprobot.core.managers;

import java.util.Vector;

import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.Operator;
import org.httprobot.common.definitions.Enums.ManagerErrorCode;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.operators.Contains;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.controls.interfaces.listeners.IOperatorControlListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.interfaces.IOperatorManagerImpl;
import org.httprobot.core.managers.interfaces.IPlaceholderManagerImpl;
import org.httprobot.core.managers.operators.ContainsManager;
import org.httprobot.core.managers.operators.interfaces.IManagerContainsListener;
import org.httprobot.core.managers.operators.interfaces.IManagerEqualsListener;
import org.httprobot.core.managers.operators.interfaces.IManagerRemoveListener;
import org.httprobot.core.managers.operators.interfaces.IManagerReplaceListener;
import org.httprobot.core.managers.operators.interfaces.IManagerSplitListener;
import org.httprobot.core.managers.operators.interfaces.IManagerSubstringListener;
import org.httprobot.core.managers.operators.interfaces.IManagerTryParseListener;

/**
 * {@link Operator} message manager class. Inherits {@link Manager}.
 * <br>
 * It's {@link IOperatorManagerImpl}.
 * <br>
 * @author joan
 *
 * @param <TControl> The {@link Operator} message type,
 * @param <TListener> The message listener type being listening.
 */
@XmlTransient
public abstract class OperatorManager
	<TMessage extends Operator, TControl extends IOperatorControlListener, TListener extends IManagerImpl> 
		extends PlaceholderManager<TMessage, TControl, TListener>
		implements IOperatorManagerImpl {
	
	/**
	 * 652447538501878409L
	 */
	private static final long serialVersionUID = 652447538501878409L;

	/**
	 * The {@link ContainsManager}'s listeners
	 */
	private Vector<IManagerContainsListener> containsListeners;
	/**
	 * The {@link EqualsManager}'s listeners
	 */
	private Vector<IManagerEqualsListener> equalsListeners;
	/**
	 * The {@link RemoveManager}'s listeners
	 */
	private Vector<IManagerRemoveListener> removeListeners;
	/**
	 * The {@link ReplaceManager}'s listeners
	 */
	private Vector<IManagerReplaceListener> replaceListeners;
	/**
	 * The {@link SplitManager}'s listeners
	 */
	private Vector<IManagerSplitListener> splitListeners;
	/**
	 * The {@link SubstringManager}'s listeners
	 */
	private Vector<IManagerSubstringListener> substringListeners;
	/**
	 * The {@link TryParseManager}'s listeners
	 */
	private Vector<IManagerTryParseListener> tryParseListeners;
	/**
	 * The place holder message manager listens all events 
	 * emitted by first operator message manager.
	 * @param listener the {@link PlaceholderManager}
	 */
	public void addPlaceholderListener(IPlaceholderManagerImpl listener)
	{
		this.addManagerContainsListener(listener);
		this.addManagerEqualsListener(listener);
		this.addManagerTryParseListener(listener);
		this.addManagerRemoveListener(listener);
		this.addManagerReplaceListener(listener);
		this.addManagerSubstringListener(listener);
		this.addManagerSplitListener(listener);
	}
	/**
	 * The operator message manager listens all events 
	 * emitted by first operator message manager.
	 * @param listener the {@link PlaceholderManager}
	 */
	public void addOperatorListener(IOperatorManagerImpl listener)
	{
		this.addManagerContainsListener(listener);
		this.addManagerEqualsListener(listener);
		this.addManagerTryParseListener(listener);
		this.addManagerRemoveListener(listener);
		this.addManagerReplaceListener(listener);
		this.addManagerSubstringListener(listener);
		this.addManagerSplitListener(listener);
	}
	/**
	 * {@link Operator} message manager default class constructor.
	 */
	public OperatorManager() 
	{
		super();
		
		//Initialize manager
		initOperatorManager();
	}	
	/**
	 * {@link Operator} message manager default class constructor.
	 * @param parent the parent
	 * @param message the message
	 */
	public OperatorManager(TListener parent, TMessage message)
	{
		super(parent, message);
				
		//Initialize manager
		initOperatorManager();
	}
	/**
	 * Adds {@link Contains} message manager listener,
	 * @param listener
	 */
	public synchronized final void addManagerContainsListener(IManagerContainsListener listener)
	{
		if(this.containsListeners == null)
		{
			this.containsListeners = new Vector<IManagerContainsListener>();
		}
		this.containsListeners.add(listener);
	}
	/**
	 * Adds {@link Equals} message manager listener,
	 * @param listener
	 */
	public synchronized final void addManagerEqualsListener(IManagerEqualsListener listener)
	{
		if(this.equalsListeners == null)
		{
			this.equalsListeners = new Vector<IManagerEqualsListener>();
		}
		this.equalsListeners.add(listener);
	}
	/**
	 * Adds {@link Remove} message manager listener,
	 * @param listener
	 */
	public synchronized final void addManagerRemoveListener(IManagerRemoveListener listener)
	{
		if(this.removeListeners == null)
		{
			this.removeListeners = new Vector<IManagerRemoveListener>();
		}
		this.removeListeners.add(listener);
	}
	/**
	 * Adds {@link Replace} message manager listener,
	 * @param listener
	 */
	public synchronized final void addManagerReplaceListener(IManagerReplaceListener listener)
	{
		if(this.replaceListeners == null)
		{
			this.replaceListeners = new Vector<IManagerReplaceListener>();
		}
		this.replaceListeners.add(listener);
	}
	/**
	 * Adds {@link Select} message manager listener,
	 * @param listener
	 */
	public synchronized final void addManagerSplitListener(IManagerSplitListener listener)
	{
		if(this.splitListeners == null)
		{
			this.splitListeners = new Vector<IManagerSplitListener>();
		}
		this.splitListeners.add(listener);
	}
	/**
	 * Adds {@link Substring} message manager listener,
	 * @param listener
	 */
	public synchronized final void addManagerSubstringListener(IManagerSubstringListener listener)
	{
		if(this.substringListeners == null)
		{
			this.substringListeners = new Vector<IManagerSubstringListener>();
		}
		this.substringListeners.add(listener);
	}
	/**
	 * Adds {@link TryParse} message manager listener,
	 * @param listener
	 */
	public synchronized final void addManagerTryParseListener(IManagerTryParseListener listener)
	{
		if(this.tryParseListeners == null)
		{
			this.tryParseListeners = new Vector<IManagerTryParseListener>();
		}
		this.tryParseListeners.add(listener);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.Manager#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public abstract void OnCommandInput(Object sender, CliEventArgs e)
			throws InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.Manager#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public abstract void OnCommandOutput(Object sender, CliEventArgs e)
			throws InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerContainsListener#OnManagerContains_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContains_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorManager.OnManagerContains_Error: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerContainsListener#OnManagerContains_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContains_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorManager.OnManagerContains_Finished: Method not overridden.");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerContainsListener#OnManagerContains_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContains_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorManager.OnManagerContains_Started: Method not overridden.");		
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerEqualsListener#OnManagerEquals_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerEquals_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorManager.OnManagerEquals_Error: Method not overridden.");		
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerEqualsListener#OnManagerEquals_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerEquals_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorManager.OnManagerEquals_Finished: Method not overridden.");		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerEqualsListener#OnManagerEquals_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerEquals_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorManager.OnManagerEquals_Started: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerRemoveListener#OnManagerRemove_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerRemove_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorManager.OnManagerRemove_Error: Method not overridden.");		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerRemoveListener#OnManagerRemove_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerRemove_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorManager.OnManagerRemove_Finished: Method not overridden.");		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerRemoveListener#OnManagerRemove_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerRemove_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorManager.OnManagerRemove_Started: Method not overridden.");		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerReplaceListener#OnManagerReplace_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerReplace_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorManager.OnManagerReplace_Error: Method not overridden.");		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerReplaceListener#OnManagerReplace_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerReplace_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorManager.OnManagerReplace_Finished: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerReplaceListener#OnManagerReplace_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerReplace_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorManager.OnManagerReplace_Started: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerSplitListener#OnManagerSplit_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSplit_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorManager.OnManagerSplit_Error: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerSplitListener#OnManagerSplit_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSplit_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorManager.OnManagerSplit_Finished: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerSplitListener#OnManagerSplit_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSplit_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorManager.OnManagerSplit_Started: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerSubstringListener#OnManagerSubstring_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSubstring_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorManager.OnManagerSubstring_Error: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerSubstringListener#OnManagerSubstring_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSubstring_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorManager.OnManagerSubstring_Finished: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerSubstringListener#OnManagerSubstring_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSubstring_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorManager.OnManagerSubstring_Started: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerTryParseListener#OnManagerTryParse_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerTryParse_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorManager.OnManagerTryParse_Error: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerTryParseListener#OnManagerTryParse_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerTryParse_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorManager.OnManagerTryParse_Finished: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerTryParseListener#OnManagerTryParse_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerTryParse_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorManager.OnManagerTryParse_Started: Method not overridden.");
	}
	/**
	 * Removes {@link Contains} message manager listener,
	 * @param listener
	 */
	public synchronized final void removeManagerContainsListener(IManagerContainsListener listener)
	{
		this.containsListeners.remove(listener);
	}
	/**
	 * Removes {@link Equals} message manager listener,
	 * @param listener
	 */
	public synchronized final void removeManagerEqualsListener(IManagerEqualsListener listener)
	{
		this.equalsListeners.remove(listener);
	}
	/**
	 * Removes {@link Remove} message manager listener,
	 * @param listener
	 */
	public synchronized final void removeManagerRemoveListener(IManagerRemoveListener listener)
	{
		this.removeListeners.remove(listener);
	}
	/**
	 * Removes {@link Replace} message manager listener,
	 * @param listener
	 */
	public synchronized final void removeManagerReplaceListener(IManagerReplaceListener listener)
	{
		this.replaceListeners.remove(listener);
	}
	/**
	 * Removes {@link Select} message manager listener,
	 * @param listener
	 */
	public synchronized final void removeManagerSplitListener(IManagerSplitListener listener)
	{
		this.splitListeners.remove(listener);
	}
	/**
	 * Removes {@link Substring} message manager listener,
	 * @param listener
	 */
	public synchronized final void removeManagerSubstringListener(IManagerSubstringListener listener)
	{
		this.substringListeners.remove(listener);
	}	
	/**
	 * Removes {@link TryParse} message manager listener,
	 * @param listener
	 */
	public synchronized final void removeManagerTryParseListener(IManagerTryParseListener listener)
	{
		this.tryParseListeners.remove(listener);
	}

	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlPlaceholderManager#start()
	 */
	@Override
	public abstract void start();
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlPlaceholderManager#stop()
	 */
	@Override
	public abstract void stop();
	/**
	 * Initializes operator message manager.
	 */
	private void initOperatorManager()
	{
		addManagerContainsListener(this);
		addManagerEqualsListener(this);
		addManagerRemoveListener(this);
		addManagerReplaceListener(this);
		addManagerSplitListener(this);
		addManagerSubstringListener(this);
		addManagerTryParseListener(this);
	}
	/**
	 * Fires {@link Contains} message manager event.
	 * @param e {@link ManagerEventArgs} the arguments
	 */
	protected final void ManagerContainsEvent(ManagerEventArgs e)
	{
		try
		{
			for(IManagerContainsListener listener : this.containsListeners)
			{
				switch (e.getEventType()) 
				{
					case STARTED:
						listener.OnManagerContains_Started(this, e);
						break;
					case FINISHED:
						listener.OnManagerContains_Finished(this, e);
						break;
					case ERROR:
						listener.OnManagerContains_Error(this, e);
						break;
					default:
						break;
				}
			}
		}
		catch (ManagerException ex1)
		{
			ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		} 
		catch (NotImplementedException ex2) 
		{
			ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.NOT_IMPLEMENTED));
		}
	}
	/**
	 * Fires {@link Equals} message manager event.
	 * @param e {@link ManagerEventArgs} the arguments
	 */
	protected final void ManagerEqualsEvent(ManagerEventArgs e)
	{
		try
		{
			for(IManagerEqualsListener listener : this.equalsListeners)
			{
				switch (e.getEventType()) 
				{
					case STARTED:
						listener.OnManagerEquals_Started(this, e);
						break;
					case FINISHED:
						listener.OnManagerEquals_Finished(this, e);
						break;
					case ERROR:
						listener.OnManagerEquals_Error(this, e);
						break;
					default:
						break;
				}
			}
		}
		catch (ManagerException ex1)
		{
			ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		} 
		catch (NotImplementedException ex2) 
		{
			ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.NOT_IMPLEMENTED));
		}
	}
	/**
	 * Fires {@link Remove} message manager event.
	 * @param e {@link ManagerEventArgs} the arguments
	 */
	protected final void ManagerRemoveEvent(ManagerEventArgs e)
	{
		try
		{
			for(IManagerRemoveListener listener : this.removeListeners)
			{
				switch (e.getEventType()) 
				{
					case STARTED:
						listener.OnManagerRemove_Started(this, e);
						break;
					case FINISHED:
						listener.OnManagerRemove_Finished(this, e);
						break;
					case ERROR:
						listener.OnManagerRemove_Error(this, e);
						break;
					default:
						break;
				}
			}
		}
		catch (ManagerException ex1)
		{
			ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		} 
		catch (NotImplementedException ex2) 
		{
			ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.NOT_IMPLEMENTED));
		}
	}
	/**
	 * Fires {@link Replace} message manager event.
	 * @param e {@link ManagerEventArgs} the arguments
	 */
	protected final void ManagerReplaceEvent(ManagerEventArgs e)
	{
		try
		{
			for(IManagerReplaceListener listener : this.replaceListeners)
			{
				switch (e.getEventType()) 
				{
					case STARTED:
						listener.OnManagerReplace_Started(this, e);
						break;
					case FINISHED:
						listener.OnManagerReplace_Finished(this, e);
						break;
					case ERROR:
						listener.OnManagerReplace_Error(this, e);
						break;
					default:
						break;
				}
			}
		}
		catch (ManagerException ex1)
		{
			ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		} 
		catch (NotImplementedException ex2) 
		{
			ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.NOT_IMPLEMENTED));
		}
	}
	/**
	 * Fires {@link Split} message manager event.
	 * @param e {@link ManagerEventArgs} the arguments
	 */
	protected final void ManagerSplitEvent(ManagerEventArgs e)
	{
		try
		{
			for(IManagerSplitListener listener : this.splitListeners)
			{
				switch (e.getEventType()) 
				{
					case STARTED:
						listener.OnManagerSplit_Started(this, e);
						break;
					case FINISHED:
						listener.OnManagerSplit_Finished(this, e);
						break;
					case ERROR:
						listener.OnManagerSplit_Error(this, e);
						break;
					default:
						break;
				}
			}
		}
		catch (ManagerException ex1)
		{
			ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		} 
		catch (NotImplementedException ex2) 
		{
			ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.NOT_IMPLEMENTED));
		}
	}
	/**
	 * Fires {@link Substring} message manager event.
	 * @param e {@link ManagerEventArgs} the arguments
	 */
	protected final void ManagerSubstringEvent(ManagerEventArgs e)
	{
		try
		{
			for(IManagerSubstringListener listener : this.substringListeners)
			{
				switch (e.getEventType()) 
				{
					case STARTED:
						listener.OnManagerSubstring_Started(this, e);
						break;
					case FINISHED:
						listener.OnManagerSubstring_Finished(this, e);
						break;
					case ERROR:
						listener.OnManagerSubstring_Error(this, e);
						break;
					default:
						break;
				}
			}
		}
		catch (ManagerException ex1)
		{
			ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		} 
		catch (NotImplementedException ex2) 
		{
			ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.NOT_IMPLEMENTED));
		}
	}
	/**
	 * Fires {@link TryParse} message manager event.
	 * @param e {@link ManagerEventArgs} the arguments
	 */
	protected final void ManagerTryParseEvent(ManagerEventArgs e)
	{
		try
		{
			for(IManagerTryParseListener listener : this.tryParseListeners)
			{
				switch (e.getEventType()) 
				{
					case STARTED:
						listener.OnManagerTryParse_Started(this, e);
						break;
					case FINISHED:
						listener.OnManagerTryParse_Finished(this, e);
						break;
					case ERROR:
						listener.OnManagerTryParse_Error(this, e);
						break;
					default:
						break;
				}
			}
		}
		catch (ManagerException ex1)
		{
			ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		} 
		catch (NotImplementedException ex2) 
		{
			ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.NOT_IMPLEMENTED));
		}
	}

}
