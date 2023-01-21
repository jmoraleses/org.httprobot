/**
 * 
 */
package org.httprobot.core.contents.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.ContentType;
import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.common.contents.FieldRef;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.core.contents.interfaces.IContentImpl;
import org.httprobot.core.contents.xml.fields.Created;
import org.httprobot.core.contents.xml.fields.CreatedBy;
import org.httprobot.core.contents.xml.fields.Modified;
import org.httprobot.core.contents.xml.fields.ModifiedBy;

/**
 * Item message type class. Inherits {@link ContentType}.
 * <br>
 * It's {@link IContentImpl}.
 * <br> 
 * @author joan
 *
 */
@XmlRootElement
public class Item extends ContentType 
implements IContentImpl
{
	/**
	 * -8746765203644372784L
	 */
	private static final long serialVersionUID = -8746765203644372784L;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IContentImpl item = new Item();
//		item.writeFile();
		System.out.println(item.toString());
	}
	/**
	 * 
	 */
	public Item() 
	{
		super();
		this.init();
		this.writeFile();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.io.MarshalObject#getDestinationPath()
	 */
	@Override
	public String getDestinationPath() {
		return "./Contents/Item.xml";
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.contents.ContentType#getContentTypeRef()
	 */
	@Override
	public ArrayList<ContentTypeRef> getContentTypeRef() {
		
		ArrayList<ContentTypeRef> contentTypeRefList = new ArrayList<ContentTypeRef>();
		
		return contentTypeRefList;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.contents.ContentType#getFieldRef()
	 */
	@Override
	public ArrayList<FieldRef> getFieldRef() {
		
		ArrayList<FieldRef> fieldRefList = new ArrayList<FieldRef>();
		
		FieldRef fieldRef = new Created();
		fieldRefList.add(fieldRef);
		
		fieldRef = new Modified();
		fieldRefList.add(fieldRef);
		
		fieldRef = new CreatedBy();	
		fieldRefList.add(fieldRef);
		
		fieldRef = new ModifiedBy();
		fieldRefList.add(fieldRef);
		
		return fieldRefList;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.contents.ContentType#getInheritedType()
	 */
	@Override
	public UUID getInheritedType() {
		return null;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.contents.ContentType#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Item";
	}
	/* (non-Javadoc)
	 * @see org.httprobot.server.contents.IContentTypeImpl#init()
	 */
	@Override
	public void init()
	{
		this.setUuid(UUID.fromString("10a2fac6-a289-467d-8c3f-93e314328674"));
		this.setInherited(true);
		this.setInheritedType(null);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.server.contents.IContentTypeImpl#writeFile()
	 */
	@Override
	public final void writeFile() {
		
		File file = new File(this.getDestinationPath());	
		
		try 
		{			
			OutputStream os = new FileOutputStream(file);	
			
			try 
			{
				this.marshal(os);
			}
			catch (JAXBException e) 
			{
				e.printStackTrace();
			}
			catch (InconsistenMessageException e) 
			{
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
}
