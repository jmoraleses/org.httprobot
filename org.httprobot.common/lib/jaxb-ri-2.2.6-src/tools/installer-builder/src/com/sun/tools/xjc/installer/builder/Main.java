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

package com.sun.tools.xjc.installer.builder;

import java.io.*;

/**
 * @author Kohsuke Kawaguchi (kk@kohsuke.org)
 */
public class Main {
    public static void main(String[] args) throws Exception {
        if(args.length!=3) {
            System.err.println("Usage: InstallerBuilder <license file> <distribution package zip> <output class file>");
            return;
        }

        build( new File(args[0]), new File(args[1]), new File(args[2]));
    }

    /**
     * Programatic entry point.
     */
    public static void build(File license, File zip, File classFile) throws Exception {
        File tmpDir = File.createTempFile("foo","bar");
        tmpDir.delete();
        tmpDir.mkdir();

        // extract the installer jar
        File rawInstaller = new File(tmpDir,"raw-installer.jar");
        copyStream(
            Main.class.getResourceAsStream("/raw-installer.jar"),
            new FileOutputStream(rawInstaller) );

        File sandbox = new File(tmpDir,"sandbox");
        sandbox.mkdir();

        run(sandbox,new String[]{"jar","xf",rawInstaller.getAbsolutePath()});

        // add those files
        copyStream(
            new FileInputStream(license),
            new FileOutputStream(new File(sandbox,"license.txt")));

        copyStream(
            new FileInputStream(zip),
            new FileOutputStream(new File(sandbox,"package.zip")));

        // create manifest
        File manifest = new File(tmpDir,"MANIFEST.MF");
        BufferedWriter w = new BufferedWriter(new FileWriter(manifest));
        w.write("Main-Class: com.sun.tools.xjc.installer.Main");
        w.newLine();
        w.close();

        // delelte the old manifest in the jar
        new File(new File(sandbox,"META-INF"),"MANIFEST.MF").delete();

        // repackage
        run(tmpDir,new String[]{"jar","cfm","installer.jar","MANIFEST.MF","-C",sandbox.toString(),"."});

        // run sfx4j
        org.kohsuke.sfx4j.Packager.main(new String[]{
            new File(tmpDir,"installer.jar").getAbsolutePath(),
            classFile.getAbsolutePath()
        });

        // remove the tmpdir
        recursiveDelete(tmpDir);
    }

    private static void run(File workDir, String[] cmdLine) throws Exception {
        Process proc = Runtime.getRuntime().exec(cmdLine,new String[0],workDir);
        new Thread(new ProcessReader(proc.getInputStream())).start();
        new Thread(new ProcessReader(proc.getErrorStream())).start();
        if(proc.waitFor()!=0)
            throw new Exception("failed to execute "+cmdLine);
    }

    private static void copyStream(InputStream in, OutputStream out)
        throws IOException {
        byte[] buf = new byte[256];
        int len;
        while ((len = in.read(buf)) >= 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }

    /** Recursively deletes all the files/folders inside a folder. */
    public static void recursiveDelete( File f ) {
        if(f.isDirectory()) {
            String[] files = f.list();
            for( int i=0; i<files.length; i++ )
                recursiveDelete( new File(f,files[i]) );
        }
        f.delete();
    }

    /**
     * Reads an input stream and copies them into System.out.
     */
    private static class ProcessReader implements Runnable {
        ProcessReader( InputStream is ) {
            reader = new BufferedReader(new InputStreamReader(is));
        }

        private final BufferedReader reader;

        public void run() {
            try {
                while(true) {
                    String s = reader.readLine();
                    if(s==null) {
                        reader.close();
                        return;
                    }
                    System.out.println(s);
                }
            } catch( Exception e ) {
                e.printStackTrace();
                throw new Error();
            }
        }
    }

}
