/**
 * 
 */
package org.httprobot.core.interfaces;

import java.util.Map;

import org.httprobot.common.interfaces.IListener;


/**
 * Correlation data mapping interface.
 * @author joan
 *
 */
public interface IDataMappingImpl<TKeyType, TValueType> 
	extends IListener, Map<TKeyType, TValueType>, Iterable<TKeyType>
{

}
