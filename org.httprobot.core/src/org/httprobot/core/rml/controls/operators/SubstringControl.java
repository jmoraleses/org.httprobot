package org.httprobot.core.rml.controls.operators;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.datatypes.operators.EndIndex;
import org.httprobot.common.rml.datatypes.operators.GetField;
import org.httprobot.common.rml.datatypes.operators.StartIndex;
import org.httprobot.common.rml.datatypes.operators.Substring;
import org.httprobot.core.rml.controls.RmlControl;
import org.httprobot.core.rml.controls.RmlOperatorControl;

/**
 * Substring control class. Inherits {@link RmlOperatorControl}.
 * @author Joan
 */
@XmlRootElement
public class SubstringControl extends RmlOperatorControl 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2151542622321854945L;
	
	private GetField get_field = null;
	private StartIndex start_index = null;
	private EndIndex end_index = null;	
	private GetFieldControl get_field_control;
	
	/**
	 * @return the get_field
	 */
	public GetField getGet_field() {
		return get_field;
	}
	/**
	 * @return the start_index
	 */
	public StartIndex getStart_index() {
		return start_index;
	}
	/**
	 * @return the end_index
	 */
	public EndIndex getEnd_index() {
		return end_index;
	}
	public SubstringControl()
	{
		super();
	}
	/**
	 * SubstringControl constructor.
	 * @param parent RmlControl the parent control
	 * @param substring Substring the substring
	 */
	public SubstringControl(RmlControl parent, Substring substring) 
	{
		super(parent, substring);
		this.start_index = substring.getStartIndex();
		this.end_index = substring.getEndIndex();
		this.get_field = substring.getGetField();
		
		if(get_field != null)
		{
			this.get_field_control = new GetFieldControl(this, get_field);
			this.get_field_control.setMessage(get_field);	
		}
		else
		{
			this.setMessage(substring);
			this.setIsRendered(true);
		}
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