package com.discoverydns.dnsapiclient.command.zone;

import java.net.URI;
import java.util.Objects;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * List record representing a Zone, from the list returned in the {@link ZoneListResponse}.
 * <p/>
 * A Zone, belonging to an Account, will be managed by the DNSAPI architecture
 * for domain names resolution purpose.
 *
 * @author Chris Wright
 */
@JsonRootName("zone")
public class ZoneRecord {

    @JsonProperty("@uri")
    private URI uri;
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("brandedNameServers")
    private Boolean brandedNameServers;
    @JsonProperty("dnssecSigned")
    private Boolean dnssecSigned;
    @JsonProperty("xfrEnabled")
    private Boolean xfrEnabled;
    @JsonProperty("createDate")
    private LocalDateTime createDate;
    @JsonProperty("lastUpdateDate")
    private LocalDateTime lastUpdateDate;

    public ZoneRecord() {
    }

    public ZoneRecord(URI uri, String id, String name, Boolean brandedNameServers, Boolean dnssecSigned,
                      Boolean xfrEnabled, LocalDateTime createDate, LocalDateTime lastUpdateDate) {
        this.uri = uri;
        this.id = id;
        this.name = name;
        this.brandedNameServers = brandedNameServers;
        this.dnssecSigned = dnssecSigned;
        this.xfrEnabled = xfrEnabled;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * @return The URI representing the Zone list record on the DNSAPI server
     */
    public URI getUri() {
        return uri;
    }

    /**
     * @return The Zone UUID
     */
    public String getId() {
        return id;
    }

    /**
     * @return The Zone name
     */
    public String getName() {
        return name;
    }

    /**
     * @return true if the Zone uses branded nameServers, false otherwise
     */
    public Boolean getBrandedNameServers() {
        return brandedNameServers;
    }

    /**
     * @return true if the Zone is DNSSEC signed, false otherwise
     */
    public Boolean getDnssecSigned() {
        return dnssecSigned;
    }

    /**
     * @return true if the Zone is XFR enabled, false otherwise
     */
    public Boolean getXfrEnabled() {
        return xfrEnabled;
    }

    /**
     * @return The creation date of the Zone
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * @return The last update date of the Zone
     */
    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, uri, brandedNameServers, dnssecSigned, xfrEnabled, createDate, lastUpdateDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ZoneRecord)) {
            return false;
        }
        ZoneRecord other = (ZoneRecord) obj;
        return Objects.equals(this.id, other.id)
                && Objects.equals(this.name, other.name)
                && Objects.equals(this.uri, other.uri)
                && Objects.equals(this.brandedNameServers, other.brandedNameServers)
                && Objects.equals(this.dnssecSigned, other.dnssecSigned)
                && Objects.equals(this.xfrEnabled, other.xfrEnabled)
                && Objects.equals(this.createDate, other.createDate)
                && Objects.equals(this.lastUpdateDate, other.lastUpdateDate);
    }

}
