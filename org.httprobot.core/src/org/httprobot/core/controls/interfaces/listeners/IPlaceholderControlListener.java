/**
 * 
 */
package org.httprobot.core.controls.interfaces.listeners;

import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.core.controls.placeholders.interfaces.IControlHtmlUnitListener;
import org.httprobot.core.controls.placeholders.interfaces.IControlHttpAddressListener;
/**
 * RML control place holder implementation interface. 
 * Inherits {@link IDataTypeControlListener}, {@link IControlHttpAddressListener},
 * {@link IControlHtmlUnitListener}.
 * @author Joan
 *
 */
public interface IPlaceholderControlListener extends IControlListener, 
	IControlHttpAddressListener, IControlHtmlUnitListener
{
	
}