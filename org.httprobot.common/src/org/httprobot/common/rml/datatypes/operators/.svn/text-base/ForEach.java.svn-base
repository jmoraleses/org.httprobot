package org.httprobot.common.rml.datatypes.operators;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.RmlOperator;
/**
 * @author Joan
 * ForEach RML object class. Inherits {@link Rml2}.
 */
@XmlRootElement
public class ForEach extends RmlOperator
{
	/**
	 * 600269286807147174L
	 */
	private static final long serialVersionUID = 600269286807147174L;
	
	ArrayList<Object> Items = new ArrayList<Object>();
	/**
	 * ForEach class constructor.
	 */
	public ForEach() { }
	/**
	 * Gets the items.
	 * @return {@link ArrayList} of {@link Object}
	 */
	public ArrayList<Object> getItems() 
	{
		if(Items != null)
		{
			return Items;
		}
		else
		{
			this.Items = new ArrayList<Object>();
			return Items;
		}
	}
	/**
	 * Sets the items.
	 * @param Items {@link ArrayList} of {@link Object}
	 */
	public void setItems(ArrayList<Object> Items) {
		this.Items = Items;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
		setItems(((ForEach)e.getRml()).getItems());
	}	
}
