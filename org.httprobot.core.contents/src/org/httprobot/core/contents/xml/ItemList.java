/**
 * 
 */
package org.httprobot.core.contents.xml;

import java.util.ArrayList;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.common.contents.FieldRef;
import org.httprobot.core.contents.interfaces.IContentImpl;
import org.httprobot.core.contents.xml.fields.ListID;
import org.httprobot.core.contents.xml.fields.ListName;

/**
 * Item list message type class. Inherits {@link Item}.
 * <br>
 * It's {@link IContentImpl}.
 * 
 * @author joan
 *
 */
@XmlRootElement
public class ItemList extends Item implements IContentImpl
{

	/**
	 * -9179051645401246410L
	 */
	private static final long serialVersionUID = -9179051645401246410L;

	/**
	 * 
	 */
	public ItemList() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Item#getName()
	 */
	@Override
	public String getName()
	{
		return "ItemList";
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Item#getDestinationPath()
	 */
	@Override
	public String getDestinationPath() 
	{
		return "./Contents/ItemList.xml";
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
	 * @see org.httprobot.core.contents.Item#getFieldRef()
	 */
	@Override
	public ArrayList<FieldRef> getFieldRef() 
	{
		ArrayList<FieldRef> fieldRefList = super.getFieldRef();
		
		FieldRef fieldRef = new ListID();
		fieldRefList.add(fieldRef);
		
		fieldRef = new ListName();
		fieldRefList.add(fieldRef);
		
		return fieldRefList;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.contents.ContentType#getContentTypeRef()
	 */
	@Override
	public ArrayList<ContentTypeRef> getContentTypeRef() 
	{
		ArrayList<ContentTypeRef> contentTypeRefList = super.getContentTypeRef();
		
		ContentTypeRef contentTypeRef = new ContentTypeRef();
		contentTypeRef.setUuid(UUID.fromString("9786c280-5d16-438b-905c-c5f33954a81b"));
		contentTypeRef.setContentTypeName("item");
		contentTypeRefList.add(contentTypeRef);
		
		return contentTypeRefList;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Item#init()
	 */
	@Override
	public void init() 
	{
		super.init();
		
		this.setUuid(UUID.fromString("19619951-83ec-4697-a457-3d3490feaea7"));
		this.setInherited(true);
	}
}