package com.discoverydns.dnsapiclient.internal.views.response;

import java.net.URI;
import java.util.List;

import com.discoverydns.dnsapiclient.command.zone.ZoneRecord;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("zones")
public class ZoneListView {

	@JsonProperty("@uri")
	private URI uri;
	@JsonProperty("zoneList")
	private List<ZoneRecord> zoneList;
	@JsonProperty("totalCount")
	private long totalCount;

	public URI getUri() {
		return uri;
	}

	public List<ZoneRecord> getZoneList() {
		return zoneList;
	}

	public long getTotalCount() {
		return totalCount;
	}

}
