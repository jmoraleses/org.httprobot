package org.httprobot.core.controls;

import java.util.Vector;

import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.RML;
import org.httprobot.common.Operator;
import org.httprobot.common.definitions.Enums.ControlEventType;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.placeholders.operators.Contains;
import org.httprobot.common.placeholders.operators.Delimiters;
import org.httprobot.common.placeholders.operators.EndIndex;
import org.httprobot.common.placeholders.operators.Equals;
import org.httprobot.common.placeholders.operators.Remove;
import org.httprobot.common.placeholders.operators.Replace;
import org.httprobot.common.placeholders.operators.Split;
import org.httprobot.common.placeholders.operators.StartIndex;
import org.httprobot.common.placeholders.operators.Substring;
import org.httprobot.common.placeholders.operators.TryParse;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.controls.interfaces.listeners.IDataTypeControlListener;
import org.httprobot.core.controls.interfaces.listeners.IOperatorControlListener;
import org.httprobot.core.controls.operators.interfaces.IControlContainsListener;
import org.httprobot.core.controls.operators.interfaces.IControlDelimitersListener;
import org.httprobot.core.controls.operators.interfaces.IControlEndIndexListener;
import org.httprobot.core.controls.operators.interfaces.IControlEqualsListener;
import org.httprobot.core.controls.operators.interfaces.IControlRemoveListener;
import org.httprobot.core.controls.operators.interfaces.IControlReplaceListener;
import org.httprobot.core.controls.operators.interfaces.IControlSplitListener;
import org.httprobot.core.controls.operators.interfaces.IControlStartIndexListener;
import org.httprobot.core.controls.operators.interfaces.IControlSubstringListener;
import org.httprobot.core.controls.operators.interfaces.IControlTryParseListener;

/**
 * Abstract {@link Operator} message control class. Inherits {@link Control}. 
 * Is {@link IOperatorControlListener}.
 * <br>
 * @author Joan
 */
