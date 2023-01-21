/**
 * 
 */
package org.httprobot.common.interfaces;

import java.io.ObjectInputStream;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.io.MarshalLayer;

/**
 * @author Joan
 *
 */
public interface IMarshallerListener extends IListener
{	
	/**
	 * Fired when {@link Object} extends {@link MarshalLayer} is marshalled.
	 * @param sender {@link ObjectInputStream}
	 * @param e {@link MarshallerEventArgs} the arguments
	 */
	public void OnObjectMarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * Fired when {@link Object} extends {@link MarshalLayer} is unmarshalled.
	 * @param sender {@link Object} who fires the event
	 * @param e {@link MarshallerEventArgs} the arguments
	 * @throws NotImplementedException 
	 */
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException;
}