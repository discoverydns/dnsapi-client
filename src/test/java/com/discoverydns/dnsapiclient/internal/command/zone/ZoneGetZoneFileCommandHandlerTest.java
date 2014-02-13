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

import com.discoverydns.dnsapiclient.command.zone.ZoneGetZoneFileCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneGetZoneFileResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.test.infrastructure.BaseExceptionMatcher;

@RunWith(MockitoJUnitRunner.class)
public class ZoneGetZoneFileCommandHandlerTest {
	@Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private WebTarget mockBaseWebTarget;
    @Mock
    private WebTarget mockZoneGetZoneFileTarget;
    @Mock
    private ZoneGetZoneFileCommand mockZoneGetZoneFileCommand;
    @Mock
    private CommandMetaData mockCommandMetaData;

    private ZoneGetZoneFileCommandHandler zoneGetZoneFileCommandHandler;

    @Before
    public void setup() {
        when(mockBaseWebTarget.path("zones/{zoneId}/zoneFile"))
                .thenReturn(mockZoneGetZoneFileTarget);

        zoneGetZoneFileCommandHandler = new ZoneGetZoneFileCommandHandler(mockBaseWebTarget);
    }

    @Test
    public void shouldInitialiseZoneGetZoneFileTarget() {
        verify(mockBaseWebTarget).path("zones/{zoneId}/zoneFile");
    }

    @Test
    public void shouldGetTargetRelativeToProvidedCommand() {
        String id = "id";
        when(mockZoneGetZoneFileCommand.getId()).thenReturn(id);
        WebTarget resultingWebTarget = mock(WebTarget.class);
        when(mockZoneGetZoneFileTarget.resolveTemplate("zoneId", id))
                .thenReturn(resultingWebTarget);

        assertEquals(resultingWebTarget,
                zoneGetZoneFileCommandHandler.getWebTarget(
                        mockZoneGetZoneFileCommand, mockCommandMetaData));
    }

    @Test
    public void shouldWrapExceptionOccurringDuringResolvingTemplate() {
        String id = "id";
        when(mockZoneGetZoneFileCommand.getId()).thenReturn(id);
        RuntimeException mockRuntimeException = mock(RuntimeException.class);
        when(mockZoneGetZoneFileTarget.resolveTemplate("zoneId", id))
                .thenThrow(mockRuntimeException);

        thrown.expect(new BaseExceptionMatcher(DNSAPIClientException.class,
                DNSAPIClientException.DNSAPIClientExceptionCode.requiredParameterMissing,
                new Object[]{"id"}, mockRuntimeException));

        zoneGetZoneFileCommandHandler.getWebTarget(
                mockZoneGetZoneFileCommand, mockCommandMetaData);
    }

    @Test
    public void shouldReturnNoEntityInvocationBuilderFactory() {
        assertThat(
                zoneGetZoneFileCommandHandler.getInvocationBuilderFactory(
                        mockZoneGetZoneFileCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuilderFactory.class));
    }

    @Test
    public void shouldReturnNoEntityInvocationBuildInvoker() {
        assertThat(
                zoneGetZoneFileCommandHandler.getInvocationBuildInvoker(
                        mockZoneGetZoneFileCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuildInvoker.class));
    }

    @Test
    public void shouldReturnExpectedZoneGetZoneFileResponse() {
        Response mockRestResponse = mock(Response.class);
        when(mockRestResponse.hasEntity()).thenReturn(true);
        byte[] zoneFile = "zoneFile".getBytes();
        when(mockRestResponse.readEntity(byte[].class)).thenReturn(zoneFile);

        ZoneGetZoneFileResponse commandResponse =
                zoneGetZoneFileCommandHandler.getCommandResponse(
                        mockRestResponse, mockCommandMetaData);

        assertEquals(zoneFile, commandResponse.getZoneFile());
    }
}

