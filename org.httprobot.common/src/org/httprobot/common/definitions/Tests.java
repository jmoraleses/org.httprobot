package org.httprobot.common.definitions;

import java.util.ArrayList;
import java.util.Date;

import org.httprobot.common.datatypes.DataSource;
import org.httprobot.common.datatypes.Document;
import org.httprobot.common.datatypes.Field;
import org.httprobot.common.datatypes.FieldRoot;
import org.httprobot.common.datatypes.DocumentRoot;
import org.httprobot.common.placeholders.operators.Replace;
import org.httprobot.common.unit.Action;

public class Tests 
{
	public static String Set_Test_Message() 
	{
		ArrayList<DataSource> serversinfo = new ArrayList<DataSource>();		
		DataSource serverinfo = new DataSource();
		
		serverinfo.setNTP(new Date());
		serverinfo.setSourceName("SERVERNAME");
//		serverinfo.setServerUrl("SERVERURL");
//		serverinfo.setStartUrl("STARTURL");
//		
		DocumentRoot steps = new DocumentRoot();		
		Document step = new Document();		
		ArrayList<Document> steps_list = new ArrayList<Document>();
		Action action = new Action();
		action.setHttpAddress("http://www.google.cat");
		action.setMethod("balbalbalba");
//		action.setType(RmlRequestType.GET);

		step.setAction(action);
		
		Replace replace = new Replace();
		replace.setNewString("NEWSTRING");
		replace.setOldString("OLDSTRING");
		
		Replace replace2 = new Replace();
		replace2.setNewString("NEWSTRING2");
		replace2.setOldString("OLDSTRING2");
		
		Field field = new Field();
		Field field2 = new Field();
		Field field3 = new Field();

		FieldRoot fields = new FieldRoot();		
		ArrayList<Field> fields_list = new ArrayList<Field>();
		FieldRoot fields2 = new FieldRoot();		
		ArrayList<Field> fields_list2 = new ArrayList<Field>();
		FieldRoot fields3 = new FieldRoot();		
		ArrayList<Field> fields_list3 = new ArrayList<Field>();
		
		fields_list.add(field);
		fields.setField(fields_list);
		
		fields_list2.add(field2);
		fields2.setField(fields_list2);	
		
		fields_list3.add(field3);
		fields3.setField(fields_list3);	
		
		Document step3 = new Document();
		//step3.setFields(fields);
		step3.setAction(action);
		step.setFieldRoot(fields);
		step.setDocument(step3);
		
		
		Document step2 = new Document();
		step2.setFieldRoot(fields2);
		step2.setAction(action);
		
		
		Document step4 = new Document();
		step4.setFieldRoot(fields3);
		step4.setAction(action);
		
		step3.setDocument(step2);
		step4.setDocument(step);
		
		steps_list.add(step);
		steps_list.add(step2);
		steps_list.add(step4);
		steps.setDocument(steps_list);
		
		serverinfo.setDocumentRoot(steps);
		
		serversinfo.add(serverinfo);
		
		//System.out.println(serverinfo.toString());
		
		return serverinfo.toString();		
	}
}
