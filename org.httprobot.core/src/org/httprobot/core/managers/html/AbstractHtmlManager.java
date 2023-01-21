/**
 * 
 */
package org.httprobot.core.managers.html;

import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.core.managers.HtmlManager;
import org.httprobot.core.managers.interfaces.IHtmlManagerImpl;
import org.httprobot.core.managers.operators.ContainsManager;
import org.httprobot.core.managers.operators.EqualsManager;
import org.httprobot.core.managers.operators.RemoveManager;
import org.httprobot.core.managers.operators.ReplaceManager;
import org.httprobot.core.managers.operators.SplitManager;
import org.httprobot.core.managers.operators.SubstringManager;
import org.httprobot.core.managers.operators.TryParseManager;

import org.httprobot.common.Html;
import org.httprobot.common.definitions.Enums.ManagerErrorCode;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.operators.Contains;
import org.httprobot.common.placeholders.operators.Equals;
import org.httprobot.common.placeholders.operators.Remove;
import org.httprobot.common.placeholders.operators.Replace;
import org.httprobot.common.placeholders.operators.Split;
import org.httprobot.common.placeholders.operators.Substring;
import org.httprobot.common.placeholders.operators.TryParse;
import org.httprobot.core.common.Enums.HtmlData;
import org.httprobot.core.controls.interfaces.impl.IHtmlControlImpl;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;

/**
 * Abstract {@link Html} message manager implementation class. 
 * Inherits {@link HtmlManager}. It's {@link IHtmlManagerImpl}.
 * <br>
 * @author joan
 *
 */
