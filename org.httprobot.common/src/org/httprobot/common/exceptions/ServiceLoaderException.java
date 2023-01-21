/**
 * 
 */
package org.httprobot.common.exceptions;

import org.httprobot.common.interfaces.IListener;

/**
 * @author joan
 *
 */
public class ServiceLoaderException extends UserException {

	/**
	 * -348782391746015282L
	 */
	private static final long serialVersionUID = -348782391746015282L;

	/**
	 * @param parent
	 * @param message
	 */
	public ServiceLoaderException(IListener parent, String message) {
		super(parent, message);
	}

}
