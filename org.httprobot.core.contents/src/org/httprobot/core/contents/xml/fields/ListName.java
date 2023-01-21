/**
 * 
 */
package org.httprobot.core.contents.xml.fields;

import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.FieldRef;
import org.httprobot.common.definitions.Enums.DataType;

/**
 * @author joan
 *
 */
@XmlRootElement
public class ListName extends FieldRef {

	/**
	 * -6527656849763679811L
	 */
	private static final long serialVersionUID = -6527656849763679811L;
	/* (non-Javadoc)
	 * @see org.httprobot.common.contents.FieldRef#getName()
	 */
	@Override
	public String getName() {
		return "listName";
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.contents.FieldRef#getDataType()
	 */
	@Override
	public DataType getDataType() {
		return DataType.TEXT;
	}
	/**
	 * 
	 */
	public ListName() {
		this.setUuid(UUID.fromString("4785932c-cd3d-427a-b8d0-8bfe005f3b31"));
		this.setInherited(true);
	}

}