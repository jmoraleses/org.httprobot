package org.httprobot.common.rml.datatypes.operators;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.RmlOperator;
/**
 * @author Joan
 * Replace RML object class. Inherits {@link Rml2}.
 */
@XmlRootElement
public class Replace extends RmlOperator
{
	/**
	 * -5019223895775168245L
	 */
	private static final long serialVersionUID = -5019223895775168245L;
	
	private String OldString;
	private String NewString;
	private GetField GetField;	
	/**
	 * Replace class constructor.
	 */
	public Replace() { }
	/**
	 * Gets the GetField
	 * @return {@link GetField} the GetField
	 */
	public GetField getGetField() {
		return GetField;
	}
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
	 * Sets the GetField
	 * @param GetField
	 */
	public void setGetField(GetField GetField) {
		this.GetField = GetField;
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
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
		setGetField(((Replace)e.getRml()).getGetField());
		setNewString(((Replace)e.getRml()).getNewString());
		setOldString(((Replace)e.getRml()).getOldString());	
	}
}