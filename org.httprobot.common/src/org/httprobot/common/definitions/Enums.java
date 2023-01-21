package org.httprobot.common.definitions;

import java.util.ArrayList;
import java.util.EnumSet;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

public class Enums 
{	
	/**
	 * The command line name space of an instance
	 * @author joan
	 *
	 */
	public enum CliNamespace
	{
		CLIENT,
		DATA,
		INET,
		PRECURSOR,
		RML,
		SERVER,
		UI
	}
	/**
	 * The command line Commands
	 * @author Joan
	 * 
	 */
	public enum Command
	{
		ADD_PARAMETER,
		CONTROL_ACTION,
		CONTROL_CONFIG,
		CONTROL_CONTAINS,
		CONTROL_CONTENT_TYPE,
		CONTROL_CONTENT_TYPE_REF,
		CONTROL_CONTENT_TYPE_ROOT,
		CONTROL_DATA_SOURCE,
		CONTROL_DATA_VIEW,
		CONTROL_DELIMITERS,
		CONTROL_END_INDEX,
		CONTROL_EQUALS,
		CONTROL_FIELD,
		CONTROL_FIELD_REF,
		CONTROL_FIELDS,
		CONTROL_HTML_BODY,
		CONTROL_HTTP_REQUEST,
		CONTROL_LOG,
		CONTROL_PAGINATOR,
		CONTROL_REMOVE,
		CONTROL_REPLACE,
		CONTROL_SERVICE_CONNECTION,
		CONTROL_SESSION,
		CONTROL_SPLIT,
		CONTROL_START_INDEX,
		CONTROL_DOCUMENT,
		CONTROL_DOCUMENT_ROOT,
		CONTROL_SUBSTRING,
		CONTROL_TRY_PARSE,
		CONTROL_WEB_OPTIONS,
		CONTROL_PAGE,
		CONTROL_ANCHOR,
		CONTROL_ELEMENT,
		CONTROL_DIVISION,
		CONTROL_TABLE,
		CONTROL_TABLE_ROW,
		CONTROL_TABLE_CELL,
		MESSAGE,
		RUN_WEB_REQUEST,
		SET_DESTINATION_PATH,
		SET_UUID,
		SET_OPTIONS,
		TREAT_WEB_RESPONSE
	}
	/**
	 * The content event type.
	 * @author joan
	 *
	 */
	public enum ContentEventType
	{
		DOCUMENT_ADDED,
		DOCUMENT_REMOVED,
		DOCUMENT_COMPLETED,
		FIELD_ADDED,
		FIELD_REMOVED,
		FIELD_CHANGED
	}
	/**
	 * Control event type.
	 * @author Joan
	 * 
	 */
	public enum ControlEventType
	{
		CHANGE,
		INIT,
		LOAD,
		READ,
		RENDER,
		WRITE
	}
	/**
	 * Document types used to define the captures.
	 * @author Joan
	 * 
	 */
	@XmlEnum
	public enum DataType
	{
		@XmlEnumValue(value = "BASE64")
		BASE64,
		@XmlEnumValue(value = "BOOLEAN")
		BOOLEAN,
		@XmlEnumValue(value = "DATETIME")
		DATETIME,
		@XmlEnumValue(value = "IMAGE")
		IMAGE,
		@XmlEnumValue(value = "LINK")
		LINK,
		@XmlEnumValue(value = "NUMBER")
		NUMBER,
		@XmlEnumValue(value = "TEXT")
		TEXT,
		@XmlEnumValue(value = "UUID")
		UUID,
		@XmlEnumValue(value = "XML")
		XML
	}
	/**
	 * Field types used to define the captures.
	 * @author joan
	 *
	 */
	@XmlEnum
	public enum FieldType
	{
		STRING,
		INTEGER,
		FLOAT,
		DATETIME,
		BOOLEAN,
		URL,
		UUID,
		BASE64,
	}
	/**
	 * The event type.
	 * @author Joan
	 * 
	 */
	public enum EventType
	{
		CONFIG,
		CONTENT,
		INET,
		MANAGER,		
		PROGRAM_DATA,
		REQUESTER,
		RML,
		RML_MARSHALLER,
		UI,
		WEB_REQUESTER
	}
	/**
	 * RML action possible values.
	 * @author Joan
	 * 
	 */
	@XmlEnum
	public enum HttpRequestType
	{
		@XmlEnumValue(value = "GET")
		GET,
		@XmlEnumValue(value = "POST")
		POST
	}
	/**
	 * The manager error codes.
	 * @author joan
	 *
	 */
	public enum ManagerErrorCode
	{
		BAD_MESSAGE,
		BAD_URL,
		BAD_CONTENT_TYPE,
		BAD_FIELD_REF,
		CONNECTION,
		INPUT_DATA_NULL,
		NULL_DATA,
		NULL_POINTER,
		STACK_CONFLICT,
		UNABLE_TO_CAST,
		NOT_IMPLEMENTED,
	}
	/**
	 * The manager event types.
	 * @author joan
	 *
	 */
	public enum ManagerEventType 
	{
		BANNED_ADDED,
		DATA_SOURCE_LIST_LOADED,
		DATA_SOURCE_LOADED,
		DOCUMENT_COMPLETED,
		DOCUMENT_INITIALIZED,
		FIELD_COMPLETED,
		ERROR,
		FINISHED,
		PAGE_LOADED,
		PARAM_ADDED,
		STARTED,
		SYSTEM_CONTENT_TYPE_ROOT_LOADED
	}
	/**
	 * The marshaler event type.
	 * @author Joan
	 * 
	 */
	public enum MarshallerEventType
	{
		OBJECT_MARSHALLED,
		OBJECT_UNMARSHALLED
	}
	/**
	 * The operations.
	 * @author Joan
	 * 
	 */
	public enum Operation
	{
		LOGINREQUEST,
		LOGINRESPONSE,
		SERVERLISTREQUEST,
		SERVERLISTRESPONSE		
	}

