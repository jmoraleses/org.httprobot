package org.httprobot.server.datasources;

import java.util.ArrayList;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.contents.ContentTypeRef;
import org.httprobot.common.datatypes.DataSource;
import org.httprobot.common.datatypes.Document;
import org.httprobot.common.datatypes.Field;
import org.httprobot.common.datatypes.FieldRoot;
import org.httprobot.common.datatypes.DocumentRoot;
import org.httprobot.common.definitions.Enums.ParameterType;
import org.httprobot.common.definitions.Enums.HttpRequestType;
import org.httprobot.common.definitions.Enums.DataType;
import org.httprobot.common.params.BannedWord;
import org.httprobot.common.params.Constant;
import org.httprobot.common.params.ServerUrl;
import org.httprobot.common.params.StartUrl;
import org.httprobot.common.placeholders.HtmlUnit;
import org.httprobot.common.placeholders.HttpAddress;
import org.httprobot.common.placeholders.operators.Contains;
import org.httprobot.common.placeholders.operators.EndIndex;
import org.httprobot.common.placeholders.operators.Remove;
import org.httprobot.common.placeholders.operators.Replace;
import org.httprobot.common.placeholders.operators.StartIndex;
import org.httprobot.common.unit.Action;
import org.httprobot.common.unit.Paginator;
import org.httprobot.common.unit.WebOptions;

import com.gargoylesoftware.htmlunit.BrowserVersion;

/**
 * @author joan
 *
 */
@XmlRootElement
public class PeliculasYonkis extends DataSource
{
	/**
	 * 6048895472159763778L
	 */
	private static final long serialVersionUID = 6048895472159763778L;
	
