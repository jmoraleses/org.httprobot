/**
 * 
 */
package org.httprobot.common.exceptions;

import org.httprobot.common.interfaces.IListener;

/**
 * @author Joan
 *
 */
public class InconsistenMessageException extends RmlException {

	/**
	 * -3879991116994613588L
	 */
	private static final long serialVersionUID = -3879991116994613588L;

	private Class<?> inconsistentClass;
	
	public InconsistenMessageException(IListener parent, String message) 
	{
		super(parent, message);
		this.setInconsistentClass(parent.getClass());
		
	}
	/**
	 * @param inconsistentClass the inconsistentClass to set
	 */
	public void setInconsistentClass(Class<?> inconsistentClass) {
		this.inconsistentClass = inconsistentClass;
	}
	/**
	 * @return the inconsistentClass
	 */
	public Class<?> getInconsistentClass() {
		return inconsistentClass;
	}
}