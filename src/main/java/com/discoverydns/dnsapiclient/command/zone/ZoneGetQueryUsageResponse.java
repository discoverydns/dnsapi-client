package com.discoverydns.dnsapiclient.command.zone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.net.URI;
import java.util.List;

import com.discoverydns.dnsapiclient.internal.views.response.ZoneGetQueryUsageView;

/**
 * Response object received by the {@link com.discoverydns.dnsapiclient.DNSAPIClient}
 * from the DNSAPI server subsequently to the sending of a {@link ZoneGetQueryUsageCommand},
 * describing the details of query usage of the specified Zone.
 *
 * A Zone, belonging to an Account, will be managed by the DNSAPI architecture
 * for domain names resolution purpose.
 *
 * @author Arnaud Dumont
 */
@JsonRootName("ZoneGetQueryUsageResponse")
@JsonPropertyOrder({ "@uri", "zoneQueryUsageRecords", "totalRecordCount" })
public class ZoneGetQueryUsageResponse {
    @JsonIgnore
    private final ZoneGetQueryUsageView zoneGetQueryUsageView;

    public ZoneGetQueryUsageResponse(final ZoneGetQueryUsageView zoneGetQueryUsageView) {
        this.zoneGetQueryUsageView = zoneGetQueryUsageView;
    }

    /**
     * @return The URI representing the retrieved query usage records for the Zone on the DNSAPI server
     */
    @JsonProperty("@uri")
    public URI getURI() {
        return zoneGetQueryUsageView.getUri();
    }

    /**
     * @return The list of retrieved query usage records for the Zone on the DNSAPI server
     */
    @JsonProperty("zoneQueryUsageRecords")
    public List<ZoneQueryUsageRecord> getZoneQueryUsageRecords() {
        return zoneGetQueryUsageView.getZoneQueryUsageRecords();
    }

    /**
     * @return The total count of retrieved query usage records for the Zone on the DNSAPI server
     */
    @JsonProperty("totalRecordCount")
    public long getTotalRecordCount() {
        return zoneGetQueryUsageView.getTotalRecordCount();
    }
}
