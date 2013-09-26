package com.discoverydns.dnsapiclient.internal.command;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;

public class NoEntityInvocationBuildInvoker implements InvocationBuildInvoker {

	@Override
	public Invocation invoke(final Builder builder, final Method method) {
		return builder.build(method.toString());
	}

}
