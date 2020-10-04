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
package org.apache.camel.builder.component.dsl;

import javax.annotation.Generated;
import org.apache.camel.Component;
import org.apache.camel.builder.component.AbstractComponentBuilder;
import org.apache.camel.builder.component.ComponentBuilder;
import org.apache.camel.component.wordpress.WordpressComponent;

/**
 * Manage posts and users using Wordpress API.
 * 
 * Generated by camel-package-maven-plugin - do not edit this file!
 */
@Generated("org.apache.camel.maven.packaging.ComponentDslMojo")
public interface WordpressComponentBuilderFactory {

    /**
     * Wordpress (camel-wordpress)
     * Manage posts and users using Wordpress API.
     * 
     * Category: cloud,api,cms
     * Since: 2.21
     * Maven coordinates: org.apache.camel:camel-wordpress
     */
    static WordpressComponentBuilder wordpress() {
        return new WordpressComponentBuilderImpl();
    }

    /**
     * Builder for the Wordpress component.
     */
    interface WordpressComponentBuilder
            extends
                ComponentBuilder<WordpressComponent> {
        /**
         * The Wordpress REST API version.
         * 
         * The option is a: <code>java.lang.String</code> type.
         * 
         * Default: 2
         * Group: common
         */
        default WordpressComponentBuilder apiVersion(java.lang.String apiVersion) {
            doSetProperty("apiVersion", apiVersion);
            return this;
        }
        /**
         * Wordpress configuration.
         * 
         * The option is a:
         * <code>org.apache.camel.component.wordpress.WordpressConfiguration</code> type.
         * 
         * Group: common
         */
        default WordpressComponentBuilder configuration(
                org.apache.camel.component.wordpress.WordpressConfiguration configuration) {
            doSetProperty("configuration", configuration);
            return this;
        }
        /**
         * The criteria to use with complex searches.
         * 
         * The option is a: <code>java.util.Map<java.lang.String,
         * java.lang.Object></code> type.
         * 
         * Group: common
         */
        default WordpressComponentBuilder criteria(
                java.util.Map<java.lang.String, java.lang.Object> criteria) {
            doSetProperty("criteria", criteria);
            return this;
        }
        /**
         * Whether to bypass trash and force deletion.
         * 
         * The option is a: <code>boolean</code> type.
         * 
         * Default: false
         * Group: common
         */
        default WordpressComponentBuilder force(boolean force) {
            doSetProperty("force", force);
            return this;
        }
        /**
         * The entity ID. Should be passed when the operation performed requires
         * a specific entity, e.g. deleting a post.
         * 
         * The option is a: <code>java.lang.Integer</code> type.
         * 
         * Group: common
         */
        default WordpressComponentBuilder id(java.lang.Integer id) {
            doSetProperty("id", id);
            return this;
        }
        /**
         * Password from authorized user.
         * 
         * The option is a: <code>java.lang.String</code> type.
         * 
         * Group: common
         */
        default WordpressComponentBuilder password(java.lang.String password) {
            doSetProperty("password", password);
            return this;
        }
        /**
         * Search criteria.
         * 
         * The option is a:
         * <code>org.apache.camel.component.wordpress.api.model.SearchCriteria</code> type.
         * 
         * Group: common
         */
        default WordpressComponentBuilder searchCriteria(
                org.apache.camel.component.wordpress.api.model.SearchCriteria searchCriteria) {
            doSetProperty("searchCriteria", searchCriteria);
            return this;
        }
        /**
         * The Wordpress API URL from your site, e.g.
         * http://myblog.com/wp-json/.
         * 
         * The option is a: <code>java.lang.String</code> type.
         * 
         * Group: common
         */
        default WordpressComponentBuilder url(java.lang.String url) {
            doSetProperty("url", url);
            return this;
        }
        /**
         * Authorized user to perform writing operations.
         * 
         * The option is a: <code>java.lang.String</code> type.
         * 
         * Group: common
         */
        default WordpressComponentBuilder user(java.lang.String user) {
            doSetProperty("user", user);
            return this;
        }
        /**
         * Allows for bridging the consumer to the Camel routing Error Handler,
         * which mean any exceptions occurred while the consumer is trying to
         * pickup incoming messages, or the likes, will now be processed as a
         * message and handled by the routing Error Handler. By default the
         * consumer will use the org.apache.camel.spi.ExceptionHandler to deal
         * with exceptions, that will be logged at WARN or ERROR level and
         * ignored.
         * 
         * The option is a: <code>boolean</code> type.
         * 
         * Default: false
         * Group: consumer
         */
        default WordpressComponentBuilder bridgeErrorHandler(
                boolean bridgeErrorHandler) {
            doSetProperty("bridgeErrorHandler", bridgeErrorHandler);
            return this;
        }
        /**
         * Whether the producer should be started lazy (on the first message).
         * By starting lazy you can use this to allow CamelContext and routes to
         * startup in situations where a producer may otherwise fail during
         * starting and cause the route to fail being started. By deferring this
         * startup to be lazy then the startup failure can be handled during
         * routing messages via Camel's routing error handlers. Beware that when
         * the first message is processed then creating and starting the
         * producer may take a little time and prolong the total processing time
         * of the processing.
         * 
         * The option is a: <code>boolean</code> type.
         * 
         * Default: false
         * Group: producer
         */
        default WordpressComponentBuilder lazyStartProducer(
                boolean lazyStartProducer) {
            doSetProperty("lazyStartProducer", lazyStartProducer);
            return this;
        }
        /**
         * Whether the component should use basic property binding (Camel 2.x)
         * or the newer property binding with additional capabilities.
         * 
         * The option is a: <code>boolean</code> type.
         * 
         * Default: false
         * Group: advanced
         */
        @Deprecated
        default WordpressComponentBuilder basicPropertyBinding(
                boolean basicPropertyBinding) {
            doSetProperty("basicPropertyBinding", basicPropertyBinding);
            return this;
        }
    }

