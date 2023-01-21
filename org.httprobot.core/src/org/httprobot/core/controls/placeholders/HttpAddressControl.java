package org.httprobot.core.controls.placeholders;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.FieldRef;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.placeholders.HttpAddress;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.PlaceholderData;
import org.httprobot.core.controls.interfaces.impl.IPlaceholderControlImpl;
import org.httprobot.core.controls.placeholders.interfaces.IControlHttpAddressListener;

/**
 * {@link HttpAddress} message control class. 
 * Inherits {@link AbstractPlaceholderControl}.
 * <br>
 * @author Joan
 *
 */
@XmlRootElement
public class HttpAddressControl 
		extends AbstractPlaceholderControl<HttpAddress, IControlHttpAddressListener> 
		implements IPlaceholderControlImpl{

	/**
	 * 6869667376460474357L
	 */
	private static final long serialVersionUID = 6869667376460474357L;	
	/**
	 *  Web request place holder control class constructor.
	 */
	public HttpAddressControl()
	{
		super();

		//Initialize using data
		this.message = new HttpAddress();
		
		//Associate message to control.
		this.addCommandOutputListener(this.message);
	}
	/**
	 * Web request place holder control class constructor.
	 * @param parent {@link IControlListener} the parent
	 */
	public HttpAddressControl(IControlHttpAddressListener parent, HttpAddress httpAddress) 
	{
		super(parent, httpAddress);

		//Initialize using data
		this.message = new HttpAddress();
		
		//Associate message to control.
		this.addCommandOutputListener(this.message);
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Initialize(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		//Call AbstractPlaceholder control method
		super.Initialize(sender, e);
		
		if(e.getMessage() != null)
		{
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Load(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		//Call AbstractPlaceholder control method
		super.Load(sender, e);
		
		if(e.getMessage() != null)
		{
			//TODO Set controlled data for HttpRequest specific messages
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Change(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		//Call AbstractPlaceholder control method
		super.Read(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Render(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		//Call AbstractPlaceholder control method
		super.Render(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		//Call AbstractPlaceholder control method
		super.Read(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Write(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		//Call AbstractPlaceholder control method
		super.Write(sender, e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControlHttpAddress_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControlHttpAddress_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControlHttpAddress_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControlHttpAddress_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControlHttpAddress_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControlHttpAddress_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControlFieldRef_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControlFieldRef_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		try
		{
			// Set loaded FieldRef
			this.message.setFieldRef(FieldRef.class.cast(e.getMessage()));
		}
		catch (ClassCastException ex1) 
		{
			CliTools.ThrowInconsistentMessageException(this,
					"\nHttpAddressControl.OnControlFieldRef_Read: FieldRef message expected");
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControlFieldRef_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControlFieldRef_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControlFieldRef_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#OnControlFieldRef_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#put(org.httprobot.core.common.Enums.PlaceholderData, java.lang.Object)
	 */
	@Override
	public Object put(PlaceholderData key, Object value)
	{
		return super.put(key, value);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.placeholders.AbstractPlaceholderControl#remove(java.lang.Object)
	 */
	@Override
	public Object remove(Object key)
	{
		return super.remove(key);
	}
	
}