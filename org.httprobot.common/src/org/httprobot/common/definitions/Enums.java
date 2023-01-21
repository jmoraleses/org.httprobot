package org.httprobot.common.definitions;

import java.util.ArrayList;
import java.util.EnumSet;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

public class Enums 
{	
	/**
	 * @author Joan
	 *
	 */
	public enum CliEventType
	{
		INPUT,
		OUTPUT
	}
	
	/**
	 * The CLI name space of an instance
	 * @author joan
	 *
	 */
	public enum CliNamespace
	{
		CLIENT,
		DATA,
		INET,
		RML,
		SERVER,
		UI,
		REQUESTER
	}
	@XmlType(name = "WebBrowserVersion")
	@XmlEnum
	public enum WebBrowserVersion
	{
		FIREFOX_17,
		DEFAULT
	}
	
	/**
	 * {@link RuntimeOptions}
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
		
		
		public static final EnumSet<RuntimeOptions> DATA_DEBUG 
		= EnumSet.of(DATA_CHECK, SHOW_LOAD_EVENTS, SHOW_RENDER_EVENTS, THROW_EXCEPTIONS);
		
		public static final EnumSet<RuntimeOptions> DEFAULT_DEBUG 
		= EnumSet.of(RML_CHECK, INET_CHECK, DATA_CHECK, UI_CHECK, CLIENT_CHECK);
		
		public static final EnumSet<RuntimeOptions> FULL_DEBUG
		= EnumSet.range(RML_CHECK, THROW_EXCEPTIONS);
		
		public static final EnumSet<RuntimeOptions> INET_DEBUG
		= EnumSet.of(INET_CHECK, SHOW_LOAD_EVENTS, SHOW_RENDER_EVENTS, THROW_EXCEPTIONS);
		
		public static final EnumSet<RuntimeOptions> RML_DEBUG 
		= EnumSet.of(RML_CHECK, SHOW_LOAD_EVENTS, SHOW_RENDER_EVENTS, THROW_EXCEPTIONS);

		
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
	 * @author Joan
	 * Commands received by application managers
	 */
	public enum Command
	{
		ACTION_CONTROL,
		CONDITION_CONTROL,
		CONFIG_CONTROL,
		DATA_VIEW_CONTROL,
		DELIMITERS_CONTROL,
		END_INDEX_CONTROL,
		FIELD_CONTROL,
		FIELD_REF_CONTROL,
		FIELDS_CONTROL,
		FOR_EACH_CONTROL,
		GET_FIELD_CONTROL,
		LOG_CONTROL,
		MESSAGE,
		RULE_CONTROL,
		RULES_CONTROL,
		RUN_WEB_REQUEST,
		SERVER_INFO_CONTROL,
		SESSION_CONTROL,
		SET_DESTINATION_PATH,
		SET_INHERITANCE,
		SET_OPTIONS,
		START_INDEX_CONTROL,
		STEP_CONTROL,
		STEPS_CONTROL,
		TREAT_WEB_RESPONSE,
		WHERE_CONTROL,
		CONTAINS_CONTROL,
		EQUALS_CONTROL,
		REMOVE_CONTROL,
		REPLACE_CONTROL,
		SELECT_CONTROL,
		SPLIT_CONTROL,
		SUBSTRING_CONTROL,
		TRY_PARSE_CONTROL,
		XML_QUERY_CONTROL,
		HTML_BODY_CONTROL,
		HTTP_REQUEST_CONTROL
	}
	/**
	 * @author Joan
	 * Event type.
	 */
	public enum EventType
	{
		CONFIG,
		INET,
		MANAGER,
		PROGRAM_DATA,		
		RML,
		RML_MARSHALLER,
		UI,
		WEB_REQUESTER,
		REQUESTER
	}
	public enum RequesterEventType
	{
		DATA_ROW_CAPTURED,
		
	}
	public enum InetEventType 
	{
		SERVER_INFO_ERROR,
		SERVER_INFO_STARTED,
		SERVER_INFO_STOPPED,
		DATA_ROW_CAPTURED,
	}
	/**
	 * @author Joan
	 * Marshaler event type
	 */
	public enum MarshallerEventType
	{
		OBJECT_MARSHALLED,
		OBJECT_UNMARSHALLED
	}
	public enum PlaceholderEventType
	{
		WEB_REQUEST,
		WEB_RESPONSE,
	}
	@XmlEnum
	public enum PlaceholderPointer
	{
		@XmlEnumValue(value = "HTML_BODY")
		HTML_BODY,
		@XmlEnumValue(value = "HTTP_ADDRESS")
		HTTP_ADDRESS,
	}
	/**
	 * @author Joan
	 * Program data event type.
	 */
	public enum ProgramDataEventType
	{
		CAPTURE_TABLE,
		DEBUG_OPTIONS,
		SERVERS_INFO,
		XML_CONFIG
	}
	/**
	 * @author Joan
	 * RML action possible values.
	 */
	@XmlEnum
	public enum RmlActionType
	{
		@XmlEnumValue(value = "AJAX")
		AJAX,
		@XmlEnumValue(value = "GET")
		GET,
		@XmlEnumValue(value = "POST")
		POST
	}
	/**
	 * @author Joan
	 * Data types used to define the captures.
	 */
	@XmlEnum
	public enum RmlDataType
	{
		@XmlEnumValue(value = "BOOLEAN")
		BOOLEAN,
		@XmlEnumValue(value = "BYTEARRAY")
		BYTEARRAY,
		@XmlEnumValue(value = "DATETIME")
		DATETIME,
		@XmlEnumValue(value = "IMAGE")
		IMAGE,
		@XmlEnumValue(value = "LINK")
		LINK,
		@XmlEnumValue(value = "NUMBER")
		NUMBER,
		@XmlEnumValue(value = "STRING")
		STRING
	}
	/**
	 * @author Joan
	 * RML event type.
	 */
	public enum RmlEventType
	{
		CHANGE,
		INIT,
		LOAD,
		READ,
		RENDER,
		WRITE
	}
	public enum RmlManagerEventType
	{
		TASK_COMPLETED
	}

	/**
	 * @author Joan
	 * RML operations.
	 */
	public enum RmlOperation
	{
		LOGINREQUEST,
		LOGINRESPONSE,
		SERVERLISTREQUEST,
		SERVERLISTRESPONSE		
	}

	/**
	 * @author Joan
	 * RML operators.
	 */
	public enum RmlOperator
	{
		CONTAINS,
		EQUALS,
		REMOVE,
		REPLACE,
		SPLIT,
		SUBSTRING,
		TRYPARSE
	}
	/**
	 * @author Joan
	 * UI manager event type.
	 */
	public enum UiEventType
	{
		CONNECT,
		EXIT,
		OPEN
	}	
	/**
	 * @author Joan
	 * Internet manager event type
	 */
	public enum WebLoaderEventType
	{		
		ADDED,
		COMPLETED,
		CHANGED,
		ERROR,
		REMOVED,
		STARTED,
		STOPPED,
		READY
	}	
	public enum WebRequestPoint
	{
		HTTP_REQUEST
	}
}
