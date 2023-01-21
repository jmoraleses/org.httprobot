package org.httprobot.client.app;

import java.util.EnumSet;

import org.httprobot.client.common.Constants;
import org.httprobot.common.definitions.Enums.RuntimeOptions;

public class Application {

	static AppLoader app_loader = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		EnumSet<RuntimeOptions> options_set = RuntimeOptions.DEFAULT_DEBUG;
		app_loader = new AppLoader(options_set, Constants.APP_CONFIG_DEFAULT_PATH);
	}
}
