package com.discoverydns.dnsapiclient.internal.command.zone;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.discoverydns.dnsapiclient.command.zone.ZoneUpdateResourceRecordsCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneUpdateResourceRecordsResponse;
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
public class ZoneUpdateResourceRecordsCommandHandlerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private WebTarget mockBaseWebTarget;
    @Mock
    private WebTarget mockZoneUpdateResourceRecordsTarget;
    @Mock
    private ZoneUpdateResourceRecordsCommand mockZoneUpdateResourceRecordsCommand;
    @Mock
    private CommandMetaData mockCommandMetaData;

    private ZoneUpdateResourceRecordsCommandHandler zoneUpdateResourceRecordsCommandHandler;

    @Before
    public void setup() throws Throwable {
        when(mockBaseWebTarget.path("zones/{zoneId}/resourcerecords/")).thenReturn(mockZoneUpdateResourceRecordsTarget);

        zoneUpdateResourceRecordsCommandHandler = new ZoneUpdateResourceRecordsCommandHandler(mockBaseWebTarget);
    }

    @Test
    public void shouldInitialiseZoneUpdateResourceRecordsTarget() {
        verify(mockBaseWebTarget).path("zones/{zoneId}/resourcerecords/");
    }

    @Test
    public void shouldGetTargetRelativeToProvidedCommand() {
        String id = "id";
        when(mockZoneUpdateResourceRecordsCommand.getId()).thenReturn(id);
        WebTarget resultingWebTarget = mock(WebTarget.class);
        when(mockZoneUpdateResourceRecordsTarget.resolveTemplate("zoneId", id))
                .thenReturn(resultingWebTarget);

        assertEquals(resultingWebTarget,
                zoneUpdateResourceRecordsCommandHandler.getWebTarget(
                        mockZoneUpdateResourceRecordsCommand, mockCommandMetaData));
    }

    @Test
    public void shouldWrapExceptionOccurringDuringResolvingTemplate() {
        String id = "id";
        when(mockZoneUpdateResourceRecordsCommand.getId()).thenReturn(id);
        RuntimeException mockRuntimeException = mock(RuntimeException.class);
        when(mockZoneUpdateResourceRecordsTarget.resolveTemplate("zoneId", id))
                .thenThrow(mockRuntimeException);

        thrown.expect(new BaseExceptionMatcher(DNSAPIClientException.class,
                DNSAPIClientException.DNSAPIClientExceptionCode.requiredParameterMissing,
                new Object[]{"id"}, mockRuntimeException));

        zoneUpdateResourceRecordsCommandHandler.getWebTarget(
                mockZoneUpdateResourceRecordsCommand, mockCommandMetaData);
    }

    @Test
    public void shouldReturnWithEntityInvocationBuilderFactory() {
        InvocationBuilderFactory invocationBuilderFactory =
                zoneUpdateResourceRecordsCommandHandler.getInvocationBuilderFactory(
                mockZoneUpdateResourceRecordsCommand, mockCommandMetaData);

        assertThat(
                invocationBuilderFactory,
                instanceOf(WithEntityInvocationBuilderFactory.class));

        invocationBuilderFactory.buildInvocationBuilder(mockZoneUpdateResourceRecordsTarget);

        verify(mockZoneUpdateResourceRecordsTarget).request(MediaType.APPLICATION_JSON_TYPE);
    }

    @Test
    public void shouldReturnNoEntityInvocationBuildInvoker() {
        assertThat(
                zoneUpdateResourceRecordsCommandHandler.getInvocationBuildInvoker(
                        mockZoneUpdateResourceRecordsCommand, mockCommandMetaData),
                instanceOf(WithEntityInvocationBuildInvoker.class));
    }

    @Test
    public void shouldReturnExpectedZoneUpdateResourceRecordsResponse() {
        Response mockRestResponse = mock(Response.class);
        ZoneGetView mockZoneGetView = mock(ZoneGetView.class);
        when(mockRestResponse.hasEntity()).thenReturn(true);
        when(mockRestResponse.readEntity(ZoneGetView.class)).thenReturn(mockZoneGetView);
        String name = "name";
        when(mockZoneGetView.getName()).thenReturn(name);

        ZoneUpdateResourceRecordsResponse commandResponse =
                zoneUpdateResourceRecordsCommandHandler.getCommandResponse(
                        mockRestResponse, mockCommandMetaData);

        assertEquals(name, commandResponse.getName());
    }
}