	/**
	 * 
	 */
	public PeliculasYonkis() 
	{
		super();
		
		this.setInherited(true);
		this.setUuid(UUID.fromString("a0597f59-6192-4ae9-9e13-b74946866dae"));
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.datatypes.DataSource#getServerName()
	 */
	@Override
	public String getSourceName() {
		return "peliculasYonkis";
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.datatypes.DataSource#getServerUrl()
	 */
	@Override
	public ServerUrl getServerUrl() 
	{		
		ServerUrl serverUrl = new ServerUrl();
		serverUrl.setUuid(null);
		serverUrl.setValue("http://www.peliculasyonkis.com/");
		serverUrl.setParamName("[@server_url]");
		
		return serverUrl;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.datatypes.DataSource#getStartUrl()
	 */
	@Override
	public StartUrl getStartUrl()
	{
		StartUrl startUrl = new StartUrl();
		startUrl.setUuid(null);
		startUrl.setInherited(true);
		startUrl.setValue("lista-de-peliculas/");
		startUrl.setParamName("[@start_url]");
		
		return startUrl;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.datatypes.DataSource#getDocumentRoot()
	 */
	@Override
	public DocumentRoot getDocumentRoot() {
		DocumentRoot steps = new DocumentRoot();
		steps.setUuid(null);
		steps.setInherited(true);
		Document step = new Document();
		step.setUuid(null);
		step.setInherited(true);
		Document step2 = new Document();
		step2.setUuid(null);
		step2.setInherited(true);
		
		Action action = new Action();
		action.setUuid(null);
		action.setStrictMode(true);
		action.setHttpAddress("[@server_url][@start_url][@action]");
//		action.setType(RmlRequestType.GET);
		
		
		BannedWord bword = new BannedWord();
		bword.setUuid(null);
		bword.setParamName("[@bword]");
		bword.setParamType(ParameterType.BANNED_WORD);
		bword.setValue("genero-");
		
		ArrayList<BannedWord> bword_list = new ArrayList<BannedWord>();
		bword_list.add(bword);		
		action.setBwords(bword_list);
		
		WebOptions webOptions2 = new WebOptions();
		webOptions2.setActiveXNativeEnabled(false);
		webOptions2.setAppletEnabled(false);
		webOptions2.setPopupBlockerEnabled(false);
		webOptions2.setCssEnabled(false);
		webOptions2.setJavaScriptEnabled(false);
		webOptions2.setPeriodTime(5000);
		webOptions2.setPaginatorEnabled(true);
		webOptions2.setBrowserVersion(BrowserVersion.FIREFOX_17);
		webOptions2.setType(HttpRequestType.GET);
		
		Paginator paginator = new Paginator();
		paginator.setAnchorValue(">");
		
		webOptions2.setPaginator(paginator);
		action.setWebOptions(webOptions2);
		
		Action action2 = new Action();
		action2.setUuid(null);
		action2.setHttpAddress("[@server_url][@constant][@action]");
		action2.setStrictMode(false);
//		action.setType(RmlRequestType.GET);
		action2.setWebOptions(this.getAction().getWebOptions());
		
		Constant constant = new Constant();
		constant.setUuid(null);
		constant.setParamName("[@constant]");
		constant.setParamType(ParameterType.CONSTANT);
		constant.setValue("pelicula/");
		
		ArrayList<Constant> constants_list = new ArrayList<Constant>();
		constants_list.add(constant);
		
		action2.setConstants(constants_list);
		
		FieldRoot fields = new FieldRoot();
		fields.setUuid(null);
		fields.setInherited(true);
		
		FieldRoot fields2 = new FieldRoot();
		fields2.setUuid(null);
		fields2.setInherited(true);
		
		ArrayList<Field> field_list = new ArrayList<Field>();
		Field field = new Field();
		field.setFieldName("DV_FIELDNAME_1");
		field.setFieldType(DataType.TEXT.toString());
		
		ArrayList<Field> field_list2 = new ArrayList<Field>();
		Field field2 = new Field();
		field2.setFieldName("DV_FIELDNAME_2");
		field2.setFieldType(DataType.LINK.toString());
				
		HttpAddress httpRequest = new HttpAddress();
		httpRequest.setUuid(null);
		httpRequest.setInherited(true);
		
		HtmlUnit htmlBody = new HtmlUnit();
		htmlBody.setUuid(null);
		htmlBody.setInherited(true);
		
		ArrayList<Contains> contains_list = new ArrayList<Contains>();
		Contains contains = new Contains();
		contains.setUuid(null);
		contains.setInherited(true);
		contains.setValue("OLA");
		contains_list.add(contains);
		
		ArrayList<Replace> replace_list = new ArrayList<Replace>();
		Replace replace = new Replace();
		replace.setNewString("HOLA");
		replace.setOldString("ADEU");
		replace.setInherited(true);
		replace.setUuid(null);
		replace_list.add(replace);
		
		
		ArrayList<Remove> remove_list = new ArrayList<Remove>();		
		Remove remove = new Remove();
		remove.setUuid(null);
		remove.setInherited(true);
		EndIndex endindex = new EndIndex();
		endindex.setStringValue("holalalalala");
		StartIndex startIndex = new StartIndex();
		startIndex.setStringValue("Adeueueueueueu");
		
		remove.setEndIndex(endindex);
		remove.setStartIndex(startIndex);
		remove_list.add(remove);
		
		httpRequest.setRemove(remove);
		field2.setHttpAddress(httpRequest);
		
		field_list2.add(field2);
		
		htmlBody.setReplace(replace);
		htmlBody.setContains(contains);
		
		httpRequest.setRemove(remove);
		
		field.setHtmlUnit(htmlBody);
		
		field_list.add(field);		
		field_list.add(field2);
		
		fields.setField(field_list);
		fields2.setField(field_list2);
		
		step.setAction(action);
		step.setFieldRoot(fields);
		
		step2.setFieldRoot(fields2);
		step2.setAction(action2);
		
		step.setDocument(step2);
		
		ArrayList<Document> step_list = new ArrayList<Document>();
		step_list.add(step);
//		step_list.add(step2);
		
		Document step3 = new Document();
		step3.setUuid(null);
		step3.setInherited(true);
		Action action3 = new Action();
		action3.setUuid(null);
		action3.setInherited(true);
		
		action3.setHttpAddress("[@server_url][@constant][@action]");
		
		Constant constant2 = new Constant();
		constant2.setUuid(null);
		constant2.setParamName("[@constant]");
		constant2.setParamType(ParameterType.CONSTANT);
		constant2.setValue("pelicula/");
		
		ArrayList<Constant> constants_list2 = new ArrayList<Constant>();
		constants_list2.add(constant2);
		
		action3.setConstants(constants_list2);
		
		WebOptions webOptions3 = new WebOptions();
		webOptions3.setUuid(null);
		webOptions3.setInherited(true);
		
		webOptions3.setActiveXNativeEnabled(false);
		webOptions3.setAppletEnabled(false);
		webOptions3.setPopupBlockerEnabled(false);
		webOptions3.setCssEnabled(false);
		webOptions3.setJavaScriptEnabled(false);
		webOptions3.setPeriodTime(5000);
		webOptions3.setPaginatorEnabled(false);
		webOptions3.setBrowserVersion(BrowserVersion.FIREFOX_17);
		webOptions3.setType(HttpRequestType.GET);
		
		FieldRoot fields3 = new FieldRoot();
		fields3.setUuid(null);
		fields3.setInherited(true);
		ArrayList<Field> field_list3 = new ArrayList<Field>();
		
		HtmlUnit htmlBody2 = new HtmlUnit();
		htmlBody2.setUuid(null);
		htmlBody2.setInherited(true);
		
		ArrayList<Contains> contains_list2 = new ArrayList<Contains>();
		Contains contains2 = new Contains();
		contains.setUuid(null);
		contains.setInherited(true);
		contains.setValue("OLA");
		
		contains_list2.add(contains2);
		
		Field field3 = new Field();
		field3.setUuid(null);
		field3.setInherited(true);
		field3.setHtmlUnit(htmlBody2);
		
		fields3.setField(field_list3);
		action3.setWebOptions(webOptions3);
		step3.setAction(action3);
		
		step2.setDocument(step3);

		steps.setDocument(step_list);
		
		return steps;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.datatypes.DataSource#getAction()
	 */
	@Override
	public Action getAction() 
	{
		Action action = new Action();
		action.setUuid(null);
		action.setHttpAddress("[@server_url][@start_url]");
		action.setStrictMode(true);
		
		WebOptions webOptions = new WebOptions();
		webOptions.setActiveXNativeEnabled(false);
		webOptions.setAppletEnabled(false);
		webOptions.setPopupBlockerEnabled(false);
		webOptions.setCssEnabled(false);
		webOptions.setJavaScriptEnabled(false);
		webOptions.setPeriodTime(5000);
		webOptions.setBrowserVersion(BrowserVersion.FIREFOX_17);
		webOptions.setPaginatorEnabled(false);
		webOptions.setType(HttpRequestType.GET);
		action.setWebOptions(webOptions);
		
		return action;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.datatypes.DataSource#getContentTypes()
	 */
	@Override
	public ContentTypeRef getContentTypeRef() {
		
		ContentTypeRef contentTypeRef = new ContentTypeRef();
		contentTypeRef.setContentTypeName("Film");
		contentTypeRef.setUuid(UUID.fromString("5e30577c-a283-4d2b-8c5d-8d56cbbc62fe"));
		contentTypeRef.setInherited(true);
		return contentTypeRef;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		System.out.println((new PeliculasYonkis()).toString());
	}
}
