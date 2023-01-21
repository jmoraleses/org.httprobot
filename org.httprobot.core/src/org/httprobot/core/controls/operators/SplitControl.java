package org.httprobot.core.controls.operators;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.operators.Delimiters;
import org.httprobot.common.placeholders.operators.Split;
import org.httprobot.core.common.Enums.OperatorData;
import org.httprobot.core.controls.operators.interfaces.IControlSplitListener;

/**
 * {@link Split} message operator control class. Inherits {@link AbstractOperatorControl}.
 * <br>
 * @author Joan 
 */
@XmlRootElement
public class SplitControl 
		extends AbstractOperatorControl<Split, IControlSplitListener> {
	
	/**
	 * -6688359449287305844L
	 */
	private static final long serialVersionUID = -6688359449287305844L;
	private ArrayList<String> delimiters;
	private ArrayList<Object> for_each;
	
	private Integer delimiters_count;
	private Integer for_each_count;
	
	DelimitersControl delimiters_control = null;
	
	/**
	 * @return the delimiters
	 */
	public ArrayList<String> getDelimiters() {
		return delimiters;
	}
	/**
	 * @return the for_each
	 */
	public ArrayList<Object> getFor_each() {
		return for_each;
	}
	/**
	 * @return the delimiters_count
	 */
	public Integer getDelimiters_count() {
		return delimiters_count;
	}
	/**
	 * @return the for_each_count
	 */
	public Integer getFor_each_count() {
		return for_each_count;
	}
	public SplitControl()
	{
		super();
	}
	/**
	 * SplitControl constructor.
	 * @param parent {@link IControlSplitListener} the parent control
	 * @param split {@link Split} the split
	 */
	public SplitControl(IControlSplitListener parent, Split split)
	{
		super(parent, split);
		
		this.delimiters = split.getDelimiters().getDelimiter();
		this.delimiters_count = split.getDelimiters().getDelimiter().size();
		
		if((this.for_each == null) && (this.delimiters == null))
		{
			//this.LoadControl();
			this.controlMessage(split);
		}
		else
		{			
			Delimiters delimiters = new Delimiters();
			delimiters.setDelimiters(this.delimiters);
			
			this.delimiters_control = new DelimitersControl(this, delimiters);			
			this.delimiters_control.controlMessage(delimiters);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlDelimiters_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	public void OnControlDelimiters_Loaded(Object sender, ControlEventArgs e)
	{		
		this.delimiters = Delimiters.class.cast(e.getMessage()).getDelimiter();
		
		if(this.for_each_count == 0)
		{
			Split split = new Split();
			Delimiters delimiters = new Delimiters();
			delimiters.setDelimiters(this.delimiters);
			split.setDelimiters(delimiters);
			
			this.controlMessage(split);
		}
		else
		{
			
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Load(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this))
		{
			this.render(true);	
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
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlSplit_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSplit_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlSplit_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSplit_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlSplit_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSplit_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this))
		{
			//Set the control ready to be iterated again.
			this.reset();

			//Fire input command event.
			CliCommandInputEvent(new CliEventArgs(this, Command.CONTROL_REPLACE, e.getMessage()));
		}
		else
		{
			//Call abstract method to control child messages.
			super.OnControlSplit_Loaded(sender, e);	
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlSplit_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSplit_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlSplit_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSplit_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#OnControlSplit_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSplit_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#put(org.httprobot.core.common.Enums.OperatorData, java.lang.Object)
	 */
	@Override
	public Object put(OperatorData key, Object value) {
		// TODO Auto-generated method stub
		return super.put(key, value);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.AbstractOperatorControl#remove(java.lang.Object)
	 */
	@Override
	public Object remove(Object key) {
		// TODO Auto-generated method stub
		return super.remove(key);
	}
	
}