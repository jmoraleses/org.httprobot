/**
 * 
 */
package org.httprobot.core.controls.interfaces.impl;

import org.httprobot.core.common.Enums.ContentData;
import org.httprobot.core.controls.interfaces.listeners.IContentControlListener;
import org.httprobot.core.interfaces.IDataMappingImpl;

/**
 * @author joan
 *
 */
public interface IContentControlImpl extends IContentControlListener,
		IDataMappingImpl<ContentData, Object> {

}
