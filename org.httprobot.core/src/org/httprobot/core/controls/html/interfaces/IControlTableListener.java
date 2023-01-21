/**
 * 
 */
package org.httprobot.core.controls.html.interfaces;

import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.placeholders.operators.html.Table;

/**
 * {@link Table} message control listener interface. Inherits {@link IControlListener}.
 * <br>
 * @author Joan
 *
 */
public interface IControlTableListener 
		extends IControlListener {
	
	/**
	 * When {@link Table} message has been initialized.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlTable_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;
	/**
	 * When {@link Table} message has been read.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlTable_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;	
	/**
	 * When {@link Table} message has been loaded.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlTable_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;
	/**
	 * When {@link Table} message has been changed.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlTable_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;	
	/**
	 * When {@link Table} message has been rendered.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlTable_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;
	/**
	 * When {@link Table} message has been written.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlTable_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;	
}