package org.httprobot.common.interfaces;

import java.util.UUID;

import org.httprobot.common.RML;

/**
 * Message control listener interface. 
 * RML controls implement it when reading messages. 
 * Inherits {@link IListener}.
 * @author Joan
 * 
 */
public interface IControlListener extends IListener
{
	/**
	 * @return if inherits parent data.
	 */
	public Boolean getInherited(); 
	/**
	 * @return the Unique ID.
	 */
	public UUID getUuid();	
	/**
	 * @return the {@link RML} message.
	 */
	public RML getMessage();	
}
