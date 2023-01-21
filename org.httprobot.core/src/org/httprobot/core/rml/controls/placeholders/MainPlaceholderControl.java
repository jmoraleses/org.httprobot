/**
 * 
 */
package org.httprobot.core.rml.controls.placeholders;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.RmlPlaceholder;
import org.httprobot.common.rml.datatypes.operators.Contains;
import org.httprobot.common.rml.datatypes.operators.Equals;
import org.httprobot.common.rml.datatypes.operators.ForEach;
import org.httprobot.common.rml.datatypes.operators.Remove;
import org.httprobot.common.rml.datatypes.operators.Replace;
import org.httprobot.common.rml.datatypes.operators.Select;
import org.httprobot.common.rml.datatypes.operators.Split;
import org.httprobot.common.rml.datatypes.operators.Substring;
import org.httprobot.common.rml.datatypes.operators.TryParse;
import org.httprobot.common.rml.datatypes.operators.XmlQuery;
import org.httprobot.common.tools.CommandLineInterface;
import org.httprobot.core.rml.controls.RmlPlaceholderControl;
import org.httprobot.core.rml.controls.operators.ForEachControl;
import org.httprobot.core.rml.controls.operators.interfaces.IContainsListener;
import org.httprobot.core.rml.controls.operators.interfaces.IEqualsListener;
import org.httprobot.core.rml.controls.operators.interfaces.IForEachListener;
import org.httprobot.core.rml.controls.operators.interfaces.IRemoveListener;
import org.httprobot.core.rml.controls.operators.interfaces.IReplaceListener;
import org.httprobot.core.rml.controls.operators.interfaces.ISelectListener;
import org.httprobot.core.rml.controls.operators.interfaces.ISplitListener;
import org.httprobot.core.rml.controls.operators.interfaces.ISubstringListener;
import org.httprobot.core.rml.controls.operators.interfaces.ITryParseListener;
import org.httprobot.core.rml.controls.operators.interfaces.IXmlQueryListener;
import org.httprobot.core.rml.controls.operators.main.ContainsControl;
import org.httprobot.core.rml.controls.operators.main.EqualsControl;
import org.httprobot.core.rml.controls.operators.main.RemoveControl;
import org.httprobot.core.rml.controls.operators.main.ReplaceControl;
import org.httprobot.core.rml.controls.operators.main.SelectControl;
import org.httprobot.core.rml.controls.operators.main.SplitControl;
import org.httprobot.core.rml.controls.operators.main.SubstringControl;
import org.httprobot.core.rml.controls.operators.main.TryParseControl;
import org.httprobot.core.rml.controls.operators.main.XmlQueryControl;

/**
 * @author joan
 *
 */
