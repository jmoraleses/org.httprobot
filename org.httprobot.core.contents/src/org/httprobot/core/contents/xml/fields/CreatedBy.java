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
public class CreatedBy extends FieldRef 
{

	/**
	 * 4317509505482263347L
	 */
	private static final long serialVersionUID = 4317509505482263347L;
	/* (non-Javadoc)
	 * @see org.httprobot.common.contents.FieldRef#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "createdBy";
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
	public CreatedBy() {

		this.setUuid(UUID.fromString("d5734db1-9359-4c77-9990-a53633ef8eb1"));
	}
}