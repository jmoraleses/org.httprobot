package org.httprobot.core.rml.controls.operators.main;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.datatypes.operators.GetField;
import org.httprobot.common.rml.datatypes.operators.Replace;
import org.httprobot.common.tools.CommandLineInterface;
import org.httprobot.core.rml.controls.operators.GetFieldControl;


/**
 * @author Joan
 * Replace Control Class. Inherits RmlStringOperatorControl. 
 */
@XmlRootElement
public class ReplaceControl extends MainOperatorControl<Replace> 
{
	/**
	 * -3219652987951425693L
	 */
	private static final long serialVersionUID = -3219652987951425693L;

	GetFieldControl getFieldControl;
	
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
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
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
			this.operator = new Replace();
			
			//Associate message to control.
			this.addCommandOutputListener(this.operator);
			
			this.setUuid(e.getMessage().getUuid());
			this.setInherited(e.getMessage().getInherited());
			this.setRuntimeOptions(e.getMessage().getRuntimeOptions());
			
			this.operator.setUuid(getUuid());
			this.operator.setInherited(getInherited());
			this.operator.setRuntimeOptions(getRuntimeOptions());
			
			try
			{
				Replace replace = Replace.class.cast(e.getMessage());
				
				if(replace.getGetField() != null)
				{
					//Instantiate GetField RML control.
					this.getFieldControl = new GetFieldControl(this, replace.getGetField());
					
					//Associate control to parent control
					this.addCommandOutputListener(getFieldControl);
					this.getFieldControl.addGetFieldListener(this);
				}
				else if (!this.hasChilds())
				{
					CommandLineInterface.ThrowInconsistentMessageException(this, "ReplaceControl.OnControlInit: At least one operator RML message expected");
				}
				
				if(replace.getNewString() == null || replace.getOldString() == null)
				{
					CommandLineInterface.ThrowInconsistentMessageException(this, "ReplaceControl.OnControlInit: OldString or NewString RML message NULL");
				}
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "ReplaceControl.OnControlInit: Replace RML message expected");
			}
		}
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
				Replace replace = Replace.class.cast(e.getMessage());
				
				if(replace.getGetField() != null)
				{
					getFieldControl.setMessage(replace.getGetField());
				}
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "ReplaceControl.OnControlLoaded: Replace RML message expected");
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
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		super.OnControlRendered(sender, e);
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
	public void OnGetFieldLoaded(Object sender, RmlEventArgs e) throws InconsistenMessageException
	{
		if(e.getMessage() != null)
		{
			try
			{
				if(this.operator != null)
				{
					this.operator.setGetField(GetField.class.cast(e.getMessage()));	
				}
				else
				{
					CommandLineInterface.ThrowInconsistentMessageException(this, "ReplaceControl.OnGetFieldLoaded: operator RML message NULL");
				}				
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "ReplaceControl.OnGetFieldLoaded: GetField RML message expected");
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