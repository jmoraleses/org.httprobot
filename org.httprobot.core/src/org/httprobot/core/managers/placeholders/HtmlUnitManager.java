 /**
 * 
 */
package org.httprobot.core.managers.placeholders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.FieldRef;
import org.httprobot.common.definitions.Enums.ManagerErrorCode;
import org.httprobot.common.definitions.Enums.ManagerEventType;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.HtmlUnit;
import org.httprobot.common.placeholders.operators.html.Page;
import org.httprobot.core.common.Enums.PlaceholderData;
import org.httprobot.core.contents.solr.InputField;
import org.httprobot.core.controls.placeholders.HtmlUnitControl;
import org.httprobot.core.controls.placeholders.interfaces.IControlHtmlUnitListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IDataMappingImpl;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.contents.FieldRefManager;
import org.httprobot.core.managers.html.PageManager;
import org.httprobot.core.managers.html.interfaces.IManagerPageListener;
import org.httprobot.core.managers.operators.ContainsManager;
import org.httprobot.core.managers.operators.EqualsManager;
import org.httprobot.core.managers.operators.RemoveManager;
import org.httprobot.core.managers.operators.ReplaceManager;
import org.httprobot.core.managers.operators.SplitManager;
import org.httprobot.core.managers.operators.SubstringManager;
import org.httprobot.core.managers.operators.TryParseManager;
import org.httprobot.core.managers.placeholders.interfaces.IManagerHtmlUnitListener;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * {@link HtmlUnit} message manager class. Inherits {@link AbstractPlaceholderManager}.
 * <br>
 * It's {@link IControlHtmlUnitListener} and {@link IDataMappingImpl}.
 * <br>
 * @author joan
 *
 */
