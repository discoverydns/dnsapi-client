package com.discoverydns.dnsapiclient.command.zone;

/**
 * Search granularity to query the zone query usage records, in the {@link ZoneGetQueryUsageCommand}.
 *
 * A Zone, belonging to an Account, will be managed by the DNSAPI architecture
 * for domain names resolution purpose.
 *
 * @author Arnaud Dumont
 */
public enum ZoneQueryUsageGranularity {
    /**
     * Queries will be grouped by month
     */
    monthly,
    /**
     * Queries will be grouped by day
     */
    daily,
    /**
     * Queries will be grouped by hour
     */
    hourly
}
