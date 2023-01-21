/**
 * 
 */
package org.httprobot.common.rml;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

import org.httprobot.common.definitions.Enums.PlaceholderPointer;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.datatypes.operators.Contains;
import org.httprobot.common.rml.datatypes.operators.Equals;
import org.httprobot.common.rml.datatypes.operators.ForEach;
import org.httprobot.common.rml.datatypes.operators.Select;
import org.httprobot.common.rml.datatypes.operators.Remove;
import org.httprobot.common.rml.datatypes.operators.Replace;
import org.httprobot.common.rml.datatypes.operators.Split;
import org.httprobot.common.rml.datatypes.operators.Substring;
import org.httprobot.common.rml.datatypes.operators.TryParse;
import org.httprobot.common.rml.datatypes.operators.XmlQuery;

/**
 * @author joan
 *
 */
public class RmlPlaceholder extends Rml 
{
	private ArrayList<Contains> Contains;
	
	private ArrayList<Equals> Equals;	
	private ArrayList<ForEach> ForEach;	
	private PlaceholderPointer pointer;
	private ArrayList<Remove> Remove;
	private ArrayList<Replace> Replace;
	private ArrayList<Select> Select;
	private ArrayList<Split> Split;
	private ArrayList<Substring> Substring;
	private ArrayList<TryParse> TryParse;
	private ArrayList<XmlQuery> XmlQuery;

	/**
	 * Placeholder default constructor.
	 */
	public RmlPlaceholder()	
	{
		this.Replace = new ArrayList<Replace>();
		this.Substring = new ArrayList<Substring>();
		this.Split = new ArrayList<Split>();
		this.Contains = new ArrayList<Contains>();
		this.Equals = new ArrayList<Equals>();
		this.Remove = new ArrayList<Remove>();
		this.TryParse = new ArrayList<TryParse>();
		this.XmlQuery = new ArrayList<XmlQuery>();
		this.Select = new ArrayList<Select>();
		this.ForEach = new ArrayList<ForEach>();
	}
	/**
	 * @return {@link Contains} the RML object
	 */
	public ArrayList<Contains> getContains() {
		return Contains;
	}
	/**
	 * @return {@link Equals} the RML object
	 */
	public ArrayList<Equals> getEquals() {
		return Equals;
	}
	/**
	 * @return the array of {@link ForEach} RML objects
	 */
	public ArrayList<ForEach> getForEach() {
		return ForEach;
	}
	/**
	 * @return {@link String} the place to point inside place holder.
	 */
	public PlaceholderPointer getPointer() {
		return pointer;
	}
	/**
	 * @return {@link Remove} the RML object
	 */
	public ArrayList<Remove> getRemove() {
		return Remove;
	}	
	/**
	 * @return {@link Replace} the RML object
	 */
	public ArrayList<Replace> getReplace() 
	{
		return Replace;
	}
	/**
	 * @return the array of {@link Select} RML objects
	 */
	public ArrayList<Select> getSelect() {
		return Select;
	}	
	/**
	 * @return {@link Split} the RML object
	 */
	public ArrayList<Split> getSplit() {
		return Split;
	}
	/**
	 * @return {@link Substring} the RML object
	 */
	public ArrayList<Substring> getSubstring() {
		return Substring;
	}
	/**
	 * @return {@link TryParse} the RML object
	 */
	public ArrayList<TryParse> getTryParse() {
		return TryParse;
	}
	/**
	 * @return {@link XmlQuery} the RML object
	 */
	public ArrayList<XmlQuery> getXmlQuery() {
		return this.XmlQuery;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/**
	 * Sets the RML object
	 * @param Contains {@link Contains}
	 */
	@XmlElements(value = { @XmlElement })
	public void setContains(ArrayList<Contains> Contains) {
		this.Contains = Contains;
	}
	/**
	 * Sets the RML object
	 * @param Equals {@link Equals}
	 */
	@XmlElements(value = { @XmlElement })
	public void setEquals(ArrayList<Equals> Equals) {
		this.Equals = Equals;
	}
	/**
	 * Sets the array of {@link ForEach} RML objects
	 * @param forEach
	 */
	@XmlElements(value = { @XmlElement })
	public void setForEach(ArrayList<ForEach> forEach) {
		ForEach = forEach;
	}
	/**
	 * Sets the place to point inside place holder.
	 * @param pointer
	 */
	@XmlAttribute
	public void setPointer(PlaceholderPointer pointer) 
	{
		this.pointer = pointer;
	}	
	/**
	 * Sets the RML object
	 * @param Remove {@link Remove}
	 */
	@XmlElements(value = { @XmlElement })
	public void setRemove(ArrayList<Remove> Remove) {
		this.Remove = Remove;
	}
	/**
	 * Sets the RML Replaces objects
	 * @param Replace {@link ArrayList} of Replace}
	 */
	@XmlElements(value = { @XmlElement })
	public void setReplace(ArrayList<Replace> Replace) {
		this.Replace = Replace;
	}
	/**
	 * Sets the array of Select RML objects
	 * @param select the array of {@link Select} RML objects
	 */
	@XmlElements(value = { @XmlElement })
	public void setSelect(ArrayList<Select> select) {
		Select = select;
	}
	/**
	 * Sets the RML object
	 * @param Split {@link Split}
	 */
	@XmlElements(value = { @XmlElement })
	public void setSplit(ArrayList<Split> Split) {
		this.Split = Split;
	}
	/**
	 * Sets the RML object
	 * @param Substring {@link Substring}
	 */
	@XmlElements(value = { @XmlElement })
	public void setSubstring(ArrayList<Substring> Substring) {
		this.Substring = Substring;
	}
	/**
	 * Sets the RML object
	 * @param TryParse {@link TryParse}
	 */
	@XmlElements(value = { @XmlElement })
	public void setTryParse(ArrayList<TryParse> TryParse) {
		this.TryParse = TryParse;
	}
	/**
	 * Sets the RML object
	 * @param XmlQuery {@link XmlQuery}
	 */
	@XmlElements(value = { @XmlElement })
	public void setXmlQuery(ArrayList<XmlQuery> XmlQuery) {
		this.XmlQuery = XmlQuery;
	}
}
