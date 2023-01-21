/**
 * 
 */
package org.httprobot.common.contents;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.Content;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;

/**
 * @author joan
 *
 */
@XmlRootElement
public class ContentTypeRoot extends Content {

	/**
	 * -3470755967862840748L
	 */
	private static final long serialVersionUID = -3470755967862840748L;

	private ArrayList<ContentType> contentType;
	private ArrayList<FieldRef> fieldRef;
	private ArrayList<ContentTypeRef> contentTypeRef;
	/**
	 * ContentTypes message constructor.
	 */
	public ContentTypeRoot() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the contentType
	 */
	public ArrayList<ContentType> getContentType() 
	{
		if(contentType != null)
		{
			return contentType;	
		}
		else
		{
			contentType = new ArrayList<ContentType>();
			return contentType;
		}		
	}
	/**
	 * @return the fieldRef
	 */
	public ArrayList<FieldRef> getFieldRef() 
	{
		if(fieldRef != null)
		{
			return fieldRef;	
		}
		else
		{
			fieldRef = new ArrayList<FieldRef>();
			return fieldRef;
		}
	}
	/**
	 * @return the contentTypeRef
	 */
	public ArrayList<ContentTypeRef> getContentTypeRef() 
	{
		if(contentTypeRef != null)
		{
			return contentTypeRef;
		}
		else
		{
			contentTypeRef = new ArrayList<ContentTypeRef>();
			return contentTypeRef;
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		super.OnObjectUnmarshalled(sender, e);
		this.setFieldRef(ContentTypeRoot.class.cast(e.getRml()).getFieldRef());
		this.setContentType(ContentTypeRoot.class.cast(e.getRml()).getContentType());
		this.setContentTypeRef(ContentTypeRoot.class.cast(e.getRml()).getContentTypeRef());
	}
	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(ArrayList<ContentType> contentType) {
		this.contentType = contentType;
	}
	/**
	 * @param fieldRef the fieldRef to set
	 */
	public void setFieldRef(ArrayList<FieldRef> fieldRef) {
		this.fieldRef = fieldRef;
	}
	/**
	 * @param contentTypeRef the contentTypeRef to set
	 */
	public void setContentTypeRef(ArrayList<ContentTypeRef> contentTypeRef) {
		this.contentTypeRef = contentTypeRef;
	}
}