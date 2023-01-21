/**
 * 
 */
package org.httprobot.core.contents.solr;

import java.util.EnumSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.SolrInputField;
import org.httprobot.common.contents.FieldRef;
import org.httprobot.common.definitions.Enums.CliNamespace;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.exceptions.ContentException;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IListener;
import org.httprobot.common.io.adapters.RuntimeOptionsAdapter;
import org.httprobot.core.contents.events.ContentEventArgs;
import org.httprobot.core.contents.interfaces.IInputDocumentListener;
import org.w3c.dom.events.Event;

/**
 * Abstract input document. Inherits {@link SolrInputDocument}.
 * This class implements {@link IListener} members.
 * @author joan
 *
 */
@XmlTransient
public abstract class AbstractInputDocument 
	extends SolrInputDocument 
	implements IInputDocumentListener, IListener {

	/**
	 * -1344150617130811793L
	 */
	private static final long serialVersionUID = -1344150617130811793L;
	
	/**
	 * The document listeners.
	 */
	Vector<IInputDocumentListener> documentListeners;
	
	/**
	 * Converts system map to SOLR data.
	 * @param fields Dictionary<FieldRef, InputField>
	 * @return Map<String, SolrInputField>
	 */
	public static Map<String, SolrInputField> toSolrMap(Map<FieldRef, InputField> fields)
	{
		Iterator<FieldRef> fieldsRefs = fields.keySet().iterator();
		Map<String, SolrInputField> solrFields = new Hashtable<String, SolrInputField>();
		
		while(fieldsRefs.hasNext())
		{
			FieldRef fieldRef = fieldsRefs.next();
			InputField inputField = fields.get(fieldRef);
			
			solrFields.put(inputField.getName(), inputField);
		}
	
		return solrFields;
	}
	
	//Command line members
	final CliNamespace cliNamespace = CliNamespace.DATA;
	protected String destinationPath;	
	protected EnumSet<RuntimeOptions> runtimeOptions;
	
	//RML members
	protected UUID uuid;
	
	/**
	 * AbstractInputDocument default class constructor.
	 */
	public AbstractInputDocument() {
		super();
	
		initializeAbstractInputDocument();
	}
	/**
	 * AbstractInputDocument class constructor.
	 * @param fields the {@link SolrInputField}s
	 */
	public AbstractInputDocument(Map<String, SolrInputField> fields) {
		super(fields);
		
		//Initialize IListener implementation members
		initializeAbstractInputDocument();
	}
	/**
	 * Adds listener to document.
	 * @param listener
	 */
	public final synchronized void addInputDocumentListener(IInputDocumentListener listener)
	{
		this.documentListeners.add(listener);
	}
	/**
	 * Removes listener from document.
	 * @param listener {@link IInputDocumentListener}
	 */
	public final synchronized void removeDocumentListener(IInputDocumentListener listener)
	{
		this.documentListeners.remove(listener);
	}
	/**
	 * Initializes {@link IListener} implementation members
	 */
	private void initializeAbstractInputDocument()
	{
		this.setUuid(null);
		this.setRuntimeOptions(RuntimeOptions.DEFAULT_DEBUG);
		this.setDestinationPath(null);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getCliNamespace()
	 */
	@Override
	public CliNamespace getCliNamespace() {
		return this.cliNamespace;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getDestinationPath()
	 */
	@Override
	public String getDestinationPath() {
		return this.destinationPath;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getRuntimeOptions()
	 */
	@Override
	public EnumSet<RuntimeOptions> getRuntimeOptions() {
		return this.runtimeOptions;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getUuid()
	 */
	@Override
	public UUID getUuid() {
		// TODO Auto-generated method stub
		return this.uuid;
	}
	/* (non-Javadoc)
	 * @see org.w3c.dom.events.EventListener#handleEvent(org.w3c.dom.events.Event)
	 */
	@Override
	public void handleEvent(Event evt) {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public abstract void OnCommandInput(Object sender, CliEventArgs e)
			throws InconsistenMessageException ;
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public abstract void OnCommandOutput(Object sender, CliEventArgs e)
			throws InconsistenMessageException ;
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#setDestinationPath(java.lang.String)
	 */
	@Override
	@XmlAttribute
	public void setDestinationPath(String destinationPath)
	{
		this.destinationPath = destinationPath;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#setRuntimeOptions(java.util.EnumSet)
	 */
	@Override
	@XmlJavaTypeAdapter(value = RuntimeOptionsAdapter.class)
	public void setRuntimeOptions(EnumSet<RuntimeOptions> options) 
	{
		this.runtimeOptions = options;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#setUuid(java.util.UUID)
	 */
	@Override
	@XmlAttribute
	public void setUuid(UUID uuid) 
	{
		if(uuid != null)
		{
			this.uuid = uuid;
		}
		else
		{
			this.uuid = UUID.randomUUID();
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.interfaces.IInputDocumentListener#OnInputDocument_DocumentAdded(java.lang.Object, org.httprobot.core.contents.events.ContentEventArgs)
	 */
	@Override
	public void OnInputDocument_DocumentAdded(Object sender, ContentEventArgs e)
			throws NotImplementedException, ContentException {
		
		InputDocumentEvent(new ContentEventArgs(e, e.getContentEventType()));
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.interfaces.IInputDocumentListener#OnInputDocument_DocumentCompleted(java.lang.Object, org.httprobot.core.contents.events.ContentEventArgs)
	 */
	@Override
	public void OnInputDocument_DocumentCompleted(Object sender, ContentEventArgs e) 
			throws NotImplementedException, ContentException {
		
		InputDocumentEvent(new ContentEventArgs(this, e.getContentEventType()));
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.interfaces.IInputDocumentListener#OnInputDocument_DocumentRemoved(java.lang.Object, org.httprobot.core.contents.events.ContentEventArgs)
	 */
	@Override
	public void OnInputDocument_DocumentRemoved(Object sender, ContentEventArgs e) 
			throws NotImplementedException, ContentException {
		
		InputDocumentEvent(new ContentEventArgs(this, e.getContentEventType()));
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.interfaces.IInputDocumentListener#OnInputDocument_FieldAdded(java.lang.Object, org.httprobot.core.contents.events.ContentEventArgs)
	 */
	@Override
	public void OnInputDocument_FieldAdded(Object sender, ContentEventArgs e)
			throws NotImplementedException, ContentException {
		
		InputDocumentEvent(new ContentEventArgs(this, e.getContentEventType()));
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.interfaces.IInputDocumentListener#OnInputDocument_FieldChanged(java.lang.Object, org.httprobot.core.contents.events.ContentEventArgs)
	 */
	@Override
	public void OnInputDocument_FieldChanged(Object sender, ContentEventArgs e)
			throws NotImplementedException, ContentException {
		
		InputDocumentEvent(new ContentEventArgs(this, e.getContentEventType()));
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.interfaces.IInputDocumentListener#OnInputDocument_FieldRemoved(java.lang.Object, org.httprobot.core.contents.events.ContentEventArgs)
	 */
	@Override
	public void OnInputDocument_FieldRemoved(Object sender, ContentEventArgs e)
			throws NotImplementedException, ContentException {
		
		InputDocumentEvent(new ContentEventArgs(this, e.getContentEventType()));
	}
	/**
	 * Fires event to {@link IInputDocumentListener} listeners
	 * @param e {@link ContentEventArgs} the arguments
	 */
	protected void InputDocumentEvent(ContentEventArgs e)
	{
		try 
		{
			for(IInputDocumentListener listener : this.documentListeners)
			{
				switch (e.getContentEventType()) 
				{
					case FIELD_ADDED:					
						listener.OnInputDocument_FieldAdded(this, e);
						break;
					case FIELD_CHANGED:
						listener.OnInputDocument_FieldChanged(this, e);
						break;
					case FIELD_REMOVED:
						listener.OnInputDocument_FieldRemoved(this, e);
						break;
					case DOCUMENT_ADDED:
						listener.OnInputDocument_DocumentAdded(this, e);
						break;
					case DOCUMENT_REMOVED:
						listener.OnInputDocument_DocumentRemoved(this, e);
						break;
					case DOCUMENT_COMPLETED:
						listener.OnInputDocument_DocumentCompleted(this, e);
						break;
					default:
						break;
				}
			}
		}
		catch (NotImplementedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ContentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}