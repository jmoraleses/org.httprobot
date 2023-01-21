package org.httprobot.common.rml.config;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.Rml;
import org.httprobot.common.rml.datatypes.ServerInfo;


/**
 * @author Joan
 * Application XML-RML configuration file. Inherits {@link Rml}.
 */
@XmlRootElement
public class Config extends Rml
{	
	/**
	 * 5862799292093094020L
	 */
	private static final long serialVersionUID = 5862799292093094020L;
	
	private ArrayList<ServerInfo> serverInfo = null;
	/**
	 * Gets the array of servers data
	 * @return {@link ArrayList} of {@link ServerInfo}
	 */
	public ArrayList<ServerInfo> getServerInfo() 
	{
		if(this.serverInfo == null)
		{
			this.serverInfo = new ArrayList<ServerInfo>();
			return this.serverInfo;
		}
		else
		{
			return this.serverInfo;
		}
	}
	/**
	 * Sets the server data.
	 * @param serverInfo {@link ArrayList} of {@link ServerInfo}
	 */
	public void setServerInfo(ArrayList<ServerInfo> serverInfo) {
		this.serverInfo = serverInfo;
	}	
	/**
	 * AppConfig class constructor.
	 */
	public Config()
	{		
		super();
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
		this.setServerInfo(((Config)e.getRml()).getServerInfo());
	}
}