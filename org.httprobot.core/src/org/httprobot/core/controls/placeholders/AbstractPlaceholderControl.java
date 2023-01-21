/**
 * 
 */
package org.httprobot.core.controls.placeholders;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.Placeholder;
import org.httprobot.common.RML;
import org.httprobot.common.contents.FieldRef;
import org.httprobot.common.definitions.Enums.ControlEventType;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.events.ControlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IControlListener;
import org.httprobot.common.placeholders.operators.Contains;
import org.httprobot.common.placeholders.operators.Equals;
import org.httprobot.common.placeholders.operators.Remove;
import org.httprobot.common.placeholders.operators.Replace;
import org.httprobot.common.placeholders.operators.Split;
import org.httprobot.common.placeholders.operators.Substring;
import org.httprobot.common.placeholders.operators.TryParse;
import org.httprobot.common.tools.CliTools;
import org.httprobot.core.common.Enums.ContentData;
import org.httprobot.core.common.Enums.OperatorData;
import org.httprobot.core.common.Enums.PlaceholderData;
import org.httprobot.core.controls.PlaceholderControl;
import org.httprobot.core.controls.contents.FieldRefControl;
import org.httprobot.core.controls.contents.interfaces.IControlFieldRefListener;
import org.httprobot.core.controls.html.interfaces.IControlPageListener;
import org.httprobot.core.controls.interfaces.impl.IControlImpl;
import org.httprobot.core.controls.operators.ContainsControl;
import org.httprobot.core.controls.operators.EqualsControl;
import org.httprobot.core.controls.operators.RemoveControl;
import org.httprobot.core.controls.operators.ReplaceControl;
import org.httprobot.core.controls.operators.SplitControl;
import org.httprobot.core.controls.operators.SubstringControl;
import org.httprobot.core.controls.operators.TryParseControl;
import org.httprobot.core.controls.operators.interfaces.IControlContainsListener;
import org.httprobot.core.controls.operators.interfaces.IControlEqualsListener;
import org.httprobot.core.controls.operators.interfaces.IControlRemoveListener;
import org.httprobot.core.controls.operators.interfaces.IControlReplaceListener;
import org.httprobot.core.controls.operators.interfaces.IControlSplitListener;
import org.httprobot.core.controls.operators.interfaces.IControlSubstringListener;
import org.httprobot.core.controls.operators.interfaces.IControlTryParseListener;
import org.httprobot.core.interfaces.IDataMappingImpl;

/**
 * Abstract {@link Placeholder} message control implementation class. Inherits {@link PlaceholderControl}.
 * <br>
 * It's {@link IControlReplaceListener}, {@link IControlSubstringListener}, {@link IControlSplitListener},
 * {@link IControlContainsListener}, {@link IControlEqualsListener}, {@link IControlRemoveListener}, 
 * {@link IControlTryParseListener}, {@link IControlPageListener} and {@link IControlSelectListener}.
 * <br>
 * @author joan
 *
 * @param <TPlacheholderMessage> The {@link Placeholder} message type.
 * @param <IListener> The {@link Placeholder} message control listener type.
 */
