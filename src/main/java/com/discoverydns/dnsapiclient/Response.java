package com.discoverydns.dnsapiclient;

import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.commandinterceptors.StopwatchCommandInterceptor;

public class Response<T> {

	private final CommandMetaData commandMetaData;
	private final T responseObject;

	public Response(CommandMetaData commandMetaData, T responseObject) {
		this.commandMetaData = commandMetaData;
		this.responseObject = responseObject;
	}

	public T getResponseObject() {
		return responseObject;
	}

	public String getServerTransactionId() {
		String serverTransactionId = null;
		if (commandMetaData != null) {
			serverTransactionId = (String) commandMetaData
					.get(DNSAPIClientCommandMetaData.SERVER_TRANSACTION_ID);
		}
		return serverTransactionId;
	}

	public String getClientTransactionId() {
		String clientTransactionId = null;
		if (commandMetaData != null) {
			clientTransactionId = (String) commandMetaData
					.get(DNSAPIClientCommandMetaData.CLIENT_TRANSACTION_ID);
		}
		return clientTransactionId;

	}

	public Double getTransactionProcessingTime() {
		Double processingTime = null;
		if (commandMetaData != null) {
			processingTime = (Double) commandMetaData
					.get(StopwatchCommandInterceptor.COMMAND_TIME_MS);
		}
		return processingTime;
	}

}
