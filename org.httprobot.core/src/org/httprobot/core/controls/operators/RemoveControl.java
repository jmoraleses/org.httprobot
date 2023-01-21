package org.httprobot.core.controls.operators;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.operators.EndIndex;
import org.httprobot.common.placeholders.operators.Remove;
import org.httprobot.common.placeholders.operators.StartIndex;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.OperatorData;
import org.httprobot.core.controls.OperatorControl;
import org.httprobot.core.controls.interfaces.impl.IControlImpl;
import org.httprobot.core.controls.operators.interfaces.IControlRemoveListener;


/**
 * {@link Remove} message operator control Class. Inherits {@link OperatorControl}.
 * <br>
 * @author Joan
 */
@XmlRootElement
public class RemoveControl 
		extends AbstractOperatorControl<Remove, IControlRemoveListener> {
	
	/**
	 * -8402465678229728468L
	 */
	private static final long serialVersionUID = -8402465678229728468L;
	
	protected StartIndexControl startIndexControl;
	protected EndIndexControl endIndexControl;
	
	/**
	 * Remove control default class constructor.
	 */
	public RemoveControl()
	{
		super();
		
		//Initialize operator message
		this.message = new Remove();
		
		//Associate message to control.
		this.addCommandOutputListener(this.message);

	}
	/**
	 * Remove control class constructor.
	 * @param parent RmlControl the parent control 
	 * @param remove Remove the remove
	 */
	public RemoveControl(IControlRemoveListener parent, Remove remove)
	{
		super(parent, remove);
		
		//Initialize operator message
		this.message = new Remove();
		
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
				Remove remove = Remove.class.cast(e.getMessage());
				
				//Iterate through child controls.
				if(this.hasChildControls())
				{
					while(this.hasNext())
					{
						IControlImpl control = this.next();
						
						//Check StartIndex message.
						if(control.equals(this.startIndexControl) ?
								remove.getStartIndex() != null ?
										control.getUuid().equals(remove.getStartIndex().getUuid()) 
										: false : false) {
							
							//Set message to control.
							this.startIndexControl.controlMessage(remove.getStartIndex());
						}
						
						//Check EndIndex message.
						if(control.equals(this.endIndexControl) ?
								remove.getEndIndex() != null ?
										control.getUuid().equals(remove.getEndIndex().getUuid()) 
										: false : false) {
							
							//Set message to control.
							this.endIndexControl.controlMessage(remove.getEndIndex());
						}
					}
				}
				
				//Set control ready to be iterated again.
				this.reset();
				
				//Call super method to set child control messages. It will be iterated again.
				super.Load(sender, e);

			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"RemoveControl.OnControlLoaded: Remove message expected");
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
				Remove remove = Remove.class.cast(e.getMessage());
				
				if(remove.getStartIndex() != null)
				{
					//Initialize StartIndex message control
					this.startIndexControl = new StartIndexControl(this, remove.getStartIndex());
					
					//Associate child control to parent.
					this.addCommandOutputListener(this.startIndexControl);
					
					//Store control.
					this.childControls.add(this.startIndexControl);
				}
				if(remove.getEndIndex() != null)
				{
					//Initialize StartIndex message control
					this.endIndexControl = new EndIndexControl(this, remove.getEndIndex());
					
					//Associate child control to parent.
					this.addCommandOutputListener(this.endIndexControl);
					
					//Store control.
					this.childControls.add(this.endIndexControl);
				}
				
				//Call super method to initialize abstract child controls.
				super.Initialize(sender, e);
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"RemoveControl.OnControlInit: Remove message expected");
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
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlRemove_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlRemove_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	
		//Child message controls are managed on its superclass.
		if(!e.getSource().equals(this))
		{ 
			super.OnControlRemove_Changed(sender, e);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlRemove_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlRemove_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		//Child message controls are managed on its superclass.
		if(!e.getSource().equals(this))
		{ 
			super.OnControlRemove_Init(sender, e);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlRemove_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlRemove_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		//Child message controls are managed on its superclass.
		if(!e.getSource().equals(this))
		{			
			super.OnControlRemove_Loaded(sender, e);
		}
		else
		{
			//Set control ready to be iterated again.
			this.reset();
			
			//Fire input command event.
			CliCommandInputEvent(new CliEventArgs(this, Command.CONTROL_REMOVE, e.getMessage()));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlRemove_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlRemove_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		//Child message controls are managed on its superclass.
		if(!e.getSource().equals(this))
		{ 
			super.OnControlRemove_Read(sender, e);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlRemove_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlRemove_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		//Child message controls are managed on its superclass.
		if(!e.getSource().equals(this))
		{ 
			super.OnControlRemove_Rendered(sender, e);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlRemove_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlRemove_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
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