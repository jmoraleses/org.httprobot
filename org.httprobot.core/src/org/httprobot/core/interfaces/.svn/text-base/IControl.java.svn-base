package org.httprobot.core.interfaces;

import java.io.OutputStream;
import java.io.Serializable;

import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.Rml;

/**
 * 
 * @author Joan
 *
 */
public interface IControl extends Serializable
{
	/**
	 * Gets render status.
	 * @return {@link Boolean} the value
	 */
	public abstract Boolean getIsRendered();
	/**
	 * Gets control's {@link Rml2} message.
	 * @return {@link Rml2} RML message object 
	 */
	public abstract Rml getMessage();
	/**
	 * Gets the {@link IRmlListener} parent.
	 * @return {@link IRmlListener} the parent
	 */
	public abstract IRmlListener getParent();
	/**
	 * Sets render status. Fires {@link IRmlListener} Render event.
	 * @param value {@link Boolean} the value
	 */
	public abstract void setIsRendered(Boolean value);
	/**
	 * Sets {@link Rml} message to control. Fires {@link IRmlListener} events.
	 * @param message {@link RmlObjsect} the message
	 */
	public abstract void setMessage(Rml message);
	/**
	 * Fired when RML Object is initialized
	 * @param sender {@link Object} who sends the event
	 * @param e {@link CliEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 */
	public void OnControlInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when RML Object is loaded
	 * @param sender {@link Object} who sends the event
	 * @param e {@link CliEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 */
	public void OnControlLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * @param sender {@link Object} who sends the event
	 * @param e {@link CliEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 */
	public void OnControlChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * @param sender {@link Object} who sends the event
	 * @param e {@link CliEventArgs}
	 * @throws NotImplementedException when the implementation is already undone
	 */
	public void OnControlRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when object has been unmarshalled from {@link OutputStream}.
	 * @param sender 
	 * @param e
	 * @throws NotImplementedException
	 */
	public void OnControlWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when object has been unmarshalled from {@link OutputStream}.
	 * @param sender 
	 * @param e
	 * @throws NotImplementedException
	 */
	public void OnControlRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;

}