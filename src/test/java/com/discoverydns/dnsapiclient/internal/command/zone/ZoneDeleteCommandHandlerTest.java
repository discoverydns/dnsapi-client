package com.discoverydns.dnsapiclient.internal.command.zone;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.discoverydns.dnsapiclient.command.zone.ZoneDeleteCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneDeleteResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.test.infrastructure.BaseExceptionMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ZoneDeleteCommandHandlerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private WebTarget mockBaseWebTarget;
    @Mock
    private WebTarget mockZoneDeleteTarget;
    @Mock
    private ZoneDeleteCommand mockZoneDeleteCommand;
    @Mock
    private CommandMetaData mockCommandMetaData;

    private ZoneDeleteCommandHandler zoneDeleteCommandHandler;

    @Before
    public void setup() throws Throwable {
        when(mockBaseWebTarget.path("zones/{zoneId}")).thenReturn(mockZoneDeleteTarget);

        zoneDeleteCommandHandler = new ZoneDeleteCommandHandler(mockBaseWebTarget);
    }

    @Test
    public void shouldInitialiseZoneDeleteTarget() {
        verify(mockBaseWebTarget).path("zones/{zoneId}");
    }

    @Test
    public void shouldGetTargetRelativeToProvidedCommand() {
        String id = "id";
        when(mockZoneDeleteCommand.getId()).thenReturn(id);
        WebTarget resultingWebTarget = mock(WebTarget.class);
        when(mockZoneDeleteTarget.resolveTemplate("zoneId", id))
                .thenReturn(resultingWebTarget);

        assertEquals(resultingWebTarget,
                zoneDeleteCommandHandler.getWebTarget(mockZoneDeleteCommand, mockCommandMetaData));
    }

    @Test
    public void shouldWrapExceptionOccurringDuringResolvingTemplate() {
        String id = "id";
        when(mockZoneDeleteCommand.getId()).thenReturn(id);
        RuntimeException mockRuntimeException = mock(RuntimeException.class);
        when(mockZoneDeleteTarget.resolveTemplate("zoneId", id))
                .thenThrow(mockRuntimeException);

        thrown.expect(new BaseExceptionMatcher(DNSAPIClientException.class,
                DNSAPIClientException.DNSAPIClientExceptionCode.requiredParameterMissing,
                new Object[]{"id"}, mockRuntimeException));

        zoneDeleteCommandHandler.getWebTarget(mockZoneDeleteCommand, mockCommandMetaData);
    }

    @Test
    public void shouldReturnNoEntityInvocationBuilderFactory() {
        assertThat(
                zoneDeleteCommandHandler.getInvocationBuilderFactory(
                        mockZoneDeleteCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuilderFactory.class));
    }

    @Test
    public void shouldReturnNoEntityInvocationBuildInvoker() {
        assertThat(
                zoneDeleteCommandHandler.getInvocationBuildInvoker(
                        mockZoneDeleteCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuildInvoker.class));
    }

    @Test
    public void shouldReturnExpectedZoneDeleteResponse() {
        Response mockRestResponse = mock(Response.class);

        assertThat(zoneDeleteCommandHandler.getCommandResponse(
                        mockRestResponse, mockCommandMetaData),
                instanceOf(ZoneDeleteResponse.class));
    }
}
