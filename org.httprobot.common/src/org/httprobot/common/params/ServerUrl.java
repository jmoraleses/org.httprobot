/**
 * 
 */
package org.httprobot.common.params;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.Parameter;
import org.httprobot.common.definitions.Enums.ParameterType;

/**
 * ServerUrl RML parameter class.
 * @author joan
 *
 */
@XmlRootElement
public class ServerUrl extends Parameter 
{

	/**
	 * 4297673528224909336L
	 */
	private static final long serialVersionUID = 4297673528224909336L;

	/**
	 * ServerUrl parameter default class constructor.
	 */
	public ServerUrl() 
	{
		super();
		this.setParamType(ParameterType.CONSTANT);
	}
}