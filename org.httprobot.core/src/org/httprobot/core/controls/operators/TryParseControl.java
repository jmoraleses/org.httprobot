package org.httprobot.core.controls.operators;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.definitions.Enums.FieldType;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.placeholders.operators.TryParse;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.OperatorData;
import org.httprobot.core.controls.operators.interfaces.IControlTryParseListener;

/**
 * {@link TryParse} operator message control Class. Inherits {@link AbstractOperatorControl}..
 * <br>
 * @author Joan
 * 
 */
@XmlRootElement
public class TryParseControl 
		extends AbstractOperatorControl<TryParse, IControlTryParseListener> {
	
	/**
	 * -6553843004370009753L
	 */
	private static final long serialVersionUID = -6553843004370009753L;
	
	/**
	 * {@link TryParse} message control default class constructor.
	 */
	public TryParseControl()
	{
		super();
		
		//Initialize operator message
		this.message = new TryParse();
		
		//Associate message to control
		this.addCommandOutputListener(this.message);
	}
	/**
	 * {@link TryParse} message control class constructor.
	 * @param parent {@link IControlListener} the parent
	 * @param tryParse {@link TryParse} the message
	 */
	public TryParseControl(IControlTryParseListener parent, TryParse tryParse) 
	{
		super(parent, tryParse);
		
		//Initialize operator message
		this.message = new TryParse();
		
		//Associate message to control
		this.addCommandOutputListener(this.message);
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
				TryParse tryParse = TryParse.class.cast(e.getMessage());
				
				//Set non-controlled data.
				this.put(OperatorData.FIELD_TYPE, tryParse.getFieldType());
				
				//Call superclass method to load child controls.
				super.Load(sender, e);
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"TryParse.OnControl_Loaded: TryParse message expected");
			}
		}
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
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Render(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		super.Render(sender, e);
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
				TryParse tryParse = TryParse.class.cast(e.getMessage());
				
				if(tryParse.getFieldType() != null)
				{
					//Call superclass method to initialize abstract child controls.
					super.Initialize(sender, e);
				}
				else
				{
					CliTools.ThrowInconsistentMessageException(this, 
							"TryParse.OnControl_Init: TryParse message expected");
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"TryParse.OnControl_Init: TryParse message expected");
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
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		super.Write(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlTryParse_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		if(!e.getSource().equals(this))
		{
			super.OnControlTryParse_Changed(sender, e);	
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlTryParse_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		if(!e.getSource().equals(this))
		{
			super.OnControlTryParse_Init(sender, e);	
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlTryParse_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(!e.getSource().equals(this))
		{
			//Call abstract method to control child operator messages.
			super.OnControlTryParse_Loaded(sender, e);	
		}
		else
		{
			//Set current control ready to be iterated again.
			this.reset();

			//Fire input command event.
			CliCommandInputEvent(new CliEventArgs(this, Command.CONTROL_TRY_PARSE, e.getMessage()));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlTryParse_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(!e.getSource().equals(this))
		{
			super.OnControlTryParse_Read(sender, e);	
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlTryParse_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		if(!e.getSource().equals(this))
		{
			super.OnControlTryParse_Rendered(sender, e);	
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlTryParse_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		if(!e.getSource().equals(this))
		{
			super.OnControlTryParse_Write(sender, e);	
		}		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#put(org.httprobot.core.common.Enums.OperatorData, java.lang.Object)
	 */
	@Override
	public Object put(OperatorData key, Object value) {
		
		switch (key) 
		{
		case FIELD_TYPE:
			this.message.setFieldType(FieldType.class.cast(value));
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
