package org.httprobot.common.operations;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.RML;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;

/**
 * @author Joan
 * Login request class. Inherit {@link RML}. 
 */
@XmlRootElement
public class LoginRequest extends RML {

	/**
	 * -8178743258336274151L
	 */
	private static final long serialVersionUID = -8178743258336274151L;
	
	private String loginName = null;	
	private String loginPwd = null;
	/**
	 * LoginRequest class constructor
	 */
	public LoginRequest() { }	
	/**
	 * Gets login name.
	 * @return {@link String} the login name
	 */
	public String getLoginName() {
		return loginName;
	}
	/**
	 * Gets password
	 * @return {@link String} the password
	 */
	public String getLoginPwd() {
		return loginPwd;
	}	
	/**
	 * Sets login name.
	 * @param loginName {@link String} the login name
	 */
	@XmlElement
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * Sets password.
	 * @param loginPwd {@link String} the password
	 */
	@XmlElement
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
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