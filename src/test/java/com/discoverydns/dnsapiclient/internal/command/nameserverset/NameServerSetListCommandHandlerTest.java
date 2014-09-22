package com.discoverydns.dnsapiclient.internal.command.nameserverset;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import java.net.URI;

import com.discoverydns.dnsapiclient.command.nameServerSet.NameServerSetListCommand;
import com.discoverydns.dnsapiclient.command.nameServerSet.NameServerSetListResponse;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.response.NameServerSetListView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NameServerSetListCommandHandlerTest {
    @Mock
    private WebTarget mockBaseWebTarget;
    @Mock
    private WebTarget mockNameServerSetListTarget;
    @Mock
    private NameServerSetListCommand mockNameServerSetListCommand;
    @Mock
    private CommandMetaData mockCommandMetaData;

    private NameServerSetListCommandHandler nameServerSetListCommandHandler;

    @Before
    public void setup() throws Throwable {
        when(mockBaseWebTarget.path("nameserversets/"))
                .thenReturn(mockNameServerSetListTarget);

        nameServerSetListCommandHandler =
                new NameServerSetListCommandHandler(mockBaseWebTarget);
    }

    @Test
    public void shouldInitialiseNameServerSetListTarget() {
        verify(mockBaseWebTarget).path("nameserversets/");
    }

    @Test
    public void shouldAddSearchStatusAsQueryParamIfNotNull() {
        String searchStatus = "searchStatus";
        when(mockNameServerSetListCommand.getSearchStatus()).thenReturn(searchStatus);

        nameServerSetListCommandHandler.getWebTarget(
                mockNameServerSetListCommand, mockCommandMetaData);

        verify(mockNameServerSetListTarget).queryParam("searchStatus", searchStatus);
    }

    @Test
    public void shouldAddSearchNameAsQueryParamIfNotNull() {
        String searchName = "searchName";
        when(mockNameServerSetListCommand.getSearchName()).thenReturn(searchName);

        nameServerSetListCommandHandler.getWebTarget(
                mockNameServerSetListCommand, mockCommandMetaData);

        verify(mockNameServerSetListTarget).queryParam("searchName", searchName);
    }

    @Test
    public void shouldAddSearchNameServerInterfaceSetIdAsQueryParamIfNotNull() {
        String searchNameServerInterfaceSetId = "searchNameServerInterfaceSetId";
        when(mockNameServerSetListCommand.getSearchNameServerInterfaceSetId())
                .thenReturn(searchNameServerInterfaceSetId);

        nameServerSetListCommandHandler.getWebTarget(
                mockNameServerSetListCommand, mockCommandMetaData);

        verify(mockNameServerSetListTarget).queryParam("searchNameServerInterfaceSetId",
                searchNameServerInterfaceSetId);
    }

    @Test
    public void shouldNotAddQueryParamsOtherwise() {
        nameServerSetListCommandHandler.getWebTarget(
                mockNameServerSetListCommand, mockCommandMetaData);

        verifyZeroInteractions(mockNameServerSetListTarget);
    }

    @Test
    public void shouldListTargetRelativeToProvidedCommand() {
        assertEquals(mockNameServerSetListTarget,
                nameServerSetListCommandHandler.getWebTarget(
                        mockNameServerSetListCommand, mockCommandMetaData));
    }

    @Test
    public void shouldReturnNoEntityInvocationBuilderFactory() {
        assertThat(
                nameServerSetListCommandHandler.getInvocationBuilderFactory(
                        mockNameServerSetListCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuilderFactory.class));
    }

    @Test
    public void shouldReturnNoEntityInvocationBuildInvoker() {
        assertThat(
                nameServerSetListCommandHandler.getInvocationBuildInvoker(
                        mockNameServerSetListCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuildInvoker.class));
    }

    @Test
    public void shouldReturnExpectedNameServerSetListResponse() throws Exception {
        Response mockRestResponse = mock(Response.class);
        NameServerSetListView mockNameServerSetListView
                = mock(NameServerSetListView.class);
        when(mockRestResponse.hasEntity()).thenReturn(true);
        when(mockRestResponse.readEntity(NameServerSetListView.class))
                .thenReturn(mockNameServerSetListView);
        URI uri = new URI("http://ddns.com/nameserversets");
        when(mockNameServerSetListView.getUri()).thenReturn(uri);

        NameServerSetListResponse commandResponse =
                nameServerSetListCommandHandler.getCommandResponse(
                        mockRestResponse, mockCommandMetaData);

        assertEquals(uri, commandResponse.getURI());
    }
}
