/**
 * 
 */
package org.httprobot.core.managers;

import java.util.Vector;

import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.Content;
import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.controls.interfaces.impl.IContentControlImpl;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.contents.interfaces.IManagerContentTypeListener;
import org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRefListener;
import org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRootListener;
import org.httprobot.core.managers.contents.interfaces.IManagerDataViewListener;
import org.httprobot.core.managers.contents.interfaces.IManagerFieldRefListener;
import org.httprobot.core.managers.interfaces.IContentManagerImpl;

/**
 * Abstract content message manager class. Inherits {@link Manager}.
 * <br>
 * It's IContentManagerImpl.
 * <br>
 * @author joan
 */
@XmlTransient
public abstract class ContentManager
	<TMessage extends Content, TControl extends IContentControlImpl, TListener extends IManagerImpl> 
		extends Manager<TMessage, TControl, TListener> 
		implements IContentManagerImpl {

	/**
	 * -7103933387442462400L
	 */
	private static final long serialVersionUID = -7103933387442462400L;
	
	private Vector<IManagerContentTypeListener> contentTypeListeners;
	private Vector<IManagerContentTypeRefListener> contentTypeRefListeners;
	private Vector<IManagerContentTypeRootListener> contentTypeRootListeners;
	private Vector<IManagerDataViewListener> dataViewListeners;
	private Vector<IManagerFieldRefListener> fieldRefListeners;
	
	/**
	 * 
	 */
	public ContentManager()
	{
		super();
		initContentManager();
	}
	/**
	 * 
	 * @param parent
	 * @param message
	 */
	public ContentManager(TListener parent, TMessage message)
	{
		super(parent, message);	
				
		//Initialize manager
		initContentManager();
	}
	

	/**
	 * Adds {@link ContentType} message manager listener
	 * @param listener
	 */
	public synchronized final void addManagerContentTypeListener(IManagerContentTypeListener listener)
	{
		if(this.contentTypeListeners == null)
		{
			this.contentTypeListeners = new Vector<IManagerContentTypeListener>();
		}
		this.contentTypeListeners.add(listener);
	}
	/**
	 * Adds {@link ContentTypeRef} message manager listener
	 * @param listener
	 */
	public synchronized final void addManagerContentTypeRefListener(IManagerContentTypeRefListener listener)
	{
		if(this.contentTypeRefListeners == null)
		{
			this.contentTypeRefListeners = new Vector<IManagerContentTypeRefListener>();
		}
		this.contentTypeRefListeners.add(listener);
	}
	/**
	 * Adds {@link ContentTypeRoot} message manager listener
	 * @param listener
	 */
	public synchronized final void addManagerContentTypeRootListener(IManagerContentTypeRootListener listener)
	{
		if(this.contentTypeRootListeners == null)
		{
			this.contentTypeRootListeners = new Vector<IManagerContentTypeRootListener>();
		}
		this.contentTypeRootListeners.add(listener);
	}
	/**
	 * Adds {@link DataView} message manager listener
	 * @param listener
	 */
	public synchronized final void addManagerDataViewListener(IManagerDataViewListener listener)
	{
		if(this.dataViewListeners == null)
		{
			this.dataViewListeners = new Vector<IManagerDataViewListener>();
		}
		this.dataViewListeners.add(listener);
	}
	/**
	 * Adds {@link FieldRef} message manager listener
	 * @param listener
	 */
	public synchronized final void addManagerFieldRefListener(IManagerFieldRefListener listener)
	{
		if(this.fieldRefListeners == null)
		{
			this.fieldRefListeners = new Vector<IManagerFieldRefListener>();
		}
		this.fieldRefListeners.add(listener);
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
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerContentTypeListener#OnManagerContentType_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentType_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentManager.OnManagerContentType_Finished: Method not overridden");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerContentTypeListener#OnManagerContentType_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentType_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentManager.OnManagerContentType_Started: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRefListener#OnManagerContentTypeRef_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentTypeRef_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentManager.OnManagerContentType_Error: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRefListener#OnManagerContentTypeRef_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentTypeRef_Finished(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentManager.OnManagerContentTypeRef_Finished: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRefListener#OnManagerContentTypeRef_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentTypeRef_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentManager.OnManagerContentTypeRef_Started: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRootListener#OnManagerContentTypeRoot_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentTypeRoot_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentManager.OnManagerContentTypeRef_Error: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRootListener#OnManagerContentTypeRoot_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentTypeRoot_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentManager.OnManagerContentTypes_Finished: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRootListener#OnManagerContentTypeRoot_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentTypeRoot_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentManager.OnManagerContentTypes_Started: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerContentTypeListener#OnManagerContentType_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContentType_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentManager.OnManagerContentType_Error: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerDataViewListener#OnManagerDataView_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDataView_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentManager.OnManagerDataView_Error: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerDataViewListener#OnManagerDataView_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDataView_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentManager.OnManagerDataView_Finished: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerDataViewListener#OnManagerDataView_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDataView_Started(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentManager.OnManagerDataView_Started: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerFieldRefListener#OnManagerFieldRef_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRef_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentManager.OnManagerFieldRef_Finished: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerFieldRefListener#OnManagerFieldRef_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRef_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentManager.OnManagerFieldRef_Finished: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.contents.interfaces.IManagerFieldRefListener#OnManagerFieldRef_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRef_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"ContentManager.OnManagerFieldRef_Started: Method not overridden");
	}
	/**
	 * Adds {@link ContentType} message manager listener
	 * @param listener
	 */
	public synchronized final void removeManagerContentTypeListener(IManagerContentTypeListener listener)
	{
		this.contentTypeListeners.remove(listener);
	}
	/**
	 * Adds {@link ContentTypeRef} message manager listener
	 * @param listener
	 */
	public synchronized final void removeManagerContentTypeRefListener(IManagerContentTypeRefListener listener)
	{
		this.contentTypeRefListeners.remove(listener);
	}
	/**
	 * Adds {@link ContentTypeRoot} message manager listener
	 * @param listener
	 */
	public synchronized final void removeManagerContentTypeRootListener(IManagerContentTypeRootListener listener)
	{
		this.contentTypeRootListeners.remove(listener);
	}
	/**
	 * Adds {@link DataView} message manager listener
	 * @param listener
	 */
	public synchronized final void removeManagerDataViewListener(IManagerDataViewListener listener)
	{
		this.dataViewListeners.remove(listener);
	}
	/**
	 * Adds {@link FieldRef} message manager listener
	 * @param listener
	 */
	public synchronized final void removeManagerFieldRefListener(IManagerFieldRefListener listener)
	{
		this.fieldRefListeners.remove(listener);
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
	 * Initializes content message manager.
	 */
	private void initContentManager() 
	{
		addManagerDataViewListener(this);
		addManagerFieldRefListener(this);
		addManagerContentTypeListener(this);
		addManagerContentTypeRootListener(this);
		addManagerContentTypeRefListener(this);
	}
	/**
	 * Fires DataView message manager event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ManagerDataViewEvent(ManagerEventArgs e)
	{
		try 
		{
			for (IManagerDataViewListener listener : dataViewListeners) 
			{
				switch (e.getEventType()) 
				{
					case STARTED:	
						listener.OnManagerDataView_Started(this, e);	
						break;
						
					case FINISHED:						
						listener.OnManagerDataView_Finished(this, e);	
						break;
						
					case ERROR:						
						listener.OnManagerDataView_Error(this, e);	
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
	 * Fires FieldRef message manager event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ManagerFieldRefEvent(ManagerEventArgs e)
	{
		try
		{
			for (IManagerFieldRefListener listener : fieldRefListeners) 
			{
				switch (e.getEventType()) 
				{
					case STARTED:	
						listener.OnManagerFieldRef_Started(this, e);	
						break;
						
					case FINISHED:						
						listener.OnManagerFieldRef_Finished(this, e);	
						break;
						
					case ERROR:						
						listener.OnManagerFieldRef_Error(this, e);	
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
	 * Fires ContentTypeRef message manager event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ManagerContentTypeRefEvent(ManagerEventArgs e)
	{
		try
		{
			for (IManagerContentTypeRefListener listener : contentTypeRefListeners) 
			{
				switch (e.getEventType()) 
				{
					case STARTED:	
						listener.OnManagerContentTypeRef_Started(this, e);	
						break;
						
					case FINISHED:						
						listener.OnManagerContentTypeRef_Finished(this, e);	
						break;
						
					case ERROR:						
						listener.OnManagerContentTypeRef_Error(this, e);	
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
	 * Fires ContentType message manager event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ManagerContentTypeEvent(ManagerEventArgs e)
	{
		try
		{
			for (IManagerContentTypeListener listener : contentTypeListeners) 
			{
				switch (e.getEventType()) 
				{
					case STARTED:	
						listener.OnManagerContentType_Started(this, e);	
						break;
						
					case FINISHED:						
						listener.OnManagerContentType_Finished(this, e);	
						break;
						
					case ERROR:						
						listener.OnManagerContentType_Error(this, e);	
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
	 * Fires ContentTypes message manager event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ManagerContentTypeRootEvent(ManagerEventArgs e)
	{
		try
		{
			for (IManagerContentTypeRootListener listener : contentTypeRootListeners) 
			{
				switch (e.getEventType()) 
				{
					case STARTED:	
						listener.OnManagerContentTypeRoot_Started(this, e);	
						break;
						
					case FINISHED:						
						listener.OnManagerContentTypeRoot_Finished(this, e);	
						break;
						
					case ERROR:						
						listener.OnManagerContentTypeRoot_Error(this, e);	
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
