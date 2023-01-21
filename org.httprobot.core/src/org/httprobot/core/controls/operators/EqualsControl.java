package org.httprobot.core.controls.operators;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.placeholders.operators.Equals;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.OperatorData;
import org.httprobot.core.controls.operators.interfaces.IControlEqualsListener;


/**
 * {@link Equals} message control class. Inherits {@link AbstractOperatorControl}.
 * <br>
 * @author Joan
 * 
 */
@XmlRootElement
public class EqualsControl 
		extends AbstractOperatorControl<Equals, IControlEqualsListener> {
	
	/**
	 * -7062776107775980742L
	 */
	private static final long serialVersionUID = -7062776107775980742L;

	/**
	 * {@link Equals} message control default class constructor.
	 */
	public EqualsControl()
	{
		super();
		
		//Initialize using data.
		this.message = new Equals();
		
		//Associate message with control.
		this.addCommandOutputListener(this.message);
	}
	/**
	 * {@link Equals} message control default class constructor.
	 * @param parent {@link IControlListener} the parent
	 * @param equals {@link Equals} the equals
	 */
	public EqualsControl(IControlEqualsListener parent, Equals equals) 
	{
		super(parent, equals);
		
		//Initialize using data.
		this.message = new Equals();
		
		//Associate message with control.
		this.addCommandOutputListener(this.message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Change(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		super.Change(sender, e);
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Initialize(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getMessage() != null)
		{
			try
			{
				Equals.class.cast(e.getMessage());

				//Call super method
				super.Initialize(sender, e);
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this,
						"EqualsControl.OnControl_Init: Equals message expected.");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Load(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getMessage() != null)
		{
			try
			{
				Equals equals = Equals.class.cast(e.getMessage());
				
				//Update control's data
				this.put(OperatorData.VALUE, equals.getValue());
				
				//Call super method
				super.Load(sender, e);
				
			}
			catch(ClassCastException ca)
			{
				CliTools.ThrowInconsistentMessageException(this,
						"EqualsControl.OnControl_Loaded: Equals message expected.");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		super.Read(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Render(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		super.Render(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		super.Write(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlEquals_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlEquals_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		super.OnControlEquals_Changed(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlEquals_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlEquals_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		super.OnControlEquals_Init(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlEquals_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlEquals_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		if(e.getSource().equals(this))
		{
			//Set control ready to be iterated again.
			this.reset();
			
			//Fire input command event.
			CliCommandInputEvent(new CliEventArgs(this, Command.CONTROL_EQUALS, e.getMessage()));
		}
		else
		{
			super.OnControlEquals_Loaded(sender, e);	
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlEquals_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlEquals_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		super.OnControlEquals_Read(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlEquals_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlEquals_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		super.OnControlEquals_Rendered(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlEquals_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlEquals_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		super.OnControlEquals_Write(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#put(org.httprobot.core.common.Enums.OperatorData, java.lang.Object)
	 */
	@Override
	public Object put(OperatorData key, Object value) 
	{
		switch (key) 
		{
			case VALUE:
				this.message.setValue(String.class.cast(value));
				break;
				
			default:
				break;
		}
		return super.put(key, value);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#remove(java.lang.Object)
	 */
	@Override
	public Object remove(Object key)
	{
		return super.remove(key);
	}
	
}