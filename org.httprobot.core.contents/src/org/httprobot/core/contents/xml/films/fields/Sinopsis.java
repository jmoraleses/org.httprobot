/**
 * 
 */
package org.httprobot.core.contents.xml.films.fields;

import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.FieldRef;
import org.httprobot.common.definitions.Enums.DataType;

/**
 * @author joan
 *
 */
@XmlRootElement
public class Sinopsis extends FieldRef {

	/**
	 * -8222173167686386330L
	 */
	private static final long serialVersionUID = -8222173167686386330L;

	/**
	 * 
	 */
	public Sinopsis() {
		this.setUuid(UUID.fromString("8cfe9b77-0e5a-4e5e-9509-a1433bf0fe3e"));
		this.setName("sinopsis");
		this.setDataType(DataType.TEXT);
	}
}