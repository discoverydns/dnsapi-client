package com.discoverydns.dnsapiclient.command.message.contents;

import com.discoverydns.dnsapiclient.command.message.MessageType;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Contents for a {@link com.discoverydns.dnsapiclient.command.message.Message}
 * of type {@link com.discoverydns.dnsapiclient.command.message.MessageType}.zoneDNSSECSigningFailed.
 *
 * @author Arnaud Dumont
 */
public class ZoneDNSSECSigningFailedMessageContents extends MessageContents {
    @JsonProperty("zoneId")
    private String zoneId;
    @JsonProperty("zoneName")
    private String zoneName;
    @JsonProperty("errorMessage")
    private String errorMessage;

    public ZoneDNSSECSigningFailedMessageContents() {
        super(MessageType.zoneDNSSECSigningFailed);
    }

    public String getZoneId() {
        return zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
