/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 1997-2011 Oracle and/or its affiliates. All rights reserved.
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
package processor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.opts.BooleanOption;
import org.kohsuke.args4j.opts.FileOption;
import org.w3c.dom.Document;
import processor.util.Util;

/**
 * This class is responsible for driving the Sample App processing. It
 * maintains a list of Processors that act on a directory containing sample app
 * meta data.
 * 
 * Ryan Shoemaker, Sun Microsystems, Inc.
 * 
 * @version $Revision: 1.1 $
 */
public class SampleProcessorDriver {

    // Processor to run
    private Processor processor;

    // storage for list of directories
    private List<File> dirs;

    // command line options for driver
    public FileOption rootDir = new FileOption("-dir",new File("."));
    public BooleanOption verbose = new BooleanOption("-verbose", false);
    public FileOption workDir = new FileOption("-work");

    /**
     * Constructor.
     * 
     * @param args
     *                command line args
     */
    SampleProcessorDriver(String[] args) {
        // setup the chain of processors
        processor =
            new ChainProcessor(new ValidatingProcessor(), new AntProcessor());

        // optional processors that run the generated ant build.xml and
        // compare the results
        processor = new ChainProcessor(processor, new ConditionalProcessor("-execute") {
            protected Processor createCoreProcessor() {
                return new ChainProcessor(new AntBuildProcessor(), new GoldenFileProcessor());
            }
        });

        // process cmd line options
        parseCmdLine(args);

        // samples/build.xml always sets the work dir, so this is only for SampleRunner
        // where the work dir is ../../work relative to the rootDir
        if(workDir.value == null) {
            final String pathname = rootDir.value.getPath()+"/../../work";
            workDir.value = new File(pathname);
        }
        if(!workDir.value.exists()) {
            workDir.value.mkdirs();
        }

        // store the list of dirs with non-excluded samples
        this.dirs = findMetaFiles(rootDir.value, "sample.meta");

        // copy them over to the work directory
        for(File d : dirs) {
            try {
                File target = new File(workDir.value, d.getName());
                Util.copyDirectory(d, target, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // reset the dirs to point to the work dir
        // kinda hacky
        this.dirs = findMetaFiles(workDir.value, "sample.meta");
    }

    private void parseCmdLine(String[] args) {
        // create the parser
        CmdLineParser parser = new CmdLineParser();

        // add driver options
        parser.addOptionClass(this);

        // add processor specific options
        processor.addCmdLineOptions(parser);

        try {
            parser.parse(args);
        } catch (CmdLineException cle) {
            System.err.println(cle.getMessage());
            usage();
        }
    }

    private void usage() {
        System.out.println(
            "usage: java processor.SampleProcessorDriver [-validation] [-ant JWSDP|workspace|RI] -dir <dir>");
        System.exit(-1);
    }

    private void process() {
        System.out.println("Processing sample applications");
        boolean result;
        ArrayList<File> failures = new ArrayList<File>();

        for( File currentDir : dirs ) {
            trace("Processing: " + currentDir.getAbsolutePath());
            result = processor.process(currentDir, verbose.value);
            if (result == false) {
                failures.add(currentDir);
            }
            trace("\n");
        }

        printSummary(failures);
    }

    /**
     * @param failures
     */
    private void printSummary(ArrayList<File> failures) {
        System.out.println("Summary:");
        if (failures.size() == 0) {
            System.out.println("\tALL PASS");
        } else {
            for( File failure : failures )
                System.out.println("\tFAILED: " + failure.getName());
        }
        // print out the summary line in the JUnit format
        // so that the tool can count this result with other unit tests.
        System.out.println("Tests run: "+dirs.size()+ 
                     ",  Failures: "+failures.size()+
                     ",  Errors: 0");
    }

    private void trace(String msg) {
        if (verbose.value)
            System.out.println(msg);
    }

    /**
     * Search the specified directory for the specified meta file
     * 
     * @param metaFileName
     *                the name of the meta file to search for
     * @return the meta file or null if not found
     */
    static File getMetaFile(File dir, String metaFileName) {
        if (!dir.isDirectory()) {
            System.out.println(
                "Warning: skipping '"
                    + dir.getAbsolutePath()
                    + "' - not a directory.");
            return null;
        } else {
            return new File(dir, metaFileName);
        }
    }

    /**
     * Recursively search below the specified directory for the specified meta
     * file.  This list will NOT contain meta files that have been excluded.
     * 
     * @param dir
     *                the directory to search
     * @param metaFileName
     *                the name of the meta file to search for
     * @return An ArrayList containing all of the dirs that contain non-excluded meta files
     */
    static List<File> findMetaFiles(File dir, String metaFileName) {
        ArrayList<File> result = new ArrayList<File>();
        File[] files = dir.listFiles();
        for( File file : files ) {
            if (file.isDirectory()) {
                result.addAll(findMetaFiles(file, metaFileName));
            } else if (metaFileName.equals(file.getName())) {
                if (!excludeSample(file))
                    result.add(file.getParentFile());
                break;
            }
        }
        return result;
    }

    /**
     * Iterate over the specified directories and recurse looking for
     * sample.meta files.
     * 
     * @param args
     *                list of directories to process
     */
    public static void main(String... args) {
        new SampleProcessorDriver(args).process();
    }

    private static final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    static {
        dbf.setNamespaceAware(true);
    }

    private static boolean excludeSample(File metaFile){
        Document doc;
        try {
            synchronized(dbf) {
                doc = dbf.newDocumentBuilder().parse(metaFile);
            }
        } catch (Exception e) {
            System.out.println("Error parsing meta file, skipping");
            e.printStackTrace();
            return true;
        }

        if (doc.getDocumentElement().getAttribute("exclude").equals("true")) {
            return true;
        }
        return false;
    }
}
