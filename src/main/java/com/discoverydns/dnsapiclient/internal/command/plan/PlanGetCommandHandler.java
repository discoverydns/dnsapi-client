package com.discoverydns.dnsapiclient.internal.command.plan;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.discoverydns.dnsapiclient.command.plan.PlanGetCommand;
import com.discoverydns.dnsapiclient.command.plan.PlanGetResponse;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.BaseRestCommandHandler;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.command.Method;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.PlanGetView;

public class PlanGetCommandHandler extends
		BaseRestCommandHandler<PlanGetCommand, PlanGetResponse> {

	private final WebTarget planGetTarget;

	public PlanGetCommandHandler(final WebTarget baseWebTarget) {
		super(Method.GET, Status.OK.getStatusCode(),
				MediaType.APPLICATION_JSON_TYPE);
		this.planGetTarget = baseWebTarget.path("plans/{planId}");
	}

	@Override
	public WebTarget getWebTarget(final PlanGetCommand command,
			final CommandMetaData commandMetaData) {
		return planGetTarget.resolveTemplate("planId", command.getIdOrName());
	}

	@Override
	public InvocationBuilderFactory getInvocationBuilderFactory(
			final PlanGetCommand command, final CommandMetaData commandMetaData) {
		return new NoEntityInvocationBuilderFactory();
	}

	@Override
	public InvocationBuildInvoker getInvocationBuildInvoker(
			final PlanGetCommand command, final CommandMetaData commandMetaData) {
		return new NoEntityInvocationBuildInvoker();
	}

	@Override
	public PlanGetResponse getCommandResponse(final Response restResponse,
			final CommandMetaData commandMetaData) {
		final PlanGetView planGetView = getResponseEntity(PlanGetView.class,
				restResponse);
		return new PlanGetResponse(planGetView);
	}

}
