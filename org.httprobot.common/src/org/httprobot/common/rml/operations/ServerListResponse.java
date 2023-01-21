package org.httprobot.common.rml.operations;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.Rml;
import org.httprobot.common.rml.datatypes.*;

/**
 * @author Joan
 * Server list response class. Inherits {@link Rml}.
 */
@XmlRootElement
public class ServerListResponse extends Rml
{
	
	/**
	 * -1808758407862213759L
	 */
	private static final long serialVersionUID = -1808758407862213759L;
	
	private ArrayList<ServerInfo> serversInfo = null;

	public ArrayList<ServerInfo> getServersInfo() {
		return serversInfo;
	}

	@XmlElement
	public void setServersInfo(ArrayList<ServerInfo> serversInfo) {
		this.serversInfo = serversInfo;
	}

	public ServerListResponse() { }

	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
	}

}
