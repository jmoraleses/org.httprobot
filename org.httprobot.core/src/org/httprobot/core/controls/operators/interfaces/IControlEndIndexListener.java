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
public interface IControlEndIndexListener extends IControlListener
{
	/**
	 * When {@link EndIndex} message has been read.
	 * @param sender {@link Object}
	 * @param e {@link ControlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnControlEndIndex_Init(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * When {@link EndIndex} message has been read.
	 * @param sender {@link Object}
	 * @param e {@link ControlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnControlEndIndex_Read(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * When {@link EndIndex} message has been read.
	 * @param sender {@link Object}
	 * @param e {@link ControlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlEndIndex_Loaded(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * When {@link EndIndex} message has been read.
	 * @param sender {@link Object}
	 * @param e {@link ControlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlEndIndex_Changed(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * When {@link EndIndex} message has been read.
	 * @param sender {@link Object}
	 * @param e {@link ControlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlEndIndex_Rendered(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * When {@link EndIndex} message has been read.
	 * @param sender {@link Object}
	 * @param e {@link ControlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	
	 */
	public void OnControlEndIndex_Write(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
}
