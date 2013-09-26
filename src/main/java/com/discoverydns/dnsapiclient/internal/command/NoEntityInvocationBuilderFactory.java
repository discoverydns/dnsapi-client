package com.discoverydns.dnsapiclient.internal.command;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

public class NoEntityInvocationBuilderFactory implements
		InvocationBuilderFactory {

	@Override
	public Builder buildInvocationBuilder(final WebTarget webTarget) {
		return webTarget.request();
	}

}
