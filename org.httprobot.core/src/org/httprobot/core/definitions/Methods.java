package org.httprobot.core.definitions;

import org.httprobot.common.events.RmlEventArgs;

public class Methods 
{
	/**
	 * @param e
	 * @param currentClass
	 */
	public static Boolean CheckMessage(RmlEventArgs e, Class<?> currentClass) 
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
