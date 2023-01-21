package org.httprobot.core.controls.html;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.operators.html.Anchor;
import org.httprobot.common.placeholders.operators.html.Division;
import org.httprobot.common.placeholders.operators.html.Element;
import org.httprobot.common.placeholders.operators.html.Page;
import org.httprobot.common.placeholders.operators.html.Table;
import org.httprobot.common.placeholders.operators.html.TableCell;
import org.httprobot.common.placeholders.operators.html.TableRow;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.HtmlData;
import org.httprobot.core.controls.html.interfaces.IControlPageListener;
import org.httprobot.core.controls.interfaces.impl.IControlImpl;
import org.httprobot.core.controls.interfaces.impl.IHtmlControlImpl;


/**
 * {@link Page} message control class. Inherits {@link AbstractHtmlControl}.
 * <br>
 * @author joan
 *
 */
@XmlRootElement
public class PageControl 
		extends AbstractHtmlControl<Page, IControlPageListener>
		implements IHtmlControlImpl {
	
	/**
	 * 1188429079391753214L
	 */
	private static final long serialVersionUID = 1188429079391753214L;
	
	protected AnchorControl anchorControl;
	protected DivisionControl divisionControl;
	protected ElementControl elementControl;
	protected PageControl pageControl;
	protected TableCellControl tableCellControl;
	protected TableControl tableControl;
	protected TableRowControl tableRowControl;
	
	/**
	 * {@link Page} message control default class constructor.
	 */
	public PageControl()
	{
		super();

		//Initialize message.
		this.message = new Page();
		
		//Associate message to control.
		this.addCommandOutputListener(this.message);
	}
	/**
	 * {@link Page} message control class constructor.
	 * @param parent {@link IControlPageListener} the parent
	 * @param page {@link Page} the page
	 */
	public PageControl(IControlPageListener parent, Page page) 
	{
		super(parent, page);
		
		//Initialize message.
		this.message = new Page();
		
		//Associate message to control.
		this.addCommandOutputListener(this.message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.AbstractHtmlControl#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Change(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		//Call super method
		super.Change(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.AbstractHtmlControl#OnControl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Initialize(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {

		//Call super method
		super.Initialize(sender, e);
		
		if(e.getMessage() != null)
		{			
			try
			{
				Page page = Page.class.cast(e.getMessage());
				
				//Check Element message.
				if(page.getElement() !=  null)
				{
					//Initialize Element operator message control.
					this.elementControl = new ElementControl(this, page.getElement());
					
					//Associate child control to parent.
					this.elementControl.addControlElementListener(this);
					this.addCommandOutputListener(this.elementControl);
					
					//Store control.
					this.childControls.add(this.elementControl);
				}
				if(page.getAnchor() != null)
				{
					//Initialize Anchor message control.
					this.anchorControl = new AnchorControl(this, page.getAnchor());
					
					//Associate child control to parent.
					this.anchorControl.addControlAnchorListener(this);
					this.addCommandOutputListener(this.anchorControl);
					
					//Store control.
					this.childControls.add(this.anchorControl);
				}
				if(page.getDivision() != null)
				{
					//Initialize Division message control.
					this.divisionControl = new DivisionControl(this, page.getDivision());
					
					//Associate child control to parent.
					this.divisionControl.addControlDivisionListener(this);
					this.addCommandOutputListener(this.divisionControl);
					
					//Store control.
					this.childControls.add(this.divisionControl);
				}
				if(page.getTable() != null)
				{
					//Initialize Table message control.
					this.tableControl = new TableControl(this, page.getTable());
					
					//Associate child control to parent.
					this.tableControl.addControlTableListener(this);
					this.addCommandOutputListener(this.tableControl);
					
					//Store control
					this.childControls.add(this.tableControl);
				}
				if(page.getTableRow() != null)
				{
					//Initialize TableRow message control.
					this.tableRowControl = new TableRowControl(this, page.getTableRow());
					
					//Associate child control to parent.
					this.tableRowControl.addControlTableRowListener(this);
					this.addCommandOutputListener(this.tableRowControl);
					
					//Store control
					this.childControls.add(this.tableRowControl);
				}
				if(page.getTableCell() != null)
				{
					//Initialize TableCell message control.
					this.tableCellControl = new TableCellControl(this, page.getTableCell());
					
					//Associate child control to parent.
					this.tableCellControl.addControlTableCellListener(this);
					this.addCommandOutputListener(this.tableCellControl);
					
					//Store control
					this.childControls.add(this.tableCellControl);
				}
				if(page.getPage() != null)
				{
					//Initialize Page message control.
					this.pageControl = new PageControl(this, page.getPage());
					
					//Associate child control to it's parent.
					this.pageControl.addControlPageListener(this.pageControl);					
					this.addCommandOutputListener(this.pageControl);
					
					//Store control
					this.childControls.add(this.pageControl);
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"PageControl.OnControlInit: Page operator message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.AbstractHtmlControl#OnControl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Load(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		//Call super method
		super.Load(sender, e);
		
		if(e.getMessage() != null)
		{
			try
			{
				Page page = Page.class.cast(e.getMessage());
				
				if(this.hasChildControls())
				{
					while(this.hasNext())
					{
						IControlImpl control = this.next();
						
						if(control.equals(this.elementControl) ?
								page.getElement() != null ? 
										control.getUuid().equals(page.getElement().getUuid()) 
										: false : false) {
							
							//Set HTML Element operator's message.
							this.elementControl.controlMessage(page.getElement());
						}
						if(control.equals(this.anchorControl) ? 
								page.getAnchor() != null ? 
										control.getUuid().equals(page.getAnchor().getUuid()) 
										: false : false) {
							
							//Set HTML Anchor operator's message.
							this.anchorControl.controlMessage(page.getAnchor());
						}
						if(control.equals(this.divisionControl) ? 
								page.getDivision() != null ?
										control.getUuid().equals(page.getDivision().getUuid()) 
										: false : false) {
							
							//Set HTML Division operator's message.
							this.divisionControl.controlMessage(page.getDivision());
						}
						if(control.equals(this.tableControl) ? 
								page.getTable() != null ?
										control.getUuid().equals(page.getTable().getUuid()) 
										: false : false) {
							
							//Set HTML Table operator's message.
							this.tableControl.controlMessage(page.getTable());
						}
						if(control.equals(this.tableRowControl) ?
								page.getTableRow() != null ? 
										control.getUuid().equals(page.getTableRow().getUuid()) 
										: false : false) {
							
							//Set HTML TableRow operator's message.
							this.tableRowControl.controlMessage(page.getTableRow());
						}
						if(control.equals(this.tableCellControl) ?
								page.getTableCell() != null ?
										control.getUuid().equals(page.getTableCell().getUuid()) 
										: false : false) {
							
							//Set HTML TableCell operator's message.
							this.tableCellControl.controlMessage(page.getTableCell());
						}
						if(control.equals(this.pageControl) ?
								page.getPage() != null ?
										control.getUuid().equals(page.getPage().getUuid()) 
										: false : false) {
							
							//Set HTML Page operator's message.
							this.pageControl.controlMessage(page.getPage());
						}
					}
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"PageControl.OnControl_Loaded: Page message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.AbstractHtmlControl#OnControl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {

		//Call super method
		super.Read(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.AbstractHtmlControl#OnControl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Render(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {

		//Call super method
		super.Render(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.AbstractHtmlControl#OnControl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {

		//Call super method
		super.Write(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlPageAnchor_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAnchor_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlPageAnchor_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAnchor_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlPageAnchor_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAnchor_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.anchorControl))
		{
			if(e.getMessage() instanceof Anchor)
			{
				this.put(HtmlData.ANCHOR, e.getMessage());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"PageControl.OnControlAnchor_Loaded: Anchor message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlPageAnchor_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAnchor_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlPageAnchor_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAnchor_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlPageAnchor_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAnchor_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlDivision_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDivision_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlDivision_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDivision_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlDivision_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDivision_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		//Check event comes from Element control child.
		if(e.getSource().equals(this.divisionControl))
		{
			if(e.getMessage() instanceof Division)
			{
				this.put(HtmlData.DIVISION, e.getMessage());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"PageControl.OnControlDivision_Loaded: Division message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlDivision_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDivision_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlDivision_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDivision_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlDivision_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDivision_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlPageElement_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlElement_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlPageElement_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlElement_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlPageElement_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlElement_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		//Check event comes from Element control child.
		if(e.getSource().equals(this.elementControl))
		{
			if(e.getMessage() instanceof Element)
			{
				this.put(HtmlData.ELEMENT, e.getMessage());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"PageControl.OnControlElement_Loaded: Element message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlPageElement_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlElement_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlPageElement_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlElement_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlPageElement_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlElement_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlPage_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPage_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlPage_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPage_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlPage_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPage_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		//Check event comes from Page control child.
		if(e.getSource().equals(this.pageControl))
		{
			if(e.getMessage() instanceof Page)
			{
				this.put(HtmlData.PAGE, e.getMessage());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"PageControl.OnControlPage_Loaded: Page message expected");
			}
		}
		//Otherwise, if event source is current object, set control ready to be iterated again.
		else if(e.getSource().equals(this))
		{
			this.reset();

			//Command CONTROL_PAGE input event
			CliCommandInputEvent(new CliEventArgs(this, Command.CONTROL_PAGE, e.getMessage()));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlPage_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPage_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlPage_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPage_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlPage_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPage_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlTable_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTable_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlTable_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTable_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlTable_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTable_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.tableControl))
		{
			if(e.getMessage() instanceof Table)
			{
				this.put(HtmlData.TABLE, e.getMessage());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"PageControl.OnControlTable_Loaded: Table message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlTable_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTable_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlTable_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTable_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlTable_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTable_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlTableCell_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableCell_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlTableCell_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableCell_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlTableCell_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableCell_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.tableCellControl))
		{
			if(e.getMessage() instanceof TableCell)
			{
				this.put(HtmlData.TABLE_CELL, e.getMessage());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"PageControl.OnControlTableCell_Loaded: TableCell message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlTableCell_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableCell_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlTableCell_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableCell_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlTableCell_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableCell_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlTableRow_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableRow_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlTableRow_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableRow_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlTableRow_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableRow_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.tableRowControl))
		{
			if(e.getMessage() instanceof TableRow)
			{
				this.put(HtmlData.TABLE_ROW, e.getMessage());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"PageControl.OnControlTableRow_Loaded: TableRow message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlTableRow_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableRow_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlTableRow_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableRow_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControlTableRow_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTableRow_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.AbstractHtmlControl#put(org.httprobot.core.common.Enums.HtmlData, java.lang.Object)
	 */
	@Override
	public Object put(HtmlData key, Object value)
	{
		switch (key)
		{
		case ANCHOR:
			this.message.setAnchor(Anchor.class.cast(value));
			break;
			
		case DIVISION:
			this.message.setDivision(Division.class.cast(value));
			break;
			
		case ELEMENT:
			this.message.setElement(Element.class.cast(value));
			break;
			
		case PAGE:
			this.message.setPage(Page.class.cast(value));
			break;
			
		case TABLE:
			this.message.setTable(Table.class.cast(value));
			break;
			
		case TABLE_CELL:
			this.message.setTableCell(TableCell.class.cast(value));
			break;
			
		case TABLE_ROW:
			this.message.setTableRow(TableRow.class.cast(value));
			break;
			
		default:
			break;
		}
		return super.put(key, value);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.AbstractHtmlControl#remove(java.lang.Object)
	 */
	@Override
	public Object remove(Object key) 
	{
		return super.remove(key);
	}
	
}