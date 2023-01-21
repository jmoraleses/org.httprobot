/**
 * 
 */
package org.httprobot.core.controls.interfaces.impl;

import org.httprobot.core.common.Enums.PlaceholderData;
import org.httprobot.core.controls.interfaces.listeners.IPlaceholderControlListener;
import org.httprobot.core.interfaces.IDataMappingImpl;

/**
 * @author joan
 *
 */
public interface IPlaceholderControlImpl extends IPlaceholderControlListener,
		IDataMappingImpl<PlaceholderData, Object> {

}
