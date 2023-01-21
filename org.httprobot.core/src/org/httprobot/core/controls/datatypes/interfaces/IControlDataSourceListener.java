package org.httprobot.core.controls.datatypes.interfaces;

import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;

/**
 * ServerInfo RML message listener interface. Inherits {@link IControlListener}.
 * @author Joan
 *
 */
public interface IControlDataSourceListener extends IControlListener 
{
	/**
	 * When {@link DataSource} message has been read.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlDataSource_Init(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * When {@link DataSource} message has been read.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlDataSource_Read(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;	
	/**
	 * When {@link DataSource} message has been read.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlDataSource_Loaded(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * When {@link DataSource} message has been read.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlDataSource_Changed(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;	
	/**
	 * When {@link DataSource} message has been read.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlDataSource_Rendered(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/**
	 * When {@link DataSource} message has been read.
	 * @param sender the sender
	 * @param e {@link ControlEventArgs} the arguments
	 * @throws NotImplementedException when the implementation is already undone
	 * @throws InconsistenMessageException when message data doesn't match with corresponding controller.	 
	 */
	public void OnControlDataSource_Write(Object sender, ControlEventArgs e) throws NotImplementedException, InconsistenMessageException;		
}