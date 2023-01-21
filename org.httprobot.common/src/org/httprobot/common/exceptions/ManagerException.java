/**
 * 
 */
package org.httprobot.common.exceptions;

import org.httprobot.common.interfaces.IListener;

/**
 * @author joan
 *
 */
public class ManagerException extends UserException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5797605197855635231L;

	/**
	 * @param parent
	 * @param message
	 */
	public ManagerException(IListener parent, String message) 
	{
		super(parent, message);
	}

}
