package org.httprobot.common.exceptions;

import java.util.EnumSet;

import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.interfaces.IListener;

public class UserException extends Exception 
{
	private IListener parent;
	private EnumSet<RuntimeOptions> debugOptions = null;
	
	/**
	 * serialVersionUID = -970213955077981554L;
	 */
	private static final long serialVersionUID = -970213955077981554L;

	public UserException(IListener parent, String message)
	{
		super(message);
		this.parent = parent;
		this.debugOptions = parent.getRuntimeOptions();
	}

	public IListener getParent() {
		return parent;
	}

	public EnumSet<RuntimeOptions> getDebugOptions() {
		return debugOptions;
	}
}
