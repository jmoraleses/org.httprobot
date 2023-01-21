package org.httprobot.common.rml.datatypes.operators;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.Rml;
import org.httprobot.common.rml.RmlOperator;

/**
 * @author Joan
 * Where RML object class. Inherits {@link Rml}.
 */
@XmlRootElement
public class Where extends RmlOperator
{
	/**
	 * 4500829644323210202L
	 */
	private static final long serialVersionUID = 4500829644323210202L;
	
	
	/**
	 * Where class constructor.
	 */
	public Where(){ }


	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
	}
}