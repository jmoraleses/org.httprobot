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
public class Cover extends FieldRef {

	/**
	 * -7458503262087163686L
	 */
	private static final long serialVersionUID = -7458503262087163686L;

	public Cover()
	{
		this.setUuid(UUID.fromString("a0d446b8-316f-431f-86d4-5ba261b7fbbc"));
		this.setName("cover");
		this.setDataType(DataType.IMAGE);
	}
}
