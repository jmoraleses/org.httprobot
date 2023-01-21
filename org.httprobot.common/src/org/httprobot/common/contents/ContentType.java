/**
 * 
 */
package org.httprobot.common.contents;

import java.util.ArrayList;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
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
public class ContentType 
		extends Content {

	/**
	 * 9142311370797421258L
	 */
	private static final long serialVersionUID = 9142311370797421258L;
	private ArrayList<FieldRef> fieldRef;
	private ArrayList<ContentTypeRef> contentTypeRef;
	private UUID inheritedType;
	private String name;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	@XmlAttribute
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the fieldRef
	 */
	public ArrayList<FieldRef> getFieldRef() 
	{
		if(this.fieldRef != null)
		{
			return this.fieldRef;
		}
		else
		{
			this.fieldRef = new ArrayList<FieldRef>();
			return this.fieldRef;
		}
	}
	/**
	 * @param fieldRef the fieldRef to set
	 */
	@XmlElement
	public void setFieldRef(ArrayList<FieldRef> fieldRef) 
	{
		this.fieldRef = fieldRef;
	}
	/**
	 * @return the contentType
	 */
	public ArrayList<ContentTypeRef> getContentTypeRef() 
	{
		if(this.contentTypeRef != null)
		{
			return this.contentTypeRef;
		}
		else
		{
			this.contentTypeRef = new ArrayList<ContentTypeRef>();
			return this.contentTypeRef;
		}
	}
	/**
	 * @param contentType the contentType to set
	 */
	@XmlElement
	public void setContentTypeRef(ArrayList<ContentTypeRef> contentType) 
	{
		this.contentTypeRef = contentType;
	}
	/**
	 * @param inheritedType the inheritedType to set
	 */
	@XmlAttribute
	public void setInheritedType(UUID inheritedType)
	{
		this.inheritedType = inheritedType;
	}
	/**
	 * @return the inheritedType
	 */
	public UUID getInheritedType() 
	{
		return inheritedType;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		super.OnObjectUnmarshalled(sender, e);
		this.setName(((ContentType)e.getRml()).getName());
		this.setFieldRef(((ContentType)e.getRml()).getFieldRef());
		this.setContentTypeRef(((ContentType)e.getRml()).getContentTypeRef());
		this.setInheritedType(((ContentType)e.getRml()).getInheritedType());
	}
}