package org.httprobot.core.common;

import org.httprobot.common.events.ControlEventArgs;

public class Methods 
{
	/**
	 * @param e
	 * @param currentClass
	 */
	public static Boolean CheckMessage(ControlEventArgs e, Class<?> currentClass) 
	{
		if(e.getMessage() != null)
		{
			if(e.getMessage().getClass().equals(currentClass))
			{
				return true;
			}else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
}
