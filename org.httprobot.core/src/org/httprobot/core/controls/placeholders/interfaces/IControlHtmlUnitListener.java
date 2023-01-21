/**
 * 
 */
package org.httprobot.core.controls.placeholders.interfaces;

import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.placeholders.HtmlUnit;

/**
 * {@link HtmlUnit} message listener interface. Inherits {@link IControlListener}.
 * <br>
 * @author Joan
 *
 */
public interface IControlHtmlUnitListener extends IControlListener
{
	/**
	 * Fired when {@link HtmlUnit} has been initialized.
	 * @param sender {@link Object} the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnControlHtmlUnit_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link HtmlUnit} has been read.
	 * @param sender {@link Object} the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnControlHtmlUnit_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link HtmlUnit} has been loaded.
	 * @param sender {@link Object} the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnControlHtmlUnit_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link HtmlUnit} has been changed.
	 * @param sender {@link Object} the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnControlHtmlUnit_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link HtmlUnit} has been rendered.
	 * @param sender {@link Object} the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnControlHtmlUnit_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link HtmlUnit} has been written.
	 * @param sender {@link Object} the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnControlHtmlUnit_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;
}