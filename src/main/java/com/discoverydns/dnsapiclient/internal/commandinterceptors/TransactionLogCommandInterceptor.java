package com.discoverydns.dnsapiclient.internal.commandinterceptors;

import com.discoverydns.dnsapiclient.TransactionLogHandler;
import com.discoverydns.dnsapiclient.framework.command.CommandInterceptor;
import com.discoverydns.dnsapiclient.framework.command.CommandInterceptorChain;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;

public class TransactionLogCommandInterceptor implements CommandInterceptor {

	private final TransactionLogHandler transactionLogHandler;

	public TransactionLogCommandInterceptor(
			final TransactionLogHandler transactionLogHandler) {
		this.transactionLogHandler = transactionLogHandler;
	}

	@Override
	public Object intercept(final Object command,
			final CommandMetaData commandMetaData,
			final CommandInterceptorChain commandInterceptorChain)
			throws Throwable {

		if (transactionLogHandler != null) {
			Object response;
			try {
				response = commandInterceptorChain.proceed();
				transactionLogHandler.logTransaction(command, response,
						commandMetaData);
			} catch (final Throwable t) {
				transactionLogHandler.logFailedTransaction(command, t,
						commandMetaData);
				throw t;
			}

			return response;
		} else {
			return commandInterceptorChain.proceed();
		}
	}

}
