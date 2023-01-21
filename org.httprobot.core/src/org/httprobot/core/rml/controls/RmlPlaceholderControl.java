/**
 * 
 */
package org.httprobot.core.rml.controls;

import java.util.Vector;

import org.httprobot.common.definitions.Enums.RmlEventType;
import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.Rml;
import org.httprobot.common.rml.datatypes.placeholders.HttpRequest;
import org.httprobot.common.rml.datatypes.placeholders.HtmlBody;
import org.httprobot.common.tools.CommandLineInterface;
import org.httprobot.core.events.RmlPlaceholderEventArgs;
import org.httprobot.core.rml.controls.interfaces.IRmlPlaceholder;
import org.httprobot.core.rml.controls.placeholders.HtmlBodyControl;
import org.httprobot.core.rml.controls.placeholders.HttpRequestControl;
import org.httprobot.core.rml.controls.placeholders.interfaces.IHttpRequestListener;
import org.httprobot.core.rml.controls.placeholders.interfaces.IHtmlBodyListener;


/**
 * @author Joan
 *
 */
public abstract class RmlPlaceholderControl extends RmlDataTypeControl implements IRmlPlaceholder
{
	/**
	 * 7430074633996268936L
	 */
	private static final long serialVersionUID = 7430074633996268936L;		
	/**
	 * WebRequest Listeners
	 */
	private Vector<IHttpRequestListener> http_request_listeners;
	/**
	 * WebResponse Listeners
	 */
	private Vector<IHtmlBodyListener> html_body_listeners;
	/**
	 * Where all operators will be working.
	 */
	private String points;
	/**
	 * @param parent
	 */
	public RmlPlaceholderControl()
	{
		super();
		InitPlaceholderControl(null, null);
	}	
	/**
	 * @param parent
	 */
	public RmlPlaceholderControl(IRmlListener parent, Rml message) {
		super(parent, message);
		InitPlaceholderControl(parent, message);
	}
	
