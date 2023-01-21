package org.httprobot.core.rml.controls.operators;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.datatypes.operators.Select;
import org.httprobot.common.rml.datatypes.operators.Where;
import org.httprobot.common.rml.datatypes.operators.XmlQuery;
import org.httprobot.core.rml.controls.RmlControl;
import org.httprobot.core.rml.controls.RmlOperatorControl;

@XmlRootElement
public class XmlQueryControl extends RmlOperatorControl 
{
	/**
	 * 1188429079391753214L
	 */
	private static final long serialVersionUID = 1188429079391753214L;
	
	private Select select;
	private Where where;
	
	public Select getSelect() {
		return select;
	}

	public void setSelect(Select select) {
		this.select = select;
	}

	public Where getWhere() {
		return where;
	}

	public void setWhere(Where where) {
		this.where = where;
	}
	/**
	 * 
	 */
	public XmlQueryControl()
	{
		super();
	}
	/**
	 * @param parent
	 * @param xmlQuery
	 */
	public XmlQueryControl(RmlControl parent, XmlQuery xmlQuery) 
	{
		super(parent, xmlQuery);
	}

	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnSelectLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSelectLoaded(Object sender, RmlEventArgs e)
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnWhereLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnWhereLoaded(Object sender, RmlEventArgs e)
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e)
{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e)
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRendered(Object sender, RmlEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlInit(Object sender, RmlEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRead(Object sender, RmlEventArgs e)
			throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlWrite(Object sender, RmlEventArgs e)
			throws NotImplementedException, InconsistenMessageException 
	{
		
	}
}