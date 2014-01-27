package com.discoverydns.dnsapiclient.main;

import com.discoverydns.dnsapiclient.config.DefaultSSLContextFactoryConfig;

public class MyDefaultSSLContextFactoryConfig implements DefaultSSLContextFactoryConfig {

    @Override
    public String getKeyStorePath() {
        return "/Users/zephyia/tmp/jetty/clientKeystore.pkcs12";
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
        return "/Users/zephyia/tmp/jetty/trustKeystore.jks";
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
