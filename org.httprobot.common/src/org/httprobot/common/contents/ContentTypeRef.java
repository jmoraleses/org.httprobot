/**
 * 
 */
package org.httprobot.common.contents;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.RML;
import org.httprobot.common.Content;

/**
 * Content type reference class.
 * Inherits {@link RML}.
 * @author joan
 *
 */
@XmlRootElement
public class ContentTypeRef extends Content
{
	/**
	 * 8186380316232471108L
	 */
	private static final long serialVersionUID = 8186380316232471108L;
	
	private String contentTypeName;

	/**
	 * @return the contentTypeName
	 */
	public String getContentTypeName() {
		return contentTypeName;
	}
	/**
	 * @param contentTypeName the contentTypeName to set
	 */
	@XmlAttribute
	public void setContentTypeName(String contentTypeName) {
		this.contentTypeName = contentTypeName;
	}
	/**
	 * 
	 */
	public ContentTypeRef() {
		// TODO Auto-generated constructor stub
	}
}
