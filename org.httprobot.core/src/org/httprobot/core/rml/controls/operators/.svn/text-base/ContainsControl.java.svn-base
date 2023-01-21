package org.httprobot.core.rml.controls.operators;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.datatypes.operators.Condition;
import org.httprobot.common.rml.datatypes.operators.Contains;
import org.httprobot.common.rml.datatypes.operators.Equals;
import org.httprobot.common.rml.datatypes.operators.ForEach;
import org.httprobot.common.rml.datatypes.operators.GetField;
import org.httprobot.common.rml.datatypes.operators.Remove;
import org.httprobot.common.rml.datatypes.operators.Replace;
import org.httprobot.common.rml.datatypes.operators.Select;
import org.httprobot.common.rml.datatypes.operators.Split;
import org.httprobot.common.rml.datatypes.operators.TryParse;
import org.httprobot.common.rml.datatypes.operators.XmlQuery;
import org.httprobot.core.rml.controls.RmlOperatorControl;

/**
 * @author Joan
 * Contains Control Class. Inherits RmlStringOperatorControl<
 */
@XmlRootElement
public class ContainsControl extends RmlOperatorControl 
{
	/**
	 * -5408324213939309086L
	 */
	private static final long serialVersionUID = -5408324213939309086L;

	private Contains contains = null;
	
	private GetFieldControl get_field_control;	

	/**
	 * Contains control default class constructor.
	 */
	public ContainsControl()
	{
		super();
	}
	/**
	 * ContainsControl class constructor.
	 * @param sender {@link IRmlListener}
	 * @param contains {@link Contains}
	 */
	public ContainsControl(IRmlListener sender, Contains contains)
	{
		super(sender, contains);

		this.contains = new Contains();
		
		if(contains != null)
		{
			//Set inherited data.
			this.setUuid(contains.getUuid());
			this.setInherited(contains.getInherited());
			this.setCliOptions(contains.getCliOptions());
			
			this.contains.setUuid(this.getUuid());
			this.contains.setInherited(this.getInherited());
			this.contains.setCliOptions(this.getCliOptions());
			
			//Associate message to control.
			this.addCommandOutputListener(this.contains);
			
			GetField get_field = contains.getGetField();
			Condition condition = contains.getCondition();
			Contains contains2 = contains.getContains();
			Equals equals = contains.getEquals();
			ForEach for_each = contains.getForEach();
			Remove remove = contains.getRemove();
			Replace replace = contains.getReplace();
			Select select = contains.getSelect();
			Split split = contains.getSplit();
			TryParse try_parse = contains.getTryParse();
			XmlQuery xml_query = contains.getXmlQuery();
			
			
			Boolean found = false;
			
			if(get_field != null)
			{
				this.get_field_control = new GetFieldControl(this, contains.getGetField());
				this.get_field_control.setMessage(contains.getGetField());
				found = true;
			}
			if(condition != null)
			{
				this.get_field_control = new GetFieldControl(this, contains.getGetField());
				this.get_field_control.setMessage(contains.getGetField());
				found = true;
			}
			if(contains2 != null)
			{
				this.get_field_control = new GetFieldControl(this, contains.getGetField());
				this.get_field_control.setMessage(contains.getGetField());
				found = true;
			}
			if(equals != null)
			{
				this.get_field_control = new GetFieldControl(this, contains.getGetField());
				this.get_field_control.setMessage(contains.getGetField());
				found = true;
			}
			if(for_each != null)
			{
				this.get_field_control = new GetFieldControl(this, contains.getGetField());
				this.get_field_control.setMessage(contains.getGetField());
				found = true;
			}
			if(remove != null)
			{
				this.get_field_control = new GetFieldControl(this, contains.getGetField());
				this.get_field_control.setMessage(contains.getGetField());
				found = true;
			}
			if(replace != null)
			{
				this.get_field_control = new GetFieldControl(this, contains.getGetField());
				this.get_field_control.setMessage(contains.getGetField());
				found = true;
			}
			if(select != null)
			{
				this.get_field_control = new GetFieldControl(this, contains.getGetField());
				this.get_field_control.setMessage(contains.getGetField());
				found = true;
			}
			if(split != null)
			{
				this.get_field_control = new GetFieldControl(this, contains.getGetField());
				this.get_field_control.setMessage(contains.getGetField());
				found = true;
			}
			if(try_parse != null)
			{
				this.get_field_control = new GetFieldControl(this, contains.getGetField());
				this.get_field_control.setMessage(contains.getGetField());
				found = true;
			}
			if(xml_query != null)
			{
				this.get_field_control = new GetFieldControl(this, contains.getGetField());
				this.get_field_control.setMessage(contains.getGetField());
				found = true;
			}
			
			if(!found)
			{	
				//Continue with work flow.
				this.setMessage(contains);			
			}
		}
		else
		{
			//Continue with work flow.
			this.setMessage(null);
		}		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnGetFieldLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnGetFieldLoaded(Object sender, RmlEventArgs e)
	{
		//Set loaded data
		this.contains.setGetField(GetField.class.cast(e.getMessage()));	
		
		//Continue with work flow.
		this.setMessage(this.contains);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e) 
	{
		//Continue with work flow.
		this.setIsRendered(true);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e)
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlRendered(Object sender, RmlEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlInit(Object sender, RmlEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
}
