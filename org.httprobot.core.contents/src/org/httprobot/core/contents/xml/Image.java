/**
 * 
 */
package org.httprobot.core.contents.xml;

import java.util.ArrayList;
import java.util.UUID;

import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.contents.FieldRef;
import org.httprobot.core.contents.interfaces.IContentImpl;
import org.httprobot.core.contents.xml.fields.StringBase64;

/**
 * @author joan
 *
 */
@XmlTransient
public class Image extends DocumentItem implements IContentImpl{

	/**
	 * 3955171103543770479L
	 */
	private static final long serialVersionUID = 3955171103543770479L;
	
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Item#getDestinationPath()
	 */
	@Override
	public String getDestinationPath() {
		
		return "./Contents/Image.xml";
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

		return "Image";
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Item#getFieldRef()
	 */
	@Override
	public ArrayList<FieldRef> getFieldRef() {
		
		ArrayList<FieldRef> fieldRefList = super.getFieldRef();
		StringBase64 stringBase64 = new StringBase64();
		fieldRefList.add(stringBase64);
		
		return fieldRefList;
	}
	/**
	 * 
	 */
	public Image() {
		super();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Item#init()
	 */
	@Override
	public void init() 
	{
		super.init();
		this.setUuid(UUID.fromString("c2f97d8c-222f-4550-9973-b34fd9ca1a46"));
		this.setInherited(true);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID());
		System.out.println(UUID.randomUUID());
		System.out.println(UUID.randomUUID());
		System.out.println(UUID.randomUUID());
		System.out.println(UUID.randomUUID());
		System.out.println(UUID.randomUUID());
		System.out.println(UUID.randomUUID());
		System.out.println(UUID.randomUUID());
		
//		Image image = new Image();
//		image.init();
//		image.writeFile();
	}
}
