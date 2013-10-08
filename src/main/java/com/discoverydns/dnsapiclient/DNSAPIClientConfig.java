package com.discoverydns.dnsapiclient;

import java.net.URI;

/**
 * The main configuration of a {@link DNSAPIClient} instance.
 * Must be implemented and provided to the {@link DNSAPIClientFactory},
 * when creating {@link DNSAPIClient} instances.
 * @author Chris Wright
 */
public interface DNSAPIClientConfig {

    /**
     * @return The path to the SSL KeyStore file
     */
	public String getKeyStorePath();

    /**
     * @return The type of SSL KeyStore file (e.g. "JKS" or "PKCS12")
     */
	public String getKeyStoreType();

    /**
     * @return The password of the SSL KeyStore file
     */
	public String getKeyStorePassword();

    /**
     * @return The password of the Key contained in the SSL KeyStore file
     */
	public String getKeyStoreKeyPassword();

    /**
     * @return The path to the SSL TrustStore file
     */
	public String getTrustStorePath();

    /**
     * @return The type of SSL TrustStore file (e.g. "JKS" or "PKCS12")
     */
	public String getTrustStoreType();

    /**
     * @return The password of the SSL TrustStore file
     */
	public String getTrustStorePassword();

    /**
     * @return The maximum number of connections of the Client HTTP connection pool
     */
	public int getMaxConnections();

    /**
     * @return The timeout (in ms) of the Client HTTP connections
     */
	public int getTimeout();

    /**
     * @return The Base URI of the DNSAPI Server REST API
     */
	public URI getBaseUri();

    /**
     * @return The path to the transaction log file
     */
	public String getTransactionLogFile();

    /**
     * @return The rotation pattern for the transaction log files, which will be gzipped if the pattern ends in '.gz'
     */
	public String getTransactionLogFileRotationPattern();

    /**
     * @return The maximum number of archive files to keep. '0' means 'keep all'.
     */
	public int getMaxTransactionLogFileVersions();

}
