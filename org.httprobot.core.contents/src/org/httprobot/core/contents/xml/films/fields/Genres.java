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
public class Genres extends FieldRef {

	/**
	 * -7958593281276042302L
	 */
	private static final long serialVersionUID = -7958593281276042302L;

	/**
	 * 
	 */
	public Genres() {
		this.setUuid(UUID.fromString("dac4f221-4579-48b2-be24-3c3b97255aca"));
		this.setName("genres");
		this.setDataType(DataType.TEXT);
		this.setMultiValued(true);
	}
}