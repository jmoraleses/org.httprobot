package org.httprobot.core.rml.controls.datatypes;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.datatypes.Field;
import org.httprobot.common.rml.datatypes.Rules;
import org.httprobot.common.tools.CommandLineInterface;
import org.httprobot.core.rml.controls.RmlDataTypeControl;


/**
 * @author Joan
 * FieldControl Class. Inherits RmlDataTypeControl.
 */
@XmlRootElement
public class FieldControl extends RmlDataTypeControl
{
	/**
	 * -7062776107775980742L
	 */
	private static final long serialVersionUID = -7062776107775980742L;
	private Integer item_num;
	private String field_name;
	private String field_type;
	private RulesControl rules_control;
	private Field field;
	/**
	 * @return item_count Integer the number of the current field
	 */
	public Integer getItem_count() 
	{
		return item_num;
	}
	/**
	 * @return  {@link String} the data view field name to be extracted
	 */
	public String getField_name()
	{
		return field_name;
	}
	/**
	 * @return {@link String} the field type
	 */
	public String getField_type()
	{
		return field_type;
	}
	/**
	 * @return Rules controller
	 */
	public RulesControl getGet_rules() 
	{
		return rules_control;
	}
	/**
	 * RML field control default class constructor.
	 */
	public FieldControl()
	{
		super();
	}	
	/** 
	 * RML field control class constructor.
	 * @param parent {@link IRmlListener} parent object
	 * @param field {@link Field} the field message to be treated
	 */
	public FieldControl(IRmlListener parent, Field field)
	{
		super(parent, field);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnRulesLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnRulesLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		if(e.getMessage() != null)
		{
			try
			{
				//Set loaded data
				this.field.setRules(Rules.class.cast(e.getMessage()));
				
//				CliCommandInputEvent(new CliEventArgs(this, Command.FIELD_CONTROL, e.getMessage()));
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "\nFieldControl.OnRulesLoad: Rules RML message expected");
			}	
		}		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			try
			{
				Field field = Field.class.cast(e.getMessage());
				
				//Set non-controlled data
				this.field_name = field.getFieldName();
				this.field_type = field.getFieldType();
				
				//Set controlled data
				if(field.getRules() != null)
				{
					this.rules_control.setMessage(field.getRules());
				}
				else
				{
					CommandLineInterface.ThrowInconsistentMessageException(this, "FieldControl.OnControlLoaded: Rules RML message expected ");
				}
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "FieldControl.OnControlLoaded: Field RML message expected ");
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnRulesChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRulesChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnRulesInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRulesInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnRulesRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRulesRead(Object sender, RmlEventArgs e)throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnRulesRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRulesRendered(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnRulesWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRulesWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			//Initialize using data.
			this.field = new Field();
			
			//Associate child control to parent.
			this.addCommandOutputListener(this.field);
			
			try
			{
				//Set inherited data.
				this.setUuid(e.getMessage().getUuid());
				this.setInherited(e.getMessage().getInherited());
				this.setRuntimeOptions(e.getMessage().getRuntimeOptions());
				
				this.field.setUuid(getUuid());
				this.field.setInherited(getInherited());
				this.field.setRuntimeOptions(getRuntimeOptions());
				
				Field field = Field.class.cast(e.getMessage());	
				
				if(field.getRules() != null)
				{
					//If Rules message not null instantiate corresponding control.
					this.rules_control = new RulesControl(this, field.getRules());
					
					//Associate child control to parent.
					this.addCommandOutputListener(this.rules_control);
				}
				else
				{
					CommandLineInterface.ThrowInconsistentMessageException(this, "FieldControl.OnControlLoaded: Rules RML message expected ");
				}	
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "FieldControl.OnControlLoaded: Field RML message expected ");
			}	
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnFieldChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnFieldInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnFieldLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldLoaded(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnFieldRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnFieldRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldRendered(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnFieldWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{

	}
}