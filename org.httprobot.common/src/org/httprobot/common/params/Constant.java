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
public class Constant extends Parameter
{
	/**
	 * 5668711144298624003L
	 */
	private static final long serialVersionUID = 5668711144298624003L;

	public Constant()
	{
		super();
		this.setParamType(ParameterType.CONSTANT);
	}
}
