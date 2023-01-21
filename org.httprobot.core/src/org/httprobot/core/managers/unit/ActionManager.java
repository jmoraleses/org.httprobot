/**
 * 
 */
package org.httprobot.core.managers.unit;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;
import org.httprobot.common.Parameter;
import org.httprobot.common.definitions.Enums.ManagerErrorCode;
import org.httprobot.common.definitions.Enums.ManagerEventType;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.exceptions.WebLoaderException;
import org.httprobot.common.tools.CliTools;
import org.httprobot.common.unit.Action;
import org.httprobot.core.common.Enums.UnitData;
import org.httprobot.core.controls.unit.ActionControl;
import org.httprobot.core.controls.unit.interfaces.IControlActionListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.events.WebLoaderEventArgs;
import org.httprobot.core.interfaces.IDataMappingImpl;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.interfaces.IWebLoaderListener;
import org.httprobot.core.managers.UnitManager;
import org.httprobot.core.managers.datatypes.DataSourceManager;
import org.httprobot.core.managers.datatypes.interfaces.IManagerActionListener;
import org.httprobot.core.net.WebLoader;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * {@link Action} message manager class. Inherits {@link Manager}.
 * It's {@link IControlActionListener}, {@link IWebLoaderListener} and {@link IDataMappingImpl}.
 * <br>
 * <h1>Input data</h1> {@link HtmlPage} dictionary.
 * <br>
 * <h1>Output data</h1> 
 * @author joan
 *
 */
