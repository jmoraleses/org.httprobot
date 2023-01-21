package org.httprobot.core.rml.controls.operators;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.datatypes.operators.GetField;
import org.httprobot.common.rml.datatypes.operators.Replace;
import org.httprobot.core.rml.controls.RmlOperatorControl;


/**
 * @author Joan
 * Replace Control Class. Inherits RmlStringOperatorControl. 
 */
@XmlRootElement
public class ReplaceControl extends RmlOperatorControl 
{
	/**
	 * -3219652987951425693L
	 */
	private static final long serialVersionUID = -3219652987951425693L;
	/**
	 * Variables
	 */
	private String new_string;
	private String old_string;
	private GetField get_field;
	private GetFieldControl get_field_control;
	
	/**
	 * @return new_string String the new_string
	 */
	public String getNew_string() {
		return new_string;
	}
	/**
	 * @return old_string String the old_string
	 */
	public String getOld_string() {
		return old_string;
	}
	/**
	 * @return get_field GetField the get_field
	 */
	public GetField getGet_field() {
		return get_field;
	}
	public ReplaceControl()
	{
		super();
	}
	/**
	 * ReplaceControl constructor.
	 * @param parent 
	 * @param replace {@link Replace} input value
	 */
	public ReplaceControl(IRmlListener parent, Replace replace)
	{
		super(parent, replace);
		this.new_string = replace.getNewString();
		this.old_string = replace.getOldString();
		GetField get_field = replace.getGetField();
		
		if(get_field != null)
		{
			this.get_field_control = new GetFieldControl(this, get_field);
			this.get_field_control.setMessage(get_field);	
		}
		else
		{
			this.setMessage(replace);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnGetFieldLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnGetFieldLoaded(Object sender, RmlEventArgs e)
	{
		this.get_field = new GetField();
		this.get_field.setFieldName(((GetField)e.getMessage()).getFieldName());
		this.get_field.setValue(((GetField)e.getMessage()).getValue());
		
		Replace replace = new Replace();
		replace.setGetField(this.get_field);
		replace.setNewString(this.new_string);
		replace.setOldString(this.old_string);
		
		this.setMessage(replace);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRendered(Object sender, RmlEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlInit(Object sender, RmlEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRead(Object sender, RmlEventArgs e)
			throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlWrite(Object sender, RmlEventArgs e)
			throws NotImplementedException, InconsistenMessageException 
	{
		
	}
}