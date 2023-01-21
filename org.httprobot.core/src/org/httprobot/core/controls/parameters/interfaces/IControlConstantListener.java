/**
 * 
 */
package org.httprobot.core.controls.parameters.interfaces;

import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.params.Constant;

/**
 * Constant RML message listener interface. Inherits {@link IControlListener}.
 * @author joan
 *
 */
public interface IControlConstantListener extends IControlListener 
{
	/**
	 * Fired when {@link Constant} RML message has been initialized.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden.
	 * @throws InconsistenMessageException when data doesn't match with corresponding control.
	 */
	public void OnControlConstant_Init(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link Constant} RML message has been read.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden.
	 * @throws InconsistenMessageException when data doesn't match with corresponding control.
	 */
	public void OnControlConstant_Read(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link Constant} RML message has been loaded.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden.
	 * @throws InconsistenMessageException when data doesn't match with corresponding control.
	 */
	public void OnControlConstant_Loaded(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link Constant} RML message has been changed.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden.
	 * @throws InconsistenMessageException when data doesn't match with corresponding control.
	 */
	public void OnControlConstant_Changed(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link Constant} RML message has been rendered.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden.
	 * @throws InconsistenMessageException when data doesn't match with corresponding control.
	 */
	public void OnControlConstant_Rendered(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link Constant} RML message has been written.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden.
	 * @throws InconsistenMessageException when data doesn't match with corresponding control.
	 */
	public void OnControlConstant_Write(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
}