package org.httprobot.core.controls.contents;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.DataView;
import org.httprobot.common.contents.FieldRef;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.controls.ContentControl;
import org.httprobot.core.controls.contents.interfaces.IControlDataViewListener;
import org.httprobot.core.controls.interfaces.impl.IContentControlImpl;

/**
 * {@link DataView} message control class. Inherits {@link ContentControl}.
 * <br>
 * @author Joan
 * 
 */
@XmlRootElement
public class DataViewControl 
		extends ContentControl<DataView, IControlDataViewListener>
		implements IContentControlImpl {
	
	/**
	 * -5408324213939309086L
	 */
	private static final long serialVersionUID = -5408324213939309086L;
	
	ArrayList<FieldRefControl> fieldRefControls;
	ArrayList<FieldRef> fieldRef_list;
	Integer fieldRef_count;
	
	/**
	 * Data view control default class constructor.
	 */
	public DataViewControl()
	{
		super();
		
		//Initialize using data
		this.message = new DataView();
		
		//Associate message to control.
		this.addCommandOutputListener(this.message);
	}
	/**
	 * DataViewControl class constructor.
	 * @param parent
	 * @param dv
	 */
	public DataViewControl(IControlDataViewListener parent, DataView dataView) 
	{
		super(parent, dataView);
	}
	/**
	 * @return {@link ArrayList}<FieldRefControl> the FieldRef's
	 */
	public ArrayList<FieldRefControl> getFieldRef_control_list() 
	{
		return fieldRefControls;
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Change(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Initialize(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getMessage() != null)
		{
			this.fieldRef_list = new ArrayList<FieldRef>();
			this.fieldRefControls = new ArrayList<FieldRefControl>();
			
			try
			{
				DataView dataView = DataView.class.cast(e.getMessage());
				

				if(!dataView.getFieldRef().isEmpty())
				{
					this.fieldRef_count = dataView.getFieldRef().size();

					for(FieldRef fieldRef : dataView.getFieldRef())
					{
						//Instantiate FieldRefControl for each FieldRef message
						FieldRefControl fieldRefControl = new FieldRefControl(this, fieldRef);
						
						//Associate control to parent control.
						this.addCommandOutputListener(fieldRefControl);
						
						//Store control
						this.fieldRefControls.add(fieldRefControl);
					}
				}
				else
				{
					CliTools.ThrowInconsistentMessageException(this, 
							"DataViewControl.OnControl_Init: FieldRef message array expected");
				}
			}
			catch(ClassCastException ex)
			{
				
			}
		}
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Load(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getMessage() != null)
		{
			try
			{
				DataView dataView = DataView.class.cast(e.getMessage());
				
				if(dataView.getFieldRef() != null)
				{
					for(FieldRefControl fieldRefControl : this.fieldRefControls)
					{
						for(FieldRef fieldRef : dataView.getFieldRef())
						{
							if(fieldRef.getUuid() == fieldRefControl.getUuid())
							{
								fieldRefControl.controlMessage(fieldRef);
								break;
							}
						}	
					}
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"DataViewControl.OnControlLoaded: DataView message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Render(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlFieldRef_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlFieldRef_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlFieldRef_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getMessage() != null)
		{
			try
			{
				FieldRef fieldRef = FieldRef.class.cast(e.getMessage());
				this.fieldRef_list.add(fieldRef);
				
				if(this.fieldRefControls.size() == this.fieldRef_count)
				{
					this.message.setFieldRef(fieldRef_list);
				}				
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"DataViewControl.OnFieldRefLoaded: FieldRef RML message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlFieldRef_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlFieldRef_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlFieldRef_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlDataView_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataView_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlDataView_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataView_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlDataView_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataView_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlDataView_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataView_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlDataView_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataView_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
}
