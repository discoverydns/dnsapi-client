package com.discoverydns.dnsapiclient.internal.command.zone;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.discoverydns.dnsapiclient.command.zone.ZoneCreateAXFRCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneCreateAXFRResponse;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.BaseRestCommandHandler;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.command.Method;
import com.discoverydns.dnsapiclient.internal.command.WithEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.WithEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.request.ZoneCreateAXFRView;
import com.discoverydns.dnsapiclient.internal.views.response.ZoneGetView;

public class ZoneCreateAXFRCommandHandler extends
		BaseRestCommandHandler<ZoneCreateAXFRCommand, ZoneCreateAXFRResponse> {
    private static final MediaType REQUEST_MEDIA_TYPE = new MediaType("application", "secondary+json");
    private static final MediaType RESPONSE_MEDIA_TYPE = MediaType.APPLICATION_JSON_TYPE;

    private final WebTarget zoneCreateAXFRTarget;

    public ZoneCreateAXFRCommandHandler(final WebTarget baseWebTarget) {
        super(Method.POST, Status.CREATED.getStatusCode(), RESPONSE_MEDIA_TYPE);
        this.zoneCreateAXFRTarget = baseWebTarget.path("zones");
    }

    @Override
    public WebTarget getWebTarget(final ZoneCreateAXFRCommand command, final CommandMetaData commandMetaData) {
        return zoneCreateAXFRTarget;
    }

    @Override
    public InvocationBuilderFactory getInvocationBuilderFactory(final ZoneCreateAXFRCommand command,
                                                                final CommandMetaData commandMetaData) {
       return new WithEntityInvocationBuilderFactory(RESPONSE_MEDIA_TYPE);
    }

    @Override
    public InvocationBuildInvoker getInvocationBuildInvoker(final ZoneCreateAXFRCommand command,
                                                            final CommandMetaData commandMetaData) {
        final ZoneCreateAXFRView zoneCreateAXFRView = new ZoneCreateAXFRView(command);
        return new WithEntityInvocationBuildInvoker<ZoneCreateAXFRView>(zoneCreateAXFRView, REQUEST_MEDIA_TYPE);
    }

    @Override
    public ZoneCreateAXFRResponse getCommandResponse(final Response restResponse,
                                                     final CommandMetaData commandMetaData) {
       final ZoneGetView zoneGetView = getResponseEntity(ZoneGetView.class, restResponse);
       return new ZoneCreateAXFRResponse(zoneGetView);
    }

}
