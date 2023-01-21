/**
 * 
 */
package org.httprobot.core.managers.html;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.httprobot.common.definitions.Enums.ManagerErrorCode;
import org.httprobot.common.definitions.Enums.ManagerEventType;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.operators.html.Element;
import org.httprobot.core.common.Enums.HtmlData;
import org.httprobot.core.controls.html.ElementControl;
import org.httprobot.core.controls.html.interfaces.IControlElementListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IDataMappingImpl;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.html.interfaces.IManagerElementListener;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;

/**
 * @author joan
 *
 */
public class ElementManager 
	extends AbstractHtmlManager<Element, ElementControl, IManagerElementListener> 
	implements IDataMappingImpl<DomNode, List<HtmlElement>>, IControlElementListener {

	/**
	 * -8541896832904706752L
	 */
	private static final long serialVersionUID = -8541896832904706752L;

	/**
	 * The current input data pointer.
	 */
	DomNode currentInput;
	/**
	 * The current output data pointer.
	 */
	List<HtmlElement> currentOutput;
	/**
	 * The input data.
	 */
	Set<DomNode> inputData;
	/**
	 * The output data.
	 */
	Map<DomNode, List<HtmlElement>> outputData;
	
	/**
	 * 
	 */
	public ElementManager() 
	{
		super();
		
		//Initialize data members.
		this.inputData = new HashSet<DomNode>();
		this.outputData = new LinkedHashMap<DomNode, List<HtmlElement>>();
	}
	/**
	 * @param parent
	 * @param message
	 */
	public ElementManager(IManagerElementListener parent, Element message) 
	{
		super(parent, message);
		
		//Initialize data members.
		this.inputData = new HashSet<DomNode>();
		this.outputData = new LinkedHashMap<DomNode, List<HtmlElement>>();
		
		//Initialize Element message control.
		this.control = new ElementControl(this, message);
		
		//Associate control to manager.
		addCommandOutputListener(this.control);
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.operators.AbstractOperatorManager#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {

		switch (e.getCommand()) 
		{		
			default:
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
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() 
	{
		return this.inputData.size();
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
	public List<HtmlElement> get(Object key) 
	{
		return this.outputData.get(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public List<HtmlElement> put(DomNode key, List<HtmlElement> value)
	{
		//Update input data.
		this.inputData.add(key);
		
		//Update pointers.
		this.currentInput = key;
		this.currentOutput = value;
		
		//If XPath attribute is defined get elements by expression.
		if(this.control.get(HtmlData.X_PATH) != null ? 
				!String.class.cast(this.control.get(HtmlData.X_PATH)).isEmpty() : false) {
			
			List<?> elementsList = key.getByXPath(String.class.cast(this.control.get(HtmlData.X_PATH))); 
			
			for(Object obj : elementsList)
			{
				if(obj instanceof HtmlElement)
				{
					filterElementByAttributeValues(HtmlElement.class.cast(obj), value);
				}
			}
		}
		//Otherwise, find elements by attributes values.
		else
		{
			for(HtmlElement element : key.getHtmlElementDescendants())
			{
				filterElementByAttributeValues(element, value);
			}
		}
		
		//Update output data
		value = this.outputData.put(key, value);
		
		//Send event to parent
		ManagerElementEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
		
		return value;
	}
	/**
	 * Check if current filters match with input element.
	 * @param element {@link HtmlElement} the current element. 
	 * @param value target {@link HtmlElement} list-
	 */
	private void filterElementByAttributeValues(HtmlElement element, List<HtmlElement> value) 
	{	
		Map<HtmlData, Boolean> filterResults = new HashMap<HtmlData, Boolean>();

		if (this.control.get(HtmlData.NODE_NAME) != null) {
			// Store operation result
			filterResults.put(
					HtmlData.NODE_NAME,
					element.getNodeName().contains(
							String.class.cast(this.control.get(HtmlData.NODE_NAME))));
		}
		if (this.control.get(HtmlData.TAG_NAME) != null) {
			// Store operation result
			filterResults.put(
					HtmlData.TAG_NAME,
					element.getTagName().contains(
							String.class.cast(this.control.get(HtmlData.TAG_NAME))));
		}
		if (this.control.get(HtmlData.TEXT_CONTENT) != null) {
			// Store operation result
			filterResults.put(
					HtmlData.TEXT_CONTENT,
					element.getTagName().contains(
							String.class.cast(this.control.get(HtmlData.TEXT_CONTENT))));
		}

		// Initialize final result
		Boolean result = true;

		while (filterResults.keySet().iterator().hasNext()) {
			HtmlData filterKey = filterResults.keySet().iterator().next();
			Boolean filterResult = filterResults.get(filterKey);

			// Inclusive filter for each non-null attribute value.
			if (result && filterResult) {
				result = true;
			}
		}

		if (result) {
			value.add(element);
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public List<HtmlElement> remove(Object key) 
	{
		this.inputData.remove(key);
		return this.outputData.remove(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends DomNode, ? extends List<HtmlElement>> m) {
		
		for(DomNode domNode : m.keySet())
		{
			List<HtmlElement> inputField = m.get(domNode);
			
			if(this.put(domNode, inputField) == null)
			{
				ManagerElementEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
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
	public Set<DomNode> keySet() 
	{
		return this.inputData;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<List<HtmlElement>> values() 
	{
		return this.outputData.values();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<java.util.Map.Entry<DomNode, List<HtmlElement>>> entrySet() 
	{
		return this.outputData.entrySet();
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
	 * @see org.httprobot.core.managers.OperatorManager#OnManagerPageElement_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerElement_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	
		if(sender.equals(this))
		{
			this.reset();			
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.OperatorManager#OnManagerPageElement_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerElement_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.OperatorManager#OnManagerPageElement_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerElement_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlElementListener#OnControlElement_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlElement_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlElementListener#OnControlElement_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlElement_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlElementListener#OnControlElement_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlElement_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		if(this.hasChildManagers())
		{
			//Start each child manager
			while(this.hasNext())
			{
				IManagerImpl manager = this.next();
				manager.start();
			}
			
			ManagerElementEvent(new ManagerEventArgs(this, ManagerEventType.STARTED));
		}
		else
		{
			ManagerElementEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlElementListener#OnControlElement_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlElement_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlElementListener#OnControlElement_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlElement_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlElementListener#OnControlElement_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlElement_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
}
