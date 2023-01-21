/**
 * 
 */
package org.httprobot.core.managers.interfaces;

import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.contents.interfaces.IManagerContentTypeListener;
import org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRefListener;
import org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRootListener;
import org.httprobot.core.managers.contents.interfaces.IManagerDataViewListener;
import org.httprobot.core.managers.contents.interfaces.IManagerFieldRefListener;

/**
 * @author joan
 *
 */
public interface IContentManagerImpl extends IManagerImpl,
	IManagerDataViewListener, IManagerContentTypeRootListener, IManagerContentTypeListener,
	IManagerFieldRefListener, IManagerContentTypeRefListener
{

}
