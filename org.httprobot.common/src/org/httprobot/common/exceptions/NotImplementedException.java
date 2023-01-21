package org.httprobot.common.exceptions;

import org.httprobot.common.interfaces.IListener;

public class NotImplementedException extends RmlException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8764657887442537339L;

	private String nodeName;
	private String nodeValue;
	
	public String getNodeName() {
		return nodeName;
	}
	public String getNodeValue() {
		return nodeValue;
	}
	
	public NotImplementedException(IListener parent, String message) 
	{ 
		super(parent, message);
	}
	public NotImplementedException(IListener parent, String node_name, String message) 
	{ 
		super(parent, message);
		this.nodeName = node_name;
	}
	public NotImplementedException(IListener parent, String node_name, String node_value, String message) 
	{ 
		super(parent, message);
		this.nodeName = node_name;
		this.nodeValue = node_value;		
	}
}
