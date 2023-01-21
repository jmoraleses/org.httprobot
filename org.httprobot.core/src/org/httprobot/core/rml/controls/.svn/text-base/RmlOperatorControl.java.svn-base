package org.httprobot.core.rml.controls;

import java.util.Vector;
import java.util.concurrent.locks.Condition;

import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.definitions.Enums.RmlEventType;
import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.Rml;
import org.httprobot.common.rml.datatypes.Rule;
import org.httprobot.common.rml.datatypes.operators.Contains;
import org.httprobot.common.rml.datatypes.operators.Delimiters;
import org.httprobot.common.rml.datatypes.operators.EndIndex;
import org.httprobot.common.rml.datatypes.operators.Equals;
import org.httprobot.common.rml.datatypes.operators.ForEach;
import org.httprobot.common.rml.datatypes.operators.GetField;
import org.httprobot.common.rml.datatypes.operators.Remove;
import org.httprobot.common.rml.datatypes.operators.Replace;
import org.httprobot.common.rml.datatypes.operators.Select;
import org.httprobot.common.rml.datatypes.operators.Split;
import org.httprobot.common.rml.datatypes.operators.StartIndex;
import org.httprobot.common.rml.datatypes.operators.Substring;
import org.httprobot.common.rml.datatypes.operators.TryParse;
import org.httprobot.common.rml.datatypes.operators.Where;
import org.httprobot.common.rml.datatypes.operators.XmlQuery;
import org.httprobot.common.tools.CommandLineInterface;
import org.httprobot.core.rml.controls.interfaces.IRmlDataTypeListener;
import org.httprobot.core.rml.controls.interfaces.IRmlOperatorListener;
import org.httprobot.core.rml.controls.operators.DelimitersControl;
import org.httprobot.core.rml.controls.operators.EndIndexControl;
import org.httprobot.core.rml.controls.operators.ForEachControl;
import org.httprobot.core.rml.controls.operators.GetFieldControl;
import org.httprobot.core.rml.controls.operators.StartIndexControl;
import org.httprobot.core.rml.controls.operators.WhereControl;
import org.httprobot.core.rml.controls.operators.interfaces.IConditionListener;
import org.httprobot.core.rml.controls.operators.interfaces.IContainsListener;
import org.httprobot.core.rml.controls.operators.interfaces.IDelimitersListener;
import org.httprobot.core.rml.controls.operators.interfaces.IEndIndexListener;
import org.httprobot.core.rml.controls.operators.interfaces.IEqualsListener;
import org.httprobot.core.rml.controls.operators.interfaces.IForEachListener;
import org.httprobot.core.rml.controls.operators.interfaces.IGetFieldListener;
import org.httprobot.core.rml.controls.operators.interfaces.IRemoveListener;
import org.httprobot.core.rml.controls.operators.interfaces.IReplaceListener;
import org.httprobot.core.rml.controls.operators.interfaces.ISelectListener;
import org.httprobot.core.rml.controls.operators.interfaces.ISplitListener;
import org.httprobot.core.rml.controls.operators.interfaces.IStartIndexListener;
import org.httprobot.core.rml.controls.operators.interfaces.ISubstringListener;
import org.httprobot.core.rml.controls.operators.interfaces.ITryParseListener;
import org.httprobot.core.rml.controls.operators.interfaces.IWhereListener;
import org.httprobot.core.rml.controls.operators.interfaces.IXmlQueryListener;
import org.httprobot.core.rml.controls.operators.main.ContainsControl;
import org.httprobot.core.rml.controls.operators.main.EqualsControl;
import org.httprobot.core.rml.controls.operators.main.RemoveControl;
import org.httprobot.core.rml.controls.operators.main.ReplaceControl;
import org.httprobot.core.rml.controls.operators.main.SelectControl;
import org.httprobot.core.rml.controls.operators.main.SplitControl;
import org.httprobot.core.rml.controls.operators.main.SubstringControl;
import org.httprobot.core.rml.controls.operators.main.TryParseControl;
import org.httprobot.core.rml.controls.operators.main.XmlQueryControl;
/**
 * @author Joan
 * RML operator control class. Inherits {@link RmlDataTypeControl}. Is {@link IRmlOperatorListener}.
 */