	public void InitPlaceholderControl(IRmlListener parent, Rml message)
	{
		this.html_body_listeners = new Vector<IHtmlBodyListener>();
		this.http_request_listeners = new Vector<IHttpRequestListener>();
		
		addHtmlBodyListener(this);
		addHttpRequestListener(this);
		
		RmlEventArgs e = new RmlEventArgs(this, RmlEventType.INIT, message);
		
		if(e.getSource() instanceof HttpRequestControl)
		{
			ControlHttpRequestEvent(e);
		}
		if(e.getSource() instanceof HtmlBodyControl)
		{
			ControlHtmlBodyEvent(e);
		}
	}
	/**
	 * Removes request load listener
	 * @param listener {@link IRmlPlaceholder} the listener
	 */
	public final synchronized void addHttpRequestListener(IHttpRequestListener listener)
	{
		this.http_request_listeners.add(listener);
	}
	/**
	 * Removes request load listener
	 * @param listener {@link IRmlPlaceholder} the listener
	 */
	public final synchronized void addHtmlBodyListener(IHtmlBodyListener listener)
	{
		this.html_body_listeners.add(listener);
	}
	/**
	 * Fires {@link RmlPlaceholderControl} event.
	 * @param e {@link RmlPlaceholderEventArgs} the arguments
	 */
	protected final void ControlHttpRequestEvent(RmlEventArgs e) 
	{
		for (IHttpRequestListener listener : http_request_listeners) 
		{
			try
			{
				switch (e.getRmlEventType()) 
				{
					case INIT:
						listener.OnHttpRequestInit(this, e);
						break;
					case READ:
						listener.OnHttpRequestRead(this, e);
						break;
					case LOAD:
						listener.OnHttpRequestLoaded(this, e);
						break;
					case CHANGE:
						listener.OnHttpRequestChanged(this, e);
						break;
					case RENDER:
						listener.OnHttpRequestRendered(this, e);
						break;
					case WRITE:
						listener.OnHttpRequestWrite(this, e);
						break;
					default:
						break;
				}
			}
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			}
			catch (InconsistenMessageException e2) 
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Fires {@link RmlPlaceholderControl} event.
	 * @param e {@link RmlPlaceholderEventArgs} the arguments
	 */
	protected final void ControlHtmlBodyEvent(RmlEventArgs e) 
	{
		for (IHtmlBodyListener listener : html_body_listeners) 
		{
			try 
			{
				switch (e.getRmlEventType()) 
				{
					case INIT:
						listener.OnHtmlBodyInit(this, e);
						break;
					case READ:
						listener.OnHtmlBodyRead(this, e);
						break;
					case LOAD:
						listener.OnHtmlBodyLoaded(this, e);
						break;
					case CHANGE:
						listener.OnHtmlBodyChanged(this, e);
						break;
					case RENDER:
						listener.OnHtmlBodyRendered(this, e);
						break;
					case WRITE:
						listener.OnHtmlBodyWrite(this, e);
						break;
					default:
						break;
				}				
			} 
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			} 
			catch (InconsistenMessageException e2) 
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * @return the points
	 */
	public String getPoints() {
		return points;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlControl#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public abstract void OnControlChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlControl#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	public abstract void OnControlInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public abstract void OnControlLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlControl#OnControlRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public abstract void OnControlRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlControl#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public abstract void OnControlRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlControl#OnControlWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public abstract void OnControlWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.placeholders.interfaces.IWebRequestControl#OnWebRequestChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHttpRequestChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlPlaceholderControl.OnHttpRequestChanged not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.placeholders.interfaces.IWebRequestControl#OnWebRequestInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHttpRequestInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlPlaceholderControl.OnHttpRequestInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlPlaceholder#OnWebRequestLoaded(java.lang.Object, org.httprobot.common.events.PlaceholderEventArgs)
	 */
	@Override
	public void OnHttpRequestLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlPlaceholderControl.OnHttpRequestLoaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.placeholders.interfaces.IWebRequestControl#OnWebRequestRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHttpRequestRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlPlaceholderControl.OnHttpRequestRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlPlaceholder#OnWebRequestRendered(java.lang.Object, org.httprobot.common.events.PlaceholderEventArgs)
	 */
	@Override
	public void OnHttpRequestRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlPlaceholderControl.OnHttpRequestRendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.placeholders.interfaces.IWebRequestControl#OnWebRequestWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHttpRequestWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlPlaceholderControl.OnHttpRequestWrite not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.placeholders.interfaces.IWebResponseControl#OnWebResponseChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHtmlBodyChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlPlaceholderControl.OnHtmlBodyChanged not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.placeholders.interfaces.IWebResponseControl#OnWebResponseInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHtmlBodyInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlPlaceholderControl.OnHtmlBodyInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlPlaceholder#OnWebResponseLoaded(java.lang.Object, org.httprobot.common.events.PlaceholderEventArgs)
	 */
	@Override
	public void OnHtmlBodyLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlPlaceholderControl.OnHtmlBodyLoaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.placeholders.interfaces.IWebResponseControl#OnWebResponseRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHtmlBodyRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlPlaceholderControl.OnHtmlBodyRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlPlaceholder#OnWebResponseRendered(java.lang.Object, org.httprobot.common.events.PlaceholderEventArgs)
	 */
	@Override
	public void OnHtmlBodyRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlPlaceholderControl.OnHtmlBodyRendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.placeholders.interfaces.IWebResponseControl#OnWebResponseWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHtmlBodyWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlPlaceholderControl.OnHtmlBodyWrite not implemented method");
	}
	/**Removes request render listener
	 * @param listener {@link IRmlPlaceholder} the listener
	 */
	public final synchronized void removeWebRequestListener(IRmlPlaceholder listener)
	{
		this.http_request_listeners.remove(listener);
	}
	/**
	 * Adds request render listener
	 * @param listener {@link IRmlPlaceholder} the listener
	 */
	public final synchronized void removeWebResponseListener(IRmlPlaceholder listener)
	{
		this.html_body_listeners.remove(listener);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#setIsRendered(java.lang.Boolean)
	 */
	@Override
	public void setIsRendered(Boolean value)
	{
		super.setIsRendered(value);
		
		if(this.getMessage() instanceof HtmlBody)
		{
			ControlHtmlBodyEvent(new RmlEventArgs(this, RmlEventType.RENDER, this.getMessage()));
		}
		else if(this.getMessage() instanceof HttpRequest)
		{
			ControlHttpRequestEvent(new RmlEventArgs(this, RmlEventType.RENDER, this.getMessage()));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#setMessage(org.httprobot.common.rml.RmlObject)
	 */
	@Override
	public void setMessage(Rml message) 
	{
		super.setMessage(message);

		if(message instanceof HtmlBody)
		{
			ControlHtmlBodyEvent(new RmlEventArgs(this, RmlEventType.LOAD, message));
		}
		else if(message instanceof HttpRequest)
		{
			ControlHttpRequestEvent(new RmlEventArgs(this, RmlEventType.LOAD, message));
		}
	}
	/**
	 * @param points the points to set
	 */
	public void setPoints(String points) 
	{
		this.points = points;
	}	
	/**
	 * Removes request load listener
	 * @param listener {@link IHttpRequestListener} the listener
	 */
	public final synchronized void removeWebRequestListener(IHttpRequestListener listener)
	{
		this.http_request_listeners.remove(listener);
	}
	/**
	 * Removes web response load listener
	 * @param listener {@link IHtmlBodyListener} the listener
	 */
	public final synchronized void removeWebResponseListener(IHtmlBodyListener listener)
	{
		this.html_body_listeners.remove(listener);
	}
}