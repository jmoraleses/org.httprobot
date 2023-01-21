/**
 * 
 */
package org.httprobot.core.controls.operators.interfaces;

import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;

/**
 * @author Joan
 *
 */
public interface IControlContainsListener extends IControlListener
{
	/**
	 * When {@link Contains} message has been initialized.
	 * @param sender {@link Object}
	 * @param e {@link ControlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlContains_Init(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * When {@link Contains} message has been read.
	 * @param sender {@link Object}
	 * @param e {@link ControlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlContains_Read(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * When {@link Contains} message has been loaded.
	 * @param sender {@link Object}
	 * @param e {@link ControlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlContains_Loaded(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * When {@link Contains} message has been changed.
	 * @param sender {@link Object}
	 * @param e {@link ControlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlContains_Changed(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * When {@link Contains} message has been rendered.
	 * @param sender {@link Object}
	 * @param e {@link ControlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlContains_Rendered(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * When {@link Contains} message has been written.
	 * @param sender {@link Object}
	 * @param e {@link ControlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlContains_Write(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
}