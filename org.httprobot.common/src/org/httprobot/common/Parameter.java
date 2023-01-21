/**
 * 
 */
package org.httprobot.common;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.definitions.Enums.ParameterType;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;

/**
 * Parameter message class. Inherits {@link RML}.
 * <br>
 * @author joan
 *
 */
@XmlTransient
public class Parameter 
		extends RML {
	
	/**
	 * -7483399655219895038L
	 */
	private static final long serialVersionUID = -7483399655219895038L;
	
	private String paramName = null;
	private ParameterType paramType = null;
	private String value = null;
	
	/**
	 * 
	 */
	public Parameter() 
	{
		super();
	}
	/**
	 * @return
	 */
	public String getParamName() 
	{
		return paramName;
	}
	/**
	 * @return
	 */
	public ParameterType getParamType() {
		return paramType;
	}

	/**
	 * @return
	 */
	public String getValue() 
	{
		return value;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.RML#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		super.OnObjectUnmarshalled(sender, e);
		
		setValue(((Parameter)e.getRml()).getValue());
		setParamName(((Parameter)e.getRml()).getParamName());
		setParamType(((Parameter)e.getRml()).getParamType());
	}
	/**
	 * @param paramName
	 */
	@XmlAttribute
	public void setParamName(String paramName) 
	{
		this.paramName = paramName;
	}
	/**
	 * @param paramType
	 */
	@XmlAttribute
	public void setParamType(ParameterType paramType) {
		this.paramType = paramType;
	}
	/**
	 * @param paramValue
	 */
	@XmlAttribute
	public void setValue(String paramValue) 
	{
		this.value = paramValue;
	}
}
