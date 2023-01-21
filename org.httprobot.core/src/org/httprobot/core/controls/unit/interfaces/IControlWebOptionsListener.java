/**
 * 
 */
package org.httprobot.core.controls.unit.interfaces;

import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;

/**
 * WebOptions RML message control interface
 * @author joan
 *
 */
public interface IControlWebOptionsListener extends IControlListener 
{
	/**
	 * Fired when WebOptions RML message has been initialized.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been implemented
	 * @throws InconsistenMessageException when message doesn't match with corresponding data
	 */
	public void OnControlWebOptions_Init(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when WebOptions RML message has been read.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been implemented
	 * @throws InconsistenMessageException when message doesn't match with corresponding data
	 */
	public void OnControlWebOptions_Read(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when WebOptions RML message has been loaded.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been implemented
	 * @throws InconsistenMessageException when message doesn't match with corresponding data
	 */
	public void OnControlWebOptions_Loaded(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when WebOptions RML message has been changed.
	 * @param sender the sender
	 * @param e  {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been implemented
	 * @throws InconsistenMessageException when message doesn't match with corresponding data
	 */
	public void OnControlWebOptions_Changed(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when WebOptions RML message has been rendered.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been implemented
	 * @throws InconsistenMessageException when message doesn't match with corresponding data
	 */
	public void OnControlWebOptions_Rendered(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when WebOptions RML message has been write.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been implemented
	 * @throws InconsistenMessageException when message doesn't match with corresponding data
	 */
	public void OnControlWebOptions_Write(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
}