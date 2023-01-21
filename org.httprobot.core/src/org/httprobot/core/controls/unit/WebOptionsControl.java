/**
 * 
 */
package org.httprobot.core.controls.unit;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.RML;
import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.definitions.Enums.HttpRequestType;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.tools.CliTools;
import org.httprobot.common.unit.Paginator;
import org.httprobot.common.unit.WebOptions;
import org.httprobot.core.common.Enums.UnitData;
import org.httprobot.core.controls.UnitControl;
import org.httprobot.core.controls.interfaces.impl.IControlImpl;
import org.httprobot.core.controls.unit.interfaces.IControlPaginatorListener;
import org.httprobot.core.controls.unit.interfaces.IControlWebOptionsListener;

import com.gargoylesoftware.htmlunit.BrowserVersion;

/**
 * {@link WebOptions} message control class. Inherits {@link UnitControl}.
 * <br>
 * Controls {@link WebOptions} message and fire events for {@link IControlWebOptionsListener}.
 * <br>
 * @author joan
 *
 */
@XmlRootElement
public class WebOptionsControl 
		extends UnitControl<WebOptions, IControlWebOptionsListener>
		implements IControlPaginatorListener {
	
	/**
	 * 3164743281243118998L
	 */
	private static final long serialVersionUID = 3164743281243118998L;
	
	/**
	 * The {@link Paginator} message control.
	 */
	protected PaginatorControl paginatorControl;
	/**
	 * {@link WebOptions} message control default class constructor.
	 */
	public WebOptionsControl() 
	{
		super();

		//Initialize using data
		this.message = new WebOptions();
		
		//Associate message to control
		this.addCommandOutputListener(this.message);
	}
	/**
	 * {@link WebOptions} message control class constructor.
	 * @param parent {@link IControlWebOptionsListener} the parent
	 * @param webOptions {@link WebOptions} the message
	 */
	public WebOptionsControl(IControlWebOptionsListener parent, WebOptions webOptions) 
	{
		super(parent, webOptions);
		
		//Initialize using data
		this.message = new WebOptions();
		
		//Associate message to control
		this.addCommandOutputListener(this.message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Change(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		RML newMessage = e.getMessage();
		
		//If received object is WebOptions
		if(newMessage instanceof WebOptions)
		{
			this.message = WebOptions.class.cast(newMessage);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Initialize(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getMessage() != null)
		{
			try
			{
				//Cast input message
				WebOptions webOptions = WebOptions.class.cast(e.getMessage());
				
				if(webOptions.getPaginator() != null)
				{
					//Initialize child's controlled data
					this.paginatorControl = new PaginatorControl(this, webOptions.getPaginator());
				
					//Associate child control to parent
					this.addCommandOutputListener(this.paginatorControl);
					
					//Store control
					this.childControls.add(this.paginatorControl);
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"WebOptionsControl.OnControlInit: WebOptions message exception");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Load(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getMessage() != null)
		{
			try
			{
				WebOptions webOptions = WebOptions.class.cast(e.getMessage()); 
				
				//Store control's attributes.
				this.put(UnitData.ACTIVE_X_NATIVE_ENABLED, webOptions.getActiveXNativeEnabled());
				this.put(UnitData.APPLET_ENABLED, webOptions.getAppletEnabled());
				this.put(UnitData.BROWSER_VERSION, webOptions.getBrowserVersion());
				this.put(UnitData.CSS_ENABLED, webOptions.getCssEnabled());
				this.put(UnitData.GEO_LOCATION_ENABLED, webOptions.getGeoLocationEnabled());
				this.put(UnitData.JAVA_SCRIPT_ENABLED, webOptions.getJavaScriptEnabled());
				this.put(UnitData.PAGINATOR_ENABLED, webOptions.getPaginatorEnabled());
				this.put(UnitData.PERIOD_TIME, webOptions.getPeriodTime());
				this.put(UnitData.POPUP_BLOCKER_ENABLED, webOptions.getPopupBlockerEnabled());
				this.put(UnitData.REDIRECT_MODE, webOptions.getRedirectEnabled());
				this.put(UnitData.TIMEOUT, webOptions.getTimeout());
				this.put(UnitData.USE_PROXY_ENABLED, webOptions.getUseProxyEnabled());
				this.put(UnitData.TYPE, webOptions.getType());
				
				if(this.hasChildControls())
				{
					while(this.hasNext())
					{
						IControlImpl control = this.next();
						
						if(control.equals(this.paginatorControl) ?
								webOptions.getPaginator() != null ?
										control.getUuid().equals(webOptions.getPaginator().getUuid()) 
												: false : false ) {
							
							if(webOptions.getPaginatorEnabled())
							{
								//Control Paginator's message.
								this.paginatorControl.controlMessage(webOptions.getPaginator());	
							}
							else
							{
								CliTools.ThrowInconsistentMessageException(this, 
										"WebOptionsControl.OnControlLoaded: Paginator message expected");
							}							
						}
					}
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"WebOptionsControl.OnControlLoaded; WebOptions message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Render(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlPaginator_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPaginator_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlPaginator_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPaginator_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlPaginator_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPaginator_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.paginatorControl))
		{
			if(e.getMessage() instanceof Paginator)
			{
				this.put(UnitData.PAGINATOR, e.getMessage());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this,
						"WebOptionsControl.OnControlPaginator_Loaded: Paginator message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlPaginator_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPaginator_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlPaginator_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPaginator_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlPaginator_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPaginator_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlWebOptions_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlWebOptions_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlWebOptions_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlWebOptions_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlWebOptions_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlWebOptions_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this))
		{
			//Set control ready to be iterated again.
			this.reset();

			//Call input Command event.
			CliCommandInputEvent(new CliEventArgs(this, Command.CONTROL_WEB_OPTIONS, this.message));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlWebOptions_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlWebOptions_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlWebOptions_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlWebOptions_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlWebOptions_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlWebOptions_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.UnitControl#put(org.httprobot.core.common.Enums.UnitAttribute, java.lang.Object)
	 */
	@Override
	public Object put(UnitData key, Object value) 
	{
		switch (key) 
		{
		case PAGINATOR:
			// Check value is a child control.
			if(value.equals(this.paginatorControl))
			{
				// Update current control's message.
				this.message.setPaginator(this.paginatorControl.getMessage());
				
				// Acknowledge child control's message has been settled. Render control.
				this.paginatorControl.put(key, this.paginatorControl.getMessage());
			}
			break;

		case ACTIVE_X_NATIVE_ENABLED:
			this.message.setActiveXNativeEnabled(Boolean.class.cast(value));
			break;

		case APPLET_ENABLED:
			this.message.setAppletEnabled(Boolean.class.cast(value));
			break;

		case BROWSER_VERSION:
			this.message.setBrowserVersion(BrowserVersion.class.cast(value));
			break;

		case CSS_ENABLED:
			this.message.setCssEnabled(Boolean.class.cast(value));
			break;

		case GEO_LOCATION_ENABLED:
			this.message.setGeoLocationEnabled(Boolean.class.cast(value));
			break;

		case JAVA_SCRIPT_ENABLED:
			this.message.setJavaScriptEnabled(Boolean.class.cast(value));
			break;

		case PERIOD_TIME:
			this.message.setPeriodTime(Integer.class.cast(value));
			break;

		case POPUP_BLOCKER_ENABLED:
			this.message.setPopupBlockerEnabled(Boolean.class.cast(value));
			break;

		case REDIRECT_MODE:
			this.message.setRedirectEnabled(Boolean.class.cast(value));
			break;

		case TIMEOUT:
			this.message.setTimeout(Integer.class.cast(value));
			break;

		case TYPE:
			this.message.setType(HttpRequestType.class.cast(value));
			break;

		case USE_PROXY_ENABLED:
			this.message.setUseProxyEnabled(Boolean.class.cast(value));
			break;

		default:
			break;
		}
		return super.put(key, value);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.UnitControl#remove(java.lang.Object)
	 */
	@Override
	public Object remove(Object key) {
		// TODO Auto-generated method stub
		return super.remove(key);
	}
	
	
}