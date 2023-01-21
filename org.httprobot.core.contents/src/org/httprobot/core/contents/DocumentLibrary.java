/**
 * 
 */
package org.httprobot.core.contents;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.common.contents.FieldRef;
import org.httprobot.core.contents.solr.InputDocument;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * @author joan
 *
 */
@XmlRootElement
public class DocumentLibrary extends InputDocumentLibrary<HtmlPage, FieldRef> {

	/**
	 * -6636460446831916948L
	 */
	private static final long serialVersionUID = -6636460446831916948L;

	ContentTypeRef contentTypeRef;

	public ContentTypeRef getContentTypeRef()
	{
		return this.contentTypeRef;
	}
	/**
	 * 
	 */
	public DocumentLibrary() {
		
		super();
	}
	/**
	 * @param templateDocument
	 * @param templateFields
	 */
	public DocumentLibrary(ContentTypeRef contentTypeRef, InputDocument templateDocument, FieldLibrary<FieldRef> templateFields) {
		
		super(templateDocument, templateFields);
		
		//Set inherited data
		this.contentTypeRef = contentTypeRef;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.solr.InputDocumentLibrary#put(java.lang.Object, org.httprobot.core.contents.solr.InputDocument)
	 */
	@Override
	public InputDocument put(HtmlPage key, InputDocument value) {
		
		if(value.getContentType().equals(this.contentTypeRef))
		{
			//Set listening input document
			value.addInputDocumentListener(this);
			
			//Store document
			super.put(key, value);
			
			return value;
		}
		else
		{
			return null;
		}
	}	
}
