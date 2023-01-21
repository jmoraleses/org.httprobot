package org.httprobot.core.interfaces;

import org.httprobot.common.interfaces.IListener;
import org.httprobot.core.events.RequesterEventArgs;

public interface IPrecursorListener extends IListener
{
	public void OnDocumentCompleted(Object sender, RequesterEventArgs e);
}
