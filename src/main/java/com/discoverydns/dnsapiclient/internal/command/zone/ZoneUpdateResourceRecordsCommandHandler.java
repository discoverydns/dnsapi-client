package com.discoverydns.dnsapiclient.internal.command.zone;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.discoverydns.dnsapiclient.command.zone.ZoneUpdateResourceRecordsCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneUpdateResourceRecordsResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException.DNSAPIClientExceptionCode;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.BaseRestCommandHandler;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.command.Method;
import com.discoverydns.dnsapiclient.internal.command.WithEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.WithEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.response.ZoneGetView;
import com.discoverydns.dnsapiclient.internal.views.request.ZoneUpdateResourceRecordsView;

public class ZoneUpdateResourceRecordsCommandHandler
		extends
		BaseRestCommandHandler<ZoneUpdateResourceRecordsCommand, ZoneUpdateResourceRecordsResponse> {

	private final WebTarget zoneUpdateResourceRecordsTarget;

	public ZoneUpdateResourceRecordsCommandHandler(final WebTarget baseWebTarget) {
		super(Method.PUT, Status.OK.getStatusCode(),
				MediaType.APPLICATION_JSON_TYPE);
		this.zoneUpdateResourceRecordsTarget = baseWebTarget
				.path("zones/{zoneId}/resourcerecords/");
	}

	@Override
	public WebTarget getWebTarget(
			final ZoneUpdateResourceRecordsCommand command,
			final CommandMetaData commandMetaData) {
		WebTarget webTarget;
		try {
			webTarget = zoneUpdateResourceRecordsTarget.resolveTemplate(
					"zoneId", command.getId());
		} catch (Throwable t) {
			throw new DNSAPIClientException(
					DNSAPIClientExceptionCode.requiredParameterMissing, t, "id");
		}
		return webTarget;
	}

	@Override
	public InvocationBuilderFactory getInvocationBuilderFactory(
			final ZoneUpdateResourceRecordsCommand command,
			final CommandMetaData commandMetaData) {
		return new WithEntityInvocationBuilderFactory(
				MediaType.APPLICATION_JSON_TYPE);
	}

	@Override
	public InvocationBuildInvoker getInvocationBuildInvoker(
			final ZoneUpdateResourceRecordsCommand command,
			final CommandMetaData commandMetaData) {
		return new WithEntityInvocationBuildInvoker<ZoneUpdateResourceRecordsView>(
				new ZoneUpdateResourceRecordsView(command));
	}

	@Override
	public ZoneUpdateResourceRecordsResponse getCommandResponse(
			final Response restResponse, final CommandMetaData commandMetaData) {
		final ZoneGetView zoneGetView = getResponseEntity(ZoneGetView.class,
				restResponse);
		return new ZoneUpdateResourceRecordsResponse(zoneGetView);
	}

}
