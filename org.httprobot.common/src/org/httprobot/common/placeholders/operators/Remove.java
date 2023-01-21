package org.httprobot.common.placeholders.operators;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.Operator;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
/**
 * @author Joan
 * Remove RML object class. Inherits {@link Rml2}.
 */
@XmlRootElement
public class Remove extends Operator {
	
	/**
	 * 2885238392369302220L
	 */
	private static final long serialVersionUID = 2885238392369302220L;
	
	private StartIndex StartIndex = null;	
	private EndIndex EndIndex = null;
	/**
	 * Remove class constructor.
	 */
	public Remove() { }
	/**
	 * Gets the end index
	 * @return {@link Integer} the end index value
	 */
	public EndIndex getEndIndex() {
		return EndIndex;
	}
	/**
	 * Gets the start index
	 * @return {@link Integer} the start index
	 */
	public StartIndex getStartIndex() {
		return StartIndex;
	}
	/**
	 * Sets the end index.
	 * @param EndIndex {@link Integer} the end index
	 */
	@XmlElement
	public void setEndIndex(EndIndex EndIndex) {
		this.EndIndex = EndIndex;
	}
	/**
	 * @param StartIndex
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
		setStartIndex(((Remove)e.getRml()).getStartIndex());
		setEndIndex(((Remove)e.getRml()).getEndIndex());
	}
}