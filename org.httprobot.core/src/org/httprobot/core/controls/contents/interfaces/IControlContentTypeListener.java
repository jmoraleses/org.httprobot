/**
 * 
 */
package org.httprobot.core.controls.contents.interfaces;

import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;

/**
 * ContentType message control listener.
 * @author joan
 *
 */
public interface IControlContentTypeListener extends IControlListener
{
	/**
	 * Fired when ContentType message control has been initialized.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden.
	 * @throws InconsistenMessageException when message doesn't match with corresponding type
	 */
	public void OnControlContentType_Init(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when ContentType message control has been read.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden.
	 * @throws InconsistenMessageException when message doesn't match with corresponding type
	 */
	public void OnControlContentType_Read(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when ContentType message control has been loaded.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden.
	 * @throws InconsistenMessageException when message doesn't match with corresponding type
	 */
	public void OnControlContentType_Loaded(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when ContentType message control has been changed.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden.
	 * @throws InconsistenMessageException when message doesn't match with corresponding type
	 */
	public void OnControlContentType_Changed(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when ContentType message control has been rendered.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden.
	 * @throws InconsistenMessageException when message doesn't match with corresponding type
	 */
	public void OnControlContentType_Rendered(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when ContentType message control has been written.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden.
	 * @throws InconsistenMessageException when message doesn't match with corresponding type
	 */
	public void OnControlContentType_Write(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
}
