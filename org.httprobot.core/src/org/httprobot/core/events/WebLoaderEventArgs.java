package org.httprobot.core.events;

import java.util.UUID;

import org.httprobot.common.definitions.Enums.EventType;
import org.httprobot.common.definitions.Enums.WebLoaderEventType;
import org.httprobot.common.events.EventArgs;

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
	private WebLoaderEventType wlet;	
	private UUID serverUUID;
	private HtmlPage receivedPage;
	/**
	 * Gets the Internet event type.
	 * @return {@link WebLoaderEventType}
	 */
	public WebLoaderEventType getIet() {
		return wlet;
	}
	/**
	 * Gets the current working server UUID.
	 * @return {@link UUID}
	 */
	public UUID getUUID() {
		return serverUUID;
	}	
	/**
	 * Gets the received page.
	 * @return {@link HtmlPage} the receivedPage
	 */
	public HtmlPage getReceivedPage() {
		return receivedPage;
	}
	/**
	 * WebRequesterEventArgs class constructor.
	 * @param value the value
	 * @param iet {@link WebLoaderEventType}
	 */
	public WebLoaderEventArgs(Object value, WebLoaderEventType iet) 
	{
		super(value, EventType.INET);
		this.wlet = iet;
	}
}