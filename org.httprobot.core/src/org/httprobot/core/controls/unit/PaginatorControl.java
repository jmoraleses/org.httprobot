/**
 * 
 */
package org.httprobot.core.controls.unit;

import org.httprobot.common.RML;
import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.tools.CliTools;
import org.httprobot.common.unit.Paginator;
import org.httprobot.core.common.Enums.UnitData;
import org.httprobot.core.controls.UnitControl;
import org.httprobot.core.controls.unit.interfaces.IControlPaginatorListener;

/**
 * {@link Paginator} message control class. Inherits {@link DataTypeControl}.
 * <br>
 * @author joan
 *
 */
public class PaginatorControl
		extends UnitControl<Paginator, IControlPaginatorListener> {

	/**
	 * 27335840895414285L
	 */
	private static final long serialVersionUID = 27335840895414285L;
	/**
	 * 
	 */
	public PaginatorControl()
	{
		super();
		
	}
	/**
	 * @param parent
	 * @param paginator
	 */
	public PaginatorControl(IControlPaginatorListener parent, Paginator paginator) 
	{
		super(parent, paginator);
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.UnitControl#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Change(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		RML newMessage = e.getMessage();
		
		//If received object is Paginator.
		if(newMessage instanceof Paginator)
		{
			Paginator paginator = Paginator.class.cast(newMessage);
			
			//Set non-controlled data
			this.put(UnitData.NEXT_PAGE_ANCHOR_HREF, paginator.getAnchorHrefAttributeValue());
			this.put(UnitData.NEXT_PAGE_ANCHOR_VALUE, paginator.getAnchorValue());
			this.put(UnitData.PAGINATOR_INCREMENT, paginator.getPaginatorIncrement());
			this.put(UnitData.PAGINATOR_URL_PATTERN, paginator.getPaginatorPattern());
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.UnitControl#OnControl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Initialize(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getMessage() != null)
		{
			//Initialize using data
			this.message = new Paginator();
			
			//Associate message to control
			this.addCommandOutputListener(this.message);
			
			//Set inherited data
			this.setUuid(e.getMessage().getUuid());
			this.setInherited(e.getMessage().getInherited());
			this.setRuntimeOptions(e.getMessage().getRuntimeOptions());
			
			this.message.setUuid(getUuid());
			this.message.setInherited(getInherited());
			this.message.setRuntimeOptions(getRuntimeOptions());
			
			try
			{
				Paginator.class.cast(e.getMessage());
				//No child controls.
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"PaginatorControl.OnControl_Init: Paginator message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.UnitControl#OnControl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Load(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getMessage() != null)
		{
			try
			{
				//Cast loaded message.
				Paginator paginator = Paginator.class.cast(e.getMessage());
				
				//Set non-controlled data
				this.put(UnitData.NEXT_PAGE_ANCHOR_HREF, paginator.getAnchorHrefAttributeValue());
				this.put(UnitData.NEXT_PAGE_ANCHOR_VALUE, paginator.getAnchorValue());
				this.put(UnitData.PAGINATOR_INCREMENT, paginator.getPaginatorIncrement());
				this.put(UnitData.PAGINATOR_URL_PATTERN, paginator.getPaginatorPattern());
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this,
						"PaginatorControl.OnControl_Init: Paginator message expected.");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.UnitControl#OnControl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.UnitControl#OnControl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Render(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.UnitControl#OnControl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.UnitControl#OnControlPaginator_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPaginator_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.UnitControl#OnControlPaginator_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPaginator_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.UnitControl#OnControlPaginator_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPaginator_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		//If event sender is current object set control ready.
		if(e.getSource().equals(this))
		{
			this.reset();

			//Fire input command event.
			CliCommandInputEvent(new CliEventArgs(this, Command.CONTROL_PAGINATOR, e.getMessage()));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.UnitControl#OnControlPaginator_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPaginator_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.UnitControl#OnControlPaginator_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPaginator_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.UnitControl#OnControlPaginator_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlPaginator_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.UnitControl#put(org.httprobot.core.common.Enums.UnitAttribute, java.lang.Object)
	 */
	@Override
	public Object put(UnitData key, Object value) 
	{
		switch (key) 
		{
		case PAGINATOR_INCREMENT:
			this.message.setPaginatorIncrement(Integer.class.cast(value));
			break;

		case NEXT_PAGE_ANCHOR_HREF:
			this.message.setAnchorHrefAttributeValue(String.class.cast(value));
			break;

		case NEXT_PAGE_ANCHOR_VALUE:
			this.message.setAnchorValue(String.class.cast(value));
			break;

		case PAGINATOR_URL_PATTERN:
			this.message.setPaginatorPattern(String.class.cast(value));
			break;
			
		default:
			break;
		}
		return super.put(key, value);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.UnitControl#remove(java.lang.Object)
	 */
	@Override
	public Object remove(Object key) 
	{
		return super.remove(key);
	}
}