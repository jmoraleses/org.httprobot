package org.httprobot.common.datatypes;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.DataType;
import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.unit.Action;

/**
 * Root document object class. Inherits {@link DataType}.
 * <br>
 * @author Joan  
 */
@XmlRootElement
public class DocumentRoot 
		extends DataType {
	
	/**
	 * -5271904432348146049L
	 */
	private static final long serialVersionUID = -5271904432348146049L;
	
	private Action Action = null;
	private ContentTypeRef contentTypeRef = null;
	private ArrayList<Document> Document = null;
	private FieldRoot Fields = null;
	
	/**
	 * DocumentRoot class constructor.
	 */
	public DocumentRoot() 
	{ 
		super();
	}
	/**
	 * @return the action
	 */
	public Action getAction() {
		return Action;
	}
	/**
	 * @return the contentTypeRef
	 */
	public ContentTypeRef getContentTypeRef() {
		return contentTypeRef;
	}
	/**
	 * Gets the steps.
	 * @return {@link ArrayList} of {@link Document}
	 */
	public ArrayList<Document> getDocument() 
	{
		if(this.Document == null)
		{
			this.Document = new ArrayList<Document>();
			return this.Document;
		}
		else
		{
			return this.Document;
		}
	}
	/**
	 * @return the fields
	 */
	public FieldRoot getFields() {
		return Fields;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.RML#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		super.OnObjectUnmarshalled(sender, e);
		
		setAction(((DocumentRoot)e.getRml()).getAction());
		setContentTypeRef(((DocumentRoot)e.getRml()).getContentTypeRef());
		setFields(((DocumentRoot)e.getRml()).getFields());
		setDocument(((DocumentRoot)e.getRml()).getDocument());
	}
	/**
	 * @param action the action to set
	 */
	@XmlElement
	public void setAction(Action action) {
		Action = action;
	}
	/**
	 * @param contentTypeRef the contentTypeRef to set
	 */
	@XmlElement
	public void setContentTypeRef(ContentTypeRef contentTypeRef) {
		this.contentTypeRef = contentTypeRef;
	}	
	/**
	 * Sets the {@link Document} list.
	 * @param Document {@link ArrayList} of {@link Document}
	 */
	public void setDocument(ArrayList<Document> Document) 
	{
		this.Document = Document;
	}
	/**
	 * @param fields the fields to set
	 */
	@XmlElement
	public void setFields(FieldRoot fields) {
		Fields = fields;
	}	
}