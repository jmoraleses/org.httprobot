/**
 * 
 */
package org.httprobot.core.controls.config;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.config.Configuration;
import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.common.datatypes.DataSource;
import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.ConfigData;
import org.httprobot.core.common.Enums.ContentData;
import org.httprobot.core.common.Enums.DataTypeData;
import org.httprobot.core.controls.ConfigControl;
import org.httprobot.core.controls.config.interfaces.IControlConfigurationListener;
import org.httprobot.core.controls.contents.ContentTypeRootControl;
import org.httprobot.core.controls.contents.interfaces.IControlContentTypeRootListener;
import org.httprobot.core.controls.datatypes.DataSourceControl;
import org.httprobot.core.controls.datatypes.interfaces.IControlDataSourceListener;
import org.httprobot.core.controls.interfaces.impl.IConfigControlImpl;
import org.httprobot.core.controls.interfaces.impl.IControlImpl;

/**
 * {@link Configuration} message control class. Inherits {@link ConfigControl}. 
 * <br>
 * Controls {@link Configuration} message and fires events for 
 * {@link IControlConfigurationListener} objects.
 * <br>
 * @author Joan
 *
 */
@XmlRootElement
public class ConfigurationControl 
		extends ConfigControl<Configuration, IControlConfigurationListener> 
		implements IControlDataSourceListener, IControlContentTypeRootListener,
			IConfigControlImpl {
	
	/**
	 * -4305797019544220348L
	 */
	private static final long serialVersionUID = -4305797019544220348L;
	/**
	 * The {@link ContentTypeRoot} message control.
	 */
	protected ContentTypeRootControl contentTypeRootControl;
	/**
	 * The {@link DataSource} messag control list.
	 */
	protected List<DataSourceControl> dataSourceControls;
	/**
	 * {@link Configuration} message control default class constructor.
	 */
	public ConfigurationControl()
	{
		super();
		
		//Initialize message.
		this.message = new Configuration();
		
		//Associate message to control.
		this.addCommandOutputListener(this.message);
	}
	/**
	 * {@link Configuration} message control class constructor.
	 * @param parent {@link IControlConfigurationListener} the parent
	 * @param config {@link Configuration} the message
	 */
	public ConfigurationControl(IControlConfigurationListener parent, Configuration message) 
	{
		super(parent, message);
		
		//Initialize message.
		this.message = new Configuration();
		
		//Associate message to control.
		this.addCommandOutputListener(this.message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Change(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#OnControl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Initialize(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		if(e.getMessage() != null ?
				e.getMessage() instanceof Configuration : false) {
			
			this.dataSourceControls = new ArrayList<DataSourceControl>();
			
			//Initialize message child control.
			Configuration config = Configuration.class.cast(e.getMessage());
			
			if(config.getContentTypeRoot() != null)
			{
				//Cast value.
				ContentTypeRoot contentTypeRoot = ContentTypeRoot.class.cast(config.getContentTypeRoot());
				
				//Initialize ContentTypeRoot message control.
				this.contentTypeRootControl = new ContentTypeRootControl(this, contentTypeRoot);
				
				//Associate control to parent.
				this.addCommandOutputListener(this.contentTypeRootControl);
				
				//Store control.
				this.childControls.add(this.contentTypeRootControl);
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"ConfigurationControl.OnControl_Init: ContentTypeRoot message cannot be null");
			}
			
			if(!config.getDataSource().isEmpty())
			{
				for(DataSource dataSource : config.getDataSource())
				{
					//This instance listens for it's CliCommandInput
					DataSourceControl dataSourceControl = new DataSourceControl(this, dataSource);
					
					//Associate child controls to parent.
					this.addCommandOutputListener(dataSourceControl);
					
					//Store control.
					this.dataSourceControls.add(dataSourceControl);
					this.childControls.add(dataSourceControl);
				}
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"ConfigurationControl.OnControl_Init: DataSource message list cannot be empty");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#OnControl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Load(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		if(e.getMessage() != null)
		{
			try
			{
				Configuration config = Configuration.class.cast(e.getMessage());
				
				if(hasChildControls())
				{
					while(hasNext())
					{
						IControlImpl control = next();
						
						if(control instanceof ContentTypeRootControl ?
								this.contentTypeRootControl != null ?
										this.contentTypeRootControl.equals(control) 
										: false : false) {
							
							//Set the ContentTypeRoot message to control.
							this.contentTypeRootControl.controlMessage(config.getContentTypeRoot());
						}
						
						if(control instanceof DataSourceControl ?
								!this.dataSourceControls.isEmpty() ?
										this.dataSourceControls.contains(control) 
										: false : false) {
							
							DataSourceControl dataSourceControl = DataSourceControl.class.cast(control);
							
							//Look for matching control's message.
							for(DataSource dataSource : config.getDataSource())
							{
								//by UUID.
								if(dataSourceControl.getUuid().equals(dataSource.getUuid()))
								{
									//Set the message.
									dataSourceControl.controlMessage(dataSource);
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
						"ConfigControl.OnControl_Loaded: Configuration message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#OnControl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		try
		{
			Configuration.class.cast(e.getMessage());
		}
		catch(ClassCastException ex)
		{
			CliTools.ThrowInconsistentMessageException(this, 
					"ConfigControl.OnControl_Read: Configuration message expected");
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#OnControl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Render(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#OnControl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#OnControlConfiguration_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConfiguration_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#OnControlConfiguration_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConfiguration_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#OnControlConfiguration_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConfiguration_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		//Set control ready to be iterated again.
		if(e.getSource().equals(this))
		{
			this.reset();
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#OnControlConfiguration_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConfiguration_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#OnControlConfiguration_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConfiguration_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this))
		{
			//Fire input command event.
			CliCommandInputEvent(new CliEventArgs(this, Command.CONTROL_CONFIG));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#OnControlConfiguration_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlConfiguration_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRootListener#OnControlContentTypeRoot_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRoot_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRootListener#OnControlContentTypeRoot_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRoot_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRootListener#OnControlContentTypeRoot_Loaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRoot_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		//Check sender is current child's control.
		if(e.getSource().equals(this.contentTypeRootControl))
		{
			//Check message matches.
			if(e.getMessage() instanceof ContentTypeRootControl) 
			{
				// Update current control's data.
				this.put(ConfigData.SYSTEM_CONTENT_TYPE_ROOT, this.contentTypeRootControl);

				// Acknowledge to child control's message has been settled. Render control.
				this.contentTypeRootControl.put(ContentData.CONTENT_TYPE_ROOT, this.contentTypeRootControl.getMessage());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this,
					"ConfigurationControl.OnControlContentTypeRoot_Loaded: ContentTypeRoot message expected");	
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRootListener#OnControlContentTypeRoot_Read(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRoot_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRootListener#OnControlContentTypeRoot_Rendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRoot_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.contentTypeRootControl))
		{
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlContentTypeRootListener#OnControlContentTypeRoot_Write(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlContentTypeRoot_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDataSourceListener#OnControlDataSource_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataSource_Changed(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDataSourceListener#OnControlDataSource_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataSource_Init(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDataSourceListener#OnControlDataSource_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataSource_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		//Check sender is current child's control.
		if(this.childControls.contains(e.getSource()))
		{
			if(e.getSource() instanceof DataSourceControl) {
				
				// Cast value.
				DataSourceControl dataSourceControl = DataSourceControl.class.cast(e.getSource());

				// Update current control's data.
				this.put(ConfigData.DATA_SOURCE, dataSourceControl);

				// Acknowledge to child control's message has been settled. Render control.
				dataSourceControl.put(DataTypeData.DATA_SOURCE, dataSourceControl.getMessage());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
					"ConfigurationControl.OnControlDataSource_Loaded: DataSource message expected");	
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDataSourceListener#OnControlDataSource_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataSource_Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDataSourceListener#OnControlDataSource_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataSource_Rendered(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.datatypes.interfaces.IControlDataSourceListener#OnControlDataSource_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlDataSource_Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#put(org.httprobot.core.common.Enums.ConfigAttribute, java.lang.Object)
	 */
	@Override
	public Object put(ConfigData key, Object value) 
	{
		switch (key)
		{
		case SYSTEM_CONTENT_TYPE_ROOT:
			
			if(this.contentTypeRootControl.equals(value))
			{
				// Update message data.
				this.message.setContentTypeRoot(this.contentTypeRootControl.getMessage());
			}
			break;

		case DATA_SOURCE:
			//Check value,
			if(this.dataSourceControls.contains(value) ?
					value instanceof DataSourceControl : false) {

				// Cast value.
				DataSourceControl dataSourceControl = DataSourceControl.class.cast(value);

				// Update message data.
				this.message.getDataSource().add(dataSourceControl.getMessage());
			}
			break;

		default:
			break;
		}
		//Update data
		return super.put(key, value);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.ConfigControl#remove(java.lang.Object)
	 */
	@Override
	public Object remove(Object key) 
	{
		return super.remove(key);
	}
}