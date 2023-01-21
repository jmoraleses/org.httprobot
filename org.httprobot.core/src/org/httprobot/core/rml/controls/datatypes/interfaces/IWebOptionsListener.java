/**
 * 
 */
package org.httprobot.core.rml.controls.datatypes.interfaces;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;

/**
 * WebOptions RML message control interface
 * @author joan
 *
 */
public interface IWebOptionsListener extends IRmlListener 
{
	/**
	 * Fired when WebOptions RML message has been initialized.
	 * @param sender the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been implemented
	 * @throws InconsistenMessageException when message doesn't match with corresponding data
	 */
	public void OnWebOptionsInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when WebOptions RML message has been read.
	 * @param sender the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been implemented
	 * @throws InconsistenMessageException when message doesn't match with corresponding data
	 */
	public void OnWebOptionsRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when WebOptions RML message has been loaded.
	 * @param sender the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been implemented
	 * @throws InconsistenMessageException when message doesn't match with corresponding data
	 */
	public void OnWebOptionsLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when WebOptions RML message has been changed.
	 * @param sender the sender
	 * @param e  {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been implemented
	 * @throws InconsistenMessageException when message doesn't match with corresponding data
	 */
	public void OnWebOptionsChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when WebOptions RML message has been rendered.
	 * @param sender the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been implemented
	 * @throws InconsistenMessageException when message doesn't match with corresponding data
	 */
	public void OnWebOptionsRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when WebOptions RML message has been write.
	 * @param sender the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been implemented
	 * @throws InconsistenMessageException when message doesn't match with corresponding data
	 */
	public void OnWebOptionsWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
}
