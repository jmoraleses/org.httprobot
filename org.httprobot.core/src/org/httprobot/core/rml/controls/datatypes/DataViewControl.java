package org.httprobot.core.rml.controls.datatypes;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.datatypes.DataView;
import org.httprobot.common.rml.datatypes.FieldRef;
import org.httprobot.common.tools.CommandLineInterface;
import org.httprobot.core.rml.controls.RmlDataTypeControl;

/**
 * @author Joan
 * Data view control class. Inherits {@link RmlDataTypeControl}.
 */
@XmlRootElement
public class DataViewControl extends RmlDataTypeControl
{
	/**
	 * -5408324213939309086L
	 */
	private static final long serialVersionUID = -5408324213939309086L;
	private DataView dataView;
	private ArrayList<FieldRefControl> fieldRef_control_list;
	private ArrayList<FieldRef> fieldRef_list;
	private Integer fieldRef_count;
	/**
	 * Data view control default class constructor.
	 */
	public DataViewControl()
	{
		super();
	}
	/**
	 * DataViewControl class constructor.
	 * @param parent
	 * @param dv
	 */
	public DataViewControl(IRmlListener parent, DataView dataView) 
	{
		super(parent, dataView);
	}
	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnDataViewChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnDataViewChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnDataViewInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnDataViewInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnDataViewLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnDataViewLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnDataViewRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnDataViewRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnDataViewRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnDataViewRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnDataViewWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnDataViewWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/**
	 * @return {@link ArrayList}<FieldRefControl> the FieldRef's
	 */
	public ArrayList<FieldRefControl> getFieldRef_control_list() 
	{
		return fieldRef_control_list;
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			//Initialize using data
			this.dataView = new DataView();
			this.fieldRef_control_list = new ArrayList<FieldRefControl>();
			
			//Associate message to control
			this.addCommandOutputListener(this.dataView);
			
			try
			{
				//Set inherited data
				this.setUuid(dataView.getUuid());
				this.setInherited(dataView.getInherited());
				this.setRuntimeOptions(dataView.getRuntimeOptions());
				
				this.dataView.setUuid(getUuid());
				this.dataView.setInherited(getInherited());
				this.dataView.setRuntimeOptions(getRuntimeOptions());
				
				DataView dataView = DataView.class.cast(e.getMessage());
				
				if(dataView.getFieldRef() != null)
				{
					this.fieldRef_count = dataView.getFieldRef().size();

					for(FieldRef fieldRef : dataView.getFieldRef())
					{
						//Instantiate FieldRefControl for each FieldRef message
						FieldRefControl fieldRefControl = new FieldRefControl(this, fieldRef);
						
						//Associate control to parent control.
						this.addCommandOutputListener(fieldRefControl);
						fieldRefControl.addFieldRefListener(this);
						
						//Store control
						this.fieldRef_control_list.add(fieldRefControl);
					}
				}
				else
				{
					CommandLineInterface.ThrowInconsistentMessageException(this, "FieldRef RML message array expected");
				}
			}
			catch(ClassCastException ex)
			{
				
			}
		}
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		if(e.getMessage() != null)
		{
			try
			{
				DataView dataView = DataView.class.cast(e.getMessage());
				
				if(dataView.getFieldRef() != null)
				{
					for(FieldRefControl fieldRefControl : this.fieldRef_control_list)
					{
						for(FieldRef fieldRef : dataView.getFieldRef())
						{
							if(fieldRef.getUuid() == fieldRefControl.getUuid())
							{
								fieldRefControl.setMessage(fieldRef);
							}
						}	
					}
				}
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "DataViewControl.OnControlLoaded: DataView RML message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlDataTypeListener#OnRuleLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnRuleLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}

	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlDataTypeListener#OnRuleRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnRuleRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnFieldRefChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldRefChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnFieldRefInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldRefInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnFieldRefLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldRefLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			try
			{
				FieldRef fieldRef = FieldRef.class.cast(e.getMessage());
				this.fieldRef_list.add(fieldRef);
				
				if(this.fieldRef_control_list.size() == this.fieldRef_count)
				{
					this.dataView.setFieldRef(fieldRef_list);
					
					CliCommandInputEvent(new CliEventArgs(this, Command.DATA_VIEW_CONTROL, e.getMessage()));
				}				
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "DataViewControl.OnFieldRefLoaded: FieldRef RML message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnFieldRefRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldRefRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnFieldRefRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldRefRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnFieldRefWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldRefWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}	
}
