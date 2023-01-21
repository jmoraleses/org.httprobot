/**
 * 
 */
package org.httprobot.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.config.Configuration;
import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.common.datatypes.DataSource;
import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.core.contents.xml.Document;
import org.httprobot.core.contents.xml.DocumentItem;
import org.httprobot.core.contents.xml.DocumentItemList;
import org.httprobot.core.contents.xml.Image;
import org.httprobot.core.contents.xml.Item;
import org.httprobot.core.contents.xml.ItemList;
import org.httprobot.core.contents.xml.Link;
import org.httprobot.core.contents.xml.fields.Created;
import org.httprobot.core.contents.xml.fields.CreatedBy;
import org.httprobot.core.contents.xml.fields.HttpAddress;
import org.httprobot.core.contents.xml.fields.Modified;
import org.httprobot.core.contents.xml.fields.ModifiedBy;
import org.httprobot.core.contents.xml.fields.StringBase64;
import org.httprobot.core.contents.xml.films.Film;
import org.httprobot.core.contents.xml.films.FilmLink;
import org.httprobot.core.contents.xml.films.FilmLinks;
import org.httprobot.core.contents.xml.films.fields.Cover;
import org.httprobot.core.contents.xml.films.fields.Genres;
import org.httprobot.core.contents.xml.films.fields.Language;
import org.httprobot.core.contents.xml.films.fields.Quality;
import org.httprobot.core.contents.xml.films.fields.Server;
import org.httprobot.core.contents.xml.films.fields.Sinopsis;
import org.httprobot.core.contents.xml.films.fields.Title;
import org.httprobot.server.datasources.DivxTotal;
import org.httprobot.server.datasources.PeliculasPepito;
import org.httprobot.server.datasources.PeliculasYonkis;

/**
 * {@link RobotSource}'s configuration class. Inherit {@link Configuration}.
 * <br>
 * <h1>Destination default path: ./config.xml</h1>
 * @author joan
 *
 */
@XmlRootElement
public class SourceConfig extends Configuration 
{
	/**
	 * -4585664172754453229L
	 */
	private static final long serialVersionUID = -4585664172754453229L;

	static SourceConfig sourceConfig;	
	/* (non-Javadoc)
	 * @see org.httprobot.common.config.Configuration#getDataSource()
	 */
	@Override
	public ArrayList<DataSource> getDataSource() {
		
		ArrayList<DataSource> dataSources = new ArrayList<DataSource>();
		dataSources.add(new DivxTotal());
		dataSources.add(new PeliculasYonkis());
		dataSources.add(new PeliculasPepito());
		return dataSources;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.config.Configuration#getContentTypeRoot()
	 */
	@Override
	public ContentTypeRoot getContentTypeRoot()
	{
		ContentTypeRoot contentTypes = new ContentTypeRoot();
		
		contentTypes.setUuid(null);
		contentTypes.setInherited(true);
		
		contentTypes.getContentType().add(new Item());
		contentTypes.getContentType().add(new Document());
		contentTypes.getContentType().add(new ItemList());
		contentTypes.getContentType().add(new DocumentItem());
		contentTypes.getContentType().add(new DocumentItemList());
		contentTypes.getContentType().add(new Film());
		contentTypes.getContentType().add(new Link());
		contentTypes.getContentType().add(new FilmLink());
		contentTypes.getContentType().add(new FilmLinks());
		contentTypes.getContentType().add(new Image());
		
		contentTypes.getFieldRef().add(new Created());
		contentTypes.getFieldRef().add(new CreatedBy());
		contentTypes.getFieldRef().add(new HttpAddress());
		contentTypes.getFieldRef().add(new Modified());
		contentTypes.getFieldRef().add(new ModifiedBy());
		contentTypes.getFieldRef().add(new StringBase64());
		contentTypes.getFieldRef().add(new Cover());
		contentTypes.getFieldRef().add(new Genres());
		contentTypes.getFieldRef().add(new Sinopsis());
		contentTypes.getFieldRef().add(new Title());
		contentTypes.getFieldRef().add(new Language());
		contentTypes.getFieldRef().add(new Quality());
		contentTypes.getFieldRef().add(new Server());

		return contentTypes;
	}
	/**
	 * {@link RobotSource} configuration default class constructor,
	 */
	public SourceConfig() 
	{
		this.setUuid(null);
		this.setInherited(false);
		this.setRuntimeOptions(RuntimeOptions.DEFAULT_DEBUG);
	}
	/**
	 * Initializes source configuration file and writes to file on default path
	 * @param args <i>Optional</i> destination path. Default path if null.
	 */
	public static void main(String[] args) 
	{
		String path = "config.xml";
		
		for(String argString : args)
		{
			path = argString;
		}
		
		sourceConfig = new SourceConfig();
		sourceConfig.setDestinationPath(path);
		File file = new File(sourceConfig.getDestinationPath());
		WriteSourceConfig(file, sourceConfig);
	}
	/**
	 * Writes configuration to specified file
	 */
	public static void WriteSourceConfig(File file, SourceConfig config) 
	{
		try 
		{			
			OutputStream os = new FileOutputStream(file);
			try 
			{
				config.marshal(os);
			} 
			catch (JAXBException e) 
			{
				e.printStackTrace();
			} 
			catch (InconsistenMessageException e) 
			{
				e.printStackTrace();
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}	
}