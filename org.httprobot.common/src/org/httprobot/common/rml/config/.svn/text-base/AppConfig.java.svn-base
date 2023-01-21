package org.httprobot.common.rml.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.rml.Rml;

@XmlRootElement
public class AppConfig extends Rml 
{
	private String configFilePath = null;
	private String logFilePath = null;
	private String rmlManagerFilePath = null;
	private String inetManagerFilePath = null;
	private String dataManagerFilePath = null;
	private String uiManagerFilePath = null;
//	private String proxy 
	
	public AppConfig()
	{
		
	}
	/**
	 * @return the configFilePath
	 */
	public String getConfigFilePath() {
		return configFilePath;
	}
	/**
	 * @return the dataManagerFilePath
	 */
	public String getDataManagerFilePath() {
		return dataManagerFilePath;
	}
	/**
	 * @return the inetManagerFilePath
	 */
	public String getInetManagerFilePath() {
		return inetManagerFilePath;
	}
	/**
	 * @return the logFilePath
	 */
	public String getLogFilePath() {
		return logFilePath;
	}	
	/**
	 * @return the rmlManagerFilePath
	 */
	public String getRmlManagerFilePath() {
		return rmlManagerFilePath;
	}
	/**
	 * @return the uiManagerFilePath
	 */
	public String getUiManagerFilePath() {
		return uiManagerFilePath;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e)
			throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
		this.setConfigFilePath(((AppConfig)e.getRml()).getConfigFilePath());
		this.setLogFilePath(((AppConfig)e.getRml()).getLogFilePath());
	}
	/**
	 * @param configFilePath the configFilePath to set
	 */
	@XmlElement
	public void setConfigFilePath(String configFilePath) {
		this.configFilePath = configFilePath;
	}
	/**
	 * @param dataManagerFilePath the dataManagerFilePath to set
	 */
	@XmlElement
	public void setDataManagerFilePath(String dataManagerFilePath) {
		this.dataManagerFilePath = dataManagerFilePath;
	}
	/**
	 * @param inetManagerFilePath the inetManagerFilePath to set
	 */
	@XmlElement
	public void setInetManagerFilePath(String inetManagerFilePath) {
		this.inetManagerFilePath = inetManagerFilePath;
	}
	/**
	 * @param logFilePath the logFilePath to set
	 */
	@XmlElement
	public void setLogFilePath(String logFilePath) {
		this.logFilePath = logFilePath;
	}
	/**
	 * @param rmlManagerFilePath the rmlManagerFilePath to set
	 */
	@XmlElement
	public void setRmlManagerFilePath(String rmlManagerFilePath) {
		this.rmlManagerFilePath = rmlManagerFilePath;
	}
	/**
	 * @param uiManagerFilePath the uiManagerFilePath to set
	 */
	@XmlElement
	public void setUiManagerFilePath(String uiManagerFilePath) {
		this.uiManagerFilePath = uiManagerFilePath;
	}
}