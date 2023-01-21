/**
 * 
 */
package org.httprobot.core.controls.unit.interfaces;

import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.unit.Action;

/**
 * Action RML message listener interface. Inherits {@link IControlListener}.
 * @author Joan
 *
 */
public interface IControlActionListener extends IControlListener 
{
	/**
	 * Fired when {@link Action} is has been initialized.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been implemented.
	 * @throws InconsistenMessageException when message data doesn't match with control.
	 */
	public void OnControlAction_Init(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link Action} is has been read.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been implemented.
	 * @throws InconsistenMessageException when message data doesn't match with control.
	 */
	public void OnControlAction_Read(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link Action} is has been loaded.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been implemented.
	 * @throws InconsistenMessageException when message data doesn't match with control.
	 */
	public void OnControlAction_Loaded(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link Action} is has been changed.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been implemented.
	 * @throws InconsistenMessageException when message data doesn't match with control.
	 */
	public void OnControlAction_Changed(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link Action} is has been rendered.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been implemented.
	 * @throws InconsistenMessageException when message data doesn't match with control.
	 */
	public void OnControlAction_Rendered(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link Action} is has been written.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been implemented.
	 * @throws InconsistenMessageException when message data doesn't match with control.
	 */
	public void OnControlAction_Write(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
}