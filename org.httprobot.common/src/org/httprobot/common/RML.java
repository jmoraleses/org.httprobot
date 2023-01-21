package org.httprobot.common;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.io.MarshalLayer;


/**
 * Resource Model Language implementation class. Inherits {@link MarshalLayer}. 
 * <br>
 * All RML elements have to inherit this class.
 * <br>
 * @author Joan 
 */
@XmlTransient
public abstract class RML 
		extends MarshalLayer {
	
	/**
	 * 1882231386993156478L
	 */
	private static final long serialVersionUID = 1882231386993156478L;
	private Boolean inherited;
	/**
	 * @param inherited the inheritedOptions to set
	 */
	@XmlAttribute
	public void setInherited(Boolean inherited)
	{
		this.inherited = inherited;
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
			throws InconsistenMessageException {
		
		//always call first super class method
		super.OnCommandOutput(sender, e);
		
		switch (e.getCommand()) 
		{
			case SET_UUID:
				this.inherited = ((IControlListener)e.getSource()).getInherited();
				break;
			default:
				break;
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.io.MarshalObject#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		// Assign read data.
		RML rml = e.getRml();
		setUuid(rml.getUuid());
		setInherited(rml.getInherited());
		setRuntimeOptions(rml.getRuntimeOptions());
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.io.MarshalObject#OnObjectMarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectMarshalled(Object sender, MarshallerEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
}
