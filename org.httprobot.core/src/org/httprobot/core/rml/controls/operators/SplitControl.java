package org.httprobot.core.rml.controls.operators;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.datatypes.operators.Delimiters;
import org.httprobot.common.rml.datatypes.operators.ForEach;
import org.httprobot.common.rml.datatypes.operators.Split;
import org.httprobot.core.rml.controls.RmlControl;
import org.httprobot.core.rml.controls.RmlOperatorControl;

/**
 * @author Joan
 * Split Control Class. Inherits RmlStringOperatorControl.
 */
@XmlRootElement
public class SplitControl extends RmlOperatorControl
{
	/**
	 * -6688359449287305844L
	 */
	private static final long serialVersionUID = -6688359449287305844L;
	private ArrayList<String> delimiters;
	private ArrayList<Object> for_each;
	
	private Integer delimiters_count;
	private Integer for_each_count;
	
	private DelimitersControl delimiters_control = null;
	private ForEachControl for_each_control = null;
	
	/**
	 * @return the delimiters
	 */
	public ArrayList<String> getDelimiters() {
		return delimiters;
	}
	/**
	 * @return the for_each
	 */
	public ArrayList<Object> getFor_each() {
		return for_each;
	}
	/**
	 * @return the delimiters_count
	 */
	public Integer getDelimiters_count() {
		return delimiters_count;
	}
	/**
	 * @return the for_each_count
	 */
	public Integer getFor_each_count() {
		return for_each_count;
	}
	public SplitControl()
	{
		super();
	}
	/**
	 * SplitControl constructor.
	 * @param parent RmlControl the parent control
	 * @param split Split the split
	 */
	public SplitControl(RmlControl parent, Split split)
	{
		super(parent, split);
		
		this.delimiters = split.GetDelimiters().getDelimiter();
		this.for_each = split.getForEach().getItems();		
		this.delimiters_count = split.GetDelimiters().getDelimiter().size();
		this.for_each_count = split.getForEach().getItems().size();
		
		if((this.for_each == null) && (this.delimiters == null))
		{
			//this.LoadControl();
			this.setMessage(split);
		}
		else
		{			
			Delimiters delimiters = new Delimiters();
			delimiters.setDelimiters(this.delimiters);
						
			ForEach for_each = new ForEach();
			for_each.setItems(for_each.getItems());
			
			this.delimiters_control = new DelimitersControl(this, delimiters);
			this.for_each_control = new ForEachControl(this, for_each);
			
			this.delimiters_control.setMessage(delimiters);
			this.for_each_control.setMessage(for_each);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnDelimitersLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	public void OnDelimitersLoaded(Object sender, RmlEventArgs e)
	{		
		this.delimiters = Delimiters.class.cast(e.getMessage()).getDelimiters().getDelimiter();
		
		if(this.for_each_count == 0)
		{
			Split split = new Split();
			Delimiters delimiters = new Delimiters();
			delimiters.setDelimiters(this.delimiters);
			split.setDelimiters(delimiters);
			
			this.setMessage(split);
		}
		else
		{
			
		}
	}
	public void OnForEachEventLoaded(Object sender, RmlEventArgs e)
	{
		this.for_each = ForEach.class.cast(e.getMessage()).getItems();
		
		if(this.delimiters_count == 0)
		{
			Split split = new Split();
			Delimiters delimiters = new Delimiters();
			
			this.delimiters = (Delimiters.class.cast(e.getMessage()).getDelimiters().getDelimiter());
			delimiters.setDelimiters(this.delimiters);;
			split.setDelimiters(delimiters);
			
			ForEach for_each = new ForEach();
			for_each.setItems(this.for_each);;
			split.setForEach(for_each);
			
			this.setMessage(split);
			
		}
		else
		{
			Split split = new Split();
			Delimiters delimiters = new Delimiters();
			delimiters.setDelimiters(this.delimiters);
			split.setDelimiters(delimiters);
			
			this.setMessage(split);
			this.setIsRendered(true);
		}
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e) 
	{
		this.setIsRendered(true);
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