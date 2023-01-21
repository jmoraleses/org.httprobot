package org.httprobot.core.controls.datatypes;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.common.datatypes.Document;
import org.httprobot.common.datatypes.FieldRoot;
import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.tools.CliTools;
import org.httprobot.common.unit.Action;
import org.httprobot.core.common.Enums.DataTypeData;
import org.httprobot.core.controls.DataTypeControl;
import org.httprobot.core.controls.contents.ContentTypeRefControl;
import org.httprobot.core.controls.contents.interfaces.IControlContentTypeRefListener;
import org.httprobot.core.controls.datatypes.interfaces.IControlDocumentListener;
import org.httprobot.core.controls.interfaces.impl.IControlImpl;
import org.httprobot.core.controls.interfaces.impl.IDataTypeControlImpl;
import org.httprobot.core.controls.unit.ActionControl;
import org.httprobot.core.controls.unit.interfaces.IControlActionListener;

/**
 * {@link Document} message control class. Inherits {@link DataTypeControl}.
 * <br>
 * @author Joan
 * 
 */
@XmlRootElement
public class DocumentControl 
		extends DataTypeControl<Document, IControlDocumentListener>
		implements IControlContentTypeRefListener, IControlActionListener, 
			IDataTypeControlImpl {
	
	/**
	 * 6271313548003227854L
	 */
	private static final long serialVersionUID = 6271313548003227854L;	

	/**
	 * The {@link ContentTypeRef} message control.
	 */
	protected ContentTypeRefControl contentTypeRefControl;
	/**
	 * The {@link Action} message control.
	 */
	protected ActionControl actionControl;
	/**
	 * The {@link FieldRoot} message control.
	 */
	protected FieldRootControl fieldRootControl;
	/**
	 * The {@link Document} message control.
	 */
	protected DocumentControl documentControl;
	
	/**
	 * {@link Document} control default class constructor
	 */
	public DocumentControl()
	{
		super();

		//Initialize message.
		this.message = new Document();

		//Associate message to control.
		addCommandOutputListener(this.message);
	}
	/**
	 * {@link Document} control constructor
	 * @param parent {@link IControlListener} listener
	 * @param document {@link Document} next step
	 */
	public DocumentControl(IControlDocumentListener parent, Document document)
	{
		super(parent, document);
		
		//Initialize message.
		this.message = new Document();

		//Associate message to control.
		this.addCommandOutputListener(this.message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Change(Object sender, ControlEventArgs e)
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
			Document document = Document.class.cast(e.getMessage());
			
			try
			{
				//Initialize Action message control.
				if(document.getAction() != null)
				{
					//If action message not null instantiate action message control.
					this.actionControl = new ActionControl(this, document.getAction());
					
					//Associate child control to parent.
					this.addCommandOutputListener(this.actionControl);
					
					//Store control.
					this.childControls.add(this.actionControl);
				}
				
				//Initialize ContentTypeRef message control.
				if(document.getContentTypeRef() != null)
				{
					this.contentTypeRefControl = new ContentTypeRefControl(this, document.getContentTypeRef());

					//Associate child control to parent.
					this.addCommandOutputListener(this.contentTypeRefControl);

					//Store control.
					this.childControls.add(this.actionControl);
				}
				
				//Initialize FieldRoot  message control.
				if(document.getFieldRoot() != null)
				{
					//If fields message not null instantiate fields message control.		
					this.fieldRootControl = new FieldRootControl(this, document.getFieldRoot());
					
					//Associate child control to parent.
					this.addCommandOutputListener(this.fieldRootControl);
					
					//Store control.
					this.childControls.add(this.actionControl);
				}
				
				//Initialize Document message control.
				if(document.getDocument() != null)
				{
					//If step message not null instantiate step message control.
					this.documentControl = new DocumentControl(this, document.getDocument());
					
					//Associate child control to parent.
					this.addCommandOutputListener(this.documentControl);

					//Store control.
					this.childControls.add(this.actionControl);
				}				
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this,
						"DocumentControl.OnControl_Init: Document message expected");	
			}
		}
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
				Document document = Document.class.cast(e.getMessage());
				
				if(this.hasChildControls())
				{
					//Iterate through controls.
					while(this.hasNext())
					{
						IControlImpl control = this.next();
						
						//Find ContentTypeRef message control.
						if(control.equals(this.contentTypeRefControl) ?
								document.getContentTypeRef() != null ?
										control.getUuid().equals(document.getContentTypeRef().getUuid()) 
										: false : false) {
							
							//Set the message.
							this.contentTypeRefControl.controlMessage(document.getContentTypeRef());
						}
						
						//Find Action message control.
						if(control.equals(this.actionControl) ?
								document.getAction() != null ?
										control.getUuid().equals(document.getAction().getUuid()) 
										: false : false ) {
							
							//Set the message.
							this.actionControl.controlMessage(document.getAction());
						}
						
						//Find Fields message control.
						if(control.equals(this.fieldRootControl) ?
								document.getFieldRoot() != null ?
										control.getUuid().equals(document.getFieldRoot().getUuid()) 
										: false : false ) {
							
							//Set the message.
							this.fieldRootControl.controlMessage(document.getFieldRoot());
						}
						
						//Find Document message control.
						if(control.equals(this.documentControl) ?
								document.getDocument() != null ?
										control.getUuid().equals(document.getDocument().getUuid()) 
										: false : false ) {
							
							//Set the message.
							this.documentControl.controlMessage(document.getDocument());
						}
					}
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"DocumentControl.OnControl_Loaded: Document message expected");
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
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlAction_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAction_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlAction_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAction_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlAction_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAction_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {

		if(e.getSource().equals(this.actionControl))
		{
			if(e.getMessage() instanceof Action)
			{
				this.put(DataTypeData.ACTION, e.getMessage());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"DocumentControl.OnControlAction_Loaded: Action message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlAction_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAction_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlAction_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAction_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlAction_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlAction_Write(Object sender, ControlEventArgs e) 
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
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.fieldRootControl))
		{
			if(e.getMessage() instanceof FieldRoot)
			{
				this.put(DataTypeData.FIELD_ROOT, e.getMessage());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"DataSourceControl.OnControlFieldRoot_Loaded: FieldRoot message expected");
			}
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
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlDocument_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocument_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlDocument_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocument_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlDocument_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocument_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		//Check event source is a child control
		if(e.getSource().equals(this.documentControl))
		{
			if(e.getMessage() instanceof Document)
			{
				//Update control's data.
				this.put(DataTypeData.DOCUMENT, e.getMessage());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"DocumentControl.OnControlDocument_Loaded: Document message expected");
			}
		}
		else if(e.getSource().equals(this))
		{
			this.reset();
			CliCommandInputEvent(new CliEventArgs(this, Command.CONTROL_DOCUMENT, e.getMessage()));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlDocument_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocument_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlDocument_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocument_Rendered(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRefListener#OnControlContentTypeRef_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRefListener#OnControlContentTypeRef_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRefListener#OnControlContentTypeRef_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.contentTypeRefControl))
		{
			if(e.getMessage() instanceof ContentTypeRef)
			{
				this.put(DataTypeData.CONTENT_TYPE_REF, e.getMessage());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"DocumentControl.OnControlContentTypeRef_Loaded: ContentTypeRef message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRefListener#OnControlContentTypeRef_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRefListener#OnControlContentTypeRef_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRefListener#OnControlContentTypeRef_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Write(Object sender, ControlEventArgs e)
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
		case ACTION:
			this.message.setAction(Action.class.cast(value));
			break;

		case CONTENT_TYPE_REF:
			this.message.setContentTypeRef(ContentTypeRef.class.cast(value));
			break;

		case FIELD_ROOT:
			this.message.setFieldRoot(FieldRoot.class.cast(value));
			break;

		case DOCUMENT:
			this.message.setDocument(Document.class.cast(value));
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