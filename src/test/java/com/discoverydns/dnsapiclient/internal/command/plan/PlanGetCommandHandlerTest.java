package com.discoverydns.dnsapiclient.internal.command.plan;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.discoverydns.dnsapiclient.command.plan.PlanGetCommand;
import com.discoverydns.dnsapiclient.command.plan.PlanGetResponse;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.PlanGetView;
import com.discoverydns.dnsapiclient.test.infrastructure.BaseExceptionMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PlanGetCommandHandlerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private WebTarget mockBaseWebTarget;
    @Mock
    private WebTarget mockPlanGetTarget;
    @Mock
    private PlanGetCommand mockPlanGetCommand;
    @Mock
    private CommandMetaData mockCommandMetaData;

    private PlanGetCommandHandler planGetCommandHandler;

    @Before
    public void setup() throws Throwable {
        when(mockBaseWebTarget.path("plans/{planId}")).thenReturn(mockPlanGetTarget);

        planGetCommandHandler = new PlanGetCommandHandler(mockBaseWebTarget);
    }

    @Test
    public void shouldInitialisePlanGetTarget() {
        verify(mockBaseWebTarget).path("plans/{planId}");
    }

    @Test
    public void shouldGetTargetRelativeToProvidedCommand() {
        String idOrName = "idOrName";
        when(mockPlanGetCommand.getIdOrName()).thenReturn(idOrName);
        WebTarget resultingWebTarget = mock(WebTarget.class);
        when(mockPlanGetTarget.resolveTemplate("planId", idOrName))
                .thenReturn(resultingWebTarget);

        assertEquals(resultingWebTarget,
                planGetCommandHandler.getWebTarget(mockPlanGetCommand, mockCommandMetaData));
    }

    @Test
    public void shouldWrapExceptionOccurringDuringResolvingTemplate() {
        String idOrName = "idOrName";
        when(mockPlanGetCommand.getIdOrName()).thenReturn(idOrName);
        RuntimeException mockRuntimeException = mock(RuntimeException.class);
        when(mockPlanGetTarget.resolveTemplate("planId", idOrName))
                .thenThrow(mockRuntimeException);

        thrown.expect(new BaseExceptionMatcher(DNSAPIClientException.class,
                DNSAPIClientException.DNSAPIClientExceptionCode.requiredParameterMissing,
                new Object[]{"idOrName"}, mockRuntimeException));

        planGetCommandHandler.getWebTarget(mockPlanGetCommand, mockCommandMetaData);
    }

    @Test
    public void shouldReturnNoEntityInvocationBuilderFactory() {
        assertThat(
                planGetCommandHandler.getInvocationBuilderFactory(
                        mockPlanGetCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuilderFactory.class));
    }

    @Test
    public void shouldReturnNoEntityInvocationBuildInvoker() {
        assertThat(
                planGetCommandHandler.getInvocationBuildInvoker(
                        mockPlanGetCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuildInvoker.class));
    }

    @Test
    public void shouldReturnExpectedPlanGetResponse() {
        Response mockRestResponse = mock(Response.class);
        PlanGetView mockPlanGetView = mock(PlanGetView.class);
        when(mockRestResponse.hasEntity()).thenReturn(true);
        when(mockRestResponse.readEntity(PlanGetView.class)).thenReturn(mockPlanGetView);
        String id = "id";
        when(mockPlanGetView.getId()).thenReturn(id);

        PlanGetResponse commandResponse =
                planGetCommandHandler.getCommandResponse(
                        mockRestResponse, mockCommandMetaData);

        assertEquals(id, commandResponse.getId());
    }
}
