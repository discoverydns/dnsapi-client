package com.discoverydns.dnsapiclient.command.message.contents;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.xbill.DNS.Record;

import java.util.Set;

/**
 * Contents for a {@link com.discoverydns.dnsapiclient.command.message.Message}
 * of type {@link com.discoverydns.dnsapiclient.command.message.MessageType}.zoneDNSSECSigningCompleted.
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
