package org.httprobot.client.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.RML;
import org.httprobot.common.config.AppConfig;
import org.httprobot.common.definitions.Enums.CliNamespace;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.core.events.RequesterEventArgs;
import org.httprobot.core.interfaces.IPrecursorListener;
import org.httprobot.core.interfaces.IUiListener;
import org.httprobot.core.managers.contents.interfaces.IManagerDataViewListener;
import org.httprobot.core.managers.datatypes.interfaces.IManagerDataSourceListener;
import org.httprobot.core.precursor.Precursor;
import org.w3c.dom.events.Event;

/**
 * Application loader class. 
 * Is {@link IManagerDataViewListener}, 
 * {@link IControlConfigurationListener}, {@link IManagerDataSourceListener}, 
 * {@link IUiListener} and {@link IRmlManagerListener}.
 * @author Joan
 */
@XmlRootElement
public class AppLoader extends RML
	implements IPrecursorListener
{
	/**
	 * -6363409743505260152L
	 */
	private static final long serialVersionUID = -6363409743505260152L;
	
	/**
	 * Command line members
	 */
	private static final CliNamespace cliNamespace = CliNamespace.CLIENT;

	AppConfig appConfig;
	Precursor precursor;
	
	public AppLoader()
	{
		super();
	}
	
	/**
	 * Application loader constructor.
	 */
	public AppLoader(String path)
	{
		super();
		
		//Load AppConfig file
		this.appConfig = LoadAppConfigFile(path);
		
		//Set inherited data
		this.setDestinationPath(path);
		this.setInherited(false);
		this.setRuntimeOptions(appConfig.getRuntimeOptions());
		this.setUuid(appConfig.getUuid());
		
		//Initialize precursor
		this.precursor = new Precursor(this, this.appConfig.getServiceConnection());
		this.precursor.start();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getCliNamespace()
	 */
	@Override
	public CliNamespace getCliNamespace() 
	{
		return cliNamespace;
	}
	/* (non-Javadoc)
	 * @see org.w3c.dom.events.EventListener#handleEvent(org.w3c.dom.events.Event)
	 */
	@Override
	public void handleEvent(Event arg0) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e) 
	{
		switch (e.getCommand()) 
		{
			case RUN_WEB_REQUEST:
				
				break;
	
			default:
				break;
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnManagerCommand(java.lang.Object, org.httprobot.common.events.CommandEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e) 
	{
		
	}
	@Override
	public void OnDocumentCompleted(Object sender, RequesterEventArgs e) 
	{
		
	}
	/**
	 * Load application configuration file.
	 */
	private AppConfig LoadAppConfigFile(String path) 
	{
		AppConfig appConfig = new AppConfig();
		appConfig.setDestinationPath(path);
		appConfig.setInherited(false);
		
		File file = new File(path);	
		InputStream is;
		
		try 
		{
			is = new FileInputStream(file);
			appConfig.unmarshal(is);
			return appConfig;
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (JAXBException e) 
		{
			e.printStackTrace();
		}
		
		return null;		
	}
}
