/**
 * 
 */
package org.httprobot.core.rml.controls.datatypes.interfaces;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;

/**
 * @author Joan
 *
 */
public interface IActionListener extends IRmlListener 
{
	/**
	 * Fired when Action is has been initialized.
	 * @param sender the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been implemented.
	 * @throws InconsistenMessageException when message data doesn't match with control.
	 */
	public void OnActionInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when Action is has been read.
	 * @param sender the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been implemented.
	 * @throws InconsistenMessageException when message data doesn't match with control.
	 */
	public void OnActionRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when Action is has been loaded.
	 * @param sender the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been implemented.
	 * @throws InconsistenMessageException when message data doesn't match with control.
	 */
	public void OnActionLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when Action is has been changed.
	 * @param sender the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been implemented.
	 * @throws InconsistenMessageException when message data doesn't match with control.
	 */
	public void OnActionChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when Action is has been rendered.
	 * @param sender the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been implemented.
	 * @throws InconsistenMessageException when message data doesn't match with control.
	 */
	public void OnActionRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when Action is has been written.
	 * @param sender the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been implemented.
	 * @throws InconsistenMessageException when message data doesn't match with control.
	 */
	public void OnActionWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
}