package org.httprobot.common.datatypes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.DataType;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.HtmlUnit;
import org.httprobot.common.placeholders.HttpAddress;
/**
 * Field object class. Inherits {@link DataType}.
 * @author Joan
 * 
 */
@XmlRootElement
public class Field extends DataType
{
	
	/**
	 * -3294678169860459611L
	 */
	private static final long serialVersionUID = -3294678169860459611L;
	private String FieldName = null;
	private String FieldType = null;

	private HttpAddress httpAddress;
	private HtmlUnit HtmlUnit;
	/**
	 * @return the webRequest
	 */
	public HttpAddress getHttpAddress()
	{
		return httpAddress;
	}
	/**
	 * @param webRequest the webRequest to set
	 */
	@XmlElement
	public void setHttpAddress(HttpAddress webRequest) 
	{
		httpAddress = webRequest;
	}
	/**
	 * @return the webResponse
	 */
	public HtmlUnit getHtmlUnit()
	{
		return HtmlUnit;
	}
	/**
	 * @param htmlBody the webResponse to set
	 */
	@XmlElement
	public void setHtmlUnit(HtmlUnit htmlBody)
	{
		HtmlUnit = htmlBody;
	}
	/**
	 * Field class constructor.
	 */
	public Field()
	{
	}
	/**
	 * Gets the field name
	 * @return {@link String} the name
	 */
	public String getFieldName() 
	{
		return FieldName;
	}
	/**
	 * Gets the FieldType
	 * @return {@link String} the type
	 */
	public String getFieldType() 
	{
		return FieldType;
	}
	/**
	 * Sets the field name
	 * @param fieldName {@link String} the field name
	 */
	@XmlElement
	public void setFieldName(String fieldName) 
	{
		this.FieldName = fieldName;
	}
	/**
	 * Sets the field type
	 * @param FieldType {@link String} the field type
	 */
	@XmlElement
	public void setFieldType(String FieldType)
	{
		this.FieldType = FieldType;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.RML#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		super.OnObjectUnmarshalled(sender, e);
		
		this.setFieldName(((Field)e.getRml()).getFieldName());
		this.setFieldType(((Field)e.getRml()).getFieldType());
		this.setHttpAddress(((Field)e.getRml()).getHttpAddress());
		this.setHtmlUnit(((Field)e.getRml()).getHtmlUnit());
	}
}