package org.httprobot.common.rml.datatypes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.Rml;
import org.httprobot.common.rml.datatypes.placeholders.HttpRequest;
import org.httprobot.common.rml.datatypes.placeholders.HtmlBody;

/**
 * Rule RML object class. Inherits {@link Rml}.
 * @author Joan
 */
@XmlRootElement
public class Rule extends Rml 
{
	/**
	 * 2625470519703484392L
	 */
	private static final long serialVersionUID = 2625470519703484392L;
	private HttpRequest httpRequest;
	private HtmlBody HtmlBody;	
	/**
	 * @return the webRequest
	 */
	public HttpRequest getHttpRequest() {
		return httpRequest;
	}
	/**
	 * @param webRequest the webRequest to set
	 */
	@XmlElement
	public void setHttpRequest(HttpRequest webRequest) {
		httpRequest = webRequest;
	}
	/**
	 * @return the webResponse
	 */
	public HtmlBody getHtmlBody() {
		return HtmlBody;
	}
	/**
	 * @param htmlBody the webResponse to set
	 */
	@XmlElement
	public void setHtmlBody(HtmlBody htmlBody) {
		HtmlBody = htmlBody;
	}
	/**
	 * Rule class default constructor.
	 */
	public Rule() 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
		setHttpRequest(((Rule)e.getRml()).getHttpRequest());
		setHtmlBody(((Rule)e.getRml()).getHtmlBody());
	}
}