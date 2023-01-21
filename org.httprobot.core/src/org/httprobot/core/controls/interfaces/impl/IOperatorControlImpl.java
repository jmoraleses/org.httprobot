/**
 * 
 */
package org.httprobot.core.controls.interfaces.impl;

import org.httprobot.core.common.Enums.OperatorData;
import org.httprobot.core.controls.interfaces.listeners.IOperatorControlListener;
import org.httprobot.core.interfaces.IDataMappingImpl;

/**
 * @author joan
 *
 */
public interface IOperatorControlImpl extends IOperatorControlListener,
		IDataMappingImpl<OperatorData, Object> {

}
