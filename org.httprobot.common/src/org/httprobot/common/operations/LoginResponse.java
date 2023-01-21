package org.httprobot.common.operations;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.RML;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;

/**
 * @author Joan
 * Login response class. Inherit {@link RML}.
 */
@XmlRootElement
public class LoginResponse extends RML 
{
	/**
	 * -7036243488495380645L
	 */
	private static final long serialVersionUID = -7036243488495380645L;
	
	private String token;
	/**
	 * LoginResponse class constructor.
	 */
	public LoginResponse() { }
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
	@XmlAttribute
	public void setToken(String token) {
		this.token = token;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
	}
}
