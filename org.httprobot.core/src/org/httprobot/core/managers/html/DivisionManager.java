/**
 * 
 */
package org.httprobot.core.managers.html;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.httprobot.common.definitions.Enums.ManagerErrorCode;
import org.httprobot.common.definitions.Enums.ManagerEventType;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.operators.html.Division;
import org.httprobot.core.controls.html.DivisionControl;
import org.httprobot.core.controls.html.interfaces.IControlDivisionListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IDataMappingImpl;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.html.interfaces.IManagerDivisionListener;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;

/**
 * @author joan
 *
 */
public class DivisionManager 
		extends AbstractHtmlManager<Division, DivisionControl, IManagerDivisionListener> 
		implements IControlDivisionListener, IDataMappingImpl<DomNode, List<HtmlDivision>> {

	/**
	 * -87725853678276043L
	 */
	private static final long serialVersionUID = -87725853678276043L;
	
	DomNode currentInput;
	
	List<HtmlDivision> currentOutput;
	
	Set<DomNode> inputData;
	Map<DomNode, List<HtmlDivision>> outputData;
	
	/**
	 * 
	 */
	public DivisionManager() 
	{
		super();
		
		//Initialize data members
		this.inputData = new HashSet<DomNode>();
		this.outputData = new LinkedHashMap<DomNode, List<HtmlDivision>>();
	}
	/**
	 * @param parent
	 * @param message
	 */
	public DivisionManager(IManagerDivisionListener parent, Division message) 
	{
		super(parent, message);

		//Initialize data members
		this.inputData = new HashSet<DomNode>();
		this.outputData = new LinkedHashMap<DomNode, List<HtmlDivision>>();
		
		//Initialize control member
		this.control = new DivisionControl(this, message);
		
		//Associate control to manager
		this.control.addControlDivisionListener(this);
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
	public Set<java.util.Map.Entry<DomNode, List<HtmlDivision>>> entrySet()
	{
		return this.outputData.entrySet();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public List<HtmlDivision> get(Object key) 
	{
		return this.outputData.get(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		
		return 
			this.inputData != null ? 
				this.inputData.isEmpty() 
				: (this.outputData != null ? 
					this.outputData.isEmpty() 
					: true);
	}
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<DomNode> iterator() 
	{
		return this.inputData.iterator();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<DomNode> keySet() 
	{
		return this.inputData;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlDivisionListener#OnControlDivision_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDivision_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlDivisionListener#OnControlDivision_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDivision_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlDivisionListener#OnControlDivision_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDivision_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(this.hasChildManagers())
		{
			//Start each child manager
			while(this.hasNext())
			{
				IManagerImpl manager = this.next();
				manager.start();
			}
			
			ManagerDivisionEvent(new ManagerEventArgs(this, ManagerEventType.STARTED));
		}
		else
		{
			ManagerDivisionEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlDivisionListener#OnControlDivision_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDivision_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlDivisionListener#OnControlDivision_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDivision_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlDivisionListener#OnControlDivision_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDivision_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.HtmlManager#OnManagerDivision_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDivision_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.HtmlManager#OnManagerDivision_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDivision_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.HtmlManager#OnManagerDivision_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerDivision_Started(Object sender, ManagerEventArgs e)
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
	public List<HtmlDivision> put(DomNode key, List<HtmlDivision> value) {
		//Update input data
		this.inputData.add(key);
		
		//Update pointers
		this.currentInput = key;
		this.currentOutput = value;
		
		for(HtmlElement element : key.getHtmlElementDescendants())
		{
			if(element instanceof HtmlDivision)
			{
				HtmlDivision div = HtmlDivision.class.cast(element);
				
				//TODO filter div
				value.add(div);
			}
		}
		
		value = this.outputData.put(key, value);
		
		ManagerTableRowEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
		
		return value;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends DomNode, ? extends List<HtmlDivision>> m) 
	{
		for(DomNode nodeKey : m.keySet())
		{
			if(this.put(nodeKey, m.get(nodeKey)) == null)
			{
				ManagerDivisionEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
			}
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public List<HtmlDivision> remove(Object key)
	{
		this.inputData.remove(key);
		return this.outputData.remove(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() {
		return this.inputData.size();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.HtmlManager#start()
	 */
	@Override
	public void start() 
	{
		control.controlMessage(this.message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.HtmlManager#stop()
	 */
	@Override
	public void stop() {
		
	}
	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<List<HtmlDivision>> values() 
	{
		return this.outputData.values();
	}
}