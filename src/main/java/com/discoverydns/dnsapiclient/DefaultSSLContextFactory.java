package com.discoverydns.dnsapiclient;

import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.eclipse.jetty.util.resource.Resource;

import com.discoverydns.dnsapiclient.config.DefaultSSLContextFactoryConfig;

public class DefaultSSLContextFactory implements SSLContextFactory {

	private static final String PROTOCOL_VERSION = "TLSv1.1";

	private final DefaultSSLContextFactoryConfig sslConfig;

	public DefaultSSLContextFactory(DefaultSSLContextFactoryConfig sslConfig) {
		this.sslConfig = sslConfig;
	}

	@Override
	public SSLContext createSSLContext() throws Exception {

		InputStream keyStoreIS = Resource.newResource(
				sslConfig.getKeyStorePath()).getInputStream();

		final KeyStore keyStore = KeyStore.getInstance(sslConfig
				.getKeyStoreType());
		keyStore.load(keyStoreIS, sslConfig.getKeyStorePassword().toCharArray());

		final KeyManagerFactory keyManagerFactory = KeyManagerFactory
				.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		keyManagerFactory.init(keyStore, sslConfig.getKeyStoreKeyPassword()
				.toCharArray());
		final KeyManager keyManagers[] = keyManagerFactory.getKeyManagers();

		TrustManager trustManagers[] = null;
		if (sslConfig.getTrustStorePath() != null) {
			InputStream trustStoreIS = Resource.newResource(
					sslConfig.getTrustStorePath()).getInputStream();

			final KeyStore trustStore = KeyStore.getInstance(sslConfig
					.getTrustStoreType());
			trustStore.load(trustStoreIS, sslConfig.getTrustStorePassword()
					.toCharArray());

			final TrustManagerFactory trustManagerFactory = TrustManagerFactory
					.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			 trustManagerFactory.init(trustStore);
//			trustManagerFactory.init((KeyStore) null);
			trustManagers = trustManagerFactory.getTrustManagers();
		}

		final SSLContext context = SSLContext.getInstance(PROTOCOL_VERSION);
		context.init(keyManagers, trustManagers, null);

		return context;
	}
}
