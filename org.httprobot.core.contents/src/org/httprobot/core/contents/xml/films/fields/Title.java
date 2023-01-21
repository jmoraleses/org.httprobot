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
public class Title extends FieldRef {

	/**
	 * -5861858402431443115L
	 */
	private static final long serialVersionUID = -5861858402431443115L;

	/**
	 * 
	 */
	public Title() {
		this.setUuid(UUID.fromString("40fb9c90-6239-4caa-ab6d-009d0f4093e4"));
		this.setName("title");
		this.setDataType(DataType.TEXT);
	}
}