package org.httprobot.core.rml.controls.operators;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.datatypes.operators.GetField;
import org.httprobot.common.rml.datatypes.operators.Remove;
import org.httprobot.core.rml.controls.RmlControl;
import org.httprobot.core.rml.controls.RmlOperatorControl;


/**
 * Remove control Class. Inherits {@link RmlOperatorControl}.
 * @author Joan
 */
@XmlRootElement
public class RemoveControl extends RmlOperatorControl
{
	/**
	 * -8402465678229728468L
	 */
	private static final long serialVersionUID = -8402465678229728468L;
	private StartIndexControl start_index_control;
	private EndIndexControl end_index_control;
	private GetField get_field;
	private GetFieldControl get_field_control;
	
	/**
	 * @return the start_index
	 */
	public StartIndexControl getStart_index() {
		return start_index_control;
	}
	/**
	 * @return the end_index
	 */
	public EndIndexControl getEnd_index() {
		return end_index_control;
	}	
	/**
	 * @return the get_field
	 */
	public GetField getGet_field() {
		return get_field;
	}
	/**
	 * Remove control default class constructor.
	 */
	public RemoveControl()
	{
		super();
	}
	/**
	 * Remove control class constructor.
	 * @param parent RmlControl the parent control 
	 * @param remove Remove the remove
	 */
	public RemoveControl(RmlControl parent, Remove remove)
	{
		super(parent, remove);
//		this.start_index_control = remove.getStartIndex();
//		this.end_index_control = remove.getEndIndex();
		this.get_field = remove.getGetField();
		
		if(this.get_field != null)
		{
			this.get_field_control = new GetFieldControl(this, get_field);
			this.get_field_control.setMessage(get_field);
		}
		else
		{
			this.setMessage(remove);
			this.setIsRendered(true);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnGetFieldLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public final void OnGetFieldLoaded(Object sender, RmlEventArgs e)
	{
		this.get_field = new GetField();
		this.get_field.setFieldName(((GetField)e.getMessage()).getFieldName());
		this.get_field.setValue(((GetField)e.getMessage()).getValue());
		
//		Remove remove = new Remove();
//		remove.setGetField(this.get_field);
//		remove.setStartIndex(this.start_index_control);
//		remove.setEndIndex(this.start_index_control);
		
//		this.setMessage(remove);
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e) 
	{
		this.setIsRendered(true);
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
