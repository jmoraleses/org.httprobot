/**
 * 
 */
package org.httprobot.core.controls.config;

import java.net.URL;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;

import org.httprobot.common.config.ServiceConnection;
import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.ConfigData;
import org.httprobot.core.controls.ConfigControl;
import org.httprobot.core.controls.config.interfaces.IControlServiceConnectionListener;
import org.httprobot.core.controls.interfaces.impl.IConfigControlImpl;

/**
 * {@link ServiceConnection} message control class. Inherits {@link ConfigControl}.
 * <br>
 * @author joan
 *
 */
@XmlRootElement
public class ServiceConnectionControl 
		extends ConfigControl<ServiceConnection, IControlServiceConnectionListener>
		implements IConfigControlImpl {
	
	/**
	 * -5327625512317353873L
	 */
	private static final long serialVersionUID = -5327625512317353873L;
	
	/**
	 * {@link ServiceConnection} message control default class constructor.
	 */
	public ServiceConnectionControl() 
	{
		super();
		
		//Initialize message
		this.message = new ServiceConnection();
		
		//Associate message to control
		this.addCommandOutputListener(this.message);
	}
	/**
	 * {@link ServiceConnection} message control class constructor.
	 * @param parent {@link IControlServiceConnectionListener} the parent
	 * @param message {@link ServiceConnection} the message
	 */
	public ServiceConnectionControl	(IControlServiceConnectionListener parent, ServiceConnection message) 
	{
		super(parent, message);
		
		//Initialize message
		this.message = new ServiceConnection();
		
		//Associate message to control
		this.addCommandOutputListener(this.message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Change(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#OnControl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Initialize(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getMessage() != null)
		{
			try
			{
				ServiceConnection serviceOptions = ServiceConnection.class.cast(e.getMessage());
				
				if(serviceOptions.getQName() == null || serviceOptions.getUrl() == null)
				{
					CliTools.ThrowInconsistentMessageException(this, 
							"ServiceOptionsControl.OnControl_Init: Malformed ServiceOptions message exception");
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this,
						"ServiceOptionsControl.OnControl_Init: ServiceOptions message exception");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#OnControl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Load(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getMessage() != null)
		{
			try
			{
				ServiceConnection serviceOptions = ServiceConnection.class.cast(e.getMessage());
				
				if(serviceOptions.getQName() != null && serviceOptions.getUrl() != null)
				{
					this.put(ConfigData.Q_NAME, serviceOptions.getQName());
					this.put(ConfigData.URL, serviceOptions.getUrl());
				}
				else
				{
					CliTools.ThrowInconsistentMessageException(this, 
							"ServiceConnectionControl.OnControl_Loaded: Malformed ServiceConnection message exception");
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"ServiceConnectionControl.OnControl_Loaded: ServiceConnection message exception");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#OnControl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#OnControl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Render(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		//Fire input command event.
		CliCommandInputEvent(new CliEventArgs(this, Command.CONTROL_SERVICE_CONNECTION));
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#OnControl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#OnControlServiceConnection_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServiceConnection_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#OnControlServiceConnection_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServiceConnection_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#OnControlServiceConnection_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServiceConnection_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		//Set control ready to be iterated again.
		if(e.getSource().equals(this))
		{
			this.reset();
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#OnControlServiceConnection_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServiceConnection_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#OnControlServiceConnection_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServiceConnection_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#OnControlServiceConnection_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServiceConnection_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#put(org.httprobot.core.common.Enums.ConfigData, java.lang.Object)
	 */
	@Override
	public Object put(ConfigData key, Object value) {
		switch (key) 
		{
		case Q_NAME:
			this.message.setQName(QName.class.cast(value));
			break;

		case URL:
			this.message.setUrl(URL.class.cast(value));
			break;

		default:
			break;
		}
		
		return super.put(key, value);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#remove(java.lang.Object)
	 */
	@Override
	public Object remove(Object key) {
		// TODO Auto-generated method stub
		return super.remove(key);
	}
}
