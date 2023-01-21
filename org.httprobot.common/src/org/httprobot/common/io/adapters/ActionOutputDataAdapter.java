/**
 * 
 */
package org.httprobot.common.io.adapters;

import java.util.Dictionary;
import java.util.Enumeration;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.httprobot.common.managers.ActionOutputData;
import org.httprobot.common.managers.action.ActionOutputDataRow;
import org.httprobot.common.managers.action.ResultHtmlPage;

import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * @author joan
 *
 */
public class ActionOutputDataAdapter extends XmlAdapter<Dictionary<HtmlPage, Dictionary<WebRequest, HtmlPage>>, ActionOutputData> 
{

	/* (non-Javadoc)
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.Object)
	 */
	@Override
	public ActionOutputData unmarshal(Dictionary<HtmlPage, Dictionary<WebRequest, HtmlPage>> v) throws Exception 
	{
		ActionOutputData actionOutputData = new ActionOutputData();
		
		Enumeration<HtmlPage> htmlPageKeys = v.keys();
		
		while(htmlPageKeys.hasMoreElements())
		{
			//Get the key
			HtmlPage sourcePage = htmlPageKeys.nextElement();
			
			//Initialize row
			ActionOutputDataRow actionOutputDataRow = new ActionOutputDataRow();
			
			//Get the values
			Dictionary<WebRequest, HtmlPage> resultPages = v.get(sourcePage);			
			Enumeration<WebRequest> webRequestResults = resultPages.keys();
			
			//Iterate throw result values
			while(webRequestResults.hasMoreElements())
			{
				//Get the key
				WebRequest webRequestResult = webRequestResults.nextElement();
				
				//Initialize row
				ResultHtmlPage resultHtmlPage = new ResultHtmlPage();
				
				//Get the value
				HtmlPage htmlPageResult = resultPages.get(webRequestResult);
				
				//Translate results
				resultHtmlPage.setHttpAddress(webRequestResult.getUrl().toString());
				resultHtmlPage.setHtmlTitle(htmlPageResult.getTitleText());
				resultHtmlPage.setHtmlTextContent(htmlPageResult.getTextContent());
				
				//Store data
				actionOutputDataRow.getResultHtmlPages().add(resultHtmlPage);
			}
			
			//Store data
			actionOutputData.getActionOutputDataRow().add(actionOutputDataRow);
		}
		
		return actionOutputData;
	}
	/* (non-Javadoc)
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
	 */
	@Override
	public Dictionary<HtmlPage, Dictionary<WebRequest, HtmlPage>> marshal(ActionOutputData v) throws Exception {
		return null;
	}
}