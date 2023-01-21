/**
 * 
 */
package org.httprobot.core.rml.controls.placeholders.interfaces;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;

/**
 * IHtmlBodyListener interface. 
 * Implemented when HtmlBody place holder has to be listened.
 * @author Joan
 *
 */
public interface IHtmlBodyListener extends IRmlListener
{
	/**
	 * Fired when HtmlBody has been initialized.
	 * @param sender {@link Object} the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnHtmlBodyInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when HtmlBody has been read.
	 * @param sender {@link Object} the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnHtmlBodyRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when HtmlBody has been loaded.
	 * @param sender {@link Object} the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnHtmlBodyLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when HtmlBody has been changed.
	 * @param sender {@link Object} the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnHtmlBodyChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when HtmlBody has been rendered.
	 * @param sender {@link Object} the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnHtmlBodyRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when HtmlBody has been written.
	 * @param sender {@link Object} the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnHtmlBodyWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
}
