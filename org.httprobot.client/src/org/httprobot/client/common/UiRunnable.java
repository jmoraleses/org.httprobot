package org.httprobot.client.common;

import org.httprobot.common.interfaces.IUiListener;

/**
 * @author Joan
 * UI runnable class. Is {@link Runnable}.
 */
public class UiRunnable implements Runnable
{
	static IUiListener application;	
	/**
	 * Gets the application.
	 * @return {@link IUiListener}
	 */
	public IUiListener getApplication() {
		return application;
	}
	public void setListener(IUiListener application2) {
		application = application2;
	}
	/**
	 * UiRunnable class constructor.
	 * @param application2 {@link IUiListener}
	 */
	public UiRunnable(IUiListener application2)
	{
		 application = application2;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
	
	}
}
