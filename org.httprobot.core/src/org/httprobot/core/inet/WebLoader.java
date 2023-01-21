package org.httprobot.core.inet;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.EnumSet;
import java.util.UUID;
import java.util.Vector;

import org.httprobot.common.definitions.Enums.CliNamespace;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.definitions.Enums.WebLoaderEventType;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.datatypes.WebOptions;
import org.httprobot.common.tools.CommandLineInterface;
import org.httprobot.core.events.WebLoaderEventArgs;
import org.httprobot.core.interfaces.IWebLoaderListener;
import org.httprobot.core.rml.controls.datatypes.ActionControl;
import org.httprobot.core.rml.controls.datatypes.interfaces.IActionListener;
import org.httprobot.core.rml.controls.datatypes.interfaces.IFieldListener;
import org.httprobot.core.rml.controls.datatypes.interfaces.IWebOptionsListener;
import org.w3c.dom.events.Event;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebWindowEvent;
import com.gargoylesoftware.htmlunit.WebWindowListener;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Web Tab class. Inherits {@link WebClient}. Is {@link WebWindowListener}.
 * @author Joan
 *
 */
public class WebLoader extends WebClient
	implements WebWindowListener, IWebOptionsListener, IActionListener
{
	//Command line members
	private static final CliNamespace cliNamespace = CliNamespace.INET;
	/**
	 * 1000671316568198372L
	 */
	private static final long serialVersionUID = 1000671316568198372L;

	private HtmlPage currentPage;
	private String destinationPath;
	
	private Integer timePeriod;
	
	private String httpAddress;
	private UUID uuid;
	private Vector<IWebLoaderListener> webLoaderListeners;
	
	/**
	 * WebTab class constructor.
	 * @param parent {@link IWebLoaderListener} the parent
	 * @param browserVersion {@link BrowserVersion} the version of the current web tab
	 */
	public WebLoader(IWebLoaderListener parent, BrowserVersion browserVersion) 
	{
		super(browserVersion);
		this.webLoaderListeners = new Vector<IWebLoaderListener>();
		this.addWebWindowListener(this);
	}
	/**
	 * Adds {@link IRmlListener} Command event listener.
	 * @param listener {@link IRmlListener} the listener
	 */
	public final synchronized void addWebTabListener(IWebLoaderListener listener)
	{
		this.webLoaderListeners.add(listener);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getCliNamespace()
	 */
	@Override
	public CliNamespace getCliNamespace() 
	{
		return cliNamespace;
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getRuntimeOptions()
	 */
	@Override
	public EnumSet<RuntimeOptions> getRuntimeOptions() 
	{
		return null;
	}
	/**
	 * Gets the current page.
	 * @return {@link HtmlPage} the current page
	 */
	public HtmlPage getCurrentPage() 
	{
		return this.currentPage;
	}
	@Override
	public String getDestinationPath()
	{
		return this.destinationPath;
	}
	/**
	 * Gets the current HTTP address.
	 * @return {@link String} the HTTP address
	 */
	public String getHttpAddress() 
	{
		return this.httpAddress;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getUuid()
	 */
	@Override
	public UUID getUuid()
	{
		return this.uuid;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e)
	{
		switch (e.getCmd()) 
		{
		case RUN_WEB_REQUEST:
			
			break;
		case TREAT_WEB_RESPONSE:
			break;

		default:
			break;
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e) 
	{
		
	}
	/**
	 * Refresh current request 
	 */
	public void refresh()
	{
		setHttpAddress(this.httpAddress);
	}
	/**
	 * Removes {@link IRmlListener} Command event listener.
	 * @param listener {@link IRmlListener} the listener
	 */
	public final synchronized void removeWebTabListener(IWebLoaderListener listener)
	{
		webLoaderListeners.remove(listener);
	}
	/**
	 * Sends a request.
	 * @param httpAddress
	 */
	public void SendRequest(String httpAddress)
	{
		try 
		{
			try 
			{
				Thread.sleep(this.timePeriod);
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
			this.currentPage = HtmlPage.class.cast(this.getPage(httpAddress));
			WebLoaderEvent(new WebLoaderEventArgs(this.currentPage, WebLoaderEventType.COMPLETED));
		}
		catch (FailingHttpStatusCodeException ex1) 
		{
			ex1.printStackTrace();
			WebLoaderEvent(new WebLoaderEventArgs(this, WebLoaderEventType.ERROR));
		}
		catch (MalformedURLException ex2) 
		{
			ex2.printStackTrace();
			WebLoaderEvent(new WebLoaderEventArgs(this, WebLoaderEventType.ERROR));
		} 
		catch (IOException ex3) 
		{
			ex3.printStackTrace();
			WebLoaderEvent(new WebLoaderEventArgs(this, WebLoaderEventType.ERROR));
		}
		finally
		{
//			WebTabStoppedEvent(new WebRequesterEventArgs(this, WebRequesterEventType.ERROR));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#setCliOptions(java.util.EnumSet)
	 */
	@Override
	public void setRuntimeOptions(EnumSet<RuntimeOptions> options) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#setDestinationPath(java.lang.String)
	 */
	@Override
	public void setDestinationPath(String destinationPath)
	{
		this.destinationPath = destinationPath;
	}
	/**
	 * Sets the HTTP address.
	 * @param httpAddress {@link String} the HTTP address
	 */
	public void setHttpAddress(String httpAddress) 
	{
		WebLoaderEvent(new WebLoaderEventArgs(httpAddress, WebLoaderEventType.CHANGED));
		this.httpAddress = httpAddress;
		
		try 
		{
			SendRequest(httpAddress);
		} 
		catch (FailingHttpStatusCodeException e) 
		{
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#setUuid(java.util.UUID)
	 */
	@Override
	public void setUuid(UUID uuid) 
	{
		this.uuid = uuid;
	}
	/**
	 * Starts process.
	 */
	public void start() 
	{

	}
	/* (non-Javadoc)
	 * @see com.gargoylesoftware.htmlunit.WebWindowListener#webWindowClosed(com.gargoylesoftware.htmlunit.WebWindowEvent)
	 */
	@Override
	public void webWindowClosed(WebWindowEvent event) 
	{	
		//TODO webWindowsClosed
	}
	/* (non-Javadoc)
	 * @see com.gargoylesoftware.htmlunit.WebWindowListener#webWindowContentChanged(com.gargoylesoftware.htmlunit.WebWindowEvent)
	 */
	@Override
	public void webWindowContentChanged(WebWindowEvent event) 
	{
		//TODO webWindowsContentChanged
	}
	/* (non-Javadoc)
	 * @see com.gargoylesoftware.htmlunit.WebWindowListener#webWindowOpened(com.gargoylesoftware.htmlunit.WebWindowEvent)
	 */
	@Override
	public void webWindowOpened(WebWindowEvent event) 
	{
		//TODO WebWindowOpened
	}
	
	/**
	 * Fires web loader event
	 * @param e {@link WebLoaderEventArgs} the arguments
	 */
	protected final void WebLoaderEvent(WebLoaderEventArgs e)
	{
		for(IWebLoaderListener listener : this.webLoaderListeners)
		{
			switch (e.getIet()) {
			case CHANGED:
				listener.OnWebLoaderAddressChanged(this, e);
				break;
			case COMPLETED:
				listener.OnWebLoaderCompleted(this, e);
				break;
			case STOPPED:
				listener.OnWebLoaderStopped(this, e);
				break;
			case STARTED:
				listener.OnWebLoaderStarted(this, e);
				break;
			default:
				break;
			}	
		}		
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.events.EventListener#handleEvent(org.w3c.dom.events.Event)
	 */
	@Override
	public void handleEvent(Event evt) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IRmlListener#getInherited()
	 */
	@Override
	public Boolean getInherited() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IRmlListener#OnControlWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IRmlListener#OnControlRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IRmlListener#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IRmlListener#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IRmlListener#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IRmlListener#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IWebOptionsListener#OnWebOptionsInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnWebOptionsInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IWebOptionsListener#OnWebOptionsRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnWebOptionsRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IWebOptionsListener#OnWebOptionsLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnWebOptionsLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			try
			{
				WebOptions webOptions = WebOptions.class.cast(e.getMessage());
				
				if(webOptions.getActiveXNativeEnabled() != null)
				{
					this.getOptions().setActiveXNative(webOptions.getActiveXNativeEnabled());
				}				
				if(webOptions.getAppletEnabled() != null)
				{
					this.getOptions().setAppletEnabled(webOptions.getAppletEnabled());
				}
				if(webOptions.getCssEnabled() != null)
				{
					this.getOptions().setCssEnabled(webOptions.getCssEnabled());
				}				
				if(webOptions.getGeoLocationEnabled() != null)
				{
					this.getOptions().setGeolocationEnabled(webOptions.getGeoLocationEnabled());
				}				
				if(webOptions.getJavaScriptEnabled() != null)
				{
					this.getOptions().setJavaScriptEnabled(webOptions.getJavaScriptEnabled());
				}
				if(webOptions.getPopupBlockerEnabled() != null)
				{
					this.getOptions().setPopupBlockerEnabled(webOptions.getPopupBlockerEnabled());
				}
				if(webOptions.getRedirectEnabled() != null)
				{
					this.getOptions().setRedirectEnabled(webOptions.getRedirectEnabled());
				}
				if(webOptions.getTimeout() != null)
				{
					this.getOptions().setTimeout(webOptions.getTimeout());
				}
				if(webOptions.getBrowserVersion() != null)
				{
					
				}
				
				if(webOptions.getPeriodTime() != null)
				{
					this.timePeriod = webOptions.getPeriodTime(); 
				}
				else
				{
					CommandLineInterface.ThrowInconsistentMessageException(this, "WebLoader.OnWebOptionsLoaded: WebOptions TimePeriod cannot be NULL");
				}				
				
				WebLoaderEvent(new WebLoaderEventArgs(webOptions, WebLoaderEventType.READY));
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "WebLoader.OnWebOptionsLoaded: WebOptions RML message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IWebOptionsListener#OnWebOptionsChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnWebOptionsChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IWebOptionsListener#OnWebOptionsRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnWebOptionsRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IWebOptionsListener#OnWebOptionsWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnWebOptionsWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IActionListener#OnActionInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnActionInit(Object sender, RmlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IActionListener#OnActionRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnActionRead(Object sender, RmlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IActionListener#OnActionLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnActionLoaded(Object sender, RmlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IActionListener#OnActionChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnActionChanged(Object sender, RmlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IActionListener#OnActionRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnActionRendered(Object sender, RmlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IActionListener#OnActionWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnActionWrite(Object sender, RmlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		// TODO Auto-generated method stub
		
	}
}