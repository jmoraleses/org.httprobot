package org.httprobot.client.ws;

import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.httprobot.common.contents.ContentType;
import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.common.definitions.Enums.CliNamespace;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ManagerException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.core.contents.DocumentLibrary;
import org.httprobot.core.contents.TemplateLibrary;
import org.httprobot.core.contents.solr.InputDocument;
import org.httprobot.core.contents.xml.Item;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.contents.ContentTypeManager;
import org.httprobot.core.managers.contents.interfaces.IManagerContentTypeListener;
import org.w3c.dom.events.Event;

public class Fooo implements IManagerContentTypeListener 
{

	/**
	 * 6926551555524206639L
	 */
	private static final long serialVersionUID = 6926551555524206639L;
	Item item;
	ContentTypeManager itemManager;
	EnumSet<RuntimeOptions> runtimeOptions = RuntimeOptions.FULL_DEBUG;
	
	public Fooo()
	{
		this.item = new Item();
		this.itemManager = new ContentTypeManager(this, item);
		this.itemManager.addManagerContentTypeListener(this);
		this.itemManager.addManagerListener(this);
		this.itemManager.start();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		new Fooo();
	}

	@Override
	public void OnManager_ParamAdded(Object sender, ManagerEventArgs e)
			throws InconsistenMessageException, NotImplementedException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void OnManager_BannedAdded(Object sender, ManagerEventArgs e)
			throws InconsistenMessageException, NotImplementedException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public UUID getUuid() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setUuid(UUID uuid) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public EnumSet<RuntimeOptions> getRuntimeOptions() {
		// TODO Auto-generated method stub
		return this.runtimeOptions;
	}
	@Override
	public void setRuntimeOptions(EnumSet<RuntimeOptions> options) {
		this.runtimeOptions = options;
	}
	@Override
	public CliNamespace getCliNamespace() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getDestinationPath() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setDestinationPath(String destinationPath) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void handleEvent(Event evt) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ContentTypeRoot getContentTypeRoot() throws NotImplementedException,
			ManagerException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void OnManagerContentType_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		System.out.println("\nOnManagerContentType_Started");
		
		Map<Object, ContentType> contentTypeManagerInputData = new LinkedHashMap<Object, ContentType>();
		
		contentTypeManagerInputData.put(this.item.getUuid(), this.item);
		
	}
	@Override
	public void OnManagerContentType_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		System.out.println("\nOnManagerContentType_Finished");
		
//		InputLibrary idh = this.itemManager.getTemplateLibrary(); 
		
	}
	@Override
	public void OnManagerContentType_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		System.out.println("\nOnManagerContentType_Error");
	}
	public Map<ContentType, InputDocument> getDocumentTemplates() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<String, String> getParameterConstants() throws NotImplementedException,
			ManagerException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<String, String> getParameterBannedWords()
			throws NotImplementedException, ManagerException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public TemplateLibrary getTemplateLibrary() throws NotImplementedException,
			ManagerException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public DocumentLibrary getDocumentLibrary() throws NotImplementedException,
			ManagerException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public IManagerImpl next() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addChildManager(IManagerImpl manager)
			throws NotImplementedException, ManagerException {
		// TODO Auto-generated method stub
		
	}



}
