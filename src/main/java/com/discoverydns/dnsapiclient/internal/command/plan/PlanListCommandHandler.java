package com.discoverydns.dnsapiclient.internal.command.plan;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.discoverydns.dnsapiclient.command.plan.PlanListCommand;
import com.discoverydns.dnsapiclient.command.plan.PlanListResponse;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.BaseRestCommandHandler;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.command.Method;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.response.PlanListView;

public class PlanListCommandHandler extends
		BaseRestCommandHandler<PlanListCommand, PlanListResponse> {

	private final WebTarget planListTarget;

	public PlanListCommandHandler(final WebTarget baseWebTarget) {
		super(Method.GET, Status.OK.getStatusCode(),
				MediaType.APPLICATION_JSON_TYPE);
		this.planListTarget = baseWebTarget.path("plans/");
	}

	@Override
	public WebTarget getWebTarget(final PlanListCommand command,
			final CommandMetaData commandMetaData) {
		WebTarget resolvedPlanListTarget = planListTarget;
		if (command.getSearchStatus() != null) {
			resolvedPlanListTarget = resolvedPlanListTarget.queryParam(
					"searchStatus", command.getSearchStatus());
		}
		if (command.getSearchName() != null) {
			resolvedPlanListTarget = resolvedPlanListTarget.queryParam(
					"searchName", command.getSearchName());
		}
		return resolvedPlanListTarget;
	}

	@Override
	public InvocationBuilderFactory getInvocationBuilderFactory(
			final PlanListCommand command, final CommandMetaData commandMetaData) {
		return new NoEntityInvocationBuilderFactory();
	}

	@Override
	public InvocationBuildInvoker getInvocationBuildInvoker(
			final PlanListCommand command, final CommandMetaData commandMetaData) {
		return new NoEntityInvocationBuildInvoker();
	}

	@Override
	public PlanListResponse getCommandResponse(final Response restResponse,
			final CommandMetaData commandMetaData) {
		final PlanListView planListView = getResponseEntity(PlanListView.class,
				restResponse);
		return new PlanListResponse(planListView);
	}
}
