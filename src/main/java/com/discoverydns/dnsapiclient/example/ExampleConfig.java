package com.discoverydns.dnsapiclient.example;

import java.net.URI;

import com.discoverydns.dnsapiclient.DNSAPIClientConfig;

public class ExampleConfig implements DNSAPIClientConfig {

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

    @Override
    public int getMaxConnections() {
        return 10;
    }

    @Override
    public int getTimeout() {
        return 100_000;
    }

    @Override
    public URI getBaseUri() {
        return URI.create("https://dnsapi.discoverydns.com:18443/");
    }

    @Override
    public String getTransactionLogFile() {
        return "transaction.log";
    }

    @Override
    public String getTransactionLogFileRotationPattern() {
        return "transaction.log.%d{yyyyMMdd}.gz";
    }

    @Override
    public int getMaxTransactionLogFileVersions() {
        return 0;
    }
}
