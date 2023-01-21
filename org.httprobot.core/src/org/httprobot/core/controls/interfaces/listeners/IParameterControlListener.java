/**
 * 
 */
package org.httprobot.core.controls.interfaces.listeners;

import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.core.controls.parameters.interfaces.IControlBannedWordListener;
import org.httprobot.core.controls.parameters.interfaces.IControlConstantListener;
import org.httprobot.core.controls.parameters.interfaces.IControlServerUrlListener;
import org.httprobot.core.controls.parameters.interfaces.IControlStartUrlListener;

/**
 * RML parameter control implementation interface.
 * @author joan
 *
 */
public interface IParameterControlListener 
		extends IControlListener, IControlStartUrlListener, IControlServerUrlListener, 
			IControlBannedWordListener, IControlConstantListener {	
}