/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 1997-2012 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package com.sun.tools.ws.wscompile;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.ws.processor.generator.GeneratorExtension;
import com.sun.tools.ws.resources.ConfigurationMessages;
import com.sun.tools.ws.resources.WscompileMessages;
import com.sun.tools.ws.util.ForkEntityResolver;
import com.sun.tools.ws.wsdl.document.jaxws.JAXWSBindingsConstants;
import com.sun.tools.ws.wsdl.document.schema.SchemaConstants;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.SpecVersion;
import com.sun.tools.xjc.api.XJC;
import com.sun.tools.xjc.reader.Util;
import com.sun.xml.ws.api.streaming.XMLStreamReaderFactory;
import com.sun.xml.ws.streaming.XMLStreamReaderUtil;
import com.sun.xml.ws.util.ServiceFinder;
import com.sun.xml.ws.util.JAXWSUtils;
import com.sun.xml.ws.util.xml.XmlUtil;
import org.w3c.dom.Element;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.LocatorImpl;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;

/**
 * @author Vivek Pandey
 */
public class WsimportOptions extends Options {
    /**
     * -wsdlLocation
     */
    public String wsdlLocation;

    /**
     * Actually stores {@link com.sun.org.apache.xml.internal.resolver.tools.CatalogResolver}, but the field
     * type is made to {@link org.xml.sax.EntityResolver} so that XJC can be
     * used even if resolver.jar is not available in the classpath.
     */
    public EntityResolver entityResolver = null;

    /**
     * The -p option that should control the default Java package that
     * will contain the generated code. Null if unspecified.
     */
    public String defaultPackage = null;

    /**
     * The -clientjar option to package client artifacts as jar
     */
    public String clientjar = null;

    /**
     * -XadditionalHeaders
     */
    public boolean additionalHeaders;
    
    /**
     * The option indicates the dir where the jwsImpl will be generated. 
     */
    public File implDestDir = null;
    
    /**
     * optional, generated impl file only for the ordered serviceName
     * Note: It is a QName string, formatted as: "{" + Namespace URI + "}" + local part
     */
    public String implServiceName = null;
    
    /**
     * optional, generated impl file only for the ordered portName
     * Note: It is a QName string, formatted as: "{" + Namespace URI + "}" + local part
     */
    public String implPortName = null;
    
    /**
     * optional, if true JWS file is generated
     */
    public boolean isGenerateJWS = false;
    
    /**
     * Setting disableSSLHostVerification to true disables the SSL Hostname verification while fetching the wsdls.
     * -XdisableSSLHostVerification
     */
    public boolean disableSSLHostnameVerification;

    /**
     * Setting useBaseResourceAndURLToLoadWSDL to true causes generated Service classes to load the WSDL file from
     * a URL generated from the base resource.
     * -XuseBaseResourceAndURLToLoadWSDL
     */
    public boolean useBaseResourceAndURLToLoadWSDL = false;

    /**
     * JAXB's {@link SchemaCompiler} to be used for handling the schema portion.
     * This object is also configured through options.
     */
    private SchemaCompiler schemaCompiler = XJC.createSchemaCompiler();

    /**
     * Authentication file
     */
    public File authFile;

    /**
     * Setting disableAuthenticator to true disables the DefaultAuthenticator.
     * -XdisableAuthenticator
     */
    public boolean disableAuthenticator;

    /**
     * Additional arguments
     */
    public HashMap<String, String> extensionOptions = new HashMap<String, String>();

    /**
     * All discovered {@link Plugin}s.
     * This is lazily parsed, so that we can take '-cp' option into account.
     *
     * @see #getAllPlugins()
     */
    private List<Plugin> allPlugins;

    /**
     * {@link Plugin}s that are enabled in this compilation.
     */
    public final List<Plugin> activePlugins = new ArrayList<Plugin>();

    public JCodeModel getCodeModel() {
        if(codeModel == null)
            codeModel = new JCodeModel();
        return codeModel;
    }

    public SchemaCompiler getSchemaCompiler() {
        schemaCompiler.setTargetVersion(SpecVersion.parse(target.getVersion()));
        if(entityResolver != null) {
            //set if its not null so as not to override catalog option specified via xjc args
            schemaCompiler.setEntityResolver(entityResolver);
        }
        return schemaCompiler;
    }

    public void setCodeModel(JCodeModel codeModel) {
        this.codeModel = codeModel;
    }

    private JCodeModel codeModel;