@XmlRootElement
public class HtmlUnitManager 
		extends AbstractPlaceholderManager
			<HtmlUnit, HtmlUnitControl, IManagerHtmlUnitListener> 
		implements IControlHtmlUnitListener, IManagerPageListener, 
			IDataMappingImpl<InputField, HtmlPage> {
	
	/**
	 * 3678413651888920404L
	 */
	private static final long serialVersionUID = 3678413651888920404L;
	/**
	 * The {@link FieldRef} message manager.
	 */
	protected FieldRefManager fieldRefManager;
	/**
	 * {@link Page} message manager.
	 */
	protected PageManager pageManager;
	/**
	 * The current fields found.
	 */
	InputField currentInput;
	/**
	 * The current pages to treat.
	 */
	HtmlPage currentOutput;
	/**
	 * The input data.
	 */
	Set<InputField> inputData;
	/**
	 * The output data.
	 */
	Map<InputField, HtmlPage> outputData;
	
	/**
	 * {@link HtmlUnit} message manager default class constructor.
	 */
	public HtmlUnitManager() 
	{
		super();
	}
	/**
	 * {@link HtmlUnit} message manager class constructor.
	 * @param parent {@link IManagerHtmlUnitListener} the parent
	 * @param htmlUnit {@link HtmlUnit} the message
	 */
	public HtmlUnitManager(IManagerHtmlUnitListener parent, HtmlUnit htmlUnit) 
	{
		super(parent, htmlUnit);
		
		//Initialize using data
		this.control = new HtmlUnitControl(this, htmlUnit);
		this.inputData = new HashSet<InputField>();
		this.outputData = new LinkedHashMap<InputField, HtmlPage>();
		
		//Add listeners
		this.addCommandOutputListener(this.control);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#clear()
	 */
	@Override
	public void clear() 
	{
		this.inputData.clear();
		this.outputData.clear();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey(Object key) 
	{
		return this.inputData.contains(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object value) 
	{
		return this.outputData.containsValue(value);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<java.util.Map.Entry<InputField, HtmlPage>> entrySet() 
	{
		return this.outputData.entrySet();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public HtmlPage get(Object key) 
	{
		return this.outputData.get(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() 
	{
		return this.inputData == null ? true : this.inputData.isEmpty();
	}
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<InputField> iterator() 
	{
		return this.inputData.iterator();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<InputField> keySet() 
	{
		return this.inputData;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e)
			throws InconsistenMessageException 	{

		switch (e.getCommand()) {
		
		case CONTROL_FIELD_REF:
			try 
			{
				// Cast source
				FieldRef fieldRef = FieldRef.class.cast(e.getMessage());

				if (this.control.get(PlaceholderData.FIELD_REF).equals(fieldRef))
				{
					// Initialize field reference message manager
					this.fieldRefManager = new FieldRefManager(this, fieldRef);

					// Associate child manager to parent
					this.fieldRefManager.addManagerListener(this);
					this.fieldRefManager.addManagerFieldRefListener(this);
					this.addCommandOutputListener(this.fieldRefManager);

					// Store manager
					this.addChildManager(this.fieldRefManager);
				}
			}
			catch (ClassCastException ex)
			{
				ManagerHtmlUnitEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}
			break;

		case CONTROL_PAGE:
			try 
			{
				// Cast source
				Page page = Page.class.cast(e.getMessage());

				// Check message ID match
				if (this.control.get(PlaceholderData.PAGE).equals(page)) 
				{
					// Initialize manager
					this.pageManager = new PageManager(this, page);

					// Add listeners to manager
					this.pageManager.addManagerListener(this);
					this.pageManager.addPlaceholderListener(this);
					this.addCommandOutputListener(this.pageManager);

					// Store manager
					this.addChildManager(this.pageManager);
				}
			} 
			catch (ClassCastException ex) 
			{
				ManagerHtmlUnitEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}
			break;

		default:
			break;
		}

		//Call abstract method for other controls.
		super.OnCommandInput(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e) 
			throws InconsistenMessageException 	{
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHtmlUnitListener#OnControlHtmlUnit_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHtmlUnitListener#OnControlHtmlUnit_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHtmlUnitListener#OnControlHtmlUnit_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException 
	{
		if(this.hasChildManagers())
		{
			//Start each child manager
			while(this.hasNext())
			{
				IManagerImpl manager = this.next();
				manager.start();
			}
			
			ManagerHtmlUnitEvent(new ManagerEventArgs(this, ManagerEventType.STARTED));
		}
		else
		{
			ManagerHtmlUnitEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHtmlUnitListener#OnControlHtmlUnit_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHtmlUnitListener#OnControlHtmlUnit_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHtmlUnitListener#OnControlHtmlUnit_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnManagerContains_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContains_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//Send report to parent
		ManagerHtmlUnitEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnManagerContains_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContains_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		try
		{
			ContainsManager containsManager = ContainsManager.class.cast(e.getSource());
			
			//Read results
			for(String inputKey : containsManager)
			{
				Boolean contains = containsManager.get(inputKey);
				
				//If contains operator fails discard field by setting the input field value to null.
				if(!contains)
				{
					//Update current field value to null.
					this.currentInput.setValue(null, this.currentInput.getBoost());
				}
			}

			//Check child managers
			if(containsManager.hasChildManagers())
			{
				while(containsManager.hasNext())
				{
					//Get the manager
					IManagerImpl manager = containsManager.next();
					
					//Put data to next manager sibling
					super.putOperatorManagerData(manager, this.currentInput);
				}
			}
			else
			{
				//And send FIELD_COMPLETED event to Field message manager.
				ManagerHtmlUnitEvent(new ManagerEventArgs(this, this.currentInput));
			}
		}
		catch(ClassCastException ex)
		{
			ManagerHtmlUnitEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnManagerContains_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerContains_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnManagerEquals_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerEquals_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//Send report to parent
		ManagerHtmlUnitEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnManagerEquals_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerEquals_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		try
		{
			EqualsManager equalsManager = EqualsManager.class.cast(e.getSource());
			
			//Read results
			for(String inputKey : equalsManager)
			{
				Boolean equals = containsManager.get(inputKey);
				
				//If equals operator fails discard field by setting the input field value as null.
				if(!equals)
				{
					//Update current field value to null.
					this.currentInput.setValue(null, this.currentInput.getBoost());
				}
			}

			//Check child managers
			if(equalsManager.hasChildManagers())
			{
				while(equalsManager.hasNext())
				{
					//Get the manager
					IManagerImpl manager = equalsManager.next();
					
					//Put data to next manager sibling
					super.putOperatorManagerData(manager, this.currentInput);
				}
			}
			else
			{
				//And send FIELD_COMPLETED event to Field message manager.
				ManagerHtmlUnitEvent(new ManagerEventArgs(this, this.currentInput));
			}
		}
		catch(ClassCastException ex)
		{
			ManagerHtmlUnitEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnManagerEquals_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerEquals_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.PlaceholderManager#OnManagerFieldRef_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRef_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//Send report to parent
		ManagerHtmlUnitEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.PlaceholderManager#OnManagerFieldRef_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRef_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		if(sender.equals(this.fieldRefManager))
		{
			for(FieldRef fieldRef : this.fieldRefManager)
			{
				if(this.fieldRefManager.get(fieldRef) == null)
				{
					ManagerHtmlUnitEvent(new ManagerEventArgs(this, ManagerErrorCode.BAD_FIELD_REF));
				}
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.PlaceholderManager#OnManagerFieldRef_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRef_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//Check sender
		if(sender.equals(this.fieldRefManager))
		{
			for(FieldRef fieldRef : this.fieldRefManager)
			{
				//Initialize new input field.
				this.fieldRefManager.put(fieldRef, this.currentInput);
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.PlaceholderManager#OnManagerHtmlUnit_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerHtmlUnit_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.PlaceholderManager#OnManagerHtmlUnit_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerHtmlUnit_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.PlaceholderManager#OnManagerHtmlUnit_InputFieldCompleted(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerHtmlUnit_InputFieldCompleted(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.PlaceholderManager#OnManagerHtmlUnit_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerHtmlUnit_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		if(e.getSource().equals(this))
		{
			//Set manager ready to be iterated again.
			this.reset();
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnManagerPage_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerPage_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//Send report to parent
		ManagerHtmlUnitEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnManagerPage_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerPage_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//Manage only child page message manager
		if(e.getSource().equals(this.pageManager))
		{
			try
			{
				PageManager pageManager = PageManager.class.cast(e.getSource());
				
				for(Map<InputField, List<DomNode>> inputMap : pageManager)
				{
					for(InputField inputField : inputMap.keySet())
					{
						if(inputField.equals(this.currentInput))
						{
							List<DomNode> pageOutputData = pageManager.get(inputMap).get(inputField);
							
							this.currentInput.setValue(pageOutputData, this.currentInput.getBoost());
							
							break;
						}					
					}
					//Read output data
//					for(DomNode domNode : pageManager)
//					{
//						List<DomNode> pageOutput = pageManager.get(domNode);
//						
//						//If output data not null and not empty
//						if(pageOutput != null & !pageOutput.isEmpty())
//						{
//							//Set values to current output data
//							this.currentOutput.setValue(pageOutput, this.currentOutput.getBoost());
//						}
//					}
				}

//				
//				if(pageManager.hasChildManagers())
//				{
//					while(pageManager.hasNext())
//					{
//						IManagerImpl manager = pageManager.next();
//						
//						if(manager instanceof PageElementManager)
//						{
//							PageElementManager pageElementManager = PageElementManager.class.cast(manager);
//							
//							
//						}
//						else
//						{
//							super.putChildManagerData(manager, this.currentOutput);	
//						}					
//					}
//				}
				
			}
			catch(ClassCastException ex)
			{
				ManagerHtmlUnitEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnManagerPage_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerPage_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnManagerRemove_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerRemove_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//Send report to parent
		ManagerHtmlUnitEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnManagerRemove_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerRemove_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		try
		{
			RemoveManager removeManager = RemoveManager.class.cast(e.getSource());
			
			//Read results
			for(String inputKey : removeManager)
			{
				String value = removeManager.get(inputKey);
				
				//If contains operator fails discard field by setting the input field value as null.
				if(value != null)
				{
					//Update current field value.
					this.currentInput.setValue(value, this.currentInput.getBoost());
				}
			}

			//Check child managers
			if(removeManager.hasChildManagers())
			{
				while(removeManager.hasNext())
				{
					//Get the manager
					IManagerImpl manager = removeManager.next();
					
					//Put data to next manager sibling
					super.putOperatorManagerData(manager, this.currentInput);
				}
			}
			else
			{
				//And send FIELD_COMPLETED event to Field message manager.
				ManagerHtmlUnitEvent(new ManagerEventArgs(this, this.currentInput));
			}
		}
		catch(ClassCastException ex)
		{
			ManagerHtmlUnitEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		}
	}
	
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnManagerRemove_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerRemove_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnManagerReplace_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerReplace_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//Send report to parent
		ManagerHtmlUnitEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnManagerReplace_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerReplace_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		try
		{
			ReplaceManager replaceManager = ReplaceManager.class.cast(e.getSource());
			
			//Read results
			for(String inputKey : replaceManager)
			{
				String value = replaceManager.get(inputKey);
				
				//If contains operator fails discard field by setting the input field value as null.
				if(value != null)
				{
					//Update current field value to manager's result.
					this.currentInput.setValue(value, this.currentInput.getBoost());
				}
			}
	
			//Check child managers
			if(replaceManager.hasChildManagers())
			{
				while(replaceManager.hasNext())
				{
					//Get the manager
					IManagerImpl manager = replaceManager.next();
					
					//Put data to next manager sibling
					super.putOperatorManagerData(manager, this.currentInput);
				}
			}
			else
			{
				//And send FIELD_COMPLETED event to Field message manager.
				ManagerHtmlUnitEvent(new ManagerEventArgs(this, this.currentInput));
			}
		}
		catch(ClassCastException ex)
		{
			ManagerHtmlUnitEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnManagerReplace_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerReplace_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnManagerSplit_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSplit_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	
		//Send report to parent
		ManagerHtmlUnitEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnManagerSplit_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSplit_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		try
		{
			SplitManager splitManager = SplitManager.class.cast(e.getSource());
			
			//Read results
			for(String inputKey : splitManager)
			{
				String[] values = splitManager.get(inputKey);
				
				//If contains operator fails discard field by setting the input field value as null.
				if(values != null ? values.length > 0 : false)
				{
					//Update current field value with obtained values.
					this.currentInput.setValue(values, this.currentInput.getBoost());
				}
				else
				{
					//Update current field value to null.
					this.currentInput.setValue(null, this.currentInput.getBoost());
				}
			}

			//Check child managers
			if(splitManager.hasChildManagers())
			{
				while(splitManager.hasNext())
				{
					//Get the manager
					IManagerImpl manager = splitManager.next();
					
					//Put data to next manager sibling
					super.putOperatorManagerData(manager, this.currentInput);
				}
			}
			else
			{
				//And send FIELD_COMPLETED event to Field message manager.
				ManagerHtmlUnitEvent(new ManagerEventArgs(this, this.currentInput));
			}
		}
		catch(ClassCastException ex)
		{
			ManagerHtmlUnitEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnManagerSplit_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSplit_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnManagerSubstring_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSubstring_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//Send report to parent
		ManagerHtmlUnitEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnManagerSubstring_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSubstring_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		try
		{
			SubstringManager substringManager = SubstringManager.class.cast(e.getSource());
			
			//Read results
			for(String inputKey : substringManager)
			{
				String value = substringManager.get(inputKey);
				
				//Update current field value to manager's result.
				this.currentInput.setValue(value, this.currentInput.getBoost());
			}
			
			if(substringManager.hasChildManagers())
			{
				while(substringManager.hasNext())
				{
					//Get the manager
					IManagerImpl manager = substringManager.next();
					
					//Put data to next manager sibling
					super.putOperatorManagerData(manager, this.currentInput);
					
					
					substringManager.reset();
				}
			}
			else
			{
				//And send FIELD_COMPLETED event to Field message manager.
				ManagerHtmlUnitEvent(new ManagerEventArgs(this, this.currentInput));
			}
		}
		catch(ClassCastException ex)
		{
			ManagerHtmlUnitEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnManagerSubstring_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSubstring_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnManagerTryParse_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerTryParse_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//Send report to parent
		ManagerHtmlUnitEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnManagerTryParse_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerTryParse_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		try
		{
			TryParseManager tryParseManager = TryParseManager.class.cast(e.getSource());
			
			//Read results
			for(String inputKey : tryParseManager)
			{
				Boolean resultValue = containsManager.get(inputKey);
				
				//If contains operator fails discard field by setting the input field value as null.
				if(!resultValue)
				{
					//Update current field value to null.
					this.currentInput.setValue(null, this.currentInput.getBoost());
				}
			}

			//Check child managers
			if(tryParseManager.hasChildManagers())
			{
				while(tryParseManager.hasNext())
				{
					//Get the manager
					IManagerImpl manager = tryParseManager.next();
					
					//Put data to next manager sibling
					super.putOperatorManagerData(manager, this.currentInput);
				}
			}
			else
			{
				//And send FIELD_COMPLETED event to Field message manager.
				ManagerHtmlUnitEvent(new ManagerEventArgs(this, this.currentInput));
			}
		}
		catch(ClassCastException ex)
		{
			ManagerHtmlUnitEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnManagerTryParse_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerTryParse_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public HtmlPage put(InputField key, HtmlPage value) 
	{
		//Update input data
		this.inputData.add(key);
		
		//Update pointers
		this.currentInput =  key;
		this.currentOutput = value;

		//Initialize field value to current HtmlPage
		this.currentInput.setValue(value, key.getBoost());
		
		//Check Page message manager
		if(this.pageManager != null)
		{
			//Set page message manager's input data.
			Map<InputField, List<DomNode>> pageInputData = new LinkedHashMap<InputField, List<DomNode>>();
			
			//Initialize first Page message manager input data.
			List<DomNode> pageInputDataList = new ArrayList<DomNode>();
			
			//Store received HtmlPage.
			pageInputDataList.add(value);				
			pageInputData.put(key, pageInputDataList);
			
			//Initialize Page message manager's output data.
			Map<InputField, List<DomNode>> pageOutputData = new LinkedHashMap<InputField, List<DomNode>>();
			
			//Put data
			this.pageManager.put(pageInputData, pageOutputData);
		}
		
		if(this.containsManager != null)
		{
			String containsInputKey = key.getValue().toString();
			this.containsManager.put(containsInputKey, new Boolean(false));
		}
				//Update output data.
		value = this.outputData.put(key, value);
		
		//Send FIELD_COMPLETED
		ManagerHtmlUnitEvent(new ManagerEventArgs(this, key));
		
		return value;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends InputField, ? extends HtmlPage> m) 
	{
		for(InputField inputField : m.keySet())
		{
			HtmlPage page = m.get(inputField);
			
			//Call put method for each HtmlPage
			if(this.put(inputField, page) == null)
			{
				ManagerHtmlUnitEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
			}
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public HtmlPage remove(Object key) 
	{
		this.inputData.remove(key);
		return this.outputData.remove(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() 
	{
		return this.outputData != null ? this.outputData.size() : 0;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#start()
	 */
	@Override
	public void start() 
	{
		this.control.controlMessage(this.message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#stop()
	 */
	@Override
	public void stop()
	{
		
	}
	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<HtmlPage> values() 
	{
		return this.outputData.values();
	}
	
}