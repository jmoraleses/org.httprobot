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
public class ModifiedBy extends FieldRef {

	/**
	 * 4420538097524876282L
	 */
	private static final long serialVersionUID = 4420538097524876282L;

	/* (non-Javadoc)
	 * @see org.httprobot.common.contents.FieldRef#getName()
	 */
	@Override
	public String getName() {
		return "modifiedBy";
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
	public ModifiedBy() {

		this.setUuid(UUID.fromString("59d4957d-d204-42bb-b44f-6725450124a0"));
	}
}