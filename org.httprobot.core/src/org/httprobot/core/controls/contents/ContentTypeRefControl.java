/**
 * 
 */
package org.httprobot.core.controls.contents;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.controls.ContentControl;
import org.httprobot.core.controls.contents.interfaces.IControlContentTypeRefListener;
import org.httprobot.core.controls.interfaces.impl.IContentControlImpl;

/**
 * {@link ContentTypeRef} message control class. Inherits {@link ContentControl}.
 * <br>
 * Controls {@link ContentTypeRef} message and fire 
 * events to {@link IControlContentTypeRefListener} objects.
 * <br>
 * @author joan
 *
 */
@XmlRootElement
public class ContentTypeRefControl 
		extends ContentControl<ContentTypeRef, IControlContentTypeRefListener>
		implements IContentControlImpl {
	
	/**
	 * -7123468172935131025L
	 */
	private static final long serialVersionUID = -7123468172935131025L;
	/**
	 * {@link ContentTypeRef} message control default class constructor.
	 */
	public ContentTypeRefControl() 
	{
		super();
		
		//Initialize members
		this.message = new ContentTypeRef();
		
		//Associate message to control.
		this.addCommandOutputListener(this.message);
	}
	/**
	 * {@link ContentTypeRef} message control class constructor.
	 * @param parent the parent
	 * @param message the message
	 */
	public ContentTypeRefControl(IControlContentTypeRefListener parent, ContentTypeRef message)
	{
		super(parent, message);
		
		//Initialize members
		this.message = new ContentTypeRef();
		
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
				ContentTypeRef contentTypeRef = ContentTypeRef.class.cast(e.getMessage());
			
				//Set inherited data
				
				if(contentTypeRef.getUuid() == null)
				{
					CliTools.ThrowInconsistentMessageException(this, 
							"ContentTypeRefControl.OnControl_Init: UUID cannot be null.");
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this,
						"ContentTypeRefControl.OnControl_Init: ContentTypeRef message expected");
			}	
		}		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Load(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
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
		
		if(e.getSource().equals(this))
		{
			this.reset();			
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
		
		if(e.getSource().equals(this))
		{
			//Send event to listeners.
			CliCommandInputEvent(new CliEventArgs(this, Command.CONTROL_CONTENT_TYPE_REF, e.getMessage()));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ContentControl#OnControlContentTypeRef_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
}