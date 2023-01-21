package org.httprobot.core.interfaces;

import org.httprobot.core.events.HttpEventArgs;

public interface IHttpHandlerImpl 
{
	void OnGetResponseReceived(Object sender, HttpEventArgs e);
	void OnPostResponseReceived(Object sender, HttpEventArgs e);
}
