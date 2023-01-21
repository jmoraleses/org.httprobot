/**
 * 
 */
package org.httprobot.core.rml.controls.config;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.config.Session;
import org.httprobot.core.rml.controls.RmlConfigControl;

/**
 * Session control class. Inherits {@link RmlConfigControl}.
 * @author Joan
 *
 */
public class SessionControl extends RmlConfigControl 
{
	/**
	 * 4256093253328659294L
	 */
	private static final long serialVersionUID = 4256093253328659294L;
	/**
	 * Session control default class constructor.
	 */
	public SessionControl() 
	{
		
	}
	/**
	 * Session control class constructor.
	 * @param parent {@link IRmlListener} the parent
	 */
	public SessionControl(IRmlListener parent, Session session) 
	{
		super(parent, session);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlConfigControl#OnControlChanged(java.lang.Object, org.httprobot.core.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlConfigControl#OnControlInit(java.lang.Object, org.httprobot.core.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlInit(Object sender, RmlEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlConfigControl#OnControlLoaded(java.lang.Object, org.httprobot.core.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e)
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlConfigControl#OnControlRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRead(Object sender, RmlEventArgs e)
			throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlConfigControl#OnControlRendered(java.lang.Object, org.httprobot.core.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlRendered(Object sender, RmlEventArgs e) 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlConfigControl#OnControlWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlWrite(Object sender, RmlEventArgs e)
			throws NotImplementedException, InconsistenMessageException
	{
		
	}
}