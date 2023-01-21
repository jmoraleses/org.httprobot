package org.httprobot.core.interfaces;

import org.httprobot.common.interfaces.IListener;
import org.httprobot.core.events.InetEventArgs;

/**
 * @author Joan
 * Internet manager listener interface. Inherits {@link IListener}.
 */
public interface IInetListener extends IListener 
{
	public void OnServerInfoStarted(Object sender, InetEventArgs e);
	public void OnServerInfoFinished(Object sender, InetEventArgs e);
	public void OnServerInfoStopped(Object sender, InetEventArgs e);
	public void OnServerInfoError(Object sender, InetEventArgs e);
}