    /**
     * This captures jars passed on the commandline and passes them to XJC and puts them in the classpath for compilation
     */
    public List<String> cmdlineJars = new ArrayList<String>();

    /**
     * Gets all the {@link Plugin}s discovered so far.
     *
     * <p>
     * A plugins are enumerated when this method is called for the first time,
     * by taking {@link #classpath} into account. That means
     * "-cp plugin.jar" has to come before you specify options to enable it.
     */
    public List<Plugin> getAllPlugins() {
        if(allPlugins==null) {
            allPlugins = new ArrayList<Plugin>();
            allPlugins.addAll(Arrays.asList(findServices(Plugin.class, getClassLoader())));
        }
        return allPlugins;
    }

    /**
     * Parses arguments and fill fields of this object.
     *
     * @exception BadCommandLineException
     *      thrown when there's a problem in the command-line arguments
     */
    @Override
    public final void parseArguments( String[] args ) throws BadCommandLineException {

        for (int i = 0; i < args.length; i++) {
            if(args[i].length()==0)
                throw new BadCommandLineException();
            if (args[i].charAt(0) == '-') {
                int j = parseArguments(args,i);
                if(j==0)
                    throw new BadCommandLineException(WscompileMessages.WSCOMPILE_INVALID_OPTION(args[i]));
                i += (j-1);
            } else {
                if(args[i].endsWith(".jar")) {

                    try {
                cmdlineJars.add(args[i]);
                schemaCompiler.getOptions().scanEpisodeFile(new File(args[i]));

            } catch (com.sun.tools.xjc.BadCommandLineException e) {
                //Driver.usage(jaxbOptions,false);
                throw new BadCommandLineException(e.getMessage(), e);
            }
                } else{
                    addFile(args[i]);
                }
            }
        }
        if(destDir == null)
            destDir = new File(".");
        if(sourceDir == null)
            sourceDir = destDir;
    }

    /** -Xno-addressing-databinding option to disable addressing namespace data binding. This is
     * experimental switch and will be working as a temporary workaround till
     * jaxb can provide a better way to selelctively disable compiling of an
     * schema component.
     * **/
    public boolean noAddressingBbinding;

