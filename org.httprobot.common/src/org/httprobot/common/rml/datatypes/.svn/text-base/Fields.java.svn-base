package org.httprobot.common.rml.datatypes;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.Rml;

/**
 * @author Joan
 * Fields RML object class. Inherits {@link Rml}.
 */
@XmlRootElement
public class Fields extends Rml
{	
	/**
	 * -6184759933543640561L
	 */
	private static final long serialVersionUID = -6184759933543640561L;
	
	private ArrayList<Field> Field = null;
	/**
	 * Fields class constructor.
	 */
	public Fields(){ }
	/**
	 * Gets the fields.
	 * @return {@link ArrayList} of {@link Field}
	 */
	public ArrayList<Field> getField() 
	{
		if(this.Field == null)
		{
			this.Field = new ArrayList<Field>();
			return Field;
		}
		else
		{
			return Field;
		}
	}	
	/**
	 * Sets the fields.
	 * @param Field {@link ArrayList} of {@link Field}
	 */
	public void setField(ArrayList<Field> Field) {
		this.Field = Field;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
		setField(((Fields)e.getRml()).getField());
	}
}