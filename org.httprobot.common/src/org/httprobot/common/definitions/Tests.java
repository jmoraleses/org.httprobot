package org.httprobot.common.definitions;

import java.util.ArrayList;

import org.httprobot.common.definitions.Enums.RmlDataType;
import org.httprobot.common.definitions.Enums.RmlActionType;
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
import org.httprobot.common.rml.datatypes.operators.GetField;
import org.httprobot.common.rml.datatypes.operators.Replace;

public class Tests 
{
	public static String Set_Test_Message() 
	{
		ArrayList<ServerInfo> serversinfo = new ArrayList<ServerInfo>();		
		ServerInfo serverinfo = new ServerInfo();
		
		serverinfo.setLastUpdate("LASTUPDATE");
		serverinfo.setServerName("SERVERNAME");
		serverinfo.setServerUrl("SERVERURL");
		serverinfo.setStartUrl("STARTURL");
		
		Steps steps = new Steps();		
		Step step = new Step();		
		ArrayList<Step> steps_list = new ArrayList<Step>();
		Action action = new Action();
		action.setHttpAddress("http://www.google.cat");
		action.setMethod("balbalbalba");
		action.setType(RmlActionType.GET);

		step.setAction(action);
		Rules rules = new Rules();
		ArrayList<Rule> rules_list = new ArrayList<Rule>();
		
		Rules rules2 = new Rules();
		ArrayList<Rule> rules_list2 = new ArrayList<Rule>();
		
		Rule rule = new Rule();
		Rule rule2 = new Rule();
		
		Replace replace = new Replace();
		GetField getfield = new GetField();
		replace.setNewString("NEWSTRING");
		replace.setOldString("OLDSTRING");
		replace.setGetField(getfield);
		
		Replace replace2 = new Replace();		
		GetField getfield2 = new GetField();
		replace2.setNewString("NEWSTRING2");
		replace2.setOldString("OLDSTRING2");
		replace2.setGetField(getfield2);
		
//		rule.setReplace(replace);
		rules_list.add(rule);
		rules.setRule(rules_list);
		
//		rule2.setReplace(replace2);
		rules_list2.add(rule2);
		rules2.setRule(rules_list2);
		
		Field field = new Field();
		Field field2 = new Field();
		Field field3 = new Field();
		
		field.setFieldName("FIELDNAME1");
		field.setRules(rules);
		
		field2.setFieldName("FIELDNAME2");
		field2.setRules(rules2);
		
		field3.setFieldName("FIELDNAME3");
		field3.setRules(rules2);
		
		Fields fields = new Fields();		
		ArrayList<Field> fields_list = new ArrayList<Field>();
		Fields fields2 = new Fields();		
		ArrayList<Field> fields_list2 = new ArrayList<Field>();
		Fields fields3 = new Fields();		
		ArrayList<Field> fields_list3 = new ArrayList<Field>();
		
		fields_list.add(field);
		fields.setField(fields_list);
		
		fields_list2.add(field2);
		fields2.setField(fields_list2);	
		
		fields_list3.add(field3);
		fields3.setField(fields_list3);	
		
		Step step3 = new Step();
		//step3.setFields(fields);
		step3.setAction(action);
		step.setFields(fields);
		step.setStep(step3);
		
		
		Step step2 = new Step();
		step2.setFields(fields2);
		step2.setAction(action);
		
		
		Step step4 = new Step();
		step4.setFields(fields3);
		step4.setAction(action);
		
		step3.setStep(step2);
		step4.setStep(step);
		
		steps_list.add(step);
		steps_list.add(step2);
		steps_list.add(step4);
		steps.setStep(steps_list);
		
		
		DataView dv = new DataView();		
		dv.setDbSchema("DB_SCHEMA_NAME");
		
		ArrayList<FieldRef> fields_ref = new ArrayList<FieldRef>();
		FieldRef field_ref = new FieldRef();
		field_ref.setFieldName("DV_FIELDNAME_1");
		field_ref.setFieldType(RmlDataType.STRING);
		
		FieldRef field_ref2 = new FieldRef();
		field_ref2.setFieldName("DV_FIELDNAME_2");
		field_ref2.setFieldType(RmlDataType.BYTEARRAY);
		
		fields_ref.add(field_ref);
		fields_ref.add(field_ref2);
		
		dv.setFieldRef(fields_ref);
		
		serverinfo.setDataView(dv);
		serverinfo.setSteps(steps);
		
		serversinfo.add(serverinfo);
		
		//System.out.println(serverinfo.toString());
		
		return serverinfo.toString();		
	}
}
