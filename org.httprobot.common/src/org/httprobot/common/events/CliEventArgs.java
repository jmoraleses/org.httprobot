package org.httprobot.common.events;

import org.httprobot.common.definitions.Enums.EventType;
import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.rml.Rml;

/**
 * @author Joan
 * Command event arguments. Contains command data. Inherits {@link EventArgs}.
 */
public class CliEventArgs extends EventArgs {

	/**
	 * -8162112873723009564L
	 */
	private static final long serialVersionUID = -8162112873723009564L;
	
	private String httpAddress;	
	private Rml message;
	private Command cmd;
	
	/**
	 * @return the {@link Command}
	 */
	public Command getCmd() {
		return cmd;
	}
	public String getHttpAddress()
	{
		return this.httpAddress;
	}
	/**
	 * CommandEventArgs constructor.
	 * @param value sender
	 * @param cmd the {@link Command}
	 */
	public CliEventArgs(Object value, Command cmd) 
	{
		super(value, EventType.MANAGER);
		this.cmd = cmd;
	}
	
	public CliEventArgs(Object value, Command cmd, Rml message) 
	{
		super(value, EventType.MANAGER);
		this.cmd = cmd;
		this.message = message;
	}
	
	public Rml getMessage() {
		return message;
	}
}
