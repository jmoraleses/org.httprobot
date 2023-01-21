package org.httprobot.core.controls.datatypes;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.common.datatypes.Document;
import org.httprobot.common.datatypes.DocumentRoot;
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
import org.httprobot.core.controls.datatypes.DocumentControl;
import org.httprobot.core.controls.datatypes.interfaces.IControlDocumentRootListener;
import org.httprobot.core.controls.interfaces.impl.IControlImpl;
import org.httprobot.core.controls.interfaces.impl.IDataTypeControlImpl;
import org.httprobot.core.controls.unit.ActionControl;
import org.httprobot.core.controls.unit.interfaces.IControlActionListener;

/**
 * {@link DocumentRoot} message control class. Inherits {@link DataTypeControl}.
 * <br>
 * @author Joan
 * 
 */
@XmlRootElement
public class DocumentRootControl 
		extends DataTypeControl<DocumentRoot, IControlDocumentRootListener>
		implements IControlContentTypeRefListener, IControlActionListener,
			IDataTypeControlImpl {
	
	/**
	 * -5878517083143765613L
	 */
	private static final long serialVersionUID = -5878517083143765613L;

	/**
	 * The list of {@link Document} message controls.
	 */
	protected List<DocumentControl> documentControls;	
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
	protected FieldRootControl fieldsControl;
	
	/**
	 * {@link DocumentRoot} control default class constructor.
	 */
	public DocumentRootControl()
	{
		super();
		
		//Initialize message.
		this.message = new DocumentRoot();
		
		//Associate message to control.
		this.addCommandOutputListener(this.message);
	}
	/**
	 * {@link DocumentRoot} control class constructor.
	 * @param parent {@link IControlListener} the parent control
	 * @param documentRoot {@link DocumentRoot} the root document
	 */
	public DocumentRootControl(IControlDocumentRootListener parent, DocumentRoot documentRoot) 
	{
		super(parent, documentRoot);
		
		//Initialize message.
		this.message = new DocumentRoot();
		
		//Associate message to control.
		this.addCommandOutputListener(this.message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Change(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Initialize(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException {
		
		if(e.getMessage() != null)
		{
			try
			{
				//Initialize using data
				this.documentControls = new ArrayList<DocumentControl>();
				
				DocumentRoot rootDocument = DocumentRoot.class.cast(e.getMessage());
				
				if(rootDocument.getContentTypeRef() != null)
				{
					//Initialize ContentType reference message control
					this.contentTypeRefControl = new ContentTypeRefControl(this, rootDocument.getContentTypeRef());

					//Associate control to parent
					this.contentTypeRefControl.addControlContentTypeRefListener(this);
					this.addCommandOutputListener(this.contentTypeRefControl);
					
					//Store control.
					this.childControls.add(this.contentTypeRefControl);
				}
				if(rootDocument.getAction() != null)
				{
					//Initialize Action message control
					this.actionControl = new ActionControl(this, rootDocument.getAction());
					
					//Associate control to parent
					this.actionControl.addControlActionListener(this);
					this.addCommandOutputListener(this.actionControl);

					//Store control.
					this.childControls.add(this.actionControl);
				}
				if(rootDocument.getFields() != null)
				{
					//Initialize Fields message control
					this.fieldsControl = new FieldRootControl(this, rootDocument.getFields());
					
					//Associate control to parent
					this.fieldsControl.addControlFieldRootListener(this);
					this.addCommandOutputListener(this.fieldsControl);

					//Store control.
					this.childControls.add(this.fieldsControl);
				}
				for(Document document : rootDocument.getDocument())
				{
					//Initialize document message control
					DocumentControl documentControl = new DocumentControl(this, document);
					
					//Associate child controls to current
					documentControl.addControlDocumentListener(this);
					this.addCommandOutputListener(documentControl);
					
					//Store the control
					this.documentControls.add(documentControl);

					//Store control.
					this.childControls.add(documentControl);
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"DocumentRootControl.OnControl_Init: DocumentRoot message expected");
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
				DocumentRoot rootDocument = DocumentRoot.class.cast(e.getMessage());
				boolean error = false;
				
				if(this.hasChildControls())
				{
					//Iterate through child message controls.
					while(this.hasNext())
					{
						IControlImpl control = this.next();
						
						//ContentTypeRef
						if(control.equals(this.contentTypeRefControl) ? 
								rootDocument.getContentTypeRef() != null ? 
										control.getUuid().equals(rootDocument.getContentTypeRef().getUuid()) 
										: false : false) {
							
							//Set message to ContentTypeRef control. 
							this.contentTypeRefControl.controlMessage(rootDocument.getContentTypeRef());
						}
						//Action
						else if(control.equals(this.actionControl) ?
								rootDocument.getAction() != null ?
										control.getUuid().equals(rootDocument.getAction().getUuid()) 
										: false : false) {
							
							//Set message to Action control.
							this.actionControl.controlMessage(rootDocument.getAction());
						}
						//Documents
						else if(this.documentControls.contains(control) ?
								!rootDocument.getDocument().isEmpty() : false ) {
							
							//Cast control.
							DocumentControl documentControl = DocumentControl.class.cast(control);
							
							//Look for correspondent message.
							for(Document document : rootDocument.getDocument())
							{
								if(documentControl.getUuid().equals(document.getUuid()))
								{
									//Set the message to current Document message control.
									documentControl.controlMessage(document);
									break;
								}
							}
						}
						else
						{
							error = true;
						}
					}
					if(error)
					{
						CliTools.ThrowInconsistentMessageException(this, 
								"DocumentRootControl.OnControl_Loaded: No matching control has been found.");
					}
				}			
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"DocumentRootControl.OnControl_Loaded: DocumentRoot message expected");
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
		if(this.documentControls.contains(e.getSource()))
		{
			if(e.getMessage() instanceof Document)
			{
				//Update control's data.
				this.put(DataTypeData.DOCUMENT, e.getMessage());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this,
						"DocumentRootControl.OnControlDocument_Loaded: Unkown type");
			}
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
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlDocument_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocument_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlDocumentRoot_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocumentRoot_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlDocumentRoot_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocumentRoot_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlDocumentRoot_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocumentRoot_Loaded(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		//Set control ready to be iterated again.
		if(e.getSource().equals(this))
		{
			this.reset();
			
			//Send input Command event.
			CliCommandInputEvent(new CliEventArgs(this, Command.CONTROL_DOCUMENT_ROOT, e.getMessage()));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlDocumentRoot_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocumentRoot_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlDocumentRoot_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocumentRoot_Rendered(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlDocumentRoot_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDocumentRoot_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
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
						"DataSourceControl.OnControlContentTypeRefLoaded: ContentTypeRef message expected");
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
	 * @see org.httprobot.core.controls.DataTypeControl#put(org.httprobot.core.common.Enums.DataTypeData, java.lang.Object)
	 */
	@Override
	public Object put(DataTypeData key, Object value)
	{
		switch (key) 
		{
		case DOCUMENT:
			this.message.getDocument().add(Document.class.cast(value));
			break;
		case FIELD_ROOT:
			this.message.setFields(FieldRoot.class.cast(value));
			break;
		case ACTION:
			this.message.setAction(Action.class.cast(value));
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