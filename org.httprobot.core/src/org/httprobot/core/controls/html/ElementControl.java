package org.httprobot.core.controls.html;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.Html;
import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.operators.html.Element;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.HtmlData;
import org.httprobot.core.controls.html.interfaces.IControlElementListener;
import org.httprobot.core.controls.interfaces.impl.IHtmlControlImpl;

/**
 * {@link Element} {@link Html} operator message class. Inherits {@link AbstractHtmlControl}.
 * <br>
 * @author joan
 *
 */
@XmlRootElement
public class ElementControl 
		extends AbstractHtmlControl<Element, IControlElementListener> 
		implements IHtmlControlImpl {
	
	/**
	 * 1188429079391753214L
	 */
	private static final long serialVersionUID = 1188429079391753214L;
	
	/**
	 * 
	 */
	public ElementControl()
	{
		super();
		
		//Initialize message.
		this.message = new Element();
		
		//Associate message to control.
		this.addCommandOutputListener(this.message);
	}
	/**
	 * @param parent
	 * @param element
	 */
	public ElementControl(IControlElementListener parent, Element element) 
	{
		super(parent, element);
		
		//Initialize message.
		this.message = new Element();
		
		//Associate message to control.
		this.addCommandOutputListener(this.message);
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.AbstractHtmlControl#OnControl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Load(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getMessage() != null)
		{
			try
			{
				Element.class.cast(e.getMessage());
				
				//Call abstract method to set child control messages.
				super.Load(sender, e);
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this,
						"ElementControl.OnControl_Loaded: Element message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.AbstractHtmlControl#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Change(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		super.Change(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.AbstractHtmlControl#OnControl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Render(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		super.Render(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.AbstractHtmlControl#OnControl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Initialize(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		super.Initialize(sender, e);
		
		if(e.getMessage() != null)
		{
			
			try
			{
				Element.class.cast(e.getMessage());
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this,
						"ElementControl.OnControlInit: Element message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.AbstractHtmlControl#OnControl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		super.Read(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.AbstractHtmlControl#OnControl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		super.Write(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlElement_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlElement_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlElement_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlElement_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlElement_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlElement_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		//Set control ready to be iterated.
		if(e.getSource().equals(this))
		{
			this.reset();
			
			//Fire input command event
			CliCommandInputEvent(new CliEventArgs(this, Command.CONTROL_ELEMENT, e.getMessage()));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlElement_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlElement_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlElement_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlElement_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlElement_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlElement_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.AbstractHtmlControl#put(org.httprobot.core.common.Enums.HtmlData, java.lang.Object)
	 */
	@Override
	public Object put(HtmlData key, Object value) 
	{
		switch (key)
		{
			case NAME:
				this.message.setId(String.class.cast(value));
				break;
				
			case HREF:
				this.message.setStyle(String.class.cast(value));
				break;
				
			case HREF_LANG:
				this.message.setClassName(String.class.cast(value));
				break;
				
			default:
				break;
		}
		return super.put(key, value);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.AbstractHtmlControl#remove(java.lang.Object)
	 */
	@Override
	public Object remove(Object key) {
		// TODO Auto-generated method stub
		return super.remove(key);
	}

}