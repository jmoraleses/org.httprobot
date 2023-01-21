/**
 * 
 */
package org.httprobot.core.controls.contents.interfaces;

import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;

/**
 * ContentTypeRoot message control listener interface.
 * @author joan
 *
 */
public interface IControlContentTypeRootListener extends IControlListener
{
	/**
	 * Fired when {@link ContentTypeRoot} message control has been initialized.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden.
	 * @throws InconsistenMessageException when message doesn't match with corresponding type
	 */
	public void OnControlContentTypeRoot_Init(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link ContentTypeRoot} message control has been read.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden.
	 * @throws InconsistenMessageException when message doesn't match with corresponding type
	 */
	public void OnControlContentTypeRoot_Read(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link ContentTypeRoot} message control has been loaded.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden.
	 * @throws InconsistenMessageException when message doesn't match with corresponding type
	 */
	public void OnControlContentTypeRoot_Loaded(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link ContentTypeRoot} message control has been changed.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden.
	 * @throws InconsistenMessageException when message doesn't match with corresponding type
	 */
	public void OnControlContentTypeRoot_Changed(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link ContentTypeRoot} message control has been rendered.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden.
	 * @throws InconsistenMessageException when message doesn't match with corresponding type
	 */
	public void OnControlContentTypeRoot_Rendered(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link ContentTypeRoot} message control has been written.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden.
	 * @throws InconsistenMessageException when message doesn't match with corresponding type
	 */
	public void OnControlContentTypeRoot_Write(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
}