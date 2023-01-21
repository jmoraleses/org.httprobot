package org.httprobot.common.config;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.Config;
import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.common.datatypes.DataSource;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;


/**
 * Configuration message class. Inherits {@link Config}.
 * @author Joan
 * 
 */
@XmlRootElement
public class Configuration extends Config
{	
	/**
	 * 5862799292093094020L
	 */
	private static final long serialVersionUID = 5862799292093094020L;
	
	private ContentTypeRoot contentTypeRoot;
	private ArrayList<DataSource> dataSource;
	/**
	 * AppConfig class constructor.
	 */
	public Configuration()
	{		
		super();
	}
	/**
	 * @return the contentTypeRoot
	 */
	public ContentTypeRoot getContentTypeRoot() 
	{
		return contentTypeRoot;
	}
	/**
	 * Gets the array of servers data
	 * @return {@link ArrayList} of {@link DataSource}
	 */
	public ArrayList<DataSource> getDataSource() 
	{
		if(this.dataSource == null)
		{
			this.dataSource = new ArrayList<DataSource>();
			return this.dataSource;
		}
		else
		{
			return this.dataSource;
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
		this.setDataSource(((Configuration)e.getRml()).getDataSource());
		this.setContentTypeRoot(((Configuration)e.getRml()).getContentTypeRoot());
	}
	/**
	 * @param contentTypeRoot the contentTypeRoot to set
	 */
	public void setContentTypeRoot(ContentTypeRoot contentTypeRoot) {
		this.contentTypeRoot = contentTypeRoot;
	}
	/**
	 * Sets the server data.
	 * @param serverInfo {@link ArrayList} of {@link DataSource}
	 */
	public void setDataSource(ArrayList<DataSource> serverInfo) {
		this.dataSource = serverInfo;
	}
}