package org.httprobot.server.datasources;

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
import org.httprobot.core.contents.xml.Document;
import org.httprobot.core.contents.xml.DocumentItem;
import org.httprobot.core.contents.xml.DocumentItemList;
import org.httprobot.core.contents.xml.Image;
import org.httprobot.core.contents.xml.Item;
import org.httprobot.core.contents.xml.ItemList;
import org.httprobot.core.contents.xml.Link;
import org.httprobot.core.contents.xml.fields.Created;
import org.httprobot.core.contents.xml.fields.CreatedBy;
import org.httprobot.core.contents.xml.fields.HttpAddress;
import org.httprobot.core.contents.xml.fields.Modified;
import org.httprobot.core.contents.xml.fields.ModifiedBy;
import org.httprobot.core.contents.xml.fields.StringBase64;
import org.httprobot.core.contents.xml.films.Film;
import org.httprobot.core.contents.xml.films.FilmLink;
import org.httprobot.core.contents.xml.films.FilmLinks;
import org.httprobot.core.contents.xml.films.fields.Cover;
import org.httprobot.core.contents.xml.films.fields.Genres;
import org.httprobot.core.contents.xml.films.fields.Language;
import org.httprobot.core.contents.xml.films.fields.Quality;
import org.httprobot.core.contents.xml.films.fields.Server;
import org.httprobot.core.contents.xml.films.fields.Sinopsis;
import org.httprobot.core.contents.xml.films.fields.Title;
import org.httprobot.core.events.ManagerEventArgs;
import org.httprobot.core.interfaces.IManagerImpl;
import org.httprobot.core.managers.contents.ContentTypeRootManager;
import org.httprobot.core.managers.contents.interfaces.IManagerContentTypeRootListener;
import org.w3c.dom.events.Event;

public class Test 
implements IManagerContentTypeRootListener
{
	/**
	 * -7844537961532733014L
	 */
	private static final long serialVersionUID = -7844537961532733014L;
	
	ContentTypeRoot contentTypeRoot;	
	ContentTypeRootManager contentTypeRootManager;
	
	UUID uuid;
	String destinationPath;
	EnumSet<RuntimeOptions> runtimeOptions;
	CliNamespace cliNamespace;
	
	public Test() 
	{
		this.setRuntimeOptions(RuntimeOptions.DEFAULT_DEBUG);
		this.setDestinationPath("test.xml");
		this.setUuid(UUID.randomUUID());

		contentTypeRoot = new ContentTypeRoot();
		
		contentTypeRoot.getContentType().add(new Item());
		contentTypeRoot.getContentType().add(new Document());
		contentTypeRoot.getContentType().add(new ItemList());
		contentTypeRoot.getContentType().add(new DocumentItem());
		contentTypeRoot.getContentType().add(new DocumentItemList());
		contentTypeRoot.getContentType().add(new Film());
		contentTypeRoot.getContentType().add(new Link());
		contentTypeRoot.getContentType().add(new FilmLink());
		contentTypeRoot.getContentType().add(new FilmLinks());
		contentTypeRoot.getContentType().add(new Image());
		
		contentTypeRoot.getFieldRef().add(new Created());
		contentTypeRoot.getFieldRef().add(new CreatedBy());
		contentTypeRoot.getFieldRef().add(new HttpAddress());
		contentTypeRoot.getFieldRef().add(new Modified());
		contentTypeRoot.getFieldRef().add(new ModifiedBy());
		contentTypeRoot.getFieldRef().add(new StringBase64());
		contentTypeRoot.getFieldRef().add(new Cover());
		contentTypeRoot.getFieldRef().add(new Genres());
		contentTypeRoot.getFieldRef().add(new Sinopsis());
		contentTypeRoot.getFieldRef().add(new Title());
		contentTypeRoot.getFieldRef().add(new Language());
		contentTypeRoot.getFieldRef().add(new Quality());
		contentTypeRoot.getFieldRef().add(new Server());
		
		contentTypeRootManager = new ContentTypeRootManager(this, contentTypeRoot);
		
		contentTypeRootManager.addManagerListener(this);
		contentTypeRootManager.addManagerContentTypeRootListener(this);
		
		contentTypeRootManager.start();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Test();
	}

	@Override
	public Map<String, String> getParameterConstants() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getParameterBannedWords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TemplateLibrary getTemplateLibrary() {
		// TODO Auto-generated method stub
		return null;
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
		return uuid;
	}

	@Override
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	@Override
	public EnumSet<RuntimeOptions> getRuntimeOptions() {
		return this.runtimeOptions;
	}

	@Override
	public void setRuntimeOptions(EnumSet<RuntimeOptions> options) {
		this.runtimeOptions = options;
	}

	@Override
	public CliNamespace getCliNamespace() {
		return this.cliNamespace;
	}

	@Override
	public String getDestinationPath() {
		return this.destinationPath;
	}

	@Override
	public void setDestinationPath(String destinationPath) {
		this.destinationPath = destinationPath;
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
	public void OnManagerContentTypeRoot_Started(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
		if(sender.equals(this.contentTypeRootManager))
		{
			Map<Object, ContentType> contentTypesManagerInputData = new LinkedHashMap<Object, ContentType>();
			
			for(ContentType contentType : this.contentTypeRoot.getContentType())
			{
				contentTypesManagerInputData.put(contentType.getUuid(), contentType);
			}
			
			//Set input data
//			this.contentTypeRootManager.setInputData(contentTypesManagerInputData);
		}
	}

	@Override
	public void OnManagerContentTypeRoot_Finished(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		
	}
	@Override
	public void OnManagerContentTypeRoot_Error(Object sender, ManagerEventArgs e)
			throws ManagerException, NotImplementedException {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IManagerImpl#getContentTypeRoot()
	 */
	@Override
	public ContentTypeRoot getContentTypeRoot() throws NotImplementedException,
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
	 * @see org.httprobot.core.interfaces.IManagerImpl#reset()
	 */
	@Override
	public void reset() {
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



}
