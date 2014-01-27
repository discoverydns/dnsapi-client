package com.discoverydns.dnsapiclient.main;

import java.net.URI;

import com.discoverydns.dnsapiclient.config.DNSAPIClientConfig;

public class MyConfig implements DNSAPIClientConfig {

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

}
