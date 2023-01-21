package org.httprobot.core.controls.operators.interfaces;

import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.placeholders.operators.Delimiters;

/**
 * @author Joan
 *
 */
public interface IControlDelimitersListener extends IControlListener
{
	/**
	 * When {@link Delimiters} {@link RML} has been read.  
	 * @param sender {@link Object}
	 * @param e {@link ControlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnControlDelimiters_Init(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * When {@link Delimiters} {@link RML} has been read.
	 * @param sender {@link Object}
	 * @param e {@link ControlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlDelimiters_Read(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * When {@link Delimiters} {@link RML} has been read.  
	 * @param sender {@link Object}
	 * @param e {@link ControlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlDelimiters_Loaded(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * When {@link Delimiters} {@link RML} has been read.  
	 * @param sender {@link Object}
	 * @param e {@link ControlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnControlDelimiters_Changed(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * When {@link Delimiters} {@link RML} has been read.
	 * @param sender {@link Object}
	 * @param e {@link ControlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlDelimiters_Rendered(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * When {@link Delimiters} {@link RML} has been read.
	 * @param sender {@link Object}
	 * @param e {@link ControlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnControlDelimiters_Write(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
}