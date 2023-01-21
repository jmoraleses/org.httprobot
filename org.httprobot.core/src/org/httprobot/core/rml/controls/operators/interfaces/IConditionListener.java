/**
 * 
 */
package org.httprobot.core.rml.controls.operators.interfaces;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;

/**
 * @author joan
 *
 */
public interface IConditionListener 
{
	/**
	 * Called when Codition RML message has been initialized.
	 * @param sender the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws InconsistenMessageException when data doesn't match with corresponding control
	 */
	public void OnConditionInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Called when Codition RML message has been read.
	 * @param sender the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws InconsistenMessageException when data doesn't match with corresponding control
	 */
	public void OnConditionRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Called when Codition RML message has been loaded.
	 * @param sender the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws InconsistenMessageException when data doesn't match with corresponding control
	 */
	public void OnConditionLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Called when Codition RML message has been changed.
	 * @param sender the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws InconsistenMessageException when data doesn't match with corresponding control
	 */
	public void OnConditionChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Called when Codition RML message has been rendered.
	 * @param sender the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws InconsistenMessageException when data doesn't match with corresponding control
	 */
	public void OnConditionRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Called when Codition RML message has been written.
	 * @param sender the sender
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws InconsistenMessageException when data doesn't match with corresponding control
	 */
	public void OnConditionWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
}
