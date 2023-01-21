/**
 * 
 */
package org.httprobot.core.rml.controls.operators.main;

import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.Rml;
import org.httprobot.common.rml.RmlOperator;
import org.httprobot.common.rml.datatypes.operators.Contains;
import org.httprobot.common.rml.datatypes.operators.Equals;
import org.httprobot.common.rml.datatypes.operators.Remove;
import org.httprobot.common.rml.datatypes.operators.Replace;
import org.httprobot.common.rml.datatypes.operators.Select;
import org.httprobot.common.rml.datatypes.operators.Split;
import org.httprobot.common.rml.datatypes.operators.Substring;
import org.httprobot.common.rml.datatypes.operators.TryParse;
import org.httprobot.common.rml.datatypes.operators.XmlQuery;
import org.httprobot.common.tools.CommandLineInterface;
import org.httprobot.core.rml.controls.RmlOperatorControl;

/**
 * @author joan
 *
 */
@XmlTransient
public class MainOperatorControl<TMainOperatorMessage extends RmlOperator> extends RmlOperatorControl 
{

	/**
	 * -5228825002159530336L
	 */
	private static final long serialVersionUID = -5228825002159530336L;
	
	TMainOperatorMessage operator;
	
	ContainsControl contains_control;
	EqualsControl equals_control;
	RemoveControl remove_control;
	ReplaceControl replace_control;
	SelectControl select_control;
	SplitControl split_control;
	SubstringControl substring_control;
	TryParseControl try_parse_control;
	XmlQueryControl xml_query_control;
	
