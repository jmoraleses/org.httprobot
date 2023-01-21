package org.httprobot.core.managers;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Vector;

import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.RML;
import org.httprobot.common.Parameter;
import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.common.definitions.Enums.ManagerErrorCode;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.core.contents.DocumentLibrary;
import org.httprobot.core.contents.TemplateLibrary;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;

/**
 * Message manager abstract class. Inherits {@link org.httprobot.common.RML}.
 * <br>
 * It's {@link IManagerImpl} and {@link IControlListener}.
 * <br>
 * @param <TMessage> {@link RML} the managed message type.
 * @param <TControl> {@link IManagerImpl} the managed message listener type.
 * @param <TListener> {@link IManagerImpl} the managed message listener type.
 * <br>
 * @author joan
 */
@XmlTransient
public abstract class Manager
	<TMessage extends RML, TControl extends IControlListener, TListener extends IManagerImpl> 
		extends RML 
		implements IManagerImpl, IControlListener {
	
	/**
	 * 8643531417130146029L
	 */
	private static final long serialVersionUID = 8643531417130146029L;
	/**
	 * The current child manager index.
	 */
	private int currentManager;
	/**
	 * The child managers.
	 */
	private List<IManagerImpl> childManagers;
	/**
	 * The manager listeners.
	 */
	private Vector<IManagerImpl> managerListeners;
	/**
	 * The parent listener.
	 */
	private TListener parent;
	/**
	 * The root content type.
	 */
	protected ContentTypeRoot contentTypeRoot;
	/**
	 * THe document library
	 */
	protected DocumentLibrary documentLibrary;
	/**
	 * The template library.
	 */
	protected TemplateLibrary templateLibrary;
	/**
	 * The managed message.
	 */
	protected TControl control;
	/**
	 * The banned words dictionary.
	 */
	protected Map<String, String> parameterBannedWords;
	/**
	 * The parameter dictionary.
	 */
	protected Map<String, String> parameterConstants;

	/**
	 * The {@link RML} managed message.
	 */
	protected TMessage message;
	/**
	 * {@link RML} message manager default class constructor.
	 */
	public Manager()
	{
		super();
		initManager(null, null);
	}
	/**
	 * {@link RML} message manager class constructor.
	 * @param parent the manager listener.
	 * @param message the {@link RML} message.
	 */
	public Manager(TListener parent, TMessage message)
	{
		super();
		
		//Initialize manager
		initManager(parent, message);
	}

	/**
	 * Adds manager to current manager.
	 * @param manager the child manager
	 */
	public void addChildManager(IManagerImpl manager)
	{
		//Initialize array if null
		if(this.childManagers == null)
		{
			this.childManagers = new ArrayList<IManagerImpl>();
		}
		//Add manager
		this.childManagers.add(manager);
	}	
	/**
	 * Adds manager listener.
	 * @param listener {@link IManagerImpl} the listener
	 */
	public synchronized final void addManagerListener(IManagerImpl listener)
	{
		if(this.managerListeners == null)
			this.managerListeners = new Vector<IManagerImpl>();
		this.managerListeners.add(listener);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#getRootContentType()
	 */
	@Override
	public ContentTypeRoot getContentTypeRoot() 
			throws NotImplementedException, ManagerException {
		
		if(this.contentTypeRoot != null)
		{
			return this.contentTypeRoot;
		}
		else
		{
			return parent.getContentTypeRoot();	
		}
	}
	/**
	 * @return the list of child managers, or null if none.
	 */
	public List<IManagerImpl> getChildManagers()
	{
		return this.childManagers;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#getDocumentLibrary()
	 */
	@Override
	public DocumentLibrary getDocumentLibrary() 
			throws NotImplementedException, ManagerException {
		
		if(this.documentLibrary == null)
		{
			return parent.getDocumentLibrary();
		}
		else
		{
			return this.documentLibrary;
		}
	}
	/**
	 * Gets the message it's being managed.
	 * @return {@link org.httprobot.common.RML} the message
	 */
	public TControl getControl()
	{
		return this.control;
	}
	
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#getBannedDictionary()
	 */
	@Override
	public final Map<String, String> getParameterBannedWords() 
			throws NotImplementedException, ManagerException {
		
		if(!this.parameterBannedWords.isEmpty())
		{
			return this.parameterBannedWords;
		}
		else 
		{
			return parent.getParameterBannedWords();
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#getParamDictionary()
	 */
	@Override
	public final Map<String, String> getParameterConstants() 
			throws NotImplementedException, ManagerException {
		
		if(!this.parameterConstants.isEmpty())
		{
			return this.parameterConstants;	
		}
		else
		{
			return parent.getParameterConstants();
		}
	}
	/**
	 * Gets the parent Object inheriting {@link org.httprobot.common.interfaces.IListener}.
	 * @return {@link org.httprobot.common.interfaces.IListener}.
	 */
	@XmlTransient
	public TListener getParent() 
	{
		return this.parent;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.io.CommandLine#getRuntimeOptions()
	 */
	@Override
	public EnumSet<RuntimeOptions> getRuntimeOptions() 
	{
		if(super.getRuntimeOptions() != null)
		{
			return super.getRuntimeOptions();
		}
		else
		{
			return parent.getRuntimeOptions();
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#getInputDocumentHandler()
	 */
	@Override
	public final TemplateLibrary getTemplateLibrary() 
			throws NotImplementedException, ManagerException {
		
		if(this.templateLibrary == null)
		{
			return parent.getTemplateLibrary();
		}
		else
		{
			return this.templateLibrary;
		}
	}
	/**
	 * @return true if child managers list not null or empty.
	 */
	public boolean hasChildManagers()
	{
		boolean isEmpty = (this.childManagers == null || this.childManagers.isEmpty());
		return !isEmpty;
	}
	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return currentManager < this.childManagers.size();
	}
	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public IManagerImpl next() 
	{		
		if (! hasNext())   
			throw new NoSuchElementException();
        
		return this.childManagers.get(currentManager++);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.io.MarshalLayer#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public abstract void OnCommandInput(Object sender, CliEventArgs e) 
			throws InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.common.RML#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public abstract void OnCommandOutput(Object sender, CliEventArgs e) 
			throws InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.interfaces.IManagerListener#OnManager_BannedAdded(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public final void OnManager_BannedAdded(Object sender, ManagerEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		try
		{
			Parameter param = Parameter.class.cast(e.getSource());
			
			switch (param.getParamType()) 
			{
				case BANNED_WORD:
					//Example: <bannedWord paramtType="banned-word" paramName="actionLink" value="/news" />
					this.parameterBannedWords.put(param.getParamName(), param.getValue());
					break;
				default:
					break;
			}
		}
		catch(ClassCastException ex)
		{
			ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IServerInfoManagerListener#OnParamAdded(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public final void OnManager_ParamAdded(Object sender, ManagerEventArgs e) 
			throws InconsistenMessageException {
		
		try
		{
			Parameter param = Parameter.class.cast(e.getSource());
			
			switch (param.getParamType()) 
			{
				case CONSTANT:
					
					if(this.parameterConstants.isEmpty() && getParameterConstants() != null)
					{
						this.parameterConstants = getParameterConstants();
					}		
					//Example: <sartUrl paramtType="href-constant" paramName="[@start_url]" value="http://blabalbal" />
					this.parameterConstants.put(param.getParamName(), param.getValue());	
					break;
					
				default:
					break;
			}
		}
		catch(ClassCastException ex1)
		{
			ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		}
		catch (NotImplementedException ex2) 
		{
			ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.NOT_IMPLEMENTED));
		} 
		catch (ManagerException ex3)
		{
			ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove()
	{
		 //// if (! hasNext())   throw new NoSuchElementException();
        //// if (currrent + 1 < myArray.end) {
        ////     System.arraycopy(myArray.arr, current+1, myArray.arr, current, myArray.end - current-1);
        //// }
        //// myArray.end--;
		if(!hasNext())
			throw new NoSuchElementException();
		if(this.currentManager + 1 < this.childManagers.size())
		{
			System.arraycopy(this.childManagers, this.currentManager + 1, this.childManagers, this.currentManager, this.currentManager - 1);
		}
		this.currentManager--;
	}
	/**
	 * @param listener
	 */
	public synchronized final void removeManagerListener(IManagerImpl listener)
	{
		this.managerListeners.remove(listener);
	}
	public final void reset()
	{
		this.currentManager = 0;
	}
	/**
	 * Starts manager.
	 */
	public abstract void start();
	/**
	 * Stops manager.
	 */
	public abstract void stop();
	/**
	 * Initializes manager
	 */
	private void initManager(TListener parent, TMessage message) 
	{
		//Set inherited data
		this.parent = parent;
		this.message = message;
		this.setUuid(message.getUuid());
		this.setInherited(message.getInherited());
		this.setDestinationPath(message.getDestinationPath());
		this.setRuntimeOptions(message.getRuntimeOptions());
		
		//Initialize members and member's lists.
		this.currentManager = 0;		
		this.parameterConstants = new LinkedHashMap<String, String>();
		this.parameterBannedWords = new LinkedHashMap<String, String>();
		this.childManagers = new ArrayList<IManagerImpl>();
	
		if(message != null)
		{
			//Output command direction
			this.addCommandOutputListener(message);	
		}		
		
		if(parent != null)
		{
			//Input command if parent not null
			addCommandInputListener(parent);
		}
	}

	/**
	 * Fires {@link IControlListener} InetManager event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ManagerEvent(ManagerEventArgs e)
	{
		for (IManagerImpl listener : this.managerListeners) 
		{
			try 
			{
				switch (e.getEventType()) 
				{
					case PARAM_ADDED:
						listener.OnManager_ParamAdded(this, e);
						break;
					case BANNED_ADDED:
						listener.OnManager_BannedAdded(this, e);
						break;
					default:
						break;
				}
			}
			catch (InconsistenMessageException ex1) 
			{
				ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.BAD_MESSAGE));
			} 
			catch (NotImplementedException ex2) 
			{
				ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.NOT_IMPLEMENTED));
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IControlListener#getMessage()
	 */
	@Override
	public TMessage getMessage() 
	{
		return this.message;
	}
}