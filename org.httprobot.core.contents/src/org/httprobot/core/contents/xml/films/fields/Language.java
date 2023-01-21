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
public class Language extends FieldRef
{
	/**
	 * -933007458541492136L
	 */
	private static final long serialVersionUID = -933007458541492136L;
	/**
	 * 
	 */
	public Language() {
		this.setUuid(UUID.fromString("851c47fb-13d2-4e7b-890f-4f448d60f60a"));
		this.setName("language");
		this.setDataType(DataType.TEXT);
	}
}