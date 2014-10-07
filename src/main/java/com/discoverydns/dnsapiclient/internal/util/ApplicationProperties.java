package com.discoverydns.dnsapiclient.internal.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationProperties {
    private static final String DEVELOPMENT_VERSION_PREFIX = "Development";
    private static Logger LOG = LoggerFactory.getLogger(ApplicationProperties.class);

    private Properties manifestProperties;

    public String getVersion() {
        if (manifestProperties == null) {
            initVersionProperties();
        }
        return manifestProperties.getProperty("Implementation-Version");
    }

    private void initVersionProperties() {
        manifestProperties = new Properties();
        try {
            final Enumeration<URL> resources = Thread.currentThread()
                    .getContextClassLoader().getResources("META-INF/MANIFEST.MF");

            while (resources.hasMoreElements()) {
                final URL url = resources.nextElement();

                final InputStream manifestAsInputStream = url.openStream();
                final Properties tempManifestProperties = new Properties();
                tempManifestProperties.load(manifestAsInputStream);
                if ("DNSAPI Client".equals(tempManifestProperties
                        .getProperty("Implementation-Title"))) {
                    if (tempManifestProperties
                            .getProperty("Implementation-Version") == null) {
                        setRandomisedDevelopmentVersion();
                    } else {
                        manifestProperties.put("Implementation-Version",
                                tempManifestProperties
                                        .getProperty("Implementation-Version"));
                        return;
                    }
                }
            }
            setRandomisedDevelopmentVersion();
        } catch (final IOException ioe) {
            LOG.warn(
                    "Cannot read information from Web MANIFEST.MF file, setting randomised Development version",
                    ioe);
            setRandomisedDevelopmentVersion();
        }
    }

    private void setRandomisedDevelopmentVersion() {
        manifestProperties.setProperty("Implementation-Version",
                DEVELOPMENT_VERSION_PREFIX + new Date().getTime());
    }
}