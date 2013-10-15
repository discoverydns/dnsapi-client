package com.discoverydns.dnsapiclient.internal.command.zone;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.discoverydns.dnsapiclient.command.zone.ZoneUpdateCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneUpdateResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException.DNSAPIClientExceptionCode;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.BaseRestCommandHandler;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.command.Method;
import com.discoverydns.dnsapiclient.internal.command.WithEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.WithEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.ZoneGetView;
import com.discoverydns.dnsapiclient.internal.views.ZoneUpdateView;

public class ZoneUpdateCommandHandler extends
		BaseRestCommandHandler<ZoneUpdateCommand, ZoneUpdateResponse> {

	private final WebTarget zoneUpdateTarget;

	public ZoneUpdateCommandHandler(final WebTarget baseWebTarget) {
		super(Method.PUT, Status.OK.getStatusCode(),
				MediaType.APPLICATION_JSON_TYPE);
		this.zoneUpdateTarget = baseWebTarget.path("zones/{zoneId}");
	}

	@Override
	public WebTarget getWebTarget(final ZoneUpdateCommand command,
			final CommandMetaData commandMetaData) {
		WebTarget webTarget;
		try {
			webTarget = zoneUpdateTarget.resolveTemplate("zoneId",
					command.getId());
		} catch (Throwable t) {
			throw new DNSAPIClientException(
					DNSAPIClientExceptionCode.requiredParameterMissing, t, "id");
		}
		return webTarget;
	}

	@Override
	public InvocationBuilderFactory getInvocationBuilderFactory(
			final ZoneUpdateCommand command,
			final CommandMetaData commandMetaData) {
		return new WithEntityInvocationBuilderFactory(
				MediaType.APPLICATION_JSON_TYPE);
	}

	@Override
	public InvocationBuildInvoker getInvocationBuildInvoker(
			final ZoneUpdateCommand command,
			final CommandMetaData commandMetaData) {
		return new WithEntityInvocationBuildInvoker<>(
				new ZoneUpdateView(command));
	}

	@Override
	public ZoneUpdateResponse getCommandResponse(final Response restResponse,
			final CommandMetaData commandMetaData) {
		final ZoneGetView zoneGetView = getResponseEntity(ZoneGetView.class,
				restResponse);
		return new ZoneUpdateResponse(zoneGetView);
	}

}
