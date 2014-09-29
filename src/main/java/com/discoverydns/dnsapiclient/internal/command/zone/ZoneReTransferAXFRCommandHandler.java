package com.discoverydns.dnsapiclient.internal.command.zone;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.discoverydns.dnsapiclient.command.zone.ZoneReTransferAXFRCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneReTransferAXFRResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException.DNSAPIClientExceptionCode;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.BaseRestCommandHandler;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.command.Method;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;

public class ZoneReTransferAXFRCommandHandler
        extends BaseRestCommandHandler<ZoneReTransferAXFRCommand, ZoneReTransferAXFRResponse> {

    private final WebTarget zoneRefreshTarget;

    public ZoneReTransferAXFRCommandHandler(final WebTarget baseWebTarget) {
        super(Method.POST, Status.OK.getStatusCode());
        this.zoneRefreshTarget = baseWebTarget.path("zones/{zoneId}/retransfer");
    }

    @Override
    public WebTarget getWebTarget(final ZoneReTransferAXFRCommand command,
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
            final ZoneReTransferAXFRCommand command,
            final CommandMetaData commandMetaData) {
        return new NoEntityInvocationBuilderFactory();
    }

    @Override
    public InvocationBuildInvoker getInvocationBuildInvoker(
            final ZoneReTransferAXFRCommand command,
            final CommandMetaData commandMetaData) {
        return new NoEntityInvocationBuildInvoker();
    }

    @Override
    public ZoneReTransferAXFRResponse getCommandResponse(final Response restResponse,
                                                 final CommandMetaData commandMetaData) {
        return new ZoneReTransferAXFRResponse();
    }

}
