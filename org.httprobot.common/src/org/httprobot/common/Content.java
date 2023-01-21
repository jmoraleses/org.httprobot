/**
 * 
 */
package org.httprobot.common;

import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;

/**
 * Content message class. Inherits {@link RML}.
 * <br>
 * @author joan
 */
@XmlTransient
public class Content
		extends RML {
	
	/**
	 * 8805921499186344211L
	 */
	private static final long serialVersionUID = 8805921499186344211L;
	
	/**
	 * 
	 */
	public Content()
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
	}
}
