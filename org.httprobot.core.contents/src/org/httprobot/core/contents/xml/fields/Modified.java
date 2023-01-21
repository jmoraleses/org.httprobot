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
public class Modified extends FieldRef {

	/**
	 * -618648088026232627L
	 */
	private static final long serialVersionUID = -618648088026232627L;
	/* (non-Javadoc)
	 * @see org.httprobot.common.contents.FieldRef#getName()
	 */
	@Override
	public String getName() {
		return "modified";
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.contents.FieldRef#getDataType()
	 */
	@Override
	public DataType getDataType() {
		return DataType.DATETIME;
	}
	/**
	 * 
	 */
	public Modified() {

		this.setUuid(UUID.fromString("976ec1a3-a8f3-4709-a279-8ec2f08238cc"));
	}
}