package com.discoverydns.dnsapiclient.internal;

import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import com.discoverydns.dnsapiclient.DNSAPIClientConfig;
import com.discoverydns.dnsapiclient.SSLContextFactory;

public class DefaultSSLContextFactory implements SSLContextFactory {

	private static String PROTOCOL_VERSION = "TLSv1.1";

	@Override
	public SSLContext createSSLContext(final DNSAPIClientConfig config)
			throws Exception {

		final FileInputStream keyStoreFIS = new FileInputStream(
				config.getKeyStorePath());
		final KeyStore keyStore = KeyStore
				.getInstance(config.getKeyStoreType());
		keyStore.load(keyStoreFIS, config.getKeyStorePassword().toCharArray());

		final FileInputStream trustStoreFIS = new FileInputStream(
				config.getTrustStorePath());
		final KeyStore trustStore = KeyStore.getInstance(config
				.getTrustStoreType());
		trustStore.load(trustStoreFIS, config.getTrustStorePassword()
				.toCharArray());

		final KeyManagerFactory keyManagerFactory = KeyManagerFactory
				.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		keyManagerFactory.init(keyStore, config.getKeyStoreKeyPassword()
				.toCharArray());
		final KeyManager keyManagers[] = keyManagerFactory.getKeyManagers();

		final TrustManagerFactory trustManagerFactory = TrustManagerFactory
				.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		trustManagerFactory.init(trustStore);
		final TrustManager trustManagers[] = trustManagerFactory
				.getTrustManagers();

		final SSLContext context = SSLContext.getInstance(PROTOCOL_VERSION);
		context.init(keyManagers, trustManagers, null);

		return context;
	}
}
