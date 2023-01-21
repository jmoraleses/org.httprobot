package org.httprobot.common.rml.datatypes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.definitions.Enums.RmlActionType;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.Rml;

/**
 * @author Joan
 * Action RML object class. Inherits {@link Rml2}.
 */
@XmlRootElement
public class Action extends Rml
{
	/**
	 * -2265946670484997201L
	 */
	private static final long serialVersionUID = -2265946670484997201L;
	
	private String httpAddress = null;
	private RmlActionType type = null;
	private String method = null;
	private WebOptions webOptions = null;
	/**
	 * Action class constructor.
	 */
	public Action()	{ }
	/**
	 * Gets the HTTP address.
	 * @return {@link String} the address
	 */
	public String getHttpAddress() 
	{
		return httpAddress;
	}
	/**
	 * Gets the method to run.
	 * @return {@link String} the method
	 */
	public String getMethod() 
	{
		return method;
	}
	/**
	 * Gets the type.
	 * @return {@link RmlActionType} the type
	 */
	public RmlActionType getType() 
	{
		return type;
	}
	/**
	 * Sets the HTTP address.
	 * @param httpAddress {@link String} the address
	 */
	@XmlElement
	public void setHttpAddress(String httpAddress) 
	{
		this.httpAddress = httpAddress;
	}
	/**
	 * Sets the method.
	 * @param method {@link String} the method
	 */
	@XmlElement
	public void setMethod(String method) 
	{
		this.method = method;
	}	
	/**
	 * Sets the type.
	 * @param type {@link RmlActionType} the type
	 */
	@XmlElement
	public void setType(RmlActionType type)
	{
		this.type = type;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
		this.setHttpAddress(((Action)e.getRml()).getHttpAddress());
		this.setMethod(((Action)e.getRml()).getMethod());
		this.setType(((Action)e.getRml()).getType());
		this.setWebOptions(((Action)e.getRml()).getWebOptions());
	}
	/**
	 * @param webOptions the options to set when calling web request
	 */
	@XmlElement
	public void setWebOptions(WebOptions webOptions) 
	{
		this.webOptions = webOptions;
	}
	/**
	 * @return {@link WebOptions} the settled options
	 */
	public WebOptions getWebOptions() 
	{
		return webOptions;
	}
}