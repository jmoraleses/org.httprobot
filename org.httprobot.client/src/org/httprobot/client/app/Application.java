package org.httprobot.client.app;

import org.httprobot.client.common.Constants;

public class Application {

	static AppLoader app_loader = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		app_loader = new AppLoader(Constants.APP_CONFIG_DEFAULT_PATH);
	}
}
