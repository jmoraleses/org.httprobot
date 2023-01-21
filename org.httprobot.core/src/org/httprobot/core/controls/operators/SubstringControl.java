package org.httprobot.core.controls.operators;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.operators.EndIndex;
import org.httprobot.common.placeholders.operators.StartIndex;
import org.httprobot.common.placeholders.operators.Substring;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.OperatorData;
import org.httprobot.core.controls.OperatorControl;
import org.httprobot.core.controls.interfaces.impl.IControlImpl;
import org.httprobot.core.controls.operators.interfaces.IControlSubstringListener;

/**
 * {@link Substring} message operator control class. Inherits {@link OperatorControl}.
 * <br>
 * @author Joan
 */
@XmlRootElement
public class SubstringControl 
		extends AbstractOperatorControl<Substring, IControlSubstringListener> {
	
	/**
	 * 2151542622321854945L
	 */
	private static final long serialVersionUID = 2151542622321854945L;
	
	/**
	 * The {@link StartIndex} operator message control.
	 */
	protected StartIndexControl startIndexControl = null;
	/**
	 * The {@link EndIndex} operator message control.
	 */
	protected EndIndexControl endIndexControl = null;
	/**
	 * {@link Substring} message control default class constructor.
	 */
	public SubstringControl()
	{
		super();
		
		//Initialize operator message control.
		this.message = new Substring();
		
		//Associate message to control.
		this.addCommandOutputListener(this.message);
	}
	/**
	 * {@link Substring} message control class constructor.
	 * @param parent {@link IControlSubstringListener} the parent control
	 * @param substring {@link Substring} the substring
	 */
	public SubstringControl(IControlSubstringListener parent, Substring substring) 
	{
		super(parent, substring);
		
		//Initialize operator message control.
		this.message = new Substring();
		
		//Associate message to control.
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
				Substring substring = Substring.class.cast(e.getMessage());
				
				if(this.hasChildControls())
				{
					while(this.hasNext())
					{
						IControlImpl control = this.next();
						
						if(control.equals(this.startIndexControl) ?
								substring.getStartIndex() != null ?
										control.getUuid().equals(this.startIndexControl.getUuid())
										: false : false) {
							
							//Set message to child control.
							this.startIndexControl.controlMessage(substring.getStartIndex());
						}
						if(control.equals(this.endIndexControl) ?
								substring.getEndIndex() != null ?
										control.getUuid().equals(this.endIndexControl.getUuid()) 
										: false : false) {
							
							//Set message to child control.
							this.endIndexControl.controlMessage(substring.getEndIndex());
						}
					}
				}
				
				//Set control ready to be iterated again.
				this.reset();
				
				//Call superclass method to set child control messages.
				super.Load(sender, e);
				
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"SubstringControl.OnControlLoaded: Substring message expected");
			}
		}
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
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Initialize(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getMessage() != null)
		{
			try
			{
				Substring substring = Substring.class.cast(e.getMessage());
				
				if(substring.getStartIndex() != null)
				{
					//Initialize StartIndex message control
					this.startIndexControl = new StartIndexControl(this, substring.getStartIndex());
					
					//Associate child control to parent.
					this.addCommandOutputListener(this.startIndexControl);
					
					//Store control.
					this.childControls.add(this.startIndexControl);
				}
				if(substring.getEndIndex() != null)
				{
					//Initialize StartIndex message control
					this.endIndexControl = new EndIndexControl(this, substring.getEndIndex());
					
					//Associate child control to parent.
					this.addCommandOutputListener(this.endIndexControl);
					
					//Store control.
					this.childControls.add(this.endIndexControl);
				}
				
				//Call super method.
				super.Initialize(sender, e);
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"SubstringControl.OnControlInit: Substring RML message expected");
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
	 * @see org.httprobot.core.controls.OperatorControl#OnControlEndIndex_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlEndIndex_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlEndIndex_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlEndIndex_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlEndIndex_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlEndIndex_Loaded(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		if(e.getSource().equals(this.endIndexControl))
		{
			if(e.getMessage() instanceof EndIndex)
			{
				this.put(OperatorData.END_INDEX, e.getMessage());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"SubstringControl.OnEndIndexLoaded: EndIndex message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlEndIndex_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlEndIndex_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlEndIndex_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlEndIndex_Rendered(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlEndIndex_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlEndIndex_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlStartIndex_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlStartIndex_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlStartIndex_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlStartIndex_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlStartIndex_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlStartIndex_Loaded(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		if(e.getSource().equals(this.startIndexControl))
		{
			if(e.getMessage() instanceof StartIndex)
			{
				this.put(OperatorData.START_INDEX, e.getMessage());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"SubstringControl.OnEndIndexLoaded: StartIndex message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlStartIndex_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlStartIndex_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlStartIndex_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlStartIndex_Rendered(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlStartIndex_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlStartIndex_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlSubstring_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSubstring_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		if(!e.getSource().equals(this))
		{
			super.OnControlSubstring_Changed(sender, e);	
		}		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlSubstring_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSubstring_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		if(!e.getSource().equals(this))
		{
			super.OnControlSubstring_Init(sender, e);	
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlSubstring_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSubstring_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		if(!e.getSource().equals(this))
		{
			//Call abstract method to control child messages.
			super.OnControlSubstring_Loaded(sender, e);	
		}
		else
		{
			this.reset();
			
			//Fire input command event.
			CliCommandInputEvent(new CliEventArgs(this, Command.CONTROL_SUBSTRING, e.getMessage()));
		}		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlSubstring_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSubstring_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		if(!e.getSource().equals(this))
		{
			super.OnControlSubstring_Read(sender, e);	
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlSubstring_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSubstring_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(!e.getSource().equals(this))
		{
			super.OnControlSubstring_Rendered(sender, e);	
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlSubstring_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSubstring_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		if(!e.getSource().equals(this))
		{
			super.OnControlSubstring_Write(sender, e);	
		}	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#put(org.httprobot.core.common.Enums.OperatorData, java.lang.Object)
	 */
	@Override
	public Object put(OperatorData key, Object value) 
	{
		switch (key) 
		{
		case START_INDEX:
			this.message.setStartIndex(StartIndex.class.cast(value));
			break;

		case END_INDEX:
			this.message.setEndIndex(EndIndex.class.cast(value));
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