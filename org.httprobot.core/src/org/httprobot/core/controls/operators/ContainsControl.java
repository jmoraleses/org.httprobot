package org.httprobot.core.controls.operators;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.placeholders.operators.Contains;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.OperatorData;
import org.httprobot.core.controls.operators.interfaces.IControlContainsListener;

/**
 * {@link Contains} message control class. Inherits {@link AbstractOperatorControl}.
 * <br>
 * @author Joan
 * 
 */
@XmlRootElement
public class ContainsControl 
		extends AbstractOperatorControl<Contains, IControlContainsListener> {
	
	/**
	 * -5408324213939309086L
	 */
	private static final long serialVersionUID = -5408324213939309086L;

	/**
	 * {@link Contains} message control default class constructor.
	 */
	public ContainsControl()
	{
		super();
		
		//Initialize message.
		this.message = new Contains();
		
		//Associate message to control.
		this.addCommandOutputListener(this.message);
	}
	/**
	 * {@link Contains} message control class constructor.
	 * @param parent {@link IControlListener}
	 * @param contains {@link Contains}
	 */
	public ContainsControl(IControlContainsListener parent, Contains contains)
	{
		super(parent, contains);

		//Initialize message.
		this.message = new Contains();
		
		//Associate message to control.
		this.addCommandOutputListener(this.message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Change(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Render(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
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
				Contains contains = Contains.class.cast(e.getMessage());
				
				//Update control's data.
				this.put(OperatorData.VALUE, contains.getValue());
				
				//Call super method.
				super.Load(sender, e);
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"ContainsControl.OnControlLoaded: Contains message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Initialize(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		
		if(e.getMessage() != null)
		{
			try
			{
				Contains.class.cast(e.getMessage());
				
				//Initialization goes on.
				super.Initialize(sender, e);
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"ContainsControl.OnControl_Init: Contains message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlContains_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContains_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		super.OnControlContains_Changed(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlContains_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContains_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		super.OnControlContains_Init(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlContains_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContains_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this))
		{
			this.reset();

			//Fire input command event.
			CliCommandInputEvent(new CliEventArgs(this, Command.CONTROL_CONTAINS, e.getMessage()));
		}
		else
		{
			super.OnControlContains_Loaded(sender, e);
		}	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlContains_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContains_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		super.OnControlContains_Read(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlContains_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContains_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		super.OnControlContains_Rendered(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlContains_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContains_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		super.OnControlContains_Write(sender, e);
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
		
		//Update operator data.
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