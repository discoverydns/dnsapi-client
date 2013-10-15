package com.discoverydns.dnsapiclient.internal.command.zone;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.discoverydns.dnsapiclient.command.zone.ZoneUpdateCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneUpdateResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.command.WithEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.WithEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.ZoneGetView;
import com.discoverydns.dnsapiclient.test.infrastructure.BaseExceptionMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ZoneUpdateCommandHandlerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private WebTarget mockBaseWebTarget;
    @Mock
    private WebTarget mockZoneUpdateTarget;
    @Mock
    private ZoneUpdateCommand mockZoneUpdateCommand;
    @Mock
    private CommandMetaData mockCommandMetaData;

    private ZoneUpdateCommandHandler zoneUpdateCommandHandler;

    @Before
    public void setup() throws Throwable {
        when(mockBaseWebTarget.path("zones/{zoneId}")).thenReturn(mockZoneUpdateTarget);

        zoneUpdateCommandHandler = new ZoneUpdateCommandHandler(mockBaseWebTarget);
    }

    @Test
    public void shouldInitialiseZoneUpdateTarget() {
        verify(mockBaseWebTarget).path("zones/{zoneId}");
    }

    @Test
    public void shouldGetTargetRelativeToProvidedCommand() {
        String id = "id";
        when(mockZoneUpdateCommand.getId()).thenReturn(id);
        WebTarget resultingWebTarget = mock(WebTarget.class);
        when(mockZoneUpdateTarget.resolveTemplate("zoneId", id))
                .thenReturn(resultingWebTarget);

        assertEquals(resultingWebTarget,
                zoneUpdateCommandHandler.getWebTarget(mockZoneUpdateCommand, mockCommandMetaData));
    }

    @Test
    public void shouldWrapExceptionOccurringDuringResolvingTemplate() {
        String id = "id";
        when(mockZoneUpdateCommand.getId()).thenReturn(id);
        RuntimeException mockRuntimeException = mock(RuntimeException.class);
        when(mockZoneUpdateTarget.resolveTemplate("zoneId", id))
                .thenThrow(mockRuntimeException);

        thrown.expect(new BaseExceptionMatcher(DNSAPIClientException.class,
                DNSAPIClientException.DNSAPIClientExceptionCode.requiredParameterMissing,
                new Object[]{"id"}, mockRuntimeException));

        zoneUpdateCommandHandler.getWebTarget(mockZoneUpdateCommand, mockCommandMetaData);
    }

    @Test
    public void shouldReturnWithEntityInvocationBuilderFactory() {
        InvocationBuilderFactory invocationBuilderFactory =
                zoneUpdateCommandHandler.getInvocationBuilderFactory(
                mockZoneUpdateCommand, mockCommandMetaData);

        assertThat(
                invocationBuilderFactory,
                instanceOf(WithEntityInvocationBuilderFactory.class));

        invocationBuilderFactory.buildInvocationBuilder(mockZoneUpdateTarget);

        verify(mockZoneUpdateTarget).request(MediaType.APPLICATION_JSON_TYPE);
    }

    @Test
    public void shouldReturnNoEntityInvocationBuildInvoker() {
        assertThat(
                zoneUpdateCommandHandler.getInvocationBuildInvoker(
                        mockZoneUpdateCommand, mockCommandMetaData),
                instanceOf(WithEntityInvocationBuildInvoker.class));
    }

    @Test
    public void shouldReturnExpectedZoneUpdateResponse() {
        Response mockRestResponse = mock(Response.class);
        ZoneGetView mockZoneGetView = mock(ZoneGetView.class);
        when(mockRestResponse.hasEntity()).thenReturn(true);
        when(mockRestResponse.readEntity(ZoneGetView.class)).thenReturn(mockZoneGetView);
        String name = "name";
        when(mockZoneGetView.getName()).thenReturn(name);

        ZoneUpdateResponse commandResponse =
                zoneUpdateCommandHandler.getCommandResponse(
                        mockRestResponse, mockCommandMetaData);

        assertEquals(name, commandResponse.getName());
    }
}
