package org.httprobot.client.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.EnumSet;
import java.util.UUID;

import javax.xml.namespace.QName;

import org.httprobot.common.contents.ContentType;
import org.httprobot.common.contents.FieldRef;
import org.httprobot.common.definitions.Enums.CliNamespace;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ServiceLoaderException;
import org.httprobot.core.contents.xml.Item;
import org.httprobot.core.contents.xml.fields.Created;
import org.httprobot.core.events.ServiceLoaderEventArgs;
import org.httprobot.core.interfaces.IServiceLoaderListener;
import org.httprobot.core.net.ServiceLoader;
import org.w3c.dom.events.Event;

public class Foo implements IServiceLoaderListener
{
	/**
	 * 6682293884764878923L
	 */
	private static final long serialVersionUID = 6682293884764878923L;
	ServiceLoader serviceLoader;
	QName qname;
	URL url;
	
	public Foo()
	{
		try 
		{
			url = new URL("http://localhost:8888/ws/server?wsdl");
			qname = new QName("http://server.httprobot.org/", "RobotDataSourceService");
			serviceLoader = new ServiceLoader(this, url, qname);
			
			System.out.println(serviceLoader.getSystemContentTypeRoot().toString());
		    
		    for(ContentType contentType : this.serviceLoader.getSystemContentTypeRoot().getContentType())
		    {
		    	System.out.println(contentType.toString());

		    	try
		    	{
		    		Item.class.cast(contentType);
		    		
		    	}
		    	catch(ClassCastException ex)
		    	{
		    		System.out.println("fail");
		    	}
		    	
		    	if(contentType instanceof Item)
		    	{
		    		System.out.println("Item found");
		    	}
		    	
		    	for(FieldRef fieldRef : contentType.getFieldRef())
		    	{
		    		try
		    		{
		    			Created created = (Created)fieldRef;
		    			
		    			System.out.println(created.toString());
		    		}
		    		catch(ClassCastException ex)
		    		{
		    			System.out.println("fail2");
		    		}
		    	}
		    }
		} 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		new Foo();
		
//		Item item = new Item();
//		System.out.println(item.toString());
		
//		ContentTypes contentTypes = new ContentTypes();
//		contentTypes.getContentType().add(new Item());
//		contentTypes.getContentType().add(new Document());
//		contentTypes.getContentType().add(new DocumentItem());
//
//		
//		for(ContentType contentType : contentTypes.getContentType())
//		{
//			if(contentType.getClass() == Item.class)
//			{
//				System.out.println("Item FOUND");
//			}
//			
//		}
//		
//		System.out.println(contentTypes.toString());
	}

	@Override
	public UUID getUuid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUuid(UUID uuid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EnumSet<RuntimeOptions> getRuntimeOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRuntimeOptions(EnumSet<RuntimeOptions> options) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CliNamespace getCliNamespace() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDestinationPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDestinationPath(String destinationPath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnCommandInput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleEvent(Event evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnServiceLoader_WebServiceError(Object sender,
			ServiceLoaderEventArgs e) throws ServiceLoaderException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnServiceLoader_Ready(Object sender, ServiceLoaderEventArgs e)
			throws ServiceLoaderException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnServiceLoader_SystemContentTypeRoot(Object sender,
			ServiceLoaderEventArgs e) throws ServiceLoaderException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnServiceLoader_DataSourceList(Object sender,
			ServiceLoaderEventArgs e) throws ServiceLoaderException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnServiceLoader_DataSource(Object sender,
			ServiceLoaderEventArgs e) throws ServiceLoaderException {
		// TODO Auto-generated method stub
		
	}
}