    class WordpressComponentBuilderImpl
            extends
                AbstractComponentBuilder<WordpressComponent>
            implements
                WordpressComponentBuilder {
        @Override
        protected WordpressComponent buildConcreteComponent() {
            return new WordpressComponent();
        }
        private org.apache.camel.component.wordpress.WordpressConfiguration getOrCreateConfiguration(
                org.apache.camel.component.wordpress.WordpressComponent component) {
            if (component.getConfiguration() == null) {
                component.setConfiguration(new org.apache.camel.component.wordpress.WordpressConfiguration());
            }
            return component.getConfiguration();
        }
        @Override
        protected boolean setPropertyOnComponent(
                Component component,
                String name,
                Object value) {
            switch (name) {
            case "apiVersion": getOrCreateConfiguration((WordpressComponent) component).setApiVersion((java.lang.String) value); return true;
            case "configuration": ((WordpressComponent) component).setConfiguration((org.apache.camel.component.wordpress.WordpressConfiguration) value); return true;
            case "criteria": getOrCreateConfiguration((WordpressComponent) component).setCriteria((java.util.Map) value); return true;
            case "force": getOrCreateConfiguration((WordpressComponent) component).setForce((boolean) value); return true;
            case "id": getOrCreateConfiguration((WordpressComponent) component).setId((java.lang.Integer) value); return true;
            case "password": getOrCreateConfiguration((WordpressComponent) component).setPassword((java.lang.String) value); return true;
            case "searchCriteria": getOrCreateConfiguration((WordpressComponent) component).setSearchCriteria((org.apache.camel.component.wordpress.api.model.SearchCriteria) value); return true;
            case "url": getOrCreateConfiguration((WordpressComponent) component).setUrl((java.lang.String) value); return true;
            case "user": getOrCreateConfiguration((WordpressComponent) component).setUser((java.lang.String) value); return true;
            case "bridgeErrorHandler": ((WordpressComponent) component).setBridgeErrorHandler((boolean) value); return true;
            case "lazyStartProducer": ((WordpressComponent) component).setLazyStartProducer((boolean) value); return true;
            case "basicPropertyBinding": ((WordpressComponent) component).setBasicPropertyBinding((boolean) value); return true;
            default: return false;
            }
        }
    }
}