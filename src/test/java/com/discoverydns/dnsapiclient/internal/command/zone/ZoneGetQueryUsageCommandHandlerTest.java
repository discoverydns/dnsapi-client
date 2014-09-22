package com.discoverydns.dnsapiclient.internal.command.zone;

import com.discoverydns.dnsapiclient.command.zone.ZoneGetQueryUsageCommand;
import com.discoverydns.dnsapiclient.command.zone.ZoneGetQueryUsageResponse;
import com.discoverydns.dnsapiclient.command.zone.ZoneQueryUsageGranularity;
import com.discoverydns.dnsapiclient.exception.DNSAPIClientException;
import com.discoverydns.dnsapiclient.framework.command.CommandMetaData;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuildInvoker;
import com.discoverydns.dnsapiclient.internal.command.NoEntityInvocationBuilderFactory;
import com.discoverydns.dnsapiclient.internal.views.response.ZoneGetQueryUsageView;
import com.discoverydns.dnsapiclient.test.infrastructure.BaseExceptionMatcher;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import java.net.URI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ZoneGetQueryUsageCommandHandlerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private WebTarget mockBaseWebTarget;
    @Mock
    private WebTarget mockZoneGetQueryUsageTarget;
    @Mock
    private ZoneGetQueryUsageCommand mockZoneGetQueryUsageCommand;
    @Mock
    private CommandMetaData mockCommandMetaData;

    private ZoneGetQueryUsageCommandHandler zoneGetQueryUsageCommandHandler;

    @Before
    public void setup() {
        when(mockBaseWebTarget.path("zones/{zoneId}/queryUsage"))
                .thenReturn(mockZoneGetQueryUsageTarget);

        zoneGetQueryUsageCommandHandler = new ZoneGetQueryUsageCommandHandler(mockBaseWebTarget);
    }

    @Test
    public void shouldInitialiseZoneGetQueryUsageTarget() {
        verify(mockBaseWebTarget).path("zones/{zoneId}/queryUsage");
    }

    @Test
    public void shouldGetTargetRelativeToProvidedCommand() {
        String id = "id";
        LocalDateTime searchStartDate = LocalDateTime.now(DateTimeZone.UTC);
        LocalDateTime searchEndDate = LocalDateTime.now(DateTimeZone.UTC);
        ZoneQueryUsageGranularity searchGranularity = ZoneQueryUsageGranularity.daily;
        Boolean searchGroupUsage = true;
        when(mockZoneGetQueryUsageCommand.getId()).thenReturn(id);
        when(mockZoneGetQueryUsageCommand.getSearchStartDate()).thenReturn(searchStartDate);
        when(mockZoneGetQueryUsageCommand.getSearchEndDate()).thenReturn(searchEndDate);
        when(mockZoneGetQueryUsageCommand.getSearchGranularity()).thenReturn(searchGranularity);
        when(mockZoneGetQueryUsageCommand.isSearchGroupUsage()).thenReturn(searchGroupUsage);
        WebTarget resultingWebTarget = mock(WebTarget.class);
        when(mockZoneGetQueryUsageTarget.resolveTemplate("zoneId", id))
                .thenReturn(resultingWebTarget);
        when(resultingWebTarget.queryParam("searchStartDate", searchStartDate))
                .thenReturn(resultingWebTarget);
        when(resultingWebTarget.queryParam("searchEndDate", searchEndDate))
                .thenReturn(resultingWebTarget);
        when(resultingWebTarget.queryParam("searchGranularity", searchGranularity))
                .thenReturn(resultingWebTarget);
        when(resultingWebTarget.queryParam("searchGroupUsage", searchGroupUsage))
                .thenReturn(resultingWebTarget);

        assertEquals(resultingWebTarget,
                zoneGetQueryUsageCommandHandler.getWebTarget(
                        mockZoneGetQueryUsageCommand, mockCommandMetaData));

        verify(resultingWebTarget).queryParam("searchStartDate", searchStartDate);
        verify(resultingWebTarget).queryParam("searchEndDate", searchEndDate);
        verify(resultingWebTarget).queryParam("searchGranularity", searchGranularity);
        verify(resultingWebTarget).queryParam("searchGroupUsage", searchGroupUsage);
    }

    @Test
    public void shouldWrapExceptionOccurringDuringResolvingTemplate() {
        String id = "id";
        when(mockZoneGetQueryUsageCommand.getId()).thenReturn(id);
        RuntimeException mockRuntimeException = mock(RuntimeException.class);
        when(mockZoneGetQueryUsageTarget.resolveTemplate("zoneId", id))
                .thenThrow(mockRuntimeException);

        thrown.expect(new BaseExceptionMatcher(DNSAPIClientException.class,
                DNSAPIClientException.DNSAPIClientExceptionCode.requiredParameterMissing,
                new Object[]{"id"}, mockRuntimeException));

        zoneGetQueryUsageCommandHandler.getWebTarget(
                mockZoneGetQueryUsageCommand, mockCommandMetaData);
    }

    @Test
    public void shouldThrowWrapExceptionIfSearchStartDateIsMissing() {
        String id = "id";
        LocalDateTime searchEndDate = LocalDateTime.now(DateTimeZone.UTC);
        ZoneQueryUsageGranularity searchGranularity = ZoneQueryUsageGranularity.daily;
        Boolean searchGroupUsage = true;
        when(mockZoneGetQueryUsageCommand.getId()).thenReturn(id);
        when(mockZoneGetQueryUsageCommand.getSearchStartDate()).thenReturn(null);
        when(mockZoneGetQueryUsageCommand.getSearchEndDate()).thenReturn(searchEndDate);
        when(mockZoneGetQueryUsageCommand.getSearchGranularity()).thenReturn(searchGranularity);
        when(mockZoneGetQueryUsageCommand.isSearchGroupUsage()).thenReturn(searchGroupUsage);
        WebTarget resultingWebTarget = mock(WebTarget.class);
        when(mockZoneGetQueryUsageTarget.resolveTemplate("zoneId", id))
                .thenReturn(resultingWebTarget);
        when(resultingWebTarget.queryParam("searchStartDate", (LocalDateTime) null))
                .thenReturn(resultingWebTarget);
        when(resultingWebTarget.queryParam("searchEndDate", searchEndDate))
                .thenReturn(resultingWebTarget);
        when(resultingWebTarget.queryParam("searchGranularity", searchGranularity))
                .thenReturn(resultingWebTarget);
        when(resultingWebTarget.queryParam("searchGroupUsage", searchGroupUsage))
                .thenReturn(resultingWebTarget);

        thrown.expect(new BaseExceptionMatcher(DNSAPIClientException.class,
                DNSAPIClientException.DNSAPIClientExceptionCode.requiredParameterMissing,
                new Object[]{"searchStartDate"}, (Throwable) null));

        zoneGetQueryUsageCommandHandler.getWebTarget(
                        mockZoneGetQueryUsageCommand, mockCommandMetaData);
    }

    @Test
    public void shouldThrowWrapExceptionIfSearchEndDateIsMissing() {
        String id = "id";
        LocalDateTime searchStartDate = LocalDateTime.now(DateTimeZone.UTC);
        ZoneQueryUsageGranularity searchGranularity = ZoneQueryUsageGranularity.daily;
        Boolean searchGroupUsage = true;
        when(mockZoneGetQueryUsageCommand.getId()).thenReturn(id);
        when(mockZoneGetQueryUsageCommand.getSearchStartDate()).thenReturn(searchStartDate);
        when(mockZoneGetQueryUsageCommand.getSearchEndDate()).thenReturn(null);
        when(mockZoneGetQueryUsageCommand.getSearchGranularity()).thenReturn(searchGranularity);
        when(mockZoneGetQueryUsageCommand.isSearchGroupUsage()).thenReturn(searchGroupUsage);
        WebTarget resultingWebTarget = mock(WebTarget.class);
        when(mockZoneGetQueryUsageTarget.resolveTemplate("zoneId", id))
                .thenReturn(resultingWebTarget);
        when(resultingWebTarget.queryParam("searchStartDate", searchStartDate))
                .thenReturn(resultingWebTarget);
        when(resultingWebTarget.queryParam("searchEndDate", (LocalDateTime) null))
                .thenReturn(resultingWebTarget);
        when(resultingWebTarget.queryParam("searchGranularity", searchGranularity))
                .thenReturn(resultingWebTarget);
        when(resultingWebTarget.queryParam("searchGroupUsage", searchGroupUsage))
                .thenReturn(resultingWebTarget);

        thrown.expect(new BaseExceptionMatcher(DNSAPIClientException.class,
                DNSAPIClientException.DNSAPIClientExceptionCode.requiredParameterMissing,
                new Object[]{"searchEndDate"}, (Throwable) null));

        zoneGetQueryUsageCommandHandler.getWebTarget(
                mockZoneGetQueryUsageCommand, mockCommandMetaData);
    }

    @Test
    public void shouldThrowWrapExceptionIfSearchGranularityIsMissing() {
        String id = "id";
        LocalDateTime searchStartDate = LocalDateTime.now(DateTimeZone.UTC);
        LocalDateTime searchEndDate = LocalDateTime.now(DateTimeZone.UTC);
        Boolean searchGroupUsage = true;
        when(mockZoneGetQueryUsageCommand.getId()).thenReturn(id);
        when(mockZoneGetQueryUsageCommand.getSearchStartDate()).thenReturn(searchStartDate);
        when(mockZoneGetQueryUsageCommand.getSearchEndDate()).thenReturn(searchEndDate);
        when(mockZoneGetQueryUsageCommand.getSearchGranularity()).thenReturn(null);
        when(mockZoneGetQueryUsageCommand.isSearchGroupUsage()).thenReturn(searchGroupUsage);
        WebTarget resultingWebTarget = mock(WebTarget.class);
        when(mockZoneGetQueryUsageTarget.resolveTemplate("zoneId", id))
                .thenReturn(resultingWebTarget);
        when(resultingWebTarget.queryParam("searchStartDate", searchStartDate))
                .thenReturn(resultingWebTarget);
        when(resultingWebTarget.queryParam("searchEndDate", searchEndDate))
                .thenReturn(resultingWebTarget);
        when(resultingWebTarget.queryParam("searchGranularity", (ZoneQueryUsageGranularity) null))
                .thenReturn(resultingWebTarget);
        when(resultingWebTarget.queryParam("searchGroupUsage", searchGroupUsage))
                .thenReturn(resultingWebTarget);

        thrown.expect(new BaseExceptionMatcher(DNSAPIClientException.class,
                DNSAPIClientException.DNSAPIClientExceptionCode.requiredParameterMissing,
                new Object[]{"searchGranularity"}, (Throwable) null));

        zoneGetQueryUsageCommandHandler.getWebTarget(
                mockZoneGetQueryUsageCommand, mockCommandMetaData);
    }

    @Test
    public void shouldThrowWrapExceptionIfSearchGroupUsageIsMissing() {
        String id = "id";
        LocalDateTime searchStartDate = LocalDateTime.now(DateTimeZone.UTC);
        LocalDateTime searchEndDate = LocalDateTime.now(DateTimeZone.UTC);
        ZoneQueryUsageGranularity searchGranularity = ZoneQueryUsageGranularity.daily;
        when(mockZoneGetQueryUsageCommand.getId()).thenReturn(id);
        when(mockZoneGetQueryUsageCommand.getSearchStartDate()).thenReturn(searchStartDate);
        when(mockZoneGetQueryUsageCommand.getSearchEndDate()).thenReturn(searchEndDate);
        when(mockZoneGetQueryUsageCommand.getSearchGranularity()).thenReturn(searchGranularity);
        when(mockZoneGetQueryUsageCommand.isSearchGroupUsage()).thenReturn(null);
        WebTarget resultingWebTarget = mock(WebTarget.class);
        when(mockZoneGetQueryUsageTarget.resolveTemplate("zoneId", id))
                .thenReturn(resultingWebTarget);
        when(resultingWebTarget.queryParam("searchStartDate", searchStartDate))
                .thenReturn(resultingWebTarget);
        when(resultingWebTarget.queryParam("searchEndDate", searchEndDate))
                .thenReturn(resultingWebTarget);
        when(resultingWebTarget.queryParam("searchGranularity", searchGranularity))
                .thenReturn(resultingWebTarget);
        when(resultingWebTarget.queryParam("searchGroupUsage", (Boolean) null))
                .thenReturn(resultingWebTarget);

        thrown.expect(new BaseExceptionMatcher(DNSAPIClientException.class,
                DNSAPIClientException.DNSAPIClientExceptionCode.requiredParameterMissing,
                new Object[]{"searchGroupUsage"}, (Throwable) null));

        zoneGetQueryUsageCommandHandler.getWebTarget(
                mockZoneGetQueryUsageCommand, mockCommandMetaData);
    }

    @Test
    public void shouldReturnNoEntityInvocationBuilderFactory() {
        assertThat(
                zoneGetQueryUsageCommandHandler.getInvocationBuilderFactory(
                        mockZoneGetQueryUsageCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuilderFactory.class));
    }

    @Test
    public void shouldReturnNoEntityInvocationBuildInvoker() {
        assertThat(
                zoneGetQueryUsageCommandHandler.getInvocationBuildInvoker(
                        mockZoneGetQueryUsageCommand, mockCommandMetaData),
                instanceOf(NoEntityInvocationBuildInvoker.class));
    }

    @Test
    public void shouldReturnExpectedZoneGetQueryUsageResponse() {
        Response mockRestResponse = mock(Response.class);
        ZoneGetQueryUsageView mockZoneGetQueryUsageView = mock(ZoneGetQueryUsageView.class);
        when(mockRestResponse.hasEntity()).thenReturn(true);
        when(mockRestResponse.readEntity(ZoneGetQueryUsageView.class)).thenReturn(mockZoneGetQueryUsageView);
        URI mockURI = URI.create("http://ddns.com/zones/1/queryUsage");
        when(mockZoneGetQueryUsageView.getUri()).thenReturn(mockURI);

        ZoneGetQueryUsageResponse commandResponse =
                zoneGetQueryUsageCommandHandler.getCommandResponse(
                        mockRestResponse, mockCommandMetaData);

        assertEquals(mockURI, commandResponse.getURI());
    }
}
