/**
 * 
 */
package org.httprobot.common.rml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.rml.datatypes.operators.Condition;
import org.httprobot.common.rml.datatypes.operators.Contains;
import org.httprobot.common.rml.datatypes.operators.Delimiters;
import org.httprobot.common.rml.datatypes.operators.EndIndex;
import org.httprobot.common.rml.datatypes.operators.Equals;
import org.httprobot.common.rml.datatypes.operators.ForEach;
import org.httprobot.common.rml.datatypes.operators.Remove;
import org.httprobot.common.rml.datatypes.operators.Replace;
import org.httprobot.common.rml.datatypes.operators.Select;
import org.httprobot.common.rml.datatypes.operators.Split;
import org.httprobot.common.rml.datatypes.operators.StartIndex;
import org.httprobot.common.rml.datatypes.operators.Substring;
import org.httprobot.common.rml.datatypes.operators.TryParse;
import org.httprobot.common.rml.datatypes.operators.Where;
import org.httprobot.common.rml.datatypes.operators.XmlQuery;

/**
 * @author joan
 *
 */
@XmlTransient
public class RmlOperator extends Rml 
{
	private Equals Equals = null;
	private Contains Contains = null;
	private Delimiters Delimiters = null;
	private EndIndex EndIndex = null;
	private ForEach ForEach = null;
	private Remove Remove = null;
	private Replace Replace = null;
	private Select Select = null;
	private Split Split = null;
	private StartIndex StartIndex = null;
	private Substring Substring = null;
	private TryParse TryParse = null;
	private Where Where = null;
	private XmlQuery XmlQuery = null;
	private Condition Condition = null;
	
	/**
	 * @return gets the condition
	 */
	public Condition getCondition()
	{
		return Condition;
	}
	/**
	 * @return the contains
	 */
	public Contains getContains() 
	{
		return Contains;
	}
	/**
	 * @return the delimiters
	 */
	public Delimiters getDelimiters() 
	{
		return Delimiters;
	}
	/**
	 * @return the endIndex
	 */
	public EndIndex getEndIndex() 
	{
		return EndIndex;
	}
	/**
	 * @return the equals
	 */
	public Equals getEquals()
	{
		return Equals;
	}
	/**
	 * @return the forEach
	 */
	public ForEach getForEach() 
	{
		return ForEach;
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
	 * @return the select
	 */
	public Select getSelect() 
	{
		return Select;
	}
	/**
	 * @return the split
	 */
	public Split getSplit() 
	{
		return Split;
	}
	/**
	 * @return the startIndex
	 */
	public StartIndex getStartIndex() 
	{
		return StartIndex;
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
	/**
	 * @return the where
	 */
	public Where getWhere() 
	{
		return Where;
	}
	/**
	 * @return the xmlQuery
	 */
	public XmlQuery getXmlQuery()
	{
		return XmlQuery;
	}
	/**
	 * @param condition sets the {@link Condition}
	 */ 
	@XmlElement
	public void setCondition(Condition condition) 
	{
		Condition = condition;
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
	 * @param delimiters the delimiters to set
	 */
	@XmlElement
	public void setDelimiters(Delimiters delimiters) 
	{
		Delimiters = delimiters;
	}
	/**
	 * @param endIndex the endIndex to set
	 */
	@XmlElement
	public void setEndIndex(EndIndex endIndex) 
	{
		EndIndex = endIndex;
	}
	@XmlElement
	public void setEquals(Equals equals) {
		this.Equals = equals;
	}
	/**
	 * @param forEach the forEach to set
	 */
	@XmlElement
	public void setForEach(ForEach forEach) {
		ForEach = forEach;
	}
	/**
	 * @param remove the remove to set
	 */
	@XmlElement
	public void setRemove(Remove remove) {
		Remove = remove;
	}
	/**
	 * @param replace the replace to set
	 */
	@XmlElement
	public void setReplace(Replace replace) {
		Replace = replace;
	}
	/**
	 * @param select the select to set
	 */
	@XmlElement
	public void setSelect(Select select) {
		Select = select;
	}
	/**
	 * @param split the split to set
	 */
	@XmlElement
	public void setSplit(Split split) {
		Split = split;
	}
	/**
	 * @param startIndex the startIndex to set
	 */
	@XmlElement
	public void setStartIndex(StartIndex startIndex) {
		StartIndex = startIndex;
	}
	/**
	 * @param substring the substring to set
	 */
	@XmlElement
	public void setSubstring(Substring substring) {
		Substring = substring;
	}
	/**
	 * @param tryParse the tryParse to set
	 */
	@XmlElement
	public void setTryParse(TryParse tryParse) {
		TryParse = tryParse;
	}
	/**
	 * @param where the where to set
	 */
	@XmlElement
	public void setWhere(Where where) {
		Where = where;
	}
	/**
	 * @param xmlQuery the xmlQuery to set
	 */
	@XmlElement
	public void setXmlQuery(XmlQuery xmlQuery) {
		XmlQuery = xmlQuery;
	}	
}