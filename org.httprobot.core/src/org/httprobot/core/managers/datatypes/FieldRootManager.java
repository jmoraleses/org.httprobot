/**
 * 
 */
package org.httprobot.core.managers.datatypes;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.datatypes.Field;
import org.httprobot.common.datatypes.FieldRoot;
import org.httprobot.common.definitions.Enums.ManagerErrorCode;
import org.httprobot.common.definitions.Enums.ManagerEventType;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.core.common.Enums.DataTypeData;
import org.httprobot.core.contents.solr.InputDocument;
import org.httprobot.core.contents.solr.InputField;
import org.httprobot.core.controls.datatypes.FieldRootControl;
import org.httprobot.core.controls.datatypes.interfaces.IControlFieldRootListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IDataMappingImpl;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.DataTypeManager;
import org.httprobot.core.managers.datatypes.interfaces.IManagerFieldListener;
import org.httprobot.core.managers.datatypes.interfaces.IManagerFieldRootListener;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * {@link FieldRoot} message manager class. Inherits {@link DataTypeManager}. 
 * <br>
 * Is {@link IControlFieldRootListener}, {@link IManagerFieldListener} and {@link IDataMappingImpl}.
 * <br>
 * <h1>Input data:</h1>Dictionary[Object, Dictionary[WebRequest, HtmlPage]]  
 * <br>
 * <h1>Output data:</h1>Dictionary[Dictionary[WebRequest, HtmlPage], Dictionary[HtmlPage, InputDocument]]
 * @author joan
 *
 */
