package com.discoverydns.dnsapiclient.internal.command;

import javax.ws.rs.client.Invocation;

public interface InvocationBuildInvoker {

	public Invocation invoke(Invocation.Builder builder, Method method);

}
