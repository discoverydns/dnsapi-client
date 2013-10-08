package com.discoverydns.dnsapiclient;

import javax.net.ssl.SSLContext;

/**
 * The factory used to create {@link SSLContext} instances,
 * to establish secure communications between the created {@link DNSAPIClient} instances
 * and the DNSAPI server.
 * @author Chris Wright
 * @see com.discoverydns.dnsapiclient.internal.DefaultSSLContextFactory
 */
public interface SSLContextFactory {

    /**
     * Creates an {@link SSLContext} instance, from the given {@link DNSAPIClientConfig} configuration.
     * @param config The configuration to be used to create the instance.
     * @return The created {@link SSLContext} instance.
     * @throws Exception In case of any error.
     */
	public SSLContext createSSLContext(final DNSAPIClientConfig config)
			throws Exception;

}
