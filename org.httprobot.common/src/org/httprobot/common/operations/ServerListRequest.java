package org.httprobot.common.operations;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.RML;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;

/**
 * @author Joan
 * Server list request class. Inherits {@link RML}.
 */
@XmlRootElement
public class ServerListRequest extends RML
{
	/**
	 * 1962530354477127811L
	 */
	private static final long serialVersionUID = 1962530354477127811L;
	
	private String token;
	/**
	 * Gets authentication token
	 * @return {@link String} the token
	 */
	public String getToken() {
		return token;
	}	
	/**
	 * Sets authentication token
	 * @param token {@link String} the token
	 */
	@XmlElement
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * ServerListRequest class constructor.
	 */
	public ServerListRequest() { }
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
	}
}