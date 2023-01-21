package org.httprobot.common.datatypes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.DataType;
import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.unit.Action;

/**
 * Document message class. Inherits {@link DataType}.
 * <br>
 * @author Joan
 *
 */
@XmlRootElement
public class Document extends DataType 
{
	/**
	 * -8153155320824643209L
	 */
	private static final long serialVersionUID = -8153155320824643209L;
	
	private Action Action = null;
	private ContentTypeRef contentTypeRef = null;
	private FieldRoot FieldRoot = null;
	private Document Document = null;
	/**
	 * Document message default class constructor.
	 */
	public Document() { }
	/**
	 * Get the action
	 * @return {@link String} the action
	 */
	public Action getAction() {
		return Action;
	}
	/**
	 * @return the docRef
	 */
	public ContentTypeRef getContentTypeRef() {
		return this.contentTypeRef;
	}
	/**
	 * Gets the next step.
	 * @return {@link Document} the step
	 */
	public Document getDocument() 
	{
		return Document;
	}
	/**
	 * Gets the fields.
	 * @return {@link FieldRoot} the fields
	 */
	public FieldRoot getFieldRoot() {
		return FieldRoot;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.RML#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		super.OnObjectUnmarshalled(sender, e);
		
		setDocument(((Document)e.getRml()).getDocument());
		setFieldRoot(((Document)e.getRml()).getFieldRoot());
		setAction(((Document)e.getRml()).getAction());
		setContentTypeRef(((Document)e.getRml()).getContentTypeRef());
	}
	/**
	 * Sets the action
	 * @param Action {@link Action} the action
	 */
	@XmlElement
	public void setAction(Action Action) {
		this.Action = Action;
	}
	/**
	 * @param contentTypeRef the docRef to set
	 */
	@XmlElement
	public void setContentTypeRef(ContentTypeRef contentTypeRef) {
		this.contentTypeRef = contentTypeRef;
	}
	/**
	 * @param Document
	 */
	@XmlElement
	public void setDocument(Document Document) {
		this.Document = Document;
	}
	/**
	 * Sets the fields
	 * @param Fields {@link FieldRoot} the fields
	 */
	@XmlElement
	public void setFieldRoot(FieldRoot Fields) {
		this.FieldRoot = Fields;
	}
}