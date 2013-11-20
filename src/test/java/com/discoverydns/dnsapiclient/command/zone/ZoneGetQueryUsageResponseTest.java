package com.discoverydns.dnsapiclient.command.zone;

import com.discoverydns.dnsapiclient.internal.views.ZoneGetQueryUsageView;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.net.URI;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ZoneGetQueryUsageResponseTest {
    @Test
    public void shouldReturnExpectedResponse() {
        ZoneGetQueryUsageView mockZoneGetQueryUsageView = mock(ZoneGetQueryUsageView.class);
        URI mockURI = URI.create("http://ddns.com/zones/1/queryUsage");
        when(mockZoneGetQueryUsageView.getUri()).thenReturn(mockURI);
        long totalRecordCount = 3L;
        when(mockZoneGetQueryUsageView.getTotalRecordCount()).thenReturn(totalRecordCount);
        ZoneQueryUsageRecord mockFirstZoneQueryUsageRecord = mock(ZoneQueryUsageRecord.class);
        ZoneQueryUsageRecord mockSecondZoneQueryUsageRecord = mock(ZoneQueryUsageRecord.class);
        List<ZoneQueryUsageRecord> listZoneQueryUsageRecords =
                Lists.newArrayList(mockFirstZoneQueryUsageRecord,
                        mockSecondZoneQueryUsageRecord);
        when(mockZoneGetQueryUsageView.getZoneQueryUsageRecords()).thenReturn(listZoneQueryUsageRecords);

        ZoneGetQueryUsageResponse zoneGetQueryUsageResponse =
                new ZoneGetQueryUsageResponse(mockZoneGetQueryUsageView);

        assertEquals(mockURI, zoneGetQueryUsageResponse.getURI());
        assertEquals(totalRecordCount, zoneGetQueryUsageResponse.getTotalRecordCount());
        assertEquals(listZoneQueryUsageRecords, zoneGetQueryUsageResponse.getZoneQueryUsageRecords());
    }
}
