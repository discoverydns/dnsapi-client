package com.discoverydns.dnsapiclient.command.zone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * List record representing a Zone query usage for a particular time period,
 * from the list returned in the {@link ZoneGetQueryUsageResponse}.
 *
 * A Zone, belonging to an Account, will be managed by the DNSAPI architecture
 * for domain names resolution purpose.
 *
 * @author Arnaud Dumont
 */
@JsonRootName("zoneQueryUsageRecord")
public class ZoneQueryUsageRecord {
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("count")
    private Long count;

    public ZoneQueryUsageRecord() {
    }

    public ZoneQueryUsageRecord(String timestamp, Long count) {
        this.timestamp = timestamp;
        this.count = count;
    }

    /**
     * @return The start date of the time period the query usage record is for
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @return The count of queries for this zone received by the system during the time period
     */
    public Long getCount() {
        return count;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime
                * result
                + ((timestamp == null) ? 0 : timestamp.hashCode());
        result = prime * result
                + ((count == null) ? 0 : count.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ZoneQueryUsageRecord)) {
            return false;
        }
        ZoneQueryUsageRecord other = (ZoneQueryUsageRecord) obj;
        if (timestamp == null) {
            if (other.timestamp != null) {
                return false;
            }
        } else if (!timestamp.equals(other.timestamp)) {
            return false;
        }
        if (count == null) {
            if (other.count != null) {
                return false;
            }
        } else if (!count.equals(other.count)) {
            return false;
        }
        return true;
    }
}