@XmlTransient
public class MainPlaceholderControl<TPlacheholderMessage extends RmlPlaceholder> extends RmlPlaceholderControl 
	implements IReplaceListener, ISubstringListener, ISplitListener,
	IContainsListener, IEqualsListener, IRemoveListener, ITryParseListener,
	IXmlQueryListener, ISelectListener, IForEachListener
{
	/**
	 * -9015911631674313159L
	 */
	private static final long serialVersionUID = -9015911631674313159L;
	
	TPlacheholderMessage placeholder;
	
	ArrayList<ReplaceControl> replaceControlList;	
	ArrayList<SubstringControl> substringControlList;	
	ArrayList<SplitControl> splitControlList;
	ArrayList<ContainsControl> containsControlList;
	ArrayList<EqualsControl> equalsControlList;
	ArrayList<RemoveControl> removeControlList;
	ArrayList<TryParseControl> tryparseControlList;
	ArrayList<XmlQueryControl> xmlqueryControlList;
	ArrayList<ForEachControl> foreachControlList;
	ArrayList<SelectControl> selectControlList;
	/**
	 * Main RML place holder controller default class constructor.
	 */
	public MainPlaceholderControl() 
	{
		super();
	}
	/**
	 * Main RML place holder controller class constructor.
	 * @param parent {@link IRmlListener} the parent
	 * @param message {@link RmlPlaceholder} the place holder RML message
	 */
	public MainPlaceholderControl(IRmlListener parent, RmlPlaceholder placeholder) 
	{
		super(parent, placeholder);
	}	
	/**
	 * Gets the contains control.
	 * @return {@link ContainsControl} the contains
	 */
	public ArrayList<ContainsControl> getContainsControl()
	{
		return containsControlList;
	}
	/**
	 * Gets the equals control.
	 * @return {@link EqualsControl} the equals
	 */
	public ArrayList<EqualsControl> getEqualsControl() 
	{
		return equalsControlList;
	}
	/**
	 * Gets the remove control.
	 * @return {@link RemoveControl} the remove
	 */
	public ArrayList<RemoveControl> getRemoveControl() 
	{
		return removeControlList;
	}
	/**
	 * Gets the replace control.
	 * @return {@link ReplaceControl} the replace
	 */
	public ArrayList<ReplaceControl> getReplaceControl()
	{
		return replaceControlList;
	}
	/**
	 * Gets the split control.
	 * @return {@link SplitControl} the split
	 */
	public ArrayList<SplitControl> getSplitControl() 
	{
		return splitControlList;
	}
	/**
	 * Gets the substring control.
	 * @return {@link SubstringControl} the substring
	 */
	public ArrayList<SubstringControl> getSubstringControl()
	{
		return substringControlList;
	}
	/**
	 * Gets the try parse control.
	 * @return {@link TryParseControl} the try parse
	 */
	public ArrayList<TryParseControl> getTryparseControl()
	{
		return tryparseControlList;
	}
	/**
	 * Gets the XML query control.
	 * @return {@link XmlQueryControl} the XML query
	 */
	public ArrayList<XmlQueryControl> getXmlqueryControl()
	{
		return xmlqueryControlList;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IContainsListener#OnContainsChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnContainsChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IContainsListener#OnContainsInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnContainsInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IContainsListener#OnContainsLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnContainsLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		if(e.getMessage() != null)
		{
			try
			{
				Contains contains = Contains.class.cast(e.getMessage());
				//Set loaded data
				this.placeholder.getContains().add(contains);
			}
			catch(ClassCastException ex1)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "WebPlaceholderControl.OnContainsLoaded: Contains RML message expected");
			}	
		}		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IContainsListener#OnContainsRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnContainsRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IContainsListener#OnContainsRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnContainsRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IContainsListener#OnContainsWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnContainsWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		if(e.getMessage() != null)
		{
			//Initialize using data
			this.containsControlList = new ArrayList<ContainsControl>();
			this.equalsControlList = new ArrayList<EqualsControl>();
			this.removeControlList = new ArrayList<RemoveControl>();
			this.replaceControlList = new ArrayList<ReplaceControl>();
			this.splitControlList = new ArrayList<SplitControl>();
			this.substringControlList = new ArrayList<SubstringControl>();
			this.tryparseControlList = new ArrayList<TryParseControl>();
			this.xmlqueryControlList = new ArrayList<XmlQueryControl>();
			this.selectControlList = new ArrayList<SelectControl>();
			this.foreachControlList = new ArrayList<ForEachControl>();
			
			try
			{
				RmlPlaceholder placeholder = RmlPlaceholder.class.cast(e.getMessage());
				
				//Set inherited data.
				this.setUuid(placeholder.getUuid());
				this.setInherited(placeholder.getInherited());
				this.setRuntimeOptions(placeholder.getRuntimeOptions());
				
				Boolean found = false;
				
				if(placeholder.getContains() != null)
				{
					for(Contains contains : placeholder.getContains())
					{
						//If contains message not null instantiate Contains message control.
						ContainsControl contains_control = new ContainsControl(this, contains);
						
						//Associate child control to parent.
						contains_control.addContainsListener(this);
						this.containsControlList.add(contains_control);
						this.addCommandOutputListener(contains_control);
						
						found = true;
					}
				}
				if(placeholder.getEquals() != null)
				{
					for(Equals equals : placeholder.getEquals())
					{
						//If Equals message not null instantiate Equals message control.
						EqualsControl equals_control = new EqualsControl(this, equals);

						//Associate child control to parent.
						equals_control.addEqualsListener(this);
						this.equalsControlList.add(equals_control);
						this.addCommandOutputListener(equals_control);

						found = true;
					}
				}
				if(placeholder.getRemove() != null)
				{
					for(Remove remove : placeholder.getRemove())
					{
						//If Remove message not null instantiate Remove message control.
						RemoveControl remove_control = new RemoveControl(this, remove);

						//Associate child control to parent.
						remove_control.addRemoveListener(this);
						this.removeControlList.add(remove_control);
						this.addCommandOutputListener(remove_control);
						
						found = true;
					}
				}
				if(placeholder.getReplace() != null)
				{
					for(Replace replace : placeholder.getReplace())
					{
						//If Replace message not null instantiate Replace message control.
						ReplaceControl replace_control = new ReplaceControl(this, replace);
						
						//Associate child control to parent.
						replace_control.addReplaceListener(this);
						this.replaceControlList.add(replace_control);
						this.addCommandOutputListener(replace_control);
						
						found = true;
					}
				}
				if(placeholder.getSplit() != null)
				{
					for(Split split : placeholder.getSplit())
					{
						//If Split message not null instantiate Split message control.
						SplitControl split_control = new SplitControl(this, split);
						
						//Associate child control to parent.
						split_control.addSplitListener(this);
						this.splitControlList.add(split_control);
						this.addCommandOutputListener(split_control);
						
						found = true;	
					}
				}
				if(placeholder.getSubstring() != null)
				{
					for(Substring substring : placeholder.getSubstring())
					{
						//If Substring message not null instantiate Substring message control.
						SubstringControl substring_control = new SubstringControl(this, substring);
						
						//Associate child control to parent.
						substring_control.addSubstringListener(this);
						this.substringControlList.add(substring_control);
						this.addCommandOutputListener(substring_control);
						
						found = true;	
					}
				}
				if(placeholder.getTryParse() != null)
				{
					for(TryParse try_parse : placeholder.getTryParse())
					{
						//If TryParse message not null instantiate TryParse message control.
						TryParseControl try_parse_control = new TryParseControl(this, try_parse);
						
						//Associate child control to parent.
						try_parse_control.addTryParseListener(this);
						this.tryparseControlList.add(try_parse_control);
						this.addCommandOutputListener(try_parse_control);
						
						found = true;
					}				
				}
				if(placeholder.getXmlQuery() != null)
				{
					for(XmlQuery xml_query : placeholder.getXmlQuery())
					{
						//If XmlQuery message not null instantiate XmlQuery message control.
						XmlQueryControl xmlquery_control = new XmlQueryControl(this, xml_query);
						
						//Associate child control to parent.
						xmlquery_control.addXmlQueryListener(this);
						this.xmlqueryControlList.add(xmlquery_control);
						this.addCommandOutputListener(xmlquery_control);
						
						found = true;
					}				
				}
				if(placeholder.getForEach() != null)
				{
					for(ForEach for_each : placeholder.getForEach())
					{
						//If XmlQuery message not null instantiate ForEach message control.
						ForEachControl foreach_control = new ForEachControl(this, for_each);
						
						//Associate child control to parent.
						foreach_control.addForEachListener(this);
						this.foreachControlList.add(foreach_control);
						this.addCommandOutputListener(foreach_control);
						
						found = true;
					}				
				}
				if(placeholder.getSelect() != null)
				{
					for(Select select : placeholder.getSelect())
					{
						//If Select message not null instantiate Select message control.
						SelectControl select_control = new SelectControl(this, select);
						
						//Associate child control to parent.
						select_control.addSelectListener(this);
						this.selectControlList.add(select_control);
						this.addCommandOutputListener(select_control);
						
						found = true;
					}				
				}
				
				if(!found)
				{
					CommandLineInterface.ThrowInconsistentMessageException(this, "WebPlaceholderControl.OnControlInit: RML operator's controls expected");
				}
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "WebPlaceholderControl.OnControlInit: Placeholder RML message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			try
			{
				RmlPlaceholder placeholder = RmlPlaceholder.class.cast(e.getMessage());
				
				if(placeholder.getContains() != null)
				{
					//For each Contains RML message control set corresponding Contains RML message
					for(ContainsControl containsControl : this.containsControlList)
					{
						for(Contains contains : placeholder.getContains())
						{
							if(contains.getUuid() == containsControl.getUuid())
							{
								containsControl.setMessage(contains);
								break;
							}
						}
					}
				}				
				if(placeholder.getEquals() != null)
				{
					//For each Equals RML message control set corresponding Equals RML message
					for(EqualsControl equalsControl : this.equalsControlList)
					{
						for(Equals contains : placeholder.getEquals())
						{
							if(contains.getUuid() == equalsControl.getUuid())
							{
								equalsControl.setMessage(contains);
								break;
							}
						}
					}
				}				
				if(placeholder.getRemove() != null)
				{
					//For each Remove RML message control set corresponding Remove RML message
					for(RemoveControl removeControl : this.removeControlList)
					{
						for(Remove remove : placeholder.getRemove())
						{
							if(remove.getUuid() == removeControl.getUuid())
							{
								removeControl.setMessage(remove);
								break;
							}
						}
					}
				}
				if(placeholder.getReplace() != null)
				{
					//For each Replace RML message control set corresponding Replace RML message
					for(ReplaceControl replaceControl : this.replaceControlList)
					{
						for(Replace replace : placeholder.getReplace())
						{
							if(replace.getUuid() == replaceControl.getUuid())
							{
								replaceControl.setMessage(replace);
								break;
							}
						}
					}
				}				
				if(placeholder.getSplit() != null)
				{
					//For each Split RML message control set corresponding Split RML message
					for(SplitControl splitControl : this.splitControlList)
					{
						for(Split split : placeholder.getSplit())
						{
							if(split.getUuid() == splitControl.getUuid())
							{
								splitControl.setMessage(split);
								break;
							}
						}
					}
				}				
				if(placeholder.getSubstring() != null)
				{
					//For each Substring RML message control set corresponding Substring RML message
					for(SubstringControl substringControl : this.substringControlList)
					{
						for(Substring substring : placeholder.getSubstring())
						{
							if(substring.getUuid() == substringControl.getUuid())
							{
								substringControl.setMessage(substring);
								break;
							}
						}
					}
				}				
				if(placeholder.getTryParse() != null)
				{
					//For each TryParse RML message control set corresponding TryParse RML message
					for(TryParseControl tryParseControl : this.tryparseControlList)
					{
						for(TryParse tryParse : placeholder.getTryParse())
						{
							if(tryParse.getUuid() == tryParseControl.getUuid())
							{
								tryParseControl.setMessage(tryParse);
								break;
							}
						}
					}
				}				
				if(placeholder.getXmlQuery() != null)
				{
					//For each XmlQuery RML message control set corresponding XmlQuery RML message
					for(XmlQueryControl xmlQueryControl : this.xmlqueryControlList)
					{
						for(XmlQuery xmlQuery : placeholder.getXmlQuery())
						{
							if(xmlQuery.getUuid() == xmlQueryControl.getUuid())
							{
								xmlQueryControl.setMessage(xmlQuery);
								break;
							}
						}
					}
				}
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "WebPlaceholderControl.OnControlLoaded: RML placeholder message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnControlRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnControlWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IEqualsListener#OnEqualsChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnEqualsChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IEqualsListener#OnEqualsInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnEqualsInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IEqualsListener#OnEqualsLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnEqualsLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		try
		{
			//Set loaded data
			this.placeholder.getEquals().add(Equals.class.cast(e.getMessage()));
		}
		catch(ClassCastException ex1)
		{
			CommandLineInterface.ThrowInconsistentMessageException(this, "\nWebPlaceholderControl.OnEqualsLoaded: Equals RML message expected");
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IEqualsListener#OnEqualsRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnEqualsRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IEqualsListener#OnEqualsRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnEqualsRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IEqualsListener#OnEqualsWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnEqualsWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IForEachListener#OnForEachChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnForEachChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IForEachListener#OnForEachInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnForEachInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}

	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IForEachListener#OnForEachLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnForEachLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		try
		{
			//Set loaded data
			this.placeholder.getForEach().add(ForEach.class.cast(e.getMessage()));
		}
		catch(ClassCastException ex1)
		{
			CommandLineInterface.ThrowInconsistentMessageException(this, "\nWebPlaceholderControl.OnForEachLoaded: Equals RML message expected");
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IForEachListener#OnForEachRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnForEachRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IForEachListener#OnForEachRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnForEachRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IForEachListener#OnForEachWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnForEachWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}

	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnHtmlBodyChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHtmlBodyChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnHtmlBodyInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHtmlBodyInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnHtmlBodyLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHtmlBodyLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnHtmlBodyRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHtmlBodyRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnHtmlBodyRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHtmlBodyRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnHtmlBodyWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHtmlBodyWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnHttpRequestChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHttpRequestChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnHttpRequestInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHttpRequestInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnHttpRequestLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHttpRequestLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnHttpRequestRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHttpRequestRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnHttpRequestRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHttpRequestRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnHttpRequestWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHttpRequestWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IRemoveListener#OnRemoveChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRemoveChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IRemoveListener#OnRemoveInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRemoveInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IRemoveListener#OnRemoveLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRemoveLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		try
		{
			//Set loaded data
			this.placeholder.getRemove().add(Remove.class.cast(e.getMessage()));
		}
		catch(ClassCastException ex1)
		{
			CommandLineInterface.ThrowInconsistentMessageException(this, "\nWebPlaceholderControl.OnRemoveLoaded: Remove RML message expected");
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IRemoveListener#OnRemoveRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRemoveRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IRemoveListener#OnRemoveRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRemoveRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IRemoveListener#OnRemoveWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRemoveWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IReplaceListener#OnReplaceChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnReplaceChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IReplaceListener#OnReplaceInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnReplaceInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IReplaceListener#OnReplaceLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnReplaceLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		try
		{
			//Set loaded data
			this.placeholder.getReplace().add(Replace.class.cast(e.getMessage()));
		}
		catch(ClassCastException ex1)
		{
			CommandLineInterface.ThrowInconsistentMessageException(this, "\nWebPlaceholderControl.OnReplaceLoaded: Replace RML message expected");
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IReplaceListener#OnReplaceRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnReplaceRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IReplaceListener#OnReplaceRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnReplaceRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IReplaceListener#OnReplaceWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnReplaceWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISelectListener#OnSelectChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSelectChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISelectListener#OnSelectInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSelectInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISelectListener#OnSelectLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSelectLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		try
		{
			//Set loaded data
			this.placeholder.getSelect().add(Select.class.cast(e.getMessage()));
		}
		catch(ClassCastException ex1)
		{
			CommandLineInterface.ThrowInconsistentMessageException(this, "\nWebPlaceholderControl.OnEqualsLoaded: Equals RML message expected");
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISelectListener#OnSelectRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSelectRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISelectListener#OnSelectRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSelectRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISelectListener#OnSelectWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSelectWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISplitListener#OnSplitChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSplitChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISplitListener#OnSplitInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSplitInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISplitListener#OnSplitLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSplitLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		try
		{
			//Set loaded data
			this.placeholder.getSplit().add(Split.class.cast(e.getMessage()));
		}
		catch(ClassCastException ex1)
		{
			CommandLineInterface.ThrowInconsistentMessageException(this, "\nWebPlaceholderControl.OnSplitLoaded: Split RML message expected");
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISplitListener#OnSplitRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSplitRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISplitListener#OnSplitRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSplitRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISplitListener#OnSplitWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSplitWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISubstringListener#OnSubstringChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSubstringChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISubstringListener#OnSubstringInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSubstringInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISubstringListener#OnSubstringLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSubstringLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		try
		{
			//Set loaded data
			this.placeholder.getSubstring().add(Substring.class.cast(e.getMessage()));
		}
		catch(ClassCastException ex1)
		{
			CommandLineInterface.ThrowInconsistentMessageException(this, "\nWebPlaceholderControl.OnSubstringLoaded: Substring RML message expected");
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISubstringListener#OnSubstringRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSubstringRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISubstringListener#OnSubstringRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSubstringRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ISubstringListener#OnSubstringWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnSubstringWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ITryParseListener#OnTryParseChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnTryParseChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ITryParseListener#OnTryParseInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnTryParseInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ITryParseListener#OnTryParseLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnTryParseLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		try
		{
			//Set loaded data
			this.placeholder.getTryParse().add(TryParse.class.cast(e.getMessage()));
		}
		catch(ClassCastException ex1)
		{
			CommandLineInterface.ThrowInconsistentMessageException(this, "\nWebPlaceholderControl.OnTryParseLoaded: TryParse RML message expected");
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ITryParseListener#OnTryParseRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnTryParseRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
				
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ITryParseListener#OnTryParseRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnTryParseRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.ITryParseListener#OnTryParseWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnTryParseWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IXmlQueryListener#OnXmlQueryChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnXmlQueryChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IXmlQueryListener#OnXmlQueryInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnXmlQueryInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IXmlQueryListener#OnXmlQueryLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnXmlQueryLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		try
		{
			//Set loaded data
			this.placeholder.getXmlQuery().add(XmlQuery.class.cast(e.getMessage()));
		}
		catch(ClassCastException ex1)
		{
			CommandLineInterface.ThrowInconsistentMessageException(this, "\nWebPlaceholderControl.OnXmlQueryLoaded: XmlQuery RML message expected");
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IXmlQueryListener#OnXmlQueryRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnXmlQueryRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IXmlQueryListener#OnXmlQueryRendered(java.lang.Objec0t, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnXmlQueryRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.operators.interfaces.IXmlQueryListener#OnXmlQueryWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnXmlQueryWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
}