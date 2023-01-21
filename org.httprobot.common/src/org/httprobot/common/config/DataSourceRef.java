/**
 * 
 */
package org.httprobot.common.config;

import java.util.Date;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.Config;

/**
 * @author joan
 *
 */
@XmlRootElement
public class DataSourceRef extends Config {

	/**
	 * -1268026950016900833L
	 */
	private static final long serialVersionUID = -1268026950016900833L;

	private UUID serverInfoID;
	private String serverInfoName;
	private Integer priority;
	private Date lastUpdate;
	
	/**
	 * 
	 */
	public DataSourceRef() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the serverInfoID
	 */
	public UUID getServerInfoID() {
		return serverInfoID;
	}

	/**
	 * @param serverInfoID the serverInfoID to set
	 */
	@XmlElement
	public void setServerInfoID(UUID serverInfoID) {
		this.serverInfoID = serverInfoID;
	}

	/**
	 * @return the serverInfoName
	 */
	public String getServerInfoName() {
		return serverInfoName;
	}

	/**
	 * @param serverInfoName the serverInfoName to set
	 */
	@XmlElement
	public void setServerInfoName(String serverInfoName) {
		this.serverInfoName = serverInfoName;
	}

	/**
	 * @return the priority
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	@XmlElement
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**
	 * @return the lastUpdate
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	@XmlElement
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	
}