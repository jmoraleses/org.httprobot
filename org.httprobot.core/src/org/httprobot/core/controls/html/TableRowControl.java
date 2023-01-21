/**
 * 
 */
package org.httprobot.core.controls.html;

import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.placeholders.operators.html.TableRow;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.HtmlData;
import org.httprobot.core.controls.html.interfaces.IControlTableRowListener;
import org.httprobot.core.controls.interfaces.impl.IHtmlControlImpl;

/**
 * @author joan
 *
 */
public class TableRowControl 
		extends AbstractHtmlControl<TableRow, IControlTableRowListener>
		implements IHtmlControlImpl {

	/**
	 * -6336247571135243327L
	 */
	private static final long serialVersionUID = -6336247571135243327L;

	/**
	 * 
	 */
	public TableRowControl() {
		
		super();
	}
	/**
	 * @param parent
	 * @param message
	 */
	public TableRowControl(IControlTableRowListener parent, TableRow message) {
		
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
			this.message = new TableRow();
			
			//Associate message to control.
			this.addCommandOutputListener(this.message);
			
			try
			{
				TableRow.class.cast(e.getMessage());
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"TableRowControl.OnControlInit: TableRow message expected");
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
				TableRow.class.cast(e.getMessage());
				
				//Call super method to set child control messages.
				super.Load(sender, e);
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"TableRowControl.OnControl_Loaded: TableRow message expected");
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

		//Set control ready to be iterated.
		if(e.getSource().equals(this))
		{
			this.reset();

			//Fire input command event.
			CliCommandInputEvent(new CliEventArgs(this, Command.CONTROL_TABLE_ROW, e.getMessage()));
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