	public boolean hasChilds()
	{
		if(contains_control == null &&
			equals_control == null &&
			remove_control == null &&
			replace_control == null &&
			select_control == null &&
			split_control == null &&
			substring_control == null &&
			try_parse_control == null &&
			xml_query_control == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	/**
	 * Main RML operator controller default class constructor.
	 */
	public MainOperatorControl() 
	{
		super();
	}
	/**
	 * Main RML operator controller class constructor.
	 * @param parent {@link IRmlListener} the parent
	 * @param message {@link Rml} the message
	 */
	public MainOperatorControl(IRmlListener parent, RmlOperator message) 
	{
		super(parent, message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnContainsChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnContainsChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnContainsInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnContainsInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnContainsLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnContainsLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			try
			{	
				this.operator.setContains(Contains.class.cast(e.getMessage()));
				CliCommandInputEvent(new CliEventArgs(this, Command.CONTAINS_CONTROL, e.getMessage()));
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "MainOperatorControl.OnContainsLoaded: Equals RML message expected");
			}
		}
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnContainsRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnContainsRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnContainsRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnContainsRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnContainsWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnContainsWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlInit(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException 
	{
		if(e.getMessage() != null)
		{
			try
			{
				RmlOperator rmlOperator = RmlOperator.class.cast(e.getMessage());
				
				//Set inherited data.
				this.setUuid(rmlOperator.getUuid());
				this.setInherited(rmlOperator.getInherited());
				this.setRuntimeOptions(rmlOperator.getRuntimeOptions());
				
				if(rmlOperator.getContains() != null)
				{
					this.contains_control = new ContainsControl(this, rmlOperator.getContains());
					this.addCommandOutputListener(this.contains_control);
				}
				if(rmlOperator.getEquals() != null)
				{
					this.equals_control = new EqualsControl(this, rmlOperator.getEquals());
					this.addCommandOutputListener(this.equals_control);
				}
				if(rmlOperator.getRemove() != null)
				{
					this.remove_control = new RemoveControl(this, rmlOperator.getRemove());
					this.addCommandOutputListener(this.remove_control);
				}
				if(rmlOperator.getReplace() != null)
				{
					this.replace_control = new ReplaceControl(this, rmlOperator.getReplace());
					this.addCommandOutputListener(this.replace_control);
				}
				if(rmlOperator.getSelect() != null)
				{
					this.select_control = new SelectControl(this, rmlOperator.getSelect());
					this.addCommandOutputListener(this.select_control);
				}
				if(rmlOperator.getSplit() != null)
				{
					this.split_control = new SplitControl(this, rmlOperator.getSplit());
					this.addCommandOutputListener(this.split_control);
				}
				if(rmlOperator.getSubstring() != null)
				{
					this.substring_control = new SubstringControl(this, rmlOperator.getSubstring());
					this.addCommandInputListener(this.substring_control);
				}
				if(rmlOperator.getTryParse() != null)
				{
					this.try_parse_control = new TryParseControl(this, rmlOperator.getTryParse());
					this.addCommandOutputListener(this.try_parse_control);
				}
				if(rmlOperator.getXmlQuery() != null)
				{
					this.xml_query_control = new XmlQueryControl(this, rmlOperator.getXmlQuery());
					this.addCommandOutputListener(this.xml_query_control);
				}
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "MainOperatorControl.OnControlInit: RML operator message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			try
			{
				RmlOperator rmlOperator = RmlOperator.class.cast(e.getMessage());
				
				if(rmlOperator.getContains() != null)
				{
					this.contains_control.setMessage(rmlOperator.getContains());
				}
				if(rmlOperator.getEquals() != null)
				{
					this.equals_control.setMessage(rmlOperator.getEquals());
				}
				if(rmlOperator.getRemove() != null)
				{
					this.remove_control.setMessage(rmlOperator.getRemove());
				}
				if(rmlOperator.getReplace() != null)
				{
					this.replace_control.setMessage(rmlOperator.getReplace());
				}
				if(rmlOperator.getSelect() != null)
				{
					this.select_control.setMessage(rmlOperator.getSelect());
				}
				if(rmlOperator.getSplit() != null)
				{
					this.split_control.setMessage(rmlOperator.getSplit());
				}
				if(rmlOperator.getSubstring() != null)
				{
					this.substring_control.setMessage(rmlOperator.getSubstring());
				}
				if(rmlOperator.getTryParse() != null)
				{
					this.try_parse_control.setMessage(rmlOperator.getTryParse());
				}
				if(rmlOperator.getXmlQuery() != null)
				{
					this.xml_query_control.setMessage(rmlOperator.getXmlQuery());
				}				
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "MainOperatorControl.OnControlLoaded: RML operator message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnControlWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnEqualsChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnEqualsChanged(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnEqualsInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnEqualsInit(Object sender, RmlEventArgs e) throws NotImplementedException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnEqualsLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnEqualsLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			try
			{	
				this.operator.setEquals(Equals.class.cast(e.getMessage()));
				CliCommandInputEvent(new CliEventArgs(this, Command.EQUALS_CONTROL, e.getMessage()));
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "MainOperatorControl.OnEqualsLoaded: Equals RML message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnEqualsRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnEqualsRead(Object sender, RmlEventArgs e)throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnEqualsRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnEqualsRendered(Object sender, RmlEventArgs e)throws NotImplementedException 
	{
		
	}
	
	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnEqualsWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnEqualsWrite(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnRemoveChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRemoveChanged(Object sender, RmlEventArgs e) throws NotImplementedException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnRemoveInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRemoveInit(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnRemoveLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRemoveLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			try
			{	
				this.operator.setRemove(Remove.class.cast(e.getMessage()));
				CliCommandInputEvent(new CliEventArgs(this, Command.REMOVE_CONTROL, e.getMessage()));
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "MainOperatorControl.OnEqualsLoaded: Equals RML message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnRemoveRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRemoveRead(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnRemoveRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRemoveRendered(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnRemoveWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRemoveWrite(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnReplaceChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnReplaceChanged(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnReplaceInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnReplaceInit(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnReplaceLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnReplaceLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			try
			{	
				this.operator.setReplace(Replace.class.cast(e.getMessage()));
				CliCommandInputEvent(new CliEventArgs(this, Command.REPLACE_CONTROL, e.getMessage()));
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "MainOperatorControl.OnEqualsLoaded: Equals RML message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnReplaceRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnReplaceRead(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnReplaceRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnReplaceRendered(Object sender, RmlEventArgs e) throws NotImplementedException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnReplaceWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnReplaceWrite(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnSelectChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSelectChanged(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnSelectInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSelectInit(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnSelectLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSelectLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			try
			{	
				this.operator.setSelect(Select.class.cast(e.getMessage()));
				CliCommandInputEvent(new CliEventArgs(this, Command.SELECT_CONTROL, e.getMessage()));
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "MainOperatorControl.OnEqualsLoaded: Equals RML message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnSelectRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSelectRead(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnSelectRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSelectRendered(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnSelectWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSelectWrite(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnSplitChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSplitChanged(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnSplitInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSplitInit(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnSplitLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSplitLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			try
			{	
				this.operator.setSplit(Split.class.cast(e.getMessage()));
				CliCommandInputEvent(new CliEventArgs(this, Command.SPLIT_CONTROL, e.getMessage()));
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "MainOperatorControl.OnEqualsLoaded: Equals RML message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnSplitRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSplitRead(Object sender, RmlEventArgs e)	 throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnSplitRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSplitRendered(Object sender, RmlEventArgs e)	throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnSplitWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSplitWrite(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnSubstringChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSubstringChanged(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnSubstringInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSubstringInit(Object sender, RmlEventArgs e)	throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnSubstringLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSubstringLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			try
			{	
				this.operator.setSubstring(Substring.class.cast(e.getMessage()));
				CliCommandInputEvent(new CliEventArgs(this, Command.SUBSTRING_CONTROL, e.getMessage()));
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "MainOperatorControl.OnEqualsLoaded: Equals RML message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnSubstringRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSubstringRead(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnSubstringRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSubstringRendered(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnSubstringWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSubstringWrite(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnTryParseChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnTryParseChanged(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnTryParseInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnTryParseInit(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnTryParseLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnTryParseLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			try
			{	
				this.operator.setTryParse(TryParse.class.cast(e.getMessage()));
				CliCommandInputEvent(new CliEventArgs(this, Command.TRY_PARSE_CONTROL, e.getMessage()));
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "MainOperatorControl.OnEqualsLoaded: Equals RML message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnTryParseRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnTryParseRead(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnTryParseRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnTryParseRendered(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnTryParseWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnTryParseWrite(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnXmlQueryChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnXmlQueryChanged(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnXmlQueryInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnXmlQueryInit(Object sender, RmlEventArgs e) throws NotImplementedException 
	{
		
}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnXmlQueryLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnXmlQueryLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			try
			{	
				this.operator.setXmlQuery(XmlQuery.class.cast(e.getMessage()));
				CliCommandInputEvent(new CliEventArgs(this, Command.XML_QUERY_CONTROL, e.getMessage()));
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "MainOperatorControl.OnEqualsLoaded: Equals RML message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnXmlQueryRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnXmlQueryRead(Object sender, RmlEventArgs e) throws NotImplementedException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnXmlQueryRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnXmlQueryRendered(Object sender, RmlEventArgs e) throws NotImplementedException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlOperatorControl#OnXmlQueryWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnXmlQueryWrite(Object sender, RmlEventArgs e) throws NotImplementedException 
	{

	}

}
