/**
 * 
 */
package org.httprobot.core.controls.interfaces.impl;

import org.httprobot.core.common.Enums.DataTypeData;
import org.httprobot.core.controls.interfaces.listeners.IDataTypeControlListener;
import org.httprobot.core.interfaces.IDataMappingImpl;

/**
 * @author joan
 *
 */
public interface IDataTypeControlImpl extends IDataTypeControlListener,
		IDataMappingImpl<DataTypeData, Object> {

}
