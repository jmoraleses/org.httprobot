/**
 * 
 */
package org.httprobot.core.controls.interfaces.impl;

import org.httprobot.core.common.Enums.UnitData;
import org.httprobot.core.controls.interfaces.listeners.IUnitControlListener;
import org.httprobot.core.interfaces.IDataMappingImpl;

/**
 * @author joan
 *
 */
public interface IUnitControlImpl extends IUnitControlListener,
		IDataMappingImpl<UnitData, Object> {

}
