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
public class Server extends FieldRef {

	/**
	 * 7147333431971580326L
	 */
	private static final long serialVersionUID = 7147333431971580326L;

	
	public Server()
	{
		this.setUuid(UUID.fromString("8b40bb81-45b7-4da2-9090-29929ec26293"));
		this.setName("server");
		this.setDataType(DataType.TEXT);		
	}
}
