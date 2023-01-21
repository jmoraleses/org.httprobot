package org.httprobot.common.exceptions;

import org.httprobot.common.interfaces.IListener;

public class DataException extends UserException
{	
	/**
	 * serialVersionUID = 5871817119237439137L;
	 */
	private static final long serialVersionUID = 5871817119237439137L;

	public DataException(IListener parent, String message)
	{
		super(parent, message);
	}
}
