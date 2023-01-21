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
public interface IWebResponseControl extends IRmlListener
{
	/**
	 * Fired when WebResponse must be read. Control rendered.
	 * @param sender {@link Object} the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnWebResponseInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when WebResponse must be read. Control loaded.
	 * @param sender {@link Object} the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnWebResponseRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when WebResponse must be read. Control rendered.
	 * @param sender {@link Object} the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnWebResponseLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when WebResponse must be read. Control loaded.
	 * @param sender {@link Object} the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnWebResponseChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when WebResponse must be read. Control rendered.
	 * @param sender {@link Object} the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnWebResponseRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when WebResponse must be read. Control loaded.
	 * @param sender {@link Object} the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnWebResponseWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
}
