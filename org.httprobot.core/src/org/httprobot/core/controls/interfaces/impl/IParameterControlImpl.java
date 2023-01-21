/**
 * 
 */
package org.httprobot.core.controls.interfaces.impl;

import org.httprobot.core.common.Enums.ParameterData;
import org.httprobot.core.controls.interfaces.listeners.IParameterControlListener;
import org.httprobot.core.interfaces.IDataMappingImpl;

/**
 * @author joan
 *
 */
public interface IParameterControlImpl extends IParameterControlListener,
		IDataMappingImpl<ParameterData, Object> {

}
