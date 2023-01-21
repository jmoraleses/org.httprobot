/**
 * 
 */
package org.httprobot.core.managers.interfaces;

import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.contents.interfaces.IManagerFieldRefListener;
import org.httprobot.core.managers.operators.interfaces.IManagerContainsListener;
import org.httprobot.core.managers.operators.interfaces.IManagerEqualsListener;
import org.httprobot.core.managers.operators.interfaces.IManagerRemoveListener;
import org.httprobot.core.managers.operators.interfaces.IManagerReplaceListener;
import org.httprobot.core.managers.operators.interfaces.IManagerSplitListener;
import org.httprobot.core.managers.operators.interfaces.IManagerSubstringListener;
import org.httprobot.core.managers.operators.interfaces.IManagerTryParseListener;
import org.httprobot.core.managers.placeholders.interfaces.IManagerHtmlUnitListener;
import org.httprobot.core.managers.placeholders.interfaces.IManagerHttpAddressListener;

/**
 * @author joan
 *
 */
public interface IPlaceholderManagerImpl extends IManagerImpl,
	IManagerHtmlUnitListener, IManagerHttpAddressListener, IManagerFieldRefListener,
	IManagerContainsListener, IManagerReplaceListener, IManagerEqualsListener,
	IManagerRemoveListener, IManagerSplitListener, IManagerSubstringListener, 
	IManagerTryParseListener
{
	
}
