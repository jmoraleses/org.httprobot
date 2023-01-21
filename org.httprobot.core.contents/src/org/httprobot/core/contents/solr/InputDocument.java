/**
 * 
 */
package org.httprobot.core.contents.solr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.SolrInputField;
import org.httprobot.common.contents.ContentType;
import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.common.contents.FieldRef;
import org.httprobot.common.definitions.Enums.ContentEventType;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.core.contents.events.ContentEventArgs;
import org.httprobot.core.contents.interfaces.IInputDocumentListener;

/**
 * Input document class. Inherits {@link AbstractInputDocument}.
 * <br>
 * Is {@link IInputDocumentListener}.
 * @author joan
 *
 */
@XmlRootElement
public class InputDocument
	extends AbstractInputDocument {

	/**
	 * 7156167863621448896L
	 */
	private static final long serialVersionUID = 7156167863621448896L;
	/**
	 * The content type for current document
	 */
	ContentType contentType;
	/**
	 * The child documents of current one.
	 */
	Map<ContentTypeRef, InputDocument> childDocuments;
	/**
	 * The current fields
	 */
	Map<FieldRef, InputField> inputFields;	
	/**
	 * Input document default class constructor.
	 */
	public InputDocument(ContentType contentType) 
	{
		super();
		
		//Set inherited data
		this.contentType = contentType;
		
		//Initialize members
		this.documentListeners = new Vector<IInputDocumentListener>();
		
		this.childDocuments = new LinkedHashMap<ContentTypeRef, InputDocument>();
		this.inputFields = new LinkedHashMap<FieldRef, InputField>();
	}
	/**
	 * Input document class constructor.
	 * @param fields {@link Map} of {@link SolrInputField}.
	 * @param contentType {@link UUID} the contentTypeRef attached
	 */
	public InputDocument(Map<FieldRef, InputField> inputFields, ContentType contentType)
	{
		super(AbstractInputDocument.toSolrMap(inputFields));
		
		//Initialize members
		this.documentListeners = new Vector<IInputDocumentListener>();
		this.childDocuments = new LinkedHashMap<ContentTypeRef, InputDocument>();
		
		//Set inherited data
		this.contentType = contentType;
		this.inputFields = inputFields;
	}
	/**
	 * Adds new child input document.
	 * @param childDocument {@link InputDocument} the document
	 */
	public void addChildDocument(InputDocument childDocument)
	{
		//Check for available children. If field reference matches with it.
		for(ContentTypeRef childRef : this.contentType.getContentTypeRef())
		{
			//Check if child document content type UUID is valid.
			if(childRef.getUuid().equals(childDocument.getContentType().getUuid()))
			{
				//Add listener to child document
				childDocument.addInputDocumentListener(this);
				
				//Store document.
				this.childDocuments.put(childRef, childDocument);
				super.addChildDocument(childDocument);
				
				//Send event to parent.
				InputDocumentEvent(new ContentEventArgs(childDocument, ContentEventType.DOCUMENT_ADDED));
				return;
			}			
		}
	}
	/* (non-Javadoc)
	 * @see org.apache.solr.common.SolrInputDocument#addChildDocument(org.apache.solr.common.SolrInputDocument)
	 */
	@Override
	public void addChildDocument(SolrInputDocument child) 
	{
		super.addChildDocument(child);
		InputDocumentEvent(new ContentEventArgs(child, ContentEventType.DOCUMENT_ADDED));
	}
	/**
	 * Adds {@link InputDocument} to current document.
	 * If inputDocument content type reference is the same as current 
	 * input document update current document data.
	 * @param inputDocument the input document
	 */
	public void addInputDocument(InputDocument inputDocument)
	{
		//Check if inputDocument refers to the same content type
		if(!this.getContentType().getUuid().equals(inputDocument.getContentType().getUuid()))
		{
			Boolean found = false;
			
			//Check for available children. If field reference matches with it.
			for(ContentTypeRef contentTypeRef : this.contentType.getContentTypeRef())
			{
				if(contentTypeRef.getUuid().equals(inputDocument.getContentType().getUuid()))
				{
					this.addChildDocument(inputDocument);
					found = true;
					break;
				}
			}
			
			//May be it's a grandsome
			if(!found && this.hasChildDocuments())
			{
				for(InputDocument inputDoc : this.getChildInputDocument())
				{
					//Recursive calling until last child is called
					inputDoc.addInputDocument(inputDocument);
				}
			}
		}
		else
		{
			//Otherwise append new fields to this
			Iterator<FieldRef> inputFieldsKeys = this.inputFields.keySet().iterator();
			
			while(inputFieldsKeys.hasNext())
			{
				//Get field reference information
				FieldRef fieldRef = inputFieldsKeys.next();
				
				if(inputDocument.getInputFields().get(fieldRef) != null)
				{
					//Update value for current field
					this.put(fieldRef, inputDocument.getInputFields().get(fieldRef));
				}
			}
		}
	}

	/**
	 * Adds {@link InputField} to current input document.
	 * @param inputField {@link InputField} the input field
	 */
	public void addInputField(InputField inputField)
	{
		Iterator<FieldRef> inputFieldsKeys = this.inputFields.keySet().iterator();
		
		while(inputFieldsKeys.hasNext())
		{
			FieldRef fieldRef = inputFieldsKeys.next();
			
			if(this.inputFields.get(fieldRef).equals(inputField))
			{
				this.put(fieldRef, inputField);		
			}
		}
	}	

	/* (non-Javadoc)
	 * @see org.apache.solr.common.SolrInputDocument#setField(java.lang.String, java.lang.Object, float)
	 */
	@Override
	public void setField(String name, Object value, float boost) {
		// TODO Auto-generated method stub
		super.setField(name, value, boost);
	}
	/* (non-Javadoc)
	 * @see org.apache.solr.common.SolrInputDocument#removeField(java.lang.String)
	 */
	@Override
	public SolrInputField removeField(String name) {

		SolrInputField result = super.removeField(name);
		InputDocumentEvent(new ContentEventArgs(result, ContentEventType.FIELD_REMOVED));
		return result;
	}
	/* (non-Javadoc)
	 * @see org.apache.solr.common.SolrInputDocument#clear()
	 */
	@Override
	public void clear() 
	{
		if(inputFields != null)
		{
			Iterator<FieldRef> fieldRefKeys = this.inputFields.keySet().iterator();
			
			//Iterate through field references and remove matching fields
			while(fieldRefKeys.hasNext())
			{
				FieldRef key = fieldRefKeys.next();
				this.inputFields.remove(key);
			}
		}
		if(this.hasChildDocuments())
		{
			Iterator<ContentTypeRef> contentTypeRefs = this.childDocuments.keySet().iterator();
						
			//Iterate through content type references and remove matching child documents
			while(contentTypeRefs.hasNext())
			{
				ContentTypeRef key = contentTypeRefs.next();				
				this.childDocuments.remove(key);
			}
		}
		super.clear();
	}
	
	/**
	 * @return {@link InputDocument} copy of current document
	 */
	public InputDocument deepInputCopy()
	{
		//Initialize input document fields copy
		Map<FieldRef, InputField> inputFields = new LinkedHashMap<FieldRef, InputField>();
		
		//Get current document fields keys
		Iterator<FieldRef> inputEntries = this.inputFields.keySet().iterator();

		while (inputEntries.hasNext()) 
		{
			//Get the key
			FieldRef fieldRef = inputEntries.next();
			
			//Get the input field
			InputField inputField = this.inputFields.get(fieldRef);

			//Store to dictionary
			inputFields.put(fieldRef, inputField.deepInputCopy());
		}
		
		//Initialize document copy with current fields and content type
		InputDocument clone = new InputDocument(inputFields, this.contentType);

		//Set document boost
		clone.setDocumentBoost(this.getDocumentBoost());

		//Check if has child documents
		if (this.getChildDocuments() != null) 
		{			
			//Recursive calling for each input document child
			for (InputDocument child : this.getChildInputDocument()) 
			{
				//Iterate through all possible content types
				for(ContentTypeRef contentTypeRef : this.contentType.getContentTypeRef())
				{
					//Check if child content type reference ID is valid.
					if(contentTypeRef.getUuid().equals(child.getContentType().getUuid()))
					{
						//Add children
						clone.addChildDocument(child.deepInputCopy());
						break;	
					}					
				}
			}			
		}

		return clone;
	}
	/**
	 * @param contentTypeRef
	 * @return the first match of {@link InputDocument} child.
	 */
	public InputDocument findDocumentByUuid(UUID contentTypeRef)
	{
		return this.findDocumentByUUID(this, contentTypeRef);
	}
	/**
	 * Find a field by ID. Also search inside child documents.
	 * @param fieldUuid the {@link UUID} field
	 * @return the first match of {@link InputField} the input field
	 */
	public InputField findFieldByUuid(UUID fieldUuid)
	{
		Iterator<FieldRef> fieldRefs = this.inputFields.keySet().iterator();
		
		while(fieldRefs.hasNext())
		{
			FieldRef fieldRef = fieldRefs.next();
			
			//If 
			if(fieldUuid.equals(fieldRef.getUuid()))
			{
				return this.inputFields.get(fieldRef);
			}
		}
		//Check if there's child documents
		if(this.getChildDocuments() != null)
		{
			//Initialize matching field
			InputField inputField = null;
			
			for(InputDocument inputDocument : this.getChildInputDocument())
			{
				//Recursive calling on child documents
				inputField = findFieldByUuid(inputDocument, fieldUuid);
				
				if(inputField == null)
				{
					continue;
				}
				else
				{
					return inputField;
				}
			}
		}
		return null;
	}
	/**
	 * The content type reference of current InputDocument.
	 * @return {@link UUID} the reference
	 */
	public ContentType getContentType()
	{
		return this.contentType;
	}
	/**
	 * @return the list of child input documents
	 */
	public List<InputDocument> getChildInputDocument()
	{
		List<InputDocument> list = new ArrayList<InputDocument>();
		
		for(SolrInputDocument solrInputDoc : this.getChildDocuments())
		{
			if(solrInputDoc instanceof InputDocument)
			{
				list.add(InputDocument.class.cast(solrInputDoc));
			}
		}
		
		return list;
	}
	/**
	 * @return Dictionary of {@link InputField} with {@link FieldRef} as primary key.
	 */
	public Map<FieldRef, InputField> getInputFields()
	{
		return this.inputFields;
	}
	
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
	}
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
	}	
	/**
	 * Adds field for selected reference
	 * @param key {@link FieldRef} the field reference
	 * @param value {@link InputField} the input field
	 * @return the inserted field. Null if operation fails. 
	 */
	public InputField put(FieldRef key, InputField value)
	{
		InputField result = null;
		
		//Check if input field exists
		if(this.inputFields.get(key) != null)
		{
			SolrInputField solrResult = this.put(key.getName(), value);
			
			if(solrResult != null && solrResult.getValue() != null)
			{
				if(solrResult instanceof InputField)
				{
					result = InputField.class.cast(solrResult);
					this.inputFields.put(key, result);
				}
				InputDocumentEvent(new ContentEventArgs(this, ContentEventType.FIELD_CHANGED));
			}
		}
		else 
		{
			SolrInputField solrResult = this.put(key.getName(), value);
			
			if(solrResult != null && solrResult.getValue() != null)
			{
				if(solrResult instanceof InputField)
				{
					result = InputField.class.cast(solrResult);
					this.inputFields.put(key, result);
				}
				InputDocumentEvent(new ContentEventArgs(this, ContentEventType.FIELD_ADDED));
			}
		}
		
		return result;
	}
	/* (non-Javadoc)
	 * @see org.apache.solr.common.SolrInputDocument#put(java.lang.String, org.apache.solr.common.SolrInputField)
	 */
	@Override
	public SolrInputField put(String key, SolrInputField value) 
	{
		SolrInputField result = null;
		
		//Check if current field exists
		if(super.get(key) != null)
		{
			//Call super method
			result = super.put(key, value);
			
			if(result != null && result.getValue() != null)
			{
				//Fire event
				InputDocumentEvent(new ContentEventArgs(this, ContentEventType.FIELD_CHANGED));
			}
		}
		else 
		{
			result = super.put(key, value);
			
			if(result != null && result.getValue() != null)
			{
				//Fire event
				InputDocumentEvent(new ContentEventArgs(this, ContentEventType.FIELD_ADDED));
			}
		}
		
		return result;
	}
	/**
	 * Removes a field of current document.
	 * @param key {@link FieldRef} the field reference
	 * @return the deleted {@link InputField} from current document
	 */
	public InputField remove(FieldRef key)
	{
		InputField inputField = this.inputFields.remove(key);
		
		if(inputField != null)
		{
			SolrInputField solrInputField = this.remove(inputField.getName());
			
			if(solrInputField != null)
			{
				return inputField;
			}
		}
		return null;
	}
	/* (non-Javadoc)
	 * @see org.apache.solr.common.SolrInputDocument#remove(java.lang.Object)
	 */
	@Override
	public SolrInputField remove(Object key) 
	{
		SolrInputField result = super.remove(key);
		
		if(result != null)
		{
			//Send event to parent
			InputDocumentEvent(new ContentEventArgs(this, ContentEventType.FIELD_REMOVED));
		}
		
		return result;
	}
	
	/**
	 * Removes child input document
	 * @param childInputDocument {@link InputDocument} the document
	 */
	public void removeChildDocument(InputDocument childInputDocument)
	{
		if(super.getChildDocuments().remove(childInputDocument))
		{
			Iterator<ContentTypeRef> docKeys = this.childDocuments.keySet().iterator();
			
			while(docKeys.hasNext())
			{				
				ContentTypeRef key = docKeys.next();
				
				if(key.getUuid().equals(childInputDocument.getContentType().getUuid()))
				{
					this.childDocuments.remove(docKeys);	
				}				
			}		
			
			//If operation success send event to parent.
			InputDocumentEvent(new ContentEventArgs(this, ContentEventType.DOCUMENT_REMOVED));
		}
	}

	public void removeInputField(InputField inputField)
	{
		this.remove(inputField);
	}
	/**
	 * @param contentType the UUID to set
	 */
	@XmlAttribute
	public void setContentType(ContentType contentType)
	{
		this.contentType = contentType;
	}
	/**
	 * Sets the field value by field reference.
	 * @param fieldRef {@link FieldRef} the field reference
	 * @param value the value to set
	 */
	public void setInputField(FieldRef fieldRef, Object value)
	{
		InputField field = new InputField(fieldRef);
		this.addInputField(field);
	}
	private InputDocument findDocumentByUUID(InputDocument currentDocument, UUID contentTypeRefID)
	{
		if(!contentTypeRefID.equals(this.getContentType().getUuid()))
		{
			if(currentDocument.hasChildDocuments())
			{
				Iterator<ContentTypeRef> contentTypeRefKeys = this.childDocuments.keySet().iterator();
				
				while(contentTypeRefKeys.hasNext())
				{
					ContentTypeRef contentTypeRef = contentTypeRefKeys.next();				
					InputDocument inputDocument = this.childDocuments.get(contentTypeRef);
					
					if(contentTypeRef.getUuid().equals(contentTypeRefID))
					{
						return inputDocument;
					}
				}
			}	
		}
		else
		{
			return this;	
		}
		return null;
	}
	/**
	 * @param fieldUuid the ID of the field you're looking for
	 * @return the {@link InputField}
	 */
	private InputField findFieldByUuid(InputDocument inputDocument, UUID fieldUuid)
	{		
		Iterator<FieldRef> fieldRefs = this.inputFields.keySet().iterator();
		
		while(fieldRefs.hasNext())
		{
			FieldRef fieldRef = fieldRefs.next();
			
			//If 
			if(fieldUuid.equals(fieldRef.getUuid()))
			{
				return this.inputFields.get(fieldRef);
			}
		}
		//Check if there's child documents
		if(inputDocument.getChildDocuments() != null)
		{
			//Declare matching field
			InputField inputField = null;
			
			for(SolrInputDocument solrInputDocument : inputDocument.getChildDocuments())
			{
				//Iterate through child documents
				if(solrInputDocument instanceof InputDocument)
				{					
					InputDocument childDocument = InputDocument.class.cast(solrInputDocument);
					
					//Recursive calling on child documents
					inputField = findFieldByUuid(childDocument, fieldUuid);
					
					if(inputField == null)
					{
						continue;
					}
					else
					{
						return inputField;
					}
				}
			}
		}
		return null;
	}
	

	
}