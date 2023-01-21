/**
 * 
 */
package org.httprobot.core.controls.interfaces.impl;

import org.httprobot.core.common.Enums.ConfigData;
import org.httprobot.core.controls.interfaces.listeners.IConfigControlListener;
import org.httprobot.core.interfaces.IDataMappingImpl;

/**
 * @author joan
 *
 */
public interface IConfigControlImpl extends IConfigControlListener,
		IDataMappingImpl<ConfigData, Object> {

}