@XmlTransient
public abstract class RmlOperatorControl extends RmlPlaceholderControl implements IRmlOperatorListener
{
	/**
	 * 1465622143637263909L
	 */
	private static final long serialVersionUID = 1465622143637263909L;
	/**
	 * EndIndex Listeners
	 */
	private Vector<IConditionListener> condition_listeners = null;
	/**
	 * EndIndex Listeners
	 */
	private Vector<IEndIndexListener> end_index_listeners = null;
	/**
	 * StartIndex Listeners
	 */
	private Vector<IStartIndexListener> start_index_listeners = null;
	/**
	 * GetField Listeners
	 */
	private Vector<IGetFieldListener> get_field_listeners = null;
	/**
	 * {@link Split} Load Listeners
	 */
	private Vector<ISplitListener> split_listeners = null;
	/**
	 * {@link Equals} Load Listeners
	 */
	private Vector<IEqualsListener> equals_listeners = null;
	/**
	 * {@link Contains} Load Listeners
	 */
	private Vector<IContainsListener> contains_listeners = null;
	/**
	 * {@link Substring} Load Listeners
	 */
	private Vector<ISubstringListener> substring_listeners = null;
	/**
	 * {@link Replace} Load Listeners
	 */
	private Vector<IReplaceListener> replace_listeners = null;
	/**
	 * {@link Remove} Load Listeners
	 */
	private Vector<IRemoveListener> remove_listeners = null;
	/**
	 * {@link ForEach} Load Listeners
	 */
	private Vector<IForEachListener> for_each_listeners = null;
	/**
	 * {@link XmlQuery} Load Listeners
	 */
	private Vector<IXmlQueryListener> xml_query_listeners = null;
	/**
	 * {@link Select} Load Listeners
	 */
	private Vector<ISelectListener> select_listeners = null;
	/**
	 * {@link Where} Load Listeners
	 */
	private Vector<IWhereListener> where_listeners = null;
	/**
	 * {@link TryParse} Render Listeners
	 */
	private Vector<ITryParseListener> try_parse_listeners = null;
	/**
	 * Delimiters Listeners
	 */
	private Vector<IDelimitersListener> delimiters_listeners = null;
	/**
	 * RML operator conrol default constructor
	 */
	public RmlOperatorControl()
	{
		super();
		InitOperatorControl(null);
	}
	/**
	 * RML operator control constructor.
	 * @param parent {@link IRmlListener} the parent listener
	 */
	public RmlOperatorControl(IRmlListener parent, Rml message)
	{
		super(parent, message);
		InitOperatorControl(message);
	}
	/**
	 * Adds {@link Condition} event {@link IRmlOperatorListener}
	 * @param listener
	 */
	public final synchronized void addConditionListener(IConditionListener listener)
	{
		condition_listeners.add(listener);
	}
	/**
	 * Adds {@link Contains} event {@link IRmlOperatorListener}
	 * @param listener
	 */
	public final synchronized void addContainsListener(IContainsListener listener)
	{
		contains_listeners.add(listener);
	}

	/**
	 * Adds {@link Delimiters} event {@link IRmlDataTypeListener}.
	 * @param listener {@link IRmlDataTypeListener} the listener
	 */
	public final synchronized void addDelimitersListener(IDelimitersListener listener)
	{
		delimiters_listeners.add(listener);
	}
	/**
	 * Adds {@link EndIndex} event {@link IRmlDataTypeListener}.
	 * @param listener {@link IRmlDataTypeListener} the listener
	 */
	public final synchronized void addEndIndexListener(IEndIndexListener listener)
	{
		end_index_listeners.add(listener);
	}
	
	/**
	 * Adds {@link Equals} event {@link IRmlOperatorListener}
	 * @param listener
	 */
	public final synchronized void addEqualsListener(IEqualsListener listener)
	{
		equals_listeners.add(listener);
	}
	/** Adds Remove event listener
	 * @param listener
	 */
	public final synchronized void addForEachListener(IForEachListener listener)
	{
		for_each_listeners.add(listener);
	}
	/**
	 * Adds {@link GetField} event {@link IRmlDataTypeListener}.
	 * @param listener {@link IRmlDataTypeListener} the listener
	 */
	public final synchronized void addGetFieldListener(IGetFieldListener listener)
	{
		get_field_listeners.add(listener);
	}
	/**
	 * Adds Remove event listener
	 * @param listener
	 */
	public final synchronized void addRemoveListener(IRemoveListener listener)
	{
		remove_listeners.add(listener);
	}
	/**
	 * Adds {@link Replace} event {@link IRmlOperatorListener}
	 * @param listener
	 */
	public final synchronized void addReplaceListener(IReplaceListener listener)
	{
		replace_listeners.add(listener);
	}
	/** Adds Select event listener
	 * @param listener
	 */
	public final synchronized void addSelectListener(ISelectListener listener)
	{
		select_listeners.add(listener);
	}
	/**
	 * Adds {@link Split} event {@link IRmlOperatorListener}
	 * @param listener
	 */
	public final synchronized void addSplitListener(ISplitListener listener)
	{
		split_listeners.add(listener);
	}
	/**
	 * Adds {@link EndIndex} event {@link IRmlDataTypeListener}.
	 * @param listener {@link IRmlDataTypeListener} the listener
	 */
	public final synchronized void addStartIndexListener(IStartIndexListener listener)
	{
		start_index_listeners.add(listener);
	}

