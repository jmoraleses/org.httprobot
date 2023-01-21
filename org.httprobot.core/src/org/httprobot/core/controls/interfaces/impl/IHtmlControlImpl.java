/**
 * 
 */
package org.httprobot.core.controls.interfaces.impl;

import org.httprobot.core.common.Enums.HtmlData;
import org.httprobot.core.controls.interfaces.listeners.IHtmlControlListener;
import org.httprobot.core.interfaces.IDataMappingImpl;

/**
 * @author joan
 *
 */
public interface IHtmlControlImpl extends IHtmlControlListener,
		IDataMappingImpl<HtmlData, Object> {

}
