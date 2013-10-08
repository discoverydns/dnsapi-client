package com.discoverydns.dnsapiclient.internal.command.nameServerSet;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.discoverydns.dnsapiclient.command.nameServerSet.NameServerSetListCommand;
import com.discoverydns.dnsapiclient.command.nameServerSet.NameServerSetListResponse;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.BaseRestCommandHandler;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.command.Method;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.NameServerSetListView;

public class NameServerSetListCommandHandler
		extends
		BaseRestCommandHandler<NameServerSetListCommand, NameServerSetListResponse> {

	private final WebTarget nameServerSetListTarget;

	public NameServerSetListCommandHandler(final WebTarget baseWebTarget) {
		super(Method.GET, Status.OK.getStatusCode(),
				MediaType.APPLICATION_JSON_TYPE);
		this.nameServerSetListTarget = baseWebTarget.path("nameServerSets/");
	}

	@Override
	public WebTarget getWebTarget(final NameServerSetListCommand command,
			final CommandMetaData commandMetaData) {
		WebTarget resolvedNameServerSetListTarget = nameServerSetListTarget;
		if (command.getSearchStatus() != null) {
			resolvedNameServerSetListTarget = resolvedNameServerSetListTarget
					.queryParam("searchStatus", command.getSearchStatus());
		}
		if (command.getSearchName() != null) {
			resolvedNameServerSetListTarget = resolvedNameServerSetListTarget
					.queryParam("searchName", command.getSearchName());
		}
		if (command.getSearchNameServerInterfaceSetId() != null) {
			resolvedNameServerSetListTarget = resolvedNameServerSetListTarget
					.queryParam("searchNameServerInterfaceSetId",
							command.getSearchNameServerInterfaceSetId());
		}
		return resolvedNameServerSetListTarget;
	}

	@Override
	public InvocationBuilderFactory getInvocationBuilderFactory(
			final NameServerSetListCommand command,
			final CommandMetaData commandMetaData) {
		return new NoEntityInvocationBuilderFactory();
	}

	@Override
	public InvocationBuildInvoker getInvocationBuildInvoker(
			final NameServerSetListCommand command,
			final CommandMetaData commandMetaData) {
		return new NoEntityInvocationBuildInvoker();
	}

	@Override
	public NameServerSetListResponse getCommandResponse(
			final Response restResponse, final CommandMetaData commandMetaData) {
		final NameServerSetListView nameServerSetListView = getResponseEntity(
				NameServerSetListView.class, restResponse);
		return new NameServerSetListResponse(nameServerSetListView);
	}
}
