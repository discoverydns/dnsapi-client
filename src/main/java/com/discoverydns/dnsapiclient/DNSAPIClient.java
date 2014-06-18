package com.discoverydns.dnsapiclient;

import javax.ws.rs.client.Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException.DNSAPIClientExceptionCode;
import com.discoverydns.dnsapiclient.framework.command.BlockingCommandExecutor;

/**
 * Instance of a DNSAPI client, used to send a command to the DNAPI server,
 * and get a {@link Response} back from it.
 * Should be obtained from the {@link DNSAPIClientFactory}.
 * @author Chris Wright
 */
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

    /**
     * Process the given command, by sending it to the DNAPI server,
     * and getting a {@link Response} back from it.
     * @param <T> The expected type of the response given the command object
     * @param command The command to send to the server
     * @return The {@link Response} received from the server
     * @throws Throwable In case of any error
     */
	@SuppressWarnings("unchecked")
	public <T> Response<T> process(final Object command) throws Throwable {
		if (clientClosed) {
			log.debug("Attempt to process command on closed client");
			throw new DNSAPIClientException(
					DNSAPIClientExceptionCode.clientClosed);
		}
		final DNSAPIClientCommandMetaData commandMetaData = new DNSAPIClientCommandMetaData();

		T commandResponse = (T) blockingCommandExecutor.process(command,
				commandMetaData);
		return new Response<T>(commandMetaData, commandResponse);
	}

    /**
     * Closes the client, which cannot be used anymore.
     */
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
