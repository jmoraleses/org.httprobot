/**
 * 
 */
package org.httprobot.core.managers.interfaces;

import org.httprobot.core.managers.operators.interfaces.IManagerContainsListener;
import org.httprobot.core.managers.operators.interfaces.IManagerEqualsListener;
import org.httprobot.core.managers.operators.interfaces.IManagerRemoveListener;
import org.httprobot.core.managers.operators.interfaces.IManagerReplaceListener;
import org.httprobot.core.managers.operators.interfaces.IManagerSplitListener;
import org.httprobot.core.managers.operators.interfaces.IManagerSubstringListener;
import org.httprobot.core.managers.operators.interfaces.IManagerTryParseListener;

/**
 * @author joan
 *
 */
public interface IOperatorManagerImpl extends IPlaceholderManagerImpl,
	IManagerContainsListener, IManagerReplaceListener, IManagerEqualsListener,
	IManagerRemoveListener, IManagerSplitListener, IManagerSubstringListener, 
	IManagerTryParseListener {
}