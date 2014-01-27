package com.discoverydns.dnsapiclient.config;

import java.net.URI;

/**
 * The main configuration of a {@link com.discoverydns.dnsapiclient.DNSAPIClient} instance.
 * Must be implemented and provided to the {@link com.discoverydns.dnsapiclient.DNSAPIClientFactory},
 * when creating {@link com.discoverydns.dnsapiclient.DNSAPIClient} instances.
 * @author Chris Wright
 */
public interface DNSAPIClientConfig {

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

}
