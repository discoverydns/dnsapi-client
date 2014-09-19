package com.discoverydns.dnsapiclient.internal.command;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;

public class WithEntityInvocationBuildInvoker<T> implements
		InvocationBuildInvoker {

	private final T entityObject;
    private final MediaType mediaType;

	public WithEntityInvocationBuildInvoker(final T entityObject) {
		this(entityObject, MediaType.APPLICATION_JSON_TYPE);
	}

    public WithEntityInvocationBuildInvoker(final T entityObject, final MediaType mediaType) {
        this.entityObject = entityObject;
        this.mediaType = mediaType;
    }

	@Override
	public Invocation invoke(final Builder builder, final Method method) {
		final Entity<T> entity = Entity.entity(entityObject, mediaType);
		return builder.build(method.toString(), entity);
	}

}
