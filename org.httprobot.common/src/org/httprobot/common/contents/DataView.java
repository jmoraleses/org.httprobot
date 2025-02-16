package org.httprobot.common.contents;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.RML;
import org.httprobot.common.Content;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;

/**
 * @author Joan
 * DataView object class. Inherits {@link RML}.
 */
@XmlRootElement
public class DataView extends Content
{
	/**
	 * -3196201492069183463L
	 */
	private static final long serialVersionUID = -3196201492069183463L;
	
	private ContentTypeRef contentTypeRef;
	private String dbSchema;
	private ArrayList<FieldRef> FieldRef;
	/**
	 * Gets the name of the working database.
	 * @return {@link String} the schema
	 */
	public String getDbSchema() {
		return dbSchema;
	}
	/**
	 * Gets the FieldRef
	 * @return {@link ArrayList} of {@link FieldRef}
	 */
	public ArrayList<FieldRef> getFieldRef() 
	{
		if(FieldRef == null)
		{
			return this.FieldRef;
		}
		else
		{
			return this.FieldRef;
		}
	}
	/**
	 * Sets the name of the working database.
	 * @param dbSchema
	 */
	@XmlAttribute
	public void setDbSchema(String dbSchema) {
		this.dbSchema = dbSchema;
	}
	/**
	 * Sets the FieldRef
	 * @param FieldRef {@link ArrayList} of {@link FieldRef}
	 */
	@XmlElement
	public void setFieldRef(ArrayList<FieldRef> FieldRef) {
		this.FieldRef = FieldRef;
	}


	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
		this.setDbSchema(((DataView)e.getRml()).getDbSchema());
		this.setFieldRef(((DataView)e.getRml()).getFieldRef());
	}
	/**
	 * @param contentTypeRef the contentTypeRef to set
	 */
	@XmlElement
	public void setContentTypeRef(ContentTypeRef contentTypeRef) {
		this.contentTypeRef = contentTypeRef;
	}
	/**
	 * @return the contentTypeRef
	 */
	public ContentTypeRef getContentTypeRef() {
		return contentTypeRef;
	}
}