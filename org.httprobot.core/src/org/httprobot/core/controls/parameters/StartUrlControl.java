/**
 * 
 */
package org.httprobot.core.controls.parameters;

import org.httprobot.common.RML;
import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.params.StartUrl;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.ParameterData;
import org.httprobot.core.controls.ParameterControl;
import org.httprobot.core.controls.parameters.interfaces.IControlStartUrlListener;

/**
 * {@link StartUrl} message control class. Inherits {@link ParameterControl}.
 * <br>
 * @author joan
 *
 */
public class StartUrlControl 
		extends ParameterControl<StartUrl, IControlStartUrlListener> {
	
	/**
	 * -7838498880326443333L
	 */
	private static final long serialVersionUID = -7838498880326443333L;
	
	/**
	 * {@link StartUrl} message control default class constructor.
	 */
	public StartUrlControl() 
	{
		super();
		
		//Initialize message.
		this.message = new StartUrl();
		
		//Associate message to control.
		this.addCommandOutputListener(this.message);
	}
	/**
	 * {@link StartUrl} message control class constructor.
	 * @param parent {@link IControlStartUrlListener} the parent
	 * @param message {@link RML} the message
	 */
	public StartUrlControl(IControlStartUrlListener parent, StartUrl message)
	{
		super(parent, message);
		
		//Initialize message.
		this.message = new StartUrl();
		
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
				StartUrl.class.cast(e.getMessage());
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this,
						"StartUrlControl.OnControl_Init: StartUrl message expected");
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
				StartUrl startUrl = StartUrl.class.cast(e.getMessage());
				this.put(ParameterData.PARAMETER_NAME, startUrl.getParamName());
				this.put(ParameterData.VALUE, startUrl.getValue());
				this.put(ParameterData.PARAMETER_TYPE, startUrl.getParamType());
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"ServerUrlControl.OnControl_Loaded: ServerUrl message edxpected");
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
	 * @see org.httprobot.core.controls.ParameterControl#OnControlStartUrl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlStartUrl_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControlStartUrl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlStartUrl_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControlStartUrl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlStartUrl_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this))
		{
			this.reset();
			
			//Fire input command event.
			CliCommandInputEvent(new CliEventArgs(this, Command.ADD_PARAMETER, e.getMessage()));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControlStartUrl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlStartUrl_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControlStartUrl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlStartUrl_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControlStartUrl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlStartUrl_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
}