package org.httprobot.core.rml.controls.operators;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.datatypes.operators.TryParse;
import org.httprobot.core.rml.controls.RmlOperatorControl;

/**
 * @author Joan
 * TryParse Control Class. Inherits RmlStringOperatorControl.
 */
@XmlRootElement
public class TryParseControl extends RmlOperatorControl
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6553843004370009753L;
	
	public TryParseControl()
	{
		super();
	}
	/**
	 * TryParseControl constructor.
	 * @param parent {@link IRmlListener} the parent
	 */
	public TryParseControl(IRmlListener parent, TryParse try_parse) 
	{
		super(parent, try_parse);
		
	}
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e) 
	{
		
	}
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e) 
	{
		
	}
	@Override
	public void OnControlRendered(Object sender, RmlEventArgs e) 
	{
		
	}
	@Override
	public void OnControlInit(Object sender, RmlEventArgs e) 
	{
		
	}
	@Override
	public void OnControlRead(Object sender, RmlEventArgs e)
			throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	@Override
	public void OnControlWrite(Object sender, RmlEventArgs e)
			throws NotImplementedException, InconsistenMessageException 
	{
		
	}


}
