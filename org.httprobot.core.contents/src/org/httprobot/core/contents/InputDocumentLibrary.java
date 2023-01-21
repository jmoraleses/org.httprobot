/**
 * 
 */
package org.httprobot.core.contents;

import java.util.UUID;
import java.util.Vector;

import javax.xml.bind.annotation.XmlRootElement;
import org.httprobot.common.exceptions.ContentException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.core.contents.events.ContentEventArgs;
import org.httprobot.core.contents.interfaces.IInputDocumentListener;
import org.httprobot.core.contents.solr.InputDocument;

/**
 * @author joan
 *
 */
@XmlRootElement
public class InputDocumentLibrary<TDocumentKeyType, TFieldKeyType> 
	extends InputLibrary<TDocumentKeyType> 
	implements IInputDocumentListener {

	/**
	 * -382419514216421237L
	 */
	private static final long serialVersionUID = -382419514216421237L;

	/**
	 * The document library listeners
	 */
	Vector<IInputDocumentListener> dataDocumentsListeners;

	//Template members
	InputDocument templateDocument;
	FieldLibrary<TFieldKeyType> templateFields;
	
	/**
	 * 
	 */
	public InputDocumentLibrary()
	{
		super();

		//Initialize members
		this.dataDocumentsListeners = new Vector<IInputDocumentListener>();		
		this.templateFields = new FieldLibrary<TFieldKeyType>();
	}
	/**
	 * {@link InputDocument} library class constructor.
	 * @param templateDocument {@link InputDocument} the template document
	 */
	public InputDocumentLibrary(InputDocument templateDocument, FieldLibrary<TFieldKeyType> templateFields) 
	{
		super();
		
		//Initialize members
		this.dataDocumentsListeners = new Vector<IInputDocumentListener>();
		
		//Set inherited data
		this.templateFields = templateFields;
		this.templateDocument = templateDocument;
	}
	/**
	 * Adds listener to current document
	 * @param listener {@link IInputDocumentListener} the listener
	 */
	public final synchronized void addInputDocumentListener(IInputDocumentListener listener)
	{
		this.dataDocumentsListeners.add(listener);
	}
	/**
	 * Removes {@link InputDocument} listener
	 * @param listener the listener
	 */
	public final synchronized void removeInputDocumentListener(IInputDocumentListener listener)
	{
		this.dataDocumentsListeners.remove(listener);
	}
	/**
	 * @return {@link InputDocument} a new copy of the template document.
	 */
	public InputDocument getTemplateDocument()
	{
		return this.templateDocument.deepInputCopy();
	}
	/**
	 * @return the field templates library
	 */
	public FieldLibrary<TFieldKeyType> getFieldLibrary()
	{
		return this.templateFields;
	}
	/**
	 * @param contentTypeRef
	 * @return {@link InputDocument} a new copy of the template sub-document.
	 */
	public InputDocument getTemplateSubdocument(UUID contentTypeRef)
	{
		InputDocument templateDocument = this.templateDocument.findDocumentByUuid(contentTypeRef);
		
		if(templateDocument != null)
		{
			return templateDocument.deepInputCopy();
		}
		else
		{
			return null;
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.solr.DocumentLibrary#put(java.lang.Object, org.httprobot.core.contents.solr.InputDocument)
	 */
	@Override
	public InputDocument put(TDocumentKeyType key, InputDocument value) 
	{		
		return this.inputDocuments.put(key, value);
	}
	/**
	 * Fires {@link IInputDocumentListener} event.
	 * @param e {@link ContentEventArgs} the arguments
	 */
	protected void DocumentLibraryEvent(ContentEventArgs e)
	{
		try 
		{
			for(IInputDocumentListener listener : this.dataDocumentsListeners)
			{
				switch (e.getContentEventType())
				{
					case DOCUMENT_ADDED:					
						listener.OnInputDocument_DocumentAdded(this, e);
						break;
					
					case DOCUMENT_REMOVED:					
						listener.OnInputDocument_DocumentRemoved(this, e);
						break;
					
					case DOCUMENT_COMPLETED:					
						listener.OnInputDocument_DocumentCompleted(this, e);
						break;
						
					case FIELD_ADDED:
						listener.OnInputDocument_FieldAdded(this, e);
						break;
					
					case FIELD_CHANGED:					
						listener.OnInputDocument_FieldAdded(this, e);
						break;
					
					case FIELD_REMOVED:	
						listener.OnInputDocument_FieldAdded(this, e);
						break;
					
					default:
						break;
				}
			}
		} 
		catch (NotImplementedException e1) 
		{
			e1.printStackTrace();
		} 
		catch (ContentException e1) 
		{
			e1.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.interfaces.IInputDocumentListener#OnInputDocument_DocumentAdded(java.lang.Object, org.httprobot.core.contents.events.ContentEventArgs)
	 */
	@Override
	public void OnInputDocument_DocumentAdded(Object sender, ContentEventArgs e)
			throws NotImplementedException, ContentException {
	
		//Fire event to input document library listeners
		DocumentLibraryEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.interfaces.IInputDocumentListener#OnInputDocument_DocumentCompleted(java.lang.Object, org.httprobot.core.contents.events.ContentEventArgs)
	 */
	@Override
	public void OnInputDocument_DocumentCompleted(Object sender, ContentEventArgs e) 
			throws NotImplementedException, ContentException {
		
		//Fire event to input document handler listeners
		DocumentLibraryEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.interfaces.IInputDocumentListener#OnInputDocument_DocumentRemoved(java.lang.Object, org.httprobot.core.contents.events.ContentEventArgs)
	 */
	@Override
	public void OnInputDocument_DocumentRemoved(Object sender, ContentEventArgs e) 
			throws NotImplementedException, ContentException {
		
		//Fire event to input document handler listeners
		DocumentLibraryEvent(e);
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.interfaces.IInputDocumentListener#OnInputDocument_FieldAdded(java.lang.Object, org.httprobot.core.contents.events.ContentEventArgs)
	 */
	@Override
	public void OnInputDocument_FieldAdded(Object sender, ContentEventArgs e)
			throws NotImplementedException, ContentException {
		
		//Fire event to input document handler listeners
		DocumentLibraryEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.interfaces.IInputDocumentListener#OnInputDocument_FieldChanged(java.lang.Object, org.httprobot.core.contents.events.ContentEventArgs)
	 */
	@Override
	public void OnInputDocument_FieldChanged(Object sender, ContentEventArgs e)
			throws NotImplementedException, ContentException {
		
		//Fire event to input document handler listeners
		DocumentLibraryEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.interfaces.IInputDocumentListener#OnInputDocument_FieldRemoved(java.lang.Object, org.httprobot.core.contents.events.ContentEventArgs)
	 */
	@Override
	public void OnInputDocument_FieldRemoved(Object sender, ContentEventArgs e)
			throws NotImplementedException, ContentException {
		
		//Fire event to input document handler listeners
		DocumentLibraryEvent(e);
	}
}
