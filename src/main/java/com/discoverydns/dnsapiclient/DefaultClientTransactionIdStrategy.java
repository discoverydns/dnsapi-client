package com.discoverydns.dnsapiclient;

import java.util.UUID;


public class DefaultClientTransactionIdStrategy implements
		ClientTransactionIdStrategy {

	@Override
	public String generateTransactionId() {
		return UUID.randomUUID().toString();
	}

}
