package com.discoverydns.dnsapiclient;

import javax.net.ssl.SSLContext;

public interface SSLContextFactory {

	public SSLContext createSSLContext(final DNSAPIClientConfig config)
			throws Exception;

}
