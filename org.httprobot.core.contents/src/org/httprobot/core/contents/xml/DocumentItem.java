/**
 * 
 */
package org.httprobot.core.contents.xml;

import java.util.ArrayList;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.FieldRef;
import org.httprobot.core.contents.interfaces.IContentImpl;
import org.httprobot.core.contents.xml.fields.DocID;
import org.httprobot.core.contents.xml.fields.DocName;

/**
 * @author joan
 *
 */
@XmlRootElement
public class DocumentItem extends Item implements IContentImpl {

	/**
	 * -9058819283773769562L
	 */
	private static final long serialVersionUID = -9058819283773769562L;

	/**
	 * 
	 */
	public DocumentItem() {
		super();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Item#getDestinationPath()
	 */
	@Override
	public String getDestinationPath() {
		return "./Contents/DocumentItem.xml";
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
		return "DocumentItem";
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.contents.ContentType#getFieldRef()
	 */
	@Override
	public ArrayList<FieldRef> getFieldRef() 
	{
		ArrayList<FieldRef> fieldRefList = super.getFieldRef();
		
		FieldRef fieldRef = new DocID();
		fieldRefList.add(fieldRef);
		
		fieldRef = new DocName();
		fieldRefList.add(fieldRef);
		
		return fieldRefList;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Document#init()
	 */
	@Override
	public void init()
	{		
		super.init();
		
		this.setUuid(UUID.fromString("9786c280-5d16-438b-905c-c5f33954a81b"));
		this.setInherited(true);
	}	
}