/**
 * 
 */
package org.httprobot.core.contents;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.core.contents.solr.InputDocument;

/**
 * @author joan
 *
 */
@XmlRootElement
public class TemplateLibrary 
	extends InputLibrary<ContentTypeRef> {

	/**
	 * -8559906225975940502L
	 */
	private static final long serialVersionUID = -8559906225975940502L;

	TemplateFieldLibrary templateFieldLibrary;
	
	public TemplateFieldLibrary getFieldsLibrary()
	{
		return this.templateFieldLibrary;
	}
	/**
	 * @param templateDocument
	 * @param templateFields
	 */
	public TemplateLibrary() 
	{
		super();
		
		this.templateFieldLibrary = new TemplateFieldLibrary();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.solr.InputLibrary#get(java.lang.Object)
	 */
	@Override
	public InputDocument get(Object key) 
	{
		//Return a copy of the template
		return super.get(key).deepInputCopy();
	}
}
