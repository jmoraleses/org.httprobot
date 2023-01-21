/**
 * 
 */
package org.httprobot.core.controls.placeholders.interfaces;

import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;

/**
 * HttpRequest RML message listener interface. Inherits {@link IControlListener}.
 * @author Joan
 *
 */
public interface IControlHttpAddressListener extends IControlListener
{
	/**
	 * Fired when {@link HttpAddress} has been initialized.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnControlHttpAddress_Init(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link HttpAddress} has been read.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnControlHttpAddress_Read(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException ;
	/**
	 * Fired when {@link HttpAddress} has been loaded.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnControlHttpAddress_Loaded(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException ;
	/**
	 * Fired when {@link HttpAddress} has been changed.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnControlHttpAddress_Changed(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException ;
	/**
	 * Fired when {@link HttpAddress} has been rendered.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnControlHttpAddress_Rendered(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException ;
	/**
	 * Fired when {@link HttpAddress} has been written.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone	 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.
	 */
	public void OnControlHttpAddress_Write(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException ;
}