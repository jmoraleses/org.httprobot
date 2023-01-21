/**
 * 
 */
package org.httprobot.core.managers;

import java.util.Vector;

import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.DataType;
import org.httprobot.common.datatypes.DataSource;
import org.httprobot.common.datatypes.Document;
import org.httprobot.common.datatypes.DocumentRoot;
import org.httprobot.common.datatypes.Field;
import org.httprobot.common.datatypes.FieldRoot;
import org.httprobot.common.definitions.Enums.CliNamespace;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.controls.interfaces.impl.IDataTypeControlImpl;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.datatypes.interfaces.IManagerDataSourceListener;
import org.httprobot.core.managers.datatypes.interfaces.IManagerFieldListener;
import org.httprobot.core.managers.datatypes.interfaces.IManagerFieldRootListener;
import org.httprobot.core.managers.datatypes.interfaces.IManagerDocumentListener;
import org.httprobot.core.managers.datatypes.interfaces.IManagerDocumentRootListener;
import org.httprobot.core.managers.interfaces.IDataTypeManagerImpl;

/**
 * {@link DataType} message manager abstract class. Inherits {@link Manager}.
 * It's {@link IDataTypeManagerImpl}.
 * 
 * @param <TMessage> {@link DataType} The manager data type message type.
 * @param <TControl> {@link IDataTypeControlImpl} The message control type.
 * @param <TListener> {@link IManagerImpl} the managed message listener type.
 * 
 * @author joan
 */
