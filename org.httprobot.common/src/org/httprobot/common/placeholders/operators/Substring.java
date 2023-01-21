package org.httprobot.common.placeholders.operators;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.Operator;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
/**
 * Substring message class. Inherits {@link Operator}.
 * <br>
 * @author Joan
 *
 */
@XmlRootElement
public class Substring extends Operator 
{

	/**
	 * -5972053213940643218L
	 */
	private static final long serialVersionUID = -5972053213940643218L;
	
	private StartIndex StartIndex = null;	
	private EndIndex EndIndex = null;
	/**
	 * Substring class constructor.
	 */
	public Substring() 
	{ 
		super();
	}
	/**
	 * Gets the end index.
	 * @return {@link EndIndex} the end index
	 */
	public EndIndex getEndIndex() {
		return EndIndex;
	}
	/**
	 * Gets the start index.
	 * @return {@link StartIndex} the start index
	 */
	public StartIndex getStartIndex() {
		return StartIndex;
	}
	/**
	 * Sets the end index.
	 * @param EndIndex {@link EndIndex} the end index
	 */
	@XmlElement
	public void setEndIndex(EndIndex EndIndex) {
		this.EndIndex = EndIndex;
	}	
	/**
	 * Sets the start index.
	 * @param StartIndex {@link StartIndex} the start index
	 */
	@XmlElement
	public void setStartIndex(StartIndex StartIndex) {
		this.StartIndex = StartIndex;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
		
		setStartIndex(((Substring)e.getRml()).getStartIndex());
		setEndIndex(((Substring)e.getRml()).getEndIndex());
	}
}