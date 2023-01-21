package org.httprobot.client.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.EnumSet;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.httprobot.common.config.AppConfig;
import org.httprobot.common.config.Configuration;
import org.httprobot.common.config.ServiceConnection;
import org.httprobot.common.datatypes.DataSource;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.exceptions.InconsistenMessageException;

public class AppConfigWriter 
{
	static AppConfig app_config;
	static EnumSet<RuntimeOptions> debug_options = RuntimeOptions.CONTROL_DEBUG;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{		
		
		try {
			
			app_config = new AppConfig();
			app_config.setDestinationPath("AppConfig.xml");
			app_config.setConfigFilePath("config.xml");
			app_config.setLogFilePath("log.xml");
			app_config.setRuntimeOptions(debug_options);
			
			URL url = new URL("http://localhost:8888/ws/server?wsdl");
			QName qname = new QName("http://server.httprobot.org/", "RobotSourceService");
			
			ServiceConnection serviceConnection = new ServiceConnection();
			serviceConnection.setUuid(null);
			serviceConnection.setInherited(true);
			serviceConnection.setUrl(url);
			serviceConnection.setQName(qname);
			
			app_config.setServiceConnection(serviceConnection);
		

			File file = new File(app_config.getDestinationPath());
			WriteAppConfig(file);
		} 
		catch (MalformedURLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
				
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
	public static Configuration GetTestConfig(File file)
	{
		Configuration config = new Configuration();
		config.setDestinationPath("config.xml");
		config.setUuid(null);
		config.setInherited(false);
		
		
		ArrayList<DataSource> serversInfos = new ArrayList<DataSource>();
		
		DataSource serverInfo = new DataSource();
		serversInfos.add(serverInfo);
		
		config.setDataSource(serversInfos);
		return config;
	}
}