    @Override
    public int parseArguments(String[] args, int i) throws BadCommandLineException {
        int j = super.parseArguments(args ,i);
        if(j>0) return j;   // understood by the super class

        if (args[i].equals("-b")) {
            addBindings(requireArgument("-b", args, ++i));
            return 2;
        } else if (args[i].equals("-wsdllocation")) {
            wsdlLocation = requireArgument("-wsdllocation", args, ++i);
            return 2;
        } else if (args[i].equals("-XadditionalHeaders")) {
            additionalHeaders = true;
            return 1;
        } else if (args[i].equals("-XdisableSSLHostnameVerification")) {
            disableSSLHostnameVerification = true;
            return 1;
        } else if (args[i].equals("-p")) {
            defaultPackage = requireArgument("-p", args, ++i);
            return 2;
        } else if (args[i].equals("-catalog")) {
            String catalog = requireArgument("-catalog", args, ++i);
            try {
                if (entityResolver == null) {
                    if (catalog != null && catalog.length() > 0)
                        entityResolver = XmlUtil.createEntityResolver(JAXWSUtils.getFileOrURL(JAXWSUtils.absolutize(Util.escapeSpace(catalog))));
                } else if (catalog != null && catalog.length() > 0) {
                    EntityResolver er = XmlUtil.createEntityResolver(JAXWSUtils.getFileOrURL(JAXWSUtils.absolutize(Util.escapeSpace(catalog))));
                    entityResolver = new ForkEntityResolver(er, entityResolver);
                }
            } catch (IOException e) {
                throw new BadCommandLineException(WscompileMessages.WSIMPORT_FAILED_TO_PARSE(catalog, e.getMessage()));
            }
            return 2;
        } else if (args[i].startsWith("-httpproxy:")) {
            String value = args[i].substring(11);
            if (value.length() == 0) {
                throw new BadCommandLineException(WscompileMessages.WSCOMPILE_INVALID_OPTION(args[i]));
            }
            int index = value.indexOf(':');
            if (index == -1) {
                System.setProperty("proxySet", "true");
                System.setProperty("proxyHost", value);
                System.setProperty("proxyPort", "8080");
            } else {
                System.setProperty("proxySet", "true");
                System.setProperty("proxyHost", value.substring(0, index));
                System.setProperty("proxyPort", value.substring(index + 1));
            }
            return 1;
        } else if (args[i].equals("-Xno-addressing-databinding")) {
            noAddressingBbinding = true;
            return 1;
        } else if (args[i].startsWith("-B")) {
            // JAXB option pass through.
            String[] subCmd = new String[args.length-i];
            System.arraycopy(args,i,subCmd,0,subCmd.length);
            subCmd[0] = subCmd[0].substring(2); // trim off the first "-B"

            com.sun.tools.xjc.Options jaxbOptions = schemaCompiler.getOptions();
            try {
                int r = jaxbOptions.parseArgument(subCmd, 0);
                if(r==0) {
                    //Driver.usage(jaxbOptions,false);
                    throw new BadCommandLineException(WscompileMessages.WSIMPORT_NO_SUCH_JAXB_OPTION(subCmd[0]));
                }
                return r;
            } catch (com.sun.tools.xjc.BadCommandLineException e) {
                //Driver.usage(jaxbOptions,false);
                throw new BadCommandLineException(e.getMessage(),e);
            }
        } else if (args[i].equals("-Xauthfile")) {
            String authfile = requireArgument("-Xauthfile", args, ++i);
            authFile = new File(authfile);
            return 2;
        } else if (args[i].equals("-clientjar")) {
            clientjar = requireArgument("-clientjar", args, ++i);
            return 2;
        } else if (args[i].equals("-implDestDir")) {
        		implDestDir = new File(requireArgument("-implDestDir", args, ++i));
            if (!implDestDir.exists())
              throw new BadCommandLineException(WscompileMessages.WSCOMPILE_NO_SUCH_DIRECTORY(implDestDir.getPath()));
        		return 2;
        } else if (args[i].equals("-implServiceName")) {
        	implServiceName = requireArgument("-implServiceName", args, ++i);
          return 2;
        } else if (args[i].equals("-implPortName")) {
      		implPortName = requireArgument("-implPortName", args, ++i);
          return 2;
        } else if (args[i].equals("-generateJWS")) {
            isGenerateJWS = true;
            return 1;
        } else if (args[i].equals("-XuseBaseResourceAndURLToLoadWSDL")) {
        	useBaseResourceAndURLToLoadWSDL = true;
            return 1;
        } else if (args[i].equals("-XdisableAuthenticator")) {
            disableAuthenticator = true;
            return 1;
        }

        // handle additional options
        for (GeneratorExtension f:ServiceFinder.find(GeneratorExtension.class)) {
            if (f.validateOption(args[i])) {
                extensionOptions.put(args[i], requireArgument(args[i], args, ++i));
                return 2;
            }
        }

        // see if this is one of the extensions
        for( Plugin plugin : getAllPlugins() ) {
            try {
                if(('-' + plugin.getOptionName()).equals(args[i])) {
                    activePlugins.add(plugin);
                    plugin.onActivated(this);
                    return 1;
                }
                int r = plugin.parseArgument(this, args, i);
                if (r != 0) {
                    return r;
                }
            } catch (IOException e) {
                throw new BadCommandLineException(e.getMessage(),e);
            }
        }

        return 0; // what's this option?
    }

    public void validate() throws BadCommandLineException {
        if (wsdls.isEmpty()) {
            throw new BadCommandLineException(WscompileMessages.WSIMPORT_MISSING_FILE());
        }

        if(wsdlLocation !=null && clientjar != null) {
           throw new BadCommandLineException(WscompileMessages.WSIMPORT_WSDLLOCATION_CLIENTJAR());
        }
        if(wsdlLocation == null){
            wsdlLocation = wsdls.get(0).getSystemId();
        }


    }

    @Override
    protected void addFile(String arg) throws BadCommandLineException {
        addFile(arg, wsdls, ".wsdl");
    }

    private final List<InputSource> wsdls = new ArrayList<InputSource>();
    private final List<InputSource> schemas = new ArrayList<InputSource>();
    private final List<InputSource> bindingFiles = new ArrayList<InputSource>();
    private final List<InputSource> jaxwsCustomBindings = new ArrayList<InputSource>();
    private final List<InputSource> jaxbCustomBindings = new ArrayList<InputSource>();
    private final List<Element> handlerConfigs = new ArrayList<Element>();

