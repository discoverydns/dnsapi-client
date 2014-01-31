package com.discoverydns.dnsapiclient.example;

import com.discoverydns.dnsapiclient.config.DefaultSSLContextFactoryConfig;

public class ExampleDefaultSSLContextFactoryConfig implements DefaultSSLContextFactoryConfig {
    @Override
    public String getKeyStorePath() {
        return "dnsapi-client/src/test/resources/testingAdminClientKeystore.pkcs12";
    }

    @Override
    public String getKeyStoreType() {
        return "PKCS12";
    }

    @Override
    public String getKeyStorePassword() {
        return "password";
    }

    @Override
    public String getKeyStoreKeyPassword() {
        return "password";
    }

    @Override
    public String getTrustStorePath() {
        return "dnsapi-client/src/test/resources/testingTrustKeystore.jks";
    }

    @Override
    public String getTrustStoreType() {
        return "JKS";
    }

    @Override
    public String getTrustStorePassword() {
        return "password";
    }
}
