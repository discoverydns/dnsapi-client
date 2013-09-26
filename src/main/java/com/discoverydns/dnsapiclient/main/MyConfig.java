package com.discoverydns.dnsapiclient.main;

import java.net.URI;

import com.discoverydns.dnsapiclient.DNSAPIClientConfig;

public class MyConfig implements DNSAPIClientConfig {

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
		return "/Users/zephyia/transaction.log";
	}

	@Override
	public String getTransactionLogFileRotationPattern() {
		return "/Users/zephyia/transaction.log.%d{yyyMMdd}.gz";
	}

	@Override
	public int getMaxTransactionLogFileVersions() {
		return 0;
	}

}
