package org.httprobot.core.net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.EnumSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.RML;
import org.httprobot.common.definitions.Enums.CliNamespace;
import org.httprobot.common.definitions.Enums.HttpRequestType;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.definitions.Enums.WebLoaderEventType;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.exceptions.WebLoaderException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.tools.CliTools;
import org.httprobot.common.unit.Paginator;
import org.httprobot.common.unit.WebOptions;
import org.httprobot.core.controls.unit.WebOptionsControl;
import org.httprobot.core.controls.unit.interfaces.IControlWebOptionsListener;
import org.httprobot.core.events.WebLoaderEventArgs;
import org.httprobot.core.interfaces.IWebLoaderListener;
import org.w3c.dom.events.Event;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebWindowEvent;
import com.gargoylesoftware.htmlunit.WebWindowListener;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Web loader class. Inherits {@link WebClient}. 
 * <br>
 * It's {@link WebWindowListener} and {@link IControlWebOptionsListener}.
 * <br>
 * @author Joan
 *
 */
@XmlTransient
public class WebLoader extends WebClient 
	implements WebWindowListener, IControlWebOptionsListener
{
	//Command line members
	private static final CliNamespace cliNamespace = CliNamespace.INET;
	/**
	 * 1000671316568198372L
	 */
	private static final long serialVersionUID = 1000671316568198372L;

	//CommandLine members
	private String destinationPath;
	
	//WebLoaders members
	private Vector<IWebLoaderListener> webLoaderListeners;
	
	Map<String, WebRequest> addressList;
	/**
	 * Hash map of HtmlUnit request {@link WebRequest}
	 * with corresponding {@link HtmlPage} response.
	 * Done action requests.
	 */
	Map<WebRequest, HtmlPage> resultData;
	
	WebRequest currentWebRequest;
	HtmlPage currentWebResponse;
	HtmlAnchor nextPageAnchor;
	
	Integer retries;
	EnumSet<RuntimeOptions> runtimeOptions;
		
	Integer timePeriod;
	WebOptions webOptions;
	WebOptionsControl webOptionsControl;
	
	HttpRequestType requestType;
	Boolean paginatorEnabled;
	/**
	 * @param addressList the addressList to set
	 */
	public void setAddressList(Map<String, WebRequest> addressList)
	{
		this.addressList = addressList;
	}
	/**
	 * WebLoader default class constructor.
	 * @param parent {@link IWebLoaderListener} the parent
	 * @param webOptions {@link WebOptions} the options
	 */
	public WebLoader(IWebLoaderListener parent, WebOptions webOptions)
	{
		super(webOptions.getBrowserVersion());
		
		//Set inherited data
		this.webOptions = webOptions;
		this.setUuid(webOptions.getUuid());		
		this.setRuntimeOptions(parent.getRuntimeOptions());
		
		//Initialize using data
		this.timePeriod = webOptions.getPeriodTime();
		this.webLoaderListeners = new Vector<IWebLoaderListener>();
		this.webOptionsControl = new WebOptionsControl(this, webOptions);
		this.resultData = new Hashtable<WebRequest, HtmlPage>();
		
		//Add listeners
		this.webOptionsControl.addControlWebOptionsListener(this);
		this.addWebWindowListener(this);
		
		//Initialize retries
		this.retries = 0;
	}
	/**
	 * Adds {@link IControlListener} Command event listener.
	 * @param listener {@link IControlListener} the listener
	 */
	public final synchronized void addWebLoaderListener(IWebLoaderListener listener)
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
	 * @see org.httprobot.core.managers.interfaces.IActionManagerListener#getCurrentResponse()
	 */
	public HtmlPage getCurrentWebResponse() 
	{
		return this.currentWebResponse;
	}
	/**
	 * @return the currentWebRequest
	 */
	public WebRequest getCurrentWebRequest() 
	{
		return currentWebRequest;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getDestinationPath()
	 */
	@Override
	public String getDestinationPath()
	{
		return this.destinationPath;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IControlListener#getInherited()
	 */
	@Override
	public Boolean getInherited() 
	{
		return this.webOptions.getInherited();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getRuntimeOptions()
	 */
	@Override
	public EnumSet<RuntimeOptions> getRuntimeOptions() 
	{
		return this.runtimeOptions;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getUuid()
	 */
	@Override
	public UUID getUuid()
	{
		return this.webOptions.getUuid();
	}
	/* (non-Javadoc)
	 * @see org.w3c.dom.events.EventListener#handleEvent(org.w3c.dom.events.Event)
	 */
	@Override
	public void handleEvent(Event evt) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
		
		switch (e.getCommand()) 
		{
			case CONTROL_WEB_OPTIONS:
				
				if(e.getMessage() != null)
				{
					try
					{
						WebOptions webOptions = WebOptions.class.cast(e.getMessage());
						
						//WebClient options
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
						
						//WebLoader options. Required members.
						if(webOptions.getType() != null)
						{
							this.requestType = webOptions.getType(); 
						}
						else
						{
							CliTools.ThrowInconsistentMessageException(this, "WebLoader.OnWebOptionsLoaded: WebOptions RequestType cannot be NULL");
						}
						
						if(webOptions.getPaginatorEnabled() != null)
						{
							this.paginatorEnabled = webOptions.getPaginatorEnabled();
						}
						else
						{
							CliTools.ThrowInconsistentMessageException(this, "WebLoader.OnWebOptionsLoaded: WebOptions PaginatorEnabled cannot be NULL");
						}
						
						if(webOptions.getPeriodTime() != null)
						{
							this.timePeriod = webOptions.getPeriodTime(); 
						}
						else
						{
							CliTools.ThrowInconsistentMessageException(this, "WebLoader.OnWebOptionsLoaded: WebOptions TimePeriod cannot be NULL");
						}
					}
					catch(ClassCastException ex)
					{
						CliTools.ThrowInconsistentMessageException(this, "WebLoader.OnWebOptionsLoaded: WebOptions RML message expected");
					}
				}
				break;

			default:
				break;
		}
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e) {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.unit.interfaces.IControlWebOptionsListener#OnControlWebOptions_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlWebOptions_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.unit.interfaces.IControlWebOptionsListener#OnControlWebOptions_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlWebOptions_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.unit.interfaces.IControlWebOptionsListener#OnControlWebOptions_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlWebOptions_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getMessage() != null)
		{
			try
			{
				WebOptions.class.cast(e.getMessage());
				WebLoaderEvent(new WebLoaderEventArgs(this, WebLoaderEventType.READY));
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, "WebLoader.OnWebOptionsLoaded: WebOptions RML message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.unit.interfaces.IControlWebOptionsListener#OnControlWebOptions_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlWebOptions_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.unit.interfaces.IControlWebOptionsListener#OnControlWebOptions_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlWebOptions_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.unit.interfaces.IControlWebOptionsListener#OnControlWebOptions_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlWebOptions_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/**
	 * Removes {@link IControlListener} Command event listener.
	 * @param listener {@link IControlListener} the listener
	 */
	public final synchronized void removeWebLoaderListener(IWebLoaderListener listener)
	{
		webLoaderListeners.remove(listener);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#setDestinationPath(java.lang.String)
	 */
	@Override
	public void setDestinationPath(String destinationPath)
	{
		this.destinationPath = destinationPath;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#setCliOptions(java.util.EnumSet)
	 */
	@Override
	public void setRuntimeOptions(EnumSet<RuntimeOptions> options) 
	{
		this.runtimeOptions = options;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#setUuid(java.util.UUID)
	 */
	@Override
	public void setUuid(UUID uuid) 
	{
		this.webOptions.setUuid(uuid);
	}
	/**
	 * Starts process.
	 */
	public void start() 
	{
		//Set controlled data
		this.webOptionsControl.controlMessage(webOptions);
	}
	/**
	 * Sends a request.
	 * @param httpAddress
	 */
	public void StartRequests()
	{
		try
		{
			Iterator<String> httpAddressKeys = this.addressList.keySet().iterator();
			
			while(httpAddressKeys.hasNext())
			{
				String address = httpAddressKeys.next();
				
				System.out.println("\nConnecting to: " + address + "\n");
				this.currentWebResponse = null;

				performRequest(address);
			}
			WebLoaderEvent(new WebLoaderEventArgs(this, this.resultData));
		}
		catch (FailingHttpStatusCodeException ex1) 
		{
			ex1.printStackTrace();
		}
		catch (MalformedURLException ex2) 
		{
			ex2.printStackTrace();
			WebLoaderEvent(new WebLoaderEventArgs(this, WebLoaderEventType.URL_ERROR));
		}
		catch (IOException ex3) 
		{
			ex3.printStackTrace();
		}
		finally
		{
			this.retries++;
		}
	}
	/**
	 * Requests current HTTP address.
	 * @param address the address
	 * @throws IOException
	 */
	private void performRequest(String address)
			throws IOException 	{
		
		System.out.print("\n Performing:" + address);
		
		//Check if it's post back. Else it's the first web request.
		if(this.currentWebResponse != null)
		{
			//Store data
			this.resultData.put(this.currentWebRequest, this.currentWebResponse);
			
			//Recurrent calling from current response. Otherwise it's the last page.			
			if(this.nextPageAnchor != null && paginatorEnabled)
			{
				//Search next's page button. Click button.
				//get <a href="HREF_VALUE">VALUE</a>
				
				//Get information from HTML elements of current response.
				//Set current values.
				this.currentWebResponse = HtmlPage.class.cast(nextPageAnchor.click());
				this.currentWebRequest = new WebRequest(this.currentWebResponse.getUrl());
				
				//Look for next page anchor.
				lookForNextPageAnchor();
				
				WebLoaderEvent(new WebLoaderEventArgs(this, this.currentWebRequest, this.currentWebResponse));
				
				//Wait.
				waitPeriodTime();
				
				//Repeat process until next page anchor doesn't exists
				performRequest(address);
			}
		}
		else
		{
			//Set current values
			this.currentWebRequest = this.addressList.get(address);
			this.currentWebResponse = HtmlPage.class.cast(this.getPage(this.currentWebRequest));
			
			//Look for next page anchor.
			lookForNextPageAnchor();
			
			WebLoaderEvent(new WebLoaderEventArgs(this, this.currentWebRequest, this.currentWebResponse));
			
			//Wait
			waitPeriodTime();
			
			//Treat response
			performRequest(address);
		}
	}	
	/**
	 * Looks for next page {@link HtmlAnchor} object.
	 */
	private final void lookForNextPageAnchor()
	{
		this.nextPageAnchor = null;
		
		if(this.webOptions.getPaginatorEnabled())
		{
			Paginator paginator = this.webOptions.getPaginator();
			
			for(HtmlAnchor anchor : this.currentWebResponse.getAnchors())
			{	
				if(anchor.getFirstChild() != null)
				{
//					System.out.println("\nAnchor content:" + anchor.asText());
					
					if(anchor.getFirstChild().getTextContent().contains(paginator.getAnchorValue()))
					{
						this.nextPageAnchor = anchor;
						break;
					}
				}
			}
		}
	}
	/**
	 * {@link Thread} sleeps for timePeriod seconds.
	 */
	private void waitPeriodTime() 
	{
		try
		{
			Thread.sleep(this.timePeriod);
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
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
			try 
			{
				switch (e.getIet()) 
				{
					case READY:					
						listener.OnWebLoader_Ready(this, e);					
						break;
					case PAGE_LOADED:
						listener.OnWebLoader_PageLoaded(this, e);
						break;
					case RML_ERROR:
						listener.OnWebLoader_PageError(this, e);
						break;
					case URL_ERROR:
						listener.OnWebLoader_PageError(this, e);
						break;
					case ALL_PAGES_LOADED:
						listener.OnWebLoader_AllPagesLoaded(this, e);
					default:
						break;
				}
			} 
			catch (WebLoaderException e1) 
			{
				e1.printStackTrace();
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IControlListener#getMessage()
	 */
	@Override
	public RML getMessage() {
		// TODO Auto-generated method stub
		return null;
	}
}