package org.httprobot.core.rml.controls.operators.main;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.datatypes.operators.GetField;
import org.httprobot.common.rml.datatypes.operators.XmlQuery;
import org.httprobot.common.tools.CommandLineInterface;
import org.httprobot.core.rml.controls.RmlControl;
import org.httprobot.core.rml.controls.operators.GetFieldControl;

@XmlRootElement
public class XmlQueryControl extends MainOperatorControl<XmlQuery> 
{
	/**
	 * 1188429079391753214L
	 */
	private static final long serialVersionUID = 1188429079391753214L;
	
	GetFieldControl getFieldControl;	
	/**
	 * 
	 */
	public XmlQueryControl()
	{
		super();
	}
	/**
	 * @param parent
	 * @param xmlQuery
	 */
	public XmlQueryControl(RmlControl parent, XmlQuery xmlQuery) 
	{
		super(parent, xmlQuery);
	}

	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
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
			this.operator = new XmlQuery();
			
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
				XmlQuery xmlQuery = XmlQuery.class.cast(e.getMessage());
				
				if(xmlQuery.getGetField() != null)
				{
					//Instantiate GetField control
					getFieldControl = new GetFieldControl(this, xmlQuery.getGetField());
					
					//Associate control to parent
					this.addCommandOutputListener(getFieldControl);
					getFieldControl.addGetFieldListener(this);
				}
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "XmlQueryControl.OnControlInit: XmlQuery RML message expected");
			}
		}
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
				if(this.operator != null)
				{
					this.operator.setGetField(GetField.class.cast(e.getMessage()));
					
					//Fire command input event
					CliCommandInputEvent(new CliEventArgs(this, Command.GET_FIELD_CONTROL, this.operator.getGetField()));
				}
				else
				{
					CommandLineInterface.ThrowInconsistentMessageException(this, "RemoveControl.OnGetFieldLoad: operator RML message NULL.");
				}
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "RemoveControl.OnGetFieldLoad: GetField RML message expected.");
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
}