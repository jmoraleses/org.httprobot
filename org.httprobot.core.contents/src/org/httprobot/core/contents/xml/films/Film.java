/**
 * 
 */
package org.httprobot.core.contents.xml.films;

import java.util.ArrayList;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.common.contents.FieldRef;
import org.httprobot.core.contents.xml.Document;
import org.httprobot.core.contents.xml.films.fields.Cover;
import org.httprobot.core.contents.xml.films.fields.Genres;
import org.httprobot.core.contents.xml.films.fields.Sinopsis;
import org.httprobot.core.contents.xml.films.fields.Title;

/**
 * Film content type message. Inherits {@link Document}.
 * <br>
 * @author joan
 *
 */
@XmlRootElement
public final class Film extends Document {

	/**
	 * -2832835285337366389L
	 */
	private static final long serialVersionUID = -2832835285337366389L;
	/**
	 * Film content type message class constructor
	 */
	public Film() {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Item#getContentTypeRef()
	 */
	@Override
	public ArrayList<ContentTypeRef> getContentTypeRef() 
	{
		ArrayList<ContentTypeRef> contentTypeRefList = super.getContentTypeRef();
		
		ContentTypeRef contentTypeRef = new ContentTypeRef();
		contentTypeRef.setUuid(UUID.fromString("276c2242-f100-4cad-9f36-6ba2611fe9ec"));
		contentTypeRef.setContentTypeName("videoLinks");
		contentTypeRefList.add(contentTypeRef);
		
		return contentTypeRefList;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Item#getDestinationPath()
	 */
	@Override
	public String getDestinationPath() 
	{
		return "./Contents/Films/Film.xml";
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Item#getFieldRef()
	 */
	@Override
	public ArrayList<FieldRef> getFieldRef() 
	{
		ArrayList<FieldRef> fieldRefList = super.getFieldRef();
		
		FieldRef fieldRef = new Title();
		fieldRefList.add(fieldRef);
		
		fieldRef = new Genres();
		fieldRefList.add(fieldRef);
		
		fieldRef = new Sinopsis();
		fieldRefList.add(fieldRef);
		
		fieldRef = new Cover();
		fieldRefList.add(fieldRef);
		
		return fieldRefList;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Item#getInheritedType()
	 */
	@Override
	public UUID getInheritedType()
	{
		return super.getUuid();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Item#getName()
	 */
	@Override
	public String getName() 
	{
		return "Film";
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Document#init()
	 */
	@Override
	public void init() 
	{
		super.init();
		
		this.setUuid(UUID.fromString("5e30577c-a283-4d2b-8c5d-8d56cbbc62fe"));
		this.setInherited(true);
	}
}