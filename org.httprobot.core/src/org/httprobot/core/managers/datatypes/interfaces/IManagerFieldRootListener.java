/**
 * 
 */
package org.httprobot.core.managers.datatypes.interfaces;

import org.httprobot.common.datatypes.FieldRoot;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.core.contents.solr.InputField;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;

/**
 * {@link FieldRoot} message manager listener implementation interface. 
 * Inherits {@link IManagerImpl}.
 * <br>
 * @author joan
 *
 */
public interface IManagerFieldRootListener 
		extends IManagerImpl {
	
	/**
	 * Fired when all {@link InputField} has been completed.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't overridden.
	 */
	public void OnManagerFieldRoot_DocumentCompleted(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException;
	/**
	 * Fired when Fields message manager has started.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerFieldRoot_Started(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException;
	/**
	 * Fired when Fields message manager has finished.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerFieldRoot_Finished(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException;
	/**
	 * Fired when Fields message manager has finished with errors.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws ManagerException
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManagerFieldRoot_Error(Object sender, ManagerEventArgs e) 
			throws ManagerException, NotImplementedException;
}
