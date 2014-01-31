package com.discoverydns.dnsapiclient.performance;

import java.util.ArrayList;
import java.util.Collection;

import ch.qos.logback.classic.Level;

import com.discoverydns.dnsapiclient.DNSAPIClient;
import com.discoverydns.dnsapiclient.config.DNSAPIClientConfig;
import com.discoverydns.dnsapiclient.DNSAPIClientFactory;
import com.discoverydns.dnsapiclient.config.DefaultSSLContextFactoryConfig;
import com.discoverydns.dnsapiclient.config.DefaultTransactionLogHandlerConfig;
import com.discoverydns.dnsapiclient.internal.util.Stopwatch;
import com.discoverydns.dnsapiclient.main.MyConfig;
import com.discoverydns.dnsapiclient.main.MyDefaultSSLContextFactoryConfig;
import com.discoverydns.dnsapiclient.main.MyDefaultTransactionLogHandlerConfig;

public class DNSAPITestClientMain {

	public static void main(final String[] args) throws InterruptedException {

		final ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory
				.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.OFF);
		// root.setLevel(Level.INFO);

        final DNSAPIClientConfig config = new MyConfig();
        final DefaultSSLContextFactoryConfig sslConfig = new MyDefaultSSLContextFactoryConfig();
        final DefaultTransactionLogHandlerConfig logConfig = new MyDefaultTransactionLogHandlerConfig();
        final DNSAPIClientFactory dnsapiClientFactory = new DNSAPIClientFactory();
        DNSAPIClient client = null;
        try {
            client = dnsapiClientFactory.createInstanceFromDefaultProviders(config, sslConfig, logConfig);
        } catch (final Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

		final Collection<Thread> threadList = new ArrayList<>();
		final long count = 1000;
		final long threads = 1;
		for (long x = 0; x < threads; x++) {
			threadList.add(new Thread(new DNSAPITestClientSimpleClient(count,
					client)));
		}
		final Stopwatch stopwatch = new Stopwatch();
		for (final Thread t : threadList) {
			t.start();
		}
		Thread.sleep(10000);
		stopwatch.start();
		DNSAPITestClientBlocker.getInstance().unblockAll();
		for (final Thread t : threadList) {
			try {
				t.join();
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
		stopwatch.stop();
		System.err.println("TOTAL " + stopwatch.elapsedTimeMs() + " ms ("
				+ (count * threads) / (stopwatch.elapsedTimeMs() / 1000.0)
				+ " TPS)");

	}

}
