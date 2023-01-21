package org.httprobot.core.controls.contents;

import org.httprobot.common.contents.FieldRef;
import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.definitions.Enums.DataType;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.ContentData;
import org.httprobot.core.controls.ContentControl;
import org.httprobot.core.controls.contents.interfaces.IControlFieldRefListener;
import org.httprobot.core.controls.interfaces.impl.IContentControlImpl;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * {@link FieldRef} message control class. Inherits {@link ContentControl}.
 * <br>
 * @author Joan
 * 
 */
@XmlRootElement
public class FieldRefControl 
		extends ContentControl<FieldRef, IControlFieldRefListener>
		implements IContentControlImpl {

	/**
	 * -3706999058687003891L
	 */
	private static final long serialVersionUID = -3706999058687003891L;
	
	/**
	 * {@link FieldRef} message control default class constructor.
	 */
	public FieldRefControl()
	{
		super();

		//Initialize members
		this.message = new FieldRef();

		//Associate message to controls.
		addCommandOutputListener(this.message);
	}
	/**
	 * {@link FieldRef} message control class constructor.
	 * @param parent {@link IControlFieldRefListener} the parent
	 * @param fieldRef {@link FieldRef} the message
	 */
	public FieldRefControl(IControlFieldRefListener parent, FieldRef fieldRef) 
	{
		super(parent, fieldRef);
		
		//Initialize members
		this.message = new FieldRef();
		
		//Associate message to controls.
		addCommandOutputListener(this.message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Initialize(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getMessage() != null)
		{		
			try
			{
				FieldRef fieldRef = FieldRef.class.cast(e.getMessage());
				
				//Post initializing checking.
				if(fieldRef.getName() == null)
				{
					CliTools.ThrowInconsistentMessageException(this, 
							"FieldRefControl.OnControlInit: FieldRef message invalid");
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"FieldRefControl.OnControlInit: FieldRef message expected");
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
				FieldRef fieldRef = FieldRef.class.cast(e.getMessage());
				
				if(fieldRef.getName() != null)
				{
					//Set non-controlled data
					this.put(ContentData.COMPRESSED, fieldRef.getCompressed());
					this.put(ContentData.COMPRESS_THRESHOLD, fieldRef.getCompressThreshold());
					this.put(ContentData.DATA_TYPE, fieldRef.getDataType());
					this.put(ContentData.INDEXED, fieldRef.getIndexed());
					this.put(ContentData.MULTI_VALUED, fieldRef.getMultiValued());
					this.put(ContentData.NAME, fieldRef.getName());
					this.put(ContentData.OMIT_NORM, fieldRef.getOmitNorm());
					this.put(ContentData.OMIT_POSITIONS, fieldRef.getOmitPositions());
					this.put(ContentData.OMIT_TERM_FREQ_AND_POSITIONS, fieldRef.getOmitTermFreqAndPositions());
					this.put(ContentData.STORED, fieldRef.getStored());
					this.put(ContentData.TERM_VECTORS, fieldRef.getTermVectors());
					this.put(ContentData.TYPE, fieldRef.getType());
				}
				else
				{
					CliTools.ThrowInconsistentMessageException(this, 
							"FieldRefControl.OnControlLoaded: FieldRef message invalid");
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"FieldRefControl.OnControlLoaded: FieldRef message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Change(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Render(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		

		CliCommandInputEvent(new CliEventArgs(this, Command.CONTROL_FIELD_REF, e.getMessage()));
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Read(Object sender, ControlEventArgs e) 
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
		
		if(e.getSource().equals(this))
		{
			this.reset();
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
	 * @see org.httprobot.core.controls.ContentControl#put(org.httprobot.core.common.Enums.SolrAttribute, java.lang.Object)
	 */
	@Override
	public Object put(ContentData key, Object value) {
		
		switch (key) 
		{
			case COMPRESSED:
				//Update message data.
				this.message.setCompressed(Boolean.class.cast(value));
				
				//Update control's data.
				return super.put(key, value);
				
			case COMPRESS_THRESHOLD:
				//Update message data.
				this.message.setCompressThreshold(Integer.class.cast(value));
				
				//Update control's data.
				return super.put(key, value);
				
			case DATA_TYPE:
				//Update message data.
				this.message.setDataType(DataType.class.cast(value));
				
				//Update control's data.
				return super.put(key, value);
				
			case INDEXED:
				//Update message data.
				this.message.setIndexed(Boolean.class.cast(value));
				
				//Update control's data.
				return super.put(key, value);
				
			case MULTI_VALUED:
				//Update message data.
				this.message.setMultiValued(Boolean.class.cast(value));
				
				//Update control's data.
				return super.put(key, value);
				
			case NAME:
				//Update message data.
				this.message.setName(String.class.cast(value));
				
				//Update control's data.
				return super.put(key, value);
				
			case OMIT_NORM:
				//Update message data.
				this.message.setOmitNorm(Boolean.class.cast(value));
				
				//Update control's data.
				return super.put(key, value);
				
			case OMIT_POSITIONS:
				//Update message data.
				this.message.setOmitPositions(Boolean.class.cast(value));
				
				//Update control's data.
				return super.put(key, value);
				
			case OMIT_TERM_FREQ_AND_POSITIONS:
				//Update message data.
				this.message.setOmitTermFreqAndPositions(Boolean.class.cast(value));
				
				//Update control's data.
				return super.put(key, value);
				
			case STORED:
				//Update message data.
				this.message.setStored(Boolean.class.cast(value));
				
				//Update control's data.
				return super.put(key, value);
				
			case TERM_VECTORS:
				//Update message data.
				this.message.setTermVectors(Boolean.class.cast(value));
				
				//Update control's data.
				return super.put(key, value);
				
			case TYPE:
				//Update message data.
				this.message.setType(String.class.cast(value));
				
				//Update control's data.
				return super.put(key, value);
	
			default:
				return super.put(key, value);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#remove(java.lang.Object)
	 */
	@Override
	public Object remove(Object key) {
		// TODO Auto-generated method stub
		return super.remove(key);
	}
}