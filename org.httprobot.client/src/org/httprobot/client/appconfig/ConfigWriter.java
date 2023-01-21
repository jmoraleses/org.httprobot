package org.httprobot.client.appconfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import javax.xml.bind.JAXBException;

import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.definitions.Enums.RmlActionType;
import org.httprobot.common.definitions.Enums.RmlDataType;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.rml.config.Config;
import org.httprobot.common.rml.datatypes.Action;
import org.httprobot.common.rml.datatypes.DataView;
import org.httprobot.common.rml.datatypes.Field;
import org.httprobot.common.rml.datatypes.FieldRef;
import org.httprobot.common.rml.datatypes.Fields;
import org.httprobot.common.rml.datatypes.Rule;
import org.httprobot.common.rml.datatypes.Rules;
import org.httprobot.common.rml.datatypes.ServerInfo;
import org.httprobot.common.rml.datatypes.Step;
import org.httprobot.common.rml.datatypes.Steps;
import org.httprobot.common.rml.datatypes.WebOptions;
import org.httprobot.common.rml.datatypes.operators.Contains;
import org.httprobot.common.rml.datatypes.operators.EndIndex;
import org.httprobot.common.rml.datatypes.operators.GetField;
import org.httprobot.common.rml.datatypes.operators.Remove;
import org.httprobot.common.rml.datatypes.operators.Replace;
import org.httprobot.common.rml.datatypes.operators.StartIndex;
import org.httprobot.common.rml.datatypes.placeholders.HtmlBody;
import org.httprobot.common.rml.datatypes.placeholders.HttpRequest;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class ConfigWriter 
{
	static Config config;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{		
		EnumSet<RuntimeOptions> debug_options = RuntimeOptions.FULL_DEBUG;
		
//		debug_options.add(CliOptions.THROW_EXCEPTIONS);
//		debug_options.add(CliOptions.SHOW_LOAD_EVENTS);
//		
		config = new Config();
		config.setDestinationPath("config.xml");
		config.setInherited(false);
		config.setRuntimeOptions(debug_options);
		
		Boolean inherited = true;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		//get current date time with Date()
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		
		ArrayList<ServerInfo> server_info_list = new ArrayList<ServerInfo>();
		ServerInfo server_info =new ServerInfo();
		server_info.setInherited(inherited);
		server_info.setUuid(null);
		server_info.setLastUpdate(dateFormat.format(date));
		server_info.setServerName("peliculasyonkis");
		server_info.setServerUrl("http://www.peliculasyonkis.com/");
		server_info.setStartUrl("lista-de-peliculas/");
		
		DataView data_view = new DataView();
		data_view.setDbSchema("DB_SCHEMA_NAME");
		data_view.setInherited(inherited);
		data_view.setUuid(null);
		FieldRef field_ref = new FieldRef();
		field_ref.setUuid(null);
		field_ref.setFieldType(RmlDataType.STRING);
		field_ref.setFieldName("DV_FIELDNAME_1");
		FieldRef field_ref2 = new FieldRef();
		field_ref2.setUuid(null);
		field_ref2.setFieldType(RmlDataType.LINK);
		field_ref2.setFieldName("DV_FIELDNAME_2");
		
		ArrayList<FieldRef> field_ref_list = new ArrayList<FieldRef>();
		field_ref_list.add(field_ref);
		field_ref_list.add(field_ref2);
		
		data_view.setFieldRef(field_ref_list);
		
		Steps steps = new Steps();
		steps.setUuid(null);
		steps.setInherited(inherited);
		Step step = new Step();
		step.setUuid(null);
		step.setInherited(inherited);
		Step step2 = new Step();
		step2.setUuid(null);
		step2.setInherited(inherited);
		
		Action action = new Action();
		action.setUuid(null);
		action.setHttpAddress("[@param]");
		action.setMethod("hoalhoalahola");
		action.setType(RmlActionType.GET);
		
		WebOptions webOptions = new WebOptions();
		webOptions.setActiveXNativeEnabled(false);
		webOptions.setAppletEnabled(false);
		webOptions.setPopupBlockerEnabled(false);
		webOptions.setCssEnabled(false);
		webOptions.setJavaScriptEnabled(false);
		webOptions.setPeriodTime(5000);
		webOptions.setBrowserVersion(BrowserVersion.FIREFOX_3_6);
		
		action.setWebOptions(webOptions);
		
		Action action2 = new Action();
		action2.setUuid(null);
		action2.setHttpAddress("[@start_url]/[@param]");
		action.setType(RmlActionType.GET);
		action2.setMethod("adeueueueueu");
		
		action2.setWebOptions(webOptions);
				
		Fields fields = new Fields();
		fields.setUuid(null);
		fields.setInherited(inherited);
		
		Fields fields2 = new Fields();
		fields2.setUuid(null);
		fields2.setInherited(inherited);
		
		ArrayList<Field> field_list = new ArrayList<Field>();
		Field field = new Field();
		field.setFieldName("DV_FIELDNAME_1");
		field.setFieldType(RmlDataType.STRING.toString());
		
		ArrayList<Field> field_list2 = new ArrayList<Field>();
		Field field2 = new Field();
		field2.setFieldName("DV_FIELDNAME_2");
		field2.setFieldType(RmlDataType.LINK.toString());
		
		Rules rules = new Rules();
		rules.setUuid(null);
		rules.setInherited(inherited);
		
		Rules rules2 = new Rules();
		rules2.setUuid(null);
		rules2.setInherited(inherited);
		
		Rule rule = new Rule();
		rule.setUuid(null);
		rule.setInherited(inherited);
		
		Rule rule2 = new Rule();
		rule2.setUuid(null);
		rule2.setInherited(inherited);
				
		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setUuid(null);
		httpRequest.setInherited(inherited);
		
		HtmlBody htmlBody = new HtmlBody();
		htmlBody.setUuid(null);
		htmlBody.setInherited(inherited);
		
		ArrayList<Contains> contains_list = new ArrayList<Contains>();
		Contains contains = new Contains();
		contains.setUuid(null);
		contains.setInherited(inherited);
		contains.setValue("OLA");
		GetField get_field = new GetField();
		get_field.setFieldName("DV_FIELDNAME_1");
		get_field.setValue("OLA");
		contains.setGetField(get_field);		
		contains_list.add(contains);
		
		ArrayList<Replace> replace_list = new ArrayList<Replace>();
		Replace replace = new Replace();
		replace.setNewString("HOLA");
		replace.setOldString("ADEU");
		replace.setInherited(inherited);
		replace.setUuid(null);
		replace_list.add(replace);
		
		GetField get_field3 = new GetField();
		get_field3.setFieldName("DV_FIELDNAME3");
		get_field3.setValue("BLABLABLABLA");
		
		replace.setGetField(get_field3);
		
		ArrayList<Remove> remove_list = new ArrayList<Remove>();		
		Remove remove = new Remove();
		remove.setUuid(null);
		remove.setInherited(inherited);
		EndIndex endindex = new EndIndex();
		endindex.setStringValue("holalalalala");
		StartIndex startIndex = new StartIndex();
		startIndex.setStringValue("Adeueueueueueu");
		GetField get_field2 = new GetField();
		get_field2.setFieldName("DV_FIELDNAME2");
		get_field2.setValue("OLALLALALA");
		remove.setGetField(get_field2);
		
		remove.setEndIndex(endindex);
		remove.setStartIndex(startIndex);
		remove_list.add(remove);
		
		httpRequest.setRemove(remove_list);
		rule2.setHttpRequest(httpRequest);
		
		field2.setRules(rules2);
		field_list2.add(field2);
		
		htmlBody.setReplace(replace_list);
		htmlBody.setContains(contains_list);
		
		httpRequest.setRemove(remove_list);
		
		rule.setHtmlBody(htmlBody);
		ArrayList<Rule> rule_list = new ArrayList<Rule>();		
		rule_list.add(rule);
		rules.setRule(rule_list);
		field.setRules(rules);
		
		ArrayList<Rule> rule_list2 = new ArrayList<Rule>();
		rule_list2.add(rule2);
		rules2.setRule(rule_list2);
		field2.setRules(rules2);
		
		field_list.add(field);		
		field_list.add(field2);
		
		fields.setField(field_list);
		fields2.setField(field_list2);
		
		step.setAction(action);
		step.setFields(fields);
		
		step2.setFields(fields2);
		step2.setAction(action2);
		
		step.setStep(step2);
		
		ArrayList<Step> step_list = new ArrayList<Step>();
		step_list.add(step);
//		step_list.add(step2);
		
		steps.setStep(step_list);
		
		server_info.setDataView(data_view);
		server_info.setSteps(steps);
		
		server_info_list.add(server_info);
		
		config.setServerInfo(server_info_list);		
		
		System.out.println(config.toString());
		File file = new File(config.getDestinationPath());
		WriteAppConfig(file);
	}
	/**
	 * 
	 */
	public static void WriteAppConfig(File file) 
	{
		try 
		{			
			OutputStream os = new FileOutputStream(file);		
			try 
			{
				config.marshal(os);
			} 
			catch (JAXBException e) 
			{
				e.printStackTrace();
			} 
			catch (InconsistenMessageException e) 
			{
				e.printStackTrace();
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}	
	
	public static Config GetTestConfig(File file)
	{
		Config config = new Config();
		config.setDestinationPath("config.xml");
		config.setUuid(null);
		config.setInherited(false);
		
		
		ArrayList<ServerInfo> serversInfos = new ArrayList<ServerInfo>();
		
		ServerInfo serverInfo = new ServerInfo();
		serversInfos.add(serverInfo);
		
		config.setServerInfo(serversInfos);
		return config;
	}
}
