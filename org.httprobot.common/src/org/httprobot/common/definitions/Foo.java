/**
 * 
 */
package org.httprobot.common.definitions;

import com.gargoylesoftware.htmlunit.SgmlPage;
import com.gargoylesoftware.htmlunit.html.DomNode;

/**
 * @author joan
 *
 */
public class Foo extends DomNode {

	/**
	 * 7553722721191421182L
	 */
	private static final long serialVersionUID = 7553722721191421182L;

	/**
	 * @param page
	 */
	public Foo(SgmlPage page) {
		super(page);
		// TODO Auto-generated constructor stub
		
	}

	/* (non-Javadoc)
	 * @see com.gargoylesoftware.htmlunit.html.DomNode#getNodeType()
	 */
	@Override
	public short getNodeType() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.gargoylesoftware.htmlunit.html.DomNode#getNodeName()
	 */
	@Override
	public String getNodeName() {
		// TODO Auto-generated method stub
		return null;
	}

}
