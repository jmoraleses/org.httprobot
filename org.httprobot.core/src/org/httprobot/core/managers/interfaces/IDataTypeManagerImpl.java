/**
 * 
 */
package org.httprobot.core.managers.interfaces;

import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.datatypes.interfaces.IManagerActionListener;
import org.httprobot.core.managers.datatypes.interfaces.IManagerDataSourceListener;
import org.httprobot.core.managers.datatypes.interfaces.IManagerFieldListener;
import org.httprobot.core.managers.datatypes.interfaces.IManagerFieldRootListener;
import org.httprobot.core.managers.datatypes.interfaces.IManagerDocumentListener;
import org.httprobot.core.managers.datatypes.interfaces.IManagerDocumentRootListener;

/**
 * @author joan
 *
 */
public interface IDataTypeManagerImpl extends IManagerImpl, 
	IManagerFieldListener, IManagerFieldRootListener, IManagerActionListener, 
	IManagerDocumentListener, IManagerDocumentRootListener,
	IManagerDataSourceListener {

}
