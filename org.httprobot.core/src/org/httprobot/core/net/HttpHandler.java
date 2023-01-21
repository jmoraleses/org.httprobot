package org.httprobot.core.net;

import java.util.Vector;

import org.httprobot.core.interfaces.IWebLoaderListener;

public class HttpHandler implements Runnable 
{
	/**
	 * HTTP address listeners.
	 */
	private Vector<IWebLoaderListener> web_tab_listeners = null;
	
	
	public HttpHandler()
	{
		
	}

	

	@Override
	public void run() 
	{
		
	}



	public void setWeb_tab_listeners(Vector<IWebLoaderListener> web_tab_listeners) 
	{
		this.web_tab_listeners = web_tab_listeners;
	}



	public Vector<IWebLoaderListener> getWeb_tab_listeners() 
	{
		return web_tab_listeners;
	}
	
	
}
