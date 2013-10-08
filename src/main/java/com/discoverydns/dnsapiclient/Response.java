package com.discoverydns.dnsapiclient;

import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.commandinterceptors.StopwatchCommandInterceptor;

/**
 * The response coming back from the DNSAPI Server, consequently to a sent command.
 * @author Chris Wright
 * @param <T> The type of response received, related to the type of command sent previously.
 */
public class Response<T> {

	private final CommandMetaData commandMetaData;
	private final T responseObject;

	Response(CommandMetaData commandMetaData, T responseObject) {
		this.commandMetaData = commandMetaData;
		this.responseObject = responseObject;
	}

    /**
     * @return The response object received from the DNSAPI Server,
     * of the given parameterized type.
     */
	public T getResponseObject() {
		return responseObject;
	}

    /**
     * @return The server transaction id, from the received {@link CommandMetaData}.
     */
	public String getServerTransactionId() {
		String serverTransactionId = null;
		if (commandMetaData != null) {
			serverTransactionId = (String) commandMetaData
					.get(DNSAPIClientCommandMetaData.SERVER_TRANSACTION_ID);
		}
		return serverTransactionId;
	}

    /**
     * @return The client transaction id, from the received {@link CommandMetaData}.
     */
	public String getClientTransactionId() {
		String clientTransactionId = null;
		if (commandMetaData != null) {
			clientTransactionId = (String) commandMetaData
					.get(DNSAPIClientCommandMetaData.CLIENT_TRANSACTION_ID);
		}
		return clientTransactionId;

	}

    /**
     * @return The transaction processing time (in ms), from the received {@link CommandMetaData}.
     */
	public Double getTransactionProcessingTime() {
		Double processingTime = null;
		if (commandMetaData != null) {
			processingTime = (Double) commandMetaData
					.get(StopwatchCommandInterceptor.COMMAND_TIME_MS);
		}
		return processingTime;
	}

}
