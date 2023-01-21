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
public class Quality extends FieldRef {

	/**
	 * 7170275366319025413L
	 */
	private static final long serialVersionUID = 7170275366319025413L;

	/**
	 * 
	 */
	public Quality() 
	{
		this.setUuid(UUID.fromString("8b569493-f54a-4832-8072-32a073301ab5"));
		this.setName("quality");
		this.setDataType(DataType.TEXT);
	}
}