/**
 * 
 */
package org.httprobot.core.controls.parameters.interfaces;

import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.params.ServerUrl;

/**
 * ServerUrl RML message listener interface. Inherits {@link IControlListener}.
 * @author joan
 *
 */
public interface IControlServerUrlListener extends IControlListener 
{
	/**
	 * Fired when {@link ServerUrl} control has been initialized.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when fired method hasn't been overridden.
	 * @throws InconsistenMessageException
	 */
	public void OnControlServerUrl_Init(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link ServerUrl} control has been read.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when fired method hasn't been overridden.
	 * @throws InconsistenMessageException when message doesn't match with corresponding data.
	 */
	public void OnControlServerUrl_Read(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link ServerUrl} control has been loaded.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when fired method hasn't been overridden.
	 * @throws InconsistenMessageException when message doesn't match with corresponding data.
	 */
	public void OnControlServerUrl_Loaded(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link ServerUrl} control has been changed.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when fired method hasn't been overridden.
	 * @throws InconsistenMessageException when message doesn't match with corresponding data.
	 */
	public void OnControlServerUrl_Changed(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link ServerUrl} control has been rendered.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when fired method hasn't been overridden.
	 * @throws InconsistenMessageException when message doesn't match with corresponding data.
	 */
	public void OnControlServerUrl_Rendered(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link ServerUrl} control has been written.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when fired method hasn't been overridden.
	 * @throws InconsistenMessageException when message doesn't match with corresponding data.
	 */
	public void OnControlServerUrl_Write(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
}