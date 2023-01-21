package org.httprobot.core.rml.controls.operators.main;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.datatypes.operators.Contains;
import org.httprobot.common.rml.datatypes.operators.GetField;
import org.httprobot.common.tools.CommandLineInterface;
import org.httprobot.core.rml.controls.operators.GetFieldControl;

/**
 * @author Joan
 * Contains Control Class. Inherits RmlStringOperatorControl<
 */
@XmlRootElement
public class ContainsControl extends MainOperatorControl<Contains> 
{
	/**
	 * -5408324213939309086L
	 */
	private static final long serialVersionUID = -5408324213939309086L;
	
	GetFieldControl getFieldControl;	

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
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnGetFieldLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnGetFieldLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		if(e.getMessage() != null)
		{
			try
			{
				//Set loaded data
				this.operator.setGetField(GetField.class.cast(e.getMessage()));	
				
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "ContainsControl,OnGetFieldLoaded; GetField RML message expected");
			}	
		}				
	}
	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnGetFieldChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnGetFieldChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnGetFieldInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnGetFieldInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnGetFieldRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnGetFieldRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnGetFieldRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnGetFieldRendered(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnGetFieldWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnGetFieldWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnControlLoaded(sender, e);
		
		if(e.getMessage() != null)
		{
			try
			{
				Contains contains = Contains.class.cast(e.getMessage());
				
				if(contains.getGetField() != null)
				{
					this.getFieldControl.setMessage(contains.getGetField());
				}
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "ContainsControl.OnControlLoaded: Contains RML message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlInit(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException 
	{
		super.OnControlInit(sender, e);
		
		if(e.getMessage() != null)
		{
			this.operator = new Contains();
			
			//Set inherited data.
			this.setUuid(e.getMessage().getUuid());
			this.setInherited(e.getMessage().getInherited());
			this.setRuntimeOptions(e.getMessage().getRuntimeOptions());
			
			this.operator.setUuid(this.getUuid());
			this.operator.setInherited(this.getInherited());
			this.operator.setRuntimeOptions(this.getRuntimeOptions());
						
			try
			{
				Contains contains = Contains.class.cast(e.getMessage());
				
				//Associate message to control.
				this.addCommandOutputListener(this.operator);
				
				if(contains.getGetField() != null)
				{
					//Instantiate GetField control.
					this.getFieldControl = new GetFieldControl(this, contains.getGetField());
					
					//Associate control to parent control.
					this.getFieldControl.addGetFieldListener(this);
					this.addCommandOutputListener(this.getFieldControl);
				}
				else if(!this.hasChilds())
				{
					CommandLineInterface.ThrowInconsistentMessageException(this, "ContainsControl.OnControlInit: GetField RML message expected");
				}
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "ContainsControl.OnControlInit: Contains RML message expected");
			}
		}
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
