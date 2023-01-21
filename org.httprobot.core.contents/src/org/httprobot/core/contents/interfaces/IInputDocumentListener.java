/**
 * 
 */
package org.httprobot.core.contents.interfaces;

import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.SolrInputField;
import org.httprobot.common.exceptions.ContentException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IListener;
import org.httprobot.core.contents.events.ContentEventArgs;

/**
 * Input document event listener interface.
 * Inherits {@link IListener}.
 * 
 * @author joan
 *
 */
public interface IInputDocumentListener extends IListener 
{
	/**
	 * Fired when {@link SolrInputField} is added to current document.
	 * @param sender the sender
	 * @param e {@link ContentEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws ContentException
	 */
	public void OnInputDocument_FieldAdded(Object sender, ContentEventArgs e) throws NotImplementedException, ContentException;
	/**
	 * Fired when {@link SolrInputField} is removed from current document.
	 * @param sender the sender
	 * @param e {@link ContentEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws ContentException
	 */
	public void OnInputDocument_FieldRemoved(Object sender, ContentEventArgs e) throws NotImplementedException, ContentException;
	/**
	 * Fired when {@link SolrInputField} has changed on current document.
	 * @param sender the sender
	 * @param e {@link ContentEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws ContentException
	 */
	public void OnInputDocument_FieldChanged(Object sender, ContentEventArgs e) throws NotImplementedException, ContentException;
	/**
	 * Fired when {@link SolrInputDocument} is added to current document.
	 * @param sender the sender
	 * @param e {@link ContentEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws ContentException
	 */
	public void OnInputDocument_DocumentAdded(Object sender, ContentEventArgs e) throws NotImplementedException, ContentException;
	/**
	 * Fired when {@link SolrInputDocument} is removed from current document.
	 * @param sender the sender
	 * @param e {@link ContentEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws ContentException
	 */
	public void OnInputDocument_DocumentRemoved(Object sender, ContentEventArgs e) throws NotImplementedException, ContentException;
	/**
	 * Fired when {@link SolrInputDocument} is completed.
	 * @param sender the sender
	 * @param e {@link ContentEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws ContentException
	 */
	public void OnInputDocument_DocumentCompleted(Object sender, ContentEventArgs e) throws NotImplementedException, ContentException;
}
