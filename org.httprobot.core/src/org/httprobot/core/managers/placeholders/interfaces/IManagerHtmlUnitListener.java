/**
 * 
 */
package org.httprobot.core.managers.placeholders.interfaces;

import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.HtmlUnit;
import org.httprobot.core.contents.solr.InputField;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;

/**
 * {@link HtmlUnit} message manager interface. Inherits {@link IManagerImpl}.
 * <br>
 * @author joan
 *
 */
public interface IManagerHtmlUnitListener extends IManagerImpl
{
	/**
	 * Fired when {@link HtmlUnit} message manager has completed an {@link InputField}.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException custom exception
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerHtmlUnit_InputFieldCompleted(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException;
	/**
	 * Fired when {@link HtmlUnit} message manager has started.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException custom exception
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerHtmlUnit_Started(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException;
	/**
	 * Fired when {@link HtmlUnit} message manager has finished.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException custom exception
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerHtmlUnit_Finished(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException;
	/**
	 * Fired when {@link HtmlUnit} message manager has finished with errors.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException custom exception
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerHtmlUnit_Error(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException;
}