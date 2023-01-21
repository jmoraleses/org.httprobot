/**
 * 
 */
package org.httprobot.core.managers.config;

import org.httprobot.common.config.Session;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.core.controls.config.SessionControl;
import org.httprobot.core.controls.config.interfaces.IControlSessionListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.managers.ConfigManager;
import org.httprobot.core.managers.config.interfaces.IManagerSessionListener;

/**
 * {@link Session} message manager class. Inherits {@link ConfigManager}.
 * It's {@link IControlSessionListener}.
 * <br>
 * @author joan
 *
 */
public class SessionManager 
		extends ConfigManager<Session, SessionControl, IManagerSessionListener> 
		implements IControlSessionListener {
	
	/**
	 * -4758290085662744654L
	 */
	private static final long serialVersionUID = -4758290085662744654L;

	/**
	 * 
	 */
	public SessionManager() 
	{
		super();
	}
	/**
	 * @param parent
	 * @param message
	 */
	public SessionManager(IManagerSessionListener parent, Session message) 
	{
		super(parent, message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlSessionListener#OnControlSession_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSession_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlSessionListener#OnControlSession_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSession_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlSessionListener#OnControlSession_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSession_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlSessionListener#OnControlSession_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSession_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlSessionListener#OnControlSession_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSession_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlSessionListener#OnControlSession_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSession_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ConfigManager#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ConfigManager#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ConfigManager#start()
	 */
	@Override
	public void start() {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ConfigManager#stop()
	 */
	@Override
	public void stop() {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ConfigManager#OnManagerSession_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSession_Error(Object sender, ManagerEventArgs e)
			throws NotImplementedException, ManagerException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ConfigManager#OnManagerSession_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSession_Finished(Object sender, ManagerEventArgs e)
			throws NotImplementedException, ManagerException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ConfigManager#OnManagerSession_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerSession_Started(Object sender, ManagerEventArgs e)
			throws NotImplementedException, ManagerException {
	}
}