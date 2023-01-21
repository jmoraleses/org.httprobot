/**
 * 
 */
package org.httprobot.core.controls;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;

import org.httprobot.common.definitions.Enums.ControlEventType;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.Html;
import org.httprobot.common.RML;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.placeholders.operators.html.Anchor;
import org.httprobot.common.placeholders.operators.html.Division;
import org.httprobot.common.placeholders.operators.html.Element;
import org.httprobot.common.placeholders.operators.html.Page;
import org.httprobot.common.placeholders.operators.html.Table;
import org.httprobot.common.placeholders.operators.html.TableCell;
import org.httprobot.common.placeholders.operators.html.TableRow;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.ContentData;
import org.httprobot.core.common.Enums.HtmlData;
import org.httprobot.core.controls.html.interfaces.IControlAnchorListener;
import org.httprobot.core.controls.html.interfaces.IControlDivisionListener;
import org.httprobot.core.controls.html.interfaces.IControlElementListener;
import org.httprobot.core.controls.html.interfaces.IControlPageListener;
import org.httprobot.core.controls.html.interfaces.IControlTableCellListener;
import org.httprobot.core.controls.html.interfaces.IControlTableListener;
import org.httprobot.core.controls.html.interfaces.IControlTableRowListener;
import org.httprobot.core.controls.interfaces.listeners.IHtmlControlListener;
import org.httprobot.core.controls.interfaces.listeners.IOperatorControlListener;
import org.httprobot.core.interfaces.IDataMappingImpl;

/**
 * Abstract {@link Html} message control implementation class. Inherits {@link OperatorControl}.
 * <br>
 * @author joan
 *
 */
