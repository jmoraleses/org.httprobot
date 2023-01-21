/**
 * 
 */
package org.httprobot.core.controls.unit;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.RML;
import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.params.BannedWord;
import org.httprobot.common.params.Constant;
import org.httprobot.common.tools.CliTools;
import org.httprobot.common.unit.Action;
import org.httprobot.common.unit.WebOptions;
import org.httprobot.core.common.Enums.UnitData;
import org.httprobot.core.controls.UnitControl;
import org.httprobot.core.controls.interfaces.impl.IControlImpl;
import org.httprobot.core.controls.interfaces.impl.IUnitControlImpl;
import org.httprobot.core.controls.parameters.BannedWordControl;
import org.httprobot.core.controls.parameters.ConstantControl;
import org.httprobot.core.controls.parameters.interfaces.IControlBannedWordListener;
import org.httprobot.core.controls.parameters.interfaces.IControlConstantListener;
import org.httprobot.core.controls.unit.interfaces.IControlActionListener;

/**
 * {@link Action} message control class. Inherits {@link UnitControl}.
 * {@link IControlActionListener} instance will listen for it.
 * <br>
 * @author Joan
 *
 */
@XmlRootElement
public class ActionControl 
		extends UnitControl<Action, IControlActionListener>
		implements IControlConstantListener, IControlBannedWordListener, IUnitControlImpl {

	/**
	 * 1814184201004522272L
	 */
	private static final long serialVersionUID = 1814184201004522272L;
	/**
	 * The {@link Constant} message controls list.
	 */
	protected List<ConstantControl> constantControls;
	/**
	 * The {@link BannedWord} message control list.
	 */
	protected List<BannedWordControl> bannedWordControls;	
	/**
	 * The {@link WebOptions} message control.
	 */
	protected WebOptionsControl webOptionsControl;
	/**
	 * {@link Action} message control default class constructor,
	 */
	public ActionControl() 
	{
		super();
		
		//Initialize using data. 
		this.message = new Action();
		
		//Associate message to control.
		this.addCommandOutputListener(this.message);
	}
	/**
	 * Action control class constructor,
	 * @param parent {@link IControlListener} the listener
	 * @param action {@link Action} the action
	 */
	public ActionControl(IControlActionListener parent, Action action) 
	{
		super(parent, action);
		
		//Initialize using data. 
		this.message = new Action();
		
		//Associate message to control.
		this.addCommandOutputListener(this.message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Change(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		RML action = e.getMessage();
		//If received object is Action
		if(action instanceof Action)
		{
			this.message = Action.class.cast(action);
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
				Action action = Action.class.cast(e.getMessage());
				
				//Initialize control lists.
				this.bannedWordControls = new ArrayList<BannedWordControl>();
				this.constantControls = new ArrayList<ConstantControl>();
				
				if(action.getWebOptions() != null)
				{
					//Initialize child's controlled data
					this.webOptionsControl = new WebOptionsControl(this, action.getWebOptions());
					
					//Associate child control to parent
					this.addCommandOutputListener(webOptionsControl);
					
					//Store control
					this.childControls.add(this.webOptionsControl);
				}
				for(BannedWord bannedWord : action.getBwords())
				{
					//Instantiate a control
					BannedWordControl bannedWordControl = new BannedWordControl(this, bannedWord);
					
					//Associate child control to parent
					this.addCommandOutputListener(bannedWordControl);
					
					//Store control
					this.bannedWordControls.add(bannedWordControl);
					this.childControls.add(bannedWordControl);
				}
				for(Constant constant : action.getConstants())
				{
					//Instantiate a control
					ConstantControl constantControl = new ConstantControl(this, constant);
					
					//Associate child control to parent
					this.addCommandOutputListener(constantControl);
					
					//Store control
					this.constantControls.add(constantControl);
					this.childControls.add(constantControl);
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"ActionControl.OnControl_Init: Action message expected.");
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
				Action action = Action.class.cast(e.getMessage());
				
				//Put control's data.
				this.put(UnitData.HTTP_ADDRESS, action.getHttpAddress());
				this.put(UnitData.METHOD, action.getMethod());
				
				//Set child control's messages.
				if(this.hasChildControls())
				{
					while(this.hasNext())
					{
						IControlImpl control = this.next();
						
						//Match WebOptions message with control.
						if(control instanceof WebOptionsControl ? 
								action.getWebOptions() != null ? 
										control.getUuid().equals(action.getWebOptions().getUuid()) : false : false) {
							
							//Set the message.
							this.webOptionsControl.controlMessage(action.getWebOptions());		
						}
						//Look for current banned words.
						else if(this.bannedWordControls.contains(control) ? 
								!action.getBwords().isEmpty() : false) {
							
							BannedWordControl bannedWordControl = BannedWordControl.class.cast(control);
							
							for(BannedWord bannedWord : action.getBwords())
							{
								//Match by UUID.
								if(bannedWordControl.getUuid().equals(bannedWord.getUuid()))
								{
									//Set the message.
									bannedWordControl.controlMessage(bannedWord);
								}
							}
						}
						//Look for constants.
						else if(this.constantControls.contains(control) ?
								!action.getConstants().isEmpty() : false) {
							
							ConstantControl constantControl = ConstantControl.class.cast(control);
							
							for(Constant constant : action.getConstants())
							{
								//Match by UUID.
								if(constantControl.getUuid().equals(constant.getUuid()))
								{
									//Set the message.
									constantControl.controlMessage(constant);
									break;
								}
							}
						}
					}
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"ActionControl.OnControlLoaded: Action message expected.");
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
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlAction_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAction_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlAction_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAction_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlAction_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAction_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this))
		{
			//Set control ready to be iterated again.
			this.reset();

			//Fire input command event. 
			CliCommandInputEvent(new CliEventArgs(this, Command.CONTROL_ACTION, e.getMessage()));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlAction_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAction_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlAction_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAction_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlAction_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAction_Write(Object sender, ControlEventArgs e) 
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
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlWebOptions_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlWebOptions_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlWebOptions_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlWebOptions_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.webOptionsControl))
		{
			if(e.getMessage() instanceof WebOptions)
			{
				this.put(UnitData.WEB_OPTIONS, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"ActionControl.OnControlWebOptions_Loaded: WebOptions message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlWebOptions_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlWebOptions_Changed(Object sender, ControlEventArgs e) 
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
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlBannedWordListener#OnControlBannedWord_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlBannedWord_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlBannedWordListener#OnControlBannedWord_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlBannedWord_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlBannedWordListener#OnControlBannedWord_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlBannedWord_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(this.bannedWordControls.contains(e.getSource()))
		{
			if(e.getMessage() instanceof BannedWord)
			{
				this.put(UnitData.BANNED_WORD, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"ActionControl.OnControlBannedWord_Loaded: BannedWord parameter message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlBannedWordListener#OnControlBannedWord_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlBannedWord_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlBannedWordListener#OnControlBannedWord_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlBannedWord_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlBannedWordListener#OnControlBannedWord_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlBannedWord_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlConstantListener#OnControlConstant_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConstant_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlConstantListener#OnControlConstant_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConstant_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlConstantListener#OnControlConstant_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConstant_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(this.constantControls.contains(e.getSource()))
		{
			if(e.getMessage() instanceof Constant)
			{
				this.put(UnitData.CONSTANT, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this,
						"ActionControl.OnControlConstantLoaded: Constant parameter message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlConstantListener#OnControlConstant_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConstant_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlConstantListener#OnControlConstant_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConstant_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlConstantListener#OnControlConstant_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConstant_Write(Object sender, ControlEventArgs e) 
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
		case BANNED_WORD:
			this.message.getBwords().add(BannedWord.class.cast(value));
			break;
			
		case CONSTANT:
			this.message.getConstants().add(Constant.class.cast(value));
			break;
			
		case STRICT_MODE:
			this.message.setStrictMode(Boolean.class.cast(value));
			break;
			
		case HTTP_ADDRESS:
			this.message.setHttpAddress(String.class.cast(value));
			break;
			
		case WEB_OPTIONS:
			//Check input value is a WebOptions control.
			if(this.webOptionsControl.equals(value))
			{
				// Update current control's message.
				this.message.setWebOptions(this.webOptionsControl.getMessage());
				
				// Acknowledge child control's message has been settled. Render control.
				this.webOptionsControl.put(UnitData.WEB_OPTIONS, this.webOptionsControl.getMessage());
			}
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