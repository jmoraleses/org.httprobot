/**
 * 
 */
package org.httprobot.core.managers;

import java.util.Vector;

import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.Placeholder;
import org.httprobot.common.contents.FieldRef;
import org.httprobot.common.definitions.Enums.ManagerErrorCode;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.HtmlUnit;
import org.httprobot.common.placeholders.HttpAddress;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.contents.solr.InputField;
import org.httprobot.core.controls.interfaces.listeners.IPlaceholderControlListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.interfaces.IOperatorManagerImpl;
import org.httprobot.core.managers.interfaces.IPlaceholderManagerImpl;
import org.httprobot.core.managers.placeholders.interfaces.IManagerHtmlUnitListener;
import org.httprobot.core.managers.placeholders.interfaces.IManagerHttpAddressListener;

/**
 * {@link Placeholder} message manager abstract class. Inherits {@link Manager}.
 * <br>
 * It's {@link IPlaceholderManagerImpl}.
 * <br>
 * PlaceholderManager generates an {@link InputField} specified by it's child {@link FieldRef} message manager.
 * <br>
 * @author joan
 *
 * @param <TControl> The {@link Placeholder} message type.
 * @param <TListener> The {@link IOperatorManagerImpl} message manager listener type.
 */
@XmlTransient
public abstract class PlaceholderManager
	<TMessage extends Placeholder, TControl extends IPlaceholderControlListener, TListener extends IManagerImpl> 
		extends Manager	<TMessage, TControl, TListener> 
		implements IPlaceholderManagerImpl {
	
	/**
	 * -6757163293497558733L
	 */
	private static final long serialVersionUID = -6757163293497558733L;
	
	/**
	 * The {@link HtmlUnit} message manager listeners.
	 */
	private Vector<IManagerHtmlUnitListener> htmlUnitListeners;
	/**
	 * The {@link HttpAddress} message manager listeners.
	 */
	private Vector<IManagerHttpAddressListener> httpAddressListeners;

	/**
	 * {@link Placeholder} message manager default class constructor.
	 */
	public PlaceholderManager() 
	{
		super();
		
		//Initialize using data
		initPlaceholderManager();
	}
	/**
	 *  {@link Placeholder} message manager class constructor.
	 * @param parent  the parent
	 * @param message the {@link Placeholder} message
	 */
	public PlaceholderManager(TListener parent, TMessage message) 
	{
		super(parent, message);
		
		//Initialize using data
		initPlaceholderManager();
	}
	/**
	 * Adds {@link HtmlUnit} message manager listener.
	 * @param listener
	 */
	public synchronized final void addManagerHtmlBodyListener(IManagerHtmlUnitListener listener)
	{
		if(this.htmlUnitListeners == null)
		{
			this.htmlUnitListeners = new Vector<IManagerHtmlUnitListener>();
		}
		this.htmlUnitListeners.add(listener);
	}
	/**
	 * Adds {@link HttpAddress} message manager listener.
	 * @param listener
	 */
	public synchronized final void addManagerHttpAddressListener(IManagerHttpAddressListener listener)
	{
		if(this.httpAddressListeners == null)
		{
			this.httpAddressListeners = new Vector<IManagerHttpAddressListener>();
		}
		this.httpAddressListeners.add(listener);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public abstract void OnCommandInput(Object sender, CliEventArgs e) 
			throws InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public abstract void OnCommandOutput(Object sender, CliEventArgs e) 
			throws InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerFieldRefListener#OnManagerFieldRef_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRef_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"PlaceholderManager.OnManagerFieldRef_Error: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerFieldRefListener#OnManagerFieldRef_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRef_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"PlaceholderManager.OnManagerFieldRef_Finished: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerFieldRefListener#OnManagerFieldRef_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRef_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"PlaceholderManager.OnManagerFieldRef_Started: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.interfaces.IManagerHtmlUnitListener#OnManagerHtmlUnit_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerHtmlUnit_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"PlaceholderManager.OnManagerHtmlUnit_Error: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.interfaces.IManagerHtmlUnitListener#OnManagerHtmlUnit_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerHtmlUnit_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"PlaceholderManager.OnManagerHtmlUnit_Finished: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.interfaces.IManagerHtmlUnitListener#OnManagerHtmlUnit_InputFieldCompleted(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerHtmlUnit_InputFieldCompleted(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"PlaceholderManager.OnManagerHtmlUnit_InputFieldCompleted: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.interfaces.IManagerHtmlUnitListener#OnManagerHtmlUnit_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerHtmlUnit_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"PlaceholderManager.OnManagerHtmlUnit_Started: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.interfaces.IManagerHttpAddressListener#OnManagerHttpAddress_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerHttpAddress_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"PlaceholderManager.OnManagerHttpAddress_Error: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.interfaces.IManagerHttpAddressListener#OnManagerHttpAddress_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerHttpAddress_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"PlaceholderManager.OnManagerHttpAddress_Finished: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.interfaces.IManagerHttpAddressListener#OnManagerHttpAddress_InputFieldCompleted(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerHttpAddress_InputFieldCompleted(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"PlaceholderManager.OnManagerHttoAddress_InputFieldCompleted: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.interfaces.IManagerHttpAddressListener#OnManagerHttpAddress_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerHttpAddress_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"PlaceholderManager.OnManagerHttpAddress_Started: Method not overridden");
	}
	/**
	 * Removes {@link HtmlUnit} message manager listener.
	 * @param listener
	 */
	public synchronized final void removeManagerHtmlUnitListener(IManagerHtmlUnitListener listener)
	{
		this.htmlUnitListeners.remove(listener);
	}
	/**
	 * Removes {@link HtmlUnit} message manager listener.
	 * @param listener
	 */
	public synchronized final void removeManagerHttpAddressListener(IManagerHttpAddressListener listener)
	{
		this.httpAddressListeners.remove(listener);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#start()
	 */
	@Override
	public abstract void start();
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#stop()
	 */
	@Override
	public abstract void stop();
	/**
	 * Initializes de place holder message manager.
	 */
	private final void initPlaceholderManager()
	{		
		addManagerHtmlBodyListener(this);
		addManagerHttpAddressListener(this);
	}
	/**
	 * Fires {@link HtmlUnit} message manager event.
	 * @param e {@link ManagerEventArgs} the arguments
	 */
	protected final void ManagerHtmlUnitEvent(ManagerEventArgs e)
	{
		try
		{
			for(IManagerHtmlUnitListener listener : this.htmlUnitListeners)
			{
				switch (e.getEventType()) 
				{
					case STARTED:
						listener.OnManagerHtmlUnit_Started(this, e);
						break;
					case FINISHED:
						listener.OnManagerHtmlUnit_Finished(this, e);
						break;
					case ERROR:
						listener.OnManagerHtmlUnit_Error(this, e);
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
	 * Fires {@link HtmlUnit} message manager event.
	 * @param e {@link ManagerEventArgs} the arguments
	 */
	protected final void ManagerHttpAddressEvent(ManagerEventArgs e)
	{
		try
		{
			for(IManagerHttpAddressListener listener : this.httpAddressListeners)
			{
				switch (e.getEventType()) 
				{
					case STARTED:
						listener.OnManagerHttpAddress_Started(this, e);
						break;
					case FINISHED:
						listener.OnManagerHttpAddress_Finished(this, e);
						break;
					case ERROR:
						listener.OnManagerHttpAddress_Error(this, e);
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