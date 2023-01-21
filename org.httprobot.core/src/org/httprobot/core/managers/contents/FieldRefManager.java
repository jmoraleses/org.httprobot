/**
 * 
 */
package org.httprobot.core.managers.contents;

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
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.ContentData;
import org.httprobot.core.contents.solr.InputField;
import org.httprobot.core.controls.contents.FieldRefControl;
import org.httprobot.core.controls.contents.interfaces.IControlFieldRefListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IDataMappingImpl;
import org.httprobot.core.managers.ContentManager;
import org.httprobot.core.managers.contents.interfaces.IManagerFieldRefListener;

/**
 * @author joan
 *
 */
@XmlRootElement
public class FieldRefManager 
extends ContentManager<FieldRef, FieldRefControl, IManagerFieldRefListener> 
implements IControlFieldRefListener, IDataMappingImpl<FieldRef, InputField>
{
	/**
	 * 3544307063492639521L
	 */
	private static final long serialVersionUID = 3544307063492639521L;

	
	Set<FieldRef> inputData;
	Map<FieldRef, InputField> outputData;
	
	/**
	 * 
	 */
	public FieldRefManager()
	{
		super();
		
		//Initialize members
		this.inputData = new HashSet<FieldRef>();
		this.outputData = new LinkedHashMap<FieldRef, InputField>();		
	}
	/**
	 * @param parent
	 * @param message
	 */
	public FieldRefManager(IManagerFieldRefListener parent, FieldRef message) 
	{
		super(parent, message);
		
		//Initialize members
		this.control = new FieldRefControl(this, message);
		
		this.inputData = new HashSet<FieldRef>();
		this.outputData = new LinkedHashMap<FieldRef, InputField>();
		
		//Set inherited data
		this.inputData.add(message);
		
		//Associate parent's control child
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
	public Set<java.util.Map.Entry<FieldRef, InputField>> entrySet() 
	{
		return this.outputData.entrySet();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public InputField get(Object key) 
	{
		if(this.inputData.contains(key))
		{
			return this.outputData.get(key);	
		}
		else
		{
			return null;
		}
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
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<FieldRef> iterator() 
	{
		return this.inputData.iterator();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<FieldRef> keySet() 
	{
		return this.inputData;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlFieldRefListener#OnControlFieldRef_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlFieldRefListener#OnControlFieldRef_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlFieldRefListener#OnControlFieldRef_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getMessage() != null)
		{
			try
			{
				FieldRef fieldRef = FieldRef.class.cast(e.getMessage());
				
				if(this.control.get(ContentData.UUID).equals(fieldRef.getUuid()))
				{
					ManagerFieldRefEvent(new ManagerEventArgs(this, ManagerEventType.STARTED));
				}
				else
				{
					ManagerFieldRefEvent(new ManagerEventArgs(this, ManagerErrorCode.BAD_MESSAGE));
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"FieldRefManager.OnControlFieldRef_Loaded: FieldRef message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlFieldRefListener#OnControlFieldRef_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlFieldRefListener#OnControlFieldRef_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlFieldRefListener#OnControlFieldRef_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnManagerFieldRef_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRef_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		//Nothing
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnManagerFieldRef_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRef_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//Nothing
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ContentManager#OnManagerFieldRef_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerFieldRef_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		if(e.getSource().equals(this))
		{
			this.reset();
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public InputField put(FieldRef key, InputField value) 
	{		
		if(this.inputData.contains(key))
		{
			//Update output data
			value = this.outputData.put(key, value);
			
			//Fire event to parent
			ManagerFieldRefEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
			
			return value;
		}
		return null;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends FieldRef, ? extends InputField> m) {
		
		if(this.inputData.containsAll(m.keySet()))
		{
			for(FieldRef fieldRef : m.keySet())
			{
				InputField inputField = m.get(fieldRef);
				
				//Update output data
				this.outputData.put(fieldRef, inputField);
				
				//Fire event to parent
				ManagerFieldRefEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
				
				return;	
			}
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public InputField remove(Object key) 
	{
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
	 * @see org.httprobot.core.rml.managers.RmlContentManager#start()
	 */
	@Override
	public void start() 
	{
		this.control.controlMessage(this.message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.RmlContentManager#stop()
	 */
	@Override
	public void stop() {
		
	}
	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<InputField> values() 
	{
		return this.outputData.values();
	}
}