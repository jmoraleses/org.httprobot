package org.httprobot.core.interfaces;

import org.httprobot.core.events.WebLoaderEventArgs;

/**
 * @author Joan
 *
 */
public interface IWebLoaderListener 
{
	public void OnWebLoaderAddressChanged(Object sender, WebLoaderEventArgs e);
	
	public void OnWebLoaderStarted(Object sender, WebLoaderEventArgs e);
	public void OnWebLoaderCompleted(Object sender, WebLoaderEventArgs  e);
	public void OnWebLoaderStopped(Object sender, WebLoaderEventArgs  e);
	public void OnWebLoaderTimedOut(Object sender, WebLoaderEventArgs e);
}
