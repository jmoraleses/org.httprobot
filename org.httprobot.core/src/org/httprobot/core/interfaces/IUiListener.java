package org.httprobot.core.interfaces;

import org.httprobot.common.events.UiEventArgs;
import org.httprobot.common.interfaces.IListener;

/**
 * @author Joan
 * UI manager listener interface. Inherits {@link IListener}.
 */
public interface IUiListener extends IManagerImpl
{
	/**
	 * Fired when UI manager has changed.
	 * @param sender {@link Object} the sender
	 * @param e {@link UiEventArgs} the arguments
	 */
	void OnUiChanged(Object sender, UiEventArgs e);
}