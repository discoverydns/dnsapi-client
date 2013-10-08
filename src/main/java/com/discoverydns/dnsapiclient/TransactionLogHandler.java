package com.discoverydns.dnsapiclient;

import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;

/**
 * The bean used to log the transactions between the {@link DNSAPIClient} instances
 * and the DNSAPI server.
 * @author Chris Wright
 * @see com.discoverydns.dnsapiclient.internal.DefaultTransactionLogHandler
 */
public interface TransactionLogHandler {

    /**
     * Logs a successful transaction between a {@link DNSAPIClient} instance
     * and the DNSAPI server.
     * @param command The command sent from the {@link DNSAPIClient} to the DNSAPI server.
     * @param response The response sent back from the DNSAPI server to the {@link DNSAPIClient}.
     * @param commandMetaData The additional information for the transaction.
     */
	public void logTransaction(final Object command, final Object response,
			final CommandMetaData commandMetaData);

    /**
     * Logs an unsuccessful transaction between a {@link DNSAPIClient} instance
     * and the DNSAPI server.
     * @param command The command sent from the {@link DNSAPIClient} to the DNSAPI server.
     * @param exception The exception sent back from the DNSAPI server to the {@link DNSAPIClient}.
     * @param commandMetaData The additional information for the transaction.
     */
	public void logFailedTransaction(final Object command,
			final Throwable exception, final CommandMetaData commandMetaData);

}
