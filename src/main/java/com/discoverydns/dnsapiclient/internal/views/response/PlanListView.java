package com.discoverydns.dnsapiclient.internal.views.response;

import java.net.URI;
import java.util.List;

import com.discoverydns.dnsapiclient.command.plan.PlanRecord;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("plans")
public class PlanListView {

	@JsonProperty("@uri")
	private URI uri;
	@JsonProperty("planList")
	private List<PlanRecord> planList;
	@JsonProperty("totalCount")
	private long totalCount;

	public URI getUri() {
		return uri;
	}

	public List<PlanRecord> getPlanList() {
		return planList;
	}

	public long getTotalCount() {
		return totalCount;
	}

}
