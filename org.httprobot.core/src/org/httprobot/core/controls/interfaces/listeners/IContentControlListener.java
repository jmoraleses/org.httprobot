/**
 * 
 */
package org.httprobot.core.controls.interfaces.listeners;

import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.core.controls.contents.interfaces.IControlContentTypeListener;
import org.httprobot.core.controls.contents.interfaces.IControlContentTypeRefListener;
import org.httprobot.core.controls.contents.interfaces.IControlContentTypeRootListener;
import org.httprobot.core.controls.contents.interfaces.IControlDataViewListener;
import org.httprobot.core.controls.contents.interfaces.IControlFieldRefListener;

/**
 * @author joan
 *
 */
public interface IContentControlListener extends IControlListener,
	IControlContentTypeListener, IControlContentTypeRootListener, IControlContentTypeRefListener,
	IControlDataViewListener, IControlFieldRefListener
{

}
