package org.httprobot.core.contents.interfaces;

import org.httprobot.common.exceptions.ContentException;
import org.httprobot.core.contents.events.SolrConnectionEventArgs;

/**
 * @author joan
 *
 */
public interface ISolrConnectionListener 
{
	/**
	 * Fired when connection result has started.
	 * @param sender the sender
	 * @param e {@link SolrConnectionEventArgs} the arguments
	 * @throws ContentException
	 */
	public void OnContentConnection_Started(Object sender, SolrConnectionEventArgs e) throws ContentException;
	/**
	 * Fired when connection result has finished.
	 * @param sender the sender
	 * @param e {@link SolrConnectionEventArgs} the arguments
	 * @throws ContentException
	 */
	public void OnContentConnection_Finished(Object sender, SolrConnectionEventArgs e) throws ContentException;
	/**
	 * Fired when connection result has failed.
	 * @param sender the sender
	 * @param e {@link SolrConnectionEventArgs} the arguments
	 * @throws ContentException
	 */
	public void OnContentConnection_Error(Object sender, SolrConnectionEventArgs e) throws ContentException;
}
