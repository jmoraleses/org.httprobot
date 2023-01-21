package org.httprobot.common.placeholders;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.Placeholder;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.operators.html.Page;

/**
 * 
 * HtmlUnit message class. Inherits {@link Placeholder}.
 * <br>
 * @author Joan
 */
@XmlRootElement
public class HtmlUnit extends Placeholder
{
	/**
	 * 8700195417806441524L
	 */
	private static final long serialVersionUID = 8700195417806441524L;

	Page page;
	/**
	 * @return the page
	 */
	public Page getPage() 
	{
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(Page page) 
	{
		this.page = page;
	}
	
	/**
	 * 
	 */
	public HtmlUnit()
	{
		super();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.Placeholder#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		super.OnObjectUnmarshalled(sender, e);

		setPage(((HtmlUnit)e.getRml()).getPage());
	}	
}