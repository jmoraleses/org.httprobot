package org.httprobot.common.events;

import org.httprobot.common.RML;
import org.httprobot.common.definitions.Enums.EventType;
import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.io.CLI;

/**
 * {@link CLI} command event arguments class. Inherits {@link EventArgs}.
 * <br>
 * @author Joan
 * 
 */
public class CliEventArgs extends EventArgs {

	/**
	 * -8162112873723009564L
	 */
	private static final long serialVersionUID = -8162112873723009564L;
	
	private Command cmd;	
	private IControlListener control;
	private String httpAddress;
	private RML message;
	
	/**
	 * {@link CLI} event arguments class constructor. constructor.
	 * @param value sender
	 * @param cmd the {@link Command}
	 */
	public CliEventArgs(Object value, Command cmd) 
	{
		super(value, EventType.MANAGER);
		this.cmd = cmd;
	}
	public CliEventArgs(Object value, Command cmd, IControlListener control)
	{
		super(value, EventType.MANAGER);
		this.cmd = cmd;
		this.control = control;
		this.message = control.getMessage();
	}
	public CliEventArgs(Object value, Command cmd, RML message) 
	{
		super(value, EventType.MANAGER);
		this.cmd = cmd;
		this.message = message;
	}
	
	/**
	 * @return the {@link Command}
	 */
	public Command getCommand() {
		return cmd;
	}
	/**
	 * @return the control
	 */
	public IControlListener getControl() {
		return control;
	}	
	public String getHttpAddress()
	{
		return this.httpAddress;
	}
	public RML getMessage() {
		return message;
	}
}
