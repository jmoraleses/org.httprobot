/**
 * 
 */
package org.httprobot.core.rml.controls.placeholders;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.definitions.Enums.WebResponsePoint;
import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.datatypes.placeholders.WebResponse;
import org.httprobot.core.rml.controls.RmlPlaceholderControl;

/**
 * Web response control class. Inherits {@link RmlPlaceholderControl}.
 * @author Joan
 *
 */
@XmlRootElement
public class WebResponseControl extends RmlPlaceholderControl
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7718681945574455983L;
	
	private WebResponse web_response;
	private Integer item_count = null;
	
	private WebResponsePoint point = null;

	/**
	 * Place holder points to specified attribute.
	 * @return {@link WebResponsePoint} the point
	 */
	public WebResponsePoint getPoint() {
		return point;
	}
	public WebResponseControl()
	{
		super();
	}
	/**
	 * WebResponseControl class constructor.
	 * @param parent {@link IRmlListener} the parent
	 * @param web_response {@link WebResponse} the web response
	 */
	public WebResponseControl(IRmlListener parent, WebResponse web_response)
	{
		super(parent);
		this.web_response = new WebResponse();

		if(web_response != null)
		{
			if((web_response.getReplace() == null) && (web_response.getSubstring() == null) &&
					(web_response.getSplit() == null ) && (web_response.getContains() == null) &&
					(web_response.getEquals() == null) && (web_response.getRemove() == null) &&
					(web_response.getTryparse() == null))
			{
				this.item_count = 0;
				this.setMessage(null);
				this.setIsRendered(true);
			}
			else
			{
				if(web_response.getReplace() != null)
				{
					this.item_count++;
				}			
				if(web_response.getSubstring() != null)
				{
					this.item_count++;
				}
				if(web_response.getSplit() != null)
				{
					this.item_count++;
				}			
				if(web_response.getContains() != null)
				{
					this.item_count++;
				}
				if(web_response.getEquals() != null)
				{
					this.item_count++;
				}			
				if(web_response.getRemove() != null)
				{
					this.item_count++;
				}
				if(web_response.getTryparse() != null)
				{
					this.item_count++;
				}
			}
			this.setMessage(web_response);	
		}
		else
		{
			this.setIsRendered(true);
		}
	}

	/**
	 * Get the item count.
	 * @return {@link Integer} the item_count
	 */
	public Integer getItem_count() {
		return item_count;
	}
	/**
	 * Gets the web response.
	 * @return {@link WebResponse} the web_response
	 */
	public WebResponse getWeb_response() {
		return web_response;
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlInit(Object sender, RmlEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRendered(Object sender, RmlEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnControlRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRead(Object sender, RmlEventArgs e)
			throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnControlWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlWrite(Object sender, RmlEventArgs e)
			throws NotImplementedException, InconsistenMessageException 
	{
		
	}
}