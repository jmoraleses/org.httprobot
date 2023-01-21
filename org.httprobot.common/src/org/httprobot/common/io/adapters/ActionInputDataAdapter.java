/**
 * 
 */
package org.httprobot.common.io.adapters;

import java.util.Dictionary;
import java.util.Enumeration;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.httprobot.common.managers.ActionInputData;
import org.httprobot.common.managers.action.ActionInputDataRow;

import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * @author joan
 *
 */
public class ActionInputDataAdapter extends XmlAdapter<Dictionary<Object, HtmlPage>, ActionInputData> {

	/* (non-Javadoc)
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.Object)
	 */
	@Override
	public ActionInputData unmarshal(Dictionary<Object, HtmlPage> v) throws Exception 
	{
		ActionInputData actionInputData = new ActionInputData();
		
		Enumeration<Object> vKeys = v.keys();
		
		while(vKeys.hasMoreElements())
		{
			Object vKey = vKeys.nextElement();
			
			ActionInputDataRow actionInputDataRow = new ActionInputDataRow();
			
			try
			{
				WebRequest webRequest = WebRequest.class.cast(vKey);
				actionInputDataRow.setHttpAddress(webRequest.getUrl().toString());
				
				HtmlPage htmlPage = HtmlPage.class.cast(v.get(vKey));
				actionInputDataRow.setHtmlTitle(htmlPage.getTitleText());
				actionInputDataRow.setHtmlTextContent(htmlPage.getTextContent());
			}
			catch(ClassCastException ex)
			{
				actionInputDataRow.setHttpAddress(ex.getMessage());
				actionInputDataRow.setHtmlTitle(null);
				actionInputDataRow.setHtmlTextContent(null);
			}
			
			actionInputData.getActionInputDataRow().add(actionInputDataRow);
		}
		
		return actionInputData;
	}

	/* (non-Javadoc)
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
	 */
	@Override
	public Dictionary<Object, HtmlPage> marshal(ActionInputData v) throws Exception 
	{
		return null;
	}
}