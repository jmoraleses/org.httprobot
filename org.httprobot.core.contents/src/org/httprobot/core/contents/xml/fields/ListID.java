/**
 * 
 */
package org.httprobot.core.contents.xml.fields;

import java.util.UUID;

import org.httprobot.common.contents.FieldRef;
import org.httprobot.common.definitions.Enums.DataType;

/**
 * @author joan
 *
 */
public class ListID extends FieldRef {

	/**
	 * -8951553288193077370L
	 */
	private static final long serialVersionUID = -8951553288193077370L;

	/**
	 * 
	 */
	public ListID() {
		super();
		this.setUuid(UUID.fromString("65b6bbf1-696f-4d4f-a694-b1befce7d702"));
		this.setInherited(true);
	}
	
	/* (non-Javadoc)
	 * @see org.httprobot.common.contents.FieldRef#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "listID";
	}
	
	/* (non-Javadoc)
	 * @see org.httprobot.common.contents.FieldRef#getDataType()
	 */
	@Override
	public DataType getDataType() {
		// TODO Auto-generated method stub
		return DataType.UUID;
	}
}
