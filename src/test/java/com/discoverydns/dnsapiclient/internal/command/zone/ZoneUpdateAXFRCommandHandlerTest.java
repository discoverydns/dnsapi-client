package com.discoverydns.dnsapiclient.internal.command.zone;

import com.discoverydns.dnsapiclient.command.zone.ZoneUpdateAXFRCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneUpdateAXFRResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.command.WithEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.WithEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.response.ZoneGetView;
import com.discoverydns.dnsapiclient.test.infrastructure.BaseExceptionMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ZoneUpdateAXFRCommandHandlerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Mock
    private WebTarget mockBaseWebTarget;
    @Mock
    private WebTarget mockZoneUpdateTarget;
    @Mock
    private ZoneUpdateAXFRCommand mockZoneUpdateAXFRCommand;
    @Mock
    private CommandMetaData mockCommandMetaData;

    private ZoneUpdateAXFRCommandHandler zoneUpdateAXFRCommandHandler;

    @Before
    public void setUp() throws Exception {
        when(mockBaseWebTarget.path("zones/{zoneId}/xfrDetails")).thenReturn(mockZoneUpdateTarget);
        zoneUpdateAXFRCommandHandler = new ZoneUpdateAXFRCommandHandler(mockBaseWebTarget);
    }

    @Test
    public void shouldInitialiseZoneUpdateTarget() {
        verify(mockBaseWebTarget).path("zones/{zoneId}/xfrDetails");
    }

    @Test
    public void shouldGetTargetRelativeToProvidedCommand() {
        String id = "id";
        when(mockZoneUpdateAXFRCommand.getId()).thenReturn(id);
        WebTarget resultingWebTarget = mock(WebTarget.class);
        when(mockZoneUpdateTarget.resolveTemplate("zoneId", id))
                .thenReturn(resultingWebTarget);

        WebTarget webTarget = zoneUpdateAXFRCommandHandler
                .getWebTarget(mockZoneUpdateAXFRCommand, mockCommandMetaData);

        assertThat(webTarget, is(resultingWebTarget));
    }

    @Test
    public void shouldWrapExceptionOccurringDuringResolvingTemplate() {
        String id = "id";
        when(mockZoneUpdateAXFRCommand.getId()).thenReturn(id);
        RuntimeException mockRuntimeException = mock(RuntimeException.class);
        when(mockZoneUpdateTarget.resolveTemplate("zoneId", id))
                .thenThrow(mockRuntimeException);

        thrown.expect(new BaseExceptionMatcher(DNSAPIClientException.class,
                DNSAPIClientException.DNSAPIClientExceptionCode.requiredParameterMissing,
                new Object[] { "id" }, mockRuntimeException));

        zoneUpdateAXFRCommandHandler.getWebTarget(mockZoneUpdateAXFRCommand, mockCommandMetaData);
    }

    @Test
    public void shouldReturnWithEntityInvocationBuilderFactory() {
        InvocationBuilderFactory invocationBuilderFactory =
                zoneUpdateAXFRCommandHandler.getInvocationBuilderFactory(
                        mockZoneUpdateAXFRCommand, mockCommandMetaData);
        invocationBuilderFactory.buildInvocationBuilder(mockZoneUpdateTarget);

        assertThat(
                invocationBuilderFactory,
                instanceOf(WithEntityInvocationBuilderFactory.class));
        verify(mockZoneUpdateTarget).request(MediaType.APPLICATION_JSON_TYPE);
    }

    @Test
    public void shouldReturnNoEntityInvocationBuildInvoker() {
        InvocationBuildInvoker invoker = zoneUpdateAXFRCommandHandler
                .getInvocationBuildInvoker(mockZoneUpdateAXFRCommand, mockCommandMetaData);
        assertThat(invoker,
                instanceOf(WithEntityInvocationBuildInvoker.class));
    }

    @Test
    public void shouldReturnExpectedZoneUpdateAXFRResponse() {
        Response mockRestResponse = mock(Response.class);
        ZoneGetView mockZoneGetView = mock(ZoneGetView.class);
        when(mockRestResponse.hasEntity()).thenReturn(true);
        when(mockRestResponse.readEntity(ZoneGetView.class))
                .thenReturn(mockZoneGetView);
        String name = "name";
        when(mockZoneGetView.getName()).thenReturn(name);

        ZoneUpdateAXFRResponse commandResponse =
                zoneUpdateAXFRCommandHandler.getCommandResponse(
                        mockRestResponse, mockCommandMetaData);

        assertThat(commandResponse.getName(), is(name));
    }

}
