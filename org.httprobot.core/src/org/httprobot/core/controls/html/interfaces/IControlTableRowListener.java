/**
 * 
 */
package org.httprobot.core.controls.html.interfaces;

import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.placeholders.operators.html.TableRow;

/**
 * {@link TableRow} message control listener interface. Inherits {@link IControlListener}.
 * <br>
 * @author Joan
 *
 */
public interface IControlTableRowListener 
		extends IControlListener {
	
	/**
	 * When {@link TableRow} message has been initialized.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlTableRow_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;
	/**
	 * When {@link TableRow} message has been read.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlTableRow_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;	
	/**
	 * When {@link TableRow} message has been loaded.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlTableRow_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;
	/**
	 * When {@link TableRow} message has been changed.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlTableRow_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;	
	/**
	 * When {@link TableRow} message has been rendered.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlTableRow_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;
	/**
	 * When {@link TableRow} message has been written.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlTableRow_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;	
}