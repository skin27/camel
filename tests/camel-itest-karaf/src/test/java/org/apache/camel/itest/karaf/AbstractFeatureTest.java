/**
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
package org.apache.camel.itest.karaf;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Dictionary;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.Properties;
import javax.inject.Inject;

import org.apache.camel.CamelContext;
import org.apache.camel.Component;
import org.apache.camel.spi.DataFormat;
import org.apache.camel.spi.Language;
import org.apache.karaf.features.FeaturesService;
import org.junit.After;
import org.junit.Before;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.CoreOptions;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.ProbeBuilder;
import org.ops4j.pax.exam.TestProbeBuilder;
import org.ops4j.pax.exam.karaf.options.KarafDistributionOption;
import org.ops4j.pax.exam.karaf.options.LogLevelOption;
import org.ops4j.pax.exam.options.UrlReference;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.blueprint.container.BlueprintContainer;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertNotNull;
import static org.ops4j.pax.exam.CoreOptions.maven;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.vmOption;


public abstract class AbstractFeatureTest {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractFeatureTest.class);

    @Inject
    protected BundleContext bundleContext;

    @Inject
    protected BlueprintContainer blueprintContainer;

    @Inject
    protected FeaturesService featuresService;

    @ProbeBuilder
    public TestProbeBuilder probeConfiguration(TestProbeBuilder probe) {
        // makes sure the generated Test-Bundle contains this import!
        probe.setHeader(Constants.DYNAMICIMPORT_PACKAGE, "*");
        return probe;
    }

    @Before
    public void setUp() throws Exception {
        LOG.info("setUp() using BundleContext: {}", bundleContext);
    }

    @After
    public void tearDown() throws Exception {
        LOG.info("tearDown()");
    }

    protected void installCamelFeature(String mainFeature) throws Exception {
        if (!mainFeature.startsWith("camel-")) {
            mainFeature = "camel-" + mainFeature;
        }
        LOG.info("Install main feature: {}", mainFeature);
        // do not refresh bundles causing out bundle context to be invalid
        // TODO: see if we can find a way maybe to install camel.xml as bundle/feature instead of part of unit test (see src/test/resources/OSGI-INF/blueprint)
        featuresService.installFeature(mainFeature, EnumSet.of(FeaturesService.Option.NoAutoRefreshBundles));
    }

    protected void testComponent(String component) throws Exception {
        testComponent("camel-" + component, component);
    }

    protected void testComponent(String mainFeature, String component) throws Exception {
        LOG.info("Looking up CamelContext(myCamel) in OSGi Service Registry");

        installCamelFeature(mainFeature);

        CamelContext camelContext = getOsgiService(bundleContext, CamelContext.class, "(camel.context.name=myCamel)", 20000);
        assertNotNull("Cannot find CamelContext with name myCamel", camelContext);

        LOG.info("Getting Camel component: {}", component);
        // do not auto start the component as it may not have been configured properly and fail in its start method
        Component comp = camelContext.getComponent(component, true, false);
        assertNotNull("Cannot get component with name: " + component, comp);

        LOG.info("Found Camel component: {} instance: {} with className: {}", component, comp, comp.getClass());
    }

    protected void testDataFormat(String dataFormat) throws Exception {
        testDataFormat("camel-" + dataFormat, dataFormat);
    }

    protected void testDataFormat(String mainFeature, String dataFormat) throws Exception {
        LOG.info("Looking up CamelContext(myCamel) in OSGi Service Registry");

        installCamelFeature(mainFeature);

        CamelContext camelContext = getOsgiService(bundleContext, CamelContext.class, "(camel.context.name=myCamel)", 20000);
        assertNotNull("Cannot find CamelContext with name myCamel", camelContext);

        LOG.info("Getting Camel dataformat: {}", dataFormat);
        DataFormat df = camelContext.resolveDataFormat(dataFormat);
        assertNotNull("Cannot get dataformat with name: " + dataFormat, df);

        LOG.info("Found Camel dataformat: {} instance: {} with className: {}", dataFormat, df, df.getClass());
    }

    protected void testLanguage(String language) throws Exception {
        testLanguage("camel-" + language, language);
    }

    protected void testLanguage(String mainFeature, String language) throws Exception {
        LOG.info("Looking up CamelContext(myCamel) in OSGi Service Registry");

        installCamelFeature(mainFeature);

        CamelContext camelContext = getOsgiService(bundleContext, CamelContext.class, "(camel.context.name=myCamel)", 20000);
        assertNotNull("Cannot find CamelContext with name myCamel", camelContext);

        LOG.info("Getting Camel language: {}", language);
        Language lan = camelContext.resolveLanguage(language);
        assertNotNull("Cannot get language with name: " + language, lan);

        LOG.info("Found Camel language: {} instance: {} with className: {}", language, lan, lan.getClass());
    }

    public static String extractName(Class<?> clazz) {
        String name = clazz.getName();
        int id0 = name.indexOf("Camel") + "Camel".length();
        int id1 = name.indexOf("Test");
        StringBuilder sb = new StringBuilder();
        for (int i = id0; i < id1; i++) {
            char c = name.charAt(i);
            if (Character.isUpperCase(c) && sb.length() > 0) {
                sb.append("-");
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    public static UrlReference getCamelKarafFeatureUrl() {
        return mavenBundle().
                groupId("org.apache.camel.karaf").
                artifactId("apache-camel").
                versionAsInProject().type("xml/features");
    }

    private static void switchPlatformEncodingToUTF8() {
        try {
            System.setProperty("file.encoding", "UTF-8");
            Field charset = Charset.class.getDeclaredField("defaultCharset");
            charset.setAccessible(true);
            charset.set(null, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getKarafVersion() {
        InputStream ins = AbstractFeatureTest.class.getResourceAsStream("/META-INF/maven/dependencies.properties");
        Properties p = new Properties();
        try {
            p.load(ins);
        } catch (Throwable t) {
            // ignore
        }
        String karafVersion = p.getProperty("org.apache.karaf/apache-karaf/version");
        if (karafVersion == null) {
            karafVersion = System.getProperty("karafVersion");
        }
        if (karafVersion == null) {
            // setup the default version of it
            karafVersion = "4.0.4";
        }
        return karafVersion;
    }

    @Configuration
    public static Option[] configure() {
        switchPlatformEncodingToUTF8();
        String karafVersion = getKarafVersion();
        LOG.info("*** Apache Karaf version is " + karafVersion + " ***");

        Option[] options = new Option[]{
            // for remote debugging
            //org.ops4j.pax.exam.CoreOptions.vmOption("-Xdebug"),
            //org.ops4j.pax.exam.CoreOptions.vmOption("-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5008"),

            // we need INFO logging otherwise we cannot see what happens
            new LogLevelOption(LogLevelOption.LogLevel.INFO),

            KarafDistributionOption.karafDistributionConfiguration()
                    .frameworkUrl(maven().groupId("org.apache.karaf").artifactId("apache-karaf").type("tar.gz").versionAsInProject())
                    .karafVersion(karafVersion)
                    .name("Apache Karaf")
                    .useDeployFolder(false).unpackDirectory(new File("target/paxexam/unpack/")),

            // keep the folder so we can look inside when something fails
            KarafDistributionOption.keepRuntimeFolder(),

            // need to modify the jre.properties to export some com.sun packages that some features rely on
            KarafDistributionOption.replaceConfigurationFile("etc/jre.properties", new File("src/test/resources/jre.properties")),

            vmOption("-Dfile.encoding=UTF-8"),

            // install junit
            CoreOptions.junitBundles(),

            // install camel
            KarafDistributionOption.features(getCamelKarafFeatureUrl(), "camel")
        };

        return options;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getOsgiService(BundleContext bundleContext, Class<T> type, String filter, long timeout) {
        ServiceTracker tracker;
        try {
            String flt;
            if (filter != null) {
                if (filter.startsWith("(")) {
                    flt = "(&(" + Constants.OBJECTCLASS + "=" + type.getName() + ")" + filter + ")";
                } else {
                    flt = "(&(" + Constants.OBJECTCLASS + "=" + type.getName() + ")(" + filter + "))";
                }
            } else {
                flt = "(" + Constants.OBJECTCLASS + "=" + type.getName() + ")";
            }
            Filter osgiFilter = FrameworkUtil.createFilter(flt);
            tracker = new ServiceTracker(bundleContext, osgiFilter, null);
            tracker.open(true);
            // Note that the tracker is not closed to keep the reference
            // This is buggy, as the service reference may change i think
            Object svc = tracker.waitForService(timeout);

            if (svc == null) {
                Dictionary<?, ?> dic = bundleContext.getBundle().getHeaders();
                LOG.warn("Test bundle headers: " + explode(dic));

                for (ServiceReference ref : asCollection(bundleContext.getAllServiceReferences(null, null))) {
                    LOG.warn("ServiceReference: " + ref + ", bundle: " + ref.getBundle() + ", symbolicName: " + ref.getBundle().getSymbolicName());
                }

                for (ServiceReference ref : asCollection(bundleContext.getAllServiceReferences(null, flt))) {
                    LOG.warn("Filtered ServiceReference: " + ref + ", bundle: " + ref.getBundle() + ", symbolicName: " + ref.getBundle().getSymbolicName());
                }

                throw new RuntimeException("Gave up waiting for service " + flt);
            }
            return type.cast(svc);
        } catch (InvalidSyntaxException e) {
            throw new IllegalArgumentException("Invalid filter", e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Explode the dictionary into a <code>,</code> delimited list of <code>key=value</code> pairs.
     */
    private static String explode(Dictionary<?, ?> dictionary) {
        Enumeration<?> keys = dictionary.keys();
        StringBuilder result = new StringBuilder();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            result.append(String.format("%s=%s", key, dictionary.get(key)));
            if (keys.hasMoreElements()) {
                result.append(", ");
            }
        }
        return result.toString();
    }

    /**
     * Provides an iterable collection of references, even if the original array is <code>null</code>.
     */
    private static Collection<ServiceReference> asCollection(ServiceReference[] references) {
        return references == null ? new ArrayList<ServiceReference>(0) : Arrays.asList(references);
    }

}
