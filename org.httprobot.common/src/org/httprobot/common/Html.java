/**
 * 
 */
package org.httprobot.common;

import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;

/**
 * HTML {@link Operator} message class. Inherits {@link Operator}.
 * <br>
 * @author joan
 *
 */
@XmlTransient
public class Html 
		extends Operator {

	/**
	 * 2401254229033110468L
	 */
	private static final long serialVersionUID = 2401254229033110468L;

	private String id;
	private String style;
	private String className;
	private String title;
	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) 
	{
		this.id = id;
	}
	/**
	 * @return the style
	 */
	public String getStyle()
	{
		return style;
	}
	/**
	 * @param style the style to set
	 */
	public void setStyle(String style) 
	{
		this.style = style;
	}
	/**
	 * @return the className
	 */
	public String getClassName() 
	{
		return className;
	}
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) 
	{
		this.className = className;
	}
	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * HTML {@link Operator} message class constructor.
	 */
	public Html()
	{
		super();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.Placeholder#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e)
			throws NotImplementedException, InconsistenMessageException {
		
		super.OnObjectUnmarshalled(sender, e);
		
		setId(((Html)e.getRml()).getId());
		setStyle(((Html)e.getRml()).getStyle());
		setClassName(((Html)e.getRml()).getClassName());
		setTitle(((Html)e.getRml()).getTitle());
	}
}
