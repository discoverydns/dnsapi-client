package com.discoverydns.dnsapiclient.main;

import com.discoverydns.dnsapiclient.config.DefaultTransactionLogHandlerConfig;

public class MyDefaultTransactionLogHandlerConfig implements DefaultTransactionLogHandlerConfig {

    @Override
    public String getTransactionLogFile() {
        return "/Users/zephyia/transaction.log";
    }

    @Override
    public String getTransactionLogFileRotationPattern() {
        return "/Users/zephyia/transaction.log.%d{yyyyMMdd}.gz";
    }

    @Override
    public int getMaxTransactionLogFileVersions() {
        return 0;
    }
}
