package org.httprobot.core.contents.xml.films;

import java.util.ArrayList;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.FieldRef;
import org.httprobot.core.contents.xml.Link;
import org.httprobot.core.contents.xml.films.fields.Language;
import org.httprobot.core.contents.xml.films.fields.Quality;
import org.httprobot.core.contents.xml.films.fields.Server;

@XmlRootElement
public class FilmLink extends Link
{
	/**
	 * 5845319954476238886L
	 */
	private static final long serialVersionUID = 5845319954476238886L;

	public FilmLink() 
	{
		super();
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Item#getInheritedType()
	 */
	@Override
	public UUID getInheritedType()
	{
		return super.getUuid();
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Item#getName()
	 */
	@Override
	public String getName() 
	{
		return "FilmLink";
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Link#getFieldRef()
	 */
	@Override
	public ArrayList<FieldRef> getFieldRef() {
		
		ArrayList<FieldRef> fieldRefList = super.getFieldRef();
		
		FieldRef fieldRef = new Quality();
		fieldRefList.add(fieldRef);
		
		fieldRef = new Language();
		fieldRefList.add(fieldRef);
		
		fieldRef = new Server();
		fieldRefList.add(fieldRef);
		
		return fieldRefList;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Link#getDestinationPath()
	 */
	@Override
	public String getDestinationPath() 
	{
		return "./Contents/Films/FilmLink.xml";
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.contents.Link#init()
	 */
	@Override
	public void init() 
	{
		super.init();
		
		this.setUuid(UUID.fromString("0c8b7919-75cb-445c-be1f-89c72a90f739"));
		this.setInherited(true);
	}
}
