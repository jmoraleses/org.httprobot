package org.httprobot.core.controls.datatypes;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.common.datatypes.DataSource;
import org.httprobot.common.datatypes.DocumentRoot;
import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.params.ServerUrl;
import org.httprobot.common.params.StartUrl;
import org.httprobot.common.tools.CliTools;
import org.httprobot.common.unit.Action;
import org.httprobot.core.common.Enums.ContentData;
import org.httprobot.core.common.Enums.DataTypeData;
import org.httprobot.core.common.Enums.ParameterData;
import org.httprobot.core.common.Enums.UnitData;
import org.httprobot.core.controls.DataTypeControl;
import org.httprobot.core.controls.contents.ContentTypeRefControl;
import org.httprobot.core.controls.contents.interfaces.IControlContentTypeRefListener;
import org.httprobot.core.controls.datatypes.interfaces.IControlDataSourceListener;
import org.httprobot.core.controls.interfaces.impl.IControlImpl;
import org.httprobot.core.controls.interfaces.impl.IDataTypeControlImpl;
import org.httprobot.core.controls.parameters.ServerUrlControl;
import org.httprobot.core.controls.parameters.StartUrlControl;
import org.httprobot.core.controls.parameters.interfaces.IControlServerUrlListener;
import org.httprobot.core.controls.parameters.interfaces.IControlStartUrlListener;
import org.httprobot.core.controls.unit.ActionControl;
import org.httprobot.core.controls.unit.interfaces.IControlActionListener;

/**
 * {@link DataSource} message control class. Inherits {@link DataTypeControl}.
 * <br>
 * It's {@link IControlServerUrlListener}, {@link IControlStartUrlListener} 
 * and {@link IControlContentTypeRefListener}.
 * <br>
 * @author Joan
 */
