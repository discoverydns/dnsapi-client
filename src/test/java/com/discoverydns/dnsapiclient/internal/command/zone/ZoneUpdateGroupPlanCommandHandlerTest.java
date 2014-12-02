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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.discoverydns.dnsapiclient.command.zone.ZoneUpdateGroupPlanCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneUpdateGroupPlanResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.InvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.command.WithEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.WithEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.test.infrastructure.BaseExceptionMatcher;

@RunWith(MockitoJUnitRunner.class)
public class ZoneUpdateGroupPlanCommandHandlerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private WebTarget mockBaseWebTarget;
    @Mock
    private WebTarget mockZoneUpdateGroupPlanTarget;
    @Mock
    private ZoneUpdateGroupPlanCommand zoneUpdateGroupPlanCommand;
    @Mock
    private CommandMetaData mockCommandMetaData;

    private ZoneUpdateGroupPlanCommandHandler zoneUpdateGroupPlanCommandHandler;

    @Before
    public void setup() {
        when(mockBaseWebTarget.path("zones")).thenReturn(mockZoneUpdateGroupPlanTarget);

        zoneUpdateGroupPlanCommandHandler = new ZoneUpdateGroupPlanCommandHandler(mockBaseWebTarget);
    }

    @Test
    public void shouldInitialiseZoneUpdateTarget() {
        verify(mockBaseWebTarget).path("zones");
    }

    @Test
    public void shouldGetTargetRelativeToProvidedCommand() {
        String group = "group";
        when(zoneUpdateGroupPlanCommand.getGroup()).thenReturn(group);
        WebTarget resultingWebTarget = mock(WebTarget.class);
        when(mockZoneUpdateGroupPlanTarget.queryParam("group", group))
                .thenReturn(resultingWebTarget);

        assertEquals(resultingWebTarget,
                zoneUpdateGroupPlanCommandHandler.getWebTarget(zoneUpdateGroupPlanCommand, mockCommandMetaData));
    }

    @Test
    public void shouldWrapExceptionOccurringDuringResolvingTemplate() {
        String group = "group";
        when(zoneUpdateGroupPlanCommand.getGroup()).thenReturn(group);
        RuntimeException mockRuntimeException = mock(RuntimeException.class);
        when(mockZoneUpdateGroupPlanTarget.queryParam("group", group))
                .thenThrow(mockRuntimeException);

        thrown.expect(new BaseExceptionMatcher(DNSAPIClientException.class,
                DNSAPIClientException.DNSAPIClientExceptionCode.requiredParameterMissing,
                new Object[]{"group"}, mockRuntimeException));

        zoneUpdateGroupPlanCommandHandler.getWebTarget(zoneUpdateGroupPlanCommand, mockCommandMetaData);
    }


    @Test
    public void shouldReturnWithEntityInvocationBuilderFactory() {
        InvocationBuilderFactory invocationBuilderFactory =
                zoneUpdateGroupPlanCommandHandler.getInvocationBuilderFactory(
                        zoneUpdateGroupPlanCommand, mockCommandMetaData);

        assertThat(
                invocationBuilderFactory,
                instanceOf(WithEntityInvocationBuilderFactory.class));

        invocationBuilderFactory.buildInvocationBuilder(mockZoneUpdateGroupPlanTarget);

        verify(mockZoneUpdateGroupPlanTarget).request(MediaType.APPLICATION_JSON_TYPE);
    }

    @Test
    public void shouldReturnWithEntityInvocationBuildInvoker() {
        assertThat(
                zoneUpdateGroupPlanCommandHandler.getInvocationBuildInvoker(
                        zoneUpdateGroupPlanCommand, mockCommandMetaData),
                instanceOf(WithEntityInvocationBuildInvoker.class));
    }

    @Test
    public void shouldReturnExpectedZoneDeleteResponse() {
        Response mockRestResponse = mock(Response.class);

        assertThat(zoneUpdateGroupPlanCommandHandler.getCommandResponse(
                        mockRestResponse, mockCommandMetaData),
                instanceOf(ZoneUpdateGroupPlanResponse.class));
    }
}