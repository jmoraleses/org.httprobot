package org.httprobot.common.rml.datatypes.placeholders;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.definitions.Enums.PlaceholderPointer;
import org.httprobot.common.rml.Rml;
import org.httprobot.common.rml.RmlPlaceholder;

/**
 * @author Joan
 * Place holder web response class. Inherits {@link Rml}.
 */
@XmlRootElement
public class HtmlBody extends RmlPlaceholder
{
	public HtmlBody()
	{
		this.setPointer(PlaceholderPointer.HTML_BODY);
	}
}