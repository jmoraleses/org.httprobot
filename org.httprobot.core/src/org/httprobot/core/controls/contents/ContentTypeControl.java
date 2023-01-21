/**
 * 
 */
package org.httprobot.core.controls.contents;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.ContentType;
import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.common.contents.FieldRef;
import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.ContentData;
import org.httprobot.core.controls.ContentControl;
import org.httprobot.core.controls.contents.interfaces.IControlContentTypeListener;
import org.httprobot.core.controls.interfaces.impl.IContentControlImpl;
import org.httprobot.core.controls.interfaces.impl.IControlImpl;

/**
 * {@link ContentType} message control class. Inherits {@link ContentControl}.
 * <br>
 * @author joan
 *
 */
@XmlRootElement
public class ContentTypeControl
		extends ContentControl<ContentType, IControlContentTypeListener>
		implements IContentControlImpl {

	/**
	 * -1629237371241625202L
	 */
	private static final long serialVersionUID = -1629237371241625202L;
	
	/**
	 * The {@link FieldRef} message control list.
	 */
	protected List<FieldRefControl> fieldRefControls;
	/**
	 * The {@link ContentTypeRef} message control list.
	 */
	protected List<ContentTypeRefControl> contentTypeRefControls;
	
	/**
	 * {@link ContentType} message control default class constructor.
	 */
	public ContentTypeControl() 
	{
		super();

		//Initialize message.
		this.message = new ContentType();

		//Associate message to control.
		this.addCommandOutputListener(this.message);
	}
	/**
	 * {@link ContentType} message control default class constructor.
	 * @param parent {@link IControlContentTypeListener} the parent
	 * @param message {@link ContentType} the message
	 */
	public ContentTypeControl(IControlContentTypeListener parent, ContentType message) 
	{
		super(parent, message);
		
		//Initialize message.
		this.message = new ContentType();
		
		//Associate message to control.
		this.addCommandOutputListener(this.message);
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
			try
			{
				//Initialize controls lists.
				this.fieldRefControls = new ArrayList<FieldRefControl>();
				this.contentTypeRefControls = new ArrayList<ContentTypeRefControl>();
				
				//Cast message
				ContentType contentType = ContentType.class.cast(e.getMessage());
				
				//Check ContentTypeRef list
				for(ContentTypeRef contentTypeRef : contentType.getContentTypeRef())
				{
					//Instantiate ContentTypeRefControl for each ContentTypeRef message
					ContentTypeRefControl contentTypeRefControl = new ContentTypeRefControl(this, contentTypeRef);
					
					//Associate child control to parent
					contentTypeRefControl.addControlContentTypeRefListener(this);
					
					//Store control
					this.contentTypeRefControls.add(contentTypeRefControl);						
					this.childControls.add(contentTypeRefControl);
				}
				
				//Check FieldRef list
				for(FieldRef fieldRef : contentType.getFieldRef())
				{
					//Instantiate FieldRefControl for each FieldRef message
					FieldRefControl fieldRefControl = new FieldRefControl(this, fieldRef);
					
					//Associate child control to parent
					this.addCommandOutputListener(fieldRefControl);
					
					//Store control
					this.fieldRefControls.add(fieldRefControl);
					this.childControls.add(fieldRefControl);
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"ContentTypeControl.OnControl_Init: ContentType message expected.");
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
				ContentType contentType = ContentType.class.cast(e.getMessage());
				
				//Set non-controlled data
				if(contentType.getInheritedType() != null)
				{
					this.put(ContentData.INHERITED_TYPE, contentType.getInheritedType());	
				}
				
				//Iterate through child controls and set it's messages.
				if(this.hasChildControls())
				{
					while(this.hasNext())
					{
						IControlImpl control = this.next();
						
						//Check if FieldRef has been stored before.
						if(this.fieldRefControls.contains(control) ?
								!contentType.getFieldRef().isEmpty() : false) {
							
							//Get the FieldRef message control.
							FieldRefControl fieldRefControl = FieldRefControl.class.cast(control);
							
							for(FieldRef fieldRef : contentType.getFieldRef())
							{
								//Match by UUID.
								if(fieldRefControl.getUuid().equals(fieldRef.getUuid()))
								{
									//Set the message
									fieldRefControl.controlMessage(fieldRef);
									break;
								}
							}							
						}
						
						//Check if ContentTypeRef has been stored before.
						if(this.contentTypeRefControls.contains(control) ?
								!contentType.getContentTypeRef().isEmpty() : false) {
							
							//Cast control.
							ContentTypeRefControl contentTypeRefControl = ContentTypeRefControl.class.cast(control);
							
							for(ContentTypeRef contentTypeRef : contentType.getContentTypeRef())
							{
								//Match by UUID.
								if(contentTypeRefControl.getUuid().equals(contentTypeRef.getUuid()))
								{
									//Set the message
									contentTypeRefControl.controlMessage(contentTypeRef);
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
						"ContentTypeControl.OnControl_Loaded: ContentType message expected.");
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
	 * @see org.httprobot.core.controls.ContentControl#OnControlContentType_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentType_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlContentType_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentType_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlContentType_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentType_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		//Set control ready to be iterated again.
		if(e.getSource().equals(this))
		{
			this.reset();
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlContentType_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentType_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlContentType_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentType_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this))
		{
			//Fire input command event.
			CliCommandInputEvent(new CliEventArgs(this, Command.CONTROL_CONTENT_TYPE, e.getMessage()));	
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlContentType_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentType_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlContentTypeRef_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlContentTypeRef_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlContentTypeRef_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(this.contentTypeRefControls.contains(e.getSource()))
		{
			if(e.getMessage() instanceof ContentTypeRef)
			{
				this.put(ContentData.CONTENT_TYPE_REF, e.getMessage());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"ContentTypeControl.OnControlContentTypeRef_Loaded: ContentTypeRef message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlContentTypeRef_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlContentTypeRef_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlContentTypeRef_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Write(Object sender, ControlEventArgs e)
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
			throws NotImplementedException, InconsistenMessageException 
	{
		if(this.fieldRefControls.contains(e.getSource()))
		{
			if(e.getMessage() instanceof FieldRef)
			{
				this.put(ContentData.FIELD_REF, e.getMessage());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"ContentTypeControl.OnControlFieldRef_Loaded: FieldRef message expected");
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
	 * @see org.httprobot.core.controls.ContentControl#put(org.httprobot.core.common.Enums.ContentData, java.lang.Object)
	 */
	@Override
	public Object put(ContentData key, Object value)
	{
		switch (key) 
		{
			case INHERITED_TYPE:
				//Update message.
				this.message.setInheritedType(UUID.class.cast(value));
				break;
				
			case FIELD_REF:
				//Update message.
				this.message.getFieldRef().add(FieldRef.class.cast(value));
				break;
				
			case CONTENT_TYPE_REF:				
				//Update message.
				this.message.getContentTypeRef().add(ContentTypeRef.class.cast(value));
				break;
				
			default:
				break;
		}
		//Update data.
		return super.put(key, value);
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