package com.discoverydns.dnsapiclient.internal.command.plan;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.net.URI;

import com.discoverydns.dnsapiclient.command.plan.PlanListCommand;
import com.discoverydns.dnsapiclient.command.plan.PlanListResponse;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.PlanListView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PlanListCommandHandlerTest {
    @Mock
    private WebTarget mockBaseWebTarget;
    @Mock
    private WebTarget mockPlanListTarget;
    @Mock
    private PlanListCommand mockPlanListCommand;
    @Mock
    private CommandMetaData mockCommandMetaData;

    private PlanListCommandHandler planListCommandHandler;

    @Before
    public void setup() throws Throwable {
        when(mockBaseWebTarget.path("plans/"))
                .thenReturn(mockPlanListTarget);

        planListCommandHandler =
                new PlanListCommandHandler(mockBaseWebTarget);
    }

    @Test
    public void shouldInitialisePlanListTarget() {
        verify(mockBaseWebTarget).path("plans/");
    }

    @Test
    public void shouldAddSearchStatusAsQueryParamIfNotNull() {
        String searchStatus = "searchStatus";
        when(mockPlanListCommand.getSearchStatus()).thenReturn(searchStatus);

        planListCommandHandler.getWebTarget(
                mockPlanListCommand, mockCommandMetaData);

        verify(mockPlanListTarget).queryParam("searchStatus", searchStatus);
    }

    @Test
    public void shouldAddSearchNameAsQueryParamIfNotNull() {
        String searchName = "searchName";
        when(mockPlanListCommand.getSearchName()).thenReturn(searchName);

        planListCommandHandler.getWebTarget(
                mockPlanListCommand, mockCommandMetaData);

        verify(mockPlanListTarget).queryParam("searchName", searchName);
    }

    @Test
    public void shouldNotAddQueryParamsOtherwise() {
        planListCommandHandler.getWebTarget(
                mockPlanListCommand, mockCommandMetaData);

        verifyZeroInteractions(mockPlanListTarget);
    }

    @Test
    public void shouldListTargetRelativeToProvidedCommand() {
        assertEquals(mockPlanListTarget,
                planListCommandHandler.getWebTarget(
                        mockPlanListCommand, mockCommandMetaData));
    }

    @Test
    public void shouldReturnNoEntityInvocationBuilderFactory() {
        assertThat(
                planListCommandHandler.getInvocationBuilderFactory(
                        mockPlanListCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuilderFactory.class));
    }

    @Test
    public void shouldReturnNoEntityInvocationBuildInvoker() {
        assertThat(
                planListCommandHandler.getInvocationBuildInvoker(
                        mockPlanListCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuildInvoker.class));
    }

    @Test
    public void shouldReturnExpectedPlanListResponse() throws Exception {
        Response mockRestResponse = mock(Response.class);
        PlanListView mockPlanListView
                = mock(PlanListView.class);
        when(mockRestResponse.hasEntity()).thenReturn(true);
        when(mockRestResponse.readEntity(PlanListView.class))
                .thenReturn(mockPlanListView);
        URI uri = new URI("http://ddns.com/plans");
        when(mockPlanListView.getUri()).thenReturn(uri);

        PlanListResponse commandResponse =
                planListCommandHandler.getCommandResponse(
                        mockRestResponse, mockCommandMetaData);

        assertEquals(uri, commandResponse.getURI());
    }
}

