package org.httprobot.core.controls.html;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.operators.html.Anchor;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.HtmlData;
import org.httprobot.core.controls.html.interfaces.IControlAnchorListener;
import org.httprobot.core.controls.interfaces.impl.IHtmlControlImpl;

/**
 * {@link Anchor} message control class. Inherits {@link AbstractHtmlControl}.
 * <br>
 * @author joan
 *
 */
@XmlRootElement
public class AnchorControl 
	extends AbstractHtmlControl<Anchor, IControlAnchorListener> 
	implements IHtmlControlImpl {
	
	/**
	 * 1188429079391753214L
	 */
	private static final long serialVersionUID = 1188429079391753214L;

	/**
	 * {@link Anchor} message control default class constructor.
	 */
	public AnchorControl()
	{
		super();
		
		//Initialize message
		this.message = new Anchor();
		
		//Associate message to control.
		this.addCommandOutputListener(this.message);
	}
	/**
	 * {@link Anchor} message control class constructor.
	 * @param parent {@link IControlAnchorListener} the parent
	 * @param pageAnchor {@link Anchor} the message
	 */
	public AnchorControl(IControlAnchorListener parent, Anchor pageAnchor) 
	{
		super(parent, pageAnchor);

		//Initialize message
		this.message = new Anchor();
		
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
				Anchor anchor = Anchor.class.cast(e.getMessage());
				
				if(anchor.getHref() != null)
				{
					//Store attribute.
					this.put(HtmlData.HREF, anchor.getHref());
				}
				if(anchor.getHrefLang() != null)
				{
					//Store attribute.
					this.put(HtmlData.HREF_LANG, anchor.getHrefLang());
				}
				if(anchor.getTextContent() != null)
				{
					//Store attribute.
					this.put(HtmlData.TEXT_CONTENT, anchor.getTextContent());
				}
				if(anchor.getName() != null)
				{
					//Store attribute.
					this.put(HtmlData.NAME, anchor.getName());
				}
				if(anchor.getCharset() != null)
				{
					//Store attribute.
					this.put(HtmlData.CHARSET, anchor.getCharset());
				}
				if(anchor.getType()!= null)
				{
					//Store attribute.
					this.put(HtmlData.TYPE, anchor.getType());
				}
				if(anchor.getTarget() != null)
				{
					//Store attribute.
					this.put(HtmlData.TARGET, anchor.getTarget());
				}
				
				//Call super method to set child control messages.
				super.Load(sender, e);
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"AnchorControl.OnControl_Loaded: Anchor message expected");
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
				Anchor.class.cast(e.getMessage());
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"PageAnchorControl.OnControlInit: PageAnchor message expected");
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
	 * @see org.httprobot.core.controls.HtmlControl#OnControlAnchor_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAnchor_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlAnchor_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAnchor_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlAnchor_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAnchor_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this))
		{
			this.reset();
			
			CliCommandInputEvent(new CliEventArgs(this, Command.CONTROL_ANCHOR, e.getMessage()));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlAnchor_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAnchor_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlAnchor_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAnchor_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlAnchor_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAnchor_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#put(org.httprobot.core.common.Enums.HtmlData, java.lang.Object)
	 */
	@Override
	public Object put(HtmlData key, Object value) 
	{
		switch (key)
		{
		case CHARSET:
			this.message.setCharset(String.class.cast(value));
			break;
			
		case NAME:
			this.message.setId(String.class.cast(value));
			break;
			
		case HREF:
			this.message.setStyle(String.class.cast(value));
			break;
			
		case HREF_LANG:
			this.message.setClassName(String.class.cast(value));
			break;
			
		case TEXT_CONTENT:
			this.message.setTextContent(String.class.cast(value));
			break;
			
		case TYPE:
			this.message.setType(String.class.cast(value));
			break;
			
		case TARGET:
			this.message.setTargetAttribute(String.class.cast(value));
			break;
			
		default:
			break;
		}
		
		return super.put(key, value);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#remove(java.lang.Object)
	 */
	@Override
	public Object remove(Object key)
	{
		return super.remove(key);
	}
	
}