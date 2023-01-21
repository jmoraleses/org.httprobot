package org.httprobot.common.rml.datatypes;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.Rml;

/**
 * @author Joan
 * Steps RML object class. Inherits {@link Rml}.
 */
@XmlRootElement
public class Steps extends Rml{
	
	/**
	 * -5271904432348146049L
	 */
	private static final long serialVersionUID = -5271904432348146049L;
	
	private ArrayList<Step> Step = null;
	/**
	 * Gets the steps.
	 * @return {@link ArrayList} of {@link Step}
	 */
	public ArrayList<Step> getStep() 
	{
		if(this.Step == null)
		{
			this.Step = new ArrayList<Step>();
			return this.Step;
		}
		else
		{
			return this.Step;
		}
	}
	/**
	 * Sets the steps.
	 * @param Step {@link ArrayList} of {@link Step}
	 */
	public void setStep(ArrayList<Step> Step) {
		this.Step = Step;
	}	
	/**
	 * Steps class constructor.
	 */
	public Steps() 
	{ 
		
	}
	
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
//		super.OnObjectUnmarshalled
		setStep(((Steps)e.getRml()).getStep());
	}	
}