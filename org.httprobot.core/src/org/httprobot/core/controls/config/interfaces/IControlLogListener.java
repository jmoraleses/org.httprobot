/**
 * 
 */
package org.httprobot.core.controls.config.interfaces;

import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
/**
 * Log RML message listener interface. Inherits {@link IControlListener}.
 * @author Joan
 *
 */
public interface IControlLogListener extends IControlListener
{
	/**
	 * Fired when {@link Log} message has been initialized.
	 * @param sender {@link Object}
	 * @param e {@link ControlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnControlLog_Init(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link Log} message has been read.
	 * @param sender {@link Object}
	 * @param e {@link ControlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlLog_Read(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link Log} message has been loaded.
	 * @param sender {@link Object}
	 * @param e {@link ControlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnControlLog_Loaded(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link Log} message has been changed.
	 * @param sender {@link Object}
	 * @param e {@link ControlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlLog_Changed(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 *Fired when {@link Log} message has been rendered.
	 * @param sender {@link Object}
	 * @param e {@link ControlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlLog_Rendered(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link Log} message has been written.
	 * @param sender {@link Object}
	 * @param e {@link ControlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnControlLog_Write(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
}
