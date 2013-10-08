package com.discoverydns.dnsapiclient.command.plan;

import java.net.URI;
import java.util.List;

import com.discoverydns.dnsapiclient.internal.views.PlanListView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("PlanListResponse")
@JsonPropertyOrder({ "@uri", "planList", "totalCount" })
public class PlanListResponse {

	@JsonIgnore
	private final PlanListView planListView;

	public PlanListResponse(final PlanListView planListView) {
		this.planListView = planListView;
	}

	@JsonProperty("@uri")
	public URI getURI() {
		return planListView.getUri();
	}

	@JsonProperty("planList")
	public List<PlanRecord> getPlanRecords() {
		return planListView.getPlanList();
	}

	@JsonProperty("totalCount")
	public long getTotalRecordCount() {
		return planListView.getTotalCount();
	}

}
