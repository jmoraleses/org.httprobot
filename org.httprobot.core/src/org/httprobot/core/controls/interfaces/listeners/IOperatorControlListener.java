package org.httprobot.core.controls.interfaces.listeners;

import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.core.controls.operators.interfaces.IControlContainsListener;
import org.httprobot.core.controls.operators.interfaces.IControlDelimitersListener;
import org.httprobot.core.controls.operators.interfaces.IControlEndIndexListener;
import org.httprobot.core.controls.operators.interfaces.IControlEqualsListener;
import org.httprobot.core.controls.operators.interfaces.IControlRemoveListener;
import org.httprobot.core.controls.operators.interfaces.IControlReplaceListener;
import org.httprobot.core.controls.operators.interfaces.IControlSplitListener;
import org.httprobot.core.controls.operators.interfaces.IControlStartIndexListener;
import org.httprobot.core.controls.operators.interfaces.IControlSubstringListener;
import org.httprobot.core.controls.operators.interfaces.IControlTryParseListener;

/**
 * 
 * Implemented when reading RML Operator elements.
 * The methods described below are executed when an RML Operator object is being read.
 * Inherits {@link IControlListener}.
 * <br>
 * @author Joan
 */
public interface IOperatorControlListener extends IPlaceholderControlListener,
	IControlContainsListener, IControlDelimitersListener, IControlEqualsListener,
	IControlRemoveListener, IControlReplaceListener,
	IControlSplitListener, IControlSubstringListener, IControlTryParseListener, 
	IControlStartIndexListener, IControlEndIndexListener {
}