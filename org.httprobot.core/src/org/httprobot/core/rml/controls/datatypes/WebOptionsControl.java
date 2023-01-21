/**
 * 
 */
package org.httprobot.core.rml.controls.datatypes;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.Rml;
import org.httprobot.common.rml.datatypes.WebOptions;
import org.httprobot.common.tools.CommandLineInterface;
import org.httprobot.core.rml.controls.RmlDataTypeControl;

/**
 * @author joan
 *
 */
public class WebOptionsControl extends RmlDataTypeControl {

	/**
	 * 3164743281243118998L
	 */
	private static final long serialVersionUID = 3164743281243118998L;
	
	WebOptions webOptions;

	/**
	 * WebOptions RML message control default class constructor
	 */
	public WebOptionsControl() 
	{
		super();
	}
	/**
	 * WebOptions RML message control class constructor
	 * @param parent
	 * @param webOptions
	 */
	public WebOptionsControl(IRmlListener parent, WebOptions webOptions) 
	{
		super(parent, webOptions);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			//Initialize using data
			this.webOptions = new WebOptions();
			
			//Associate message to control
			this.addCommandOutputListener(this.webOptions);
			
			//Set inherited data
			this.setUuid(e.getMessage().getUuid());
			this.setInherited(e.getMessage().getInherited());
			this.setRuntimeOptions(e.getMessage().getRuntimeOptions());
			
			this.webOptions.setUuid(getUuid());
			this.webOptions.setInherited(getInherited());
			this.webOptions.setRuntimeOptions(getRuntimeOptions());
			
			try
			{
				WebOptions.class.cast(e.getMessage());
				/*No child controls*/
//				WebOptions webOptions = WebOptions.class.cast(e.getMessage());
//				
//				if(webOptions.getActiveXNativeEnabled() != null)
//				{
//					//Instantiate new controls
//				}
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "WebOptionsControl.OnControlInit: WebOptions RML message exception");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			Rml rml = e.getMessage();
			
			//Set non-controlled data
			if(rml instanceof WebOptions)
			{
				this.webOptions = WebOptions.class.cast(rml);
			}
			
			//If controlled data -> setMessage for each control.
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
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
}