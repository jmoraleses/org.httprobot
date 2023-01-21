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
public class Substring extends RmlOperator 
{

	/**
	 * -5972053213940643218L
	 */
	private static final long serialVersionUID = -5972053213940643218L;
	
	private StartIndex StartIndex = null;	
	private EndIndex EndIndex = null;	
	private GetField GetField = null;
	/**
	 * Substring class constructor.
	 */
	public Substring() { }
	/**
	 * Gets the end index.
	 * @return {@link EndIndex} the end index
	 */
	public EndIndex getEndIndex() {
		return EndIndex;
	}
	/**
	 * Gets the get field.
	 * @return {@link GetField} the get field
	 */
	public GetField getGetField() {
		return GetField;
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
	 * Sets the get field.
	 * @param GetField {@link GetField} the get field
	 */
	@XmlElement
	public void setGetField(GetField GetField) {
		this.GetField = GetField;
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
		setGetField(((Substring)e.getRml()).getGetField());
	}
}