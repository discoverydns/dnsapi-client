package com.discoverydns.dnsapiclient.internal.command.zone;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.discoverydns.dnsapiclient.command.zone.ZoneRefreshAXFRCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneRefreshResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException.DNSAPIClientExceptionCode;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.BaseRestCommandHandler;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.command.Method;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;

public class ZoneRefreshAXFRCommandHandler extends BaseRestCommandHandler<ZoneRefreshAXFRCommand, ZoneRefreshResponse> {

    private final WebTarget zoneRefreshTarget;

    public ZoneRefreshAXFRCommandHandler(final WebTarget baseWebTarget) {
        super(Method.POST, Status.OK.getStatusCode());
        this.zoneRefreshTarget = baseWebTarget.path("zones/{zoneId}/refresh");
    }

    @Override
    public WebTarget getWebTarget(final ZoneRefreshAXFRCommand command,
                                  final CommandMetaData commandMetaData) {
        WebTarget webTarget;
        try {
            webTarget = zoneRefreshTarget.resolveTemplate("zoneId", command.getId());
        } catch (Throwable t) {
            throw new DNSAPIClientException(DNSAPIClientExceptionCode.requiredParameterMissing, t, "id");
        }
        return webTarget;
    }

    @Override
    public InvocationBuilderFactory getInvocationBuilderFactory(
            final ZoneRefreshAXFRCommand command,
            final CommandMetaData commandMetaData) {
        return new NoEntityInvocationBuilderFactory();
    }

    @Override
    public InvocationBuildInvoker getInvocationBuildInvoker(
            final ZoneRefreshAXFRCommand command,
            final CommandMetaData commandMetaData) {
        return new NoEntityInvocationBuildInvoker();
    }

    @Override
    public ZoneRefreshResponse getCommandResponse(final Response restResponse,
                                                 final CommandMetaData commandMetaData) {
        return new ZoneRefreshResponse();
    }

}
