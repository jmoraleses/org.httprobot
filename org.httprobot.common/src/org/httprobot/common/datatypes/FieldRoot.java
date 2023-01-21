package org.httprobot.common.datatypes;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.DataType;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;

/**
 * @author Joan
 * Fields RML object class. Inherits {@link DataType}.
 */
@XmlRootElement
public class FieldRoot extends DataType
{	
	/**
	 * -6184759933543640561L
	 */
	private static final long serialVersionUID = -6184759933543640561L;
	
	private ArrayList<Field> Field = null;
	/**
	 * Fields class constructor.
	 */
	public FieldRoot(){ }
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
		setField(((FieldRoot)e.getRml()).getField());
	}
}