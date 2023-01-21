package org.httprobot.client.inet.proxy.dataview;

import java.util.ArrayList;

import org.httprobot.core.rml.controls.datatypes.DataViewControl;
import org.httprobot.core.rml.controls.datatypes.FieldRefControl;

/**
 * Proxy data view class. Inherits {@link DataViewControl}.
 * @author joan
 *
 */
public class ProxyDataView extends DataViewControl
{
	/**
	 * -78724038435436502L
	 */
	private static final long serialVersionUID = -78724038435436502L;
	
	ArrayList<FieldRefControl> fields_refs;
	String dbSchema;
	
	/**
	 * ProxyDataView default class constructor.
	 */
	public ProxyDataView()
	{
		super();
		this.setDestinationPath("ProxyDataView.xml");
		fields_refs = new ArrayList<FieldRefControl>();
	}

}