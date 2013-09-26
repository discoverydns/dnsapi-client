package com.discoverydns.dnsapiclient.internal.command.zone;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.discoverydns.dnsapiclient.command.zone.ZoneGetCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneGetResponse;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.BaseRestCommandHandler;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.command.Method;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.ZoneGetView;

public class ZoneGetCommandHandler extends
		BaseRestCommandHandler<ZoneGetCommand, ZoneGetResponse> {

	private final WebTarget zoneGetTarget;

	public ZoneGetCommandHandler(final WebTarget baseWebTarget) {
		super(Method.GET, Status.OK.getStatusCode(),
				MediaType.APPLICATION_JSON_TYPE);
		this.zoneGetTarget = baseWebTarget.path("zones/{zoneId}");
	}

	@Override
	public WebTarget getWebTarget(final ZoneGetCommand command,
			final CommandMetaData commandMetaData) {
		return zoneGetTarget.resolveTemplate("zoneId", command.getId());
	}

	@Override
	public InvocationBuilderFactory getInvocationBuilderFactory(
			final ZoneGetCommand command, final CommandMetaData commandMetaData) {
		return new NoEntityInvocationBuilderFactory();
	}

	@Override
	public InvocationBuildInvoker getInvocationBuildInvoker(
			final ZoneGetCommand command, final CommandMetaData commandMetaData) {
		return new NoEntityInvocationBuildInvoker();
	}

	@Override
	public ZoneGetResponse getCommandResponse(final Response restResponse,
			final CommandMetaData commandMetaData) {
		final ZoneGetView zoneGetView = getResponseEntity(ZoneGetView.class,
				restResponse);
		return new ZoneGetResponse(zoneGetView);
	}

}
