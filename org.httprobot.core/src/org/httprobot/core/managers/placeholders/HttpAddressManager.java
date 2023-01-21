/**
 * 
 */
package org.httprobot.core.managers.placeholders;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
import org.httprobot.common.placeholders.HttpAddress;
import org.httprobot.core.contents.solr.InputField;
import org.httprobot.core.controls.placeholders.HttpAddressControl;
import org.httprobot.core.controls.placeholders.interfaces.IControlHttpAddressListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IDataMappingImpl;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.contents.FieldRefManager;
import org.httprobot.core.managers.placeholders.interfaces.IManagerHttpAddressListener;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * {@link HttpAddress} message manager default class. Inherits {@link AbstractPlaceholderManager}.
 * <br>
 * @author joan
 *
 */
@XmlRootElement
public class HttpAddressManager 
	extends AbstractPlaceholderManager<HttpAddress, HttpAddressControl, IManagerHttpAddressListener>
	implements IControlHttpAddressListener, 
		IDataMappingImpl<InputField, HtmlPage> {

	/**
	 * -5708436432241540482L
	 */
	private static final long serialVersionUID = -5708436432241540482L;
	
	/**
	 * The {@link FieldRef} message manager.
	 */
	protected FieldRefManager fieldRefManager;
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
	 * {@link HttpAddress} message manager default class constructor.
	 */
	public HttpAddressManager()
	{
		super();
	}
	/**
	 * {@link HttpAddress} message manager class constructor.
	 * @param parent the parent
	 * @param httpAddress the message
	 */
	public HttpAddressManager(IManagerHttpAddressListener parent, HttpAddress httpAddres) 
	{
		super(parent, httpAddres);
		
		//Initialize members
		this.control = new HttpAddressControl(this, httpAddres);
		
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
		return this.inputData == null | this.inputData.isEmpty();
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
		
		//Initialize managers
		switch (e.getCommand()) 
		{
			default:
				super.OnCommandInput(sender, e);
				break;
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.AbstractPlaceholderManager#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e) 
			throws InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHttpAddressListener#OnControlHttpAddress_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHttpAddressListener#OnControlHttpAddress_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHttpAddressListener#OnControlHttpAddress_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(this.hasChildManagers())
		{
			//Start each child manager
			while(this.hasNext())
			{
				IManagerImpl manager = this.next();
				manager.start();
			}
			
			ManagerHttpAddressEvent(new ManagerEventArgs(this, ManagerEventType.STARTED));
		}
		else
		{
			ManagerHttpAddressEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHttpAddressListener#OnControlHttpAddress_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHttpAddressListener#OnControlHttpAddress_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHttpAddressListener#OnControlHttpAddress_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.PlaceholderManager#OnManagerHttpAddress_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerHttpAddress_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.PlaceholderManager#OnManagerHttpAddress_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerHttpAddress_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.interfaces.IManagerHttpAddressListener#OnManagerHttpAddress_InputFieldCompleted(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerHttpAddress_InputFieldCompleted(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.PlaceholderManager#OnManagerHttpAddress_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerHttpAddress_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
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
		this.currentOutput = value;
		this.currentInput = key;
		
		//Initialize field value to current HtmlPage
		this.currentInput.setValue(value, key.getBoost());
		
		//Check Contains operator message manager.
		if(this.containsManager != null)
		{
			String containsInputKey = key.getValue().toString();
			this.containsManager.put(containsInputKey, new Boolean(false));
		}
		
		//Update output data.
		value = this.outputData.put(key, value);
		
		//Send FIELD_COMPLETED
		ManagerHttpAddressEvent(new ManagerEventArgs(this, key));
		
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
		return this.outputData.size();
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
	public Collection<HtmlPage> values()
	{
		return this.outputData.values();
	}

}