@XmlTransient
public abstract class OperatorControl
	<TMessage extends Operator, IListener extends IControlListener> 
		extends PlaceholderControl<TMessage, IListener>
		implements IOperatorControlListener {
	
	/**
	 * 1465622143637263909L
	 */
	private static final long serialVersionUID = 1465622143637263909L;
	
	/**
	 * {@link Contains} message listeners
	 */
	private Vector<IControlContainsListener> containsListeners = null;
	/**
	 * {@link Delimiters} message listeners
	 */
	private Vector<IControlDelimitersListener> delimitersListeners = null;
	/**
	 * {@link EndIndex} message listeners
	 */
	private Vector<IControlEndIndexListener> endIndexListeners = null;
	/**
	 * {@link Equals} message listeners
	 */
	private Vector<IControlEqualsListener> equalsListeners = null;
	/**
	 * {@link Remove} message listeners
	 */
	private Vector<IControlRemoveListener> removeListeners = null;
	/**
	 * {@link Replace} message listeners
	 */
	private Vector<IControlReplaceListener> replaceListeners = null;
	/**
	 * {@link Split} message listeners
	 */
	private Vector<IControlSplitListener> splitListeners = null;
	/**
	 * {@link StartIndex} message listeners
	 */
	private Vector<IControlStartIndexListener> startIndexListeners = null;
	/**
	 * {@link Substring} message listeners
	 */
	private Vector<IControlSubstringListener> substringListeners = null;
	/**
	 * {@link TryParse} message listeners
	 */
	private Vector<IControlTryParseListener> tryParseListeners = null;
	
	/**
	 * {@link Operator} control default class constructor
	 */
	public OperatorControl()
	{
		super();

		//Initialize Operator control
		initOperatorControl(null, null);
	}
	/**
	 * {@link Operator} control class constructor.
	 * @param parent {@link IControlListener} the parent listener
	 */
	public OperatorControl(IListener parent, TMessage message)
	{
		super(parent, message);
		
		//Initialize Operator control
		initOperatorControl(message, parent);
	}
	/**
	 * Adds {@link Contains} event {@link IOperatorControlListener}
	 * @param listener {@link IControlContainsListener} the listener
	 */
	public final synchronized void addControlContainsListener(IControlContainsListener listener)
	{
		if(this.containsListeners == null)
		{
			this.containsListeners = new Vector<IControlContainsListener>();
		}
		this.containsListeners.add(listener);
	}
	/**
	 * Adds {@link Delimiters} event {@link IDataTypeControlListener}.
	 * @param listener {@link IControlDelimitersListener} the listener
	 */
	public final synchronized void addControlDelimitersListener(IControlDelimitersListener listener)
	{
		if(this.delimitersListeners == null)
		{
			this.delimitersListeners = new Vector<IControlDelimitersListener>();
		}
		this.delimitersListeners.add(listener);
	}
	/**
	 * Adds {@link EndIndex} event {@link IDataTypeControlListener}.
	 * @param listener {@link IControlEndIndexListener} the listener
	 */
	public final synchronized void addControlEndIndexListener(IControlEndIndexListener listener)
	{
		if(this.endIndexListeners == null)
		{
			this.endIndexListeners = new Vector<IControlEndIndexListener>();
		}
		this.endIndexListeners.add(listener);
	}
	/**
	 * Adds {@link Equals} event {@link IOperatorControlListener}.
	 * @param listener {@link IControlEqualsListener} the listener
	 */
	public final synchronized void addControlEqualsListener(IControlEqualsListener listener)
	{
		if(this.equalsListeners == null)
		{
			this.equalsListeners = new Vector<IControlEqualsListener>();
		}
		this.equalsListeners.add(listener);
	}
	/**
	 * Adds Remove event listener
	 * @param listener {@link IControlRemoveListener} the listener
	 */
	public final synchronized void addControlRemoveListener(IControlRemoveListener listener)
	{
		if(this.removeListeners == null)
		{
			this.removeListeners = new Vector<IControlRemoveListener>();
		}
		this.removeListeners.add(listener);
	}
	/**
	 * Adds {@link Replace} event {@link IOperatorControlListener}
	 * @param listener {@link IControlReplaceListener} the listener
	 */
	public final synchronized void addControlReplaceListener(IControlReplaceListener listener)
	{
		if(this.replaceListeners == null)
		{
			this.replaceListeners = new Vector<IControlReplaceListener>();
		}
		this.replaceListeners.add(listener);
	}
	/**
	 * Adds {@link Split} event {@link IOperatorControlListener}
	 * @param listener {@link IControlSplitListener} the listener
	 */
	public final synchronized void addControlSplitListener(IControlSplitListener listener)
	{
		if(this.splitListeners == null)
		{
			this.splitListeners = new Vector<IControlSplitListener>();
		}
		this.splitListeners.add(listener);
	}
	/**
	 * Adds {@link StartIndex} event {@link IDataTypeControlListener}.
	 * @param listener {@link IControlStartIndexListener} the listener
	 */
	public final synchronized void addControlStartIndexListener(IControlStartIndexListener listener)
	{
		if(this.startIndexListeners == null)
		{
			this.startIndexListeners = new Vector<IControlStartIndexListener>();
		}
		this.startIndexListeners.add(listener);
	}
	/**
	 * Adds {@link Substring} event listener
	 * @param listener {@link IControlSubstringListener} the listener
	 */
	public final synchronized void addControlSubstringListener(IControlSubstringListener listener)
	{
		if(this.substringListeners == null)
		{
			this.substringListeners = new Vector<IControlSubstringListener>();
		}
		this.substringListeners.add(listener);
	}
	/**
	 * Adds {@link TryParse} event {@link IOperatorControlListener}
	 * @param listener {@link IControlTryParseListener} the listener
	 */
	public final synchronized void addControlTryParseListener(IControlTryParseListener listener)
	{
		if(this.tryParseListeners == null)
		{
			this.tryParseListeners = new Vector<IControlTryParseListener>();
		}
		this.tryParseListeners.add(listener);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.PlaceholderControl#setMessage(org.httprobot.common.Placeholder)
	 */
	@Override
	public void controlMessage(TMessage message)
	{
		super.controlMessage(message);
		
		if(message instanceof Delimiters)
		{
			ControlDelimitersEvent(new ControlEventArgs(this, ControlEventType.LOAD, message));
		}
		else if(message instanceof Contains)
		{
			ControlContainsEvent(new ControlEventArgs(this, ControlEventType.LOAD, message));
		}
		else if(message instanceof Equals)
		{
			ControlEqualsEvent(new ControlEventArgs(this, ControlEventType.LOAD, message));
		}
		else if(message instanceof Remove)
		{
			ControlRemoveEvent(new ControlEventArgs(this, ControlEventType.LOAD, message));
		}
		else if(message instanceof Replace)
		{
			ControlReplaceEvent(new ControlEventArgs(this, ControlEventType.LOAD, message));
		}
		else if(message instanceof Split)
		{
			ControlSplitEvent(new ControlEventArgs(this, ControlEventType.LOAD, message));
		}
		else if(message instanceof Substring)
		{
			ControlSubstringEvent(new ControlEventArgs(this, ControlEventType.LOAD, message));
		}
		else if(message instanceof TryParse)
		{
			ControlTryParseEvent(new ControlEventArgs(this, ControlEventType.LOAD, message));
		}
		else if(message instanceof EndIndex)
		{
			ControlEndIndexEvent(new ControlEventArgs(this, ControlEventType.LOAD, message));
		}
		else if(message instanceof StartIndex)
		{
			ControlStartIndexEvent(new ControlEventArgs(this, ControlEventType.LOAD, message));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.PlaceholderControl#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Change(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.PlaceholderControl#OnControl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Initialize(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.PlaceholderControl#OnControl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Load(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.PlaceholderControl#OnControl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.PlaceholderControl#OnControl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Render(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.PlaceholderControl#OnControl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlContainsListener#OnControlContains_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContains_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlContains_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlContainsListener#OnControlContains_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContains_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlContains_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlContainsListener#OnControlContains_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContains_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlContains_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlContainsListener#OnControlContains_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContains_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlContains_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlContainsListener#OnControlContains_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContains_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlContains_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlContainsListener#OnControlContains_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContains_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlContains_Write not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlDelimitersListener#OnControlDelimiters_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDelimiters_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlDelimiters_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlDelimitersListener#OnControlDelimiters_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDelimiters_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlDelimiters_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlDelimitersListener#OnControlDelimiters_Loaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlDelimiters_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlDelimiters_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlDelimitersListener#OnControlDelimiters_Read(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlDelimiters_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"OperatorControl.OnControlDelimiters_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlDelimitersListener#OnControlDelimiters_Rendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlDelimiters_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlDelimiters_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlDelimitersListener#OnControlDelimiters_Write(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlDelimiters_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlDelimiters_Write not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlEndIndexListener#OnControlEndIndex_Changed(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlEndIndex_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlEndIndex_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlEndIndexListener#OnControlEndIndex_Init(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlEndIndex_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlEndIndex_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlEndIndexListener#OnControlEndIndex_Loaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlEndIndex_Loaded(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlEndIndex_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlEndIndexListener#OnControlEndIndex_Read(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlEndIndex_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"OperatorControl.OnControlEndIndex_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlEndIndexListener#OnControlEndIndex_Rendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlEndIndex_Rendered(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlEndIndex_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlEndIndexListener#OnControlEndIndex_Write(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlEndIndex_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlEndIndex_Write not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlEqualsListener#OnControlEquals_Changed(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlEquals_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlEquals_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlEqualsListener#OnControlEquals_Init(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlEquals_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"OperatorControl.OnControlEquals_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlEqualsListener#OnControlEquals_Loaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlEquals_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlEquals_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlEqualsListener#OnControlEquals_Read(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlEquals_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"OperatorControl.OnControlEquals_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlEqualsListener#OnControlEquals_Rendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlEquals_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlEquals_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlEqualsListener#OnControlEquals_Write(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlEquals_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlEquals_Write not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlRemoveListener#OnControlRemove_Changed(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRemove_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlRemove_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlRemoveListener#OnControlRemove_Init(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRemove_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlRemove_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlRemoveListener#OnControlRemove_Loaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRemove_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlRemove_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlRemoveListener#OnControlRemove_Read(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRemove_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlRemove_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlRemoveListener#OnControlRemove_Rendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRemove_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlRemove_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlRemoveListener#OnControlRemove_Write(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRemove_Write(Object sender, ControlEventArgs e) 	
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlRemove_Write not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlReplaceListener#OnControlReplace_Changed(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlReplace_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"OperatorControl.OnControlReplace_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlReplaceListener#OnControlReplace_Init(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlReplace_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlReplace_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlReplaceListener#OnControlReplace_Loaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlReplace_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"OperatorControl.OnControlReplace_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlReplaceListener#OnControlReplace_Read(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlReplace_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"OperatorControl.OnControlReplace_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlReplaceListener#OnControlReplace_Rendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlReplace_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"OperatorControl.OnControlReplace_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlReplaceListener#OnControlReplace_Write(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlReplace_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlReplace_Write not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSplitListener#OnControlSplit_Changed(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlSplit_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlSplit_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSplitListener#OnControlSplit_Init(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlSplit_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"OperatorControl.OnControlSplit_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSplitListener#OnControlSplit_Loaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlSplit_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"OperatorControl.OnControlSplit_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSplitListener#OnControlSplit_Read(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlSplit_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"OperatorControl.OnControlSplit_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSplitListener#OnControlSplit_Rendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlSplit_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"OperatorControl.OnControlSplit_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSplitListener#OnControlSplit_Write(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlSplit_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"OperatorControl.OnControlSplit_Write not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlStartIndexListener#OnStartIndexChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlStartIndex_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"OperatorControl.OnControlStartIndex_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlStartIndexListener#OnStartIndexInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlStartIndex_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlStartIndex_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlStartIndexListener#OnStartIndexLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlStartIndex_Loaded(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlStartIndex_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlStartIndexListener#OnStartIndexRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlStartIndex_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlStartIndex_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlStartIndexListener#OnStartIndexRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlStartIndex_Rendered(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlStartIndex_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlStartIndexListener#OnStartIndexWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlStartIndex_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlStartIndex_Write not implemented method");	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSubstringListener#OnControlSubstring_Changed(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlSubstring_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"OperatorControl.OnControlSubstring_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSubstringListener#OnControlSubstring_Init(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlSubstring_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"OperatorControl.OnControlSubstring_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSubstringListener#OnControlSubstring_Loaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlSubstring_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"OperatorControl.OnControlSubstring_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSubstringListener#OnControlSubstring_Read(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlSubstring_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"OperatorControl.OnControlSubstring_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSubstringListener#OnControlSubstring_Rendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlSubstring_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlSubstring_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSubstringListener#OnControlSubstring_Write(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlSubstring_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlSubstring_Write not implemented method");
	}

	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlTryParseListener#OnControlTryParse_Changed(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"OperatorControl.OnControlTryParse_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlTryParseListener#OnControlTryParse_Init(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this,
				"OperatorControl.OnControlTryParse_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlTryParseListener#OnControlTryParse_Loaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException{
		
		CliTools.ThrowNotImplementedException(this,
				"OperatorControl.OnControlTryParse_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlTryParseListener#OnControlTryParse_Read(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException	{
		
		CliTools.ThrowNotImplementedException(this,
				"OperatorControl.OnControlTryParse_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlTryParseListener#OnControlTryParse_Rendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException	{
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlTryParse_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlTryParseListener#OnControlTryParse_Write(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"OperatorControl.OnControlTryParse_Write not implemented method");
	}
	/**
	 * Removes Contains event listener
	 * @param listener
	 */
	public final synchronized void removeControlContainsListener(IControlContainsListener listener)
	{
		this.containsListeners.remove(listener);
	}
	/**
	 * Removes {@link Delimiters} event {@link IDataTypeControlListener}.
	 * @param listener {@link IDataTypeControlListener} the listener
	 */
	public final synchronized void removeControlDelimitersListener(IControlDelimitersListener listener)
	{
		this.delimitersListeners.remove(listener);
	}
	/**
	 * Removes {@link EndIndex} event {@link IDataTypeControlListener}.
	 * @param listener {@link IDataTypeControlListener} the listener
	 */
	public final synchronized void removeControlEndIndexListener(IControlEndIndexListener listener)
	{
		this.endIndexListeners.remove(listener);
	}
	/**
	 * Removes Equals event listener
	 * @param listener
	 */
	public final synchronized void removeControlEqualsListener(IControlEqualsListener listener)
	{
		this.equalsListeners.remove(listener);
	}
	/**
	 * Removes {@link Remove} event listener
	 * @param listener
	 */
	public final synchronized void removeControlRemoveListener(IControlRemoveListener listener)
	{
		this.removeListeners.remove(listener);
	}
	/**
	 * Removes {@link Replace} event {@link IOperatorControlListener}.
	 * @param listener
	 */
	public final synchronized void removeControlReplaceListener(IControlReplaceListener listener)
	{
		this.replaceListeners.remove(listener);
	}
	/**
	 * Removes {@link Split} event {@link IOperatorControlListener}.
	 * @param listener
	 */
	public final synchronized void removeControlSplitListener(IControlSplitListener listener)
	{
		this.splitListeners.remove(listener);
	}
	/**
	 * Removes {@link StartIndex} event {@link IOperatorControlListener}.
	 * @param listener {@link IOperatorControlListener} the listener
	 */
	public final synchronized void removeControlStartIndexListener(IControlStartIndexListener listener)
	{
		this.startIndexListeners.remove(listener);
	}
	/**
	 * Removes {@link Substring} event {@link IOperatorControlListener}.
	 * @param listener
	 */
	public final synchronized void removeControlSubstringListener(IControlSubstringListener listener)
	{
		this.substringListeners.remove(listener);
	}
	/**
	 * Removes {@link TryParse} event {@link IOperatorControlListener}.
	 * @param listener
	 */
	public final synchronized void removeControlTryParseListener(IControlTryParseListener listener)
	{
		this.tryParseListeners.remove(listener);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.PlaceholderControl#setIsRendered(java.lang.Boolean)
	 */
	@Override
	public void render(Boolean value) 
	{
		super.render(value);
		
		RML message = this.message;
		
		if(message instanceof Delimiters)
		{
			ControlDelimitersEvent(new ControlEventArgs(this, ControlEventType.RENDER, message));
		}
		else if(message instanceof Contains)
		{
			ControlContainsEvent(new ControlEventArgs(this, ControlEventType.RENDER, message));
		}
		else if(message instanceof Equals)
		{
			ControlEqualsEvent(new ControlEventArgs(this, ControlEventType.RENDER, message));
		}
		else if(message instanceof Remove)
		{
			ControlRemoveEvent(new ControlEventArgs(this, ControlEventType.RENDER, message));
		}
		else if(message instanceof Replace)
		{
			ControlReplaceEvent(new ControlEventArgs(this, ControlEventType.RENDER, message));
		}
		else if(message instanceof Split)
		{
			ControlSplitEvent(new ControlEventArgs(this, ControlEventType.RENDER, message));
		}
		else if(message instanceof Substring)
		{
			ControlSubstringEvent(new ControlEventArgs(this, ControlEventType.RENDER, message));
		}
		else if(message instanceof TryParse)
		{
			ControlTryParseEvent(new ControlEventArgs(this, ControlEventType.RENDER, message));
		}
		else if(message instanceof EndIndex)
		{
			ControlEndIndexEvent(new ControlEventArgs(this, ControlEventType.RENDER, message));
		}
		else if(message instanceof StartIndex)
		{
			ControlStartIndexEvent(new ControlEventArgs(this, ControlEventType.RENDER, message));
		}
	}
	/**
	 * Initializes the operator message control.
	 */
	private final void initOperatorControl(TMessage message, IListener parent) 
	{
		//Initialize event's arguments.
		ControlEventArgs e = new ControlEventArgs(this, ControlEventType.INIT, message);
		
		//Check by instance to add control listeners efficiently.
		if(message instanceof Contains)
		{
			addControlContainsListener(this);
			
			//Check if parent is null and match with parent listener's type.
			if(parent != null ?
					parent instanceof IControlContainsListener : false) {
				
				//Parent is properly listening initialization event
				addControlContainsListener(IControlContainsListener.class.cast(parent));
			}
			//Fire Contains event
			ControlContainsEvent(e);
		}
		else if(message instanceof Delimiters)
		{
			addControlDelimitersListener(this);
			
			//Check if parent is null and match with parent listener's type.
			if(parent != null ?
					parent instanceof IControlDelimitersListener : false) {
				
				//Parent is properly listening initialization event
				addControlDelimitersListener(IControlDelimitersListener.class.cast(parent));
			}
			//Fire Delimiters event
			ControlDelimitersEvent(e);
		}
		else if(message instanceof EndIndex)
		{
			addControlEndIndexListener(this);
		
			//Check if parent is null and match with parent listener's type.
			if(parent != null ?
					parent instanceof IControlEndIndexListener : false) {
				
				//Parent is properly listening initialization event
				addControlEndIndexListener(IControlEndIndexListener.class.cast(parent));
			}
			//Fire EndIndex event
			ControlEndIndexEvent(e);
		}
		//Check if parent is null and match with parent listener's type.
		else if(message instanceof StartIndex)
		{
			addControlStartIndexListener(this);		
			
			if(parent != null ?
					parent instanceof IControlStartIndexListener : false) {

				//Parent is properly listening initialization event
				addControlStartIndexListener(IControlStartIndexListener.class.cast(parent));
			}
			//Fire StartIndex event
			ControlStartIndexEvent(e);
		}
		//Check if parent is null and match with parent listener's type.
		else if(message instanceof Equals)
		{
			addControlEqualsListener(this);
			
			if(parent != null ?
					parent instanceof IControlEqualsListener : false) {
				
				//Parent is properly listening initialization event
				addControlEqualsListener(IControlEqualsListener.class.cast(parent));
			}
			//Fire Equals event
			ControlEqualsEvent(e);
		}
		//Check if parent is null and match with parent listener's type.
		else if(message instanceof Remove)
		{
			addControlRemoveListener(this);
			
			if(parent != null ?
					parent instanceof IControlRemoveListener : false) {
				
				//Parent is properly listening initialization event
				addControlRemoveListener(IControlRemoveListener.class.cast(parent));
			}
			//Fire Remove event
			ControlRemoveEvent(e);
		}
		//Check if parent is null and match with parent listener's type.
		else if(message instanceof Replace)
		{
			addControlReplaceListener(this);
		
			if(parent != null ?
					parent instanceof IControlReplaceListener : false) {
				
				//Parent is properly listening initialization event
				addControlReplaceListener(IControlReplaceListener.class.cast(parent));
			}
			//Fire Replace event
			ControlReplaceEvent(e);
		}
		//Check if parent is null and match with parent listener's type.
		else if(message instanceof Split)
		{
			addControlSplitListener(this);
		
			if(parent != null ?
					parent instanceof IControlSplitListener : false) {
				
				//Parent is properly listening initialization event
				addControlSplitListener(IControlSplitListener.class.cast(parent));
			}
			//Fire Split event
			ControlSplitEvent(e);
		}
		//Check if parent is null and match with parent listener's type.
		else if(message instanceof Substring)
		{
			addControlSubstringListener(this);
			
			if(parent != null ?
					parent instanceof IControlSubstringListener : false) {
				
				//Parent is properly listening initialization event
				addControlSubstringListener(IControlSubstringListener.class.cast(parent));
			}
			//Fire Substring event
			ControlSubstringEvent(e);
		}
		//Check if parent is null and match with parent listener's type.
		else if(message instanceof TryParse)
		{
			addControlTryParseListener(this);
		
			if(parent != null ?
					parent instanceof IControlTryParseListener : false) {
				
				//Parent is properly listening initialization event
				addControlTryParseListener(IControlTryParseListener.class.cast(parent));
			}
			//Fire TryParse event
			ControlTryParseEvent(e);
		}
	}
	/**
	 * Fires {@link Contains} event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlContainsEvent(ControlEventArgs e) 
	{
		for (IControlContainsListener listener : containsListeners) 
		{
			try 
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlContains_Init(this, e);
						break;
					case READ:
						listener.OnControlContains_Read(this, e);
						break;
					case LOAD:
						listener.OnControlContains_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlContains_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlContains_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlContains_Write(this, e);
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
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlDelimitersEvent(ControlEventArgs e)
	{
		for (IControlDelimitersListener listener : delimitersListeners) 
		{
			try 
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlDelimiters_Init(this, e);
						break;
					case READ:
						listener.OnControlDelimiters_Read(this, e);
						break;
					case LOAD:
						listener.OnControlDelimiters_Loaded(this, e);
						break;	
					case CHANGE:
						listener.OnControlDelimiters_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlDelimiters_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlDelimiters_Write(this, e);
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
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlEndIndexEvent(ControlEventArgs e)
	{
		for (IControlEndIndexListener listener : endIndexListeners) 
		{
			try 
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlEndIndex_Init(this, e);
					break;
					case READ:
						listener.OnControlEndIndex_Read(this, e);
					break;
					case LOAD:
						listener.OnControlEndIndex_Loaded(this, e);
					break;
					case CHANGE:
						listener.OnControlEndIndex_Changed(this, e);
					break;
					case RENDER:
						listener.OnControlEndIndex_Rendered(this, e);
					break;
					case WRITE:
						listener.OnControlEndIndex_Write(this, e);
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
	 * Fires {@link Equals} event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlEqualsEvent(ControlEventArgs e) 
	{
		for (IControlEqualsListener listener : equalsListeners) 
		{
			try 
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlEquals_Init(this, e);
						break;
					case READ:
						listener.OnControlEquals_Read(this, e);
						break;
					case LOAD:
						listener.OnControlEquals_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlEquals_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlEquals_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlEquals_Write(this, e);
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
	 * Fires {@link Remove} event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlRemoveEvent(ControlEventArgs e) 
	{
		for (IControlRemoveListener listener : removeListeners) 
		{
			try 
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlRemove_Init(this, e);
						break;
					case READ:
						listener.OnControlRemove_Read(this, e);
						break;
					case LOAD:
						listener.OnControlRemove_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlRemove_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlRemove_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlRemove_Write(this, e);
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
	 * Fires {@link Replace} event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlReplaceEvent(ControlEventArgs e)
	{
		for (IControlReplaceListener listener : replaceListeners) 
		{
			try
			{
				switch (e.getControlEventType())
				{
					case INIT:						
						listener.OnControlReplace_Init(this, e);
						break;
					case READ:						
						listener.OnControlReplace_Read(this, e);
						break;
					case LOAD:						
						listener.OnControlReplace_Loaded(this, e);
						break;
					case CHANGE:						
						listener.OnControlReplace_Changed(this, e);
						break;
					case RENDER:						
						listener.OnControlReplace_Rendered(this, e);
						break;
					case WRITE:						
						listener.OnControlReplace_Write(this, e);
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
	 * Fires {@link Split} event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlSplitEvent(ControlEventArgs e) 
	{
		for (IControlSplitListener listener : splitListeners) 
		{
			try
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlSplit_Init(this, e);
						break;
					case READ:
						listener.OnControlSplit_Read(this, e);
						break;
					case LOAD:
						listener.OnControlSplit_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlSplit_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlSplit_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlSplit_Write(this, e);
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
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlStartIndexEvent(ControlEventArgs e)
	{
		for (IControlStartIndexListener listener : startIndexListeners) 
		{
			try
			{
				switch (e.getControlEventType()) 
				{
					case INIT:
						listener.OnControlStartIndex_Init(this, e);
						break;
					case READ:
						listener.OnControlStartIndex_Read(this, e);
						break;
					case LOAD:
						listener.OnControlStartIndex_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlStartIndex_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlStartIndex_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlStartIndex_Write(this, e);
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
	 * Fires {@link Substring} event
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlSubstringEvent(ControlEventArgs e) 
	{
		for (IControlSubstringListener listener : substringListeners) 
		{
			try 
			{
				switch (e.getControlEventType())
				{
					case INIT:
						listener.OnControlSubstring_Init(this, e);
						break;
					case READ:
						listener.OnControlSubstring_Read(this, e);
						break;
					case LOAD:
						listener.OnControlSubstring_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlSubstring_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlSubstring_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlSubstring_Write(this, e);
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
	 * Fires {@link TryParse} event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlTryParseEvent(ControlEventArgs e) 
	{
		for (IControlTryParseListener listener : tryParseListeners) 
		{
			try
			{
				switch (e.getControlEventType()) {
				case INIT:
					listener.OnControlTryParse_Init(this, e);
					break;
				case READ:
					listener.OnControlTryParse_Read(this, e);
					break;
				case LOAD:
					listener.OnControlTryParse_Loaded(this, e);
					break;
				case CHANGE:
					listener.OnControlTryParse_Changed(this, e);
					break;
				case RENDER:
					listener.OnControlTryParse_Rendered(this, e);
					break;
				case WRITE:
					listener.OnControlTryParse_Write(this, e);
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