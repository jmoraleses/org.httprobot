/**
 * 
 */
package org.httprobot.core.contents.events;

import org.httprobot.common.definitions.Enums.SolrConnectionStatus;
import org.httprobot.common.definitions.Enums.EventType;
import org.httprobot.common.events.EventArgs;

/**
 * @author joan
 *
 */
public class SolrConnectionEventArgs extends EventArgs 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8869275850254155437L;

	SolrConnectionStatus status;
	
	public SolrConnectionStatus getStatus()
	{
		return this.status;
	}	 
	/**
	 * @param value
	 * @param et
	 */
	public SolrConnectionEventArgs(Object value, SolrConnectionStatus et) {
		super(value, EventType.CONTENT);	
	}
}
