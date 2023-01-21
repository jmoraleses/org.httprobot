/**
 * 
 */
package org.httprobot.core.contents.solr;

import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import org.apache.http.client.HttpClient;
import org.apache.solr.client.solrj.ResponseParser;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.NamedList;
import org.httprobot.common.definitions.Enums.SolrConnectionStatus;
import org.httprobot.common.exceptions.ContentException;
import org.httprobot.core.contents.events.SolrConnectionEventArgs;
import org.httprobot.core.contents.interfaces.ISolrConnectionListener;

/**
 * @author joan
 *
 */
public class SolrConnection extends HttpSolrServer 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5276053184094800547L;

	Vector<ISolrConnectionListener> connectionListeners;

	SolrConnectionStatus status;
	
	public SolrConnectionStatus getStatus()
	{
		return this.status;
	}
	public void setStatus(SolrConnectionStatus status)
	{
		this.status = status;
		
		switch (status)
		{
			case CONNECTED:
				FireSolrConnectionEvent(new SolrConnectionEventArgs(this, SolrConnectionStatus.CONNECTED));
				break;
			case DISCONNECTED:
				FireSolrConnectionEvent(new SolrConnectionEventArgs(this, SolrConnectionStatus.DISCONNECTED));
				break;
			case ERROR:
				FireSolrConnectionEvent(new SolrConnectionEventArgs(this, SolrConnectionStatus.ERROR));
				break;
		default:
			break;
		}
	}
	
	public synchronized void addConnectionListener(ISolrConnectionListener listener)
	{
		this.connectionListeners.add(listener);
	}
	public synchronized void removeConnectionListener(ISolrConnectionListener listener)
	{
		this.connectionListeners.remove(listener);
	}
	
	protected void FireSolrConnectionEvent(SolrConnectionEventArgs e)
	{	
		try 
		{
			for(ISolrConnectionListener listener : this.connectionListeners)
			{
				switch (e.getStatus()) {
					case CONNECTED:					
						listener.OnContentConnection_Started(this, e);					
						break;
					case DISCONNECTED:
						listener.OnContentConnection_Finished(this, e);
						break;
					case ERROR:
						listener.OnContentConnection_Error(this, e);
						break;
					default:
						break;
				}
			}
		} 
		catch (ContentException e1) 
		{
			e1.printStackTrace();
		}
	}
	
	public SolrConnection(String baseURL)
	{
		super(baseURL, null, null);
	}	
	public SolrConnection(String baseURL, HttpClient client)
	{
		super(baseURL, client, null);
	}	
	public SolrConnection(String baseURL, HttpClient client,
			ResponseParser parser) {
		super(baseURL, client, parser);
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see org.apache.solr.client.solrj.impl.HttpSolrServer#request(org.apache.solr.client.solrj.SolrRequest)
	 */
	@Override
	public NamedList<Object> request(SolrRequest request)
			throws SolrServerException, IOException {
		// TODO Auto-generated method stub
		return super.request(request);
	}
	/* (non-Javadoc)
	 * @see org.apache.solr.client.solrj.impl.HttpSolrServer#request(org.apache.solr.client.solrj.SolrRequest, org.apache.solr.client.solrj.ResponseParser)
	 */
	@Override
	public NamedList<Object> request(SolrRequest request, ResponseParser processor) 
	{

		try 
		{
			return super.request(request, processor);
		}
		catch (SolrServerException e)
		{
			e.printStackTrace();
			this.setStatus(SolrConnectionStatus.ERROR);
			return null;
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			this.setStatus(SolrConnectionStatus.ERROR);
			return null;
		}
	}
	/* (non-Javadoc)
	 * @see org.apache.solr.client.solrj.impl.HttpSolrServer#add(java.util.Iterator)
	 */
	@Override
	public UpdateResponse add(Iterator<SolrInputDocument> docIterator)
			throws SolrServerException, IOException {
		// TODO Auto-generated method stub
		return super.add(docIterator);
	}
	
	
}
