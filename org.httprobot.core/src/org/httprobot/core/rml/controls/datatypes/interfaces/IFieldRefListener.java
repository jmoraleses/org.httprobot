/**
 * 
 */
package org.httprobot.core.rml.controls.datatypes.interfaces;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;

/**
 * FieldRef RML message listener interface. Inherits {@link IRmlListener}.
 * @author joan
 *
 */
public interface IFieldRefListener extends IRmlListener 
{
	/**
	 * Called when FieldRef message has been initialized.
	 * @param sender the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws InconsistenMessageException when message doesn't match with corresponding data
	 */
	public void OnFieldRefInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Called when FieldRef message has been initialized.
	 * @param sender the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws InconsistenMessageException when message doesn't match with corresponding data
	 */
	public void OnFieldRefRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Called when FieldRef message has been initialized.
	 * @param sender the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws InconsistenMessageException when message doesn't match with corresponding data
	 */
	public void OnFieldRefLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Called when FieldRef message has been initialized.
	 * @param sender the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws InconsistenMessageException when message doesn't match with corresponding data
	 */
	public void OnFieldRefChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Called when FieldRef message has been initialized.
	 * @param sender the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws InconsistenMessageException when message doesn't match with corresponding data
	 */
	public void OnFieldRefRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Called when FieldRef message has been initialized.
	 * @param sender the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws InconsistenMessageException when message doesn't match with corresponding data
	 */
	public void OnFieldRefWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
}
