/*
 * Copyright (c) 2002-2013 Gargoyle Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gargoylesoftware.htmlunit.javascript;

/**
 * This is a Java object that is used to test the ActiveX binding to Java objects.
 *
 * @version $Revision: 7931 $
 * @author <a href="mailto:bcurren@esomnie.com">Ben Curren</a>
 */
public class MockActiveXObject {
    /**
     * Test property.
     */
    public static final String MESSAGE = "ActiveX is working!";

    /**
     * Test function.
     * @return the test message
     */
    public String GetMessage() {
        return "Javascript called this method!";
    }
}
