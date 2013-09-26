package com.discoverydns.dnsapiclient;

import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;

public interface TransactionLogHandler {

	public void logTransaction(final Object command, final Object response,
			final CommandMetaData commandMetaData);

	public void logFailedTransaction(final Object command,
			final Throwable exception, final CommandMetaData commandMetaData);

}
