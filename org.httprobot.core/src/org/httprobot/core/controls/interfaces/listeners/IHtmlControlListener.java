/**
 * 
 */
package org.httprobot.core.controls.interfaces.listeners;

import org.httprobot.core.controls.html.interfaces.IControlAnchorListener;
import org.httprobot.core.controls.html.interfaces.IControlDivisionListener;
import org.httprobot.core.controls.html.interfaces.IControlElementListener;
import org.httprobot.core.controls.html.interfaces.IControlPageListener;
import org.httprobot.core.controls.html.interfaces.IControlTableCellListener;
import org.httprobot.core.controls.html.interfaces.IControlTableListener;
import org.httprobot.core.controls.html.interfaces.IControlTableRowListener;

/**
 * @author joan
 *
 */
public interface IHtmlControlListener extends IOperatorControlListener,
		IControlAnchorListener, IControlElementListener, IControlPageListener,
		IControlDivisionListener, IControlTableListener, IControlTableRowListener,
		IControlTableCellListener {

}