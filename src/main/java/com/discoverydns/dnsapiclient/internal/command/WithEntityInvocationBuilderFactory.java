package com.discoverydns.dnsapiclient.internal.command;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class WithEntityInvocationBuilderFactory implements
		InvocationBuilderFactory {

	private final MediaType mediaType;

	public WithEntityInvocationBuilderFactory(final MediaType mediaType) {
		this.mediaType = mediaType;
	}

	@Override
	public Builder buildInvocationBuilder(final WebTarget webTarget) {
		return webTarget.request(mediaType);
	}

}
