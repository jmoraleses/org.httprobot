package org.httprobot.common.interfaces;

import java.io.OutputStream;
import java.util.UUID;

import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;

/**
 * @author Joan
 * Implemented when reading RML elements.
 * The methods described below are executed when an RML object is being read.
 */
public interface IRmlListener extends IListener
{	
	/**
	 * @return
	 */
	public UUID getUuid(); 
	public Boolean getInherited();
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


}
