package org.httprobot.core.controls.datatypes;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.datatypes.Field;
import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.placeholders.HtmlUnit;
import org.httprobot.common.placeholders.HttpAddress;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.DataTypeData;
import org.httprobot.core.common.Enums.PlaceholderData;
import org.httprobot.core.controls.DataTypeControl;
import org.httprobot.core.controls.datatypes.interfaces.IControlFieldListener;
import org.httprobot.core.controls.interfaces.impl.IControlImpl;
import org.httprobot.core.controls.interfaces.impl.IDataTypeControlImpl;
import org.httprobot.core.controls.interfaces.listeners.IPlaceholderControlListener;
import org.httprobot.core.controls.placeholders.HtmlUnitControl;
import org.httprobot.core.controls.placeholders.HttpAddressControl;

/**
 * {@link Field} message control Class. Inherits {@link DataTypeControl}.
 * <br>
 * @author Joan
 * 
 */
@XmlRootElement
public class FieldControl 
		extends DataTypeControl<Field, IControlFieldListener>
		implements IPlaceholderControlListener, IDataTypeControlImpl {
	
	/**
	 * -7062776107775980742L
	 */
	private static final long serialVersionUID = -7062776107775980742L;
	
	/**
	 * The {@link HttpAddress} message control.<b>{@link HttpAddressControl}</b>
	 */	
	protected HttpAddressControl httpAddressControl;
	/**
	 * The {@link HtmlUnit} message control.<b>{@link HtmlUnitControl}</b>
	 */
	protected HtmlUnitControl htmlUnitControl;
	
	/**
	 * {@link Field} message control default class constructor.
	 */
	public FieldControl()
	{
		super();
		
		//Initialize message.
		this.message = new Field();
		
		//Associate message to control
		this.addCommandOutputListener(this.message);
	}	
	/** 
	 * {@link Field} message control class constructor.
	 * @param parent {@link IControlListener} parent object
	 * @param field {@link Field} the field message to be treated
	 */
	public FieldControl(IControlFieldListener parent, Field field)
	{
		super(parent, field);
		
		//Initialize message.
		this.message = new Field();
		
		//Associate message to control
		this.addCommandOutputListener(this.message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Load(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getMessage() != null)
		{
			try
			{
				//Cast message loaded.
				Field field = Field.class.cast(e.getMessage());
				boolean error = false;
				
				if(this.hasChildControls())
				{
					//Iterate through child controls.
					while(this.hasNext())
					{
						IControlImpl control = this.next();
						
						//HttpAddress place holder message.
						if(control.equals(this.httpAddressControl) ?
								field.getHttpAddress() != null ?
										control.getUuid().equals(field.getHttpAddress().getUuid()) 
										: false : false) {
							
							//Set message to control.
							this.httpAddressControl.controlMessage(field.getHttpAddress());
						}
						
						//HtmlUnit place holder message.
						else if(control.equals(this.htmlUnitControl) ?
								field.getHtmlUnit() != null ?
										control.getUuid().equals(field.getHtmlUnit().getUuid())
										: false : false) {
							
							//Set message to control.
							this.htmlUnitControl.controlMessage(field.getHtmlUnit());
						}
						else
						{
							error = true;
						}
					}
				}
				//Check if an error has occurred.
				if(error)
				{
					CliTools.ThrowInconsistentMessageException(this, 
							"FieldControl.OnControlLoaded: Incomplete message.");
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"FieldControl.OnControlLoaded: Field message expected.");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Change(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Render(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Initialize(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getMessage() != null)
		{
			
			try
			{
				Field field = Field.class.cast(e.getMessage());
				
				if(field.getHttpAddress() != null)
				{
					//If HTTP request message not null instantiate action message control.
					this.httpAddressControl = new HttpAddressControl(this, field.getHttpAddress());
					
					//Associate child control to parent.
					this.httpAddressControl.addControlHttpAddressListener(this);
					this.addCommandOutputListener(this.httpAddressControl);
					
					//Store control.
					this.childControls.add(this.httpAddressControl);
				}

				if(field.getHtmlUnit() != null)
				{
					//If HTML body message not null instantiate action message control.
					this.htmlUnitControl = new HtmlUnitControl(this, field.getHtmlUnit());
					
					//Associate child control to parent.
					this.htmlUnitControl.addControlHtmlUnitListener(this);
					this.addCommandOutputListener(this.htmlUnitControl);
					
					//Store control.
					this.childControls.add(this.httpAddressControl);
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"FieldControl.OnControl_Init: Field message expected.");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlField_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlField_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlField_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlField_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlField_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlField_Loaded(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		//Set control ready to be iterated again.
		if(e.getSource().equals(this))
		{
			this.reset();
			
			CliCommandInputEvent(new CliEventArgs(this, Command.CONTROL_FIELD, e.getMessage()));
		}
	}
		/* (non-Javadoc)
		 * @see org.httprobot.core.controls.DataTypeControl#OnControlField_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
		 */
		@Override
	public void OnControlField_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlField_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlField_Rendered(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlField_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlField_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHttpAddressListener#OnControlHttpAddress_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHttpAddressListener#OnControlHttpAddress_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHttpAddressListener#OnControlHttpAddress_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		if(e.getSource().equals(this.httpAddressControl))
		{
			if(e.getMessage() instanceof HttpAddress)
			{
				this.put(DataTypeData.HTTP_ADDRESS, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"FieldControl.OnControlHttpAddress_Loaded: HttpAddress message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHttpAddressListener#OnControlHttpAddress_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHttpAddressListener#OnControlHttpAddress_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHttpAddressListener#OnControlHttpAddress_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHtmlUnitListener#OnControlHtmlUnit_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHtmlUnitListener#OnControlHtmlUnit_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHtmlUnitListener#OnControlHtmlUnit_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.htmlUnitControl))
		{
			if(e.getMessage() instanceof HtmlUnit)
			{
				this.put(DataTypeData.HTML_UNIT, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"FieldControl.OnControlHtmlUnit_Loaded: HtmlUnit message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHtmlUnitListener#OnControlHtmlUnit_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHtmlUnitListener#OnControlHtmlUnit_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.interfaces.IControlHtmlUnitListener#OnControlHtmlUnit_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#put(org.httprobot.core.common.Enums.DataTypeData, java.lang.Object)
	 */
	@Override
	public Object put(DataTypeData key, Object value) 
	{
		switch (key) 
		{
		case HTML_UNIT:
			if(value.equals(this.htmlUnitControl))
			{
				this.message.setHtmlUnit(this.htmlUnitControl.getMessage());
				
				this.htmlUnitControl.put(PlaceholderData.HTML_UNIT, this.htmlUnitControl.getMessage());
			}
			
			break;
			
		case HTTP_ADDRESS:
			if(value.equals(this.httpAddressControl))
			{
				this.message.setHttpAddress(HttpAddress.class.cast(value));
				
				this.httpAddressControl.put(PlaceholderData.HTTP_ADDRESS, this.httpAddressControl.getMessage());
			}
			
			break;
			
		default:
			break;
		}
		return super.put(key, value);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#remove(java.lang.Object)
	 */
	@Override
	public Object remove(Object key)
	{
		return super.remove(key);
	}
}