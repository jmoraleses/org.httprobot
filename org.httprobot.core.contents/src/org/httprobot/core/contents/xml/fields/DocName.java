/**
 * 
 */
package org.httprobot.core.contents.xml.fields;

import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.FieldRef;
import org.httprobot.common.definitions.Enums.DataType;

/**
 * Name field type class. Inherits {@link FieldRef}.
 * <br>
 * @author joan
 *
 */
@XmlRootElement
public class DocName extends FieldRef {

	/**
	 * -3218648755492581866L
	 */
	private static final long serialVersionUID = -3218648755492581866L;

	/* (non-Javadoc)
	 * @see org.httprobot.common.contents.FieldRef#getName()
	 */
	@Override
	public String getName() 
	{
		return "docName";
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.contents.FieldRef#getDataType()
	 */
	@Override
	public DataType getDataType()
	{		
		return DataType.TEXT;
	}
	/**
	 * 
	 */
	public DocName() {
		
		this.setUuid(UUID.fromString("2515ec0f-0385-478c-8f64-88a44814df1d"));
		this.setInherited(true);
	}
}