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
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.operators.html.Anchor;
import org.httprobot.core.common.Enums.HtmlData;
import org.httprobot.core.controls.html.AnchorControl;
import org.httprobot.core.controls.html.interfaces.IControlAnchorListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IDataMappingImpl;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.html.interfaces.IManagerAnchorListener;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;

/**
 * {@link Anchor} message manager class. Inherits {@link AbstractHtmlManager}.
 * <br>
 * It's {@link IControlAnchorListener} and {@link IDataMappingImpl}.
 * <br>
 * @author joan
 *
 */
public class AnchorManager 
		extends AbstractHtmlManager<Anchor, AnchorControl, IManagerAnchorListener> 
		implements IControlAnchorListener, IDataMappingImpl<DomNode, List<HtmlAnchor>> {

	/**
	 * 1104049373678531468L
	 */
	private static final long serialVersionUID = 1104049373678531468L;

	/**
	 * The current input data pointer.
	 */
	DomNode currentInput;
	/**
	 * The current output data pointer.
	 */
	List<HtmlAnchor> currentOutput;
	/**
	 * The input data.
	 */
	Set<DomNode> inputData;
	/**
	 * The output data.
	 */
	Map<DomNode, List<HtmlAnchor>> outputData;

	/**
	 * {@link Anchor} message manager default class constructor.
	 */
	public AnchorManager() 
	{
		super();
	}
	/**
	 * {@link Anchor} message manager default class constructor.
	 * @param parent {@link IManagerAnchorListener} the parent
	 * @param message {@link Anchor} the message
	 */
	public AnchorManager(IManagerAnchorListener parent, Anchor message) 
	{
		super(parent, message);
		
		//Initialize data members.
		this.inputData = new HashSet<DomNode>();
		this.outputData = new LinkedHashMap<DomNode, List<HtmlAnchor>>();
		
		//Initialize message control member.
		this.control = new AnchorControl(this, message);
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
	public Set<java.util.Map.Entry<DomNode, List<HtmlAnchor>>> entrySet() 
	{
		return this.outputData.entrySet();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public List<HtmlAnchor> get(Object key) 
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
	 * @see org.httprobot.core.managers.html.AbstractHtmlManager#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
		
		switch (e.getCommand()) 
		{
			default:
				super.OnCommandInput(sender, e);
				break;
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlAnchorListener#OnControlAnchor_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAnchor_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlAnchorListener#OnControlAnchor_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAnchor_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlAnchorListener#OnControlAnchor_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAnchor_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(this.hasChildManagers())
		{
			//Start each child manager
			while(this.hasNext())
			{
				IManagerImpl manager = this.next();
				manager.start();
			}
			
			ManagerAnchorEvent(new ManagerEventArgs(this, ManagerEventType.STARTED));
		}
		else
		{
			ManagerAnchorEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlAnchorListener#OnControlAnchor_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAnchor_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlAnchorListener#OnControlAnchor_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAnchor_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlAnchorListener#OnControlAnchor_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAnchor_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.OperatorManager#OnManagerAnchor_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAnchor_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.OperatorManager#OnManagerAnchor_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAnchor_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.OperatorManager#OnManagerAnchor_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAnchor_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		if(sender.equals(this))
		{
			this.reset();
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public List<HtmlAnchor> put(DomNode key, List<HtmlAnchor> value) 
	{
		//Update input data
		this.inputData.add(key);
		
		//Update pointers
		this.currentInput = key;
		this.currentOutput = value;
		
		for(HtmlElement element : key.getHtmlElementDescendants())
		{
			//<a href="/peliculas/" name="btnVer"></a>
			if(element instanceof HtmlAnchor)
			{
				//Cast as HtmlAnchor and check attributes.
				HtmlAnchor htmlAnchor = HtmlAnchor.class.cast(element);
				
				boolean discard = false;
				
				//If some filter fails discard element by no adding to current value.
				for(HtmlData attribute : this.control)
				{
					if(this.control.get(attribute) != null)
					{
						String attributeValue = String.class.cast(this.control.get(attribute));
						
						switch (attribute)
						{
							//<a charset="foo">
							case CHARSET:
								if(htmlAnchor.getCharsetAttribute() != null ? 
										!htmlAnchor.getCharsetAttribute().contains(attributeValue) : false) {
									
									discard = true;
								}
							//<a href="foo">
							case HREF:
								if(htmlAnchor.getHrefAttribute() != null ?
										!htmlAnchor.getHrefAttribute().contains(attributeValue) : false) {
									
									discard = true;
								}
							//<a hrefLang="foo">
							case HREF_LANG:
								if(htmlAnchor.getHrefLangAttribute() != null ?
										!htmlAnchor.getHrefLangAttribute().contains(attributeValue) : false) {
									
									discard = true;
								}
							//<a name="foo">
							case NAME:
								if(htmlAnchor.getNameAttribute() != null ? 
										!htmlAnchor.getNameAttribute().contains(attributeValue) : false) {
									
									discard = true;
								}
							//<a target="foo">
							case TARGET:
								if(htmlAnchor.getTargetAttribute() != null ? 
										!htmlAnchor.getTargetAttribute().contains(attributeValue) : false) {
									
									discard = true;
								}
							//<a textContent="foo">
							case TEXT_CONTENT:
								if(htmlAnchor.getTargetAttribute() != null ?
										!htmlAnchor.getTargetAttribute().contains(attributeValue) : false) {
									
									discard = true;
								}								
							//<a type="foo">
							case TYPE:
								if(htmlAnchor.getTargetAttribute() != null ? 
										!htmlAnchor.getTargetAttribute().contains(attributeValue) : false) {
									
									discard = true;
								}
								
							default:
								if(!discard)
								{
									value.add(htmlAnchor);
								}
								break;
						}
					}
				}
			}
		}
		
		//Update output data
		value = this.outputData.put(key, value);

		//Send FINISHED event to parent.
		ManagerAnchorEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
		
		return value;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends DomNode, ? extends List<HtmlAnchor>> m) 
	{
		for(DomNode domNode : m.keySet())
		{
			List<HtmlAnchor> list = m.get(domNode);
			
			if(this.put(domNode, list) == null)
			{
				ManagerAnchorEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
			}
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public List<HtmlAnchor> remove(Object key)
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
	public Collection<List<HtmlAnchor>> values() 
	{
		return this.outputData.values();
	}
}