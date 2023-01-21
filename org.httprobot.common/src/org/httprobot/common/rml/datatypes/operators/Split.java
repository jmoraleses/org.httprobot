package org.httprobot.common.rml.datatypes.operators;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.RmlOperator;

/**
 * @author Joan
 *
 */
@XmlRootElement
public class Split extends RmlOperator
{

	/**
	 * 2801835392539069520L
	 */
	private static final long serialVersionUID = 2801835392539069520L;
	
	private Delimiters Delimiters = null;
	private ForEach ForEach = null;
	/**
	 * Split class constructor.
	 */
	public Split() { }	
	/**
	 * Gets the delimiters.
	 * @return {@link Delimiters} the delimiters
	 */
	public Delimiters GetDelimiters() {
		return Delimiters;
	}
	/**
	 * Gets the ForEach object
	 * @return {@link ForEach} the object
	 */
	public ForEach getForEach() {
		return ForEach;
	}
	/**
	 * Sets delimiters to split
	 * @param Delimiters {@link Delimiters} the delimiters
	 */
	public void setDelimiters(Delimiters Delimiters) {
		this.Delimiters = Delimiters;
	}	
	/**
	 * Sets the ForEach object
	 * @param ForEach {@link ForEach} the object
	 */
	@XmlElement
	public void setForEach(ForEach ForEach) {
		this.ForEach = ForEach;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
		setDelimiters(((Split)e.getRml()).getDelimiters());
		setForEach(((Split)e.getRml()).getForEach());
	}
}
