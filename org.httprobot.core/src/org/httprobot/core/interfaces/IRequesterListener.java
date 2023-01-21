package org.httprobot.core.interfaces;

import org.httprobot.common.interfaces.IListener;
import org.httprobot.core.events.RequesterEventArgs;

public interface IRequesterListener extends IListener
{
	public void OnDataRowCaptured(Object sender, RequesterEventArgs e);
}