@XmlRootElement
public class FieldRootManager 
		extends DataTypeManager<FieldRoot, FieldRootControl, IManagerFieldRootListener> 
		implements IControlFieldRootListener, IManagerFieldListener, 
			IDataMappingImpl<InputDocument, HtmlPage> {
	
	/**
	 * -898031145427185471L
	 */
	private static final long serialVersionUID = -898031145427185471L;
	/**
	 * The {@link Field} manager collection.
	 */
	Map<Field, FieldManager> fieldManagers;
	/**
	 * The current input data.
	 */
	HtmlPage currentOutput;
	/**
	 * The current output data.
	 */
	InputDocument currentInput;
	/**
	 * The input data.
	 */
	Set<InputDocument> inputData;
	/**
	 * The output data.
	 */
	Map<InputDocument, HtmlPage> outputData;
	/**
	 * {@link FieldRoot} message manager default class constructor.
	 */
	public FieldRootManager() 
	{
		super();
	}
	/**
	 * {@link FieldRoot} message manager class constructor.
	 * @param parent {@link IManagerFieldRootListener} the parent
	 * @param fields {@link FieldRoot} the message
	 */
	public FieldRootManager(IManagerFieldRootListener parent, FieldRoot fields)
	{
		super(parent, fields);
		
		//Initialize members
		this.control = new FieldRootControl(this, fields);
		this.fieldManagers = new LinkedHashMap<Field, FieldManager>();
		this.inputData = new HashSet<InputDocument>();
		this.outputData = new LinkedHashMap<InputDocument, HtmlPage>();
		this.currentOutput = null;
		this.currentInput = null;
		
		//Add listeners
		addCommandOutputListener(this.control);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
		
		switch (e.getCommand()) 
		{
			case CONTROL_FIELD:
				try
				{
					//Cast sources
					Field field = Field.class.cast(e.getMessage());
					
					if(this.control.get(DataTypeData.FIELD) != null ?
							this.control.get(DataTypeData.FIELD).equals(field) : false) {

						//Instantiate manager for each Field message received
						FieldManager fieldManager = new FieldManager(this, field);
						
						//Associate child manager to parent
						fieldManager.addManagerListener(this);
						fieldManager.addManagerFieldListener(this);					
						this.addCommandOutputListener(fieldManager);
						
						//Store manager
						this.fieldManagers.put(field, fieldManager);
						addChildManager(fieldManager);
					}
				}
				catch(ClassCastException ex)
				{
					ManagerFieldRootEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
				}
				return;
	
			default:
				return;
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e) 
			throws InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlFieldRootListener#OnControlFieldRoot_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRoot_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlFieldRootListener#OnControlFieldRoot_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRoot_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlFieldRootListener#OnControlFieldRoot_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRoot_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(this.hasChildManagers())
		{
			//Start each child manager
			while(this.hasNext())
			{
				IManagerImpl manager = this.next();
				manager.start();
			}
			
			ManagerFieldRootEvent(new ManagerEventArgs(this, ManagerEventType.STARTED));
		}
		else
		{
			ManagerFieldRootEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlFieldRootListener#OnControlFieldRoot_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRoot_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlFieldRootListener#OnControlFieldRoot_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRoot_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlFieldRootListener#OnControlFieldRoot_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRoot_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnManagerField_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerField_Error(Object sender, ManagerEventArgs e) 
			throws ManagerException {
		
		ManagerFieldRootEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnManagerField_FieldCompleted(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerField_FieldCompleted(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnManagerField_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerField_Finished(Object sender, ManagerEventArgs e) 
			throws ManagerException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnManagerField_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerField_Started(Object sender, ManagerEventArgs e) 
			throws ManagerException {
		
		try
		{
			FieldManager.class.cast(e.getSource());
		}
		catch(ClassCastException ex)
		{
			ManagerFieldRootEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnManagerFields_DocumentCompleted(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRoot_DocumentCompleted(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnManagerFields_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRoot_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnManagerFields_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRoot_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnManagerFields_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRoot_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		if(e.getSource().equals(this))
		{
			//When all managers has been started. 
			//Set iterator ready to iterate again.
			this.reset();	
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#start()
	 */
	@Override
	public void start() 
	{
		this.control.controlMessage(this.message);		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#stop()
	 */
	@Override
	public void stop()
	{
		
	}
	/* (non-Javadoc)
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() 
	{
		return this.outputData.size();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() 
	{
		return this.outputData.isEmpty();
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
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public HtmlPage get(Object key) 
	{
		return this.outputData.get(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public HtmlPage put(InputDocument key, HtmlPage value) 
	{
		//Store key to input data.
		this.inputData.add(key);
		
		//Update pointers.
		this.currentOutput = value;
		this.currentInput = key;
		
		//Get available fields.
		for(Field field : this.fieldManagers.keySet())
		{
			//Get the field managers
			FieldManager fieldManager = this.fieldManagers.get(field);
			
			try
			{
				//Get template field giving it's reference ID.
				InputField inputField = this.getTemplateLibrary().getFieldsLibrary().get(field.getUuid());

				//Update manager's data.
				fieldManager.put(inputField, value);
			}
			catch (NotImplementedException e) 
			{
				ManagerFieldRootEvent(new ManagerEventArgs(this, ManagerErrorCode.NOT_IMPLEMENTED));
			}
			catch (ManagerException e) 
			{
				ManagerFieldRootEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
			}
		}
		
		//Update output data.
		value = this.outputData.put(this.currentInput, this.currentOutput);
		
		//Send event to parent.
		ManagerFieldRootEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
		
		return value;
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
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends InputDocument, ? extends HtmlPage> m) 
	{
		for(InputDocument inputDocument : m.keySet())
		{
			HtmlPage pageValue = m.get(inputDocument);
			//Call put method for each HtmlPage
			if(this.put(inputDocument, pageValue) == null)
			{
				ManagerFieldRootEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
			}
		}
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
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<InputDocument> keySet() 
	{
		return this.inputData;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<HtmlPage> values() 
	{
		return this.outputData.values();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<java.util.Map.Entry<InputDocument, HtmlPage>> entrySet() 
	{
		return this.outputData.entrySet();
	}
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<InputDocument> iterator() 
	{
		return this.inputData.iterator();
	}
}