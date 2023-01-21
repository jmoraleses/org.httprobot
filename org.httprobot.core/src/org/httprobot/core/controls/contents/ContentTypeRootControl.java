/**
 * 
 */
package org.httprobot.core.controls.contents;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.ContentType;
import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.common.contents.FieldRef;
import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.ContentData;
import org.httprobot.core.controls.ContentControl;
import org.httprobot.core.controls.contents.interfaces.IControlContentTypeRootListener;
import org.httprobot.core.controls.interfaces.impl.IContentControlImpl;
import org.httprobot.core.controls.interfaces.impl.IControlImpl;

/**
 * {@link ContentTypeRoot} message control class. Inherits {@link ContentControl}.
 * <br>
 * @author joan
 *
 */
@XmlRootElement
public class ContentTypeRootControl 
		extends ContentControl<ContentTypeRoot, IControlContentTypeRootListener>
		implements IContentControlImpl {

	/**
	 * 3455563568587488914L
	 */
	private static final long serialVersionUID = 3455563568587488914L;

	/**
	 * {@link ContentType} message controls.
	 */
	protected List<ContentTypeControl> contentTypeControls;
	/**
	 * {@link FieldRef} message controls.
	 */
	protected List<FieldRefControl> fieldRefControls;	
	/**
	 * {@link ContentTypeRef} message controls.
	 */
	protected List<ContentTypeRefControl> contentTypeRefControls;
	
	/**
	 * {@link ContentTypeRoot} message manager default class constructor.
	 */
	public ContentTypeRootControl()
	{
		super();

		//Initialize message.
		this.message = new ContentTypeRoot();
		
		//Associate message to control.
		this.addCommandOutputListener(this.message);
	}
	/**
	 * {@link ContentTypeRoot} message manager class constructor.
	 * @param parent {@link IControlContentTypeRootListener} the parent
	 * @param message {@link ContentTypeRoot} the message
	 */
	public ContentTypeRootControl(IControlContentTypeRootListener parent, ContentTypeRoot message) 
	{
		super(parent, message);
				
		//Initialize message.
		this.message = new ContentTypeRoot();
		
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
				//Initialize members lists.
				this.contentTypeControls = new ArrayList<ContentTypeControl>();
				this.fieldRefControls = new ArrayList<FieldRefControl>();
				this.contentTypeRefControls = new ArrayList<ContentTypeRefControl>();

				ContentTypeRoot contentTypeRoot = ContentTypeRoot.class.cast(e.getMessage());
				
				//Iterate through FieldRef list
				for(FieldRef fieldRef : contentTypeRoot.getFieldRef())
				{
					//Instantiate FieldRefControl for each FieldRef message
					FieldRefControl fieldRefControl = new FieldRefControl(this, fieldRef);
					
					//Associate child control to parent
					this.addCommandOutputListener(fieldRefControl);
					
					//Store control
					this.fieldRefControls.add(fieldRefControl);
					this.childControls.add(fieldRefControl);
				}
				
				//Iterate through ContentTypeRef list
				for(ContentTypeRef contentTypeRef : contentTypeRoot.getContentTypeRef())
				{
					//Initialize content type reference message control
					ContentTypeRefControl contentTypeRefControl = new ContentTypeRefControl(this, contentTypeRef);
				
					//Associate child control to parent
					this.addCommandOutputListener(contentTypeRefControl);
					
					//Store control
					this.contentTypeRefControls.add(contentTypeRefControl);
					this.childControls.add(contentTypeRefControl);
				}
				
				//Iterate through ContentType list
				for(ContentType contentType : contentTypeRoot.getContentType())
				{
					//Initialize content type message control
					ContentTypeControl contentTypeControl = new ContentTypeControl(this, contentType);
					
					//Associate child controls to parent
					this.addCommandOutputListener(contentTypeControl);
					
					//Store message control
					this.contentTypeControls.add(contentTypeControl);
					this.childControls.add(contentTypeControl);
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"ContentTypesControl.OnControl_Init; ContentTypeRoot message expected");
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
				ContentTypeRoot contentTypeRoot = ContentTypeRoot.class.cast(e.getMessage());
				
				//Iterate through child controls.
				if(this.hasChildControls())
				{
					while(this.hasNext())
					{
						IControlImpl control = this.next();
						
						if(control instanceof FieldRefControl ?
								!contentTypeRoot.getFieldRef().isEmpty() ?
										this.fieldRefControls.contains(control) 
										: false : false) {
							
							FieldRefControl fieldRefControl = FieldRefControl.class.cast(control);
							
							//Look for current FieldRef message control.
							for(FieldRef fieldRef : contentTypeRoot.getFieldRef())
							{
								//By UUID
								if(fieldRefControl.getUuid().equals(fieldRef.getUuid()))
								{
									//Set the message.
									fieldRefControl.controlMessage(fieldRef);
									break;
								}
							}
						}
						else if(control instanceof ContentTypeRefControl ? 
								!contentTypeRoot.getContentTypeRef().isEmpty() ? 
										this.contentTypeRefControls.contains(control) 
										: false : false) {
							
							ContentTypeRefControl contentTypeRefControl = ContentTypeRefControl.class.cast(control);

							//Look for current ContentTypeRef message control.
							for(ContentTypeRef contentTypeRef : contentTypeRoot.getContentTypeRef())
							{
								//By UUID.
								if(contentTypeRefControl.getUuid().equals(contentTypeRef.getUuid()))
								{
									//Set the message.
									contentTypeRefControl.controlMessage(contentTypeRef);
									break;
								}
							}
						}
						else if(control instanceof ContentTypeControl ? 
								!contentTypeRoot.getContentType().isEmpty() ? 
										this.contentTypeControls.contains(control) 
										: false : false) {
							
							ContentTypeControl contentTypeControl = ContentTypeControl.class.cast(control);

							//Look for current ContentType message control.
							for(ContentType contentType : contentTypeRoot.getContentType())
							{
								if(contentTypeControl.getUuid().equals(contentType.getUuid()))
								{
									contentTypeControl.controlMessage(contentType);
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
						"ContentTypesControl.OnControl_Loaded: ContentTypeRoot message expected");
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
		
		//Check sender is a child control.
		if(this.contentTypeControls.contains(e.getSource()))
		{
			//Check loaded message.
			if(e.getMessage() instanceof ContentType)
			{
				//Put loaded message to current data.
				this.put(ContentData.CONTENT_TYPE, e.getSource());	
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this,
						"ContentTypesControl.OnControlContentType_Loaded: ContentType message expected");
			}
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
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlContentType_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentType_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlContentTypeRoot_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRoot_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlContentTypeRoot_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRoot_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlContentTypeRoot_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRoot_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		//Set control ready to be iterated again.
		if(e.getSource().equals(this))
		{
			this.reset();
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlContentTypeRoot_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRoot_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlContentTypeRoot_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRoot_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this))
		{
			//Call input command
			CliCommandInputEvent(new CliEventArgs(this, Command.CONTROL_CONTENT_TYPE_ROOT, IControlListener.class.cast(e.getSource())));	
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlContentTypeRoot_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRoot_Write(Object sender, ControlEventArgs e)
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
		
		//Check sender is child control.
		if(this.childControls.contains(e.getSource()))
		{
			//Check loaded message.
			if(e.getMessage() instanceof FieldRef)
			{
				//Put loaded message to current data.
				this.put(ContentData.FIELD_REF, e.getMessage());	
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
		
		//Check sender is child control.
		if(this.childControls.contains(e.getSource()))
		{
			//Check loaded message.
			if(e.getMessage() instanceof ContentTypeRef)
			{
				//Put loaded message to current data.
				this.put(ContentData.CONTENT_TYPE_REF, e.getMessage());	
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this,
						"ContentTypesControl.OnControlContentType_Loaded: ContentTypeRef message expected");
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
	 * @see org.httprobot.core.controls.ContentControl#put(org.httprobot.core.common.Enums.SolrAttribute, java.lang.Object)
	 */
	@Override
	public Object put(ContentData key, Object value) 
	{
		switch (key) {
		
			case FIELD_REF:
				//Update message.
				this.message.getFieldRef().add(FieldRef.class.cast(value));
				break;
				
			case CONTENT_TYPE:
				//Update message
				this.message.getContentType().add(ContentType.class.cast(value));
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
	public Object remove(Object key)
	{
		if(key instanceof ContentData)
		{
			switch (ContentData.class.cast(key)) 
			{
				case FIELD_REF:
					break;
				case CONTENT_TYPE_REF:
					break;
				case CONTENT_TYPE:
					break;
			default:
				break;
			}
		}
		return super.remove(key);
	}
}