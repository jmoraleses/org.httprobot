package org.httprobot.core.data.view;

import java.util.EnumSet;
import java.util.Map;

import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.common.definitions.Enums.CliNamespace;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IListener;
import org.httprobot.common.io.CLI;
import org.httprobot.core.contents.DocumentLibrary;
import org.httprobot.core.contents.TemplateLibrary;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.contents.interfaces.IManagerDataViewListener;
import org.w3c.dom.events.Event;

/**
 * {@link ViewTable}'s elements
 * @author Joan
 *
 */
public abstract class ViewElement extends CLI implements IManagerDataViewListener
{
	/**
	 * 9169739345535979992L
	 */
	private static final long serialVersionUID = 9169739345535979992L;
	
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#reset()
	 */
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public IManagerImpl next() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#start()
	 */
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#stop()
	 */
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#addChildManager(org.httprobot.core.interfaces.IManagerImpl)
	 */
	@Override
	public void addChildManager(IManagerImpl manager)
			throws NotImplementedException, ManagerException {
		// TODO Auto-generated method stub
		
	}

	private static final CliNamespace cliNamespace = CliNamespace.DATA;
	private EnumSet<RuntimeOptions> debugOptions;
	private String destinationPath;
	/**
	 * ViewElement default class constructor.
	 * @param listener
	 */
	public ViewElement(IListener listener) 
	{
		this.debugOptions = listener.getRuntimeOptions();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getDebugNamespace()
	 */
	@Override
	public CliNamespace getCliNamespace() 
	{
		return cliNamespace;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getDestinationPath()
	 */
	@Override
	public String getDestinationPath()
	{
		return this.destinationPath;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getDebugOptions()
	 */
	@Override
	public EnumSet<RuntimeOptions> getRuntimeOptions() 
	{
		return this.debugOptions;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#getContentTypeRoot()
	 */
	@Override
	public ContentTypeRoot getContentTypeRoot() throws NotImplementedException,
			ManagerException {
		return null;
	}
	/* (non-Javadoc)
	 * @see org.w3c.dom.events.EventListener#handleEvent(org.w3c.dom.events.Event)
	 */
	@Override
	public void handleEvent(Event arg0) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnControlCommand(java.lang.Object, org.httprobot.common.events.CommandEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e)
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandRead(java.lang.Object, org.httprobot.common.events.CommandEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e) 
	{
		
	};
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.interfaces.IManagerListener#OnManager_BannedAdded(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManager_BannedAdded(Object sender, ManagerEventArgs e) throws InconsistenMessageException, NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.managers.interfaces.IManagerListener#OnParamAdded(java.lang.Object, org.httprobot.core.events.ManagerEventArgs)
	 */
	@Override
	public void OnManager_ParamAdded(Object sender, ManagerEventArgs e) throws InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.datatypes.interfaces.IManagerDataViewListener#OnManagerDataView_Finished(java.lang.Object, org.httprobot.core.events.ProgramDataEventArgs)
	 */
	@Override
	public void OnManagerDataView_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.managers.datatypes.interfaces.IManagerDataViewListener#OnManagerDataView_Error(java.lang.Object, org.httprobot.core.events.ProgramDataEventArgs)
	 */
	@Override
	public void OnManagerDataView_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IDataListener#OnProgramDataChanged(java.lang.Object, org.httprobot.core.events.ProgramDataEventArgs)
	 */
	@Override
	public void OnManagerDataView_Started(Object sender, ManagerEventArgs e)
	{

	}	
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#setDestinationPath(java.lang.String)
	 */
	@Override
	public void setDestinationPath(String destinationPath) 
	{
		this.destinationPath = destinationPath;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#setCliOptions(java.util.EnumSet)
	 */
	@Override
	public void setRuntimeOptions(EnumSet<RuntimeOptions> options) 
	{
		this.debugOptions = options;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#getParameters()
	 */
	@Override
	public Map<String, String> getParameterConstants() throws NotImplementedException,
			ManagerException {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#getParametersBanned()
	 */
	@Override
	public Map<String, String> getParameterBannedWords()
			throws NotImplementedException, ManagerException {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#getTemplateLibrary()
	 */
	@Override
	public TemplateLibrary getTemplateLibrary() throws NotImplementedException,
			ManagerException {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#getDocumentLibrary()
	 */
	@Override
	public DocumentLibrary getDocumentLibrary() throws NotImplementedException,
			ManagerException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
