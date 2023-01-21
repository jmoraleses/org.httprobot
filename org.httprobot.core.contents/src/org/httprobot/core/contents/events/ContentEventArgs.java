/**
 * 
 */
package org.httprobot.core.contents.events;

import org.httprobot.common.definitions.Enums.ContentEventType;
import org.httprobot.common.definitions.Enums.EventType;
import org.httprobot.common.events.EventArgs;
import org.httprobot.core.contents.solr.InputDocument;

/**
 * @author joan
 *
 */
public class ContentEventArgs extends EventArgs {

	/**
	 * -6097135569674329281L
	 */
	private static final long serialVersionUID = -6097135569674329281L;
	
	ContentEventType contentEventType;
	
	InputDocument inputDocument;
	
	public InputDocument getInputDocument()
	{
		return this.inputDocument;
	}	
	/**
	 * @return the contentEventType
	 */
	public ContentEventType getContentEventType() {
		return contentEventType;
	}
	/**
	 * @param value
	 * @param et
	 */
	public ContentEventArgs(Object value, ContentEventType et) {
		super(value, EventType.CONTENT);
		this.contentEventType = et;
		
		if(value instanceof InputDocument)
		{
			this.inputDocument = InputDocument.class.cast(value);
		}
	}
}
