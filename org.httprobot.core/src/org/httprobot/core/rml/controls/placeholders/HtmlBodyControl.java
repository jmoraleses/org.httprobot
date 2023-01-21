/**
 * 
 */
package org.httprobot.core.rml.controls.placeholders;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.datatypes.placeholders.HtmlBody;
import org.httprobot.core.rml.controls.RmlPlaceholderControl;

/**
 * Web response control class. Inherits {@link RmlPlaceholderControl}.
 * @author Joan
 *
 */
@XmlRootElement
public class HtmlBodyControl extends MainPlaceholderControl<HtmlBody>
{
	/**
	 * 7718681945574455983L
	 */
	private static final long serialVersionUID = 7718681945574455983L;	
	/**
	 * Web response place holder control default class constructor.
	 */
	public HtmlBodyControl()
	{
		super();
	}
	/**
	 * Web response place holder control class constructor.
	 * @param parent {@link IRmlListener} the parent
	 * @param html_body {@link HtmlBody} the web response
	 */
	public HtmlBodyControl(IRmlListener parent, HtmlBody html_body)
	{
		super(parent, html_body);		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		super.OnControlInit(sender, e);
		
		if(e.getMessage() != null)
		{
			//Initialize using data
			this.placeholder = new HtmlBody();
			
			//Associate message to control.
			this.addCommandOutputListener(this.placeholder);			
	
			//Set inherited data
			this.placeholder.setUuid(e.getMessage().getUuid());
			this.placeholder.setInherited(e.getMessage().getInherited());
			this.placeholder.setRuntimeOptions(e.getMessage().getRuntimeOptions());
			
			try
			{
//				HtmlBody htmlBody = HtmlBody.class.cast(e.getMessage());
//				
//				
				
			}
			catch(ClassCastException ex)
			{
				
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		super.OnControlLoaded(sender, e);
		
		if(e.getMessage() != null)
		{
			//TODO Set controlled data for HtmlBody specific messages
		
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnControlRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnControlRead(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		super.OnControlRendered(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnControlWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnControlWrite(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnControlChanged(sender, e);
	}
}