package com.discoverydns.dnsapiclient.internal.command;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;

public class WithEntityInvocationBuildInvoker<T> implements
		InvocationBuildInvoker {

	private final T entityObject;

	public WithEntityInvocationBuildInvoker(final T entityObject) {
		this.entityObject = entityObject;
	}

	@Override
	public Invocation invoke(final Builder builder, final Method method) {
		final Entity<T> entity = Entity.json(entityObject);
		return builder.build(method.toString(), entity);
	}

}
