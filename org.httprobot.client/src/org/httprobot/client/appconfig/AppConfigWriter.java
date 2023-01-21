package org.httprobot.client.appconfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.EnumSet;

import javax.xml.bind.JAXBException;

import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.rml.config.AppConfig;
import org.httprobot.common.rml.config.Config;
import org.httprobot.common.rml.datatypes.ServerInfo;
import org.httprobot.common.rml.datatypes.operators.Contains;
import org.httprobot.common.rml.datatypes.operators.Equals;

public class AppConfigWriter 
{
	static AppConfig app_config;
	static EnumSet<RuntimeOptions> debug_options = RuntimeOptions.RML_DEBUG;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{		
//		app_config = new AppConfig();
//		app_config.setDestinationPath("AppConfig2.xml");
//		app_config.setConfigFilePath("config.xml");
//		app_config.setLogFilePath("log.xml");
//		File file = new File(app_config.getDestinationPath());		
//		WriteAppConfig(file);
//		
		
		Contains contains = new Contains();
		Equals equals = new Equals();
		equals.setValue("HOLA");
		contains.setEquals(equals);		
		contains.setValue("CONTAINSSS");
		System.out.println(contains.toString());
		
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
				app_config.marshal(os);
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
