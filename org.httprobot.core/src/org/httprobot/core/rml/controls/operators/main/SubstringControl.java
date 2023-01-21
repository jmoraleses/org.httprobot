package org.httprobot.core.rml.controls.operators.main;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.datatypes.operators.EndIndex;
import org.httprobot.common.rml.datatypes.operators.GetField;
import org.httprobot.common.rml.datatypes.operators.StartIndex;
import org.httprobot.common.rml.datatypes.operators.Substring;
import org.httprobot.common.tools.CommandLineInterface;
import org.httprobot.core.rml.controls.RmlControl;
import org.httprobot.core.rml.controls.RmlOperatorControl;
import org.httprobot.core.rml.controls.operators.EndIndexControl;
import org.httprobot.core.rml.controls.operators.GetFieldControl;
import org.httprobot.core.rml.controls.operators.StartIndexControl;

/**
 * Substring control class. Inherits {@link RmlOperatorControl}.
 * @author Joan
 */
@XmlRootElement
public class SubstringControl extends MainOperatorControl<Substring> 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2151542622321854945L;
	
	StartIndexControl startIndexControl = null;
	EndIndexControl endIndexControl = null;	
	GetFieldControl getFieldControl;

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
				Substring substring = Substring.class.cast(e.getMessage());
				
				if(substring.getGetField() != null)
				{
					this.getFieldControl.setMessage(substring.getGetField());
				}
				if(substring.getStartIndex() != null && substring.getEndIndex() != null)
				{
					this.startIndexControl.setMessage(substring.getStartIndex());
					this.endIndexControl.setMessage(substring.getEndIndex());
				}
				else if(!this.hasChilds())
				{
					CommandLineInterface.ThrowInconsistentMessageException(this, "SubstringControl.OnControlLoaded: At least one RML message expected");
				}
				else
				{
					CommandLineInterface.ThrowInconsistentMessageException(this, "SubstringControl.OnControlLoaded: StartIndex or EndIndex RML message expected");
				}
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "SubstringControl.OnControlLoaded: Substring RML message expected");
			}
		}
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
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		if(e.getMessage() != null)
		{
			this.operator = new Substring();
			
			this.setUuid(e.getMessage().getUuid());
			this.setInherited(e.getMessage().getInherited());
			this.setRuntimeOptions(e.getMessage().getRuntimeOptions());
			
			this.operator.setUuid(getUuid());
			this.operator.setInherited(getInherited());
			this.operator.setRuntimeOptions(getRuntimeOptions());			
			
			try
			{
				Substring substring = Substring.class.cast(e.getMessage());
				
				if(substring.getGetField() != null)
				{
					//Instantiate GetField RML message control.
					this.getFieldControl = new GetFieldControl(this, substring.getGetField());
					
					//Associate control to parent control.
					this.addCommandOutputListener(this);
					this.getFieldControl.addGetFieldListener(this);
				}
				
				if(substring.getEndIndex() != null && substring.getStartIndex() !=null)
				{
					//Instantiate StartIndex and EndIndex controls
					this.endIndexControl = new EndIndexControl(this, substring.getEndIndex());
					this.startIndexControl = new StartIndexControl(this, substring.getStartIndex());
					
					//Associate controls to parent control.
					this.addCommandOutputListener(this.endIndexControl);
					this.addCommandOutputListener(this.startIndexControl);
					this.endIndexControl.addEndIndexListener(this);
					this.startIndexControl.addStartIndexListener(this);
				}
				else
				{
					CommandLineInterface.ThrowInconsistentMessageException(this, "SubstringControl.OnControlInit: StartIndex and EndIndex RML messages expected");
				}
				
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "SubstringControl.OnControlInit: Substring RML message expected");
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
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnEndIndexChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnEndIndexChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnEndIndexInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnEndIndexInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnEndIndexLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnEndIndexLoaded(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException
	{
		if(e.getMessage() != null)
		{
			try
			{
				if(this.operator != null)
				{
					this.operator.setEndIndex(EndIndex.class.cast(e.getMessage()));
				}
				else
				{
					CommandLineInterface.ThrowInconsistentMessageException(this, "SubstringControl.OnEndIndexLoaded: operaotr RML message NULL");
				}
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "SubstringControl.OnEndIndexLoaded: EndIndex RML message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnEndIndexRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnEndIndexRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnEndIndexRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnEndIndexRendered(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnEndIndexWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnEndIndexWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnStartIndexChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStartIndexChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnStartIndexInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStartIndexInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnStartIndexLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStartIndexLoaded(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException 
	{
		if(e.getMessage() != null)
		{
			try
			{
				if(this.operator != null)
				{
					this.operator.setStartIndex(StartIndex.class.cast(e.getMessage()));
				}
				else
				{
					CommandLineInterface.ThrowInconsistentMessageException(this, "SubstringControl.OnEndIndexLoaded: operator RML message NULL");
				}
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "SubstringControl.OnEndIndexLoaded: StartIndex RML message expected");
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
				}
				else
				{
					CommandLineInterface.ThrowInconsistentMessageException(this, "SubstringControl.OnGetFieldLoaded: operator RML message NULL");
				}
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "SubstringControl.OnGetFieldLoaded: GetField RML message expected");
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
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnStartIndexRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStartIndexRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnStartIndexRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStartIndexRendered(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnStartIndexWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStartIndexWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
}