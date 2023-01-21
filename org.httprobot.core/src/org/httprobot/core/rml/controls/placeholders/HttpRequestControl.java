package org.httprobot.core.rml.controls.placeholders;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.datatypes.placeholders.HttpRequest;

/**
 * @author Joan
 *
 */
@XmlRootElement
public class HttpRequestControl extends MainPlaceholderControl<HttpRequest> 
{
	/**
	 * 6869667376460474357L
	 */
	private static final long serialVersionUID = 6869667376460474357L;	
	/**
	 *  Web request place holder control class constructor.
	 */
	public HttpRequestControl()
	{
		super();
	}
	/**
	 * Web request place holder control class constructor.
	 * @param parent {@link IRmlListener} the parent
	 */
	public HttpRequestControl(IRmlListener parent, HttpRequest web_request) 
	{
		super(parent, web_request);
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		super.OnControlInit(sender, e);
		
		if(e.getMessage() != null)
		{
			//Initialize using data
			this.placeholder = new HttpRequest();
			
			//Associate message to control.
			this.addCommandOutputListener(this.placeholder);			
			
			//Set inherited data.
			this.setUuid(e.getMessage().getUuid());
			this.setInherited(e.getMessage().getInherited());
			this.setRuntimeOptions(e.getMessage().getRuntimeOptions());
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnControlLoaded(sender, e);
		
		if(e.getMessage() != null)
		{
			//TODO Set controlled data for HttpRequest specific messages
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		super.OnControlRead(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		super.OnControlRendered(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnControlRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		super.OnControlRead(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnControlWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnControlWrite(sender, e);
	}
}