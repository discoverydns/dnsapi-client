package com.discoverydns.dnsapiclient.internal.command.zone;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.discoverydns.dnsapiclient.command.zone.ZoneGetCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneGetResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
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
public class ZoneGetCommandHandlerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private WebTarget mockBaseWebTarget;
    @Mock
    private WebTarget mockZoneGetTarget;
    @Mock
    private ZoneGetCommand mockZoneGetCommand;
    @Mock
    private CommandMetaData mockCommandMetaData;

    private ZoneGetCommandHandler zoneGetCommandHandler;

    @Before
    public void setup() throws Throwable {
        when(mockBaseWebTarget.path("zones/{zoneId}")).thenReturn(mockZoneGetTarget);

        zoneGetCommandHandler = new ZoneGetCommandHandler(mockBaseWebTarget);
    }

    @Test
    public void shouldInitialiseZoneGetTarget() {
        verify(mockBaseWebTarget).path("zones/{zoneId}");
    }

    @Test
    public void shouldGetTargetRelativeToProvidedCommand() {
        String id = "id";
        when(mockZoneGetCommand.getId()).thenReturn(id);
        WebTarget resultingWebTarget = mock(WebTarget.class);
        when(mockZoneGetTarget.resolveTemplate("zoneId", id))
                .thenReturn(resultingWebTarget);

        assertEquals(resultingWebTarget,
                zoneGetCommandHandler.getWebTarget(mockZoneGetCommand, mockCommandMetaData));
    }

    @Test
    public void shouldWrapExceptionOccurringDuringResolvingTemplate() {
        String id = "id";
        when(mockZoneGetCommand.getId()).thenReturn(id);
        RuntimeException mockRuntimeException = mock(RuntimeException.class);
        when(mockZoneGetTarget.resolveTemplate("zoneId", id))
                .thenThrow(mockRuntimeException);

        thrown.expect(new BaseExceptionMatcher(DNSAPIClientException.class,
                DNSAPIClientException.DNSAPIClientExceptionCode.requiredParameterMissing,
                new Object[]{"id"}, mockRuntimeException));

        zoneGetCommandHandler.getWebTarget(mockZoneGetCommand, mockCommandMetaData);
    }

    @Test
    public void shouldReturnNoEntityInvocationBuilderFactory() {
        assertThat(
                zoneGetCommandHandler.getInvocationBuilderFactory(
                        mockZoneGetCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuilderFactory.class));
    }

    @Test
    public void shouldReturnNoEntityInvocationBuildInvoker() {
        assertThat(
                zoneGetCommandHandler.getInvocationBuildInvoker(
                        mockZoneGetCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuildInvoker.class));
    }

    @Test
    public void shouldReturnExpectedZoneGetResponse() {
        Response mockRestResponse = mock(Response.class);
        ZoneGetView mockZoneGetView = mock(ZoneGetView.class);
        when(mockRestResponse.hasEntity()).thenReturn(true);
        when(mockRestResponse.readEntity(ZoneGetView.class)).thenReturn(mockZoneGetView);
        String id = "id";
        when(mockZoneGetView.getId()).thenReturn(id);

        ZoneGetResponse commandResponse =
                zoneGetCommandHandler.getCommandResponse(
                        mockRestResponse, mockCommandMetaData);

        assertEquals(id, commandResponse.getId());
    }
}
