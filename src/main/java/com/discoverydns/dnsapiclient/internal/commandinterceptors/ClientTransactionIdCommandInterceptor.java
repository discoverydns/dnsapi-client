package com.discoverydns.dnsapiclient.internal.commandinterceptors;

import com.discoverydns.dnsapiclient.ClientTransactionIdStrategy;
import com.discoverydns.dnsapiclient.DNSAPIClientCommandMetaData;
import com.discoverydns.dnsapiclient.framework.command.CommandInterceptor;
import com.discoverydns.dnsapiclient.framework.command.CommandInterceptorChain;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;

public class ClientTransactionIdCommandInterceptor implements
		CommandInterceptor {

	private final ClientTransactionIdStrategy clientTransactionIdStrategy;

	public ClientTransactionIdCommandInterceptor(
			final ClientTransactionIdStrategy clientTransactionIdStrategy) {
		this.clientTransactionIdStrategy = clientTransactionIdStrategy;
	}

	@Override
	public Object intercept(final Object command,
			final CommandMetaData commandMetaData,
			final CommandInterceptorChain commandInterceptorChain)
			throws Throwable {
		commandMetaData.put(DNSAPIClientCommandMetaData.CLIENT_TRANSACTION_ID,
				clientTransactionIdStrategy.generateTransactionId());
		return commandInterceptorChain.proceed();
	}
}
