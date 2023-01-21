/**
 * 
 */
package org.httprobot.common;

import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;

/**
 * {@link Operator} message class. Inherits {@link Placeholder}.
 * <br>
 * @author joan
 */
@XmlTransient
public class Operator
		extends Placeholder {
	
	/**
	 * 275369766850238746L
	 */
	private static final long serialVersionUID = 275369766850238746L;
	
	/* (non-Javadoc)
	 * @see org.httprobot.common.Placeholder#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		super.OnObjectUnmarshalled(sender, e);
	}
}