    /**
     * There is supposed to be one handler chain per generated SEI.
     * TODO: There is possible bug, how to associate a @HandlerChain
     * with each port on the generated SEI. For now lets preserve the JAXWS 2.0 FCS
     * behaviour and generate only one @HandlerChain on the SEI
     */
    public Element getHandlerChainConfiguration(){
        if(handlerConfigs.size() > 0)
            return handlerConfigs.get(0);
        return null;
    }

    public void addHandlerChainConfiguration(Element config){
        handlerConfigs.add(config);
    }

    public InputSource[] getWSDLs() {
        return wsdls.toArray(new InputSource[wsdls.size()]);
    }

    public InputSource[] getSchemas() {
        return schemas.toArray(new InputSource[schemas.size()]);
    }

    public InputSource[] getWSDLBindings() {
        return jaxwsCustomBindings.toArray(new InputSource[jaxwsCustomBindings.size()]);
    }

    public InputSource[] getSchemaBindings() {
        return jaxbCustomBindings.toArray(new InputSource[jaxbCustomBindings.size()]);
    }

    public void addWSDL(File source) {
        addWSDL(fileToInputSource(source));
    }

    public void addWSDL(InputSource is) {
        wsdls.add(absolutize(is));
    }

    public void addSchema(File source) {
        addSchema(fileToInputSource(source));
    }

    public void addSchema(InputSource is) {
        schemas.add(is);
    }

    private InputSource fileToInputSource(File source) {
        try {
            String url = source.toURL().toExternalForm();
            return new InputSource(Util.escapeSpace(url));
        } catch (MalformedURLException e) {
            return new InputSource(source.getPath());
        }
    }

    /**
     * Recursively scan directories and add all XSD files in it.
     */
    public void addGrammarRecursive(File dir) {
        addRecursive(dir, ".wsdl", wsdls);
        addRecursive(dir, ".xsd", schemas);
    }

    /**
     * Adds a new input schema.
     */
    public void addWSDLBindFile(InputSource is) {
        jaxwsCustomBindings.add(new RereadInputSource(absolutize(is)));
    }

    public void addSchemmaBindFile(InputSource is) {
        jaxbCustomBindings.add(new RereadInputSource(absolutize(is)));
    }

    private void addRecursive(File dir, String suffix, List<InputSource> result) {
        File[] files = dir.listFiles();
        if (files == null) return; // work defensively

        for (File f : files) {
            if (f.isDirectory())
                addRecursive(f, suffix, result);
            else if (f.getPath().endsWith(suffix))
                result.add(absolutize(fileToInputSource(f)));
        }
    }

    private InputSource absolutize(InputSource is) {
        // absolutize all the system IDs in the input,
        // so that we can map system IDs to DOM trees.
        try {
            URL baseURL = new File(".").getCanonicalFile().toURL();
            is.setSystemId(new URL(baseURL, is.getSystemId()).toExternalForm());
        } catch (IOException e) {
            // ignore
        }
        return is;
    }

    public void addBindings(String name) throws BadCommandLineException {
        addFile(name, bindingFiles, null);
    }

    /**
     * Parses a token to a file (or a set of files)
     * and add them as {@link InputSource} to the specified list.
     *
     * @param suffix If the given token is a directory name, we do a recusive search
     *               and find all files that have the given suffix.
     */
    private void addFile(String name, List<InputSource> target, String suffix) throws BadCommandLineException {
        Object src;
        try {
            src = Util.getFileOrURL(name);
        } catch (IOException e) {
            throw new BadCommandLineException(WscompileMessages.WSIMPORT_NOT_A_FILE_NOR_URL(name));
        }
        if (src instanceof URL) {
            target.add(absolutize(new InputSource(Util.escapeSpace(((URL) src).toExternalForm()))));
        } else {
            File fsrc = (File) src;
            if (fsrc.isDirectory()) {
                addRecursive(fsrc, suffix, target);
            } else {
                target.add(absolutize(fileToInputSource(fsrc)));
            }
        }
    }


