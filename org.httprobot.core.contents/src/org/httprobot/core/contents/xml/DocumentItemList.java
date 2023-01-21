/**
 * 
 */
package org.httprobot.core.contents.xml;

import java.util.ArrayList;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.ContentTypeRef;

/**
 * @author joan
 *
 */
@XmlRootElement
public class DocumentItemList extends ItemList {

	/**
	 * 6749115861941731843L
	 */
	private static final long serialVersionUID = 6749115861941731843L;

	/**
	 * 
	 */
	public DocumentItemList() 
	{
		super();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Item#getDestinationPath()
	 */
	@Override
	public String getDestinationPath() {
		
		return "./Contents/DocumentList.xml";
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Item#getInheritedType()
	 */
	@Override
	public UUID getInheritedType() {
		
		return super.getUuid();		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Item#getName()
	 */
	@Override
	public String getName() {
		
		return "DocumentList";		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.ItemList#getContentTypeRef()
	 */
	@Override
	public ArrayList<ContentTypeRef> getContentTypeRef() 
	{
		ArrayList<ContentTypeRef> contentTypeRefList = super.getContentTypeRef();
		
		ContentTypeRef contentTypeRef = new ContentTypeRef();		
		contentTypeRef.setUuid(UUID.fromString("9786c280-5d16-438b-905c-c5f33954a81b"));
		contentTypeRef.setContentTypeName("documentItem");
		contentTypeRefList.add(contentTypeRef);
		
		return contentTypeRefList;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.ItemList#init()
	 */
	@Override
	public void init() {
		super.init();
		this.setUuid(UUID.fromString("b52c2a50-1c5f-43a0-bcf9-74eae8537c4c"));
		this.setInherited(true);
	}
}