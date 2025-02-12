/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.util;

import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Test;

import static org.apache.camel.support.PlatformHelper.isOsgiContext;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlatformHelperTest {

    @Test
    public void shouldNotMatchDefaultCamelContext() {
        assertFalse(isOsgiContext(new DefaultCamelContext()));
    }

    @Test
    public void shouldMatchBlueprintCamelContext() {
        assertTrue(isOsgiContext(new BlueprintCamelContext()));
    }

    @Test
    public void shouldMatchOsgiDefaultCamelContext() {
        assertTrue(isOsgiContext(new OsgiDefaultCamelContext()));
    }

    private static class BlueprintCamelContext extends DefaultCamelContext {
    }

    private static class OsgiDefaultCamelContext extends DefaultCamelContext {
    }

}
