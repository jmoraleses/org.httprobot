/**
 * 
 */
package org.httprobot.core.contents;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.httprobot.common.RML;
import org.httprobot.core.contents.solr.InputField;

/**
 * Input document field library class. Inherits {@link RML}.
 * <br>
 * It's a {@link Map} and {@link Iterable}.
 * @author joan
 *
 * @param <TKeyType> the iterable type.
 */
public class FieldLibrary<TKeyType> 
	extends RML 
	implements Map<TKeyType, InputField>, Iterable<TKeyType> {

	/**
	 * 3311368886501795373L
	 */
	private static final long serialVersionUID = 3311368886501795373L;

	/**
	 * The input fields.
	 */
	Map<TKeyType, InputField> inputFields;
	/**
	 * Input document field library default class constructor.
	 */
	public FieldLibrary() 
	{
		this.inputFields = new LinkedHashMap<TKeyType, InputField>();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() 
	{
		return this.inputFields.size();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() 
	{
		return this.inputFields.isEmpty();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey(Object key) 
	{
		return this.inputFields.containsKey(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object value) 
	{
		return this.inputFields.containsValue(value);
	}
	/**
	 * @param uuid {@link UUID} the field reference ID.
	 * @return the {@link InputField}
	 */
	public InputField get(UUID uuid)
	{
		for(TKeyType key : this.inputFields.keySet())
		{
			InputField inputField = this.inputFields.get(key);
			
			if(inputField.getFieldRef().getUuid().equals(uuid))
			{
				return inputField.deepInputCopy();
			}
		}
		return null;
	}
	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public InputField get(Object key) 
	{
		return this.inputFields.get(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public InputField put(TKeyType key, InputField value) 
	{
		return this.inputFields.put(key, value);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public InputField remove(Object key)
	{
		return this.inputFields.remove(key);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends TKeyType, ? extends InputField> m) 
	{
		this.inputFields.putAll(m);
	}
	/* (non-Javadoc)
	 * @see java.util.Map#clear()
	 */
	@Override
	public void clear() 
	{
		this.inputFields.clear();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<TKeyType> keySet() 
	{
		return inputFields.keySet();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<InputField> values() 
	{
		return this.inputFields.values();
	}
	/* (non-Javadoc)
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<java.util.Map.Entry<TKeyType, InputField>> entrySet() 
	{
		return this.inputFields.entrySet();
	}
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<TKeyType> iterator() 
	{
		return this.inputFields.keySet().iterator();
	}
}