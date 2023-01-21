/**
 * 
 */
package org.httprobot.core.managers.config;

import org.httprobot.common.config.Log;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.core.controls.config.LogControl;
import org.httprobot.core.controls.config.interfaces.IControlLogListener;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.managers.ConfigManager;
import org.httprobot.core.managers.config.interfaces.IManagerLogListener;

/**
 * {@link Log} message manager class. Inherits {@link ConfigManager}.
 * <br>
 * It's {@link IControlLogListener}.
 * @author joan
 *
 */
public class LogManager 
	extends ConfigManager<Log, LogControl, IManagerLogListener>
	implements IControlLogListener {

	/**
	 * 4149961515527350971L
	 */
	private static final long serialVersionUID = 4149961515527350971L;

	/**
	 * {@link Log} message manager default class constructor.
	 */
	public LogManager() {
		super();
	}
	/**
	 * {@link Log} message manager class constructor.
	 * @param parent {@link IManagerLogListener} the parent
	 * @param message {@link Log} the message
	 */
	public LogManager(IManagerLogListener parent, Log message) {
		super(parent, message);
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
	 * @see org.httprobot.core.controls.config.interfaces.IControlLogListener#OnControlLog_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlLog_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlLogListener#OnControlLog_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlLog_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlLogListener#OnControlLog_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlLog_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlLogListener#OnControlLog_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlLog_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlLogListener#OnControlLog_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlLog_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.config.interfaces.IControlLogListener#OnControlLog_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlLog_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ConfigManager#OnManagerLog_Error(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerLog_Error(Object sender, ManagerEventArgs e)
			throws NotImplementedException, ManagerException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ConfigManager#OnManagerLog_Finished(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerLog_Finished(Object sender, ManagerEventArgs e)
			throws NotImplementedException, ManagerException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.ConfigManager#OnManagerLog_Started(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManagerLog_Started(Object sender, ManagerEventArgs e)
			throws NotImplementedException, ManagerException {
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
}