@XmlTransient
public abstract class AbstractHtmlManager
	<TMessage extends Html, TControl extends IHtmlControlImpl, TListener extends IManagerImpl>
		extends HtmlManager<TMessage, TControl, TListener> 
		implements IHtmlManagerImpl {

	/**
	 * -7609043264593345723L
	 */
	private static final long serialVersionUID = -7609043264593345723L;
	
	/**
	 * {@link Contains} message manager.
	 */
	protected ContainsManager containsManager;
	/**
	 * {@link Equals} message manager.
	 */
	protected EqualsManager equalsManager;
	/**
	 * {@link Remove} message manager.
	 */
	protected RemoveManager removeManager;
	/**
	 * {@link Replace} message manager.
	 */
	protected ReplaceManager replaceManager;
	/**
	 * {@link Split} message manager.
	 */
	protected SplitManager splitManager;
	/**
	 * {@link Substring} message manager.
	 */
	protected SubstringManager substringManager;
	/**
	 * {@link TryParse} message manager.
	 */
	protected TryParseManager tryParseManager;

	/**
	 * Abstract HTML message manager default class constructor.
	 */
	public AbstractHtmlManager() 
	{
		super();
	}
	/**
	 * Abstract HTML message manager class constructor.
	 * @param parent the parent
	 * @param message the message
	 */
	public AbstractHtmlManager(TListener parent, TMessage message) 
	{
		super(parent, message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.HtmlManager#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
		
		switch (e.getCommand()) 
		{
		
		case CONTROL_CONTAINS:
			try 
			{
				// Cast source
				Contains contains = Contains.class.cast(e.getMessage());

				if (this.control.get(HtmlData.CONTAINS) != null ? 
						this.control.get(HtmlData.CONTAINS).equals(contains) : false) {

					// Initialize manager
					this.containsManager = new ContainsManager(this, contains);

					// Add listeners to manager
					addCommandOutputListener(this.containsManager);
					this.containsManager.addManagerListener(this);
					this.containsManager.addOperatorListener(this);

					// Store manager
					addChildManager(this.containsManager);
				}
			}
			catch (ClassCastException ex) 
			{
				ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}
			return;

		case CONTROL_EQUALS:
			try 
			{
				// Cast source
				Equals equals = Equals.class.cast(e.getMessage());

				if (this.control.get(HtmlData.EQUALS) != null ?
						this.control.get(HtmlData.EQUALS).equals(equals) : false) {
					
					// Initialize manager
					this.equalsManager = new EqualsManager(this, equals);

					// Add listeners to manager
					addCommandOutputListener(this.equalsManager);
					this.equalsManager.addManagerListener(this);
					this.equalsManager.addOperatorListener(this);

					// Store manager
					addChildManager(this.equalsManager);
				}
			}
			catch (ClassCastException ex) 
			{
				ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}
			return;

		case CONTROL_REMOVE:
			try
			{
				// Cast source
				Remove remove = Remove.class.cast(e.getMessage());

				if (this.control.get(HtmlData.REMOVE) != null ?
						this.control.get(HtmlData.REMOVE).equals(remove) : false) {
					
					// Initialize manager
					this.removeManager = new RemoveManager(this, remove);

					// Add listeners to manager
					addCommandOutputListener(this.removeManager);
					this.removeManager.addManagerListener(this);
					this.removeManager.addOperatorListener(this);

					// Store manager
					addChildManager(this.removeManager);
				}
			} 
			catch (ClassCastException ex) 
			{
				ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}
			return;

		case CONTROL_REPLACE:
			try 
			{
				// Cast source
				Replace replace = Replace.class.cast(e.getMessage());

				if (this.control.get(HtmlData.REPLACE) != null ?
						this.control.get(HtmlData.REPLACE).equals(replace) : false) {
					
					// Initialize manager
					this.replaceManager = new ReplaceManager(this, replace);

					// Add listeners to manager
					addCommandOutputListener(this.replaceManager);
					this.replaceManager.addManagerListener(this);
					this.replaceManager.addOperatorListener(this);

					// Store manager
					addChildManager(this.replaceManager);
				}
			} 
			catch (ClassCastException ex) 
			{
				ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}
			return;

		case CONTROL_SPLIT:
			try
			{
				// Cast source
				Split split = Split.class.cast(e.getMessage());

				if (this.control.get(HtmlData.SPLIT) != null ? 
						this.control.get(HtmlData.SPLIT).equals(split) : false) {
					
					this.splitManager = new SplitManager(this, split);

					// Add listeners to manager
					addCommandOutputListener(this.splitManager);
					this.splitManager.addManagerListener(this);
					this.splitManager.addOperatorListener(this);

					// Store manager
					addChildManager(this.splitManager);
				}
			} 
			catch (ClassCastException ex) 
			{
				ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}
			return;

		case CONTROL_SUBSTRING:
			try
			{
				// Cast source
				Substring substring = Substring.class.cast(e.getMessage());

				if (this.control.get(HtmlData.SUBSTRING) != null ? 
						this.control.get(HtmlData.SUBSTRING).equals(substring) : false) {
					
					this.substringManager = new SubstringManager(this, substring);

					// Add listeners to manager
					addCommandOutputListener(this.substringManager);
					this.substringManager.addManagerListener(this);
					this.substringManager.addOperatorListener(this);

					// Store manager
					addChildManager(this.substringManager);
				}
			} 
			catch (ClassCastException ex)
			{
				ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}
			return;

		case CONTROL_TRY_PARSE:
			try 
			{
				// Cast source
				TryParse tryParse = TryParse.class.cast(e.getMessage());

				if (this.control.get(HtmlData.SUBSTRING) != null ? 
						this.control.get(HtmlData.SUBSTRING).equals(tryParse) : false) {
					
					this.tryParseManager = new TryParseManager(this, tryParse);

					// Add listeners to manager
					addCommandOutputListener(this.tryParseManager);
					this.tryParseManager.addManagerListener(this);
					this.tryParseManager.addOperatorListener(this);

					// Store manager
					addChildManager(this.tryParseManager);
				}
			}
			catch (ClassCastException ex) 
			{
				ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}
			return;

		default:
			return;
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.HtmlManager#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlOperatorManager#OnManagerContains_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContains_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//If sender is a child manager repeat event
		if(this.getChildManagers().contains(e.getSource()))
		{
			ManagerContainsEvent(e);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlOperatorManager#OnManagerContains_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContains_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//If sender is a child manager repeat event
		if(this.getChildManagers().contains(e.getSource()))
		{
			ManagerContainsEvent(e);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlOperatorManager#OnManagerContains_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContains_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//If sender is a child manager repeat event
		if(this.getChildManagers().contains(e.getSource()))
		{
			ManagerContainsEvent(e);
		}
		else if(sender.equals(this))
		{
			this.reset();
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlOperatorManager#OnManagerEquals_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerEquals_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//If sender is a child manager repeat event
		if(this.getChildManagers().contains(e.getSource()))
		{
			ManagerEqualsEvent(e);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlOperatorManager#OnManagerEquals_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerEquals_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//If sender is a child manager repeat event
		if(this.getChildManagers().contains(e.getSource()))
		{
			ManagerEqualsEvent(e);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlOperatorManager#OnManagerEquals_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerEquals_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//If sender is a child manager repeat event
		if(this.getChildManagers().contains(e.getSource()))
		{
			ManagerEqualsEvent(e);
		}
		else if(e.getSource().equals(this))
		{
			this.reset();
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlOperatorManager#OnManagerRemove_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerRemove_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//If sender is a child manager repeat event
		if(this.getChildManagers().contains(e.getSource()))
		{
			ManagerRemoveEvent(e);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlOperatorManager#OnManagerRemove_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerRemove_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//If sender is a child manager repeat event
		if(this.getChildManagers().contains(e.getSource()))
		{
			ManagerRemoveEvent(e);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlOperatorManager#OnManagerRemove_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerRemove_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//If sender is a child manager repeat event
		if(this.getChildManagers().contains(e.getSource()))
		{
			ManagerRemoveEvent(e);
		}
		else if(e.getSource().equals(this))
		{
			this.reset();
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlOperatorManager#OnManagerReplace_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerReplace_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//If sender is a child manager repeat event
		if(this.getChildManagers().contains(e.getSource()))
		{
			ManagerReplaceEvent(e);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlOperatorManager#OnManagerReplace_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerReplace_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//If sender is a child manager repeat event
		if(this.getChildManagers().contains(e.getSource()))
		{
			ManagerReplaceEvent(e);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlOperatorManager#OnManagerReplace_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerReplace_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//If sender is a child manager repeat event
		if(this.getChildManagers().contains(e.getSource()))
		{
			ManagerReplaceEvent(e);
		}
		else if(sender.equals(this))
		{
			this.reset();
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlOperatorManager#OnManagerSplit_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSplit_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//If sender is a child manager repeat event
		if(this.getChildManagers().contains(e.getSource()))
		{
			ManagerSplitEvent(e);
		}			
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlOperatorManager#OnManagerSplit_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSplit_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//If sender is a child manager repeat event
		if(this.getChildManagers().contains(e.getSource()))
		{
			ManagerSplitEvent(e);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlOperatorManager#OnManagerSplit_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSplit_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//If sender is a child manager repeat event
		if(this.getChildManagers().contains(e.getSource()))
		{
			ManagerSplitEvent(e);
		}
		else if(sender.equals(this))
		{
			this.reset();
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlOperatorManager#OnManagerSubstring_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSubstring_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//If sender is a child manager repeat event
		if(this.getChildManagers().contains(e.getSource()))
		{
			ManagerSubstringEvent(e);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlOperatorManager#OnManagerSubstring_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSubstring_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//If sender is a child manager repeat event
		if(this.getChildManagers().contains(e.getSource()))
		{
			ManagerSubstringEvent(e);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlOperatorManager#OnManagerSubstring_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSubstring_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//If sender is a child manager repeat event
		if(this.getChildManagers().contains(e.getSource()))
		{
			ManagerSubstringEvent(e);
		}
		else if(e.getSource().equals(this))
		{
			this.reset();
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlOperatorManager#OnManagerTryParse_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerTryParse_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//If sender is a child manager repeat event
		if(this.getChildManagers().contains(e.getSource()))
		{
			ManagerTryParseEvent(e);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlOperatorManager#OnManagerTryParse_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerTryParse_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//If sender is a child manager repeat event
		if(this.getChildManagers().contains(e.getSource()))
		{
			ManagerTryParseEvent(e);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlOperatorManager#OnManagerTryParse_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerTryParse_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//If sender is a child manager repeat event
		if(this.getChildManagers().contains(e.getSource()))
		{
			ManagerTryParseEvent(e);
		}
		else if(e.getSource().equals(this))
		{
			this.reset();
		}
	}
}