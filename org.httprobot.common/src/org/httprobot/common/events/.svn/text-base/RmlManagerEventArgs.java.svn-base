package org.httprobot.common.events;

import org.httprobot.common.definitions.Enums.EventType;
import org.httprobot.common.definitions.Enums.RmlManagerEventType;

/**
 * RML manager event arguments class. Inherits {@link EventArgs}.
 * @author Joan
 * 
 */
public class RmlManagerEventArgs extends EventArgs {

	/**
	 * 3643125804674425328L
	 */
	private static final long serialVersionUID = 3643125804674425328L;
	private RmlManagerEventType rmet;	
	/**
	 * Gets the type
	 * @return {@link RmlManagerEventType}
	 */
	public RmlManagerEventType getRmet() {
		return rmet;
	}
	/**
	 * RmlManagerEventArgs class constructor.
	 * @param value {@link Object} the value
	 * @param rmet {@link RmlManagerEventType} the type
	 */
	public RmlManagerEventArgs(Object value, RmlManagerEventType rmet) {
		super(value, EventType.RML);
		this.rmet = rmet;
	}
}
