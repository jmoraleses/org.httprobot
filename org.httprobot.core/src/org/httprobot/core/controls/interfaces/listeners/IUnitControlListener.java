/**
 * 
 */
package org.httprobot.core.controls.interfaces.listeners;

import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.core.controls.unit.interfaces.IControlActionListener;
import org.httprobot.core.controls.unit.interfaces.IControlPaginatorListener;
import org.httprobot.core.controls.unit.interfaces.IControlWebOptionsListener;

/**
 * @author joan
 *
 */
public interface IUnitControlListener extends IControlListener,
		IControlActionListener, IControlWebOptionsListener,
		IControlPaginatorListener {

}
