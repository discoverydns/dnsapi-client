package com.discoverydns.dnsapiclient.internal.views.response;

import com.discoverydns.dnsapiclient.command.zone.ZoneQueryUsageRecord;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.net.URI;
import java.util.List;

@JsonRootName("zoneGetQueryUsage")
public class ZoneGetQueryUsageView {
    @JsonProperty("@uri")
    private URI uri;
    @JsonProperty("zoneQueryUsageRecords")
    public List<ZoneQueryUsageRecord> zoneQueryUsageRecords;
    @JsonProperty("totalRecordCount")
    public long totalRecordCount;

    public URI getUri() {
        return uri;
    }

    public List<ZoneQueryUsageRecord> getZoneQueryUsageRecords() {
        return zoneQueryUsageRecords;
    }

    public long getTotalRecordCount() {
        return totalRecordCount;
    }
}
