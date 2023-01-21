/**
 * 
 */
package org.httprobot.core.managers.placeholders;

import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.Placeholder;
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
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.PlaceholderData;
import org.httprobot.core.contents.solr.InputField;
import org.httprobot.core.controls.interfaces.impl.IPlaceholderControlImpl;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.PlaceholderManager;
import org.httprobot.core.managers.operators.ContainsManager;
import org.httprobot.core.managers.operators.EqualsManager;
import org.httprobot.core.managers.operators.RemoveManager;
import org.httprobot.core.managers.operators.ReplaceManager;
import org.httprobot.core.managers.operators.SplitManager;
import org.httprobot.core.managers.operators.SubstringManager;
import org.httprobot.core.managers.operators.TryParseManager;


/**
 * Abstract {@link Placeholder} message manager implementation class. Inherits {@link PlaceholderManager}-
 * <br>
 * @author joan
 *
 * @param <TMessage> The {@link Placeholder} message type being managed.
 * @param <TControl> The {@link IPlaceholderControlImpl} type.
 * @param <TListener> The {@link Placeholder} message listener type being listening.
 * <br>
 */
@XmlTransient
public abstract class AbstractPlaceholderManager
	<TMessage extends Placeholder, TControl extends IPlaceholderControlImpl, TListener extends IManagerImpl>
		extends PlaceholderManager<TMessage, TControl, TListener> {
	
	/**
	 * -2191770061881284261L;
	 */
	private static final long serialVersionUID = -2191770061881284261L;
	
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
	 * {@link Replace} message manager
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
	 * Abstract {@link PlaceholderManager} default class constructor.
	 */
	public AbstractPlaceholderManager() 
	{
		super();
	}
	/**
	 * Abstract {@link PlaceholderManager} class constructor.
	 * @param parent {@link IManagerImpl} the parent manager
	 * @param message {@link Placeholder} the  message to manage
	 */
	public AbstractPlaceholderManager(TListener parent, TMessage message) 
	{
		super(parent, message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.PlaceholderManager#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e)	
			throws InconsistenMessageException { 
		
		switch (e.getCommand()) {
		
		case CONTROL_CONTAINS:
			try
			{
				//Cast source
				Contains contains = Contains.class.cast(e.getMessage());
					
				//Check if message comes from child manager
				if(this.control.get(PlaceholderData.CONTAINS) != null ?
						this.control.get(PlaceholderData.CONTAINS).equals(contains) : false) {

					//Initialize manager
					this.containsManager = new ContainsManager(this, contains);
					
					//Add listeners to manager
					this.containsManager.addManagerListener(this);
					this.containsManager.addPlaceholderListener(this);						
					this.addCommandOutputListener(this.containsManager);
				
					//Store manager
					addChildManager(this.containsManager);
				}
			}
			catch(ClassCastException ex)
			{
				ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}
			
		case CONTROL_EQUALS:
			try
			{
				//Cast source
				Equals equals = Equals.class.cast(e.getMessage());

				//Check if message comes from child manager
				if(this.control.get(PlaceholderData.EQUALS) != null ?
						this.control.get(PlaceholderData.EQUALS).equals(equals) : false) {

					//Initialize manager
					this.equalsManager = new EqualsManager(this, equals);
					
					//Add listeners to manager
					this.equalsManager.addManagerListener(this);
					this.equalsManager.addPlaceholderListener(this);
					this.addCommandOutputListener(this.equalsManager);

					//Store manager
					addChildManager(this.equalsManager);
				}
			}
			catch(ClassCastException ex)
			{
				ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}
			
		case CONTROL_REMOVE:
			try
			{
				//Cast source
				Remove remove = Remove.class.cast(e.getMessage());

				//Check if message comes from child manager
				if(this.control.get(PlaceholderData.REMOVE) != null ?
						this.control.get(PlaceholderData.REMOVE).equals(remove) : false) {

					//Initialize manager
					this.removeManager = new RemoveManager(this, remove);
					
					//Add listeners to manager
					this.removeManager.addManagerListener(this);
					this.removeManager.addPlaceholderListener(this);
					this.addCommandOutputListener(this.removeManager);

					//Store manager
					addChildManager(this.removeManager);
				}
			}
			catch(ClassCastException ex)
			{
				ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}

		case CONTROL_REPLACE:
			try
			{
				//Cast source
				Replace replace = Replace.class.cast(e.getMessage());

				//Check if message comes from child manager
				if(this.control.get(PlaceholderData.REPLACE) != null ?
						this.control.get(PlaceholderData.REPLACE).equals(replace) : false) {

					//Initialize manager
					this.replaceManager = new ReplaceManager(this, replace);
					
					//Add listeners to manager
					this.replaceManager.addManagerListener(this);
					this.replaceManager.addPlaceholderListener(this);
					this.addCommandOutputListener(this.replaceManager);

					//Store manager
					addChildManager(this.replaceManager);
				}
			}
			catch(ClassCastException ex)
			{
				ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}

		case CONTROL_SPLIT:
			try
			{
				//Cast source
				Split split = Split.class.cast(e.getMessage());

				//Check if message comes from child manager
				if(this.control.get(PlaceholderData.SPLIT) != null ?
						this.control.get(PlaceholderData.SPLIT).equals(split) : false) {

					//Initialize manager
					this.splitManager = new SplitManager(this, split);
					
					//Add listeners to manager
					this.splitManager.addManagerListener(this);
					this.splitManager.addPlaceholderListener(this);
					this.addCommandOutputListener(this.splitManager);

					//Store manager
					addChildManager(this.splitManager);
				}
			}
			catch(ClassCastException ex)
			{
				ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}
		
		case CONTROL_SUBSTRING:
			try
			{
				//Cast source
				Substring substring = Substring.class.cast(e.getMessage());

				//Check if message comes from child manager
				if(this.control.get(PlaceholderData.SUBSTRING) != null ?
						this.control.get(PlaceholderData.SUBSTRING).equals(substring) : false) {

					//Initialize manager
					this.substringManager = new SubstringManager(this, substring);
					
					//Add listeners to manager
					this.substringManager.addManagerListener(this);
					this.substringManager.addPlaceholderListener(this);
					this.addCommandOutputListener(this.substringManager);

					//Store manager
					addChildManager(this.substringManager);
				}
			}
			catch(ClassCastException ex)
			{
				ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}
			
		case CONTROL_TRY_PARSE:
			try
			{
				//Cast source
				TryParse tryParse = TryParse.class.cast(e.getMessage());

				//Check if message comes from child manager
				if(this.control.get(PlaceholderData.TRY_PARSE) != null ?
						this.control.get(PlaceholderData.TRY_PARSE).equals(tryParse) : false) {

					//Initialize manager
					this.tryParseManager = new TryParseManager(this, tryParse);
					
					//Add listeners to manager
					this.tryParseManager.addManagerListener(this);
					this.tryParseManager.addPlaceholderListener(this);
					this.addCommandOutputListener(this.tryParseManager);

					//Store manager
					addChildManager(this.tryParseManager);
				}
			}
			catch(ClassCastException ex)
			{
				ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}
		default:
			return;
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.PlaceholderManager#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public abstract void OnCommandOutput(Object sender, CliEventArgs e)
			throws InconsistenMessageException ;
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.PlaceholderManager#start()
	 */
	@Override
	public abstract void start();
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.PlaceholderManager#stop()
	 */
	@Override
	public abstract void stop();
	/**
	 * @param child the child manager of current class.
	 */
	protected void putOperatorManagerData(IManagerImpl child, InputField inputField) 
	{
		/*
		 * Boolean operator types
		 * */
		if(child instanceof ContainsManager)
		{
			//Cast child manager
			ContainsManager containsManager = ContainsManager.class.cast(child);
			
			//Set current contains manager's input data to current output data.
			String containsInputKey = inputField.getValue().toString();
			
			//Put the data and initialize manager's output data value
			containsManager.put(containsInputKey, new Boolean(false));
		}
		else if(child instanceof EqualsManager)
		{
			//Cast child manager
			EqualsManager equalsManager = EqualsManager.class.cast(child);
			
			//Set current equals manager's input data to current output data.
			String equalsInputKey = inputField.getValue().toString();

			//Put the data and initialize manager's output data value
			equalsManager.put(equalsInputKey, new Boolean(false));
		}
		else if(child instanceof TryParseManager)
		{
			TryParseManager tryParseManager = TryParseManager.class.cast(child);
			
			String tryParseInputKey = inputField.getValue().toString();
			
			tryParseManager.put(tryParseInputKey, new Boolean(false));
		}
		/*
		 * String operator types
		 * */		
		else if(child instanceof RemoveManager)
		{
			//Cast child manager
			RemoveManager removeManager = RemoveManager.class.cast(child);
			
			//Set current remove manager's input data to current output data.
			String removeInputKey = inputField.getValue().toString();
			
			//Put the data and initialize manager's output data value
			removeManager.put(removeInputKey, new String());
		}
		else if(child instanceof ReplaceManager)
		{
			//Cast child manager
			ReplaceManager replaceManager = ReplaceManager.class.cast(child);
			
			//Set current replace manager's input data to current output data.
			String replaceInputKey = inputField.getValue().toString();
			
			//Put the data and initialize manager's output data value
			replaceManager.put(replaceInputKey, new String());
		}
		else if(child instanceof SubstringManager)
		{
			//Cast child manager
			SubstringManager substringManager = SubstringManager.class.cast(child);
			
			//Set current replace manager's input data to current output data.
			String substringInputKey = inputField.getValue().toString();
			
			//Put the data and initialize manager's output data value
			substringManager.put(substringInputKey, new String());
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerContainsListener#OnManagerContains_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContains_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderManager.OnManagerContains_Error: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerContainsListener#OnManagerContains_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContains_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderManager.OnManagerContains_Finished: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerContainsListener#OnManagerContains_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContains_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {

		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderManager.OnManagerContains_Started: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerEqualsListener#OnManagerEquals_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerEquals_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderManager.OnManagerEquals_Started: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerEqualsListener#OnManagerEquals_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerEquals_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {

		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderManager.OnManagerEquals_Finished: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerEqualsListener#OnManagerEquals_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerEquals_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderManager.OnManagerEquals_Started: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerRemoveListener#OnManagerRemove_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerRemove_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {

		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderManager.OnManagerRemove_Error: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerRemoveListener#OnManagerRemove_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerRemove_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {

		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderManager.OnManagerRemove_Finished: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerRemoveListener#OnManagerRemove_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerRemove_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {

		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderManager.OnManagerRemove_Started: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerReplaceListener#OnManagerReplace_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerReplace_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {

		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderManager.OnManagerReplace_Error: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerReplaceListener#OnManagerReplace_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerReplace_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {

		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderManager.OnManagerReplace_Finished: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerReplaceListener#OnManagerReplace_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerReplace_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderManager.OnManagerReplace_Started: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerSplitListener#OnManagerSplit_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSplit_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {

		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderManager.OnManagerSplit_Error: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerSplitListener#OnManagerSplit_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSplit_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {

		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderManager.OnManagerSplit_Error: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerSplitListener#OnManagerSplit_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSplit_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderManager.OnManagerSplit_Started: Method not overridden");
	}
	@Override
	public void OnManagerSubstring_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {

		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderManager.OnManagerSubstring_Error: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerSubstringListener#OnManagerSubstring_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSubstring_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {

		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderManager.OnManagerSubstring_Finished: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerSubstringListener#OnManagerSubstring_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSubstring_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {

		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderManager.OnManagerSubstring_Started: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerTryParseListener#OnManagerTryParse_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerTryParse_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {

		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderManager.OnManagerTryParse_Error: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerTryParseListener#OnManagerTryParse_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerTryParse_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {

		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderManager.OnManagerTryParse_Error: Method not overridden");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.interfaces.IManagerTryParseListener#OnManagerTryParse_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerTryParse_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {

		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderManager.OnManagerTryParse_Started: Method not overridden");
	}
}
