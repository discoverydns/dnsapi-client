package com.discoverydns.dnsapiclient.internal.command.zone;

import com.discoverydns.dnsapiclient.command.zone.ZoneUpdateAXFRCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneUpdateAXFRResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.BaseRestCommandHandler;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.command.Method;
import com.discoverydns.dnsapiclient.internal.command.WithEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.WithEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.request.ZoneUpdateAXFRView;
import com.discoverydns.dnsapiclient.internal.views.response.ZoneGetView;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ZoneUpdateAXFRCommandHandler extends
        BaseRestCommandHandler<ZoneUpdateAXFRCommand, ZoneUpdateAXFRResponse> {
    private static final MediaType REQUEST_MEDIA_TYPE = new MediaType("application", "secondary+json");
    private static final MediaType RESPONSE_MEDIA_TYPE = MediaType.APPLICATION_JSON_TYPE;

    private final WebTarget zoneUpdateTarget;

    public ZoneUpdateAXFRCommandHandler(WebTarget baseWebTarget) {
        super(Method.PUT, Response.Status.OK.getStatusCode(),
                RESPONSE_MEDIA_TYPE);
        this.zoneUpdateTarget = baseWebTarget.path("zones/{zoneId}/xfrDetails");
    }

    @Override
    public WebTarget getWebTarget(final ZoneUpdateAXFRCommand command, final CommandMetaData commandMetaData) {
        WebTarget webTarget;
        try {
            webTarget = zoneUpdateTarget.resolveTemplate("zoneId", command.getId());
        } catch (Throwable t) {
            throw new DNSAPIClientException(
                    DNSAPIClientException.DNSAPIClientExceptionCode.requiredParameterMissing, t, "id");
        }
        return webTarget;
    }

    @Override
    public InvocationBuildInvoker getInvocationBuildInvoker(final ZoneUpdateAXFRCommand command,
                                                            final CommandMetaData commandMetaData) {
        return new WithEntityInvocationBuildInvoker<ZoneUpdateAXFRView>(
                new ZoneUpdateAXFRView(command), REQUEST_MEDIA_TYPE);
    }

    @Override
    public InvocationBuilderFactory getInvocationBuilderFactory(
            final ZoneUpdateAXFRCommand command,
            final CommandMetaData commandMetaData) {

        return new WithEntityInvocationBuilderFactory(RESPONSE_MEDIA_TYPE);
    }

    @Override
    public ZoneUpdateAXFRResponse getCommandResponse(final Response restResponse,
                                                     final CommandMetaData commandMetaData) {
        final ZoneGetView zoneGetView = getResponseEntity(ZoneGetView.class,
                restResponse);
        return new ZoneUpdateAXFRResponse(zoneGetView);
    }
}
