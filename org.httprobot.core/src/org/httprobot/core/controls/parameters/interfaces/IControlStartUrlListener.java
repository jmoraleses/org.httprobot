/**
 * 
 */
package org.httprobot.core.controls.parameters.interfaces;

import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.params.StartUrl;

/**
 * StartUrl RML message listener interface. Inherits {@link IControlListener}.
 * @author joan
 *
 */
public interface IControlStartUrlListener extends IControlListener
{
	/**
	 * Fired when {@link StartUrl} control has been initialized.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when fired method hasn't been overridden.
	 * @throws InconsistenMessageException
	 */
	public void OnControlStartUrl_Init(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link StartUrl} control has been read.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when fired method hasn't been overridden.
	 * @throws InconsistenMessageException when message doesn't match with corresponding data.
	 */
	public void OnControlStartUrl_Read(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link StartUrl} control has been loaded.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when fired method hasn't been overridden.
	 * @throws InconsistenMessageException when message doesn't match with corresponding data.
	 */
	public void OnControlStartUrl_Loaded(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link StartUrl} control has been changed.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when fired method hasn't been overridden.
	 * @throws InconsistenMessageException when message doesn't match with corresponding data.
	 */
	public void OnControlStartUrl_Changed(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link StartUrl} control has been rendered.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when fired method hasn't been overridden.
	 * @throws InconsistenMessageException when message doesn't match with corresponding data.
	 */
	public void OnControlStartUrl_Rendered(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link StartUrl} control has been written.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when fired method hasn't been overridden.
	 * @throws InconsistenMessageException when message doesn't match with corresponding data.
	 */
	public void OnControlStartUrl_Write(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
}