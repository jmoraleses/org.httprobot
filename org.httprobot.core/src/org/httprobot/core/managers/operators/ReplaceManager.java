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

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.definitions.Enums.ManagerErrorCode;
import org.httprobot.common.definitions.Enums.ManagerEventType;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.operators.Replace;
import org.httprobot.core.common.Enums.OperatorData;
import org.httprobot.core.controls.operators.ReplaceControl;
import org.httprobot.core.controls.operators.interfaces.IControlReplaceListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IDataMappingImpl;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.operators.interfaces.IManagerReplaceListener;

/**
 * {@link Replace} operator message manager. Inherits {@link AbstractOperatorManager}.
 * <br>
 * It's {@link IControlReplaceListener} and {@link IDataMappingImpl}.
 * <br>
 * @author joan
 *
 */
@XmlRootElement
public class ReplaceManager 
	extends AbstractOperatorManager<Replace, ReplaceControl, IManagerReplaceListener> 
	implements IControlReplaceListener, IDataMappingImpl<String, String> {

	/**
	 * -4634603534295627219L
	 */
	private static final long serialVersionUID = -4634603534295627219L;

	String currentInput;
	String currentOutput;
	
	Set<String> inputData;
	Map<String, String> outputData;

	/**
	 * 
	 */
	public ReplaceManager()
	{
		super();
	}
	/**
	 * @param parent
	 */
	public ReplaceManager(IManagerReplaceListener parent, Replace replace) 
	{
		super(parent, replace);
		
		//Initialize data members
		this.inputData = new HashSet<String>();
		this.outputData = new LinkedHashMap<String, String>();
		
		//Initialize Replace message control
		this.control = new ReplaceControl(this, replace);
		
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
		return this.inputData == null | this.inputData.isEmpty();
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
	 * @see org.httprobot.core.rml.managers.RmlManager#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
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
	 * @see org.httprobot.core.rml.managers.RmlManager#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IControlReplaceListener#OnControlReplace_Changed(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlReplace_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IControlReplaceListener#OnControlReplace_Init(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlReplace_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IControlReplaceListener#OnControlReplace_Loaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlReplace_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(this.hasChildManagers())
		{
			//Start each child manager
			while(this.hasNext())
			{
				IManagerImpl manager = this.next();
				manager.start();
			}
			
			ManagerContainsEvent(new ManagerEventArgs(this, ManagerEventType.STARTED));
		}
		else
		{
			ManagerContainsEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IControlReplaceListener#OnControlReplace_Read(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlReplace_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IControlReplaceListener#OnControlReplace_Rendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlReplace_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IControlReplaceListener#OnControlReplace_Write(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlReplace_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public String put(String key, String value) 
	{
		//Update input data
		this.inputData.add(key);
		
		//Update pointers
		this.currentInput = key;
		this.currentOutput = value;
		
		//Operate string
		value = key.replace(
				String.class.cast(this.control.get(OperatorData.OLD_STRING)), 
				String.class.cast(this.control.get(OperatorData.NEW_STRING)));
		
		//Update output data
		value = this.outputData.put(key, value);
		
		//Send event to parent
		ManagerContainsEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
		
		return value;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends String, ? extends String> m) {
		
		for(String key : m.keySet())
		{
			//Call put method for each string value received
			if(this.put(key, m.get(key)) == null)
			{
				ManagerContainsEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
			}
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public String remove(Object key) {

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
	 * @see org.httprobot.core.rml.managers.RmlManager#start()
	 */
	@Override
	public void start() 
	{
		this.control.controlMessage(this.message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlManager#stop()
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
