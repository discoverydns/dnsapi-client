package com.discoverydns.dnsapiclient;

import java.net.URI;

public interface DNSAPIClientConfig {

	public String getKeyStorePath();

	public String getKeyStoreType();

	public String getKeyStorePassword();

	public String getKeyStoreKeyPassword();

	public String getTrustStorePath();

	public String getTrustStoreType();

	public String getTrustStorePassword();

	public int getMaxConnections();

	// in ms
	public int getTimeout();

	public URI getBaseUri();

	// null to use pattern below
	public String getTransactionLogFile();

	// accepts a date pattern and will roatate according to pattern
	// if ends in .gz will zip it
	public String getTransactionLogFileRotationPattern();

	// 0 means keep all
	public int getMaxTransactionLogFileVersions();

}
