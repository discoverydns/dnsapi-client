package com.discoverydns.dnsapiclient.example;

import com.discoverydns.dnsapiclient.config.DefaultTransactionLogHandlerConfig;

public class ExampleDefaultTransactionLogHandlerConfig implements DefaultTransactionLogHandlerConfig {

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
