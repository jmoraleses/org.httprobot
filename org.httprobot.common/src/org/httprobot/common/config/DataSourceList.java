/**
 * 
 */
package org.httprobot.common.config;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.Config;

/**
 * @author joan
 *
 */
@XmlRootElement
public class DataSourceList extends Config
{
	/**
	 * 3249256274532113123L
	 */
	private static final long serialVersionUID = 3249256274532113123L;

	private Date dateCreated;	
	private ArrayList<DataSourceRef> dataSourceRef;
	/**
	 * 
	 */
	public DataSourceList() {
		this.dataSourceRef = new ArrayList<DataSourceRef>();
	}
	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}
	/**
	 * @param dateCreated the dateCreated to set
	 */
	@XmlAttribute
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	/**
	 * @return the availableServers
	 */
	public ArrayList<DataSourceRef> getDataSourceRef() {
		return dataSourceRef;
	}
	/**
	 * @param availableServers the availableServers to set
	 */
	public void setDataSourceRef(ArrayList<DataSourceRef> availableServers) {
		this.dataSourceRef = availableServers;
	}
}