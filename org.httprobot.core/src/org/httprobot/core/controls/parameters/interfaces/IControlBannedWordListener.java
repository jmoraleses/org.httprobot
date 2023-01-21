/**
 * 
 */
package org.httprobot.core.controls.parameters.interfaces;

import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.params.BannedWord;

/**
 * BannedWord RML message listener interface. Inherits {@link IControlListener}.
 * @author joan
 *
 */
public interface IControlBannedWordListener extends IControlListener 
{
	/**
	 * Fired when {@link BannedWord} RML message has been initialized.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden.
	 * @throws InconsistenMessageException when data doesn't match with corresponding control.
	 */
	public void OnControlBannedWord_Init(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link BannedWord} RML message has been read.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden.
	 * @throws InconsistenMessageException when data doesn't match with corresponding control.
	 */
	public void OnControlBannedWord_Read(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link BannedWord} RML message has been loaded.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden.
	 * @throws InconsistenMessageException when data doesn't match with corresponding control.
	 */
	public void OnControlBannedWord_Loaded(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link BannedWord} RML message has been changed.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden.
	 * @throws InconsistenMessageException when data doesn't match with corresponding control.
	 */
	public void OnControlBannedWord_Changed(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link BannedWord} RML message has been rendered.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden.
	 * @throws InconsistenMessageException when data doesn't match with corresponding control.
	 */
	public void OnControlBannedWord_Rendered(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link BannedWord} RML message has been written.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden.
	 * @throws InconsistenMessageException when data doesn't match with corresponding control.
	 */
	public void OnControlBannedWord_Write(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
}
