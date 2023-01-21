/**
 * 
 */
package org.httprobot.core.net;

import java.net.URL;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.UUID;
import java.util.Vector;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceFeature;

import org.httprobot.common.config.DataSourceList;
import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.common.datatypes.DataSource;
import org.httprobot.common.definitions.Enums.CliNamespace;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.definitions.Enums.ServiceLoaderEventType;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.ServiceLoaderException;
import org.httprobot.common.interfaces.IListener;
import org.httprobot.common.interfaces.IServiceImpl;
import org.httprobot.core.events.ServiceLoaderEventArgs;
import org.httprobot.core.interfaces.IServiceLoaderListener;
import org.w3c.dom.events.Event;

/**
 * Web service  loader class. Inherits {@link Service}.
 * Implements {@link IServiceImpl}.
 * @author joan
 */
public class ServiceLoader extends Service implements IServiceImpl, IListener
{
	private static final CliNamespace cliNamespace = CliNamespace.INET;
	
	/**
	 * -2410944878989797828L
	 */
	private static final long serialVersionUID = -2410944878989797828L;

	private String destinationPath;
	
	private EnumSet<RuntimeOptions> runtimeOptions;
	/**
	 * The web service listeners
	 */
	private Vector<IServiceLoaderListener> serviceLoaderListeners;
	/**
	 * IListener members
	 */
	private UUID uuid;
	
	/**
	 * Service loader class constructor.
	 * @param parent {@link IServiceLoaderListener} the parent
	 * @param wsdlDocumentLocation {@link URL} the WSDL URL
	 * @param serviceName {@link QName} the qualified name for the service
	 */
	public ServiceLoader(IServiceLoaderListener parent, URL wsdlDocumentLocation, QName serviceName) {		
		super(wsdlDocumentLocation, serviceName);
		initServiceLoader(parent);
	}	
	/**
	 * Service loader class constructor.
	 * @param parent {@link IServiceLoaderListener} the parent
	 * @param wsdlDocumentLocation {@link URL} the WSDL URL
	 * @param serviceName {@link QName} the qualified name for the service
	 * @param features {@link WebServiceFeature} the features
	 */
	public ServiceLoader(IServiceLoaderListener parent, URL wsdlDocumentLocation, QName serviceName, 
			WebServiceFeature... features) {
		super(wsdlDocumentLocation, serviceName, features);		
		initServiceLoader(parent);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getCliNamespace()
	 */
	@Override
	public CliNamespace getCliNamespace() {
		return cliNamespace;
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IServiceImpl#getServerInfoList()
	 */
	@Override
	public DataSourceList getDataSourceList() 
	{
		DataSourceList dataSourceList = this.getPort(IServiceImpl.class).getDataSourceList();
		ServiceLoaderEvent(new ServiceLoaderEventArgs(this, dataSourceList));
		return dataSourceList;
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getDestinationPath()
	 */
	@Override
	public String getDestinationPath() {
		return this.destinationPath;
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getRuntimeOptions()
	 */
	@Override
	public EnumSet<RuntimeOptions> getRuntimeOptions() {
		return this.runtimeOptions;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IServiceImpl#getSystemContentTypes()
	 */
	@Override
	public ContentTypeRoot getSystemContentTypeRoot()
	{
		ContentTypeRoot systemContentTypes = this.getPort(IServiceImpl.class).getSystemContentTypeRoot();
		ServiceLoaderEvent(new ServiceLoaderEventArgs(this, systemContentTypes));
		return systemContentTypes;
	}
	/* (non-Javadoc) 
	 * @see org.httprobot.common.interfaces.IServiceImpl#getTargetServerInfo(java.lang.String)
	 */
	@Override
	public DataSource getTargetDataSource(String dataSourceUUID) 
	{
		DataSource dataSource = this.getPort(IServiceImpl.class).getTargetDataSource(dataSourceUUID);
		ServiceLoaderEvent(new ServiceLoaderEventArgs(this, dataSource));
		return dataSource;
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#getUuid()
	 */
	@Override
	public UUID getUuid() {
		return this.uuid;
	}
	/* (non-Javadoc)
	 * @see org.w3c.dom.events.EventListener#handleEvent(org.w3c.dom.events.Event)
	 */
	@Override
	public void handleEvent(Event evt) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * For each {@link UUID} getTargetDataSource
	 * @param uuidList the list
	 */
	public void LoadDataSources(ArrayList<String> uuidList)
	{
		for(String uuid : uuidList)
		{
			getTargetDataSource(uuid);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#setDestinationPath(java.lang.String)
	 */
	@Override
	public void setDestinationPath(String destinationPath) {
		this.destinationPath = destinationPath;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#setRuntimeOptions(java.util.EnumSet)
	 */
	@Override
	public void setRuntimeOptions(EnumSet<RuntimeOptions> options) {
		this.runtimeOptions = options;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#setUuid(java.util.UUID)
	 */
	@Override
	public void setUuid(UUID uuid) {
		this.uuid = uuid;	
	}
	/**
	 * Starts the process.
	 */
	public void start()
	{
		//Initial required operations
		this.getSystemContentTypeRoot();
		this.getDataSourceList();
	}
	/**
	 * Initializes service loader members.
	 */
	private void initServiceLoader(IServiceLoaderListener parent)
	{
		//Set inherited data
		this.uuid  = parent.getUuid();
		this.runtimeOptions = parent.getRuntimeOptions();
		this.destinationPath = parent.getDestinationPath();		
		
		//Initialize members
		this.serviceLoaderListeners = new Vector<IServiceLoaderListener>();
		this.serviceLoaderListeners.add(parent);
		
		//Fire READY event
		ServiceLoaderEvent(new ServiceLoaderEventArgs(this, ServiceLoaderEventType.READY));
	}
	/**
	 * Fires web service loader event.
	 * @param e {@link ServiceLoaderEventArgs} the arguments
	 */
	protected final void ServiceLoaderEvent(ServiceLoaderEventArgs e) 
	{
		for(IServiceLoaderListener listener : this.serviceLoaderListeners)
		{
			try 
			{
				switch (e.getEventType()) 
				{
					case READY:					
						listener.OnServiceLoader_Ready(this, e);					
						break;
					case SYSTEM_CONTENT_TYPES_LOADED:
						listener.OnServiceLoader_SystemContentTypeRoot(this, e);
						break;
					case DATA_SOURCE_LIST_LOADED:
						listener.OnServiceLoader_DataSourceList(this, e);
						break;
					case DATA_SOURCE_LOADED:
						listener.OnServiceLoader_DataSource(this, e);
						break;
					case WEB_SERVICE_ERROR:
						listener.OnServiceLoader_WebServiceError(this, e);
					default:
						break;
				}
			} 
			catch (ServiceLoaderException ex) {
				ex.printStackTrace();
			}
		}
	}
}
