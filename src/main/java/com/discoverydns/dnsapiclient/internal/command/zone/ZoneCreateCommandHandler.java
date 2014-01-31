package com.discoverydns.dnsapiclient.internal.command.zone;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.discoverydns.dnsapiclient.command.zone.ZoneCreateCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneCreateResponse;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.BaseRestCommandHandler;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.command.Method;
import com.discoverydns.dnsapiclient.internal.command.WithEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.WithEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.ZoneCreateView;
import com.discoverydns.dnsapiclient.internal.views.ZoneGetView;

public class ZoneCreateCommandHandler extends
		BaseRestCommandHandler<ZoneCreateCommand, ZoneCreateResponse> {

	private final WebTarget zoneCreateTarget;

	public ZoneCreateCommandHandler(final WebTarget baseWebTarget) {
		super(Method.POST, Status.CREATED.getStatusCode(),
				MediaType.APPLICATION_JSON_TYPE);
		this.zoneCreateTarget = baseWebTarget.path("zones/");
	}

	@Override
	public WebTarget getWebTarget(final ZoneCreateCommand command,
			final CommandMetaData commandMetaData) {
		return zoneCreateTarget;
	}

	@Override
	public InvocationBuilderFactory getInvocationBuilderFactory(
			final ZoneCreateCommand command,
			final CommandMetaData commandMetaData) {
		return new WithEntityInvocationBuilderFactory(
				MediaType.APPLICATION_JSON_TYPE);
	}

	@Override
	public InvocationBuildInvoker getInvocationBuildInvoker(
			final ZoneCreateCommand command,
			final CommandMetaData commandMetaData) {
		return new WithEntityInvocationBuildInvoker<>(
				new ZoneCreateView(command));
	}

	@Override
	public ZoneCreateResponse getCommandResponse(final Response restResponse,
			final CommandMetaData commandMetaData) {
		final ZoneGetView zoneGetView = getResponseEntity(ZoneGetView.class,
				restResponse);
		return new ZoneCreateResponse(zoneGetView);
	}

}
