package org.httprobot.core.controls.datatypes;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.datatypes.Field;
import org.httprobot.common.datatypes.FieldRoot;
import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.DataTypeData;
import org.httprobot.core.controls.DataTypeControl;
import org.httprobot.core.controls.datatypes.interfaces.IControlFieldRootListener;
import org.httprobot.core.controls.interfaces.impl.IControlImpl;
import org.httprobot.core.controls.interfaces.impl.IDataTypeControlImpl;


/**
 * 
 * {@link FieldRoot} message control class. Inherits {@link DataTypeControl}.
 * <br>
 * @author Joan
 */
@XmlRootElement
public class FieldRootControl 
		extends DataTypeControl<FieldRoot, IControlFieldRootListener>
		implements IDataTypeControlImpl {
	
	/**
	 * -723575690933280498L
	 */
	private static final long serialVersionUID = -723575690933280498L;
	
	protected List<FieldControl> fieldControls;
	/**
	 * {@link FieldRoot} message control default class constructor.
	 */
	public FieldRootControl()
	{
		super();
		
		//Initialize message.
		this.message = new FieldRoot();
		
		//Associate message to control.
		this.addCommandOutputListener(this.message);		
	}
	/**
	 * {@link FieldRoot} message control class constructor.
	 * @param parent {@link IControlListener} the parent control
	 * @param fields {@link FieldRoot} the fields to set
	 */
	public FieldRootControl(IControlFieldRootListener parent, FieldRoot fields)
	{
		super(parent, fields);
		
		//Initialize message.
		this.message = new FieldRoot();
		
		//Associate message to control.
		this.addCommandOutputListener(this.message);		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Change(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Initialize(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		if(e.getMessage() != null)
		{
			
			try 
			{
				//Initialize using data.
				this.fieldControls = new ArrayList<FieldControl>();
				
				//Cast source's message.
				FieldRoot fields = FieldRoot.class.cast(e.getMessage());
				
				for(Field field : fields.getField())
				{
					//This instance listens for it's input commands.
					FieldControl fieldControl = new FieldControl(this, field);
					
					//Associate child controls to parent.
					fieldControl.addControlFieldListener(this);
					this.addCommandOutputListener(fieldControl);					
					
					//Store control
					this.fieldControls.add(fieldControl);
					this.childControls.add(fieldControl);
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"FieldsControl.OnControl_Init: Fields message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Load(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		if(e.getMessage() != null)
		{
			try
			{
				//Cast loaded message
				FieldRoot fields = FieldRoot.class.cast(e.getMessage());
				
				if(this.hasChildControls())
				{
					//Iterate through child controls.
					while(this.hasNext())
					{
						IControlImpl control = this.next();
						
						//Check if control it's a child of current control.
						if(this.fieldControls.contains(control) ? 
								!fields.getField().isEmpty() : false) {
							
							//Cast Field control.
							FieldControl fieldControl = FieldControl.class.cast(control);
							
							for(Field field : fields.getField())
							{
								if(fieldControl.getUuid().equals(field.getUuid()))
								{
									//Set message to correspondent control.
									fieldControl.controlMessage(field);
									break;
								}
							}
						}
					}
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
					"FieldsControl.OnControl_Loaded: FieldRoot message expected");
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
	 * @see org.httprobot.core.controls.DataTypeControl#OnControl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Render(Object sender, ControlEventArgs e) 
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
			throws NotImplementedException, InconsistenMessageException {
		
		if(fieldControls.contains(e.getSource()))
		{
			if(e.getMessage() instanceof Field)
			{
				this.put(DataTypeData.FIELD, e.getMessage());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"FieldsControl.OnControlField_Loaded: Field message expected.");
			}
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
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlFields_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRoot_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlFields_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRoot_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlFields_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRoot_Loaded(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		//Set control ready to be iterated again.
		if(e.getSource().equals(this))
		{
			this.reset();
			
			//Call input command event.
			CliCommandInputEvent(new CliEventArgs(this, Command.CONTROL_FIELDS, e.getMessage()));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlFields_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRoot_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlFields_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRoot_Rendered(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlFields_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRoot_Write(Object sender, ControlEventArgs e)
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
			case FIELD:
				this.message.getField().add(Field.class.cast(value));
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