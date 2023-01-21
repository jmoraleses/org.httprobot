/**
 * 
 */
package org.httprobot.core.contents;

import org.httprobot.common.contents.FieldRef;
import org.httprobot.core.contents.solr.InputField;

/**
 * @author joan
 *
 */
public class TemplateFieldLibrary extends FieldLibrary<FieldRef> {

	/**
	 * 4624041555177468835L
	 */
	private static final long serialVersionUID = 4624041555177468835L;

	/**
	 * 
	 */
	public TemplateFieldLibrary() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.FieldLibrary#get(java.lang.Object)
	 */
	@Override
	public InputField get(Object key) 
	{
		return super.get(key).deepInputCopy();
	}

}
