package com.discoverydns.dnsapiclient.internal.command;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class WithEntityInvocationBuilderFactory implements
		InvocationBuilderFactory {

	private final MediaType acceptedResponseType;

	public WithEntityInvocationBuilderFactory(final MediaType acceptedResponseType) {
		this.acceptedResponseType = acceptedResponseType;
	}

	@Override
	public Builder buildInvocationBuilder(final WebTarget webTarget) {
		return webTarget.request(acceptedResponseType);
	}

}