    /**
     * Exposing it as a public method to allow external tools such as NB to read from wsdl model and work on it.
     * TODO: WSDL model needs to be exposed - basically at tool time we need to use the runtimw wsdl model
     *
     * Binding files could be jaxws or jaxb. This method identifies jaxws and jaxb binding files and keeps them separately. jaxb binding files are given separately
     * to JAXB in {@link com.sun.tools.ws.processor.modeler.wsdl.JAXBModelBuilder}
     *
     * @param receiver {@link ErrorReceiver}
     */
    public final void parseBindings(ErrorReceiver receiver){
        for (InputSource is : bindingFiles) {
            XMLStreamReader reader =
                    XMLStreamReaderFactory.create(is,true);
            XMLStreamReaderUtil.nextElementContent(reader);
            if (reader.getName().equals(JAXWSBindingsConstants.JAXWS_BINDINGS)) {
                jaxwsCustomBindings.add(new RereadInputSource(is));
            } else if (reader.getName().equals(JAXWSBindingsConstants.JAXB_BINDINGS) ||
                    reader.getName().equals(new QName(SchemaConstants.NS_XSD, "schema"))) {
                jaxbCustomBindings.add(new RereadInputSource(is));
            } else {
                LocatorImpl locator = new LocatorImpl();
                locator.setSystemId(reader.getLocation().getSystemId());
                locator.setPublicId(reader.getLocation().getPublicId());
                locator.setLineNumber(reader.getLocation().getLineNumber());
                locator.setColumnNumber(reader.getLocation().getColumnNumber());
                receiver.warning(locator, ConfigurationMessages.CONFIGURATION_NOT_BINDING_FILE(is.getSystemId()));
            }
        }
    }
 
    /**
     * Get extension argument 
     */
    public String getExtensionOption(String argument) {
        return extensionOptions.get(argument);
    }

    /**
     * Looks for all "META-INF/services/[className]" files and
     * create one instance for each class name found inside this file.
     */
    private static <T> T[] findServices(Class<T> clazz, ClassLoader classLoader) {
        ServiceFinder<T> serviceFinder = ServiceFinder.find(clazz, classLoader);
        List<T> r = new ArrayList<T>();
        for (T t : serviceFinder) {
            r.add(t);
        }
        return r.toArray((T[]) Array.newInstance(clazz, r.size()));
    }

    private static final class ByteStream extends ByteArrayOutputStream {
    	byte[] getBuffer() {
    		return buf;
    	}
    }
    
    private static final class RereadInputStream extends InputStream {
    	private InputStream is;
    	private ByteStream bs;
    	
    	RereadInputStream(InputStream is) {
    		this.is = is;
    		this.bs = new ByteStream();
    	}
    	
		@Override
		public int available() throws IOException {
			return is.available();
		}

		@Override
		public void close() throws IOException {
			if (bs != null) {
				InputStream i = new ByteArrayInputStream(bs.getBuffer());
				bs = null;
				is.close();
				is = i;
			}
		}

		@Override
		public synchronized void mark(int readlimit) {
			is.mark(readlimit);
		}

		@Override
		public boolean markSupported() {
			return is.markSupported();
		}

		@Override
		public int read() throws IOException {
			int r = is.read();
			if (bs != null)
				bs.write(r);
			return r;
		}

		@Override
		public int read(byte[] b, int off, int len) throws IOException {
			int r = is.read(b, off, len);
			if (r > 0 && bs != null)
				bs.write(b, off, r);
			return r;
		}

		@Override
		public int read(byte[] b) throws IOException {
			int r = is.read(b);
			if (r > 0 && bs != null)
				bs.write(b, 0, r);
			return r;
		}

		@Override
		public synchronized void reset() throws IOException {
			is.reset();
		}
    }
    
    private static final class RereadInputSource extends InputSource {
    	private InputSource is;
    	
    	RereadInputSource(InputSource is) {
    		this.is = is;
    	}
    	
		@Override
		public InputStream getByteStream() {
			InputStream i = is.getByteStream();
			if (i != null && !(i instanceof RereadInputStream)) {
				i = new RereadInputStream(i);
				is.setByteStream(i);
			}
			return i;
		}

		@Override
		public Reader getCharacterStream() {
			// TODO Auto-generated method stub
			return is.getCharacterStream();
		}

		@Override
		public String getEncoding() {
			return is.getEncoding();
		}

		@Override
		public String getPublicId() {
			return is.getPublicId();
		}

		@Override
		public String getSystemId() {
			return is.getSystemId();
		}

		@Override
		public void setByteStream(InputStream byteStream) {
			is.setByteStream(byteStream);
		}

		@Override
		public void setCharacterStream(Reader characterStream) {
			is.setCharacterStream(characterStream);
		}

		@Override
		public void setEncoding(String encoding) {
			is.setEncoding(encoding);
		}

		@Override
		public void setPublicId(String publicId) {
			is.setPublicId(publicId);
		}

		@Override
		public void setSystemId(String systemId) {
			is.setSystemId(systemId);
		}
    }
}
