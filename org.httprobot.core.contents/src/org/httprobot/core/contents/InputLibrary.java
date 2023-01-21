/**
 * 
 */
package org.httprobot.core.contents;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.RML;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.core.contents.solr.InputDocument;

/**
 * Input document library class. Inherits {@link RML}. 
 * <br>
 * It's {@link Map} and {@link Iterable}.
 * <br>
 * This class represents a collection of input document for SOLR server.
 * <br> 
 * @author joan
 *
 */
@XmlRootElement
public class InputLibrary<TKeyType> 
	extends RML 
	implements Map<TKeyType, InputDocument>, Iterable<TKeyType> {
	
	/**
	 * -263754320415086768L
	 */
	private static final long serialVersionUID = -263754320415086768L;
	
	Map<TKeyType, InputDocument> inputDocuments;

	/**
	 * {@link InputDocument} library default class constructor.
	 */
	public InputLibrary()
	{
		super();
		
		//Initialize input documents map
		this.inputDocuments = new LinkedHashMap<TKeyType, InputDocument>();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandInput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.interfaces.IListener#OnCommandOutput(java.lang.Object, org.httprobot.common.events.CliEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e)
			throws InconsistenMessageException {
		
	}
	/* (non-Javadoc)
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() 
	{
		return this.inputDocuments.size();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() 
	{
		return this.inputDocuments.isEmpty();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey(Object key) {

		return this.inputDocuments.containsKey(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object value) {
		
		return this.inputDocuments.containsValue(value);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public InputDocument get(Object key) {
		return this.inputDocuments.get(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public InputDocument put(TKeyType key, InputDocument value) 
	{
		return this.inputDocuments.put(key, value);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public InputDocument remove(Object key) 
	{
		return this.inputDocuments.remove(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends TKeyType, ? extends InputDocument> m)
	{			
		this.inputDocuments.putAll(m);		
	}
	/* (non-Javadoc)
	 * @see java.util.Map#clear()
	 */
	@Override
	public void clear() 
	{		
		this.inputDocuments.clear();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<TKeyType> keySet() {

		//Convert Enumeration to Set
//		Set<Object> set = new HashSet<Object>(Collections.list(this.dataDocuments.keys()));
		return this.inputDocuments.keySet();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<InputDocument> values() {
//		Collection<InputDocument> collection = new HashSet<InputDocument>(Collections.list(this.dataDocuments.elements()));
		return this.inputDocuments.values();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<Entry<TKeyType, InputDocument>> entrySet() {
		//Convert Enumeration to Set
//		Set<Entry<Object, InputDocument>> set = new HashSet<Entry<Object, InputDocument>(this.dataDocuments);
		return this.inputDocuments.entrySet();
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<TKeyType> iterator() {
		return this.inputDocuments.keySet().iterator();
	}
}