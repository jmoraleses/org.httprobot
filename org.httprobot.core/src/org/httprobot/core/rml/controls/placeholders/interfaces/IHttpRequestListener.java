/**
 * 
 */
package org.httprobot.core.rml.controls.placeholders.interfaces;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;

/**
 * @author Joan
 *
 */
public interface IHttpRequestListener extends IRmlListener
{
	/**
	 * Fired when WebRequest must be read. Control loaded.
	 * @param sender {@link Object} the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnHttpRequestInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when WebRequest must be read. Control loaded.
	 * @param sender {@link Object} the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnHttpRequestRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException ;
	/**
	 * Fired when WebRequest must be read. Control loaded.
	 * @param sender {@link Object} the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnHttpRequestLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException ;
	/**
	 * Fired when WebRequest must be read. Control loaded.
	 * @param sender {@link Object} the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnHttpRequestChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException ;
	/**
	 * Fired when WebRequest must be read. Control loaded.
	 * @param sender {@link Object} the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnHttpRequestRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException ;
	/**
	 * Fired when WebRequest must be read. Control loaded.
	 * @param sender {@link Object} the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnHttpRequestWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException ;
}