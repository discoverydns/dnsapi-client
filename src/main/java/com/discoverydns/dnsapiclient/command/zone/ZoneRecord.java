package com.discoverydns.dnsapiclient.command.zone;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.joda.time.LocalDateTime;

/**
 * List record representing a Zone, from the list returned in the {@link ZoneListResponse}.
 *
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
	@JsonProperty("createDate")
	private LocalDateTime createDate;
	@JsonProperty("lastUpdateDate")
	private LocalDateTime lastUpdateDate;

	public ZoneRecord() {
	}

	public ZoneRecord(URI uri, String id, String name,
			Boolean brandedNameServers, Boolean dnssecSigned,
            LocalDateTime createDate, LocalDateTime lastUpdateDate) {
		this.uri = uri;
		this.id = id;
		this.name = name;
		this.brandedNameServers = brandedNameServers;
		this.dnssecSigned = dnssecSigned;
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
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((brandedNameServers == null) ? 0 : brandedNameServers
						.hashCode());
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((dnssecSigned == null) ? 0 : dnssecSigned.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastUpdateDate == null) ? 0 : lastUpdateDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
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
		if (!(obj instanceof ZoneRecord)) {
			return false;
		}
		ZoneRecord other = (ZoneRecord) obj;
		if (brandedNameServers == null) {
			if (other.brandedNameServers != null) {
				return false;
			}
		} else if (!brandedNameServers.equals(other.brandedNameServers)) {
			return false;
		}
		if (createDate == null) {
			if (other.createDate != null) {
				return false;
			}
		} else if (!createDate.equals(other.createDate)) {
			return false;
		}
		if (dnssecSigned == null) {
			if (other.dnssecSigned != null) {
				return false;
			}
		} else if (!dnssecSigned.equals(other.dnssecSigned)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (lastUpdateDate == null) {
			if (other.lastUpdateDate != null) {
				return false;
			}
		} else if (!lastUpdateDate.equals(other.lastUpdateDate)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (uri == null) {
			if (other.uri != null) {
				return false;
			}
		} else if (!uri.equals(other.uri)) {
			return false;
		}
		return true;
	}

}