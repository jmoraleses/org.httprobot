package org.httprobot.common.rml.datatypes.operators;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.RmlOperator;

/**
 * @author Joan
 * Delimiters RML object class. Inherits {@link Rml2}.
 */
@XmlRootElement
public class Delimiters extends RmlOperator
{	
	/**
	 * 890251842592470702L
	 */
	private static final long serialVersionUID = 890251842592470702L;
	
	private ArrayList<String> Delimiter = null;
	/**
	 * Gets the delimiters.
	 * @return {@link ArrayList} of {@link String}
	 */
	public ArrayList<String> getDelimiter() {
		return Delimiter;
	}
	/**
	 * Sets the delimiters.
	 * @param Delimiters {@link ArrayList} of {@link String}
	 */
	public void setDelimiters(ArrayList<String> Delimiter) {
		this.Delimiter = Delimiter;
	}
	/**
	 * Delimiters RML object class constructor.
	 */
	public Delimiters()
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
		this.setDelimiters(((Delimiters)e.getRml()).getDelimiters());
	}
}