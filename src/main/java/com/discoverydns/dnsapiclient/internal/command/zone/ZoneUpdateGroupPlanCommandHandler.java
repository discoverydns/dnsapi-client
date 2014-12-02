package com.discoverydns.dnsapiclient.internal.command.zone;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.discoverydns.dnsapiclient.command.zone.ZoneUpdateGroupPlanCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneUpdateGroupPlanResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.BaseRestCommandHandler;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.command.Method;
import com.discoverydns.dnsapiclient.internal.command.WithEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.WithEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.request.ZoneUpdateGroupPlanView;

public class ZoneUpdateGroupPlanCommandHandler extends
        BaseRestCommandHandler<ZoneUpdateGroupPlanCommand, ZoneUpdateGroupPlanResponse> {
    private final WebTarget zoneUpdateGroupPlanTarget;

    public ZoneUpdateGroupPlanCommandHandler(final WebTarget baseWebTarget) {
        super(Method.PUT, Response.Status.NO_CONTENT.getStatusCode());
        this.zoneUpdateGroupPlanTarget = baseWebTarget.path("zones");
    }

    @Override
    public WebTarget getWebTarget(ZoneUpdateGroupPlanCommand command, CommandMetaData commandMetaData) {
        WebTarget webTarget;
        try {
            webTarget = zoneUpdateGroupPlanTarget.queryParam("group",
                    command.getGroup());
        } catch (Throwable t) {
            throw new DNSAPIClientException(
                    DNSAPIClientException.DNSAPIClientExceptionCode.requiredParameterMissing, t, "group");
        }
        return webTarget;
    }

    @Override
    public InvocationBuildInvoker getInvocationBuildInvoker(ZoneUpdateGroupPlanCommand command, CommandMetaData
            commandMetaData) {
        return new WithEntityInvocationBuildInvoker<ZoneUpdateGroupPlanView>(
                new ZoneUpdateGroupPlanView(command));
    }

    @Override
    public InvocationBuilderFactory getInvocationBuilderFactory(ZoneUpdateGroupPlanCommand command, CommandMetaData
            commandMetaData) {
        return new WithEntityInvocationBuilderFactory(MediaType.APPLICATION_JSON_TYPE);
    }

    @Override
    public ZoneUpdateGroupPlanResponse getCommandResponse(Response restResponse, CommandMetaData commandMetaData) {
        return new ZoneUpdateGroupPlanResponse();
    }
}
