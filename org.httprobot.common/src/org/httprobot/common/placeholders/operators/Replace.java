package org.httprobot.common.placeholders.operators;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.Operator;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
/**
 * Replace message class. Inherits {@link Operator}.
 * @author Joan
 * 
 */
@XmlRootElement
public class Replace 
		extends Operator {
	
	/**
	 * -5019223895775168245L
	 */
	private static final long serialVersionUID = -5019223895775168245L;
	
	private String OldString;
	private String NewString;
	/**
	 * Replace class constructor.
	 */
	public Replace() { }
	/**
	 * Gets the new text
	 * @return {@link String} the text
	 */
	public String getNewString() {
		return NewString;
	}	
	/**
	 * Gets the old string
	 * @return {@link String} text to replace
	 */
	public String getOldString() {
		return OldString;
	}
	/**
	 * Sets the new text
	 * @param NewString {@link String} the new string
	 */
	@XmlElement
	public void setNewString(String NewString) {
		this.NewString = NewString;
	}	
	/**
	 * Sets the text to replace
	 * @param OldString {@link String} the old string
	 */
	@XmlElement
	public void setOldString(String OldString) {
		this.OldString = OldString;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.Operator#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		super.OnObjectUnmarshalled(sender, e);
		setNewString(((Replace)e.getRml()).getNewString());
		setOldString(((Replace)e.getRml()).getOldString());	
	}
}