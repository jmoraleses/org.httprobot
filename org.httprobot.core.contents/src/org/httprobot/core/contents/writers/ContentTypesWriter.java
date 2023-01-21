package org.httprobot.core.contents.writers;

import org.httprobot.common.contents.ContentTypeRoot;
import org.httprobot.core.contents.xml.Document;
import org.httprobot.core.contents.xml.DocumentItem;
import org.httprobot.core.contents.xml.DocumentItemList;
import org.httprobot.core.contents.xml.Image;
import org.httprobot.core.contents.xml.Item;
import org.httprobot.core.contents.xml.ItemList;
import org.httprobot.core.contents.xml.Link;
import org.httprobot.core.contents.xml.fields.Created;
import org.httprobot.core.contents.xml.fields.CreatedBy;
import org.httprobot.core.contents.xml.fields.DocID;
import org.httprobot.core.contents.xml.fields.DocName;
import org.httprobot.core.contents.xml.fields.HttpAddress;
import org.httprobot.core.contents.xml.fields.ListID;
import org.httprobot.core.contents.xml.fields.ListName;
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

public class ContentTypesWriter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ContentTypeRoot contentTypeRoot = new ContentTypeRoot();
		
		contentTypeRoot.getContentType().add(new Item());
		contentTypeRoot.getContentType().add(new Document());
		contentTypeRoot.getContentType().add(new ItemList());
		contentTypeRoot.getContentType().add(new DocumentItem());
		contentTypeRoot.getContentType().add(new DocumentItemList());
		contentTypeRoot.getContentType().add(new Film());
		contentTypeRoot.getContentType().add(new Link());
		contentTypeRoot.getContentType().add(new FilmLink());
		contentTypeRoot.getContentType().add(new FilmLinks());
		contentTypeRoot.getContentType().add(new Image());
		
		contentTypeRoot.getFieldRef().add(new Created());
		contentTypeRoot.getFieldRef().add(new CreatedBy());
		contentTypeRoot.getFieldRef().add(new HttpAddress());
		contentTypeRoot.getFieldRef().add(new Modified());
		contentTypeRoot.getFieldRef().add(new ModifiedBy());
		contentTypeRoot.getFieldRef().add(new DocID());
		contentTypeRoot.getFieldRef().add(new DocName());
		contentTypeRoot.getFieldRef().add(new ListID());
		contentTypeRoot.getFieldRef().add(new ListName());
		contentTypeRoot.getFieldRef().add(new StringBase64());
		contentTypeRoot.getFieldRef().add(new Cover());
		contentTypeRoot.getFieldRef().add(new Genres());
		contentTypeRoot.getFieldRef().add(new Sinopsis());
		contentTypeRoot.getFieldRef().add(new Title());
		contentTypeRoot.getFieldRef().add(new Language());
		contentTypeRoot.getFieldRef().add(new Quality());
		contentTypeRoot.getFieldRef().add(new Server());
		
		System.out.println(contentTypeRoot.toString());
	}
}