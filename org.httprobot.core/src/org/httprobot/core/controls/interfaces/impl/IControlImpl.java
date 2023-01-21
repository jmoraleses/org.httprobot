/**
 * 
 */
package org.httprobot.core.controls.interfaces.impl;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.ResourceBundle.Control;

import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;

/**
 * {@link Control} implementation interface. Inherits {@link IControlListener}.
 * <br>
 * @author joan
 *
 */
public interface IControlImpl
	extends IControlListener, Iterator<IControlImpl> {

	/**
	 * Fired when message control has changed.
	 * @param sender {@link Object} the sender
	 * @param e {@link CliEventArgs}
	 * @throws NotImplementedException when method is not overridden,
	 */
	public void Change(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when message control has been initialized
	 * @param sender {@link Object} the sender
	 * @param e {@link CliEventArgs}
	 * @throws NotImplementedException when method is not overridden,
	 */
	public void Initialize(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when control has been loaded
	 * @param sender {@link Object} the sender
	 * @param e {@link CliEventArgs}
	 * @throws NotImplementedException when method is not overridden,
	 */
	public void Load(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when control has been marshalled from {@link OutputStream}.
	 * @param sender the sender 
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method is not overridden,
	 */
	public void Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;
	/**
	 * @param sender {@link Object} who sends the event
	 * @param e {@link CliEventArgs} the sender
	 * @throws NotImplementedException when method is not overridden,
	 */
	public void Render(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when object has been unmarshalled from {@link OutputStream}.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when method is not overridden,
	 */
	public void Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;
}
