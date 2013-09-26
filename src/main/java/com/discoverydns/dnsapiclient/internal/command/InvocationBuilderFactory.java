package com.discoverydns.dnsapiclient.internal.command;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

public interface InvocationBuilderFactory {

	public Invocation.Builder buildInvocationBuilder(final WebTarget webTarget);

}