public abstract class HtmlControl
	<TMessage extends Html, TListener extends IControlListener>
		extends OperatorControl<TMessage, TListener> 
		implements IHtmlControlListener,
			IDataMappingImpl<HtmlData, Object>  {

	/**
	 * -7162333579805767427L
	 */
	private static final long serialVersionUID = -7162333579805767427L;

	/**
	 * {@link Anchor} message control listeners.
	 */
	private Vector<IControlAnchorListener> anchorListeners = null;
	/**
	 * {@link Division} message control listeners.
	 */
	private Vector<IControlDivisionListener> divisionListeners = null;
	/**
	 * {@link Anchor} message control listeners.
	 */
	private Vector<IControlElementListener> elementListeners = null;
	/**
	 * {@link Page} message control listeners.
	 */
	private Vector<IControlPageListener> pageListeners = null;
	/**
	 * {@link TableCell} message control listeners.
	 */
	private Vector<IControlTableCellListener> tableCellListeners = null;
	/**
	 * {@link Table} message control listeners.
	 */
	private Vector<IControlTableListener> tableListeners = null;
	/**
	 * {@link TableRow} message control listeners.
	 */
	private Vector<IControlTableRowListener> tableRowListeners = null;
	
	/**
	 * The attributes of the message.
	 */
	Map<HtmlData, Object> data;
	
	/**
	 * {@link Html} message control default class constructor,
	 */
	public HtmlControl()
	{
		super();
		
		//Initialize the HtmlControl.
		initHtmlControl(null, null);
	}
	/**
	 * {@link Html} message control class constructor,
	 * @param parent the parent
	 * @param message the message
	 */
	public HtmlControl(TListener parent, TMessage message) 
	{
		super(parent, message);
		
		//Initialize the HtmlControl.
		initHtmlControl(message, parent);
	}
	/** 
	 * Adds {@link Anchor} message control event listener.
	 * @param listener {@link IControlAnchorListener} the listener
	 */
	public final synchronized void addControlAnchorListener(IControlAnchorListener listener)
	{
		if(this.anchorListeners == null)
		{
			this.anchorListeners = new Vector<IControlAnchorListener>();
		}
		this.anchorListeners.add(listener);
	}
	/** 
	 * Adds {@link Division} message control event listener.
	 * @param listener {@link IControlDivisionListener} the listener
	 */
	public final synchronized void addControlDivisionListener(IControlDivisionListener listener)
	{
		if(this.divisionListeners == null)
		{
			this.divisionListeners = new Vector<IControlDivisionListener>();
		}
		this.divisionListeners.add(listener);
	}
	/** 
	 * Adds {@link Element} message control event listener.
	 * @param listener {@link IControlElementListener} the listener
	 */
	public final synchronized void addControlElementListener(IControlElementListener listener)
	{
		if(this.elementListeners == null)
		{
			this.elementListeners = new Vector<IControlElementListener>();
		}
		this.elementListeners.add(listener);
	}
	/**
	 * Adds {@link Page} message control event listener.
	 * @param listener {@link IControlPageListener} the listener
	 */
	public final synchronized void addControlPageListener(IControlPageListener listener)
	{
		if(this.pageListeners == null)
		{
			this.pageListeners = new Vector<IControlPageListener>();
		}
		this.pageListeners.add(listener);
	}
	/** 
	 * Adds {@link TableCell} message control event listener.
	 * @param listener {@link IControlTableCellListener} the listener
	 */
	public final synchronized void addControlTableCellListener(IControlTableCellListener listener)
	{
		if(this.tableCellListeners == null)
		{
			this.tableCellListeners = new Vector<IControlTableCellListener>();
		}
		this.tableCellListeners.add(listener);
	}
	/** 
	 * Adds {@link Table} message control event listener.
	 * @param listener {@link IControlTableListener} the listener
	 */
	public final synchronized void addControlTableListener(IControlTableListener listener)
	{
		if(this.tableListeners == null)
		{
			this.tableListeners = new Vector<IControlTableListener>();
		}
		this.tableListeners.add(listener);
	}
	/** 
	 * Adds {@link TableRow} message control event listener.
	 * @param listener {@link IControlTableRowListener} the listener
	 */
	public final synchronized void addControlTableRowListener(IControlTableRowListener listener)
	{
		if(this.tableRowListeners == null)
		{
			this.tableRowListeners = new Vector<IControlTableRowListener>();
		}
		this.tableRowListeners.add(listener);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#ChangeControl(org.httprobot.common.RML)
	 */
	@Override
	public void ChangeControl(RML message) 
	{
		super.ChangeControl(message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#InitControl(org.httprobot.common.RML)
	 */
	@Override
	public void InitControl(RML message) 
	{
		super.InitControl(message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#LoadControl(org.httprobot.common.RML)
	 */
	@Override
	public void LoadControl(RML message) 
	{
		//Insert loaded inherited data
		this.put(HtmlData.UUID, message.getUuid());
		this.put(HtmlData.INHERITED, message.getInherited());
		
		if(!Boolean.class.cast(this.get(ContentData.INHERITED)) ? 
				message.getRuntimeOptions() != null : false) {

			for(RuntimeOptions option : message.getRuntimeOptions())
			{
				this.put(HtmlData.RUNTIME_OPTIONS, option);
			}
		}

		//Load goes on.
		ControlLoadedEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		
		if(this.message instanceof Page)
		{
			ControlPageEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		}
		else if(this.message instanceof Anchor)
		{
			ControlAnchorEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		}
		else if(this.message instanceof Element)
		{
			ControlElementEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		}
		else if(this.message instanceof Division)
		{
			ControlDivisionEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		}
		else if(this.message instanceof Table)
		{
			ControlTableEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		}
		else if(this.message instanceof TableRow)
		{
			ControlTableRowEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		}
		else if(this.message instanceof TableCell)
		{
			ControlTableCellEvent(new ControlEventArgs(this, ControlEventType.LOAD, this.message));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#RenderControl()
	 */
	@Override
	public void RenderControl(RML message) 
	{
		super.RenderControl(message);
		
		if(message instanceof Page)
		{
			ControlPageEvent(new ControlEventArgs(this, ControlEventType.RENDER, message));
		}
		else if(message instanceof Anchor)
		{
			
		}
		else if(message instanceof Element)
		{
			ControlElementEvent(new ControlEventArgs(this, ControlEventType.RENDER, message));
		}
		else if(message instanceof Division)
		{
			ControlDivisionEvent(new ControlEventArgs(this, ControlEventType.RENDER, message));
		}
		else if(message instanceof Table)
		{
			ControlTableEvent(new ControlEventArgs(this, ControlEventType.RENDER, message));
		}
		else if(message instanceof TableRow)
		{
			ControlTableRowEvent(new ControlEventArgs(this, ControlEventType.RENDER, message));
		}
		else if(message instanceof TableCell)
		{
			ControlTableCellEvent(new ControlEventArgs(this, ControlEventType.RENDER, message));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Change(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Initialize(Object sender, ControlEventArgs e)
			throws InconsistenMessageException, NotImplementedException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Load(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract void Render(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public abstract  void Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlPageAnchorListener#OnControlPageAnchor_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAnchor_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlAnchor_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlPageAnchorListener#OnControlPageAnchor_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAnchor_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlAnchor_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlPageAnchorListener#OnControlPageAnchor_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAnchor_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlAnchor_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlPageAnchorListener#OnControlPageAnchor_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAnchor_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlAnchor_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlPageAnchorListener#OnControlPageAnchor_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAnchor_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlAnchor_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlPageAnchorListener#OnControlPageAnchor_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAnchor_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlAnchor_Write not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlDivisionListener#OnControlDivision_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDivision_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlDivision_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlDivisionListener#OnControlDivision_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDivision_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlDivision_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlDivisionListener#OnControlDivision_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDivision_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlDivision_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlDivisionListener#OnControlDivision_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDivision_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlDivision_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlDivisionListener#OnControlDivision_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDivision_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlDivision_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlDivisionListener#OnControlDivision_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDivision_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlDivision_Write not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlElementListener#OnControlElement_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlElement_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlElement_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlElementListener#OnControlElement_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlElement_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlElement_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlElementListener#OnControlElement_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlElement_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlElement_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlElementListener#OnControlElement_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlElement_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlElement_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlElementListener#OnControlElement_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlElement_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlElement_Rendered not implemented method");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlElementListener#OnControlElement_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlElement_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlElement_Write not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlPageListener#OnControlPage_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPage_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlPage_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlPageListener#OnControlPage_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPage_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlPage_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlPageListener#OnControlPage_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPage_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlPage_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlPageListener#OnControlPage_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPage_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlPage_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlPageListener#OnControlPage_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPage_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlPage_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlPageListener#OnControlPage_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPage_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlPage_Write not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableListener#OnControlTable_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTable_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlTable_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableListener#OnControlTable_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTable_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlTable_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableListener#OnControlTable_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTable_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlTable_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableListener#OnControlTable_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTable_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlTable_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableListener#OnControlTable_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTable_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlTable_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableListener#OnControlTable_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTable_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlTable_Write not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableCellListener#OnControlTableCell_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableCell_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlTableCell_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableCellListener#OnControlTableCell_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableCell_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlTableCell_Init not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableCellListener#OnControlTableCell_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableCell_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlTableCell_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableCellListener#OnControlTableCell_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableCell_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlTableCell_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableCellListener#OnControlTableCell_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableCell_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlTableCell_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableCellListener#OnControlTableCell_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableCell_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlTableCell_Write not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableRowListener#OnControlTableRow_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableRow_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlTableRow_Changed not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableRowListener#OnControlTableRow_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableRow_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlTableRow_Init not implemented method");	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableRowListener#OnControlTableRow_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableRow_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlTableRow_Loaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableRowListener#OnControlTableRow_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableRow_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlTableRow_Read not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableRowListener#OnControlTableRow_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableRow_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlTableRow_Rendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.interfaces.IControlTableRowListener#OnControlTableRow_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableRow_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"HtmlControl.OnControlTableRow_Write not implemented method");
	}
	/** 
	 * Removes {@link Anchor} message control event listener.
	 * @param listener {@link IControlAnchorListener} the listener
	 */
	public final synchronized void removeControlAnchorListener(IControlAnchorListener listener)
	{
		this.anchorListeners.remove(listener);
	}
	/** 
	 * Removes {@link Division} message control event listener.
	 * @param listener {@link IControlDivisionListener} the listener
	 */
	public final synchronized void removeControlDivisionListener(IControlDivisionListener listener)
	{
		this.divisionListeners.remove(listener);
	}
	/** 
	 * Removes {@link Element} message control event listener.
	 * @param listener {@link IControlElementListener} the listener
	 */
	public final synchronized void removeControlElementListener(IControlElementListener listener)
	{
		this.elementListeners.remove(listener);
	}
	/**
	 * Removes {@link Page} message control event listener.
	 * @param listener {@link IOperatorControlListener} the listener
	 */
	public final synchronized void removeControlPageListener(IControlPageListener listener)
	{
		this.pageListeners.remove(listener);
	}
	/** 
	 * Removes {@link TableCell} message control event listener.
	 * @param listener {@link IControlTableCellListener} the listener
	 */
	public final synchronized void removeControlTableCellListener(IControlTableCellListener listener)
	{
		this.tableCellListeners.remove(listener);
	}
	/** 
	 * Removes {@link Table} message control event listener.
	 * @param listener {@link IControlTableListener} the listener
	 */
	public final synchronized void removeControlTableListener(IControlTableListener listener)
	{
		this.tableListeners.remove(listener);
	}
	/** 
	 * Removes {@link TableRow} message control event listener.
	 * @param listener {@link IControlTableRowListener} the listener
	 */
	public final synchronized void removeControlTableRowListener(IControlTableRowListener listener)
	{
		this.tableRowListeners.remove(listener);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#setIsRendered(java.lang.Boolean)
	 */
	@Override
	public void render(Boolean value) 
	{
		super.render(value);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#setMessage(org.httprobot.common.Operator)
	 */
	@Override
	public void controlMessage(TMessage message) 
	{
		super.controlMessage(message);
	}
	/**
	/* (non-Javadoc)
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() 
	{
		return this.data.size();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() 
	{
		return this.data.isEmpty();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey(Object key) 
	{
		return this.data.containsKey(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object value) 
	{
		return this.data.containsValue(value);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public Object get(Object key) 
	{
		return this.data.get(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Object put(HtmlData key, Object value) 
	{
		//Default HTML attribute values.
		switch (key)
		{
		case UUID:
			this.message.setUuid(UUID.class.cast(value));
			break;

		case INHERITED:
			if (value != null)
			{
				this.message.setInherited(Boolean.class.cast(value));
			} 
			else
			{
				// Set default value.
				value = new Boolean(true);
				
				this.message.setInherited(Boolean.class.cast(value));
			}
			break;

		case RUNTIME_OPTIONS:
			this.message.getRuntimeOptions().add(RuntimeOptions.class.cast(value));
			break;

		case TITLE:
			this.message.setTitle(String.class.cast(value));
			break;

		case ID:
			this.message.setId(String.class.cast(value));
			break;

		case STYLE:
			this.message.setStyle(String.class.cast(value));
			break;

		case CLASS:
			this.message.setClassName(String.class.cast(value));
			break;

		case ANCHOR:
			// Check input value is the Action's message.
			if(value.equals(this.message))
			{
				RenderControl(this.message);
				ControlAnchorEvent(new ControlEventArgs(this, ControlEventType.RENDER, this.message));
			}
			break;
		
		case DIVISION:
			// Check input value is the Division's message.
			if(value.equals(this.message))
			{
				RenderControl(this.message);
				ControlDivisionEvent(new ControlEventArgs(this, ControlEventType.RENDER, this.message));
			}
			break;
			
		case ELEMENT:
			// Check input value is the Element's message.
			if(value.equals(this.message))
			{
				RenderControl(this.message);
				ControlElementEvent(new ControlEventArgs(this, ControlEventType.RENDER, this.message));
			}
			break;
			
		case PAGE:
			// Check input value is the Page's message.
			if(value.equals(this.message))
			{
				RenderControl(this.message);
				ControlPageEvent(new ControlEventArgs(this, ControlEventType.RENDER, this.message));
			}
			break;
			
		case TABLE:
			// Check input value is the Table's message.
			if(value.equals(this.message))
			{
				RenderControl(this.message);
				ControlTableEvent(new ControlEventArgs(this, ControlEventType.RENDER, this.message));
			}
			break;
			
		case TABLE_CELL:
			// Check input value is the TableCell's message.
			if(value.equals(this.message))
			{
				RenderControl(this.message);
				ControlTableCellEvent(new ControlEventArgs(this, ControlEventType.RENDER, this.message));
			}
			break;
			
		case TABLE_ROW:
			// Check input value is the TableRow's message.
			if(value.equals(this.message))
			{
				RenderControl(this.message);
				ControlTableRowEvent(new ControlEventArgs(this, ControlEventType.RENDER, this.message));
			}
			break;
		default:
			break;
		}
		
		//Update HtmlControl data
		return this.data.put(key, value);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public Object remove(Object key) 
	{
		return this.data.remove(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends HtmlData, ? extends Object> m) 
	{		
		for(HtmlData att : m.keySet())
		{
			if(this.put(att, m.get(att)) == null)
			{
			}
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#clear()
	 */
	@Override
	public void clear() 
	{
		this.data.clear();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<HtmlData> keySet() 
	{
		return this.data.keySet();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<Object> values() 
	{
		return this.data.values();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<java.util.Map.Entry<HtmlData, Object>> entrySet() 
	{
		return this.data.entrySet();
	}
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<HtmlData> iterator() 
	{
		return this.data.keySet().iterator();
	}
	/** Initializes {@link Html} message control.
	 * @param message {@link RML} the message
	 */
	private final void initHtmlControl(TMessage message, TListener parent)
	{
		//Initialize control's data.
		this.data = new HashMap<HtmlData, Object>();
		
		//Initialize event's arguments. 
		ControlEventArgs e = new ControlEventArgs(this, ControlEventType.INIT, message);
		
		if(message instanceof Page)
		{
			addControlPageListener(this);

			//Check if parent is null and match with parent listener's type.
			if(parent != null ?
					parent instanceof IControlPageListener : false) {

				//Parent is properly listening initialization event
				addControlPageListener(IControlPageListener.class.cast(parent));
			}
			//Send event to parent
			ControlPageEvent(e);
		}
		else if(message instanceof Anchor)
		{
			addControlAnchorListener(this);
			
			//Check if parent is null and match with parent listener's type.
			if(parent != null ?
					parent instanceof IControlAnchorListener : false) {

				//Parent is properly listening initialization event
				addControlAnchorListener(IControlAnchorListener.class.cast(parent));
			}
			//Send event to parent
			ControlAnchorEvent(e);
		}
		else if(message instanceof Element)
		{
			addControlElementListener(this);
			
			//Check if parent is null and match with parent listener's type.
			if(parent != null ?
					parent instanceof IControlElementListener : false) {

				//Parent is properly listening initialization event
				addControlElementListener(IControlElementListener.class.cast(parent));
			}
			//Send event to parent
			ControlElementEvent(e);
		}
		else if(message instanceof Division)
		{
			addControlDivisionListener(this);
			
			//Check if parent is null and match with parent listener's type.
			if(parent != null ?
					parent instanceof IControlDivisionListener : false) {

				//Parent is properly listening initialization event
				addControlDivisionListener(IControlDivisionListener.class.cast(parent));
			}
			//Send event to parent
			ControlDivisionEvent(e);
		}
		else if(message instanceof Table)
		{
			addControlTableListener(this);
			
			//Check if parent is null and match with parent listener's type.
			if(parent != null ?
					parent instanceof IControlTableListener : false) {

				//Parent is properly listening initialization event
				addControlTableListener(IControlTableListener.class.cast(parent));
			}
			//Send event to parent
			ControlTableEvent(e);
		}
		else if(message instanceof TableRow)
		{
			addControlTableRowListener(this);

			//Check if parent is null and match with parent listener's type.
			if(parent != null ?
					parent instanceof IControlTableRowListener : false) {

				//Parent is properly listening initialization event
				addControlTableRowListener(IControlTableRowListener.class.cast(parent));
			}
			//Send event to parent
			ControlTableRowEvent(e);
		}
		else if(message instanceof TableCell)
		{
			addControlTableCellListener(this);

			//Check if parent is null and match with parent listener's type.
			if(parent != null ?
					parent instanceof IControlTableCellListener : false) {

				//Parent is properly listening initialization event
				addControlTableCellListener(IControlTableCellListener.class.cast(parent));
			}
			//Send event to parent
			ControlTableCellEvent(e);
		}
	}
	/**
	 * Fires {@link Anchor} message control event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlAnchorEvent(ControlEventArgs e) 
	{
		for (IControlAnchorListener listener : anchorListeners)
		{
			try 
			{
				switch(e.getControlEventType())
				{
					case INIT:
						listener.OnControlAnchor_Init(this, e);
						break;
					case READ:
						listener.OnControlAnchor_Read(this, e);
						break;
					case LOAD:
						listener.OnControlAnchor_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlAnchor_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlAnchor_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlAnchor_Write(this, e);
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
	 * Fires {@link Anchor}  message control event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlElementEvent(ControlEventArgs e) 
	{
		for (IControlElementListener listener : elementListeners)
		{
			try 
			{
				switch(e.getControlEventType())
				{
					case INIT:
						listener.OnControlElement_Init(this, e);
						break;
					case READ:
						listener.OnControlElement_Read(this, e);
						break;
					case LOAD:
						listener.OnControlElement_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlElement_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlElement_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlElement_Write(this, e);
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
	 * Fires {@link Page} message control event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlPageEvent(ControlEventArgs e) 
	{
		for (IControlPageListener listener : pageListeners)
		{
			try 
			{
				switch(e.getControlEventType())
				{
					case INIT:
						listener.OnControlPage_Init(this, e);
						break;
					case READ:
						listener.OnControlPage_Read(this, e);
						break;
					case LOAD:
						listener.OnControlPage_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlPage_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlPage_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlPage_Write(this, e);
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
	 * Fires {@link Division} message control event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlDivisionEvent(ControlEventArgs e) 
	{
		for (IControlDivisionListener listener : this.divisionListeners)
		{
			try 
			{
				switch(e.getControlEventType())
				{
					case INIT:
						listener.OnControlDivision_Init(this, e);
						break;
					case READ:
						listener.OnControlDivision_Read(this, e);
						break;
					case LOAD:
						listener.OnControlDivision_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlDivision_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlDivision_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlDivision_Write(this, e);
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
	 * Fires {@link Table} message control event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlTableEvent(ControlEventArgs e) 
	{
		for (IControlTableListener listener : this.tableListeners)
		{
			try 
			{
				switch(e.getControlEventType())
				{
					case INIT:
						listener.OnControlTable_Init(this, e);
						break;
					case READ:
						listener.OnControlTable_Read(this, e);
						break;
					case LOAD:
						listener.OnControlTable_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlTable_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlTable_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlTable_Write(this, e);
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
	 * Fires {@link TableRow} message control event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlTableRowEvent(ControlEventArgs e) 
	{
		for (IControlTableRowListener listener : this.tableRowListeners)
		{
			try 
			{
				switch(e.getControlEventType())
				{
					case INIT:
						listener.OnControlTableRow_Init(this, e);
						break;
					case READ:
						listener.OnControlTableRow_Read(this, e);
						break;
					case LOAD:
						listener.OnControlTableRow_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlTableRow_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlTableRow_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlTableRow_Write(this, e);
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
	 * Fires {@link TableCell} message control event.
	 * @param e {@link ControlEventArgs} the arguments
	 */
	protected final void ControlTableCellEvent(ControlEventArgs e) 
	{
		for (IControlTableCellListener listener : this.tableCellListeners)
		{
			try 
			{
				switch(e.getControlEventType())
				{
					case INIT:
						listener.OnControlTableCell_Init(this, e);
						break;
					case READ:
						listener.OnControlTableCell_Read(this, e);
						break;
					case LOAD:
						listener.OnControlTableCell_Loaded(this, e);
						break;
					case CHANGE:
						listener.OnControlTableCell_Changed(this, e);
						break;
					case RENDER:
						listener.OnControlTableCell_Rendered(this, e);
						break;
					case WRITE:
						listener.OnControlTableCell_Write(this, e);
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
