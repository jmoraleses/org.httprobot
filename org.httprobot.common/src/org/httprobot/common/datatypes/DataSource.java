package org.httprobot.common.datatypes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.DataType;
import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.params.ServerUrl;
import org.httprobot.common.params.StartUrl;
import org.httprobot.common.unit.Action;

/**
 * Data source object class. Inherits {@link DataType}.
 * @author Joan  
 */
@XmlRootElement
public class DataSource 
		extends DataType {
	
	/**
	 * 6784877235043676956L
	 */
	private static final long serialVersionUID = 6784877235043676956L;
	
	private Action Action = null;
	private ContentTypeRef ContentTypeRef = null;
	private DocumentRoot DocumentRoot = null;
	private String SourceName = null;
	private ServerUrl ServerUrl = null;
	private StartUrl StartUrl = null;
	
	/**
	 * Data source class constructor
	 */
	public DataSource() { }	
	/**
	 * @return
	 */
	public Action getAction() {
		return Action;
	}
	/**
	 * @return the contentTypes
	 */
	public ContentTypeRef getContentTypeRef() {
		return ContentTypeRef;
	}
	/**
	 * @return
	 */
	public DocumentRoot getDocumentRoot() {
		return DocumentRoot;
	}
	/**
	 * @return
	 */
	public String getSourceName() {
		return SourceName;
	}
	/**
	 * @return
	 */
	public ServerUrl getServerUrl() {
		return ServerUrl;
	}
	/**
	 * @return
	 */
	public StartUrl getStartUrl() {
		return StartUrl;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.RML#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		super.OnObjectUnmarshalled(sender, e);
		
		setSourceName(((DataSource)e.getRml()).getSourceName());
		setServerUrl(((DataSource)e.getRml()).getServerUrl());
		setStartUrl(((DataSource)e.getRml()).getStartUrl());
		setDocumentRoot(((DataSource)e.getRml()).getDocumentRoot());
		setContentTypeRef(((DataSource)e.getRml()).getContentTypeRef());
	}
	/**
	 * @param action
	 */
	@XmlElement
	public void setAction(Action action) {
		Action = action;
	}	
	/**
	 * @param contentTypeRef the content type reference to set
	 */
	@XmlElement
	public void setContentTypeRef(ContentTypeRef contentTypeRef) {
		this.ContentTypeRef = contentTypeRef;
	}
	/**
	 * @param Steps
	 */
	@XmlElement
	public void setDocumentRoot(DocumentRoot Steps) {
		this.DocumentRoot = Steps;
	}
	/**
	 * @param SourceName
	 */
	@XmlElement
	public void setSourceName(String SourceName) {
		this.SourceName = SourceName;
	}
	/**
	 * @param ServerUrl
	 */
	public void setServerUrl(ServerUrl ServerUrl) {
		this.ServerUrl = ServerUrl;
	}
	/**
	 * @param StartUrl
	 */
	public void setStartUrl(StartUrl StartUrl) {
		this.StartUrl = StartUrl;
	}
}