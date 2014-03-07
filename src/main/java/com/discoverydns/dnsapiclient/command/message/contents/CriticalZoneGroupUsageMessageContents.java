package com.discoverydns.dnsapiclient.command.message.contents;

import com.discoverydns.dnsapiclient.command.message.MessageType;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Contents for a {@link com.discoverydns.dnsapiclient.command.message.Message}
 * of type {@link com.discoverydns.dnsapiclient.command.message.MessageType}.criticalZoneGroupUsage.
 *
 * @author Arnaud Dumont
 */
public class CriticalZoneGroupUsageMessageContents extends MessageContents {
    @JsonProperty("zoneGroup")
    private String zoneGroup;
    @JsonProperty("currentZoneGroupUsage")
    private long currentZoneGroupUsage;
    @JsonProperty("zoneGroupUsageLimit")
    private long zoneGroupUsageLimit;

    public CriticalZoneGroupUsageMessageContents() {
        super(MessageType.criticalZoneGroupUsage);
    }

    public String getZoneGroup() {
        return zoneGroup;
    }

    public long getCurrentZoneGroupUsage() {
        return currentZoneGroupUsage;
    }

    public long getZoneGroupUsageLimit() {
        return zoneGroupUsageLimit;
    }
}
