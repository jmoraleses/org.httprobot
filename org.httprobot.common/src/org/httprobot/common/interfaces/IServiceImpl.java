/**
 * 
 */
package org.httprobot.common.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.httprobot.common.config.DataSourceList;
import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.common.datatypes.DataSource;

/**
 * Service end point implementation interface. Inherits {@link IListener}. 
 * @author joan
 */
@WebService
@SOAPBinding(style=Style.RPC)
public interface IServiceImpl
{
	@WebMethod ContentTypeRoot getSystemContentTypeRoot();
	@WebMethod DataSource getTargetDataSource(String serverInfoUIID);
	@WebMethod DataSourceList getDataSourceList();
}