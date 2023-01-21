package org.httprobot.common.rml;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.io.MarshalObject;

/**
 * RML object class. All RML elements have to inherit this class.
 * @author Joan 
 */
@XmlTransient
public abstract class Rml extends MarshalObject
{
	private boolean inherited = true;
	/**
	 * @param inherited the inheritedOptions to set
	 */
	@XmlAttribute
	public void setInherited(Boolean inherited)
	{
		if(inherited == null)
		{
			this.inherited = true;	
		}
		else
		{
			this.inherited = inherited;
		}
	}
	/**
	 * @return the Inherited
	 */
	public Boolean getInherited() 
	{
		return this.inherited;
	}
	


	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e) 
	{
		//always call first super class method
		super.OnCommandOutput(sender, e);
		
		switch (e.getCmd()) 
		{
			case SET_INHERITANCE:
				this.inherited = ((IRmlListener)e.getSource()).getInherited();
				break;
			default:
				break;
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.io.MarshalObject#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		// Assign read data.
		Rml rml = e.getRml();
		setUuid(rml.getUuid());
		setInherited(rml.getInherited());
		
		if (!rml.getInherited())
		{
			if(rml.getRuntimeOptions() != null)
			{
				setRuntimeOptions(rml.getRuntimeOptions());	
			}
			else
			{
				throw new InconsistenMessageException(this, "\n CLI options null when Inherited = false");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.io.MarshalObject#OnObjectMarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectMarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
}
