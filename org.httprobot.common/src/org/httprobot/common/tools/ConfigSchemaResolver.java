/**
 * 
 */
package org.httprobot.common.tools;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import org.httprobot.common.config.Configuration;

/**
 * @author Joan
 *
 */
public class ConfigSchemaResolver extends SchemaOutputResolver {

	/**
	 * 
	 */
	public ConfigSchemaResolver() {
	}

	public static void main(String[] args) 
	{	
		try 
		{
			JAXBContext jaxbContext = JAXBContext.newInstance(Configuration.class);
			SchemaOutputResolver sor = new ConfigSchemaResolver();
			sor.createOutput("http://httprobot/rml/config/", "ConfigSchema.xsd");
			jaxbContext.generateSchema(sor);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		} 
		catch (JAXBException e) 
		{
			e.printStackTrace();
		}		
	}
	
	/* (non-Javadoc)
	 * @see javax.xml.bind.SchemaOutputResolver#createOutput(java.lang.String, java.lang.String)
	 */
	@Override
	public Result createOutput(String namespaceURI, String suggestedFileName) throws IOException {
		File file = new File(suggestedFileName);
        StreamResult result = new StreamResult(file);
        result.setSystemId(file.toURI().toURL().toString());
        return result;
	}

}
