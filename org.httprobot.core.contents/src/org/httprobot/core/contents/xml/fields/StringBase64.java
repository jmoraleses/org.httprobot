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
public class StringBase64 extends FieldRef
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5697383257072292235L;

	/* (non-Javadoc)
	 * @see org.httprobot.common.contents.FieldRef#getName()
	 */
	@Override
	public String getName() {
		return "stringBase64";
	}
	
	/* (non-Javadoc)
	 * @see org.httprobot.common.contents.FieldRef#getDataType()
	 */
	@Override
	public DataType getDataType() {
		return DataType.BASE64;
	}
	/**
	 * 
	 */
	public StringBase64() {

		this.setUuid(UUID.fromString("8d4c52d1-f213-4940-b9ec-47eb9668d702"));
	}	
}
