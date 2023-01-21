package org.httprobot.core.rml.controls.interfaces;

import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.core.rml.controls.operators.interfaces.IConditionListener;
import org.httprobot.core.rml.controls.operators.interfaces.IContainsListener;
import org.httprobot.core.rml.controls.operators.interfaces.IDelimitersListener;
import org.httprobot.core.rml.controls.operators.interfaces.IEndIndexListener;
import org.httprobot.core.rml.controls.operators.interfaces.IEqualsListener;
import org.httprobot.core.rml.controls.operators.interfaces.IForEachListener;
import org.httprobot.core.rml.controls.operators.interfaces.IGetFieldListener;
import org.httprobot.core.rml.controls.operators.interfaces.IRemoveListener;
import org.httprobot.core.rml.controls.operators.interfaces.IReplaceListener;
import org.httprobot.core.rml.controls.operators.interfaces.ISelectListener;
import org.httprobot.core.rml.controls.operators.interfaces.ISplitListener;
import org.httprobot.core.rml.controls.operators.interfaces.IStartIndexListener;
import org.httprobot.core.rml.controls.operators.interfaces.ISubstringListener;
import org.httprobot.core.rml.controls.operators.interfaces.ITryParseListener;
import org.httprobot.core.rml.controls.operators.interfaces.IWhereListener;
import org.httprobot.core.rml.controls.operators.interfaces.IXmlQueryListener;

/**
 * @author Joan
 * Implemented when reading RML Operator elements.
 * The methods described below are executed when an RML Operator object is being read.
 * Inherits {@link IRmlListener}.
 */
public interface IRmlOperatorListener extends IRmlPlaceholder,
	IContainsListener, IDelimitersListener, IEqualsListener,
	IForEachListener, IRemoveListener, IReplaceListener,
	ISelectListener, ISplitListener, ISubstringListener,
	ITryParseListener, IWhereListener, IXmlQueryListener,
	IGetFieldListener, IStartIndexListener, IEndIndexListener,
	IConditionListener
{
	
}