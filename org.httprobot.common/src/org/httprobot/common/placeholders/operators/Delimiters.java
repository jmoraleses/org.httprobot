package org.httprobot.common.placeholders.operators;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.Operator;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;

/**
 * {@link Delimiters} message class. Inherits {@link Operator}.
 * <br>
 * @author Joan
 * 
 */
@XmlRootElement
public class Delimiters extends Operator
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
	 * Sets the delimiters.
	 * @param Delimiters {@link ArrayList} of {@link String}
	 */
	public ArrayList<String> getDelimiters() 
	{
		return this.Delimiter;
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
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		super.OnObjectUnmarshalled(sender, e);
		
		this.setDelimiters(((Delimiters)e.getRml()).getDelimiters());
	}
}