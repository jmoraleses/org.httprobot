package org.httprobot.common.rml.datatypes;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.Rml;
/**
 * @author Joan
 * Rules RML object class. Inherits {@link Rml}.
 */
@XmlRootElement
public class Rules extends Rml
{
	/**
	 * -1586880356308229735L
	 */
	private static final long serialVersionUID = -1586880356308229735L;
	
	private ArrayList<Rule> Rule = null;
	/**
	 * Gets the rules
	 * @return {@link ArrayList} of {@link Rule}
	 */
	public ArrayList<Rule> getRule() {
		return Rule;
	}
	/**
	 * Sets the rules
	 * @param Rule {@link ArrayList} of {@link Rule}
	 */
	@XmlElement
	public void setRule(ArrayList<Rule> Rule) {
		this.Rule = Rule;
	}
	/**
	 * Rules class constructor.
	 */
	public Rules() 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
		setRule(((Rules)e.getRml()).getRule());
	}
}
