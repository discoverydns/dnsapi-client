package com.discoverydns.dnsapiclient.command.message;

/**
 * Type of the polled Message, which then infers on the actual Message contents' data structure.
 *
 * A Message is a notification from the DNSAPI server, for the targeted sponsor Account's Users,
 * offering information about changes performed by the server on a related object in the system.
 *
 * @author Arnaud Dumont
 */
public enum MessageType {
    /**
     * Message received when a Zone has been successfully DNSSEC-signed,
     * subsequently to either a {@link com.discoverydns.dnsapiclient.command.zone.ZoneDNSSECSignCommand},
     * a {@link com.discoverydns.dnsapiclient.command.zone.ZoneUpdateCommand}
     * or a {@link com.discoverydns.dnsapiclient.command.zone.ZoneUpdateResourceRecordsCommand}.
     * The message contents will then be
     * of type {@link com.discoverydns.dnsapiclient.command.message.contents.ZoneDNSSECSigningCompletedMessageContents}.
     */
    zoneDNSSECSigningCompleted,
    /**
     * Message received when an error occurred while DNSSEC-signing a Zone,
     * subsequently to either a {@link com.discoverydns.dnsapiclient.command.zone.ZoneDNSSECSignCommand},
     * a {@link com.discoverydns.dnsapiclient.command.zone.ZoneUpdateCommand}
     * or a {@link com.discoverydns.dnsapiclient.command.zone.ZoneUpdateResourceRecordsCommand}.
     * The message contents will then be
     * of type {@link com.discoverydns.dnsapiclient.command.message.contents.ZoneDNSSECSigningFailedMessageContents}.
     */
    zoneDNSSECSigningFailed
}
