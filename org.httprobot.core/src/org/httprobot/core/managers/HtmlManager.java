/**
 * 
 */
package org.httprobot.core.managers;

import java.util.Vector;

import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.definitions.Enums.ManagerErrorCode;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.operators.html.Division;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.managers.html.interfaces.IManagerAnchorListener;
import org.httprobot.core.managers.html.interfaces.IManagerDivisionListener;
import org.httprobot.core.managers.html.interfaces.IManagerElementListener;
import org.httprobot.core.managers.html.interfaces.IManagerPageListener;
import org.httprobot.core.managers.html.interfaces.IManagerTableCellListener;
import org.httprobot.core.managers.html.interfaces.IManagerTableListener;
import org.httprobot.core.managers.html.interfaces.IManagerTableRowListener;
import org.httprobot.core.managers.interfaces.IHtmlManagerImpl;

import org.httprobot.common.Html;
import org.httprobot.core.controls.interfaces.listeners.IHtmlControlListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;

/**
 * {@link Html} message manager class. Inherits {@link OperatorManager}.
 * <br>
 * It's {@link IHtmlManagerImpl}.
 * <br>
 * @author joan
 *
 */
@XmlTransient
public abstract class HtmlManager
	<TMessage extends Html, TControl extends IHtmlControlListener, TListener extends IManagerImpl>
		extends OperatorManager<TMessage, TControl, TListener> 
		implements IHtmlManagerImpl {

	/**
	 * -8959533805865888860L
	 */
	private static final long serialVersionUID = -8959533805865888860L;

	/**
	 * The {@link Anchor} message manager listeners
	 */
	private Vector<IManagerAnchorListener> anchorListeners;
	/**
	 * The {@link Element} message manager listeners
	 */
	private Vector<IManagerElementListener> elementListeners;
	/**
	 * The {@link Page} message manager listeners
	 */
	private Vector<IManagerPageListener> pageListeners;
	/**
	 * The {@link Division} message manager listeners.
	 */
	private Vector<IManagerDivisionListener> divisionListeners;
	/**
	 * The {@link Table} message manager listeners.
	 */
	private Vector<IManagerTableListener> tableListeners;
	/**
	 * The {@link TableRow} message manager listeners.
	 */
	private Vector<IManagerTableRowListener> tableRowListeners;
	/**
	 * The {@link TableCell} message manager listeners.
	 */
	private Vector<IManagerTableCellListener> tableCellListeners;
	
	/**
	 * {@link Html} message manager default class constructor.
	 */
	public HtmlManager() {
		
		super();
		
		initHtmlManager();
	}
	/**
	 * {@link Html} message manager class constructor.
	 * @param parent the parent
	 * @param message the message
	 */
	public HtmlManager(TListener parent, TMessage message) {
		
		super(parent, message);
		this.initHtmlManager();
	}
	/**
	 * Adds {@link Anchor} message manager listener,
	 * @param listener
	 */
	public synchronized final void addManagerAnchorListener(IManagerAnchorListener listener)
	{
		if(this.anchorListeners == null)
		{
			this.anchorListeners = new Vector<IManagerAnchorListener>();
		}
		this.anchorListeners.add(listener);
	}
	/**
	 * Adds {@link Element} message manager listener,
	 * @param listener
	 */
	public synchronized final void addManagerElementListener(IManagerElementListener listener)
	{
		if(this.elementListeners == null)
		{
			this.elementListeners = new Vector<IManagerElementListener>();
		}
		this.elementListeners.add(listener);
	}
	/**
	 * Adds {@link Page} message manager listener,
	 * @param listener
	 */
	public synchronized final void addManagerPageListener(IManagerPageListener listener)
	{
		if(this.pageListeners == null)
		{
			this.pageListeners = new Vector<IManagerPageListener>();
		}
		this.pageListeners.add(listener);
	}
	/**
	 * Adds {@link Division} message manager listener,
	 * @param listener
	 */
	public synchronized final void addManagerDivisionListener(IManagerDivisionListener listener)
	{
		if(this.divisionListeners == null)
		{
			this.divisionListeners = new Vector<IManagerDivisionListener>();
		}
		this.divisionListeners.add(listener);
	}
	/**
	 * Adds {@link Table} message manager listener,
	 * @param listener
	 */
	public synchronized final void addManagerTableListener(IManagerTableListener listener)
	{
		if(this.tableListeners == null)
		{
			this.tableListeners = new Vector<IManagerTableListener>();
		}
		this.tableListeners.add(listener);
	}
	/**
	 * Adds {@link TableRow} message manager listener,
	 * @param listener
	 */
	public synchronized final void addManagerTableRowListener(IManagerTableRowListener listener)
	{
		if(this.tableRowListeners == null)
		{
			this.tableRowListeners = new Vector<IManagerTableRowListener>();
		}
		this.tableRowListeners.add(listener);
	}
	/**
	 * Adds {@link TableCell} message manager listener,
	 * @param listener
	 */
	public synchronized final void addManagerTableCellListener(IManagerTableCellListener listener)
	{
		if(this.tableCellListeners == null)
		{
			this.tableCellListeners = new Vector<IManagerTableCellListener>();
		}
		this.tableCellListeners.add(listener);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.OperatorManager#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public abstract void OnCommandInput(Object sender, CliEventArgs e)
			throws InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.OperatorManager#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public abstract void OnCommandOutput(Object sender, CliEventArgs e)
			throws InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.html.interfaces.IManagerAnchorListener#OnManagerAnchor_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAnchor_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlManager.OnManagerAnchor_Error: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.html.interfaces.IManagerAnchorListener#OnManagerAnchor_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAnchor_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlManager.OnManagerAnchor_Finished: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.html.interfaces.IManagerAnchorListener#OnManagerAnchor_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAnchor_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this,
				"HtmlManager.OnManagerAnchor_Started: Method not overridden.");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.html.interfaces.IManagerElementListener#OnManagerElement_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerElement_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this,
				"HtmlManager.OnManagerElement_Error: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.html.interfaces.IManagerElementListener#OnManagerElement_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerElement_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this,
				"HtmlManager.OnManagerElement_Finished: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.html.interfaces.IManagerElementListener#OnManagerElement_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerElement_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this,
				"HtmlManager.OnManagerElement_Started: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.html.interfaces.IManagerPageListener#OnManagerPage_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerPage_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlManager.OnManagerPage_Error: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.html.interfaces.IManagerPageListener#OnManagerPage_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerPage_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlManager.OnManagerPage_Finished: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.html.interfaces.IManagerPageListener#OnManagerPage_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerPage_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this,
				"HtmlManager.OnManagerPage_Started: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.html.interfaces.IManagerDivisionListener#OnManagerDivision_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDivision_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this,
				"HtmlManager.OnManagerDivision_Started: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.html.interfaces.IManagerDivisionListener#OnManagerDivision_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDivision_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlManager.OnManagerDivision_Finished: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.html.interfaces.IManagerDivisionListener#OnManagerDivision_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDivision_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlManager.OnManagerDivision_Error: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.html.interfaces.IManagerTableCellListener#OnManagerTableCell_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerTableCell_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this,
				"HtmlManager.OnManagerTableCell_Started: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.html.interfaces.IManagerTableCellListener#OnManagerTableCell_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerTableCell_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this,
				"HtmlManager.OnManagerTableCell_Finished: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.html.interfaces.IManagerTableCellListener#OnManagerTableCell_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerTableCell_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlManager.OnManagerTableCell_Error: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.html.interfaces.IManagerTableListener#OnManagerTable_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerTable_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlManager.OnManagerTable_Started: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.html.interfaces.IManagerTableListener#OnManagerTable_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerTable_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlManager.OnManagerTable_Finished: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.html.interfaces.IManagerTableListener#OnManagerTable_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerTable_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlManager.OnManagerTable_Error: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.html.interfaces.IManagerTableRowListener#OnManagerTableRow_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerTableRow_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlManager.OnManagerTableRow_Started: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.html.interfaces.IManagerTableRowListener#OnManagerTableRow_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerTableRow_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlManager.OnManagerTableRow_Finished: Method not overridden.");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.html.interfaces.IManagerTableRowListener#OnManagerTableRow_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerTableRow_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlManager.OnManagerTableRow_Error: Method not overridden.");
	}
	/**
	 * Adds {@link Element} message manager listener,
	 * @param listener
	 */
	public synchronized final void removeElementListener(IManagerElementListener listener)
	{
		this.elementListeners.remove(listener);
	}
	/**
	 * Adds {@link Anchor} message manager listener,
	 * @param listener
	 */
	public synchronized final void removeManagerAnchorListener(IManagerAnchorListener listener)
	{
		this.anchorListeners.remove(listener);
	}
	/**
	 * Removes {@link Page} message manager listener,
	 * @param listener
	 */
	public synchronized final void removeManagerPageListener(IManagerPageListener listener)
	{
		this.pageListeners.remove(listener);
	}
	/**
	 * Removes {@link Division} message manager listener,
	 * @param listener
	 */
	public synchronized final void removeManagerDivisionListener(IManagerDivisionListener listener)
	{
		this.divisionListeners.remove(listener);
	}
	/**
	 * Removes {@link Table} message manager listener,
	 * @param listener
	 */
	public synchronized final void removeManagerTableListener(IManagerTableListener listener)
	{
		this.tableListeners.remove(listener);
	}
	/**
	 * Removes {@link TableRow} message manager listener,
	 * @param listener
	 */
	public synchronized final void removeManagerTableRowListener(IManagerTableRowListener listener)
	{
		this.tableRowListeners.remove(listener);
	}
	/**
	 * Removes {@link TableCell} message manager listener,
	 * @param listener
	 */
	public synchronized final void removeManagerTableCellListener(IManagerTableCellListener listener)
	{
		this.tableCellListeners.remove(listener);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.OperatorManager#start()
	 */
	@Override
	public abstract void start();
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.OperatorManager#stop()
	 */
	@Override
	public abstract void stop();
	/**
	 * Initializes the {@link Html} message manager.
	 */
	private final void initHtmlManager()
	{
		addManagerPageListener(this);
		addManagerAnchorListener(this);
		addManagerElementListener(this);
		addManagerDivisionListener(this);
		addManagerTableListener(this);
		addManagerTableRowListener(this);
		addManagerTableCellListener(this);
	}
	/**
	 * Fires {@link Anchor} message manager event.
	 * @param e {@link ManagerEventArgs} the arguments
	 */
	protected final void ManagerAnchorEvent(ManagerEventArgs e)
	{
		try
		{
			for(IManagerAnchorListener listener : this.anchorListeners)
			{
				switch (e.getEventType()) 
				{
					case STARTED:
						listener.OnManagerAnchor_Started(this, e);
						break;
					case FINISHED:
						listener.OnManagerAnchor_Finished(this, e);
						break;
					case ERROR:
						listener.OnManagerAnchor_Error(this, e);
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
	 * Fires {@link Element} message manager event.
	 * @param e {@link ManagerEventArgs} the arguments
	 */
	protected final void ManagerElementEvent(ManagerEventArgs e)
	{
		try
		{
			for(IManagerElementListener listener : this.elementListeners)
			{
				switch (e.getEventType()) 
				{
					case STARTED:
						listener.OnManagerElement_Started(this, e);
						break;
					case FINISHED:
						listener.OnManagerElement_Finished(this, e);
						break;
					case ERROR:
						listener.OnManagerElement_Error(this, e);
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
	 * Fires {@link Page} message manager event.
	 * @param e {@link ManagerEventArgs} the arguments
	 */
	protected final void ManagerPageEvent(ManagerEventArgs e)
	{
		try
		{
			for(IManagerPageListener listener : this.pageListeners)
			{
				switch (e.getEventType()) 
				{
					case STARTED:
						listener.OnManagerPage_Started(this, e);
						break;
					case FINISHED:
						listener.OnManagerPage_Finished(this, e);
						break;
					case ERROR:
						listener.OnManagerPage_Error(this, e);
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
	 * Fires {@link Division} message manager event.
	 * @param e {@link ManagerEventArgs} the arguments
	 */
	protected final void ManagerDivisionEvent(ManagerEventArgs e)
	{
		try
		{
			for(IManagerDivisionListener listener : this.divisionListeners)
			{
				switch (e.getEventType()) 
				{
					case STARTED:
						listener.OnManagerDivision_Started(this, e);
						break;
					case FINISHED:
						listener.OnManagerDivision_Finished(this, e);
						break;
					case ERROR:
						listener.OnManagerDivision_Error(this, e);
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
	 * Fires {@link Table} message manager event.
	 * @param e {@link ManagerEventArgs} the arguments
	 */
	protected final void ManagerTableEvent(ManagerEventArgs e)
	{
		try
		{
			for(IManagerTableListener listener : this.tableListeners)
			{
				switch (e.getEventType()) 
				{
					case STARTED:
						listener.OnManagerTable_Started(this, e);
						break;
					case FINISHED:
						listener.OnManagerTable_Finished(this, e);
						break;
					case ERROR:
						listener.OnManagerTable_Error(this, e);
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
	 * Fires {@link TableRow} message manager event.
	 * @param e {@link ManagerEventArgs} the arguments
	 */
	protected final void ManagerTableRowEvent(ManagerEventArgs e)
	{
		try
		{
			for(IManagerTableRowListener listener : this.tableRowListeners)
			{
				switch (e.getEventType()) 
				{
					case STARTED:
						listener.OnManagerTableRow_Started(this, e);
						break;
					case FINISHED:
						listener.OnManagerTableRow_Finished(this, e);
						break;
					case ERROR:
						listener.OnManagerTableRow_Error(this, e);
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
	 * Fires {@link TableCell} message manager event.
	 * @param e {@link ManagerEventArgs} the arguments
	 */
	protected final void ManagerTableCellEvent(ManagerEventArgs e)
	{
		try
		{
			for(IManagerTableCellListener listener : this.tableCellListeners)
			{
				switch (e.getEventType()) 
				{
					case STARTED:
						listener.OnManagerTableCell_Started(this, e);
						break;
					case FINISHED:
						listener.OnManagerTableCell_Finished(this, e);
						break;
					case ERROR:
						listener.OnManagerTableCell_Error(this, e);
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
