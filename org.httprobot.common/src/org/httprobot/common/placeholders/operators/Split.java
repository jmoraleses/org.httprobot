package org.httprobot.common.placeholders.operators;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.Operator;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;

/**
 * Split message class. Inherits {@link Operator}.
 * <br>
 * @author Joan
 *
 */
@XmlRootElement
public class Split extends Operator
{
	/**
	 * 2801835392539069520L
	 */
	private static final long serialVersionUID = 2801835392539069520L;
	
	private Delimiters Delimiters = null;
	/**
	 * Split class constructor.
	 */
	public Split() { }	
	/**
	 * Gets the delimiters.
	 * @return {@link Delimiters} the delimiters
	 */
	public Delimiters getDelimiters() {
		return Delimiters;
	}
	/**
	 * Sets delimiters to split
	 * @param Delimiters {@link Delimiters} the delimiters
	 */
	@XmlElement
	public void setDelimiters(Delimiters Delimiters) {
		this.Delimiters = Delimiters;
	}	

	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		super.OnObjectUnmarshalled(sender, e);
		
		setDelimiters(((Split)e.getRml()).getDelimiters());
	}
}
