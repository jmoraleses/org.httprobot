/**
 * 
 */
package org.httprobot.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import javax.xml.ws.Endpoint;

import org.httprobot.common.config.DataSourceList;
import org.httprobot.common.config.DataSourceRef;
import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.common.datatypes.DataSource;
import org.httprobot.common.interfaces.IServiceImpl;

/**
 * @author joan
 *
 */
@WebService(endpointInterface="org.httprobot.common.interfaces.IServiceImpl")
public class RobotSource implements IServiceImpl {

	/**
	 * -1847130223284056620L
	 */
	private static final long serialVersionUID = -1847130223284056620L;
	public static void main(String[] args) 
	{
		RobotSource robotSource = new RobotSource();
		
		Endpoint.publish("http://localhost:8888/ws/server", robotSource);
		System.out.println("Service is published!\n" + robotSource.getConfig().toString());
	}

	SourceConfig config;	
	
	public SourceConfig getConfig()
	{
		return config;
	}
	/**
	 * 
	 */
	public RobotSource() {
		LoadConfigFile("config.xml");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.server.interfaces.IServiceImpl#getSystemContentTypes()
	 */
	@Override
	public ContentTypeRoot getSystemContentTypeRoot() 
	{
		return config.getContentTypeRoot();
	}
	/**
	 * Load a configuration file
	 * @param path
	 */
	private void LoadConfigFile(String path) 
	{
		this.config = new SourceConfig();
		this.config.setDestinationPath(path);
		
		File file = new File(path);		
		InputStream is;
		
		try 
		{
			is = new FileInputStream(file);
			this.config.unmarshal(is);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (JAXBException e) 
		{
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.server.interfaces.IServiceImpl#getTargetServerInfo(java.util.UUID)
	 */
	@Override
	public DataSource getTargetDataSource(String serverInfoID) 
	{		
		UUID dataSourceUUID = UUID.fromString(serverInfoID);
		
		for(DataSource dataSource : this.config.getDataSource())
		{
			if(dataSource.getUuid().equals(dataSourceUUID))
			{
				return dataSource;
			}
		}
		
		return new DataSource();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.server.interfaces.IServiceImpl#getServerInfoList()
	 */
	@Override
	public DataSourceList getDataSourceList()
	{
		DataSourceList dataSourceList = new DataSourceList();
		
		dataSourceList.setDateCreated(new Date());
		
		ArrayList<DataSourceRef> dataSourceRefList = new ArrayList<DataSourceRef>();
		Integer priority = 0;
		
		for(DataSource dataSource : this.config.getDataSource())
		{
			DataSourceRef dataSourceRef = new DataSourceRef();
			dataSourceRef.setLastUpdate(dataSource.getNTP());
			dataSourceRef.setServerInfoID(dataSource.getUuid());
			dataSourceRef.setServerInfoName(dataSource.getSourceName());
			dataSourceRef.setPriority(priority);
			
			dataSourceRefList.add(dataSourceRef);
			priority++;
		}
		
		dataSourceList.setDataSourceRef(dataSourceRefList);
		
		return dataSourceList;
	}
}