@XmlTransient
public abstract class DataTypeManager
	<TMessage extends DataType, TControl extends IDataTypeControlImpl, TListener extends IManagerImpl> 
		extends Manager<TMessage, TControl, TListener> 
		implements IDataTypeManagerImpl {
	
	private static final CliNamespace cliNamespace = CliNamespace.INET;
	/**
	 * -975534443063450098L
	 */
	private static final long serialVersionUID = -975534443063450098L;

	/**
	 * The {@link DataSource} message manager listeners.
	 */
	private Vector<IManagerDataSourceListener> dataSourceListeners;	
	/**
	 * The {@link Document} message manager listeners.
	 */
	private Vector<IManagerDocumentListener> documentListeners;
	/**
	 * The {@link DocumentRoot} message manager listeners.
	 */
	private Vector<IManagerDocumentRootListener> documentRootListeners;
	/**
	 * The {@link Field} message manager listeners.
	 */
	private Vector<IManagerFieldListener> fieldListeners;	
	/**
	 * The {@link FieldRoot} message manager listeners.
	 */
	private Vector<IManagerFieldRootListener> fieldRootListeners;
	/**
	 * {@link DataType} message manager default class constructor.
	 */
	public DataTypeManager()
	{
		super();
		
		initDataTypeManager();
	}
	/**
	 * {@link DataType} message manager class constructor.
	 * @param parent
	 * @param message
	 */
	public DataTypeManager(TListener parent, TMessage message)
	{
		super(parent, message);
		
		initDataTypeManager();
	}
	/**
	 * Adds {@link DataSource} message manager listener
	 * @param listener
	 */
	public synchronized final void addManagerDataSourceListener(IManagerDataSourceListener listener)
	{
		if(this.dataSourceListeners == null)
		{
			this.dataSourceListeners = new Vector<IManagerDataSourceListener>();
		}
		this.dataSourceListeners.add(listener);
	}
	/**
	 * Adds {@link Document} message manager listener
	 * @param listener
	 */
	public synchronized final void addManagerDocumentListener(IManagerDocumentListener listener)
	{
		if(this.documentListeners == null)
		{
			this.documentListeners = new Vector<IManagerDocumentListener>();
		}
		this.documentListeners.add(listener);
	}
	/**
	 * Adds {@link DocumentRoot} message manager listener
	 * @param listener
	 */
	public synchronized final void addManagerDocumentRootListener(IManagerDocumentRootListener listener)
	{
		if(this.documentRootListeners == null)
		{
			this.documentRootListeners = new Vector<IManagerDocumentRootListener>();
		}
		this.documentRootListeners.add(listener);
	}
	/**
	 * Adds {@link Field} message manager listener
	 * @param listener
	 */
	public synchronized final void addManagerFieldListener(IManagerFieldListener listener)
	{
		if(this.fieldListeners == null)
		{
			this.fieldListeners = new Vector<IManagerFieldListener>();
		}
		this.fieldListeners.add(listener);
	}
	/**
	 * Adds {@link FieldRoot} message manager listener
	 * @param listener
	 */
	public synchronized final void addManagerFieldRootListener(IManagerFieldRootListener listener)
	{
		if(this.fieldRootListeners == null)
		{
			this.fieldRootListeners = new Vector<IManagerFieldRootListener>();
		}
		this.fieldRootListeners.add(listener);
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
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerActionListener#OnManagerAction_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAction_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this,
				"DataTypeManager.OnManagerAction_Error: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerActionListener#OnManagerAction_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAction_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this,
				"DataTypeManager.OnManagerAction_Finished: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerActionListener#OnManagerAction_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAction_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this,
				"DataTypeManager.OnManagerAction_Started: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerActionListener#OnManagerAction_WebLoaded(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAction_WebLoaded(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this,
				"DataTypeManager.OnManagerAction_WebLoaded: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerDataSourceListener#OnManagerDataSource_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDataSource_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this,
				"DataTypeManager.OnManagerDataSource_Error: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerDataSourceListener#OnManagerDataSource_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDataSource_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this,
				"DataTypeManager.OnManagerDataSource_Finished: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerDataSourceListener#OnManagerDataSource_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDataSource_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {		
		
		CliTools.ThrowNotImplementedException(this,
				"DataTypeManager.OnManagerDataSource_Started: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerDocumentListener#OnManagerDocument_DocumentCompleted(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDocument_DocumentCompleted(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this,
				"DataTypeManager.OnManagerDocument_DocumentCompleted: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerDocumentListener#OnManagerDocument_DocumentInitialized(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDocument_DocumentInitialized(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {		
		
		CliTools.ThrowNotImplementedException(this,
				"DataTypeManager.OnManagerDocument_DocumentInitialized: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerDocumentListener#OnManagerDocument_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDocument_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeManager.OnManagerDocument_Error: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerDocumentListener#OnManagerDocument_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDocument_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this,
				"DataTypeManager.OnManagerDocument_Finished: Method not overridden");		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerDocumentListener#OnManagerDocument_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDocument_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this,
				"DataTypeManager.OnManagerDocument_Started: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerDocumentRootListener#OnManagerDocumentRoot_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDocumentRoot_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this,
				"DataTypeManager.OnManagerDocumentRoot_Error: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerDocumentRootListener#OnManagerDocumentRoot_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDocumentRoot_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this,
				"DataTypeManager.OnManagerDocumentRoot_Finished: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerDocumentRootListener#OnManagerDocumentRoot_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDocumentRoot_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this,
				"DataTypeManager.OnManagerDocumentRoot_Started: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerFieldListener#OnManagerField_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerField_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeManager.OnManagerField_Error: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerFieldListener#OnManagerField_FieldCompleted(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerField_FieldCompleted(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeManager.OnManagerField_FieldCompleted: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerFieldListener#OnManagerField_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerField_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this,
				"DataTypeManager.OnManagerField_Finished: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerFieldListener#OnManagerField_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerField_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this,
				"DataTypeManager.OnManagerField_Started: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerFieldsListener#OnManagerFields_DocumentCompleted(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRoot_DocumentCompleted(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeManager.OnManagerFields_DocumentCompleted: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerFieldsListener#OnManagerFields_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRoot_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeManager.OnManagerFields_Error: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerFieldsListener#OnManagerFields_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRoot_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeManager.OnManagerFields_Finished: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerFieldsListener#OnManagerFields_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRoot_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"DataTypeManager.OnManagerFields_Started: Method not overridden");
	}
	/**
	 * Removes {@link Field} message manager listener.
	 * @param listener
	 */
	public synchronized final void removeManagerFieldListener(IManagerFieldListener listener)
	{
		this.fieldListeners.remove(listener);
	}
	/**
	 * Removes {@link FieldRoot} message manager listener.
	 * @param listener
	 */
	public synchronized final void removeManagerFieldRootListener(IManagerFieldRootListener listener)
	{
		this.fieldRootListeners.remove(listener);
	}
	/**
	 * Removes {@link DataSource} message manager listener.
	 * @param listener
	 */
	public synchronized final void removeManagerDataSourceListener(IManagerDataSourceListener listener)
	{
		dataSourceListeners.remove(listener);
	}	
	/**
	 * Removes {@link Document} message manager listener.
	 * @param listener
	 */
	public synchronized final void removeManagerDocumentListener(IManagerDocumentListener listener)
	{
		documentListeners.remove(listener);
	}
	/**
	 * Removes {@link DocumentRoot} message manager listener.
	 * @param listener
	 */
	public synchronized final void removeManagerDocumentRootListener(IManagerDocumentRootListener listener)
	{
		documentRootListeners.remove(listener);
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.Manager#start()
	 */
	@Override
	public abstract void start();
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.Manager#stop()
	 */
	@Override
	public abstract void stop();
	/**
	 * Initializes {@link DataType} message manager members.
	 */
	private void initDataTypeManager() 
	{	
		addManagerDataSourceListener(this);
		addManagerDocumentListener(this);
		addManagerDocumentRootListener(this);
		addManagerFieldListener(this);
		addManagerFieldRootListener(this);
	}

	/**
	 * Fires {@link DataSource} message manager event.
	 * @param e {@link ManagerEventArgs} the arguments
	 */
	protected final void ManagerDataSourceEvent(ManagerEventArgs e)
	{
		for (IManagerDataSourceListener listener : this.dataSourceListeners) 
		{
			try 
			{
				switch (e.getEventType()) 
				{
					case STARTED:					
						listener.OnManagerDataSource_Started(this, e);					
						break;
					case FINISHED:
						listener.OnManagerDataSource_Finished(this, e);
						break;
					case ERROR:
						listener.OnManagerDataSource_Error(this, e);
						break;
					default:
						break;
				}
			}
			catch (ManagerException e1) 
			{
				e1.printStackTrace();
			} 
			catch (NotImplementedException e2) 
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Fires {@link Document} message manager event.
	 * @param e {@link ManagerEventArgs} the arguments
	 */
	protected final void ManagerDocumentEvent(ManagerEventArgs e)
	{
		for(IManagerDocumentListener listener : this.documentListeners)
		{
			try
			{
				switch (e.getEventType())
				{
					case STARTED:
						listener.OnManagerDocument_Started(this, e);
						break;
					case FINISHED:
						listener.OnManagerDocument_Finished(this, e);
						break;
					case ERROR:
						listener.OnManagerDocument_Error(this, e);
						break;
					case DOCUMENT_COMPLETED:
						listener.OnManagerDocument_DocumentCompleted(this, e);
						break;
					case DOCUMENT_INITIALIZED:
						listener.OnManagerDocument_DocumentInitialized(this, e);
						break;			
					default:
						break;
				}				
			} 
			catch (ManagerException e1) 
			{
				e1.printStackTrace();
			} 
			catch (NotImplementedException e2) 
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Fires {@link DocumentRoot} message manager event.
	 * @param e {@link ManagerEventArgs} the arguments
	 */
	protected final void ManagerDocumentRootEvent(ManagerEventArgs e)
	{
		for(IManagerDocumentRootListener listener : documentRootListeners)
		{
			try
			{
				switch (e.getEventType()) 
				{
					case STARTED:
						listener.OnManagerDocumentRoot_Started(this, e);
						break;
					case FINISHED:
						listener.OnManagerDocumentRoot_Finished(this, e);
						break;
					case ERROR:
						listener.OnManagerDocumentRoot_Error(this, e);
						break;
					default:
						break;
				}
			}
			catch (ManagerException e1)
			{
				e1.printStackTrace();
			} 
			catch (NotImplementedException e2)
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Fires {@link Field}d message manager event.
	 * @param e {@link ManagerEventArgs} the arguments
	 */
	protected final void ManagerFieldEvent(ManagerEventArgs e)
	{
		try 
		{
			for (IManagerFieldListener listener : fieldListeners) 
			{
				switch (e.getEventType()) 
				{
					case STARTED:	
						listener.OnManagerField_Started(this, e);
						break;
					case FINISHED:	
						listener.OnManagerField_Finished(this, e);
						break;
					case ERROR:	
						listener.OnManagerField_Error(this, e);
						break;

					default:
						break;
				}
			}
		} 
		catch (NotImplementedException e1) 
		{
			e1.printStackTrace();
		} 
		catch (ManagerException e1) 
		{
			e1.printStackTrace();
		}
	}
	/**
	 * Fires {@link FieldRoot} message manager event.
	 * @param e {@link ManagerEventArgs} the arguments
	 */
	protected final void ManagerFieldRootEvent(ManagerEventArgs e)
	{
		try 
		{
			for (IManagerFieldRootListener listener : fieldRootListeners) 
			{
				switch (e.getEventType()) 
				{
					case STARTED:	
						listener.OnManagerFieldRoot_Started(this, e);
						break;
					case FINISHED:	
						listener.OnManagerFieldRoot_Finished(this, e);
						break;
					case ERROR:	
						listener.OnManagerFieldRoot_Error(this, e);
						break;

					default:
						break;
				}
			}
		} 
		catch (NotImplementedException e1) 
		{
			e1.printStackTrace();
		} 
		catch (ManagerException e1) 
		{
			e1.printStackTrace();
		}
	}	
}