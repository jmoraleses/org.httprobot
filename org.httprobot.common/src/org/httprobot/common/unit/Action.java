package org.httprobot.common.unit;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.Unit;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.params.BannedWord;
import org.httprobot.common.params.Constant;

/**
 * Action message class. Inherits {@link Unit}.
 * @author Joan
 * 
 */
@XmlRootElement
public class Action extends Unit
{
	/**
	 * -2265946670484997201L
	 */
	private static final long serialVersionUID = -2265946670484997201L;
	
	private Boolean strictMode = null;
	private String httpAddress = null;
	
	private String method = null;
	private WebOptions webOptions = null;
	private ArrayList<Constant> constants = null;
	private ArrayList<BannedWord> bwords = null;
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

	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
		this.setHttpAddress(((Action)e.getRml()).getHttpAddress());
		this.setMethod(((Action)e.getRml()).getMethod());
		
		this.setWebOptions(((Action)e.getRml()).getWebOptions());
		this.setConstants(((Action)e.getRml()).getConstants());
		this.setStrictMode(((Action)e.getRml()).getStrictMode());
		this.setBwords(((Action)e.getRml()).getBwords());
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
	public void setConstants(ArrayList<Constant> constants) 
	{
		this.constants = constants;
	}
	/**
	 * @return {@link Constant} parameters.
	 */
	public ArrayList<Constant> getConstants()
	{
		if(constants != null)
		{
			return constants;	
		}
		else
		{
			constants = new ArrayList<Constant>();
			return constants;
		}		
	}
	public void setBwords(ArrayList<BannedWord> bwords)
	{
		this.bwords = bwords;
	}
	public ArrayList<BannedWord> getBwords()
	{
		if(this.bwords == null)
			this.bwords = new ArrayList<BannedWord>();
		return bwords;
	}
	@XmlAttribute
	public void setStrictMode(Boolean strictMode) 
	{
		this.strictMode = strictMode;
	}
	public Boolean getStrictMode()
	{
		return strictMode;
	}
}