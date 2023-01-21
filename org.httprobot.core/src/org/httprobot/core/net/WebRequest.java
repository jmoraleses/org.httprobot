package org.httprobot.core.net;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.httprobot.core.events.HttpEventArgs;
import org.httprobot.core.interfaces.IHttpHandlerImpl;

public class WebRequest implements IHttpHandlerImpl
{
	private String httpAddress = null;
	private HttpGet httpGet = null;
	private HttpPost httpPost = null;
	private HttpResponse httpResponse = null;
	private HttpEntity httpEntity = null;
	private List<Cookie> cookies = null;	
	static HttpHandler httpHandler = null;	

	/**
	 * @return the httpAddress
	 */
	public String getHttpAddress() {
		return httpAddress;
	}
	/**
	 * @return the httpGet
	 */
	public HttpGet getHttpGet() {
		return httpGet;
	}
	/**
	 * @return the httpPost
	 */
	public HttpPost getHttpPost() {
		return httpPost;
	}
	/**
	 * @return the httpResponse
	 */
	public HttpResponse getHttpResponse() {
		return httpResponse;
	}
	/**
	 * @return the httpEntity
	 */
	public HttpEntity getHttpEntity() {
		return httpEntity;
	}
	/**
	 * @return the cookies
	 */
	public List<Cookie> getCookies() {
		return cookies;
	}	
	
	public WebRequest(String httpAddress)
	{
		this.httpAddress = httpAddress;
	}
	
	/**
	 * Sends request via GET method.
	 */
	public void doGet(String URI)
	{
		this.httpGet = new HttpGet(URI);
	}
	/**
	 * Sends request via POST method.
	 */
	public void doPost(String URI)
	{
		this.httpPost = new HttpPost(URI);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IHttpHandlerImpl#OnGetResponseReceived(java.lang.Object, org.httprobot.common.events.HttpEventArgs)
	 */
	@Override
	public void OnGetResponseReceived(Object sender, HttpEventArgs e) {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IHttpHandlerImpl#OnPostResponseReceived(java.lang.Object, org.httprobot.common.events.HttpEventArgs)
	 */
	@Override
	public void OnPostResponseReceived(Object sender, HttpEventArgs e) {
	}
}	
