package com.discoverydns.dnsapiclient.internal.command.zone;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.discoverydns.dnsapiclient.command.zone.ZoneCreateAXFRCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneCreateAXFRResponse;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.command.WithEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.WithEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.response.ZoneGetView;

@RunWith(MockitoJUnitRunner.class)
public class ZoneCreateAXFRCommandHandlerTest {
    @Mock
    private WebTarget mockBaseWebTarget;
    @Mock
    private WebTarget mockZoneCreateAXFRTarget;
    @Mock
    private ZoneCreateAXFRCommand mockZoneCreateAXFRCommand;
    @Mock
    private CommandMetaData mockCommandMetaData;

    private ZoneCreateAXFRCommandHandler zoneCreateAXFRCommandHandler;

    @Before
    public void setup() throws Throwable {
        when(mockBaseWebTarget.path("zones/axfr")).thenReturn(mockZoneCreateAXFRTarget);

        zoneCreateAXFRCommandHandler = new ZoneCreateAXFRCommandHandler(mockBaseWebTarget);
    }

    @Test
    public void shouldInitialiseZoneCreateAXFRTarget() {
        verify(mockBaseWebTarget).path("zones/axfr");
    }

    @Test
    public void shouldGetTargetRelativeToProvidedCommand() {
        assertEquals(mockZoneCreateAXFRTarget,
                zoneCreateAXFRCommandHandler.getWebTarget(mockZoneCreateAXFRCommand, mockCommandMetaData));
    }

    @Test
    public void shouldReturnWithEntityInvocationBuilderFactory() {
        InvocationBuilderFactory invocationBuilderFactory =
                zoneCreateAXFRCommandHandler.getInvocationBuilderFactory(
                        mockZoneCreateAXFRCommand, mockCommandMetaData);

        assertThat(
                invocationBuilderFactory,
                instanceOf(WithEntityInvocationBuilderFactory.class));

        invocationBuilderFactory.buildInvocationBuilder(mockZoneCreateAXFRTarget);

        verify(mockZoneCreateAXFRTarget).request(MediaType.APPLICATION_JSON_TYPE);
    }

    @Test
    public void shouldReturnNoEntityInvocationBuildInvoker() {
        assertThat(
                zoneCreateAXFRCommandHandler.getInvocationBuildInvoker(
                        mockZoneCreateAXFRCommand, mockCommandMetaData),
                instanceOf(WithEntityInvocationBuildInvoker.class));
    }

    @Test
    public void shouldReturnExpectedZoneCreateAXFRResponse() {
        Response mockRestResponse = mock(Response.class);
        ZoneGetView mockZoneGetView = mock(ZoneGetView.class);
        when(mockRestResponse.hasEntity()).thenReturn(true);
        when(mockRestResponse.readEntity(ZoneGetView.class)).thenReturn(mockZoneGetView);
        String name = "name";
        when(mockZoneGetView.getName()).thenReturn(name);

        ZoneCreateAXFRResponse commandResponse =
                zoneCreateAXFRCommandHandler.getCommandResponse(
                        mockRestResponse, mockCommandMetaData);

        assertEquals(name, commandResponse.getName());
    }
}
