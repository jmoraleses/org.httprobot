/**
 * 
 */
package org.httprobot.core.controls.html;

import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.operators.html.TableCell;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.HtmlData;
import org.httprobot.core.controls.html.interfaces.IControlTableCellListener;
import org.httprobot.core.controls.interfaces.impl.IHtmlControlImpl;

/**
 * {@link TableCell} message control class. Inherits {@link AbstractHtmlControl}.
 * <br>
 * @author joan
 *
 */
public class TableCellControl 
		extends AbstractHtmlControl<TableCell, IControlTableCellListener> 
		implements IHtmlControlImpl {

	/**
	 * -5619864897174342588L
	 */
	private static final long serialVersionUID = -5619864897174342588L;

	/**
	 * 
	 */
	public TableCellControl() {
		super();
	}
	/**
	 * @param parent
	 * @param message
	 */
	public TableCellControl(IControlTableCellListener parent, TableCell message) {
		super(parent, message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.AbstractHtmlControl#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Change(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		super.Change(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.AbstractHtmlControl#OnControl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Initialize(Object sender, ControlEventArgs e)
			throws InconsistenMessageException, NotImplementedException {
		
		super.Initialize(sender, e);
		
		if(e.getMessage() != null)
		{
			this.message = new TableCell();
			
			//Associate message to control.
			this.addCommandOutputListener(this.message);
			
			try
			{
				TableCell.class.cast(e.getMessage());
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"TableCellControl.OnControl_Init: TableCell message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.AbstractHtmlControl#OnControl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Load(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		
		if(e.getMessage() != null)
		{
			try
			{
				TableCell.class.cast(e.getMessage());
				
				//Call super method to set child control messages.
				super.Load(sender, e);
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this,
						"TableCellControl.OnControl_Loaded: TableCell message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.AbstractHtmlControl#OnControl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		super.Read(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.AbstractHtmlControl#OnControl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Render(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		super.Render(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.AbstractHtmlControl#OnControl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		super.Write(sender, e);
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

		//Set control ready to be iterated.
		if(e.getSource().equals(this))
		{
			this.reset();
			
			CliCommandInputEvent(new CliEventArgs(this, Command.CONTROL_TABLE_CELL, e.getMessage()));
			
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
	 * @see org.httprobot.core.controls.html.AbstractHtmlControl#put(org.httprobot.core.common.Enums.HtmlData, java.lang.Object)
	 */
	@Override
	public Object put(HtmlData key, Object value) {
		// TODO Auto-generated method stub
		return super.put(key, value);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.html.AbstractHtmlControl#remove(java.lang.Object)
	 */
	@Override
	public Object remove(Object key) {
		// TODO Auto-generated method stub
		return super.remove(key);
	}
	
}