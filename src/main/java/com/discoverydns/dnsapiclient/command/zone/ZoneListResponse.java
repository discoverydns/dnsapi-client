package com.discoverydns.dnsapiclient.command.zone;

import java.net.URI;
import java.util.List;

import com.discoverydns.dnsapiclient.internal.views.ZoneListView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("zoneListResponse")
@JsonPropertyOrder({ "@uri", "zoneList", "totalCount" })
public class ZoneListResponse {

	@JsonIgnore
	private final ZoneListView zoneListView;

	public ZoneListResponse(final ZoneListView zoneListView) {
		this.zoneListView = zoneListView;
	}

	@JsonProperty("@uri")
	public URI getURI() {
		return zoneListView.getUri();
	}

	@JsonProperty("zoneList")
	public List<ZoneRecord> getZoneRecords() {
		return zoneListView.getZoneList();
	}

	@JsonProperty("totalCount")
	public long getTotalRecordCount() {
		return zoneListView.getTotalCount();
	}

}
