package org.httprobot.core.rml.controls.placeholders;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.datatypes.placeholders.WebRequest;
import org.httprobot.core.rml.controls.RmlPlaceholderControl;
import org.httprobot.core.rml.controls.operators.ContainsControl;
import org.httprobot.core.rml.controls.operators.EqualsControl;
import org.httprobot.core.rml.controls.operators.RemoveControl;
import org.httprobot.core.rml.controls.operators.ReplaceControl;
import org.httprobot.core.rml.controls.operators.SplitControl;
import org.httprobot.core.rml.controls.operators.SubstringControl;
import org.httprobot.core.rml.controls.operators.TryParseControl;
import org.httprobot.core.rml.controls.operators.XmlQueryControl;

/**
 * @author Joan
 *
 */
@XmlRootElement
public class WebRequestControl extends RmlPlaceholderControl 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6869667376460474357L;
	
	private WebRequest web_request;
	private ReplaceControl replace_control = null;	
	private SubstringControl substring_control = null;	
	private SplitControl split_control = null;
	private ContainsControl contains_control = null;
	private EqualsControl equals_control = null;
	private RemoveControl remove_control = null;
	private TryParseControl tryparse_control = null;
	private XmlQueryControl xmlquery_control = null;

	/**
	 * Gets the contains control.
	 * @return {@link ContainsControl} the contains
	 */
	public ContainsControl getContainsControl() {
		return contains_control;
	}
	/**
	 * Gets the equals control.
	 * @return {@link EqualsControl} the equals
	 */
	public EqualsControl getEqualsControl() {
		return equals_control;
	}
	/**
	 * Gets the remove control.
	 * @return {@link RemoveControl} the remove
	 */
	public RemoveControl getRemoveControl() {
		return remove_control;
	}
	/**
	 * Gets the replace control.
	 * @return {@link ReplaceControl} the replace
	 */
	public ReplaceControl getReplaceControl() {
		return replace_control;
	}
	/**
	 * Gets the split control.
	 * @return {@link SplitControl} the split
	 */
	public SplitControl getSplitControl() {
		return split_control;
	}
	/**
	 * Gets the substring control.
	 * @return {@link SubstringControl} the substring
	 */
	public SubstringControl getSubstringControl() {
		return substring_control;
	}
	/**
	 * Gets the try parse control.
	 * @return {@link TryParseControl} the try parse
	 */
	public TryParseControl getTryparseControl() {
		return tryparse_control;
	}
	/**
	 * Gets the XML query control.
	 * @return {@link XmlQueryControl} the XML query
	 */
	public XmlQueryControl getXmlqueryControl() {
		return xmlquery_control;
	}
	/**
	 * @return the web_request
	 */
	public WebRequest getWeb_request() {
		return web_request;
	}
	/**
	 * WebRequest default class constructor.
	 */
	public WebRequestControl()
	{
		super();
	}
	/**
	 * WebRequest class constructor.
	 * @param parent {@link IRmlListener} the parent
	 */
	public WebRequestControl(IRmlListener parent) 
	{
		super(parent);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlInit(Object sender, RmlEventArgs e)
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlRendered(Object sender, RmlEventArgs e) 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnControlRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRead(Object sender, RmlEventArgs e)
			throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlPlaceholderControl#OnControlWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlWrite(Object sender, RmlEventArgs e)
			throws NotImplementedException, InconsistenMessageException 
	{
		
	}	
}