package com.discoverydns.dnsapiclient.internal.command.nameserverset;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.discoverydns.dnsapiclient.command.nameServerSet.NameServerSetGetCommand;
import com.discoverydns.dnsapiclient.command.nameServerSet.NameServerSetGetResponse;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.BaseRestCommandHandler;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.command.Method;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.NameServerSetGetView;

public class NameServerSetGetCommandHandler
		extends
		BaseRestCommandHandler<NameServerSetGetCommand, NameServerSetGetResponse> {

	private final WebTarget nameServerSetGetTarget;

	public NameServerSetGetCommandHandler(final WebTarget baseWebTarget) {
		super(Method.GET, Status.OK.getStatusCode(),
				MediaType.APPLICATION_JSON_TYPE);
		this.nameServerSetGetTarget = baseWebTarget
				.path("nameserversets/{nameServerSetId}");
	}

	@Override
	public WebTarget getWebTarget(final NameServerSetGetCommand command,
			final CommandMetaData commandMetaData) {
		return nameServerSetGetTarget.resolveTemplate("nameServerSetId",
				command.getIdOrName());
	}

	@Override
	public InvocationBuilderFactory getInvocationBuilderFactory(
			final NameServerSetGetCommand command,
			final CommandMetaData commandMetaData) {
		return new NoEntityInvocationBuilderFactory();
	}

	@Override
	public InvocationBuildInvoker getInvocationBuildInvoker(
			final NameServerSetGetCommand command,
			final CommandMetaData commandMetaData) {
		return new NoEntityInvocationBuildInvoker();
	}

	@Override
	public NameServerSetGetResponse getCommandResponse(
			final Response restResponse, final CommandMetaData commandMetaData) {
		final NameServerSetGetView nameServerSetGetView = getResponseEntity(
				NameServerSetGetView.class, restResponse);
		return new NameServerSetGetResponse(nameServerSetGetView);
	}

}
