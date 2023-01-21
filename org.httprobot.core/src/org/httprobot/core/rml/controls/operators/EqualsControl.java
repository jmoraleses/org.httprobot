package org.httprobot.core.rml.controls.operators;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.datatypes.operators.Equals;
import org.httprobot.core.rml.controls.RmlOperatorControl;


/**
 * @author Joan
 * Equals Control Class. Inherits RmlStringOperatorControl.
 */
@XmlRootElement
public class EqualsControl extends RmlOperatorControl  
{
	/**
	 * -7062776107775980742L
	 */
	private static final long serialVersionUID = -7062776107775980742L;

	Equals equals = null;
	
	public EqualsControl()
	{
		super();
		
		//Initialize using data
		this.equals = new Equals();
		
		//Associate message with control
		this.addCommandOutputListener(equals);
	}
	/**
	 * @param parent {@link IRmlListener} the parent
	 * @param equals {@link Equals} the equals
	 */
	public EqualsControl(IRmlListener parent, Equals equals) 
	{
		super(parent, equals);
		
		//Initialize using data
		this.equals = new Equals();
		
		//Associate message with control
		this.addCommandOutputListener(equals);
		
		if(equals != null)
		{
			//Set inherited data
			this.setUuid(equals.getUuid());
			this.setInherited(equals.getInherited());
			this.setCliOptions(equals.getCliOptions());
			
			this.equals.setUuid(getUuid());
			this.equals.setInherited(getInherited());
			this.equals.setCliOptions(getCliOptions());
			
			//TODO How to check if this instance has child controls?
		}
		else
		{
			this.setMessage(null);	
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