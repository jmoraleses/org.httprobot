package org.httprobot.core.operations;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.RML;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.core.controls.Control;

/**
 * LoginRequest control class.Inherits {@link Control}.
 * @author joan
 *
 */
@XmlRootElement
public class LoginRequest extends Control<RML, IControlListener> 
{
	/**
	 * 2742337657286835695L
	 */
	private static final long serialVersionUID = 2742337657286835695L;
	/**
	 * Login requests default class constructor.
	 */
	public LoginRequest() 
	{
		super();		
	}
	/**
	 * Login requests class constructor.
	 * @param parent {@link IControlListener} the parent
	 */
	public LoginRequest(IControlListener parent, RML message) 
	{
		super(parent, message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void Load(Object sender, ControlEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlControl#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void Change(Object sender, ControlEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlControl#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void Render(Object sender, ControlEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlControl#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void Initialize(Object sender, ControlEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlControl#OnControlRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlControl#OnControlWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException 
	{
		
	}
}