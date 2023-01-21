package org.httprobot.core.interfaces;

import org.httprobot.common.exceptions.WebLoaderException;
import org.httprobot.common.interfaces.IListener;
import org.httprobot.core.events.WebLoaderEventArgs;
import org.httprobot.core.net.WebLoader;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * {@link WebLoader} listener interface.
 * Inherits {@link IListener}.
 * @author Joan
 *
 */
public interface IWebLoaderListener extends IListener
{
	/**
	 * Fired when {@link WebLoader} has been initialized.
	 * @param sender the sender
	 * @param e {@link WebLoaderEventArgs} the arguments
	 * @throws WebLoaderException
	 */
	public void OnWebLoader_Ready(Object sender, WebLoaderEventArgs e) throws WebLoaderException;
	/**
	 * Fired when {@link WebLoader} has loaded a {@link HtmlPage}.
	 * @param sender the sender
	 * @param e {@link WebLoaderEventArgs} the arguments
	 * @throws WebLoaderException
	 */
	public void OnWebLoader_PageLoaded(Object sender, WebLoaderEventArgs e) throws WebLoaderException;
	/**
	 * Fired when {@link WebLoader} has loaded all {@link HtmlPage}s
	 * @param sender the sender
	 * @param e {@link WebLoaderEventArgs} the arguments
	 * @throws WebLoaderException
	 */
	public void OnWebLoader_AllPagesLoaded(Object sender, WebLoaderEventArgs e) throws WebLoaderException;
	/**
	 * Fired when a {@link WebLoader} error has occurred during {@link HtmlPage} loading.
	 * @param sender the sender
	 * @param e {@link WebLoaderEventArgs} the arguments
	 * @throws WebLoaderException
	 */
	public void OnWebLoader_PageError(Object sender, WebLoaderEventArgs e) throws WebLoaderException;
}
