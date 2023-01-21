package org.httprobot.common.operations;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.RML;
import org.httprobot.common.datatypes.*;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;

/**
 * @author Joan
 * Server list response class. Inherits {@link RML}.
 */
@XmlRootElement
public class ServerListResponse extends RML
{
	
	/**
	 * -1808758407862213759L
	 */
	private static final long serialVersionUID = -1808758407862213759L;
	
	private ArrayList<DataSource> serversInfo = null;

	public ArrayList<DataSource> getServersInfo() {
		return serversInfo;
	}

	@XmlElement
	public void setServersInfo(ArrayList<DataSource> serversInfo) {
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
