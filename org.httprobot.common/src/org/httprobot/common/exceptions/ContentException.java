/**
 * 
 */
package org.httprobot.common.exceptions;

import org.httprobot.common.interfaces.IListener;

/**
 * @author joan
 *
 */
public class ContentException extends UserException {

	/**
	 * -1860129733221982236L
	 */
	private static final long serialVersionUID = -1860129733221982236L;

	/**
	 * @param parent
	 * @param message
	 */
	public ContentException(IListener parent, String message) {
		super(parent, message);
		// TODO Auto-generated constructor stub
	}

}
