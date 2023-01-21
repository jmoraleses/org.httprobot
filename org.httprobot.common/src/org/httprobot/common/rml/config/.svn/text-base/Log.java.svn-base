/**
 * 
 */
package org.httprobot.common.rml.config;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.Rml;



/**
 * 
 * LogFile {@link Rml}.
 * @author Joan
 */
@XmlRootElement
public class Log extends Rml
{
	/**
	 * -3633468793118493297L
	 */
	private static final long serialVersionUID = -3633468793118493297L;
	
	private ArrayList<Session> session = null;
	/**
	 * LogFile class constructor
	 */
	public Log() {
	}
	
	public void setSession(ArrayList<Session> session) {
		this.session = session;
	}
	/**
	 * @return
	 */
	public ArrayList<Session> getSession() {
		return session;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
		this.setSession(((Log)e.getRml()).getSession());
	}
}
