/**
 * 
 */
package org.httprobot.core.controls.operators.interfaces;

import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.placeholders.operators.StartIndex;

/**
 * {@link StartIndex} message listener interface. 
 * Inherits {@link IControlListener}.
 * @author Joan
 *
 */
public interface IControlStartIndexListener extends IControlListener 
{
	/**
	 * When {@link StartIndex} message has been read.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlStartIndex_Init(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException; 
	/**
	 * When {@link StartIndex} message has been read.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlStartIndex_Read(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * When {@link StartIndex} message has been read.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlStartIndex_Loaded(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException; 
	/**
	 * When {@link StartIndex} message has been read.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlStartIndex_Changed(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;	
	/**
	 * When {@link StartIndex} message has been read.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlStartIndex_Rendered(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException; 
	/**
	 * When {@link StartIndex} message has been read.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlStartIndex_Write(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
}