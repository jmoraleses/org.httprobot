package org.httprobot.core.events;

import java.util.Map;
import java.util.UUID;

import org.httprobot.common.definitions.Enums.EventType;
import org.httprobot.common.definitions.Enums.WebLoaderEventType;
import org.httprobot.common.events.EventArgs;
import org.httprobot.common.unit.WebOptions;

import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


/**
 * @author Joan
 * Web requester event arguments class. Inherits {@link EventArgs}.
 */
public class WebLoaderEventArgs extends EventArgs 
{
	/**
	 * -2004198426390153457L
	 */
	private static final long serialVersionUID = -2004198426390153457L;
	private WebOptions currentWebOptions;	
	private HtmlPage receivedPage;
	private WebRequest requestedPage;
	private Map<WebRequest, HtmlPage> resultData;
	private UUID serverUUID;
	private WebLoaderEventType wlet;
	
	/**
	 * WebRequesterEventArgs class constructor. When WebLoaderEventType.COMPLETED.
	 * @param value the value
	 * @param iet {@link WebLoaderEventType}
	 */
	public WebLoaderEventArgs(Object value, Map<WebRequest, HtmlPage> resultData) 
	{
		super(value, EventType.INET);
		this.wlet = WebLoaderEventType.ALL_PAGES_LOADED;
		this.resultData = resultData;
	}
	/**
	 * WebLoaderEventArgs class constructor.
	 * @param value the value
	 * @param wlet {@link WebLoaderEventType}
	 */
	public WebLoaderEventArgs(Object value, WebLoaderEventType wlet) 
	{
		super(value, EventType.INET);
		this.wlet = wlet;
	}
	/**
	 * @param sender
	 * @param currentRequest
	 * @param currentResponse
	 */
	public WebLoaderEventArgs(Object sender, WebRequest currentRequest, HtmlPage currentResponse)
	{
		super(sender, EventType.INET);
		this.wlet = WebLoaderEventType.PAGE_LOADED;
		this.receivedPage = currentResponse;
		this.requestedPage = currentRequest;
	}	
	/**
	 * @return the currentAction
	 */
	public WebOptions getCurrentWebOptions() 
	{
		return currentWebOptions;
	}	
	/**
	 * Gets the Internet event type.
	 * @return {@link WebLoaderEventType}
	 */
	public WebLoaderEventType getIet() 
	{
		return wlet;
	}
	/**
	 * Gets the received page.
	 * @return {@link HtmlPage} the receivedPage
	 */
	public HtmlPage getReceivedPage() 
	{
		return receivedPage;
	}
	/**
	 * @return the requestedPage
	 */
	public WebRequest getRequestedPage() 
	{
		return requestedPage;
	}
	public Map<WebRequest, HtmlPage> getResultData()
	{
		return this.resultData;
	}
	/**
	 * Gets the current working server UUID.
	 * @return {@link UUID}
	 */
	public UUID getUUID() 
	{
		return serverUUID;
	}
}