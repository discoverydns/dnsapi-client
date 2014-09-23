package com.discoverydns.dnsapiclient.internal.command.zone;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.discoverydns.dnsapiclient.command.zone.ZoneRefreshAXFRCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneRefreshResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.test.infrastructure.BaseExceptionMatcher;

@RunWith(MockitoJUnitRunner.class)
public class ZoneRefreshAXFRCommandHandlerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private WebTarget mockBaseWebTarget;
    @Mock
    private WebTarget mockZoneRefreshAXFRTarget;
    @Mock
    private ZoneRefreshAXFRCommand mockZoneRefreshAXFRCommand;
    @Mock
    private CommandMetaData mockCommandMetaData;

    private ZoneRefreshAXFRCommandHandler zoneRefreshAXFRCommandHandler;

    @Before
    public void setup() throws Throwable {
        when(mockBaseWebTarget.path("zones/{zoneId}/refresh")).thenReturn(mockZoneRefreshAXFRTarget);

        zoneRefreshAXFRCommandHandler = new ZoneRefreshAXFRCommandHandler(mockBaseWebTarget);
    }

    @Test
    public void shouldInitialiseZoneDeleteTarget() {
        verify(mockBaseWebTarget).path("zones/{zoneId}/refresh");
    }

    @Test
    public void shouldGetTargetRelativeToProvidedCommand() {
        String id = "id";
        when(mockZoneRefreshAXFRCommand.getId()).thenReturn(id);
        WebTarget resultingWebTarget = mock(WebTarget.class);
        when(mockZoneRefreshAXFRTarget.resolveTemplate("zoneId", id))
                .thenReturn(resultingWebTarget);

        assertEquals(resultingWebTarget,
                zoneRefreshAXFRCommandHandler.getWebTarget(mockZoneRefreshAXFRCommand, mockCommandMetaData));
    }

    @Test
    public void shouldWrapExceptionOccurringDuringResolvingTemplate() {
        String id = "id";
        when(mockZoneRefreshAXFRCommand.getId()).thenReturn(id);
        RuntimeException mockRuntimeException = mock(RuntimeException.class);
        when(mockZoneRefreshAXFRTarget.resolveTemplate("zoneId", id))
                .thenThrow(mockRuntimeException);

        thrown.expect(new BaseExceptionMatcher(DNSAPIClientException.class,
                DNSAPIClientException.DNSAPIClientExceptionCode.requiredParameterMissing,
                new Object[]{"id"}, mockRuntimeException));

        zoneRefreshAXFRCommandHandler.getWebTarget(mockZoneRefreshAXFRCommand, mockCommandMetaData);
    }

    @Test
    public void shouldReturnNoEntityInvocationBuilderFactory() {
        assertThat(
                zoneRefreshAXFRCommandHandler.getInvocationBuilderFactory(
                        mockZoneRefreshAXFRCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuilderFactory.class));
    }

    @Test
    public void shouldReturnNoEntityInvocationBuildInvoker() {
        assertThat(
                zoneRefreshAXFRCommandHandler.getInvocationBuildInvoker(
                        mockZoneRefreshAXFRCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuildInvoker.class));
    }

    @Test
    public void shouldReturnExpectedZoneDeleteResponse() {
        Response mockRestResponse = mock(Response.class);

        assertThat(zoneRefreshAXFRCommandHandler.getCommandResponse(
                        mockRestResponse, mockCommandMetaData),
                instanceOf(ZoneRefreshResponse.class));
    }
}
