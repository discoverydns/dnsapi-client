package com.discoverydns.dnsapiclient;

import javax.ws.rs.client.Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException.DNSAPIClientExceptionCode;
import com.discoverydns.dnsapiclient.framework.command.BlockingCommandExecutor;

public class DNSAPIClient {

	private static Logger log = LoggerFactory.getLogger(DNSAPIClient.class);

	private final Client client;
	private final BlockingCommandExecutor blockingCommandExecutor;

	private boolean clientClosed;

	public DNSAPIClient(final Client client,
			final BlockingCommandExecutor blockingCommandExecutor) {
		this.client = client;
		this.clientClosed = false;
		this.blockingCommandExecutor = blockingCommandExecutor;
	}

	public Object process(final Object command) throws Throwable {
		if (clientClosed) {
			log.debug("Attempt to process command on closed client");
			throw new DNSAPIClientException(
					DNSAPIClientExceptionCode.clientClosed);
		}
		final DNSAPIClientCommandMetaData commandMetaData = new DNSAPIClientCommandMetaData();
		return blockingCommandExecutor.process(command, commandMetaData);
	}

	public void close() {
		if (!clientClosed) {
			log.debug("Closing client");
			clientClosed = true;
			client.close();
		} else {
			log.debug("Attempt to close an already closed client");
		}
	}

}