	/**
	 * Adds Remove event listener
	 * @param listener
	 */
	public final synchronized void addSubstringListener(ISubstringListener listener)
	{
		substring_listeners.add(listener);
	}
	/**
	 * Adds {@link TryParse} event {@link IRmlOperatorListener}
	 * @param listener
	 */
	public final synchronized void addTryParseListener(ITryParseListener listener)
	{
		try_parse_listeners.add(listener);
	}
	/** Adds Where event listener
	 * @param listener
	 */
	public final synchronized void addWhereListener(IWhereListener listener)
	{
		where_listeners.add(listener);
	}
	/** Adds XmlQuery event listener
	 * @param listener
	 */
	public final synchronized void addXmlQueryListener(IXmlQueryListener listener)
	{
		xml_query_listeners.add(listener);
	}
	/**
	 * Get childen's {@link RmlOperatorControl}
	 * @param control RML Control identified
	 * @return children {@link RmlOperatorControl}
	 */
	public final RmlOperatorControl GetOperatorControl(Object control) 
	{
		try
		{
			if(RmlOperatorControl.class.cast(control) != null)
			{
				if(control instanceof Contains)
				{
					Contains contains = Contains.class.cast(control);
					if(contains != null)
					{
						ContainsControl contains_control = new ContainsControl(this, contains);
						contains_control.setMessage(contains);
						return contains_control;
					}
				}
				else if(control instanceof Equals)
				{
					Equals equals = Equals.class.cast(control);
					if(equals != null)
					{
						EqualsControl equals_control = new EqualsControl(this, equals);
						equals_control.setMessage(equals);
						return equals_control;
					}
				}
				else if(control instanceof Remove)
				{
					Remove remove = Remove.class.cast(control);
					if(remove != null)
					{
						RemoveControl remove_control = new RemoveControl(this, remove);
						remove_control.setMessage(remove);
						return remove_control;
					}
				}
				else if(control instanceof Replace)
				{
					Replace replace = Replace.class.cast(control);
					if(replace != null)
					{
						ReplaceControl replace_control = new ReplaceControl(this, replace);
						replace_control.setMessage(replace);
						return replace_control;
					}
				}
				else if(control instanceof Split)
				{
					Split split = Split.class.cast(control);
					if(split != null)
					{
						SplitControl split_control = new SplitControl(this, split);
						split_control.setMessage(split);
						return split_control;
					}
				}
				else if(control instanceof Substring)
				{
					Substring substring = Substring.class.cast(control);
					if(substring != null)
					{
						SubstringControl substring_control = new SubstringControl(this, substring);
						substring_control.setMessage(substring);
						return substring_control;
					}
				}
				else if(control instanceof TryParse)
				{
					TryParse try_parse = TryParse.class.cast(control);
					if(try_parse != null)
					{
						TryParseControl try_parse_control = new TryParseControl(this, try_parse);
						try_parse_control.setMessage(try_parse);
						return try_parse_control;
					}
				}
				else if(control instanceof EndIndexControl)
				{
					EndIndex end_index = EndIndex.class.cast(control);
					if(end_index != null)
					{
						EndIndexControl end_index_control = new EndIndexControl(this, end_index);
						end_index_control.setMessage(end_index);
						return end_index_control;
					}
				}
			}
			return null;
		}
		catch(ClassCastException e)
		{
			return null;
		}		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IConditionListener#OnConditionChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnConditionChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnConditionChanged not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IConditionListener#OnConditionInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnConditionInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnConditionInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IConditionListener#OnConditionLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnConditionLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnConditionLoaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IConditionListener#OnConditionRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnConditionRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnConditionRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IConditionListener#OnConditionRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnConditionRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnConditionRendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IConditionListener#OnConditionWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnConditionWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnConditionWrite not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IContainsControl#OnContainsChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnContainsChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnContainsChanged not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IContainsControl#OnContainsInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnContainsInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnContainsInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlOperatorListener#OnContainsLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnContainsLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnContainsLoaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IContainsControl#OnContainsRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnContainsRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnContainsRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlOperatorListener#OnContainsRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnContainsRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnContainsRendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IContainsControl#OnContainsWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnContainsWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnContainsWrite not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public abstract void OnControlChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public abstract void OnControlInit(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public abstract void OnControlLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnControlRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public abstract void OnControlRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public abstract void OnControlRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnControlWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public abstract void OnControlWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IDelimitersControl#OnDelimitersChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnDelimitersChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnDelimitersChanged not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IDelimitersControl#OnDelimitersInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnDelimitersInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnDelimitersInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IDelimitersControl#OnDelimitersLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnDelimitersLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException {
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnDelimitersLoaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IDelimitersControl#OnDelimitersRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnDelimitersRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException {
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnDelimitersRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IDelimitersControl#OnDelimitersRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnDelimitersRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnDelimitersRendered not implemented method");
	}

	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IDelimitersControl#OnDelimitersWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnDelimitersWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnDelimitersWrite not implemented method");
	}
	@Override
	public void OnEndIndexChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnEndIndexChanged not implemented method");
	}
	@Override
	public void OnEndIndexInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnEndIndexInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlDataTypeListener#OnEndIndexLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnEndIndexLoaded(Object sender, RmlEventArgs e)  throws InconsistenMessageException, NotImplementedException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnEndIndexLoaded not implemented method");
	}
	@Override
	public void OnEndIndexRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnEndIndexRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlDataTypeListener#OnEndIndexRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnEndIndexRendered(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnEndIndexRendered not implemented method");
	}
	@Override
	public void OnEndIndexWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnEndIndexWrite not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IEqualsControl#OnEqualsChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnEqualsChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnEqualsChanged not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IEqualsControl#OnEqualsInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnEqualsInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnEqualsInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlOperatorListener#OnEqualsLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnEqualsLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnEqualsLoaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IEqualsControl#OnEqualsRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnEqualsRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnEqualsRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlOperatorListener#OnEqualsRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnEqualsRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnEqualsRendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IEqualsControl#OnEqualsWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnEqualsWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnEqualsWrite not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IForEachControl#OnForEachChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnForEachChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnForEachChanged not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IForEachControl#OnForEachInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnForEachInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnForEachInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlOperatorListener#OnForEachLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnForEachLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnForEachLoaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IForEachControl#OnForEachRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnForEachRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnForEachRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlOperatorListener#OnForEachRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnForEachRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnForEachRendered not implemented method");
	}

	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IForEachControl#OnForEachWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnForEachWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnForEachWrite not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IGetFieldControl#OnGetFieldChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnGetFieldChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnGetFieldChanged not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IGetFieldControl#OnGetFieldInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnGetFieldInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnGetFieldInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlDataTypeListener#OnGetFieldLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnGetFieldLoaded(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnGetFieldLoaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IGetFieldControl#OnGetFieldRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnGetFieldRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnGetFieldRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlDataTypeListener#OnGetFieldRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnGetFieldRendered(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnGetFieldRendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IGetFieldControl#OnGetFieldWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnGetFieldWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnGetFieldWrite not implemented method");
	}
	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IRemoveControl#OnRemoveChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRemoveChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnRemoveChanged not implemented method");
	}

	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IRemoveControl#OnRemoveInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRemoveInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnRemoveInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlOperatorListener#OnRemoveLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnRemoveLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnRmoveLoaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IRemoveControl#OnRemoveRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRemoveRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnRemoveRead not implemented method");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlOperatorListener#OnRemoveRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnRemoveRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnRemoveRendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IRemoveControl#OnRemoveWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRemoveWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnRemoveWrite not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IReplaceControl#OnReplaceChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnReplaceChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnReplaceChanged not implemented method");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IReplaceControl#OnReplaceInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnReplaceInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnReplaceInit not implemented method");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlOperatorListener#OnReplaceLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnReplaceLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnReplaceLoaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IReplaceControl#OnReplaceRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnReplaceRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnReplaceRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlOperatorListener#OnReplaceRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnReplaceRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnReplaceRendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IReplaceControl#OnReplaceWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnReplaceWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnReplaceWrite not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISelectControl#OnSelectChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSelectChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnSelectChanged not implemented method");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISelectControl#OnSelectInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSelectInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnSelectInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlOperatorListener#OnSelectLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnSelectLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnSelectLoaded not implemented method");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISelectControl#OnSelectRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSelectRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnSelectRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlOperatorListener#OnSelectRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnSelectRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnSelectRendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISelectControl#OnSelectWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSelectWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnSelectWrite not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISplitControl#OnSplitChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSplitChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnSplitChanged not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISplitControl#OnSplitInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSplitInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnSplitInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlOperatorListener#OnSplitLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnSplitLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnSplitLoaded not implemented method");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISplitControl#OnSplitRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSplitRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnSplitRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlOperatorListener#OnSplitRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnSplitRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnSplitRendered not implemented method");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISplitControl#OnSplitWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSplitWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnSplitWrite not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IStartIndexControl#OnStartIndexChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStartIndexChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnStartIndexChanged not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IStartIndexControl#OnStartIndexInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStartIndexInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnStartIndexInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlDataTypeListener#OnStartIndexLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnStartIndexLoaded(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnStartIndexLoaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IStartIndexControl#OnStartIndexRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStartIndexRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnStartIndexRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlDataTypeListener#OnStartIndexRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnStartIndexRendered(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnStartIndexRendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IStartIndexControl#OnStartIndexWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStartIndexWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnStartIndexWrite not implemented method");	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISubstringControl#OnSubstringChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSubstringChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnSubstringChanged not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISubstringControl#OnSubstringInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSubstringInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnSubstringInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlOperatorListener#OnSubstringLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnSubstringLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnSubstringLoaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISubstringControl#OnSubstringRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSubstringRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnSubstringRead not implemented method");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlOperatorListener#OnSubstringRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnSubstringRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnSubstringRendered not implemented method");
	}		
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISubstringControl#OnSubstringWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSubstringWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnSubstringWrite not implemented method");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ITryParseControl#OnTryParseChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnTryParseChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnTryParseChanged not implemented method");
	}		
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ITryParseControl#OnTryParseInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnTryParseInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnTryParseInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlOperatorListener#OnTryParseLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnTryParseLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnTryParseLoaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ITryParseControl#OnTryParseRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnTryParseRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnTryParseRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlOperatorListener#OnTryParseRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnTryParseRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnTryParseRebdered not implemented method");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ITryParseControl#OnTryParseWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnTryParseWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnTryParseWrite not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IWhereControl#OnWhereChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnWhereChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnWhereChanged not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IWhereControl#OnWhereInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnWhereInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnWhereInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlOperatorListener#OnWhereLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnWhereLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnWhereLoaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IWhereControl#OnWhereRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnWhereRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnWhereRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlOperatorListener#OnWhereRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnWhereRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnWhereRendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IWhereControl#OnWhereWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnWhereWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnWhereWrite not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IXmlQueryControl#OnXmlQueryChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnXmlQueryChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnXmlQueryChanged not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IXmlQueryControl#OnXmlQueryInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnXmlQueryInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnXmlQueryInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlOperatorListener#OnXmlQueryLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnXmlQueryLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnXmlQueryLoaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IXmlQueryControl#OnXmlQueryRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnXmlQueryRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnXmlQueryRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlOperatorListener#OnXmlQueryRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnXmlQueryRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnXmlQueryRendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IXmlQueryControl#OnXmlQueryWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnXmlQueryWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlOperatorControl.OnXmlQueryWrite not implemented method");
	}
	/**
	 * Removes {@link Condition} event {@link IRmlOperatorListener}
	 * @param listener
	 */
	public final synchronized void removeContainsListener(IConditionListener listener)
	{
		condition_listeners.remove(listener);
	}
	/**
	 * Removes Contains event listener
	 * @param listener
	 */
	public final synchronized void removeContainsListener(IRmlOperatorListener listener)
	{
		contains_listeners.remove(listener);
	}
	/**
	 * Removes {@link Delimiters} event {@link IRmlDataTypeListener}.
	 * @param listener {@link IRmlDataTypeListener} the listener
	 */
	public final synchronized void removeDelimitersListener(IRmlListener listener)
	{
		delimiters_listeners.remove(listener);
	}
	/**
	 * Removes {@link EndIndex} event {@link IRmlDataTypeListener}.
	 * @param listener {@link IRmlDataTypeListener} the listener
	 */
	public final synchronized void removeEndIndexListener(IRmlDataTypeListener listener)
	{
		end_index_listeners.remove(listener);
	}
	/**
	 * Removes Equals event listener
	 * @param listener
	 */
	public final synchronized void removeEqualsListener(IRmlOperatorListener listener)
	{
		equals_listeners.remove(listener);
	}

	/**
	 * Removes ForEach event listener
	 * @param listener
	 */
	public final synchronized void removeForEachListener(IRmlOperatorListener listener)
	{
		for_each_listeners.remove(listener);
	}
	/**
	 * Removes {@link GetField} event {@link IRmlDataTypeListener}.
	 * @param listener {@link IRmlDataTypeListener} the listener
	 */
	public final synchronized void removeGetFieldListener(IRmlDataTypeListener listener)
	{
		get_field_listeners.remove(listener);
	}
	/**
	 * Removes Remove event listener
	 * @param listener
	 */
	public final synchronized void removeRemoveListener(IRmlOperatorListener listener)
	{
		remove_listeners.remove(listener);
	}
	/**
	 * Removes Replace event listener
	 * @param listener
	 */
	public final synchronized void removeReplaceListener(IRmlOperatorListener listener)
	{
		replace_listeners.remove(listener);
	}
	/**
	 * Removes Split event listener
	 * @param listener
	 */
	public final synchronized void removeSplitListener(IRmlOperatorListener listener)
	{
		split_listeners.remove(listener);
	}
	/**
	 * Removes {@link StartIndex} event {@link IRmlDataTypeListener}.
	 * @param listener {@link IRmlDataTypeListener} the listener
	 */
	public final synchronized void removeStartIndexListener(IRmlDataTypeListener listener)
	{
		start_index_listeners.remove(listener);
	}
	/**
	 * Removes Replace event listener
	 * @param listener
	 */
	public final synchronized void removeSubstringListener(IRmlOperatorListener listener)
	{
		substring_listeners.remove(listener);
	}
	/**
	 * Removes TryParse event listener
	 * @param listener
	 */
	public final synchronized void removeTryParseListener(IRmlOperatorListener listener)
	{
		try_parse_listeners.remove(listener);
	}
	/**
	 * Removes ForEach event listener
	 * @param listener
	 */
	public final synchronized void removeXmlQueryListener(IRmlOperatorListener listener)
	{
		xml_query_listeners.remove(listener);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#setIsRendered(java.lang.Boolean)
	 */
	@Override
	public void setIsRendered(Boolean value) 
	{
		super.setIsRendered(value);
		
		Rml message = this.getMessage();
		
		if(message instanceof Delimiters)
		{
			ControlDelimitersEvent(new RmlEventArgs(this, RmlEventType.RENDER, message));
		}
		else if(message instanceof Contains)
		{
			ControlContainsEvent(new RmlEventArgs(this, RmlEventType.RENDER, message));
		}
		else if(message instanceof Equals)
		{
			ControlEqualsEvent(new RmlEventArgs(this, RmlEventType.RENDER, message));
		}
		else if(message instanceof Rule)
		{
			ControlRuleEvent(new RmlEventArgs(this, RmlEventType.RENDER, message));
		}
		else if(message instanceof ForEach)
		{
			ControlForEachEvent(new RmlEventArgs(this, RmlEventType.RENDER, message));
		}
		else if(message instanceof Remove)
		{
			ControlRemoveEvent(new RmlEventArgs(this, RmlEventType.RENDER, message));
		}
		else if(message instanceof Replace)
		{
			ControlReplaceEvent(new RmlEventArgs(this, RmlEventType.RENDER, message));
		}
		else if(message instanceof Split)
		{
			ControlSplitEvent(new RmlEventArgs(this, RmlEventType.RENDER, message));
		}
		else if(message instanceof Substring)
		{
			ControlSubstringEvent(new RmlEventArgs(this, RmlEventType.RENDER, message));
		}
		else if(message instanceof TryParse)
		{
			ControlTryParseEvent(new RmlEventArgs(this, RmlEventType.RENDER, message));
		}
		else if(message instanceof XmlQuery)
		{
			ControlXmlQueryEvent(new RmlEventArgs(this, RmlEventType.RENDER, message));
		}
		else if(message instanceof Select)
		{
			ControlSelectEvent(new RmlEventArgs(this, RmlEventType.RENDER, message));
		}
		else if(message instanceof Where)
		{
			ControlWhereEvent(new RmlEventArgs(this, RmlEventType.RENDER, message));
		}
		else if(message instanceof EndIndex)
		{
			ControlEndIndexEvent(new RmlEventArgs(this, RmlEventType.RENDER, message));
		}
		else if(message instanceof StartIndex)
		{
			ControlStartIndexEvent(new RmlEventArgs(this, RmlEventType.RENDER, message));
		}
		else if(message instanceof GetField)
		{
			ControlGetFieldEvent(new RmlEventArgs(this, RmlEventType.RENDER, message));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#setMessage(org.httprobot.common.rml.RmlObject)
	 */
	@Override
	public void setMessage(Rml message)
	{
		super.setMessage(message);
		
		if(message instanceof Delimiters)
		{
			ControlDelimitersEvent(new RmlEventArgs(this, RmlEventType.LOAD, message));
		}
		else if(message instanceof Contains)
		{
			ControlContainsEvent(new RmlEventArgs(this, RmlEventType.LOAD, message));
		}
		else if(message instanceof Equals)
		{
			ControlEqualsEvent(new RmlEventArgs(this, RmlEventType.LOAD, message));
		}
		else if(message instanceof ForEach)
		{
			ControlForEachEvent(new RmlEventArgs(this, RmlEventType.LOAD, message));
		}
		else if(message instanceof Remove)
		{
			ControlRemoveEvent(new RmlEventArgs(this, RmlEventType.LOAD, message));
		}
		else if(message instanceof Replace)
		{
			ControlReplaceEvent(new RmlEventArgs(this, RmlEventType.LOAD, message));
		}
		else if(message instanceof Split)
		{
			ControlSplitEvent(new RmlEventArgs(this, RmlEventType.LOAD, message));
		}
		else if(message instanceof Substring)
		{
			ControlSubstringEvent(new RmlEventArgs(this, RmlEventType.LOAD, message));
		}
		else if(message instanceof TryParse)
		{
			ControlTryParseEvent(new RmlEventArgs(this, RmlEventType.LOAD, message));
		}
		else if(message instanceof XmlQuery)
		{
			ControlXmlQueryEvent(new RmlEventArgs(this, RmlEventType.LOAD, message));
		}
		else if(message instanceof Select)
		{
			ControlSelectEvent(new RmlEventArgs(this, RmlEventType.LOAD, message));
		}
		else if(message instanceof Where)
		{
			ControlWhereEvent(new RmlEventArgs(this, RmlEventType.LOAD, message));
		}
		else if(message instanceof EndIndex)
		{
			ControlEndIndexEvent(new RmlEventArgs(this, RmlEventType.LOAD, message));
		}
		else if(message instanceof StartIndex)
		{
			ControlStartIndexEvent(new RmlEventArgs(this, RmlEventType.LOAD, message));
		}
		else if(message instanceof GetField)
		{
			ControlGetFieldEvent(new RmlEventArgs(this, RmlEventType.LOAD, message));
		}
	}
	/**
	 * 
	 */
	private final void InitOperatorControl(Rml message) 
	{
		contains_listeners = new Vector<IContainsListener>();
		equals_listeners = new Vector<IEqualsListener>();
		split_listeners = new Vector<ISplitListener>();
		substring_listeners = new Vector<ISubstringListener>();
		remove_listeners = new Vector<IRemoveListener>();
		replace_listeners = new Vector<IReplaceListener>();
		try_parse_listeners = new Vector<ITryParseListener>();
		for_each_listeners = new Vector<IForEachListener>();
		xml_query_listeners = new Vector<IXmlQueryListener>();
		select_listeners = new Vector<ISelectListener>();
		where_listeners = new Vector<IWhereListener>();
		end_index_listeners = new Vector<IEndIndexListener>();
		start_index_listeners = new Vector<IStartIndexListener>();
		get_field_listeners = new Vector<IGetFieldListener>();
		
		addContainsListener(this);
		addEqualsListener(this);
		addSplitListener(this);
		addSubstringListener(this);
		addRemoveListener(this);
		addReplaceListener(this);
		addTryParseListener(this);
		addForEachListener(this);		
		addXmlQueryListener(this);
		addSelectListener(this);
		addWhereListener(this);
		addEndIndexListener(this);
		addStartIndexListener(this);
		addGetFieldListener(this);
		
		RmlEventArgs e = new RmlEventArgs(this, RmlEventType.INIT, message);
		
		if(e.getSource() instanceof ContainsControl)
		{
			ControlContainsEvent(e);
		}
		else if(e.getSource() instanceof DelimitersControl)
		{
			ControlDelimitersEvent(e);
		}
		else if(e.getSource() instanceof EndIndexControl)
		{
			ControlEndIndexEvent(e);
		}
		else if(e.getSource() instanceof StartIndexControl)
		{
			ControlStartIndexEvent(e);
		}
		else if(e.getSource() instanceof EqualsControl)
		{
			ControlEqualsEvent(e);
		}
		else if(e.getSource() instanceof ForEachControl)
		{
			ControlForEachEvent(e);
		}
		else if(e.getSource() instanceof GetFieldControl)
		{
			ControlGetFieldEvent(e);
		}
		else if(e.getSource() instanceof RemoveControl)
		{
			ControlRemoveEvent(e);
		}
		else if(e.getSource() instanceof ReplaceControl)
		{
			ControlReplaceEvent(e);
		}
		else if(e.getSource() instanceof SelectControl)
		{
			ControlSelectEvent(e);
		}
		else if(e.getSource() instanceof SplitControl)
		{
			ControlSplitEvent(e);
		}
		else if(e.getSource() instanceof SubstringControl)
		{
			ControlTryParseEvent(e);
		}
		else if(e.getSource() instanceof TryParseControl)
		{
			ControlStepsEvent(e);
		}
		else if(e.getSource() instanceof WhereControl)
		{
			ControlWhereEvent(e);
		}
		else if(e.getSource() instanceof XmlQueryControl)
		{
			ControlXmlQueryEvent(e);
		}
	}
	/**
	 * Fires {@link Condition} event
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlConditionEvent(RmlEventArgs e) 
	{
		for (IConditionListener listener : condition_listeners) 
		{
			try 
			{
				switch (e.getRmlEventType()) 
				{
					case INIT:
						listener.OnConditionInit(this, e);
						break;
					case READ:
						listener.OnConditionRead(this, e);
						break;
					case LOAD:
						listener.OnConditionLoaded(this, e);
						break;
					case CHANGE:
						listener.OnConditionChanged(this, e);
						break;
					case RENDER:
						listener.OnConditionRendered(this, e);
						break;
					case WRITE:
						listener.OnConditionWrite(this, e);
						break;
					default:
						break;
				}		
			}
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			}
			catch (InconsistenMessageException e2) 
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Fires Contains event
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlContainsEvent(RmlEventArgs e) 
	{
		for (IContainsListener listener : contains_listeners) 
		{
			try 
			{
				switch (e.getRmlEventType()) 
				{
					case INIT:
						listener.OnContainsInit(this, e);
						break;
					case READ:
						listener.OnContainsRead(this, e);
						break;
					case LOAD:
						listener.OnContainsLoaded(this, e);
						break;
					case CHANGE:
						listener.OnContainsChanged(this, e);
						break;
					case RENDER:
						listener.OnContainsRendered(this, e);
						break;
					case WRITE:
						listener.OnContainsWrite(this, e);
						break;
					default:
						break;
				}		
			}
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			}
			catch (InconsistenMessageException e2) 
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Fires {@link Delimiters} event.
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlDelimitersEvent(RmlEventArgs e)
	{
		for (IDelimitersListener listener : delimiters_listeners) 
		{
			try 
			{
				switch (e.getRmlEventType()) 
				{
					case INIT:
						listener.OnDelimitersInit(this, e);
						break;
					case READ:
						listener.OnDelimitersRead(this, e);
						break;
					case LOAD:
						listener.OnDelimitersLoaded(this, e);
						break;	
					case CHANGE:
						listener.OnDelimitersChanged(this, e);
						break;
					case RENDER:
						listener.OnDelimitersRendered(this, e);
						break;
					case WRITE:
						listener.OnDelimitersWrite(this, e);
						break;
					default:
						break;
				}
			} 
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			} 
			catch (InconsistenMessageException e2) 
			{
				e2.printStackTrace();
			}
		}		
	}
	/**
	 * Fires {@link EndIndex} event.
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlEndIndexEvent(RmlEventArgs e)
	{
		for (IEndIndexListener listener : end_index_listeners) 
		{
			try 
			{
				switch (e.getRmlEventType()) 
				{
					case INIT:
						listener.OnEndIndexInit(this, e);
					break;
					case READ:
						listener.OnEndIndexRead(this, e);
					break;
					case LOAD:
						listener.OnEndIndexLoaded(this, e);
					break;
					case CHANGE:
						listener.OnEndIndexChanged(this, e);
					break;
					case RENDER:
						listener.OnEndIndexRendered(this, e);
					break;
					case WRITE:
						listener.OnEndIndexWrite(this, e);
					break;
				default:
					break;
				}
			}
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			} 
			catch (InconsistenMessageException e2) 
			{
				e2.printStackTrace();
			}
		}		
	}
	/**
	 * Fires Equals RML event
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlEqualsEvent(RmlEventArgs e) 
	{
		for (IEqualsListener listener : equals_listeners) 
		{
			try 
			{
				switch (e.getRmlEventType()) 
				{
					case INIT:
						listener.OnEqualsInit(this, e);
						break;
					case READ:
						listener.OnEqualsRead(this, e);
						break;
					case LOAD:
						listener.OnEqualsLoaded(this, e);
						break;
					case CHANGE:
						listener.OnEqualsChanged(this, e);
						break;
					case RENDER:
						listener.OnEqualsRendered(this, e);
						break;
					case WRITE:
						listener.OnEqualsWrite(this, e);
						break;
					default:
						break;
				}				
			} 
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			} 
			catch (InconsistenMessageException e2) 
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Fires ForEach event
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlForEachEvent(RmlEventArgs e) 
	{
		for (IForEachListener listener : for_each_listeners) 
		{
			try 
			{
				switch (e.getRmlEventType())
				{
					case INIT:
						listener.OnForEachInit(this, e);
						break;
					case READ:
						listener.OnForEachRead(this, e);
						break;
					case LOAD:
						listener.OnForEachLoaded(this, e);
						break;
					case CHANGE:
						listener.OnForEachChanged(this, e);
						break;
					case RENDER:
						listener.OnForEachRendered(this, e);
						break;
					case WRITE:
						listener.OnForEachWrite(this, e);
						break;
					default:
						break;
				}
			}
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			} 
			catch (InconsistenMessageException e2) 
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Fires {@link GetField} event.
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlGetFieldEvent(RmlEventArgs e)
	{
		for (IGetFieldListener listener : get_field_listeners) 
		{
			try 
			{
				switch (e.getRmlEventType()) {
					case INIT:
						listener.OnGetFieldInit(this, e);
						break;
					case READ:
						listener.OnGetFieldRead(this, e);
						break;
					case LOAD:
						listener.OnGetFieldLoaded(this, e);
						break;
					case CHANGE:
						listener.OnGetFieldChanged(this, e);
						break;
					case RENDER:
						listener.OnGetFieldRendered(this, e);
						break;
					case WRITE:
						listener.OnGetFieldWrite(this, e);
						break;
					default:
						break;
				}
				
			} 
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			} 
			catch (InconsistenMessageException e2) 
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Fires Remove event
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlRemoveEvent(RmlEventArgs e) 
	{
		for (IRemoveListener listener : remove_listeners) 
		{
			try 
			{
				switch (e.getRmlEventType()) 
				{
					case INIT:
						listener.OnRemoveInit(this, e);
						break;
					case READ:
						listener.OnRemoveRead(this, e);
						break;
					case LOAD:
						listener.OnRemoveLoaded(this, e);
						break;
					case CHANGE:
						listener.OnRemoveChanged(this, e);
						break;
					case RENDER:
						listener.OnRemoveRendered(this, e);
						break;
					case WRITE:
						listener.OnRemoveWrite(this, e);
						break;
					default:
						break;
				}				
			} 
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			} 
			catch (InconsistenMessageException e2) 
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Fires Replace event
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlReplaceEvent(RmlEventArgs e)
	{
		for (IReplaceListener listener : replace_listeners) 
		{
			try
			{
				switch (e.getRmlEventType())
				{
					case INIT:						
						listener.OnReplaceInit(this, e);
						break;
					case READ:						
						listener.OnReplaceRead(this, e);
						break;
					case LOAD:						
						listener.OnReplaceLoaded(this, e);
						break;
					case CHANGE:						
						listener.OnReplaceChanged(this, e);
						break;
					case RENDER:						
						listener.OnReplaceRendered(this, e);
						break;
					case WRITE:						
						listener.OnReplaceWrite(this, e);
						break;	
					default:
						break;
				}				
			}
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			} 
			catch (InconsistenMessageException e2) 
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Fires XmlQuery event
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlSelectEvent(RmlEventArgs e) 
	{
		for (ISelectListener listener : select_listeners) 
		{
			try
			{
				switch (e.getRmlEventType()) 
				{
					case INIT:
						listener.OnSelectInit(this, e);
						break;
					case READ:
						listener.OnSelectRead(this, e);
						break;
					case LOAD:
						listener.OnSelectLoaded(this, e);
						break;
					case CHANGE:
						listener.OnSelectChanged(this, e);
						break;
					case RENDER:
						listener.OnSelectRendered(this, e);
						break;
					case WRITE:
						listener.OnSelectWrite(this, e);
						break;	
					default:
						break;
				}				
			} 
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			}
			catch (InconsistenMessageException e2) 
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Fires Split event
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlSplitEvent(RmlEventArgs e) 
	{
		for (ISplitListener listener : split_listeners) 
		{
			try
			{
				switch (e.getRmlEventType()) 
				{
					case INIT:
						listener.OnSplitInit(this, e);
						break;
					case READ:
						listener.OnSplitRead(this, e);
						break;
					case LOAD:
						listener.OnSplitLoaded(this, e);
						break;
					case CHANGE:
						listener.OnSplitChanged(this, e);
						break;
					case RENDER:
						listener.OnSplitRendered(this, e);
						break;
					case WRITE:
						listener.OnSplitWrite(this, e);
						break;
	
					default:
						break;
				}
			} 
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			} 
			catch (InconsistenMessageException e2)
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Fires {@link StartIndex} event.
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlStartIndexEvent(RmlEventArgs e)
	{
		for (IStartIndexListener listener : start_index_listeners) 
		{
			try
			{
				switch (e.getRmlEventType()) 
				{
					case INIT:
						listener.OnStartIndexInit(this, e);
						break;
					case READ:
						listener.OnStartIndexRead(this, e);
						break;
					case LOAD:
						listener.OnStartIndexLoaded(this, e);
						break;
					case CHANGE:
						listener.OnStartIndexChanged(this, e);
						break;
					case RENDER:
						listener.OnStartIndexRendered(this, e);
						break;
					case WRITE:
						listener.OnStartIndexWrite(this, e);
						break;
					default:
						break;
				}
			}
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			} 
			catch (InconsistenMessageException e2)
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Fires Substring event
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlSubstringEvent(RmlEventArgs e) 
	{
		for (ISubstringListener listener : substring_listeners) 
		{
			try 
			{
				switch (e.getRmlEventType())
				{
					case INIT:
						listener.OnSubstringInit(this, e);
						break;
					case READ:
						listener.OnSubstringRead(this, e);
						break;
					case LOAD:
						listener.OnSubstringLoaded(this, e);
						break;
					case CHANGE:
						listener.OnSubstringChanged(this, e);
						break;
					case RENDER:
						listener.OnSubstringRendered(this, e);
						break;
					case WRITE:
						listener.OnSubstringWrite(this, e);
						break;
	
					default:
						break;
				}			
			}
			catch (NotImplementedException e1)
			{
				
				e1.printStackTrace();
			} 
			catch (InconsistenMessageException e2)
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Fires ForEach event
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlTryParseEvent(RmlEventArgs e) 
	{
		for (ITryParseListener listener : try_parse_listeners) 
		{
			try
			{
				switch (e.getRmlEventType()) {
				case INIT:
					listener.OnTryParseInit(this, e);
					break;
				case READ:
					listener.OnTryParseRead(this, e);
					break;
				case LOAD:
					listener.OnTryParseLoaded(this, e);
					break;
				case CHANGE:
					listener.OnTryParseChanged(this, e);
					break;
				case RENDER:
					listener.OnTryParseRendered(this, e);
					break;
				case WRITE:
					listener.OnTryParseWrite(this, e);
					break;

				default:
					break;
				}				
			}
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			} 
			catch (InconsistenMessageException e2) 
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Fires Where event
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlWhereEvent(RmlEventArgs e) 
	{
		for (IWhereListener listener : where_listeners) 
		{
			try 
			{
				switch (e.getRmlEventType())
				{
					case INIT:
						listener.OnWhereInit(this, e);
						break;
					case READ:
						listener.OnWhereRead(this, e);
						break;
					case LOAD:
						listener.OnWhereLoaded(this, e);
						break;
					case CHANGE:
						listener.OnWhereChanged(this, e);
						break;
					case RENDER:
						listener.OnWhereRendered(this, e);
						break;
					case WRITE:
						listener.OnWhereWrite(this, e);
						break;	
					default:
						break;
				}
				
			} 
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			} 
			catch (InconsistenMessageException e2)
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Fires XmlQuery event
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlXmlQueryEvent(RmlEventArgs e) 
	{
		for (IXmlQueryListener listener : xml_query_listeners)
		{
			try 
			{
				switch(e.getRmlEventType())
				{
					case INIT:
						listener.OnXmlQueryInit(this, e);
						break;
					case READ:
						listener.OnXmlQueryRead(this, e);
						break;
					case LOAD:
						listener.OnXmlQueryLoaded(this, e);
						break;
					case CHANGE:
						listener.OnXmlQueryChanged(this, e);
						break;
					case RENDER:
						listener.OnXmlQueryRendered(this, e);
						break;
					case WRITE:
						listener.OnXmlQueryWrite(this, e);
						break;
					default:
						break;
				}
				
			}
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			}
			catch (InconsistenMessageException e2) 
			{
				e2.printStackTrace();
			}
		}
	}
}