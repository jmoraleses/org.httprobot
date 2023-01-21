/**
 * 
 */
package org.httprobot.core.rml.controls.operators.interfaces;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;

/**
 * @author Joan
 *
 */
public interface IStartIndexListener extends IRmlListener 
{
	/**
	 * When {@link StartIndex} {@link Rml} has been read.
	 * @param sender {@link Object}
	 * @param e {@link RmlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnStartIndexInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException; 
	/**
	 * When {@link StartIndex} {@link Rml} has been read.
	 * @param sender {@link Object}
	 * @param e {@link RmlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnStartIndexRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * When {@link StartIndex} {@link Rml} has been read.
	 * @param sender {@link Object}
	 * @param e {@link RmlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnStartIndexLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException; 
	/**
	 * When {@link StartIndex} {@link Rml} has been read.
	 * @param sender {@link Object}
	 * @param e {@link RmlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnStartIndexChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;	
	/**
	 * When {@link StartIndex} {@link Rml} has been read.
	 * @param sender {@link Object}
	 * @param e {@link RmlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnStartIndexRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException; 
	/**
	 * When {@link StartIndex} {@link Rml} has been read.
	 * @param sender {@link Object}
	 * @param e {@link RmlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnStartIndexWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
}