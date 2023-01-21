/**
 * 
 */
package org.httprobot.core.managers.interfaces;

import org.httprobot.core.managers.html.interfaces.IManagerAnchorListener;
import org.httprobot.core.managers.html.interfaces.IManagerDivisionListener;
import org.httprobot.core.managers.html.interfaces.IManagerElementListener;
import org.httprobot.core.managers.html.interfaces.IManagerPageListener;
import org.httprobot.core.managers.html.interfaces.IManagerTableCellListener;
import org.httprobot.core.managers.html.interfaces.IManagerTableListener;
import org.httprobot.core.managers.html.interfaces.IManagerTableRowListener;

/**
 * @author joan
 *
 */
public interface IHtmlManagerImpl extends IOperatorManagerImpl,
	IManagerPageListener, IManagerElementListener, IManagerAnchorListener, 
	IManagerDivisionListener, IManagerTableListener, IManagerTableRowListener, 
	IManagerTableCellListener {

}