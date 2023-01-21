/**
 * 
 */
package org.httprobot.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.contents.FieldRef;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.operators.Contains;
import org.httprobot.common.placeholders.operators.Equals;
import org.httprobot.common.placeholders.operators.Remove;
import org.httprobot.common.placeholders.operators.Replace;
import org.httprobot.common.placeholders.operators.Split;
import org.httprobot.common.placeholders.operators.Substring;
import org.httprobot.common.placeholders.operators.TryParse;

/**
 * Placeholder message class. Inherits {@link RML}.
 * <br>
 * @author joan
 *
 */
@XmlTransient
public class Placeholder 
		extends RML {

	/**
	 * 8818768171198364801L
	 */
	private static final long serialVersionUID = 8818768171198364801L;
	
	Contains Contains;
	Equals Equals;	
	FieldRef fieldRef;
	Remove Remove;
	Replace Replace;
	Split Split;
	Substring Substring;
	TryParse TryParse;
	
	/**
	 * Placeholder default constructor.
	 */
	public Placeholder()
	{
		super();
	}
	/**
	 * @return the contains
	 */
	public Contains getContains()
	{
		return Contains;
	}
	/**
	 * @return the equals
	 */
	public Equals getEquals()
	{
		return Equals;
	}
	/**
	 * @return the fieldRef
	 */
	public FieldRef getFieldRef() 
	{
		return fieldRef;
	}
	/**
	 * @return the remove
	 */
	public Remove getRemove()
	{
		return Remove;
	}
	/**
	 * @return the replace
	 */
	public Replace getReplace() 
	{
		return Replace;
	}
	/**
	 * @return the split
	 */
	public Split getSplit() 
	{
		return Split;
	}
	/**
	 * @return the substring
	 */
	public Substring getSubstring() 
	{
		return Substring;
	}
	/**
	 * @return the tryParse
	 */
	public TryParse getTryParse()
	{
		return TryParse;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.RML#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		//Field reference
		setFieldRef(((Placeholder)e.getRml()).getFieldRef());
		
		//Operators
		setContains(((Placeholder)e.getRml()).getContains());
		setEquals(((Placeholder)e.getRml()).getEquals());
		setRemove(((Placeholder)e.getRml()).getRemove());
		setReplace(((Placeholder)e.getRml()).getReplace());
		setSplit(((Placeholder)e.getRml()).getSplit());
		setSubstring(((Placeholder)e.getRml()).getSubstring());
		setTryParse(((Placeholder)e.getRml()).getTryParse());
	}
	/**
	 * @param contains the contains to set
	 */
	@XmlElement
	public void setContains(Contains contains) 
	{
		Contains = contains;
	}
	/**
	 * @param equals the equals to set
	 */
	@XmlElement
	public void setEquals(Equals equals)
	{
		Equals = equals;
	}
	/**
	 * @param fieldRef the fieldRef to set
	 */
	@XmlElement
	public void setFieldRef(FieldRef fieldRef)
	{
		this.fieldRef = fieldRef;
	}
	/**
	 * @param remove the remove to set
	 */
	@XmlElement
	public void setRemove(Remove remove)
	{
		Remove = remove;
	}
	/**
	 * @param replace the replace to set
	 */
	@XmlElement
	public void setReplace(Replace replace)
	{
		Replace = replace;
	}
	/**
	 * @param split the split to set
	 */
	@XmlElement
	public void setSplit(Split split)
	{
		Split = split;
	}
	/**
	 * @param substring the substring to set
	 */
	@XmlElement
	public void setSubstring(Substring substring) 
	{
		Substring = substring;
	}
	/**
	 * @param tryParse the tryParse to set
	 */
	@XmlElement
	public void setTryParse(TryParse tryParse)
	{
		TryParse = tryParse;
	}
}