@XmlRootElement
public class DataSourceControl 
		extends DataTypeControl<DataSource, IControlDataSourceListener>
		implements IControlServerUrlListener, IControlStartUrlListener, 
			IControlContentTypeRefListener, IControlActionListener,
			IDataTypeControlImpl {
	
	/**
	 * -7800283969359559523L
	 */
	private static final long serialVersionUID = -7800283969359559523L;
	
	/**
	 * The {@link Action} message control.
	 */
	protected ActionControl actionControl;
	/**
	 * The {@link ContentTypeRef} message control.
	 */
	protected ContentTypeRefControl contentTypeRefControl;	
	/**
	 * The {@link DocumentRoot} message control.
	 */
	protected DocumentRootControl documentRootControl;
	/**
	 * The {@link ServerUrl} message control.
	 */
	protected ServerUrlControl serverUrlControl;
	/**
	 * The {@link StartUrl} message control.
	 */
	protected StartUrlControl startUrlControl;
	
	/**
	 * {@link DataSource} message control default class constructor.
	 */
	public DataSourceControl()
	{
		super();
		
		//Initialize message.
		this.message = new DataSource();
		
		//Associate message to control.
		addCommandOutputListener(this.message);
	}
	/**
	 * {@link DataSource} message control class constructor.
	 * @param parent {@link IControlListener}
	 * @param dataSource {@link DataSource}
	 */
	public DataSourceControl(IControlDataSourceListener parent, DataSource dataSource) 
	{
		super(parent, dataSource);
		
		//Initialize message.
		this.message = new DataSource();

		//Associate message to control.
		addCommandOutputListener(this.message);
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
			try
			{
				DataSource dataSource = DataSource.class.cast(e.getMessage());			

				//Required element
				if(dataSource.getAction() != null)
				{
					//Initialize Action message control
					this.actionControl = new ActionControl(this, dataSource.getAction());
					
					//Associate control to parent
					this.addCommandOutputListener(this.actionControl);
					
					//Store control
					this.childControls.add(this.actionControl);
				}
				else
				{
					CliTools.ThrowInconsistentMessageException(this, 
							"DataSourceControl.OnControl_Init: Action message expected");
				}
				//Required element
				if(dataSource.getContentTypeRef() != null)
				{
					//Initialize ContentTypeRef message control
					this.contentTypeRefControl = new ContentTypeRefControl(this, dataSource.getContentTypeRef());
					
					//Associate control to parent
					this.addCommandOutputListener(this.contentTypeRefControl);
					
					//Store control
					this.childControls.add(this.contentTypeRefControl);
				}
				else
				{
					CliTools.ThrowInconsistentMessageException(this, 
							"DataSourceControl.OnControl_Init: ContentTypeRef message expected");
				}
				//Required element
				if(dataSource.getDocumentRoot() != null)
				{
					//Instantiate steps control
					this.documentRootControl = new DocumentRootControl(this, dataSource.getDocumentRoot());
					
					//Associate control to parent
					this.addCommandOutputListener(this.documentRootControl);
					
					//Store control
					this.childControls.add(this.documentRootControl);
				}
				else
				{
					CliTools.ThrowInconsistentMessageException(this,
							"DataSourceControl.OnControl_Init: DocumentRoot message expected");		
				}
				//Required element
				if(dataSource.getServerUrl() != null)
				{
					//Instantiate ServerUrl control
					this.serverUrlControl = new ServerUrlControl(this, dataSource.getServerUrl());
				
					//Associate control to parent
					this.addCommandOutputListener(this.serverUrlControl);
					
					//Store control
					this.childControls.add(this.serverUrlControl);
				}
				else
				{
					CliTools.ThrowInconsistentMessageException(this,
							"DataSourceControl.OnControl_Init: ServerUrl message expected");
				}
				//Required element
				if(dataSource.getStartUrl() != null)
				{
					//Instantiate ServerUrl control
					this.startUrlControl = new StartUrlControl(this, dataSource.getStartUrl());
				
					//Associate control to parent
					this.addCommandOutputListener(this.startUrlControl);

					//Store control
					this.childControls.add(this.startUrlControl);
				}
				else
				{
					CliTools.ThrowInconsistentMessageException(this, 
							"DataSourceControl.OnControlInit: ServerUrl message expected");
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"DataSourceControl.OnControlInit: DataSource message expected");
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
				DataSource dataSource = DataSource.class.cast(e.getMessage());
				
				//Set non-controlled data
				this.put(DataTypeData.NTP, dataSource.getNTP());
				this.put(DataTypeData.SOURCE_NAME, dataSource.getSourceName());
				
				if(this.hasChildControls())
				{
					while(this.hasNext())
					{
						IControlImpl control = this.next();
						
						//Check by instance, message existence and matching child control.
						if(control instanceof ActionControl ?
								dataSource.getAction() != null ? 
										control.equals(this.actionControl)
										: false : false) {
							
							//Set Action message control.
							this.actionControl.controlMessage(dataSource.getAction());
						}
						else if(control instanceof DocumentRootControl ?
								dataSource.getDocumentRoot() != null ?
										control.equals(this.documentRootControl)
										: false : false) {

							//Set DocumentRoot message control.
							this.documentRootControl.controlMessage(dataSource.getDocumentRoot());
						}
						else if(control instanceof ContentTypeRefControl ?
								dataSource.getContentTypeRef() != null ?
										control.equals(this.contentTypeRefControl)
										: false : false) {

							//Set ContentTypeRef message control.
							this.contentTypeRefControl.controlMessage(dataSource.getContentTypeRef());		
						}
						else if(control instanceof ServerUrlControl ?
								dataSource.getServerUrl() != null ?
										control.equals(this.serverUrlControl)
										: false : false) {

							//Set ServerUrl message control.
							this.serverUrlControl.controlMessage(dataSource.getServerUrl());
						}
						else if(control instanceof StartUrlControl ?
								dataSource.getStartUrl() != null ?
										control.equals(this.startUrlControl)
										: false : false) {

							//Set StartUrl message control.
							this.startUrlControl.controlMessage(dataSource.getStartUrl());
						}
					}
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"DataSourceControl.OnControl_Loaded: DataSource message expected");
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
						"DataSourceControl.OnControlAction_Loaded: Action message expected");
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
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlDataSource_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataSource_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlDataSource_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataSource_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlDataSource_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataSource_Loaded(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		//Set control ready to be iterated again.
		if(e.getSource().equals(this))
		{
			this.reset();
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlDataSource_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataSource_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlDataSource_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataSource_Rendered(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		if(e.getSource().equals(this))
		{
			CliCommandInputEvent(new CliEventArgs(this, Command.CONTROL_DATA_SOURCE, e.getMessage()));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.DataTypeControl#OnControlDataSource_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataSource_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRefListener#OnControlContentTypeRef_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Changed(Object sender, ControlEventArgs e) 
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
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRefListener#OnControlContentTypeRef_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRef_Read(Object sender, ControlEventArgs e) 
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
			throws InconsistenMessageException, InconsistenMessageException {
		
		if(e.getSource().equals(this.documentRootControl))
		{
			if(e.getMessage() instanceof DocumentRoot)
			{
				this.put(DataTypeData.DOCUMENT_ROOT, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"DataSourceControl.OnDocumentRoot_Loaded: DocumentRoot message expected");
			}
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
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlServerUrlListener#OnControlServerUrl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServerUrl_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlServerUrlListener#OnControlServerUrl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServerUrl_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlServerUrlListener#OnControlServerUrl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServerUrl_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.serverUrlControl))
		{
			if(e.getMessage() instanceof ServerUrl)
			{
				this.put(DataTypeData.SERVER_URL, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"DataSourceControl.OnServerUrlLoaded: ServerUrl message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlServerUrlListener#OnControlServerUrl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServerUrl_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlServerUrlListener#OnControlServerUrl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServerUrl_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlServerUrlListener#OnControlServerUrl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlServerUrl_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlStartUrlListener#OnControlStartUrl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlStartUrl_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlStartUrlListener#OnControlStartUrl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlStartUrl_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlStartUrlListener#OnControlStartUrl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlStartUrl_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.startUrlControl))
		{
			if(e.getMessage() instanceof StartUrl)
			{	
				this.put(DataTypeData.START_URL, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"DataSourceControl.OnServerUrlLoaded: ServerUrl message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlStartUrlListener#OnControlStartUrl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlStartUrl_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlStartUrlListener#OnControlStartUrl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlStartUrl_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.parameters.interfaces.IControlStartUrlListener#OnControlStartUrl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlStartUrl_Write(Object sender, ControlEventArgs e) 
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
		case SOURCE_NAME:
			this.message.setSourceName(String.class.cast(value));
			break;
			
		case ACTION:
			// Check value is a child control.
			if(value.equals(this.actionControl))
			{
				// Update current control's message.
				this.message.setAction(Action.class.cast(value));
				
				// Acknowledge child control's message has been settled. Render control.
				this.actionControl.put(UnitData.ACTION, this.actionControl.getMessage());
			}
			break;
			
		case CONTENT_TYPE_REF:
			// Check value is a child control.
			if(value.equals(this.contentTypeRefControl))
			{
				// Update current control's message.
				this.message.setContentTypeRef(ContentTypeRef.class.cast(value));

				// Acknowledge child control's message has been settled. Render control.
				this.contentTypeRefControl.put(ContentData.CONTENT_TYPE_REF, this.contentTypeRefControl.getMessage());
			}
			
			break;
			
		case DOCUMENT_ROOT:
			// Check value is a child control.
			if(value.equals(this.documentRootControl))
			{
				// Update current control's message.
				this.message.setDocumentRoot(this.documentRootControl.getMessage());
				
				// Acknowledge child control's message has been settled. Render control.
				this.documentRootControl.put(DataTypeData.DOCUMENT_ROOT, this.documentRootControl.getMessage());
			}
			
			break;
		case SERVER_URL:
			// Check value is a child control.
			if(value.equals(this.serverUrlControl))
			{
				ServerUrl serverUrl = this.serverUrlControl.getMessage();

				//Check input message.
				if (serverUrl.getParamName() != null && 
						serverUrl.getValue() != null) {
					
					// Update current control's message.
					this.message.setServerUrl(serverUrl);
					
					// Acknowledge child control's message has been settled. Render control.
					this.serverUrlControl.put(ParameterData.SERVER_URL, serverUrl);
				}
			}
			break;
		case START_URL:
			// Check value is a child control.
			if(value.equals(this.startUrlControl))
			{
				StartUrl startUrl = this.startUrlControl.getMessage();

				if (startUrl.getParamName() != null && startUrl.getValue() != null) 
				{
					// Update current control's message.
					this.message.setStartUrl(startUrl);

					// Acknowledge child control's message has been settled. Render control.
					this.startUrlControl.put(ParameterData.START_URL, startUrl);
				}
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
