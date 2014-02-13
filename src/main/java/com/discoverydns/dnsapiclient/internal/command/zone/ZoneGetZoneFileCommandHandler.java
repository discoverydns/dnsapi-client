package com.discoverydns.dnsapiclient.internal.command.zone;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.discoverydns.dnsapiclient.command.zone.ZoneGetZoneFileCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneGetZoneFileResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException.DNSAPIClientExceptionCode;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.BaseRestCommandHandler;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.command.Method;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.ZoneGetZoneFileView;

public class ZoneGetZoneFileCommandHandler extends
	BaseRestCommandHandler<ZoneGetZoneFileCommand, ZoneGetZoneFileResponse> {
	private final WebTarget zoneGetZoneFileTarget;

	public ZoneGetZoneFileCommandHandler(final WebTarget baseWebTarget) {
		super(Method.GET, Response.Status.OK.getStatusCode(),
                "text/dns");
        this.zoneGetZoneFileTarget = baseWebTarget.path("zones/{zoneId}/zoneFile");
	}

	@Override
	public WebTarget getWebTarget(ZoneGetZoneFileCommand command,
			CommandMetaData commandMetaData) {
		WebTarget webTarget;
		try {
			webTarget = zoneGetZoneFileTarget
					.resolveTemplate("zoneId", command.getId());

		} catch (Throwable t) {
			throw new DNSAPIClientException(
					DNSAPIClientExceptionCode.requiredParameterMissing, t, "id");
		}
		return webTarget;
	}

	@Override
	public InvocationBuildInvoker getInvocationBuildInvoker(
			ZoneGetZoneFileCommand command, CommandMetaData commandMetaData) {
        return new NoEntityInvocationBuildInvoker();
	}

	@Override
	public InvocationBuilderFactory getInvocationBuilderFactory(
			ZoneGetZoneFileCommand command, CommandMetaData commandMetaData) {
		return new NoEntityInvocationBuilderFactory();
	}

	@Override
	public ZoneGetZoneFileResponse getCommandResponse(Response restResponse,
			CommandMetaData commandMetaData) {
		ZoneGetZoneFileView zoneGetZoneFileView =
				new ZoneGetZoneFileView(getResponseEntity(byte[].class, restResponse));
		return new ZoneGetZoneFileResponse(zoneGetZoneFileView);
	}
}
