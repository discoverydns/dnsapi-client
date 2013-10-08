package com.discoverydns.dnsapiclient.command.nameServerSet;

import java.net.URI;
import java.util.List;

import com.discoverydns.dnsapiclient.internal.views.NameServerSetListView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("NameServerSetListResponse")
@JsonPropertyOrder({ "@uri", "nameServerSetList", "totalCount" })
public class NameServerSetListResponse {

	@JsonIgnore
	private final NameServerSetListView nameServerSetListView;

	public NameServerSetListResponse(
			final NameServerSetListView nameServerSetListView) {
		this.nameServerSetListView = nameServerSetListView;
	}

	@JsonProperty("@uri")
	public URI getURI() {
		return nameServerSetListView.getUri();
	}

	@JsonProperty("nameServerSetList")
	public List<NameServerSetRecord> getNameServerSetRecords() {
		return nameServerSetListView.getNameServerSetList();
	}

	@JsonProperty("totalCount")
	public long getTotalRecordCount() {
		return nameServerSetListView.getTotalCount();
	}

}
