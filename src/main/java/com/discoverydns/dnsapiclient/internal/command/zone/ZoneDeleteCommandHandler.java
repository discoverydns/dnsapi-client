package com.discoverydns.dnsapiclient.internal.command.zone;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.discoverydns.dnsapiclient.command.zone.ZoneDeleteCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneDeleteResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException.DNSAPIClientExceptionCode;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.BaseRestCommandHandler;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.command.Method;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;

public class ZoneDeleteCommandHandler extends
		BaseRestCommandHandler<ZoneDeleteCommand, ZoneDeleteResponse> {

	private final WebTarget zoneDeleteTarget;

	public ZoneDeleteCommandHandler(final WebTarget baseWebTarget) {
		super(Method.DELETE, Status.NO_CONTENT.getStatusCode());
		this.zoneDeleteTarget = baseWebTarget.path("zones/{zoneId}");
	}

	@Override
	public WebTarget getWebTarget(final ZoneDeleteCommand command,
			final CommandMetaData commandMetaData) {
		WebTarget webTarget;
		try {
			webTarget = zoneDeleteTarget.resolveTemplate("zoneId",
					command.getId());
		} catch (Throwable t) {
			throw new DNSAPIClientException(
					DNSAPIClientExceptionCode.requiredParameterMissing, t, "id");
		}
		return webTarget;
	}

	@Override
	public InvocationBuilderFactory getInvocationBuilderFactory(
			final ZoneDeleteCommand command,
			final CommandMetaData commandMetaData) {
		return new NoEntityInvocationBuilderFactory();
	}

	@Override
	public InvocationBuildInvoker getInvocationBuildInvoker(
			final ZoneDeleteCommand command,
			final CommandMetaData commandMetaData) {
		return new NoEntityInvocationBuildInvoker();
	}

	@Override
	public ZoneDeleteResponse getCommandResponse(final Response restResponse,
			final CommandMetaData commandMetaData) {
		return new ZoneDeleteResponse();
	}

}
