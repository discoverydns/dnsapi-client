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

import com.discoverydns.dnsapiclient.command.zone.ZoneCreateCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneCreateResponse;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.command.WithEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.WithEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.response.ZoneGetView;

@RunWith(MockitoJUnitRunner.class)
public class ZoneCreateCommandHandlerTest {
    @Mock
    private WebTarget mockBaseWebTarget;
    @Mock
    private WebTarget mockZoneCreateTarget;
    @Mock
    private ZoneCreateCommand mockZoneCreateCommand;
    @Mock
    private CommandMetaData mockCommandMetaData;

    private ZoneCreateCommandHandler zoneCreateCommandHandler;

    @Before
    public void setup() throws Throwable {
        when(mockBaseWebTarget.path("zones")).thenReturn(mockZoneCreateTarget);

        zoneCreateCommandHandler = new ZoneCreateCommandHandler(mockBaseWebTarget);
    }

    @Test
    public void shouldInitialiseZoneCreateTarget() {
        verify(mockBaseWebTarget).path("zones");
    }

    @Test
    public void shouldGetTargetRelativeToProvidedCommand() {
        assertEquals(mockZoneCreateTarget,
                zoneCreateCommandHandler.getWebTarget(mockZoneCreateCommand, mockCommandMetaData));
    }

    @Test
    public void shouldReturnWithEntityInvocationBuilderFactory() {
        InvocationBuilderFactory invocationBuilderFactory =
                zoneCreateCommandHandler.getInvocationBuilderFactory(
                mockZoneCreateCommand, mockCommandMetaData);

        assertThat(
                invocationBuilderFactory,
                instanceOf(WithEntityInvocationBuilderFactory.class));

        invocationBuilderFactory.buildInvocationBuilder(mockZoneCreateTarget);

        verify(mockZoneCreateTarget).request(MediaType.APPLICATION_JSON_TYPE);
    }

    @Test
    public void shouldReturnNoEntityInvocationBuildInvoker() {
        assertThat(
                zoneCreateCommandHandler.getInvocationBuildInvoker(
                        mockZoneCreateCommand, mockCommandMetaData),
                instanceOf(WithEntityInvocationBuildInvoker.class));
    }

    @Test
    public void shouldReturnExpectedZoneCreateResponse() {
        Response mockRestResponse = mock(Response.class);
        ZoneGetView mockZoneGetView = mock(ZoneGetView.class);
        when(mockRestResponse.hasEntity()).thenReturn(true);
        when(mockRestResponse.readEntity(ZoneGetView.class)).thenReturn(mockZoneGetView);
        String name = "name";
        when(mockZoneGetView.getName()).thenReturn(name);

        ZoneCreateResponse commandResponse =
                zoneCreateCommandHandler.getCommandResponse(
                        mockRestResponse, mockCommandMetaData);

        assertEquals(name, commandResponse.getName());
    }
}
