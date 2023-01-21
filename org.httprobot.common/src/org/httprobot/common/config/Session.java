/**
 * 
 */
package org.httprobot.common.config;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.Config;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;

/**
 * @author Joan
 *
 */
@XmlRootElement
public class Session extends Config
{
	/**
	 * -822329674324641492L
	 */
	private static final long serialVersionUID = -822329674324641492L;
	
	private Date datetime;
	
	public Session()
	{
		
	}
	@XmlElement
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public Date getDatetime() {
		return datetime;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
		setDatetime(((Session)e.getRml()).getDatetime());
	}
}