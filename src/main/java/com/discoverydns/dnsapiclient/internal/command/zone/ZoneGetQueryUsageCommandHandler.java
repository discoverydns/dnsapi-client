package com.discoverydns.dnsapiclient.internal.command.zone;

import com.discoverydns.dnsapiclient.command.zone.ZoneGetQueryUsageCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneGetQueryUsageResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.*;
import com.discoverydns.dnsapiclient.internal.views.response.ZoneGetQueryUsageView;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ZoneGetQueryUsageCommandHandler extends
        BaseRestCommandHandler<ZoneGetQueryUsageCommand, ZoneGetQueryUsageResponse> {
    private final WebTarget zoneGetQueryUsageTarget;

    public ZoneGetQueryUsageCommandHandler(final WebTarget baseWebTarget) {
        super(Method.GET, Response.Status.OK.getStatusCode(),
                MediaType.APPLICATION_JSON_TYPE);
        this.zoneGetQueryUsageTarget = baseWebTarget.path("zones/{zoneId}/queryUsage");
    }

    @Override
    public WebTarget getWebTarget(ZoneGetQueryUsageCommand command, CommandMetaData commandMetaData) {
        WebTarget webTarget;
        try {
            webTarget = zoneGetQueryUsageTarget
                    .resolveTemplate("zoneId", command.getId());
        } catch (Throwable t) {
            throw new DNSAPIClientException(
                    DNSAPIClientException.DNSAPIClientExceptionCode.requiredParameterMissing, t, "id");
        }
        webTarget = setParameterOnWebTarget("searchStartDate", command.getSearchStartDate(), webTarget);
        webTarget = setParameterOnWebTarget("searchEndDate", command.getSearchEndDate(), webTarget);
        webTarget = setParameterOnWebTarget("searchGranularity", command.getSearchGranularity(), webTarget);
        webTarget = setParameterOnWebTarget("searchGroupUsage", command.isSearchGroupUsage(), webTarget);
        return webTarget;
    }

    private WebTarget setParameterOnWebTarget(String parameterName, Object value, WebTarget webTarget) {
        try {
            if (value == null) {
                throw new IllegalArgumentException();
            }
            webTarget = webTarget.queryParam(parameterName, value);
        } catch (Throwable t) {
            throw new DNSAPIClientException(
                    DNSAPIClientException.DNSAPIClientExceptionCode.requiredParameterMissing, t, parameterName);
        }
        return webTarget;
    }

    @Override
    public InvocationBuildInvoker getInvocationBuildInvoker(
            ZoneGetQueryUsageCommand command, CommandMetaData commandMetaData) {
        return new NoEntityInvocationBuildInvoker();
    }

    @Override
    public InvocationBuilderFactory getInvocationBuilderFactory(
            ZoneGetQueryUsageCommand command, CommandMetaData commandMetaData) {
        return new NoEntityInvocationBuilderFactory();
    }

    @Override
    public ZoneGetQueryUsageResponse getCommandResponse(
            Response restResponse, CommandMetaData commandMetaData) {
        final ZoneGetQueryUsageView zoneGetQueryUsageView = getResponseEntity(ZoneGetQueryUsageView.class,
                restResponse);
        return new ZoneGetQueryUsageResponse(zoneGetQueryUsageView);
    }
}
