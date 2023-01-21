/**
 * 
 */
package org.httprobot.core.controls.contents.interfaces;

import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;

/**
 * FieldRef RML message listener interface. Inherits {@link IControlListener}.
 * @author joan
 *
 */
public interface IControlFieldRefListener extends IControlListener 
{
	/**
	 * Called when FieldRef message has been initialized.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws InconsistenMessageException when message doesn't match with corresponding data
	 */
	public void OnControlFieldRef_Init(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Called when FieldRef message has been initialized.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws InconsistenMessageException when message doesn't match with corresponding data
	 */
	public void OnControlFieldRef_Read(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Called when FieldRef message has been initialized.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws InconsistenMessageException when message doesn't match with corresponding data
	 */
	public void OnControlFieldRef_Loaded(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Called when FieldRef message has been initialized.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws InconsistenMessageException when message doesn't match with corresponding data
	 */
	public void OnControlFieldRef_Changed(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Called when FieldRef message has been initialized.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws InconsistenMessageException when message doesn't match with corresponding data
	 */
	public void OnControlFieldRef_Rendered(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Called when FieldRef message has been initialized.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws InconsistenMessageException when message doesn't match with corresponding data
	 */
	public void OnControlFieldRef_Write(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
}
