/**
 * 
 */
package org.httprobot.core.controls.operators;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.Operator;
import org.httprobot.common.RML;
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
import org.httprobot.core.common.Enums.OperatorData;
import org.httprobot.core.controls.OperatorControl;
import org.httprobot.core.controls.interfaces.impl.IControlImpl;
import org.httprobot.core.controls.interfaces.impl.IOperatorControlImpl;

/**
 * Abstract {@link Operator} message control class. Inherits {@link OperatorControl}.
 * <br>
 * @author joan
 *
 * @param <TOperatorMessage> The {@link Operator} message type
 * @param <IListener> The {@link IControlListener} type
 */
@XmlTransient
public abstract class AbstractOperatorControl
	<TOperatorMessage extends Operator, IListener extends IControlListener>
		extends OperatorControl<TOperatorMessage, IListener> 
		implements IOperatorControlImpl {

	/**
	 * -5228825002159530336L
	 */
	private static final long serialVersionUID = -5228825002159530336L;
	/**
	 * The {@link Contains} message control.
	 */
	protected ContainsControl containsControl;
	/**
	 * The {@link Equals} message control.
	 */
	protected EqualsControl equalsControl;
	/**
	 * The {@link Remove} message control.
	 */
	protected RemoveControl removeControl;
	/**
	 * The {@link Replace} message control.
	 */
	protected ReplaceControl replaceControl;
	/**
	 * The {@link Split} message control.
	 */
	protected SplitControl splitControl;
	/**
	 * The {@link Substring} message control.
	 */
	protected SubstringControl substringControl;
	/**
	 * {@link TryParse} message control.
	 */
	protected TryParseControl tryParseControl;
	/**
	 * The {@link Operator} control data.
	 */
	Map<OperatorData, Object> data;
	/**
	 * Abstract {@link Operator} message control default class constructor.
	 */
	public AbstractOperatorControl() 
	{
		super();
		
		//Initialize operator data.
		this.data = new HashMap<OperatorData, Object>();
	}
	/**
	 * Abstract {@link Operator} message control default class constructor.
	 * @param parent {@link IControlListener} the parent
	 * @param message {@link Operator} the message
	 */
	public AbstractOperatorControl(IListener parent, TOperatorMessage message) 
	{
		super(parent, message);
		
		//Initialize operator data.
		this.data = new HashMap<OperatorData, Object>();
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
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<java.util.Map.Entry<OperatorData, Object>> entrySet() 
	{
		return this.data.entrySet();
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
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() 
	{
		return this.data.isEmpty();
	}
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<OperatorData> iterator() 
	{
		return this.data.keySet().iterator();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<OperatorData> keySet() 
	{
		return this.data.keySet();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControl_Changed(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Change(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControl_Init(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Initialize(Object sender, ControlEventArgs e) 
			throws InconsistenMessageException, NotImplementedException {
		
		if(e.getMessage() != null)
		{
			try
			{
				Operator operator = Operator.class.cast(e.getMessage());
				
				//Initialize controls
				if(operator.getContains() != null)
				{
					//Initialize Contains operator message control.
					this.containsControl = new ContainsControl(this, operator.getContains());
					
					//Associate child operator control to parent
					this.addCommandOutputListener(this.containsControl);
					
					//Store control.
					this.childControls.add(this.containsControl);
				}
				if(operator.getEquals() != null)
				{
					//Initialize Equals operator message control.
					this.equalsControl = new EqualsControl(this, operator.getEquals());
					
					//Associate child operator control to parent
					this.addCommandOutputListener(this.equalsControl);
					
					//Store control.
					this.childControls.add(this.containsControl);
				}
				if(operator.getRemove() != null)
				{
					//Initialize Remove operator message control.
					this.removeControl = new RemoveControl(this, operator.getRemove());
					
					//Associate child operator control to parent	
					this.addCommandOutputListener(this.removeControl);
					
					//Store control.
					this.childControls.add(this.containsControl);
				}
				if(operator.getReplace() != null)
				{
					//Initialize Replace operator message control.
					this.replaceControl = new ReplaceControl(this, operator.getReplace());
					
					//Associate child operator control to parent
					this.addCommandOutputListener(this.replaceControl);
					
					//Store control.
					this.childControls.add(this.containsControl);
				}
				if(operator.getSplit() != null)
				{
					//Initialize Split operator message control.
					this.splitControl = new SplitControl(this, operator.getSplit());
					
					//Associate child operator control to parent
					this.addCommandOutputListener(this.splitControl);
					
					//Store control.
					this.childControls.add(this.containsControl);
				}
				if(operator.getSubstring() != null)
				{
					//Initialize Substring operator message control.
					this.substringControl = new SubstringControl(this, operator.getSubstring());
					
					//Associate child operator control to parent
					this.addCommandOutputListener(this.substringControl);
					
					//Store control.
					this.childControls.add(this.containsControl);
				}
				if(operator.getTryParse() != null)
				{
					//Initialize TryParse operator message control.
					this.tryParseControl = new TryParseControl(this, operator.getTryParse());
					
					//Associate child operator control to parent
					this.addCommandOutputListener(this.tryParseControl);
					
					//Store control.
					this.childControls.add(this.containsControl);
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"AbstractOperatorControl.OnControl_Init: Operator message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControl_Loaded(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Load(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getMessage() != null)
		{
			try
			{
				Operator operator = Operator.class.cast(e.getMessage());
				
				//Iterate through child controls.
				if(this.hasChildControls())
				{
					while(this.hasNext())
					{
						IControlImpl control = this.next();
						
						//Contains message.
						if(control.equals(this.containsControl) ? 
								operator.getContains() != null ?
										control.getUuid().equals(operator.getContains().getUuid()) 
										: false : false) {
							
							//Contains message control set corresponding Contains message
							Contains contains = operator.getContains();
							this.containsControl.controlMessage(contains);
						}
						//Equals message.
						if(control.equals(this.equalsControl) ?
								operator.getEquals() != null ?
										control.getUuid().equals(operator.getEquals().getUuid())
										: false : false) {
							
							//Equals message control set corresponding Equals message
							Equals contains = operator.getEquals();
							this.equalsControl.controlMessage(contains);
						}
						//Remove message.
						if(control.equals(this.removeControl) ?
								operator.getRemove() != null ?
										control.getUuid().equals(operator.getRemove().getUuid())
										: false : false) {
							
							//Remove message control set corresponding Remove message
							Remove remove = operator.getRemove();
							this.removeControl.controlMessage(remove);
						}
						//Replace message.
						if(control.equals(this.replaceControl) ?
								operator.getReplace() != null ?
										control.getUuid().equals(operator.getReplace().getUuid())
										: false : false) {
							
							//Replace message control set corresponding Replace message
							Replace replace = operator.getReplace();
							this.replaceControl.controlMessage(replace);
						}
						//Split message.
						if(control.equals(this.splitControl) ?
								operator.getSplit() != null ?
										control.getUuid().equals(operator.getSplit().getUuid())
										: false : false) {
							
							//Split message control set corresponding Split message
							Split split = operator.getSplit();
							this.splitControl.controlMessage(split);
						}
						//Substring message.
						if(control.equals(this.substringControl) ?
								operator.getSubstring() != null ?
										control.getUuid().equals(operator.getSubstring().getUuid())
										: false : false) {
							
							//Substring message control set corresponding Substring message
							Substring substring = operator.getSubstring();
							this.substringControl.controlMessage(substring);
						}
						//TryParse message.
						if(operator.getTryParse() != null ?
								control.equals(this.tryParseControl) ?
										control.getUuid().equals(operator.getTryParse().getUuid())
										: false : false) {
							
							//TryParse message control set corresponding TryParse message
							TryParse tryParse = operator.getTryParse();
							this.tryParseControl.controlMessage(tryParse);
						}
					}
				}
			}
			catch(ClassCastException ex)
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"AbstractOperatorControl.OnControl_Loaded: Operator message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControl_Read(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Read(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControl_Rendered(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
	 */
	@Override
	public void Render(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.OperatorControl#OnControl_Write(java.lang.Object, org.httprobot.common.events.ControlEventArgs)
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
				this.put(OperatorData.CONTAINS, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"AbstractOperatorControl.OnControlContains_Loaded: Contains message expected");
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
				this.put(OperatorData.EQUALS, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"AbstractOperatorControl.OnControlEquals_Loaded: Equals message expected");
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
				this.put(OperatorData.REMOVE, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"AbstractOperatorControl.OnControlRemove_Loaded: Remove message expected");
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
				this.put(OperatorData.REPLACE, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"AbstractOperatorControl.OnControlReplace_Loaded: Replace message expected");
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
				this.put(OperatorData.SPLIT, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"AbstractOperatorControl.OnControlSplit_Loaded: Split message expected");
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
				this.put(OperatorData.SUBSTRING, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"AbstractOperatorControl.OnControlSubstring_Loaded: Substring message expected");
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
	@Override
	public void OnControlTryParse_Loaded(Object sender, ControlEventArgs e) 
			throws NotImplementedException, InconsistenMessageException {
		
		if(e.getSource().equals(this.tryParseControl))
		{
			if(e.getMessage() instanceof TryParse)
			{
				this.put(OperatorData.TRY_PARSE, e.getSource());
			}
			else
			{
				CliTools.ThrowInconsistentMessageException(this, 
						"AbstractOperatorControl.OnControlTryParse_Loaded: TryParse message expected");
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
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Object put(OperatorData key, Object value)
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
				
			case CONTAINS:
				// Check value is a child control.
				if(value.equals(this.containsControl))
				{
					this.message.setContains(this.containsControl.getMessage());
					
					this.containsControl.put(key, this.containsControl.getMessage());
				}
				// Check input value is the current's message.
				else if(value.equals(this.message))
				{
					RenderControl(this.message);
					ControlContainsEvent(new ControlEventArgs(this, ControlEventType.RENDER, this.message));
				}
				break;
				
			case EQUALS:
				// Check value is a child control.
				if(value.equals(this.equalsControl))
				{
					// Update current control's message.
					this.message.setEquals(this.equalsControl.getMessage());
					
					// Acknowledge child control's message has been settled. Render control.
					this.equalsControl.put(key, this.equalsControl.getMessage());
				}
				// Check input value is the current's message.
				else if(value.equals(this.message))
				{
					RenderControl(this.message);
					ControlEqualsEvent(new ControlEventArgs(this, ControlEventType.RENDER, this.message));
				}
				break;
				
			case REMOVE:
				// Check value is a child control.
				if(value.equals(this.removeControl))
				{
					// Update current control's message.
					this.message.setRemove(this.removeControl.getMessage());
					
					// Acknowledge child control's message has been settled. Render control.
					this.removeControl.put(key, this.removeControl.getMessage());
				}
				// Check input value is the current's message.
				else if(value.equals(this.message))
				{
					RenderControl(this.message);
					ControlRemoveEvent(new ControlEventArgs(this, ControlEventType.RENDER, this.message));
				}
				break;
				
			case REPLACE:
				// Check value is a child control.
				if(value.equals(this.replaceControl))
				{
					// Update current control's message.
					this.message.setReplace(this.replaceControl.getMessage());
					
					// Acknowledge child control's message has been settled. Render control.
					this.replaceControl.put(key, this.replaceControl.getMessage());
				}
				// Check input value is the current's message.
				else if(value.equals(this.message))
				{
					RenderControl(this.message);
					ControlReplaceEvent(new ControlEventArgs(this, ControlEventType.RENDER, this.message));
				}
				break;
				
			case SPLIT:
				// Check value is a child control.
				if(value.equals(this.splitControl))
				{
					// Update current control's message.
					this.message.setSplit(this.splitControl.getMessage());
					
					// Acknowledge child control's message has been settled. Render control.
					this.splitControl.put(key, this.splitControl.getMessage());
				}
				// Check input value is the current's message.
				else if(value.equals(this.message))
				{
					RenderControl(this.message);
					ControlSplitEvent(new ControlEventArgs(this, ControlEventType.RENDER, this.message));
				}
				break;
				
			case SUBSTRING:
				// Check value is a child control.
				if(value.equals(this.substringControl))
				{
					// Update current control's message.
					this.message.setSubstring(this.substringControl.getMessage());
					
					// Acknowledge child control's message has been settled. Render control.
					this.substringControl.put(key, this.substringControl.getMessage());
				}
				// Check input value is the current's message.
				else if(value.equals(this.message))
				{
					RenderControl(this.message);
					ControlSubstringEvent(new ControlEventArgs(this, ControlEventType.RENDER, this.message));
				}
				break;
				
			case TRY_PARSE:
				// Check value is a child control.
				if(value.equals(this.tryParseControl))
				{
					// Update current control's message.
					this.message.setTryParse(this.tryParseControl.getMessage());
					
					// Acknowledge child control's message has been settled. Render control.
					this.tryParseControl.put(key, this.tryParseControl.getMessage());
				}
				// Check input value is the current's message.
				else if(value.equals(this.message))
				{
					RenderControl(this.message);
					ControlTryParseEvent(new ControlEventArgs(this, ControlEventType.RENDER, this.message));
				}
				break;
				
			default:
				break;
		}
		
		//Update operator data.
		return this.data.put(key, value);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends OperatorData, ? extends Object> m) {
		
		for(OperatorData data : m.keySet())
		{
			if(this.put(data, m.get(data)) == null)
			{
				//TODO report error to parent.
			}
		}
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
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() 
	{
		return this.data.size();
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
		this.put(OperatorData.UUID, message.getUuid());
		this.put(OperatorData.INHERITED, message.getInherited());
		
		if(!Boolean.class.cast(this.get(OperatorData.INHERITED)) ? 
				message.getRuntimeOptions() != null : false) {

			for(RuntimeOptions option : message.getRuntimeOptions())
			{
				this.put(OperatorData.RUNTIME_OPTIONS, option);
			}
		}

		//Send event to parent.
		ControlLoadedEvent(new ControlEventArgs(this, ControlEventType.LOAD, message));
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.controls.Control#RenderControl()
	 */
	@Override
	public void RenderControl(RML message) 
	{
		super.RenderControl(message);
	}	
}