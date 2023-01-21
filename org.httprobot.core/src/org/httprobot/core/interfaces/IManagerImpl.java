/**
 * 
 */
package org.httprobot.core.interfaces;

import java.util.Iterator;
import java.util.Map;

import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IListener;
import org.httprobot.common.params.Constant;
import org.httprobot.core.contents.DocumentLibrary;
import org.httprobot.core.contents.TemplateLibrary;
import org.httprobot.core.events.ManagerEventArgs;

import org.httprobot.core.managers.Manager;

/**
 * Message manager implementation interface.
 * Inherits {@link IListener}.
 * <br>
 * @author joan
 *
 */
public interface IManagerImpl 
		extends IListener, Iterator<IManagerImpl> {
	
	/**
	 * Starts the manager.
	 */
	public void start();
	/**
	 * Stops the manager.
	 */
	public void stop();
	/**
	 * Sets the current manager index to 0.
	 * It's ready again to be iterated.
	 */
	public void reset();
	public void addChildManager(IManagerImpl manager)
			throws NotImplementedException, ManagerException;
	/**
	 * @return Declared {@link Constant} parameters dictionary.
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws ManagerException
	 */
	public Map<String, String> getParameterConstants() 
			throws NotImplementedException, ManagerException;	
	/**
	 * @return declared configuration banned words dictionary
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws ManagerException
	 */
	public Map<String, String> getParameterBannedWords() 
			throws NotImplementedException, ManagerException;
	/**
	 * @return the current manager's input document handler
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws ManagerException
	 */
	public TemplateLibrary getTemplateLibrary() 
			throws NotImplementedException, ManagerException;
	/**
	 * @return the current {@link DocumentLibrary}.
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws ManagerException
	 */
	public DocumentLibrary getDocumentLibrary() 
			throws NotImplementedException, ManagerException;
	/**
	 * @return the {@link ContentTypeRoot} collection
	 * @throws NotImplementedException when method hasn't been overridden
	 * @throws ManagerException
	 */
	public ContentTypeRoot getContentTypeRoot() 
			throws NotImplementedException, ManagerException;
	/**
	 * Fired when a {@link Manager} has received new parameter.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws InconsistenMessageException when received message is not valid
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManager_ParamAdded(Object sender, ManagerEventArgs e) 
			throws InconsistenMessageException, NotImplementedException;
	/**
	 * Fired when a {@link Manager} has received new banned word.
	 * @param sender the sender
	 * @param e {@link ManagerEventArgs} the arguments
	 * @throws InconsistenMessageException when received message is not valid
	 * @throws NotImplementedException when method hasn't been overridden
	 */
	public void OnManager_BannedAdded(Object sender, ManagerEventArgs e) 
			throws InconsistenMessageException, NotImplementedException;
}