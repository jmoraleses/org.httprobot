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
public class BannedWord extends Parameter 
{
	/**
	 * -5207913202599955509L
	 */
	private static final long serialVersionUID = -5207913202599955509L;

	/**
	 * 
	 */
	public BannedWord()
	{
		super();
		this.setParamType(ParameterType.BANNED_WORD);
	}
}