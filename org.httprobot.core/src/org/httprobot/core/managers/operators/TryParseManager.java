/**
 * 
 */
package org.httprobot.core.managers.operators;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.solr.common.util.Base64;
import org.httprobot.common.definitions.Enums.FieldType;
import org.httprobot.common.definitions.Enums.ManagerErrorCode;
import org.httprobot.common.definitions.Enums.ManagerEventType;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.operators.TryParse;
import org.httprobot.core.common.DateValidator;
import org.httprobot.core.common.Enums.OperatorData;
import org.httprobot.core.controls.operators.TryParseControl;
import org.httprobot.core.controls.operators.interfaces.IControlTryParseListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IDataMappingImpl;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.operators.interfaces.IManagerTryParseListener;

/**
 * {@link TryParse} operator message manager. Inherits {@link AbstractOperatorManager}.
 * <br>
 * It's {@link IControlTryParseListener} and {@link IDataMappingImpl}.
 * <br>
 * @author joan
 *
 */
public class TryParseManager 
	extends AbstractOperatorManager<TryParse, TryParseControl, IManagerTryParseListener>
	implements IControlTryParseListener, IDataMappingImpl<String, Boolean> {
	
	/**
	 * -6838860696809062236L
	 */
	private static final long serialVersionUID = -6838860696809062236L;
	
	String currentInput;
	Boolean currentOutput;
	
	Set<String> inputData;
	Map<String, Boolean> outputData;
	
	/**
	 * {@link TryParse} operator message manager default class constructor.
	 */
	public TryParseManager() 
	{
		super();
	}
	/**
	 * {@link TryParse} operator message manager class constructor.
	 * @param parent {@link IManagerTryParseListener} the parent
	 * @param message {@link TryParse} the message
	 */
	public TryParseManager(IManagerTryParseListener parent, TryParse message)
	{
		super(parent, message);
		
		//Initialize control
		this.control = new TryParseControl(this, message);
		
		//Associate control to manager
		addCommandOutputListener(this.control);
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
	public Set<java.util.Map.Entry<String, Boolean>> entrySet() 
	{
		return this.outputData.entrySet();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public Boolean get(Object key) 
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
	 * @see org.httprobot.core.controls.operators.interfaces.IControlTryParseListener#OnControlTryParse_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlTryParseListener#OnControlTryParse_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlTryParseListener#OnControlTryParse_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(this.hasChildManagers())
		{
			//Start each child manager
			while(this.hasNext())
			{
				IManagerImpl manager = this.next();
				manager.start();
			}
			
			ManagerTryParseEvent(new ManagerEventArgs(this, ManagerEventType.STARTED));
		}
		else
		{
			ManagerTryParseEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlTryParseListener#OnControlTryParse_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlTryParseListener#OnControlTryParse_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlTryParseListener#OnControlTryParse_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Boolean put(String key, Boolean value)
	{
		//Update input data
		this.inputData.add(key);
		
		//Update pointers
		this.currentInput = key;
		this.currentOutput = value;
		
		try
		{
			FieldType type = FieldType.class.cast(this.control.get(OperatorData.FIELD_TYPE));
			
			//Operate
			switch (type) 
			{
				case BASE64:
					Base64.base64ToByteArray(key);
					value = true;
					break;
					
				case BOOLEAN:
					value = Boolean.parseBoolean(key);
				break;
				
				case FLOAT:
					try
					{
						Float.parseFloat(key);
						value = true;
					}
					catch(NumberFormatException ex)
					{
						value = false;
					}
					break;
					
				case INTEGER:
					try
					{
						Integer.parseInt(key);
						value = true;
					}
					catch(NumberFormatException ex)
					{
						value = false;
					}
					break;
				
				case DATETIME:
					DateValidator dateValidator = new DateValidator();
					value = dateValidator.validate(key);				
					break;
					
				case URL:
					try
					{
						new URL(key);					
						value = true;
					}
					catch(MalformedURLException ex)
					{
						value = false;
					}
					break;
				
				case UUID:
					try
					{
						UUID.fromString(key);
						value = true;
					}
					catch(IllegalArgumentException ex)
					{
						value = false;
					}
					break;

				default:
					break;
			}
		}
		catch(ClassCastException ex)
		{
			ManagerTryParseEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
		}
		catch(NullPointerException ex)
		{
			ManagerTryParseEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_POINTER));
		}
		
		Boolean result = this.outputData.put(key, value); 
		
		ManagerTryParseEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
		
		return result;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends String, ? extends Boolean> m) {
	
		for(String key : m.keySet())
		{
			if(this.put(key, m.get(key)) == null)
			{
				ManagerTryParseEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
			}
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public Boolean remove(Object key) 
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
		return this.outputData.size();
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
	public void stop() {
		
	}
	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<Boolean> values() 
	{
		return this.outputData.values();
	}
}
