package org.httprobot.common.rml.datatypes.placeholders;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.definitions.Enums.PlaceholderPointer;
import org.httprobot.common.rml.Rml;
import org.httprobot.common.rml.RmlPlaceholder;

/**
 * Place holder web request class. Inherits {@link Rml}.
 * @author Joan 
 */
@XmlRootElement
public class HttpRequest extends RmlPlaceholder
{
	/**
	 * Web request default constructor.
	 */
	public HttpRequest()
	{ 
		this.setPointer(PlaceholderPointer.HTTP_ADDRESS);
	}

}