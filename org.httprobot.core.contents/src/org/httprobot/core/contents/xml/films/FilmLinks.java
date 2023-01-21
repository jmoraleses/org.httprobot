/**
 * 
 */
package org.httprobot.core.contents.xml.films;

import java.util.ArrayList;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.core.contents.xml.ItemList;

/**
 * @author joan
 *
 */
@XmlRootElement
public class FilmLinks extends ItemList {

	/**
	 * 2118291669187033207L
	 */
	private static final long serialVersionUID = 2118291669187033207L;
	/**
	 * 
	 */
	public FilmLinks() {
		super();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.ItemList#getContentTypeRef()
	 */
	@Override
	public ArrayList<ContentTypeRef> getContentTypeRef() 
	{
		ArrayList<ContentTypeRef> contentTypeRefList = super.getContentTypeRef();
		
		ContentTypeRef contentTypeRef = new ContentTypeRef();
		contentTypeRef.setContentTypeName("FilmLinks");
		contentTypeRef.setUuid(UUID.fromString("0c8b7919-75cb-445c-be1f-89c72a90f739"));
		contentTypeRefList.add(contentTypeRef);
		
		return contentTypeRefList;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.ItemList#getName()
	 */
	@Override
	public String getName() 
	{
		return "FilmLinks";
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.ItemList#getInheritedType()
	 */
	@Override
	public UUID getInheritedType() {
		return super.getUuid();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.ItemList#getDestinationPath()
	 */
	@Override
	public String getDestinationPath() {
		// TODO Auto-generated method stub
		return "./Contents/Films/FilmLinks.xml";
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.ItemList#init()
	 */
	@Override
	public void init() 
	{
		super.init();
		
		this.setUuid(UUID.fromString("276c2242-f100-4cad-9f36-6ba2611fe9ec"));
		this.setInherited(true);
	}
}