/**
 * 
 */
package org.httprobot.core.managers.html;

import java.util.ArrayList;
import java.util.Collection;
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
import org.httprobot.common.placeholders.operators.html.Anchor;
import org.httprobot.common.placeholders.operators.html.Element;
import org.httprobot.common.placeholders.operators.html.Page;
import org.httprobot.core.common.Enums.HtmlData;
import org.httprobot.core.contents.solr.InputField;
import org.httprobot.core.controls.html.PageControl;
import org.httprobot.core.controls.html.interfaces.IControlPageListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IDataMappingImpl;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.html.interfaces.IManagerPageListener;
import org.httprobot.core.managers.operators.AbstractOperatorManager;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;

/**
 * {@link Page} operator message manager class. Inherits {@link AbstractOperatorManager}.
 * <br>
 * It's {@link IControlPageListener} and {@link IDataMappingImpl}.
 * <br>
 * @author joan
 *
 */
public class PageManager 
		extends AbstractHtmlManager
			<Page, PageControl, IManagerPageListener> 
		implements IControlPageListener, 
			IDataMappingImpl<Map<InputField, List<DomNode>>, Map<InputField, List<DomNode>>> {
	
	/**
	 * 3831514308982506514L
	 */
	private static final long serialVersionUID = 3831514308982506514L;
	/**
	 * <b>Child</b> {@link Page} message manager.
	 */
	protected PageManager pageManager;
	/**
	 * {@link Anchor} message manager.
	 */
	protected AnchorManager anchorManager;
	/**
	 * {@link Element} message manager.
	 */
	protected ElementManager elementManager;
	/**
	 * The current {@link InputField} being created.
	 */
	InputField currentInputField;
	/**
	 * The current {@link DomNode} being treated.
	 */
	DomNode currentInputDomNode;	
	/**
	 * The current input {@link DomNode} list.
	 */
	List<DomNode> currentInputList;
	/**
	 * The current input data pointer.
	 */
	Map<InputField, List<DomNode>> currentInput;
	/**
	 * The current output {@link DomNode} list.
	 */
	List<DomNode> currentOutputList;
	/**
	 * The current output data.
	 */
	Map<InputField, List<DomNode>> currentOutput;	
	/**
	 * The input data.
	 */
	Set<Map<InputField, List<DomNode>>> inputData;
	/**
	 * The output data.
	 */
	Map<Map<InputField, List<DomNode>>, Map<InputField, List<DomNode>>> outputData;
	
	/**
	 * {@link Page} operator message manager default class constructor.
	 */
	public PageManager()
	{
		super();
		
		//Initialize data members
		this.inputData = new HashSet<Map<InputField, List<DomNode>>>();
		this.outputData = new LinkedHashMap<Map<InputField, List<DomNode>>, Map<InputField, List<DomNode>>>();
	}
	/**
	 * {@link Page} operator message manager class constructor.
	 * @param parent {@link IManagerPageListener} the parent
	 * @param message {@link Page} the message
	 */
	public PageManager(IManagerPageListener parent, Page message) 
	{
		super(parent, message);

		//Initialize data members
		this.inputData = new HashSet<Map<InputField, List<DomNode>>>();
		this.outputData = new LinkedHashMap<Map<InputField, List<DomNode>>, Map<InputField, List<DomNode>>>();
		
		//Initialize message control
		this.control = new PageControl(this, message);
		
		//Associate control to it's manager
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
	public Set<java.util.Map.Entry<Map<InputField, List<DomNode>>, 
			Map<InputField, List<DomNode>>>> entrySet() {
		
		return this.outputData.entrySet();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public Map<InputField, List<DomNode>> get(Object key) 
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
	public Iterator<Map<InputField, List<DomNode>>> iterator() 
	{
		return this.inputData.iterator();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<Map<InputField, List<DomNode>>> keySet() 
	{
		return this.inputData;
	}
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
		
		switch (e.getCommand())
		{
		
		case CONTROL_PAGE:
			try 
			{
				// Cast source
				Page page = Page.class.cast(e.getMessage());

				if (this.control.get(HtmlData.PAGE) != null ? 
						this.control.get(HtmlData.PAGE).equals(page) : false) {
					
					this.pageManager = new PageManager(this, page);

					// Add listeners to manager
					this.addCommandOutputListener(this.pageManager);
					this.pageManager.addManagerListener(this);
					this.pageManager.addOperatorListener(this);

					// Store manager
					addChildManager(this.pageManager);
				}
			} 
			catch (ClassCastException ex) 
			{
				ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}
			break;

		case CONTROL_ANCHOR:
			try
			{
				// Cast source
				Anchor anchor = Anchor.class.cast(e.getMessage());

				if (this.control.get(HtmlData.ANCHOR) != null ? 
						this.control.get(HtmlData.ANCHOR).equals(anchor) : false) {
					
					this.anchorManager = new AnchorManager(this, anchor);

					// Add listeners to manager
					this.addCommandOutputListener(this.anchorManager);
					this.anchorManager.addManagerListener(this);
					this.anchorManager.addOperatorListener(this);

					// Store manager
					addChildManager(this.anchorManager);
				}
			} 
			catch (ClassCastException ex) 
			{
				ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}
			break;

		case CONTROL_ELEMENT:
			try 
			{
				// Cast source
				Element element = Element.class.cast(e.getMessage());

				if (this.control.get(HtmlData.ELEMENT) != null ?
						this.control.get(HtmlData.ELEMENT).equals(element) : false) {
					
					this.elementManager = new ElementManager(this, element);

					// Add listeners to manager
					this.addCommandOutputListener(this.anchorManager);
					this.elementManager.addManagerListener(this);
					this.elementManager.addOperatorListener(this);

					// Store manager
					addChildManager(this.elementManager);
				}
			} 
			catch (ClassCastException ex) 
			{
				ManagerEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
			}
			break;

		default:
			break;
		}

		// Call abstract method for other child managers.
		super.OnCommandInput(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.html.AbstractHtmlManager#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlPageListener#OnControlPage_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPage_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlPageListener#OnControlPage_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPage_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlPageListener#OnControlPage_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPage_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(this.hasChildManagers())
		{
			//Start each child manager
			while(this.hasNext())
			{
				IManagerImpl manager = this.next();
				manager.start();
			}
			
			ManagerPageEvent(new ManagerEventArgs(this, ManagerEventType.STARTED));
		}
		else
		{
			ManagerPageEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlPageListener#OnControlPage_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPage_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlPageListener#OnControlPage_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPage_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlPageListener#OnControlPage_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPage_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.HtmlManager#OnManagerAnchor_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAnchor_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.HtmlManager#OnManagerAnchor_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAnchor_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		if(e.getSource().equals(this.anchorManager))
		{
			for(DomNode nodeKey : this.anchorManager)
			{
				List<HtmlAnchor> anchorList = this.anchorManager.get(nodeKey);
				
				for(HtmlAnchor anchor : anchorList)
				{
					//Store found elements.
					this.currentOutputList.add(anchor);
				}
			}
			
			//If it's the last PageManager send event to parent.
			if(this.pageManager == null)
			{
				ManagerPageEvent(new ManagerEventArgs(this, this.currentInputField));
			}
		}
	}	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.HtmlManager#OnManagerAnchor_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAnchor_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.HtmlManager#OnManagerElement_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerElement_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.HtmlManager#OnManagerElement_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerElement_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.HtmlManager#OnManagerElement_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerElement_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.HtmlManager#OnManagerPage_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerPage_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.HtmlManager#OnManagerPage_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerPage_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.HtmlManager#OnManagerPage_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerPage_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		//Current manager.
		if(sender.equals(this))
		{
			this.reset();
		}
		//Child manager.
		else if(sender.equals(this.pageManager))
		{
			
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Map<InputField, List<DomNode>> put(Map<InputField, List<DomNode>> key, 
			Map<InputField, List<DomNode>> value) {
		
		//Update input data.
		this.inputData.add(key);
		
		//Update pointers.
		this.currentInput = key;
		this.currentOutput = value;
		
		for(InputField inputKey : key.keySet())
		{
			//Update current InputField and DomNode list being treated.
			this.currentInputField = inputKey;			
			this.currentInputList = key.get(inputKey);
			
			for(DomNode domNode : this.currentInputList)
			{
				//Update current input DomNode.
				this.currentInputDomNode = domNode;
				
				//Initialize current output DomNode list.
				this.currentOutputList = new ArrayList<DomNode>();
				
				//Update current output data.
				value.put(this.currentInputField, this.currentOutputList);
				
				//Check Element message manager.
				if(this.elementManager != null)
				{
					//Initialize Element manager's output data.
					List<HtmlElement> elementManagerOutputaData = new ArrayList<HtmlElement>();
					
					//Put current DomNode
					this.elementManager.put(this.currentInputDomNode, elementManagerOutputaData);
				}
				
				//Check Anchor message manager.
				if(this.anchorManager != null)
				{
					//Initialize Anchor manager's output data.
					List<HtmlAnchor> anchorManagerOutputData = new ArrayList<HtmlAnchor>();
					
					//Put data to Anchor manager
					this.anchorManager.put(this.currentInputDomNode, anchorManagerOutputData);
				}
				
				//Check child Page message manager.
				if(this.pageManager != null)
				{
					//Initialize new Page message manager's output data.
					Map<InputField, List<DomNode>> pageManagerOutputData = new LinkedHashMap<InputField, List<DomNode>>();
					
					//Put next page manager input data as current manager's output data.
					this.pageManager.put(value, pageManagerOutputData);
				}
			}
		}
		
		//Update output data.
		value = this.outputData.put(key, value);
		
		//Send FINISHED event to parent.
		ManagerPageEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
		
		return value;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends Map<InputField, List<DomNode>>, 
			? extends Map<InputField, List<DomNode>>> m) {
		
		for(Map<InputField, List<DomNode>> map : m.keySet())
		{
			//Call put method for each DomNode map
			if(this.put(map, m.get(map)) == null)
			{
				ManagerPageEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
			}
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public Map<InputField, List<DomNode>> remove(Object key) 
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
	public Collection<Map<InputField, List<DomNode>>> values() 
	{
		return this.outputData.values();
	}
}