@XmlTransient
public abstract class AbstractPlaceholderControl
	<TPlacheholderMessage extends Placeholder, IListener extends IControlListener>
		extends PlaceholderControl<TPlacheholderMessage, IListener> 
		implements IControlReplaceListener, IControlSubstringListener, IControlSplitListener,
			IControlContainsListener, IControlEqualsListener, IControlRemoveListener, IControlTryParseListener,
			IControlFieldRefListener, IDataMappingImpl<PlaceholderData, Object> {
	
	/**
	 * -9015911631674313159L
	 */
	private static final long serialVersionUID = -9015911631674313159L;
	
	/**
	 * {@link Contains} operator message control.
	 */
	protected ContainsControl containsControl;	
	/**
	 * {@link Equals} operator message control.
	 */
	protected EqualsControl equalsControl;
	/**
	 * {@link Remove} operator message control.
	 */
	protected RemoveControl removeControl;
	/**
	 * {@link Replace} operator message control.
	 */
	protected ReplaceControl replaceControl;
	/**
	 * {@link Split} operator message control.
	 */
	protected SplitControl splitControl;
	/**
	 * {@link Substring} operator message control.
	 */
	protected SubstringControl substringControl;
	/**
	 * {@link TryParse} operator message control.
	 */
	protected TryParseControl tryParseControl;
	/**
	 * {@link FieldRef} content message control.
	 */
	protected FieldRefControl fieldRefControl;
	/**
	 * The controlled data.
	 */
	Map<PlaceholderData, Object> data;
	
	/**
	 * Abstract {@link Placeholder} control default class constructor.
	 */
	public AbstractPlaceholderControl() 
	{
		super();
	}
	/**
	 * Abstract {@link Placeholder} controller class constructor.
	 * @param parent {@link IControlListener} the parent
	 * @param control {@link Placeholder} the place holder message
	 */
	public AbstractPlaceholderControl(IListener parent, TPlacheholderMessage placeholder)
	{
		super(parent, placeholder);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.PlaceholderControl#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Change(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.PlaceholderControl#OnControl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Initialize(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getMessage() != null)
		{
			try
			{
				Placeholder placeholder = Placeholder.class.cast(e.getMessage());
								
				if(placeholder.getFieldRef() != null)
				{
					//Get field reference message
					FieldRef fieldRef = placeholder.getFieldRef();
					
					//Initialize field reference message control
					this.fieldRefControl = new FieldRefControl(this, fieldRef);
					
					//Associate child control to parent.
					this.addCommandOutputListener(this.fieldRefControl);
					
					//Store control.
					this.childControls.add(this.fieldRefControl);
				}
				if(placeholder.getContains() != null)
				{					
					Contains contains = placeholder.getContains();
					
					//If contains message not null instantiate Contains message control.
					this.containsControl = new ContainsControl(this, contains);

					//Associate child control to parent.
					this.addCommandOutputListener(this.containsControl);

					//Store control.
					this.childControls.add(this.fieldRefControl);
				}
				if(placeholder.getEquals() != null)
				{
					Equals equals = placeholder.getEquals();
					
					//If Equals message not null instantiate Equals message control.
					this.equalsControl = new EqualsControl(this, equals);

					//Associate child control to parent.			
					this.addCommandOutputListener(this.equalsControl);

					//Store control.
					this.childControls.add(this.fieldRefControl);
				}
				if(placeholder.getRemove() != null)
				{
					Remove remove = placeholder.getRemove();
					
					//If Remove message not null instantiate Remove message control.
					this.removeControl = new RemoveControl(this, remove);

					//Associate child control to parent.
					this.addCommandOutputListener(this.removeControl);

					//Store control.
					this.childControls.add(this.removeControl);
				}
				if(placeholder.getReplace() != null)
				{
					Replace replace = placeholder.getReplace();
					
					//If Replace message not null instantiate Replace message control.
					this.replaceControl = new ReplaceControl(this, replace);
					
					//Associate child control to parent.
					this.addCommandOutputListener(this.replaceControl);

					//Store control.
					this.childControls.add(this.replaceControl);
				}
				if(placeholder.getSplit() != null)
				{
					Split split = placeholder.getSplit();
					
					//If Split message not null instantiate Split message control.
					this.splitControl = new SplitControl(this, split);
					
					//Associate child control to parent.
					this.addCommandOutputListener(this.splitControl);

					//Store control.
					this.childControls.add(this.splitControl);
				}
				if(placeholder.getSubstring() != null)
				{
					Substring substring = placeholder.getSubstring();
					
					//If Substring message not null instantiate Substring message control.
					this.substringControl = new SubstringControl(this, substring);
					
					//Associate child control to parent.
					this.addCommandOutputListener(this.substringControl);

					//Store control.
					this.childControls.add(this.substringControl);
				}
				if(placeholder.getTryParse() != null)
				{
					TryParse try_parse = placeholder.getTryParse();
					
					//If TryParse message not null instantiate TryParse message control.
					this.tryParseControl = new TryParseControl(this, try_parse);
					
					//Associate child control to parent.
					this.addCommandOutputListener(this.tryParseControl);
					
					//Store control.
					this.childControls.add(this.tryParseControl);
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"AbstractPlaceholderControl.OnControl_Init: Placeholder message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.PlaceholderControl#OnControl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Load(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if (e.getMessage() != null) 
		{
			try 
			{
				Placeholder placeholder = Placeholder.class.cast(e.getMessage());
				
				if(this.hasChildControls())
				{
					//Iterate through child controls.
					while(this.hasNext())
					{
						IControlImpl control = this.next();
						
						if(control.equals(this.fieldRefControl) ?
								placeholder.getFieldRef() != null ?
										control.getUuid().equals(placeholder.getFieldRef().getUuid()) 
										: false : false) {
							
							//FieldRef message control set corresponding FieldRef message
							FieldRef fieldRef = placeholder.getFieldRef();
							this.fieldRefControl.controlMessage(fieldRef);
						}
						if(control.equals(this.containsControl) ?
								placeholder.getContains() != null ?
										control.getUuid().equals(placeholder.getContains().getUuid()) 
										: false : false) {
							
							//Contains message control set corresponding Contains message
							Contains contains = placeholder.getContains();
							this.containsControl.controlMessage(contains);
						}
						if(control.equals(this.equalsControl) ?
								placeholder.getEquals() != null ?
										placeholder.getEquals().getUuid().equals(this.equalsControl.getUuid()) 
										: false : false) {
							
							//Equals message control set corresponding Equals message
							Equals contains = placeholder.getEquals();
							this.equalsControl.controlMessage(contains);
						}
						if(control.equals(this.removeControl) ?
								placeholder.getRemove() != null ?
										placeholder.getRemove().getUuid().equals(this.removeControl.getUuid()) 
										: false : false) {
							
							//Remove message control set corresponding Remove message
							Remove remove = placeholder.getRemove();
							this.removeControl.controlMessage(remove);
						}
						if(control.equals(this.replaceControl) ?
								placeholder.getReplace() != null ?
								placeholder.getReplace().getUuid().equals(this.replaceControl.getUuid()) 
								: false : false) {
							
							//Replace message control set corresponding Replace message
							Replace replace = placeholder.getReplace();
							this.replaceControl.controlMessage(replace);
						}
						if(control.equals(this.splitControl) ? 
								placeholder.getSplit() != null ?
										placeholder.getSplit().getUuid().equals(this.splitControl.getUuid()) 
										: false : false) {
							
							//Split message control set corresponding Split message
							Split split = placeholder.getSplit();
							this.splitControl.controlMessage(split);
						}
						if(control.equals(this.substringControl) ?
								placeholder.getSubstring() != null ?
										placeholder.getSubstring().getUuid().equals(this.substringControl.getUuid()) 
										: false : false) {
							
							//Substring message control set corresponding Substring message
							Substring substring = placeholder.getSubstring();
							this.substringControl.controlMessage(substring);
						}
						if(control.equals(tryParseControl) ?
								placeholder.getTryParse() != null ?
										placeholder.getTryParse().getUuid().equals(this.tryParseControl.getUuid()) 
										: false : false) {
							
							//TryParse message control set corresponding TryParse message
							TryParse tryParse = placeholder.getTryParse();
							this.tryParseControl.controlMessage(tryParse);
						}
					}
				}
			}
			catch (ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this,
						"AbstractPlaceholderControl.OnControl_Loaded: Placeholder message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.PlaceholderControl#OnControl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.PlaceholderControl#OnControl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Render(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.PlaceholderControl#OnControl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlContainsListener#OnControlContains_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContains_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlContainsListener#OnControlContains_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContains_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlContainsListener#OnControlContains_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContains_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.containsControl))
		{
			if(e.getMessage() instanceof Contains)
			{
				this.put(PlaceholderData.CONTAINS, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"AbstractPlaceholderControl.OnControlContains_Loaded: Contains message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlContainsListener#OnControlContains_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContains_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlContainsListener#OnControlContains_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContains_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlContainsListener#OnControlContains_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContains_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlEqualsListener#OnControlEquals_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlEquals_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlEqualsListener#OnControlEquals_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlEquals_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlEqualsListener#OnControlEquals_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlEquals_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		if(e.getSource().equals(this.equalsControl))
		{
			if(e.getMessage() instanceof Equals)
			{
				this.put(PlaceholderData.EQUALS, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this,
						"AbstractPlaceholderControl.OnControlEquals_Loaded: Equals message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlEqualsListener#OnControlEquals_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlEquals_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlEqualsListener#OnControlEquals_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlEquals_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlEqualsListener#OnControlEquals_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlEquals_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.PlaceholderControl#OnControlHtmlUnit_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderControl.OnControlHtmlUnit_Changed: Method not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.PlaceholderControl#OnControlHtmlUnit_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderControl.OnControlHtmlUnit_Init: Method not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.PlaceholderControl#OnControlHtmlUnit_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderControl.OnControlHtmlUnit_Changed: Method not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.PlaceholderControl#OnControlHtmlUnit_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderControl.OnControlHtmlUnit_Read: Method not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.PlaceholderControl#OnControlHtmlUnit_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderControl.OnControlHtmlUnit_Rendered: Method not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.PlaceholderControl#OnControlHtmlUnit_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHtmlUnit_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderControl.OnControlHtmlUnit_Write: Method not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.PlaceholderControl#OnControlHttpAddress_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderControl.OnControlHttpAddress_Changed: Method not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.PlaceholderControl#OnControlHttpAddress_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderControl.OnControlHttpAddress_Init: Method not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.PlaceholderControl#OnControlHttpAddress_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderControl.OnControlHttpAddress_Loaded: Method not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.PlaceholderControl#OnControlHttpAddress_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderControl.OnControlHttpAddress_Read: Method not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.PlaceholderControl#OnControlHttpAddress_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderControl.OnControlHttpAddress_Rendered: Method not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.PlaceholderControl#OnControlHttpAddress_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlHttpAddress_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		CliTools.ThrowNotImplementedException(this, 
				"AbstractPlaceholderControl.OnControlHttpAddress_Write: Method not implemented");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlRemoveListener#OnControlRemove_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlRemove_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlRemoveListener#OnControlRemove_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlRemove_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlRemoveListener#OnControlRemove_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlRemove_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.removeControl))
		{
			if(e.getMessage() instanceof Remove)
			{
				this.put(PlaceholderData.REMOVE, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this,
						"AbstractPlaceholderControl.OnControlRemove_Loaded: Remove message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlRemoveListener#OnControlRemove_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlRemove_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlRemoveListener#OnControlRemove_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlRemove_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlRemoveListener#OnControlRemove_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlRemove_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlReplaceListener#OnControlReplace_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlReplace_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlReplaceListener#OnControlReplace_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlReplace_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlReplaceListener#OnControlReplace_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlReplace_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {

		if(e.getSource().equals(this.replaceControl))
		{
			if(e.getMessage() instanceof Replace)
			{
				this.put(PlaceholderData.REPLACE, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this,
						"AbstractPlaceholderControl.OnControlReplace_Loaded: Replace message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlReplaceListener#OnControlReplace_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlReplace_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlReplaceListener#OnControlReplace_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlReplace_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlReplaceListener#OnControlReplace_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlReplace_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSplitListener#OnControlSplit_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSplit_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSplitListener#OnControlSplit_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSplit_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSplitListener#OnControlSplit_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSplit_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.splitControl))
		{
			if(e.getMessage() instanceof Split)
			{
				this.put(PlaceholderData.SPLIT, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this,
						"AbstractPlaceholderControl.OnControlSplit_Loaded: Split message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSplitListener#OnControlSplit_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSplit_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSplitListener#OnControlSplit_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSplit_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSplitListener#OnControlSplit_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSplit_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSubstringListener#OnControlSubstring_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSubstring_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSubstringListener#OnControlSubstring_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSubstring_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSubstringListener#OnControlSubstring_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSubstring_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.substringControl))
		{
			if(e.getMessage() instanceof Substring)
			{
				this.put(PlaceholderData.SUBSTRING, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this,
						"AbstractPlaceholderControl.OnSubstringLoaded: Substring message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSubstringListener#OnControlSubstring_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSubstring_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSubstringListener#OnControlSubstring_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSubstring_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlSubstringListener#OnControlSubstring_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSubstring_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlTryParseListener#OnControlTryParse_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlTryParseListener#OnControlTryParse_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlTryParseListener#OnControlTryParse_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.tryParseControl))
		{
			if(e.getMessage() instanceof TryParse)
			{
				this.put(PlaceholderData.TRY_PARSE, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this,
						"AbstractPlaceholderControl.OnControlTryParse_Loaded: TryParse message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlTryParseListener#OnControlTryParse_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlTryParseListener#OnControlTryParse_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.operators.interfaces.IControlTryParseListener#OnControlTryParse_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlFieldRefListener#OnControlFieldRef_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlFieldRefListener#OnControlFieldRef_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlFieldRefListener#OnControlFieldRef_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.fieldRefControl))
		{
			// Set loaded FieldRef
			this.put(PlaceholderData.FIELD_REF, FieldRef.class.cast(e.getSource()));
		}
		else
		{
			CliTools.ThrowInconsistentMessageException(this,
					"\nHtmlBodyControl.OnControlFieldRef_Loaded: FieldRef message expected");
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlFieldRefListener#OnControlFieldRef_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlFieldRefListener#OnControlFieldRef_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.contents.interfaces.IControlFieldRefListener#OnControlFieldRef_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlFieldRef_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<PlaceholderData> iterator() 
	{
		return this.data.keySet().iterator();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() 
	{
		return this.data.size();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() 
	{
		return this.data.isEmpty();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey(Object key) 
	{
		return this.data.containsKey(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object value) 
	{
		return this.data.containsValue(value);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public Object get(Object key) 
	{
		return this.data.get(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Object put(PlaceholderData key, Object value) 
	{
		switch (key) 
		{
			case UUID:
				this.message.setUuid(UUID.class.cast(value));
				break;
	
			case INHERITED:
				if (value != null)
				{
					this.message.setInherited(Boolean.class.cast(value));
				} 
				else 
				{
					value = new Boolean(true);
					
					// Set default value.
					this.message.setInherited(Boolean.class.cast(value));
				}
				break;
	
			case RUNTIME_OPTIONS:
				this.message.addRuntimeOption(RuntimeOptions.class.cast(value));
				break;
	
			case HTML_UNIT:
				// Check input value is the HtmlUnit's message.
				if(value.equals(this.message))
				{
					RenderControl(this.message);
					ControlHtmlBodyEvent(new ControlEventArgs(this, ControlEventType.RENDER, this.message));
				}
				break;
				
			case HTTP_ADDRESS:
				// Check input value is the HttpAddress's message.
				if(value.equals(this.message))
				{
					RenderControl(this.message);
					ControlHttpAddressEvent(new ControlEventArgs(this, ControlEventType.RENDER, this.message));
				}
				break;
				
			case FIELD_REF:
				// Check value is a child control.
				if(value.equals(this.fieldRefControl))
				{
					// Update current control's message.
					this.message.setFieldRef(this.fieldRefControl.getMessage());
					
					// Acknowledge child control's message has been settled. Render control.
					this.fieldRefControl.put(ContentData.FIELD_REF, this.fieldRefControl.getMessage());
				}				
				break;
				
			case CONTAINS:
				// Check value is a child control.
				if(value.equals(this.containsControl))
				{
					// Update current control's message.
					this.message.setContains(this.containsControl.getMessage());
					
					// Acknowledge child control's message has been settled. Render control.
					this.containsControl.put(OperatorData.CONTAINS, this.containsControl.getMessage());
				}
				break;
				
			case EQUALS:
				// Check value is a child control.
				if(value.equals(this.equalsControl))
				{
					// Update current control's message.
					this.message.setEquals(this.equalsControl.getMessage());
					
					// Acknowledge child control's message has been settled. Render control.
					this.equalsControl.put(OperatorData.EQUALS, this.equalsControl.getMessage());
				}
				break;
				
			case TRY_PARSE:
				// Check value is a child control.
				if(value.equals(this.tryParseControl))
				{
					// Update current control's message.
					this.message.setTryParse(this.tryParseControl.getMessage());
					
					// Acknowledge child control's message has been settled. Render control.
					this.tryParseControl.put(OperatorData.TRY_PARSE, this.tryParseControl.getMessage());
				}
				break;
				
			case REPLACE:
				// Check value is a child control.
				if(value.equals(this.replaceControl))
				{
					// Update current control's message.
					this.message.setReplace(this.replaceControl.getMessage());
					
					// Acknowledge child control's message has been settled. Render control.
					this.replaceControl.put(OperatorData.REPLACE, this.replaceControl.getMessage());
				}
				break;
				
			case REMOVE:
				// Check value is a child control.
				if(value.equals(this.removeControl))
				{
					// Update current control's message.
					this.message.setRemove(this.removeControl.getMessage());
					
					// Acknowledge child control's message has been settled. Render control.
					this.removeControl.put(OperatorData.REMOVE, this.removeControl.getMessage());
				}
				break;
				
			case SUBSTRING:
				// Check value is a child control.
				if(value.equals(this.substringControl))
				{
					// Update current control's message.
					this.message.setSubstring(this.substringControl.getMessage());
					
					// Acknowledge child control's message has been settled. Render control.
					this.substringControl.put(OperatorData.SUBSTRING, this.substringControl.getMessage());
				}
				break;
				
			case SPLIT:
				// Check value is a child control.
				if(value.equals(this.splitControl))
				{
					// Update current control's message.
					this.message.setSplit(this.splitControl.getMessage());
					
					// Acknowledge child control's message has been settled. Render control.
					this.splitControl.put(OperatorData.SPLIT, this.splitControl.getMessage());
				}
				break;
				
			default:
				break;
		}
		return this.data.put(key, value);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public Object remove(Object key) 
	{
		return this.data.remove(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends PlaceholderData, ? extends Object> m) {
		
		for(PlaceholderData placeholder :m.keySet())
		{
			if(this.put(placeholder, m.get(placeholder)) == null)
			{
				//TODO report error
			}
		}
	}
	/* (non-Javadoc)
	 * @see java.util.Map#clear()
	 */
	@Override
	public void clear() 
	{
		this.data.clear();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<PlaceholderData> keySet() 
	{
		return this.data.keySet();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<Object> values() 
	{
		return this.data.values();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<java.util.Map.Entry<PlaceholderData, Object>> entrySet() 
	{
		return this.data.entrySet();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#ChangeControl(org.httprobot.common.RML)
	 */
	@Override
	public void ChangeControl(RML message) {
		// TODO Auto-generated method stub
		super.ChangeControl(message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#InitControl(org.httprobot.common.RML)
	 */
	@Override
	public void InitControl(RML message) {
		// TODO Auto-generated method stub
		super.InitControl(message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#LoadControl(org.httprobot.common.RML)
	 */
	@Override
	public void LoadControl(RML message) {
		
		if (this.message == null)
			throw new NullPointerException("UnitControl.LoadControl: Control's message hasn't been initialized");
		
		//Insert loaded inherited data
		this.put(PlaceholderData.UUID, message.getUuid());
		this.put(PlaceholderData.INHERITED, message.getInherited());
		
		if(!Boolean.class.cast(this.get(PlaceholderData.INHERITED)) ? 
				message.getRuntimeOptions() != null : false) {

			for(RuntimeOptions option : message.getRuntimeOptions())
			{
				this.put(PlaceholderData.RUNTIME_OPTIONS, option);
			}
		}

		//Send event to parent.
		ControlLoadedEvent(new ControlEventArgs(this, ControlEventType.LOAD, message));
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#RenderControl()
	 */
	@Override
	public void RenderControl(RML message) {
		// TODO Auto-generated method stub
		super.RenderControl(message);
	}
	
}