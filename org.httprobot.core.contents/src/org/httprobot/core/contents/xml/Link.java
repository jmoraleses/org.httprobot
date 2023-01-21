/**
 * 
 */
package org.httprobot.core.contents.xml;

import java.util.ArrayList;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.FieldRef;
import org.httprobot.core.contents.interfaces.IContentImpl;
import org.httprobot.core.contents.xml.fields.HttpAddress;

/**
 * @author joan
 *
 */
@XmlRootElement
public class Link extends DocumentItem 
implements IContentImpl
{

	/**
	 * -8243423882235701900L
	 */
	private static final long serialVersionUID = -8243423882235701900L;
	
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Item#getDestinationPath()
	 */
	@Override
	public String getDestinationPath() {
		
		return "./Contents/Link.xml";
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

		return "Link";
	}
	@Override
	public void init()
	{
		super.init();
		
		this.setUuid(UUID.fromString("5b5f13d4-10f2-4808-b424-4771613eb657"));
		this.setInherited(true);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Item#getFieldRef()
	 */
	@Override
	public ArrayList<FieldRef> getFieldRef() {
		
		ArrayList<FieldRef> fieldRefList = super.getFieldRef();
		
		FieldRef fieldRef = new HttpAddress();
		fieldRefList.add(fieldRef);
		
		return fieldRefList;
	}
	public Link()
	{
		super();

		this.writeFile();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
}