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
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.operators.html.TableCell;
import org.httprobot.core.controls.html.TableCellControl;
import org.httprobot.core.controls.html.interfaces.IControlTableCellListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IDataMappingImpl;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.html.interfaces.IManagerTableCellListener;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;

/**
 * @author joan
 *
 */
public class TableCellManager 
		extends AbstractHtmlManager<TableCell, TableCellControl, IManagerTableCellListener> 
		implements IControlTableCellListener, IDataMappingImpl<DomNode, List<HtmlTableCell>> {

	/**
	 * -5284712958371377742L
	 */
	private static final long serialVersionUID = -5284712958371377742L;
	
	DomNode currentInput;
	List<HtmlTableCell> currentOutput;
	
	Set<DomNode> inputData;
	Map<DomNode, List<HtmlTableCell>> outputData;
	
	/**
	 * 
	 */
	public TableCellManager() 
	{
		super();
		
		//Initialize data members
		this.inputData = new HashSet<DomNode>();
		this.outputData = new LinkedHashMap<DomNode, List<HtmlTableCell>>();
	}
	/**
	 * @param parent
	 * @param message
	 */
	public TableCellManager(IManagerTableCellListener parent, TableCell message) 
	{
		super(parent, message);
		
		//Initialize data members.
		this.inputData = new HashSet<DomNode>();
		this.outputData = new LinkedHashMap<DomNode, List<HtmlTableCell>>();
		
		//Initialize control member.
		this.control = new TableCellControl(this, message);
		
		//Associate control to parent.
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
	public Set<java.util.Map.Entry<DomNode, List<HtmlTableCell>>> entrySet() 
	{
		return this.outputData.entrySet();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public List<HtmlTableCell> get(Object key) 
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
				!this.inputData.isEmpty() ? 
						this.outputData != null ? 
								this.outputData.isEmpty() 
								: true
						: true 
				: true;
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
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableCellListener#OnControlTableCell_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableCell_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableCellListener#OnControlTableCell_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableCell_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableCellListener#OnControlTableCell_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableCell_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(this.hasChildManagers())
		{
			//Start each child manager
			while(this.hasNext())
			{
				IManagerImpl manager = this.next();
				manager.start();
			}
			
			ManagerTableCellEvent(new ManagerEventArgs(this, ManagerEventType.STARTED));
		}
		else
		{
			ManagerTableCellEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableCellListener#OnControlTableCell_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableCell_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableCellListener#OnControlTableCell_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableCell_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableCellListener#OnControlTableCell_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableCell_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public List<HtmlTableCell> put(DomNode key, List<HtmlTableCell> value) {

		//Update input data
		this.inputData.add(key);
		
		//Update pointers
		this.currentInput = key;
		this.currentOutput = value;
		
		//TODO set criteria to add value to element list
		for(HtmlElement element : key.getHtmlElementDescendants())
		{
			if(element instanceof HtmlTableCell)
			{
				HtmlTableCell td = HtmlTableCell.class.cast(element);
				value.add(td);
			}
		}
		
		//Update output data
		value = this.outputData.put(key, value);
		
		//Send event to parent
		ManagerTableCellEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
		
		return value;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends DomNode, ? extends List<HtmlTableCell>> m) 
	{
		for(DomNode nodeKey : m.keySet())
		{
			if(this.put(nodeKey, m.get(nodeKey)) == null)
			{
				//Send error report to parent
				ManagerTableCellEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
			}
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public List<HtmlTableCell> remove(Object key) 
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
		return 
			this.outputData != null ? 
					!this.outputData.isEmpty() ? 
							this.outputData.size() 
							: this.inputData != null ? 
									this.inputData.size() 
									: 0 
					: 0;
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
	public void stop() {
		
	}
	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<List<HtmlTableCell>> values() 
	{
		return this.outputData.values();
	}
}
