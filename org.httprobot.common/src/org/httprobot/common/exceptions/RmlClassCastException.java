package org.httprobot.common.exceptions;

import org.httprobot.common.interfaces.IListener;

public class RmlClassCastException extends RmlException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2890906852863518910L;

	private String nodeName;
	private Object value;

	public String getNodeName() {
		return this.nodeName;
	}
	
	public Object getValue()
	{
		return this.value;
	}
	
	public RmlClassCastException(IListener parent, String message) 
	{
		super(parent, message);
	}
	public RmlClassCastException(IListener parent, String node_name, Object value, String message) 
	{ 
		super(parent, message);
		this.nodeName = node_name;
		this.value = value;
	}

}
