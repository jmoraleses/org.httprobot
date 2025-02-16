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

package com.sun.xml.ws.assembler.dev;

import java.util.ArrayList;
import java.util.Collection;

import com.sun.xml.ws.api.pipe.Tube;

/**
 * Decorate Tubes during tubeline assembly
 *
 * @since 2.2.7
 */
public class TubelineAssemblyDecorator {
    /**
     * Composite decorator
     * @param decorators decorators
     * @return composite that delegates to a list of decorators
     */
    public static TubelineAssemblyDecorator composite(Iterable<TubelineAssemblyDecorator> decorators) {
        return new CompositeTubelineAssemblyDecorator(decorators);
    }
    
    /**
     * Decorate client tube
     * @param tube tube
     * @param context client context
     * @return updated tube for tubeline or return tube parameter to no-op
     */
    public Tube decorateClient(Tube tube, ClientTubelineAssemblyContext context) {
        return tube;
    }
    
    /**
     * Decorate client head tube.  The decorateClient method will have been called first.
     * @param tube tube
     * @param context client context
     * @return updated tube for tubeline or return tube parameter to no-op
     */
    public Tube decorateClientHead(
            Tube tube, ClientTubelineAssemblyContext context) {
        return tube;
    }

    /**
     * Decorate client tail tube.  The decorateClient method will have been called first.
     * @param tube tube
     * @param context client context
     * @return updated tube for tubeline or return tube parameter to no-op
     */
    public Tube decorateClientTail(
            Tube tube,
            ClientTubelineAssemblyContext context) {
        return tube;
    }
    
    /**
     * Decorate server tube
     * @param tube tube
     * @param context server context
     * @return updated tube for tubeline or return tube parameter to no-op
     */
    public Tube decorateServer(Tube tube, ServerTubelineAssemblyContext context) {
        return tube;
    }
    
    /**
     * Decorate server tail tube.  The decorateServer method will have been called first.
     * @param tube tube
     * @param context server context
     * @return updated tube for tubeline or return tube parameter to no-op
     */
    public Tube decorateServerTail(
            Tube tube, ServerTubelineAssemblyContext context) {
        return tube;
    }

    /**
     * Decorate server head tube.  The decorateServer method will have been called first
     * @param tube tube
     * @param context server context
     * @return updated tube for tubeline or return tube parameter to no-op
     */
    public Tube decorateServerHead(
            Tube tube,
            ServerTubelineAssemblyContext context) {
        return tube;
    }
    
    private static class CompositeTubelineAssemblyDecorator extends TubelineAssemblyDecorator {
        private Collection<TubelineAssemblyDecorator> decorators = new ArrayList<TubelineAssemblyDecorator>();
        
        public CompositeTubelineAssemblyDecorator(Iterable<TubelineAssemblyDecorator> decorators) {
            for (TubelineAssemblyDecorator decorator : decorators) {
                this.decorators.add(decorator);
            }
        }
        
        @Override
        public Tube decorateClient(Tube tube, ClientTubelineAssemblyContext context) {
            for (TubelineAssemblyDecorator decorator : decorators) {
                tube = decorator.decorateClient(tube, context);
            }
            return tube;
        }

        @Override
        public Tube decorateClientHead(
                Tube tube, ClientTubelineAssemblyContext context) {
            for (TubelineAssemblyDecorator decorator : decorators) {
                tube = decorator.decorateClientHead(tube, context);
            }
            return tube;
        }

        @Override
        public Tube decorateClientTail(
                Tube tube,
                ClientTubelineAssemblyContext context) {
            for (TubelineAssemblyDecorator decorator : decorators) {
                tube = decorator.decorateClientTail(tube, context);
            }
            return tube;
        }
        
        public Tube decorateServer(Tube tube, ServerTubelineAssemblyContext context) {
            for (TubelineAssemblyDecorator decorator : decorators) {
                tube = decorator.decorateServer(tube, context);
            }
            return tube;
        }
        
        @Override
        public Tube decorateServerTail(
                Tube tube, ServerTubelineAssemblyContext context) {
            for (TubelineAssemblyDecorator decorator : decorators) {
                tube = decorator.decorateServerTail(tube, context);
            }
            return tube;
        }

        @Override
        public Tube decorateServerHead(
                Tube tube,
                ServerTubelineAssemblyContext context) {
            for (TubelineAssemblyDecorator decorator : decorators) {
                tube = decorator.decorateServerHead(tube, context);
            }
            return tube;
        }
    }
}
