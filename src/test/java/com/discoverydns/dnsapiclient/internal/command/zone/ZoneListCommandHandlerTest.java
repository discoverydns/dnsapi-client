package com.discoverydns.dnsapiclient.internal.command.zone;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.net.URI;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.discoverydns.dnsapiclient.command.zone.ZoneListCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneListResponse;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.ZoneListView;

@RunWith(MockitoJUnitRunner.class)
public class ZoneListCommandHandlerTest {
    @Mock
    private WebTarget mockBaseWebTarget;
    @Mock
    private WebTarget mockZoneListTarget;
    @Mock
    private ZoneListCommand mockZoneListCommand;
    @Mock
    private CommandMetaData mockCommandMetaData;

    private ZoneListCommandHandler zoneListCommandHandler;

    @Before
    public void setup() throws Throwable {
        when(mockBaseWebTarget.path("zones/"))
                .thenReturn(mockZoneListTarget);
        when(mockZoneListCommand.getSearchBrandedNameServers()).thenReturn(null);
        when(mockZoneListCommand.getSearchDNSSECSigned()).thenReturn(null);

        zoneListCommandHandler =
                new ZoneListCommandHandler(mockBaseWebTarget);
    }

    @Test
    public void shouldInitialiseZoneListTarget() {
        verify(mockBaseWebTarget).path("zones/");
    }

    @Test
    public void shouldAddSearchNameAsQueryParamIfNotNull() {
        String searchName = "searchName";
        when(mockZoneListCommand.getSearchName()).thenReturn(searchName);

        zoneListCommandHandler.getWebTarget(
                mockZoneListCommand, mockCommandMetaData);

        verify(mockZoneListTarget).queryParam("searchName", searchName);
    }

    @Test
    public void shouldAddSearchNameServerInterfaceSetIdAsQueryParamIfNotNull() {
        String searchNameServerInterfaceSetId = "searchNameServerInterfaceSetId";
        when(mockZoneListCommand.getSearchNameServerInterfaceSetId())
                .thenReturn(searchNameServerInterfaceSetId);

        zoneListCommandHandler.getWebTarget(
                mockZoneListCommand, mockCommandMetaData);

        verify(mockZoneListTarget).queryParam("searchNameServerInterfaceSetId",
                searchNameServerInterfaceSetId);
    }

    @Test
    public void shouldAddSearchNameServerSetIdAsQueryParamIfNotNull() {
        String searchNameServerSetId = "searchNameServerSetId";
        when(mockZoneListCommand.getSearchNameServerSetId())
                .thenReturn(searchNameServerSetId);

        zoneListCommandHandler.getWebTarget(
                mockZoneListCommand, mockCommandMetaData);

        verify(mockZoneListTarget).queryParam("searchNameServerSetId",
                searchNameServerSetId);
    }
    
    @Test
    public void shouldAddSearchGroupAsQueryParamIfNotNull() {
        String searchGroup = "searchGroup";
        when(mockZoneListCommand.getSearchGroup()).thenReturn(searchGroup);

        zoneListCommandHandler.getWebTarget(
                mockZoneListCommand, mockCommandMetaData);

        verify(mockZoneListTarget).queryParam("searchGroup", searchGroup);
    }

    @Test
    public void shouldAddSearchBrandedNameServersAsQueryParamIfNotNull() {
        Boolean searchBrandedNameServers = true;
        when(mockZoneListCommand.getSearchBrandedNameServers())
                .thenReturn(searchBrandedNameServers);

        zoneListCommandHandler.getWebTarget(
                mockZoneListCommand, mockCommandMetaData);

        verify(mockZoneListTarget).queryParam("searchBrandedNameServers",
                searchBrandedNameServers);
    }

    @Test
    public void shouldAddSearchDNSSECSignedAsQueryParamIfNotNull() {
        Boolean searchDNSSECSigned = true;
        when(mockZoneListCommand.getSearchDNSSECSigned())
                .thenReturn(searchDNSSECSigned);

        zoneListCommandHandler.getWebTarget(
                mockZoneListCommand, mockCommandMetaData);

        verify(mockZoneListTarget).queryParam("searchDNSSECSigned",
                searchDNSSECSigned);
    }

    @Test
    public void shouldNotAddQueryParamsOtherwise() {
        zoneListCommandHandler.getWebTarget(
                mockZoneListCommand, mockCommandMetaData);

        verifyZeroInteractions(mockZoneListTarget);
    }

    @Test
    public void shouldListTargetRelativeToProvidedCommand() {
        assertEquals(mockZoneListTarget,
                zoneListCommandHandler.getWebTarget(
                        mockZoneListCommand, mockCommandMetaData));
    }

    @Test
    public void shouldReturnNoEntityInvocationBuilderFactory() {
        assertThat(
                zoneListCommandHandler.getInvocationBuilderFactory(
                        mockZoneListCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuilderFactory.class));
    }

    @Test
    public void shouldReturnNoEntityInvocationBuildInvoker() {
        assertThat(
                zoneListCommandHandler.getInvocationBuildInvoker(
                        mockZoneListCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuildInvoker.class));
    }

    @Test
    public void shouldReturnExpectedZoneListResponse() throws Exception {
        Response mockRestResponse = mock(Response.class);
        ZoneListView mockZoneListView
                = mock(ZoneListView.class);
        when(mockRestResponse.hasEntity()).thenReturn(true);
        when(mockRestResponse.readEntity(ZoneListView.class))
                .thenReturn(mockZoneListView);
        URI uri = new URI("http://ddns.com/zones");
        when(mockZoneListView.getUri()).thenReturn(uri);

        ZoneListResponse commandResponse =
                zoneListCommandHandler.getCommandResponse(
                        mockRestResponse, mockCommandMetaData);

        assertEquals(uri, commandResponse.getURI());
    }
}
