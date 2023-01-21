/**
 * 
 */
package org.httprobot.core.controls.parameters;

import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.params.ServerUrl;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.ParameterData;
import org.httprobot.core.controls.ParameterControl;
import org.httprobot.core.controls.parameters.interfaces.IControlServerUrlListener;

/**
 * {@link ServerUrl} parameter message control class. Inherits {@link ParameterControl}.
 * <br>
 * @author joan
 *
 */
public class ServerUrlControl
		extends ParameterControl<ServerUrl, IControlServerUrlListener> {
	/**
	 * -2072864194893214680L
	 */
	private static final long serialVersionUID = -2072864194893214680L;
	/**
	 * {@link ServerUrl} message control default class constructor. 
	 */
	public ServerUrlControl() 
	{
		super();
		
		//Initialize message.
		this.message = new ServerUrl();

		//Associate message to control.
		this.addCommandOutputListener(this.message);
	}
	/**
	 * {@link ServerUrl} message control class constructor.
	 * @param parent {@link IControlServerUrlListener} the parent
	 * @param message {@link ServerUrl} the message
	 */
	public ServerUrlControl(IControlServerUrlListener parent, ServerUrl message) 
	{
		super(parent, message);
		
		//Initialize message.
		this.message = new ServerUrl();

		//Associate message to control.
		this.addCommandOutputListener(this.message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Change(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Initialize(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getMessage() != null)
		{			
			try
			{
				ServerUrl.class.cast(e.getMessage());
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"ServerUrlControl.OnControlInit: ServerUrl message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Load(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getMessage() != null)
		{
			try
			{
				//Set data non-controlled data
				ServerUrl serverUrl = ServerUrl.class.cast(e.getMessage());
				
				this.put(ParameterData.PARAMETER_TYPE, serverUrl.getParamType());
				this.put(ParameterData.PARAMETER_NAME, serverUrl.getParamName());
				this.put(ParameterData.VALUE, serverUrl.getValue());
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this,
						"ServerUrlControl.OnControlLoaded: ServerUrl message edxpected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Render(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControlServerUrl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServerUrl_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControlServerUrl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServerUrl_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControlServerUrl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServerUrl_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		//Set control ready to be iterated again.
		if(e.getSource().equals(this))
		{
			this.reset();
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControlServerUrl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServerUrl_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControlServerUrl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServerUrl_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this))
		{
			//Command to manager
			CliCommandInputEvent(new CliEventArgs(this, Command.ADD_PARAMETER, e.getMessage()));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControlServerUrl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServerUrl_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#put(org.httprobot.core.common.Enums.ParameterAttribute, java.lang.Object)
	 */
	@Override
	public Object put(ParameterData key, Object value) 
	{
		return super.put(key, value);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#remove(java.lang.Object)
	 */
	@Override
	public Object remove(Object key) {
	
		return super.remove(key);
	}
}