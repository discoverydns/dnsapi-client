package com.discoverydns.dnsapiclient.internal.views.response;

import java.net.URI;
import java.util.List;

import com.discoverydns.dnsapiclient.command.nameServerSet.NameServerSetRecord;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("nameServerSets")
public class NameServerSetListView {

	@JsonProperty("@uri")
	private URI uri;
	@JsonProperty("nameServerSetList")
	private List<NameServerSetRecord> nameServerSetList;
	@JsonProperty("totalCount")
	private long totalCount;

	public URI getUri() {
		return uri;
	}

	public List<NameServerSetRecord> getNameServerSetList() {
		return nameServerSetList;
	}

	public long getTotalCount() {
		return totalCount;
	}

}
