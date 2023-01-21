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
import org.httprobot.common.definitions.Enums.ManagerErrorCode;
import org.httprobot.common.definitions.Enums.ManagerEventType;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.HtmlUnit;
import org.httprobot.common.placeholders.HttpAddress;
import org.httprobot.core.common.Enums.DataTypeData;
import org.httprobot.core.contents.solr.InputField;
import org.httprobot.core.controls.datatypes.FieldControl;
import org.httprobot.core.controls.datatypes.interfaces.IControlFieldListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IDataMappingImpl;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.DataTypeManager;
import org.httprobot.core.managers.datatypes.interfaces.IManagerFieldListener;
import org.httprobot.core.managers.placeholders.HtmlUnitManager;
import org.httprobot.core.managers.placeholders.HttpAddressManager;
import org.httprobot.core.managers.placeholders.interfaces.IManagerHtmlUnitListener;
import org.httprobot.core.managers.placeholders.interfaces.IManagerHttpAddressListener;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * {@link Field} message manager class. Inherits {@link DataTypeManager},
 * <br>
 * It's {@link IControlFieldListener}, {@link IManagerHtmlUnitListener}, 
 * {@link IManagerHttpAddressListener} and {@link IDataMappingImpl},
 * <br>
 * @author joan
 *
 */
@XmlRootElement
public class FieldManager 
	extends DataTypeManager<Field, FieldControl, IManagerFieldListener> 
	implements IControlFieldListener, IManagerHtmlUnitListener, IManagerHttpAddressListener, 
		IDataMappingImpl<InputField, HtmlPage> {
	
	/**
	 * 1906755128946076846L
	 */
	private static final long serialVersionUID = 1906755128946076846L;
	
	InputField currentInput;

	//Data members
	HtmlPage currentOutput;

	//Manager members
	protected HtmlUnitManager htmlUnitManager;
	protected HttpAddressManager httpAddresManager;
	
	Set<InputField> inputData;
	Map<InputField, HtmlPage> outputData;

	/**
	 * {@link Field} message manager default class constructor.
	 */
	public FieldManager() 
	{
		super();
	}
	/**
	 * {@link Field} message manager class constructor.
	 * @param parent
	 */
	public FieldManager(IManagerFieldListener parent, Field field) 
	{
		super(parent, field);
		
		//Initialize members
		this.control = new FieldControl(this, field);		
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
		this.outputData.clear();		
	}
	/* (non-Javadoc)
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey(Object key) 
	{
		return this.containsKey(key);
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
		return this.inputData != null ? this.inputData.isEmpty() : false;
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
	 * @see org.httprobot.core.managers.DataTypeManager#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e) 
			throws InconsistenMessageException {
		
		switch (e.getCommand()) 
		{
			case CONTROL_HTML_BODY:
				try
				{
					//Read message
					HtmlUnit htmlUnit = HtmlUnit.class.cast(e.getMessage());
					
					if(this.control.get(DataTypeData.HTML_UNIT) != null ?
							this.control.get(DataTypeData.HTML_UNIT).equals(htmlUnit) : false) {
						
						//Initialize HtmlBody place holder message manager.
						this.htmlUnitManager = new HtmlUnitManager(this, htmlUnit);
						
						//Associate child manager to parent.
						this.htmlUnitManager.addManagerListener(this);
						this.htmlUnitManager.addManagerHtmlBodyListener(this);
						addCommandOutputListener(this.htmlUnitManager);	
						
						//Store manager.
						addChildManager(this.htmlUnitManager);
					}					
				}
				catch(ClassCastException ex)
				{
					ManagerFieldEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
				}
				return;
				
			case CONTROL_HTTP_REQUEST:
				try
				{
					//Read message
					HttpAddress httpAddress = HttpAddress.class.cast(e.getMessage());

					if(this.control.get(DataTypeData.HTML_UNIT) != null ?
							this.control.get(DataTypeData.HTML_UNIT).equals(httpAddress) : false) {
						
						//Initialize HttpAddress place holder message manager.
						this.httpAddresManager = new HttpAddressManager(this, httpAddress);
						
						//Associate child manager to parent.
						this.httpAddresManager.addManagerListener(this);
						this.httpAddresManager.addManagerHttpAddressListener(this);
						addCommandOutputListener(this.httpAddresManager);
						
						//Store manager.
						addChildManager(this.htmlUnitManager);
					}
				}
				catch(ClassCastException ex)
				{
					ManagerFieldEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
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
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlFieldListener#OnControlField_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlField_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlFieldListener#OnControlField_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlField_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlFieldListener#OnControlField_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlField_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(this.hasChildManagers())
		{
			//Start each child manager
			while(this.hasNext())
			{
				IManagerImpl manager = this.next();
				manager.start();
			}
			
			ManagerFieldEvent(new ManagerEventArgs(this, ManagerEventType.STARTED));
		}
		else
		{
			ManagerFieldEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlFieldListener#OnControlField_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlField_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlFieldListener#OnControlField_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlField_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlFieldListener#OnControlField_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlField_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnManagerField_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerField_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnManagerField_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerField_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.DataTypeManager#OnManagerField_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerField_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	
		if(e.getSource().equals(this))
		{
			//When all managers has been started. 
			//Set iterator ready to iterate again.
			this.reset();	
		}		
	}

	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.interfaces.IManagerHtmlUnitListener#OnManagerHtmlUnit_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerHtmlUnit_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.interfaces.IManagerHtmlUnitListener#OnManagerHtmlUnit_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerHtmlUnit_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.interfaces.IManagerHtmlUnitListener#OnManagerHtmlUnit_InputFieldCompleted(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerHtmlUnit_InputFieldCompleted(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.interfaces.IManagerHtmlUnitListener#OnManagerHtmlUnit_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerHtmlUnit_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.interfaces.IManagerHttpAddressListener#OnManagerHttpAddress_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerHttpAddress_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.placeholders.interfaces.IManagerHttpAddressListener#OnManagerHttpAddress_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
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
	 * @see org.httprobot.core.managers.placeholders.interfaces.IManagerHttpAddressListener#OnManagerHttpAddress_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerHttpAddress_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public HtmlPage put(InputField key, HtmlPage value) 
	{
		//Update input data.
		this.inputData.add(key);
		
		//Update pointers.
		this.currentOutput = value;
		this.currentInput = key;
		
		if(this.htmlUnitManager != null)
		{
			this.htmlUnitManager.put(key, value);
		}
		
		if(this.httpAddresManager != null)
		{
			this.httpAddresManager.put(key, value);
		}
				
		value = this.outputData.put(key, value);
		
		//Send FIELD_COMPLETED to parent
		ManagerFieldEvent(new ManagerEventArgs(this, key));
		
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
			HtmlPage pageValue =  m.get(inputField);
			
			if(this.put(inputField, pageValue) == null)
			{
				ManagerFieldEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
			}
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public HtmlPage remove(Object key) 
	{
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
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<HtmlPage> values() 
	{
		return this.outputData.values();
	}
}