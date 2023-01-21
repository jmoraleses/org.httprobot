/**
 * 
 */
package org.httprobot.common.placeholders.operators.html;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.Html;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;

/**
 * @author joan
 *
 */
@XmlRootElement
public class Anchor extends Html
{
	/**
	 * -7357679447564974949L
	 */
	private static final long serialVersionUID = -7357679447564974949L;

	private String href;
	private String hrefLang;
	private String name;
	private String textContent;
	private String charset;
	private String type;
	private String target;

	/* (non-Javadoc)
	 * @see org.httprobot.common.Placeholder#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		super.OnObjectUnmarshalled(sender, e);
		
		setHref(((Anchor)e.getRml()).getHref());
		setHrefLang(((Anchor)e.getRml()).getHrefLang());
		setName(((Anchor)e.getRml()).getName());
		setTextContent(((Anchor)e.getRml()).getTextContent());
		setCharset(((Anchor)e.getRml()).getCharset());
		setType(((Anchor)e.getRml()).getType());
		setTargetAttribute(((Anchor)e.getRml()).getTarget());	
	}
	/**
	 * 
	 */
	public Anchor() 
	{

	}
	/**
	 * @param hrefAttribute the hrefAttribute to set
	 */
	@XmlAttribute
	public void setHref(String hrefAttribute) {
		this.href = hrefAttribute;
	}
	/**
	 * @return the hrefAttribute
	 */
	public String getHref() {
		return href;
	}
	/**
	 * @return the nameAttribute
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param nameAttribute the nameAttribute to set
	 */
	@XmlAttribute
	public void setName(String nameAttribute) {
		this.name = nameAttribute;
	}
	/**
	 * @return the textContent
	 */
	public String getTextContent() {
		return textContent;
	}
	/**
	 * @param textContent the textContent to set
	 */
	@XmlAttribute
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	/**
	 * @return the charsetAttribute
	 */
	public String getCharset() {
		return charset;
	}
	/**
	 * @param charsetAttribute the charsetAttribute to set
	 */
	@XmlAttribute
	public void setCharset(String charsetAttribute) {
		this.charset = charsetAttribute;
	}
	/**
	 * @return the typeAttribute
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param typeAttribute the typeAttribute to set
	 */
	@XmlAttribute
	public void setType(String typeAttribute) {
		this.type = typeAttribute;
	}
	/**
	 * @return the targetAttribute
	 */
	public String getTarget() {
		return target;
	}
	/**
	 * @param targetAttribute the targetAttribute to set
	 */
	@XmlAttribute
	public void setTargetAttribute(String targetAttribute) {
		this.target = targetAttribute;
	}
	/**
	 * @param hrefLangAttribute the hrefLangAttribute to set
	 */
	@XmlAttribute
	public void setHrefLang(String hrefLangAttribute) {
		this.hrefLang = hrefLangAttribute;
	}
	/**
	 * @return the hrefLangAttribute
	 */
	public String getHrefLang() {
		return hrefLang;
	}
}
