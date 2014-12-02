package com.discoverydns.dnsapiclient.command.zone;

import java.net.URI;
import java.util.List;

import com.discoverydns.dnsapiclient.internal.views.response.ZoneListView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Response object received by the {@link com.discoverydns.dnsapiclient.DNSAPIClient}
 * from the DNSAPI server subsequently to the sending of a {@link ZoneListCommand},
 * returning the list of retrieved {@link ZoneRecord}s.
 *
 * A Zone, belonging to an Account, will be managed by the DNSAPI architecture
 * for domain names resolution purpose.
 *
 * @author Chris Wright
 */
@JsonRootName("ZoneListResponse")
@JsonPropertyOrder({ "@uri", "zoneList", "totalCount" })
public class ZoneListResponse {

	@JsonIgnore
	private final ZoneListView zoneListView;

	public ZoneListResponse(final ZoneListView zoneListView) {
		this.zoneListView = zoneListView;
	}

    /**
     * @return The URI representing the search query on the DNSAPI server
     */
	@JsonProperty("@uri")
	public URI getURI() {
		return zoneListView.getUri();
	}

    /**
     * @return The list of retrieved {@link ZoneRecord}s
     */
	@JsonProperty("zoneList")
	public List<ZoneRecord> getZoneRecords() {
		return zoneListView.getZoneList();
	}

    /**
     * @return The total count of {@link ZoneRecord}s that would match the query, if ignoring the pagination limitations.
     */
	@JsonProperty("totalCount")
	public long getTotalRecordCount() {
		return zoneListView.getTotalCount();
	}

}