@XmlRootElement
public class ActionManager 
	extends UnitManager<Action, ActionControl, IManagerActionListener> 
	implements IControlActionListener, IWebLoaderListener, 
		IDataMappingImpl<HtmlPage, Set<HtmlPage>> {
	
	/**
	 * 2361189367613629952L
	 */ 
	private static final long serialVersionUID = 2361189367613629952L;
	/**
	 * The list of addresses calculated by patterns
	 */
	Map<String, WebRequest> addressList;
	/**
	 * The current input {@link HtmlPage} page.
	 */
	HtmlPage currentInput;
	/**
	 * The current output data being processed.
	 */
	Set<HtmlPage> currentOutput;	
	/**
	 * The current output {@link HtmlPage}.
	 */
	HtmlPage currentOutputPage;
	/**
	 * The input data.
	 */
	Set<HtmlPage> inputData;
	/**
	 * The output data.
	 */
	Map<HtmlPage, Set<HtmlPage>> outputData;
	/**
	 * The {@link WebLoader}.
	 */
	protected WebLoader webLoader;
	
	/**
	 * {@link Action} message manager default class constructor.
	 */
	public ActionManager()
	{
		super();
	}
	/**
	 * Action manager class constructor.
	 * @param parent {@link IManagerActionListener} the parent
	 * @param action {@link Action} the message to manage
	 */
	public ActionManager(IManagerActionListener parent, Action action) 
	{
		super(parent, action);
		
		//Initialize using data
		this.control = new ActionControl(this, action);
		this.webLoader = new WebLoader(this, action.getWebOptions());
		
		this.currentInput = null;
		
		//Mapping members
		this.inputData = new HashSet<HtmlPage>();
		this.outputData = new LinkedHashMap<HtmlPage, Set<HtmlPage>>();

		//Add listeners
		this.webLoader.addWebLoaderListener(this);
		addCommandOutputListener(this.control);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#clear()
	 */
	@Override
	public void clear() 
	{
		this.inputData.clear();
		this.outputData.clear();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey(Object key) 
	{
		return this.inputData.contains(key);
	}	
	/* (non-Javadoc)
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object value) 
	{
		return this.outputData.containsValue(value);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<java.util.Map.Entry<HtmlPage, Set<HtmlPage>>> entrySet() 
	{
		return this.outputData.entrySet();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public Set<HtmlPage> get(Object key) 
	{
		return this.outputData.get(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() 
	{
		return this.outputData.isEmpty();
	}
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<HtmlPage> iterator() {
		return this.outputData.keySet().iterator();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<HtmlPage> keySet() {
		return this.inputData;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e) 
			throws InconsistenMessageException {
		
		switch (e.getCommand()) 
		{
			case ADD_PARAMETER:
				//Parameter message received
				try
				{
					Parameter param = Parameter.class.cast(e.getMessage());
					
					switch (param.getParamType())
					{
						case CONSTANT:							
							ManagerEvent(new ManagerEventArgs(param, ManagerEventType.PARAM_ADDED));								
							break;
							
						case BANNED_WORD:							
							ManagerEvent(new ManagerEventArgs(param, ManagerEventType.BANNED_ADDED));								
							break;

						default:
							break;
					}
				}
				catch(ClassCastException ex)
				{
					ManagerActionEvent(new ManagerEventArgs(this, ManagerErrorCode.UNABLE_TO_CAST));
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
	public void OnCommandOutput(Object sender, CliEventArgs e) 
			throws InconsistenMessageException  {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.unit.interfaces.IControlActionListener#OnControlAction_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAction_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.unit.interfaces.IControlActionListener#OnControlAction_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAction_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.unit.interfaces.IControlActionListener#OnControlAction_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAction_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(this.hasChildManagers())
		{
			//Start each child manager
			while(this.hasNext())
			{
				IManagerImpl manager = this.next();
				manager.start();
			}
			
			ManagerActionEvent(new ManagerEventArgs(this, ManagerEventType.STARTED));
		}
		else
		{
			ManagerActionEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.unit.interfaces.IControlActionListener#OnControlAction_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAction_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.unit.interfaces.IControlActionListener#OnControlAction_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAction_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.unit.interfaces.IControlActionListener#OnControlAction_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAction_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerActionListener#OnManagerAction_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAction_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		//Nothing
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerActionListener#OnManagerAction_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAction_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		//Nothing
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerActionListener#OnManagerAction_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAction_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
	
		if(e.getSource().equals(this))
		{
			//When all managers has been started. 
			//Set iterator ready to iterate again.
			this.reset();
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.datatypes.interfaces.IManagerActionListener#OnManagerAction_WebLoaded(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerAction_WebLoaded(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		//Nothing
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IWebLoaderListener#OnWebLoader_AllPagesLoaded(java.lang.Object, org.httprobot.core.events.WebLoaderEventArgs)
	 */
	@Override
	public void OnWebLoader_AllPagesLoaded(Object sender, WebLoaderEventArgs e) 
	{
		ManagerActionEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IWebLoaderListener#OnWebLoader_PageError(java.lang.Object, org.httprobot.core.events.WebLoaderEventArgs)
	 */
	@Override
	public void OnWebLoader_PageError(Object sender, WebLoaderEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IWebLoaderListener#OnWebLoader_PageLoaded(java.lang.Object, org.httprobot.core.events.WebLoaderEventArgs)
	 */
	@Override
	public void OnWebLoader_PageLoaded(Object sender, WebLoaderEventArgs e) 
	{
		if(e.getReceivedPage() != null && e.getRequestedPage() != null)
		{
			WebRequest webRequest = e.getRequestedPage();
			
			//Update pointers
			this.currentOutputPage = e.getReceivedPage();			
			this.currentOutput.add(e.getReceivedPage());			
			
			//Update output data for each web response key
			this.outputData.get(currentInput).add(this.currentOutputPage);	
			
			//Send PAGE_LOADED event to parent
			ManagerActionEvent(new ManagerEventArgs(this, webRequest, this.currentOutputPage));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IWebLoaderListener#OnWebLoader_Ready(java.lang.Object, org.httprobot.core.events.WebLoaderEventArgs)
	 */
	@Override
	public void OnWebLoader_Ready(Object sender, WebLoaderEventArgs e) 
			throws WebLoaderException {
		
		//Start requesting
		this.webLoader.StartRequests();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Set<HtmlPage> put(HtmlPage key, Set<HtmlPage> value)
	{
		//Add to input data. Key can be null so it will be assigned on DataSource's action.
		this.inputData.add(key);
		
		//Update pointers
		this.currentInput = key;
		this.currentOutput = value;
		
		//Start requesting,
		try
		{
			//Initialize using data
			this.addressList = new Hashtable<String, WebRequest>();
			
			//Get Action Address
			String absoluteHttpAddress = String.class.cast(this.control.get(UnitData.HTTP_ADDRESS));
			String relativeHttpAddress = String.class.cast(this.control.get(UnitData.HTTP_ADDRESS)).replace("[@server_url]", "").trim();
			
			//De-parameterize URL's
			absoluteHttpAddress = deParameterizeURL(absoluteHttpAddress);
			relativeHttpAddress = deParameterizeURL(relativeHttpAddress);
			
			//Get the server URL
			String serverUrl = this.getParameterConstants().get("[@server_url]");
			
			if(this.getParent() instanceof DataSourceManager)
			{
				//Initialize URL
				URL url = new URL(absoluteHttpAddress);
				
				//Initialize web request.
				WebRequest webRequest = new WebRequest(url);
				
				//Add link to address list
				this.addressList.put(url.toString(), webRequest);
			}
			
			//Calculate addresses
			calculateAddresses(serverUrl, absoluteHttpAddress, relativeHttpAddress);
			
			this.webLoader.setAddressList(this.addressList);
			this.webLoader.start();
		}
		catch (MalformedURLException e1) 
		{
			ManagerActionEvent(new ManagerEventArgs(this, ManagerErrorCode.BAD_MESSAGE));
		}
		catch (ManagerException e2) 
		{
			ManagerActionEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		} 
		catch (NotImplementedException e3)
		{
			ManagerActionEvent(new ManagerEventArgs(this, ManagerErrorCode.NOT_IMPLEMENTED));
		}
		catch(NullPointerException e4)
		{
			ManagerActionEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_POINTER));
		}
				
		return this.outputData.put(key, value);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends HtmlPage, ? extends Set<HtmlPage>> m) 
	{
		for(HtmlPage inputPage : m.keySet())
		{
			Set<HtmlPage> outputPages = m.get(inputPage);
			
			if(this.put(inputPage, outputPages) == null)
			{
				ManagerActionEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
			}
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public Set<HtmlPage> remove(Object key) 
	{
		return null;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() 
	{
		return this.outputData.size();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.Manager#start()
	 */
	@Override
	public void start()
	{
		//Set controlled data
		this.control.controlMessage(this.message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.Manager#stop()
	 */
	@Override
	public void stop() 
	{
		
	}
	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<Set<HtmlPage>> values() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @throws WebLoaderException 
	 * @throws MalformedURLException 
	 * @throws NotImplementedException 
	 * 
	 */
	private final void calculateAddresses(String serverUrl, String absoluteHttpAddress, String relativeHttpAddress) 
			throws MalformedURLException, ManagerException, NotImplementedException {
		
		//If address after de-parameterization is still parameterized with [@action] parameter. 
		//Seek on current HtmlPage links containing current mainHttpAddress
		//If else it's the first action on ServerInfo message. 
		if(absoluteHttpAddress.contains("[@action]") && relativeHttpAddress.contains("[@action]"))
		{
			absoluteHttpAddress = absoluteHttpAddress.replace("[@action]", "");
			relativeHttpAddress = relativeHttpAddress.replace("[@action]", "");
			
			//Check if input HtmlPage has been settled.
			if(this.currentInput != null)
			{
				Iterable<HtmlAnchor> htmlAnchorCollection = this.currentInput.getAnchors();
				Iterator<HtmlAnchor> htmlAnchorIterator = htmlAnchorCollection.iterator();
				
				//Iterate through HTML anchor elements </a>
				while(htmlAnchorIterator.hasNext())
				{
					HtmlAnchor anchor = htmlAnchorIterator.next();
					
					//Get href value
					String hrefValue = anchor.getHrefAttribute();
					
					//Check if href link is absolute or relative
					if(!hrefValue.contains("http://"))
					{
						//RELATIVE
						if(hrefValue.startsWith("/"))
						{
							hrefValue = hrefValue.substring(1);
						}
						
						if(!hrefValue.isEmpty())
						{
							if(!checkBannedAddresses(hrefValue))
							{
								String finalUrl = serverUrl + hrefValue;
								
								//When strictMode=true only get links that has no sub-levels: "/[@action]/"
								if(Boolean.class.cast(this.control.get(UnitData.STRICT_MODE)))
								{									
									String relUrl = finalUrl.replace(absoluteHttpAddress, "");
									
									if(relUrl.endsWith("/"))
									{
										relUrl = relUrl.substring(0, relUrl.length() - 1);
									}
									
									if(!relUrl.contains("/"))
									{
										if(hrefValue.contains(relativeHttpAddress) &&
												!relativeHttpAddress.equals(hrefValue))
										{
											URL url = new URL(finalUrl);
											//Add link to address list
											this.addressList.put(url.toString(), new WebRequest(url));
										}	
									}
								}
								//When strictMode=false get all links with sub-levels: "/[@action]/more/more/..."
								else
								{
									if(hrefValue.contains(relativeHttpAddress) &&
											!relativeHttpAddress.equals(hrefValue))
									{
										URL url = new URL(finalUrl);
										//Add link to address list
										this.addressList.put(url.toString(), new WebRequest(url));
									}
								}
							}
						}
					}
					else if(hrefValue.contains(relativeHttpAddress))
					{
						//ABSOLUTE
						//Add link to address list
						URL url = new URL(hrefValue);
						//Add link to address list
						this.addressList.put(url.toString(), new WebRequest(url));
					}		
				}
			}
			else
			{
				CliTools.ThrowManagerException(this, 
						"ActionManager.CalculateAddresses: Impossible to work");
			}
		}
		//DataSource's Action
		else 
		{
//			//Add link to address list
//			URL url = new URL(absoluteHttpAddress);
//			this.currentWebRequest = new WebRequest(url);
//			//Add link to address list
//			this.addressList.put(url.toString(), this.currentWebRequest);
		}
	}
	/**
	 * Checks if link string value is denied.
	 * @param hrefValue
	 * @return <code>true</code> Current HrefValue appears in Banned list addresses
	 */
	private boolean checkBannedAddresses(String hrefValue)
	{
		try 
		{
			for(String key : this.getParameterBannedWords().keySet())
			{
				String value = getParameterBannedWords().get(key);
				
				if(hrefValue.contains(value))
				{
					return true;
				}
			}
		}
		catch (NotImplementedException e)
		{
			ManagerActionEvent(new ManagerEventArgs(this, ManagerErrorCode.NOT_IMPLEMENTED));
		} 
		catch (ManagerException e)
		{
			ManagerActionEvent(new ManagerEventArgs(this, ManagerErrorCode.NULL_DATA));
		}
		
		return false;
	}
	/**
	 * Substitutes [@parameters] to corresponding values.
	 * @param url the string URL to de-parameterize.
	 * @return the de-parameterized URL.
	 * @throws NotImplementedException
	 * @throws ManagerException
	 */
	private String deParameterizeURL(String url) 
			throws NotImplementedException, ManagerException {
		
		//De-parameterization of current address
		for(String paramName : getParameterConstants().keySet())
		{	
			//Replace all instances of 
			if(url.contains(paramName))
			{
				String paramValue = getParameterConstants().get(paramName);
				System.out.println("\n ParamName: " + paramName + ", ParamValue: " + paramValue);
				
				url = url.replace(paramName, paramValue);
			}
		}
		
		return url;
	}
}