package org.httprobot.core.rml.controls.operators.main;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.datatypes.operators.Equals;
import org.httprobot.common.rml.datatypes.operators.GetField;
import org.httprobot.common.tools.CommandLineInterface;
import org.httprobot.core.rml.controls.operators.GetFieldControl;


/**
 * @author Joan
 * Equals Control Class. Inherits RmlStringOperatorControl.
 */
@XmlRootElement
public class EqualsControl extends MainOperatorControl<Equals>
{
	/**
	 * -7062776107775980742L
	 */
	private static final long serialVersionUID = -7062776107775980742L;

	GetFieldControl getFieldControl;
	
	public EqualsControl()
	{
		super();
	}
	/**
	 * @param parent {@link IRmlListener} the parent
	 * @param equals {@link Equals} the equals
	 */
	public EqualsControl(IRmlListener parent, Equals equals) 
	{
		super(parent, equals);
	}	
	/**
	 * @return the value of Equals RML message object
	 * @throws InconsistenMessageException when operator RML message is NULL
	 */
	public Object getValue() throws InconsistenMessageException
	{
		if(this.operator != null)
		{
			try
			{
				Equals equals = Equals.class.cast(this.operator);
				
				return equals.getValue();
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "EqualsControl.getValue: operator RML message must be Equals message");
				return null;
			}
		}
		else
		{
			CommandLineInterface.ThrowInconsistentMessageException(this, "EqualsControl.getValue: operator RML message NULL");
			return null;
		}
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{		
		if(e.getMessage() != null)
		{
			try
			{
				Equals equals = Equals.class.cast(e.getMessage());
			
				if(equals.getGetField() != null)
				{
					getFieldControl.setMessage(equals.getGetField());
				}
				else if(!this.hasChilds())
				{
					CommandLineInterface.ThrowInconsistentMessageException(this, "EqualsControl.OnControlInit: At least one RML operator message expected");
				}
			}
			catch(ClassCastException ca)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "EqualsControls.OnControlLoaded: Equals RML message expected.");
			}
		}
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnControlChanged(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnControlRendered(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnControlInit(sender, e);
		
		if(e.getMessage() != null)
		{
			//Initialize using data
			this.operator = new Equals();
			
			//Associate message with control
			this.addCommandOutputListener(this.operator);
			
			//Set inherited data
			this.setUuid(e.getMessage().getUuid());
			this.setInherited(e.getMessage().getInherited());
			this.setRuntimeOptions(e.getMessage().getRuntimeOptions());
			
			this.operator.setUuid(getUuid());
			this.operator.setInherited(getInherited());
			this.operator.setRuntimeOptions(getRuntimeOptions());
			
			try
			{
				Equals equals = Equals.class.cast(e.getMessage());
				
				//Instantiate controls for each specific child.
				if(equals.getGetField() != null)
				{
					this.getFieldControl = new GetFieldControl(this, equals.getGetField());
					
					//Associate control to parent.
					this.getFieldControl.addGetFieldListener(this);
					this.addCommandOutputListener(this.getFieldControl);
				}
				else if(!this.hasChilds())
				{
					CommandLineInterface.ThrowInconsistentMessageException(this, "EqualsControl.OnControlInit: At least one operator RML message expected.");
				}
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "EqualsControl.OnControlInit: Equals RML message expected.");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnControlRead(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnControlWrite(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnGetFieldChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnGetFieldChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnGetFieldInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnGetFieldInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnGetFieldLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnGetFieldLoaded(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException 
	{
		if(e.getMessage() != null)
		{
			try
			{
				this.operator.setGetField(GetField.class.cast(e.getMessage()));
				
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "EqualsControl.OnGetFieldLoaded: RML GetField message expected.");
			}
		}
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
}