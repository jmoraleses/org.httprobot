/**
 * 
 */
package org.httprobot.core.controls.placeholders;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.placeholders.HtmlUnit;
import org.httprobot.common.placeholders.operators.html.Page;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.PlaceholderData;
import org.httprobot.core.controls.PlaceholderControl;
import org.httprobot.core.controls.html.PageControl;
import org.httprobot.core.controls.html.interfaces.IControlPageListener;
import org.httprobot.core.controls.interfaces.impl.IControlImpl;
import org.httprobot.core.controls.interfaces.impl.IPlaceholderControlImpl;
import org.httprobot.core.controls.placeholders.interfaces.IControlHtmlUnitListener;

/**
 * HtmlBody place holder message control class. Inherits {@link PlaceholderControl}. 
 * <br>
 * @author Joan
 *
 */
@XmlRootElement
public class HtmlUnitControl 
		extends AbstractPlaceholderControl<HtmlUnit, IControlHtmlUnitListener>
		implements IControlPageListener, IPlaceholderControlImpl {
	
	/**
	 * 7718681945574455983L
	 */
	private static final long serialVersionUID = 7718681945574455983L;
	
	protected PageControl pageControl;
	/**
	 * {@link HtmlUnit} place holder message control default class constructor.
	 */
	public HtmlUnitControl()
	{
		super();
		
		//Initialize using data.
		this.message = new HtmlUnit();
		
		//Associate message to control.
		this.addCommandOutputListener(this.message);
	}
	/**
	 * {@link HtmlUnit} place holder message control class constructor.
	 * @param parent {@link IControlListener} the parent
	 * @param htmlUnit {@link HtmlUnit} the message
	 */
	public HtmlUnitControl(IControlHtmlUnitListener parent, HtmlUnit htmlUnit)
	{
		super(parent, htmlUnit);
		
		//Initialize using data.
		this.message = new HtmlUnit();
		
		//Associate message to control.
		this.addCommandOutputListener(this.message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Initialize(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getMessage() != null)
		{
			try
			{
				HtmlUnit placeholder = HtmlUnit.class.cast(e.getMessage());
				
				if(placeholder.getPage() != null)
				{
					Page page = placeholder.getPage();
				
					//If Page message not null instantiate Page message control.
					this.pageControl = new PageControl(this, page);
					
					//Associate child control to parent.
					this.addCommandOutputListener(this.pageControl);
					
					//Store control.
					this.childControls.add(this.pageControl);
				}
				
				//Call AbstractPlaceholder control method
				super.Initialize(sender, e);
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"HtmlBodyControl.OnControl_Init: HtmlUnit message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Load(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {

		//Call AbstractPlaceholder control method
		super.Load(sender, e);
		
		if(e.getMessage() != null)
		{
			try
			{
				HtmlUnit htmlUnit = HtmlUnit.class.cast(e.getMessage());
				
				if(this.hasChildControls())
				{
					while(this.hasNext())
					{
						IControlImpl control = this.next();
						
						if(control.equals(this.pageControl) ?
								htmlUnit.getPage() != null ?
										control.getUuid().equals(htmlUnit.getPage().getUuid()) 
										: false : false) {
							
							//Cast control.
							PageControl pageControl = PageControl.class.cast(control);
							
							//Set the message.
							pageControl.controlMessage(htmlUnit.getPage());
						}
					}
				}

				//Fire event
				CliCommandInputEvent(new CliEventArgs(this, Command.CONTROL_HTML_BODY, htmlUnit));
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"HtmlBodyControl.OnControl_Loaded: HtmlUnit message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		//Call AbstractPlaceholder control method
		super.Read(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Render(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		//Call AbstractPlaceholder control method
		super.Render(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		//Call AbstractPlaceholder control method
		super.Write(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Change(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		super.Change(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControlHtmlUnit_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControlHtmlUnit_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControlHtmlBody_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this))
		{
			this.reset();
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControlHtmlUnit_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControlHtmlUnit_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControlHtmlUnit_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
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
		
		if(e.getSource().equals(this.pageControl))
		{
			if(e.getMessage() instanceof Page)
			{
				this.put(PlaceholderData.PAGE, e.getMessage());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this,
						"\nAbstractPlaceholderControl.OnControlPage_Loaded: Page message expected");	
			}
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
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#put(org.httprobot.core.common.Enums.PlaceholderData, java.lang.Object)
	 */
	@Override
	public Object put(PlaceholderData key, Object value) {

		switch (key) 
		{
			case PAGE:
				this.message.setPage(Page.class.cast(value));
				break;
				
			default:
				break;
		}
		return super.put(key, value);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#remove(java.lang.Object)
	 */
	@Override
	public Object remove(Object key) {
		// TODO Auto-generated method stub
		return super.remove(key);
	}
}