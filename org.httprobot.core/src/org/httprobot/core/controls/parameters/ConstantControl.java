/**
 * 
 */
package org.httprobot.core.controls.parameters;

import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.params.Constant;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.ParameterData;
import org.httprobot.core.controls.ParameterControl;
import org.httprobot.core.controls.parameters.interfaces.IControlConstantListener;

/**
 * {@link Constant} parameter message control class. Inherits {@link ParameterControl}.
 * <br>
 * @author joan
 *
 */
public class ConstantControl
		extends ParameterControl<Constant, IControlConstantListener> {
	
	/**
	 * 2484367278780857241L
	 */
	private static final long serialVersionUID = 2484367278780857241L;
	String key;
	
	/**
	 * {@link Constant} parameter message control default class constructor.
	 */
	public ConstantControl() 
	{
		super();
		
		//Initialize message.
		this.message = new Constant();

		//Associate message to control.
		this.addCommandOutputListener(this.message);
	}
	/**
	 * {@link Constant} parameter message control class constructor.
	 * @param parent {@link IControlConstantListener} the parent
	 * @param message {@link Constant} the message
	 */
	public ConstantControl(IControlConstantListener parent, Constant message) 
	{
		super(parent, message);
		
		//Initialize message.
		this.message = new Constant();

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
				Constant.class.cast(e.getMessage());
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"ConstantControl.OnControlInit: Constant parameter message expected");
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
				Constant constant = Constant.class.cast(e.getMessage());

				this.put(ParameterData.PARAMETER_NAME, constant.getParamName());
				this.put(ParameterData.VALUE, constant.getValue());
				this.put(ParameterData.PARAMETER_TYPE, constant.getParamType());
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"ConstantControl.OnControl_Loaded: Constant parameter message expected");
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
	 * @see org.httprobot.core.controls.ParameterControl#OnControlConstant_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConstant_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControlConstant_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConstant_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControlConstant_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConstant_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this))
		{
			//Set current control ready to be iterated.
			this.reset();

			//Command to manager
			CliCommandInputEvent(new CliEventArgs(this, Command.ADD_PARAMETER, e.getMessage()));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControlConstant_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConstant_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControlConstant_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConstant_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControlConstant_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConstant_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#put(org.httprobot.core.common.Enums.ParameterData, java.lang.Object)
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
	public Object remove(Object key) 
	{
		return super.remove(key);
	}
	
}