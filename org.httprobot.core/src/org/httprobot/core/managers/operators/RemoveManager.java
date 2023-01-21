/**
 * 
 */
package org.httprobot.core.managers.operators;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.httprobot.common.definitions.Enums.ManagerErrorCode;
import org.httprobot.common.definitions.Enums.ManagerEventType;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.operators.EndIndex;
import org.httprobot.common.placeholders.operators.Remove;
import org.httprobot.common.placeholders.operators.StartIndex;
import org.httprobot.core.common.Enums.OperatorData;
import org.httprobot.core.controls.operators.RemoveControl;
import org.httprobot.core.controls.operators.interfaces.IControlRemoveListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IDataMappingImpl;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.operators.interfaces.IManagerRemoveListener;

/**
 * {@link Remove} operator message manager. Inherits {@link AbstractOperatorManager}.
 * <br>
 * It's {@link IControlRemoveListener} and {@link IDataMappingImpl}.
 * <br>
 * @author joan
 *
 */
public class RemoveManager 
	extends AbstractOperatorManager<Remove, RemoveControl, IManagerRemoveListener> 
	implements IControlRemoveListener, IDataMappingImpl<String, String> {
	
	/**
	 * 2804956398724095402L
	 */
	private static final long serialVersionUID = 2804956398724095402L;
	
	String currentInput;
	String currentOutput;
	
	Set<String> inputData;
	Map<String, String> outputData;
	
	/**
	 * {@link Remove} message manager default class constructor.
	 */
	public RemoveManager() 
	{
		super();
		
		//Initialize data members
		this.inputData = new HashSet<String>();
		this.outputData = new LinkedHashMap<String, String>();
	}
	/**
	 * {@link Remove} message manager class constructor.
	 * @param parent {@link IManagerRemoveListener} the parent
	 * @param message {@link Remove} the message
	 */
	public RemoveManager(IManagerRemoveListener parent, Remove message) 
	{
		super(parent, message);
		
		//Initialize data members
		this.inputData = new HashSet<String>();
		this.outputData = new LinkedHashMap<String, String>();		
		
		//Initialize message control
		this.control = new RemoveControl(this, message);
		
		//Associate control to it's manager
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
	public Set<java.util.Map.Entry<String, String>> entrySet() 
	{
		return this.outputData.entrySet();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public String get(Object key) 
	{
		return this.outputData.get(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() 
	{
		return this.inputData != null ? this.inputData.isEmpty() : true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<String> iterator() 
	{
		return this.inputData.iterator();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<String> keySet() 
	{
		return this.inputData;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.AbstractOperatorManager#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {

		if(e.getMessage() != null)
		{
			//Call abstract method to manage operator's messages
			super.OnCommandInput(sender, e);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.AbstractOperatorManager#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlRemoveListener#OnControlRemove_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlRemove_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {		
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlRemoveListener#OnControlRemove_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlRemove_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlRemoveListener#OnControlRemove_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlRemove_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(this.hasChildManagers())
		{
			//Start each child manager
			while(this.hasNext())
			{
				IManagerImpl manager = this.next();
				manager.start();
			}
			
			ManagerRemoveEvent(new ManagerEventArgs(this, ManagerEventType.STARTED));
		}
		else
		{
			ManagerRemoveEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlRemoveListener#OnControlRemove_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlRemove_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlRemoveListener#OnControlRemove_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlRemove_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlRemoveListener#OnControlRemove_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlRemove_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public String put(String key, String value) 
	{		
		//Update input data
		this.inputData.add(value);
		
		//Update pointers
		this.currentInput = key;
		this.currentOutput = value;
		
		try
		{
			//Operate
			value = key.replace(
						key.substring(
							StartIndex.class.cast(this.control.get(OperatorData.START_INDEX)).getIntegerValue(), 
							EndIndex.class.cast(this.control.get(OperatorData.END_INDEX)).getIntegerValue()),
						""
							);

			//Update output data
			value = this.outputData.put(key, value);	
		}
		catch(NullPointerException ex)
		{
			ManagerRemoveEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_POINTER));
		}
		
		//Send event to parent
		ManagerRemoveEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		
		return value;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends String, ? extends String> m) {
		
		for(String key : m.keySet())
		{
			if(this.put(key, m.get(key)) == null)
			{
				ManagerRemoveEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
			}
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public String remove(Object key)
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
		return this.inputData.size();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.AbstractOperatorManager#start()
	 */
	@Override
	public void start() 
	{
		this.control.controlMessage(this.message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.AbstractOperatorManager#stop()
	 */
	@Override
	public void stop() 
	{

	}
	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<String> values() 
	{
		return this.outputData.values();
	}	
}