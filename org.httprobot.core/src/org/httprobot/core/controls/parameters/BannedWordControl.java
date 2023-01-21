/**
 * 
 */
package org.httprobot.core.controls.parameters;

import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.params.BannedWord;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.ParameterData;
import org.httprobot.core.controls.ParameterControl;
import org.httprobot.core.controls.parameters.interfaces.IControlBannedWordListener;

/**
 * {@link BannedWord} message control class. Inherits {@link ParameterControl}.
 * <br>
 * @author joan
 *
 */
public class BannedWordControl 
		extends ParameterControl<BannedWord, IControlBannedWordListener> {
	
	/**
	 * 8316265730782709553L
	 */
	private static final long serialVersionUID = 8316265730782709553L;
	
	/**
	 * {@link BannedWord} message control default class constructor.
	 */
	public BannedWordControl() 
	{
		super();
		
		//Initialize message
		this.message = new BannedWord();
		
		//Associate message to control
		this.addCommandOutputListener(this.message);
	}
	/**
	 * {@link BannedWord} message control class constructor.
	 * @param parent the parent
	 * @param message the message
	 */
	public BannedWordControl(IControlBannedWordListener parent, BannedWord message) 
	{
		super(parent, message);

		//Initialize message
		this.message = new BannedWord();
		
		//Associate message to control
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
				BannedWord.class.cast(e.getMessage());
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"BannedWordControl.OnControl_Init: BannedWord message expected");
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
				BannedWord bannedWord = BannedWord.class.cast(e.getMessage());
				
				this.put(ParameterData.VALUE, bannedWord.getValue());
				this.put(ParameterData.PARAMETER_NAME, bannedWord.getParamName());
				this.put(ParameterData.PARAMETER_TYPE, bannedWord.getParamType());
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"BannedWordControl.OnControl_Loaded: BannedWord message expected");
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
	 * @see org.httprobot.core.controls.ParameterControl#OnControlBannedWord_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlBannedWord_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControlBannedWord_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlBannedWord_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControlBannedWord_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlBannedWord_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this))
		{
			this.reset();

			//Command to manager
			CliCommandInputEvent(new CliEventArgs(this, Command.ADD_PARAMETER, e.getMessage()));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControlBannedWord_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlBannedWord_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControlBannedWord_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlBannedWord_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ParameterControl#OnControlBannedWord_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlBannedWord_Write(Object sender, ControlEventArgs e) 
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