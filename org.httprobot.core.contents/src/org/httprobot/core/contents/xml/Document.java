package org.httprobot.core.contents.xml;

import java.util.ArrayList;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.common.contents.FieldRef;
import org.httprobot.core.contents.interfaces.IContentImpl;
import org.httprobot.core.contents.xml.fields.DocID;
import org.httprobot.core.contents.xml.fields.DocName;

/**
 * Document message type class. Inherits {@link Item}.
 * <br>
 * It's {@link IContentImpl}.
 * <br>
 * @author joan
 *
 */
@XmlRootElement
public class Document extends Item implements IContentImpl {

	/**
	 * 521394580273683783L
	 */
	private static final long serialVersionUID = 521394580273683783L;
	
	public Document() 
	{
		super();
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Item#getDestinationPath()
	 */
	@Override
	public String getDestinationPath()
	{		
		return "./Contents/Document.xml";
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
		return "Document";
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Item#getFieldRef()
	 */
	@Override
	public ArrayList<FieldRef> getFieldRef() {
	
		ArrayList<FieldRef> fieldRefList = super.getFieldRef();
		
		FieldRef fieldRef = new DocID();
		fieldRefList.add(fieldRef);
		
		fieldRef = new DocName();
		fieldRefList.add(fieldRef);
		
		return fieldRefList;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Item#getContentTypeRef()
	 */
	@Override
	public ArrayList<ContentTypeRef> getContentTypeRef() {
		
		ArrayList<ContentTypeRef> contentTypeRefList = super.getContentTypeRef();
		
		ContentTypeRef contentTypeRef = new ContentTypeRef();
		contentTypeRef.setUuid(UUID.fromString("9786c280-5d16-438b-905c-c5f33954a81b"));
		contentTypeRef.setContentTypeName("documentItem");
		contentTypeRefList.add(contentTypeRef);
		
		contentTypeRef = new ContentTypeRef();
		contentTypeRef.setUuid(UUID.fromString("b52c2a50-1c5f-43a0-bcf9-74eae8537c4c"));
		contentTypeRef.setContentTypeName("documentItemList");
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
		
		this.setUuid(UUID.fromString("d6c75de5-890f-4411-9958-d884b3dd2162"));
		this.setInherited(true);
	}
}