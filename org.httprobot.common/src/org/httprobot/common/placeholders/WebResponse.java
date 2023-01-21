package org.httprobot.common.placeholders;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.RML;
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
import org.httprobot.common.placeholders.operators.html.Page;

/**
 * @author Joan
 * Place holder web response class. Inherits {@link RML}.
 */
@XmlRootElement
public class WebResponse extends RML 
{
	/**
	 * 3876121179437924391L
	 */
	private static final long serialVersionUID = 3876121179437924391L;
	
	private Replace replace = null;	
	private Substring substring = null;	
	private Split split = null;
	private Contains contains = null;
	private Equals equals = null;
	private Remove remove = null;
	private TryParse tryparse = null;
	private Page xmlquery = null;
	/**
	 * Gets the Contains element.
	 * @return {@link Contains} the contains
	 */
	public Contains getContains() {
		return contains;
	}
	/**
	 * Gets the Equals element.
	 * @return {@link Equals} the equals
	 */
	public Equals getEquals() {
		return equals;
	}
	/**
	 * Gets the Remove element.
	 * @return {@link Remove} the remove
	 */
	public Remove getRemove() {
		return remove;
	}
	/**
	 * Gets the Replace element.
	 * @return {@link Replace} the replace
	 */
	public Replace getReplace() {
		return replace;
	}
	/**
	 * Gets the Split element.
	 * @return {@link Split} the split
	 */
	public Split getSplit() {
		return split;
	}
	/**
	 * Gets the Substring element.
	 * @return {@link Substring} the substring
	 */
	public Substring getSubstring() {
		return substring;
	}
	/**
	 * Gets the TryParse element.
	 * @return {@link TryParse} try tryParse
	 */
	public TryParse getTryparse() {
		return tryparse;
	}
	/**
	 * Rule Control class constructor.
	 * @param parent RmlControl the parent control
	 * @param item_count the current rule number
	 * @param rule the rule
	 */
	/**
	 * @return the xmlquery
	 */
	public Page getXmlquery() {
		return xmlquery;
	}
	/**
	 * Gets the XmlQuery element.
	 * @return {@link Page} the xmlQuery
	 */
	public Page getXmlQuery()
	{
		return xmlquery;
	}
	/**
	 * @param contains the contains to set
	 */
	@XmlElement
	public void setContains(Contains contains) {
		this.contains = contains;
	}
	/**
	 * @param equals the equals to set
	 */
	@XmlElement
	public void setEquals(Equals equals) {
		this.equals = equals;
	}
	/**
	 * @param remove the remove to set
	 */
	@XmlElement
	public void setRemove(Remove remove) {
		this.remove = remove;
	}
	/**
	 * @param replace the replace to set
	 */
	@XmlElement
	public void setReplace(Replace replace) {
		this.replace = replace;
	}
	/**
	 * @param split the split to set
	 */
	@XmlElement
	public void setSplit(Split split) {
		this.split = split;
	}
	/**
	 * @param substring the substring to set
	 */
	@XmlElement
	public void setSubstring(Substring substring) {
		this.substring = substring;
	}
	/**
	 * @param tryparse the tryparse to set
	 */
	@XmlElement
	public void setTryparse(TryParse tryparse) {
		this.tryparse = tryparse;
	}
	/**
	 * @param xmlquery the xmlquery to set
	 */
	@XmlElement
	public void setXmlquery(Page xmlquery) {
		this.xmlquery = xmlquery;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}	
}

