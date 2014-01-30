package com.discoverydns.dnsapiclient.main;

import ch.qos.logback.classic.Level;

import com.discoverydns.dnsapiclient.DNSAPIClient;
import com.discoverydns.dnsapiclient.DNSAPIClientFactory;
import com.discoverydns.dnsapiclient.Response;
import com.discoverydns.dnsapiclient.command.user.UserGetCommand;
import com.discoverydns.dnsapiclient.command.user.UserGetResponse;
import com.discoverydns.dnsapiclient.command.user.UserListCommand;
import com.discoverydns.dnsapiclient.command.user.UserListResponse;
import com.discoverydns.dnsapiclient.config.DefaultSSLContextFactoryConfig;
import com.discoverydns.dnsapiclient.config.DefaultTransactionLogHandlerConfig;
import com.discoverydns.dnsapiclient.config.DNSAPIClientConfig;

public class ClientMain {

	public static void main(final String[] args) {

		final ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory
				.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
		// root.setLevel(Level.OFF);
		root.setLevel(Level.TRACE);

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

		final UserGetCommand userGetCommand = new UserGetCommand.Builder()
				.withIdOrUsername("admin").build();

		final UserListCommand userListCommand = new UserListCommand.Builder()
				.build();

		final UserListResponse userListResponse = null;
		UserGetResponse userGetResponse = null;

		for (int x = 1; x < 100000000; x++) {
			try {
				Response<UserGetResponse> response = client
						.process(userGetCommand);
				userGetResponse = response.getResponseObject();
				// userListResponse = (UserListResponse) client
				// .process(userListCommand);
			} catch (final Throwable e) {
				e.printStackTrace();
				System.exit(1);
			}

			// System.err.println("[" + userGetResponse.getId() + "]");
		}
		// System.err.println("[" + userListResponse.getTotalRecordCount() +
		// "]");

		client.close();

	}
}
