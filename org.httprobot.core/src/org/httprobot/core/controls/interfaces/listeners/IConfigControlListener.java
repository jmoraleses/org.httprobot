package org.httprobot.core.controls.interfaces.listeners;

import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.core.controls.config.interfaces.IControlConfigurationListener;
import org.httprobot.core.controls.config.interfaces.IControlLogListener;
import org.httprobot.core.controls.config.interfaces.IControlServiceConnectionListener;
import org.httprobot.core.controls.config.interfaces.IControlSessionListener;

/**
 * RML configuration control implementation interface.
 * Inherits {@link IControlListener}, {@link IControlConfigurationListener}, 
 * {@link IControlSessionListener}, {@link IControlLogListener}
 * @author Joan
 *
 */
public interface IConfigControlListener extends IControlListener,
	IControlConfigurationListener, IControlSessionListener, IControlLogListener,
	IControlServiceConnectionListener
{
	
}
