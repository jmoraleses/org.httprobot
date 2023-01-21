/**
 * 
 */
package org.httprobot.server.datasources;

import java.util.Date;

import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.common.datatypes.DataSource;
import org.httprobot.common.datatypes.DocumentRoot;
import org.httprobot.common.params.ServerUrl;
import org.httprobot.common.params.StartUrl;
import org.httprobot.common.unit.Action;

/**
 * @author joan
 *
 */
public class TestSource extends DataSource {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7554660931853643532L;

	/* (non-Javadoc)
	 * @see org.httprobot.common.datatypes.DataSource#getServerName()
	 */
	@Override
	public String getSourceName() {
		// TODO Auto-generated method stub
		return "hola";
	}
	
	

	/* (non-Javadoc)
	 * @see org.httprobot.common.datatypes.DataSource#getServerUrl()
	 */
	@Override
	public ServerUrl getServerUrl() {
		ServerUrl serverUrl = new ServerUrl();
		serverUrl.setUuid(null);
		serverUrl.setValue("http://www.divxtotal.com/");
		serverUrl.setParamName("[@server_url]");		
		return serverUrl;
	}



	/* (non-Javadoc)
	 * @see org.httprobot.common.datatypes.DataSource#getAction()
	 */
	@Override
	public Action getAction() {
		// TODO Auto-generated method stub
		return super.getAction();
	}



	/* (non-Javadoc)
	 * @see org.httprobot.common.datatypes.DataSource#getContentTypeRef()
	 */
	@Override
	public ContentTypeRef getContentTypeRef() {
		// TODO Auto-generated method stub
		return super.getContentTypeRef();
	}



	/* (non-Javadoc)
	 * @see org.httprobot.common.datatypes.DataSource#getDocumentRoot()
	 */
	@Override
	public DocumentRoot getDocumentRoot() {
		// TODO Auto-generated method stub
		return super.getDocumentRoot();
	}



	/* (non-Javadoc)
	 * @see org.httprobot.common.datatypes.DataSource#getLastUpdate()
	 */
	@Override
	public Date getNTP() {
		//get current date time with Date()
		Date date = new Date();
		return date;
	}



	/* (non-Javadoc)
	 * @see org.httprobot.common.datatypes.DataSource#getStartUrl()
	 */
	@Override
	public StartUrl getStartUrl() {
		StartUrl startUrl = new StartUrl();
		startUrl.setUuid(null);
		startUrl.setInherited(true);
		startUrl.setValue("peliculas/");
		startUrl.setParamName("[@start_url]");
		return startUrl;
	}



	/**
	 * 
	 */
	public TestSource() {
		this.setUuid(null);
	}

}
