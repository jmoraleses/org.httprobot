package org.httprobot.common.interfaces;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.UUID;

import org.httprobot.common.definitions.Enums.CliNamespace;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.w3c.dom.events.EventListener;

/**
 * Command line listener interface.
 * @author Joan
 * 
 */
public interface IListener extends EventListener, Serializable 
{
	/**
	 * @return the Unique ID
	 */
	public UUID getUuid();
	/**
	 * @param uuid the Unique ID. Null to generate a new one.
	 */
	public void setUuid(UUID uuid);
	/**
	 * Gets debug options.
	 * @return {@link EnumSet} of {@link RuntimeOptions}
	 */
	public EnumSet<RuntimeOptions> getRuntimeOptions();
	/**
	 * Sets the debug options.
	 * @param options {@link EnumSet} of {@link RuntimeOptions}
	 */
	public void setRuntimeOptions(EnumSet<RuntimeOptions> options);
	/**
	 * Gets the name space of current class.
	 * @return {@link CliNamespace}
	 */
	public CliNamespace getCliNamespace();
	/**
	 * Gets the name space of current class.
	 * @return {@link String} the destination path
	 */
	public String getDestinationPath();
	/**
	 * Gets the name space of current class.
	 * @return {@link String} the destination path
	 */
	public void setDestinationPath(String destinationPath);
	/**
	 * Fired when a command is received from current implementing class
	 * @param sender {@link Object} who sends the event
	 * @param e {@link CliEventArgs}
	 */
	public void OnCommandInput(Object sender, CliEventArgs e) throws InconsistenMessageException;
	/**
	 * Fired when a command is received from anywhere. 
	 * @param sender {@link Object} the sender
	 * @param e {@link CliEventArgs} the arguments
	 */
	public void OnCommandOutput(Object sender, CliEventArgs e) throws InconsistenMessageException;
}