/**
 * 
 */
package org.httprobot.common;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;

/**
 * @author joan
 *
 */
@XmlTransient
public class DataType 
		extends RML {

	/**
	 * 7599972994948305916L
	 */
	private static final long serialVersionUID = 7599972994948305916L;

	private Date NTP = null;
	/**
	 * Gets the last update date
	 * @return {@link String} the date
	 */
	public Date getNTP() {
		return NTP;
	}
	/**
	 * @param NTP
	 */
	@XmlElement
	public void setNTP(Date NTP) {
		this.NTP = NTP;
	}
	/**
	 * 
	 */
	public DataType() 
	{
		super();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.RML#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		super.OnObjectUnmarshalled(sender, e);

		setNTP(((DataType)e.getRml()).getNTP());
	}
}