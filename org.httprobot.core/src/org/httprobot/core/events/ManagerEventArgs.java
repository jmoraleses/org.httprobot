package org.httprobot.core.events;

import java.util.Dictionary;

import org.httprobot.common.config.DataSourceList;
import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.common.datatypes.DataSource;
import org.httprobot.common.definitions.Enums.EventType;
import org.httprobot.common.definitions.Enums.ManagerErrorCode;
import org.httprobot.common.definitions.Enums.ManagerEventType;
import org.httprobot.common.events.EventArgs;
import org.httprobot.core.contents.solr.InputDocument;
import org.httprobot.core.contents.solr.InputField;
import org.httprobot.core.managers.Manager;

import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Message manager event arguments class. Inherits {@link EventArgs}.
 * @author joan
 *
 */
public class ManagerEventArgs extends EventArgs 
{
	/**
	 *  -305874349827454967L
	 */
	private static final long serialVersionUID = -305874349827454967L;

	ManagerEventType eventType;
	WebRequest currentRequest;
	HtmlPage currentResponse;
	Dictionary<WebRequest, HtmlPage> capturedData;
	ManagerErrorCode errorCode;
	InputDocument inputDocument;
	InputField inputField;
	DataSource dataSource;
	DataSourceList dataSourceList;
	ContentTypeRoot contentTypeRoot;
	
	public InputDocument getInputDocument()
	{
		return this.inputDocument;
	}
	public ManagerErrorCode getErrorCode()
	{
		return this.errorCode;
	}
	/**
	 * Gets WebLoader's captured Data
	 * @return
	 */
	public Dictionary<WebRequest, HtmlPage> getCapturedData()
	{
		return this.capturedData;
	}
	/**
	 * @return the currentRequest
	 */
	public WebRequest getCurrentRequest()
	{
		return currentRequest;
	}
	/**
	 * @return the currentResponse
	 */
	public HtmlPage getCurrentResponse()
	{
		return currentResponse;
	}
	/**
	 * @return the event type
	 */
	public ManagerEventType getEventType()
	{
		return this.eventType;
	}
	/**
	 * @return the data source
	 */
	public DataSource getDataSource()
	{
		return this.dataSource;
	}
	/**
	 * @return the data source list
	 */
	public DataSourceList getDataSourceList()
	{
		return this.dataSourceList;
	}
	/**
	 * @return the input field.
	 */
	public InputField getInputField()
	{
		return this.inputField;
	}
	/**
	 * @return the content types
	 */
	public ContentTypeRoot getContentTypeRoot()
	{
		return this.contentTypeRoot;
	}
	/**
	 * {@link Manager} event.
	 * @param source the source
	 * @param et {@link ManagerEventType} the event type
	 */
	public ManagerEventArgs(Object source, ManagerEventType et) 
	{
		super(source, EventType.MANAGER);
		this.eventType = et;
	}
	/**
	 * {@link Manager} FIELD_COMPLETED event.
	 * @param value the source
	 * @param inputField the input field completed
	 */
	public ManagerEventArgs(Object value, InputField inputField)
	{
		super(value, EventType.MANAGER);
		this.eventType = ManagerEventType.FIELD_COMPLETED;
	}
	/**
	 * {@link Manager} ERROR event.
	 * @param source
	 * @param errorCode
	 */
	public ManagerEventArgs(Object source, ManagerErrorCode errorCode)
	{
		super(source, EventType.MANAGER);
		this.eventType = ManagerEventType.ERROR;
		this.errorCode = errorCode;
	}
	/**
	 * {@link Manager} PAGE_LOADED event.
	 * @param source the source
	 * @param request {@link WebRequest} the loaded web request
	 * @param response {@link HtmlPage} the loaded page
	 */
	public ManagerEventArgs(Object source, WebRequest request, HtmlPage response) 
	{
		super(source, EventType.MANAGER);
		this.eventType = ManagerEventType.PAGE_LOADED;
		this.currentRequest = request;
		this.currentResponse = response;
	}
	public ManagerEventArgs(Object sender, Dictionary<WebRequest, HtmlPage> capturedData)
	{
		super(sender, EventType.MANAGER);
		this.eventType = ManagerEventType.FINISHED;
		this.capturedData = capturedData;		
	}
	/**
	 * {@link Manager} DOCUMENT_COMPLETED event.
	 * @param source the source
	 * @param inputDocument
	 */
	public ManagerEventArgs(Object source, InputDocument inputDocument)
	{
		super(source, EventType.MANAGER);
		this.eventType = ManagerEventType.DOCUMENT_COMPLETED;
		this.inputDocument = inputDocument;
	}
	/**
	 * {@link Manager} DATA_SOURCE_LOADED event. 
	 * Fired when a {@link DataSource} has been received.
	 * @param sender the source
	 * @param dataSource {@link DataSource} the message
	 */
	public ManagerEventArgs(Object sender, DataSource dataSource)
	{
		super(sender, EventType.MANAGER);
		this.eventType = ManagerEventType.DATA_SOURCE_LOADED;
		this.dataSource = dataSource;
	}
	/**
	 * {@link Manager} DATA_SOURCE_LIST_LOADED event. 
	 * Fired when the {@link DataSourceList} has been received.
	 * @param source the source
	 * @param dataSourceList {@link DataSourceList} the list.
	 */
	public ManagerEventArgs(Object source, DataSourceList dataSourceList)
	{
		super(source, EventType.MANAGER);
		this.eventType = ManagerEventType.DATA_SOURCE_LIST_LOADED;
		this.dataSourceList = dataSourceList;
	}
	/**
	 * {@link Manager} SYSTEM_CONTENT_TYPE_ROOT_LOADED event. 
	 * Fired when the {@link ContentTypeRoot} has been received.
	 * @param source the source
	 * @param contentTypeRoot {@link ContentTypeRoot} the message.
	 */
	public ManagerEventArgs(Object source, ContentTypeRoot contentTypeRoot)
	{
		super(source, EventType.MANAGER);
		this.eventType = ManagerEventType.SYSTEM_CONTENT_TYPE_ROOT_LOADED;
		this.contentTypeRoot = contentTypeRoot;
	}
}
