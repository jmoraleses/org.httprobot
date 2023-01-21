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
import org.httprobot.common.placeholders.operators.html.TableRow;
import org.httprobot.core.controls.html.TableRowControl;
import org.httprobot.core.controls.html.interfaces.IControlTableRowListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IDataMappingImpl;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.html.interfaces.IManagerTableRowListener;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

/**
 * @author joan
 *
 */
public class TableRowManager 
		extends AbstractHtmlManager<TableRow, TableRowControl, IManagerTableRowListener> 
		implements IControlTableRowListener, IDataMappingImpl<DomNode, List<HtmlTableRow>> {

	/**
	 * 4042617260058596374L
	 */
	private static final long serialVersionUID = 4042617260058596374L;
	
	DomNode currentInput;
	List<HtmlTableRow> currentOutput;
	
	Set<DomNode> inputData;
	Map<DomNode, List<HtmlTableRow>> outputData;
	
	/**
	 * 
	 */
	public TableRowManager() 
	{
		super();
		
		//Initialize data members.
		this.inputData = new HashSet<DomNode>();
		this.outputData = new LinkedHashMap<DomNode, List<HtmlTableRow>>();
	}
	/**
	 * @param parent
	 * @param message
	 */
	public TableRowManager(IManagerTableRowListener parent, TableRow message) 
	{
		super(parent, message);
	
		//Initialize data members.
		this.inputData = new HashSet<DomNode>();
		this.outputData = new LinkedHashMap<DomNode, List<HtmlTableRow>>();
		
		//Initialize control member.
		this.control = new TableRowControl(this, message);
		
		//Associate control to parent.
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
	public Set<java.util.Map.Entry<DomNode, List<HtmlTableRow>>> entrySet() 
	{
		return this.outputData.entrySet();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public List<HtmlTableRow> get(Object key) 
	{
		return this.outputData.get(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() 
	{
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
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableRowListener#OnControlTableRow_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableRow_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableRowListener#OnControlTableRow_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableRow_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableRowListener#OnControlTableRow_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableRow_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(this.hasChildManagers())
		{
			//Start each child manager
			while(this.hasNext())
			{
				IManagerImpl manager = this.next();
				manager.start();
			}
			
			ManagerTableRowEvent(new ManagerEventArgs(this, ManagerEventType.STARTED));
		}
		else
		{
			ManagerTableRowEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableRowListener#OnControlTableRow_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableRow_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableRowListener#OnControlTableRow_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableRow_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableRowListener#OnControlTableRow_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableRow_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.HtmlManager#OnManagerTableRow_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerTableRow_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.HtmlManager#OnManagerTableRow_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerTableRow_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.HtmlManager#OnManagerTableRow_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerTableRow_Started(Object sender, ManagerEventArgs e)
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
	public List<HtmlTableRow> put(DomNode key, List<HtmlTableRow> value)
	{
		//Update input data
		this.inputData.add(key);
		
		//Update pointers
		this.currentInput = key;
		this.currentOutput = value;
		
		for(HtmlElement element : key.getHtmlElementDescendants())
		{
			if(element instanceof HtmlTableRow)
			{
				HtmlTableRow tr = HtmlTableRow.class.cast(element);
				
				//TODO criteria to select rows from current message
				if(true)
				{
					value.add(tr);
				}
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
	public void putAll(Map<? extends DomNode, ? extends List<HtmlTableRow>> m) 
	{
		for(DomNode nodeKey : m.keySet())
		{
			if(this.put(nodeKey, m.get(nodeKey)) == null)
			{
				ManagerTableRowEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
			}
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public List<HtmlTableRow> remove(Object key) 
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
	 * @see org.httprobot.core.managers.HtmlManager#start()
	 */
	@Override
	public void start() 
	{
		this.control.controlMessage(this.message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.HtmlManager#stop()
	 */
	@Override
	public void stop() 
	{
		
	}
	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<List<HtmlTableRow>> values() 
	{
		return this.outputData.values();
	}
}
