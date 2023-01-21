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
 * Place holder web request class. Inherits {@link RML}.
 * @author Joan 
 */
@XmlRootElement
public class WebRequest extends RML
{
	/**
	 * -8799549475521731607L
	 */
	private static final long serialVersionUID = -8799549475521731607L;
	private Replace Replace = null;	
	private Substring Substring = null;	
	private Split Split = null;
	private Contains Contains = null;
	private Equals Equals = null;
	private Remove Remove = null;
	private TryParse TryParse = null;
	private Page XmlQuery = null;
	
//	private WebRequest WebRequest = null;
//	private WebResponse WebResponse = null;
//	
//	/**
//	 * @return the webRequest
//	 */
//	public WebRequest getWebRequest() {
//		return WebRequest;
//	}
//	/**
//	 * @return the webResponse
//	 */
//	public WebResponse getWebResponse() {
//		return WebResponse;
//	}
	/**
	 * Web request default constructor.
	 */
	public WebRequest()	{ }
	/**
	 * @return {@link Contains} the RML object
	 */
	public Contains getContains() {
		return Contains;
	}
	/**
	 * @return {@link Equals} the RML object
	 */
	public Equals getEquals() {
		return Equals;
	}
	/**
	 * @return {@link Remove} the RML object
	 */
	public Remove getRemove() {
		return Remove;
	}
	/**
	 * @return {@link Replace} the RML object
	 */
	public Replace getReplace() 
	{
		return Replace;
	}
	/**
	 * @return {@link Split} the RML object
	 */
	public Split getSplit() {
		return Split;
	}	
	/**
	 * @return {@link Substring} the RML object
	 */
	public Substring getSubstring() {
		return Substring;
	}
	/**
	 * @return {@link TryParse} the RML object
	 */
	public TryParse getTryParse() {
		return TryParse;
	}	
	/**
	 * @return {@link Page} the RML object
	 */
	public Page getXmlQuery() {
		return this.XmlQuery;
	}
	/**
	 * Sets the RML object
	 * @param Contains {@link Contains}
	 */
	@XmlElement
	public void setContains(Contains Contains) {
		this.Contains = Contains;
	}
	/**
	 * Sets the RML object
	 * @param Equals {@link Equals}
	 */
	@XmlElement
	public void setEquals(Equals Equals) {
		this.Equals = Equals;
	}
	/**
	 * Sets the RML object
	 * @param Remove {@link Remove}
	 */
	@XmlElement
	public void setRemove(Remove Remove) {
		this.Remove = Remove;
	}
	/**
	 * Sets the RML object
	 * @param Replace {@link Replace}
	 */
	@XmlElement
	public void setReplace(Replace Replace) {
		this.Replace = Replace;
	}
	/**
	 * Sets the RML object
	 * @param Split {@link Split}
	 */
	@XmlElement
	public void setSplit(Split Split) {
		this.Split = Split;
	}
	/**
	 * Sets the RML object
	 * @param Substring {@link Substring}
	 */
	@XmlElement
	public void setSubstring(Substring Substring) {
		this.Substring = Substring;
	}
	/**
	 * Sets the RML object
	 * @param TryParse {@link TryParse}
	 */
	@XmlElement
	public void setTryParse(TryParse TryParse) {
		this.TryParse = TryParse;
	}
	/**
	 * Sets the RML object
	 * @param XmlQuery {@link Page}
	 */
	@XmlElement
	public void setXmlQuery(Page XmlQuery) {
		this.XmlQuery = XmlQuery;
	}
//	/**
//	 * Sets the RML object
//	 * @param webRequest {@link WebRequest} the webRequest to set
//	 */
//	@XmlElement
//	public void setWebRequest(WebRequest webRequest) {
//		WebRequest = webRequest;
//	}
//	/**
//	 * Sets the RML object
//	 * @param webResponse {@link WebResponse} the webResponse to set
//	 */
//	@XmlElement
//	public void setWebResponse(WebResponse webResponse) {
//		WebResponse = webResponse;
//	}
	
	
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
}