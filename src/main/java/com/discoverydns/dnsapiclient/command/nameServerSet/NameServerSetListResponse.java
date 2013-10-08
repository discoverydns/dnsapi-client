package com.discoverydns.dnsapiclient.command.nameServerSet;

import java.net.URI;
import java.util.List;

import com.discoverydns.dnsapiclient.internal.views.NameServerSetListView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Response object received by the {@link com.discoverydns.dnsapiclient.DNSAPIClient}
 * from the DNSAPI server subsequently to the sending of a {@link NameServerSetListCommand},
 * returning the list of retrieved {@link NameServerSetRecord}s.
 *
 * A NameServer Set represents a logical entity of the DNSAPI architecture,
 * to link an Account with a NameServer Interface Set,
 * through the configuration of the Name Server names that will be provided to the Account's Zones.
 *
 * @author Chris Wright
 */
@JsonRootName("NameServerSetListResponse")
@JsonPropertyOrder({ "@uri", "nameServerSetList", "totalCount" })
public class NameServerSetListResponse {

	@JsonIgnore
	private final NameServerSetListView nameServerSetListView;

	public NameServerSetListResponse(
			final NameServerSetListView nameServerSetListView) {
		this.nameServerSetListView = nameServerSetListView;
	}

    /**
     * @return The URI representing the search query on the DNSAPI server
     */
	@JsonProperty("@uri")
	public URI getURI() {
		return nameServerSetListView.getUri();
	}

    /**
     * @return The list of retrieved {@link NameServerSetRecord}s
     */
	@JsonProperty("nameServerSetList")
	public List<NameServerSetRecord> getNameServerSetRecords() {
		return nameServerSetListView.getNameServerSetList();
	}

    /**
     * @return The total count of {@link NameServerSetRecord}s that would match the query,
     * if ignoring the pagination limitations.
     */
	@JsonProperty("totalCount")
	public long getTotalRecordCount() {
		return nameServerSetListView.getTotalCount();
	}

}
