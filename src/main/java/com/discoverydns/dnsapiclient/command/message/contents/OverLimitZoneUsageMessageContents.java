package com.discoverydns.dnsapiclient.command.message.contents;

import com.discoverydns.dnsapiclient.command.message.MessageType;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Contents for a {@link com.discoverydns.dnsapiclient.command.message.Message}
 * of type {@link com.discoverydns.dnsapiclient.command.message.MessageType}.overLimitZoneUsage.
 *
 * @author Arnaud Dumont
 */
public class OverLimitZoneUsageMessageContents extends MessageContents {
    @JsonProperty("zoneId")
    private String zoneId;
    @JsonProperty("zoneName")
    private String zoneName;
    @JsonProperty("currentZoneUsage")
    private long currentZoneUsage;
    @JsonProperty("zoneUsageLimit")
    private long zoneUsageLimit;

    public OverLimitZoneUsageMessageContents() {
        super(MessageType.overLimitZoneUsage);
    }

    public String getZoneId() {
        return zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public long getCurrentZoneUsage() {
        return currentZoneUsage;
    }

    public long getZoneUsageLimit() {
        return zoneUsageLimit;
    }
}
