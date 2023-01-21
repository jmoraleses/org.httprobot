package org.httprobot.common.rml.datatypes.operators;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.Rml;
import org.httprobot.common.rml.RmlOperator;

/**
 * @author Joan
 * XmlQuery RML object class. Inherits {@link Rml}.
 */
@XmlRootElement
public class XmlQuery extends RmlOperator
{
	/**
	 * -2633250624543608352L
	 */
	private static final long serialVersionUID = -2633250624543608352L;
	private String xPath;	
	private GetField GetField;
	/**
	 * XmlQuery class constructor.
	 */
	public XmlQuery(){ }

	@XmlAttribute
	public void setxPath(String xPath) {
		this.xPath = xPath;
	}


	public String getxPath() {
		return xPath;
	}

	@XmlElement
	public void setGetField(GetField getField) {
		GetField = getField;
	}

	public GetField getGetField() {
		return GetField;
	}

	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		// TODO Auto-generated method stub
		super.OnObjectUnmarshalled(sender, e);
		setxPath(((XmlQuery)e.getRml()).getxPath());
		setGetField(((XmlQuery)e.getRml()).getGetField());
	}
	
	
}