/**
 * 
 */
package org.httprobot.core.rml.controls.config.interfaces;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;

/**
 * @author Joan
 *
 */
public interface IConfigListener extends IRmlListener
{
	/**
	 * Fired when {@link Config} {@link Rml} has been initialized.  
	 * @param sender {@link Object}
	 * @param e {@link RmlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnConfigInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;

	/**
	 * Fired when {@link Config} {@link Rml} has been initialized.  
	 * @param sender {@link Object}
	 * @param e {@link RmlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnConfigRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link Config} {@link Rml} has been read.  
	 * @param sender {@link Object}
	 * @param e {@link RmlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnConfigChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link Config} {@link Rml} has been loaded.  
	 * @param sender {@link Object}
	 * @param e {@link RmlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException 
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnConfigLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link Config} {@link Rml} has been read.  
	 * @param sender {@link Object}
	 * @param e {@link RmlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnConfigRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link Config} {@link Rml} has been initialized.  
	 * @param sender {@link Object}
	 * @param e {@link RmlEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnConfigWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;

}
