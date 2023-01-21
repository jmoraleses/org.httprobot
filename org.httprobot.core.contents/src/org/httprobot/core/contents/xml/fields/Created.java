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
public class Created extends FieldRef
{
	/**
	 * 4326705235004214716L
	 */
	private static final long serialVersionUID = 4326705235004214716L;

	/* (non-Javadoc)
	 * @see org.httprobot.common.contents.FieldRef#getName()
	 */
	@Override
	public String getName()
	{
		return "created";
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.contents.FieldRef#getDataType()
	 */
	@Override
	public DataType getDataType()
	{		
		return DataType.DATETIME;
	}
	/**
	 * 
	 */
	public Created() {

		this.setUuid(UUID.fromString("280bb3f0-fd70-4b5f-9ef3-fdd4575c57d7"));
	}	
}