package org.httprobot.common.exceptions;

import org.httprobot.common.interfaces.IListener;

public class ViewTableException extends DataException
{

	public ViewTableException(IListener sender, String message) 
	{
		super(sender, message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2350472748188395489L;

}
