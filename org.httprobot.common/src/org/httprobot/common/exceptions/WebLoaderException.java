/**
 * 
 */
package org.httprobot.common.exceptions;

import org.httprobot.common.interfaces.IListener;

/**
 * @author joan
 *
 */
public class WebLoaderException extends UserException {

	/**
	 * -4581658297882910228L
	 */
	private static final long serialVersionUID = -4581658297882910228L;

	/**
	 * @param parent
	 * @param message
	 */
	public WebLoaderException(IListener parent, String message) 
	{
		super(parent, message);
	}

}