	/**
	 * The parameter type
	 * @author joan
	 *
	 */
	@XmlEnum
	public enum ParameterType
	{
		@XmlEnumValue(value = "href-banned")
		BANNED_WORD,
		@XmlEnumValue(value = "href-constant")
		CONSTANT
	}
	/**
	 * The place holder event type.
	 * @author joan
	 *
	 */
	public enum PlaceholderEventType
	{
		WEB_REQUEST,
		WEB_RESPONSE,
	}
	/**
	 * The precursor event type
	 * @author joan
	 *
	 */
	public enum PrecursorEventType
	{
		DATA_ROW_CAPTURED,
	}
	/**
	 * The program data event type.
	 * @author Joan
	 * 
	 */
	public enum ProgramDataEventType
	{
		CAPTURE_TABLE,
		DEBUG_OPTIONS,
		SERVERS_INFO,
		XML_CONFIG
	}
	/**
	 * The runtime options
	 * @author Joan
	 * 
	 */
	@XmlType(name = "RuntimeOptions")
	@XmlEnum
	public enum RuntimeOptions
	{
		CLIENT_CHECK,
		DATA_CHECK,
		INET_CHECK,
		NULL_STATE,
		RML_CHECK,
		SERVER_CHECK,
		SHOW_LOAD_EVENTS,
		SHOW_RENDER_EVENTS,
		THROW_EXCEPTIONS,		
		UI_CHECK;		
		
		/**
		 * Shows controller message events
		 */
		public static final EnumSet<RuntimeOptions> CONTROL_DEBUG 
		= EnumSet.of(RML_CHECK, SHOW_LOAD_EVENTS, SHOW_RENDER_EVENTS, THROW_EXCEPTIONS);
		
		/**
		 * Shows data message events.
		 */
		public static final EnumSet<RuntimeOptions> DATA_DEBUG 
		= EnumSet.of(DATA_CHECK, SHOW_LOAD_EVENTS, SHOW_RENDER_EVENTS, THROW_EXCEPTIONS);
		
		/**
		 * Default runtime options configuration
		 */
		public static final EnumSet<RuntimeOptions> DEFAULT_DEBUG 
		= EnumSet.of(RML_CHECK, INET_CHECK, DATA_CHECK, UI_CHECK, CLIENT_CHECK);
		
		/**
		 * Shows all messages events
		 */
		public static final EnumSet<RuntimeOptions> FULL_DEBUG
		= EnumSet.range(RML_CHECK, THROW_EXCEPTIONS);
		
		/**
		 * Shows Internet message events
		 */
		public static final EnumSet<RuntimeOptions> INTERNET_DEBUG
		= EnumSet.of(INET_CHECK, SHOW_LOAD_EVENTS, SHOW_RENDER_EVENTS, THROW_EXCEPTIONS);
		
		/**
		 * PreLoad state.
		 */
		public static final EnumSet<RuntimeOptions> EMPTY_DEBUG = EnumSet.of(NULL_STATE);
		
		/**
		 * @param options {@link ArrayList} the options
		 * @return {@link EnumSet} the options
		 */
		public static EnumSet<RuntimeOptions> toEnumSet(ArrayList<RuntimeOptions> options)
		{
			EnumSet<RuntimeOptions> enum_set = EnumSet.of(NULL_STATE);

			if(options != null)
			{
				if(options.size() > 0)
				{
					if(enum_set.addAll(options))
					{
						enum_set.remove(NULL_STATE);
						
						return enum_set;
					}
					else
					{
						return DEFAULT_DEBUG;	
					}
				}
				else
				{
					return DEFAULT_DEBUG;
				}
			}
			else
			{
				return null;
			}
		}
	}
	/**
	 * Web service loader event types.
	 * @author joan
	 *
	 */
	public enum ServiceLoaderEventType
	{
		DATA_SOURCE_LIST_LOADED,
		DATA_SOURCE_LOADED,
		READY,
		SYSTEM_CONTENT_TYPES_LOADED,
		WEB_SERVICE_ERROR
	}
	/**
	 * The status types.
	 * @author joan
	 *
	 */
	public enum SolrConnectionStatus
	{
		CONNECTED,
		DISCONNECTED,
		ERROR
	}
	/**
	 * 
	 * UI manager event type.
	 * @author Joan
	 */
	public enum UiEventType
	{
		CONNECT,
		EXIT,
		OPEN
	}	
	@XmlType(name = "UnitBrowserVersion")
	@XmlEnum
	public enum UnitBrowserVersion
	{
		DEFAULT,
		FIREFOX_17
	}
	/**
	 * Web loader manager event type
	 * @author Joan
	 */
	public enum WebLoaderEventType
	{		
		ADDED,
		ALL_PAGES_LOADED,
		CHANGED,
		PAGE_LOADED,
		READY,
		REMOVED,
		RML_ERROR,
		STARTED,
		STOPPED,
		URL_ERROR,
	}	


}
