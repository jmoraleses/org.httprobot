/**
 * 
 */
package org.httprobot.core.contents.xml.fields;

import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.FieldRef;
import org.httprobot.common.definitions.Enums.DataType;

/**
 * DocID field type message class. Inherits {@link FieldRef}.
 * <br>
 * @author joan
 *
 */
@XmlRootElement
public class DocID extends FieldRef {

	/**
	 * -6924263416633170789L
	 */
	private static final long serialVersionUID = -6924263416633170789L;

	/* (non-Javadoc)
	 * @see org.httprobot.common.contents.FieldRef#getName()
	 */
	@Override
	public String getName() 
	{
		return "docID";
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.contents.FieldRef#getDataType()
	 */
	@Override
	public DataType getDataType()
	{		
		return DataType.UUID;
	}	
	/**
	 * Document identifier field reference message default class constructor.
	 */
	public DocID() {
		
		this.setUuid(UUID.fromString("f3854a20-2def-4a18-a932-193c5587e71c"));
		this.setInherited(true);
	}
}