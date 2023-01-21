/**
 * 
 */
package org.httprobot.core.managers.interfaces;

import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.config.interfaces.IManagerConfigurationListener;
import org.httprobot.core.managers.config.interfaces.IManagerLogListener;
import org.httprobot.core.managers.config.interfaces.IManagerServiceConnectionListener;
import org.httprobot.core.managers.config.interfaces.IManagerSessionListener;

/**
 * @author joan
 *
 */
public interface IConfigManagerImpl extends IManagerImpl, 
		IManagerConfigurationListener,IManagerLogListener, IManagerSessionListener, 
		IManagerServiceConnectionListener {

}
