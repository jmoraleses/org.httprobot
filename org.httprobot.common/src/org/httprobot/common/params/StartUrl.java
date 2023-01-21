/**
 * 
 */
package org.httprobot.common.params;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.Parameter;
import org.httprobot.common.definitions.Enums.ParameterType;

/**
 * @author joan
 *
 */
@XmlRootElement
public class StartUrl extends Parameter 
{

	/**
	 * 941906864503016982L
	 */
	private static final long serialVersionUID = 941906864503016982L;

	/**
	 * 
	 */
	public StartUrl() 
	{
		super();
		this.setParamType(ParameterType.CONSTANT);
	}

}
