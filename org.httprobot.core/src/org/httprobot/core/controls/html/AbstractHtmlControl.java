/**
 * 
 */
package org.httprobot.core.controls.html;

import org.httprobot.core.common.Enums.HtmlData;
import org.httprobot.core.common.Enums.OperatorData;
import org.httprobot.core.controls.HtmlControl;
import org.httprobot.core.controls.interfaces.impl.IControlImpl;
import org.httprobot.core.controls.interfaces.listeners.IHtmlControlListener;
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
import org.httprobot.common.Html;
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

/**
 * Abstract {@link Html} message control implementation class. Inherits {@link HtmlControl}.
 * <br>
 * @author joan
 *
 * @param <TMessage> The {@link Html} message type.
 * @param <TListener> The {@link IControlListener} type for matching message.
 */
public abstract class AbstractHtmlControl
	<TMessage extends Html, TListener extends IControlListener>
		extends HtmlControl	<TMessage, TListener> 
		implements IHtmlControlListener, IControlReplaceListener, IControlSubstringListener, 
			IControlSplitListener, IControlContainsListener, IControlEqualsListener, 
			IControlRemoveListener, IControlTryParseListener {

	/**
	 * -3624474139828643792L
	 */
	private static final long serialVersionUID = -3624474139828643792L;
	
	/**
	 * The {@link Contains} operator message control.
	 */
	protected ContainsControl containsControl;
	/**
	 * The {@link Equals} operator message control.
	 */
	protected EqualsControl equalsControl;
	/**
	 * The {@link Remove} operator message control.
	 */
	protected RemoveControl removeControl;
	/**
	 * The {@link Replace} operator message control.
	 */
	protected ReplaceControl replaceControl;
	/**
	 * The {@link Split} operator message control.
	 */
	protected SplitControl splitControl;
	/**
	 * The {@link Substring} operator message control.
	 */
	protected SubstringControl substringControl;
	/**
	 * The {@link TryParse} operator message control.
	 */
	protected TryParseControl tryParseControl;

	/**
	 * Abstract {@link HtmlControl} default class constructor.
	 */
	public AbstractHtmlControl() 
	{
		super();
	}
	/**
	 * Abstract {@link HtmlControl} class constructor.
	 * @param parent the listener
	 * @param message the message
	 */
	public AbstractHtmlControl(TListener parent, TMessage message) 
	{
		super(parent, message);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Change(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Initialize(Object sender, ControlEventArgs e)
			throws InconsistenMessageException, NotImplementedException {
		
		if(e.getMessage() != null)
		{
			try
			{
				Html html = Html.class.cast(e.getMessage());
				
				if(html.getContains() != null)
				{					
					Contains contains = html.getContains();
					
					// If contains message not null instantiate Contains message control.
					this.containsControl = new ContainsControl(this, contains);

					// Associate child control to parent.
					this.addCommandOutputListener(this.containsControl);
					
					//Store control.
					this.childControls.add(this.containsControl);
				}
				if(html.getEquals() != null)
				{
					Equals equals = html.getEquals();
					
					//If Equals message not null instantiate Equals message control.
					this.equalsControl = new EqualsControl(this, equals);

					//Associate child control to parent.		
					this.addCommandOutputListener(this.equalsControl);
					
					//Store control.
					this.childControls.add(this.equalsControl);
				}
				if(html.getRemove() != null)
				{
					Remove remove = html.getRemove();
					
					//If Remove message not null instantiate Remove message control.
					this.removeControl = new RemoveControl(this, remove);

					//Associate child control to parent.
					this.addCommandOutputListener(this.removeControl);
					
					//Store control.
					this.childControls.add(this.removeControl);
				}
				if(html.getReplace() != null)
				{
					Replace replace = html.getReplace();
					
					//If Replace message not null instantiate Replace message control.
					this.replaceControl = new ReplaceControl(this, replace);
					
					//Associate child control to parent.
					this.addCommandOutputListener(this.replaceControl);
					
					//Store control.
					this.childControls.add(this.replaceControl);
				}
				if(html.getSplit() != null)
				{
					Split split = html.getSplit();
					
					//If Split message not null instantiate Split message control.
					this.splitControl = new SplitControl(this, split);
					
					//Associate child control to parent.
					this.addCommandOutputListener(this.splitControl);
					
					//Store control.
					this.childControls.add(this.splitControl);
				}
				if(html.getSubstring() != null)
				{
					Substring substring = html.getSubstring();
					
					//If Substring message not null instantiate Substring message control.
					this.substringControl = new SubstringControl(this, substring);
					
					//Associate child control to parent.
					this.addCommandOutputListener(this.substringControl);
					
					//Store control.
					this.childControls.add(this.substringControl);
				}
				if(html.getTryParse() != null)
				{
					TryParse try_parse = html.getTryParse();
					
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
						"AbstractHtmlControl.OnControl_Init: Html message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Load(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getMessage() != null)
		{
			try
			{
				Html html = Html.class.cast(e.getMessage());
				/*
				 * Check main HTML attributes.
				 * */
				if(html.getId() != null)
				{
					this.put(HtmlData.ID, html.getId());
				}
				if(html.getTitle() != null)
				{
					this.put(HtmlData.TITLE, html.getTitle());
				}
				if(html.getStyle() != null)
				{
					this.put(HtmlData.STYLE, html.getStyle());
				}
				if(html.getClassName() != null)
				{
					this.put(HtmlData.CLASS, html.getTitle());
				}
				
				//Iterate through child controls.
				if(this.hasChildControls())
				{
					while(this.hasNext())
					{
						IControlImpl control = this.next();
						
						//Contains message.
						if(control.equals(this.containsControl) ? 
								html.getContains() != null ?
										control.getUuid().equals(html.getContains().getUuid()) 
										: false : false) {
							
							//Contains message control set corresponding Contains message
							Contains contains = html.getContains();
							this.containsControl.controlMessage(contains);
						}
						//Equals message.
						if(control.equals(this.equalsControl) ?
								html.getEquals() != null ?
										control.getUuid().equals(html.getEquals().getUuid())
										: false : false) {
							
							//Equals message control set corresponding Equals message
							Equals contains = html.getEquals();
							this.equalsControl.controlMessage(contains);
						}
						//Remove message.
						if(control.equals(this.removeControl) ?
								html.getRemove() != null ?
										control.getUuid().equals(html.getRemove().getUuid())
										: false : false) {
							
							//Remove message control set corresponding Remove message
							Remove remove = html.getRemove();
							this.removeControl.controlMessage(remove);
						}
						//Replace message.
						if(control.equals(this.replaceControl) ?
								html.getReplace() != null ?
										control.getUuid().equals(html.getReplace().getUuid())
										: false : false) {
							
							//Replace message control set corresponding Replace message
							Replace replace = html.getReplace();
							this.replaceControl.controlMessage(replace);
						}
						//Split message.
						if(control.equals(this.splitControl) ?
								html.getSplit() != null ?
										control.getUuid().equals(html.getSplit().getUuid())
										: false : false) {
							
							//Split message control set corresponding Split message
							Split split = html.getSplit();
							this.splitControl.controlMessage(split);
						}
						//Substring message.
						if(control.equals(this.substringControl) ?
								html.getSubstring() != null ?
										control.getUuid().equals(html.getSubstring().getUuid())
										: false : false) {
							
							//Substring message control set corresponding Substring message
							Substring substring = html.getSubstring();
							this.substringControl.controlMessage(substring);
						}
						//TryParse message.
						if(html.getTryParse() != null ?
								control.equals(this.tryParseControl) ?
										control.getUuid().equals(html.getTryParse().getUuid())
										: false : false) {
							
							//TryParse message control set corresponding TryParse message
							TryParse tryParse = html.getTryParse();
							this.tryParseControl.controlMessage(tryParse);
						}
					}
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"AbstractHtmlControl.OnControl_Loaded: Html message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Render(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#OnControl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlContains_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContains_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlContains_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContains_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlContains_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContains_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.containsControl))
		{
			if(e.getMessage() instanceof Contains)
			{
				this.put(HtmlData.CONTAINS, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"AbstractHtmlControl.OnControlContains_Loaded: Contains message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlContains_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContains_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlContains_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContains_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlContains_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlContains_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlEquals_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlEquals_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlEquals_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlEquals_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlEquals_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlEquals_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.equalsControl))
		{
			if(e.getMessage() instanceof Equals)
			{
				this.put(HtmlData.EQUALS, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"AbstractHtmlControl.OnControlEquals_Loaded: Equals message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlEquals_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlEquals_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlEquals_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlEquals_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlEquals_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlEquals_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlRemove_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlRemove_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlRemove_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlRemove_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlRemove_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlRemove_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.removeControl))
		{
			if(e.getMessage() instanceof Remove)
			{
				this.put(HtmlData.REMOVE, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"AbstractHtmlControl.OnControlRemove_Loaded: Remove message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlRemove_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlRemove_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlRemove_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlRemove_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlRemove_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlRemove_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlReplace_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlReplace_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlReplace_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlReplace_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlReplace_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlReplace_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.replaceControl))
		{
			if(e.getMessage() instanceof Replace)
			{
				this.put(HtmlData.REPLACE, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this,
						"AbstractHtmlControl.OnControlReplace_Loaded: Replace message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlReplace_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlReplace_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlReplace_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlReplace_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlReplace_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlReplace_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlSplit_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSplit_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlSplit_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSplit_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlSplit_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSplit_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.splitControl))
		{
			if(e.getMessage() instanceof Split)
			{
				this.put(HtmlData.SPLIT, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"AbstractHtmlControl.OnControlSplit_Loaded: Split message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlSplit_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSplit_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlSplit_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSplit_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlSplit_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSplit_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlSubstring_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSubstring_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlSubstring_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSubstring_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlSubstring_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSubstring_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.substringControl))
		{
			if(e.getMessage() instanceof Substring)
			{
				this.put(HtmlData.SUBSTRING, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"AbstractHtmlControl.OnControlSubstring_Loaded: Substring message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlSubstring_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSubstring_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlSubstring_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSubstring_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlSubstring_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlSubstring_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlTryParse_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Changed(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlTryParse_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Init(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlTryParse_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Loaded(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.tryParseControl))
		{
			if(e.getMessage() instanceof TryParse)
			{
				this.put(HtmlData.TRY_PARSE, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this,
						"AbstractHtmlControl.OnControlTryParse_Loaded: TryParse message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlTryParse_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Read(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlTryParse_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Rendered(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControlTryParse_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void OnControlTryParse_Write(Object sender, ControlEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#put(org.httprobot.core.common.Enums.HtmlData, java.lang.Object)
	 */
	@Override
	public Object put(HtmlData key, Object value)
	{
		switch (key)
		{
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
		}
		
		//Update HtmlControl data.
		return super.put(key, value);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.HtmlControl#remove(java.lang.Object)
	 */
	@Override
	public Object remove(Object key) 
	{
		return super.remove(key);
	}
	
}