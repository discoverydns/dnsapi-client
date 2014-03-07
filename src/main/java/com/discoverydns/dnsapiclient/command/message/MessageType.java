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
     * Message received when a Zone has been successfully DNSSEC-signed.
     * The message contents will then be
     * of type {@link com.discoverydns.dnsapiclient.command.message.contents.ZoneDNSSECSigningCompletedMessageContents}.
     */
    zoneDNSSECSigningCompleted,
    /**
     * Message received when an error occurred while DNSSEC-signing a Zone.
     * The message contents will then be
     * of type {@link com.discoverydns.dnsapiclient.command.message.contents.ZoneDNSSECSigningFailedMessageContents}.
     */
    zoneDNSSECSigningFailed,
    /**
     * Message received when a Zone usage has reached the Warning threshold.
     * The message contents will then be
     * of type {@link com.discoverydns.dnsapiclient.command.message.contents.WarningZoneUsageMessageContents}.
     */
    warningZoneUsage,
    /**
     * Message received when a Zone usage has reached the Critical threshold.
     * The message contents will then be
     * of type {@link com.discoverydns.dnsapiclient.command.message.contents.CriticalZoneUsageMessageContents}.
     */
    criticalZoneUsage,
    /**
     * Message received when a Zone usage has exceeded its limit.
     * The message contents will then be
     * of type {@link com.discoverydns.dnsapiclient.command.message.contents.OverLimitZoneUsageMessageContents}.
     */
    overLimitZoneUsage,
    /**
     * Message received when a Zone group usage has reached the Warning threshold.
     * The message contents will then be
     * of type {@link com.discoverydns.dnsapiclient.command.message.contents.WarningZoneGroupUsageMessageContents}.
     */
    warningZoneGroupUsage,
    /**
     * Message received when a Zone usage has reached the Critical threshold.
     * The message contents will then be
     * of type {@link com.discoverydns.dnsapiclient.command.message.contents.CriticalZoneGroupUsageMessageContents}.
     */
    criticalZoneGroupUsage,
    /**
     * Message received when a Zone usage has exceeded its limit.
     * The message contents will then be
     * of type {@link com.discoverydns.dnsapiclient.command.message.contents.OverLimitZoneGroupUsageMessageContents}.
     */
    overLimitZoneGroupUsage
}
