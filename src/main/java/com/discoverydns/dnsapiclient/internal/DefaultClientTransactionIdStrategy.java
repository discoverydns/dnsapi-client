package com.discoverydns.dnsapiclient.internal;

import java.util.UUID;

import com.discoverydns.dnsapiclient.ClientTransactionIdStrategy;

public class DefaultClientTransactionIdStrategy implements
		ClientTransactionIdStrategy {

	@Override
	public String generateTransactionId() {
		return UUID.randomUUID().toString();
	}

}
