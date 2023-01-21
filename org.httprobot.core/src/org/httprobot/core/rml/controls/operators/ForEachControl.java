package org.httprobot.core.rml.controls.operators;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.Rml;
import org.httprobot.common.rml.datatypes.operators.ForEach;
import org.httprobot.core.rml.controls.RmlOperatorControl;

/**
 * ForEach control class. Inherits {@link RmlOperatorControl}.
 * @author Joan
 * 
 */
@XmlRootElement
public class ForEachControl extends RmlOperatorControl
{
	/**
	 * -2419721130268221852L
	 */
	private static final long serialVersionUID = -2419721130268221852L;
	private ArrayList<Object> for_each_elements = null;
	private Integer for_each_elements_num = 0;
	private Integer for_each_elements_counter = 0;
	/**
	 * @return ArrayList<Object> the for_each_elements
	 */
	public ArrayList<Object> getFor_each_elements() {
		return for_each_elements;
	}
	/**
	 * @return Integer the for_each_elements_num
	 */
	public Integer getFor_each_elements_num() {
		return for_each_elements_num;
	}
	/**
	 * Gets the ForEach count.
	 * @return Integer the for_each_elements_counter
	 */
	public Integer getFor_each_elements_counter() {
		return for_each_elements_counter;
	}
	public ForEachControl()
	{
		super();
	}
	/**
	 * ForEachControl class constructor.
	 * @param parent {@link IRmlListener} the parent listener
	 * @param for_each {@link ForEach} the for each
	 */
	public ForEachControl(IRmlListener parent, ForEach for_each)
	{
		super(parent, for_each);
		this.for_each_elements = new ArrayList<Object>();
		this.for_each_elements_num = for_each.getItems().size();
		
		if(this.for_each_elements_num > 0)
		{
			for(Object control : for_each.getItems())
			{

				if(control != null)
				{
					RmlOperatorControl ctrl = GetOperatorControl(control);
					this.for_each_elements.add(ctrl);
				}	
			}
		}
		else
		{
			this.setMessage(for_each);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnContainsLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnContainsLoaded(Object sender, RmlEventArgs e)
	{
		CheckEventArgs(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnEqualsLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnEqualsLoaded(Object sender, RmlEventArgs e)
	{
		CheckEventArgs(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnRemoveLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnRemoveLoaded(Object sender, RmlEventArgs e)
	{
		CheckEventArgs(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnReplaceLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnReplaceLoaded(Object sender, RmlEventArgs e)
	{
		CheckEventArgs(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnSplitLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnSplitLoaded(Object sender, RmlEventArgs e)
	{
		CheckEventArgs(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnSubstringLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnSubstringLoaded(Object sender, RmlEventArgs e)
	{
		CheckEventArgs(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnTryParseLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnTryParseLoaded(Object sender, RmlEventArgs e)
	{
		CheckEventArgs(e);
	}
	/**
	 * Checks if the current element received is the last. If true it renders control.
	 * @param e {@link RmlEventArgs} the arguments
	 */
	private void CheckEventArgs(RmlEventArgs e)
	{
		this.for_each_elements_counter++;
		this.for_each_elements = new ArrayList<Object>();
		this.for_each_elements.add((Rml)e.getMessage());
		
		if(this.for_each_elements_counter >= this.for_each_elements_num)
		{
			ForEach for_each = new ForEach();
			for_each.setItems(this.for_each_elements);
			
			this.setMessage(for_each);
			this.setIsRendered(true);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
	@Override
	public void OnControlRead(Object sender, RmlEventArgs e)
			throws NotImplementedException, InconsistenMessageException
	{
		
	}
	@Override
	public void OnControlWrite(Object sender, RmlEventArgs e)
			throws NotImplementedException, InconsistenMessageException
	{
		
	}
}