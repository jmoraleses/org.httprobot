/**
 * 
 */
package org.httprobot.core.contents.solr;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.solr.common.SolrInputField;
import org.httprobot.common.contents.FieldRef;

import com.gargoylesoftware.htmlunit.html.DomNode;

/**
 * Input document field class. Inherits {@link SolrInputField}.
 * <br>
 * @author joan
 *
 */
@XmlRootElement
public class InputField extends SolrInputField
{
	/**
	 * 8163553473367022068L
	 */
	private static final long serialVersionUID = 8163553473367022068L;

	FieldRef fieldRef;
	Boolean isParsed;
	Boolean isDomNode;
	
	/**
	 * @param n
	 */
	public InputField(FieldRef fieldRef) 
	{
		super(fieldRef.getName());

		//Set inherited data
		this.fieldRef = fieldRef;
	}
	public InputField(FieldRef fieldRef, SolrInputField solrField)
	{
		super(fieldRef.getName());
		
		this.fieldRef = fieldRef;
		this.setValue(solrField.getValue(), solrField.getBoost());
	}
	/* (non-Javadoc)
	 * @see org.apache.solr.common.SolrInputField#deepCopy()
	 */
	@Override
	public SolrInputField deepCopy() {

		return super.deepCopy();
	}
	public InputField deepInputCopy()
	{
		InputField clone = new InputField(this.fieldRef, this.deepCopy());
		
		return clone;
	}
	/**
	 * @param fieldType the fieldType to set
	 */
	@XmlElement
	public void setFieldRef(FieldRef fieldType) 
	{
		this.fieldRef = fieldType;
	}
	/**
	 * @return the fieldType
	 */
	public FieldRef getFieldRef() 
	{
		return fieldRef;
	}
	/* (non-Javadoc)
	 * @see org.apache.solr.common.SolrInputField#setValue(java.lang.Object, float)
	 */
	@Override
	public void setValue(Object v, float b) {

		if(v instanceof DomNode)
		{
			this.setIsDomNode(true);
			setIsParsed(false);
		}
		else
		{
			this.setIsDomNode(false);
			
			switch (fieldRef.getDataType()) 
			{
				case BOOLEAN:
					if(Boolean.class.cast(v) != null)
					{
						setIsParsed(true);
					}
					break;
					
				case TEXT:
					if(String.class.cast(v) != null)
					{
						setIsParsed(true);
					}
					//TODO Anything else...?
					break;
		
				default:
					break;
			}
		}
		
		//Call super method
		super.setValue(v, b);
	}
	/**
	 * @param parsed the parsed to set
	 */
	public void setIsParsed(Boolean parsed) 
	{
		this.isParsed = parsed;
	}
	/**
	 * @return the parsed
	 */
	public Boolean getIsParsed() 
	{
		return isParsed;
	}
	/**
	 * @param isDomNode the isDomNode to set
	 */
	public void setIsDomNode(Boolean isDomNode) 
	{
		this.isDomNode = isDomNode;
	}
	/**
	 * @return the isDomNode
	 */
	public Boolean getIsDomNode() 
	{
		return isDomNode;
	}
}