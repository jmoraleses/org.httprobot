package org.httprobot.core.operations;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.RML;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.core.controls.Control;
import org.httprobot.core.controls.interfaces.impl.IControlImpl;

/**
 * @author Joan
 * ServerInfoResponse Operation Class. Inherits RmlControl
 */
@XmlRootElement
public class ServerInfoResponse extends Control<RML, IControlImpl>
{
	/**
	 * 4710647340710735107L
	 */
	private static final long serialVersionUID = 4710647340710735107L;

	/**
	 * ServerInfo response control class constructor.
	 * @param parent 
	 */
	public ServerInfoResponse(IControlImpl parent, ServerInfoResponse serverInfoResponse) {
		super(parent, serverInfoResponse);
		
	}
	/**
	 * ServerInfo response control default class constructor.
	 */
	public ServerInfoResponse()
	{
		super();
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