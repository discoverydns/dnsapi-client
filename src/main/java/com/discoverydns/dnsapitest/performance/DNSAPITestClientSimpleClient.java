package com.discoverydns.dnsapitest.performance;

import com.discoverydns.dnsapiclient.DNSAPIClient;
import com.discoverydns.dnsapiclient.Response;
import com.discoverydns.dnsapiclient.command.user.UserGetCommand;
import com.discoverydns.dnsapiclient.command.user.UserGetResponse;
import com.discoverydns.dnsapiclient.internal.util.Stopwatch;

public class DNSAPITestClientSimpleClient implements Runnable {
	long count = 1000;
	DNSAPIClient client;

	public DNSAPITestClientSimpleClient(final long count,
			final DNSAPIClient client) {
		this.count = count;
		this.client = client;
	}

	@Override
	public void run() {

		final UserGetCommand userGetCommand = new UserGetCommand.Builder()
				.withIdOrUsername("admin").build();

		// UserListCommand userListCommand = new
		// UserListCommand.Builder().build();

		DNSAPITestClientBlocker.getInstance().block();

		final Stopwatch sw = new Stopwatch();
		sw.start();
		for (int x = 0; x < count; x++) {

			try {
				Response<UserGetResponse> response = client
						.process(userGetCommand);
				final UserGetResponse userGetResponse = response
						.getResponseObject();
				// userListResponse = (UserListResponse) client
				// .process(userListCommand);
				// System.err.println(userGetResponse.getId());
			} catch (final Throwable e) {
				// e.printStackTrace();
			}

		}
		sw.stop();

		System.err.println("Thread:" + Thread.currentThread().getId()
				+ " Total time [" + sw.elapsedTimeMsDecimal() + "] TPS ["
				+ count / (sw.elapsedTimeMsDecimal() / 1000) + "]");

	}

}
